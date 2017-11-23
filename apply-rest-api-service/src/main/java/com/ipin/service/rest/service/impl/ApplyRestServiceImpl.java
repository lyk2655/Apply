package com.ipin.service.rest.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.ipin.service.rest.beans.apply.Application;
import com.ipin.service.rest.beans.apply.ApplicationResult;
import com.ipin.service.rest.beans.apply.Company;
import com.ipin.service.rest.beans.apply.CompanyDetail;
import com.ipin.service.rest.beans.apply.CompanyResult;
import com.ipin.service.rest.beans.apply.Examination;
import com.ipin.service.rest.beans.apply.ExaminationResult;
import com.ipin.service.rest.beans.apply.Interview;
import com.ipin.service.rest.beans.apply.InterviewResult;
import com.ipin.service.rest.beans.apply.Plan;
import com.ipin.service.rest.beans.apply.PlanMail;
import com.ipin.service.rest.beans.apply.PlanResult;
import com.ipin.service.rest.beans.apply.Teachin;
import com.ipin.service.rest.beans.apply.TeachinResult;
import com.ipin.service.rest.beans.apply.UserResult;
import com.ipin.service.rest.constants.Constants;
import com.ipin.service.rest.service.ApplyRestService;
import com.ipin.service.rest.utils.ApplyUtils;
import com.ipin.service.rest.utils.mail.ApplyMailTask;
import com.ipin.service.rest.utils.mail.ApplyMialTimter;
import com.linyk3.thrift.apply.ApplicationInfo;
import com.linyk3.thrift.apply.ApplyService;
import com.linyk3.thrift.apply.CollectionInfo;
import com.linyk3.thrift.apply.CompanyInfo;
import com.linyk3.thrift.apply.ExaminationInfo;
import com.linyk3.thrift.apply.InterviewInfo;
import com.linyk3.thrift.apply.PersonalInfo;
import com.linyk3.thrift.apply.TeachinInfo;
import com.linyk3.thrift.apply.UserInfo;
import com.linyk3.thrift.apply.params.FindApplicationInfoParams;
import com.linyk3.thrift.apply.params.FindCompanyInfoParams;
import com.linyk3.thrift.apply.params.FindExaminationInfoParams;
import com.linyk3.thrift.apply.params.FindInterviewInfoParams;
import com.linyk3.thrift.apply.params.FindPersonalInfoParams;
import com.linyk3.thrift.apply.params.FindTeachinInfoParams;
import com.linyk3.thrift.apply.params.FindUserInfoParams;
import com.linyk3.thrift.apply.params.ListApplicationInfoParams;
import com.linyk3.thrift.apply.params.ListCompanyInfoParams;
import com.linyk3.thrift.apply.params.ListExaminationInfoParams;
import com.linyk3.thrift.apply.params.ListInterviewInfoParams;
import com.linyk3.thrift.apply.params.ListTeachinInfoParams;
import com.linyk3.thrift.apply.params.ListUserInfoParams;

public class ApplyRestServiceImpl implements ApplyRestService {

	@Resource
	private ApplyService.Iface applyService;

