package com.ipin.service.rest.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;

import com.ipin.service.rest.beans.MajorDetailResult;
import com.ipin.service.rest.beans.MajorFilter;
import com.ipin.service.rest.beans.MajorFilterListResult;
import com.ipin.service.rest.beans.MajorFilterParamsResult;
import com.ipin.service.rest.beans.MajorName;
import com.ipin.service.rest.beans.MajorNameListResult;
import com.ipin.service.rest.beans.MajorSecondCategoryNameListResult;
import com.ipin.service.rest.beans.RecommendSch;
import com.ipin.service.rest.beans.RecommendSchListResult;
import com.ipin.service.rest.beans.SortRecommendSch;
import com.ipin.service.rest.beans.impl.MajorResult;
import com.ipin.service.rest.constants.Constants;
import com.ipin.service.rest.service.MajorService;
import com.ipin.service.rest.utils.EduStatUtils;
import com.ipin.service.rest.utils.LoggerUtils;
import com.ipin.service.rest.utils.ParameterUtils;
import com.ipin.thrift.edu.EduInfoService;
import com.ipin.thrift.edu.EduStatService;
import com.ipin.thrift.edu.MajorIdBaseInfo;
import com.ipin.thrift.edu.MajorInfo;
import com.ipin.thrift.edu.MajorInfoDistinctAttribute;
import com.ipin.thrift.edu.MajorStatInfo;
import com.ipin.thrift.edu.SchInfo;
import com.ipin.thrift.edu.SchMajorAreaSalaryStatInfo;
import com.ipin.thrift.edu.SchMajorStatInfo;
import com.ipin.thrift.edu.SchStatInfo;
import com.ipin.thrift.edu.commons.EDU_DIPLOMA;
import com.ipin.thrift.edu.commons.EDU_MAJOR_CATE_LEVEL;
import com.ipin.thrift.edu.params.FindMajorIdBaseInfoParams;
import com.ipin.thrift.edu.params.FindMajorInfoDistinctAttributeParams;
import com.ipin.thrift.edu.params.FindMajorInfoParams;
import com.ipin.thrift.edu.params.FindMajorStatInfoParams;
import com.ipin.thrift.edu.params.FindSchMajorAreaSalaryStatInfoParams;
import com.ipin.thrift.edu.params.ListSchInfoParams;
import com.ipin.thrift.edu.params.ListSchMajorStatInfoParams;
import com.ipin.thrift.edu.params.ListSchStatInfoParams;

/**
 * MajorServiceImpl.
 * 专业服务.
 * 
 * @author zhongyongsheng
 *
 */
public class MajorServiceImpl implements MajorService{
	
	private static final Logger logger = LoggerFactory.getLogger(MajorServiceImpl.class);
	
	@Resource
	private EduStatService.Iface eduStatService;
	@Resource
	private EduInfoService.Iface eduInfoService;
	
	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate<String, MajorResult> redisTemplate;

	@Override
	public MajorDetailResult getMajorDetailResult(String majorId, int diplomaId) {
		try {
			// 查询专业信息
			FindMajorInfoParams findMajorInfoParams = new FindMajorInfoParams();
			findMajorInfoParams.setMajorId(majorId);
			MajorInfo majorInfo = eduInfoService.findMajorInfo(findMajorInfoParams);
			if (majorInfo == null) {
				return null;
			}
			
			// 查询专业统计信息
			FindMajorStatInfoParams findMajorStatInfoParams = new FindMajorStatInfoParams();
			findMajorStatInfoParams.setMajorId(majorId);
			findMajorStatInfoParams.setDiplomaId(EDU_DIPLOMA.findByValue(diplomaId));
			MajorStatInfo majorStatInfo = eduStatService.findMajorStatInfo(findMajorStatInfoParams);
			
			// 获取全国学校专业地区薪酬列表, 旧python代码是获取其专业二级类的全国薪酬列表
			FindSchMajorAreaSalaryStatInfoParams findSchMajorAreaSalaryStatInfoParams = new FindSchMajorAreaSalaryStatInfoParams();
			findSchMajorAreaSalaryStatInfoParams.setAreaId("-1");
			findSchMajorAreaSalaryStatInfoParams.setMajorId(majorId);
			findSchMajorAreaSalaryStatInfoParams.setDiplomaId(EDU_DIPLOMA.findByValue(diplomaId));
			SchMajorAreaSalaryStatInfo countrySalaryStatInfo = eduStatService.findSchMajorAreaSalaryStatInfo(findSchMajorAreaSalaryStatInfoParams);
		
			return convertToMajorDetailResult(majorInfo, majorStatInfo, countrySalaryStatInfo);
		} catch (TException e) {
			LoggerUtils.logEduTExceptionAndThrowAppException(logger, e);
		}
		return null;
	}
	
