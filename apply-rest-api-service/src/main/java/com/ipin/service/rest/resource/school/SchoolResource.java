package com.ipin.service.rest.resource.school;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.ipin.service.rest.beans.SchDetailResult;
import com.ipin.service.rest.beans.SchEnrollPlanListResult;
import com.ipin.service.rest.beans.SchEnrollPlanParamsResult;
import com.ipin.service.rest.beans.SchFilterListResult;
import com.ipin.service.rest.beans.SchMajorRankingListResult;
import com.ipin.service.rest.beans.SchMajorScoreListResult;
import com.ipin.service.rest.beans.SchMajorScoreParamsResult;
import com.ipin.service.rest.beans.SchMajorSettingListResult;
import com.ipin.service.rest.beans.SchRankingListResult;
import com.ipin.service.rest.beans.SchScoreListResult;
import com.ipin.service.rest.beans.SchScoreParamsResult;
import com.ipin.service.rest.errorhandling.IllegalParameterException;
import com.ipin.service.rest.errorhandling.MissingParameterException;
import com.ipin.service.rest.service.SchoolService;
import com.ipin.service.rest.utils.ParameterUtils;

@Component
@Path("/school")
public class SchoolResource {
	private static final Logger logger = LoggerFactory.getLogger(SchoolResource.class);

	@Autowired
	private SchoolService schoolService;

	// /************************************ READ
	// ************************************/
	@GET
	@Path("{sch_id}/detail")
	@Produces({ MediaType.APPLICATION_JSON })
	@Cacheable(value = "sch_school", cacheManager = "cacheManager", keyGenerator = "keyGenerator")
	/**
	 * 获取学校详情
	 * 
	 * @param schId 必填 学校ID
	 * @param diplomaId 必填 学历ID
	 * @return 学校详情结果
	 */
	public SchDetailResult getSchoolDetail(@PathParam("sch_id") String schId,
			@QueryParam("diploma_id") Integer diplomaId) {
		// 检测必要参数
		if (StringUtils.isBlank(schId) || diplomaId == null) {
			throw new MissingParameterException("Parameters is not set, please check.");
		}

		// 检测学历
		if (!ParameterUtils.isDiplomaId(diplomaId)) {
			throw new IllegalParameterException("Parameter diploma_id  is error, please check.");
		}

		return schoolService.getSchoolDetail(schId, diplomaId);
	}

	@GET
	@Path("{sch_id}/majors")
	@Produces({ MediaType.APPLICATION_JSON })
	/**
	 * 获取专业设置列表
	 * 
	 * @param schId 必填 学校ID
	 * @param diplomaId 必填 学历id，例如7为本科/5专科等
	 * @param majorType 必填 0为在招专业，1为各省在招专业, 2为全部专业（含已停办）
	 * @param provinceFilter 选填 城市名字筛选
	 * @param wenliFilter 选填 文理筛选，"wen"为文科，"li"为理科
	 * @param yearFilter 选填 招生年份筛选，例如"2015"
	 * @return 专业设置列表结果
	 */
	@Cacheable(value = "sch_major_setting", cacheManager = "cacheManager", keyGenerator = "keyGenerator")
	public Object getSchMajorSettingList(@PathParam("sch_id") String schId, @QueryParam("diploma_id") Integer diplomaId,
			@QueryParam("major_type") Integer majorType, @QueryParam("province_filter") String provinceFilter,
			@QueryParam("wenli_filter") String wenliFilter, @QueryParam("year_filter") String yearFilter) {
		// 检测必要参数
		if (StringUtils.isBlank(schId) || diplomaId == null || majorType == null) {
			throw new MissingParameterException("Parameters is not set, please check.");
		}

		// 检测学历
		if (!ParameterUtils.isDiplomaId(diplomaId)) {
			throw new IllegalParameterException("Parameter diploma_id  is error, please check.");
		}

		// 专业设置类型
		if (!ParameterUtils.isMajorSettingType(majorType)) {
			throw new IllegalParameterException("Parameter major_type is error, please check.");
		}

		// 文理过滤
		if (!StringUtils.isBlank(wenliFilter) && !ParameterUtils.isWenLi(wenliFilter)) {
			throw new IllegalParameterException("Parameter wenli_filter is error, please check.");
		}

		// 年份过滤
		if (!StringUtils.isBlank(yearFilter) && !ParameterUtils.isNum(yearFilter)) {
			throw new IllegalParameterException("Parameter year_filter is error, please check.");
		}

		SchMajorSettingListResult result = schoolService.getSchMajorSettingListResult(schId, diplomaId, majorType,
				provinceFilter, wenliFilter, yearFilter);
		return result;
	}

