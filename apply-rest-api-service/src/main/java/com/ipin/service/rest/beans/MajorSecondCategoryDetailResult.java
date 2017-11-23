package com.ipin.service.rest.beans;

import java.io.Serializable;
import java.util.List;

/**
 * MajorSecondCateDetailResult. 专业二级目录详情结果.
 * 
 * @author zhongyongsheng
 *
 */
public class MajorSecondCategoryDetailResult implements Serializable {

	private static final long serialVersionUID = 2382214369660719888L;

	private String major_second_category;
	private String major_category;
	private List<SimpleMajorInfo> major_list;
	private int sample_count;
	private List<IndustryDist> industry_dis;
	private String industry_status;
	private String top_worker_city;
	private double top_worker_city_ratio;
	private List<GenderDist> gender_dis;
	private String gender_status;
	private int salary_factor;
	private int salary_factor_rank;
	private List<SalaryStat> salary_stats;
	private List<PredictSalaryStat> predict_salary_stats;
	private int salary_predict_show_type;
	private List<SalaryStat> country_salary_stats;
	private int in_country_salary_index;
	private List<LocationDist> location_dis;
	private List<JobFunctionDist> zhineng_dis;
	private List<CompanyDist> company_dis;

	public String getMajor_second_category() {
		return major_second_category;
	}

	public void setMajor_second_category(String major_second_category) {
		this.major_second_category = major_second_category;
	}

	public String getMajor_category() {
		return major_category;
	}

	public void setMajor_category(String major_category) {
		this.major_category = major_category;
	}

	public List<SimpleMajorInfo> getMajor_list() {
		return major_list;
	}

	public void setMajor_list(List<SimpleMajorInfo> major_list) {
		this.major_list = major_list;
	}

	public int getSample_count() {
		return sample_count;
	}

	public void setSample_count(int sample_count) {
		this.sample_count = sample_count;
	}

	public List<IndustryDist> getIndustry_dis() {
		return industry_dis;
	}

	public void setIndustry_dis(List<IndustryDist> industry_dis) {
		this.industry_dis = industry_dis;
	}

	public String getIndustry_status() {
		return industry_status;
	}

	public void setIndustry_status(String industry_status) {
		this.industry_status = industry_status;
	}

	public String getTop_worker_city() {
		return top_worker_city;
	}

	public void setTop_worker_city(String top_worker_city) {
		this.top_worker_city = top_worker_city;
	}

	public double getTop_worker_city_ratio() {
		return top_worker_city_ratio;
	}

	public void setTop_worker_city_ratio(double top_worker_city_ratio) {
		this.top_worker_city_ratio = top_worker_city_ratio;
	}

	public List<GenderDist> getGender_dis() {
		return gender_dis;
	}

	public void setGender_dis(List<GenderDist> gender_dis) {
		this.gender_dis = gender_dis;
	}

	public String getGender_status() {
		return gender_status;
	}

	public void setGender_status(String gender_status) {
		this.gender_status = gender_status;
	}

	public int getSalary_factor() {
		return salary_factor;
	}

	public void setSalary_factor(int salary_factor) {
		this.salary_factor = salary_factor;
	}

	public int getSalary_factor_rank() {
		return salary_factor_rank;
	}

	public void setSalary_factor_rank(int salary_factor_rank) {
		this.salary_factor_rank = salary_factor_rank;
	}

	public List<SalaryStat> getSalary_stats() {
		return salary_stats;
	}

	public void setSalary_stats(List<SalaryStat> salary_stats) {
		this.salary_stats = salary_stats;
	}

	public List<PredictSalaryStat> getPredict_salary_stats() {
		return predict_salary_stats;
	}

	public void setPredict_salary_stats(List<PredictSalaryStat> predict_salary_stats) {
		this.predict_salary_stats = predict_salary_stats;
	}

	public int getSalary_predict_show_type() {
		return salary_predict_show_type;
	}

	public void setSalary_predict_show_type(int salary_predict_show_type) {
		this.salary_predict_show_type = salary_predict_show_type;
	}

	public List<SalaryStat> getCountry_salary_stats() {
		return country_salary_stats;
	}

	public void setCountry_salary_stats(List<SalaryStat> country_salary_stats) {
		this.country_salary_stats = country_salary_stats;
	}

	public int getIn_country_salary_index() {
		return in_country_salary_index;
	}

	public void setIn_country_salary_index(int in_country_salary_index) {
		this.in_country_salary_index = in_country_salary_index;
	}

	public List<LocationDist> getLocation_dis() {
		return location_dis;
	}

	public void setLocation_dis(List<LocationDist> location_dis) {
		this.location_dis = location_dis;
	}

	public List<JobFunctionDist> getZhineng_dis() {
		return zhineng_dis;
	}

	public void setZhineng_dis(List<JobFunctionDist> zhineng_dis) {
		this.zhineng_dis = zhineng_dis;
	}

	public List<CompanyDist> getCompany_dis() {
		return company_dis;
	}

