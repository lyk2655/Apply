package com.ipin.service.rest.service;

import javax.ws.rs.QueryParam;

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

/**
 * Created by longman on 1/12/16.
 */
public interface SchoolService {
    
    /**
     * 获取学校详情结果
     * @param schId 学校ID
     * @param diplomaId 学历ID
     * @return
     */
    public SchDetailResult getSchoolDetail(String schId, int diplomaId);
    
    /**
     * 获取专业设置列表结果
     * @param schId 学校ID
     * @param diplomaId 学历ID
     * @param majorType 查询专业设置列表类型
     * @param provinceFilter 省份过滤
     * @param wenliFilter 文理过滤
     * @param yearFilter 招生年份过滤
     * @return
     */
    public SchMajorSettingListResult getSchMajorSettingListResult(String schId, int diplomaId, int majorType, String provinceFilter, String wenliFilter, String yearFilter);
    
    /**
     * 获取学校招生计划参数
     * @param schId 学校ID
     * @param diplomaId 学历ID
     * @return
     */
    public SchEnrollPlanParamsResult getSchEnrollPlanParams(String schId, int diplomaId);
    
    /**
     * 获取学校排行榜
     * @param rankType 排行榜类型
     * @param provinceFilter 省份过滤条件
     * @param schNamePattern 学校名字部分匹配
     * @param pageSize 每页显示个数
     * @param curPage 要查询的页数
     * @return
     */
    public SchRankingListResult getSchRankingListResult(String rankType, String provinceFilter, String schNamePattern,
    		Integer pageSize,  Integer curPage);
    
    /**
     * 获取薪酬学校专业排行榜
     * @param schId 学校ID
     * @param diplomaId 学历ID
     * @return
     */
    public SchMajorRankingListResult getSchMajorRankingListResultBySalary(String schId, int diplomaId);
    
    /**
     * 获取女性比例学校专业排行榜
     * @param schId 学校ID
     * @param diplomaId 学历ID
     * @return
     */
    public SchMajorRankingListResult getSchMajorRankingListResultByGender(String schId, int diplomaId);
    
    /**
     * 获取薪酬增长学校专业排行榜
     * @param schId 学校ID
     * @param diplomaId 学历ID
     * @return
     */
    public SchMajorRankingListResult getSchMajorRankingListResultBySalaryGrowth(String schId, int diplomaId);
    
    /**
     * 获取录取分数学校专业排行榜
     * @param schId 学校ID
     * @param diplomaId 学历ID
     * @param wenliFilter 文理过滤
     * @param provinceIdFilter 省份过滤
     * @param yearFilter 年份过滤
     * @return
     */
    public SchMajorRankingListResult getSchMajorRankingListResultByScore(String schId, int diplomaId, String wenliFilter, String provinceIdFilter, String yearFilter);

    /**
     * 获取学校录取分数列表结果
     * @param schId 学校ID
     * @param diplomaId 学历ID
     * @param provinceIdFilter 省份过滤
     * @param wenliFilter 文理过滤
     * @return
     */
    public SchScoreListResult getSchScoreListResult(String schId, int diplomaId, String provinceIdFilter, String wenliFilter);
    
    /**
     * 获取学校录取分参数结果
     * @param schId 学校ID
     * @param diplomaId 学历ID
     * @return
     */
    public SchScoreParamsResult getSchScoreParamsResult(String schId, int diplomaId);
    
    /**
     * 获取学校专业录取分列表结果
     * @param schId 学校ID
     * @param diplomaId 学历ID
     * @param wenliFilter 文理过滤
     * @param provinceIdFilter 省份过滤
     * @param yearFilter 年份过滤
     * @return
     */
    public SchMajorScoreListResult getSchMajorScoreListResult(String schId, int diplomaId, String wenliFilter, String provinceIdFilter, String yearFilter);

    /**
     * 获取学校专业录取分参数结果
     * @param schId 学校ID
     * @param diplomaId 学历ID
     * @return
     */
    public SchMajorScoreParamsResult getSchMajorScoreParamsResult(String schId, int diplomaId);
    
    /**
     * 获取学生招生计划列表结果
     * @param schId 学校iD
     * @param diplomaId 学历ID
     * @param provinceFilter 省份过滤
     * @param wenliFilter 文理过滤
     * @param yearFilter 年份过滤
     * @return
     */
    public SchEnrollPlanListResult getSchEnrollPlanListResult(String schId, int diplomaId, String provinceFilter, String wenliFilter, String yearFilter);

   /**
    * 获取学校筛选列表结果
    * @param provinceFilter 省份过滤
    * @param diplomaId 学历ID
    * @param schoolType 学校类型
    * @param majorSecondCategoryFilter 学校专业二级目录
    * @param majorFilter 专业id
    * @param schNamePattern 学校名字部分匹配
    * @param sortBy 排序方式
    * @param count 每页显示数量
    * @param pageNo 页码
    * @return
    */
	public SchFilterListResult getSchFilterListResult(String provinceFilter, int diplomaId, String schoolType,
			String majorSecondCategoryFilter, String majorFilter, String schNamePattern, String sortBy, Integer count, Integer pageNo);
}
