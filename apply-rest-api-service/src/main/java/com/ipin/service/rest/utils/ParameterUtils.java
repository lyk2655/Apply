package com.ipin.service.rest.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ipin.service.rest.constants.Constants;
import com.ipin.thrift.edu.commons.EDU_BATCH;
import com.ipin.thrift.edu.commons.EDU_DIPLOMA;
import com.ipin.thrift.edu.commons.EDU_SCH_RANKING_LIST_TYPE;

/**
 * ParameterUtils. 参数帮助工具
 * 
 * @author zhongyongsheng
 *
 */
public class ParameterUtils {

	/**
	 * 判断是否是学历ID
	 * 
	 * @param diplomaId
	 *            学历ID
	 * @return
	 */
	public static boolean isDiplomaId(int diplomaId) {
		return diplomaId == Constants.DIPLOMA_ID_UNIVERSITY || diplomaId == Constants.DIPLOMA_ID_JUNIOR;
	}

	/**
	 * 判断是否是专业设置类型
	 * 
	 * @param majorSettingType
	 *            专业设置类型
	 * @return
	 */
	public static boolean isMajorSettingType(int majorSettingType) {
		return majorSettingType == Constants.MAJOR_SETTING_ENROLLING_MAJOR
				|| majorSettingType == Constants.MAJOR_SETTING_ENROLLING_MAJOR_PER_PROVINCE
				|| majorSettingType == Constants.MAJOR_SETTING_ALL_MAJOR;
	}

	/**
	 * 判断是否文理字段
	 * 
	 * @param wenliFilter
	 *            文理过滤
	 * @return
	 */
	public static boolean isWenLi(String wenliFilter) {
		return Constants.WEN_KE.equals(wenliFilter) || Constants.LI_KE.equals(wenliFilter);
	}

	/**
	 * 判断是否是数字
	 * 
	 * @param content
	 *            内容
	 * @return
	 */
	public static boolean isNum(String content) {
		return RegexUtils.isMatch(content, RegexUtils.NUM_REGEX);
	}

