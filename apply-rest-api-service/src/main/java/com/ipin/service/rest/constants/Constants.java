package com.ipin.service.rest.constants;

/**
 * Constants.
 * 常量类
 * 
 * @author zhongyongsheng
 *
 */
public class Constants {
	
	//招聘信息类型
	public static final String APPLY_TYPE_APPLICATION = "网申"; //网申
	public static final String APPLY_TYPE_TEACHIN = "宣讲会"; //宣讲会
	public static final String APPLY_TYPE_EXAMINATION = "笔试"; //笔试
	public static final String APPLY_TYPR_INTERVIEW = "面试"; //面试
	
	//招聘状态:无，未开始，未完成，已完成，已被刷，已通过，已取消，已错过
	public static final int APPLY_STATUS_NONE = 0; //没有
	public static final int APPLY_STATUS_NOT_BEGIN = 1; //未开始
	public static final int APPLY_STATUS_NOT_COMPLETED = 2; //未完成
	public static final int APPLY_STATUS_COMPLETED = 3; //已完成
	public static final int APPLY_STATUS_FAILED = 4; //已被刷
	public static final int APPLY_STATUS_SUCCESS = 5;//通过
	public static final int APPLY_STATUS_CANCEL = 6;//取消
	public static final int APPLY_STATUS_MISSING = 7;//错过
	public static final int APPY_STATUS_NO_CHANCE = 8; //没机会
	public static final int APPLY_STATUS_ALL = 9; //所有
	
	//计划日期筛选参数
	public static final int APPLY_DATE_FILTER_0 = 0; //今天之内
	public static final int APPLY_DATE_FILTER_1 = 1; //明天之前
	public static final int APPLY_DATE_FILTER_7 = 7; //一周内
	public static final int APPLY_DATE_FILTER_30 = 30; //一个月内
	public static final int APPLY_DATE_FILTER_365 = 365; //所有
	
	
	
	
	// 参数
	public static final int DIPLOMA_ID_UNIVERSITY = 7; // 本科
	public static final int DIPLOMA_ID_JUNIOR = 5;// 专科
	public static final String WEN_KE = "wen"; // 文科
	public static final String LI_KE = "li"; // 理科
	public static final int MAJOR_CATE_LEVEL_1 = 1; //1级目录
	public static final int MAJOR_CATE_LEVEL_2 = 2;//二级目录
	public static final String PROVINCE_LEVEL = "province";// 省级别
	public static final String CITY_LEVEL = "city";// 城市级别
	
	// 学校统计信息
	public static final int GENDER_MALE = 1;// 男性
	public static final int GENDER_FEMALE = 0;// 女性
	public static final int SCH_STATS_SAMPLE_COUNT = 30; // 学校统计样本值最小值
	public static final int SCH_STATS_SALARY_SHOW_YEAR = 10; // 只展示10年的
	public static final int SCH_RANKING_LIST_PAEG_SIZE = 20;// 学校排行榜每页个数
	public static final int SCH_FILTER_LIST_PAGE_SIZE = 20;// 学校筛选列表每页个数
	public static final int MAJOR_FILTER_LIST_PAGE_SIZE = 20;//专业筛选列表每页个数
	
	//  专业设置
	public static final int MAJOR_SETTING_ENROLLING_MAJOR =  0;// 在招专业
	public static final int MAJOR_SETTING_ENROLLING_MAJOR_PER_PROVINCE = 1;//各省在招专业
	public static final int MAJOR_SETTING_ALL_MAJOR = 2;//全部专业
	public static final int MAJOR_SETTING_SAMPLE_COUNT = 31; // 专业筛选样本值最小值
	
	// ThriftClient代理
	public static final int THRIFT_CLIENT_PROXY_TRANSPORT_TIMEOUT = 20000;// 超时时间
	
