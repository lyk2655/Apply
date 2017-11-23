package com.linyk3.apply.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.linyk3.apply.business.ApplyInfoBusiness;
import com.linyk3.apply.utils.ApplyUtils;
import com.linyk3.thrift.apply.ApplicationInfo;
import com.linyk3.thrift.apply.ApplyService;
import com.linyk3.thrift.apply.CompanyInfo;
import com.linyk3.thrift.apply.EmployInfo;
import com.linyk3.thrift.apply.ExaminationInfo;
import com.linyk3.thrift.apply.InterviewInfo;
import com.linyk3.thrift.apply.PersonalInfo;
import com.linyk3.thrift.apply.TeachinInfo;
import com.linyk3.thrift.apply.UserInfo;
import com.linyk3.thrift.apply.exception.ApplyException;
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
@Controller
public class ApplyServiceIpml implements ApplyService.Iface {

	private static final Logger logger = LoggerFactory.getLogger(ApplyServiceIpml.class);

	@Resource
	private ApplyInfoBusiness applyInfoBusiness;

	public UserInfo findUserInfo(FindUserInfoParams findUserInfoParams) throws ApplyException, TException {
		ApplyUtils.checkParamsIsNull(findUserInfoParams);
		ApplyUtils.checkString(findUserInfoParams.getName());
		UserInfo userInfo = applyInfoBusiness.findUserInfo(findUserInfoParams);
		return userInfo;
	}

	public List<UserInfo> listUserInfo(ListUserInfoParams listUserInfoParams) throws ApplyException, TException {
		ApplyUtils.checkParamsIsNull(listUserInfoParams);
		List<UserInfo> userInfoList = applyInfoBusiness.listUserInfo(listUserInfoParams);
		return userInfoList;
	}

	public PersonalInfo findPersonalInfo(FindPersonalInfoParams findPersonalInfoParams)
			throws ApplyException, TException {
		ApplyUtils.checkParamsIsNull(findPersonalInfoParams);
		ApplyUtils.checkString(findPersonalInfoParams.getName());

		PersonalInfo personalInfo = applyInfoBusiness.findPersonalInfo(findPersonalInfoParams);
		return personalInfo;
	}

	public List<PersonalInfo> listPersonalInfo(ListPersonalInfoParams listPersonalInfoParams)
			throws ApplyException, TException {
		ApplyUtils.checkParamsIsNull(listPersonalInfoParams);
		List<PersonalInfo> personalInfoList = applyInfoBusiness.listPersonalInfo(listPersonalInfoParams);
		return personalInfoList;
	}

	public CompanyInfo findCompanyInfo(FindCompanyInfoParams findCompanyInfoParams) throws ApplyException, TException {
		ApplyUtils.checkParamsIsNull(findCompanyInfoParams);
		ApplyUtils.checkString(findCompanyInfoParams.getCompanyName());
		CompanyInfo companyInfo = applyInfoBusiness.findCompanyInfo(findCompanyInfoParams);
		return companyInfo;
	}

	public List<CompanyInfo> listCompanyInfo(ListCompanyInfoParams listCompanyInfoParams)
			throws ApplyException, TException {
		ApplyUtils.checkParamsIsNull(listCompanyInfoParams);
		List<CompanyInfo> companyInfoList = applyInfoBusiness.listCompanyInfo(listCompanyInfoParams);
		return companyInfoList;
	}

	public EmployInfo findEmployInfo(FindEmployInfoParams findEmployInfoParams) throws ApplyException, TException {
		ApplyUtils.checkParamsIsNull(findEmployInfoParams);
		ApplyUtils.checkString(findEmployInfoParams.getEmployId());

		EmployInfo employInfo = applyInfoBusiness.findEmployInfo(findEmployInfoParams);
		return employInfo;
	}

	public List<EmployInfo> listEmployInfo(ListEmployInfoParams listEmployInfoParams)
			throws ApplyException, TException {
		ApplyUtils.checkParamsIsNull(listEmployInfoParams);
		List<EmployInfo> employInfoList = applyInfoBusiness.listEmployInfo(listEmployInfoParams);
		return employInfoList;
	}

	public ApplicationInfo findApplicationInfo(FindApplicationInfoParams findApplicationInfoParams)
			throws ApplyException, TException {
		ApplyUtils.checkParamsIsNull(findApplicationInfoParams);
		ApplyUtils.checkString(findApplicationInfoParams.getApplicationId());

		ApplicationInfo applicationInfo = applyInfoBusiness.findApplicationInfo(findApplicationInfoParams);
		return applicationInfo;
	}

	public List<ApplicationInfo> listApplicationInfo(ListApplicationInfoParams listApplicationInfoParams)
			throws ApplyException, TException {
		ApplyUtils.checkParamsIsNull(listApplicationInfoParams);
		List<ApplicationInfo> applicationInfoList = applyInfoBusiness.listApplicationInfo(listApplicationInfoParams);
		return applicationInfoList;
	}

	public TeachinInfo findTeachinInfo(FindTeachinInfoParams findTeachinInfoParams) throws ApplyException, TException {
		ApplyUtils.checkParamsIsNull(findTeachinInfoParams);
		ApplyUtils.checkString(findTeachinInfoParams.getTeachinId());

		TeachinInfo teachinInfo = applyInfoBusiness.findTeachinInfo(findTeachinInfoParams);
		return teachinInfo;
	}

