package com.ipin.service.rest.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;

import com.ipin.service.rest.beans.CompetitivenessSchRankingList;
import com.ipin.service.rest.beans.FamousSchRankingList;
import com.ipin.service.rest.beans.GenderDist;
import com.ipin.service.rest.beans.GenderSchMajorRankingList;
import com.ipin.service.rest.beans.GenderSchRankingList;
import com.ipin.service.rest.beans.IndustryDist;
import com.ipin.service.rest.beans.LocationDist;
import com.ipin.service.rest.beans.PredictSalaryStat;
import com.ipin.service.rest.beans.SalaryGrowthSchMajorRankingList;
import com.ipin.service.rest.beans.SalarySchMajorRankingList;
import com.ipin.service.rest.beans.SalarySchRankingList;
import com.ipin.service.rest.beans.SalaryStat;
import com.ipin.service.rest.beans.SchDetailResult;
import com.ipin.service.rest.beans.SchEnrollPlan;
import com.ipin.service.rest.beans.SchEnrollPlanListResult;
import com.ipin.service.rest.beans.SchEnrollPlanParamsResult;
import com.ipin.service.rest.beans.SchFilter;
import com.ipin.service.rest.beans.SchFilterListResult;
import com.ipin.service.rest.beans.SchMajorRankingListResult;
import com.ipin.service.rest.beans.SchMajorScore;
import com.ipin.service.rest.beans.SchMajorScoreListResult;
import com.ipin.service.rest.beans.SchMajorScoreParamsResult;
import com.ipin.service.rest.beans.SchMajorSetting;
import com.ipin.service.rest.beans.SchMajorSettingListResult;
import com.ipin.service.rest.beans.SchRankingListResult;
import com.ipin.service.rest.beans.SchScore;
import com.ipin.service.rest.beans.SchScoreDetail;
import com.ipin.service.rest.beans.SchScoreListResult;
import com.ipin.service.rest.beans.SchScoreParamsResult;
import com.ipin.service.rest.beans.ScoreSchMajorRankingList;
import com.ipin.service.rest.beans.TotalSchRankingList;
import com.ipin.service.rest.beans.impl.MajorResult;
import com.ipin.service.rest.constants.Constants;
import com.ipin.service.rest.service.SchoolService;
import com.ipin.service.rest.utils.LocationUtils;
import com.ipin.service.rest.utils.LoggerUtils;
import com.ipin.service.rest.utils.ParameterUtils;
import com.ipin.service.rest.utils.EduStatUtils;
import com.ipin.thrift.common.CommonService;
import com.ipin.thrift.common.IndustryInfo;
import com.ipin.thrift.common.ProvinceInfo;
import com.ipin.thrift.edu.CurrentLocationInfo;
import com.ipin.thrift.edu.EduEnrollService;
import com.ipin.thrift.edu.EduInfoService;
import com.ipin.thrift.edu.EduScoreService;
import com.ipin.thrift.edu.EduStatService;
import com.ipin.thrift.edu.FemaleRank;
import com.ipin.thrift.edu.GenderInfo;
import com.ipin.thrift.edu.IndustryDistInfo;
import com.ipin.thrift.edu.MajorCateInfo;
import com.ipin.thrift.edu.MajorIdBaseInfo;
import com.ipin.thrift.edu.MajorInfo;
import com.ipin.thrift.edu.MajorSecondCateIdBaseInfo;
import com.ipin.thrift.edu.PredictSalaryInfo;
import com.ipin.thrift.edu.ProvinceToudangScoreInfo;
import com.ipin.thrift.edu.SalaryDetailInfo;
import com.ipin.thrift.edu.SalaryGrowRatioRank;
import com.ipin.thrift.edu.SalaryRank;
import com.ipin.thrift.edu.SchAreaSalaryStatInfo;
import com.ipin.thrift.edu.SchEnrollPlanDistinctAttribute;
import com.ipin.thrift.edu.SchEnrollPlanInfo;
import com.ipin.thrift.edu.SchIdBaseInfo;
import com.ipin.thrift.edu.SchInfo;
import com.ipin.thrift.edu.SchMajorRankingList;
import com.ipin.thrift.edu.SchMajorScoreDistinctAttribute;
import com.ipin.thrift.edu.SchMajorScoreRankingList;
import com.ipin.thrift.edu.SchMajorScoreStatInfo;
import com.ipin.thrift.edu.SchMajorSecondCateStatMarkInfo;
import com.ipin.thrift.edu.SchMajorStatInfo;
import com.ipin.thrift.edu.SchMajorStatMarkInfo;
import com.ipin.thrift.edu.SchRankingList;
import com.ipin.thrift.edu.SchRankingListPage;
import com.ipin.thrift.edu.SchScoreDistinctAttribute;
import com.ipin.thrift.edu.SchScoreStatInfo;
import com.ipin.thrift.edu.SchStatInfo;
import com.ipin.thrift.edu.SchStatMarkInfo;
import com.ipin.thrift.edu.ScoreRank;
import com.ipin.thrift.edu.commons.EDU_DIPLOMA;
import com.ipin.thrift.edu.commons.EDU_ENROLL_LOCATION;
import com.ipin.thrift.edu.commons.EDU_SCH_RANKING_LIST_TYPE;
import com.ipin.thrift.edu.commons.EDU_TYPE_WENLI;
import com.ipin.thrift.edu.params.FindMajorIdBaseInfoParams;
import com.ipin.thrift.edu.params.FindMajorSecondCateIdBaseInfoParams;
import com.ipin.thrift.edu.params.FindProvinceToudangScoreInfoParams;
import com.ipin.thrift.edu.params.FindSchAreaSalaryStatInfoParams;
import com.ipin.thrift.edu.params.FindSchEnrollPlanDistinctAttributeParams;
import com.ipin.thrift.edu.params.FindSchIdBaseInfoParams;
import com.ipin.thrift.edu.params.FindSchInfoParams;
import com.ipin.thrift.edu.params.FindSchMajorRankingListParams;
import com.ipin.thrift.edu.params.FindSchMajorScoreDistinctAttributeParams;
import com.ipin.thrift.edu.params.FindSchMajorScoreRankingListParams;
import com.ipin.thrift.edu.params.FindSchScoreDistinctAttributeParams;
import com.ipin.thrift.edu.params.FindSchStatInfoParams;
import com.ipin.thrift.edu.params.FindSchStatMarkInfoParams;
import com.ipin.thrift.edu.params.ListMajorCateInfoParams;
import com.ipin.thrift.edu.params.ListMajorInfoByIdsParams;
import com.ipin.thrift.edu.params.ListSchEnrollPlanInfoParams;
import com.ipin.thrift.edu.params.ListSchMajorScoreStatInfoParams;
import com.ipin.thrift.edu.params.ListSchMajorSecondCateStatMarkInfoParams;
import com.ipin.thrift.edu.params.ListSchMajorStatInfoParams;
import com.ipin.thrift.edu.params.ListSchMajorStatMarkInfoParams;
import com.ipin.thrift.edu.params.ListSchScoreStatInfoParams;
import com.ipin.thrift.edu.params.PageSchRankingListParams;

/**
 * Created by longman on 1/12/16.
 */

public class SchoolServiceImpl implements SchoolService {
	private static final Logger logger = LoggerFactory.getLogger(SchoolServiceImpl.class);

	@Resource
	private EduInfoService.Iface eduInfoService;
	@Resource
	private EduEnrollService.Iface eduEnrollService;
	@Resource
	private EduStatService.Iface eduStatService;
	@Resource
	private EduScoreService.Iface eduScoreService;
	@Resource
	private CommonService.Iface commonService;

	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate<String, MajorResult> redisTemplate;

	@Override
	public SchDetailResult getSchoolDetail(String schId, int diplomaId) {
		try {
			// 查询学校是否有统计信息
			FindSchStatMarkInfoParams findSchStatMarkInfoParams = new FindSchStatMarkInfoParams();
			findSchStatMarkInfoParams.setSchId(schId);
			SchStatMarkInfo schStatMarkInfo = eduStatService.findSchStatMarkInfo(findSchStatMarkInfoParams);
			if (schStatMarkInfo == null || !schStatMarkInfo.isHasStatData()) {
				// 该学校没有统计信息,不显示
				return null;
			}
			
			// 获取学校统计信息
			FindSchStatInfoParams findSchStatInfoParams = new FindSchStatInfoParams();
			findSchStatInfoParams.setSchId(schId);
			findSchStatInfoParams.setDiploma(EDU_DIPLOMA.findByValue(diplomaId));
			SchStatInfo schStatInfo = eduStatService.findSchStatInfo(findSchStatInfoParams);
			if (schStatInfo == null) {
				return null;
			}
			
			// 获取学校信息
			FindSchInfoParams findSchInfoParams = new FindSchInfoParams();
			findSchInfoParams.setSchId(schId);
			findSchInfoParams.setDiplomaId(EDU_DIPLOMA.findByValue(diplomaId));
			SchInfo schInfo = eduInfoService.findSchInfo(findSchInfoParams);
			if (schInfo == null) {
				return null;
			}
			
			// 获取学校全国薪酬统计信息
			FindSchAreaSalaryStatInfoParams findSchAreaSalaryStatInfoParams = new FindSchAreaSalaryStatInfoParams();
			findSchAreaSalaryStatInfoParams.setAreaId("-1");
			findSchAreaSalaryStatInfoParams.setDiplomaId(EDU_DIPLOMA.findByValue(diplomaId));
			SchAreaSalaryStatInfo countrySalaryStatInfo = eduStatService.findSchAreaSalaryStatInfo(findSchAreaSalaryStatInfoParams);
			
			// 获取学校省份薪酬统计信息
			findSchAreaSalaryStatInfoParams = new FindSchAreaSalaryStatInfoParams();
			findSchAreaSalaryStatInfoParams.setAreaId(schInfo.getProvinceId().substring(0, 2));
			findSchAreaSalaryStatInfoParams.setDiplomaId(EDU_DIPLOMA.findByValue(diplomaId));
			SchAreaSalaryStatInfo provinceSalaryStatInfo = eduStatService.findSchAreaSalaryStatInfo(findSchAreaSalaryStatInfoParams);
			
			// 获取学校全国学校排名信息
			PageSchRankingListParams pageSchRankingListParams = new PageSchRankingListParams();
			pageSchRankingListParams.setDiplomaId(EDU_DIPLOMA.findByValue(diplomaId));
			pageSchRankingListParams.setRankType(EDU_SCH_RANKING_LIST_TYPE.EDU_SCH_RANKING_LIST_TYPE_XINCHOU);
			List<String> schIdList = new ArrayList<String>();
			schIdList.add(schId);
			pageSchRankingListParams.setSchIdList(schIdList);
			pageSchRankingListParams.setPageSize(1);
			pageSchRankingListParams.setCurPage(1);
			SchRankingListPage countrySchRankingListPage = eduStatService.pageSchRankingList(pageSchRankingListParams);
			
			// 获取学校省内学校排名信息
			pageSchRankingListParams = new PageSchRankingListParams();
			pageSchRankingListParams.setDiplomaId(EDU_DIPLOMA.findByValue(diplomaId));
			pageSchRankingListParams.setRankType(EDU_SCH_RANKING_LIST_TYPE.EDU_SCH_RANKING_LIST_TYPE_XINCHOU);
			schIdList = new ArrayList<String>();
			schIdList.add(schId);
			pageSchRankingListParams.setSchIdList(schIdList);
			pageSchRankingListParams.setProvinceIdFilter(schInfo.getProvinceId());
			pageSchRankingListParams.setCurPage(1);
			pageSchRankingListParams.setPageSize(1);
			SchRankingListPage provinceSchRankingListPage = eduStatService.pageSchRankingList(pageSchRankingListParams);
			
			return convertToSchoolDetailResult(schInfo, schStatInfo, countrySalaryStatInfo, provinceSalaryStatInfo, countrySchRankingListPage, provinceSchRankingListPage);
			
		} catch (TException e) {
			LoggerUtils.logEduTExceptionAndThrowAppException(logger, e);
		}
		return null;
	}

