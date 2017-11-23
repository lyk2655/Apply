package com.ipin.service.rest.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ipin.service.rest.beans.GenderDist;
import com.ipin.service.rest.beans.SchFilter;
import com.ipin.service.rest.beans.impl.MajorResult;
import com.ipin.service.rest.constants.Constants;
import com.ipin.service.rest.utils.EduStatUtils;
import com.ipin.service.rest.utils.LocationUtils;
import com.ipin.service.rest.utils.ParameterUtils;
import com.ipin.thrift.common.CommonService;
import com.ipin.thrift.common.ProvinceInfo;
import com.ipin.thrift.edu.EduInfoService;
import com.ipin.thrift.edu.EduStatService;
import com.ipin.thrift.edu.SchIdBaseInfo;
import com.ipin.thrift.edu.SchInfo;
import com.ipin.thrift.edu.SchRankingList;
import com.ipin.thrift.edu.SchRankingListPage;
import com.ipin.thrift.edu.SchStatInfo;
import com.ipin.thrift.edu.commons.EDU_DIPLOMA;
import com.ipin.thrift.edu.commons.EDU_SCH_RANKING_LIST_TYPE;
import com.ipin.thrift.edu.params.FindSchInfoParams;
import com.ipin.thrift.edu.params.FindSchStatInfoParams;
import com.ipin.thrift.edu.params.ListSchIdBaseInfoParams;
import com.ipin.thrift.edu.params.ListSchInfoParams;
import com.ipin.thrift.edu.params.PageSchRankingListParams;

@Component
@Lazy(false)
public class CacheSchoolFilterListTask extends Task {
	
private static final Logger logger = LoggerFactory.getLogger(CacheSchoolFilterListTask.class);
	
	@Resource
	private EduInfoService.Iface eduInfoService;
	@Resource
	private EduStatService.Iface eduStatService;
	@Resource
	private CommonService.Iface commonService;
	
	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate<String, MajorResult> redisTemplate;
	
