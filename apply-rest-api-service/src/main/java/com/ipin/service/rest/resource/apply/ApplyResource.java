package com.ipin.service.rest.resource.apply;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ipin.service.rest.beans.apply.ApplicationResult;
import com.ipin.service.rest.beans.apply.CompanyDetail;
import com.ipin.service.rest.beans.apply.CompanyResult;
import com.ipin.service.rest.beans.apply.ExaminationResult;
import com.ipin.service.rest.beans.apply.InterviewResult;
import com.ipin.service.rest.beans.apply.PlanResult;
import com.ipin.service.rest.beans.apply.TeachinResult;
import com.ipin.service.rest.beans.apply.UserResult;
import com.ipin.service.rest.service.ApplyRestService;

@Component
@Path("/apply")
public class ApplyResource {
	
	@Autowired
	private ApplyRestService applyRestService;
	
	@GET
	@Path("res")
	@Produces({ MediaType.APPLICATION_JSON })
	public UserResult getUserResult(@QueryParam("name") String name) {
		UserResult userResult = applyRestService.findUser(name);
		return userResult;
	}
	
	@GET
	@Path("plan")
	@Produces({ MediaType.APPLICATION_JSON })
	public PlanResult getPlan(@QueryParam("name") String name,@QueryParam("dateFilter") int dateFilter, @QueryParam("typeFilter") String typeFilter,@QueryParam("statusFilter") int statusFilter) {
		PlanResult planResult = applyRestService.getPlanResult(name,dateFilter,typeFilter,statusFilter);
		return planResult;
	}
	
	@GET
	@Path("plan/delete")
	@Produces({ MediaType.APPLICATION_JSON })
	public void deletePlan(@QueryParam("name") String name, @QueryParam("type") String type, @QueryParam("_id") String _id) {
		applyRestService.deletePlan(name,type,_id);
	}
	
	@GET
	@Path("plan/change")
	@Produces({ MediaType.APPLICATION_JSON })
	public void changePlan(@QueryParam("type") String type, @QueryParam("_id") String _id, @QueryParam("date") String date, @QueryParam("companyName") String companyName, @QueryParam("status") int status, @QueryParam("address") String address, @QueryParam("positions") String positions    ) {
		System.out.println("plan---change");
		applyRestService.changePlan(type,_id,date,companyName,status,address,positions);
	}
//	@POST
//	@Path("plan/delete")
//	@Produces({ MediaType.APPLICATION_JSON })
//	public void getPost(@ModelAttribute("type") String type, @ModelAttribute("_id") String _id) {
//		System.out.print(type);
//		System.out.print(_id);
//		System.out.print("fjasdlkhttp://www.baidu.com/#ie=UTF-8&wd=ipinjfa;lsdkjfa;lsdkf");
//	}
	
	@GET
	@Path("company")
	@Produces({ MediaType.APPLICATION_JSON })
	public CompanyResult getCompany(@QueryParam("typeFilter") String typeFilter, @QueryParam("statusFilter") int statusFilter) {
		CompanyResult companyResult = applyRestService.getCompanyResult(typeFilter,statusFilter);
		return companyResult;
	}
	
	@GET
	@Path("/company/delete")
	@Produces({ MediaType.APPLICATION_JSON })
	public void deleteCompany(@QueryParam("_id") String _id) {
		System.out.println("comapany delete resource");
		applyRestService.deleteCompany(_id);
	}
	
	@GET
	@Path("company/change")
	@Produces({ MediaType.APPLICATION_JSON })
	public void changeCompany(@QueryParam("type") String type, @QueryParam("_id") String _id,  @QueryParam("name") String name, @QueryParam("website") String website,@QueryParam("status") int status, @QueryParam("address") String address, @QueryParam("positions") String positions    ) {
		System.out.println("company---change");
		applyRestService.changeCompany(type,_id,name,website,status,address,positions);
	}
	
	@GET
	@Path("company/detail")
	@Produces({ MediaType.APPLICATION_JSON })
	public CompanyDetail getCompanyInfoDetail(@QueryParam("_id") String _id) {
		System.out.println(_id);
		CompanyDetail detail = applyRestService.getCompanyDetail(_id);
		return detail;
	}
	
	@GET
	@Path("application")
	@Produces({ MediaType.APPLICATION_JSON })
	public ApplicationResult getApplication(@QueryParam("dateFilter") int dateFilter,@QueryParam("statusFilter") int statusFilter) {
		ApplicationResult applicationResult = applyRestService.getApplicationResult(dateFilter,statusFilter);
		return applicationResult;
	}
	
	@GET
	@Path("/application/delete")
	@Produces({ MediaType.APPLICATION_JSON })
	public void deleteApplication(@QueryParam("name") String name,@QueryParam("_id") String _id) {
		applyRestService.deleteApplication(_id);
		applyRestService.deletePlan(name,"网申",_id);
	}
	
	@GET
	@Path("application/change")
	@Produces({ MediaType.APPLICATION_JSON })
	public void changeApplication(@QueryParam("beginDate") String beginDate,@QueryParam("endDate") String endDate,  @QueryParam("_id") String _id,  @QueryParam("companyName") String companyName, @QueryParam("website") String website,@QueryParam("status") int status,  @QueryParam("positions") String positions    ) {
		applyRestService.changeApplication(_id,companyName,beginDate,endDate,website,status,positions);
	}
	
