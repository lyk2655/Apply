package com.ipin.service.rest.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ipin.service.rest.beans.GenderDist;
import com.ipin.service.rest.beans.IndustryDist;
import com.ipin.service.rest.beans.JobFunctionDist;
import com.ipin.service.rest.beans.LocationDist;
import com.ipin.service.rest.beans.PredictSalaryStat;
import com.ipin.service.rest.beans.SalaryStat;
import com.ipin.service.rest.constants.Constants;
import com.ipin.thrift.edu.CurrentLocationInfo;
import com.ipin.thrift.edu.GenderInfo;
import com.ipin.thrift.edu.IndustryDistInfo;
import com.ipin.thrift.edu.JobFunctionInfo;
import com.ipin.thrift.edu.JobPositionInfo;
import com.ipin.thrift.edu.PredictSalaryInfo;
import com.ipin.thrift.edu.SalaryDetailInfo;
import com.ipin.thrift.edu.SchRankInfo;

/**
 * SchStatDescribeUtils 学校统计描述帮助工具
 * 
 * @author zhongyongsheng
 *
 */
public class EduStatUtils {
	
	private static String[] rankNames = new String[]{"A+", "A", "A-",
																									   "B+", "B", "B-",
																									   "C+", "C", "C-",
																									   "D+", "D", "D-",
																									   "E+", "E", "F"};
	
	private static double[] rankValues = new double[]{0.93, 0.9, 0.85,
																											   0.77, 0.7, 0.63,
																											   0.57, 0.5, 0.43,
																											   0.37, 0.3, 0.23,
																											   0.17, 0.1, 0.0};
	
	// 页面展示的长度
	private static double[] rankWidths = new double[]{1.0, 0.9, 0.85,
																											  0.8, 0.7, 0.65, 
																											  0.6, 0.5, 0.45, 
																											  0.4, 0.33, 0.28,
																											  0.23, 0.18, 0.15};
			

	/**
	 * 学校等级描述
	 * @param schRankInfo 学校排名信息
	 * @return
	 */
	public static String getTotalGradeDesc(SchRankInfo schRankInfo) {
		if(schRankInfo == null) {
			return "-";
		}
		
		int totalRankIndex = schRankInfo.getTotalRankIndex();
		int salaryFactorRankIndex = schRankInfo.getSalaryFactorRankIndex();
		int popularityRankIndex = schRankInfo.getPopularityRankIndex();
		
		// 所有排名均在前5名，则为A++
		if((totalRankIndex > 0 && totalRankIndex <= Constants.UTILS_TOTAL_GRADE_DESC_TOP_RANK_COUNT)
				&& (salaryFactorRankIndex > 0 && salaryFactorRankIndex <= Constants.UTILS_TOTAL_GRADE_DESC_TOP_RANK_COUNT)
				&& (popularityRankIndex > 0 && popularityRankIndex <= Constants.UTILS_TOTAL_GRADE_DESC_TOP_RANK_COUNT) ) {
			return "A++";
		}
		
		// 如果没有总排名, 则显示-
		if(schRankInfo.getTotalRank() <= 0) {
			return "-";
		}
		
		// 根据totalRank显示相应的等级描述
		double totalRankValue = schRankInfo.getTotalRank() / 100.0;
		return getRankName(totalRankValue);
	}
	
	/**
	 * 获取排名比例
	 * @param rank 排名值
	 * @param rankIndex 排名值(整数)
	 * @return
	 */
	public static double getRankRatio(double rank, int rankIndex) {
		if(rank <= 0) {
			return 0.0;
		}
		
		if(rankIndex > 0 && rankIndex <= Constants.UTILS_TOTAL_GRADE_DESC_TOP_RANK_COUNT) {
			return 1.0;
		}
		
		double rankValue = rank / 100.0;
		return getRankWidth(rankValue);
		
	}
	
	/**
	 * 获取城市排名比例
	 * @param cityLevel 城市级别
	 * @return
	 */
	public static double getCityRankRatio(int cityLevel) {
		double cityValue = 0.0;
		if(cityLevel == 1) {
			cityValue = 0.95;
		} else if (cityLevel == 2) {
			cityValue = 0.7;
		} else if(cityLevel == 3) {
			cityValue = 0.5;
		} else {
			cityValue = 0.3;
		}
		
		return getRankWidth(cityValue);
	}
	
	/**
	 * 返回排名名字
	 * @param rankValue 排名值
	 * @return
	 */
	private static String getRankName(double rankValue) {
		for(int i = 0; i < rankValues.length; i++) {
			if(rankValue >= rankValues[i]) {
				return rankNames[i];
			}
		}
		
		return rankNames[rankNames.length - 1];
	}
	
