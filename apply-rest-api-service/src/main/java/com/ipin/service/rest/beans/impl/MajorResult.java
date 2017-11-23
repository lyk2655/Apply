package com.ipin.service.rest.beans.impl;

import com.ipin.service.rest.beans.Major;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * Created by longman on 1/12/16.
 */
public class MajorResult implements Serializable {
    private static final long serialVersionUID = -8039686696076337044L;

    private List<Major> majors;

    public List<Major> getMajors() {
        return majors;
    }

    public void setMajors(List<Major> majors) {
        this.majors = majors;
    }
}
 