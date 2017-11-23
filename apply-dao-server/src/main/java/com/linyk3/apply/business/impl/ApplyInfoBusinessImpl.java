package com.linyk3.apply.business.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.linyk3.apply.bean.Application;
import com.linyk3.apply.bean.Collection;
import com.linyk3.apply.bean.Company;
import com.linyk3.apply.bean.Employ;
import com.linyk3.apply.bean.EmployCondition;
import com.linyk3.apply.bean.Examination;
import com.linyk3.apply.bean.Interview;
import com.linyk3.apply.bean.Personal;
import com.linyk3.apply.bean.Teachin;
import com.linyk3.apply.bean.User;
import com.linyk3.apply.business.ApplyInfoBusiness;
import com.linyk3.apply.dao.ApplyDao;
import com.linyk3.apply.utils.ApplyUtils;
import com.linyk3.thrift.apply.ApplicationInfo;
import com.linyk3.thrift.apply.CollectionInfo;
import com.linyk3.thrift.apply.CompanyInfo;
import com.linyk3.thrift.apply.EmployConditionInfo;
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
@Service
public class ApplyInfoBusinessImpl implements ApplyInfoBusiness {

	@Resource
	private ApplyDao applyDao;

	public UserInfo findUserInfo(FindUserInfoParams findUserInfoParams) {
		User user = applyDao.findUserByName(findUserInfoParams);
		if (user == null) {
			return null;
		}
		UserInfo userInfo = this.getUserInfo(user);
		return userInfo;
	}

	private UserInfo getUserInfo(User user) {
		if (user == null) {
			return null;
		}
		UserInfo info = new UserInfo();
		if (user.get_id() != null) {
			info.set_id(user.get_id().toHexString());
		}
		info.setName(user.getName());
		info.setPassword(user.getPassword());
		info.setEmail(user.getEmail());
		return info;
	}

	public List<UserInfo> listUserInfo(ListUserInfoParams listUserInfoParams) {
		List<User> userList = applyDao.listUserInfo(listUserInfoParams);
		if (ApplyUtils.isListNull(userList)) {
			return null;
		}
		List<UserInfo> infoList = new ArrayList<UserInfo>();
		for (User user : userList) {
			UserInfo info = getUserInfo(user);
			infoList.add(info);
		}
		return infoList;
	}

	public PersonalInfo findPersonalInfo(FindPersonalInfoParams findPersonalInfoParams) {
		Personal personal = applyDao.findPersonal(findPersonalInfoParams);
		if (personal == null) {
			return null;
		}
		PersonalInfo info = getPersonalInfo(personal);
		return info;
	}

	private PersonalInfo getPersonalInfo(Personal personal) {
		if (personal == null) {
			return null;
		}
		PersonalInfo info = new PersonalInfo();
		if (personal.get_id() != null) {
			info.set_id(personal.get_id().toHexString());
		}
		info.setName(personal.getName());
		info.setSex(personal.getSex());
		info.setBirthday(personal.getBirthday());
		info.setSchool(personal.getSchool());
		info.setDiploma(personal.getDiploma());
		info.setMajor(personal.getMajor());
		info.setMajorCategory(personal.getMajorCategory());
		info.setLocationId(personal.getLocationId());
		info.setLocationName(personal.getLocationName());
		info.setPositions(personal.getPositions());
		info.setSalary(personal.getSalary());
		info.setFavor(getColletionInfo(personal.getFavor()));
		info.setIgnore(getColletionInfo(personal.getIgnore()));
		return info;
	}

	private CollectionInfo getColletionInfo(Collection collection) {
		if (collection == null) {
			return null;
		}
		CollectionInfo info = new CollectionInfo();
		if (collection.get_id() != null) {
			info.set_id(collection.get_id().toHexString());
		}
		info.setCompanyIdList(collection.getCompanyIdList());
		info.setApplicationIdList(collection.getApplicationIdList());
		info.setEmployIdList(collection.getEmployIdList());
		info.setExaminationIdList(collection.getExaminationIdList());
		info.setTeachinIdList(collection.getTeachinIdList());
		info.setInterviewIdList(collection.getInterviewIdList());
		return info;
	}

	public List<PersonalInfo> listPersonalInfo(ListPersonalInfoParams listPersonalInfoParams) {
		List<Personal> personalList = applyDao.listPersionalInfo(listPersonalInfoParams);
		if (ApplyUtils.isListNull(personalList)) {
			return null;
		}
		List<PersonalInfo> infoList = new ArrayList<PersonalInfo>();
		for (Personal personal : personalList) {
			PersonalInfo info = new PersonalInfo();
			info = getPersonalInfo(personal);
			infoList.add(info);
		}
		return infoList;
	}