	/**
	 * 获取排名长度
	 * @param rankValue 排名值
	 * @return
	 */
	private static double getRankWidth(double rankValue) {
		for(int i = 0; i < rankValues.length; i++) {
			if(rankValue >= rankValues[i]) {
				return rankWidths[i];
			}
		}
		
		return rankWidths[rankWidths.length];
	}
	
	/**
	 * 获取行业分布描述
	 * @param industryDistList 行业分布列表
	 * @return
	 */
	public static String getIndustryDistDesc(List<IndustryDist> industryDistList) {
		if(industryDistList != null) {
			int count = 0;
			double top5Ratio = 0.0;
			for(IndustryDist industryDist : industryDistList) {
				count++;
				if(count > 5) {
					break;
				}
				top5Ratio += industryDist.getRatio();
			}
			if(top5Ratio >= Constants.UTILS_INDUSTRY_RATIO_LINE) {
				return "集中";
			}
		}
		return "宽泛";
	}
	
	/**
	 * 获取性别描述
	 * @param generDistList 性别列表
	 * @return
	 */
	public static String getGenderDistDesc(List<GenderDist> genderDistList) {
		if(genderDistList == null || genderDistList.size() != 2) {
			return "未知";
		}
		
		double maleRatio = 0.0;
		double femaleRatio = 0.0;
		for(GenderDist genderDist : genderDistList) {
			if(genderDist.getGender() == Constants.GENDER_MALE) {
				maleRatio = genderDist.getRatio();
			} else {
				femaleRatio = genderDist.getRatio();
			}
		}
		double abs = Math.abs(maleRatio * 100 - femaleRatio * 100);
		if(abs < Constants.UTILS_GENDER_RATIO_ABS) {
			// 在范围内
			return "男女均衡";
		}
		
		if(maleRatio > femaleRatio) {
			return "男生较多";
		} else {
			return "女生较多";
		}
		
	}
	
	/**
	 * 转换为行业分布列表
	 * @param industryDistInfoList 行业分布列表
	 * @return
	 */
	public static List<IndustryDist> convertToIndustryDistList(List<IndustryDistInfo> industryDistInfoList) {
		List<IndustryDist> industryDistList = new ArrayList<IndustryDist>();
		if(industryDistInfoList != null) {
			IndustryDist jinrongIndustryDist = null;
			for(IndustryDistInfo industryDistInfo : industryDistInfoList) {
				if(industryDistInfo.getIndustryId().equals("58")) {
					// 过滤id为58的行业
					continue;
				}
				if(industryDistInfo.getIndustryId().equals("27") || industryDistInfo.getIndustryId().equals("49")) {
					// 合并金融行业
					if(jinrongIndustryDist == null) {
						jinrongIndustryDist = new IndustryDist();
						jinrongIndustryDist.setIndustry_id("27");
						jinrongIndustryDist.setRatio(industryDistInfo.getRatio());
						jinrongIndustryDist.setSample_count(industryDistInfo.getSampleCount());
						industryDistList.add(jinrongIndustryDist);
					} else {
						jinrongIndustryDist.setRatio(jinrongIndustryDist.getRatio() + industryDistInfo.getRatio());
						jinrongIndustryDist.setSample_count(jinrongIndustryDist.getSample_count() + industryDistInfo.getSampleCount());
					}
					continue;
				}
				
				IndustryDist industryDist = new IndustryDist();
				industryDist.setIndustry_id(industryDistInfo.getIndustryId());
				industryDist.setRatio(industryDistInfo.getRatio() > 0.0001 ? industryDistInfo.getRatio() : 0.0001);// 小于0.0001 显示0.0001
				industryDist.setSample_count(industryDistInfo.getSampleCount());
				industryDistList.add(industryDist);
			}
			// 降序排序
			Collections.sort(industryDistList);
		}
		return industryDistList;
	}
	
	/**
	 * 转换为地区分布，且后缀补充字段
	 * @param currentLocationDistInfoList 现居地列表
	 * @param suffix 后缀
	 * @return
	 */
	public static List<LocationDist> convertToLocationDistList(List<CurrentLocationInfo> currentLocationDistInfoList, String suffix) {
		List<LocationDist> locationDistList = convertToLocationDistList(currentLocationDistInfoList);
		if (!StringUtils.isBlank(suffix)) {
			for (LocationDist locaitonDist : locationDistList) {
				locaitonDist.setCity_id(locaitonDist.getCity_id() + suffix);
			}
		}
		return locationDistList;
	}
	
