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

import org.apache.thrift.TException;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.Assert;
import org.junit.Test;

import com.ipin.service.rest.beans.GenderSchMajorRankingList;
import com.ipin.service.rest.beans.Location;
import com.ipin.service.rest.beans.LocationListResult;
import com.ipin.service.rest.beans.SalaryGrowthSchMajorRankingList;
import com.ipin.service.rest.beans.SalarySchMajorRankingList;
import com.ipin.service.rest.beans.SchEnrollPlan;
import com.ipin.service.rest.beans.SchEnrollPlanListResult;
import com.ipin.service.rest.beans.SchFilter;
import com.ipin.service.rest.beans.SchFilterListResult;
import com.ipin.service.rest.beans.SchMajorScore;
import com.ipin.service.rest.beans.SchMajorScoreListResult;
import com.ipin.service.rest.beans.SchMajorScoreParamsResult;
import com.ipin.service.rest.beans.SchMajorSettingListResult;
import com.ipin.service.rest.beans.SchScore;
import com.ipin.service.rest.beans.SchScoreListResult;
import com.ipin.service.rest.beans.SchScoreParamsResult;
import com.ipin.service.rest.beans.ScoreSchMajorRankingList;
import com.ipin.service.rest.beans.TotalSchRankingList;
import com.ipin.service.test.service.test.beans.GenderSchMajorRankingListResult;
import com.ipin.service.test.service.test.beans.SalaryGrowthSchMajorRankingListResult;
import com.ipin.service.test.service.test.beans.SalarySchMajorRankingListResult;
import com.ipin.service.test.service.test.beans.ScoreSchMajorRankingListResult;
import com.ipin.service.test.service.test.beans.TotalSchRankingListResult;

/**
 * SchoolRestServiceTest 学校api测试
 * 
 * @author zhongyongsheng
 *
 */
public class SchoolRestServiceTest {
	
//	public static final String HOST = "http://192.168.0.31:8080/rest-api-service/";

//	public static final String HOST = "http://192.168.1.81:8082/rest-api-service/";
	
	public static final String HOST = "http://localhost:8080/";
	
	/**
	 * 测试学校详情
	 * @throws TException
	 */
	@Test
	public void testSchoolDetail() throws TException {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);

		Client client = ClientBuilder.newClient(clientConfig);
		String schId = "52ac2e9a747aec013fcf50e8";
		String url = HOST + "school/" + schId + "/detail?diploma_id=7";
		WebTarget webTarget = client.target(url);
		Builder request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		Response response = null;
		response = request.get();
		printResponse(response, url, "学校详情");
	}

	/**
	 * 测试学校专业设置
	 * @throws Exception
	 */
	@Test
	public void testGetMajorSetting() throws Exception{

		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);

		Client client = ClientBuilder.newClient(clientConfig);

		// 本科 全国在招专业
		String schId = "52ac2e99747aec013fcf4e6f";
		String url = HOST + "school/" + schId + "/majors?diploma_id=7&major_type=0";
		WebTarget webTarget = client.target(url);
		Builder request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		Response response = null;
		long startTime = System.currentTimeMillis();
		response = request.get();
		System.out.println("本科全国在招专业: " + url);
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		 SchMajorSettingListResult majorSettingListResult = new ObjectMapper().readValue(response.readEntity(String.class), SchMajorSettingListResult.class);
		 int totalSize = 45;
		 Assert.assertTrue(totalSize == majorSettingListResult.getMajors().size());

		// 本科 全国各省在招专业
		url = HOST + "school/" + schId
				+ "/majors?diploma_id=7&major_type=1&province_filter=" + URLEncoder.encode("安徽", "utf-8")  +"&wenli_filter=li&year_filter=2015";
		webTarget = client.target(url);
		request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		startTime = System.currentTimeMillis();
		response = request.get();
		System.out.println("各省在招专业: " + url);
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		 majorSettingListResult = new ObjectMapper().readValue(response.readEntity(String.class), SchMajorSettingListResult.class);
		 totalSize = 30;
		 Assert.assertTrue(totalSize == majorSettingListResult.getMajors().size());
//
		// 本科 所有专业(包括已停办)
		url = HOST + "school/" + schId + "/majors?diploma_id=7&major_type=2";
		webTarget = client.target(url);
		startTime = System.currentTimeMillis();
		request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		startTime = System.currentTimeMillis();
		response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		 majorSettingListResult = new ObjectMapper().readValue(response.readEntity(String.class), SchMajorSettingListResult.class);
		 totalSize = 51;
		 Assert.assertTrue(totalSize == majorSettingListResult.getMajors().size());
		 
		 schId = "52ac2e9a747aec013fcf50e8";
		url = HOST + "school/" + schId + "/majors?diploma_id=7&major_type=2";
		webTarget = client.target(url);
		startTime = System.currentTimeMillis();
		request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		majorSettingListResult = new ObjectMapper().readValue(response.readEntity(String.class),
				SchMajorSettingListResult.class);
		totalSize = 124;
		Assert.assertTrue(totalSize == majorSettingListResult.getMajors().size());
//
//		// 专科 全国在招专业
		schId = "52ac2e9a747aec013fcf4fd2";
		url = HOST + "school/" + schId + "/majors?diploma_id=5&major_type=0";
		webTarget = client.target(url);
		startTime = System.currentTimeMillis();
		request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		majorSettingListResult = new ObjectMapper().readValue(response.readEntity(String.class),
				SchMajorSettingListResult.class);
		totalSize = 9;
		Assert.assertTrue(totalSize == majorSettingListResult.getMajors().size());

		// 专科 全国各省在招专业
		url = HOST + "school/" + schId
				+ "/majors?diploma_id=5&major_type=1&province_filter=" + URLEncoder.encode("陕西", "utf-8")  +"&wenli_filter=li&year_filter=2015";
		webTarget = client.target(url);
		startTime = System.currentTimeMillis();
		request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		majorSettingListResult = new ObjectMapper().readValue(response.readEntity(String.class),
				SchMajorSettingListResult.class);
		totalSize = 8;
		Assert.assertTrue(totalSize == majorSettingListResult.getMajors().size());

		// 专科 所有专业(包括已停办)
		url = HOST + "school/" + schId + "/majors?diploma_id=5&major_type=2";
		webTarget = client.target(url);
		startTime = System.currentTimeMillis();
		request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		majorSettingListResult = new ObjectMapper().readValue(response.readEntity(String.class),
				SchMajorSettingListResult.class);
		totalSize = 7;
		Assert.assertTrue(totalSize == majorSettingListResult.getMajors().size());
	}
	
	@Test
	public void testSchEnrollPlanParams() {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);

		Client client = ClientBuilder.newClient(clientConfig);
		// 本科
		String schId = "52ac2e99747aec013fcf4e6f";
		String url = HOST + "school/" + schId + "/zs_plan_params?diploma_id=7";
		WebTarget webTarget = client.target(url);
		Builder request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		Response response = request.get();
		System.out.println("本科参数: " + url);
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println(response.readEntity(String.class));
		
		// 专科
		schId = "52ac2e9a747aec013fcf52f1";
		url = HOST + "school/" + schId + "/zs_plan_params?diploma_id=5";
		webTarget = client.target(url);
		request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		response = request.get();
		System.out.println("专科参数: " + url);
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println(response.readEntity(String.class));
	}
	
	@Test
	public void testAllSchEnrollPlanParams() {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);

		Client client = ClientBuilder.newClient(clientConfig);
		// 本科
		String url = HOST + "school/zs_plan_params?diploma_id=7";
		WebTarget webTarget = client.target(url);
		Builder request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		Response response = request.get();
		System.out.println("本科参数: " + url);
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println(response.readEntity(String.class));
		
		// 专科
		url = HOST + "school/zs_plan_params?diploma_id=5";
		webTarget = client.target(url);
		request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		response = request.get();
		System.out.println("专科参数: " + url);
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println(response.readEntity(String.class));
		
	}

	@Test
	public void test() throws Exception{
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);

		Client client = ClientBuilder.newClient(clientConfig);
		String schId = "52ac2e99747aec013fcf4e6f";
		String url = HOST + "school/" + schId + "/majors?diploma_id=5&major_type=1&province_filter=" + URLEncoder.encode("北京", "utf-8")  +"&wenli_filter=li&year_filter=2015";
		WebTarget webTarget = client.target(url);
		Builder request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		Response response = request.get();
		System.out.println("各省在招专业: " + url);
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println(response.readEntity(String.class));
	}
	
	private void printResponse(Response response, String url, String title) {
		System.out.println(title);
		System.out.println("url:" + url);
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println(response.readEntity(String.class));
	}
	
	//测试薪酬学校专业排行榜
	@Test
	public void testSchMajorRankingListBySalary() throws Exception{
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);

		Client client = ClientBuilder.newClient(clientConfig);
		String schId = "52ac2e99747aec013fcf4e6f";
		String url = HOST + "school/" + schId + "/major_rank/by_salary?diploma_id=7";
		long startTime = System.currentTimeMillis();
		WebTarget webTarget = client.target(url);
		Builder request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		Response response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
//		System.out.println(response.readEntity(String.class));
		SalarySchMajorRankingListResult salarySchMajorRankingListResult = new ObjectMapper().readValue(response.readEntity(String.class), SalarySchMajorRankingListResult.class);
		System.out.println(salarySchMajorRankingListResult.toString());
		int totalCount = 31;
		List<SalarySchMajorRankingList> testSalarySchMajorRankingList = new ArrayList<SalarySchMajorRankingList>();
		SalarySchMajorRankingList bean = new SalarySchMajorRankingList();
		bean.setRank_index(1);
		bean.setMajor_id("52aedf5b747aec1cfc8416d0");
		bean.setMajor_name("建筑学");
		bean.setMain_industry("房地产");
		bean.setMain_function("土木/建筑/装修/市政工程");
		bean.setSalary_factor(17824.0);
		bean.setGrow_ratio(87.0);
		bean.setCountry_salary_factor(10462.0);
		bean.setPercent_ratio(98.0);
		bean.setMajor_has_stats(true);
		testSalarySchMajorRankingList.add(bean);
		bean = new SalarySchMajorRankingList();
		bean.setRank_index(2);
		bean.setMajor_id("52aedf5b747aec1cfc841606");
		bean.setMajor_name("新闻学");
		bean.setMain_industry("媒体");
		bean.setMain_function("影视/媒体/出版/印刷");
		bean.setSalary_factor(16959.0);
		bean.setGrow_ratio(707.0);
		bean.setCountry_salary_factor(6557.0);
		bean.setPercent_ratio(100.0);
		bean.setMajor_has_stats(true);
		testSalarySchMajorRankingList.add(bean);
		Assert.assertTrue(totalCount == salarySchMajorRankingListResult.getMajors().size());
		for(int i = 0; i < testSalarySchMajorRankingList.size(); i++) {
			SalarySchMajorRankingList tempBean = testSalarySchMajorRankingList.get(i);
			SalarySchMajorRankingList dataBean = salarySchMajorRankingListResult.getMajors().get(i);
			Assert.assertTrue(tempBean.getRank_index() == dataBean.getRank_index());
			Assert.assertTrue(tempBean.getMajor_id().equals(dataBean.getMajor_id()));
			Assert.assertTrue(tempBean.getMajor_name().equals(dataBean.getMajor_name()));
			Assert.assertTrue(tempBean.getMain_industry().equals(dataBean.getMain_industry()));
			Assert.assertTrue(tempBean.getMain_function().equals(dataBean.getMain_function()));
			Assert.assertTrue(dataBean.getSalary_factor() == tempBean.getSalary_factor());
			Assert.assertTrue(dataBean.getGrow_ratio() - tempBean.getGrow_ratio() < 1.0);
			Assert.assertTrue(tempBean.getCountry_salary_factor() == dataBean.getCountry_salary_factor());
			Assert.assertTrue(dataBean.getPercent_ratio() - tempBean.getPercent_ratio() < 1.0);
			Assert.assertTrue(tempBean.isMajor_has_stats() == dataBean.isMajor_has_stats());
			System.out.println("通过本科专业第" + (i + 1) + "个.");
		}
		System.out.println("本科通过测试");
		
		// 专科
		schId = "52ac2e9a747aec013fcf52f1";
		url = HOST + "school/" + schId + "/major_rank/by_salary?diploma_id=5";
		startTime = System.currentTimeMillis();
		webTarget = client.target(url);
		request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