	public CompanyInfo findCompanyInfo(FindCompanyInfoParams findCompanyInfoParams) {
		Company company = applyDao.findCompanyInfo(findCompanyInfoParams);
		if (company == null) {
			return null;
		}
		CompanyInfo info = getCompanyInfo(company);
		return info;
	}

	private CompanyInfo getCompanyInfo(Company com) {
		if (com == null) {
			return null;
		}
		CompanyInfo info = new CompanyInfo();
		if (com.get_id() != null) {
			info.set_id(com.get_id().toHexString());
		}
		info.setName(com.getName());
		info.setCompanyType(com.getCompanyType());
		info.setIntro(com.getIntro());
		info.setAddress(com.getAddress());
		info.setTelphone(com.getTelphone());
		info.setWebsite(com.getWebsite());
		info.setEmployIdList(com.getEmployIdList());
		info.setApplicationIdList(com.getApplicationIdList());
		info.setTeachinIdList(com.getTeachinIdList());
		info.setExaminationIdList(com.getExaminationIdList());
		info.setInterviewIdList(com.getInterviewIdList());
		info.setProgress(com.getProgress());
		info.setType(com.getType());
		info.setStatus(com.getStatus());
		info.setMarks(com.getMarks());
		info.setPositions(com.getPositions());
		return info;

	}

	private InterviewInfo getInterviewInfo(Interview view) {
		if (view == null) {
			return null;
		}
		InterviewInfo info = new InterviewInfo();
		if (view.get_id() != null) {
			info.set_id(view.get_id().toHexString());
		}
		info.setCompanyId(view.getCompanyId());
		info.setCompanyName(view.getCompanyName());
		info.setSchool(view.getSchool());
		info.setAddress(view.getAddress());
		info.setDate(view.getDate());
		info.setStatus(view.getStatus());
		info.setMarks(view.getMarks());
		info.setPositions(view.getPositions());
		return info;
	}

	private ExaminationInfo getExaminationInfo(Examination exam) {
		if (exam == null) {
			return null;
		}
		ExaminationInfo info = new ExaminationInfo();
		if (exam.get_id() != null) {
			info.set_id(exam.get_id().toHexString());
		}
		info.setCompanyId(exam.getCompanyId());
		info.setCompanyName(exam.getCompanyName());
		info.setSchool(exam.getSchool());
		info.setAddress(exam.getAddress());
		info.setDate(exam.getDate());
		info.setStatus(exam.getStatus());
		info.setMarks(exam.getMarks());
		info.setPositions(exam.getPositions());
		return info;
	}

	private TeachinInfo getTeachinInfo(Teachin teachin) {
		if (teachin == null) {
			return null;
		}
		TeachinInfo info = new TeachinInfo();
		if (teachin.get_id() != null) {
			info.set_id(teachin.get_id().toHexString());
		}
		info.setCompanyId(teachin.getCompanyId());
		info.setCompanyName(teachin.getCompanyName());
		info.setSchool(teachin.getSchool());
		info.setAddress(teachin.getAddress());
		info.setDate(teachin.getDate());
		info.setStatus(teachin.getStatus());
		info.setMarks(teachin.getMarks());
		info.setPositions(teachin.getPositions());
		return info;
	}

	private ApplicationInfo getApplicationInfo(Application application) {
		if (application == null) {
			return null;
		}
		ApplicationInfo info = new ApplicationInfo();
		if (application.get_id() != null) {
			info.set_id(application.get_id().toHexString());
		}
		info.setCompanyId(application.getCompanyId());
		info.setCompanyName(application.getCompanyName());
		info.setBeginDate(application.getBeginDate());
		info.setEndDate(application.getEndDate());
		info.setWebsite(application.getWebsite());
		info.setStatus(application.getStatus());
		info.setMarks(application.getMarks());
		info.setPositions(application.getPositions());
		return info;
	}

	private EmployConditionInfo getEmployCondition(EmployCondition employCondition) {
		if (employCondition == null) {
			return null;
		}
		EmployConditionInfo info = new EmployConditionInfo();
		info.setDiplomas(employCondition.getDiplomas());
		info.setSchools(employCondition.getSchools());
		info.setMajors(employCondition.getMajors());
		info.setOthers(employCondition.getOthers());
		return info;
	}