	/**
	 * 转换为地区分布
	 * @param currentLocationDistInfoList 现居地列表
	 * @return
	 */
	public static List<LocationDist> convertToLocationDistList(List<CurrentLocationInfo> currentLocationDistInfoList) {
		List<LocationDist> locationDistList = new ArrayList<LocationDist>();
		if(currentLocationDistInfoList != null) {
			for(CurrentLocationInfo currentLocationInfo : currentLocationDistInfoList) {
				LocationDist locationDist = new LocationDist();
				locationDist.setCity_id(currentLocationInfo.getCityId());
				locationDist.setRatio(currentLocationInfo.getRatio());
				locationDist.setSample_count(currentLocationInfo.getSampleCount());
				locationDistList.add(locationDist);
			}
			// 降序排序
			Collections.sort(locationDistList);
		}
		return locationDistList;
	}
	
	/**
	 * 转换为性别分布
	 * @param genderInfo 性别信息
	 * @return
	 */
	public static List<GenderDist> convertToGenderDistList(GenderInfo currentGenderInfo, GenderInfo genderInfo, int schStatsSampleCount) {
		List<GenderDist> genderDistList = new ArrayList<GenderDist>();
		if( schStatsSampleCount < Constants.SCH_STATS_SAMPLE_COUNT || (currentGenderInfo == null && genderInfo == null)) {
			// 样本数太少或者没有性别统计信息
			return genderDistList;
		}
		
		int maleSampleCount = 0;
		int femaleSampeCount = 0;
		if(currentGenderInfo != null && currentGenderInfo.getFemaleSampleCount() + currentGenderInfo.getMaleSampleCount() >= Constants.SCH_STATS_SAMPLE_COUNT) {
			// 优先选用最近几年的性别统计信息
			maleSampleCount = currentGenderInfo.getMaleSampleCount();
			femaleSampeCount = currentGenderInfo.getFemaleSampleCount();
		} else {
			maleSampleCount = genderInfo.getMaleSampleCount();
			femaleSampeCount = genderInfo.getFemaleSampleCount();
		}
		int totalSampleCount = maleSampleCount + femaleSampeCount;
		
		GenderDist maleGenderDist = new GenderDist();
		maleGenderDist.setGender(Constants.GENDER_MALE);
		maleGenderDist.setSample_count(maleSampleCount);
		maleGenderDist.setRatio(maleSampleCount * 1.0 /  totalSampleCount);
		genderDistList.add(maleGenderDist);
		
		GenderDist femaleGenderDist = new GenderDist();
		femaleGenderDist.setGender(Constants.GENDER_FEMALE);
		femaleGenderDist.setSample_count(femaleSampeCount);
		femaleGenderDist.setRatio(femaleSampeCount * 1.0 / totalSampleCount);
		genderDistList.add(femaleGenderDist);
		return genderDistList;
	}
	
	/**
	 * 转换为薪酬统计列表
	 * @param salaryDetailInfoList 薪酬详细信息列表
	 * @return
	 */
	public static List<SalaryStat> convertToSalaryStatList(List<SalaryDetailInfo> salaryDetailInfoList, List<Double> salaryWeightList) {
		List<SalaryStat> salaryStatList = new ArrayList<SalaryStat>();
		if (salaryDetailInfoList != null) {
			for (SalaryDetailInfo salaryDetailInfo : salaryDetailInfoList) {
				SalaryStat salaryStat = new SalaryStat();
				salaryStat.setGrad_year(salaryDetailInfo.getYearAfterGraduate());
				int graduateYear = salaryDetailInfo.getYearAfterGraduate();
				if(salaryWeightList != null) {
					if (graduateYear >= salaryWeightList.size()) {
						continue;
					}
					salaryStat.setSalary(salaryDetailInfo.getSalary() * salaryWeightList.get(graduateYear));
				} else {
					if(graduateYear > Constants.SCH_STATS_SALARY_SHOW_YEAR) {
						continue;
					}
					salaryStat.setSalary(salaryDetailInfo.getSalary());
					
					
				}
				salaryStat.setSample_count(salaryDetailInfo.getSampleCount());
				salaryStatList.add(salaryStat);
			}
			// 顺序排序
			Collections.sort(salaryStatList);
		}
		return salaryStatList;
	}