	@Override
	public UserResult findUser(String name) {
		try {
			UserResult userResult = new UserResult();
			// userResult.setName(name);
			// userResult.setPassword("123456");
			// userResult.setEmail("linyk3@126.com");
			FindUserInfoParams findUserInfoParams = new FindUserInfoParams();
			findUserInfoParams.setName(name);
			UserInfo userInfo = applyService.findUserInfo(findUserInfoParams);
			userResult.setName(userInfo.getName());
			userResult.setPassword(userInfo.getPassword());
			userResult.setEmail(userInfo.getEmail());
			return userResult;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<UserResult> listUsers() {
		try {
			List<UserResult> list = new ArrayList<UserResult>();
			ListUserInfoParams params = new ListUserInfoParams();
			List<UserInfo> userInfoList = applyService.listUserInfo(params);
			if (userInfoList == null || userInfoList.size() == 0) {
				return null;
			}
			for (UserInfo info : userInfoList) {
				UserResult user = new UserResult();
				user.setName(info.getName());
				user.setPassword(info.getPassword());
				user.setEmail(info.getEmail());
				list.add(user);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PlanResult getPlanResult(String name, int dateFilter, String typeFilter, int statusFilter) {
		try {
			if (StringUtils.isBlank(name)) {
				return null;
			}
			FindPersonalInfoParams findPersonalInfoParams = new FindPersonalInfoParams();
			findPersonalInfoParams.setName(name);
			PersonalInfo personalInfo = applyService.findPersonalInfo(findPersonalInfoParams);
			System.out.println(personalInfo);
			if (personalInfo == null || personalInfo.getFavor() == null) {
				return null;
			}
			CollectionInfo favor = personalInfo.getFavor();
			List<Plan> planList = new ArrayList<Plan>();

			if ((StringUtils.isBlank(typeFilter) || Constants.APPLY_TYPE_APPLICATION.equals(typeFilter))
					&& (favor.getApplicationIdList() != null && favor.getApplicationIdList().size() > 0)) {
				ListApplicationInfoParams listApplicationInfoParams = new ListApplicationInfoParams();
				listApplicationInfoParams.setApplicationIdList(favor.getApplicationIdList());
				List<ApplicationInfo> infoList = applyService.listApplicationInfo(listApplicationInfoParams);
				for (ApplicationInfo info : infoList) {
					// 只添加有效期内的网申
					if ((statusFilter == 9 || statusFilter == info.getStatus()) && (ApplyUtils
							.isApplicaitonValidDate(info.getBeginDate(), info.getEndDate(), dateFilter))) {
						Plan plan = new Plan();
						plan.set_id(info.get_id());
						// 设置当前时间
						Calendar c = Calendar.getInstance();
						Date now = c.getTime();
						DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						plan.setDate(fmt.format(now));
						plan.setCompanyId(info.getCompanyId());
						// FindCompanyInfoParams findCompanyInfoParams = new
						// FindCompanyInfoParams();
						// findCompanyInfoParams.setCompanyId(info.getCompanyId());
						// CompanyInfo companyInfo =
						// applyService.findCompanyInfo(findCompanyInfoParams);
						plan.setCompanyName(info.getCompanyName());
						plan.setType(Constants.APPLY_TYPE_APPLICATION);
						plan.setStatus(info.getStatus());
						plan.setProgress(ApplyUtils.getStatus(Constants.APPLY_TYPE_APPLICATION, info.getStatus()));
						plan.setAddress(info.getWebsite());
						plan.setPositions(info.getPositions());
						planList.add(plan);
					}
				}
			}
			if ((StringUtils.isBlank(typeFilter) || Constants.APPLY_TYPE_TEACHIN.equals(typeFilter))
					&& (favor.getTeachinIdList() != null && favor.getTeachinIdList().size() > 0)) {
				ListTeachinInfoParams listTeachinInfoParams = new ListTeachinInfoParams();
				listTeachinInfoParams.setTeachinIdList(favor.getTeachinIdList());
				List<TeachinInfo> infoList = applyService.listTeachinInfo(listTeachinInfoParams);
				for (TeachinInfo info : infoList) {
					// 只添一周内未开始的宣讲会
					if ((statusFilter == 9 || statusFilter == info.getStatus())
							&& (ApplyUtils.isTeachinValidDate(info.getDate(), dateFilter))) {
						Plan plan = new Plan();
						plan.set_id(info.get_id());
						plan.setDate(info.getDate());
						plan.setCompanyId(info.getCompanyId());
						// FindCompanyInfoParams findCompanyInfoParams = new
						// FindCompanyInfoParams();
						// findCompanyInfoParams.setCompanyId(info.getCompanyId());
						// CompanyInfo companyInfo =
						// applyService.findCompanyInfo(findCompanyInfoParams);
						plan.setCompanyName(info.getCompanyName());
						plan.setStatus(info.getStatus());
						plan.setProgress(ApplyUtils.getStatus(Constants.APPLY_TYPE_TEACHIN, info.getStatus()));
						plan.setType(Constants.APPLY_TYPE_TEACHIN);
						plan.setAddress(info.getSchool() + "  " + info.getAddress());
						plan.setPositions(info.getPositions());
						planList.add(plan);
					}
				}
			}

			if ((StringUtils.isBlank(typeFilter) || Constants.APPLY_TYPE_EXAMINATION.equals(typeFilter))
					&& (favor.getExaminationIdList() != null && favor.getExaminationIdList().size() > 0)) {
				ListExaminationInfoParams listExaminationInfoParams = new ListExaminationInfoParams();
				listExaminationInfoParams.setExaminationIdList(favor.getExaminationIdList());
				List<ExaminationInfo> infoList = applyService.listExaminationInfo(listExaminationInfoParams);
				for (ExaminationInfo info : infoList) {
					// 只添一周内未开始的笔试
					if ((statusFilter == 9 || statusFilter == info.getStatus())
							&& (ApplyUtils.isExaminationValidDate(info.getDate(), dateFilter))) {
						Plan plan = new Plan();
						plan.set_id(info.get_id());
						plan.setDate(info.getDate());
						plan.setCompanyId(info.getCompanyId());
						// FindCompannameyInfoParams findCompanyInfoParams = new
						// FindCompanyInfoParams();
						// findCompanyInfoParams.setCompanyId(info.getCompanyId());
						// CompanyInfo companyInfo =
						// applyService.findCompanyInfo(findCompanyInfoParams);
						plan.setCompanyName(info.getCompanyName());
						plan.setProgress(ApplyUtils.getStatus(Constants.APPLY_TYPE_EXAMINATION, info.getStatus()));
						plan.setType(Constants.APPLY_TYPE_EXAMINATION);
						plan.setStatus(info.getStatus());
						plan.setAddress(info.getSchool() + "  " + info.getAddress());
						plan.setPositions(info.getPositions());
						planList.add(plan);
					}
				}
			}
			if ((StringUtils.isBlank(typeFilter) || Constants.APPLY_TYPR_INTERVIEW.equals(typeFilter))
					&& (favor.getInterviewIdList() != null && favor.getInterviewIdList().size() > 0)) {
				ListInterviewInfoParams listInterviewInfoParams = new ListInterviewInfoParams();
				listInterviewInfoParams.setInterviewIdList(favor.getInterviewIdList());
				List<InterviewInfo> infoList = applyService.listInterviewInfo(listInterviewInfoParams);
				for (InterviewInfo info : infoList) {
					// 只添一周内未开始的面试
					if ((statusFilter == 9 || statusFilter == info.getStatus())
							&& (ApplyUtils.isInterviewValidDate(info.getDate(), dateFilter))) {
						Plan plan = new Plan();
						plan.set_id(info.get_id());
						plan.setDate(info.getDate());
						plan.setCompanyId(info.getCompanyId());
						// FindCompanyInfoParams findCompanyInfoParams = new
						// FindCompanyInfoParams();
						// findCompanyInfoParams.setCompanyId(info.getCompanyId());
						// CompanyInfo companyInfo =
						// applyService.findCompanyInfo(findCompanyInfoParams);
						plan.setCompanyName(info.getCompanyName());
						plan.setProgress(ApplyUtils.getStatus(Constants.APPLY_TYPR_INTERVIEW, info.getStatus()));
						plan.setType(Constants.APPLY_TYPR_INTERVIEW);
						plan.setStatus(info.getStatus());
						plan.setAddress(info.getSchool() + "  " + info.getAddress());
						plan.setPositions(info.getPositions());
						planList.add(plan);
					}
				}
			}
			// 计划按时间排序
			Collections.sort(planList, new Comparator<Plan>() {
				public int compare(Plan arg0, Plan arg1) {
					return ApplyUtils.getCompareString(arg0.getDate(), arg1.getDate());
				}
			});
			PlanResult planResult = new PlanResult();
			planResult.setName(name);
			planResult.setPlanList(planList);
			return planResult;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws ParseException {
		Calendar c = Calendar.getInstance();
		Date c2 = new Date();
		c2.setDate(c.getTime().getDate() + 1);
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date now = c.getTime();
		Date week = new Date();
		Calendar today = Calendar.getInstance();
		// today.set(Calendar.YEAR, c.get(Calendar.YEAR));
		// today.set(Calendar.MONTH, c.get(Calendar.MONTH));
		// today.set(Calendar.DAY_OF_MONTH,c.get(Calendar.DAY_OF_MONTH)+21);
		// Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
		today.set(Calendar.HOUR_OF_DAY, 23);
		today.set(Calendar.MINUTE, 59);
		today.set(Calendar.SECOND, 59);
		// today.set(Calendar.YEAR, c2.getYear());
		// today.set(Calendar.MONTH, c2.getMonth());
		// today.set(Calendar.DAY_OF_MONTH,c2.getDay());
		// // Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
		// today.set( Calendar.HOUR_OF_DAY, 0);
		// today.set( Calendar.MINUTE, 0);
		// today.set(Calendar.SECOND, 0);
		String s = today.getTime().toLocaleString();
		fmt.parse(s);
		fmt.format(today.getTime());
		// fmt.format(today.getTime().toLocaleString());

		week.setDate(c.getTime().getDate() + 7);
		Date month = new Date();
		month.setDate(c.getTime().getDate() + 30);
		System.out.println(now.toLocaleString());
		System.out.println(s);
		System.out.println(fmt.format(today.getTime()));
		System.out.println(week.toLocaleString());
		System.out.println(month.toLocaleString());
		// System.out.println(endDate);
		// System.out.println(now.getTime() > beginDate.getTime());
		// System.out.println(now.getTime() > endDate.getTime());
		System.out.println(ApplyUtils.isTimeLegal(""));
	}

	@Override
	public CompanyResult getCompanyResult(String typeFilter, int statusFilter) {
		try {
			ListCompanyInfoParams listCompanyInfoParams = new ListCompanyInfoParams();
			List<CompanyInfo> infoList = applyService.listCompanyInfo(listCompanyInfoParams);
			if (infoList == null || infoList.size() == 0) {
				return null;
			}
			List<Company> companyList = new ArrayList<Company>();
			for (CompanyInfo info : infoList) {
				if ((statusFilter == 9 || statusFilter == info.getStatus())
						&& ((StringUtils.isBlank(typeFilter) || (info.getType() != null && info.getType().equals(typeFilter))))) {
					Company company = new Company();
					company.set_id(info.get_id());
					company.setName(info.getName());
					company.setAddress(info.getAddress());
					company.setStatus(info.getStatus());
					company.setType(info.getType());
					company.setPositions(info.getPositions());
					company.setProgress(info.getProgress());
					company.setWebsite(info.getWebsite());
					companyList.add(company);
				}
			}
			CompanyResult result = new CompanyResult();
			// 公司按进度排序
			Collections.sort(companyList, new Comparator<Company>() {
				public int compare(Company arg0, Company arg1) {
					return ApplyUtils.getCompareProgress(arg0.getType(), arg0.getStatus(), arg1.getType(),
							arg1.getStatus());
				}
			});
			result.setCompanyList(companyList);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ApplicationResult getApplicationResult(int dateFilter, int statusFilter) {
		try {
			ListApplicationInfoParams listApplicationInfoParams = new ListApplicationInfoParams();
			List<ApplicationInfo> infoList = applyService.listApplicationInfo(listApplicationInfoParams);
			if (infoList == null || infoList.size() == 0) {
				return null;
			}
			List<Application> applicationList = new ArrayList<Application>();
			for (ApplicationInfo info : infoList) {
				// 只添加有效期内的网申
				if ((statusFilter == 9 || statusFilter == info.getStatus())
						&& (ApplyUtils.isApplicaitonValidDate(info.getBeginDate(), info.getEndDate(), dateFilter))) {
					Application application = getApplication(info);
					applicationList.add(application);
				}
			}
			// 网申按结束时间排序
			ApplicationResult result = new ApplicationResult();
			Collections.sort(applicationList, new Comparator<Application>() {
				public int compare(Application a, Application b) {
					return ApplyUtils.getCompareString(a.getEndDate(), b.getEndDate());
				}
			});
			result.setApplicationList(applicationList);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Application getApplication(ApplicationInfo info) {
		if (info == null) {
			return null;
		}
		Application application = new Application();
		application.set_id(info.get_id());
		application.setCompanyId(info.getCompanyId());
		application.setCompanyName(info.getCompanyName());
		application.setBeginDate(info.getBeginDate());
		application.setEndDate(info.getEndDate());
		application.setWebsite(info.getWebsite());
		application.setStatus(info.getStatus());
		application.setProgress(ApplyUtils.getStatus(Constants.APPLY_TYPE_APPLICATION, info.getStatus()));
		application.setPositions(info.getPositions());
		return application;
	}

	@Override
	public TeachinResult getTeachinResult(int dateFilter, int statusFilter) {
		try {
			ListTeachinInfoParams listTeachinInfoParams = new ListTeachinInfoParams();
			List<TeachinInfo> infoList = applyService.listTeachinInfo(listTeachinInfoParams);
			if (infoList == null || infoList.size() == 0) {
				return null;
			}
			List<Teachin> teachinList = new ArrayList<Teachin>();
			for (TeachinInfo info : infoList) {
				// 只添一周内未开始的宣讲会
				if ((statusFilter == 9 || statusFilter == info.getStatus())
						&& (ApplyUtils.isTeachinValidDate(info.getDate(), dateFilter))) {
					Teachin teachin = getTeachin(info);
					teachinList.add(teachin);
				}
			}
			TeachinResult result = new TeachinResult();
			// 宣讲会按时间排序
			Collections.sort(teachinList, new Comparator<Teachin>() {
				public int compare(Teachin a, Teachin b) {
					return ApplyUtils.getCompareString(a.getDate(), b.getDate());
				}
			});
			result.setTeachinList(teachinList);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Teachin getTeachin(TeachinInfo info) {
		if (info == null) {
			return null;
		}
		Teachin teachin = new Teachin();
		teachin.set_id(info.get_id());
		teachin.setCompanyId(info.getCompanyId());
		teachin.setCompanyName(info.getCompanyName());
		teachin.setSchool(info.getSchool());
		teachin.setAddress(info.getAddress());
		teachin.setDate(info.getDate());
		teachin.setStatus(info.getStatus());
		teachin.setProgress(ApplyUtils.getStatus(Constants.APPLY_TYPE_TEACHIN, info.getStatus()));
		teachin.setMarks(info.getMarks());
		teachin.setPositions(info.getPositions());
		return teachin;
	}

	@Override
	public ExaminationResult getExaminationResult(int dateFilter, int statusFilter) {
		try {
			ListExaminationInfoParams listExaminationInfoParams = new ListExaminationInfoParams();
			List<ExaminationInfo> infoList = applyService.listExaminationInfo(listExaminationInfoParams);
			if (infoList == null || infoList.size() == 0) {
				return null;
			}
			List<Examination> examinationList = new ArrayList<Examination>();
			for (ExaminationInfo info : infoList) {
				// 只添一周内未开始的笔试
				if ((statusFilter == 9 || statusFilter == info.getStatus())
						&& (ApplyUtils.isExaminationValidDate(info.getDate(), dateFilter))) {
					Examination examination = getExamination(info);
					examinationList.add(examination);
				}
			}
			ExaminationResult result = new ExaminationResult();

			// 笔试按时间排序
			Collections.sort(examinationList, new Comparator<Examination>() {
				public int compare(Examination a, Examination b) {
					return ApplyUtils.getCompareString(a.getDate(), b.getDate());
				}
			});
			result.setExaminationList(examinationList);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Examination getExamination(ExaminationInfo info) {
		if (info == null) {
			return null;
		}
		Examination examination = new Examination();
		examination.set_id(info.get_id());
		examination.setCompanyId(info.getCompanyId());
		examination.setCompanyName(info.getCompanyName());
		examination.setSchool(info.getSchool());
		examination.setAddress(info.getAddress());
		examination.setDate(info.getDate());
		examination.setStatus(info.getStatus());
		examination.setProgress(ApplyUtils.getStatus(Constants.APPLY_TYPE_EXAMINATION, info.getStatus()));
		examination.setMarks(info.getMarks());
		examination.setPositions(info.getPositions());
		return examination;
	}

	@Override
	public InterviewResult getInterviewResult(int dateFilter, int statusFilter) {
		try {
			ListInterviewInfoParams listInterviewInfoParams = new ListInterviewInfoParams();
			List<InterviewInfo> infoList = applyService.listInterviewInfo(listInterviewInfoParams);
			if (infoList == null || infoList.size() == 0) {
				return null;
			}
			List<Interview> interviewList = new ArrayList<Interview>();
			for (InterviewInfo info : infoList) {
				// 只添一周内未开始的面试
				if ((statusFilter == 9 || statusFilter == info.getStatus())
						&& (ApplyUtils.isInterviewValidDate(info.getDate(), dateFilter))) {
					Interview interview = getInterview(info);
					interviewList.add(interview);
				}
			}
			InterviewResult result = new InterviewResult();
			// 面试按时间排序
			Collections.sort(interviewList, new Comparator<Interview>() {
				public int compare(Interview a, Interview b) {
					return ApplyUtils.getCompareString(a.getDate(), b.getDate());
				}
			});
			result.setInterviewList(interviewList);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Interview getInterview(InterviewInfo info) {
		if (info == null) {
			return null;
		}
		Interview interview = new Interview();
		interview.set_id(info.get_id());
		interview.setCompanyId(info.getCompanyId());
		interview.setCompanyName(info.getCompanyName());
		interview.setSchool(info.getSchool());
		interview.setAddress(info.getAddress());
		interview.setDate(info.getDate());
		interview.setStatus(info.getStatus());
		interview.setProgress(ApplyUtils.getStatus(Constants.APPLY_TYPR_INTERVIEW, info.getStatus()));
		interview.setMarks(info.getMarks());
		interview.setPositions(info.getPositions());
		return interview;
	}

	@Override
	public void deletePlan(String name, String type, String _id) {
		try {
			// 根据name 读取personal
			FindPersonalInfoParams findPersonalInfoParams = new FindPersonalInfoParams();
			findPersonalInfoParams.setName(name);
			PersonalInfo personalInfo = applyService.findPersonalInfo(findPersonalInfoParams);
			if (personalInfo != null) {
				CollectionInfo favor = personalInfo.getFavor();
				if (favor != null) {
					List<String> idList = new ArrayList<String>();
					if (type.equals(Constants.APPLY_TYPE_APPLICATION)) {
						idList = favor.getApplicationIdList();
						favor.setApplicationIdList(ApplyUtils.listDeleteId(idList, _id));
					} else if (type.equals(Constants.APPLY_TYPE_TEACHIN)) {
						idList = favor.getTeachinIdList();
						favor.setTeachinIdList(ApplyUtils.listDeleteId(idList, _id));
					} else if (type.equals(Constants.APPLY_TYPE_EXAMINATION)) {
						idList = favor.getExaminationIdList();
						favor.setExaminationIdList(ApplyUtils.listDeleteId(idList, _id));
					} else if (type.equals(Constants.APPLY_TYPR_INTERVIEW)) {
						idList = favor.getInterviewIdList();
						favor.setInterviewIdList(ApplyUtils.listDeleteId(idList, _id));
					}
					// 更新数据库中的personal
					personalInfo.setFavor(favor);
					personalInfo.setBirthday(new Date().toLocaleString());
					applyService.updatePersonalInfo(personalInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void changePlan(String type, String _id, String date, String companyName, int status, String address,
			String positions) {
		try {
			if (StringUtils.isBlank(type)) {
				return;
			}
			// 根据type判断修改的事哪一张表
			if (type.equals(Constants.APPLY_TYPE_APPLICATION)) {
				FindApplicationInfoParams findApplicationInfoParams = new FindApplicationInfoParams();
				findApplicationInfoParams.setApplicationId(_id);
				ApplicationInfo info = applyService.findApplicationInfo(findApplicationInfoParams);
				info.setCompanyName(companyName);
				info.setStatus(status);
				info.setWebsite(address);
				info.setPositions(positions);
				applyService.updateApplicationInfo(info);
			} else if (type.equals(Constants.APPLY_TYPE_TEACHIN)) {
				FindTeachinInfoParams findTeachinInfoParams = new FindTeachinInfoParams();
				findTeachinInfoParams.setTeachinId(_id);
				TeachinInfo info = applyService.findTeachinInfo(findTeachinInfoParams);
				info.setDate(date);
				info.setCompanyName(companyName);
				info.setStatus(status);
				info.setAddress(address);
				info.setPositions(positions);
				applyService.updateTeachinInfo(info);
			} else if (type.equals(Constants.APPLY_TYPE_EXAMINATION)) {
				FindExaminationInfoParams findExaminationInfoParams = new FindExaminationInfoParams();
				findExaminationInfoParams.setExaminationId(_id);
				ExaminationInfo info = applyService.findExaminationInfo(findExaminationInfoParams);
				info.setDate(date);
				info.setCompanyName(companyName);
				info.setStatus(status);
				info.setAddress(address);
				info.setPositions(positions);
				applyService.updateExaminationInfo(info);
			} else if (type.equals(Constants.APPLY_TYPR_INTERVIEW)) {
				FindInterviewInfoParams findInterviewInfoParams = new FindInterviewInfoParams();
				findInterviewInfoParams.setInterviewId(_id);
				InterviewInfo info = applyService.findInterviewInfo(findInterviewInfoParams);
				info.setDate(date);
				info.setCompanyName(companyName);
				info.setStatus(status);
				info.setAddress(address);
				info.setPositions(positions);
				applyService.updateInterviewInfo(info);
			} else {
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteCompany(String _id) {
		try {
			applyService.deleteCompanyInfo(_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteApplication(String _id) {
		try {
			applyService.deleteApplicationInfo(_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteTeachin(String _id) {
		try {
			applyService.deleteTeachinInfo(_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteExamination(String _id) {
		try {
			applyService.deleteExaminationInfo(_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteInterview(String _id) {
		try {
			applyService.deleteInterviewInfo(_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void changeCompany(String type, String _id, String name, String website, int status, String address,
			String positions) {
		try {
			if (StringUtils.isBlank(_id) && StringUtils.isBlank(name)) {
				return;
			}
			FindCompanyInfoParams findCompanyInfoParams = new FindCompanyInfoParams();
			if (StringUtils.isNotBlank(_id)) {
				findCompanyInfoParams.setCompanyId(_id);
			} else if (StringUtils.isNotBlank(name)) {
				findCompanyInfoParams.setCompanyName(name);
			}
			CompanyInfo info = applyService.findCompanyInfo(findCompanyInfoParams);
			if (info == null) {
				info = new CompanyInfo();
				info.set_id(_id);
			}
			info.setName(name);
			info.setWebsite(website);
			info.setType(type);
			info.setStatus(status);
			info.setProgress(ApplyUtils.getStatus(type, status));
			info.setAddress(address);
			info.setPositions(positions);
			applyService.updateCompanyInfo(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void changeApplication(String _id, String companyName, String beginDate, String endDate, String website,
			int status, String positions) {
		try {
			FindApplicationInfoParams findApplicationInfoParams = new FindApplicationInfoParams();
			if (StringUtils.isNotBlank(_id)) {
				findApplicationInfoParams.setApplicationId(_id);
			}
			ApplicationInfo info = applyService.findApplicationInfo(findApplicationInfoParams);
			if (info == null) {
				info = new ApplicationInfo();
				info.set_id(_id);
			}
			info.setCompanyName(info.getCompanyName());
			if (ApplyUtils.isTimeLegal(beginDate)) {
				info.setBeginDate(beginDate);
			}
			if (ApplyUtils.isTimeLegal(endDate)) {
				info.setEndDate(endDate);
			}
			info.setWebsite(website);
			info.setStatus(status);
			info.setPositions(positions);
			applyService.updateApplicationInfo(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void changeTeachin(String _id, String companyName, String date, String school, String address,
			String positions) {
		try {
			FindTeachinInfoParams findTeachinInfoParams = new FindTeachinInfoParams();
			if (StringUtils.isNotBlank(_id)) {
				findTeachinInfoParams.setTeachinId(_id);
			}
			TeachinInfo info = applyService.findTeachinInfo(findTeachinInfoParams);
			if (info == null) {
				info = new TeachinInfo();
				info.set_id(_id);
			}
			info.setCompanyName(companyName);
			if (ApplyUtils.isTimeLegal(date)) {
				info.setDate(date);
			}
			info.setSchool(school);
			info.setAddress(address);
			info.setPositions(positions);
			applyService.updateTeachinInfo(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void changeExamination(String _id, String companyName, String date, String school, String address,
			String positions, int status) {
		try {
			FindExaminationInfoParams findExaminationInfoParams = new FindExaminationInfoParams();
			if (StringUtils.isNotBlank(_id)) {
				findExaminationInfoParams.setExaminationId(_id);
			}
			ExaminationInfo info = applyService.findExaminationInfo(findExaminationInfoParams);
			if (info == null) {
				info = new ExaminationInfo();
				info.set_id(_id);
			}
			info.setCompanyName(companyName);
			if (ApplyUtils.isTimeLegal(date)) {
				info.setDate(date);
			}
			info.setSchool(school);
			info.setAddress(address);
			info.setPositions(positions);
			info.setStatus(status);
			applyService.updateExaminationInfo(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void changeInterview(String _id, String companyName, String date, String school, String address,
			String positions, int status) {
		try {
			FindInterviewInfoParams findInterviewInfoParams = new FindInterviewInfoParams();
			if (StringUtils.isNotBlank(_id)) {
				findInterviewInfoParams.setInterviewId(_id);
			}
			InterviewInfo info = applyService.findInterviewInfo(findInterviewInfoParams);
			if (info == null) {
				info = new InterviewInfo();
				info.set_id(_id);
			}
			info.setCompanyName(companyName);
			if (ApplyUtils.isTimeLegal(date)) {
				info.setDate(date);
			}
			info.setSchool(school);
			info.setAddress(address);
			info.setPositions(positions);
			info.setStatus(status);
			applyService.updateInterviewInfo(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public CompanyDetail getCompanyDetail(String _id) {
		try {
			if (StringUtils.isBlank(_id)) {
				return null;
			}
			FindCompanyInfoParams findCompanyInfoParams = new FindCompanyInfoParams();
			findCompanyInfoParams.setCompanyId(_id);
			CompanyInfo info = applyService.findCompanyInfo(findCompanyInfoParams);
			if (info == null) {
				return null;
			}
			CompanyDetail detail = new CompanyDetail();
			detail.set_id(info.get_id());
			detail.setName(info.getName());
			detail.setCompanyType(info.getCompanyType());
			detail.setIntro(info.getIntro());
			detail.setAddress(info.getAddress());
			detail.setTelphone(info.getTelphone());
			detail.setWebsite(info.getWebsite());
			detail.setApplicationList(getApplicationList(info.getApplicationIdList()));
			if (detail.getApplicationList() == null || detail.getApplicationList().size() == 0) {
				detail.setApplicationListSize(0);
			} else {
				detail.setApplicationListSize(detail.getApplicationList().size());
			}
			detail.setTeachinList(getTeachinList(info.getTeachinIdList()));
			if (detail.getTeachinList() == null || detail.getTeachinList().size() == 0) {
				detail.setTeachinListSize(0);
			} else {
				detail.setTeachinListSize(detail.getTeachinList().size());
			}
			detail.setExaminationList(getExaminationLis(info.getExaminationIdList()));
			if (detail.getExaminationList() == null || detail.getExaminationList().size() == 0) {
				detail.setExaminationListSize(0);
			} else {
				detail.setExaminationListSize(detail.getExaminationList().size());
			}
			detail.setInterviewList(getInterviewList(info.getInterviewIdList()));
			if (detail.getInterviewList() == null || detail.getInterviewList().size() == 0) {
				detail.setInterviewListSize(0);
			} else {
				detail.setInterviewListSize(detail.getInterviewList().size());
			}
			detail.setProgress(info.getProgress());
			detail.setMarks(info.getMarks());
			detail.setPositions(info.getPositions());
			detail.setStatus(info.getStatus());
			detail.setType(info.getType());
			return detail;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<Interview> getInterviewList(List<String> idList) {
		try {
			if (idList == null || idList.size() == 0) {
				return null;
			}
			ListInterviewInfoParams listInterviewInfoParams = new ListInterviewInfoParams();
			listInterviewInfoParams.setInterviewIdList(idList);
			List<InterviewInfo> infoList = applyService.listInterviewInfo(listInterviewInfoParams);
			if (infoList == null || infoList.size() == 0) {
				return null;
			}
			List<Interview> tempList = new ArrayList<Interview>();
			for (InterviewInfo info : infoList) {
				Interview temp = getInterview(info);
				tempList.add(temp);
			}
			return tempList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<Examination> getExaminationLis(List<String> idList) {
		try {
			if (idList == null || idList.size() == 0) {
				return null;
			}
			ListExaminationInfoParams listExaminationInfoParams = new ListExaminationInfoParams();
			listExaminationInfoParams.setExaminationIdList(idList);
			List<ExaminationInfo> infoList = applyService.listExaminationInfo(listExaminationInfoParams);
			if (infoList == null || infoList.size() == 0) {
				return null;
			}
			List<Examination> tempList = new ArrayList<Examination>();
			for (ExaminationInfo info : infoList) {
				Examination temp = getExamination(info);
				tempList.add(temp);
			}
			return tempList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<Teachin> getTeachinList(List<String> idList) {
		try {
			if (idList == null || idList.size() == 0) {
				return null;
			}
			ListTeachinInfoParams listTeachinInfoParams = new ListTeachinInfoParams();
			listTeachinInfoParams.setTeachinIdList(idList);
			List<TeachinInfo> infoList = applyService.listTeachinInfo(listTeachinInfoParams);
			if (infoList == null || infoList.size() == 0) {
				return null;
			}
			List<Teachin> tempList = new ArrayList<Teachin>();
			for (TeachinInfo info : infoList) {
				Teachin temp = getTeachin(info);
				tempList.add(temp);
			}
			return tempList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<Application> getApplicationList(List<String> idList) {
		try {
			if (idList == null || idList.size() == 0) {
				return null;
			}
			ListApplicationInfoParams listApplicationInfoParams = new ListApplicationInfoParams();
			listApplicationInfoParams.setApplicationIdList(idList);
			List<ApplicationInfo> infoList = applyService.listApplicationInfo(listApplicationInfoParams);
			if (infoList == null || infoList.size() == 0) {
				return null;
			}
			List<Application> tempList = new ArrayList<Application>();
			for (ApplicationInfo info : infoList) {
				Application temp = getApplication(info);
				tempList.add(temp);
			}
			return tempList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void collect(String name, String _id, String type) {
		try {
			if (StringUtils.isBlank(name) || StringUtils.isBlank(_id) || StringUtils.isBlank(type)) {
				return;
			}
			FindPersonalInfoParams findPersonalInfoParams = new FindPersonalInfoParams();
			findPersonalInfoParams.setName(name);
			PersonalInfo personalInfo = applyService.findPersonalInfo(findPersonalInfoParams);
			if (personalInfo == null) {
				return;
			}
			CollectionInfo collectionInfo = null;
			if (personalInfo.getFavor() == null) {
				collectionInfo = new CollectionInfo();
				List<String> list = new ArrayList<String>();
				list.add(_id);
				if (type.equals(Constants.APPLY_TYPE_APPLICATION)) {
					collectionInfo.setApplicationIdList(list);
				} else if (type.equals(Constants.APPLY_TYPE_TEACHIN)) {
					collectionInfo.setTeachinIdList(list);
				} else if (type.equals(Constants.APPLY_TYPE_EXAMINATION)) {
					collectionInfo.setExaminationIdList(list);
				} else if (type.equals(Constants.APPLY_TYPR_INTERVIEW)) {
					collectionInfo.setInterviewIdList(list);
				} else {
					return;
				}
			} else {
				collectionInfo = personalInfo.getFavor();
				List<String> list = new ArrayList<String>();
				if (type.equals(Constants.APPLY_TYPE_APPLICATION)) {
					list = collectionInfo.getApplicationIdList();
				} else if (type.equals(Constants.APPLY_TYPE_TEACHIN)) {
					list = collectionInfo.getTeachinIdList();
				} else if (type.equals(Constants.APPLY_TYPE_EXAMINATION)) {
					list = collectionInfo.getExaminationIdList();
				} else if (type.equals(Constants.APPLY_TYPR_INTERVIEW)) {
					list = collectionInfo.getInterviewIdList();
				} else {
					return;
				}
				// 判断list是否为空
				if (list != null && list.size() > 0) {
					// 判断_id是否已经存在idList中，若存在不操作
					Iterator<String> sListIterator = list.iterator();
					while (sListIterator.hasNext()) {
						String e = sListIterator.next();
						if (e.equals(_id)) {
							return;
						}
					}
				} else {
					list = new ArrayList<String>();
				}
				// 添加_id到idList中前
				list.add(_id);
				// 设置新的favor
				if (type.equals(Constants.APPLY_TYPE_APPLICATION)) {
					collectionInfo.setApplicationIdList(list);
				} else if (type.equals(Constants.APPLY_TYPE_TEACHIN)) {
					collectionInfo.setTeachinIdList(list);
				} else if (type.equals(Constants.APPLY_TYPE_EXAMINATION)) {
					collectionInfo.setExaminationIdList(list);
				} else if (type.equals(Constants.APPLY_TYPR_INTERVIEW)) {
					collectionInfo.setInterviewIdList(list);
				} else {
					return;
				}
				personalInfo.setFavor(collectionInfo);
				// 更新personalInfo
				applyService.updatePersonalInfo(personalInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendMail(String type, String _id, String date, String companyName, int status, String address,
			String positions, String subject, String sendTime, String marks, String userName, String email,
			String school, String website, String beginDate, String endDate) {
		try {
			Date sendT = ApplyUtils.getSendTime(sendTime);
			PlanMail mail = new PlanMail(type, _id, date, companyName, status, address, positions, subject, sendT,
					marks, userName, email);
			System.out.println("邮件发送时间"+sendT.toLocaleString());
			new ApplyMialTimter().doOnce(new ApplyMailTask(type, _id, date, companyName, status, address, positions, subject, sendT,
					marks, userName, email,school,website,beginDate,endDate), sendT);
		} catch ( ParseException e)	{
			e.printStackTrace();
		}
	}
}