	public List<CompanyInfo> listCompanyInfo(ListCompanyInfoParams listCompanyInfoParams) {
		List<Company> companyList = applyDao.listCompanyInfo(listCompanyInfoParams);
		if (ApplyUtils.isListNull(companyList)) {
			return null;
		}
		List<CompanyInfo> infoList = new ArrayList<CompanyInfo>();
		for (Company company : companyList) {
			CompanyInfo info = new CompanyInfo();
			info = getCompanyInfo(company);
			infoList.add(info);
		}
		return infoList;
	}

	public EmployInfo findEmployInfo(FindEmployInfoParams findEmployInfoParams) {
		Employ employ = applyDao.findEmployInfo(findEmployInfoParams);
		if (employ == null) {
			return null;
		}
		EmployInfo info = getEmployInfo(employ);
		return info;
	}

	private EmployInfo getEmployInfo(Employ employ) {
		if (employ == null) {
			return null;
		}
		EmployInfo employInfo = new EmployInfo();
		if (employ.get_id() != null) {
			employInfo.set_id(employ.get_id().toHexString());
		}
		employInfo.setCompanyId(employ.getCompanyId());
		employInfo.setPositions(employ.getPositions());
		employInfo.setNumber(employ.getNumber());
		employInfo.setEmployCondition(getEmployCondition(employ.getEmployCondition()));
		employInfo.setSalaryMin(employ.getSalaryMin());
		employInfo.setSalaryMax(employ.getSalaryMax());
		employInfo.setBenefits(employ.getBenefits());
		employInfo.setMarks(employ.getMarks());
		return employInfo;
	}

	public List<EmployInfo> listEmployInfo(ListEmployInfoParams listEmployInfoParams) {
		List<Employ> employList = applyDao.listEmployInfo(listEmployInfoParams);
		if (ApplyUtils.isListNull(employList)) {
			return null;
		}
		List<EmployInfo> infoList = new ArrayList<EmployInfo>();
		for (Employ employ : employList) {
			EmployInfo info = new EmployInfo();
			info = getEmployInfo(employ);
			infoList.add(info);
		}
		return infoList;
	}

	public ApplicationInfo findApplicationInfo(FindApplicationInfoParams findApplicationInfoParams) {
		Application application = applyDao.findApplicationInfo(findApplicationInfoParams);
		if (application == null) {
			return null;
		}
		ApplicationInfo info = getApplicationInfo(application);
		return info;
	}

	public List<ApplicationInfo> listApplicationInfo(ListApplicationInfoParams listApplicationInfoParams) {
		List<Application> applicationList = applyDao.listApplicationInfo(listApplicationInfoParams);
		if (ApplyUtils.isListNull(applicationList)) {
			return null;
		}
		List<ApplicationInfo> infoList = new ArrayList<ApplicationInfo>();
		for (Application application : applicationList) {
			ApplicationInfo info = new ApplicationInfo();
			info = getApplicationInfo(application);
			infoList.add(info);
		}
		return infoList;
	}

	public TeachinInfo findTeachinInfo(FindTeachinInfoParams findTeachinInfoParams) {
		Teachin teachin = applyDao.findTeachinInfo(findTeachinInfoParams);
		if (teachin == null) {
			return null;
		}
		TeachinInfo info = getTeachinInfo(teachin);
		return info;
	}

	public List<TeachinInfo> listTeachinInfo(ListTeachinInfoParams listTeachinInfoParams) {
		List<Teachin> teachinList = applyDao.listTeachinInfo(listTeachinInfoParams);
		if (ApplyUtils.isListNull(teachinList)) {
			return null;
		}
		List<TeachinInfo> infoList = new ArrayList<TeachinInfo>();
		for (Teachin teachin : teachinList) {
			TeachinInfo info = new TeachinInfo();
			info = getTeachinInfo(teachin);
			infoList.add(info);
		}
		return infoList;
	}

	public ExaminationInfo findExaminationInfo(FindExaminationInfoParams findExaminationInfoParams) {
		Examination examination = applyDao.findExaminationInfo(findExaminationInfoParams);
		if (examination == null) {
			return null;
		}
		ExaminationInfo info = getExaminationInfo(examination);
		return info;
	}

	public List<ExaminationInfo> listExaminationInfo(ListExaminationInfoParams listExaminationInfoParams) {
		List<Examination> examinationList = applyDao.listExaminationInfo(listExaminationInfoParams);
		if (ApplyUtils.isListNull(examinationList)) {
			return null;
		}
		List<ExaminationInfo> infoList = new ArrayList<ExaminationInfo>();
		for (Examination examination : examinationList) {
			ExaminationInfo info = new ExaminationInfo();
			info = getExaminationInfo(examination);
			infoList.add(info);
		}
		return infoList;
	}

