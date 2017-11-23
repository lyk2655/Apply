package com.ipin.service.rest.beans;

import java.io.Serializable;

/**
 * Location.
 * 地区.
 * 
 * @author zhongyongsheng
 *
 */
public class Location implements Serializable{

	private static final long serialVersionUID = -2312713582764814402L;

	private String name;
	private String loc_id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoc_id() {
		return loc_id;
	}

	public void setLoc_id(String loc_id) {
		this.loc_id = loc_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((loc_id == null) ? 0 : loc_id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Location other = (Location) obj;
		if (loc_id == null) {
			if (other.loc_id != null)
				return false;
		} else if (!loc_id.equals(other.loc_id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Location [name=" + name + ", loc_id=" + loc_id + "]";
	}
	
}