	@Override
	public SchMajorSettingListResult getSchMajorSettingListResult(String schId, int diplomaId, int majorType,
			String provinceFilter, String wenliFilter, String yearFilter) {
		try {
			if (Constants.MAJOR_SETTING_ENROLLING_MAJOR == majorType) {
				// 在招专业
				return getSchMajorSettingListResultForEnrollingMajor(schId, diplomaId);
			} else if (Constants.MAJOR_SETTING_ENROLLING_MAJOR_PER_PROVINCE == majorType) {
				// 各省在招专业
				return getSchMajorSettingListResultForEnrollingMajorPerProvince(schId, diplomaId, provinceFilter, wenliFilter, yearFilter);
			} else if (Constants.MAJOR_SETTING_ALL_MAJOR == majorType) {
				// 全部专业
				return getSchMajorSettingListResultForAllMajor(schId, diplomaId);
			}

		} catch (TException e) {
			LoggerUtils.logEduTExceptionAndThrowAppException(logger, e);
		}
		return null;
		
	}
	
	@Override
	public SchEnrollPlanParamsResult getSchEnrollPlanParams(String schId, int diplomaId) {
		try {
			
			SchEnrollPlanDistinctAttribute schEnrollPlanDistinctAttribute = null;
			if (StringUtils.isBlank(schId)) {
				// 先尝试去redis中,查看是否已经存在
				boolean hasKey = redisTemplate.opsForHash().hasKey(Constants.REDIS_SCH_ENROLL_PLAN_PARAMS_KEY, diplomaId);
				if (hasKey) {
					schEnrollPlanDistinctAttribute = (SchEnrollPlanDistinctAttribute) redisTemplate.opsForHash().get(Constants.REDIS_SCH_ENROLL_PLAN_PARAMS_KEY, diplomaId);
				}
				
				if (schEnrollPlanDistinctAttribute != null) {
					logger.debug("Get cache success.");
				}
			} 
			
			// 如果当前为空,则表名如果前面有尝试从redis中查找，那么也是没有缓存起来的
			if (schEnrollPlanDistinctAttribute == null) {
				// 获取学校招生参数
				FindSchEnrollPlanDistinctAttributeParams findSchEnrollPlanDistinctAttributeParams = new FindSchEnrollPlanDistinctAttributeParams();
				findSchEnrollPlanDistinctAttributeParams.setSchId(schId);
//				findSchEnrollPlanDistinctAttributeParams.setDiplomaId(EDU_DIPLOMA.findByValue(diplomaId));
				schEnrollPlanDistinctAttribute = eduEnrollService.findSchEnrollPlanDistinctAttribute(findSchEnrollPlanDistinctAttributeParams);
			}

			if (schEnrollPlanDistinctAttribute == null) {
				return null;
			}
			
			// 如果当前schId为空，则是需要缓存起来的
			if (StringUtils.isBlank(schId)) {
				redisTemplate.opsForHash().put(Constants.REDIS_SCH_ENROLL_PLAN_PARAMS_KEY, diplomaId, schEnrollPlanDistinctAttribute);
			}
			
			// 转换结果
			SchEnrollPlanParamsResult schEnrollPlanParamsResult = new SchEnrollPlanParamsResult();
			schEnrollPlanParamsResult.setProvince_names(schEnrollPlanDistinctAttribute.getEnrollLocNameList());
			List<String> types = new ArrayList<String>();
			types.add("wen");
			types.add("li");
			schEnrollPlanParamsResult.setTypes(types);
			List<String> enrollYears = new ArrayList<String>();
			if (schEnrollPlanDistinctAttribute.getEnrollYearList() != null) {
				for(int enrollYear : schEnrollPlanDistinctAttribute.getEnrollYearList()) {
					enrollYears.add(String.valueOf(enrollYear));
				}
			}
			schEnrollPlanParamsResult.setYears(enrollYears);
			return schEnrollPlanParamsResult;
		} catch (TException e) {
			LoggerUtils.logEduTExceptionAndThrowAppException(logger, e);
		}
		return null;
	}
	
	@Override
	public SchRankingListResult getSchRankingListResult(String rankType, String provinceFilter, String schNamePattern,
			Integer pageSize, Integer curPage) {
		try {
			PageSchRankingListParams pageSchRankingListParams = new PageSchRankingListParams();
			// 排行榜类型
			EDU_SCH_RANKING_LIST_TYPE schRankingListType = ParameterUtils.convertToSchRankType(rankType);
			pageSchRankingListParams.setRankType(schRankingListType);
			// 省份过滤
			if (StringUtils.isNotBlank(provinceFilter)) {
				String provinceId = getProvinceId(provinceFilter);
				if (StringUtils.isNotBlank(provinceId)) {
					pageSchRankingListParams.setProvinceIdFilter(provinceId);
				}
			}
			// 学校名字部分匹配
			if (StringUtils.isNotBlank(schNamePattern)) {
				pageSchRankingListParams.setSchNamePattern(schNamePattern);
			}
			// 每页显示个数
			if (pageSize == null || pageSize <= 0) {
				pageSchRankingListParams.setPageSize(Constants.SCH_RANKING_LIST_PAEG_SIZE);
			} else {
				pageSchRankingListParams.setPageSize(pageSize);
			}
			// 查询页数
			if (curPage == null || curPage <= 0) {
				pageSchRankingListParams.setCurPage(1);
			} else {
				pageSchRankingListParams.setCurPage(curPage);
			}
			pageSchRankingListParams.setDiplomaId(EDU_DIPLOMA.EDU_DIPLOMA_BK);
			SchRankingListPage schRankingListPage = eduStatService.pageSchRankingList(pageSchRankingListParams);
			if (schRankingListPage == null) {
				return null;
			}
			
			return convertToSchRankingListResult(schRankingListType, schRankingListPage);
		} catch (TException e) {
			LoggerUtils.logEduTExceptionAndThrowAppException(logger, e);
		}
		return null;
	}
	
	@Override
	public SchMajorRankingListResult getSchMajorRankingListResultBySalary(String schId, int diplomaId) {
		try {
			// 查询学校专业统计信息
			FindSchMajorRankingListParams findSchMajorRankingListParams = new FindSchMajorRankingListParams();
			findSchMajorRankingListParams.setSchId(schId);
			findSchMajorRankingListParams.setDiplomaId(EDU_DIPLOMA.findByValue(diplomaId));
			SchMajorRankingList schMajorRankingList = eduStatService.findSchMajorRankingList(findSchMajorRankingListParams);
			if (schMajorRankingList == null) {
				return null;
			}

			// 统计专业id列表和行业id列表
			List<String> majorIdList = new ArrayList<String>();
			for(SalaryRank salaryRank : schMajorRankingList.getSalaryRankList()) {
				majorIdList.add(salaryRank.getMajorId());
			}
			
			// 查询专业信息
			ListMajorInfoByIdsParams listMajorInfoByIdsParams = new ListMajorInfoByIdsParams();
			listMajorInfoByIdsParams.setMajorIdList(majorIdList);
			listMajorInfoByIdsParams.setSimpleData(true);
			List<MajorInfo> majorInfoList = eduInfoService.listMajorInfoByIds(listMajorInfoByIdsParams);
			if (majorInfoList == null) {
				return null;
			}
		
			// 查询行业信息
			List<IndustryInfo> industryInfoList = commonService.getAllIndustry(null);
			
			return convertToSalarySchMajorRankingListResult(schMajorRankingList.getSalaryRankList(), majorInfoList, industryInfoList);
		} catch (TException e) {
			LoggerUtils.logEduTExceptionAndThrowAppException(logger, e);
		}
		return null;
	}
	
	@Override
	public SchMajorRankingListResult getSchMajorRankingListResultByGender(String schId, int diplomaId) {
		try {
			// 查询学校专业统计信息
			FindSchMajorRankingListParams findSchMajorRankingListParams = new FindSchMajorRankingListParams();
			findSchMajorRankingListParams.setSchId(schId);
			findSchMajorRankingListParams.setDiplomaId(EDU_DIPLOMA.findByValue(diplomaId));
			SchMajorRankingList schMajorRankingList = eduStatService.findSchMajorRankingList(findSchMajorRankingListParams);
			if (schMajorRankingList == null) {
				return null;
			}
			
			// 统计专业id列表和行业id列表
			List<String> majorIdList = new ArrayList<String>();
			for(FemaleRank femaleRank : schMajorRankingList.getFemaleRankList()) {
				majorIdList.add(femaleRank.getMajorId());
			}
			
			// 查询专业信息
			ListMajorInfoByIdsParams listMajorInfoByIdsParams = new ListMajorInfoByIdsParams();
			listMajorInfoByIdsParams.setMajorIdList(majorIdList);
			listMajorInfoByIdsParams.setSimpleData(true);
			List<MajorInfo> majorInfoList = eduInfoService.listMajorInfoByIds(listMajorInfoByIdsParams);
			if (majorInfoList == null) {
				return null;
			}
			
			// 查询行业信息
			List<IndustryInfo> industryInfoList = commonService.getAllIndustry(null);
			
			return convertToGenderSchMajorRankingListResult(schMajorRankingList.getFemaleRankList(), majorInfoList, industryInfoList);
		} catch (TException e) {
			LoggerUtils.logEduTExceptionAndThrowAppException(logger, e);
		}
		return null;
	}
	
	@Override
	public SchMajorRankingListResult getSchMajorRankingListResultBySalaryGrowth(String schId, int diplomaId) {
		try {
			// 查询学校专业统计信息
			FindSchMajorRankingListParams findSchMajorRankingListParams = new FindSchMajorRankingListParams();
			findSchMajorRankingListParams.setSchId(schId);
			findSchMajorRankingListParams.setDiplomaId(EDU_DIPLOMA.findByValue(diplomaId));
			SchMajorRankingList schMajorRankingList = eduStatService.findSchMajorRankingList(findSchMajorRankingListParams);
			if (schMajorRankingList == null) {
				return null;
			}
			
			// 统计专业id列表和行业id列表
			List<String> majorIdList = new ArrayList<String>();
			for(SalaryGrowRatioRank salaryGrowRatioRank : schMajorRankingList.getSalaryGrowRatioRankList()) {
				majorIdList.add(salaryGrowRatioRank.getMajorId());
			}
			
			// 查询专业信息
			ListMajorInfoByIdsParams listMajorInfoByIdsParams = new ListMajorInfoByIdsParams();
			listMajorInfoByIdsParams.setMajorIdList(majorIdList);
			listMajorInfoByIdsParams.setSimpleData(true);
			List<MajorInfo> majorInfoList = eduInfoService.listMajorInfoByIds(listMajorInfoByIdsParams);
			if (majorInfoList == null) {
				return null;
			}

			// 查询行业信息
			List<IndustryInfo> industryInfoList = commonService.getAllIndustry(null);
			
			return convertToSalaryGrowthSchMajorRankingListResult(schMajorRankingList.getSalaryGrowRatioRankList(), majorInfoList, industryInfoList);
		} catch (TException e) {
			LoggerUtils.logEduTExceptionAndThrowAppException(logger, e);
		}
		return null;
	}
	
