package com.ipin.service.rest.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;

import com.ipin.service.rest.beans.MajorSecondCategoryDetailResult;
import com.ipin.service.rest.beans.RecommendSch;
import com.ipin.service.rest.beans.RecommendSchListResult;
import com.ipin.service.rest.beans.SimpleMajorInfo;
import com.ipin.service.rest.beans.SortRecommendSch;
import com.ipin.service.rest.beans.impl.MajorResult;
import com.ipin.service.rest.service.MajorSecondCategoryService;
import com.ipin.service.rest.utils.EduStatUtils;
import com.ipin.service.rest.utils.LoggerUtils;
import com.ipin.service.rest.utils.ParameterUtils;
import com.ipin.thrift.edu.EduInfoService;
import com.ipin.thrift.edu.EduStatService;
import com.ipin.thrift.edu.MajorInfo;
import com.ipin.thrift.edu.MajorSecondCateIdBaseInfo;
import com.ipin.thrift.edu.MajorSecondCateStatInfo;
import com.ipin.thrift.edu.SchInfo;
import com.ipin.thrift.edu.SchMajorSecondCateAreaSalaryStatInfo;
import com.ipin.thrift.edu.SchMajorSecondCateStatInfo;
import com.ipin.thrift.edu.SchStatInfo;
import com.ipin.thrift.edu.commons.EDU_DIPLOMA;
import com.ipin.thrift.edu.params.FindMajorSecondCateIdBaseInfoParams;
import com.ipin.thrift.edu.params.FindMajorSecondCateInfoParams;
import com.ipin.thrift.edu.params.FindSchMajorSecondCateAreaSalaryStatInfoParams;
import com.ipin.thrift.edu.params.ListMajorInfoByIdsParams;
import com.ipin.thrift.edu.params.ListMajorInfoParams;
import com.ipin.thrift.edu.params.ListSchInfoParams;
import com.ipin.thrift.edu.params.ListSchMajorSecondCateStatInfoParams;
import com.ipin.thrift.edu.params.ListSchStatInfoParams;

/**
 * MajorSecondCategoryServiceImpl.
 * 专业二级目录服务实现类.
 * 
 * @author zhongyongsheng
 *
 */
public class MajorSecondCategoryServiceImpl implements MajorSecondCategoryService{
	
	private static final Logger logger = LoggerFactory.getLogger(MajorSecondCategoryServiceImpl.class);
	
	@Resource
	private EduInfoService.Iface eduInfoService;
	@Resource
	private EduStatService.Iface eduStatService;
	
	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate<String, MajorResult> redisTemplate;

	@Override
	public MajorSecondCategoryDetailResult getMajorSecondCategoryDetailResult(String majorSecondCategoryName,
			int diplomaId) {
		try {
			// 查询专业二级统计信息
			FindMajorSecondCateInfoParams findMajorSecondCateInfoParams = new FindMajorSecondCateInfoParams();
			findMajorSecondCateInfoParams.setMajorSecondCate(majorSecondCategoryName);
			findMajorSecondCateInfoParams.setDiplomaId(EDU_DIPLOMA.findByValue(diplomaId));
			MajorSecondCateStatInfo majorSecondCateStatInfo = eduStatService.findMajorSecondCateStatInfo(findMajorSecondCateInfoParams);
			if (majorSecondCateStatInfo == null) {
				return null;
			}
			
			// 查询专业信息
			ListMajorInfoParams listMajorInfoParams = new ListMajorInfoParams();
			listMajorInfoParams.setDiplomaId(EDU_DIPLOMA.findByValue(diplomaId));
			listMajorInfoParams.setMajorSecondCategory(majorSecondCategoryName);
			listMajorInfoParams.setSimpleData(true);
			MajorInfo majorInfo = eduInfoService.findFirstMajorInfo(listMajorInfoParams);
			if (majorInfo == null) {
				return null;
			}
			
			// 查询全国学校专业二级目录地区薪酬
			FindSchMajorSecondCateAreaSalaryStatInfoParams findSchMajorSecondCateAreaSalaryStatInfoParams = new FindSchMajorSecondCateAreaSalaryStatInfoParams();
			findSchMajorSecondCateAreaSalaryStatInfoParams.setMajorSecondCate(majorSecondCategoryName);
			findSchMajorSecondCateAreaSalaryStatInfoParams.setDiplomaId(EDU_DIPLOMA.findByValue(diplomaId));
			findSchMajorSecondCateAreaSalaryStatInfoParams.setAreaId("-1");
			SchMajorSecondCateAreaSalaryStatInfo schMajorSecondCateAreaSalaryStatInfo = eduStatService.findSchMajorSecondCateAreaSalaryStatInfo(findSchMajorSecondCateAreaSalaryStatInfoParams);
		
			return convertToMajorSecondCategoryDetailResult(majorInfo.getMajorCategory(), majorSecondCateStatInfo, schMajorSecondCateAreaSalaryStatInfo, diplomaId);
		} catch (TException e) {
			LoggerUtils.logEduTExceptionAndThrowAppException(logger, e);
		}
		return null;
	}
	
