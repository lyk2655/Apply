package com.ipin.service.rest.beans;

import java.io.Serializable;
import java.util.List;

/**
 * SchoolDetailResult. 
 * 学校详情结果.
 * 
 * @author zhongyongsheng
 *
 */
public class SchDetailResult implements Serializable {

	private static final long serialVersionUID = 9148414049001088718L;

	private String sch_id;
	private String sch_name;
	private List<String> sch_old_name;
	private String sch_address;
	private List<String> sch_alias_names;
	private String sch_city_id;
	private String sch_competentdept;
	private List<String> sch_diplomas;
	private List<String> sch_diplomas_view;
	private List<String> sch_grade_tag;
	private String sch_intro;
	private String sch_province_id;
	private List<String> sch_special_enrolls;
	private String sch_tel_num;
	private List<String> sch_type_names;
	private String sch_website;
	private int sample_count;
	private String total_grade_str;
	private double salary_factor_ratio;
	private double social_factor_ratio;
	private double city_ratio;
	private int salary_factor;
	private int salary_factor_rank;
	private int salary_factor_index;
	private List<IndustryDist> industry_dis;
	private String industry_status;
	private String top_worker_city;
	private double top_worker_city_ratio;
	private List<GenderDist> gender_dis;
	private String gender_status;
	private List<SalaryStat> salary_stats;
	private List<PredictSalaryStat> predict_salary_stats;
	private int salary_predict_show_type;
	private List<SalaryStat> country_salary_stats;
	private List<SalaryStat> province_salary_stats;
	private int in_country_salary_index;
	private int in_country_salary_total;
	private int in_province_salary_index;
	private int in_province_salary_total;
	private List<LocationDist> location_dis;

	public String getSch_id() {
		return sch_id;
	}

	public void setSch_id(String sch_id) {
		this.sch_id = sch_id;
	}

	public String getSch_name() {
		return sch_name;
	}

	public void setSch_name(String sch_name) {
		this.sch_name = sch_name;
	}

	public List<String> getSch_old_name() {
		return sch_old_name;
	}

	public void setSch_old_name(List<String> sch_old_name) {
		this.sch_old_name = sch_old_name;
	}

	public String getSch_address() {
		return sch_address;
	}

	public void setSch_address(String sch_address) {
		this.sch_address = sch_address;
	}

	public List<String> getSch_alias_names() {
		return sch_alias_names;
	}

	public void setSch_alias_names(List<String> sch_alias_names) {
		this.sch_alias_names = sch_alias_names;
	}

	public String getSch_city_id() {
		return sch_city_id;
	}

	public void setSch_city_id(String sch_city_id) {
		this.sch_city_id = sch_city_id;
	}

	public String getSch_competentdept() {
		return sch_competentdept;
	}

	public void setSch_competentdept(String sch_competentdept) {
		this.sch_competentdept = sch_competentdept;
	}

	public List<String> getSch_diplomas() {
		return sch_diplomas;
	}

	public void setSch_diplomas(List<String> sch_diplomas) {
		this.sch_diplomas = sch_diplomas;
	}

	public List<String> getSch_diplomas_view() {
		return sch_diplomas_view;
	}

	public void setSch_diplomas_view(List<String> sch_diplomas_view) {
		this.sch_diplomas_view = sch_diplomas_view;
	}

	public List<String> getSch_grade_tag() {
		return sch_grade_tag;
	}

	public void setSch_grade_tag(List<String> sch_grade_tag) {
		this.sch_grade_tag = sch_grade_tag;
	}

	public String getSch_intro() {
		return sch_intro;
	}

	public void setSch_intro(String sch_intro) {
		this.sch_intro = sch_intro;
	}

	public String getSch_province_id() {
		return sch_province_id;
	}

	public void setSch_province_id(String sch_province_id) {
		this.sch_province_id = sch_province_id;
	}

	public List<String> getSch_special_enrolls() {
		return sch_special_enrolls;
	}

	public void setSch_special_enrolls(List<String> sch_special_enrolls) {
		this.sch_special_enrolls = sch_special_enrolls;
	}