	@Override
	public SchMajorRankingListResult getSchMajorRankingListResultByScore(String schId, int diplomaId,
			String wenliFilter, String provinceIdFilter, String yearFilter) {
		try {
			// 查询学校专业统计信息
			FindSchMajorScoreRankingListParams findSchMajorScoreRankingListParams = new FindSchMajorScoreRankingListParams();
			findSchMajorScoreRankingListParams.setSchId(schId);
			findSchMajorScoreRankingListParams.setDiplomaId(EDU_DIPLOMA.findByValue(diplomaId));
			findSchMajorScoreRankingListParams.setProvinceId(provinceIdFilter);
			if (Constants.WEN_KE.equals(wenliFilter)) {
				findSchMajorScoreRankingListParams.setWenliFilter(EDU_TYPE_WENLI.EDU_TYPE_WEN);
			} else if (Constants.LI_KE.equals(wenliFilter)) {
				findSchMajorScoreRankingListParams.setWenliFilter(EDU_TYPE_WENLI.EDU_TYPE_LI);
			}
			findSchMajorScoreRankingListParams.setYear(Integer.parseInt(yearFilter));
			SchMajorScoreRankingList schMajorScoreRankingList = eduStatService.findSchMajorScoreRankingList(findSchMajorScoreRankingListParams);
			if (schMajorScoreRankingList == null) {
				return null;
			}
			
			// 统计专业id列表和行业id列表
			List<String> majorIdList = new ArrayList<String>();
			for (ScoreRank scoreRank : schMajorScoreRankingList.getScoreRankList()) {
					majorIdList.add(scoreRank.getMajorId());
			}
		

			// 查询专业信息
			ListMajorInfoByIdsParams listMajorInfoByIdsParams = new ListMajorInfoByIdsParams();
			listMajorInfoByIdsParams.setMajorIdList(majorIdList);
			listMajorInfoByIdsParams.setSimpleData(true);
			List<MajorInfo> majorInfoList = eduInfoService.listMajorInfoByIds(listMajorInfoByIdsParams);
			if (majorInfoList == null) {
				return null;
			}

			// 查询行业信息
			List<IndustryInfo> industryInfoList = commonService.getAllIndustry(null);
			
			return convertToScoreSchMajorRankingListResult(schMajorScoreRankingList.getScoreRankList(), majorInfoList, industryInfoList);
		} catch (TException e) {
			LoggerUtils.logEduTExceptionAndThrowAppException(logger, e);
		}
		return null;
	}
	
	@Override
	public SchScoreListResult getSchScoreListResult(String schId, int diplomaId, String provinceIdFilter,
			String wenliFilter) {
		try {
			// 查询学校录取分数详情列表
			List<SchScoreDetail> schScoreDetailList = getSchScoreDetailList(schId, provinceIdFilter, wenliFilter);
			
			// 遍历查询该年份省份的录取分数
			if (schScoreDetailList != null) {
				SchScoreListResult schScoreListResult = new SchScoreListResult();
				List<SchScore> schScoreList = new ArrayList<SchScore>();
				for (int i = 0; i < schScoreDetailList.size(); i++) {
					SchScoreDetail schScoreDetail = schScoreDetailList.get(i);
					SchScore schScore = new SchScore(schScoreDetail);
					schScoreList.add(schScore);
				}
				schScoreListResult.setScores(schScoreList);
				return schScoreListResult;
			}
			
		} catch (TException e) {
			LoggerUtils.logEduTExceptionAndThrowAppException(logger, e);
		}
		return null;
	}
	
	@Override
	public SchScoreParamsResult getSchScoreParamsResult(String schId, int diplomaId) {
		try {
			// 查询学校录取分数详情列表
			FindSchScoreDistinctAttributeParams findSchScoreDistinctAttributeParams = new FindSchScoreDistinctAttributeParams();
			findSchScoreDistinctAttributeParams.setSchId(schId);
			SchScoreDistinctAttribute schScoreDistinctAttribute = eduScoreService.findSchScoreDistinctAttribute(findSchScoreDistinctAttributeParams);
			if (schScoreDistinctAttribute != null) {
				SchScoreParamsResult schScoreParamsResult = new SchScoreParamsResult();
				schScoreParamsResult.setProvince_ids(schScoreDistinctAttribute.getProvinceIdList());
				List<String> types = new ArrayList<String>();
				types.add("wen");
				types.add("li");
				schScoreParamsResult.setTypes(types);
				return schScoreParamsResult;
			}
			
		} catch (TException e) {
			LoggerUtils.logEduTExceptionAndThrowAppException(logger, e);
		}
		return null;
	}
	
	@Override
	public SchMajorScoreListResult getSchMajorScoreListResult(String schId, int diplomaId, String wenliFilter,
			String provinceIdFilter, String yearFilter) {
		try {
			// 查询学校专业录取分数列表
			ListSchMajorScoreStatInfoParams listSchMajorScoreStatInfoParams = new ListSchMajorScoreStatInfoParams();
			listSchMajorScoreStatInfoParams.setSchId(schId);
			listSchMajorScoreStatInfoParams.setBatchList(ParameterUtils.getBatchListByDiplomaId(diplomaId));
			if (Constants.WEN_KE.equals(wenliFilter)) {
				listSchMajorScoreStatInfoParams.setEduTypeWenli(EDU_TYPE_WENLI.EDU_TYPE_WEN);
			} else if (Constants.LI_KE.equals(wenliFilter)) {
				listSchMajorScoreStatInfoParams.setEduTypeWenli(EDU_TYPE_WENLI.EDU_TYPE_LI);
			}
			listSchMajorScoreStatInfoParams.setProvinceId(provinceIdFilter);
			listSchMajorScoreStatInfoParams.setYear(Integer.parseInt(yearFilter));
			List<SchMajorScoreStatInfo> schMajorScoreStatInfoList = eduScoreService.listSchMajorScoreStatInfo(listSchMajorScoreStatInfoParams);
			if (schMajorScoreStatInfoList == null || schMajorScoreStatInfoList.size() <= 0) {
				return null;
			}
			
			// 查询有统计信息的
			List<String> majorIdList = new ArrayList<String>();
			for(SchMajorScoreStatInfo schMajorScoreStatInfo : schMajorScoreStatInfoList) {
				majorIdList.add(schMajorScoreStatInfo.getMajorId());
			}
			Set<String> hasStatMajorIdSet = distinctHasStatMajorId(schId, diplomaId, majorIdList);
			Map<String, MajorInfo> majorInfoMap = getMajorInfoMapByIds(majorIdList, true);
			
			SchMajorScoreListResult schMajorScoreListResult = new SchMajorScoreListResult();
			List<SchMajorScore> schMajorScoreList = new ArrayList<SchMajorScore>();
			for(int i = 0; i < schMajorScoreStatInfoList.size(); i++) {
				SchMajorScoreStatInfo schMajorScoreStatInfo = schMajorScoreStatInfoList.get(i);
				MajorInfo majorInfo = majorInfoMap.get(schMajorScoreStatInfo.getMajorId());
				if (majorInfo == null) {
					continue;
				}
				
				// 查询该年份省份的录取分数线
				FindProvinceToudangScoreInfoParams findProvinceToudangScoreInfoParams = new FindProvinceToudangScoreInfoParams();
				findProvinceToudangScoreInfoParams.setYear(schMajorScoreStatInfo.getYear());
				findProvinceToudangScoreInfoParams.setProvinceId(schMajorScoreStatInfo.getFromProvinceId());
				findProvinceToudangScoreInfoParams.setEduTypeWenli(schMajorScoreStatInfo.getEduTypeWenli());
				findProvinceToudangScoreInfoParams.setBatch(schMajorScoreStatInfo.getBatch());
				ProvinceToudangScoreInfo provinceToudangScoreInfo = eduScoreService.findProvinceToudangScoreInfo(findProvinceToudangScoreInfoParams);
				if (provinceToudangScoreInfo == null) {
					continue;
				}
				if (0 == schMajorScoreStatInfo.getMinScore() || 0 == schMajorScoreStatInfo.getAvgScore() || 0 == schMajorScoreStatInfo.getMaxScore()) {
					continue;
				}
				
				SchMajorScore schMajorScore = new SchMajorScore();
				schMajorScore.setRank_index(i + 1);
				schMajorScore.setMajor_id(schMajorScoreStatInfo.getMajorId());
				schMajorScore.setMajor_name(majorInfo.getMajorName());
				schMajorScore.setMajor_category(majorInfo.getMajorCategory());
				schMajorScore.setMajor_second_category(majorInfo.getMajorSecondCategory());
				schMajorScore.setMax_score(schMajorScoreStatInfo.getMaxScore());
				schMajorScore.setAvg_score(schMajorScoreStatInfo.getAvgScore());
				schMajorScore.setAvg_diff_score(schMajorScoreStatInfo.getAvgScore() - provinceToudangScoreInfo.getScore());
				schMajorScore.setLuqu_batch(ParameterUtils.toBatchDesc(schMajorScoreStatInfo.getBatch()));
				if (hasStatMajorIdSet.contains(schMajorScoreStatInfo.getMajorId())) {
					schMajorScore.setMajor_has_stats(true);
				} else {
					schMajorScore.setMajor_has_stats(false);
				}
				
				schMajorScoreList.add(schMajorScore);
			}
			schMajorScoreListResult.setScores(schMajorScoreList);
			return schMajorScoreListResult;
		} catch (TException e) {
			LoggerUtils.logEduTExceptionAndThrowAppException(logger, e);
		}
		return null;
	}
	
	@Override
	public SchMajorScoreParamsResult getSchMajorScoreParamsResult(String schId, int diplomaId) {
		try {
			// 参数有问题，后续更改
			// 查询学校录取分
			FindSchMajorScoreDistinctAttributeParams findSchMajorScoreDistinctAttributeParams = new FindSchMajorScoreDistinctAttributeParams();
			findSchMajorScoreDistinctAttributeParams.setSchId(schId);
			findSchMajorScoreDistinctAttributeParams.setBatchList(ParameterUtils.getAllBatch());
			SchMajorScoreDistinctAttribute schMajorScoreDistinctAttribute = eduScoreService.findSchMajorScoreDistinctAttribute(findSchMajorScoreDistinctAttributeParams);
			if (schMajorScoreDistinctAttribute != null) {
				SchMajorScoreParamsResult schMajorScoreParamsResult = new SchMajorScoreParamsResult();
				schMajorScoreParamsResult.setProvince_ids(schMajorScoreDistinctAttribute.getProvinceIdList());
				List<String> types = new ArrayList<String>();
				types.add("wen");
				types.add("li");
				schMajorScoreParamsResult.setTypes(types);
				List<String> yearList = new ArrayList<String>();
				if (schMajorScoreDistinctAttribute.getYearList() != null) {
					for(int year : schMajorScoreDistinctAttribute.getYearList()) {
						yearList.add(String.valueOf(year));
					}
				}
				schMajorScoreParamsResult.setYears(yearList);
				return schMajorScoreParamsResult;
			}
		} catch (TException e) {
			LoggerUtils.logEduTExceptionAndThrowAppException(logger, e);
		}
		return null;
	}
	