	/**
	 * 转换为专业二级目录详情结果.
	 * @param majorCateName 专业一级目录名字
	 * @param majorSecondCateStatInfo 专业二级目录统计信息
	 * @param schMajorSecondCateAreaSalaryStatInfo
	 * @return
	 */
	private MajorSecondCategoryDetailResult convertToMajorSecondCategoryDetailResult(String majorCateName,
			MajorSecondCateStatInfo majorSecondCateStatInfo,
			SchMajorSecondCateAreaSalaryStatInfo schMajorSecondCateAreaSalaryStatInfo, int diplomaId) throws TException{
		MajorSecondCategoryDetailResult majorSecondCategoryDetailResult = new MajorSecondCategoryDetailResult();
		majorSecondCategoryDetailResult.setMajor_second_category(majorSecondCateStatInfo.getMajorSecondCate());
		majorSecondCategoryDetailResult.setMajor_category(majorCateName);
		List<String> majorIdList = majorSecondCateStatInfo.getMajorIdList();
		if(majorIdList != null && majorIdList.size() > 0) {
			ListMajorInfoByIdsParams listMajorInfoByIdsParams = new ListMajorInfoByIdsParams();
			listMajorInfoByIdsParams.setMajorIdList(majorIdList);
			listMajorInfoByIdsParams.setSimpleData(true);
			List<MajorInfo>  majorInfoList = eduInfoService.listMajorInfoByIds(listMajorInfoByIdsParams);
			if (majorInfoList != null) {
				List<SimpleMajorInfo> simpleMajorInfoList = new ArrayList<SimpleMajorInfo>();
				for (MajorInfo majorInfo : majorInfoList) {
					SimpleMajorInfo simpleMajorInfo = new SimpleMajorInfo();
					simpleMajorInfo.setMajor_id(majorInfo.getMajorId());
					simpleMajorInfo.setMajor_name(majorInfo.getMajorName());
					simpleMajorInfoList.add(simpleMajorInfo);
				}
				majorSecondCategoryDetailResult.setMajor_list(simpleMajorInfoList);
			}
		}
		majorSecondCategoryDetailResult.setSample_count(majorSecondCateStatInfo.getSampleCount());
		majorSecondCategoryDetailResult.setIndustry_dis(EduStatUtils.convertToIndustryDistList(majorSecondCateStatInfo.getIndustryDistInfoList()));
		if (majorSecondCateStatInfo.getMajorRankInfo() != null) {
			majorSecondCategoryDetailResult.setIndustry_status(EduStatUtils.getMajorIndustryDistDesc(majorSecondCateStatInfo.getMajorRankInfo().getIndustryGiniRank()));
		}
		majorSecondCategoryDetailResult.setLocation_dis(EduStatUtils.convertToLocationDistList(majorSecondCateStatInfo.getCurrentLocationInfoList(), "00000000"));
		if (majorSecondCategoryDetailResult.getLocation_dis().size() > 0) {
			majorSecondCategoryDetailResult.setTop_worker_city(majorSecondCategoryDetailResult.getLocation_dis().get(0).getCity_id());
			majorSecondCategoryDetailResult.setTop_worker_city_ratio(majorSecondCategoryDetailResult.getLocation_dis().get(0).getRatio());
		}
		majorSecondCategoryDetailResult.setGender_dis(EduStatUtils.convertToGenderDistList(null, majorSecondCateStatInfo.getGenderInfo(), majorSecondCateStatInfo.getSampleCount()));
		majorSecondCategoryDetailResult.setGender_status(EduStatUtils.getGenderDistDesc(majorSecondCategoryDetailResult.getGender_dis()));
		if(majorSecondCateStatInfo.getMajorRankInfo() != null) {
			majorSecondCategoryDetailResult.setSalary_factor((int) ParameterUtils.roundDouble(majorSecondCateStatInfo.getMajorRankInfo().getSalaryFactor(), 0));
			majorSecondCategoryDetailResult.setSalary_factor_rank(EduStatUtils.getSalaryFactorRank(majorSecondCateStatInfo.getMajorRankInfo().getSalaryRank()));
		}
		majorSecondCategoryDetailResult.setSalary_stats(EduStatUtils.convertToSalaryStatList(majorSecondCateStatInfo.getSalaryDetailInfoList(), majorSecondCateStatInfo.getSalaryPredictWeightList()));
		majorSecondCategoryDetailResult.setPredict_salary_stats(EduStatUtils.convertToPredictSalaryStat(majorSecondCateStatInfo.getPredictSalaryInfoList(), majorSecondCateStatInfo.getSalaryPredictWeightList()));
		majorSecondCategoryDetailResult.setSalary_predict_show_type(majorSecondCateStatInfo.getSalaryPredictShowType());
		majorSecondCategoryDetailResult.setCountry_salary_stats(EduStatUtils.convertToSalaryStatList(schMajorSecondCateAreaSalaryStatInfo.getSalaryInfoList(), null));
		majorSecondCategoryDetailResult.setIn_country_salary_index(-1);
		majorSecondCategoryDetailResult.setZhineng_dis(EduStatUtils.convertToJobFunctionDist(majorSecondCateStatInfo.getJobFunctionInfoList()));
		// 缺失公司信息
		return  majorSecondCategoryDetailResult;
	}