//		System.out.println(response.readEntity(String.class));
		salarySchMajorRankingListResult = new ObjectMapper().readValue(response.readEntity(String.class), SalarySchMajorRankingListResult.class);
		System.out.println(salarySchMajorRankingListResult.toString());
		totalCount = 3;
		testSalarySchMajorRankingList = new ArrayList<SalarySchMajorRankingList>();
		bean = new SalarySchMajorRankingList();
		bean.setRank_index(1);
		bean.setMajor_id("52aedf5b747aec1cfc84153a");
		bean.setMajor_name("影视广告");
		bean.setMain_industry("媒体");
		bean.setMain_function("影视/媒体/出版/印刷");
		bean.setSalary_factor(8375.0);
		bean.setGrow_ratio(-1);
		bean.setCountry_salary_factor(5860.0);
		bean.setPercent_ratio(100.0);
		bean.setMajor_has_stats(true);
		testSalarySchMajorRankingList.add(bean);
		bean = new SalarySchMajorRankingList();
		bean.setRank_index(2);
		bean.setMajor_id("52aedf5b747aec1cfc841545");
		bean.setMajor_name("摄影");
		bean.setMain_industry("媒体");
		bean.setMain_function("影视/媒体/出版/印刷");
		bean.setSalary_factor(7608.0);
		bean.setGrow_ratio(65.0);
		bean.setCountry_salary_factor(6078.0);
		bean.setPercent_ratio(100.0);
		bean.setMajor_has_stats(true);
		testSalarySchMajorRankingList.add(bean);
		bean = new SalarySchMajorRankingList();
		bean.setRank_index(3);
		bean.setMajor_id("52aedf5b747aec1cfc841528");
		bean.setMajor_name("表演艺术");
		bean.setMain_industry("媒体");
		bean.setMain_function("影视/媒体/出版/印刷");
		bean.setSalary_factor(7212.0);
		bean.setGrow_ratio(50.0);
		bean.setCountry_salary_factor(5659.0);
		bean.setPercent_ratio(100.0);
		bean.setMajor_has_stats(true);
		testSalarySchMajorRankingList.add(bean);
		Assert.assertTrue(totalCount == salarySchMajorRankingListResult.getMajors().size());
		for(int i = 0; i < testSalarySchMajorRankingList.size(); i++) {
			SalarySchMajorRankingList tempBean = testSalarySchMajorRankingList.get(i);
			SalarySchMajorRankingList dataBean = salarySchMajorRankingListResult.getMajors().get(i);
			Assert.assertTrue(tempBean.getRank_index() == dataBean.getRank_index());
			Assert.assertTrue(tempBean.getMajor_id().equals(dataBean.getMajor_id()));
			Assert.assertTrue(tempBean.getMajor_name().equals(dataBean.getMajor_name()));
			Assert.assertTrue(tempBean.getMain_industry().equals(dataBean.getMain_industry()));
			Assert.assertTrue(tempBean.getMain_function().equals(dataBean.getMain_function()));
			Assert.assertTrue(dataBean.getSalary_factor() == tempBean.getSalary_factor());
			Assert.assertTrue(dataBean.getGrow_ratio() - tempBean.getGrow_ratio() < 1.0);
			Assert.assertTrue(tempBean.getCountry_salary_factor() == dataBean.getCountry_salary_factor());
			Assert.assertTrue(dataBean.getPercent_ratio() - tempBean.getPercent_ratio() < 1.0);
			Assert.assertTrue(tempBean.isMajor_has_stats() == dataBean.isMajor_has_stats());
			System.out.println("通过专科专业第" + (i + 1) + "个.");
		}
		System.out.println("专科通过测试");
	}
	
	// 测试女性比例学校专业排行榜
	@Test
	public void testSchMajorRankingListByGender() throws Exception{
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);

		Client client = ClientBuilder.newClient(clientConfig);
		String schId = "52ac2e99747aec013fcf4e6f";
		String url = HOST + "school/" + schId + "/major_rank/by_gender?diploma_id=7";
		long startTime = System.currentTimeMillis();
		WebTarget webTarget = client.target(url);
		Builder request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		Response response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		GenderSchMajorRankingListResult genderSchMajorRankingListResult = new ObjectMapper().readValue(response.readEntity(String.class), GenderSchMajorRankingListResult.class);
		List<GenderSchMajorRankingList> testSchMajorRankingList = new ArrayList<GenderSchMajorRankingList>();
		int totalCount = 51;
		GenderSchMajorRankingList bean = new GenderSchMajorRankingList();
		bean.setRank_index(1);
		bean.setMajor_id("52aedf5b747aec1cfc841606");
		bean.setMajor_name("新闻学");
		bean.setMain_industry("媒体");
		bean.setMain_function("影视/媒体/出版/印刷");
		bean.setFemale_ratio(77.0);
		bean.setCountry_female_ratio(74.0);
		bean.setMajor_has_stats(true);
		testSchMajorRankingList.add(bean);
		bean = new GenderSchMajorRankingList();
		bean.setRank_index(2);
		bean.setMajor_id("52aedf5b747aec1cfc8415ce");
		bean.setMajor_name("日语");
		bean.setMain_industry("汽车");
		bean.setMain_function("翻译（口译与笔译）");
		bean.setFemale_ratio(71.0);
		bean.setCountry_female_ratio(82.0);
		bean.setMajor_has_stats(true);
		testSchMajorRankingList.add(bean);
		bean = new GenderSchMajorRankingList();
		bean.setRank_index(3);
		bean.setMajor_id("52aedf5b747aec1cfc841591");
		bean.setMajor_name("法学");
		bean.setMain_industry("房地产");
		bean.setMain_function("律师/法务/合规");
		bean.setFemale_ratio(63.0);
		bean.setCountry_female_ratio(60.0);
		bean.setMajor_has_stats(true);
		testSchMajorRankingList.add(bean);
		
		Assert.assertTrue(totalCount == genderSchMajorRankingListResult.getMajors().size());
		for(int i = 0; i < testSchMajorRankingList.size(); i++) {
			GenderSchMajorRankingList tempBean = testSchMajorRankingList.get(i);
			GenderSchMajorRankingList dataBean = genderSchMajorRankingListResult.getMajors().get(i);
			Assert.assertTrue(tempBean.getRank_index() == dataBean.getRank_index());
			Assert.assertTrue(tempBean.getMajor_id().equals(dataBean.getMajor_id()));
			Assert.assertTrue(tempBean.getMajor_name().equals(dataBean.getMajor_name()));
			Assert.assertTrue(tempBean.getMain_industry().equals(dataBean.getMain_industry()));
			Assert.assertTrue(tempBean.getMain_function().equals(dataBean.getMain_function()));
			Assert.assertTrue(tempBean.getFemale_ratio() == Math.round(dataBean.getFemale_ratio() * 100));
			Assert.assertTrue(tempBean.getCountry_female_ratio() == Math.round(dataBean.getCountry_female_ratio() * 100));
			Assert.assertTrue(tempBean.isMajor_has_stats() == dataBean.isMajor_has_stats());
			System.out.println("通过本科专业第" + (i + 1) + "个.");
		}
		System.out.println("本科通过测试");
		
		schId = "52ac2e9a747aec013fcf52f1";
		url = HOST + "school/" + schId + "/major_rank/by_gender?diploma_id=5";
		startTime = System.currentTimeMillis();
		webTarget = client.target(url);
		request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		genderSchMajorRankingListResult = new ObjectMapper().readValue(response.readEntity(String.class), GenderSchMajorRankingListResult.class);
		testSchMajorRankingList = new ArrayList<GenderSchMajorRankingList>();
		totalCount = 8;
		bean = new GenderSchMajorRankingList();
		bean.setRank_index(1);
		bean.setMajor_id("52aedf5b747aec1cfc841514");
		bean.setMajor_name("人物形象设计");
		bean.setMain_industry("媒体");
		bean.setMain_function("影视/媒体/出版/印刷");
		bean.setFemale_ratio(91.0);
		bean.setCountry_female_ratio(87.0);
		bean.setMajor_has_stats(true);
		testSchMajorRankingList.add(bean);
		bean = new GenderSchMajorRankingList();
		bean.setRank_index(2);
		bean.setMajor_id("52aedf5b747aec1cfc841521");
		bean.setMajor_name("美术");
		bean.setMain_industry("媒体");
		bean.setMain_function("影视/媒体/出版/印刷");
		bean.setFemale_ratio(76.0);
		bean.setCountry_female_ratio(65.0);
		bean.setMajor_has_stats(true);
		testSchMajorRankingList.add(bean);
		bean = new GenderSchMajorRankingList();
		bean.setRank_index(3);
		bean.setMajor_id("52aedf5b747aec1cfc841510");
		bean.setMajor_name("艺术设计");
		bean.setMain_industry("媒体");
		bean.setMain_function("影视/媒体/出版/印刷");
		bean.setFemale_ratio(64.0);
		bean.setCountry_female_ratio(58.0);
		bean.setMajor_has_stats(true);
		testSchMajorRankingList.add(bean);
		
		Assert.assertTrue(totalCount == genderSchMajorRankingListResult.getMajors().size());
		for(int i = 0; i < testSchMajorRankingList.size(); i++) {
			GenderSchMajorRankingList tempBean = testSchMajorRankingList.get(i);
			GenderSchMajorRankingList dataBean = genderSchMajorRankingListResult.getMajors().get(i);
			Assert.assertTrue(tempBean.getRank_index() == dataBean.getRank_index());
			Assert.assertTrue(tempBean.getMajor_id().equals(dataBean.getMajor_id()));
			Assert.assertTrue(tempBean.getMajor_name().equals(dataBean.getMajor_name()));
			Assert.assertTrue(tempBean.getMain_industry().equals(dataBean.getMain_industry()));
			Assert.assertTrue(tempBean.getMain_function().equals(dataBean.getMain_function()));
			Assert.assertTrue(tempBean.getFemale_ratio() == Math.round(dataBean.getFemale_ratio() * 100));
			Assert.assertTrue(tempBean.getCountry_female_ratio() == Math.round(dataBean.getCountry_female_ratio() * 100));
			Assert.assertTrue(tempBean.isMajor_has_stats() == dataBean.isMajor_has_stats());
			System.out.println("通过专科专业第" + (i + 1) + "个.");
		}
		System.out.println("专科通过测试");
	}
	
	// 测试薪酬增长专业排行榜
	@Test
	public void testSchMajorRankingListBySalaryGrowth() throws Exception{
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);

		Client client = ClientBuilder.newClient(clientConfig);
		String schId = "52ac2e99747aec013fcf4e6f";
		String url = HOST + "school/" + schId + "/major_rank/by_salary_growth?diploma_id=7";
		long startTime = System.currentTimeMillis();
		WebTarget webTarget = client.target(url);
		Builder request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		Response response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		SalaryGrowthSchMajorRankingListResult salaryGrowthSchMajorRankingListResult = new ObjectMapper().readValue(response.readEntity(String.class), SalaryGrowthSchMajorRankingListResult.class);
		System.out.println(salaryGrowthSchMajorRankingListResult.toString());
		List<SalaryGrowthSchMajorRankingList> testSchMajorRankingList = new ArrayList<SalaryGrowthSchMajorRankingList>();
		int totalCount = 17;
		SalaryGrowthSchMajorRankingList bean = new SalaryGrowthSchMajorRankingList();
		bean.setRank_index(1);
		bean.setMajor_id("52aedf5b747aec1cfc841606");
		bean.setMajor_name("新闻学");
		bean.setMain_industry("媒体");
		bean.setMain_function("影视/媒体/出版/印刷");
		bean.setGrow_ratio(708.0);
		bean.setSalary_factor(16959.0);
		bean.setCountry_growth_ratio(143.0);
		bean.setPercent_ratio(100.0);
		bean.setMajor_has_stats(true);
		testSchMajorRankingList.add(bean);
		bean = new SalaryGrowthSchMajorRankingList();
		bean.setRank_index(2);
		bean.setMajor_id("52aedf5b747aec1cfc841676");
		bean.setMajor_name("土木工程");
		bean.setMain_industry("房地产");
		bean.setMain_function("土木/建筑/装修/市政工程");
		bean.setGrow_ratio(406.0);
		bean.setSalary_factor(14508.0);
		bean.setCountry_growth_ratio(140.0);
		bean.setPercent_ratio(100.0);
		bean.setMajor_has_stats(true);
		testSchMajorRankingList.add(bean);
		bean = new SalaryGrowthSchMajorRankingList();
		bean.setRank_index(3);
		bean.setMajor_id("52aedf5b747aec1cfc84167c");
		bean.setMajor_name("水利水电工程");
		bean.setMain_industry("房地产");
		bean.setMain_function("土木/建筑/装修/市政工程");
		bean.setGrow_ratio(391.0);
		bean.setSalary_factor(13208.0);
		bean.setCountry_growth_ratio(107.0);
		bean.setPercent_ratio(100.0);
		bean.setMajor_has_stats(true);
		testSchMajorRankingList.add(bean);
		
		Assert.assertTrue(totalCount == salaryGrowthSchMajorRankingListResult.getMajors().size());
		for(int i = 0; i < testSchMajorRankingList.size(); i++) {
			SalaryGrowthSchMajorRankingList tempBean = testSchMajorRankingList.get(i);
			SalaryGrowthSchMajorRankingList dataBean = salaryGrowthSchMajorRankingListResult.getMajors().get(i);
			Assert.assertTrue(tempBean.getRank_index() == dataBean.getRank_index());
			Assert.assertTrue(tempBean.getMajor_id().equals(dataBean.getMajor_id()));
			Assert.assertTrue(tempBean.getMajor_name().equals(dataBean.getMajor_name()));
			Assert.assertTrue(tempBean.getMain_industry().equals(dataBean.getMain_industry()));
			Assert.assertTrue(tempBean.getMain_function().equals(dataBean.getMain_function()));
			Assert.assertTrue(tempBean.getGrow_ratio() == Math.round(dataBean.getGrow_ratio()));
			Assert.assertTrue(tempBean.getCountry_growth_ratio() == Math.round(dataBean.getCountry_growth_ratio()));
			Assert.assertTrue(tempBean.getPercent_ratio() == dataBean.getPercent_ratio());
			Assert.assertTrue(tempBean.isMajor_has_stats() == dataBean.isMajor_has_stats());
			System.out.println("通过本科专业第" + (i + 1) + "个.");
		}
		System.out.println("本科通过测试");
		
		schId = "52ac2e9a747aec013fcf52f1";
		url = HOST + "school/" + schId + "/major_rank/by_salary_growth?diploma_id=5";
		startTime = System.currentTimeMillis();
		webTarget = client.target(url);
		request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		salaryGrowthSchMajorRankingListResult = new ObjectMapper().readValue(response.readEntity(String.class), SalaryGrowthSchMajorRankingListResult.class);
		System.out.println(salaryGrowthSchMajorRankingListResult.toString());
		testSchMajorRankingList = new ArrayList<SalaryGrowthSchMajorRankingList>();
		totalCount = 2;
		bean = new SalaryGrowthSchMajorRankingList();
		bean.setRank_index(1);
		bean.setMajor_id("52aedf5b747aec1cfc841545");
		bean.setMajor_name("摄影");
		bean.setMain_industry("媒体");
		bean.setMain_function("影视/媒体/出版/印刷");
		bean.setGrow_ratio(66.0);
		bean.setSalary_factor(7608.0);
		bean.setCountry_growth_ratio(66.0);
		bean.setPercent_ratio(100.0);
		bean.setMajor_has_stats(true);
		testSchMajorRankingList.add(bean);
		bean = new SalaryGrowthSchMajorRankingList();
		bean.setRank_index(2);
		bean.setMajor_id("52aedf5b747aec1cfc841528");
		bean.setMajor_name("表演艺术");
		bean.setMain_industry("媒体");
		bean.setMain_function("影视/媒体/出版/印刷");
		bean.setGrow_ratio(50.0);
		bean.setSalary_factor(7212.0);
		bean.setCountry_growth_ratio(50.0);
		bean.setPercent_ratio(100.0);
		bean.setMajor_has_stats(true);
		testSchMajorRankingList.add(bean);
		
		Assert.assertTrue(totalCount == salaryGrowthSchMajorRankingListResult.getMajors().size());
		for(int i = 0; i < testSchMajorRankingList.size(); i++) {
			SalaryGrowthSchMajorRankingList tempBean = testSchMajorRankingList.get(i);
			SalaryGrowthSchMajorRankingList dataBean = salaryGrowthSchMajorRankingListResult.getMajors().get(i);
			Assert.assertTrue(tempBean.getRank_index() == dataBean.getRank_index());
			Assert.assertTrue(tempBean.getMajor_id().equals(dataBean.getMajor_id()));
			Assert.assertTrue(tempBean.getMajor_name().equals(dataBean.getMajor_name()));
			Assert.assertTrue(tempBean.getMain_industry().equals(dataBean.getMain_industry()));
			Assert.assertTrue(tempBean.getMain_function().equals(dataBean.getMain_function()));
			Assert.assertTrue(tempBean.getGrow_ratio() == Math.round(dataBean.getGrow_ratio()));
			Assert.assertTrue(tempBean.getCountry_growth_ratio() == Math.round(dataBean.getCountry_growth_ratio()));
			Assert.assertTrue(tempBean.getPercent_ratio() == dataBean.getPercent_ratio());
			Assert.assertTrue(tempBean.isMajor_has_stats() == dataBean.isMajor_has_stats());
			System.out.println("通过专科专业第" + (i + 1) + "个.");
		}
		System.out.println("专科通过测试");
	}

	// 测试录取分数学校专业排行榜
	@Test
	public void testSchMajorRankingListByScore() throws Exception{
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);

		Client client = ClientBuilder.newClient(clientConfig);
		String schId = "52ac2e99747aec013fcf4e6f";
		int diplomaId = 7;
		String wenliFilter = "wen";
		String provinceIdFilter = "440000000000";
		String yearFilter = "2012";
		String url = HOST + "school/" + schId + "/major_rank/by_luqu_score?diploma_id=" + diplomaId + "&wenli_filter=" + wenliFilter + "&province_id_filter=" + provinceIdFilter + "&year_filter=" + yearFilter;
		long startTime = System.currentTimeMillis();
		WebTarget webTarget = client.target(url);
		Builder request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		Response response = request.get();
		System.out.println("专科参数: " + url);
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		ScoreSchMajorRankingListResult scoreSchMajorRankingListResult = new ObjectMapper().readValue(response.readEntity(String.class), ScoreSchMajorRankingListResult.class);
		System.out.println(scoreSchMajorRankingListResult.toString());
		List<ScoreSchMajorRankingList> testSchMajorRankingList = new ArrayList<ScoreSchMajorRankingList>();
		int totalCount = 5;
		ScoreSchMajorRankingList bean = new ScoreSchMajorRankingList();
		bean.setRank_index(1);
		bean.setMajor_id("52aedf5b747aec1cfc84158e");
		bean.setMajor_name("经济与金融");
		bean.setMain_industry("中介服务");
		bean.setMain_function("实习生/培训生/储备干部");
		bean.setLuqu_count(1);
		bean.setLuqu_socre(682);
		bean.setMajor_has_stats(true);
		testSchMajorRankingList.add(bean);
		bean = new ScoreSchMajorRankingList();
		bean.setRank_index(2);
		bean.setMajor_id("52aedf5b747aec1cfc841599");
		bean.setMajor_name("社会学");
		bean.setMain_industry("互联网");
		bean.setMain_function("影视/媒体/出版/印刷");
		bean.setLuqu_count(2);
		bean.setLuqu_socre(669);
		bean.setMajor_has_stats(true);
		testSchMajorRankingList.add(bean);
		bean = new ScoreSchMajorRankingList();
		bean.setRank_index(3);
		bean.setMajor_id("52aedf5b747aec1cfc841591");
		bean.setMajor_name("法学");
		bean.setMain_industry("房地产");
		bean.setMain_function("律师/法务/合规");
		bean.setLuqu_count(3);
		bean.setLuqu_socre(669);
		bean.setMajor_has_stats(true);
		testSchMajorRankingList.add(bean);
		
		Assert.assertTrue(totalCount == scoreSchMajorRankingListResult.getMajors().size());
		for(int i = 0; i < testSchMajorRankingList.size(); i++) {
			ScoreSchMajorRankingList tempBean = testSchMajorRankingList.get(i);
			ScoreSchMajorRankingList dataBean = scoreSchMajorRankingListResult.getMajors().get(i);
			Assert.assertTrue(tempBean.getRank_index() == dataBean.getRank_index());
			Assert.assertTrue(tempBean.getMajor_id().equals(dataBean.getMajor_id()));
			Assert.assertTrue(tempBean.getMajor_name().equals(dataBean.getMajor_name()));
			Assert.assertTrue(tempBean.getMain_industry().equals(dataBean.getMain_industry()));
			Assert.assertTrue(tempBean.getMain_function().equals(dataBean.getMain_function()));
			Assert.assertTrue(tempBean.getLuqu_count() == dataBean.getLuqu_count());
			Assert.assertTrue(tempBean.getLuqu_socre() == dataBean.getLuqu_socre());
			Assert.assertTrue(tempBean.isMajor_has_stats() == dataBean.isMajor_has_stats());
			System.out.println("通过本科文科专业第" + (i + 1) + "个.");
		}
		System.out.println("本科文科通过测试");
		
		schId = "52ac2e99747aec013fcf4e6f";
		diplomaId = 7;
		wenliFilter = "li";
		provinceIdFilter = "440000000000";
		yearFilter = "2012";
		url = HOST + "school/" + schId + "/major_rank/by_luqu_score?diploma_id=" + diplomaId + "&wenli_filter=" + wenliFilter + "&province_id_filter=" + provinceIdFilter + "&year_filter=" + yearFilter;
		startTime = System.currentTimeMillis();
		webTarget = client.target(url);
		request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		response = request.get();
		System.out.println("专科参数: " + url);
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		scoreSchMajorRankingListResult = new ObjectMapper().readValue(response.readEntity(String.class), ScoreSchMajorRankingListResult.class);
		System.out.println(scoreSchMajorRankingListResult.toString());
		testSchMajorRankingList = new ArrayList<ScoreSchMajorRankingList>();
		totalCount = 29;
		bean = new ScoreSchMajorRankingList();
		bean.setRank_index(1);
		bean.setMajor_id("52aedf5b747aec1cfc8416d0");
		bean.setMajor_name("建筑学");
		bean.setMain_industry("房地产");
		bean.setMain_function("土木/建筑/装修/市政工程");
		bean.setLuqu_count(2);
		bean.setLuqu_socre(694);
		bean.setMajor_has_stats(true);
		testSchMajorRankingList.add(bean);
		bean = new ScoreSchMajorRankingList();
		bean.setRank_index(2);
		bean.setMajor_id("52aedf5b747aec1cfc84158e");
		bean.setMajor_name("经济与金融");
		bean.setMain_industry("中介服务");
		bean.setMain_function("实习生/培训生/储备干部");
		bean.setLuqu_count(1);
		bean.setLuqu_socre(692);
		bean.setMajor_has_stats(true);
		testSchMajorRankingList.add(bean);
		bean = new ScoreSchMajorRankingList();
		bean.setRank_index(3);
		bean.setMajor_id("52aedf5b747aec1cfc841646");
		bean.setMajor_name("材料科学与工程");
		bean.setMain_industry("电子技术");
		bean.setMain_function("电子/电器/半导体/仪器仪表");
		bean.setLuqu_count(3);
		bean.setLuqu_socre(691);
		bean.setMajor_has_stats(true);
		testSchMajorRankingList.add(bean);
		
		Assert.assertTrue(totalCount == scoreSchMajorRankingListResult.getMajors().size());
		for(int i = 0; i < testSchMajorRankingList.size(); i++) {
			ScoreSchMajorRankingList tempBean = testSchMajorRankingList.get(i);
			ScoreSchMajorRankingList dataBean = scoreSchMajorRankingListResult.getMajors().get(i);
			Assert.assertTrue(tempBean.getRank_index() == dataBean.getRank_index());
			Assert.assertTrue(tempBean.getMajor_id().equals(dataBean.getMajor_id()));
			Assert.assertTrue(tempBean.getMajor_name().equals(dataBean.getMajor_name()));
			Assert.assertTrue(tempBean.getMain_industry().equals(dataBean.getMain_industry()));
			Assert.assertTrue(tempBean.getMain_function().equals(dataBean.getMain_function()));
			Assert.assertTrue(tempBean.getLuqu_count() == dataBean.getLuqu_count());
			Assert.assertTrue(tempBean.getLuqu_socre() == dataBean.getLuqu_socre());
			Assert.assertTrue(tempBean.isMajor_has_stats() == dataBean.isMajor_has_stats());
			System.out.println("通过本科理科专业第" + (i + 1) + "个.");
		}
		System.out.println("本科理科通过测试");
	}
	
	// 测试学校录取分数
	@Test
	public void testSchScoreList() throws Exception{
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);

		Client client = ClientBuilder.newClient(clientConfig);
		String schId = "52ac2e99747aec013fcf4e6f";
		int diplomaId = 7;
		String wenliFilter = "li";
		String provinceIdFilter = "440000000000";
		String url = HOST + "school/" + schId + "/luqu_scores/in_school?diploma_id=" + diplomaId + "&wenli_filter=" + wenliFilter + "&province_id_filter=" + provinceIdFilter;
		long startTime = System.currentTimeMillis();
		WebTarget webTarget = client.target(url);
		Builder request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		Response response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		SchScoreListResult schScoreListResult = new ObjectMapper().readValue(response.readEntity(String.class), SchScoreListResult.class);
		int totalCount = 3;
		List<SchScore> testSchScoreList = new ArrayList<SchScore>();
		SchScore bean = new SchScore();
		bean.setRank_index(1);
		bean.setYear(2014);
		bean.setMax_score(711);
		bean.setAvg_score(684);
		bean.setAvg_diff_score(124);
		bean.setMin_score(677);
		bean.setLuqu_count(57);
		bean.setLuqu_batch("本科第一批");
		testSchScoreList.add(bean);
		bean = new SchScore();
		bean.setRank_index(2);
		bean.setYear(2013);
		bean.setMax_score(710);
		bean.setAvg_score(679);
		bean.setAvg_diff_score(105);
		bean.setMin_score(661);
		bean.setLuqu_count(71);
		bean.setLuqu_batch("本科第一批");
		testSchScoreList.add(bean);
		bean = new SchScore();
		bean.setRank_index(3);
		bean.setYear(2012);
		bean.setMax_score(705);
		bean.setAvg_score(686);
		bean.setAvg_diff_score(101);
		bean.setMin_score(671);
		bean.setLuqu_count(53);
		bean.setLuqu_batch("本科第一批");
		testSchScoreList.add(bean);
		
		Assert.assertTrue(totalCount == schScoreListResult.getScores().size());
		for(int i = 0; i < testSchScoreList.size(); i++) {
			SchScore tempBean = testSchScoreList.get(i);
			SchScore dataBean = schScoreListResult.getScores().get(i);
			Assert.assertTrue(tempBean.getRank_index() == dataBean.getRank_index());
			Assert.assertTrue(tempBean.getYear() == dataBean.getYear());
			Assert.assertTrue(tempBean.getMax_score() == dataBean.getMax_score());
			Assert.assertTrue(tempBean.getAvg_score() == dataBean.getAvg_score());
			Assert.assertTrue(tempBean.getAvg_diff_score() == dataBean.getAvg_diff_score());
			Assert.assertTrue(tempBean.getMin_score() == dataBean.getMin_score());
			Assert.assertTrue(tempBean.getLuqu_count() == dataBean.getLuqu_count());
			Assert.assertTrue(tempBean.getLuqu_batch().equals(dataBean.getLuqu_batch()));
			System.out.println("通过本科录取分数第" + (i + 1) + "个.");
		}
		System.out.println("通过本科学校录取分数线测试.");
		
		schId = "52ac2e9b747aec013fcf5319";
		diplomaId = 5;
		wenliFilter = "li";
		provinceIdFilter = "500000000000";
		url = HOST + "school/" + schId + "/luqu_scores/in_school?diploma_id=" + diplomaId + "&wenli_filter=" + wenliFilter + "&province_id_filter=" + provinceIdFilter;
		startTime = System.currentTimeMillis();
		webTarget = client.target(url);
		request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		schScoreListResult = new ObjectMapper().readValue(response.readEntity(String.class), SchScoreListResult.class);
		totalCount = 2;
		testSchScoreList = new ArrayList<SchScore>();
		bean = new SchScore();
		bean.setRank_index(1);
		bean.setYear(2014);
		bean.setMax_score(468);
		bean.setAvg_score(454);
		bean.setAvg_diff_score(32);
		bean.setMin_score(427);
		bean.setLuqu_count(6);
		bean.setLuqu_batch("本科第三批");
		testSchScoreList.add(bean);
		bean = new SchScore();
		bean.setRank_index(2);
		bean.setYear(2014);
		bean.setMax_score(418);
		bean.setAvg_score(359);
		bean.setAvg_diff_score(146);
		bean.setMin_score(308);
		bean.setLuqu_count(15);
		bean.setLuqu_batch("专科第一批");
		testSchScoreList.add(bean);
		
		Assert.assertTrue(totalCount == schScoreListResult.getScores().size());
		for(int i = 0; i < testSchScoreList.size(); i++) {
			SchScore tempBean = testSchScoreList.get(i);
			SchScore dataBean = schScoreListResult.getScores().get(i);
			Assert.assertTrue(tempBean.getRank_index() == dataBean.getRank_index());
			Assert.assertTrue(tempBean.getYear() == dataBean.getYear());
			Assert.assertTrue(tempBean.getMax_score() == dataBean.getMax_score());
			Assert.assertTrue(tempBean.getAvg_score() == dataBean.getAvg_score());
			Assert.assertTrue(tempBean.getAvg_diff_score() == dataBean.getAvg_diff_score());
			Assert.assertTrue(tempBean.getMin_score() == dataBean.getMin_score());
			Assert.assertTrue(tempBean.getLuqu_count() == dataBean.getLuqu_count());
			Assert.assertTrue(tempBean.getLuqu_batch().equals(dataBean.getLuqu_batch()));
			System.out.println("通过专科录取分数第" + (i + 1) + "个.");
		}
		System.out.println("通过专科学校录取分数线测试.");
		
	}
	
	// 测试学校录取分数参数
	@Test
	public void testSchScoreParams() throws Exception{
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);
		
		// 查询专科学校录取分数参数
		Client client = ClientBuilder.newClient(clientConfig);
		String schId = "52ac2e9b747aec013fcf5319";
		int diplomaId = 5;
		String url = HOST + "school/" + schId + "/luqu_scores/in_school_params?diploma_id=" + diplomaId;
		long startTime = System.currentTimeMillis();
		WebTarget webTarget = client.target(url);
		Builder request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		Response response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		SchScoreParamsResult schScoreParamsResult = new ObjectMapper().readValue(response.readEntity(String.class), SchScoreParamsResult.class);
		// 查询省份信息
		String level = "province";
		url = HOST + "location?level=" + level;
		startTime = System.currentTimeMillis();
		webTarget = client.target(url);
		request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		response = null;
		response = request.get();
		LocationListResult locationListResult = new ObjectMapper().readValue(response.readEntity(String.class),
				LocationListResult.class);
		for (String provinceId : schScoreParamsResult.getProvince_ids()) {
			for (Location location : locationListResult.getItems()) {
				if (location.getLoc_id().equals(provinceId)) {
					System.out.println(provinceId + ":" + location.getName());
				}
			}
		}
		int provinceSize = 17;
		Assert.assertTrue(provinceSize == schScoreParamsResult.getProvince_ids().size());
		System.out.println("通过专科学校录取分数参数");
		
		// 查看本科
		schId = "52ac2e99747aec013fcf4e6f";
		diplomaId = 7;
		url = HOST + "school/" + schId + "/luqu_scores/in_school_params?diploma_id=" + diplomaId;
		startTime = System.currentTimeMillis();
		webTarget = client.target(url);
		request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		schScoreParamsResult = new ObjectMapper().readValue(response.readEntity(String.class), SchScoreParamsResult.class);
		System.out.println(schScoreParamsResult.getProvince_ids().size());
		for (String provinceId : schScoreParamsResult.getProvince_ids()) {
			for (Location location : locationListResult.getItems()) {
				if (location.getLoc_id().equals(provinceId)) {
					System.out.println(provinceId + ":" + location.getName());
				}
			}
		}
	}
	
	// 测试学校专业录取分数
	@Test
	public void testSchMajorScoreList() throws Exception{
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);

		Client client = ClientBuilder.newClient(clientConfig);
		String schId = "52ac2e99747aec013fcf4e6f";
		int diplomaId = 7;
		String wenliFilter = "li";
		String provinceIdFilter = "440000000000";
		String yearFilter = "2014";
		String url = HOST + "school/" + schId + "/luqu_scores/in_major?diploma_id=" + diplomaId + "&wenli_filter=" + wenliFilter + "&province_id_filter=" + provinceIdFilter + "&year_filter=" + yearFilter;
		long startTime = System.currentTimeMillis();
		WebTarget webTarget = client.target(url);
		Builder request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		Response response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		SchMajorScoreListResult schMajorScoreListResult = new ObjectMapper().readValue(response.readEntity(String.class), SchMajorScoreListResult.class);
		System.out.println(schMajorScoreListResult.toString());
		int totalCount = 24;
		List<SchMajorScore> testSchMajorScoreList = new ArrayList<SchMajorScore>();
		SchMajorScore bean = new SchMajorScore();
		bean.setRank_index(1);
		bean.setMajor_id("52aedf5b747aec1cfc84158e");
		bean.setMajor_name("经济与金融");
		bean.setMajor_category("经济学");
		bean.setMajor_second_category("金融学类");
		bean.setMax_score(711);
		bean.setAvg_score(699);
		bean.setAvg_diff_score(139);
		bean.setLuqu_batch("本科第一批");
		testSchMajorScoreList.add(bean);
		bean = new SchMajorScore();
		bean.setRank_index(2);
		bean.setMajor_id("52aedf5b747aec1cfc841615");
		bean.setMajor_name("数理基础科学");
		bean.setMajor_category("理学");
		bean.setMajor_second_category("数学类");
		bean.setMax_score(708);
		bean.setAvg_score(690);
		bean.setAvg_diff_score(130);
		bean.setLuqu_batch("本科第一批");
		testSchMajorScoreList.add(bean);
		bean = new SchMajorScore();
		bean.setRank_index(3);
		bean.setMajor_id("52aedf5b747aec1cfc84165a");
		bean.setMajor_name("电气工程与智能控制");
		bean.setMajor_category("工学");
		bean.setMajor_second_category("电气类");
		bean.setMax_score(705);
		bean.setAvg_score(692);
		bean.setAvg_diff_score(132);
		bean.setLuqu_batch("本科第一批");
		testSchMajorScoreList.add(bean);
		
		Assert.assertTrue(totalCount == schMajorScoreListResult.getScores().size());
		for(int i = 0; i < testSchMajorScoreList.size(); i++) {
			SchMajorScore testBean = testSchMajorScoreList.get(i);
			SchMajorScore dataBean = schMajorScoreListResult.getScores().get(i);
			Assert.assertTrue(testBean.getRank_index() == dataBean.getRank_index());
			Assert.assertTrue(testBean.getMajor_id().equals(dataBean.getMajor_id()));
			Assert.assertTrue(testBean.getMajor_name().equals(dataBean.getMajor_name()));
			Assert.assertTrue(testBean.getMajor_category().equals(dataBean.getMajor_category()));
			Assert.assertTrue(testBean.getMajor_second_category().equals(dataBean.getMajor_second_category()));
			Assert.assertTrue(testBean.getMax_score() == dataBean.getMax_score());
			Assert.assertTrue(testBean.getAvg_score() == dataBean.getAvg_score());
			Assert.assertTrue(testBean.getAvg_diff_score() == dataBean.getAvg_diff_score());
			Assert.assertTrue(testBean.getLuqu_batch().equals(dataBean.getLuqu_batch()));
			System.out.println("通过本科第" + (i + 1) + "个.");
		}
		System.out.println("通过本科测试");
		
		
		schId = "52ac2e9b747aec013fcf5319";
		diplomaId = 5;
		wenliFilter = "li";
		provinceIdFilter = "440000000000";
		yearFilter = "2014";
		url = HOST + "school/" + schId + "/luqu_scores/in_major?diploma_id=" + diplomaId + "&wenli_filter=" + wenliFilter + "&province_id_filter=" + provinceIdFilter + "&year_filter=" + yearFilter;
		startTime = System.currentTimeMillis();
		webTarget = client.target(url);
		request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		schMajorScoreListResult = new ObjectMapper().readValue(response.readEntity(String.class), SchMajorScoreListResult.class);
		System.out.println(schMajorScoreListResult.toString());
		totalCount = 5;
		testSchMajorScoreList = new ArrayList<SchMajorScore>();
		bean = new SchMajorScore();
		bean.setRank_index(1);
		bean.setMajor_id("52aedf5b747aec1cfc8413ae");
		bean.setMajor_name("电气自动化技术");
		bean.setMajor_category("装备制造大类");
		bean.setMajor_second_category("自动化类");
		bean.setMax_score(380);
		bean.setAvg_score(380);
		bean.setAvg_diff_score(100);
		bean.setLuqu_batch("专科第二批");
		testSchMajorScoreList.add(bean);
		bean = new SchMajorScore();
		bean.setRank_index(2);
		bean.setMajor_id("52aedf5b747aec1cfc841369");
		bean.setMajor_name("工程造价");
		bean.setMajor_category("土建大类");
		bean.setMajor_second_category("工程管理类");
		bean.setMax_score(377);
		bean.setAvg_score(330);
		bean.setAvg_diff_score(50);
		bean.setLuqu_batch("专科第二批");
		testSchMajorScoreList.add(bean);
		bean = new SchMajorScore();
		bean.setRank_index(3);
		bean.setMajor_id("52aedf5b747aec1cfc84135d");
		bean.setMajor_name("建筑工程技术");
		bean.setMajor_category("土木建筑大类");
		bean.setMajor_second_category("土建施工类");
		bean.setMax_score(368);
		bean.setAvg_score(360);
		bean.setAvg_diff_score(80);
		bean.setLuqu_batch("专科第二批");
		testSchMajorScoreList.add(bean);
		
		Assert.assertTrue(totalCount == schMajorScoreListResult.getScores().size());
		for(int i = 0; i < testSchMajorScoreList.size(); i++) {
			SchMajorScore testBean = testSchMajorScoreList.get(i);
			SchMajorScore dataBean = schMajorScoreListResult.getScores().get(i);
			Assert.assertTrue(testBean.getRank_index() == dataBean.getRank_index());
			Assert.assertTrue(testBean.getMajor_id().equals(dataBean.getMajor_id()));
			Assert.assertTrue(testBean.getMajor_name().equals(dataBean.getMajor_name()));
			Assert.assertTrue(testBean.getMajor_category().equals(dataBean.getMajor_category()));
			Assert.assertTrue(testBean.getMajor_second_category().equals(dataBean.getMajor_second_category()));
			Assert.assertTrue(testBean.getMax_score() == dataBean.getMax_score());
			Assert.assertTrue(testBean.getAvg_score() == dataBean.getAvg_score());
			Assert.assertTrue(testBean.getAvg_diff_score() == dataBean.getAvg_diff_score());
			Assert.assertTrue(testBean.getLuqu_batch().equals(dataBean.getLuqu_batch()));
			System.out.println("通过专科第" + (i + 1) + "个.");
		}
		System.out.println("通过专科测试");
	}
	
	// 测试学校专业录取分数参数
	@Test
	public void testSchMajorScoreParams() throws Exception{
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);

		Client client = ClientBuilder.newClient(clientConfig);
		String schId = "52ac2e9b747aec013fcf5319";
		int diplomaId = 5;
		String url = HOST + "school/" + schId + "/luqu_scores/in_major_params?diploma_id=" + diplomaId;
		long startTime = System.currentTimeMillis();
		WebTarget webTarget = client.target(url);
		Builder request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		Response response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		SchMajorScoreParamsResult schMajorScoreParamsResult = new ObjectMapper().readValue(response.readEntity(String.class), SchMajorScoreParamsResult.class);
		System.out.println(schMajorScoreParamsResult.getProvince_ids().size());
		// 查询省份信息
		String level = "province";
		url = HOST + "location?level=" + level;
		startTime = System.currentTimeMillis();
		webTarget = client.target(url);
		request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		response = null;
		response = request.get();
		LocationListResult locationListResult = new ObjectMapper().readValue(response.readEntity(String.class),
				LocationListResult.class);
		for (String provinceId : schMajorScoreParamsResult.getProvince_ids()) {
			for (Location location : locationListResult.getItems()) {
				if (location.getLoc_id().equals(provinceId)) {
					System.out.println(provinceId + ":" + location.getName());
				}
			}
		}
		int provinceSize = 25;
		Assert.assertTrue(provinceSize == schMajorScoreParamsResult.getProvince_ids().size());
		
		
		// 查询本科
		schId = "52ac2e99747aec013fcf4e6f";
		diplomaId = 7;
		url = HOST + "school/" + schId + "/luqu_scores/in_major_params?diploma_id=" + diplomaId;
		startTime = System.currentTimeMillis();
		webTarget = client.target(url);
		request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		schMajorScoreParamsResult = new ObjectMapper().readValue(response.readEntity(String.class), SchMajorScoreParamsResult.class);
		for (String provinceId : schMajorScoreParamsResult.getProvince_ids()) {
			for (Location location : locationListResult.getItems()) {
				if (location.getLoc_id().equals(provinceId)) {
					System.out.println(provinceId + ":" + location.getName());
				}
			}
		}
		provinceSize = 27;
		Assert.assertTrue(provinceSize == schMajorScoreParamsResult.getProvince_ids().size());
		System.out.println("通过本科");
	}
	
	// 测试学校招生计划
	@Test
	public void testSchEnrollPlanList() throws Exception{
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);

		Client client = ClientBuilder.newClient(clientConfig);
		String schId = "52ac2e99747aec013fcf4e6f";
		int diplomaId = 7;
		String wenliFilter = "li";
		String provinceFilter = "广东";
		String yearFilter = "2015";
		String url = HOST + "school/" + schId + "/zs_plan?diploma_id=" + diplomaId + "&wenli_filter=" + wenliFilter + "&province_filter=" + URLEncoder.encode(provinceFilter, "utf-8") + "&year_filter=" + yearFilter;
		long startTime = System.currentTimeMillis();
		WebTarget webTarget = client.target(url);
		Builder request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		Response response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		SchEnrollPlanListResult schEnrollPlanListResult = new ObjectMapper().readValue(response.readEntity(String.class), SchEnrollPlanListResult.class);
		System.out.println(schEnrollPlanListResult.toString());
		List<SchEnrollPlan> schEnrollPlanList = new ArrayList<SchEnrollPlan>();
		int totalCount = 29;
		SchEnrollPlan bean = new SchEnrollPlan();
		bean.setRank_index(1);
		bean.setMajor_id("52aedf5b747aec1cfc8416fc");
		bean.setMajor_name("临床医学");
		bean.setPlan_type("非定向");
		bean.setWenli("理工");
		bean.setZhaosheng_count(3);
		bean.setZhaosheng_xuezhi("-");
		bean.setMajor_has_stats(false);
		schEnrollPlanList.add(bean);
		bean = new SchEnrollPlan();
		bean.setRank_index(2);
		bean.setMajor_id("52aedf5b747aec1cfc841688");
		bean.setMajor_name("化学工程与工业生物工程");
		bean.setPlan_type("非定向");
		bean.setWenli("理工");
		bean.setZhaosheng_count(3);
		bean.setZhaosheng_xuezhi("-");
		bean.setMajor_has_stats(true);
		schEnrollPlanList.add(bean);
		bean = new SchEnrollPlan();
		bean.setRank_index(3);
		bean.setMajor_id("52aedf5b747aec1cfc841676");
		bean.setMajor_name("土木工程");
		bean.setPlan_type("非定向");
		bean.setWenli("理工");
		bean.setZhaosheng_count(2);
		bean.setZhaosheng_xuezhi("-");
		bean.setMajor_has_stats(true);
		schEnrollPlanList.add(bean);
		
		Assert.assertTrue(totalCount == schEnrollPlanListResult.getPlans().size());
		for(int i = 0; i < schEnrollPlanList.size(); i++){
			SchEnrollPlan testBean = schEnrollPlanList.get(i);
			SchEnrollPlan dataBean = schEnrollPlanListResult.getPlans().get(i);
			Assert.assertTrue(testBean.getRank_index() == dataBean.getRank_index());
			Assert.assertTrue(testBean.getMajor_id().equals(dataBean.getMajor_id()));
			Assert.assertTrue(testBean.getMajor_name().equals(dataBean.getMajor_name()));
			Assert.assertTrue(testBean.getPlan_type().equals(dataBean.getPlan_type()));
			Assert.assertTrue(testBean.getWenli().equals(dataBean.getWenli()));
			Assert.assertTrue(testBean.getZhaosheng_count() == dataBean.getZhaosheng_count());
			Assert.assertTrue(testBean.getZhaosheng_xuezhi().equals(dataBean.getZhaosheng_xuezhi()));
			Assert.assertTrue(testBean.isMajor_has_stats() == dataBean.isMajor_has_stats());
			System.out.println("通过本科第" + (i + 1) + "个测试.");
		}
		System.out.println("通过本科测试.");
		
		schId = "52ac2e9b747aec013fcf5319";
		diplomaId = 5;
		wenliFilter = "li";
		provinceFilter = "广东";
		yearFilter = "2015";
		url = HOST + "school/" + schId + "/zs_plan?diploma_id=" + diplomaId + "&wenli_filter=" + wenliFilter + "&province_filter=" + URLEncoder.encode(provinceFilter, "utf-8") + "&year_filter=" + yearFilter;
		startTime = System.currentTimeMillis();
		webTarget = client.target(url);
		request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		schEnrollPlanListResult = new ObjectMapper().readValue(response.readEntity(String.class), SchEnrollPlanListResult.class);
		System.out.println(schEnrollPlanListResult.toString());
		schEnrollPlanList = new ArrayList<SchEnrollPlan>();
		totalCount = 7;
		bean = new SchEnrollPlan();
		bean.setRank_index(1);
		bean.setMajor_id("52aedf5b747aec1cfc841369");
		bean.setMajor_name("工程造价");
		bean.setPlan_type("非定向");
		bean.setWenli("理工");
		bean.setZhaosheng_count(4);
		bean.setZhaosheng_xuezhi("-");
		bean.setMajor_has_stats(true);
		schEnrollPlanList.add(bean);
		bean = new SchEnrollPlan();
		bean.setRank_index(2);
		bean.setMajor_id("52aedf5b747aec1cfc84135d");
		bean.setMajor_name("建筑工程技术");
		bean.setPlan_type("非定向");
		bean.setWenli("理工");
		bean.setZhaosheng_count(3);
		bean.setZhaosheng_xuezhi("-");
		bean.setMajor_has_stats(true);
		schEnrollPlanList.add(bean);
		bean = new SchEnrollPlan();
		bean.setRank_index(3);
		bean.setMajor_id("52aedf5b747aec1cfc841355");
		bean.setMajor_name("建筑装饰工程技术");
		bean.setPlan_type("非定向");
		bean.setWenli("理工");
		bean.setZhaosheng_count(2);
		bean.setZhaosheng_xuezhi("-");
		bean.setMajor_has_stats(true);
		schEnrollPlanList.add(bean);
		
		Assert.assertTrue(totalCount == schEnrollPlanListResult.getPlans().size());
		for(int i = 0; i < schEnrollPlanList.size(); i++){
			SchEnrollPlan testBean = schEnrollPlanList.get(i);
			SchEnrollPlan dataBean = schEnrollPlanListResult.getPlans().get(i);
			Assert.assertTrue(testBean.getRank_index() == dataBean.getRank_index());
			Assert.assertTrue(testBean.getMajor_id().equals(dataBean.getMajor_id()));
			Assert.assertTrue(testBean.getMajor_name().equals(dataBean.getMajor_name()));
			Assert.assertTrue(testBean.getPlan_type().equals(dataBean.getPlan_type()));
			Assert.assertTrue(testBean.getWenli().equals(dataBean.getWenli()));
			Assert.assertTrue(testBean.getZhaosheng_count() == dataBean.getZhaosheng_count());
			Assert.assertTrue(testBean.getZhaosheng_xuezhi().equals(dataBean.getZhaosheng_xuezhi()));
			Assert.assertTrue(testBean.isMajor_has_stats() == dataBean.isMajor_has_stats());
			System.out.println("通过专科第" + (i + 1) + "个测试.");
		}
		System.out.println("通过专科测试.");
		
	}
	
	// 测试学校筛选列表