	public List<TeachinInfo> listTeachinInfo(ListTeachinInfoParams listTeachinInfoParams) {
		ApplyUtils.checkParamsIsNull(listTeachinInfoParams);
		List<TeachinInfo> teachinInfoList = applyInfoBusiness.listTeachinInfo(listTeachinInfoParams);
		return teachinInfoList;
	}

	public ExaminationInfo findExaminationInfo(FindExaminationInfoParams findExaminationInfoParams)
			throws ApplyException, TException {
		ApplyUtils.checkParamsIsNull(findExaminationInfoParams);
		ApplyUtils.checkString(findExaminationInfoParams.getExaminationId());

		ExaminationInfo examinationInfo = applyInfoBusiness.findExaminationInfo(findExaminationInfoParams);
		return examinationInfo;
	}

	public List<ExaminationInfo> listExaminationInfo(ListExaminationInfoParams listExaminationInfoParams)
			throws ApplyException, TException {
		ApplyUtils.checkParamsIsNull(listExaminationInfoParams);
		List<ExaminationInfo> examinationInfoList = applyInfoBusiness.listExaminationInfo(listExaminationInfoParams);
		return examinationInfoList;
	}

	public InterviewInfo findInterviewInfo(FindInterviewInfoParams findInterviewInfoParams)
			throws ApplyException, TException {
		ApplyUtils.checkParamsIsNull(findInterviewInfoParams);
		ApplyUtils.checkString(findInterviewInfoParams.getInterviewId());

		InterviewInfo interviewInfo = applyInfoBusiness.findInterviewInfo(findInterviewInfoParams);
		return interviewInfo;
	}

	public List<InterviewInfo> listInterviewInfo(ListInterviewInfoParams listInterviewInfoParams)
			throws ApplyException, TException {
		ApplyUtils.checkParamsIsNull(listInterviewInfoParams);
		List<InterviewInfo> interviewInfoList = applyInfoBusiness.listInterviewInfo(listInterviewInfoParams);
		return interviewInfoList;
	}

	public PersonalInfo updatePersonalInfo(PersonalInfo personalInfo) throws ApplyException, TException {
		if(personalInfo == null) {
			return null;
		}
		PersonalInfo info = applyInfoBusiness.updatePersonal(personalInfo);
		return info;
	}

	public CompanyInfo updateCompanyInfo(CompanyInfo companyInfo) throws ApplyException, TException {
		if(companyInfo == null) {
			return null;
		}
		CompanyInfo info = applyInfoBusiness.updateCompanyInfo(companyInfo);
		return info;
	}

	public EmployInfo updateEmployInfo(EmployInfo employInfo) throws ApplyException, TException {
		if(employInfo == null) {
			return null;
		}
		EmployInfo info = applyInfoBusiness.updateEmployInfo(employInfo);
		return info;
	}

	public ApplicationInfo updateApplicationInfo(ApplicationInfo applicationInfo) throws ApplyException, TException {
		if(applicationInfo == null) {
			return null;
		}
		ApplicationInfo info = applyInfoBusiness.updateApplicationInfo(applicationInfo);
		return info;
	}

	public TeachinInfo updateTeachinInfo(TeachinInfo teachinInfo) throws ApplyException, TException {
		if(teachinInfo == null) {
			return null;
		}
		TeachinInfo info = applyInfoBusiness.updateTeachinInfo(teachinInfo);
		return info;
	}

	public ExaminationInfo updateExaminationInfo(ExaminationInfo examinationInfo) throws ApplyException, TException {
		if(examinationInfo == null) {
			return null;
		}
		ExaminationInfo info = applyInfoBusiness.updateExaminationInfo(examinationInfo);
		return info;
	}

	public InterviewInfo updateInterviewInfo(InterviewInfo interviewInfo) throws ApplyException, TException {
		if(interviewInfo == null) {
			return null;
		}
		InterviewInfo info = applyInfoBusiness.updateInterviewInfo(interviewInfo);
		return info;
	}

	public PersonalInfo deletePersonalInfo(String _id) throws ApplyException, TException {
		if(StringUtils.isBlank(_id)) {
			return null;
		}
		applyInfoBusiness.deletePersonalInfo(_id);
		return null;
	}

	public CompanyInfo deleteCompanyInfo(String _id) throws ApplyException, TException {
		if(StringUtils.isBlank(_id)) {
			return null;
		}
		applyInfoBusiness.deleteCompanyInfo(_id);
		return null;
	}

	public EmployInfo deleteEmployInfo(String _id) throws ApplyException, TException {
		if(StringUtils.isBlank(_id)) {
			return null;
		}
		applyInfoBusiness.deleteEmployInfo(_id);
		return null;
	}

	public ApplicationInfo deleteApplicationInfo(String _id) throws ApplyException, TException {
		if(StringUtils.isBlank(_id)) {
			return null;
		}
		applyInfoBusiness.deleteApplicationInfo(_id);
		return null;
	}

	public TeachinInfo deleteTeachinInfo(String _id) throws ApplyException, TException {
		if(StringUtils.isBlank(_id)) {
			return null;
		}
		applyInfoBusiness.deleteTeachinInfo(_id);
		return null;
	}

	public ExaminationInfo deleteExaminationInfo(String _id) throws ApplyException, TException {
		if(StringUtils.isBlank(_id)) {
			return null;
		}
		applyInfoBusiness.deleteExaminationInfo(_id);
		return null;
	}

	public InterviewInfo deleteInterviewInfo(String _id) throws ApplyException, TException {
		if(StringUtils.isBlank(_id)) {
			return null;
		}
		applyInfoBusiness.deleteInterviewInfo(_id);
		return null;
	}

}
