package com.ipin.service.rest.beans;

import java.io.Serializable;

/**
 * SchRankingList. 学校排行榜.
 * 
 * @author zhongyongsheng
 *
 */
public class SchRankingList implements Serializable {

	private static final long serialVersionUID = -7840423909348198930L;

	private int rank_index;
	private String sch_id;
	private String sch_name;
	private String sch_type;
	private String location;
	private String degrees;

	public int getRank_index() {
		return rank_index;
	}

	public void setRank_index(int rank_index) {
		this.rank_index = rank_index;
	}

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

	public String getSch_type() {
		return sch_type;
	}

	public void setSch_type(String sch_type) {
		this.sch_type = sch_type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDegrees() {
		return degrees;
	}

	public void setDegrees(String degrees) {
		this.degrees = degrees;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((degrees == null) ? 0 : degrees.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + rank_index;
		result = prime * result + ((sch_id == null) ? 0 : sch_id.hashCode());
		result = prime * result + ((sch_name == null) ? 0 : sch_name.hashCode());
		result = prime * result + ((sch_type == null) ? 0 : sch_type.hashCode());
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
		SchRankingList other = (SchRankingList) obj;
		if (degrees == null) {
			if (other.degrees != null)
				return false;
		} else if (!degrees.equals(other.degrees))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (rank_index != other.rank_index)
			return false;
		if (sch_id == null) {
			if (other.sch_id != null)
				return false;
		} else if (!sch_id.equals(other.sch_id))
			return false;
		if (sch_name == null) {
			if (other.sch_name != null)
				return false;
		} else if (!sch_name.equals(other.sch_name))
			return false;
		if (sch_type == null) {
			if (other.sch_type != null)
				return false;
		} else if (!sch_type.equals(other.sch_type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SchRankingList [rank_index=" + rank_index + ", sch_id=" + sch_id + ", sch_name=" + sch_name
				+ ", sch_type=" + sch_type + ", location=" + location + ", degrees=" + degrees + "]";
	}

}