//	@Test
	public void testSchFilterList() throws Exception{
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);

		Client client = ClientBuilder.newClient(clientConfig);
		String provinceFilter = "";
		int diplomaId = 7;
		String schoolType = "";
		String majorSecondCategoryFilter = "";
		String majorIdFilter = "";
		String schNamePattern = "";
		int count = 20;
		int pageNo = 1;
		String url = HOST + "school?province_filter=" + URLEncoder.encode(provinceFilter, "utf-8") +"&diploma_id=" + diplomaId + "&school_type=" + URLEncoder.encode(schoolType, "utf-8") + "&major_second_category_filter=" + URLEncoder.encode(majorSecondCategoryFilter, "utf-8") + "&major_filter=" + majorIdFilter + "&sch_name_pattern=" + schNamePattern + "&count=" + count + "&page_no=" + pageNo;
		long startTime = System.currentTimeMillis();
		WebTarget webTarget = client.target(url);
		Builder request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		Response response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		SchFilterListResult schFileterListResult = new ObjectMapper().readValue(response.readEntity(String.class), SchFilterListResult.class);
		int totalPage = 1;
		int totalCount = 5;
		List<SchFilter> schFilterList = new ArrayList<SchFilter>();
		SchFilter schFilter = new SchFilter();
		schFilter.setSch_id("52ac2e99747aec013fcf4e6f");
		schFilter.setSch_name("清华大学");
		schFilter.setSch_type("工科院校");
		schFilter.setLocation("北京市");
		schFilter.setDegrees("本科");
		schFilter.setTotal_rank_str("A++");
		schFilterList.add(schFilter);
		schFilter = new SchFilter();
		schFilter.setSch_id("52ac2e99747aec013fcf4dc1");
		schFilter.setSch_name("北京邮电大学");
		schFilter.setSch_type("工科院校");
		schFilter.setLocation("北京市");
		schFilter.setDegrees("本科");
		schFilter.setTotal_rank_str("A+");
		schFilterList.add(schFilter);
		schFilter = new SchFilter();
		schFilter.setSch_id("52ac2e9a747aec013fcf501c");
		schFilter.setSch_name("北京科技大学");
		schFilter.setSch_type("工科院校");
		schFilter.setLocation("北京市");
		schFilter.setDegrees("本科/专科");
		schFilter.setTotal_rank_str("A+");
		schFilterList.add(schFilter);
		schFilter = new SchFilter();
		schFilter.setSch_id("52ac2e98747aec013fcf4cc6");
		schFilter.setSch_name("中国地质大学(北京)");
		schFilter.setSch_type("工科院校");
		schFilter.setLocation("北京市");
		schFilter.setDegrees("本科");
		schFilter.setTotal_rank_str("A");
		schFilterList.add(schFilter);
		schFilter = new SchFilter();
		schFilter.setSch_id("52ac2e99747aec013fcf4cf8");
		schFilter.setSch_name("中国石油大学(北京)");
		schFilter.setSch_type("工科院校");
		schFilter.setLocation("北京市");
		schFilter.setDegrees("本科");
		schFilter.setTotal_rank_str("A-");
		schFilterList.add(schFilter);
		
		Assert.assertTrue(totalPage == schFileterListResult.getTotal_page());
		Assert.assertTrue(totalCount == schFileterListResult.getSchools().size());
		for(int i = 0; i < schFilterList.size(); i++) {
			SchFilter testBean = schFilterList.get(i);
			SchFilter dataBean = schFileterListResult.getSchools().get(i);
			Assert.assertTrue(testBean.getSch_id().equals(dataBean.getSch_id()));
			Assert.assertTrue(testBean.getSch_name().equals(dataBean.getSch_name()));
			Assert.assertTrue(testBean.getSch_type().equals(dataBean.getSch_type()));
			Assert.assertTrue(testBean.getDegrees().equals(dataBean.getDegrees()));
			Assert.assertTrue(testBean.getTotal_rank_str().equals(dataBean.getTotal_rank_str()));
			System.out.println("通过学校筛选本科第" + (i + 1) + "个.");
		}
		System.out.println("通过本科筛选");
		
		provinceFilter = "北京市";
		diplomaId = 7;
		schoolType = "工科";
		majorSecondCategoryFilter = "新闻传播学类";
		majorIdFilter = "";
		schNamePattern = "";
		count = 20;
		pageNo = 1;
		url = HOST + "school?province_filter=" + URLEncoder.encode(provinceFilter, "utf-8") +"&diploma_id=" + diplomaId + "&school_type=" + URLEncoder.encode(schoolType, "utf-8") + "&major_second_category_filter=" + URLEncoder.encode(majorSecondCategoryFilter, "utf-8") + "&major_filter=" + majorIdFilter + "&sch_name_pattern=" + schNamePattern + "&count=" + count + "&page_no=" + pageNo;
		startTime = System.currentTimeMillis();
		webTarget = client.target(url);
		request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		schFileterListResult = new ObjectMapper().readValue(response.readEntity(String.class), SchFilterListResult.class);
		totalPage = 1;
		totalCount = 7;
		schFilterList = new ArrayList<SchFilter>();
		schFilter = new SchFilter();
		schFilter.setSch_id("52ac2e99747aec013fcf4e6f");
		schFilter.setSch_name("清华大学");
		schFilter.setSch_type("工科院校");
		schFilter.setLocation("北京市");
		schFilter.setDegrees("本科");
		schFilter.setTotal_rank_str("A++");
		schFilterList.add(schFilter);
		schFilter = new SchFilter();
		schFilter.setSch_id("52ac2e9a747aec013fcf526f");
		schFilter.setSch_name("北京工业大学");
		schFilter.setSch_type("工科院校");
		schFilter.setLocation("北京市");
		schFilter.setDegrees("本科/专科");
		schFilter.setTotal_rank_str("A");
		schFilterList.add(schFilter);
		schFilter = new SchFilter();
		schFilter.setSch_id("52ac2e99747aec013fcf4e1a");
		schFilter.setSch_name("华北电力大学(北京)");
		schFilter.setSch_type("工科院校");
		schFilter.setLocation("北京市");
		schFilter.setDegrees("本科");
		schFilter.setTotal_rank_str("A-");
		schFilterList.add(schFilter);
		
		Assert.assertTrue(totalPage == schFileterListResult.getTotal_page());
		Assert.assertTrue(totalCount == schFileterListResult.getSchools().size());
		for(int i = 0; i < schFilterList.size(); i++) {
			SchFilter testBean = schFilterList.get(i);
			SchFilter dataBean = schFileterListResult.getSchools().get(i);
			Assert.assertTrue(testBean.getSch_id().equals(dataBean.getSch_id()));
			Assert.assertTrue(testBean.getSch_name().equals(dataBean.getSch_name()));
			Assert.assertTrue(testBean.getSch_type().equals(dataBean.getSch_type()));
			Assert.assertTrue(testBean.getDegrees().equals(dataBean.getDegrees()));
			Assert.assertTrue(testBean.getTotal_rank_str().equals(dataBean.getTotal_rank_str()));
			System.out.println("通过学校筛选本科第" + (i + 1) + "个.");
		}
		System.out.println("通过本科筛选");
		
		provinceFilter = "广东";
		diplomaId = 5;
		schoolType = "师范";
		majorSecondCategoryFilter = "";
		majorIdFilter = "";
		schNamePattern = "";
		count = 20;
		pageNo = 1;
		url = HOST + "school?province_filter=" + URLEncoder.encode(provinceFilter, "utf-8") +"&diploma_id=" + diplomaId + "&school_type=" + URLEncoder.encode(schoolType, "utf-8") + "&major_second_category_filter=" + URLEncoder.encode(majorSecondCategoryFilter, "utf-8") + "&major_filter=" + majorIdFilter + "&sch_name_pattern=" + schNamePattern + "&count=" + count + "&page_no=" + pageNo;
		startTime = System.currentTimeMillis();
		webTarget = client.target(url);
		request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		schFileterListResult = new ObjectMapper().readValue(response.readEntity(String.class), SchFilterListResult.class);
		totalPage = 1;
		totalCount = 3;
		schFilterList = new ArrayList<SchFilter>();
		schFilter = new SchFilter();
		schFilter.setSch_id("52ac2e99747aec013fcf4db9");
		schFilter.setSch_name("岭南师范学院");
		schFilter.setSch_type("师范院校");
		schFilter.setLocation("广东");
		schFilter.setDegrees("本科/专科");
		schFilter.setSalary_factor(4603);
		schFilterList.add(schFilter);
		schFilter = new SchFilter();
		schFilter.setSch_id("52ac2e98747aec013fcf4bd3");
		schFilter.setSch_name("广东技术师范学院");
		schFilter.setSch_type("师范院校");
		schFilter.setLocation("广东");
		schFilter.setDegrees("本科/专科");
		schFilter.setSalary_factor(4342);
		schFilterList.add(schFilter);
		schFilter = new SchFilter();
		schFilter.setSch_id("52ac2e9b747aec013fcf532e");
		schFilter.setSch_name("韩山师范学院");
		schFilter.setSch_type("师范院校");
		schFilter.setLocation("广东");
		schFilter.setDegrees("本科/专科");
		schFilter.setSalary_factor(0);
		schFilterList.add(schFilter);
		
		Assert.assertTrue(totalPage == schFileterListResult.getTotal_page());
		Assert.assertTrue(totalCount == schFileterListResult.getSchools().size());
		for(int i = 0; i < schFilterList.size(); i++) {
			SchFilter testBean = schFilterList.get(i);
			SchFilter dataBean = schFileterListResult.getSchools().get(i);
			Assert.assertTrue(testBean.getSch_id().equals(dataBean.getSch_id()));
			Assert.assertTrue(testBean.getSch_name().equals(dataBean.getSch_name()));
			Assert.assertTrue(testBean.getSch_type().equals(dataBean.getSch_type()));
			Assert.assertTrue(testBean.getDegrees().equals(dataBean.getDegrees()));
			Assert.assertTrue(testBean.getSalary_factor() == dataBean.getSalary_factor());
			System.out.println("通过学校筛选本科第" + (i + 1) + "个.");
		}
		System.out.println("通过本科筛选");
	}
	
	@Test
	public void testSchRankingList() throws Exception{
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);

		Client client = ClientBuilder.newClient(clientConfig);
		String rankType = "zonghe";
		String provinceFilter = "";
		String schNamePattern = "";
		int count = 20;
		int pageNo = 1;
		String url = HOST + "school/rank?province_filter=" + URLEncoder.encode(provinceFilter, "utf-8") + "&rank_type=" + rankType + "&count=" + count + "&page_no=" + pageNo;
