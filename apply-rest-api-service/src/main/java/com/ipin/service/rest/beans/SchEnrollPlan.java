package com.ipin.service.rest.beans;

import java.io.Serializable;

/**
 * SchEnrollPlan.
 * 学校招生计划
 * 
 * @author zhongyongsheng
 *
 */
public class SchEnrollPlan implements Serializable{

	private static final long serialVersionUID = -8477991325082528640L;
	
	private int rank_index;
	private String major_name;
	private String major_id;
	private boolean major_has_stats;
	private String wenli;
	private String zhaosheng_batch;
	private int zhaosheng_count;
	private String zhaosheng_xuezhi;
	private String plan_type;

	public int getRank_index() {
		return rank_index;
	}

	public void setRank_index(int rank_index) {
		this.rank_index = rank_index;
	}

	public String getMajor_name() {
		return major_name;
	}

	public void setMajor_name(String major_name) {
		this.major_name = major_name;
	}

	public String getMajor_id() {
		return major_id;
	}

	public void setMajor_id(String major_id) {
		this.major_id = major_id;
	}

	public boolean isMajor_has_stats() {
		return major_has_stats;
	}

	public void setMajor_has_stats(boolean major_has_stats) {
		this.major_has_stats = major_has_stats;
	}

	public String getWenli() {
		return wenli;
	}

	public void setWenli(String wenli) {
		this.wenli = wenli;
	}

	public String getZhaosheng_batch() {
		return zhaosheng_batch;
	}

	public void setZhaosheng_batch(String zhaosheng_batch) {
		this.zhaosheng_batch = zhaosheng_batch;
	}

	public int getZhaosheng_count() {
		return zhaosheng_count;
	}

	public void setZhaosheng_count(int zhaosheng_count) {
		this.zhaosheng_count = zhaosheng_count;
	}

	public String getZhaosheng_xuezhi() {
		return zhaosheng_xuezhi;
	}

	public void setZhaosheng_xuezhi(String zhaosheng_xuezhi) {
		this.zhaosheng_xuezhi = zhaosheng_xuezhi;
	}

	public String getPlan_type() {
		return plan_type;
	}

	public void setPlan_type(String plan_type) {
		this.plan_type = plan_type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (major_has_stats ? 1231 : 1237);
		result = prime * result + ((major_id == null) ? 0 : major_id.hashCode());
		result = prime * result + ((major_name == null) ? 0 : major_name.hashCode());
		result = prime * result + ((plan_type == null) ? 0 : plan_type.hashCode());
		result = prime * result + rank_index;
		result = prime * result + ((wenli == null) ? 0 : wenli.hashCode());
		result = prime * result + ((zhaosheng_batch == null) ? 0 : zhaosheng_batch.hashCode());
		result = prime * result + zhaosheng_count;
		result = prime * result + ((zhaosheng_xuezhi == null) ? 0 : zhaosheng_xuezhi.hashCode());
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
		SchEnrollPlan other = (SchEnrollPlan) obj;
		if (major_has_stats != other.major_has_stats)
			return false;
		if (major_id == null) {
			if (other.major_id != null)
				return false;
		} else if (!major_id.equals(other.major_id))
			return false;
		if (major_name == null) {
			if (other.major_name != null)
				return false;
		} else if (!major_name.equals(other.major_name))
			return false;
		if (plan_type == null) {
			if (other.plan_type != null)
				return false;
		} else if (!plan_type.equals(other.plan_type))
			return false;
		if (rank_index != other.rank_index)
			return false;
		if (wenli == null) {
			if (other.wenli != null)
				return false;
		} else if (!wenli.equals(other.wenli))
			return false;
		if (zhaosheng_batch == null) {
			if (other.zhaosheng_batch != null)
				return false;
		} else if (!zhaosheng_batch.equals(other.zhaosheng_batch))
			return false;
		if (zhaosheng_count != other.zhaosheng_count)
			return false;
		if (zhaosheng_xuezhi == null) {
			if (other.zhaosheng_xuezhi != null)
				return false;
		} else if (!zhaosheng_xuezhi.equals(other.zhaosheng_xuezhi))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SchEnrollPlan [rank_index=" + rank_index + ", major_name=" + major_name + ", major_id=" + major_id
				+ ", major_has_stats=" + major_has_stats + ", wenli=" + wenli + ", zhaosheng_batch=" + zhaosheng_batch
				+ ", zhaosheng_count=" + zhaosheng_count + ", zhaosheng_xuezhi=" + zhaosheng_xuezhi + ", plan_type="
				+ plan_type + "]";
	}


}
