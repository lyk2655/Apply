package com.ipin.service.rest.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ipin.service.rest.beans.MajorFilter;
import com.ipin.service.rest.beans.MajorName;
import com.ipin.service.rest.constants.Constants;
import com.ipin.thrift.edu.EduInfoService;
import com.ipin.thrift.edu.EduStatService;
import com.ipin.thrift.edu.MajorCateInfo;
import com.ipin.thrift.edu.MajorInfo;
import com.ipin.thrift.edu.MajorSecondCateIdBaseInfo;
import com.ipin.thrift.edu.commons.EDU_DIPLOMA;
import com.ipin.thrift.edu.params.FindMajorSecondCateIdBaseInfoParams;
import com.ipin.thrift.edu.params.ListMajorCateInfoParams;
import com.ipin.thrift.edu.params.ListMajorInfoByIdsParams;

/**
 * CacheMajorCategoryMappingTask
 * 缓存专业目录映射关系任务.
 * 
 * @author zhongyongsheng
 *
 */

@Component
@Lazy(false)
public class CacheMajorCategoryMappingTask extends Task {
	
	private static final Logger logger = LoggerFactory.getLogger(CacheMajorCategoryMappingTask.class);
	
	@Resource
	private EduInfoService.Iface eduInfoService;
	@Resource
	private EduStatService.Iface eduStatService;
	
	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate<String, List<MajorFilter>> redisTemplate;
	
