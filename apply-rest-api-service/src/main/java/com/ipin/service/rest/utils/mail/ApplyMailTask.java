package com.ipin.service.rest.utils.mail;

import java.util.Date;
import java.util.TimerTask;

import org.apache.commons.lang3.StringUtils;

import com.ipin.service.rest.beans.apply.PlanMail;
import com.ipin.service.rest.utils.ApplyUtils;

public class ApplyMailTask extends TimerTask {
	String type;
	private String _id;
	private String date;
	private String companyName;
	private int status;
	private String address;
	private String positions;
	private String subject;
	private Date sendTime;
	private String marks;
	private String userName;
	private String email;
	private String school;
	private String website;
	private String beginDate;
	private String endDate;

	public ApplyMailTask() {
		super();
	}

	public ApplyMailTask(String type, String _id, String date, String companyName, int status, String address,
			String positions, String subject, Date sendTime, String marks, String userName, String email, String school,
			String website, String beginDate, String endDate) {
		super();
		this.type = type;
		this._id = _id;
		this.date = date;
		this.companyName = companyName;
		this.status = status;
		this.address = address;
		this.positions = positions;
		this.subject = subject;
		this.sendTime = sendTime;
		this.marks = marks;
		this.userName = userName;
		this.email = email;
		this.school = school;
		this.website = website;
		this.beginDate = beginDate;
		this.endDate = endDate;
	}

	@Override
	public void run() {
		System.out.println(new Date());
		System.out.println("RUN" + email);
		System.out.println("RUN" + sendTime);
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.126.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("linyk3@126.com");
		mailInfo.setPassword("linyk:126");// 您的邮箱密码
		mailInfo.setFromAddress("linyk3@126.com");

		mailInfo.setToAddress(email);
		mailInfo.setSubject(subject);
		String con = "Hi !" + userName + "\r\n" + "这是来自校园招聘信息管理系统的提醒通知：\r\n";
		if (StringUtils.isNotBlank(date) && !date.equals("undefined") ) {
			con = con + "时间：" + date + "\r\n";
		}
		if (StringUtils.isNotBlank(beginDate) && !beginDate.equals("undefined")) {
			con = con + "开始时间：" + beginDate + "\r\n";
		}
		if (StringUtils.isNotBlank(endDate) && !endDate.equals("undefined") ) {
			con = con + "结束时间：" + endDate + "\r\n";
		}
		if (StringUtils.isNotBlank(companyName)) {
			con = con + "公司：" + companyName + "\r\n";
		}
		if (StringUtils.isNotBlank(website) && !website.equals("undefined")) {
			con = con + "网址：" + website + "\r\n";
		}
		if (StringUtils.isNotBlank(type)) {
			con = con + "进度：" + ApplyUtils.getStatus(type, status) + "\r\n";
		}
		if (StringUtils.isNotBlank(school) && !school.equals("undefined")) {
			con = con + "学校：" + school + "\r\n";
		}
		if (StringUtils.isNotBlank(address) && !address.equals("undefined")) {
			con = con + "地点：" + address + "\r\n";
		}
		if (StringUtils.isNotBlank(positions)) {
			con = con + "岗位：" + positions + "\r\n";
		}
		if (StringUtils.isNotBlank(marks)) {
			con = con + "其他：" + marks + "\r\n";
		}

		mailInfo.setContent(con);

		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		sms.sendTextMail(mailInfo);// 发送文体格式
		// sms.sendHtmlMail(mailInfo);// 发送html格式
		System.out.println("邮件发送内容：\r\n" + mailInfo.toString());
		// System.out.println(mail);
		System.out.println("邮件发送!");
	}
}
