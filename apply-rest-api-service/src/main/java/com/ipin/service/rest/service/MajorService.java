package com.ipin.service.rest.service;

import com.ipin.service.rest.beans.MajorDetailResult;
import com.ipin.service.rest.beans.MajorFilterListResult;
import com.ipin.service.rest.beans.MajorFilterParamsResult;
import com.ipin.service.rest.beans.MajorNameListResult;
import com.ipin.service.rest.beans.MajorSecondCategoryNameListResult;
import com.ipin.service.rest.beans.RecommendSchListResult;

/**
 * MajorService.
 * 专业业务.
 * 
 * @author zhongyongsheng
 *
 */
public interface MajorService {
	
	/**
	 * 获取专业详情结果
	 * @param majorId 专业ID
	 * @param diplomaId 学历ID
	 * @return
	 */
	public MajorDetailResult getMajorDetailResult(String majorId, int diplomaId);

	/**
	 * 获取推荐学校列表结果
	 * @param majorId 专业ID
	 * @param diplomaId 学历ID
	 * @return
	 */
	public RecommendSchListResult getRecommendSchoolListResult(String majorId, int diplomaId);
	
	/**
	 * 获取专业筛选列表结果
	 * @param diplomaId 学历ID
	 * @param majorCategory 专业一级目录
	 * @param sortBy 排序类型
	 * @param count 每页显示数量
	 * @param pageNo 查询第几页
	 * @return
	 */
	public MajorFilterListResult getMajorFilterListResult(Integer diplomaId, String majorCategory, String sortBy, Integer count, Integer pageNo);

	/**
	 * 获取专业过滤参数结果
	 * @param diplomaId 学历ID
	 * @param categoryLevel 目录级别
	 * @return
	 */
	public MajorFilterParamsResult getMajorFilterParamsResult(int diplomaId, int categoryLevel);
	
	/**
	 * 获取专业二级目录名字列表结果
	 * @param majorCategoryName 专业目录名字
	 * @param diplomaId 学历ID
	 * @return
	 */
	public MajorSecondCategoryNameListResult getMajorSecondCategoryNameListResult(String majorCategoryName, int diplomaId);

	/**
	 * 获取专业名字列表结果
	 * @param majorSecondCategoryName 专业二级目录名字
	 * @param diplomaId 学历ID
	 * @return
	 */
	public MajorNameListResult getMajorNameListResult(String majorSecondCategoryName, int diplomaId);
}