	public InterviewInfo findInterviewInfo(FindInterviewInfoParams findInterviewInfoParams) {
		Interview interview = applyDao.findInterviewInfo(findInterviewInfoParams);
		if (interview == null) {
			return null;
		}
		InterviewInfo info = getInterviewInfo(interview);
		return info;
	}

	public List<InterviewInfo> listInterviewInfo(ListInterviewInfoParams listInterviewInfoParams) {
		List<Interview> interviewList = applyDao.listInterviewInfo(listInterviewInfoParams);
		if (ApplyUtils.isListNull(interviewList)) {
			return null;
		}
		List<InterviewInfo> infoList = new ArrayList<InterviewInfo>();
		for (Interview interview : interviewList) {
			InterviewInfo info = new InterviewInfo();
			info = getInterviewInfo(interview);
			infoList.add(info);
		}
		return infoList;
	}

	public PersonalInfo updatePersonal(PersonalInfo personalInfo) {
		if (personalInfo == null) {
			return null;
		}
		Personal personal = getPersonal(personalInfo);
		applyDao.updatePersonal(personal);
		return personalInfo;
	}

	private Personal getPersonal(PersonalInfo personalInfo) {
		if (personalInfo == null) {
			return null;
		}
		Personal personal = new Personal();
		personal.set_id(new ObjectId(personalInfo.get_id()));
		personal.setName(personalInfo.getName());
		personal.setSex(personalInfo.getSex());
		personal.setBirthday(personalInfo.getBirthday());
		personal.setSchool(personalInfo.getSchool());
		personal.setDiploma(personalInfo.getDiploma());
		personal.setMajor(personalInfo.getMajor());
		personal.setMajorCategory(personalInfo.getMajorCategory());
		personal.setLocationId(personalInfo.getLocationId());
		personal.setLocationName(personalInfo.getLocationName());
		personal.setPositions(personalInfo.getPositions());
		personal.setSalary(personalInfo.getSalary());
		personal.setFavor(getCollection(personalInfo.getFavor()));
		personal.setIgnore(getCollection(personalInfo.getIgnore()));
		return personal;
	}

	private Collection getCollection(CollectionInfo info) {
		if (info == null) {
			return null;
		}
		Collection collection = new Collection();
		if (info.get_id() != null) {
			collection.set_id(new ObjectId(info.get_id()));
		}
		collection.setCompanyIdList(info.getCompanyIdList());
		collection.setApplicationIdList(info.getApplicationIdList());
		collection.setEmployIdList(info.getEmployIdList());
		collection.setExaminationIdList(info.getExaminationIdList());
		collection.setTeachinIdList(info.getTeachinIdList());
		collection.setInterviewIdList(info.getInterviewIdList());
		return collection;
	}

	public CompanyInfo updateCompanyInfo(CompanyInfo companyInfo) {
		if (companyInfo == null) {
			return null;
		}
		Company company = getCompany(companyInfo);
		applyDao.updateCompany(company);
		return companyInfo;
	}

	private Company getCompany(CompanyInfo info) {
		if (info == null) {
			return null;
		}
		Company company = new Company();
		company.set_id(new ObjectId(info.get_id()));
		company.setName(info.getName());
		company.setCompanyType(info.getCompanyType());
		company.setIntro(info.getIntro());
		company.setAddress(info.getAddress());
		company.setTelphone(info.getTelphone());
		company.setWebsite(info.getWebsite());
		company.setEmployIdList(info.getEmployIdList());
		company.setApplicationIdList(info.getApplicationIdList());
		company.setTeachinIdList(info.getTeachinIdList());
		company.setExaminationIdList(info.getExaminationIdList());
		company.setInterviewIdList(info.getInterviewIdList());
		company.setProgress(info.getProgress());
		company.setType(info.getType());
		company.setStatus(info.getStatus());
		company.setMarks(info.getMarks());
		company.setPositions(info.getPositions());
		return company;
	}

	public EmployInfo updateEmployInfo(EmployInfo employInfo) {
		if (employInfo == null) {
			return null;
		}
		Employ employ = getEmploy(employInfo);
		applyDao.updateEmploy(employ);
		return employInfo;
	}

