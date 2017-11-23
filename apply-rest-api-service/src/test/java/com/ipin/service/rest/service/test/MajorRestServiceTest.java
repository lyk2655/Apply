package com.ipin.service.rest.service.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.Assert;
import org.junit.Test;

import com.ipin.service.rest.beans.Industry;
import com.ipin.service.rest.beans.IndustryDist;
import com.ipin.service.rest.beans.IndustryListResult;
import com.ipin.service.rest.beans.JobFunctionDist;
import com.ipin.service.rest.beans.Location;
import com.ipin.service.rest.beans.LocationDist;
import com.ipin.service.rest.beans.LocationListResult;
import com.ipin.service.rest.beans.MajorDetailResult;
import com.ipin.service.rest.beans.MajorFilterListResult;
import com.ipin.service.rest.beans.MajorFilterParamsResult;
import com.ipin.service.rest.beans.MajorName;
import com.ipin.service.rest.beans.MajorNameListResult;
import com.ipin.service.rest.beans.MajorSecondCategoryNameListResult;
import com.ipin.service.rest.beans.PredictSalaryStat;
import com.ipin.service.rest.beans.RecommendSch;
import com.ipin.service.rest.beans.RecommendSchListResult;
import com.ipin.service.rest.beans.SalaryStat;


public class MajorRestServiceTest {
	
//	public static final String HOST = "http://192.168.0.31:8080/rest-api-service/";
	
//	public static final String HOST = "http://192.168.1.81:8082/rest-api-service/";
	
	public static final String HOST = "http://localhost:8080/";
	
	@Test
	public void testMajorDetail() throws Exception {
		
		IndustryListResult industryListResult = getIndustry();
		LocationListResult cityListResult = getCity();
		LocationListResult provinceListResult = getProvince();
		
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);

		// 查询本科专业信息
		Client client = ClientBuilder.newClient(clientConfig);
		String major_id = "52aedf5b747aec1cfc841596";
		String diploma_id = "7";
		String url = HOST + "major/" + major_id + "/detail?diploma_id=" + diploma_id;
		long startTime = System.currentTimeMillis();
		WebTarget webTarget = client.target(url);
		Builder request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		Response response = null;
		response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		MajorDetailResult majorDetailResult = new ObjectMapper().readValue(response.readEntity(String.class), MajorDetailResult.class);
		System.out.println(majorDetailResult.toString());
		
