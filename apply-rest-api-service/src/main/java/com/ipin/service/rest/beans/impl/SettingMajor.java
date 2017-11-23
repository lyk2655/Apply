package com.ipin.service.rest.beans.impl;

import com.ipin.service.rest.beans.Major;

import java.io.Serializable;

/**
 * Created by longman on 1/12/16.
 */
public class SettingMajor extends Major implements Serializable {
    private static final long serialVersionUID = -8039686696076337053L;

    private Boolean majorHasStats;

    public Boolean getMajorHasStats() {
        return majorHasStats;
    }

    public void setMajorHasStats(Boolean majorHasStats) {
        this.majorHasStats = majorHasStats;
    }
}
