package com.nic.sarathi.api.testcases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.Test;

import com.nic.sarathi.api.payloads.Payload;
import com.nic.sarathi.api.pojo.Product;
import com.nic.sarathi.api.routes.Routes;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class ProductTest extends BaseClass{
	
	@Test
	public void testGetAllProducts() {
		given()
		.when()
		.get(Routes.GET_ALL_PRODUCTS)
		.then()
		.statusCode(200)
		.body("size()", greaterThan(0))
		.log().body();
		
	}
	
	@Test
	public void testGetSingleProductById() {
		
		int productid=configreader.getIntProperty("productId");
		given()
		.pathParam("id", productid)
		.when()
		.get(Routes.GET_PRODUCT_BY_ID)
		.then()
		.statusCode(200).log().body();
		
	}

	@Test
	public void testGetLimitedProducts() {
		
		
		given()
		.pathParam("limit", 3)
		.when()
		.get(Routes.GET_PRODUCTS_WITH_LIMIT)
		.then()
		.statusCode(200).log().body().body("size()", equalTo(3));
		
	}
	
	@Test
	
	public void testGetSortedProductsDesc() {
		
		
		Response response=given()
				.pathParam("order", "desc")
		.when()
		.get(Routes.GET_PRODUCTS_SORTED)
		.then().statusCode(200).extract().response();
		
		List<Integer> productIds=response.jsonPath().getList("id", Integer.class);
		
		assertEquals(isSortedDescending(productIds), true);
		
	}
	
	
	@Test
	
	public void testGetSortedProductsAsc() {
		
		
		Response response=given()
				.pathParam("order", "asc")
		.when()
		.get(Routes.GET_PRODUCTS_SORTED)
		.then().statusCode(200).extract().response();
		
		List<Integer> productIds=response.jsonPath().getList("id", Integer.class);
		
		assertEquals(isSortedAscending(productIds), true);
		
	}

	
	@Test
	public void testGetAllCategories() {
		given()
		.when()
		.get(Routes.GET_ALL_CATEGORIES)
		.then()
		.statusCode(200)
		.body("size()", greaterThan(0))
		.log().body();
		
	}
	
	@Test
	public void testGetProductByCategory() {
		given()
		.pathParam("category", "electronics")
		.when()
		.get(Routes.GET_PRODUCTS_BY_CATEGORY)
		.then()
		.statusCode(200)
		.body("size()", greaterThan(0))
		.body("category", everyItem(notNullValue()))
		.body("category", everyItem(equalTo("electronics")))
		.log().body();
		
	}
	
	@Test
	public void testCreateNewProduct() {
		
		Product newProduct=Payload.productPayload();
		
		int productid=given().contentType(ContentType.JSON).body(newProduct)
		
		.when()
		.post(Routes.CREATE_PRODUCT)
		.then()
		.log().body().
		statusCode(201)
		.body("id", notNullValue())
		.body("title", equalTo(newProduct.getTitle()))
		.extract().jsonPath().getInt("id");
		
		System.out.println(productid);
		
		
	}
	
	@Test
	public void testUpdateProduct() {
		
	int productid= configreader.getIntProperty("productId");
	
	Product updateproductpayload=Payload.productPayload();
	
	given().contentType(ContentType.JSON).body(updateproductpayload).pathParam("id", productid)
	.when()
	.put(Routes.UPDATE_PRODUCT)
	
	.then()
	.log().body().statusCode(200).body("title", equalTo(updateproductpayload.getTitle()));
	
	
	}
	
	@Test
	public void testDeleteProduct() {
		
		int productid=configreader.getIntProperty("productId");
		given().pathParam("id",productid)
		.when()
		.delete(Routes.DELETE_PRODUCT)
		.then().statusCode(200).log().body();
	}
	
	
}
