package com.linyk3.apply.dao;

import java.util.List;

import com.linyk3.apply.bean.Application;
import com.linyk3.apply.bean.Company;
import com.linyk3.apply.bean.Employ;
import com.linyk3.apply.bean.Examination;
import com.linyk3.apply.bean.Interview;
import com.linyk3.apply.bean.Personal;
import com.linyk3.apply.bean.Teachin;
import com.linyk3.apply.bean.User;
import com.linyk3.thrift.apply.PersonalInfo;
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

public interface ApplyDao {

	User findUserByName(FindUserInfoParams findUserInfoParams);

	List<User> listUserInfo(ListUserInfoParams listUserInfoParams);

	Personal findPersonal(FindPersonalInfoParams findPersonalInfoParams);

	List<Personal> listPersionalInfo(ListPersonalInfoParams listPersonalInfoParams);

	Company findCompanyInfo(FindCompanyInfoParams findCompanyInfoParams);

	List<Company> listCompanyInfo(ListCompanyInfoParams listCompanyInfoParams);

	Employ findEmployInfo(FindEmployInfoParams findEmployInfoParams);

	List<Employ> listEmployInfo(ListEmployInfoParams listEmployInfoParams);

	Application findApplicationInfo(FindApplicationInfoParams findApplicationInfoParams);

	List<Application> listApplicationInfo(ListApplicationInfoParams listApplicationInfoParams);

	Teachin findTeachinInfo(FindTeachinInfoParams findTeachinInfoParams);

	List<Teachin> listTeachinInfo(ListTeachinInfoParams listTeachinInfoParams);

	Examination findExaminationInfo(FindExaminationInfoParams findExaminationInfoParams);

	List<Examination> listExaminationInfo(ListExaminationInfoParams listExaminationInfoParams);

	Interview findInterviewInfo(FindInterviewInfoParams findInterviewInfoParams);

	List<Interview> listInterviewInfo(ListInterviewInfoParams listInterviewInfoParams);

	void updatePersonal(Personal personal);

	void updateCompany(Company company);

	void updateEmploy(Employ employ);

	void updateApplication(Application application);

	void updateTeachin(Teachin teachin);

	void updateExamination(Examination examination);

	void updateInterview(Interview interview);

	void deletePersonalInfo(String _id);

	void deleteCompanyInfo(String _id);

	void deleteEmployInfo(String _id);

	void deleteApplicationInfo(String _id);

	void deleteTeachinInfo(String _id);

	void deleteExaminationInfo(String _id);

	void deleteInterviewInfo(String _id);

}
