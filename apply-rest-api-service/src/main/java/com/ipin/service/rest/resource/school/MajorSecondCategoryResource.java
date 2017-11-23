package com.ipin.service.rest.resource.school;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.ipin.service.rest.beans.MajorSecondCategoryDetailResult;
import com.ipin.service.rest.beans.RecommendSchListResult;
import com.ipin.service.rest.errorhandling.IllegalParameterException;
import com.ipin.service.rest.errorhandling.MissingParameterException;
import com.ipin.service.rest.service.MajorSecondCategoryService;
import com.ipin.service.rest.utils.ParameterUtils;


@Component
@Path("/major_second_category")
public class MajorSecondCategoryResource {
	
	@Autowired
	private MajorSecondCategoryService majorSecondCategoryService;
	
	@GET
	@Path("{major_second_category_name}/detail")
	@Produces({ MediaType.APPLICATION_JSON })
	@Cacheable(value = "major_second_category", cacheManager = "cacheManager", keyGenerator = "keyGenerator")
	/**
	 * 获取专业二级目录详情
	 * @param majorSecondCategoryName 专业二级目录名字 必填
	 * @param diplomaId 学历ID 必填
	 * @return
	 */
	public MajorSecondCategoryDetailResult getMajorSecondCategoryDetail(
			@PathParam("major_second_category_name") String majorSecondCategoryName,
			@QueryParam("diploma_id") Integer diplomaId) {
		// 检测必要参数
		if (StringUtils.isBlank(majorSecondCategoryName) || diplomaId == null) {
			throw new MissingParameterException("Parameters is not set, please check.");
		}
		
		// 检测学历
		if (!ParameterUtils.isDiplomaId(diplomaId)) {
			throw new IllegalParameterException("Parameter diploma_id  is error, please check.");
		}
		
		return majorSecondCategoryService.getMajorSecondCategoryDetailResult(majorSecondCategoryName, diplomaId);
	}
	
	@GET
	@Path("{major_second_category_name}/recommend_schools")
	@Produces({ MediaType.APPLICATION_JSON })
	@Cacheable(value = "sch_recommend_schools", cacheManager = "cacheManager", keyGenerator = "keyGenerator")
	/**
	 * 获取学校推荐列表结果
	 * @param majorSecondCategoryName 专业二级目录名字
	 * @param diplomaId 学历ID
	 * @return
	 */
	public RecommendSchListResult getRecommendSchList(
			@PathParam("major_second_category_name") String majorSecondCategoryName,
			@QueryParam("diploma_id") Integer diplomaId) {
		// 检测必要参数
		if (StringUtils.isBlank(majorSecondCategoryName) || diplomaId == null) {
			throw new MissingParameterException("Parameters is not set, please check.");
		}

		// 检测学历
		if (!ParameterUtils.isDiplomaId(diplomaId)) {
			throw new IllegalParameterException("Parameter diploma_id  is error, please check.");
		}
		
		return majorSecondCategoryService.getRecommendSchListResult(majorSecondCategoryName, diplomaId);
	}

}