//		client.target(url).request().get();
		long startTime = System.currentTimeMillis();
		WebTarget webTarget = client.target(url);
		Builder request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		Response response = request.get();
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		TotalSchRankingListResult totalSchRankListResult = new ObjectMapper().readValue(response.readEntity(String.class), TotalSchRankingListResult.class);
		int totalPage = 36;
		List<TotalSchRankingList> schRankingLists = new ArrayList<TotalSchRankingList>();
		TotalSchRankingList bean = new TotalSchRankingList();
		bean.setRank_index(1);
		bean.setSch_id("52ac2e99747aec013fcf4e6f");
		bean.setSch_name("清华大学");
		bean.setSch_type("工科院校");
		bean.setLocation("北京市");
		bean.setDegrees("本科");
		bean.setTotal_rank_index(1);
		bean.setTotal_rank_str("A++");
		schRankingLists.add(bean);
		bean = new TotalSchRankingList();
		bean.setRank_index(2);
		bean.setSch_id("52ac2e9a747aec013fcf5190");
		bean.setSch_name("北京大学");
		bean.setSch_type("综合院校");
		bean.setLocation("北京市");
		bean.setDegrees("本科/专科");
		bean.setTotal_rank_index(2);
		bean.setTotal_rank_str("A++");
		schRankingLists.add(bean);
		bean = new TotalSchRankingList();
		bean.setRank_index(3);
		bean.setSch_id("52ac2e9b747aec013fcf5417");
		bean.setSch_name("复旦大学");
		bean.setSch_type("综合院校");
		bean.setLocation("上海市");
		bean.setDegrees("本科/专科");
		bean.setTotal_rank_index(3);
		bean.setTotal_rank_str("A++");
		schRankingLists.add(bean);
		
		Assert.assertTrue(totalPage == totalSchRankListResult.getTotal_page());
		for(int i = 0; i < schRankingLists.size(); i++) {
			TotalSchRankingList testBean = schRankingLists.get(i);
			TotalSchRankingList dataBean = totalSchRankListResult.getSchools().get(i);
			Assert.assertTrue(testBean.getRank_index() == dataBean.getRank_index());
			Assert.assertTrue(testBean.getSch_id().equals(dataBean.getSch_id()));
			Assert.assertTrue(testBean.getSch_name().equals(dataBean.getSch_name()));
			Assert.assertTrue(testBean.getSch_type().equals(dataBean.getSch_type()));
			Assert.assertTrue(testBean.getDegrees().equals(dataBean.getDegrees()));
			Assert.assertTrue(testBean.getLocation().equals(dataBean.getLocation()));
			Assert.assertTrue(testBean.getTotal_rank_index() == dataBean.getTotal_rank_index());
			Assert.assertTrue(testBean.getTotal_rank_str().equals(dataBean.getTotal_rank_str()));
			System.out.println("通过本科综合第" + (i + 1) + "个.");
		}
		System.out.println("通过本科综合测试");
		
		rankType = "zonghe";
		provinceFilter = "广东";
		schNamePattern = "";
		count = 40;
		pageNo = 1;
		url = HOST + "school/rank?province_filter=" + URLEncoder.encode(provinceFilter, "utf-8") + "&rank_type=" + rankType + "&count=" + count + "&page_no=" + pageNo;
		startTime = System.currentTimeMillis();
		webTarget = client.target(url);
		request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		totalSchRankListResult = new ObjectMapper().readValue(response.readEntity(String.class), TotalSchRankingListResult.class);
		totalPage = 1;
		int totalSize = 35;
		schRankingLists = new ArrayList<TotalSchRankingList>();
		bean = new TotalSchRankingList();
		bean.setRank_index(1);
		bean.setSch_id("52ac2e9b747aec013fcf5338");
		bean.setSch_name("中山大学");
		bean.setSch_type("综合院校");
		bean.setLocation("广东");
		bean.setDegrees("本科");
		bean.setTotal_rank_str("A+");
		schRankingLists.add(bean);
		bean = new TotalSchRankingList();
		bean.setRank_index(2);
		bean.setSch_id("52ac2e98747aec013fcf4b1b");
		bean.setSch_name("华南理工大学");
		bean.setSch_type("工科院校");
		bean.setLocation("广东");
		bean.setDegrees("本科");
		bean.setTotal_rank_str("A+");
		schRankingLists.add(bean);
		bean = new TotalSchRankingList();
		bean.setRank_index(3);
		bean.setSch_id("52ac2e99747aec013fcf4d06");
		bean.setSch_name("暨南大学");
		bean.setSch_type("综合院校");
		bean.setLocation("广东");
		bean.setDegrees("本科");
		bean.setTotal_rank_str("A+");
		schRankingLists.add(bean);
		
		Assert.assertTrue(totalPage == totalSchRankListResult.getTotal_page());
		Assert.assertTrue(totalSize == totalSchRankListResult.getSchools().size());
		for(int i = 0; i < schRankingLists.size(); i++) {
			TotalSchRankingList testBean = schRankingLists.get(i);
			TotalSchRankingList dataBean = totalSchRankListResult.getSchools().get(i);
			Assert.assertTrue(testBean.getRank_index() == dataBean.getRank_index());
			Assert.assertTrue(testBean.getSch_id().equals(dataBean.getSch_id()));
			Assert.assertTrue(testBean.getSch_name().equals(dataBean.getSch_name()));
			Assert.assertTrue(testBean.getSch_type().equals(dataBean.getSch_type()));
			Assert.assertTrue(testBean.getDegrees().equals(dataBean.getDegrees()));
			Assert.assertTrue(testBean.getLocation().equals(dataBean.getLocation()));
			Assert.assertTrue(testBean.getTotal_rank_str().equals(dataBean.getTotal_rank_str()));
			System.out.println("通过本科综合第" + (i + 1) + "个.");
		}
		System.out.println("通过本科综合测试");
		