	// redis cache
	public static final String REDIS_SCH_ENROLL_PLAN_PARAMS_KEY = "all_sch_enroll_plan_params";
	public static final String REDIS_LOCATION_KEY = "sch_location";
	public static final String REDIS_ALL_PROVINCE_HASH_KEY = "all_province";
	public static final String REDIS_ALL_CITY_HASH_KEY = "all_city";
	public static final String REDIS_SCH_FILTER_KEY = "sch_filter";
	public static final String REDIS_MAJOR_CATEGORY_MAPPING_KEY = "major_category_mapping";
	public static final String REDIS_SCH_FILTER_BK_ZONGHE_HASH_KEY = "bk_zonghe";
	public static final String REDIS_SCH_FILTER_BK_ZHIMINGDU_HASH_KEY = "bk_zhimingdu";
	public static final String REDIS_SCH_FILTER_BK_JINGZHENGLI_HASH_KEY = "bk_jingzhengli";
	public static final String REDIS_SCH_FILTER_BK_XINCHOU_HASH_KEY = "bk_xinchou";
	public static final String REDIS_SCH_FILTER_BK_MEIZHI_HASH_KEY = "bk_meizhi";
	public static final String REDIS_SCH_FILTER_BK_NANZHI_HASH_KEY = "bk_nanzhi";
	public static final String REDIS_SCH_FILTER_ZK_XINCHOU_HASH_KEY = "zk_xinchou";
	public static final String REDIS_SCH_FILTER_ZK_MEIZHI_HASH_KEY = "zk_meizhi";
	public static final String REDIS_SCH_FILTER_ZK_NANZHI_HASH_KEY = "zk_nanzhi";
	public static final String REDIS_MAJOR_FILTER_KEY = "major_filter";
	public static final String REDIS_MAJOR_FILTER_BK_HASH_KEY = "bk";
	public static final String REDIS_MAJOR_FILTER_BK_XINCHOU_HASH_KEY = "bk_xinchou";
	public static final String REDIS_MAJOR_FILTER_BK_MEIZHI_HASH_KEY = "bk_meizhi";
	public static final String REDIS_MAJOR_FILTER_BK_NANZI_HASH_KEY = "bk_nanzi";
	public static final String REDIS_MAJOR_FILTER_ZK_HASH_KEY = "zk";
	public static final String REDIS_MAJOR_FILTER_ZK_XINCHOU_HASH_KEY = "zk_xinchou";
	public static final String REDIS_MAJOR_FILTER_ZK_MEIZHI_HASH_KEY = "zk_meizhi";
	public static final String REDIS_MAJOR_FILTER_ZK_NANZI_HASH_KEY = "zk_nanzi";
	public static final String REDIS_MAJOR_CATEGORY_BK_MAPPING_HASH_KEY = "bk_major_category";
	public static final String REDIS_MAJOR_SECOND_CATEGORY_BK_MAPPING_HASH_KEY = "bk_major_second_category";
	public static final String REDIS_MAJOR_CATEGORY_ZK_MAPPING_HASH_KEY = "zk_major_category";
	public static final String REDIS_MAJOR_SECOND_CATEGORY_ZK_MAPPING_HASH_KEY = "zk_major_second_category";
	

	// utils
	public static final int UTILS_TOTAL_GRADE_DESC_TOP_RANK_COUNT = 5;//  排名前几位
	public static final double UTILS_INDUSTRY_RATIO_LINE = 80.0;// 行业比例分界线 
	public static final double UTILS_GENDER_RATIO_ABS = 2.0; //性别比例相差绝对值
	
	// 学校筛选
	public static final String SCH_FILTER_LIST_TYPE_ZONGHE = "zonghe";// 就业综合排名
	public static final String SCH_FILTER_LIST_TYPE_ZHIMINGDU = "zhimingdu";// 知名度排名
	public static final String SCH_FILTER_LIST_TYPE_JINGZHENGLI = "jingzhengli";// 竞争力排名
	public static final String SCH_FILTER_LIST_TYPE_XINCHOU = "xinchou";// 薪酬排名
	public static final String SCH_FILTER_LIST_TYPE_MEIZHI = "meizhi";// 妹纸最多
	public static final String SCH_FILTER_LIST_TYPE_NANZHI = "nanzhi";// 男纸最多
	
	// 学校排行榜
	public static final String SCH_RANKING_LIST_TYPE_ZONGHE = "zonghe";// 就业综合排名
	public static final String SCH_RANKING_LIST_TYPE_ZHIMINGDU = "zhimingdu";// 知名度排名
	public static final String SCH_RANKING_LIST_TYPE_JINGZHENGLI = "jingzhengli";// 竞争力排名
	public static final String SCH_RANKING_LIST_TYPE_XINCHOU = "xinchou";// 薪酬排名
	public static final String SCH_RANKING_LIST_TYPE_MEIZHI = "meizhi";// 妹纸最多
	
	// 专业筛选
	public static final String MAJOR_FILTER_LIST_TYPE_XINCHOU = "xinchou"; // 薪酬排名
	public static final String MAJOR_FILTER_LIST_TYPE_MEIZHI = "meizhi"; // 妹纸最多
	public static final String MAJOR_FILTER_LIST_TYPE_NANZHI = "nanzhi"; // 男纸最多
}