	public String getSch_tel_num() {
		return sch_tel_num;
	}

	public void setSch_tel_num(String sch_tel_num) {
		this.sch_tel_num = sch_tel_num;
	}

	public List<String> getSch_type_names() {
		return sch_type_names;
	}

	public void setSch_type_names(List<String> sch_type_names) {
		this.sch_type_names = sch_type_names;
	}

	public String getSch_website() {
		return sch_website;
	}

	public void setSch_website(String sch_website) {
		this.sch_website = sch_website;
	}

	public int getSample_count() {
		return sample_count;
	}

	public void setSample_count(int sample_count) {
		this.sample_count = sample_count;
	}

	public String getTotal_grade_str() {
		return total_grade_str;
	}

	public void setTotal_grade_str(String total_grade_str) {
		this.total_grade_str = total_grade_str;
	}

	public double getSalary_factor_ratio() {
		return salary_factor_ratio;
	}

	public void setSalary_factor_ratio(double salary_factor_ratio) {
		this.salary_factor_ratio = salary_factor_ratio;
	}

	public double getSocial_factor_ratio() {
		return social_factor_ratio;
	}

	public void setSocial_factor_ratio(double social_factor_ratio) {
		this.social_factor_ratio = social_factor_ratio;
	}

	public double getCity_ratio() {
		return city_ratio;
	}