//		rankType = "zhimingdu";
//		provinceFilter = "";
//		schNamePattern = "";
//		count = 800;
//		pageNo = 1;
//		url = HOST + "school/rank?province_filter=" + URLEncoder.encode(provinceFilter, "utf-8") + "&rank_type=" + rankType + "&count=" + count + "&page_no=" + pageNo;
//		startTime = System.currentTimeMillis();
//		webTarget = client.target(url);
//		request = webTarget.request();
//		request.header("Content-type", MediaType.APPLICATION_JSON);
//		response = request.get();
//		System.out.println(response.getStatus());
//		System.out.println(response.toString());
//		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
//		FamousSchRankingListResult famousSchRankListResult = new ObjectMapper().readValue(response.readEntity(String.class), FamousSchRankingListResult.class);
//		totalPage = 1;
//		totalSize = 709;
//		List<FamousSchRankingList> famousSchRankingLists = new ArrayList<FamousSchRankingList>();
//		FamousSchRankingList fBean = new FamousSchRankingList();
//		fBean.setRank_index(1);
//		fBean.setSch_id("52ac2e9a747aec013fcf5190");
//		fBean.setSch_name("北京大学");
//		fBean.setSch_type("综合院校");
//		fBean.setLocation("北京市");
//		fBean.setDegrees("本科/专科");
//		fBean.setFamous_rank_index(1);
//		fBean.setFamous_rank_str("A++");
//		famousSchRankingLists.add(fBean);
//		fBean = new FamousSchRankingList();
//		fBean.setRank_index(2);
//		fBean.setSch_id("52ac2e99747aec013fcf4e6f");
//		fBean.setSch_name("清华大学");
//		fBean.setSch_type("工科院校");
//		fBean.setLocation("北京市");
//		fBean.setDegrees("本科");
//		fBean.setFamous_rank_str("A++");
//		fBean.setFamous_rank_index(2);
//		famousSchRankingLists.add(fBean);
//		fBean = new FamousSchRankingList();
//		fBean.setRank_index(3);
//		fBean.setSch_id("52ac2e9b747aec013fcf5417");
//		fBean.setSch_name("复旦大学");
//		fBean.setSch_type("综合院校");
//		fBean.setLocation("上海市");
//		fBean.setDegrees("本科/专科");
//		fBean.setFamous_rank_str("A++");
//		fBean.setFamous_rank_index(3);
//		famousSchRankingLists.add(fBean);
//		
//		Assert.assertTrue(totalPage == famousSchRankListResult.getTotal_page());
//		Assert.assertTrue(totalSize == famousSchRankListResult.getSchools().size());
//		for(int i = 0; i < famousSchRankingLists.size(); i++) {
//			FamousSchRankingList testBean = famousSchRankingLists.get(i);
//			FamousSchRankingList dataBean = famousSchRankListResult.getSchools().get(i);
//			Assert.assertTrue(testBean.getRank_index() == dataBean.getRank_index());
//			Assert.assertTrue(testBean.getSch_id().equals(dataBean.getSch_id()));
//			Assert.assertTrue(testBean.getSch_name().equals(dataBean.getSch_name()));
//			Assert.assertTrue(testBean.getSch_type().equals(dataBean.getSch_type()));
//			Assert.assertTrue(testBean.getDegrees().equals(dataBean.getDegrees()));
//			Assert.assertTrue(testBean.getLocation().equals(dataBean.getLocation()));
//			Assert.assertTrue(testBean.getFamous_rank_index() == dataBean.getFamous_rank_index());
//			Assert.assertTrue(testBean.getFamous_rank_str().equals(dataBean.getFamous_rank_str()));
//			System.out.println("通过本科知名度第" + (i + 1) + "个.");
//		}
//		System.out.println("通过本科知名度测试");
//		
//		rankType = "zhimingdu";
//		provinceFilter = "内蒙古";
//		schNamePattern = "";
//		count = 20;
//		pageNo = 1;
//		url = HOST + "school/rank?province_filter=" + URLEncoder.encode(provinceFilter, "utf-8") + "&rank_type=" + rankType + "&count=" + count + "&page_no=" + pageNo;
//		startTime = System.currentTimeMillis();
//		webTarget = client.target(url);
//		request = webTarget.request();
//		request.header("Content-type", MediaType.APPLICATION_JSON);
//		response = request.get();
//		System.out.println(response.getStatus());
//		System.out.println(response.toString());
//		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
//		famousSchRankListResult = new ObjectMapper().readValue(response.readEntity(String.class), FamousSchRankingListResult.class);
//		totalPage = 1;
//		totalSize = 10;
//		famousSchRankingLists = new ArrayList<FamousSchRankingList>();
//		fBean = new FamousSchRankingList();
//		fBean.setRank_index(1);
//		fBean.setSch_id("52ac2e9a747aec013fcf5090");
//		fBean.setSch_name("内蒙古大学");
//		fBean.setSch_type("综合院校");
//		fBean.setLocation("内蒙古");
//		fBean.setDegrees("本科/专科");
//		fBean.setFamous_rank_str("B+");
//		famousSchRankingLists.add(fBean);
//		fBean = new FamousSchRankingList();
//		fBean.setRank_index(2);
//		fBean.setSch_id("52ac2e98747aec013fcf4ae5");
//		fBean.setSch_name("内蒙古师范大学");
//		fBean.setSch_type("师范院校");
//		fBean.setLocation("内蒙古");
//		fBean.setDegrees("本科/专科");
//		fBean.setFamous_rank_str("B");
//		famousSchRankingLists.add(fBean);
//		fBean = new FamousSchRankingList();
//		fBean.setRank_index(3);
//		fBean.setSch_id("52ac2e99747aec013fcf4dcb");
//		fBean.setSch_name("内蒙古农业大学");
//		fBean.setSch_type("农业院校");
//		fBean.setLocation("内蒙古");
//		fBean.setDegrees("本科/专科");
//		fBean.setFamous_rank_str("B");
//		famousSchRankingLists.add(fBean);
//		
//		Assert.assertTrue(totalPage == famousSchRankListResult.getTotal_page());
//		Assert.assertTrue(totalSize == famousSchRankListResult.getSchools().size());
//		for(int i = 0; i < famousSchRankingLists.size(); i++) {
//			FamousSchRankingList testBean = famousSchRankingLists.get(i);
//			FamousSchRankingList dataBean = famousSchRankListResult.getSchools().get(i);
//			Assert.assertTrue(testBean.getRank_index() == dataBean.getRank_index());
//			Assert.assertTrue(testBean.getSch_id().equals(dataBean.getSch_id()));
//			Assert.assertTrue(testBean.getSch_name().equals(dataBean.getSch_name()));
//			Assert.assertTrue(testBean.getSch_type().equals(dataBean.getSch_type()));
//			Assert.assertTrue(testBean.getDegrees().equals(dataBean.getDegrees()));
//			Assert.assertTrue(testBean.getLocation().equals(dataBean.getLocation()));
//			Assert.assertTrue(testBean.getFamous_rank_str().equals(dataBean.getFamous_rank_str()));
//			System.out.println("通过本科知名度第" + (i + 1) + "个.");
//		}
//		System.out.println("通过本科知名度测试");
//		
//		rankType = "jingzhengli";
//		provinceFilter = "";
//		schNamePattern = "";
//		count = 800;
//		pageNo = 1;
//		url = HOST + "school/rank?province_filter=" + URLEncoder.encode(provinceFilter, "utf-8") + "&rank_type=" + rankType + "&count=" + count + "&page_no=" + pageNo;
//		startTime = System.currentTimeMillis();
//		webTarget = client.target(url);
//		request = webTarget.request();
//		request.header("Content-type", MediaType.APPLICATION_JSON);
//		response = request.get();
//		System.out.println(response.getStatus());
//		System.out.println(response.toString());
//		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
//		CompetitivenessSchRankingListResult competitivenessSchRankListResult = new ObjectMapper().readValue(response.readEntity(String.class), CompetitivenessSchRankingListResult.class);
//		totalPage = 1;
//		totalSize = 50;
//		List<CompetitivenessSchRankingList> competitivenessSchRankingLists = new ArrayList<CompetitivenessSchRankingList>();
//		CompetitivenessSchRankingList cBean = new CompetitivenessSchRankingList();
//		cBean.setRank_index(1);
//		cBean.setSch_id("52ac2e99747aec013fcf4e6f");
//		cBean.setSch_name("清华大学");
//		cBean.setSch_type("工科院校");
//		cBean.setLocation("北京市");
//		cBean.setDegrees("本科");
//		cBean.setCompetitiveness_rank_index(1);
//		competitivenessSchRankingLists.add(cBean);
//		cBean = new CompetitivenessSchRankingList();
//		cBean.setRank_index(2);
//		cBean.setSch_id("52ac2e9a747aec013fcf5190");
//		cBean.setSch_name("北京大学");
//		cBean.setSch_type("综合院校");
//		cBean.setLocation("北京市");
//		cBean.setDegrees("本科/专科");
//		cBean.setCompetitiveness_rank_index(2);
//		competitivenessSchRankingLists.add(cBean);
//		cBean = new CompetitivenessSchRankingList();
//		cBean.setRank_index(3);
//		cBean.setSch_id("52ac2e97747aec013fcf4a45");
//		cBean.setSch_name("北京外国语大学");
//		cBean.setSch_type("语言院校");
//		cBean.setLocation("北京市");
//		cBean.setDegrees("本科");
//		cBean.setCompetitiveness_rank_index(3);
//		competitivenessSchRankingLists.add(cBean);
//		
//		Assert.assertTrue(totalPage == competitivenessSchRankListResult.getTotal_page());
//		Assert.assertTrue(totalSize == competitivenessSchRankListResult.getSchools().size());
//		for(int i = 0; i < competitivenessSchRankingLists.size(); i++) {
//			CompetitivenessSchRankingList testBean = competitivenessSchRankingLists.get(i);
//			CompetitivenessSchRankingList dataBean = competitivenessSchRankListResult.getSchools().get(i);
//			Assert.assertTrue(testBean.getRank_index() == dataBean.getRank_index());
//			Assert.assertTrue(testBean.getSch_id().equals(dataBean.getSch_id()));
//			Assert.assertTrue(testBean.getSch_name().equals(dataBean.getSch_name()));
//			Assert.assertTrue(testBean.getSch_type().equals(dataBean.getSch_type()));
//			Assert.assertTrue(testBean.getDegrees().equals(dataBean.getDegrees()));
//			Assert.assertTrue(testBean.getLocation().equals(dataBean.getLocation()));
//			Assert.assertTrue(testBean.getCompetitiveness_rank_index() == dataBean.getCompetitiveness_rank_index());
//			System.out.println("通过本科竞争力第" + (i + 1) + "个.");
//		}
//		System.out.println("通过本科竞争力测试");
//		
//		rankType = "jingzhengli";
//		provinceFilter = "广东";
//		schNamePattern = "";
//		count = 800;
//		pageNo = 1;
//		url = HOST + "school/rank?province_filter=" + URLEncoder.encode(provinceFilter, "utf-8") + "&rank_type=" + rankType + "&count=" + count + "&page_no=" + pageNo;
//		startTime = System.currentTimeMillis();
//		webTarget = client.target(url);
//		request = webTarget.request();
//		request.header("Content-type", MediaType.APPLICATION_JSON);
//		response = request.get();
//		System.out.println(response.getStatus());
//		System.out.println(response.toString());
//		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
//		competitivenessSchRankListResult = new ObjectMapper().readValue(response.readEntity(String.class), CompetitivenessSchRankingListResult.class);
//		totalPage = 1;
//		totalSize = 4;
//		competitivenessSchRankingLists = new ArrayList<CompetitivenessSchRankingList>();
//		cBean = new CompetitivenessSchRankingList();
//		cBean.setRank_index(1);
//		cBean.setSch_id("52ac2e9b747aec013fcf5338");
//		cBean.setSch_name("中山大学");
//		cBean.setSch_type("综合院校");
//		cBean.setLocation("广东");
//		cBean.setDegrees("本科");
//		competitivenessSchRankingLists.add(cBean);
//		cBean = new CompetitivenessSchRankingList();
//		cBean.setRank_index(2);
//		cBean.setSch_id("52ac2e9a747aec013fcf5270");
//		cBean.setSch_name("广东外语外贸大学");
//		cBean.setSch_type("语言院校");
//		cBean.setLocation("广东");
//		cBean.setDegrees("本科");
//		competitivenessSchRankingLists.add(cBean);
//		cBean = new CompetitivenessSchRankingList();
//		cBean.setRank_index(3);
//		cBean.setSch_id("52ac2e99747aec013fcf4d06");
//		cBean.setSch_name("暨南大学");
//		cBean.setSch_type("综合院校");
//		cBean.setLocation("广东");
//		cBean.setDegrees("本科");
//		competitivenessSchRankingLists.add(cBean);
//		
//		Assert.assertTrue(totalPage == competitivenessSchRankListResult.getTotal_page());
//		Assert.assertTrue(totalSize == competitivenessSchRankListResult.getSchools().size());
//		for(int i = 0; i < competitivenessSchRankingLists.size(); i++) {
//			CompetitivenessSchRankingList testBean = competitivenessSchRankingLists.get(i);
//			CompetitivenessSchRankingList dataBean = competitivenessSchRankListResult.getSchools().get(i);
//			Assert.assertTrue(testBean.getRank_index() == dataBean.getRank_index());
//			Assert.assertTrue(testBean.getSch_id().equals(dataBean.getSch_id()));
//			Assert.assertTrue(testBean.getSch_name().equals(dataBean.getSch_name()));
//			Assert.assertTrue(testBean.getSch_type().equals(dataBean.getSch_type()));
//			Assert.assertTrue(testBean.getDegrees().equals(dataBean.getDegrees()));
//			Assert.assertTrue(testBean.getLocation().equals(dataBean.getLocation()));
//			System.out.println("通过本科竞争力第" + (i + 1) + "个.");
//		}
//		System.out.println("通过本科竞争力测试");
//		
//		rankType = "xinchou";
//		provinceFilter = "";
//		schNamePattern = "";
//		count = 800;
//		pageNo = 1;
//		url = HOST + "school/rank?province_filter=" + URLEncoder.encode(provinceFilter, "utf-8") + "&rank_type=" + rankType + "&count=" + count + "&page_no=" + pageNo;
//		startTime = System.currentTimeMillis();
//		webTarget = client.target(url);
//		request = webTarget.request();
//		request.header("Content-type", MediaType.APPLICATION_JSON);
//		response = request.get();
//		System.out.println(response.getStatus());
//		System.out.println(response.toString());
//		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
//		SalarySchRankingListResult salarySchRankListResult = new ObjectMapper().readValue(response.readEntity(String.class), SalarySchRankingListResult.class);
//		totalPage = 1;
//		totalSize = 638;
//		List<SalarySchRankingList> salarySchRankingLists = new ArrayList<SalarySchRankingList>();
//		SalarySchRankingList sBean = new SalarySchRankingList();
//		sBean.setRank_index(1);
//		sBean.setSch_id("52ac2e99747aec013fcf4e6f");
//		sBean.setSch_name("清华大学");
//		sBean.setSch_type("工科院校");
//		sBean.setLocation("北京市");
//		sBean.setDegrees("本科");
//		sBean.setSalary_factor_rank_index(1);
//		sBean.setSalary_factor_rank_str("A++");
//		salarySchRankingLists.add(sBean);
//		sBean = new SalarySchRankingList();
//		sBean.setRank_index(2);
//		sBean.setSch_id("52ac2e9b747aec013fcf5417");
//		sBean.setSch_name("复旦大学");
//		sBean.setSch_type("综合院校");
//		sBean.setLocation("上海市");
//		sBean.setDegrees("本科/专科");
//		sBean.setSalary_factor_rank_index(2);
//		sBean.setSalary_factor_rank_str("A++");
//		salarySchRankingLists.add(sBean);
//		sBean = new SalarySchRankingList();
//		sBean.setRank_index(3);
//		sBean.setSch_id("52ac2e9a747aec013fcf527a");
//		sBean.setSch_name("上海财经大学");
//		sBean.setSch_type("财经院校");
//		sBean.setLocation("上海市");
//		sBean.setDegrees("本科");
//		sBean.setSalary_factor_rank_index(3);
//		sBean.setSalary_factor_rank_str("A++");
//		salarySchRankingLists.add(sBean);
//		
//		Assert.assertTrue(totalPage == salarySchRankListResult.getTotal_page());
//		Assert.assertTrue(totalSize == salarySchRankListResult.getSchools().size());
//		for(int i = 0; i < salarySchRankingLists.size(); i++) {
//			SalarySchRankingList testBean = salarySchRankingLists.get(i);
//			SalarySchRankingList dataBean = salarySchRankListResult.getSchools().get(i);
//			Assert.assertTrue(testBean.getRank_index() == dataBean.getRank_index());
//			Assert.assertTrue(testBean.getSch_id().equals(dataBean.getSch_id()));
//			Assert.assertTrue(testBean.getSch_name().equals(dataBean.getSch_name()));
//			Assert.assertTrue(testBean.getSch_type().equals(dataBean.getSch_type()));
//			Assert.assertTrue(testBean.getDegrees().equals(dataBean.getDegrees()));
//			Assert.assertTrue(testBean.getLocation().equals(dataBean.getLocation()));
//			Assert.assertTrue(testBean.getSalary_factor_rank_index() == dataBean.getSalary_factor_rank_index());
//			Assert.assertTrue(testBean.getSalary_factor_rank_str().equals(dataBean.getSalary_factor_rank_str()));
//			System.out.println("通过本科薪酬第" + (i + 1) + "个.");
//		}
//		System.out.println("通过本科薪酬测试");
//		
//		rankType = "xinchou";
//		provinceFilter = "黑龙江";
//		schNamePattern = "";
//		count = 800;
//		pageNo = 1;
//		url = HOST + "school/rank?province_filter=" + URLEncoder.encode(provinceFilter, "utf-8") + "&rank_type=" + rankType + "&count=" + count + "&page_no=" + pageNo;
//		startTime = System.currentTimeMillis();
//		webTarget = client.target(url);
//		request = webTarget.request();
//		request.header("Content-type", MediaType.APPLICATION_JSON);
//		response = request.get();
//		System.out.println(response.getStatus());
//		System.out.println(response.toString());
//		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
//		salarySchRankListResult = new ObjectMapper().readValue(response.readEntity(String.class), SalarySchRankingListResult.class);
//		totalPage = 1;
//		totalSize = 22;
//		salarySchRankingLists = new ArrayList<SalarySchRankingList>();
//		sBean = new SalarySchRankingList();
//		sBean.setRank_index(1);
//		sBean.setSch_id("52ac2e9a747aec013fcf51e2");
//		sBean.setSch_name("哈尔滨工业大学");
//		sBean.setSch_type("工科院校");
//		sBean.setLocation("黑龙江");
//		sBean.setDegrees("本科");
//		sBean.setSalary_factor_rank_str("A+");
//		salarySchRankingLists.add(sBean);
//		sBean = new SalarySchRankingList();
//		sBean.setRank_index(2);
//		sBean.setSch_id("52ac2e9a747aec013fcf50ba");
//		sBean.setSch_name("哈尔滨工程大学");
//		sBean.setSch_type("工科院校");
//		sBean.setLocation("黑龙江");
//		sBean.setDegrees("本科");
//		sBean.setSalary_factor_rank_str("A");
//		salarySchRankingLists.add(sBean);
//		sBean = new SalarySchRankingList();
//		sBean.setRank_index(3);
//		sBean.setSch_id("52ac2e99747aec013fcf4e8f");
//		sBean.setSch_name("哈尔滨理工大学");
//		sBean.setSch_type("工科院校");
//		sBean.setLocation("黑龙江");
//		sBean.setDegrees("本科/专科");
//		sBean.setSalary_factor_rank_str("B+");
//		salarySchRankingLists.add(sBean);
//		
//		Assert.assertTrue(totalPage == salarySchRankListResult.getTotal_page());
//		Assert.assertTrue(totalSize == salarySchRankListResult.getSchools().size());
//		for(int i = 0; i < salarySchRankingLists.size(); i++) {
//			SalarySchRankingList testBean = salarySchRankingLists.get(i);
//			SalarySchRankingList dataBean = salarySchRankListResult.getSchools().get(i);
//			Assert.assertTrue(testBean.getRank_index() == dataBean.getRank_index());
//			Assert.assertTrue(testBean.getSch_id().equals(dataBean.getSch_id()));
//			Assert.assertTrue(testBean.getSch_name().equals(dataBean.getSch_name()));
//			Assert.assertTrue(testBean.getSch_type().equals(dataBean.getSch_type()));
//			Assert.assertTrue(testBean.getDegrees().equals(dataBean.getDegrees()));
//			Assert.assertTrue(testBean.getLocation().equals(dataBean.getLocation()));
//			Assert.assertTrue(testBean.getSalary_factor_rank_str().equals(dataBean.getSalary_factor_rank_str()));
//			System.out.println("通过本科薪酬第" + (i + 1) + "个.");
//		}
//		System.out.println("通过本科薪酬测试");
		