		Assert.assertTrue(9898 == majorDetailResult.getSalary_factor());
		Assert.assertTrue(97 == majorDetailResult.getSalary_factor_rank());
		Assert.assertTrue(majorDetailResult.getMajor_id().equals("52aedf5b747aec1cfc841596"));
		Assert.assertTrue(majorDetailResult.getMajor_second_category().equals("政治学类"));
		Assert.assertTrue(majorDetailResult.getMajor_category().equals("法学"));
		Assert.assertTrue(StringUtils.isNotBlank(majorDetailResult.getMajor_intro()));
		Assert.assertTrue(majorDetailResult.getSample_count() > 800);
		Assert.assertTrue(majorDetailResult.getIndustry_dis().size() == 47);
		IndustryDist industryDist = majorDetailResult.getIndustry_dis().get(0);
		Assert.assertTrue(0.1037 < industryDist.getRatio() && industryDist.getRatio() <= 0.1039);
		industryDist = majorDetailResult.getIndustry_dis().get(majorDetailResult.getIndustry_dis().size() - 1);
		Assert.assertTrue(industryDist.getRatio() == 0.0001);
		Assert.assertTrue(majorDetailResult.getIndustry_status().equals("较广"));
		Assert.assertTrue(majorDetailResult.getTop_worker_city().equals("110000000000"));
		Assert.assertTrue(0.41 <= majorDetailResult.getTop_worker_city_ratio() && majorDetailResult.getTop_worker_city_ratio() <= 0.43);
		Assert.assertTrue(0.22 < majorDetailResult.getGender_dis().get(0).getRatio() && majorDetailResult.getGender_dis().get(0).getRatio() <= 0.24);
		Assert.assertTrue(majorDetailResult.getGender_status().equals("女生较多"));
		List<Double> salaryList = new ArrayList<Double>();
		salaryList.add(3142.0);
		salaryList.add(4429.0);
		salaryList.add(5542.0);
		salaryList.add(6992.0);
		salaryList.add(7836.0);
		salaryList.add(9898.0);
		salaryList.add(10671.0);
		salaryList.add(12858.0);
		salaryList.add(13671.0);
		salaryList.add(16349.0);
		salaryList.add(18925.0);
		if(majorDetailResult.getSalary_predict_show_type() == 2) {
			assertSalaryStats(majorDetailResult.getSalary_stats(), salaryList);
		} else {
			assertPredictStats(majorDetailResult.getPredict_salary_stats(), salaryList);
		}
		salaryList = new ArrayList<Double>();
		salaryList.add(3068.0);
		salaryList.add(3877.0);
		salaryList.add(5283.0);
		salaryList.add(6145.0);
		salaryList.add(7676.0);
		salaryList.add(8237.0);
		salaryList.add(12666.0);
		salaryList.add(10485.0);
		salaryList.add(11240.0);
		salaryList.add(14269.0);
		salaryList.add(17205.0);
		assertSalaryStats(majorDetailResult.getCountry_salary_stats(), salaryList);
		List<Location> allLocationList = new ArrayList<Location>();
		allLocationList.addAll(cityListResult.getItems());
		allLocationList.addAll(provinceListResult.getItems());
		List<LocationDist> locationDistList = new ArrayList<LocationDist>();
		LocationDist locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("北京市", allLocationList));
		locationDist.setRatio(0.4284);
		locationDistList.add(locationDist);
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("广州市", allLocationList));
		locationDist.setRatio(0.0901);
		locationDistList.add(locationDist);
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("上海市", allLocationList));
		locationDist.setRatio(0.0831);
		locationDistList.add(locationDist);
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("深圳市", allLocationList));
		locationDist.setRatio(0.0531);
		locationDistList.add(locationDist);
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("武汉市", allLocationList));
		locationDist.setRatio(0.0462);
		locationDistList.add(locationDist);
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("西安市", allLocationList));
		locationDist.setRatio(0.0404);
		locationDistList.add(locationDist);
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("郑州市", allLocationList));
		locationDist.setRatio(0.0346);
		locationDistList.add(locationDist);
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("天津市", allLocationList));
		locationDist.setRatio(0.0346);
		locationDistList.add(locationDist);
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("新乡市", allLocationList));
		locationDist.setRatio(0.0139);
		locationDistList.add(locationDist);
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("厦门市", allLocationList));
		locationDist.setRatio(0.0127);
		locationDistList.add(locationDist);
		this.assertLocationDist(majorDetailResult.getLocation_dis(), locationDistList);
		List<JobFunctionDist> jobFunctionDistList = new ArrayList<JobFunctionDist>();
		JobFunctionDist jobFunctionDist = new JobFunctionDist();
		jobFunctionDist.setZhineng("影视/媒体/出版/印刷");
		jobFunctionDist.setRatio(0.1);
		jobFunctionDist.setPosition_list(toList(new String[]{"实习生","记者/采编","实习记者","编辑","编辑/撰稿","记者","实习编辑","兼职","实习","英语类"}));
		jobFunctionDist.setIndustry_list(toList(new String[]{"影视/传媒/出版","互联网/电子商务","其它","广告/会展/公关","娱乐/体育/休闲"}));
		jobFunctionDistList.add(jobFunctionDist);
		jobFunctionDist = new JobFunctionDist();
		jobFunctionDist.setZhineng("教育/培训");
		jobFunctionDist.setRatio(0.0819);
		jobFunctionDist.setPosition_list(toList(new String[]{"兼职教师","教学/教务管理人员","初中教师","讲师/助教","实习生","教师/培训师","英语教师","助教","兼职","中学教师"}));
		jobFunctionDist.setIndustry_list(toList(new String[]{"其它","金融/投资","广告/会展/公关","贸易/进出口","咨询等专业服务"}));
		jobFunctionDistList.add(jobFunctionDist);
		jobFunctionDist = new JobFunctionDist();
		jobFunctionDist.setZhineng("行政/后勤/文秘");
		jobFunctionDist.setRatio(0.078);
		jobFunctionDist.setPosition_list(toList(new String[]{"行政专员/助理","助理/秘书","经理助理/秘书","文员","行政经理/主管/办公室主任","行政主管","行政助理","秘书","综合秘书","办公室"}));
		jobFunctionDist.setIndustry_list(toList(new String[]{"其它","教育/培训/科研","汽车/摩托车","快速消费品","房地产/建筑"}));
		jobFunctionDistList.add(jobFunctionDist);
		jobFunctionDist = new JobFunctionDist();
		jobFunctionDist.setZhineng("市场");
		jobFunctionDist.setRatio(0.065);
		jobFunctionDist.setPosition_list(toList(new String[]{"市场经理","市场/营销/拓展经理","市场部","市场/营销/拓展专员","市场营销经理","市场专员/市场助理","产品/品牌专员","市场助理","促销员","市场策划专员"}));
		jobFunctionDist.setIndustry_list(toList(new String[]{"其它","互联网/电子商务","快速消费品","影视/传媒/出版","电子技术"}));
		jobFunctionDistList.add(jobFunctionDist);
		jobFunctionDist = new JobFunctionDist();
		jobFunctionDist.setZhineng("人力资源");
		jobFunctionDist.setRatio(0.052);
		jobFunctionDist.setPosition_list(toList(new String[]{"人力资源部","招聘专员/助理","人力资源主管","人事主管","人事专员","人事行政助理","培训经理/主管","人力资源专员/助理","人力资源专员","人事助理"}));
		jobFunctionDist.setIndustry_list(toList(new String[]{"通信/电信设备","交通/运输/物流","房地产/建筑","其它","通信/电信运营"}));
		jobFunctionDistList.add(jobFunctionDist);
		this.assertJobFunctionDist(majorDetailResult.getZhineng_dis(), jobFunctionDistList, industryListResult.getIndustries());
		
		// 查询专科专业信息
		client = ClientBuilder.newClient(clientConfig);
		major_id = "52aedf5b747aec1cfc841259";
		diploma_id = "5";
		url = HOST + "major/" + major_id + "/detail?diploma_id=" + diploma_id;
		startTime = System.currentTimeMillis();
		webTarget = client.target(url);
		request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		response = null;
		response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		majorDetailResult = new ObjectMapper().readValue(response.readEntity(String.class),
				MajorDetailResult.class);
		System.out.println(majorDetailResult.toString());

		Assert.assertTrue(-1 == majorDetailResult.getSalary_factor());
		Assert.assertTrue(-1 == majorDetailResult.getSalary_factor_rank());
		Assert.assertTrue(majorDetailResult.getMajor_id().equals("52aedf5b747aec1cfc841259"));
		Assert.assertTrue(majorDetailResult.getMajor_second_category().equals("农业技术类"));
		Assert.assertTrue(majorDetailResult.getMajor_category().equals("农林牧渔大类"));
		Assert.assertTrue(StringUtils.isNotBlank(majorDetailResult.getMajor_intro()));
		Assert.assertTrue(majorDetailResult.getSample_count() > 100);
