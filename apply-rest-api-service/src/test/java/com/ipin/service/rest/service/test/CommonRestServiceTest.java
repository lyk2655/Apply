package com.ipin.service.rest.service.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.Assert;
import org.junit.Test;

import com.ipin.service.rest.beans.IndustryListResult;
import com.ipin.service.rest.beans.LocationListResult;

public class CommonRestServiceTest {
	
//	public static final String HOST = "http://192.168.0.31:8080/rest-api-service/";
	
//	public static final String HOST = "http://192.168.1.81:8082/rest-api-service/";
	
	public static final String HOST = "http://localhost:8080/";
	
	@Test
	public void testLocation() throws Exception {
		
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);

		Client client = ClientBuilder.newClient(clientConfig);
		String level = "province";
		String url = HOST + "location?level=" + level;
		long startTime = System.currentTimeMillis();
		WebTarget webTarget = client.target(url);
		Builder request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		Response response = null;
		response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		LocationListResult locationListResult = new ObjectMapper().readValue(response.readEntity(String.class), LocationListResult.class);
		System.out.println(locationListResult.toString());
		System.out.println(locationListResult.getItems().size());
		int totalSize = 31;
		Assert.assertTrue(totalSize == locationListResult.getItems().size());
		
		level = "city";
		url = HOST + "location?level=" + level;
		startTime = System.currentTimeMillis();
		webTarget = client.target(url);
		request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		response = null;
		response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		locationListResult = new ObjectMapper().readValue(response.readEntity(String.class), LocationListResult.class);
		System.out.println(locationListResult.toString());
		System.out.println(locationListResult.getItems().size());
	}
	
	@Test
	public void testIndustry() throws Exception {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);

		Client client = ClientBuilder.newClient(clientConfig);
		String url = HOST + "industry";
		long startTime = System.currentTimeMillis();
		WebTarget webTarget = client.target(url);
		Builder request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		Response response = null;
		response = request.get();
		System.out.println(response.getStatus());
		System.out.println(response.toString());
		System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms.");
		IndustryListResult industryListResult = new ObjectMapper().readValue(response.readEntity(String.class), IndustryListResult.class);
		System.out.println(industryListResult.toString());
		System.out.println(industryListResult.getIndustries().size());
	}

}