//		rankType = "meizhi";
//		provinceFilter = "";
//		schNamePattern = "";
//		count = 800;
//		pageNo = 1;
//		url = HOST + "school/rank?province_filter=" + URLEncoder.encode(provinceFilter, "utf-8") + "&rank_type=" + rankType + "&count=" + count + "&page_no=" + pageNo;
//		startTime = System.currentTimeMillis();
//		webTarget = client.target(url);
//		request = webTarget.request();
//		request.header("Content-type", MediaType.APPLICATION_JSON);
//		response = request.get();
//		System.out.println(response.getStatus());
//		System.out.println(response.toString());
//		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
//		GenderSchRankingListResult genderSchRankListResult = new ObjectMapper().readValue(response.readEntity(String.class), GenderSchRankingListResult.class);
//		totalPage = 1;
//		totalSize = 638;
//		List<GenderSchRankingList> generSchRankingLists = new ArrayList<GenderSchRankingList>();
//		GenderSchRankingList gBean = new GenderSchRankingList();
//		gBean.setRank_index(1);
//		gBean.setSch_id("52ac2e99747aec013fcf4e6f");
//		gBean.setSch_name("清华大学");
//		gBean.setSch_type("工科院校");
//		gBean.setLocation("北京市");
//		gBean.setDegrees("本科");
//		gBean.setSalary_factor_rank_index(1);
//		gBean.setSalary_factor_rank_str("A++");
//		generSchRankingLists.add(gBean);
//		
//		Assert.assertTrue(totalPage == salarySchRankListResult.getTotal_page());
//		Assert.assertTrue(totalSize == salarySchRankListResult.getSchools().size());
//		for(int i = 0; i < salarySchRankingLists.size(); i++) {
//			SalarySchRankingList testBean = salarySchRankingLists.get(i);
//			SalarySchRankingList dataBean = salarySchRankListResult.getSchools().get(i);
//			Assert.assertTrue(testBean.getRank_index() == dataBean.getRank_index());
//			Assert.assertTrue(testBean.getSch_id().equals(dataBean.getSch_id()));
//			Assert.assertTrue(testBean.getSch_name().equals(dataBean.getSch_name()));
//			Assert.assertTrue(testBean.getSch_type().equals(dataBean.getSch_type()));
//			Assert.assertTrue(testBean.getDegrees().equals(dataBean.getDegrees()));
//			Assert.assertTrue(testBean.getLocation().equals(dataBean.getLocation()));
//			Assert.assertTrue(testBean.getSalary_factor_rank_index() == dataBean.getSalary_factor_rank_index());
//			Assert.assertTrue(testBean.getSalary_factor_rank_str().equals(dataBean.getSalary_factor_rank_str()));
//			System.out.println("通过本科薪酬第" + (i + 1) + "个.");
//		}
//		System.out.println("通过本科薪酬测试");
	}
	
	@Test
	public void testContest() throws Exception{
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);

		Client client = ClientBuilder.newClient(clientConfig);
		String provinceFilter = "";
		int diplomaId = 7;
		String schoolType = "";
		String majorSecondCategoryFilter = "";
		String majorIdFilter = "";
		String schNamePattern = "";
		String sortBy = "zonghe";
		int count = 100;
		int pageNo = 1;
		String url = HOST + "school?sort_by=" + sortBy + "&province_filter=" + URLEncoder.encode(provinceFilter, "utf-8") +"&diploma_id=" + diplomaId + "&school_type=" + URLEncoder.encode(schoolType, "utf-8") + "&major_second_category_filter=" + URLEncoder.encode(majorSecondCategoryFilter, "utf-8") + "&major_filter=" + majorIdFilter + "&sch_name_pattern=" + schNamePattern + "&count=" + count + "&page_no=" + pageNo;
		long startTime = System.currentTimeMillis();
		WebTarget webTarget = client.target(url);
		Builder request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		Response response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		SchFilterListResult schFileterListResult = new ObjectMapper().readValue(response.readEntity(String.class), SchFilterListResult.class);
		System.out.println(schFileterListResult);
		