//		Assert.assertTrue(majorDetailResult.getIndustry_dis().size() == 47);
		industryDist = majorDetailResult.getIndustry_dis().get(0);
		Assert.assertTrue(0.1711 < industryDist.getRatio() && industryDist.getRatio() <= 0.1713);
		industryDist = majorDetailResult.getIndustry_dis().get(majorDetailResult.getIndustry_dis().size() - 1);
		Assert.assertTrue(industryDist.getRatio() == 0.0001);
		Assert.assertTrue(majorDetailResult.getIndustry_status().equals("很集中"));
		Assert.assertTrue(majorDetailResult.getTop_worker_city().equals("110000000000"));
		Assert.assertTrue(0.19 <= majorDetailResult.getTop_worker_city_ratio()
				&& majorDetailResult.getTop_worker_city_ratio() <= 0.21);
		Assert.assertTrue(0.40 < majorDetailResult.getGender_dis().get(0).getRatio()
				&& majorDetailResult.getGender_dis().get(0).getRatio() <= 0.42);
		Assert.assertTrue(majorDetailResult.getGender_status().equals("女生较多"));
		salaryList = new ArrayList<Double>();
		salaryList.add(2653.0);
		salaryList.add(3254.0);
		salaryList.add(3446.0);
		salaryList.add(4143.0);
		salaryList.add(4144.0);
		salaryList.add(4539.0);
		salaryList.add(4502.28);
		if (majorDetailResult.getSalary_predict_show_type() == 2) {
			assertSalaryStats(majorDetailResult.getSalary_stats(), salaryList);
		} else {
			assertPredictStats(majorDetailResult.getPredict_salary_stats(), salaryList);
		}
		allLocationList = new ArrayList<Location>();
		allLocationList.addAll(cityListResult.getItems());
		allLocationList.addAll(provinceListResult.getItems());
		locationDistList = new ArrayList<LocationDist>();
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("北京市", allLocationList));
		locationDist.setRatio(0.2025);
		locationDistList.add(locationDist);
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("哈尔滨市", allLocationList));
		locationDist.setRatio(0.1076);
		locationDistList.add(locationDist);
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("石家庄市", allLocationList));
		locationDist.setRatio(0.0633);
		locationDistList.add(locationDist);
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("沈阳市", allLocationList));
		locationDist.setRatio(0.057);
		locationDistList.add(locationDist);
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("郑州市", allLocationList));
		locationDist.setRatio(0.038);
		locationDistList.add(locationDist);
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("上海市", allLocationList));
		locationDist.setRatio(0.0316);
		locationDistList.add(locationDist);
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("广州市", allLocationList));
		locationDist.setRatio(0.0253);
		locationDistList.add(locationDist);
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("昆明市", allLocationList));
		locationDist.setRatio(0.0253);
		locationDistList.add(locationDist);
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("唐山市", allLocationList));
		locationDist.setRatio(0.019);
		locationDistList.add(locationDist);
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("包头市", allLocationList));
		locationDist.setRatio(0.019);
		locationDistList.add(locationDist);
		this.assertLocationDist(majorDetailResult.getLocation_dis(), locationDistList);
		
	}
	
	@Test
	public void testRecommendSchoolList() throws Exception{
		IndustryListResult industryListResult = getIndustry();
		LocationListResult cityListResult = getCity();
		LocationListResult provinceListResult = getProvince();
		List<Location> allLocationList = new ArrayList<Location>();
		allLocationList.addAll(cityListResult.getItems());
		allLocationList.addAll(provinceListResult.getItems());
		
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);
		
		// 查询本科专业信息
		Client client = ClientBuilder.newClient(clientConfig);
		String major_id = "52aedf5b747aec1cfc841596";
		String diploma_id = "7";
		String url = HOST + "major/" + major_id + "/recommend_schools?diploma_id=" + diploma_id;
		long startTime = System.currentTimeMillis();
		WebTarget webTarget = client.target(url);
		Builder request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		Response response = null;
		response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		RecommendSchListResult recommendSchListResult = new ObjectMapper().readValue(response.readEntity(String.class),
				RecommendSchListResult.class);
		System.out.println(recommendSchListResult.toString());
		int size = 5;
		List<RecommendSch> recommendSchList = new ArrayList<RecommendSch>();
		RecommendSch recommendSch = new RecommendSch();
		recommendSch.setSch_id("52ac2e9b747aec013fcf53f1");
		recommendSch.setRank_index(1);
		recommendSch.setCity(this.getLocationId("长春市", allLocationList));
		recommendSch.setRank_str("A+");
		recommendSch.setSchool_type("综合院校");
		recommendSchList.add(recommendSch);
		recommendSch = new RecommendSch();
		recommendSch.setSch_id("52ac2e97747aec013fcf4a45");
		recommendSch.setRank_index(2);
		recommendSch.setCity(this.getLocationId("北京市", allLocationList));
		recommendSch.setRank_str("A+");
		recommendSch.setSchool_type("语言院校");
		recommendSchList.add(recommendSch);
		recommendSch = new RecommendSch();
		recommendSch.setSch_id("52ac2e9a747aec013fcf51d1");
		recommendSch.setRank_index(3);
		recommendSch.setCity(this.getLocationId("北京市", allLocationList));
		recommendSch.setRank_str("A-");
		recommendSch.setSchool_type("语言院校");
		recommendSchList.add(recommendSch);
		recommendSch = new RecommendSch();
		recommendSch.setSch_id("52ac2e9a747aec013fcf4fab");
		recommendSch.setRank_index(4);
		recommendSch.setCity(this.getLocationId("西安市", allLocationList));
		recommendSch.setRank_str("A-");
		recommendSch.setSchool_type("语言院校");
		recommendSchList.add(recommendSch);
		recommendSch = new RecommendSch();
		recommendSch.setSch_id("52ac2e97747aec013fcf49c9");
		recommendSch.setRank_index(5);
		recommendSch.setCity(this.getLocationId("重庆市", allLocationList));
		recommendSch.setRank_str("B");
		recommendSch.setSchool_type("语言院校");
		recommendSchList.add(recommendSch);
		
		Assert.assertTrue(size == recommendSchListResult.getSchools().size());
		for(int i = 0; i < recommendSchList.size(); i++) {
			RecommendSch testBean = recommendSchList.get(i);
			RecommendSch dataBean = recommendSchListResult.getSchools().get(i);
			Assert.assertTrue(testBean.getSch_id().equals(dataBean.getSch_id()));
			Assert.assertTrue(testBean.getRank_index() == dataBean.getRank_index());
			Assert.assertTrue(testBean.getSchool_type().equals(dataBean.getSchool_type()));
			Assert.assertTrue(testBean.getCity().equals(dataBean.getCity()));
			Assert.assertTrue(testBean.getRank_str().equals(dataBean.getRank_str()));
			System.out.println("通过推荐学校第" + (i + 1) + "个");
		}
		System.out.println("通过本科测试");
		
		// 查询专科专业信息
		client = ClientBuilder.newClient(clientConfig);
		major_id = "52aedf5b747aec1cfc841259";
		diploma_id = "5";
		url = HOST + "major/" + major_id + "/recommend_schools?diploma_id=" + diploma_id;
		startTime = System.currentTimeMillis();
		webTarget = client.target(url);
		request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		response = null;
		response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		recommendSchListResult = new ObjectMapper().readValue(response.readEntity(String.class),
				RecommendSchListResult.class);
		System.out.println(recommendSchListResult.toString());
		size = 6;
		recommendSchList = new ArrayList<RecommendSch>();
		recommendSch = new RecommendSch();
		recommendSch.setSch_id("52ac2e99747aec013fcf4e2c");
		recommendSch.setRank_index(1);
		recommendSch.setCity(this.getLocationId("杭州市", allLocationList));
		recommendSch.setRank_str("-");
		recommendSch.setSchool_type("工科院校");
		recommendSchList.add(recommendSch);
		recommendSch = new RecommendSch();
		recommendSch.setSch_id("52ac2e99747aec013fcf4eb4");
		recommendSch.setRank_index(2);
		recommendSch.setCity(this.getLocationId("温州市", allLocationList));
		recommendSch.setRank_str("-");
		recommendSch.setSchool_type("综合院校");
		recommendSchList.add(recommendSch);
		recommendSch = new RecommendSch();
		recommendSch.setSch_id("52ac2e99747aec013fcf4f17");
		recommendSch.setRank_index(3);
		recommendSch.setCity(this.getLocationId("临沧市", allLocationList));
		recommendSch.setRank_str("-");
		recommendSch.setSchool_type("师范院校");
		recommendSchList.add(recommendSch);
		recommendSch = new RecommendSch();
		recommendSch.setSch_id("52ac2e9a747aec013fcf4fce");
		recommendSch.setRank_index(4);
		recommendSch.setCity(this.getLocationId("哈尔滨市", allLocationList));
		recommendSch.setRank_str("-");
		recommendSch.setSchool_type("农业院校");
		recommendSchList.add(recommendSch);
		recommendSch = new RecommendSch();
		recommendSch.setSch_id("52ac2e9a747aec013fcf50dc");
		recommendSch.setRank_index(5);
		recommendSch.setCity(this.getLocationId("铜仁市", allLocationList));
		recommendSch.setRank_str("-");
		recommendSch.setSchool_type("综合院校");
		recommendSchList.add(recommendSch);
		recommendSch = new RecommendSch();
		recommendSch.setSch_id("52ac2e9b747aec013fcf53f5");
		recommendSch.setRank_index(6);
		recommendSch.setCity(this.getLocationId("银川市", allLocationList));
		recommendSch.setRank_str("-");
		recommendSch.setSchool_type("工科院校");
		recommendSchList.add(recommendSch);

		Assert.assertTrue(size == recommendSchListResult.getSchools().size());
		for (int i = 0; i < recommendSchList.size(); i++) {
			RecommendSch testBean = recommendSchList.get(i);
			RecommendSch dataBean = recommendSchListResult.getSchools().get(i);
			Assert.assertTrue(testBean.getSch_id().equals(dataBean.getSch_id()));
			Assert.assertTrue(testBean.getRank_index() == dataBean.getRank_index());
			Assert.assertTrue(testBean.getSchool_type().equals(dataBean.getSchool_type()));
			Assert.assertTrue(testBean.getCity().equals(dataBean.getCity()));
			Assert.assertTrue(testBean.getRank_str().equals(dataBean.getRank_str()));
			System.out.println("通过推荐学校第" + (i + 1) + "个");
		}
		System.out.println("通过专科测试");
	}
	
	@Test
	public void testMajorFilter() throws Exception{
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);
		
		// 查询本科专业信息
		Client client = ClientBuilder.newClient(clientConfig);
		String diploma_id = "7";
		String major_category = "";
		int count = 600;
		int page_no = 1;
		String url = HOST + "major?diploma_id=" + diploma_id + "&major_category=" + URLEncoder.encode(major_category, "utf-8") + "&count=" + count + "&page_no" + page_no;
		long startTime = System.currentTimeMillis();
		WebTarget webTarget = client.target(url);
		Builder request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		Response response = null;
		response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		MajorFilterListResult majorFilterListResult = new ObjectMapper().readValue(response.readEntity(String.class), MajorFilterListResult.class);
		System.out.println(majorFilterListResult.getMajors().size());
		
