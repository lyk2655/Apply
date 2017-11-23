package com.ipin.service.rest.service;

import com.ipin.service.rest.beans.MajorSecondCategoryDetailResult;
import com.ipin.service.rest.beans.RecommendSchListResult;

/**
 * MajorSecondCategoryService.
 * 专业二级目录服务接口.
 * 
 * @author zhongyongsheng
 *
 */
public interface MajorSecondCategoryService {
	
	/**
	 * 获取专业二级目录详情结果
	 * @param majorSecondCategoryName 专业二级目录名字
	 * @param diplomaId 学历ID
	 * @return
	 */
	public MajorSecondCategoryDetailResult getMajorSecondCategoryDetailResult(String majorSecondCategoryName, int diplomaId);
	
	/**
	 * 获取推荐学校列表结果
	 * @param majorSecondCategoryName 专业二级目录名字
	 * @param diplomaId 学历ID
	 * @return
	 */
	public RecommendSchListResult getRecommendSchListResult(String majorSecondCategoryName, int diplomaId);

}
