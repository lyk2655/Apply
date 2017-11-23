package com.ipin.service.rest.filters;

import java.io.IOException;

import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.StringUtils;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ipin.service.rest.errorhandling.ResourceForbiddenException;
import com.ipin.service.rest.utils.LoggerUtils;
import com.ipin.service.rest.utils.ParameterUtils;
import com.ipin.service.rest.utils.RegexUtils;
import com.ipin.thrift.edu.EduInfoService;
import com.ipin.thrift.edu.commons.EDU_DIPLOMA;
import com.ipin.thrift.edu.params.FindSchIdBaseInfoParams;

/**
 * 检测学校IDbase是否存在
 * @author zhongyongsheng
 *
 */
@Provider
@Path("/school")
public class CheckSchIdBaseRequestFilter implements ContainerRequestFilter{
	
	public static Logger logger = LoggerFactory.getLogger("CheckSchIdBaseRequestFilter");
	
	@Autowired
	private EduInfoService.Iface eduInfoService;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String schId = requestContext.getUriInfo().getPathParameters().getFirst("sch_id");
		String diplomaId = requestContext.getUriInfo().getQueryParameters().getFirst("diploma_id");
		if(StringUtils.isBlank(schId) || StringUtils.isBlank(diplomaId) || !ParameterUtils.isNum(diplomaId)) {
			return;// 参数缺失, 交由下面resource层处理
		}
		
		// 查询是否有diploma_id
		FindSchIdBaseInfoParams findSchIdBaseInfoParams = new FindSchIdBaseInfoParams();
		findSchIdBaseInfoParams.setSchId(schId);
		findSchIdBaseInfoParams.setDiplomaId(EDU_DIPLOMA.findByValue(Integer.parseInt(diplomaId)));
		try {
			if (eduInfoService.findSchIdBaseInfo(findSchIdBaseInfoParams) != null) {
				// 可以进行展示, 也交由下面resource层处理
				return;
			}
		} catch (TException e) {
			LoggerUtils.logEduTExceptionAndThrowAppException(logger, e);
		}
		
		// 说明学校ID没有在schidbase基准表中找到,不允许展示
		throw new ResourceForbiddenException();
		
	}

}