	/**
	 * 转换为预测薪酬信息列表
	 * @param predictSalaryInfoList 预测薪酬信息列表
	 * @return
	 */
	public static List<PredictSalaryStat> convertToPredictSalaryStat(List<PredictSalaryInfo> predictSalaryInfoList, List<Double> predictSalaryWeightList) {
		List<PredictSalaryStat> predictSalaryStatList = new ArrayList<PredictSalaryStat>();
		if (predictSalaryInfoList != null) {
			for (int i = 0; i < predictSalaryInfoList.size(); i++) { 
				PredictSalaryInfo predictSalaryInfo = predictSalaryInfoList.get(i);
				PredictSalaryStat predictSalaryStat = new PredictSalaryStat();
				predictSalaryStat.setGrad_year(predictSalaryInfo.getYearAfterGraduation());
				int yearAfterGraduation = predictSalaryInfo.getYearAfterGraduation();
				if (yearAfterGraduation >= predictSalaryWeightList.size()) {
					continue;
				}
				predictSalaryStat.setSalary(predictSalaryInfo.getSalary() * predictSalaryWeightList.get(yearAfterGraduation));// 乘以权重
				predictSalaryStat.setVirutal_flag(predictSalaryInfo.isVirtualSalary());
				predictSalaryStatList.add(predictSalaryStat);
			}
			
			Collections.sort(predictSalaryStatList);
		}
		return predictSalaryStatList;
	}
	
	/**
	 * 转换为职能分布情况
	 * @param jobFunctionInfoList 职能信息列表
	 * @return
	 */
	public static List<JobFunctionDist> convertToJobFunctionDist(List<JobFunctionInfo> jobFunctionInfoList) {
		List<JobFunctionDist> jobFunctionDistList = new ArrayList<JobFunctionDist>();
		if (jobFunctionInfoList != null) {
			for (JobFunctionInfo jobFunctionInfo : jobFunctionInfoList) {
				if ("老板".equals(jobFunctionInfo.getJobFunctionName())
						|| "实习".equals(jobFunctionInfo.getJobFunctionName())) {
					continue;
				}
				JobFunctionDist jobFunctionDist = new JobFunctionDist();
				jobFunctionDist.setZhineng(jobFunctionInfo.getJobFunctionName());
				jobFunctionDist.setSample_count(jobFunctionInfo.getSampleCount());
				jobFunctionDist.setRatio(jobFunctionInfo.getRatio());
				List<String> positionList = new ArrayList<String>();
				if (jobFunctionInfo.getJobPositionInfoList() != null) {
					for (JobPositionInfo jobPositionInfo : jobFunctionInfo.getJobPositionInfoList()) {
						positionList.add(jobPositionInfo.getPositionName());
					}
				}
				jobFunctionDist.setPosition_list(positionList);
				List<String> industryList = new ArrayList<String>();
				if (jobFunctionInfo.getIndustryDistInfoList() != null) {
					for (IndustryDistInfo industryDistInfo : jobFunctionInfo.getIndustryDistInfoList()) {
						industryList.add(industryDistInfo.getIndustryId());
					}
				}
				jobFunctionDist.setIndustry_list(industryList);
				jobFunctionDistList.add(jobFunctionDist);
			}
			
			Collections.sort(jobFunctionDistList);
		}
		return jobFunctionDistList;
	}

	/**
	 * 获取薪酬指数排名描述
	 * @param salaryFactorRank 薪酬指数排名
	 * @return
	 */
	public static String getSalaryFactorRankDesc(double salaryFactorRank) {
		// u'很低',u'较低',u'一般',u'较高',u'很高'
		if (salaryFactorRank < 0) {
			return "未知";
		} else if (salaryFactorRank < 10) {
			return "很低";
		} else if (salaryFactorRank < 40) {
			return "较低";
		} else if (salaryFactorRank < 60) {
			return "一般";
		} else if (salaryFactorRank < 90) {
			return "较高";
		} else if (salaryFactorRank < 101) {
			return "很高";
		} else {
			return "未知";
		}
	}
	
	/**
	 * 获取专业行业分布描述
	 * @param industryGiniRank
	 * @return
	 */
	public static String getMajorIndustryDistDesc(double industryGiniRank) {
		// u'很低',u'较低',u'一般',u'较高',u'很高'
		if (industryGiniRank < 0) {
			return "未知";
		} else if (industryGiniRank < 10) {
			return "很广";
		} else if (industryGiniRank < 40) {
			return "较广";
		} else if (industryGiniRank < 60) {
			return "适中";
		} else if (industryGiniRank < 90) {
			return "较集中";
		} else if (industryGiniRank < 101) {
			return "很集中";
		} else {
			return "未知";
		}
	}
	
	/**
	 * 获取薪酬指数排名
	 * @param salaryFactorRank 薪酬指数排名值
	 * @return
	 */
	public static int getSalaryFactorRank(double salaryFactorRank) {
		if (salaryFactorRank <= 0) {
			return 0;
		}
		
		double remainSalaryFactorRank = 100 - salaryFactorRank;
		int remainSalaryFactorRankIntValue = (int) (ParameterUtils.roundDouble(Math.max(remainSalaryFactorRank, 1.0), 2));
		return 100 - remainSalaryFactorRankIntValue;
	}
}