	@GET
	@Path("{sch_id}/zs_plan_params")
	@Produces({ MediaType.APPLICATION_JSON })
	@Cacheable(value = "sch_enroll_plan_params", cacheManager = "cacheManager", keyGenerator = "keyGenerator")
	/**
	 * 获取学校招生计划参数
	 * 
	 * @param schId 必填 学校ID
	 * @param diplomaId 必填 学历ID
	 * @return
	 */
	public SchEnrollPlanParamsResult getSchEnrollPlanParamsBySchId(@PathParam("sch_id") String schId,
			@QueryParam("diploma_id") Integer diplomaId) {
		if (StringUtils.isBlank(schId) || diplomaId == null) {
			throw new MissingParameterException("Parameters is not set, please check.");
		}

		// 检测学历
		if (!ParameterUtils.isDiplomaId(diplomaId)) {
			throw new IllegalParameterException("Parameter diploma_id  is error, please check.");
		}

		return schoolService.getSchEnrollPlanParams(schId, diplomaId);
	}

	@GET
	@Path("zs_plan_params")
	@Produces({ MediaType.APPLICATION_JSON })
	@Cacheable(value = "sch_enroll_plan_params", cacheManager = "cacheManager", keyGenerator = "keyGenerator")
	public SchEnrollPlanParamsResult getAllSchEnrollPlanParams(@QueryParam("diploma_id") Integer diplomaId) {
		if (diplomaId == null || !ParameterUtils.isDiplomaId(diplomaId)) {
			throw new IllegalParameterException("Parameter diploma_id  is error, please check.");
		}

		return schoolService.getSchEnrollPlanParams(null, diplomaId);
	}

	@GET
	@Path("rank")
	@Produces({ MediaType.APPLICATION_JSON })
	@Cacheable(value = "sch_ranking_list", cacheManager = "cacheManager", keyGenerator = "keyGenerator")
	/**
	 * 获取学校排行榜信息
	 * 
	 * @param rankType 必填 榜单类型，有“就业综合排名”榜（请求参数："zonghe"），“知名度排名”榜（请求参数："zhimingdu"）， “竞争力排名”榜（请求参数："jingzhengli"）， “薪酬排名”（请求参数："xinchou"），“妹纸最多”（请求参数："meizhi"）
	 * @param provinceFilter 选填 省份筛选, 省份名字，例如"广东"。如果不限，不设置该参数
	 * @param schNamePattern 选填 学校名筛选，在满足前面几个筛选条件下的所有学校名部分匹配，例如该参数为“武汉”，会出现“武汉大学”，“武汉科技大学”等大学
	 * @param pageSize 选填 单页返回的记录条数，默认20
	 * @param curPage 选填 请求的页码，默认第1页
	 * @return
	 */
	public SchRankingListResult getSchRankingList(@QueryParam("rank_type") String rankType,
			@QueryParam("province_filter") String provinceFilter, @QueryParam("sch_name_pattern") String schNamePattern,
			@QueryParam("count") Integer pageSize, @QueryParam("page_no") Integer curPage) {
		// 检测必要参数
		if (StringUtils.isBlank(rankType)) {
			throw new MissingParameterException("Parameters is not set, please check.");
		}

		// 判断是否是排行榜类型
		if (!ParameterUtils.isSchRankingListType(rankType)) {
			throw new IllegalParameterException("Parameter rank_type  is error, please check.");
		}

		return schoolService.getSchRankingListResult(rankType, provinceFilter, schNamePattern, pageSize, curPage);
	}

	@GET
	@Path("{sch_id}/major_rank/by_salary")
	@Produces({ MediaType.APPLICATION_JSON })
	@Cacheable(value = "sch_major_ranking_list", cacheManager = "cacheManager", keyGenerator = "keyGenerator")
	/**
	 * 获取薪酬学校专业排行榜
	 * 
	 * @param schId 必填 学校ID
	 * @param diplomaId 必填 学历ID
	 * @return
	 */
	public SchMajorRankingListResult getSchMajorRankingListBySalary(@PathParam("sch_id") String schId,
			@QueryParam("diploma_id") Integer diplomaId) {
		// 检测必要参数
		if (StringUtils.isBlank(schId) || diplomaId == null) {
			throw new MissingParameterException("Parameters is not set, please check.");
		}

		// 检测学历
		if (!ParameterUtils.isDiplomaId(diplomaId)) {
			throw new IllegalParameterException("Parameter diploma_id  is error, please check.");
		}

		return schoolService.getSchMajorRankingListResultBySalary(schId, diplomaId);
	}

