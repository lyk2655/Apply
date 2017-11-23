package com.ipin.service.rest.tasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

import com.ipin.service.rest.beans.MajorFilter;
import com.ipin.service.rest.constants.Constants;
import com.ipin.service.rest.utils.EduStatUtils;
import com.ipin.thrift.common.CommonService;
import com.ipin.thrift.edu.EduInfoService;
import com.ipin.thrift.edu.EduStatService;
import com.ipin.thrift.edu.MajorInfo;
import com.ipin.thrift.edu.MajorStatInfo;
import com.ipin.thrift.edu.commons.EDU_DIPLOMA;
import com.ipin.thrift.edu.params.FindMajorStatInfoParams;
import com.ipin.thrift.edu.params.ListMajorInfoParams;

/**
 * CacheMajorFilterListTask.
 * 缓存专业筛选列表结果.
 * 
 * @author zhongyongsheng
 *
 */

@Component
@Lazy(false)
public class CacheMajorFilterListTask extends Task {
	private static final Logger logger = LoggerFactory.getLogger(CacheMajorFilterListTask.class);
	
	@Resource
	private EduInfoService.Iface eduInfoService;
	@Resource
	private EduStatService.Iface eduStatService;
	@Resource
	private CommonService.Iface commonService;
	
	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate<String, List<MajorFilter>> redisTemplate;
	
	@Scheduled(cron = "${schedule.cache.majorfilterlist.cron}")
	public void doTask() {
		try {
			logger.debug("Updating bk major filter list....");
			ListMajorInfoParams listMajorInfoParams = new ListMajorInfoParams();
			listMajorInfoParams.setDiplomaId(EDU_DIPLOMA.EDU_DIPLOMA_BK);
			listMajorInfoParams.setSimpleData(true);
			List<MajorInfo> majorInfoList = eduInfoService.listMajorInfo(listMajorInfoParams);
			if (majorInfoList != null) {
				// 查询专业统计信息列表
				List<MajorFilter> majorFilterList = convertToMajorFilterList(majorInfoList);
				
				if(majorFilterList != null && majorFilterList.size() > 0) {
					// 缓存本科没有排序列表
					redisTemplate.opsForHash().put(Constants.REDIS_MAJOR_FILTER_KEY, Constants.REDIS_MAJOR_FILTER_BK_HASH_KEY, majorFilterList);
					// 缓存本科薪酬列表
					cacheMajorFilterBySalary(majorFilterList, Constants.REDIS_MAJOR_FILTER_BK_XINCHOU_HASH_KEY);
					// 缓存本科女纸列表
					cacheMajorFilterByFemaleRatio(majorFilterList, Constants.REDIS_MAJOR_FILTER_BK_MEIZHI_HASH_KEY, true);
					// 缓存本科女纸列表
					cacheMajorFilterByFemaleRatio(majorFilterList, Constants.REDIS_MAJOR_FILTER_BK_NANZI_HASH_KEY, false);
				}
				
				redisTemplate.opsForHash().put(Constants.REDIS_MAJOR_FILTER_KEY, Constants.REDIS_MAJOR_FILTER_BK_HASH_KEY, majorFilterList);
			}
			
			logger.debug("Update bk major filter list success....");
			
			logger.debug("Updating zk major filter list....");
			listMajorInfoParams = new ListMajorInfoParams();
			listMajorInfoParams.setDiplomaId(EDU_DIPLOMA.EDU_DIPLOMA_ZK);
			listMajorInfoParams.setSimpleData(true);
			majorInfoList = eduInfoService.listMajorInfo(listMajorInfoParams);
			if (majorInfoList != null) {
				// 查询专业统计信息列表
				List<MajorFilter> majorFilterList = convertToMajorFilterList(majorInfoList);
				if (majorFilterList != null && majorFilterList.size() > 0) {
					// 缓存专科没有排序列表
					redisTemplate.opsForHash().put(Constants.REDIS_MAJOR_FILTER_KEY, Constants.REDIS_MAJOR_FILTER_ZK_HASH_KEY, majorFilterList);
					// 缓存专科科薪酬列表
					cacheMajorFilterBySalary(majorFilterList, Constants.REDIS_MAJOR_FILTER_ZK_XINCHOU_HASH_KEY);
					// 缓存专科女纸列表
					cacheMajorFilterByFemaleRatio(majorFilterList, Constants.REDIS_MAJOR_FILTER_ZK_MEIZHI_HASH_KEY, true);
					// 缓存专科女纸列表
					cacheMajorFilterByFemaleRatio(majorFilterList, Constants.REDIS_MAJOR_FILTER_ZK_NANZI_HASH_KEY, false);
				}
			}
			
			logger.debug("Update zk major filter list success....");
		} catch (TException e) {
			logger.error("Error occured while caching sch filter list, message code : " + e.getMessage(), e);
		}
	}
	