	/**
	 * 四舍五入
	 * 
	 * @param value
	 *            值
	 * @param num
	 *            保留的小数位数
	 * @return
	 */
	public static double roundDouble(double value, int num) {
		return new BigDecimal(value).setScale(num, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 是否是学校排行榜类型
	 * 
	 * @param rankType
	 *            排行榜类型
	 * @return
	 */
	public static boolean isSchRankingListType(String rankType) {
		return Constants.SCH_RANKING_LIST_TYPE_ZONGHE.equals(rankType)
				|| Constants.SCH_RANKING_LIST_TYPE_ZHIMINGDU.equals(rankType)
				|| Constants.SCH_RANKING_LIST_TYPE_JINGZHENGLI.equals(rankType)
				|| Constants.SCH_RANKING_LIST_TYPE_XINCHOU.equals(rankType)
				|| Constants.SCH_RANKING_LIST_TYPE_MEIZHI.equals(rankType);
	}

	/**
	 * 是否是学校筛选列表排序类型
	 * 
	 * @param sortType
	 *            排序类型
	 * @return
	 */
	public static boolean isSchFilterListSortType(String sortType) {
		return Constants.SCH_FILTER_LIST_TYPE_ZONGHE.equals(sortType)
				|| Constants.SCH_FILTER_LIST_TYPE_ZHIMINGDU.equals(sortType)
				|| Constants.SCH_FILTER_LIST_TYPE_JINGZHENGLI.equals(sortType)
				|| Constants.SCH_FILTER_LIST_TYPE_XINCHOU.equals(sortType)
				|| Constants.SCH_FILTER_LIST_TYPE_MEIZHI.equals(sortType)
				|| Constants.SCH_FILTER_LIST_TYPE_NANZHI.equals(sortType);
	}

	/**
	 * 是否是专业筛选列表排序类型
	 * 
	 * @param sortType
	 *            排序类型
	 * @return
	 */
	public static boolean isMajorFilterListSortType(String sortType) {
		return Constants.MAJOR_FILTER_LIST_TYPE_XINCHOU.equals(sortType)
				|| Constants.MAJOR_FILTER_LIST_TYPE_MEIZHI.equals(sortType)
				|| Constants.MAJOR_FILTER_LIST_TYPE_NANZHI.equals(sortType);
	}

	/**
	 * 转换为学校排行榜类型
	 * 
	 * @param rankType
	 *            排行榜类型名字
	 * @return
	 */
	public static EDU_SCH_RANKING_LIST_TYPE convertToSchRankType(String rankType) {
		if (Constants.SCH_RANKING_LIST_TYPE_ZONGHE.equals(rankType)) {
			return EDU_SCH_RANKING_LIST_TYPE.EDU_SCH_RANKING_LIST_TYPE_ZONGHE;
		} else if (Constants.SCH_RANKING_LIST_TYPE_ZHIMINGDU.equals(rankType)) {
			return EDU_SCH_RANKING_LIST_TYPE.EDU_SCH_RANKING_LIST_TYPE_ZHIMINGDU;
		} else if (Constants.SCH_RANKING_LIST_TYPE_JINGZHENGLI.equals(rankType)) {
			return EDU_SCH_RANKING_LIST_TYPE.EDU_SCH_RANKING_LIST_TYPE_JINGZHENGLI;
		} else if (Constants.SCH_RANKING_LIST_TYPE_XINCHOU.equals(rankType)) {
			return EDU_SCH_RANKING_LIST_TYPE.EDU_SCH_RANKING_LIST_TYPE_XINCHOU;
		} else if (Constants.SCH_RANKING_LIST_TYPE_MEIZHI.equals(rankType)) {
			return EDU_SCH_RANKING_LIST_TYPE.EDU_SCH_RANKING_LIST_TYPE_MEIZHI;
		}

		return null;
	}

	/**
	 * 集合转换为字符串
	 * 
	 * @param contentList
	 *            集合内容
	 * @param splitStr
	 *            分割字符
	 * @return
	 */
	public static String listToString(List<String> contentList, String splitStr) {
		if (contentList == null || contentList.size() <= 0) {
			return null;
		}

		String temp = "";
		int i = 0;
		for (; i < contentList.size() - 1; i++) {
			temp += contentList.get(i) + splitStr;
		}
		return temp += contentList.get(i);
	}

	/**
	 * 获取批次对应的名字描述
	 * 
	 * @param batch
	 *            批次
	 * @return
	 */
	public static String toBatchDesc(EDU_BATCH batch) {
		if (EDU_BATCH.EDU_BATCH_BK_1 == batch) {
			return "本科第一批";
		} else if (EDU_BATCH.EDU_BATCH_BK_2 == batch) {
			return "本科第二批";
		} else if (EDU_BATCH.EDU_BATCH_BK_2A == batch) {
			return "本科第二批A";
		} else if (EDU_BATCH.EDU_BATCH_BK_2B == batch) {
			return "本科第二批B";
		} else if (EDU_BATCH.EDU_BATCH_BK_3 == batch) {
			return "本科第三批";
		} else if (EDU_BATCH.EDU_BATCH_BK_3A == batch) {
			return "本科第三批A";
		} else if (EDU_BATCH.EDU_BATCH_BK_3B == batch) {
			return "本科第三批B";
		} else if (EDU_BATCH.EDU_BATCH_ZK_1 == batch) {
			return "专科第一批";
		} else if (EDU_BATCH.EDU_BATCH_ZK_2 == batch) {
			return "专科第二批";
		} else {
			return "未知";
		}
	}

	/**
	 * 根据学历id获取批次列表
	 * 
	 * @param diplomaId
	 *            学历id
	 * @return
	 */
	public static List<EDU_BATCH> getBatchListByDiplomaId(int diplomaId) {
		List<EDU_BATCH> batchList = new ArrayList<EDU_BATCH>();
		if (EDU_DIPLOMA.EDU_DIPLOMA_BK.getValue() == diplomaId) {
			// 本科
			batchList.add(EDU_BATCH.EDU_BATCH_BK_1);
			batchList.add(EDU_BATCH.EDU_BATCH_BK_2);
			batchList.add(EDU_BATCH.EDU_BATCH_BK_2A);
			batchList.add(EDU_BATCH.EDU_BATCH_BK_2B);
			batchList.add(EDU_BATCH.EDU_BATCH_BK_3);
			batchList.add(EDU_BATCH.EDU_BATCH_BK_3A);
			batchList.add(EDU_BATCH.EDU_BATCH_BK_3B);
		} else if (EDU_DIPLOMA.EDU_DIPLOMA_ZK.getValue() == diplomaId) {
			// 专科
			batchList.add(EDU_BATCH.EDU_BATCH_ZK_1);
			batchList.add(EDU_BATCH.EDU_BATCH_ZK_2);
		} else {
			return null;
		}
		return batchList;
	}

	/**
	 * 获取所有批次
	 * 
	 * @return
	 */
	public static List<EDU_BATCH> getAllBatch() {
		List<EDU_BATCH> batchList = new ArrayList<EDU_BATCH>();
		// 本科
		batchList.add(EDU_BATCH.EDU_BATCH_BK_1);
		batchList.add(EDU_BATCH.EDU_BATCH_BK_2);
		batchList.add(EDU_BATCH.EDU_BATCH_BK_2A);
		batchList.add(EDU_BATCH.EDU_BATCH_BK_2B);
		batchList.add(EDU_BATCH.EDU_BATCH_BK_3);
		batchList.add(EDU_BATCH.EDU_BATCH_BK_3A);
		batchList.add(EDU_BATCH.EDU_BATCH_BK_3B);
		// 专科
		batchList.add(EDU_BATCH.EDU_BATCH_ZK_1);
		batchList.add(EDU_BATCH.EDU_BATCH_ZK_2);
		return batchList;
	}

	/**
	 * 判断是否是专业目录等级
	 * 
	 * @param majorCateLevel
	 *            专业目录级别
	 * @return
	 */
	public static boolean isMajorCateLevel(int majorCateLevel) {
		return majorCateLevel == Constants.MAJOR_CATE_LEVEL_1 || majorCateLevel == Constants.MAJOR_CATE_LEVEL_2;
	}

	/**
	 * 判断是否是地区级别
	 * 
	 * @param level
	 *            级别
	 * @return
	 */
	public static boolean isLocationLevel(String level) {
		return Constants.PROVINCE_LEVEL.equals(level) || Constants.CITY_LEVEL.equals(level);
	}

}