	@Scheduled(cron = "${schedule.cache.schoolfilterlist.cron}")
	public void doTask() {
		try {
			logger.debug("Updating bk sch filter list....");
			// 获取省份列表
			List<ProvinceInfo> provinceInfoList = getProvinceInfoList();
			
			// 先计算本科所有学校排名
			ListSchIdBaseInfoParams listSchIdBaseInfoParams = new ListSchIdBaseInfoParams();
			listSchIdBaseInfoParams.setDiplomaId(EDU_DIPLOMA.EDU_DIPLOMA_BK);
			List<SchIdBaseInfo> schIdBaseInfoList = eduInfoService.listSchIdBaseInfo(listSchIdBaseInfoParams);
			if(schIdBaseInfoList != null && schIdBaseInfoList.size() > 0) {
				List<String> schIdList = new ArrayList<String>();
				for(SchIdBaseInfo schIdBaseInfo : schIdBaseInfoList) {
					schIdList.add(schIdBaseInfo.getSchId());
				}
				ListSchInfoParams listSchInfoParams = new ListSchInfoParams();
				listSchInfoParams.setSchIdList(schIdList);
				listSchInfoParams.setSimpleData(true);
				List<SchInfo> schInfoList = eduInfoService.listSchInfo(listSchInfoParams);
				if (schInfoList != null && schInfoList.size() > 0) {
					Map<String, SchInfo> schInfoMap = this.convertToSchMap(schInfoList);
					// 查询学校统计信息
					Map<String, SchStatInfo> schStatInfoMap = new HashMap<String, SchStatInfo>();
					for(SchInfo schInfo : schInfoList) {
						FindSchStatInfoParams findSchStatInfoParams = new FindSchStatInfoParams();
						findSchStatInfoParams.setSchId(schInfo.getSchId());
						findSchStatInfoParams.setDiploma(EDU_DIPLOMA.EDU_DIPLOMA_BK);
						SchStatInfo schStatInfo = eduStatService.findSchStatInfo(findSchStatInfoParams);
						if(schStatInfo != null) {
							schStatInfoMap.put(schStatInfo.getSchId(), schStatInfo);
						}
					}
					
					// 缓存本科综合学校过滤
					List<SchFilter> schFilterList = cacheBKSchFilterByTotalRank(schInfoMap, schStatInfoMap, provinceInfoList);
					
					if(schFilterList != null && schFilterList.size() > 0) {
						// 缓存本科知名度学校过滤
						cacheSchFilter(schInfoMap, schStatInfoMap, provinceInfoList, EDU_SCH_RANKING_LIST_TYPE.EDU_SCH_RANKING_LIST_TYPE_ZHIMINGDU,
								Constants.REDIS_SCH_FILTER_BK_ZHIMINGDU_HASH_KEY, EDU_DIPLOMA.EDU_DIPLOMA_BK, true);
						// 缓存本科竞争力学校过滤
						cacheSchFilter(schInfoMap, schStatInfoMap, provinceInfoList, EDU_SCH_RANKING_LIST_TYPE.EDU_SCH_RANKING_LIST_TYPE_JINGZHENGLI,
								Constants.REDIS_SCH_FILTER_BK_JINGZHENGLI_HASH_KEY, EDU_DIPLOMA.EDU_DIPLOMA_BK, true);
						// 缓存本科薪酬学校过滤
						cacheSchFilter(schInfoMap, schStatInfoMap, provinceInfoList, EDU_SCH_RANKING_LIST_TYPE.EDU_SCH_RANKING_LIST_TYPE_XINCHOU,
								Constants.REDIS_SCH_FILTER_BK_XINCHOU_HASH_KEY, EDU_DIPLOMA.EDU_DIPLOMA_BK, true);
						// 缓存本科妹纸学校过滤
						cacheSchFilter(schInfoMap, schStatInfoMap, provinceInfoList, EDU_SCH_RANKING_LIST_TYPE.EDU_SCH_RANKING_LIST_TYPE_MEIZHI,
								Constants.REDIS_SCH_FILTER_BK_MEIZHI_HASH_KEY, EDU_DIPLOMA.EDU_DIPLOMA_BK, true);
						// 缓存本科男纸学校过滤
						cacheSchFilter(schInfoMap, schStatInfoMap, provinceInfoList, EDU_SCH_RANKING_LIST_TYPE.EDU_SCH_RANKING_LIST_TYPE_MEIZHI,
								Constants.REDIS_SCH_FILTER_BK_NANZHI_HASH_KEY, EDU_DIPLOMA.EDU_DIPLOMA_BK, false);
					}
					
				}
			}
			logger.debug("Update bk sch filter list success....");
			
			logger.debug("Updating zk sch filter list....");
			// 计算专科所有学校排名
			listSchIdBaseInfoParams = new ListSchIdBaseInfoParams();
			listSchIdBaseInfoParams.setDiplomaId(EDU_DIPLOMA.EDU_DIPLOMA_ZK);
			schIdBaseInfoList = eduInfoService.listSchIdBaseInfo(listSchIdBaseInfoParams);
			if(schIdBaseInfoList != null && schIdBaseInfoList.size() > 0) {
				List<String> schIdList = new ArrayList<String>();
				for(SchIdBaseInfo schIdBaseInfo : schIdBaseInfoList) {
					schIdList.add(schIdBaseInfo.getSchId());
				}
				ListSchInfoParams listSchInfoParams = new ListSchInfoParams();
				listSchInfoParams.setSchIdList(schIdList);
				listSchInfoParams.setSimpleData(true);
				List<SchInfo> schInfoList = eduInfoService.listSchInfo(listSchInfoParams);
				if (schInfoList != null && schInfoList.size() > 0) {
					Map<String, SchInfo> schInfoMap = this.convertToSchMap(schInfoList);
					// 查询学校统计信息
					Map<String, SchStatInfo> schStatInfoMap = new HashMap<String, SchStatInfo>();
					for(SchInfo schInfo : schInfoList) {
						FindSchStatInfoParams findSchStatInfoParams = new FindSchStatInfoParams();
						findSchStatInfoParams.setSchId(schInfo.getSchId());
						findSchStatInfoParams.setDiploma(EDU_DIPLOMA.EDU_DIPLOMA_ZK);
						SchStatInfo schStatInfo = eduStatService.findSchStatInfo(findSchStatInfoParams);
						if(schStatInfo != null) {
							schStatInfoMap.put(schStatInfo.getSchId(), schStatInfo);
						}
					}
					
					// 缓存专科薪酬学校过滤
					List<SchFilter> schFilterList = cacheZKSchFilterBySalary(schInfoMap, schStatInfoMap, provinceInfoList);
					if(schFilterList != null && schFilterList.size() > 0) {
						// 缓存专业妹纸学校过滤
						cacheSchFilter(schInfoMap, schStatInfoMap, provinceInfoList, EDU_SCH_RANKING_LIST_TYPE.EDU_SCH_RANKING_LIST_TYPE_MEIZHI,
								Constants.REDIS_SCH_FILTER_ZK_MEIZHI_HASH_KEY, EDU_DIPLOMA.EDU_DIPLOMA_ZK, true);
						// 缓存专业男纸学校过滤
						cacheSchFilter(schInfoMap, schStatInfoMap, provinceInfoList, EDU_SCH_RANKING_LIST_TYPE.EDU_SCH_RANKING_LIST_TYPE_MEIZHI,
								Constants.REDIS_SCH_FILTER_ZK_NANZHI_HASH_KEY, EDU_DIPLOMA.EDU_DIPLOMA_ZK, false);
					}
				}
			}
			logger.debug("Update zk sch filter list success....");
		} catch (TException e) {
			logger.error("Error occured while caching sch filter list, message code : " + e.getMessage(), e);
		}
	}
	