	@Override
	public SchEnrollPlanListResult getSchEnrollPlanListResult(String schId, int diplomaId, String provinceFilter,
			String wenliFilter, String yearFilter) {
		try {
			// 查询招生计划
			ListSchEnrollPlanInfoParams listSchEnrollPlanInfoParams = new ListSchEnrollPlanInfoParams();
			listSchEnrollPlanInfoParams.setSchId(schId);
			listSchEnrollPlanInfoParams.setDiplomaId(EDU_DIPLOMA.findByValue(diplomaId));
			if (Constants.WEN_KE.equals(wenliFilter)) {
				listSchEnrollPlanInfoParams.setEduTypeWenli(EDU_TYPE_WENLI.EDU_TYPE_WEN);
			} else if (Constants.LI_KE.equals(wenliFilter)) {
				listSchEnrollPlanInfoParams.setEduTypeWenli(EDU_TYPE_WENLI.EDU_TYPE_LI);
			}
			listSchEnrollPlanInfoParams.setLocName(provinceFilter);
			listSchEnrollPlanInfoParams.setYear(Integer.parseInt(yearFilter));
			listSchEnrollPlanInfoParams.setErollLocType(EDU_ENROLL_LOCATION.EDU_ENROLL_LOCATION_BY_PROVINCE);
			List<SchEnrollPlanInfo> schEnrollPlanInfoList = eduEnrollService.listSchEnrollPlanInfo(listSchEnrollPlanInfoParams);
			if (schEnrollPlanInfoList == null || schEnrollPlanInfoList.size() <= 0) {
				return null;
			}
			
			// 查询有统计的专业id
			List<String> majorIdList = new ArrayList<String>();
			for(SchEnrollPlanInfo schEnrollPlanInfo : schEnrollPlanInfoList) {
				majorIdList.add(schEnrollPlanInfo.getMajorId());
			}
			Set<String> hasStatMajorIdSet = distinctHasStatMajorId(schId, diplomaId, majorIdList);
			
			// 拼接结果
			SchEnrollPlanListResult schEnrollPlanListResult = new SchEnrollPlanListResult();
			List<SchEnrollPlan> schEnrollPlanList = new ArrayList<SchEnrollPlan>();
			for(int i = 0; i < schEnrollPlanInfoList.size(); i++) {
				SchEnrollPlanInfo schEnrollPlanInfo = schEnrollPlanInfoList.get(i);
				SchEnrollPlan schEnrollPlan = new SchEnrollPlan();
				schEnrollPlan.setRank_index(i + 1);
				schEnrollPlan.setMajor_id(schEnrollPlanInfo.getMajorId());
				schEnrollPlan.setMajor_name(schEnrollPlanInfo.getMajorName());
				schEnrollPlan.setWenli(schEnrollPlanInfo.getMajorCate());
				schEnrollPlan.setZhaosheng_count(schEnrollPlanInfo.getEnrollPlanCount());
				if (schEnrollPlanInfo.getEnrollSemesterCount() <= 0) {
					schEnrollPlan.setZhaosheng_xuezhi("-");
				} else {
					schEnrollPlan.setZhaosheng_xuezhi(String.valueOf(schEnrollPlanInfo.getEnrollSemesterCount()));
				}
				schEnrollPlan.setPlan_type(schEnrollPlanInfo.getPlanType());
				if (hasStatMajorIdSet.contains(schEnrollPlanInfo.getMajorId())) {
					schEnrollPlan.setMajor_has_stats(true);
				} else {
					schEnrollPlan.setMajor_has_stats(false);
				}
				schEnrollPlanList.add(schEnrollPlan);
			}
			schEnrollPlanListResult.setPlans(schEnrollPlanList);
			return schEnrollPlanListResult;
		} catch (TException e) {
			LoggerUtils.logEduTExceptionAndThrowAppException(logger, e);
		}
		return null;
	}
	
	@Override
	public SchFilterListResult getSchFilterListResult(String provinceFilter, int diplomaId, String schoolType,
			String majorSecondCategoryFilter, String majorFilter, String schNamePattern, String sortBy, Integer count, Integer pageNo) {
		try {
			// 获取学校筛选排名
			
			String hashKey = null;
			if (EDU_DIPLOMA.EDU_DIPLOMA_BK.getValue() == diplomaId) {
				if (Constants.SCH_FILTER_LIST_TYPE_ZONGHE.equals(sortBy)) {
					hashKey = Constants.REDIS_SCH_FILTER_BK_ZONGHE_HASH_KEY;
				} else if (Constants.SCH_FILTER_LIST_TYPE_ZHIMINGDU.equals(sortBy)) {
					hashKey = Constants.REDIS_SCH_FILTER_BK_ZHIMINGDU_HASH_KEY;
				} else if (Constants.SCH_FILTER_LIST_TYPE_JINGZHENGLI.equals(sortBy)) {
					hashKey = Constants.REDIS_SCH_FILTER_BK_JINGZHENGLI_HASH_KEY;
				} else if (Constants.SCH_FILTER_LIST_TYPE_XINCHOU.equals(sortBy)) {
					hashKey = Constants.REDIS_SCH_FILTER_BK_XINCHOU_HASH_KEY;
				} else if (Constants.SCH_FILTER_LIST_TYPE_MEIZHI.equals(sortBy)) {
					hashKey = Constants.REDIS_SCH_FILTER_BK_MEIZHI_HASH_KEY;
				} else if (Constants.SCH_FILTER_LIST_TYPE_NANZHI.equals(sortBy)) {
					hashKey = Constants.REDIS_SCH_FILTER_BK_NANZHI_HASH_KEY;
				}
				
 			} else if (EDU_DIPLOMA.EDU_DIPLOMA_ZK.getValue() == diplomaId) {
				if (Constants.SCH_FILTER_LIST_TYPE_XINCHOU.equals(sortBy)) {
					hashKey = Constants.REDIS_SCH_FILTER_ZK_XINCHOU_HASH_KEY;
				} else if (Constants.SCH_FILTER_LIST_TYPE_MEIZHI.equals(sortBy)) {
					hashKey = Constants.REDIS_SCH_FILTER_ZK_MEIZHI_HASH_KEY;
				} else if (Constants.SCH_FILTER_LIST_TYPE_NANZHI.equals(sortBy)) {
					hashKey = Constants.REDIS_SCH_FILTER_ZK_NANZHI_HASH_KEY;
				}
			}
			if (hashKey == null) {
				return null;
			}
			boolean hasKey = redisTemplate.opsForHash().hasKey(Constants.REDIS_SCH_FILTER_KEY, hashKey);
			if (!hasKey) {
				return null;
			}
			List<SchFilter> schFilterList = (List<SchFilter>) redisTemplate.opsForHash().get(Constants.REDIS_SCH_FILTER_KEY, hashKey);
			if(schFilterList == null) {
				return null;
			}
			// 获取专业二级目录和专业过滤条件
			Set<String> schIdSet = null;
			if (!StringUtils.isBlank(majorFilter)) {
				schIdSet = new HashSet<String>();
				FindMajorIdBaseInfoParams findMajorIdBaseInfoParams = new FindMajorIdBaseInfoParams();
				findMajorIdBaseInfoParams.setDiploma(EDU_DIPLOMA.findByValue(diplomaId));
				findMajorIdBaseInfoParams.setMajorId(majorFilter);
				MajorIdBaseInfo majorIdBaseInfo = eduInfoService.findMajorIdBaseInfo(findMajorIdBaseInfoParams);
				if (majorIdBaseInfo != null) {
					schIdSet.addAll(majorIdBaseInfo.getSchIdList());
				}
			} else if (!StringUtils.isBlank(majorSecondCategoryFilter)) {
				schIdSet = new HashSet<String>();
				FindMajorSecondCateIdBaseInfoParams findMajorSecondCateIdBaseInfoParams = new FindMajorSecondCateIdBaseInfoParams();
				findMajorSecondCateIdBaseInfoParams.setDiplomaId(EDU_DIPLOMA.findByValue(diplomaId));
				findMajorSecondCateIdBaseInfoParams.setMajorSecondCate(majorSecondCategoryFilter);
				MajorSecondCateIdBaseInfo majorSecondCateIdBaseInfo = eduInfoService.findMajorSecondCateIdBaseInfo(findMajorSecondCateIdBaseInfoParams);
				if (majorSecondCateIdBaseInfo != null) {
					schIdSet.addAll(majorSecondCateIdBaseInfo.getSchIdList());
				}
			}
			
			// 过滤学校ID、省份、学校类型、名字部分匹配
			if (schFilterList == null) {
				return null;
			}
			
			SchFilterListResult schFilterListResult = new SchFilterListResult();
			List<SchFilter> resultSchFilterList = new ArrayList<SchFilter>();
			for(SchFilter schFilter : schFilterList) {
				// 过滤schId
				if (schIdSet != null && !schIdSet.contains(schFilter.getSch_id())) {
					continue;
				}
				
				// 过滤省份
				if (!StringUtils.isBlank(provinceFilter) && (StringUtils.isBlank(schFilter.getLocation()) ||  !schFilter.getLocation().contains(provinceFilter))) {
					continue;
				}

				// 过滤学校类型
				if (!StringUtils.isBlank(schoolType) && (StringUtils.isBlank(schFilter.getSch_type()) || !schFilter.getSch_type().contains(schoolType))) {
					continue;
				}

				// 过滤名字部分匹配
				if (!StringUtils.isBlank(schNamePattern) && (StringUtils.isBlank(schFilter.getSch_name()) || !schFilter.getSch_name().contains(schNamePattern))) {
					continue;
				}
				resultSchFilterList.add(schFilter);
			}
			
			// 分页显示
			if (count == null || count <= 0) {
				count = Constants.SCH_FILTER_LIST_PAGE_SIZE;
			}
			if (pageNo == null || pageNo <= 0) {
				pageNo = 1;
			}
			int totalPage = (int) Math.ceil(resultSchFilterList.size() * 1.0 / count);
			int totalCount = resultSchFilterList.size();
			int startIndex = (pageNo - 1) * count;
			if (startIndex > resultSchFilterList.size() - 1) {
				resultSchFilterList = new ArrayList<SchFilter>();
			} else {
				int endIndex = (startIndex + count) > resultSchFilterList.size() ? resultSchFilterList.size() : startIndex + count;
				resultSchFilterList = resultSchFilterList.subList(startIndex, endIndex);
				List<SchFilter> tempSchFilterList = new ArrayList<SchFilter>(resultSchFilterList);
				resultSchFilterList = tempSchFilterList;
			}
			
			schFilterListResult.setTotal_page(totalPage);
			schFilterListResult.setTotal_count(totalCount);
			schFilterListResult.setSchools(resultSchFilterList);
			return schFilterListResult;
		} catch (TException e) {
			LoggerUtils.logEduTExceptionAndThrowAppException(logger, e);
		}
		return null;
	}
	
