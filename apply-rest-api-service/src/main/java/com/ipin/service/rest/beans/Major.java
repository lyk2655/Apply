package com.ipin.service.rest.beans;

import java.io.Serializable;

/**
 * Created by longman on 1/12/16.
 */
public abstract class Major implements Serializable {
    private static final long serialVersionUID = -7039686696076337053L;

    private String majorName;
    private String majorId;
    private String majorCategory;
    private String majorSecondCategory;

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getMajorCategory() {
        return majorCategory;
    }

    public void setMajorCategory(String majorCategory) {
        this.majorCategory = majorCategory;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public String getMajorSecondCategory() {
        return majorSecondCategory;
    }

    public void setMajorSecondCategory(String majorSecondCategory) {
        this.majorSecondCategory = majorSecondCategory;
    }
}