	/**
	 * 转换为专业过滤列表
	 * @param majorInfoList
	 * @return
	 * @throws TException
	 */
	private List<MajorFilter> convertToMajorFilterList(List<MajorInfo> majorInfoList) throws TException{
		List<MajorFilter> majorFilterList = new ArrayList<MajorFilter>();
		if (majorInfoList != null) {
			for (int i = 0; i < majorInfoList.size(); i++) {
				MajorFilter majorFilter = new MajorFilter();
				majorFilter.setRank_index(i + 1);
				MajorInfo majorInfo = majorInfoList.get(i);
				majorFilter.setMajor_id(majorInfo.getMajorId());
				majorFilter.setMajor_name(majorInfo.getMajorName());
				majorFilter.setDiploma_id(majorInfo.getDiplomaId().getValue());
				majorFilter.setMajor_catetory(majorInfo.getMajorCategory());
				majorFilter.setMajor_second_category(majorInfo.getMajorSecondCategory());
				
				FindMajorStatInfoParams findMajorStatInfoParams = new FindMajorStatInfoParams();
				findMajorStatInfoParams.setMajorId(majorInfo.getMajorId());
				findMajorStatInfoParams.setDiplomaId(majorInfo.getDiplomaId());
				MajorStatInfo majorStatInfo = eduStatService.findMajorStatInfo(findMajorStatInfoParams);
				if (majorStatInfo != null) {
					// 获取薪酬
					if(majorStatInfo.getMajorRankInfo() != null && majorStatInfo.getMajorRankInfo().getSampleCount() > 30) {
						majorFilter.setSalary_status_rank(majorStatInfo.getMajorRankInfo().getSalaryRank());
						majorFilter.setSalary_factor((int) majorStatInfo.getMajorRankInfo().getSalaryFactor());
						majorFilter.setSalary_status_str(EduStatUtils.getSalaryFactorRankDesc(majorFilter.getSalary_status_rank()));
					} else {
						majorFilter.setSalary_status_rank(-1);
						majorFilter.setSalary_factor(-1);
						majorFilter.setSalary_status_str(EduStatUtils.getSalaryFactorRankDesc(-1));
					}
					
					// 获取性别比例
					if (majorStatInfo.getGenderInfo() != null) {
						int femaleCount = majorStatInfo.getGenderInfo().getFemaleSampleCount();
						int maleCount = majorStatInfo.getGenderInfo().getMaleSampleCount();
						if (femaleCount != 0 || maleCount != 0) {
							int totalCount = femaleCount + maleCount;
							if (totalCount >= 30) {
								majorFilter.setFemale_ratio(femaleCount * 1.0 / totalCount);
							}
						}
					}
				}
				majorFilterList.add(majorFilter);
			}
		}
		return majorFilterList;
	}

	/**
	 * 缓存专业过滤根据薪酬排序
	 * @param majorFilterList 专业过滤列表
	 * @param hashKey 
	 */
	private void cacheMajorFilterBySalary(List<MajorFilter> majorFilterList, String hashKey) {
		Collections.sort(majorFilterList, new Comparator<MajorFilter>() {
			@Override
			public int compare(MajorFilter majorFilter1, MajorFilter majorFilter2) {
				if (majorFilter1.getSalary_factor() < majorFilter2.getSalary_factor()) {
					return 1;
				} else if (majorFilter1.getSalary_factor() > majorFilter2.getSalary_factor()) {
					return -1;
				}
				return 0;
			}
		});
		redisTemplate.opsForHash().put(Constants.REDIS_MAJOR_FILTER_KEY, hashKey, majorFilterList);
		logger.debug("Update major filter by salary succss...");
	}
	
	/**
	 * 缓存专业筛选通过女生比例
	 * @param majorFilterList 专业筛选列表
	 * @param hashKey
	 * @param desc 是否降序
	 */
	private void cacheMajorFilterByFemaleRatio(List<MajorFilter> majorFilterList, String hashKey, boolean desc) {
		List<MajorFilter> rankMajorFilterList = new ArrayList<MajorFilter>();
		List<MajorFilter> noRankMajorFilterList = new ArrayList<MajorFilter>();
		for (MajorFilter majorFilter : majorFilterList) {
			if (majorFilter.getFemale_ratio() <= 0 && majorFilter.getMale_ratio() <= 0) {
				noRankMajorFilterList.add(majorFilter);
			} else {
				rankMajorFilterList.add(majorFilter);
			}
		}
		if(desc) {
			Collections.sort(rankMajorFilterList, new Comparator<MajorFilter>() {
				@Override
				public int compare(MajorFilter majorFilter1, MajorFilter majorFilter2) {
					if (majorFilter1.getFemale_ratio() < majorFilter2.getFemale_ratio()) {
						return 1;
					} else if (majorFilter1.getFemale_ratio() > majorFilter2.getFemale_ratio()) {
						return -1;
					}
					return 0;
				}
			});
		} else {
			Collections.sort(rankMajorFilterList, new Comparator<MajorFilter>() {
				@Override
				public int compare(MajorFilter majorFilter1, MajorFilter majorFilter2) {
					if (majorFilter1.getFemale_ratio() < majorFilter2.getFemale_ratio()) {
						return -1;
					} else if (majorFilter1.getFemale_ratio() > majorFilter2.getFemale_ratio()) {
						return 1;
					}
					return 0;
				}
			});
		}
		rankMajorFilterList.addAll(noRankMajorFilterList);
		redisTemplate.opsForHash().put(Constants.REDIS_MAJOR_FILTER_KEY, hashKey, rankMajorFilterList);
		logger.debug("Update major filter by female ratio succss...");
	}
 }