	@GET
	@Path("{sch_id}/major_rank/by_gender")
	@Produces({ MediaType.APPLICATION_JSON })
	@Cacheable(value = "sch_major_ranking_list", cacheManager = "cacheManager", keyGenerator = "keyGenerator")
	/**
	 * 获取女性比例学校专业排行榜
	 * 
	 * @param schId 必填 学校ID
	 * @param diplomaId 必填 学历ID
	 * @return
	 */
	public SchMajorRankingListResult getSchMajorRankingListByGender(@PathParam("sch_id") String schId,
			@QueryParam("diploma_id") Integer diplomaId) {
		// 检测必要参数
		if (StringUtils.isBlank(schId) || diplomaId == null) {
			throw new MissingParameterException("Parameters is not set, please check.");
		}

		// 检测学历
		if (!ParameterUtils.isDiplomaId(diplomaId)) {
			throw new IllegalParameterException("Parameter diploma_id  is error, please check.");
		}

		return schoolService.getSchMajorRankingListResultByGender(schId, diplomaId);
	}

	@GET
	@Path("{sch_id}/major_rank/by_salary_growth")
	@Produces({ MediaType.APPLICATION_JSON })
	@Cacheable(value = "sch_major_ranking_list", cacheManager = "cacheManager", keyGenerator = "keyGenerator")
	/**
	 * 获取薪酬增长学校专业排行榜
	 * 
	 * @param schId 必填 学校ID
	 * @param diplomaId 必填 学历ID
	 * @return
	 */
	public SchMajorRankingListResult getSchMajorRankingListBySalaryGrowth(@PathParam("sch_id") String schId,
			@QueryParam("diploma_id") Integer diplomaId) {
		// 检测必要参数
		if (StringUtils.isBlank(schId) || diplomaId == null) {
			throw new MissingParameterException("Parameters is not set, please check.");
		}

		// 检测学历
		if (!ParameterUtils.isDiplomaId(diplomaId)) {
			throw new IllegalParameterException("Parameter diploma_id  is error, please check.");
		}

		return schoolService.getSchMajorRankingListResultBySalaryGrowth(schId, diplomaId);
	}

	@GET
	@Path("{sch_id}/major_rank/by_luqu_score")
	@Produces({ MediaType.APPLICATION_JSON })
	@Cacheable(value = "sch_major_ranking_list", cacheManager = "cacheManager", keyGenerator = "keyGenerator")
	/**
	 * 获取录取分学校专业排行榜
	 * 
	 * @param schId 必填 学校ID
	 * @param diplomaId 必填 学历ID
	 * @param wenliFilter 必填 文理筛选，"wen"为文科，"li"为理科
	 * @param provinceFilter 必填 省份筛选, 省份id，例如"110000000000"
	 * @param yearFilter 必填 年份筛选，例如 "2015"
	 * @return
	 */
	public SchMajorRankingListResult getSchMajorRankingListByScore(@PathParam("sch_id") String schId,
			@QueryParam("diploma_id") Integer diplomaId, @QueryParam("wenli_filter") String wenliFilter,
			@QueryParam("province_id_filter") String provinceIdFilter, @QueryParam("year_filter") String yearFilter) {
		// 检测必要参数
		if (StringUtils.isBlank(schId) || diplomaId == null || StringUtils.isBlank(wenliFilter)
				|| StringUtils.isBlank(provinceIdFilter) || StringUtils.isBlank(yearFilter)) {
			throw new MissingParameterException("Parameters is not set, please check.");
		}

		// 检测学历
		if (!ParameterUtils.isDiplomaId(diplomaId)) {
			throw new IllegalParameterException("Parameter diploma_id  is error, please check.");
		}

		// 文理过滤
		if (!ParameterUtils.isWenLi(wenliFilter)) {
			throw new IllegalParameterException("Parameter wenli_filter is error, please check.");
		}

		// 年份过滤
		if (!ParameterUtils.isNum(yearFilter)) {
			throw new IllegalParameterException("Parameter year_filter is error, please check.");
		}

		return schoolService.getSchMajorRankingListResultByScore(schId, diplomaId, wenliFilter, provinceIdFilter,
				yearFilter);
	}

