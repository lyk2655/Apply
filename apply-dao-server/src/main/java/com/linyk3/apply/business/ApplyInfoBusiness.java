package com.linyk3.apply.business;

import java.util.List;

import com.linyk3.thrift.apply.ApplicationInfo;
import com.linyk3.thrift.apply.CompanyInfo;
import com.linyk3.thrift.apply.EmployInfo;
import com.linyk3.thrift.apply.ExaminationInfo;
import com.linyk3.thrift.apply.InterviewInfo;
import com.linyk3.thrift.apply.PersonalInfo;
import com.linyk3.thrift.apply.TeachinInfo;
import com.linyk3.thrift.apply.UserInfo;
import com.linyk3.thrift.apply.params.FindApplicationInfoParams;
import com.linyk3.thrift.apply.params.FindCompanyInfoParams;
import com.linyk3.thrift.apply.params.FindEmployInfoParams;
import com.linyk3.thrift.apply.params.FindExaminationInfoParams;
import com.linyk3.thrift.apply.params.FindInterviewInfoParams;
import com.linyk3.thrift.apply.params.FindPersonalInfoParams;
import com.linyk3.thrift.apply.params.FindTeachinInfoParams;
import com.linyk3.thrift.apply.params.FindUserInfoParams;
import com.linyk3.thrift.apply.params.ListApplicationInfoParams;
import com.linyk3.thrift.apply.params.ListCompanyInfoParams;
import com.linyk3.thrift.apply.params.ListEmployInfoParams;
import com.linyk3.thrift.apply.params.ListExaminationInfoParams;
import com.linyk3.thrift.apply.params.ListInterviewInfoParams;
import com.linyk3.thrift.apply.params.ListPersonalInfoParams;
import com.linyk3.thrift.apply.params.ListTeachinInfoParams;
import com.linyk3.thrift.apply.params.ListUserInfoParams;
/**
 * 
 * @author linyk3
 *
 */
public interface ApplyInfoBusiness {

	UserInfo findUserInfo(FindUserInfoParams findUserInfoParams);

	List<UserInfo> listUserInfo(ListUserInfoParams listUserInfoParams);

	PersonalInfo findPersonalInfo(FindPersonalInfoParams findPersonalInfoParams);

	List<PersonalInfo> listPersonalInfo(ListPersonalInfoParams listPersonalInfoParams);

	CompanyInfo findCompanyInfo(FindCompanyInfoParams findCompanyInfoParams);

	List<CompanyInfo> listCompanyInfo(ListCompanyInfoParams listCompanyInfoParams);

	EmployInfo findEmployInfo(FindEmployInfoParams findEmployInfoParams);

	List<EmployInfo> listEmployInfo(ListEmployInfoParams listEmployInfoParams);

	ApplicationInfo findApplicationInfo(FindApplicationInfoParams findApplicationInfoParams);

	List<ApplicationInfo> listApplicationInfo(ListApplicationInfoParams listApplicationInfoParams);

	TeachinInfo findTeachinInfo(FindTeachinInfoParams findTeachinInfoParams);

	List<TeachinInfo> listTeachinInfo(ListTeachinInfoParams listTeachinInfoParams);

	ExaminationInfo findExaminationInfo(FindExaminationInfoParams findExaminationInfoParams);

	List<ExaminationInfo> listExaminationInfo(ListExaminationInfoParams listExaminationInfoParams);
	
	InterviewInfo findInterviewInfo(FindInterviewInfoParams findInterviewInfoParams);

	List<InterviewInfo> listInterviewInfo(ListInterviewInfoParams listInterviewInfoParams);

	PersonalInfo updatePersonal(PersonalInfo personalInfo);

	CompanyInfo updateCompanyInfo(CompanyInfo companyInfo);

	EmployInfo updateEmployInfo(EmployInfo employInfo);

	ApplicationInfo updateApplicationInfo(ApplicationInfo applicationInfo);

	TeachinInfo updateTeachinInfo(TeachinInfo teachinInfo);

	ExaminationInfo updateExaminationInfo(ExaminationInfo examinationInfo);

	InterviewInfo updateInterviewInfo(InterviewInfo interviewInfo);

	void deletePersonalInfo(String _id);

	void deleteCompanyInfo(String _id);

	void deleteEmployInfo(String _id);

	void deleteApplicationInfo(String _id);

	void deleteTeachinInfo(String _id);

	void deleteExaminationInfo(String _id);

	void deleteInterviewInfo(String _id);



}