//		List<String> majorIdList = getBkMajorIdList();
		Assert.assertTrue(506 == majorFilterListResult.getMajors().size());
//		for(int i = 0; i < majorIdList.size(); i++) {
//			String majorId = majorIdList.get(i);
//			Assert.assertTrue(majorId.equals((majorFilterListResult.getMajors().get(i).getMajor_id())));
//		}
		System.out.println("通过本科专业测试");
		
		// 查询本科哲学
//		diploma_id = "7";
//		major_category = "哲学";
//		count = 10;
//		page_no = 1;
//		url = HOST + "major?diploma_id=" + diploma_id + "&major_category=" + URLEncoder.encode(major_category, "utf-8") + "&count=" + count + "&page_no" + page_no;
//		startTime = System.currentTimeMillis();
//		webTarget = client.target(url);
//		request = webTarget.request();
//		request.header("Content-type", MediaType.APPLICATION_JSON);
//		response = null;
//		response = request.get();
//		System.out.println(response.getStatus());
//		System.out.println(response.toString());
//		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
//		majorFilterListResult = new ObjectMapper().readValue(response.readEntity(String.class), MajorFilterListResult.class);
//		System.out.println(majorFilterListResult.getMajors().size());
//		
//		List<MajorFilter> testMajorFilterList = new ArrayList<MajorFilter>();
//		MajorFilter majorFilter = new MajorFilter();
//		majorFilter.setMajor_id("52aedf5b747aec1cfc84157c");
//		majorFilter.setDiploma_id(7);
//		majorFilter.setMajor_name("哲学");
//		majorFilter.setMajor_second_category("哲学类");
//		majorFilter.setMajor_catetory("哲学");
//		majorFilter.setRank_index(1);
//		majorFilter.setSalary_status_str("一般");
//		testMajorFilterList.add(majorFilter);
//		majorFilter = new MajorFilter();
//		majorFilter.setDiploma_id(7);
//		majorFilter.setMajor_id("52aedf5b747aec1cfc84157f");
//		majorFilter.setMajor_name("伦理学");
//		majorFilter.setMajor_second_category("哲学类");
//		majorFilter.setMajor_catetory("哲学");
//		majorFilter.setRank_index(2);
//		majorFilter.setSalary_status_str("很高");
//		testMajorFilterList.add(majorFilter);
//		majorFilter = new MajorFilter();
//		majorFilter.setDiploma_id(7);
//		majorFilter.setMajor_id("52aedf5b747aec1cfc84157d");
//		majorFilter.setMajor_name("逻辑学");
//		majorFilter.setMajor_second_category("哲学类");
//		majorFilter.setMajor_catetory("哲学");
//		majorFilter.setRank_index(3);
//		majorFilter.setSalary_status_str("一般");
//		testMajorFilterList.add(majorFilter);
//		majorFilter = new MajorFilter();
//		majorFilter.setDiploma_id(7);
//		majorFilter.setMajor_id("52aedf5b747aec1cfc84157e");
//		majorFilter.setMajor_name("宗教学");
//		majorFilter.setMajor_second_category("哲学类");
//		majorFilter.setMajor_catetory("哲学");
//		majorFilter.setRank_index(4);
//		majorFilter.setSalary_status_str("很高");
//		testMajorFilterList.add(majorFilter);
//		Assert.assertTrue(testMajorFilterList.size() == majorFilterListResult.getMajors().size());
//		for(int i = 0; i < testMajorFilterList.size(); i++) {
//			MajorFilter testBean = testMajorFilterList.get(i);
//			MajorFilter dataBean = majorFilterListResult.getMajors().get(i);
//			Assert.assertTrue(testBean.getRank_index() == dataBean.getRank_index());
//			Assert.assertTrue(testBean.getMajor_id().equals(dataBean.getMajor_id()));
//			Assert.assertTrue(testBean.getMajor_name().equals(dataBean.getMajor_name()));
//			Assert.assertTrue(testBean.getMajor_second_category().equals(dataBean.getMajor_second_category()));
//			Assert.assertTrue(testBean.getMajor_catetory().equals(dataBean.getMajor_catetory()));
//			Assert.assertTrue(testBean.getDiploma_id() == dataBean.getDiploma_id());
//			Assert.assertTrue(testBean.getSalary_status_str().equals(dataBean.getSalary_status_str()));
//			System.out.println("通过第" + (i + 1) + "测试.");
//		}
//		System.out.println("通过本科专业测试");
		
		// 查询专科
		diploma_id = "5";
		major_category = "";
		count = 1000;
		page_no = 1;
		url = HOST + "major?diploma_id=" + diploma_id + "&major_category=" + URLEncoder.encode(major_category, "utf-8") + "&count=" + count + "&page_no" + page_no;
		startTime = System.currentTimeMillis();
		webTarget = client.target(url);
		request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		response = null;
		response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		majorFilterListResult = new ObjectMapper().readValue(response.readEntity(String.class), MajorFilterListResult.class);
		System.out.println(majorFilterListResult.getMajors().size());
		
