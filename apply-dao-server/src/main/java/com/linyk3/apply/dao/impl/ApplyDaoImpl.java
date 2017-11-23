package com.linyk3.apply.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.linyk3.apply.bean.Application;
import com.linyk3.apply.bean.Company;
import com.linyk3.apply.bean.Employ;
import com.linyk3.apply.bean.Examination;
import com.linyk3.apply.bean.Interview;
import com.linyk3.apply.bean.Personal;
import com.linyk3.apply.bean.Teachin;
import com.linyk3.apply.bean.User;
import com.linyk3.apply.dao.ApplyDao;
import com.linyk3.apply.repository.ApplicationRepository;
import com.linyk3.apply.repository.CompanyRepository;
import com.linyk3.apply.repository.EmployRepository;
import com.linyk3.apply.repository.ExaminationRepository;
import com.linyk3.apply.repository.InterviewRepository;
import com.linyk3.apply.repository.PersonalRepository;
import com.linyk3.apply.repository.TeachinRepository;
import com.linyk3.apply.repository.UserRepository;
import com.linyk3.apply.utils.ApplyUtils;
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

@Repository
public class ApplyDaoImpl implements ApplyDao {

	@Resource
	private UserRepository userRepository;

	@Resource
	private PersonalRepository personalRepository;

	@Resource
	private EmployRepository employRepository;

	@Resource
	private CompanyRepository companyRepository;

	@Resource
	private ApplicationRepository applicationRepository;

	@Resource
	private TeachinRepository teachinRepository;

	@Resource
	private ExaminationRepository examinationRepository;

	@Resource
	private InterviewRepository interviewRepository;

	public User findUserByName(FindUserInfoParams findUserInfoParams) {
		User user = userRepository.findUserByName(findUserInfoParams.getName());
		if (user == null) {
			return null;
		}
		return user;
	}

	public List<User> listUserInfo(ListUserInfoParams listUserInfoParams) {
		List<User> userList = new ArrayList<User>();
		if (listUserInfoParams.getNameList() == null || listUserInfoParams.getNameList().size() == 0) {
			userList = (List<User>) userRepository.findAll();
		} else {
			userList = userRepository.findByNameIn(listUserInfoParams.getNameList());
		}
		return userList;
	}

	public Personal findPersonal(FindPersonalInfoParams findPersonalInfoParams) {
		if (findPersonalInfoParams == null) {
			return null;
		}
		if (StringUtils.isBlank(findPersonalInfoParams.getPersonalId())
				&& StringUtils.isBlank(findPersonalInfoParams.getName())) {
			return null;
		}
		Personal personal = new Personal();
		if (StringUtils.isNotBlank(findPersonalInfoParams.getPersonalId())) {
			return personalRepository.findBy_id(new ObjectId(findPersonalInfoParams.getPersonalId()));
		} else if (StringUtils.isNotBlank(findPersonalInfoParams.getName())) {
			return personalRepository.findByName(findPersonalInfoParams.getName());
		}
		return null;
	}

	public List<Personal> listPersionalInfo(ListPersonalInfoParams listPersonalInfoParams) {
		List<Personal> personalList = new ArrayList<Personal>();
		if (listPersonalInfoParams == null || ApplyUtils.isListNull(listPersonalInfoParams.getNameList())) {
			personalList = (List<Personal>) personalRepository.findAll();
		} else {
			personalList = personalRepository.findByNameIn(listPersonalInfoParams.getNameList());
		}
		return personalList;
	}

	public void updatePersonal(Personal personal) {
		personalRepository.save(personal);
	}

	public Company findCompanyInfo(FindCompanyInfoParams findCompanyInfoParams) {
		if (findCompanyInfoParams == null) {
			return null;
		}
		if (StringUtils.isBlank(findCompanyInfoParams.getCompanyId())
				&& StringUtils.isBlank(findCompanyInfoParams.getCompanyName())) {
			return null;
		}
		Company company = new Company();
		if (StringUtils.isNotBlank(findCompanyInfoParams.getCompanyId())) {
			return companyRepository.findBy_id(new ObjectId(findCompanyInfoParams.getCompanyId()));
		} else if (StringUtils.isNotBlank(findCompanyInfoParams.getCompanyName())) {
			return companyRepository.findByName(findCompanyInfoParams.getCompanyName());
		}
		return null;
	}

