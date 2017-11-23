package com.ipin.service.rest.beans;

import java.io.Serializable;
import java.util.List;

/**
 * LocationListResult. 地区列表结果.
 * 
 * @author zhongyongsheng
 *
 */
public class LocationListResult implements Serializable {

	private static final long serialVersionUID = 346735142949254813L;

	private String level;
	private List<Location> items;

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public List<Location> getItems() {
		return items;
	}

	public void setItems(List<Location> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
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
		LocationListResult other = (LocationListResult) obj;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LocationListResult [level=" + level + ", items=" + items + "]";
	}

}