//		majorIdList = getZKMajorIdList();
		Assert.assertTrue(871 == majorFilterListResult.getMajors().size());
//		for(int i = 0; i < majorIdList.size(); i++) {
//			String majorId = majorIdList.get(i);
//			Assert.assertTrue(majorId.equals((majorFilterListResult.getMajors().get(i).getMajor_id())));
//		}
		System.out.println("通过专科专业测试");
		
//		diploma_id = "5";
//		major_category = "土建大类";
//		count = 40;
//		page_no = 1;
//		url = HOST + "major?diploma_id=" + diploma_id + "&major_category=" + URLEncoder.encode(major_category, "utf-8") + "&count=" + count + "&page_no" + page_no;
//		startTime = System.currentTimeMillis();
//		webTarget = client.target(url);
//		request = webTarget.request();
//		request.header("Content-type", MediaType.APPLICATION_JSON);
//		response = null;
//		response = request.get();
//		System.out.println(response.getStatus());
//		System.out.println(response.toString());
//		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
//		majorFilterListResult = new ObjectMapper().readValue(response.readEntity(String.class), MajorFilterListResult.class);
//		System.out.println(majorFilterListResult.getMajors().size());
//		
//		testMajorFilterList = new ArrayList<MajorFilter>();
//		majorFilter = new MajorFilter();
//		majorFilter.setMajor_id("52aedf5b747aec1cfc84135d");
//		majorFilter.setDiploma_id(5);
//		majorFilter.setMajor_name("建筑工程技术");
//		majorFilter.setMajor_second_category("土建施工类");
//		majorFilter.setMajor_catetory("土建大类");
//		majorFilter.setRank_index(1);
//		majorFilter.setSalary_status_str("很高");
//		testMajorFilterList.add(majorFilter);
//		majorFilter = new MajorFilter();
//		majorFilter.setDiploma_id(5);
//		majorFilter.setMajor_id("52aedf5b747aec1cfc84136a");
//		majorFilter.setMajor_name("建筑经济管理");
//		majorFilter.setMajor_second_category("工程管理类");
//		majorFilter.setMajor_catetory("土建大类");
//		majorFilter.setRank_index(2);
//		majorFilter.setSalary_status_str("很高");
//		testMajorFilterList.add(majorFilter);
//		majorFilter = new MajorFilter();
//		majorFilter.setDiploma_id(5);
//		majorFilter.setMajor_id("52aedf5b747aec1cfc841354");
//		majorFilter.setMajor_name("建筑设计技术");
//		majorFilter.setMajor_second_category("建筑设计类");
//		majorFilter.setMajor_catetory("土建大类");
//		majorFilter.setRank_index(3);
//		majorFilter.setSalary_status_str("很高");
//		testMajorFilterList.add(majorFilter);
//		Assert.assertTrue(36 == majorFilterListResult.getMajors().size());
//		for(int i = 0; i < testMajorFilterList.size(); i++) {
//			MajorFilter testBean = testMajorFilterList.get(i);
//			MajorFilter dataBean = majorFilterListResult.getMajors().get(i);
//			Assert.assertTrue(testBean.getRank_index() == dataBean.getRank_index());
//			Assert.assertTrue(testBean.getMajor_id().equals(dataBean.getMajor_id()));
//			Assert.assertTrue(testBean.getMajor_name().equals(dataBean.getMajor_name()));
//			Assert.assertTrue(testBean.getMajor_second_category().equals(dataBean.getMajor_second_category()));
//			Assert.assertTrue(testBean.getMajor_catetory().equals(dataBean.getMajor_catetory()));
//			Assert.assertTrue(testBean.getDiploma_id() == dataBean.getDiploma_id());
//			Assert.assertTrue(testBean.getSalary_status_str().equals(dataBean.getSalary_status_str()));
//			System.out.println("通过第" + (i + 1) + "测试.");
//		}
//		System.out.println("通过专科专业测试");
//		
	}
	
	@Test
	public void testMajorCategory() throws Exception {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);
		
		// 查询本科专业信息
		Client client = ClientBuilder.newClient(clientConfig);
		String diploma_id = "7";
		String category_level = "1";
		String url = HOST + "major/category?diploma_id=" + diploma_id + "&category_level=" + category_level;
		long startTime = System.currentTimeMillis();
		WebTarget webTarget = client.target(url);
		Builder request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		Response response = null;
		response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		MajorFilterParamsResult majorFilterParamsResult = new ObjectMapper().readValue(response.readEntity(String.class), MajorFilterParamsResult.class);
		System.out.println(majorFilterParamsResult.getCategorys());
		Set<String> categorySet = new HashSet<String>();
		categorySet.add("经济学");
		categorySet.add("法学");
		categorySet.add("文学");
		categorySet.add("历史学");
		categorySet.add("理学");
		categorySet.add("工学");
		categorySet.add("哲学");
		categorySet.add("教育学");
		categorySet.add("农学");
		categorySet.add("医学");
		categorySet.add("管理学");
		categorySet.add("艺术学");
		Assert.assertTrue(categorySet.size() == majorFilterParamsResult.getCategorys().size());
		for(String category : majorFilterParamsResult.getCategorys()){
			Assert.assertTrue(categorySet.contains(category));
			System.out.println("通过专业类型" + category + "测试");
		}
		
		// 测试专科
		diploma_id = "5";
		category_level = "1";
		url = HOST + "major/category?diploma_id=" + diploma_id + "&category_level=" + category_level;
		startTime = System.currentTimeMillis();
		webTarget = client.target(url);
		request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		response = null;
		response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		majorFilterParamsResult = new ObjectMapper().readValue(response.readEntity(String.class), MajorFilterParamsResult.class);
		System.out.println(majorFilterParamsResult.getCategorys());
		categorySet = new HashSet<String>();
		categorySet.add("农林牧渔大类");
		categorySet.add("交通运输大类");
		categorySet.add("生化与药品大类");
		categorySet.add("财经大类");
		categorySet.add("资源开发与测绘大类");
		categorySet.add("材料与能源大类");
		categorySet.add("土建大类");
		categorySet.add("制造大类");
		categorySet.add("水利大类");
		categorySet.add("电子信息大类");
		categorySet.add("环保、气象与安全大类");
		categorySet.add("轻纺食品大类");
		categorySet.add("医药卫生大类");
		categorySet.add("旅游大类");
		categorySet.add("公共事业大类");
		categorySet.add("文化教育大类");
		categorySet.add("艺术设计传媒大类");
		categorySet.add("公安大类");
		categorySet.add("法律大类");
		Assert.assertTrue(majorFilterParamsResult.getCategorys().size() > 0);