	public List<Company> listCompanyInfo(ListCompanyInfoParams listCompanyInfoParams) {
		return companyRepository.listCompanyInfo(listCompanyInfoParams);
	}

	public Employ findEmployInfo(FindEmployInfoParams findEmployInfoParams) {
		if (findEmployInfoParams == null) {
			return null;
		}
		if (StringUtils.isBlank(findEmployInfoParams.getEmployId())) {
			return null;
		}
		Employ employ = employRepository.findBy_id(new ObjectId(findEmployInfoParams.getEmployId()));
		return employ;
	}

	public List<Employ> listEmployInfo(ListEmployInfoParams listEmployInfoParams) {
		return employRepository.listEmployInfo(listEmployInfoParams);
	}

	public Application findApplicationInfo(FindApplicationInfoParams findApplicationInfoParams) {
		if (findApplicationInfoParams == null) {
			return null;
		}
		if (StringUtils.isBlank(findApplicationInfoParams.getApplicationId())) {
			return null;
		}
		return applicationRepository.findBy_id(new ObjectId(findApplicationInfoParams.getApplicationId()));

	}

	public List<Application> listApplicationInfo(ListApplicationInfoParams listApplicationInfoParams) {
		return applicationRepository.listApplicationInfo(listApplicationInfoParams);
	}

	public Teachin findTeachinInfo(FindTeachinInfoParams findTeachinInfoParams) {
		if (findTeachinInfoParams == null) {
			return null;
		}
		if (StringUtils.isBlank(findTeachinInfoParams.getTeachinId())) {
			return null;
		}
		return teachinRepository.findBy_id(new ObjectId(findTeachinInfoParams.getTeachinId()));

	}

	public List<Teachin> listTeachinInfo(ListTeachinInfoParams listTeachinInfoParams) {
		return teachinRepository.listTeachinInfo(listTeachinInfoParams);
	}

	public Examination findExaminationInfo(FindExaminationInfoParams findExaminationInfoParams) {
		if (findExaminationInfoParams == null) {
			return null;
		}
		if (StringUtils.isBlank(findExaminationInfoParams.getExaminationId())) {
			return null;
		}
		return examinationRepository.findBy_id(new ObjectId(findExaminationInfoParams.getExaminationId()));

	}

	public List<Examination> listExaminationInfo(ListExaminationInfoParams listExaminationInfoParams) {
		return examinationRepository.listExaminationInfo(listExaminationInfoParams);
	}

	public Interview findInterviewInfo(FindInterviewInfoParams findInterviewInfoParams) {
		if (findInterviewInfoParams == null) {
			return null;
		}
		if (StringUtils.isBlank(findInterviewInfoParams.getInterviewId())) {
			return null;
		}
		return interviewRepository.findBy_id(new ObjectId(findInterviewInfoParams.getInterviewId()));

	}

	public List<Interview> listInterviewInfo(ListInterviewInfoParams listInterviewInfoParams) {
		return interviewRepository.listInterviewInfo(listInterviewInfoParams);
	}

	public void updateCompany(Company company) {
		companyRepository.save(company);
	}

	public void updateEmploy(Employ employ) {
		employRepository.save(employ);
	}

	public void updateApplication(Application application) {
		applicationRepository.save(application);
	}

	public void updateTeachin(Teachin teachin) {
		teachinRepository.save(teachin);
	}

	public void updateExamination(Examination examination) {
		examinationRepository.save(examination);
	}

	public void updateInterview(Interview interview) {
		interviewRepository.save(interview);
	}

	public void deletePersonalInfo(String _id) {
		personalRepository.delete(_id);
	}

	public void deleteCompanyInfo(String _id) {
		companyRepository.delete(_id);
	}

	public void deleteEmployInfo(String _id) {
		employRepository.delete(_id);
	}

	public void deleteApplicationInfo(String _id) {
		applicationRepository.delete(_id);
	}

	public void deleteTeachinInfo(String _id) {
		teachinRepository.delete(_id);
	}

	public void deleteExaminationInfo(String _id) {
		examinationRepository.delete(_id);
	}

	public void deleteInterviewInfo(String _id) {
		interviewRepository.delete(_id);
	}

}
