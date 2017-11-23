package com.ipin.service.rest.beans;

import java.io.Serializable;
import java.util.List;

/**
 * SchEnrollPlanListResult.
 * 学校招生计划列表结果.
 * 
 * @author zhongyongsheng
 *
 */
public class SchEnrollPlanListResult implements Serializable{

	private static final long serialVersionUID = 3880157307967141850L;
	
	private List<SchEnrollPlan> plans;

	public List<SchEnrollPlan> getPlans() {
		return plans;
	}

	public void setPlans(List<SchEnrollPlan> plans) {
		this.plans = plans;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((plans == null) ? 0 : plans.hashCode());
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
		SchEnrollPlanListResult other = (SchEnrollPlanListResult) obj;
		if (plans == null) {
			if (other.plans != null)
				return false;
		} else if (!plans.equals(other.plans))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SchEnrollPlanListResult [plans=" + plans + "]";
	}

}