	private MajorDetailResult convertToMajorDetailResult(MajorInfo majorInfo, MajorStatInfo majorStatInfo, SchMajorAreaSalaryStatInfo countrySalaryStatInfo) {
		MajorDetailResult majorDetailResult = new MajorDetailResult();
		majorDetailResult.setMajor_id(majorInfo.getMajorId());
		majorDetailResult.setMajor_name(majorInfo.getMajorName());
		majorDetailResult.setMajor_category(majorInfo.getMajorCategory());
		majorDetailResult.setMajor_second_category(majorInfo.getMajorSecondCategory());
		majorDetailResult.setMajor_intro(majorInfo.getMajorIntro());
		if(majorStatInfo != null) {
			majorDetailResult.setSample_count(majorStatInfo.getSampleCount());
			majorDetailResult.setIndustry_dis(EduStatUtils.convertToIndustryDistList(majorStatInfo.getIndustryDistInfoList()));
			if (majorStatInfo.getMajorRankInfo() != null) {
				majorDetailResult.setIndustry_status(EduStatUtils.getMajorIndustryDistDesc(majorStatInfo.getMajorRankInfo().getIndustryGiniRank()));
				if(majorStatInfo.getMajorRankInfo().getSampleCount() > 30 ) {
					majorDetailResult.setSalary_factor((int) ParameterUtils.roundDouble(majorStatInfo.getMajorRankInfo().getSalaryFactor(), 0));
					majorDetailResult.setSalary_factor_rank(EduStatUtils.getSalaryFactorRank(majorStatInfo.getMajorRankInfo().getSalaryRank()));
				} else {
					majorDetailResult.setSalary_factor(-1);
					majorDetailResult.setSalary_factor_rank(-1);
				}
			}
			majorDetailResult.setLocation_dis(EduStatUtils.convertToLocationDistList(majorStatInfo.getCurrentLocationInfoList(), "00000000"));
			if (majorDetailResult.getLocation_dis().size() > 0) {
				majorDetailResult.setTop_worker_city(majorDetailResult.getLocation_dis().get(0).getCity_id());
				majorDetailResult.setTop_worker_city_ratio(majorDetailResult.getLocation_dis().get(0).getRatio());
			}
			majorDetailResult.setGender_dis(EduStatUtils.convertToGenderDistList(null, majorStatInfo.getGenderInfo(), majorStatInfo.getSampleCount()));
			majorDetailResult.setGender_status(EduStatUtils.getGenderDistDesc(majorDetailResult.getGender_dis()));
			majorDetailResult.setSalary_stats(EduStatUtils.convertToSalaryStatList(majorStatInfo.getSalaryDetailInfoList(), majorStatInfo.getSalaryPredictWeightList()));
			majorDetailResult.setPredict_salary_stats(EduStatUtils.convertToPredictSalaryStat(majorStatInfo.getPredictSalaryInfoList(), majorStatInfo.getSalaryPredictWeightList()));
			majorDetailResult.setSalary_predict_show_type(majorStatInfo.getSalaryPredictShowType());
			majorDetailResult.setZhineng_dis(EduStatUtils.convertToJobFunctionDist(majorStatInfo.getJobFunctionInfoList()));
		}
		majorDetailResult.setCountry_salary_stats(EduStatUtils.convertToSalaryStatList(countrySalaryStatInfo.getSalaryInfoList(), null));
		majorDetailResult.setIn_country_salary_index(-1);// 不进行计算
		// 差个公司分布
		return majorDetailResult;
	}