	/**
	 * 获取学校录取分数详情
	 * @param schId 学校ID
	 * @param provinceFilter 省份过滤
	 * @param wenliFilter 文理过滤
	 * @return
	 * @throws TException
	 */
	private List<SchScoreDetail> getSchScoreDetailList(String schId, String provinceIdFilter,
			String wenliFilter) throws TException {
		// 查询学校录取分数统计信息
		ListSchScoreStatInfoParams listSchScoreStatInfoParams = new ListSchScoreStatInfoParams();
		listSchScoreStatInfoParams.setSchId(schId);
		listSchScoreStatInfoParams.setProvinceId(provinceIdFilter);
		if (Constants.WEN_KE.equals(wenliFilter)) {
			listSchScoreStatInfoParams.setEduTypeWenli(EDU_TYPE_WENLI.EDU_TYPE_WEN);
		} else if (Constants.LI_KE.equals(wenliFilter)) {
			listSchScoreStatInfoParams.setEduTypeWenli(EDU_TYPE_WENLI.EDU_TYPE_LI);
		}
		// listSchScoreStatInfoParams.setBatchList(ParameterUtils.getBatchListByDiplomaId(diplomaId));
		// 本科和专科共享录取分数线展示
		List<SchScoreStatInfo> schScoreStatInfoList = eduScoreService.listSchScoreStatInfo(listSchScoreStatInfoParams);

		// 遍历查询该年份省份的录取分数
		if (schScoreStatInfoList != null) {
			List<SchScoreDetail> schScoreDetailList = new ArrayList<SchScoreDetail>();
			for (int i = 0; i < schScoreStatInfoList.size(); i++) {
				SchScoreStatInfo schScoreStatInfo = schScoreStatInfoList.get(i);
				SchScoreDetail schScoreDetail = new SchScoreDetail();
				FindProvinceToudangScoreInfoParams findProvinceToudangScoreInfoParams = new FindProvinceToudangScoreInfoParams();
				findProvinceToudangScoreInfoParams.setYear(schScoreStatInfo.getYear());
				findProvinceToudangScoreInfoParams.setProvinceId(schScoreStatInfo.getFromProvinceId());
				findProvinceToudangScoreInfoParams.setEduTypeWenli(schScoreStatInfo.getEduTypeWenli());
				findProvinceToudangScoreInfoParams.setBatch(schScoreStatInfo.getBatch());
				ProvinceToudangScoreInfo provinceToudangScoreInfo = eduScoreService
						.findProvinceToudangScoreInfo(findProvinceToudangScoreInfoParams);
				if (provinceToudangScoreInfo == null) {
					continue;
				}

				if (0 == schScoreStatInfo.getMinScore() || 0 == schScoreStatInfo.getAvgScore()
						|| 0 == schScoreStatInfo.getMaxScore()) {
					continue;
				}

				if (schScoreStatInfo.getYear() == 2012 && schScoreStatInfo.getPeopelCount() <= 8) {
					continue;
				}

				schScoreDetail.setRank_index(i + 1);
				schScoreDetail.setYear(schScoreStatInfo.getYear());
				schScoreDetail.setMax_score(schScoreStatInfo.getMaxScore());
				schScoreDetail.setAvg_score(schScoreStatInfo.getAvgScore());
				schScoreDetail.setAvg_diff_score(schScoreStatInfo.getAvgScore() - provinceToudangScoreInfo.getScore());
				schScoreDetail.setMin_score(schScoreStatInfo.getMinScore());
				schScoreDetail.setLuqu_count(schScoreStatInfo.getPeopelCount());
				schScoreDetail.setLuqu_batch(ParameterUtils.toBatchDesc(schScoreStatInfo.getBatch()));
				schScoreDetail.setProvinceId(schScoreStatInfo.getFromProvinceId());

				schScoreDetailList.add(schScoreDetail);
			}
			return schScoreDetailList;
		}
		return null;
	}


	/**
	 * 转换为录取分数学校专业排行榜结果
	 * @param scoreRankList 录取分数排名列表
	 * @param majorInfoList 专业信息列表
	 * @param industryInfoList 行业信息列表
	 * @return
	 */
	private SchMajorRankingListResult convertToScoreSchMajorRankingListResult(List<ScoreRank> scoreRankList,
			List<MajorInfo> majorInfoList, List<IndustryInfo> industryInfoList) {
		// 转换为map
		Map<String, MajorInfo> majorInfoMap = new HashMap<String, MajorInfo>();
		if (majorInfoList != null) {
			for (MajorInfo majorInfo : majorInfoList) {
				majorInfoMap.put(majorInfo.getMajorId(), majorInfo);
			}
		}
		Map<String, IndustryInfo> industryInfoMap = new HashMap<String, IndustryInfo>();
		if (industryInfoList != null) {
			for (IndustryInfo industryInfo : industryInfoList) {
				industryInfoMap.put(industryInfo.getIndustryId(), industryInfo);
			}
		}
		

		// 拼装结果
		SchMajorRankingListResult schMajorRankingListResult = new SchMajorRankingListResult();
		List<com.ipin.service.rest.beans.SchMajorRankingList> schMajorRankingLists = new ArrayList<com.ipin.service.rest.beans.SchMajorRankingList>();
		for (int i = 0; i < scoreRankList.size(); i++) {
			ScoreRank scoreRank = scoreRankList.get(i);
			MajorInfo majorInfo = majorInfoMap.get(scoreRank.getMajorId());
			IndustryInfo industryInfo = industryInfoMap.get(scoreRank.getMainIndustryId());
			
			ScoreSchMajorRankingList scoreSchMajorRankingList = new ScoreSchMajorRankingList();
			scoreSchMajorRankingList.setRank_index(i + 1);
			scoreSchMajorRankingList.setMajor_id(scoreRank.getMajorId());
			scoreSchMajorRankingList.setMajor_name(majorInfo.getMajorName());
			scoreSchMajorRankingList.setMajor_category(majorInfo.getMajorCategory());
			scoreSchMajorRankingList.setMain_industry(industryInfo.getIndustryShortName());
			scoreSchMajorRankingList.setMain_function(scoreRank.getMainFunction());
			scoreSchMajorRankingList.setLuqu_count(scoreRank.getStudentNum());
			scoreSchMajorRankingList.setLuqu_socre(scoreRank.getScore());
			scoreSchMajorRankingList.setLuqu_score_rank(scoreRank.getScoreRank());
			scoreSchMajorRankingList.setMajor_has_stats(true);
			schMajorRankingLists.add(scoreSchMajorRankingList);
		}
		schMajorRankingListResult.setMajors(schMajorRankingLists);
		
		return schMajorRankingListResult;
	}
	/**
	 * 转换为薪酬增长学校专业排行榜
	 * @param salaryGrowRatioRankList 薪酬增长排名列表
	 * @param majorInfoList 专业信息列表
	 * @param industryInfoList 行业信息列表
	 * @return
	 */
	private SchMajorRankingListResult convertToSalaryGrowthSchMajorRankingListResult(
			List<SalaryGrowRatioRank> salaryGrowRatioRankList, List<MajorInfo> majorInfoList,
			List<IndustryInfo> industryInfoList) {
		// 转换为map
		Map<String, MajorInfo> majorInfoMap = new HashMap<String, MajorInfo>();
		if (majorInfoList != null) {
			for (MajorInfo majorInfo : majorInfoList) {
				majorInfoMap.put(majorInfo.getMajorId(), majorInfo);
			}
		}

		Map<String, IndustryInfo> industryInfoMap = new HashMap<String, IndustryInfo>();
		if (industryInfoList != null) {
			for (IndustryInfo industryInfo : industryInfoList) {
				industryInfoMap.put(industryInfo.getIndustryId(), industryInfo);
			}
		}
		

		// 拼装结果
		SchMajorRankingListResult schMajorRankingListResult = new SchMajorRankingListResult();
		List<com.ipin.service.rest.beans.SchMajorRankingList> schMajorRankingLists = new ArrayList<com.ipin.service.rest.beans.SchMajorRankingList>();
		for (int i = 0; i < salaryGrowRatioRankList.size(); i++) {
			SalaryGrowRatioRank salaryGrowRatioRank = salaryGrowRatioRankList.get(i);
			MajorInfo majorInfo = majorInfoMap.get(salaryGrowRatioRank.getMajorId());
			IndustryInfo industryInfo = industryInfoMap.get(salaryGrowRatioRank.getMainIndustryId());
			
			SalaryGrowthSchMajorRankingList salaryGrowthSchMajorRankingList = new SalaryGrowthSchMajorRankingList();
			salaryGrowthSchMajorRankingList.setRank_index(i + 1);
			salaryGrowthSchMajorRankingList.setMajor_id(salaryGrowRatioRank.getMajorId());
			salaryGrowthSchMajorRankingList.setMajor_name(majorInfo.getMajorName());
			salaryGrowthSchMajorRankingList.setMajor_category(majorInfo.getMajorCategory());
			salaryGrowthSchMajorRankingList.setMain_industry(industryInfo.getIndustryShortName());
			salaryGrowthSchMajorRankingList.setMain_function(salaryGrowRatioRank.getMainFunction());
			salaryGrowthSchMajorRankingList.setGrow_ratio(salaryGrowRatioRank.getGrowRatio());
			salaryGrowthSchMajorRankingList.setGrow_ratio_rank(salaryGrowRatioRank.getGrowRatioRank());
			salaryGrowthSchMajorRankingList.setSalary_factor(salaryGrowRatioRank.getSalaryFactor5year());
			salaryGrowthSchMajorRankingList.setCountry_growth_ratio(salaryGrowRatioRank.getMajorAvg());
			salaryGrowthSchMajorRankingList.setPercent_ratio(salaryGrowRatioRank.getPercentRatio());
			salaryGrowthSchMajorRankingList.setMajor_has_stats(true);
			schMajorRankingLists.add(salaryGrowthSchMajorRankingList);
		}
		schMajorRankingListResult.setMajors(schMajorRankingLists);
		
		return schMajorRankingListResult;
	}

	/**
	 * 转换为女性比例学校专业排行榜结果
	 * @param femaleRankList 女性排名列表
	 * @param majorInfoList 专业信息列表
	 * @param industryInfoList 行业信息列表
	 * @return
	 */
	private SchMajorRankingListResult convertToGenderSchMajorRankingListResult(List<FemaleRank> femaleRankList,
			List<MajorInfo> majorInfoList, List<IndustryInfo> industryInfoList) {
		// 转换为map
		Map<String, MajorInfo> majorInfoMap = new HashMap<String, MajorInfo>();
		if (majorInfoList != null) {
			for (MajorInfo majorInfo : majorInfoList) {
				majorInfoMap.put(majorInfo.getMajorId(), majorInfo);
			}
		}
		Map<String, IndustryInfo> industryInfoMap = new HashMap<String, IndustryInfo>();
		if (industryInfoList != null) {
			for (IndustryInfo industryInfo : industryInfoList) {
				industryInfoMap.put(industryInfo.getIndustryId(), industryInfo);
			}
		}
		

		// 拼装结果
		SchMajorRankingListResult schMajorRankingListResult = new SchMajorRankingListResult();
		List<com.ipin.service.rest.beans.SchMajorRankingList> schMajorRankingLists = new ArrayList<com.ipin.service.rest.beans.SchMajorRankingList>();
		for (int i = 0; i < femaleRankList.size(); i++) {
			FemaleRank femaleRank = femaleRankList.get(i);
			MajorInfo majorInfo = majorInfoMap.get(femaleRank.getMajorId());
			IndustryInfo industryInfo = industryInfoMap.get(femaleRank.getMainIndustryId());
			
			GenderSchMajorRankingList genderSchMajorRankingList = new GenderSchMajorRankingList();
			genderSchMajorRankingList.setRank_index(i + 1);
			genderSchMajorRankingList.setMajor_id(femaleRank.getMajorId());
			genderSchMajorRankingList.setMajor_name(majorInfo.getMajorName());
			genderSchMajorRankingList.setMajor_category(majorInfo.getMajorCategory());
			genderSchMajorRankingList.setMain_industry(industryInfo.getIndustryShortName());
			genderSchMajorRankingList.setMain_function(femaleRank.getMainFunction());
			genderSchMajorRankingList.setFemale_ratio(femaleRank.getFemaleRatio());
			genderSchMajorRankingList.setFemale_rank(femaleRank.getFemaleRank());
			genderSchMajorRankingList.setCountry_female_ratio(femaleRank.getMajorAvgRatio());
			genderSchMajorRankingList.setMajor_has_stats(true);
			schMajorRankingLists.add(genderSchMajorRankingList);
		}
		schMajorRankingListResult.setMajors(schMajorRankingLists);
		
		return schMajorRankingListResult;
	}
	