	@Scheduled(cron = "${schedule.cache.majorcategorymapping.cron}")
	public void doTask() {
		try {
			logger.debug("Updating bk major category mapping...");
			// 缓存本科
			// 查询所有的一级目录和二级目录关系
			ListMajorCateInfoParams listMajorCateInfoParams = new ListMajorCateInfoParams();
			listMajorCateInfoParams.setDiplomaId(EDU_DIPLOMA.EDU_DIPLOMA_BK);
			List<MajorCateInfo> majorCateInfoList = eduInfoService.listMajorCateInfo(listMajorCateInfoParams);
			if (majorCateInfoList != null && majorCateInfoList.size() > 0) {
				Map<String, List<String>> majorCategoryMapToMajorSecondCategory = new HashMap<String, List<String>>();
				// 遍历查询其所有二级目录
				for (MajorCateInfo majorCateInfo : majorCateInfoList) {
					List<String> majorSecondCategoryNameList = majorCategoryMapToMajorSecondCategory.get(majorCateInfo.getMajorCateName());
					if(majorSecondCategoryNameList == null) {
						majorSecondCategoryNameList = new ArrayList<String>();
						majorCategoryMapToMajorSecondCategory.put(majorCateInfo.getMajorCateName(), majorSecondCategoryNameList);
					}
					majorSecondCategoryNameList.add(majorCateInfo.getMajorSecondCateName());
				}
				// 缓存本科一级目录和二级目录关系
				redisTemplate.opsForHash().put(Constants.REDIS_MAJOR_CATEGORY_MAPPING_KEY, Constants.REDIS_MAJOR_CATEGORY_BK_MAPPING_HASH_KEY, majorCategoryMapToMajorSecondCategory);
				Map<String, List<MajorName>> majorSecondCategoryMappingToMajorName  = new HashMap<String, List<MajorName>>();
				// 遍历查询二级目录所对应的专业
				for (List<String> majorSecondCategoryNameList : majorCategoryMapToMajorSecondCategory.values()) {
					for(String majorSecondCategoryName : majorSecondCategoryNameList) {
						FindMajorSecondCateIdBaseInfoParams findMajorSecondCateIdBaseInfoParams = new FindMajorSecondCateIdBaseInfoParams();
						findMajorSecondCateIdBaseInfoParams.setMajorSecondCate(majorSecondCategoryName);
						findMajorSecondCateIdBaseInfoParams.setDiplomaId(EDU_DIPLOMA.EDU_DIPLOMA_BK);
						MajorSecondCateIdBaseInfo majorSecondCateIdBaseInfo = eduInfoService.findMajorSecondCateIdBaseInfo(findMajorSecondCateIdBaseInfoParams);
						if (majorSecondCateIdBaseInfo != null) {
							List<MajorName> majorNameList = majorSecondCategoryMappingToMajorName.get(majorSecondCateIdBaseInfo.getMajorSecondCategory());
							if(majorNameList == null) {
								majorNameList = new ArrayList<MajorName>();
								majorSecondCategoryMappingToMajorName.put(majorSecondCateIdBaseInfo.getMajorSecondCategory(), majorNameList);
							}
							// 遍历保存专业二级目录对应的专业列表
							List<String> majorIdList = majorSecondCateIdBaseInfo.getMajorIdList();
							if (majorIdList != null && majorIdList.size() > 0) {
								ListMajorInfoByIdsParams listMajorInfoByIdsParams = new ListMajorInfoByIdsParams();
								listMajorInfoByIdsParams.setSimpleData(true);
								listMajorInfoByIdsParams.setMajorIdList(majorIdList);
								List<MajorInfo> majorInfoList = eduInfoService.listMajorInfoByIds(listMajorInfoByIdsParams);
								if (majorInfoList != null && majorInfoList.size() > 0) {
									for (MajorInfo majorInfo : majorInfoList) {
										MajorName majorName = new MajorName();
										majorName.setMajor_id(majorInfo.getMajorId());
										majorName.setMajor_name(majorInfo.getMajorName());
										majorNameList.add(majorName);
									}
								}
							}
						}
					}
				}
				// 缓存二级专业目录和其所映射的专业列表
				redisTemplate.opsForHash().put(Constants.REDIS_MAJOR_CATEGORY_MAPPING_KEY, Constants.REDIS_MAJOR_SECOND_CATEGORY_BK_MAPPING_HASH_KEY, majorSecondCategoryMappingToMajorName);
			}
			logger.debug("Update bk major category mapping success...");
			
			logger.debug("Updating zk major category mapping...");
			// 缓存专科
			// 查询所有的一级目录和二级目录关系
			listMajorCateInfoParams = new ListMajorCateInfoParams();
			listMajorCateInfoParams.setDiplomaId(EDU_DIPLOMA.EDU_DIPLOMA_ZK);
			majorCateInfoList = eduInfoService.listMajorCateInfo(listMajorCateInfoParams);
			if (majorCateInfoList != null && majorCateInfoList.size() > 0) {
				Map<String, List<String>> majorCategoryMapToMajorSecondCategory = new HashMap<String, List<String>>();
				// 遍历查询其所有二级目录
				for (MajorCateInfo majorCateInfo : majorCateInfoList) {
					List<String> majorSecondCategoryNameList = majorCategoryMapToMajorSecondCategory.get(majorCateInfo.getMajorCateName());
					if(majorSecondCategoryNameList == null) {
						majorSecondCategoryNameList = new ArrayList<String>();
						majorCategoryMapToMajorSecondCategory.put(majorCateInfo.getMajorCateName(), majorSecondCategoryNameList);
					}
					majorSecondCategoryNameList.add(majorCateInfo.getMajorSecondCateName());
				}
				// 缓存专科一级目录和二级目录关系
				redisTemplate.opsForHash().put(Constants.REDIS_MAJOR_CATEGORY_MAPPING_KEY, Constants.REDIS_MAJOR_CATEGORY_ZK_MAPPING_HASH_KEY, majorCategoryMapToMajorSecondCategory);
				Map<String, List<MajorName>> majorSecondCategoryMappingToMajorName  = new HashMap<String, List<MajorName>>();
				// 遍历查询二级目录所对应的专业
				for (List<String> majorSecondCategoryNameList : majorCategoryMapToMajorSecondCategory.values()) {
					for(String majorSecondCategoryName : majorSecondCategoryNameList) {
						FindMajorSecondCateIdBaseInfoParams findMajorSecondCateIdBaseInfoParams = new FindMajorSecondCateIdBaseInfoParams();
						findMajorSecondCateIdBaseInfoParams.setMajorSecondCate(majorSecondCategoryName);
						findMajorSecondCateIdBaseInfoParams.setDiplomaId(EDU_DIPLOMA.EDU_DIPLOMA_ZK);
						MajorSecondCateIdBaseInfo majorSecondCateIdBaseInfo = eduInfoService.findMajorSecondCateIdBaseInfo(findMajorSecondCateIdBaseInfoParams);
						if (majorSecondCateIdBaseInfo != null) {
							List<MajorName> majorNameList = majorSecondCategoryMappingToMajorName.get(majorSecondCateIdBaseInfo.getMajorSecondCategory());
							if(majorNameList == null) {
								majorNameList = new ArrayList<MajorName>();
								majorSecondCategoryMappingToMajorName.put(majorSecondCateIdBaseInfo.getMajorSecondCategory(), majorNameList);
							}
							// 遍历保存专业二级目录对应的专业列表
							List<String> majorIdList = majorSecondCateIdBaseInfo.getMajorIdList();
							if (majorIdList != null && majorIdList.size() > 0) {
								ListMajorInfoByIdsParams listMajorInfoByIdsParams = new ListMajorInfoByIdsParams();
								listMajorInfoByIdsParams.setSimpleData(true);
								listMajorInfoByIdsParams.setMajorIdList(majorIdList);
								List<MajorInfo> majorInfoList = eduInfoService.listMajorInfoByIds(listMajorInfoByIdsParams);
								if (majorInfoList != null && majorInfoList.size() > 0) {
									for (MajorInfo majorInfo : majorInfoList) {
										MajorName majorName = new MajorName();
										majorName.setMajor_id(majorInfo.getMajorId());
										majorName.setMajor_name(majorInfo.getMajorName());
										majorNameList.add(majorName);
									}
								}
							}
						}
					}
				}
				// 缓存二级专业目录和其所映射的专业列表
				redisTemplate.opsForHash().put(Constants.REDIS_MAJOR_CATEGORY_MAPPING_KEY, Constants.REDIS_MAJOR_SECOND_CATEGORY_ZK_MAPPING_HASH_KEY, majorSecondCategoryMappingToMajorName);
			}
			logger.debug("Update zk major category mapping success...");
			
		} catch (Exception e) {
			logger.error("Error occured while caching major category mapping task, message code : " + e.getMessage(), e);
		}
	}

}