	/**
	 * 转换为学校Map
	 * @param schInfoList 学校信息列表
	 * @return
	 */
	private Map<String, SchInfo> convertToSchMap(List<SchInfo> schInfoList) {
		Map<String, SchInfo> schInfoMap = new HashMap<String, SchInfo>();
		for(SchInfo schInfo : schInfoList) {
			schInfoMap.put(schInfo.getSchId(), schInfo);
		}
		return schInfoMap;
	}
	
	/**
	 * 转换为学校筛选Map
	 * @param schFilterList
	 * @return
	 */
	private Map<String, SchFilter> convertToSchFilterMap(List<SchFilter> schFilterList) {
		Map<String, SchFilter> schFilterMap = new HashMap<String, SchFilter>();
		for(SchFilter schFilter : schFilterList) {
			schFilterMap.put(schFilter.getSch_id(), schFilter);
		}
		return schFilterMap;
	}
	
	/**
	 * 获取省份列表
	 * @return
	 * @throws TException
	 */
	private List<ProvinceInfo> getProvinceInfoList() throws TException{
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
		
		return provinceInfoList;
	}
	
	private class ZKSchFilter extends SchFilter{
		
		private double salaryFactorRank;

		public double getSalaryFactorRank() {
			return salaryFactorRank;
		}

		public void setSalaryFactorRank(double salaryFactorRank) {
			this.salaryFactorRank = salaryFactorRank;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = super.hashCode();
			result = prime * result + getOuterType().hashCode();
			long temp;
			temp = Double.doubleToLongBits(salaryFactorRank);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!super.equals(obj))
				return false;
			if (getClass() != obj.getClass())
				return false;
			ZKSchFilter other = (ZKSchFilter) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (Double.doubleToLongBits(salaryFactorRank) != Double.doubleToLongBits(other.salaryFactorRank))
				return false;
			return true;
		}

		private CacheSchoolFilterListTask getOuterType() {
			return CacheSchoolFilterListTask.this;
		}

