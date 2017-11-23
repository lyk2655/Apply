package com.ipin.service.rest.service.test;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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
import com.ipin.service.rest.beans.MajorSecondCategoryDetailResult;
import com.ipin.service.rest.beans.PredictSalaryStat;
import com.ipin.service.rest.beans.RecommendSch;
import com.ipin.service.rest.beans.RecommendSchListResult;
import com.ipin.service.rest.beans.SalaryStat;

public class MajorSecondCateRestServiceTest {
	
//	public static final String HOST = "http://192.168.0.31:8080/rest-api-service/";
	
//	public static final String HOST = "http://192.168.1.81:8082/rest-api-service/";
	
	public static final String HOST = "http://localhost:8080/";
	
	@Test
	public void testMajorSecondCateDetail() throws Exception{
		IndustryListResult industryListResult = getIndustry();
		LocationListResult cityListResult = getCity();
		LocationListResult provinceListResult = getProvince();
		
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);

		// 查询本科专业信息
		Client client = ClientBuilder.newClient(clientConfig);
		String major_second_category_name = "政治学类";
		String diploma_id = "7";
		String url = HOST + "major_second_category/" + URLEncoder.encode(major_second_category_name, "utf-8") + "/detail?diploma_id=" + diploma_id;
		long startTime = System.currentTimeMillis();
		WebTarget webTarget = client.target(url);
		Builder request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		Response response = null;
		response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		MajorSecondCategoryDetailResult majorDetailResult = new ObjectMapper().readValue(response.readEntity(String.class), MajorSecondCategoryDetailResult.class);
		System.out.println(majorDetailResult.toString());
		