	@Override
	public RecommendSchListResult getRecommendSchListResult(String majorSecondCategoryName, int diplomaId) {
		try {
			// 查询专业二级目录IdBase
			long startTime = System.currentTimeMillis();
			FindMajorSecondCateIdBaseInfoParams findMajorSecondCateIdBaseInfoParams = new FindMajorSecondCateIdBaseInfoParams();
			findMajorSecondCateIdBaseInfoParams.setMajorSecondCate(majorSecondCategoryName);
			findMajorSecondCateIdBaseInfoParams.setDiplomaId(EDU_DIPLOMA.findByValue(diplomaId));
			MajorSecondCateIdBaseInfo majorSecondCateIdBaseInfo = eduInfoService.findMajorSecondCateIdBaseInfo(findMajorSecondCateIdBaseInfoParams);
			if (majorSecondCateIdBaseInfo == null) {
				return null;
			}
			List<String> schIdList = majorSecondCateIdBaseInfo.getSchIdList();
			logger.debug("findMajorSecondCateIdBaseInfo耗时:" +  (System.currentTimeMillis() - startTime) +"ms.");
			startTime = System.currentTimeMillis();
			
			// 查询专业统计信息
			ListSchMajorSecondCateStatInfoParams listSchMajorSecondCateStatInfoParams = new ListSchMajorSecondCateStatInfoParams();
			listSchMajorSecondCateStatInfoParams.setMajorSecondCate(majorSecondCategoryName);
			listSchMajorSecondCateStatInfoParams.setSchIdList(schIdList);
			listSchMajorSecondCateStatInfoParams.setDiplomaId(EDU_DIPLOMA.findByValue(diplomaId));
			List<SchMajorSecondCateStatInfo> schMajorSecondCateStatInfoList = eduStatService.listSchMajorSecondCateStatInfo(listSchMajorSecondCateStatInfoParams);
			logger.debug("listSchMajorSecondCateStatInfo耗时:" +  (System.currentTimeMillis() - startTime) +"ms.");
			startTime = System.currentTimeMillis();
			
			// 排序学校顺序
			List<SortRecommendSch> sortRecommendSchoolList = new ArrayList<SortRecommendSch>();
			List<RecommendSch> recommendSchoolList = new ArrayList<RecommendSch>();
			if (schMajorSecondCateStatInfoList != null && schMajorSecondCateStatInfoList.size() > 0) {
				List<String> tempSchIdList = new ArrayList<String>();
				Map<String, SchMajorSecondCateStatInfo> schMajorSecondStatInfoMap = new HashMap<String, SchMajorSecondCateStatInfo>();
				for(SchMajorSecondCateStatInfo schMajorSecondCateStatInfo : schMajorSecondCateStatInfoList) {
					// 样本数不足的薪酬设置为-1
					double salaryFactor = -1;
					if (schMajorSecondCateStatInfo.getRankInfo() != null
							&& schMajorSecondCateStatInfo.getRankInfo().getSalaryFactor() > 0
							&& schMajorSecondCateStatInfo.getRankInfo().getSalaryFactorSampleCount() > 10) {
						salaryFactor = schMajorSecondCateStatInfo.getRankInfo().getSalaryFactor();
					}
					if(schMajorSecondCateStatInfo.getRankInfo() != null) {
						schMajorSecondCateStatInfo.getRankInfo().setSalaryFactor(salaryFactor);
					}
					
					tempSchIdList.add(schMajorSecondCateStatInfo.getSchId());
					schMajorSecondStatInfoMap.put(schMajorSecondCateStatInfo.getSchId(), schMajorSecondCateStatInfo);
				}
				
				// 查询学校统计信息
				ListSchStatInfoParams listSchStatInfoParams = new ListSchStatInfoParams();
				listSchStatInfoParams.setSchIdList(tempSchIdList);
				listSchStatInfoParams.setDiplomaId(EDU_DIPLOMA.findByValue(diplomaId));
				List<SchStatInfo> schStatInfoList = eduStatService.listSchStatInfo(listSchStatInfoParams);
				Map<String, SchStatInfo> schStatMap = new HashMap<String, SchStatInfo>();
				if (schStatInfoList != null) {
					for (SchStatInfo schStatInfo : schStatInfoList) {
						SchMajorSecondCateStatInfo schMajorSecondCateStatInfo = schMajorSecondStatInfoMap.get(schStatInfo.getSchId());
						if (schMajorSecondCateStatInfo == null) {
							continue;
						}
						SortRecommendSch sortRecommendSchool = new SortRecommendSch();
						sortRecommendSchool.setSchId(schStatInfo.getSchId());
						sortRecommendSchool.setSalaryFactor(schMajorSecondCateStatInfo.getRankInfo().getSalaryFactor());
						sortRecommendSchool.setTotalRank(schStatInfo.getRankInfo().getTotalRank());
						sortRecommendSchoolList.add(sortRecommendSchool);
						schStatMap.put(schStatInfo.getSchId(), schStatInfo);
					}
				}
				logger.debug("listSchStatInfo耗时:" +  (System.currentTimeMillis() - startTime) +"ms.");
				startTime = System.currentTimeMillis();
				
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
				logger.debug("listSchInfo耗时:" +  (System.currentTimeMillis() - startTime) +"ms.");
			}
			RecommendSchListResult recommendSchoolListResult = new RecommendSchListResult();
			recommendSchoolListResult.setSchools(recommendSchoolList);
			return recommendSchoolListResult;
		} catch (TException e) {
			LoggerUtils.logEduTExceptionAndThrowAppException(logger, e);
		}
		return null;
	}

}
