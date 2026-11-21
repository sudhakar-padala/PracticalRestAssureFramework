package com.nic.sarathi.api.testcases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.testng.Assert.assertEquals;

import java.util.List;

import org.hamcrest.core.IsEqual;
import org.testng.annotations.Test;

import com.nic.sarathi.api.payloads.Payload;
import com.nic.sarathi.api.pojo.User;
import com.nic.sarathi.api.routes.Routes;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserTest extends BaseClass{
	
	@Test
	public void testGetAllUsers() {
		given()
		.when()
		.get(Routes.GET_ALL_USERS)
		.then()
		.statusCode(200)
		.body("size()", greaterThan(0))
		.log().body();
		
	}
	
	@Test
	public void testGetUserById() {
		
		int userid=configreader.getIntProperty("userId");
		given().pathParam("id", userid)
		.when().get(Routes.GET_USER_ID)
		.then().statusCode(200).log().body();
	}
	
	@Test
	public void testGetUserByLimit() {
		
		given().pathParam("limit", 3)
		.when().get(Routes.GET_USERS_WITH_LIMIT)
		.then().statusCode(200).log().body();
	}
	
	@Test
	public void testGetUserByDsc() {
		
		
		Response response=given().pathParam("order", "desc")
		.when().get(Routes.GET_USERS_SORTED)
		.then().statusCode(200).extract().response();
		
		List<Integer> userIds=response.jsonPath().getList("id", Integer.class);
		assertEquals(isSortedDescending(userIds), true);
		
	}
	
	
	@Test
	public void testGetUserByAsc() {
		
		
		Response response=given().pathParam("order", "asc")
		.when().get(Routes.GET_USERS_SORTED)
		.then().statusCode(200).extract().response();
		
		List<Integer> userIds=response.jsonPath().getList("id", Integer.class);
		assertEquals(isSortedAscending(userIds), true);
		
	}
	
	@Test
	public void testCreateUser() {
		
		User newuser=Payload.userpayload();
		
		int userId=given().contentType(ContentType.JSON).body(newuser)
		.when().post(Routes.CREATE_USER)
		.then().statusCode(201)
		.log().body()
		.extract().jsonPath().getInt("id");
		
		
		System.out.println("Created User ID ====>"+userId);
	}
	@Test
	public void testUpdateUser()
	{
		int userId=configreader.getIntProperty("userId");
		
		User updateUser=Payload.userpayload();
				
		given()
			.contentType(ContentType.JSON)
			.pathParam("id", userId)
			.body(updateUser)
		.when()
			.put(Routes.UPDATE_USER)
		.then() 
			.log().body()
			.statusCode(200);
				
	}
	
	//8) delete user
	
	@Test
	void testDeleteUser()
	{

		int userId=configreader.getIntProperty("userId");
		
		given()
			.pathParam("id", userId)
		.when()
			.delete(Routes.DELETE_USER)
		.then()
			.statusCode(200).log().body();
		}
	
	
	
	
}