	private Employ getEmploy(EmployInfo info) {
		if (info == null) {
			return null;
		}
		Employ em = new Employ();
		em.set_id(new ObjectId(info.get_id()));
		em.setCompanyId(info.getCompanyId());
		em.setCompanyName(info.getCompanyName());
		em.setPositions(info.getPositions());
		em.setNumber(info.getNumber());
		EmployConditionInfo conInfo = info.getEmployCondition();
		if (conInfo != null) {
			EmployCondition con = new EmployCondition();
			con.setDiplomas(conInfo.getDiplomas());
			con.setMajors(conInfo.getMajors());
			con.setSchools(conInfo.getSchools());
			con.setOthers(conInfo.getOthers());
			em.setEmployCondition(con);
		}
		em.setSalaryMin(info.getSalaryMin());
		em.setSalaryMax(info.getSalaryMax());
		em.setBenefits(info.getBenefits());
		em.setMarks(info.getMarks());
		return em;
	}

	public ApplicationInfo updateApplicationInfo(ApplicationInfo applicationInfo) {
		if (applicationInfo == null) {
			return null;
		}
		Application application = getApplication(applicationInfo);
		applyDao.updateApplication(application);
		return applicationInfo;
	}

	private Application getApplication(ApplicationInfo info) {
		if(info == null) {
			return null;
		}
		Application app = new Application();
		app.set_id(new ObjectId(info.get_id()));
		app.setCompanyId(info.getCompanyId());
		app.setCompanyName(info.getCompanyName());
		app.setBeginDate(info.getBeginDate());
		app.setEndDate(info.getEndDate());
		app.setWebsite(info.getWebsite());
		app.setStatus(info.getStatus());
		app.setMarks(info.getMarks());
		app.setPositions(info.getPositions());
		return app;
	}

	public TeachinInfo updateTeachinInfo(TeachinInfo teachinInfo) {
		if (teachinInfo == null) {
			return null;
		}
		Teachin teachin = getTeachin(teachinInfo);
		applyDao.updateTeachin(teachin);
		return teachinInfo;
	}

	private Teachin getTeachin(TeachinInfo info) {
		if(info == null) {
			return null;
		}
		Teachin tea = new Teachin();
		tea.set_id(new ObjectId(info.get_id()));
		tea.setCompanyId(info.getCompanyId());
		tea.setCompanyName(info.getCompanyName());
		tea.setSchool(info.getSchool());
		tea.setAddress(info.getAddress());
		tea.setDate(info.getDate());
		tea.setStatus(info.getStatus());
		tea.setMarks(info.getMarks());
		tea.setPositions(info.getPositions());
		return tea;
	}

	public ExaminationInfo updateExaminationInfo(ExaminationInfo examinationInfo) {
		if (examinationInfo == null) {
			return null;
		}
		Examination examination = getExamination(examinationInfo);
		applyDao.updateExamination(examination);
		return examinationInfo;
	}

	private Examination getExamination(ExaminationInfo info) {
		if(info == null) {
			return null;
		}
		Examination exam = new Examination();
		exam.set_id(new ObjectId(info.get_id()));
		exam.setCompanyId(info.getCompanyId());
		exam.setCompanyName(info.getCompanyName());
		exam.setSchool(info.getSchool());
		exam.setAddress(info.getAddress());
		exam.setDate(info.getDate());
		exam.setStatus(info.getStatus());
		exam.setMarks(info.getMarks());
		exam.setPositions(info.getPositions());
		return exam;
	}

	public InterviewInfo updateInterviewInfo(InterviewInfo interviewInfo) {
		if (interviewInfo == null) {
			return null;
		}
		Interview interview = getInterview(interviewInfo);
		applyDao.updateInterview(interview);
		return interviewInfo;
	}

	private Interview getInterview(InterviewInfo info) {
		if(info == null) {
			return null;
		}
		Interview view = new Interview();
		view.set_id(new ObjectId(info.get_id()));
		view.setCompanyId(info.getCompanyId());
		view.setCompanyName(info.getCompanyName());
		view.setSchool(info.getSchool());
		view.setAddress(info.getAddress());
		view.setDate(info.getDate());
		view.setStatus(info.getStatus());
		view.setMarks(info.getMarks());
		view.setPositions(info.getPositions());
		return view;
	}

	public void deletePersonalInfo(String _id) {
		applyDao.deletePersonalInfo(_id);
	}

	public void deleteCompanyInfo(String _id) {
		applyDao.deleteCompanyInfo(_id);
	}

	public void deleteEmployInfo(String _id) {
		applyDao.deleteEmployInfo(_id);
	}

	public void deleteApplicationInfo(String _id) {
		applyDao.deleteApplicationInfo(_id);
	}

	public void deleteTeachinInfo(String _id) {
		applyDao.deleteTeachinInfo(_id);
	}

	public void deleteExaminationInfo(String _id) {
		applyDao.deleteExaminationInfo(_id);
	}

	public void deleteInterviewInfo(String _id) {
		applyDao.deleteInterviewInfo(_id);
	}
}