//		for(String category : majorFilterParamsResult.getCategorys()){
//			Assert.assertTrue(categorySet.contains(category));
//			System.out.println("通过专业类型" + category + "测试");
//		}
		
		diploma_id = "7";
		category_level = "2";
		url = HOST + "major/category?diploma_id=" + diploma_id + "&category_level=" + category_level;
		startTime = System.currentTimeMillis();
		webTarget = client.target(url);
		request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		response = null;
		response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		majorFilterParamsResult = new ObjectMapper().readValue(response.readEntity(String.class), MajorFilterParamsResult.class);
		System.out.println(majorFilterParamsResult.getCategorys());
		Assert.assertTrue(majorFilterParamsResult.getCategorys().size() > 0);
		
		diploma_id = "5";
		category_level = "2";
		url = HOST + "major/category?diploma_id=" + diploma_id + "&category_level=" + category_level;
		startTime = System.currentTimeMillis();
		webTarget = client.target(url);
		request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		response = null;
		response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		majorFilterParamsResult = new ObjectMapper().readValue(response.readEntity(String.class), MajorFilterParamsResult.class);
		System.out.println(majorFilterParamsResult.getCategorys());
		Assert.assertTrue(majorFilterParamsResult.getCategorys().size() > 0);
		
		
	}
	
	@Test
	public void testMajorCategoryName() throws Exception{
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);
		
		// 查询本科专业信息
		Client client = ClientBuilder.newClient(clientConfig);
		String diploma_id = "7";
		String major_category_name = "工学";
		String url = HOST + "major/category/" + URLEncoder.encode(major_category_name, "utf-8") + "?diploma_id=" + diploma_id;
		long startTime = System.currentTimeMillis();
		WebTarget webTarget = client.target(url);
		Builder request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		Response response = null;
		response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		MajorSecondCategoryNameListResult majorSecondCategoryNameListResult = new ObjectMapper().readValue(response.readEntity(String.class), MajorSecondCategoryNameListResult.class);
		System.out.println(majorSecondCategoryNameListResult.getMajor_second_categorys());
		String[] majorCategoryNames = new String[]{"力学类","材料类","电子信息类","自动化类","计算机类","土木类","化工与制药类","纺织类","轻工类","海洋工程类","兵器类","环境科学与工程类","建筑类","机械类","能源动力类","电气类","水利类","交通运输类","测绘类","航空航天类","农业工程类","地质类","矿业类","公安技术类","核工程类","食品科学与工程类","安全科学与工程类","生物医学工程类","生物工程类","林业工程类","仪器类"};
		Set<String> tSet = new HashSet<String>();
		for(String name : majorCategoryNames) {
			tSet.add(name);
		}
		Assert.assertTrue(tSet.size() == majorSecondCategoryNameListResult.getMajor_second_categorys().size());
		for(String name : majorSecondCategoryNameListResult.getMajor_second_categorys()) {
			Assert.assertTrue(tSet.contains(name));
		}
		System.out.println("通过本科测试");
		
		// 查询专科
		client = ClientBuilder.newClient(clientConfig);
		diploma_id = "5";
		major_category_name = "资源开发与测绘大类";
		url = HOST + "major/category/" + URLEncoder.encode(major_category_name, "utf-8") + "?diploma_id=" + diploma_id;
		startTime = System.currentTimeMillis();
		webTarget = client.target(url);
		request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		response = null;
		response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		majorSecondCategoryNameListResult = new ObjectMapper().readValue(response.readEntity(String.class), MajorSecondCategoryNameListResult.class);
		System.out.println(majorSecondCategoryNameListResult.getMajor_second_categorys());
		majorCategoryNames = new String[]{"资源勘查类","矿业工程类","石油与天然气类","地质工程与技术类","测绘类","矿物加工类"};
		tSet = new HashSet<String>();
		for(String name : majorCategoryNames) {
			tSet.add(name);
		}
		Assert.assertTrue(tSet.size() == majorSecondCategoryNameListResult.getMajor_second_categorys().size());
		for(String name : majorSecondCategoryNameListResult.getMajor_second_categorys()) {
			Assert.assertTrue(tSet.contains(name));
		}
		System.out.println("通过专科测试");
	}
	
	@Test
	public void testMajorNameListResult() throws Exception{
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);
		
		// 查询本科专业信息
		Client client = ClientBuilder.newClient(clientConfig);
		String diploma_id = "7";
		String major_second_category_name = "经济学类";
		String url = HOST + "major/second_category/" + URLEncoder.encode(major_second_category_name, "utf-8") + "?diploma_id=" + diploma_id;
		long startTime = System.currentTimeMillis();
		WebTarget webTarget = client.target(url);
		Builder request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		Response response = null;
		response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		MajorNameListResult majorNameListResult = new ObjectMapper().readValue(response.readEntity(String.class), MajorNameListResult.class);
		System.out.println(majorNameListResult.getMajors());
		Set<String> majorNameSet = new HashSet<String>();
		majorNameSet.add("经济学");
		majorNameSet.add("经济统计学");
		majorNameSet.add("国民经济管理");
		majorNameSet.add("资源与环境经济学");
		majorNameSet.add("商务经济学");
		majorNameSet.add("能源经济");
		Assert.assertTrue(majorNameSet.size() == majorNameListResult.getMajors().size());
		for(MajorName majorName : majorNameListResult.getMajors()) {
			Assert.assertTrue(majorNameSet.contains(majorName.getMajor_name()));
		}
		System.out.println("通过本科测试");
		
		// 查询专科
		client = ClientBuilder.newClient(clientConfig);
		diploma_id = "5";
		major_second_category_name = "水产养殖类";
		url = HOST + "major/second_category/" + URLEncoder.encode(major_second_category_name, "utf-8") + "?diploma_id=" + diploma_id;
		startTime = System.currentTimeMillis();
		webTarget = client.target(url);
		request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		response = null;
		response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		majorNameListResult = new ObjectMapper().readValue(response.readEntity(String.class), MajorNameListResult.class);
		System.out.println(majorNameListResult.getMajors());
		majorNameSet = new HashSet<String>();
		majorNameSet.add("水产养殖技术");
		majorNameSet.add("海洋渔业技术");
		majorNameSet.add("渔业综合技术");
		majorNameSet.add("水族科学与技术");
		Assert.assertTrue(majorNameSet.size() == majorNameListResult.getMajors().size());
		for(MajorName majorName : majorNameListResult.getMajors()) {
			Assert.assertTrue(majorNameSet.contains(majorName.getMajor_name()));
		}
		System.out.println("通过专科测试");
	}
	
	private void assertSalaryStats(List<SalaryStat> salaryDistList, List<Double> salaryList) {
		for(int i = 0 ; i <= 10 && i < salaryDistList.size(); i++) {
			SalaryStat salaryStat = salaryDistList.get(i);
			Assert.assertTrue(i == salaryStat.getGrad_year());
			Assert.assertTrue(salaryStat.getSalary() - salaryList.get(i) >= 0.0 && salaryStat.getSalary() - salaryList.get(i) < 1.0);
			System.out.println("通过第" + (i + 1) + "个薪酬测试.");
		}
	}
	
	private void assertPredictStats(List<PredictSalaryStat> predictSalaryDistList, List<Double> salaryList) {
		for(int i = 0 ; i <= 10 && i < predictSalaryDistList.size(); i++) {
			PredictSalaryStat salaryStat = predictSalaryDistList.get(i);
			Assert.assertTrue(i == salaryStat.getGrad_year());
			Assert.assertTrue(salaryStat.getSalary() - salaryList.get(i) >= 0.0 && salaryStat.getSalary() - salaryList.get(i) < 1.0);
			System.out.println("通过第" + (i + 1) + "个预测薪酬测试.");
		}
	}
	
	private String getIndustryName(String industryId, List<Industry> industryList) {
		for(Industry industry : industryList) {
			if(industry.getIndustry_id().equals(industryId)){
				return industry.getIndustry_name();
			}
		}
		return "";
	}
	
	private IndustryListResult getIndustry() throws Exception{
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);

		// 查询本科专业信息
		Client client = ClientBuilder.newClient(clientConfig);
		String url = HOST + "industry";
		WebTarget webTarget = client.target(url);
		Builder request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		Response response = null;
		response = request.get();
		return new ObjectMapper().readValue(response.readEntity(String.class), IndustryListResult.class);
		
	}
	
	private void assertLocationDist(List<LocationDist> locationDistList, List<LocationDist> dataList) {
		for(int i = 0 ; i < dataList.size(); i++) {
			LocationDist testBean = locationDistList.get(i);
			LocationDist dataBean = dataList.get(i);
			Assert.assertTrue(testBean.getCity_id().equals(dataBean.getCity_id()));
			Assert.assertTrue(Math.abs(testBean.getRatio() - dataBean.getRatio()) < 1.0);
			System.out.println("通过第" + (i + 1) + "个地区测试");
		}
	}
	
	private String getLocationId(String locationName, List<Location> locationList) {
		for(Location location : locationList) {
			if(location.getName().equals(locationName)){
				return location.getLoc_id();
			}
		}
		return null;
	}
	
	private String getLocationName(String locationId, List<Location> locationList) {
		for(Location location : locationList) {
			if(location.getLoc_id().equals(locationId)) {
				return location.getName();
			}
		}
		return "";
	}
	
	private LocationListResult getCity() throws Exception {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);

		Client client = ClientBuilder.newClient(clientConfig);
		String level = "city";
		String url = HOST + "location?level=" + level;
		WebTarget webTarget = client.target(url);
		Builder request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		Response response = null;
		response = request.get();
		return new ObjectMapper().readValue(response.readEntity(String.class), LocationListResult.class);
	}
	
	private LocationListResult getProvince() throws Exception {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);

		Client client = ClientBuilder.newClient(clientConfig);
		String level = "province";
		String url = HOST + "location?level=" + level;
		WebTarget webTarget = client.target(url);
		Builder request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		Response response = null;
		response = request.get();
		return new ObjectMapper().readValue(response.readEntity(String.class), LocationListResult.class);
	}
	
	private void assertJobFunctionDist(List<JobFunctionDist> jobFunctionDistList, List<JobFunctionDist> dataList, List<Industry> industryList) {
		for(int i = 0; i < dataList.size(); i++) {
			JobFunctionDist testBean = jobFunctionDistList.get(i);
			JobFunctionDist dataBean = dataList.get(i);
			Assert.assertTrue(testBean.getZhineng().equals(dataBean.getZhineng()));
			Assert.assertTrue(Math.abs(testBean.getRatio() - dataBean.getRatio() )< 1.0);
//			for(int j = 0 ; j < dataBean.getIndustry_list().size(); j++) {
//				Assert.assertTrue(this.getIndustryName(testBean.getIndustry_list().get(i), industryList).equals(dataBean.getIndustry_list().get(i)));
//			}
			for(int j = 0 ; j < dataBean.getPosition_list().size(); j++) {
				Assert.assertTrue(testBean.getPosition_list().get(i).equals(dataBean.getPosition_list().get(i)));
			}
			System.out.println("通过第" + (i + 1) + "个职能");
		}
	}
	
	private List<String> toList(String[] strings) {
		List<String> resultList = new ArrayList<String>();
		for(String string : strings) {
			resultList.add(string);
		}
		return resultList;
	}
	
	private List<String> getBkMajorIdList() throws Exception{
		File rootFile = new File("/data/downloads_file/test3.txt");
		if (!rootFile.exists()) {
			System.out.println("文件不存在");
		}
		FileReader fileReader = new FileReader(rootFile);
		BufferedReader br = new BufferedReader(fileReader);
		StringBuffer stringBuffer = new StringBuffer("");
		String line = null;
		while((line = br.readLine()) != null) {
			stringBuffer.append(line).append("\n");
		}
//		System.out.println(stringBuffer);
		
		//正则表达式
//		String regex = "<td class=\"col1td\">[\\S\\s]+([\u4e00-\u9fa5]+)[\\S\\s]+</td>";
//		String regex = "/school/profile/s_[0-9a-z]+/d_7/n_([\\-()\u4e00-\u9fa5]+)/";
		String regex = "/school/genMajor/m_([0-9a-z]+)/d_7/";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(stringBuffer.toString());
		int count = 0;
		List<String> majorIdList = new ArrayList<String>();
		while(matcher.find()) {
			majorIdList.add(matcher.group(1));
		}
		br.close();
		return majorIdList;
	}
	
	private List<String> getZKMajorIdList() throws Exception{
		File rootFile = new File("/data/downloads_file/test4.txt");
		if (!rootFile.exists()) {
			System.out.println("文件不存在");
		}
		FileReader fileReader = new FileReader(rootFile);
		BufferedReader br = new BufferedReader(fileReader);
		StringBuffer stringBuffer = new StringBuffer("");
		String line = null;
		while((line = br.readLine()) != null) {
			stringBuffer.append(line).append("\n");
		}
//		System.out.println(stringBuffer);
		
		//正则表达式
//		String regex = "<td class=\"col1td\">[\\S\\s]+([\u4e00-\u9fa5]+)[\\S\\s]+</td>";
//		String regex = "/school/profile/s_[0-9a-z]+/d_7/n_([\\-()\u4e00-\u9fa5]+)/";
		String regex = "/school/genMajor/m_([0-9a-z]+)/d_5/";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(stringBuffer.toString());
		int count = 0;
		List<String> majorIdList = new ArrayList<String>();
		while(matcher.find()) {
			majorIdList.add(matcher.group(1));
		}
		br.close();
		return majorIdList;
	}
	
	@Test
	public void test() throws Exception{
		IndustryListResult industryListResult = getIndustry();
		LocationListResult cityListResult = getCity();
		LocationListResult provinceListResult = getProvince();
		List<Location> allLocationList = new ArrayList<Location>();
		allLocationList.addAll(cityListResult.getItems());
		allLocationList.addAll(provinceListResult.getItems());
		
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);
		
		// 查询本科专业信息
		Client client = ClientBuilder.newClient(clientConfig);
		String major_id = "52aedf5b747aec1cfc841259";
		String diploma_id = "5";
		String url = HOST + "major/" + major_id + "/recommend_schools?diploma_id=" + diploma_id;
		long startTime = System.currentTimeMillis();
		WebTarget webTarget = client.target(url);
		Builder request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		Response response = null;
		response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		RecommendSchListResult recommendSchListResult = new ObjectMapper().readValue(response.readEntity(String.class),
				RecommendSchListResult.class);
		System.out.println(recommendSchListResult.toString());
	}

}