	/**
	 * 转换为薪酬学校专业排行榜结果
	 * @param salaryRankList 薪酬排名列表
	 * @param majorInfoList  专业信息列表
	 * @param industryInfoList 行业信息列表
	 * @return
	 */
	private SchMajorRankingListResult convertToSalarySchMajorRankingListResult(List<SalaryRank> salaryRankList,
			List<MajorInfo> majorInfoList, List<IndustryInfo> industryInfoList) {
		// 转换为map
		Map<String, MajorInfo> majorInfoMap = new HashMap<String, MajorInfo>();
		if (majorInfoList != null) {
			for (MajorInfo majorInfo : majorInfoList) {
				majorInfoMap.put(majorInfo.getMajorId(), majorInfo);
			}
		}
		
		Map<String, IndustryInfo> industryInfoMap = new HashMap<String, IndustryInfo>();
		if (industryInfoList != null) {
			for (IndustryInfo industryInfo : industryInfoList) {
				industryInfoMap.put(industryInfo.getIndustryId(), industryInfo);
			}
		}
		
		
		// 拼装结果
		SchMajorRankingListResult schMajorRankingListResult = new SchMajorRankingListResult();
		List<com.ipin.service.rest.beans.SchMajorRankingList> schMajorRankingLists = new ArrayList<com.ipin.service.rest.beans.SchMajorRankingList>();
		for (int i = 0; i < salaryRankList.size(); i++) {
			SalaryRank salaryRank = salaryRankList.get(i);
			MajorInfo majorInfo = majorInfoMap.get(salaryRank.getMajorId());
			IndustryInfo industryInfo = industryInfoMap.get(salaryRank.getMainIndustryId());
			
			SalarySchMajorRankingList salarySchMajorRankingList = new SalarySchMajorRankingList();
			salarySchMajorRankingList.setRank_index(i + 1);
			salarySchMajorRankingList.setMajor_id(salaryRank.getMajorId());
			salarySchMajorRankingList.setMajor_name(majorInfo.getMajorName());
			salarySchMajorRankingList.setMajor_category(majorInfo.getMajorCategory());
			salarySchMajorRankingList.setMain_industry(industryInfo.getIndustryShortName());
			salarySchMajorRankingList.setMain_function(salaryRank.getMainFunction());
			salarySchMajorRankingList.setSalary_factor(salaryRank.getSalaryFactor());
			salarySchMajorRankingList.setSalary_factor_rank(salaryRank.getSalaryFactorRank());
			salarySchMajorRankingList.setGrow_ratio(salaryRank.getGrowRatio5year());
			salarySchMajorRankingList.setCountry_salary_factor(salaryRank.getMajorAvg());
			salarySchMajorRankingList.setPercent_ratio(salaryRank.getPercentRatio());
			salarySchMajorRankingList.setMajor_has_stats(true);
			schMajorRankingLists.add(salarySchMajorRankingList);
		}
		schMajorRankingListResult.setMajors(schMajorRankingLists);
		return schMajorRankingListResult;
	}
	
	/**
	 * 转换为学校排行榜结果
	 * @param rankType 排行榜类型
	 * @param schRankingListPage 学校排行榜分页信息
	 * @return
	 */
	private SchRankingListResult convertToSchRankingListResult(EDU_SCH_RANKING_LIST_TYPE rankType, SchRankingListPage schRankingListPage) {
		SchRankingListResult schRankingListResult = new SchRankingListResult();
		schRankingListResult.setTotal_page(schRankingListPage.getTotalPage());
		List<com.ipin.service.rest.beans.SchRankingList> schRankingLists = new ArrayList<com.ipin.service.rest.beans.SchRankingList>();
		if (EDU_SCH_RANKING_LIST_TYPE.EDU_SCH_RANKING_LIST_TYPE_ZONGHE.equals(rankType)) {
			// 综合排名
			for(SchRankingList schRankingList : schRankingListPage.getSchRankingLists()) {
				TotalSchRankingList totalSchRankingList = new TotalSchRankingList();
				setBaseInfo(totalSchRankingList, schRankingList);
				totalSchRankingList.setTotal_rank_index(schRankingList.getRankIndex());
				totalSchRankingList.setTotal_rank_str(schRankingList.getTotalRankGrade());
				schRankingLists.add(totalSchRankingList);
			}
		} else if (EDU_SCH_RANKING_LIST_TYPE.EDU_SCH_RANKING_LIST_TYPE_ZHIMINGDU.equals(rankType)) {
			// 知名度排名
			for(SchRankingList schRankingList : schRankingListPage.getSchRankingLists()) {
				FamousSchRankingList famousSchRankingList = new FamousSchRankingList();
				setBaseInfo(famousSchRankingList, schRankingList);
				famousSchRankingList.setFamous_rank_index(schRankingList.getRankIndex());
				famousSchRankingList.setFamous_rank_str(schRankingList.getPopularityGrade());
				schRankingLists.add(famousSchRankingList);
			}
		} else if (EDU_SCH_RANKING_LIST_TYPE.EDU_SCH_RANKING_LIST_TYPE_JINGZHENGLI.equals(rankType)) {
			// 竞争力排名
			for(SchRankingList schRankingList : schRankingListPage.getSchRankingLists()) {
				CompetitivenessSchRankingList competitivenessSchRankingList = new CompetitivenessSchRankingList();
				setBaseInfo(competitivenessSchRankingList, schRankingList);
				competitivenessSchRankingList.setCompetitiveness_rank_index(schRankingList.getRankIndex());
				schRankingLists.add(competitivenessSchRankingList);
			}
		} else if (EDU_SCH_RANKING_LIST_TYPE.EDU_SCH_RANKING_LIST_TYPE_XINCHOU.equals(rankType)) {
			// 薪酬排名
			for(SchRankingList schRankingList : schRankingListPage.getSchRankingLists()) {
				SalarySchRankingList salarySchRankingList = new SalarySchRankingList();
				setBaseInfo(salarySchRankingList, schRankingList);
				salarySchRankingList.setSalary_factor_rank_index(schRankingList.getRankIndex());
				salarySchRankingList.setSalary_factor_rank_str(schRankingList.getSalaryFactorGrade());
				schRankingLists.add(salarySchRankingList);
			}
		} else if (EDU_SCH_RANKING_LIST_TYPE.EDU_SCH_RANKING_LIST_TYPE_MEIZHI.equals(rankType)) {
			// 妹纸排名
			for(SchRankingList schRankingList : schRankingListPage.getSchRankingLists()) {
				GenderSchRankingList genderSchRankingList = new GenderSchRankingList();
				setBaseInfo(genderSchRankingList, schRankingList);
				genderSchRankingList.setFemale_rank_index(schRankingList.getRankIndex());
				double femaleRatio = (schRankingList.getFemaleRatio() / 100.0);
				genderSchRankingList.setFemale_ratio(ParameterUtils.roundDouble(femaleRatio, 2));
				schRankingLists.add(genderSchRankingList);
			}
		}
		schRankingListResult.setSchools(schRankingLists);
		return schRankingListResult;
	}
	
	/**
	 * 填充基础信息
	 * @param schRankingList 填充的排行榜信息
	 * @param schRankingListInfo
	 */
	private void setBaseInfo(com.ipin.service.rest.beans.SchRankingList schRankingList, SchRankingList schRankingListInfo) {
		schRankingList.setRank_index(schRankingListInfo.getRankIndex());
		schRankingList.setSch_id(schRankingListInfo.getSchId());
		schRankingList.setSch_name(schRankingListInfo.getSchName());
		if (schRankingListInfo.getSchTypeNameList() != null && schRankingListInfo.getSchTypeNameList().size() > 0) {
			schRankingList.setSch_type(schRankingListInfo.getSchTypeNameList().get(0));
		}
		schRankingList.setDegrees(ParameterUtils.listToString(schRankingListInfo.getDiplomaViewNameList(), "/"));
		schRankingList.setLocation(schRankingListInfo.getProvinceName());
	}
	