	@Override
	public RecommendSchListResult getRecommendSchoolListResult(String majorId, int diplomaId) {
		try {
			// 查询专业idbase
			FindMajorIdBaseInfoParams findMajorIdBaseInfoParams = new FindMajorIdBaseInfoParams();
			findMajorIdBaseInfoParams.setMajorId(majorId);
			findMajorIdBaseInfoParams.setDiploma(EDU_DIPLOMA.findByValue(diplomaId));
			MajorIdBaseInfo majorIdBaseInfo = eduInfoService.findMajorIdBaseInfo(findMajorIdBaseInfoParams);
			if (majorIdBaseInfo == null) {
				return null;
			}
			List<String> schIdList = majorIdBaseInfo.getSchIdList();
			
			// 查询学校专业统计信息
			ListSchMajorStatInfoParams listSchMajorStatInfoParams = new ListSchMajorStatInfoParams();
			listSchMajorStatInfoParams.setDiplomaId(EDU_DIPLOMA.findByValue(diplomaId));
			listSchMajorStatInfoParams.setMajorId(majorId);
			listSchMajorStatInfoParams.setSchIdList(schIdList);
			listSchMajorStatInfoParams.setSampleCount(-1);
			List<SchMajorStatInfo> schMajorStatInfoList = eduStatService.listSchMajorStatInfo(listSchMajorStatInfoParams);
			
			// 排序学校顺序
			List<SortRecommendSch> sortRecommendSchoolList = new ArrayList<SortRecommendSch>();
			List<RecommendSch> recommendSchoolList = new ArrayList<RecommendSch>();
			if(schMajorStatInfoList != null && schMajorStatInfoList.size() > 0) {
				List<String> tempSchIdList = new ArrayList<String>();
				Map<String, SchMajorStatInfo> schMajorStatInfoMap = new HashMap<String, SchMajorStatInfo>();
				for(SchMajorStatInfo schMajorStatInfo : schMajorStatInfoList) {
					if (schMajorStatInfo.getMajorId().equals("52aedf5b747aec1cfc841728")
							&& schMajorStatInfo.getSchId().equals("52ac2e9a747aec013fcf51e2")) {
						SortRecommendSch sortRecommendSchool = new SortRecommendSch();
						sortRecommendSchool.setSchId(schMajorStatInfo.getSchId());
						sortRecommendSchool.setSalaryFactor(1000000);
						sortRecommendSchool.setTotalRank(10000000);
						sortRecommendSchoolList.add(sortRecommendSchool);
						continue;
					}
					// 样本数不足的薪酬设置为-1
					double salaryFactor = -1;
					if (schMajorStatInfo.getRankInfo() != null
							&& schMajorStatInfo.getRankInfo().getSalaryFactor() > 0
							&& schMajorStatInfo.getRankInfo().getSalaryFactorSampleCount() > 10) {
						salaryFactor = schMajorStatInfo.getRankInfo().getSalaryFactor();
					}
					if(schMajorStatInfo.getRankInfo() != null) {
						schMajorStatInfo.getRankInfo().setSalaryFactor(salaryFactor);
					}
					
					tempSchIdList.add(schMajorStatInfo.getSchId());
					schMajorStatInfoMap.put(schMajorStatInfo.getSchId(), schMajorStatInfo);
				}
				
				// 查询学校统计信息
				ListSchStatInfoParams listSchStatInfoParams = new ListSchStatInfoParams();
				listSchStatInfoParams.setSchIdList(tempSchIdList);
				listSchStatInfoParams.setDiplomaId(EDU_DIPLOMA.findByValue(diplomaId));
				List<SchStatInfo> schStatInfoList = eduStatService.listSchStatInfo(listSchStatInfoParams);
				Map<String, SchStatInfo> schStatMap = new HashMap<String, SchStatInfo>();
				if (schStatInfoList != null) {
					for (SchStatInfo schStatInfo : schStatInfoList) {
						SchMajorStatInfo schMajorStatInfo = schMajorStatInfoMap.get(schStatInfo.getSchId());
						if (schMajorStatInfo == null) {
							continue;
						}
						SortRecommendSch sortRecommendSchool = new SortRecommendSch();
						sortRecommendSchool.setSchId(schStatInfo.getSchId());
						sortRecommendSchool.setSalaryFactor(schMajorStatInfo.getRankInfo().getSalaryFactor());
						sortRecommendSchool.setTotalRank(schStatInfo.getRankInfo().getTotalRank());
						sortRecommendSchoolList.add(sortRecommendSchool);
						schStatMap.put(schStatInfo.getSchId(), schStatInfo);
					}
				}
				
				// 排序学校
				Collections.sort(sortRecommendSchoolList);
				
				// 取前6个
				int size = sortRecommendSchoolList.size() >= 6 ? 6 : sortRecommendSchoolList.size();
				List<String> topRecommendSchIdList = new ArrayList<String>();
				for(int i = 0; i < size; i++) {
					topRecommendSchIdList.add(sortRecommendSchoolList.get(i).getSchId());
				}
				ListSchInfoParams listSchInfoParams = new ListSchInfoParams();
				listSchInfoParams.setSchIdList(topRecommendSchIdList);
				List<EDU_DIPLOMA> diplomaIdList = new ArrayList<EDU_DIPLOMA>();
				diplomaIdList.add(EDU_DIPLOMA.findByValue(diplomaId));
				listSchInfoParams.setDiplomaIdList(diplomaIdList);
				listSchInfoParams.setSimpleData(true);
				List<SchInfo> schInfoList = eduInfoService.listSchInfo(listSchInfoParams);
				Map<String, SchInfo> schInfoMap = new HashMap<String, SchInfo>();
				if (schInfoList != null) {
					for (SchInfo schInfo : schInfoList) {
						schInfoMap.put(schInfo.getSchId(), schInfo);
					}
				}
				
				// 拼接结果
				for(int i = 0; i < topRecommendSchIdList.size(); i++) {
					String schId = topRecommendSchIdList.get(i);
					SchInfo schInfo = schInfoMap.get(schId);
					if (schInfo == null) {
						continue;
					}
					RecommendSch recommendSchool = new RecommendSch();
					recommendSchool.setRank_index(i + 1);
					recommendSchool.setSch_id(schInfo.getSchId());
					recommendSchool.setSch_name(schInfo.getSchName());
					recommendSchool.setCity(schInfo.getCityId());
					if (schInfo.getSchTypeNameList() != null && schInfo.getSchTypeNameList().size() > 0) {
						recommendSchool.setSchool_type(schInfo.getSchTypeNameList().get(0));
					}
					SchStatInfo schStatInfo = schStatMap.get(schInfo.getSchId());
					recommendSchool.setRank_str(EduStatUtils.getTotalGradeDesc(schStatInfo.getRankInfo()));
					recommendSchoolList.add(recommendSchool);
				}
			}
			RecommendSchListResult recommendSchoolListResult = new RecommendSchListResult();
			recommendSchoolListResult.setSchools(recommendSchoolList);
			return recommendSchoolListResult;

		} catch (TException e) {
			LoggerUtils.logEduTExceptionAndThrowAppException(logger, e);
		}
		return null;
	}