	public void setCity_ratio(double city_ratio) {
		this.city_ratio = city_ratio;
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

	public int getSalary_factor_index() {
		return salary_factor_index;
	}

	public void setSalary_factor_index(int salary_factor_index) {
		this.salary_factor_index = salary_factor_index;
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

	public List<SalaryStat> getProvince_salary_stats() {
		return province_salary_stats;
	}

	public void setProvince_salary_stats(List<SalaryStat> province_salary_stats) {
		this.province_salary_stats = province_salary_stats;
	}

	public int getIn_country_salary_index() {
		return in_country_salary_index;
	}

	public void setIn_country_salary_index(int in_country_salary_index) {
		this.in_country_salary_index = in_country_salary_index;
	}

	public int getIn_country_salary_total() {
		return in_country_salary_total;
	}

	public void setIn_country_salary_total(int in_country_salary_total) {
		this.in_country_salary_total = in_country_salary_total;
	}

	public int getIn_province_salary_index() {
		return in_province_salary_index;
	}

	public void setIn_province_salary_index(int in_province_salary_index) {
		this.in_province_salary_index = in_province_salary_index;
	}

	public int getIn_province_salary_total() {
		return in_province_salary_total;
	}

	public void setIn_province_salary_total(int in_province_salary_total) {
		this.in_province_salary_total = in_province_salary_total;
	}

	public List<LocationDist> getLocation_dis() {
		return location_dis;
	}

	public void setLocation_dis(List<LocationDist> location_dis) {
		this.location_dis = location_dis;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(city_ratio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((country_salary_stats == null) ? 0 : country_salary_stats.hashCode());
		result = prime * result + ((gender_dis == null) ? 0 : gender_dis.hashCode());
		result = prime * result + ((gender_status == null) ? 0 : gender_status.hashCode());
		result = prime * result + in_country_salary_index;
		result = prime * result + in_country_salary_total;
		result = prime * result + in_province_salary_index;
		result = prime * result + in_province_salary_total;
		result = prime * result + ((industry_dis == null) ? 0 : industry_dis.hashCode());
		result = prime * result + ((industry_status == null) ? 0 : industry_status.hashCode());
		result = prime * result + ((location_dis == null) ? 0 : location_dis.hashCode());
		result = prime * result + ((predict_salary_stats == null) ? 0 : predict_salary_stats.hashCode());
		result = prime * result + ((province_salary_stats == null) ? 0 : province_salary_stats.hashCode());
		result = prime * result + salary_factor;
		result = prime * result + salary_factor_index;
		result = prime * result + salary_factor_rank;
		temp = Double.doubleToLongBits(salary_factor_ratio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + salary_predict_show_type;
		result = prime * result + ((salary_stats == null) ? 0 : salary_stats.hashCode());
		result = prime * result + sample_count;
		result = prime * result + ((sch_address == null) ? 0 : sch_address.hashCode());
		result = prime * result + ((sch_alias_names == null) ? 0 : sch_alias_names.hashCode());
		result = prime * result + ((sch_city_id == null) ? 0 : sch_city_id.hashCode());
		result = prime * result + ((sch_competentdept == null) ? 0 : sch_competentdept.hashCode());
		result = prime * result + ((sch_diplomas == null) ? 0 : sch_diplomas.hashCode());
		result = prime * result + ((sch_diplomas_view == null) ? 0 : sch_diplomas_view.hashCode());
		result = prime * result + ((sch_grade_tag == null) ? 0 : sch_grade_tag.hashCode());
		result = prime * result + ((sch_id == null) ? 0 : sch_id.hashCode());
		result = prime * result + ((sch_intro == null) ? 0 : sch_intro.hashCode());
		result = prime * result + ((sch_name == null) ? 0 : sch_name.hashCode());
		result = prime * result + ((sch_old_name == null) ? 0 : sch_old_name.hashCode());
		result = prime * result + ((sch_province_id == null) ? 0 : sch_province_id.hashCode());
		result = prime * result + ((sch_special_enrolls == null) ? 0 : sch_special_enrolls.hashCode());
		result = prime * result + ((sch_tel_num == null) ? 0 : sch_tel_num.hashCode());
		result = prime * result + ((sch_type_names == null) ? 0 : sch_type_names.hashCode());
		result = prime * result + ((sch_website == null) ? 0 : sch_website.hashCode());
		temp = Double.doubleToLongBits(social_factor_ratio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((top_worker_city == null) ? 0 : top_worker_city.hashCode());
		temp = Double.doubleToLongBits(top_worker_city_ratio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((total_grade_str == null) ? 0 : total_grade_str.hashCode());
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
		SchDetailResult other = (SchDetailResult) obj;
		if (Double.doubleToLongBits(city_ratio) != Double.doubleToLongBits(other.city_ratio))
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
		if (in_country_salary_total != other.in_country_salary_total)
			return false;
		if (in_province_salary_index != other.in_province_salary_index)
			return false;
		if (in_province_salary_total != other.in_province_salary_total)
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
		if (predict_salary_stats == null) {
			if (other.predict_salary_stats != null)
				return false;
		} else if (!predict_salary_stats.equals(other.predict_salary_stats))
			return false;
		if (province_salary_stats == null) {
			if (other.province_salary_stats != null)
				return false;
		} else if (!province_salary_stats.equals(other.province_salary_stats))
			return false;
		if (salary_factor != other.salary_factor)
			return false;
		if (salary_factor_index != other.salary_factor_index)
			return false;
		if (salary_factor_rank != other.salary_factor_rank)
			return false;
		if (Double.doubleToLongBits(salary_factor_ratio) != Double.doubleToLongBits(other.salary_factor_ratio))
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
		if (sch_address == null) {
			if (other.sch_address != null)
				return false;
		} else if (!sch_address.equals(other.sch_address))
			return false;
		if (sch_alias_names == null) {
			if (other.sch_alias_names != null)
				return false;
		} else if (!sch_alias_names.equals(other.sch_alias_names))
			return false;
		if (sch_city_id == null) {
			if (other.sch_city_id != null)
				return false;
		} else if (!sch_city_id.equals(other.sch_city_id))
			return false;
		if (sch_competentdept == null) {
			if (other.sch_competentdept != null)
				return false;
		} else if (!sch_competentdept.equals(other.sch_competentdept))
			return false;
		if (sch_diplomas == null) {
			if (other.sch_diplomas != null)
				return false;
		} else if (!sch_diplomas.equals(other.sch_diplomas))
			return false;
		if (sch_diplomas_view == null) {
			if (other.sch_diplomas_view != null)
				return false;
		} else if (!sch_diplomas_view.equals(other.sch_diplomas_view))
			return false;
		if (sch_grade_tag == null) {
			if (other.sch_grade_tag != null)
				return false;
		} else if (!sch_grade_tag.equals(other.sch_grade_tag))
			return false;
		if (sch_id == null) {
			if (other.sch_id != null)
				return false;
		} else if (!sch_id.equals(other.sch_id))
			return false;
		if (sch_intro == null) {
			if (other.sch_intro != null)
				return false;
		} else if (!sch_intro.equals(other.sch_intro))
			return false;
		if (sch_name == null) {
			if (other.sch_name != null)
				return false;
		} else if (!sch_name.equals(other.sch_name))
			return false;
		if (sch_old_name == null) {
			if (other.sch_old_name != null)
				return false;
		} else if (!sch_old_name.equals(other.sch_old_name))
			return false;
		if (sch_province_id == null) {
			if (other.sch_province_id != null)
				return false;
		} else if (!sch_province_id.equals(other.sch_province_id))
			return false;
		if (sch_special_enrolls == null) {
			if (other.sch_special_enrolls != null)
				return false;
		} else if (!sch_special_enrolls.equals(other.sch_special_enrolls))
			return false;
		if (sch_tel_num == null) {
			if (other.sch_tel_num != null)
				return false;
		} else if (!sch_tel_num.equals(other.sch_tel_num))
			return false;
		if (sch_type_names == null) {
			if (other.sch_type_names != null)
				return false;
		} else if (!sch_type_names.equals(other.sch_type_names))
			return false;
		if (sch_website == null) {
			if (other.sch_website != null)
				return false;
		} else if (!sch_website.equals(other.sch_website))
			return false;
		if (Double.doubleToLongBits(social_factor_ratio) != Double.doubleToLongBits(other.social_factor_ratio))
			return false;
		if (top_worker_city == null) {
			if (other.top_worker_city != null)
				return false;
		} else if (!top_worker_city.equals(other.top_worker_city))
			return false;
		if (Double.doubleToLongBits(top_worker_city_ratio) != Double.doubleToLongBits(other.top_worker_city_ratio))
			return false;
		if (total_grade_str == null) {
			if (other.total_grade_str != null)
				return false;
		} else if (!total_grade_str.equals(other.total_grade_str))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SchoolDetailResult [sch_id=" + sch_id + ", sch_name=" + sch_name + ", sch_old_name=" + sch_old_name
				+ ", sch_address=" + sch_address + ", sch_alias_names=" + sch_alias_names + ", sch_city_id="
				+ sch_city_id + ", sch_competentdept=" + sch_competentdept + ", sch_diplomas=" + sch_diplomas
				+ ", sch_diplomas_view=" + sch_diplomas_view + ", sch_grade_tag=" + sch_grade_tag + ", sch_intro="
				+ sch_intro + ", sch_province_id=" + sch_province_id + ", sch_special_enrolls=" + sch_special_enrolls
				+ ", sch_tel_num=" + sch_tel_num + ", sch_type_names=" + sch_type_names + ", sch_website=" + sch_website
				+ ", sample_count=" + sample_count + ", total_grade_str=" + total_grade_str + ", salary_factor_ratio="
				+ salary_factor_ratio + ", social_factor_ratio=" + social_factor_ratio + ", city_ratio=" + city_ratio
				+ ", salary_factor=" + salary_factor + ", salary_factor_rank=" + salary_factor_rank
				+ ", salary_factor_index=" + salary_factor_index + ", industry_dis=" + industry_dis
				+ ", industry_status=" + industry_status + ", top_worker_city=" + top_worker_city
				+ ", top_worker_city_ratio=" + top_worker_city_ratio + ", gender_dis=" + gender_dis + ", gender_status="
				+ gender_status + ", salary_stats=" + salary_stats + ", predict_salary_stats=" + predict_salary_stats
				+ ", salary_predict_show_type=" + salary_predict_show_type + ", country_salary_stats="
				+ country_salary_stats + ", province_salary_stats=" + province_salary_stats
				+ ", in_country_salary_index=" + in_country_salary_index + ", in_country_salary_total="
				+ in_country_salary_total + ", in_province_salary_index=" + in_province_salary_index
				+ ", in_province_salary_total=" + in_province_salary_total + ", location_dis=" + location_dis + "]";
	}

}