	/**
	 * 转换成学校详情结果
	 * @param schInfo 学校信息
	 * @param schStatInfo 学校统计信息
	 * @return
	 * @throws TException
	 */
	private SchDetailResult convertToSchoolDetailResult(SchInfo schInfo, SchStatInfo schStatInfo,
			SchAreaSalaryStatInfo countrySalaryStatInfo, SchAreaSalaryStatInfo provinceSalaryStatInfo,
			SchRankingListPage countrySchRankingListPage, SchRankingListPage provinceSchRankingListPage)
					throws TException {
		SchDetailResult schoolDetailResult = new SchDetailResult();
		schoolDetailResult.setSch_id(schInfo.getSchId());
		schoolDetailResult.setSch_name(schInfo.getSchName());
		schoolDetailResult.setSch_old_name(schInfo.getSchPreNameList());
		schoolDetailResult.setSch_address(convertToString(schInfo.getSchAddress()));
		schoolDetailResult.setSch_alias_names(schInfo.getSchAliasNameList());
		schoolDetailResult.setSch_city_id(schInfo.getCityId());
		schoolDetailResult.setSch_competentdept(schInfo.getSchCompetentdept());
		schoolDetailResult.setSch_diplomas(schInfo.getSchDiplomaNameList());
		schoolDetailResult.setSch_diplomas_view(schInfo.getSchDiplomaViewList());
		schoolDetailResult.setSch_intro(schInfo.getSchIntro());
		schoolDetailResult.setSch_province_id(schInfo.getProvinceId());
		schoolDetailResult.setSch_special_enrolls(schInfo.getSchSpecialEnrollList());
		schoolDetailResult.setSch_tel_num(convertToString(schInfo.getSchTelNumList()));
		schoolDetailResult.setSch_type_names(schInfo.getSchTypeNameList());
		schoolDetailResult.setSch_website(schInfo.getSchWebsite());
		schoolDetailResult.setSample_count(schStatInfo.getSampleCount());
		schoolDetailResult.setSch_grade_tag(schInfo.getSchGradeList());
		if (schStatInfo.getRankInfo() != null) {
			schoolDetailResult.setTotal_grade_str(EduStatUtils.getTotalGradeDesc(schStatInfo.getRankInfo()));
			schoolDetailResult.setSalary_factor_ratio(EduStatUtils.getRankRatio(schStatInfo.getRankInfo().getSalaryFactorRank(), schStatInfo.getRankInfo().getSalaryFactorRankIndex()));
			schoolDetailResult.setSocial_factor_ratio(EduStatUtils.getRankRatio(schStatInfo.getRankInfo().getPopularityRank(), schStatInfo.getRankInfo().getPopularityRankIndex()));
			schoolDetailResult.setCity_ratio(EduStatUtils.getCityRankRatio(schStatInfo.getRankInfo().getSchCityLevel()));
			schoolDetailResult.setSalary_factor((int)ParameterUtils.roundDouble(schStatInfo.getRankInfo().getSalaryFactor(), 0));
			schoolDetailResult.setSalary_factor_rank(EduStatUtils.getSalaryFactorRank(schStatInfo.getRankInfo().getSalaryFactorRank()));
			schoolDetailResult.setSalary_factor_index(schStatInfo.getRankInfo().getSalaryFactorRankIndex());
		}
		schoolDetailResult.setIndustry_dis(EduStatUtils.convertToIndustryDistList(schStatInfo.getIndustryDistInfoList()));
		schoolDetailResult.setIndustry_status(EduStatUtils.getIndustryDistDesc(schoolDetailResult.getIndustry_dis()));
		schoolDetailResult.setLocation_dis(EduStatUtils.convertToLocationDistList(schStatInfo.getCurrentLocationInfoList()));
		if (schoolDetailResult.getLocation_dis().size() > 0) {
			schoolDetailResult.setTop_worker_city(schoolDetailResult.getLocation_dis().get(0).getCity_id());
			schoolDetailResult.setTop_worker_city_ratio(schoolDetailResult.getLocation_dis().get(0).getRatio());
		}
		schoolDetailResult.setGender_dis(EduStatUtils.convertToGenderDistList(schStatInfo.getCurrentGenderInfo(), schStatInfo.getGenderInfo(), schStatInfo.getSampleCount()));
		schoolDetailResult.setGender_status(EduStatUtils.getGenderDistDesc(schoolDetailResult.getGender_dis()));
		schoolDetailResult.setSalary_stats(EduStatUtils.convertToSalaryStatList(schStatInfo.getSalaryDetailInfoList(), schStatInfo.getSalaryPredictWeightList()));
		schoolDetailResult.setPredict_salary_stats(EduStatUtils.convertToPredictSalaryStat(schStatInfo.getPredictSalaryInfoList(), schStatInfo.getSalaryPredictWeightList()));
		schoolDetailResult.setSalary_predict_show_type(schStatInfo.getSalaryPredictShowType());
		schoolDetailResult.setCountry_salary_stats(EduStatUtils.convertToSalaryStatList(countrySalaryStatInfo.getSalaryInfoList(), null));
		schoolDetailResult.setProvince_salary_stats(EduStatUtils.convertToSalaryStatList(provinceSalaryStatInfo.getSalaryInfoList(), null));
		if (countrySchRankingListPage == null || countrySchRankingListPage.getSchRankingLists() == null
				|| countrySchRankingListPage.getSchRankingLists().isEmpty()) {
			schoolDetailResult.setIn_country_salary_index(-1);
			schoolDetailResult.setIn_country_salary_total(-1);
		} else {
			schoolDetailResult.setIn_country_salary_index(countrySchRankingListPage.getSchRankingLists().get(0).getRankIndex());
			schoolDetailResult.setIn_country_salary_total(countrySchRankingListPage.getTotalCount());
		}
		if (provinceSchRankingListPage == null || provinceSchRankingListPage.getSchRankingLists() == null
				|| provinceSchRankingListPage.getSchRankingLists().isEmpty()) {
			schoolDetailResult.setIn_province_salary_index(-1);
			schoolDetailResult.setIn_province_salary_total(-1);
		} else {
			schoolDetailResult.setIn_province_salary_index(provinceSchRankingListPage.getSchRankingLists().get(0).getRankIndex());
			schoolDetailResult.setIn_province_salary_total(provinceSchRankingListPage.getTotalCount());
		}
		return schoolDetailResult;
	}
	
	/**
	 * 转换为地址内容
	 * @param addressList
	 * @return
	 */
	private String convertToString(List<String> list) {
		if (list == null || list.size() <= 0) {
			return null;
		}

		if (StringUtils.isNotBlank(list.get(0))) {
			return list.get(0);
		}

		if (list.size() != 2) {
			return list.get(1);
		}
		
		return null;
		
	}
	
	/**
	 * 在招专业
	 * @param schId 学校ID
	 * @param diplomaId 学历ID
	 * @return
	 * @throws TException
	 */
	private SchMajorSettingListResult getSchMajorSettingListResultForEnrollingMajor(String schId, int diplomaId) throws TException{
		SchIdBaseInfo schIdBaseInfo = findSchIdBaseInfo(schId, diplomaId);
		if (schIdBaseInfo == null) {
			// 表明该学校不允许显示
			return null;
		}
		List<String> majorIdList = schIdBaseInfo.getMajorIdList();
		if (majorIdList == null || majorIdList.size() <= 0) {
			return null;
		}
		// 获取专业信息
		List<MajorInfo> majorInfoList = listMajorInfoByIds(majorIdList, true);
		if (majorInfoList == null) {
			return null;
		}
		
		SchMajorSettingListResult majorSettingResult = new SchMajorSettingListResult();
		List<SchMajorSetting> majorSettingList = new ArrayList<SchMajorSetting>();
		majorSettingResult.setMajors(majorSettingList);
		
		// 获取有统计数据的MajorId
		Set<String> hasStatMajorIdSet = distinctHasStatMajorId(schId, diplomaId, majorIdList);
		
		for(MajorInfo majorInfo : majorInfoList) {
			
			// 查询该专业是否有统计信息
			boolean isHasStats = false;
			if (hasStatMajorIdSet.contains(majorInfo.getMajorId())) {
				isHasStats = true;
			}
			
			// 转换为MajorSetting
			majorSettingList.add(convertToSchMajorSetting(majorInfo, isHasStats, true));
		}
		
		
		
		// 二级目录信息
		List<String> majorSecondCategoryList = schIdBaseInfo.getMajorSecondCategoryList();
		if (majorSecondCategoryList != null && majorSecondCategoryList.size() > 0) {
			// 获取有统计信息的专业二级目录
			Set<String> hasStatMajorSecondCategorySet = distinctHasStatMajorSecondCategory(schId, diplomaId, majorSecondCategoryList);
			List<MajorCateInfo> majorSecondCateInfoList = listMajorCateInfo(majorSecondCategoryList, diplomaId);
			if (majorSecondCateInfoList != null) {
				for(MajorCateInfo majorCateInfo : majorSecondCateInfoList) {
					// 查询该专业是否有统计信息
					boolean isHasStats = false;
					if (hasStatMajorSecondCategorySet.contains(majorCateInfo.getMajorSecondCateName())) {
						isHasStats = true;
					}
					
					majorSettingList.add(convertToSchMajorSetting(majorCateInfo, isHasStats));
				}	
			}
		}
		
		return majorSettingResult;
	}
	
	/**
	 * 全部专业(含已停办)
	 * @param schId 学校ID
	 * @param diplomaId 学历ID
	 * @return
	 * @throws TException
	 */
	private SchMajorSettingListResult getSchMajorSettingListResultForAllMajor(String schId, int diplomaId) throws TException {
		// 获取有统计数据的MajorId
		Set<String> hasStatMajorIdSet = distinctHashStatMajorIdBySampleCount(schId, diplomaId, Constants.MAJOR_SETTING_SAMPLE_COUNT);
		
		SchMajorSettingListResult majorSettingResult = new SchMajorSettingListResult();
		List<SchMajorSetting> majorSettingList = new ArrayList<SchMajorSetting>();
		majorSettingResult.setMajors(majorSettingList);

		// 查询专业信息
		List<String> majorIdList = new ArrayList<String>(hasStatMajorIdSet);
		List<MajorInfo> majorInfoList = listMajorInfoByIds(majorIdList, true);
		
		// 专业详情
		if (majorInfoList != null) {
			for (MajorInfo majorInfo : majorInfoList) {
				majorSettingList.add(convertToSchMajorSetting(majorInfo, true, true));
			}
		}
		
		return majorSettingResult;
	}
	
	/**
	 * 各省在招专业
	 * @param schId 学校ID
	 * @param diplomaId 学历ID
	 * @param provinceFilter 省份ID
	 * @param wenliFilter 文理
	 * @param yearFilter 招生年份
	 * @return
	 * @throws TException
	 */
	private SchMajorSettingListResult getSchMajorSettingListResultForEnrollingMajorPerProvince(String schId, int diplomaId,
			String provinceFilter, String wenliFilter, String yearFilter) throws TException {
		
		// 查询学校招生计划信息
		ListSchEnrollPlanInfoParams listSchEnrollPlanInfoParams = new ListSchEnrollPlanInfoParams();
		listSchEnrollPlanInfoParams.setSchId(schId);
		listSchEnrollPlanInfoParams.setDiplomaId(EDU_DIPLOMA.findByValue(diplomaId));
		listSchEnrollPlanInfoParams.setErollLocType(EDU_ENROLL_LOCATION.EDU_ENROLL_LOCATION_BY_PROVINCE);
		// 省份
		if (!StringUtils.isBlank(provinceFilter)) {
			// 省份
			listSchEnrollPlanInfoParams.setLocName(provinceFilter);
		}
		
		// 文理
		if (Constants.WEN_KE.equals(wenliFilter)) {
			listSchEnrollPlanInfoParams.setEduTypeWenli(EDU_TYPE_WENLI.EDU_TYPE_WEN);
		} else if (Constants.LI_KE.equals(wenliFilter)) {
			listSchEnrollPlanInfoParams.setEduTypeWenli(EDU_TYPE_WENLI.EDU_TYPE_LI);
		}
		// 招生年份
		if (StringUtils.isNotBlank(yearFilter)) {
			listSchEnrollPlanInfoParams.setYear(Integer.parseInt(yearFilter));
		}
		List<SchEnrollPlanInfo> schEnrollPlanInfoList = eduEnrollService.listSchEnrollPlanInfo(listSchEnrollPlanInfoParams);
		if (schEnrollPlanInfoList == null || schEnrollPlanInfoList.size() <= 0) {
			return null;
		}
		
		SchMajorSettingListResult majorSettingResult = new SchMajorSettingListResult();
		List<SchMajorSetting> majorSettingList = new ArrayList<SchMajorSetting>();
		majorSettingResult.setMajors(majorSettingList);
		
		// 获取MajorIdList
		Set<String> majorIdSet = new HashSet<String>();
		Set<String> majorSecondCategorySet = new HashSet<String>();
		
		for (SchEnrollPlanInfo schEnrollPlanInfo : schEnrollPlanInfoList) {
			if (!schEnrollPlanInfo.getMajorName().endsWith("类")) {
				if (StringUtils.isNotBlank(schEnrollPlanInfo.getMajorId())
						&& !majorIdSet.contains(schEnrollPlanInfo.getMajorId())) {
					majorIdSet.add(schEnrollPlanInfo.getMajorId());
				} else {
					// 其他, 暂时不处理
				}
			} else if (!majorSecondCategorySet.contains(schEnrollPlanInfo.getMajorName())) {
				majorSecondCategorySet.add(schEnrollPlanInfo.getMajorName());
			}
		}
		
		// 查询专业信息列表
		List<String> majorIdList = new ArrayList<String>(majorIdSet);
		if (majorIdList.size() > 0) {
			// 获取有统计数据的MajorId
			Set<String> hasStatMajorIdSet = distinctHasStatMajorId(schId, diplomaId, majorIdList);
			List<MajorInfo> majorInfoList = listMajorInfoByIds(majorIdList, true);
			if (majorInfoList != null) {
				for(MajorInfo majorInfo : majorInfoList) {
					// 查询该专业是否有统计信息
					boolean isHasStats = false;
					if (hasStatMajorIdSet.contains(majorInfo.getMajorId())) {
						isHasStats = true;
					}
					
					// 转换为MajorSetting
					majorSettingList.add(convertToSchMajorSetting(majorInfo, isHasStats, true));
				}
			}
		}
		
		// 查询专业二级目录信息列表
		List<String> majorSecondCateList =  new ArrayList<String>(majorSecondCategorySet);
		if (majorSecondCateList.size() > 0) {
			// 获取有统计数据的专业二级目录
			Set<String> hasStatMajorSecondCategorySet = distinctHasStatMajorSecondCategory(schId, diplomaId, majorSecondCateList);
			List<MajorCateInfo> majorSecondCateInfoList = listMajorCateInfo(majorSecondCateList, diplomaId);
			if (majorSecondCateInfoList != null) {
				for(MajorCateInfo majorCateInfo : majorSecondCateInfoList) {
					// 查询该专业是否有统计信息
					boolean isHasStats = false;
					if (hasStatMajorSecondCategorySet.contains(majorCateInfo.getMajorSecondCateName())) {
						isHasStats = true;
					}
					// 转换为MajorSetting
					majorSettingList.add(convertToSchMajorSetting(majorCateInfo, isHasStats));
				}
			}
		}
		
		
	
		return majorSettingResult;
	}
	