	@Override
	public MajorFilterListResult getMajorFilterListResult(Integer diplomaId, String majorCategory, String sortBy, Integer count,
			Integer pageNo) {
		// 获取学校筛选排名
		String hashKey = null;
		if (EDU_DIPLOMA.EDU_DIPLOMA_BK.getValue() == diplomaId) {
			if (StringUtils.isBlank(sortBy)) {
				hashKey = Constants.REDIS_MAJOR_FILTER_BK_HASH_KEY;
			} else if (Constants.MAJOR_FILTER_LIST_TYPE_XINCHOU.equals(sortBy)) {
				hashKey = Constants.REDIS_MAJOR_FILTER_BK_XINCHOU_HASH_KEY;
			} else if (Constants.MAJOR_FILTER_LIST_TYPE_MEIZHI.equals(sortBy)) {
				hashKey = Constants.REDIS_MAJOR_FILTER_BK_MEIZHI_HASH_KEY;
			} else if (Constants.MAJOR_FILTER_LIST_TYPE_NANZHI.equals(sortBy)) {
				hashKey = Constants.REDIS_MAJOR_FILTER_BK_NANZI_HASH_KEY;
			}
		} else if (EDU_DIPLOMA.EDU_DIPLOMA_ZK.getValue() == diplomaId) {
			if (StringUtils.isBlank(sortBy)) {
				hashKey = Constants.REDIS_MAJOR_FILTER_ZK_HASH_KEY;
			} else if (Constants.MAJOR_FILTER_LIST_TYPE_XINCHOU.equals(sortBy)) {
				hashKey = Constants.REDIS_MAJOR_FILTER_ZK_XINCHOU_HASH_KEY;
			} else if (Constants.MAJOR_FILTER_LIST_TYPE_MEIZHI.equals(sortBy)) {
				hashKey = Constants.REDIS_MAJOR_FILTER_ZK_MEIZHI_HASH_KEY;
			} else if (Constants.MAJOR_FILTER_LIST_TYPE_NANZHI.equals(sortBy)) {
				hashKey = Constants.REDIS_MAJOR_FILTER_ZK_NANZI_HASH_KEY;
			}
		}
		if (StringUtils.isBlank(hashKey)) {
			return null;
		}
		boolean hasKey = redisTemplate.opsForHash().hasKey(Constants.REDIS_MAJOR_FILTER_KEY, hashKey);
		if (!hasKey) {
			return null;
		} 
		List<MajorFilter> majorFilterList = (List<MajorFilter>) redisTemplate.opsForHash().get(Constants.REDIS_MAJOR_FILTER_KEY, hashKey);
		if (majorFilterList == null) {
			return null;
		}
		
		// 构造分页结果
		List<MajorFilter> resultMajorFilterList = new ArrayList<MajorFilter>();
		for (MajorFilter majorFilter : majorFilterList) {
			if (StringUtils.isNotBlank(majorCategory) && !majorCategory.equals(majorFilter.getMajor_catetory())) {
				continue;
			}
			resultMajorFilterList.add(majorFilter);
		}
		// 分页显示
		if (count == null || count <= 0) {
			count = Constants.MAJOR_FILTER_LIST_PAGE_SIZE;
		}
		if (pageNo == null || pageNo <= 0) {
			pageNo = 1;
		}
		int totalPage = (int) Math.ceil(resultMajorFilterList.size() * 1.0 / count);
		int totalCount = resultMajorFilterList.size();
		int startIndex = (pageNo - 1) * count;
		if (startIndex > resultMajorFilterList.size() - 1) {
			resultMajorFilterList = new ArrayList<MajorFilter>();
		} else {
			int endIndex = (startIndex + count) > resultMajorFilterList.size() ? resultMajorFilterList.size()
					: startIndex + count;
			resultMajorFilterList = resultMajorFilterList.subList(startIndex, endIndex);
			List<MajorFilter> tempMajorFilterList = new ArrayList<MajorFilter>(resultMajorFilterList);
			resultMajorFilterList = tempMajorFilterList;
		}
		
		if(resultMajorFilterList != null) {
			for(int i = 0; i < resultMajorFilterList.size(); i++) {
				resultMajorFilterList.get(i).setRank_index(i + 1);
			}
		}
		
		MajorFilterListResult majorFilterListResult = new MajorFilterListResult();
		majorFilterListResult.setMajors(resultMajorFilterList);
		majorFilterListResult.setTotal_page(totalPage);
		majorFilterListResult.setTotal_count(totalCount);
		return majorFilterListResult;
	}