	public void setCompany_dis(List<CompanyDist> company_dis) {
		this.company_dis = company_dis;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company_dis == null) ? 0 : company_dis.hashCode());
		result = prime * result + ((country_salary_stats == null) ? 0 : country_salary_stats.hashCode());
		result = prime * result + ((gender_dis == null) ? 0 : gender_dis.hashCode());
		result = prime * result + ((gender_status == null) ? 0 : gender_status.hashCode());
		result = prime * result + in_country_salary_index;
		result = prime * result + ((industry_dis == null) ? 0 : industry_dis.hashCode());
		result = prime * result + ((industry_status == null) ? 0 : industry_status.hashCode());
		result = prime * result + ((location_dis == null) ? 0 : location_dis.hashCode());
		result = prime * result + ((major_category == null) ? 0 : major_category.hashCode());
		result = prime * result + ((major_list == null) ? 0 : major_list.hashCode());
		result = prime * result + ((major_second_category == null) ? 0 : major_second_category.hashCode());
		result = prime * result + ((predict_salary_stats == null) ? 0 : predict_salary_stats.hashCode());
		result = prime * result + salary_factor;
		result = prime * result + salary_factor_rank;
		result = prime * result + salary_predict_show_type;
		result = prime * result + ((salary_stats == null) ? 0 : salary_stats.hashCode());
		result = prime * result + sample_count;
		result = prime * result + ((top_worker_city == null) ? 0 : top_worker_city.hashCode());
		long temp;
		temp = Double.doubleToLongBits(top_worker_city_ratio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((zhineng_dis == null) ? 0 : zhineng_dis.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MajorSecondCategoryDetailResult other = (MajorSecondCategoryDetailResult) obj;
		if (company_dis == null) {
			if (other.company_dis != null)
				return false;
		} else if (!company_dis.equals(other.company_dis))
			return false;
		if (country_salary_stats == null) {
			if (other.country_salary_stats != null)
				return false;
		} else if (!country_salary_stats.equals(other.country_salary_stats))
			return false;
		if (gender_dis == null) {
			if (other.gender_dis != null)
				return false;
		} else if (!gender_dis.equals(other.gender_dis))
			return false;
		if (gender_status == null) {
			if (other.gender_status != null)
				return false;
		} else if (!gender_status.equals(other.gender_status))
			return false;
		if (in_country_salary_index != other.in_country_salary_index)
			return false;
		if (industry_dis == null) {
			if (other.industry_dis != null)
				return false;
		} else if (!industry_dis.equals(other.industry_dis))
			return false;
		if (industry_status == null) {
			if (other.industry_status != null)
				return false;
		} else if (!industry_status.equals(other.industry_status))
			return false;
		if (location_dis == null) {
			if (other.location_dis != null)
				return false;
		} else if (!location_dis.equals(other.location_dis))
			return false;
		if (major_category == null) {
			if (other.major_category != null)
				return false;
		} else if (!major_category.equals(other.major_category))
			return false;
		if (major_list == null) {
			if (other.major_list != null)
				return false;
		} else if (!major_list.equals(other.major_list))
			return false;
		if (major_second_category == null) {
			if (other.major_second_category != null)
				return false;
		} else if (!major_second_category.equals(other.major_second_category))
			return false;
		if (predict_salary_stats == null) {
			if (other.predict_salary_stats != null)
				return false;
		} else if (!predict_salary_stats.equals(other.predict_salary_stats))
			return false;
		if (salary_factor != other.salary_factor)
			return false;
		if (salary_factor_rank != other.salary_factor_rank)
			return false;
		if (salary_predict_show_type != other.salary_predict_show_type)
			return false;
		if (salary_stats == null) {
			if (other.salary_stats != null)
				return false;
		} else if (!salary_stats.equals(other.salary_stats))
			return false;
		if (sample_count != other.sample_count)
			return false;
		if (top_worker_city == null) {
			if (other.top_worker_city != null)
				return false;
		} else if (!top_worker_city.equals(other.top_worker_city))
			return false;
		if (Double.doubleToLongBits(top_worker_city_ratio) != Double.doubleToLongBits(other.top_worker_city_ratio))
			return false;
		if (zhineng_dis == null) {
			if (other.zhineng_dis != null)
				return false;
		} else if (!zhineng_dis.equals(other.zhineng_dis))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MajorSecondCategoryDetailResult [major_second_category=" + major_second_category + ", major_category="
				+ major_category + ", major_list=" + major_list + ", sample_count=" + sample_count + ", industry_dis="
				+ industry_dis + ", industry_status=" + industry_status + ", top_worker_city=" + top_worker_city
				+ ", top_worker_city_ratio=" + top_worker_city_ratio + ", gender_dis=" + gender_dis + ", gender_status="
				+ gender_status + ", salary_factor=" + salary_factor + ", salary_factor_rank=" + salary_factor_rank
				+ ", salary_stats=" + salary_stats + ", predict_salary_stats=" + predict_salary_stats
				+ ", salary_predict_show_type=" + salary_predict_show_type + ", country_salary_stats="
				+ country_salary_stats + ", in_country_salary_index=" + in_country_salary_index + ", location_dis="
				+ location_dis + ", zhineng_dis=" + zhineng_dis + ", company_dis=" + company_dis + "]";
	}


}