		@Override
		public String toString() {
			return "ZKSchFilter [salaryFactorRank=" + salaryFactorRank + "]";
		}
		
	}
	
	/**
	 *  设置基本信息
	 * @param schFilter
	 * @param schRankingListInfo
	 */
	private void setBaseInfo(SchFilter schFilter, SchRankingList schRankingListInfo) {
		schFilter.setRank_index(schRankingListInfo.getRankIndex());
		schFilter.setSch_id(schRankingListInfo.getSchId());
		schFilter.setSch_name(schRankingListInfo.getSchName());
		if (schRankingListInfo.getSchTypeNameList() != null && schRankingListInfo.getSchTypeNameList().size() > 0) {
			schFilter.setSch_type(schRankingListInfo.getSchTypeNameList().get(0));
		}
		schFilter.setDegrees(ParameterUtils.listToString(schRankingListInfo.getDiplomaViewNameList(), "/"));
		schFilter.setLocation(schRankingListInfo.getProvinceName());
	}
	
	/**
	 * 设置基本信息
	 * @param schFilter
	 * @param schInfo
	 * @param rankIndex
	 * @param provinceName
	 * @return
	 */
	private void setBaseInfo(SchFilter schFilter, SchInfo schInfo, int rankIndex, String provinceName) {
		schFilter.setRank_index(rankIndex);
		schFilter.setSch_id(schInfo.getSchId());
		schFilter.setSch_name(schInfo.getSchName());
		if (schInfo.getSchTypeNameList() != null && schInfo.getSchTypeNameList().size() > 0) {
			schFilter.setSch_type(schInfo.getSchTypeNameList().get(0));
		}
		schFilter.setSch_grade_tag(schInfo.getSchGradeList());
		schFilter.setDegrees(ParameterUtils.listToString(schInfo.getSchDiplomaViewList(), "/"));
		schFilter.setLocation(provinceName);
	}
	
	/**
	 * 默认创建学校筛选
	 * @param schInfo
	 * @return
	 */
	private SchFilter createSchFilter(SchInfo schInfo, int rankIndex, String provinceName) {
		SchFilter schFilter = new SchFilter();
		schFilter.setRank_index(rankIndex);
		schFilter.setSch_id(schInfo.getSchId());
		schFilter.setSch_name(schInfo.getSchName());
		if (schInfo.getSchTypeNameList() != null && schInfo.getSchTypeNameList().size() > 0) {
			schFilter.setSch_type(schInfo.getSchTypeNameList().get(0));
		}
		schFilter.setSch_grade_tag(schInfo.getSchGradeList());
		schFilter.setDegrees(ParameterUtils.listToString(schInfo.getSchDiplomaViewList(), "/"));
		schFilter.setLocation(provinceName);
		schFilter.setTotal_rank_str("-");
		return schFilter;
	}
	
	/**
	 * 默认创建学校筛选
	 * @param schFilter
	 * @return
	 */
	private SchFilter createSchFilter(SchFilter schFilter) {
		SchFilter resultSchFilter = new SchFilter();
		resultSchFilter.setRank_index(schFilter.getRank_index());
		resultSchFilter.setSch_id(schFilter.getSch_id());
		resultSchFilter.setSch_name(schFilter.getSch_name());
		resultSchFilter.setSch_type(schFilter.getSch_type());
		resultSchFilter.setSch_grade_tag(schFilter.getSch_grade_tag());
		resultSchFilter.setDegrees(schFilter.getDegrees());
		resultSchFilter.setLocation(schFilter.getLocation());
		resultSchFilter.setTotal_rank_str("-");
		return resultSchFilter;
	}
	
	/**
	 * 缓存本科综合学校过滤
	 * @param schInfoMap
	 * @param schStatInfoMap
	 * @param provinceInfoList
	 * @throws TException
	 */
	private List<SchFilter> cacheSchFilter(Map<String, SchInfo> schInfoMap, Map<String, SchStatInfo> schStatInfoMap, List<ProvinceInfo> provinceInfoList, EDU_SCH_RANKING_LIST_TYPE rankType, String redisHashKey, EDU_DIPLOMA dioploma, boolean desc) throws TException {
		// 查询就业综合排名
		PageSchRankingListParams pageSchRankingListParams = new PageSchRankingListParams();
		pageSchRankingListParams.setRankType(rankType);
		pageSchRankingListParams.setDiplomaId(dioploma);
		pageSchRankingListParams.setCurPage(-1);
		pageSchRankingListParams.setPageSize(-1);
		SchRankingListPage schRankingListPage = eduStatService.pageSchRankingList(pageSchRankingListParams);
		if (schRankingListPage != null && schRankingListPage.getSchRankingLists() != null
				&& schRankingListPage.getSchRankingLists().size() > 0) {
			Set<String> schIdSet = new HashSet<String>(schInfoMap.keySet());
			List<SchFilter> schFilterList = new ArrayList<SchFilter>();
			int i = 0;
			for (; i < schRankingListPage.getSchRankingLists().size(); i++) {
				SchRankingList schRankingList = null;
				if(desc) {
					schRankingList = schRankingListPage.getSchRankingLists().get(i);
				} else {
					schRankingList = schRankingListPage.getSchRankingLists().get(schRankingListPage.getSchRankingLists().size() - i - 1);
				}
				schIdSet.remove(schRankingList.getSchId());
				SchFilter schFilter = new SchFilter();
				SchInfo schInfo = schInfoMap.get(schRankingList.getSchId());
				if (schInfo != null) {
					setBaseInfo(schFilter, schInfo, schRankingList.getRankIndex(), LocationUtils.getProvinceName(provinceInfoList, schInfo.getProvinceId()));
				} else {
					// 没有该学校baseinfo信息，继续查询该学校的schInfo信息
					FindSchInfoParams findSchInfoParams = new FindSchInfoParams();
					findSchInfoParams.setSchId(schRankingList.getSchId());
					findSchInfoParams.setDiplomaId(dioploma);
					schInfo = eduInfoService.findSchInfo(findSchInfoParams);
					if (schInfo != null) {
						setBaseInfo(schFilter, schInfo, schRankingList.getRankIndex(), LocationUtils.getProvinceName(provinceInfoList, schInfo.getProvinceId()));
					} else {
						setBaseInfo(schFilter, schRankingList);
					}
				}
				schFilter.setTotal_rank_str(schRankingList.getTotalRankGrade());
				SchStatInfo schStatInfo = schStatInfoMap.get(schRankingList.getSchId());
				if (schStatInfo != null && schStatInfo.getRankInfo() != null) {
					schFilter.setSalary_factor((int)schStatInfo.getRankInfo().getSalaryFactor());
				} else {
					schFilter.setSalary_factor((int)schRankingList.getSalaryFactor());
				}
				if (schStatInfo != null) {
					List<GenderDist> genderDistList = EduStatUtils.convertToGenderDistList(schStatInfo.getCurrentGenderInfo(), schStatInfo.getGenderInfo(), schStatInfo.getSampleCount());
					if(genderDistList != null && genderDistList.size() == 2) {
						schFilter.setFemale_ratio(genderDistList.get(1).getRatio());
					}
				}
				schFilterList.add(schFilter);
			}
			// 补充剩下的
			for(String schId : schIdSet) {
				SchInfo schInfo = schInfoMap.get(schId);
				if (schInfo == null) {
					continue;
				}
				
				String provinceName = LocationUtils.getProvinceName(provinceInfoList, schInfo.getProvinceId());
				SchFilter schFilter = createSchFilter(schInfo, i, provinceName);
				i++;
				schFilter.setRank_index(i);
				SchStatInfo schStatInfo = schStatInfoMap.get(schFilter.getSch_id());
				if (schStatInfo != null && schStatInfo.getRankInfo() != null) {
					schFilter.setSalary_factor((int) schStatInfo.getRankInfo().getSalaryFactor());
				}
				if (schStatInfo != null) {
					List<GenderDist> genderDistList = EduStatUtils.convertToGenderDistList(schStatInfo.getCurrentGenderInfo(), schStatInfo.getGenderInfo(), schStatInfo.getSampleCount());
					if(genderDistList != null && genderDistList.size() == 2) {
						schFilter.setFemale_ratio(genderDistList.get(1).getRatio());
					}
				}
				if (EDU_SCH_RANKING_LIST_TYPE.EDU_SCH_RANKING_LIST_TYPE_ZONGHE.equals(rankType)) {
					// 综合
					schFilter.setTotal_rank_str("-");
				} else if(EDU_SCH_RANKING_LIST_TYPE.EDU_SCH_RANKING_LIST_TYPE_XINCHOU.equals(rankType)) {
					// 竞争力
					schFilter.setSalary_factor(-1);
				} else if(EDU_SCH_RANKING_LIST_TYPE.EDU_SCH_RANKING_LIST_TYPE_MEIZHI.equals(rankType)) {
					// 妹纸
					schFilter.clearSexRatio();
				}
				
				schFilterList.add(schFilter);
				
			}
			redisTemplate.opsForHash().put(Constants.REDIS_SCH_FILTER_KEY, redisHashKey, schFilterList);
			logger.debug("Update " + redisHashKey + " filter list success...");
			return schFilterList;
		}
		return null;
	}
	
	/**
	 * 缓存本科综合学校过滤
	 * @param schInfoMap
	 * @param schStatInfoMap
	 * @param provinceInfoList
	 * @throws TException
	 */
	private List<SchFilter> cacheBKSchFilterByTotalRank(Map<String, SchInfo> schInfoMap, Map<String, SchStatInfo> schStatInfoMap, List<ProvinceInfo> provinceInfoList) throws TException {
		// 查询就业综合排名
		PageSchRankingListParams pageSchRankingListParams = new PageSchRankingListParams();
		pageSchRankingListParams.setRankType(EDU_SCH_RANKING_LIST_TYPE.EDU_SCH_RANKING_LIST_TYPE_ZONGHE);
		pageSchRankingListParams.setDiplomaId(EDU_DIPLOMA.EDU_DIPLOMA_BK);
		pageSchRankingListParams.setCurPage(-1);
		pageSchRankingListParams.setPageSize(-1);
		SchRankingListPage schRankingListPage = eduStatService.pageSchRankingList(pageSchRankingListParams);
		if (schRankingListPage != null && schRankingListPage.getSchRankingLists() != null
				&& schRankingListPage.getSchRankingLists().size() > 0) {
			Set<String> schIdSet = new HashSet<String>(schInfoMap.keySet());
			List<SchFilter> schFilterList = new ArrayList<SchFilter>();
			int i = 0;
			for (; i < schRankingListPage.getSchRankingLists().size(); i++) {
				SchRankingList schRankingList = schRankingListPage.getSchRankingLists().get(i);
				schIdSet.remove(schRankingList.getSchId());
				SchFilter schFilter = new SchFilter();
				SchInfo schInfo = schInfoMap.get(schRankingList.getSchId());
				if (schInfo != null) {
					setBaseInfo(schFilter, schInfo, schRankingList.getRankIndex(), LocationUtils.getProvinceName(provinceInfoList, schInfo.getProvinceId()));
				} else {
					// 没有该学校baseinfo信息，继续查询该学校的schInfo信息
					FindSchInfoParams findSchInfoParams = new FindSchInfoParams();
					findSchInfoParams.setSchId(schRankingList.getSchId());
					findSchInfoParams.setDiplomaId(EDU_DIPLOMA.EDU_DIPLOMA_BK);
					schInfo = eduInfoService.findSchInfo(findSchInfoParams);
					if (schInfo != null) {
						setBaseInfo(schFilter, schInfo, schRankingList.getRankIndex(), LocationUtils.getProvinceName(provinceInfoList, schInfo.getProvinceId()));
					} else {
						setBaseInfo(schFilter, schRankingList);
					}
				}
				schFilter.setTotal_rank_str(schRankingList.getTotalRankGrade());
				SchStatInfo schStatInfo = schStatInfoMap.get(schRankingList.getSchId());
				if (schStatInfo != null && schStatInfo.getRankInfo() != null) {
					schFilter.setSalary_factor((int)schStatInfo.getRankInfo().getSalaryFactor());
				}
				if (schStatInfo != null) {
					List<GenderDist> genderDistList = EduStatUtils.convertToGenderDistList(schStatInfo.getCurrentGenderInfo(), schStatInfo.getGenderInfo(), schStatInfo.getSampleCount());
					if(genderDistList != null && genderDistList.size() == 2) {
						schFilter.setFemale_ratio(genderDistList.get(1).getRatio());
					}
				}
				schFilterList.add(schFilter);
			}
			// 补充剩下的
			for(String schId : schIdSet) {
				SchInfo schInfo = schInfoMap.get(schId);
				if (schInfo == null) {
					continue;
				}
				
				String provinceName = LocationUtils.getProvinceName(provinceInfoList, schInfo.getProvinceId());
				SchFilter schFilter = createSchFilter(schInfo, i, provinceName);
				i++;
				schFilter.setRank_index(i);
				SchStatInfo schStatInfo = schStatInfoMap.get(schFilter.getSch_id());
				if (schStatInfo != null && schStatInfo.getRankInfo() != null) {
					schFilter.setSalary_factor((int) schStatInfo.getRankInfo().getSalaryFactor());
				}
				if (schStatInfo != null) {
					List<GenderDist> genderDistList = EduStatUtils.convertToGenderDistList(schStatInfo.getCurrentGenderInfo(), schStatInfo.getGenderInfo(), schStatInfo.getSampleCount());
					if(genderDistList != null && genderDistList.size() == 2) {
						schFilter.setFemale_ratio(genderDistList.get(1).getRatio());
					}
				}
				schFilterList.add(schFilter);
				
			}
			redisTemplate.opsForHash().put(Constants.REDIS_SCH_FILTER_KEY, Constants.REDIS_SCH_FILTER_BK_ZONGHE_HASH_KEY, schFilterList);
			logger.debug("Update bk zonghe filter list success...");
			return schFilterList;
		}
		return null;
	}
	
	/**
	 * 缓存学校筛选
	 * @param schInfoMap
	 * @param schStatInfoMap
	 * @param provinceInfoList
	 * @param rankType 排名类型
	 * @param redisHashKey
	 * @param diploma 学历
	 * @throws TException
	 */
	private void cacheSchFilter(List<SchFilter> zongheSchFilterList, Map<String, SchStatInfo> schStatInfoMap, EDU_SCH_RANKING_LIST_TYPE rankType, String redisHashKey, EDU_DIPLOMA dioploma, boolean desc) throws TException {
		// 查询就业综合排名
		PageSchRankingListParams pageSchRankingListParams = new PageSchRankingListParams();
		pageSchRankingListParams.setRankType(rankType);
		pageSchRankingListParams.setDiplomaId(dioploma);
		pageSchRankingListParams.setCurPage(-1);
		pageSchRankingListParams.setPageSize(-1);
		SchRankingListPage schRankingListPage = eduStatService.pageSchRankingList(pageSchRankingListParams);
		if (schRankingListPage != null && schRankingListPage.getSchRankingLists() != null
				&& schRankingListPage.getSchRankingLists().size() > 0) {
			Map<String, SchFilter> schFilterMap = convertToSchFilterMap(zongheSchFilterList);
			Set<String> schIdSet = new HashSet<String>(schFilterMap.keySet());
			List<SchFilter> schFilterList = new ArrayList<SchFilter>();
			int i = 0;
			for (; i < schRankingListPage.getSchRankingLists().size(); i++) {
				SchRankingList schRankingList = null;
				if(desc) {
					schRankingList = schRankingListPage.getSchRankingLists().get(i);
				} else {
					schRankingList = schRankingListPage.getSchRankingLists().get(schRankingListPage.getSchRankingLists().size() - i - 1);
				}
				schIdSet.remove(schRankingList.getSchId());
				SchFilter schFilter = schFilterMap.get(schRankingList.getSchId());
				if(schFilter == null && rankType.equals(EDU_SCH_RANKING_LIST_TYPE.EDU_SCH_RANKING_LIST_TYPE_MEIZHI)) {
					//表名该学校是新的
					FindSchInfoParams findSchInfoParams = new FindSchInfoParams();
					findSchInfoParams.setSchId(schRankingList.getSchId());
					findSchInfoParams.setDiplomaId(dioploma);
					SchInfo schInfo = eduInfoService.findSchInfo(findSchInfoParams);
					schFilter = new SchFilter();
					if(schInfo != null) {
						this.setBaseInfo(schFilter, schInfo, schRankingList.getRankIndex(), schRankingList.getProvinceName());
					} else {
						this.setBaseInfo(schFilter, schRankingList);
					}
					schFilter.setFemale_ratio(schRankingList.getFemaleRatio() / 100.0);
				}
				schFilter.setRank_index(i + 1);
				schFilterList.add(schFilter);
			}
			// 补充剩下的
			for (String schId : schIdSet) {
				SchFilter schFilter = schFilterMap.get(schId);
				SchFilter resultSchFilter = createSchFilter(schFilter);
				i++;
				resultSchFilter.setRank_index(i);
				schFilterList.add(resultSchFilter);
			}
			redisTemplate.opsForHash().put(Constants.REDIS_SCH_FILTER_KEY, redisHashKey, schFilterList);
			logger.debug("Update " + redisHashKey + " filter list success...");
		}
	}
	
	/**
	 * 缓存本科综合学校过滤
	 * @param schInfoMap
	 * @param schStatInfoMap
	 * @param provinceInfoList
	 * @throws TException
	 */
	private List<SchFilter> cacheZKSchFilterBySalary(Map<String, SchInfo> schInfoMap, Map<String, SchStatInfo> schStatInfoMap, List<ProvinceInfo> provinceInfoList) throws TException {
		// 查询就业综合排名
		PageSchRankingListParams pageSchRankingListParams = new PageSchRankingListParams();
		pageSchRankingListParams.setRankType(EDU_SCH_RANKING_LIST_TYPE.EDU_SCH_RANKING_LIST_TYPE_XINCHOU);
		pageSchRankingListParams.setDiplomaId(EDU_DIPLOMA.EDU_DIPLOMA_ZK);
		pageSchRankingListParams.setCurPage(-1);
		pageSchRankingListParams.setPageSize(-1);
		SchRankingListPage schRankingListPage = eduStatService.pageSchRankingList(pageSchRankingListParams);
		if (schRankingListPage != null && schRankingListPage.getSchRankingLists() != null
				&& schRankingListPage.getSchRankingLists().size() > 0) {
			Set<String> schIdSet = new HashSet<String>(schInfoMap.keySet());
			List<SchFilter> schFilterList = new ArrayList<SchFilter>();
			int i = 0;
			for (; i < schRankingListPage.getSchRankingLists().size(); i++) {
				SchRankingList schRankingList = schRankingListPage.getSchRankingLists().get(i);
				schIdSet.remove(schRankingList.getSchId());
				SchFilter schFilter = new SchFilter();
				SchInfo schInfo = schInfoMap.get(schRankingList.getSchId());
				if (schInfo != null) {
					setBaseInfo(schFilter, schInfo, schRankingList.getRankIndex(), LocationUtils.getProvinceName(provinceInfoList, schInfo.getProvinceId()));
				} else {
					// 没有该学校baseinfo信息，继续查询该学校的schInfo信息
					FindSchInfoParams findSchInfoParams = new FindSchInfoParams();
					findSchInfoParams.setSchId(schRankingList.getSchId());
					findSchInfoParams.setDiplomaId(EDU_DIPLOMA.EDU_DIPLOMA_ZK);
					schInfo = eduInfoService.findSchInfo(findSchInfoParams);
					if (schInfo != null) {
						setBaseInfo(schFilter, schInfo, schRankingList.getRankIndex(), LocationUtils.getProvinceName(provinceInfoList, schInfo.getProvinceId()));
					} else {
						setBaseInfo(schFilter, schRankingList);
					}
				}
				schFilter.setSalary_factor((int)schRankingList.getSalaryFactor());
				SchStatInfo schStatInfo = schStatInfoMap.get(schRankingList.getSchId());
				if (schStatInfo != null && schStatInfo.getRankInfo() != null) {
					schFilter.setSalary_factor((int) schStatInfo.getRankInfo().getSalaryFactor());
				}
				if (schStatInfo != null) {
					List<GenderDist> genderDistList = EduStatUtils.convertToGenderDistList(schStatInfo.getCurrentGenderInfo(), schStatInfo.getGenderInfo(), schStatInfo.getSampleCount());
					if(genderDistList != null && genderDistList.size() == 2) {
						schFilter.setFemale_ratio(genderDistList.get(1).getRatio());
					}
				}
				schFilterList.add(schFilter);
			}
			// 补充剩下的
			for(String schId : schIdSet) {
				SchInfo schInfo = schInfoMap.get(schId);
				if (schInfo == null) {
					continue;
				}
				
				String provinceName = LocationUtils.getProvinceName(provinceInfoList, schInfo.getProvinceId());
				SchFilter schFilter = createSchFilter(schInfo, i, provinceName);
				i++;
				schFilter.setRank_index(i);
//				SchStatInfo schStatInfo = schStatInfoMap.get(schFilter.getSch_id());
//				if (schStatInfo != null && schStatInfo.getRankInfo() != null) {
//					schFilter.setSalary_factor((int) schStatInfo.getRankInfo().getSalaryFactor());
//				}
//				if (schStatInfo != null) {
//					List<GenderDist> genderDistList = EduStatUtils.convertToGenderDistList(schStatInfo.getCurrentGenderInfo(), schStatInfo.getGenderInfo(), schStatInfo.getSampleCount());
//					if(genderDistList != null && genderDistList.size() == 2) {
//						schFilter.setFemale_ratio(genderDistList.get(1).getRatio());
//					}
//				}
				schFilterList.add(schFilter);
			}
			redisTemplate.opsForHash().put(Constants.REDIS_SCH_FILTER_KEY, Constants.REDIS_SCH_FILTER_ZK_XINCHOU_HASH_KEY, schFilterList);
			logger.debug("Update zk xinchou filter list success...");
			return schFilterList;
		}
		return null;
	}

}