	@Override
	public MajorFilterParamsResult getMajorFilterParamsResult(int diplomaId, int categoryLevel) {
		try {
			// 查询专业信息属性
			FindMajorInfoDistinctAttributeParams findMajorInfoDistinctAttributeParams = new FindMajorInfoDistinctAttributeParams();
			findMajorInfoDistinctAttributeParams.setDiplomaId(EDU_DIPLOMA.findByValue(diplomaId));
			findMajorInfoDistinctAttributeParams.setMajorCateLevel(EDU_MAJOR_CATE_LEVEL.findByValue(categoryLevel));
			MajorInfoDistinctAttribute majorInfoDistinctAttribute = eduInfoService.findMajorInfoDistinctAttribute(findMajorInfoDistinctAttributeParams);
			if(majorInfoDistinctAttribute == null) {
				return null;
			}
			
			// 拼装结果
			MajorFilterParamsResult majorFilterParamsResult = new MajorFilterParamsResult();
			majorFilterParamsResult.setCategorys(majorInfoDistinctAttribute.getMajorCateNameList());
			return majorFilterParamsResult;
		} catch (TException e) {
			LoggerUtils.logEduTExceptionAndThrowAppException(logger, e);
		}
		return null;
	}

	@Override
	public MajorSecondCategoryNameListResult getMajorSecondCategoryNameListResult(String majorCategoryName,
			int diplomaId) {
		String hashKey = null;
		if (EDU_DIPLOMA.EDU_DIPLOMA_BK.getValue() == diplomaId) {
			hashKey = Constants.REDIS_MAJOR_CATEGORY_BK_MAPPING_HASH_KEY;
		} else if (EDU_DIPLOMA.EDU_DIPLOMA_ZK.getValue() == diplomaId) {
			hashKey = Constants.REDIS_MAJOR_CATEGORY_ZK_MAPPING_HASH_KEY;
		}
		if (hashKey == null) {
			return null;
		}
		boolean hasKey = redisTemplate.opsForHash().hasKey(Constants.REDIS_MAJOR_CATEGORY_MAPPING_KEY, hashKey);
		if (!hasKey) {
			return null;
		}
		Map<String, List<String>> majorCategoryMappingMajorSecondCategoryNameList = (Map<String, List<String>>) redisTemplate.opsForHash().get(Constants.REDIS_MAJOR_CATEGORY_MAPPING_KEY, hashKey);
		if (majorCategoryMappingMajorSecondCategoryNameList == null) {
			return null;
		}
		MajorSecondCategoryNameListResult majorSecondCategoryNameListResult = new MajorSecondCategoryNameListResult();
		majorSecondCategoryNameListResult.setMajor_second_categorys(majorCategoryMappingMajorSecondCategoryNameList.get(majorCategoryName));
		return majorSecondCategoryNameListResult;
	}