		Assert.assertTrue(majorDetailResult.getMajor_second_category().equals("政治学类"));
		Assert.assertTrue(majorDetailResult.getMajor_category().equals("法学"));
		// 测试专业列表
		List<String> majorIdList = new ArrayList<String>();
		majorIdList.add("52aedf5b747aec1cfc841594");
		majorIdList.add("52aedf5b747aec1cfc841596");
		majorIdList.add("52aedf5b747aec1cfc841597");
		majorIdList.add("52aedf5b747aec1cfc841598");
		majorIdList.add("52aedf5b747aec1cfc841595");
//		for(int i = 0 ; i < majorIdList.size(); i++) {
//			Assert.assertTrue(majorIdList.get(i).equals(majorDetailResult.getMajor_list().get(i).getMajor_id()));
//			System.out.println("通过第" + (i + 1) + "个id");
//		}
		Assert.assertTrue(5 ==majorDetailResult.getMajor_list().size() );
		Assert.assertTrue(8928 == majorDetailResult.getSalary_factor());
		Assert.assertTrue(97 == majorDetailResult.getSalary_factor_rank());
		Assert.assertTrue(majorDetailResult.getSample_count() >= 10000);
		Assert.assertTrue(majorDetailResult.getIndustry_dis().size() == 47);
		IndustryDist industryDist = majorDetailResult.getIndustry_dis().get(0);
		Assert.assertTrue(0.1833 < industryDist.getRatio() && industryDist.getRatio() <= 0.1835);
		industryDist = majorDetailResult.getIndustry_dis().get(majorDetailResult.getIndustry_dis().size() - 1);
		Assert.assertTrue(0.0005 < industryDist.getRatio() && industryDist.getRatio() <= 0.0007);
		Assert.assertTrue(majorDetailResult.getIndustry_status().equals("较广"));
		Assert.assertTrue(majorDetailResult.getTop_worker_city().equals("110000000000"));
		Assert.assertTrue(0.18 <= majorDetailResult.getTop_worker_city_ratio() && majorDetailResult.getTop_worker_city_ratio() <= 0.20);
		Assert.assertTrue(0.38 < majorDetailResult.getGender_dis().get(0).getRatio() && majorDetailResult.getGender_dis().get(0).getRatio() <= 0.40);
		Assert.assertTrue(majorDetailResult.getGender_status().equals("女生较多"));
		List<Double> salaryList = new ArrayList<Double>();
		salaryList.add(3189.0);
		salaryList.add(4187.0);
		salaryList.add(5134.0);
		salaryList.add(6107.0);
		salaryList.add(7115.0);
		salaryList.add(8289.0);
		salaryList.add(9712.44);
		salaryList.add(11108.24);
		salaryList.add(12489.98);
		salaryList.add(13757.04);
		salaryList.add(15038.1);
		if(majorDetailResult.getSalary_predict_show_type() == 2) {
			assertSalaryStats(majorDetailResult.getSalary_stats(), salaryList);
		} else {
			assertPredictStats(majorDetailResult.getPredict_salary_stats(), salaryList);
		}
		salaryList = new ArrayList<Double>();
		salaryList.add(2992.0);
		salaryList.add(3921.0);
		salaryList.add(4798.0);
		salaryList.add(5412.0);
		salaryList.add(6556.0);
		salaryList.add(7345.0);
		salaryList.add(8672.0);
		salaryList.add(9886.0);
		salaryList.add(10673.0);
		salaryList.add(11635.0);
		salaryList.add(12415.0);
		assertSalaryStats(majorDetailResult.getCountry_salary_stats(), salaryList);
		List<Location> allLocationList = new ArrayList<Location>();
		allLocationList.addAll(cityListResult.getItems());
		allLocationList.addAll(provinceListResult.getItems());
		List<LocationDist> locationDistList = new ArrayList<LocationDist>();
		LocationDist locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("北京市", allLocationList));
		locationDist.setRatio(0.1956);
		locationDistList.add(locationDist);
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("上海市", allLocationList));
		locationDist.setRatio(0.172);
		locationDistList.add(locationDist);
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("广州市", allLocationList));
		locationDist.setRatio(0.0732);
		locationDistList.add(locationDist);
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("深圳市", allLocationList));
		locationDist.setRatio(0.0663);
		locationDistList.add(locationDist);
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("武汉市", allLocationList));
		locationDist.setRatio(0.0442);
		locationDistList.add(locationDist);
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("天津市", allLocationList));
		locationDist.setRatio(0.0363);
		locationDistList.add(locationDist);
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("西安市", allLocationList));
		locationDist.setRatio(0.0274);
		locationDistList.add(locationDist);
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("成都市", allLocationList));
		locationDist.setRatio(0.0204);
		locationDistList.add(locationDist);
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("沈阳市", allLocationList));
		locationDist.setRatio(0.019);
		locationDistList.add(locationDist);
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("青岛市", allLocationList));
		locationDist.setRatio(0.0177);
		locationDistList.add(locationDist);
		this.assertLocationDist(majorDetailResult.getLocation_dis(), locationDistList);
		List<JobFunctionDist> jobFunctionDistList = new ArrayList<JobFunctionDist>();
		JobFunctionDist jobFunctionDist = new JobFunctionDist();
		jobFunctionDist.setZhineng("财务/审计/税务");
		jobFunctionDist.setRatio(0.119);
		jobFunctionDist.setPosition_list(toList(new String[]{"会计","财务部","财务经理","审计专员/助理","财务/会计助理","财务主管/总帐主管","审计经理/主管","出纳员","高级审计员","财务分析员"}));
		jobFunctionDist.setIndustry_list(toList(new String[]{"咨询等专业服务","金融/投资","房地产/建筑","会计/审计","贸易/进出口"}));
		jobFunctionDistList.add(jobFunctionDist);
		jobFunctionDist = new JobFunctionDist();
		jobFunctionDist.setZhineng("行政/后勤/文秘");
		jobFunctionDist.setRatio(0.08);
		jobFunctionDist.setPosition_list(toList(new String[]{"行政专员/助理","助理/秘书","经理助理/秘书","行政经理/主管/办公室主任","文员","经理助理/秘书/文员","行政主管","行政助理","行政经理/办公室主任","办公室"}));
		jobFunctionDist.setIndustry_list(toList(new String[]{"其它","房地产/建筑","教育/培训/科研","金融/投资","快速消费品"}));
		jobFunctionDistList.add(jobFunctionDist);
		jobFunctionDist = new JobFunctionDist();
		jobFunctionDist.setZhineng("人力资源");
		jobFunctionDist.setRatio(0.063);
		jobFunctionDist.setPosition_list(toList(new String[]{"人力资源专员/助理","人事专员","招聘专员/助理","人事助理","人事主管","人力资源主管","人力资源部","人事经理","人力资源经理","人力资源专员"}));
		jobFunctionDist.setIndustry_list(toList(new String[]{"房地产/建筑","快速消费品","其它","机械/设备/重工","服装/纺织/皮革"}));
		jobFunctionDistList.add(jobFunctionDist);
		jobFunctionDist = new JobFunctionDist();
		jobFunctionDist.setZhineng("市场");
		jobFunctionDist.setRatio(0.055);
		jobFunctionDist.setPosition_list(toList(new String[]{"市场部","市场/营销/拓展专员","市场/营销/拓展经理","市场助理","市场专员/市场助理","销售部","其他","市场/营销/拓展主管","市场专员","市场部经理"}));
		jobFunctionDist.setIndustry_list(toList(new String[]{"快速消费品","其它","房地产/建筑","服装/纺织/皮革","教育/培训/科研"}));
		jobFunctionDistList.add(jobFunctionDist);
		jobFunctionDist = new JobFunctionDist();
		jobFunctionDist.setZhineng("证券业务");
		jobFunctionDist.setRatio(0.0509);
		jobFunctionDist.setPosition_list(toList(new String[]{"客户经理","实习生","证券/期货/外汇经纪人","投资/理财顾问","客户经理/主管","其他","销售代表","保险代理/经纪人/客户经理","客户代表","投资/理财服务"}));
		jobFunctionDist.setIndustry_list(toList(new String[]{"金融/投资","保险","其它","房地产/建筑","贸易/进出口"}));
		jobFunctionDistList.add(jobFunctionDist);
		this.assertJobFunctionDist(majorDetailResult.getZhineng_dis(), jobFunctionDistList, industryListResult.getIndustries());
		
		// 查询本科专业信息
		client = ClientBuilder.newClient(clientConfig);
		major_second_category_name = "农业技术类";
		diploma_id = "5";
		url = HOST + "major_second_category/" + URLEncoder.encode(major_second_category_name, "utf-8")
				+ "/detail?diploma_id=" + diploma_id;
		startTime = System.currentTimeMillis();
		webTarget = client.target(url);
		request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		response = null;
		response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		majorDetailResult = new ObjectMapper()
				.readValue(response.readEntity(String.class), MajorSecondCategoryDetailResult.class);
		System.out.println(majorDetailResult.toString());

		Assert.assertTrue(majorDetailResult.getMajor_second_category().equals("农业技术类"));
		Assert.assertTrue(majorDetailResult.getMajor_category().equals("农林牧渔大类"));
		Assert.assertTrue(16 == majorDetailResult.getMajor_list().size());
		Assert.assertTrue(4485 == majorDetailResult.getSalary_factor());
		Assert.assertTrue(majorDetailResult.getSalary_factor_rank() <= 0);
		Assert.assertTrue(majorDetailResult.getSample_count() >= 10000);
		industryDist = majorDetailResult.getIndustry_dis().get(0);
		Assert.assertTrue(0.1398 < industryDist.getRatio() && industryDist.getRatio() <= 0.1400);
		industryDist = majorDetailResult.getIndustry_dis().get(majorDetailResult.getIndustry_dis().size() - 1);
		Assert.assertTrue(0.0013 < industryDist.getRatio() && industryDist.getRatio() <= 0.0015);
		Assert.assertTrue(majorDetailResult.getIndustry_status().equals("很广"));
		Assert.assertTrue(majorDetailResult.getTop_worker_city().equals("110000000000"));
		Assert.assertTrue(0.12 <= majorDetailResult.getTop_worker_city_ratio()
				&& majorDetailResult.getTop_worker_city_ratio() <= 0.14);
		Assert.assertTrue(0.31 < majorDetailResult.getGender_dis().get(0).getRatio()
				&& majorDetailResult.getGender_dis().get(0).getRatio() <= 0.33);
		Assert.assertTrue(majorDetailResult.getGender_status().equals("女生较多"));
		salaryList = new ArrayList<Double>();
		salaryList.add(2741.0);
		salaryList.add(3218.0);
		salaryList.add(3627.0);
		salaryList.add(3956.0);
		salaryList.add(4229.0);
		salaryList.add(4466.0);
		salaryList.add(4792.98);
		salaryList.add(5191.68);
		salaryList.add(5561.82);
		salaryList.add(6348.24);
		salaryList.add(7603.2);
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
		locationDist.setRatio(0.1316);
		locationDistList.add(locationDist);
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("上海市", allLocationList));
		locationDist.setRatio(0.0707);
		locationDistList.add(locationDist);
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("郑州市", allLocationList));
		locationDist.setRatio(0.0573);
		locationDistList.add(locationDist);
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("成都市", allLocationList));
		locationDist.setRatio(0.0442);
		locationDistList.add(locationDist);
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("广州市", allLocationList));
		locationDist.setRatio(0.0435);
		locationDistList.add(locationDist);
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("沈阳市", allLocationList));
		locationDist.setRatio(0.0393);
		locationDistList.add(locationDist);
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("深圳市", allLocationList));
		locationDist.setRatio(0.0342);
		locationDistList.add(locationDist);
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("石家庄市", allLocationList));
		locationDist.setRatio(0.0298);
		locationDistList.add(locationDist);
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("杭州市", allLocationList));
		locationDist.setRatio(0.0295);
		locationDistList.add(locationDist);
		locationDist = new LocationDist();
		locationDist.setCity_id(this.getLocationId("苏州市", allLocationList));
		locationDist.setRatio(0.0282);
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
		String major_second_category_name = "政治学类";
		String diploma_id = "7";
		String url = HOST + "major_second_category/" + URLEncoder.encode(major_second_category_name, "utf-8") + "/recommend_schools?diploma_id=" + diploma_id;
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
		int size = 6;
		List<RecommendSch> recommendSchList = new ArrayList<RecommendSch>();
		RecommendSch recommendSch = new RecommendSch();
		recommendSch.setSch_id("52ac2e98747aec013fcf4a8b");
		recommendSch.setRank_index(1);
		recommendSch.setCity(this.getLocationId("北京市", allLocationList));
		recommendSch.setRank_str("A");
		recommendSch.setSchool_type("财经院校");
		recommendSchList.add(recommendSch);
		recommendSch = new RecommendSch();
		recommendSch.setSch_id("52ac2e9a747aec013fcf50bf");
		recommendSch.setRank_index(2);
		recommendSch.setCity(this.getLocationId("上海市", allLocationList));
		recommendSch.setRank_str("A+");
		recommendSch.setSchool_type("语言院校");
		recommendSchList.add(recommendSch);
		recommendSch = new RecommendSch();
		recommendSch.setSch_id("52ac2e99747aec013fcf4f0b");
		recommendSch.setRank_index(3);
		recommendSch.setCity(this.getLocationId("北京市", allLocationList));
		recommendSch.setRank_str("A+");
		recommendSch.setSchool_type("综合院校");
		recommendSchList.add(recommendSch);
		recommendSch = new RecommendSch();
		recommendSch.setSch_id("52ac2e9a747aec013fcf5190");
		recommendSch.setRank_index(4);
		recommendSch.setCity(this.getLocationId("北京市", allLocationList));
		recommendSch.setRank_str("A++");
		recommendSch.setSchool_type("综合院校");
		recommendSchList.add(recommendSch);
		recommendSch = new RecommendSch();
		recommendSch.setSch_id("52ac2e9b747aec013fcf5338");
		recommendSch.setRank_index(5);
		recommendSch.setCity(this.getLocationId("广州市", allLocationList));
		recommendSch.setRank_str("A+");
		recommendSch.setSchool_type("综合院校");
		recommendSchList.add(recommendSch);
		recommendSch = new RecommendSch();
		recommendSch.setSch_id("52ac2e98747aec013fcf4b66");
		recommendSch.setRank_index(6);
		recommendSch.setCity(this.getLocationId("上海市", allLocationList));
		recommendSch.setRank_str("D");
		recommendSch.setSchool_type("财经院校");
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
		major_second_category_name = "农业技术类";
		diploma_id = "5";
		url = HOST + "major_second_category/" + URLEncoder.encode(major_second_category_name, "utf-8")
				+ "/recommend_schools?diploma_id=" + diploma_id;
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
		recommendSch.setSch_name("辽宁农业职业技术学院");
		recommendSch.setRank_index(1);
		recommendSch.setCity(this.getLocationId("营口市", allLocationList));
		recommendSch.setRank_str("-");
		recommendSch.setSchool_type("农业院校");
		recommendSchList.add(recommendSch);
		recommendSch = new RecommendSch();
		recommendSch.setSch_name("北京农业职业学院");
		recommendSch.setRank_index(2);
		recommendSch.setCity(this.getLocationId("北京市", allLocationList));
		recommendSch.setRank_str("-");
		recommendSch.setSchool_type("农业院校");
		recommendSchList.add(recommendSch);
		recommendSch = new RecommendSch();
		recommendSch.setSch_name("河北农业大学");
		recommendSch.setRank_index(3);
		recommendSch.setCity(this.getLocationId("保定市", allLocationList));
		recommendSch.setRank_str("-");
		recommendSch.setSchool_type("农业院校");
		recommendSchList.add(recommendSch);
		recommendSch = new RecommendSch();
		recommendSch.setSch_name("遵义职业技术学院");
		recommendSch.setRank_index(4);
		recommendSch.setCity(this.getLocationId("遵义市", allLocationList));
		recommendSch.setRank_str("-");
		recommendSch.setSchool_type("综合院校");
		recommendSchList.add(recommendSch);
		recommendSch = new RecommendSch();
		recommendSch.setSch_name("河南科技学院");
		recommendSch.setRank_index(5);
		recommendSch.setCity(this.getLocationId("新乡市", allLocationList));
		recommendSch.setRank_str("-");
		recommendSch.setSchool_type("师范院校");
		recommendSchList.add(recommendSch);
		recommendSch = new RecommendSch();
		recommendSch.setSch_name("襄阳职业技术学院");
		recommendSch.setRank_index(6);
		recommendSch.setCity(this.getLocationId("襄阳市", allLocationList));
		recommendSch.setRank_str("-");
		recommendSch.setSchool_type("工科院校");
		recommendSchList.add(recommendSch);

		Assert.assertTrue(size == recommendSchListResult.getSchools().size());
		for (int i = 0; i < recommendSchList.size(); i++) {
			RecommendSch testBean = recommendSchList.get(i);
			RecommendSch dataBean = recommendSchListResult.getSchools().get(i);
			Assert.assertTrue(testBean.getSch_name().equals(dataBean.getSch_name()));
			Assert.assertTrue(testBean.getRank_index() == dataBean.getRank_index());
			Assert.assertTrue(testBean.getSchool_type().equals(dataBean.getSchool_type()));
			Assert.assertTrue(testBean.getCity().equals(dataBean.getCity()));
			Assert.assertTrue(testBean.getRank_str().equals(dataBean.getRank_str()));
			System.out.println("通过推荐学校第" + (i + 1) + "个");
		}
		System.out.println("通过专科测试");
	}

	private void assertSalaryStats(List<SalaryStat> salaryDistList, List<Double> salaryList) {
		for(int i = 0 ; i <= 10 ; i++) {
			SalaryStat salaryStat = salaryDistList.get(i);
			Assert.assertTrue(i == salaryStat.getGrad_year());
			Assert.assertTrue(salaryStat.getSalary() - salaryList.get(i) >= 0.0 && salaryStat.getSalary() - salaryList.get(i) < 1.0);
			System.out.println("通过第" + (i + 1) + "个薪酬测试.");
		}
	}
	
	private void assertPredictStats(List<PredictSalaryStat> predictSalaryDistList, List<Double> salaryList) {
		for(int i = 0 ; i <= 10 ; i++) {
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
}