	@GET
	@Path("{sch_id}/luqu_scores/in_school")
	@Produces({ MediaType.APPLICATION_JSON })
	@Cacheable(value = "sch_score", cacheManager = "cacheManager", keyGenerator = "keyGenerator")
	/**
	 * 获取学校录取分数
	 * 
	 * @param schId 必填 学校ID
	 * @param diplomaId 必填 学历ID
	 * @param provinceFilter 必填 省份筛选, 省份id，例如"110000000000"
	 * @param wenliFilter 必填 文理筛选，"wen"为文科，"li"为理科
	 * @return
	 */
	public SchScoreListResult getSchScoreList(@PathParam("sch_id") String schId,
			@QueryParam("diploma_id") Integer diplomaId, @QueryParam("province_id_filter") String provinceIdFilter,
			@QueryParam("wenli_filter") String wenliFilter) {
		// 检测必要参数
		if (StringUtils.isBlank(schId) || diplomaId == null || StringUtils.isBlank(provinceIdFilter)
				|| StringUtils.isBlank(wenliFilter)) {
			throw new MissingParameterException("Parameters is not set, please check.");
		}

		// 检测学历
		if (!ParameterUtils.isDiplomaId(diplomaId)) {
			throw new IllegalParameterException("Parameter diploma_id  is error, please check.");
		}

		// 文理过滤
		if (!ParameterUtils.isWenLi(wenliFilter)) {
			throw new IllegalParameterException("Parameter wenli_filter is error, please check.");
		}

		return schoolService.getSchScoreListResult(schId, diplomaId, provinceIdFilter, wenliFilter);
	}

	@GET
	@Path("{sch_id}/luqu_scores/in_school_params")
	@Produces({ MediaType.APPLICATION_JSON })
	@Cacheable(value = "sch_score_params", cacheManager = "cacheManager", keyGenerator = "keyGenerator")
	/**
	 * 获取学校录取分参数结果
	 * 
	 * @param schId 必填 学校ID
	 * @param diplomaId 必填 学历ID
	 * @return
	 */
	public SchScoreParamsResult getSchScoreParams(@PathParam("sch_id") String schId,
			@QueryParam("diploma_id") Integer diplomaId) {
		// 检测必要参数
		if (StringUtils.isBlank(schId) || diplomaId == null) {
			throw new MissingParameterException("Parameters is not set, please check.");
		}

		// 检测学历
		if (!ParameterUtils.isDiplomaId(diplomaId)) {
			throw new IllegalParameterException("Parameter diploma_id  is error, please check.");
		}

		return schoolService.getSchScoreParamsResult(schId, diplomaId);
	}

	@GET
	@Path("{sch_id}/luqu_scores/in_major")
	@Produces({ MediaType.APPLICATION_JSON })
	@Cacheable(value = "sch_major_score", cacheManager = "cacheManager", keyGenerator = "keyGenerator")
	public SchMajorScoreListResult getSchMajorScoreList(@PathParam("sch_id") String schId,
			@QueryParam("diploma_id") Integer diplomaId, @QueryParam("wenli_filter") String wenliFilter,
			@QueryParam("province_id_filter") String provinceIdFilter, @QueryParam("year_filter") String yearFilter) {
		// 检测必要参数
		if (StringUtils.isBlank(schId) || diplomaId == null || StringUtils.isBlank(wenliFilter)
				|| StringUtils.isBlank(provinceIdFilter) || StringUtils.isBlank(yearFilter)) {
			throw new MissingParameterException("Parameters is not set, please check.");
		}

		// 检测学历
		if (!ParameterUtils.isDiplomaId(diplomaId)) {
			throw new IllegalParameterException("Parameter diploma_id  is error, please check.");
		}

		// 文理过滤
		if (!ParameterUtils.isWenLi(wenliFilter)) {
			throw new IllegalParameterException("Parameter wenli_filter is error, please check.");
		}

		// 年份过滤
		if (!ParameterUtils.isNum(yearFilter)) {
			throw new IllegalParameterException("Parameter year_filter is error, please check.");
		}

		return schoolService.getSchMajorScoreListResult(schId, diplomaId, wenliFilter, provinceIdFilter, yearFilter);
	}

	@GET
	@Path("{sch_id}/luqu_scores/in_major_params")
	@Produces({ MediaType.APPLICATION_JSON })
	@Cacheable(value = "sch_major_score_params", cacheManager = "cacheManager", keyGenerator = "keyGenerator")
	/**
	 * 获取学校录取分参数结果
	 * 
	 * @param schId 必填 学校ID
	 * @param diplomaId 必填 学历ID
	 * @return
	 */
	public SchMajorScoreParamsResult getSchMajorScoreParams(@PathParam("sch_id") String schId,
			@QueryParam("diploma_id") Integer diplomaId) {
		// 检测必要参数
		if (StringUtils.isBlank(schId) || diplomaId == null) {
			throw new MissingParameterException("Parameters is not set, please check.");
		}

		// 检测学历
		if (!ParameterUtils.isDiplomaId(diplomaId)) {
			throw new IllegalParameterException("Parameter diploma_id  is error, please check.");
		}

		return schoolService.getSchMajorScoreParamsResult(schId, diplomaId);
	}