	@Override
	public MajorNameListResult getMajorNameListResult(String majorSecondCategoryName, int diplomaId) {
		String hashKey = null;
		if (EDU_DIPLOMA.EDU_DIPLOMA_BK.getValue() == diplomaId) {
			hashKey = Constants.REDIS_MAJOR_SECOND_CATEGORY_BK_MAPPING_HASH_KEY;
		} else if (EDU_DIPLOMA.EDU_DIPLOMA_ZK.getValue() == diplomaId) {
			hashKey = Constants.REDIS_MAJOR_SECOND_CATEGORY_ZK_MAPPING_HASH_KEY;
		}
		if (hashKey == null) {
			return null;
		}
		boolean hasKey = redisTemplate.opsForHash().hasKey(Constants.REDIS_MAJOR_CATEGORY_MAPPING_KEY, hashKey);
		if (!hasKey) {
			return null;
		}
		Map<String, List<MajorName>> majorSecondCategoryMappingMajorNameList = (Map<String, List<MajorName>>) redisTemplate.opsForHash().get(Constants.REDIS_MAJOR_CATEGORY_MAPPING_KEY, hashKey);
		if (majorSecondCategoryMappingMajorNameList == null) {
			return null;
		}
		MajorNameListResult majorNameListResult = new MajorNameListResult();
		majorNameListResult.setMajors(majorSecondCategoryMappingMajorNameList.get(majorSecondCategoryName));
		return majorNameListResult;
	}

}
