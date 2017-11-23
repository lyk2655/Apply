package com.ipin.service.rest.service;

import java.text.ParseException;
import java.util.List;

import com.ipin.service.rest.beans.apply.ApplicationResult;
import com.ipin.service.rest.beans.apply.CompanyDetail;
import com.ipin.service.rest.beans.apply.CompanyResult;
import com.ipin.service.rest.beans.apply.ExaminationResult;
import com.ipin.service.rest.beans.apply.InterviewResult;
import com.ipin.service.rest.beans.apply.PlanResult;
import com.ipin.service.rest.beans.apply.TeachinResult;
import com.ipin.service.rest.beans.apply.UserResult;

public interface ApplyRestService {

	UserResult findUser(String name);
	
	List<UserResult> listUsers();

	PlanResult getPlanResult(String name, int dateFilter, String typeFilter, int statusFilter);

	CompanyResult getCompanyResult(String typeFilter, int statusFilter);

	ApplicationResult getApplicationResult(int dateFilter, int statusFilter);

	TeachinResult getTeachinResult(int dateFilter, int statusFilter);

	ExaminationResult getExaminationResult(int dateFilter, int statusFilter);

	InterviewResult getInterviewResult(int dateFilter, int statusFilter);

	void deletePlan(String name, String type, String _id);

	void changePlan(String type, String _id, String date, String companyName, int status, String address,
			String positions);

	void deleteCompany(String _id);

	void deleteApplication(String _id);

	void deleteTeachin(String _id);

	void deleteExamination(String _id);

	void deleteInterview(String _id);

	void changeCompany(String type, String _id, String name, String website, int status, String address,
			String positions);

	void changeApplication(String _id, String companyName, String beginDate, String endDate, String website, int status,
			String positions);

	void changeTeachin(String _id, String companyName, String date, String school, String address, String positions);

	void changeExamination(String _id, String companyName, String date, String school, String address, String positions,
			int status);
	
	void changeInterview(String _id, String companyName, String date, String school, String address, String positions,
			int status);

	CompanyDetail getCompanyDetail(String _id);

	void collect(String name, String _id, String type);

	void sendMail(String type, String _id, String date, String companyName, int status, String address,
			String positions, String subject, String sendTime, String marks, String userName, String email, String school, String website, String beginDate, String endDate);


}