	@GET
	@Path("{sch_id}/zs_plan")
	@Produces({ MediaType.APPLICATION_JSON })
	@Cacheable(value = "sch_enroll_plan", cacheManager = "cacheManager", keyGenerator = "keyGenerator")
	/**
	 * 获取学校招生计划列表
	 * 
	 * @param schId 学校ID
	 * @param diplomaId 学历ID
	 * @param provinceFilter 省份过滤
	 * @param wenliFilter 文理过滤
	 * @param yearFilter 年份过滤
	 * @return
	 */
	public SchEnrollPlanListResult getSchEnrollPlanList(@PathParam("sch_id") String schId,
			@QueryParam("diploma_id") Integer diplomaId, @QueryParam("province_filter") String provinceFilter,
			@QueryParam("wenli_filter") String wenliFilter, @QueryParam("year_filter") String yearFilter) {
		// 检测必要参数
		if (StringUtils.isBlank(schId) || diplomaId == null || StringUtils.isBlank(provinceFilter)
				|| StringUtils.isBlank(wenliFilter) || StringUtils.isBlank(yearFilter)) {
			throw new MissingParameterException("Parameters is not set, please check.");
		}

		// 检测学历
		if (!ParameterUtils.isDiplomaId(diplomaId)) {
			throw new IllegalParameterException("Parameter diploma_id  is error, please check.");
		}

		// 文理过滤
		if (!ParameterUtils.isWenLi(wenliFilter)) {
			throw new IllegalParameterException("Parameter wenli_filter is error, please check.");
		}

		// 年份过滤
		if (!ParameterUtils.isNum(yearFilter)) {
			throw new IllegalParameterException("Parameter year_filter is error, please check.");
		}

		return schoolService.getSchEnrollPlanListResult(schId, diplomaId, provinceFilter, wenliFilter, yearFilter);
	}

	@GET
	@Path("")
	@Produces({ MediaType.APPLICATION_JSON })
	@Cacheable(value = "sch_filter", cacheManager = "cacheManager", keyGenerator = "keyGenerator")
	/**
	 * 获取学校筛选列表结果
	 * 
	 * @param provinceFilter 选填 省份
	 * @param diplomaId 选填 学历ID
	 * @param schoolType 选填 学校类型
	 * @param majorSecondCategoryFilter 选填 专业二级目录
	 * @param majorFilter 选填 专业id过滤
	 * @param schNamePattern 选填 名字部分匹配
	 * @param sortBy 必填 排序方式
	 * @param count 选填 每页显示记录条数 默认20
	 * @param pageNo 选填 请求页码 默认第1页
	 * @return
	 */
	public SchFilterListResult getSchFilterList(@QueryParam("province_filter") String provinceFilter,
			@QueryParam("diploma_id") Integer diplomaId, @QueryParam("school_type") String schoolType,
			@QueryParam("major_second_category_filter") String majorSecondCategoryFilter,
			@QueryParam("major_filter") String majorFilter, @QueryParam("sch_name_pattern") String schNamePattern,
			@QueryParam("sort_by") String sortBy, @QueryParam("count") Integer count,
			@QueryParam("page_no") Integer pageNo) {

		// 检测必要参数
		if (StringUtils.isBlank(sortBy) || diplomaId == null) {
			throw new MissingParameterException("Parameters is not set, please check.");
		}

		// 检测学历
		if (!ParameterUtils.isDiplomaId(diplomaId)) {
			throw new IllegalParameterException("Parameter diploma_id  is error, please check.");
		}

		// 检测排序方式
		if (!ParameterUtils.isSchFilterListSortType(sortBy)) {
			throw new IllegalParameterException("Parameter sort_by  is error, please check.");
		}

		// 检测数量
		if (count != null && count <= 0) {
			throw new IllegalParameterException("Parameter count  is error, please check.");
		}

		// 检测页码
		if (pageNo != null && pageNo <= 0) {
			throw new IllegalParameterException("Parameter pageNo  is error, please check.");
		}

		return schoolService.getSchFilterListResult(provinceFilter, diplomaId, schoolType, majorSecondCategoryFilter,
				majorFilter, schNamePattern, sortBy, count, pageNo);
	}

}