	@GET
	@Path("teachin")
	@Produces({ MediaType.APPLICATION_JSON })
	public TeachinResult getTeachin(@QueryParam("dateFilter") int dateFilter, @QueryParam("statusFilter") int statusFilter) {
		TeachinResult teachinResult = applyRestService.getTeachinResult(dateFilter,statusFilter);
		return teachinResult;
	}
	
	@GET
	@Path("/teachin/delete")
	@Produces({ MediaType.APPLICATION_JSON })
	public void deleteTeachin(@QueryParam("name") String name,@QueryParam("_id") String _id) {
		applyRestService.deleteTeachin(_id);
		applyRestService.deletePlan(name,"宣讲会",_id);
	}
	
	@GET
	@Path("teachin/change")
	@Produces({ MediaType.APPLICATION_JSON })
	public void changeTeachin(@QueryParam("date") String date,@QueryParam("school") String school,  @QueryParam("_id") String _id,  @QueryParam("companyName") String companyName, @QueryParam("address") String address, @QueryParam("positions") String positions    ) {
		applyRestService.changeTeachin(_id,companyName,date,school,address,positions);
	}
	
	@GET
	@Path("examination")
	@Produces({ MediaType.APPLICATION_JSON })
	public ExaminationResult getExamination(@QueryParam("dateFilter") int dateFilter, @QueryParam("statusFilter") int statusFilter) {
		ExaminationResult examinationResult = applyRestService.getExaminationResult(dateFilter, statusFilter);
		return examinationResult;
	}
	
	@GET
	@Path("/examination/delete")
	@Produces({ MediaType.APPLICATION_JSON })
	public void deleteExamination(@QueryParam("_id") String _id,@QueryParam("name") String name) {
		applyRestService.deleteExamination(_id);
		applyRestService.deletePlan(name,"笔试",_id);
	}
	
	@GET
	@Path("examination/change")
	@Produces({ MediaType.APPLICATION_JSON })
	public void changeExamination(@QueryParam("date") String date,@QueryParam("school") String school,  @QueryParam("_id") String _id,  @QueryParam("companyName") String companyName, @QueryParam("address") String address,@QueryParam("status") int status,  @QueryParam("positions") String positions    ) {
		applyRestService.changeExamination(_id,companyName,date,school,address,positions,status);
	}
	
	@GET
	@Path("interview")
	@Produces({ MediaType.APPLICATION_JSON })
	public InterviewResult getInterview(@QueryParam("dateFilter") int dateFilter, @QueryParam("statusFilter") int statusFilter) {
		InterviewResult interviewResult = applyRestService.getInterviewResult(dateFilter, statusFilter);
		return interviewResult;
	}
	
	@GET
	@Path("/interview/delete")
	@Produces({ MediaType.APPLICATION_JSON })
	public void deleteInterview(@QueryParam("name") String name,@QueryParam("_id") String _id) {
		applyRestService.deleteInterview(_id);
		applyRestService.deletePlan(name,"面试",_id);
	}
	
	@GET
	@Path("interview/change")
	@Produces({ MediaType.APPLICATION_JSON })
	public void changeInterview(@QueryParam("date") String date,@QueryParam("school") String school,  @QueryParam("_id") String _id,  @QueryParam("companyName") String companyName, @QueryParam("address") String address,@QueryParam("status") int status,  @QueryParam("positions") String positions    ) {
		applyRestService.changeInterview(_id,companyName,date,school,address,positions,status);
	}
	
	@GET
	@Path("test")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<UserResult> listUserResult(@QueryParam("name") String name) {
		List<UserResult> list = applyRestService.listUsers();
		return list;
	}
	
	
	@GET
	@Path("/collect")
	@Produces({ MediaType.APPLICATION_JSON })
	public void collect(@QueryParam("name") String name,@QueryParam("_id") String _id,@QueryParam("type") String type) {
		if(StringUtils.isBlank(name) || StringUtils.isBlank(_id) ||StringUtils.isBlank(type)) {
			return;
		}
		applyRestService.collect(name,_id,type);
	}
	
	@GET
	@Path("mail")
	@Produces({ MediaType.APPLICATION_JSON })
	public void sendMail(@QueryParam("type") String type, @QueryParam("_id") String _id, @QueryParam("date") String date, 
			@QueryParam("companyName") String companyName, @QueryParam("status") int status, 
			@QueryParam("address") String address, @QueryParam("positions") String positions,
			@QueryParam("subject") String subject, @QueryParam("sendTime") String sendTime, @QueryParam("marks") String marks, 
			@QueryParam("userName") String userName, @QueryParam("email") String email,
			@QueryParam("school") String school, @QueryParam("website") String website,
			@QueryParam("beginDate") String beginDate, @QueryParam("endDate") String endDate) {
		
		System.out.println("send mail " + sendTime);
		applyRestService.sendMail(type,_id,date,companyName,status,address,positions,subject,sendTime,marks,userName,email,school,website,beginDate,endDate);
	}
	
//	@POST
//	@Path("post")
//	@Produces({ MediaType.APPLICATION_JSON })
//	public void getPost(@ModelAttribute("title") String title) {
//		System.out.print(title);
//	}
	
}