//		ClientConfig clientConfig = new ClientConfig();
//		clientConfig.register(JacksonFeature.class);
//
//		Client client = ClientBuilder.newClient(clientConfig);
//		String schId = "52ac2e9a747aec013fcf52f1";
//		int diplomaId = 5;
//		String url = HOST + "school/" + schId + "/luqu_scores/in_major_params?diploma_id=" + diplomaId;
//		long startTime = System.currentTimeMillis();
//		WebTarget webTarget = client.target(url);
//		Builder request = webTarget.request();
//		request.header("Content-type", MediaType.APPLICATION_JSON);
//		Response response = request.get();
//		System.out.println(response.getStatus());
//		System.out.println(response.toString());
//		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
//		SchMajorScoreParamsResult schMajorScoreParamsResult = new ObjectMapper().readValue(response.readEntity(String.class), SchMajorScoreParamsResult.class);
//		System.out.println(schMajorScoreParamsResult.getProvince_ids().size());
		
//		ClientConfig clientConfig = new ClientConfig();
//		clientConfig.register(JacksonFeature.class);
//
//		Client client = ClientBuilder.newClient(clientConfig);
//		String provinceFilter = "";
//		int diplomaId = 7;
//		String schoolType = "";
//		String majorSecondCategoryFilter = "";
//		String majorIdFilter = "";
//		String schNamePattern = "";
//		int count = 20;
//		int pageNo = 1;
//		String url = HOST + "school?province_filter=" + URLEncoder.encode(provinceFilter, "utf-8") +"&diploma_id=" + diplomaId + "&school_type=" + URLEncoder.encode(schoolType, "utf-8") + "&major_second_category_filter=" + URLEncoder.encode(majorSecondCategoryFilter, "utf-8") + "&major_filter=" + majorIdFilter + "&sch_name_pattern=" + schNamePattern + "&count=" + count + "&page_no=" + pageNo;
//		long startTime = System.currentTimeMillis();
//		WebTarget webTarget = client.target(url);
//		Builder request = webTarget.request();
//		request.header("Content-type", MediaType.APPLICATION_JSON);
//		Response response = request.get();
//		System.out.println(response.getStatus());
//		System.out.println(response.toString());
//		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
//		SchFilterListResult schFileterListResult = new ObjectMapper().readValue(response.readEntity(String.class), SchFilterListResult.class);
//	
		
	}
}
