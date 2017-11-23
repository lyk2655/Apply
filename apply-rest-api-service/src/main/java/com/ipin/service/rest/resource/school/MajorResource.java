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

import com.ipin.service.rest.beans.MajorDetailResult;
import com.ipin.service.rest.beans.MajorFilterListResult;
import com.ipin.service.rest.beans.MajorFilterParamsResult;
import com.ipin.service.rest.beans.MajorNameListResult;
import com.ipin.service.rest.beans.MajorSecondCategoryNameListResult;
import com.ipin.service.rest.beans.RecommendSchListResult;
import com.ipin.service.rest.errorhandling.IllegalParameterException;
import com.ipin.service.rest.errorhandling.MissingParameterException;
import com.ipin.service.rest.service.MajorService;
import com.ipin.service.rest.utils.ParameterUtils;

@Component
@Path("/major")
public class MajorResource {
	
	@Autowired
	private MajorService majorService;
	
	@GET
	@Path("{major_id}/detail")
	@Produces({ MediaType.APPLICATION_JSON })
	@Cacheable(value = "major", cacheManager = "cacheManager", keyGenerator = "keyGenerator")
	/**
	 * 获取专业详情
	 * @param majorId 专业ID 必填
	 * @param diplomaId 学历ID 必填
	 * @return
	 */
	public MajorDetailResult getMajorDetail(@PathParam("major_id") String majorId,
			@QueryParam("diploma_id") Integer diplomaId) {
		// 检测必要参数
		if (StringUtils.isBlank(majorId) || diplomaId == null) {
			throw new MissingParameterException("Parameters is not set, please check.");
		}

		// 检测学历
		if (!ParameterUtils.isDiplomaId(diplomaId)) {
			throw new IllegalParameterException("Parameter diploma_id  is error, please check.");
		}
		
		return majorService.getMajorDetailResult(majorId, diplomaId);
	}
	
	@GET
	@Path("{major_id}/recommend_schools")
	@Produces({ MediaType.APPLICATION_JSON })
	@Cacheable(value = "sch_recommend_schools", cacheManager = "cacheManager", keyGenerator = "keyGenerator")
	/**
	 * 获取推荐学校列表结果
	 * @param majorId 专业ID 必填
	 * @param diplomaId 学历ID 必填
	 * @return
	 */
	public RecommendSchListResult getRecommendSchoolList(@PathParam("major_id") String majorId,
			@QueryParam("diploma_id") Integer diplomaId) {
		// 检测必要参数
		if (StringUtils.isBlank(majorId) || diplomaId == null) {
			throw new MissingParameterException("Parameters is not set, please check.");
		}

		// 检测学历
		if (!ParameterUtils.isDiplomaId(diplomaId)) {
			throw new IllegalParameterException("Parameter diploma_id  is error, please check.");
		}
		
		return majorService.getRecommendSchoolListResult(majorId, diplomaId);
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Cacheable(value = "major_filter", cacheManager = "cacheManager", keyGenerator = "keyGenerator")
	/**
	 * 获取专业筛选列表
	 * @param diplomaId 学历ID 选填
	 * @param majorCategory 专业一级目录 选填
	 * @param sortBy 排序 选填
	 * @param count 单页返回的记录条数 选填 默认20条
	 * @param pageNo 请求的页码 选填 默认第1页
	 * @return
	 */
	public MajorFilterListResult getMajorFilterList(@QueryParam("diploma_id") Integer diplomaId,
			@QueryParam("major_category") String majorCategory, @QueryParam("sort_by") String sortBy,
			@QueryParam("count") Integer count, @QueryParam("page_no") Integer pageNo) {

		// 检测学历
		if (diplomaId != null && !ParameterUtils.isDiplomaId(diplomaId)) {
			throw new IllegalParameterException("Parameter diploma_id  is error, please check.");
		}
		
		// 检测排序类型
		if (!StringUtils.isBlank(sortBy) && !ParameterUtils.isMajorFilterListSortType(sortBy)) {
			throw new IllegalParameterException("Parameter sort_by  is error, please check.");
		}
		
		// 检测数量
		if(count != null && count <= 0) {
			throw new IllegalParameterException("Parameter count  is error, please check.");
		}
		
		// 检测页码
		if (pageNo != null && pageNo <= 0) {
			throw new IllegalParameterException("Parameter page_no  is error, please check.");
		}
		
		return majorService.getMajorFilterListResult(diplomaId, majorCategory, sortBy, count, pageNo);
	}
	
	@GET
	@Path("/category")
	@Produces({ MediaType.APPLICATION_JSON })
	@Cacheable(value = "major_filter_params", cacheManager = "cacheManager", keyGenerator = "keyGenerator")
	/**
	 * 获取专业过滤参数
	 * @param diplomaId 学历ID 必填
	 * @param categoryLevel 专业目录级别 必填
	 * @return
	 */
	public MajorFilterParamsResult getMajorFilterParams(@QueryParam("diploma_id") Integer diplomaId,
			@QueryParam("category_level") Integer categoryLevel) {
		// 检测必要参数
		if (diplomaId == null || categoryLevel == null) {
			throw new MissingParameterException("Parameters is not set, please check.");
		}
		
		// 检测学历
		if (!ParameterUtils.isDiplomaId(diplomaId)) {
			throw new IllegalParameterException("Parameter diploma_id  is error, please check.");
		}
		
		// 检测数量
		if (!ParameterUtils.isMajorCateLevel(categoryLevel)) {
			throw new IllegalParameterException("Parameter category_level  is error, please check.");
		}
		
		return majorService.getMajorFilterParamsResult(diplomaId, categoryLevel);
	}
	
	@GET
	@Path("category/{major_category_name}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Cacheable(value = "major_category_name", cacheManager = "cacheManager", keyGenerator = "keyGenerator")
	/**
	 * 获取专业二级目录名字列表结果
	 * @param majorCategoryName 专业目录名字 必填
	 * @param diplomaId 学历ID 必填
	 * @return
	 */
	public MajorSecondCategoryNameListResult getMajorSecondCategoryNameList(
			@PathParam("major_category_name") String majorCategoryName, @QueryParam("diploma_id") Integer diplomaId) {
		// 检测必要参数
		if (StringUtils.isBlank(majorCategoryName) || diplomaId == null) {
			throw new MissingParameterException("Parameters is not set, please check.");
		}
		
		// 检测学历
		if (!ParameterUtils.isDiplomaId(diplomaId)) {
			throw new IllegalParameterException("Parameter diploma_id  is error, please check.");
		}
				
		return majorService.getMajorSecondCategoryNameListResult(majorCategoryName, diplomaId);
	}
	
	@GET
	@Path("second_category/{major_second_category_name}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Cacheable(value = "major_name", cacheManager = "cacheManager", keyGenerator = "keyGenerator")
	/**
	 * 获取专业列表结果
	 * @param majorSecondCategoryName 专业二级目录名字 必填
	 * @param diplomaId 学历ID 必填
	 * @return
	 */
	public MajorNameListResult getMajorNameList(@PathParam("major_second_category_name") String majorSecondCategoryName,
			@QueryParam("diploma_id") Integer diplomaId) {
		if (StringUtils.isBlank(majorSecondCategoryName) || diplomaId == null) {
			throw new MissingParameterException("Parameters is not set, please check.");
		}
		
		// 检测学历
		if (!ParameterUtils.isDiplomaId(diplomaId)) {
			throw new IllegalParameterException("Parameter diploma_id  is error, please check.");
		}
		
		return majorService.getMajorNameListResult(majorSecondCategoryName, diplomaId);
	}
	
}