	/**
	 * 获取有统计数据的专业二级目录
	 * @param schId 学校ID
	 * @param diplomaId 学历ID
	 * @return
	 * @throws TException
	 */
	private Set<String> distinctHasStatMajorSecondCategory(String schId, int diplomaId, List<String> majorSecondCategoryList) throws TException {
		Set<String> hasStatMajorSecondCategorySet = new HashSet<String>();
		ListSchMajorSecondCateStatMarkInfoParams listSchMajorSecondCateStatMarkInfoParams = new ListSchMajorSecondCateStatMarkInfoParams();
		listSchMajorSecondCateStatMarkInfoParams.setSchId(schId);
		listSchMajorSecondCateStatMarkInfoParams.setDiplomaId(EDU_DIPLOMA.findByValue(diplomaId));
		if (majorSecondCategoryList != null && majorSecondCategoryList.size() > 0) {
			listSchMajorSecondCateStatMarkInfoParams.setMajorSecondCateList(majorSecondCategoryList);
		}
		List<SchMajorSecondCateStatMarkInfo> schMajorSecondCateMarkInfoList = eduStatService.listSchMajorSecondCateStatMarkInfo(listSchMajorSecondCateStatMarkInfoParams);
		if (schMajorSecondCateMarkInfoList != null) {
			for(SchMajorSecondCateStatMarkInfo schMajorSecondCateStatMarkInfo : schMajorSecondCateMarkInfoList) {
				if (schMajorSecondCateStatMarkInfo.isHasStatData()) {
					hasStatMajorSecondCategorySet.add(schMajorSecondCateStatMarkInfo.getMajorSecondCate());
				}
			}
		}
		return hasStatMajorSecondCategorySet;
	}
	
	/**
	 * 获取有统计数据的专业ID
	 * @param schId 学校ID
	 * @param diplomaId 学历ID
	 * @param sampleCount 样本数
	 * @return
	 * @throws TException
	 */
	private Set<String> distinctHashStatMajorIdBySampleCount(String schId, int diplomaId, int sampleCount) throws TException {
		Set<String> hasStatMajorIdSet = new HashSet<String>();
		ListSchMajorStatInfoParams listSchMajorStatInfoParams = new ListSchMajorStatInfoParams();
		List<String> schIdList = new ArrayList<String>();
		schIdList.add(schId);
		listSchMajorStatInfoParams.setSchIdList(schIdList);
		listSchMajorStatInfoParams.setDiplomaId(EDU_DIPLOMA.findByValue(diplomaId));
		listSchMajorStatInfoParams.setSampleCount(sampleCount);
		listSchMajorStatInfoParams.setSimpleData(true);
		List<SchMajorStatInfo> schMajorStatInfoList = eduStatService.listSchMajorStatInfo(listSchMajorStatInfoParams);
		
		if (schMajorStatInfoList != null) {
			for (SchMajorStatInfo schMajorStatInfo : schMajorStatInfoList) {
				hasStatMajorIdSet.add(schMajorStatInfo.getMajorId());
			}
		}
		return hasStatMajorIdSet;
	}
	
	/**
	 * 获取有统计数据的专业ID
	 * @param schId 学校ID
	 * @param diplomaId 学历ID
	 * @return
	 * @throws TException
	 */
	private Set<String> distinctHasStatMajorId(String schId, int diplomaId, List<String> majorIdList) throws TException{
		Set<String> hasStatMajorIdSet = new HashSet<String>();
		
		ListSchMajorStatMarkInfoParams listSchMajorStatMarkInfoParams = new ListSchMajorStatMarkInfoParams();
		listSchMajorStatMarkInfoParams.setSchId(schId);
		listSchMajorStatMarkInfoParams.setDiplomaId(EDU_DIPLOMA.findByValue(diplomaId));
		if (majorIdList != null && majorIdList.size() > 0) {
			listSchMajorStatMarkInfoParams.setMajorIdList(majorIdList);
		}
		List<SchMajorStatMarkInfo> schMajorStatMarkInfoList = eduStatService.listSchMajorStatMarkInfo(listSchMajorStatMarkInfoParams);
		
		if (schMajorStatMarkInfoList != null) {
			for (SchMajorStatMarkInfo schMajorStatMarkInfo : schMajorStatMarkInfoList) {
				if (schMajorStatMarkInfo.isHasStatData()) {
					hasStatMajorIdSet.add(schMajorStatMarkInfo.getMajorId());
				}
			}
		}
		
		return hasStatMajorIdSet;
	}
	
	/**
	 * 从学校招生计划信息列表中获取专业ID列表
	 * @param schEnrollPlanInfoList 学校招生计划信息列表
	 * @return
	 */
	private List<String> distinctMajorId(List<SchEnrollPlanInfo> schEnrollPlanInfoList) {
		Set<String> majorIdSet = new HashSet<String>();
		for (SchEnrollPlanInfo schEnrollPlanInfo : schEnrollPlanInfoList) {
			majorIdSet.add(schEnrollPlanInfo.getMajorId());
		}
		return new ArrayList<String>(majorIdSet);
	}
	
	/**
	 * 获取专业信息Map
	 * @param majorIdList 专业id列表
	 * @param simpleData 是否是简单数据
	 * @return
	 * @throws TException
	 */
	private Map<String, MajorInfo> getMajorInfoMapByIds(List<String> majorIdList, boolean simpleData)
			throws TException {
		Map<String, MajorInfo> majorInfoMap = new HashMap<String, MajorInfo>();
		List<MajorInfo> majorInfoList = listMajorInfoByIds(majorIdList, simpleData);
		if (majorInfoList != null) {
			for (MajorInfo majorInfo : majorInfoList) {
				majorInfoMap.put(majorInfo.getMajorId(), majorInfo);
			}
		}
		return majorInfoMap;
	}
	
	/**
	 * 获取专业信息列表根据id列表
	 * @param majorIdList 专业id列表
	 * @param siimpleData 是否是简单数据
	 * @return
	 */
	private List<MajorInfo> listMajorInfoByIds(List<String> majorIdList, boolean simpleData) throws TException{
		ListMajorInfoByIdsParams listMajorInfoByIdsParams = new ListMajorInfoByIdsParams();
		listMajorInfoByIdsParams.setMajorIdList(majorIdList);
		listMajorInfoByIdsParams.setSimpleData(simpleData);
		return eduInfoService.listMajorInfoByIds(listMajorInfoByIdsParams);
	}
	
	/**
	 * 获取专业目录列表
	 * @param majorSecondCateList 专业二级目录列表
	 * @param diplomaId 学历ID
	 * @return
	 * @throws TException
	 */
	private List<MajorCateInfo> listMajorCateInfo(List<String> majorSecondCateList, int diplomaId) throws TException {
		ListMajorCateInfoParams listMajorCateInfoParams = new ListMajorCateInfoParams();
		listMajorCateInfoParams.setDiplomaId(EDU_DIPLOMA.findByValue(diplomaId));
		listMajorCateInfoParams.setMajorSecondCateList(majorSecondCateList);
		return eduInfoService.listMajorCateInfo(listMajorCateInfoParams);
	}
	
	/**
	 * 转换为MajorSetting对象
	 * @param majorCateInfo 专业目录信息
	 * @param isHashStats 是否有统计信息
	 */
	private SchMajorSetting convertToSchMajorSetting(MajorCateInfo majorCateInfo, boolean isHashStats) {
		SchMajorSetting majorSetting = new SchMajorSetting();
		majorSetting.setMajor_category(majorCateInfo.getMajorCateName());
		majorSetting.setMajor_second_category(majorCateInfo.getMajorSecondCateName());
		majorSetting.setMajor_name(majorCateInfo.getMajorSecondCateName());
		majorSetting.setIs_major_id(false);
		majorSetting.setMajor_has_stats(isHashStats);
		return majorSetting;
	}
	
	/**
	 * 转换为MajorSetting对象
	 * @param majorInfo 专业信息
	 * @param isHasStats 是否有统计信息
	 * @return MajorSetting对象
	 */
	private SchMajorSetting convertToSchMajorSetting(MajorInfo majorInfo, boolean isHasStats, boolean isMajorId) {
		SchMajorSetting majorSetting = new SchMajorSetting();
		majorSetting.setMajor_id(majorInfo.getMajorId());
		majorSetting.setMajor_name(majorInfo.getMajorName());
		majorSetting.setMajor_second_category(majorInfo.getMajorSecondCategory());
		majorSetting.setMajor_category(majorInfo.getMajorCategory());
		majorSetting.setMajor_has_stats(isHasStats);
		majorSetting.setIs_major_id(isMajorId);
		return majorSetting;
	}
	
	/**
	 * 查询SchIdBase是否存在
	 * @param schId 学校ID
	 * @param diplomaId 学历ID
	 * @return
	 */
	private SchIdBaseInfo findSchIdBaseInfo(String schId, int diplomaId) throws TException{
		FindSchIdBaseInfoParams findSchIdBaseInfoParams = new FindSchIdBaseInfoParams();
		findSchIdBaseInfoParams.setSchId(schId);
		findSchIdBaseInfoParams.setDiplomaId(EDU_DIPLOMA.findByValue(diplomaId));
		return eduInfoService.findSchIdBaseInfo(findSchIdBaseInfoParams);
	}

	/**
	 * 判断SchIdBase是否存在
	 * @param schId 学校ID
	 * @param diplomaId 学历ID
	 * @return
	 * @throws TException
	 */
	private boolean existSchIdBaseInfo(String schId, int diplomaId) throws TException {
		return findSchIdBaseInfo(schId, diplomaId) != null;
	}
	
	/**
	 * 获取省份ID
	 * @param provinceName 省份名字
	 * @return
	 * @throws TException
	 */
	private String getProvinceId(String provinceName) throws TException{
		boolean hasKey = redisTemplate.opsForHash().hasKey(Constants.REDIS_LOCATION_KEY, Constants.REDIS_ALL_PROVINCE_HASH_KEY);
		List<ProvinceInfo> provinceInfoList = null;
		if (hasKey) {
			provinceInfoList = (List<ProvinceInfo>) redisTemplate.opsForHash().get(Constants.REDIS_LOCATION_KEY, Constants.REDIS_ALL_PROVINCE_HASH_KEY);
		} else {
			provinceInfoList = commonService.getAllProvince(null);
			if (provinceInfoList != null) {
				redisTemplate.opsForHash().put(Constants.REDIS_LOCATION_KEY, Constants.REDIS_ALL_PROVINCE_HASH_KEY, provinceInfoList);
			}
		}
		
		return LocationUtils.getProvinceId(provinceInfoList, provinceName);
	}

}
