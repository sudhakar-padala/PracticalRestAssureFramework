package com.nic.sarathi.api.testcases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import java.util.Map;
import org.testng.annotations.Test;
import com.nic.sarathi.api.pojo.Product;
import com.nic.sarathi.api.routes.Routes;
import io.restassured.http.ContentType;

public class ProductDataDrivenTest extends BaseClass {

	
	//@Test(dataProvider = "csvDataProvider", dataProviderClass = com.nic.sarathi.api.utils.DataProviders.class)
	//@Test(dataProvider = "excelDataProvider", dataProviderClass = com.nic.sarathi.api.utils.DataProviders.class)
	@Test(dataProvider = "jsonDataProvider", dataProviderClass = com.nic.sarathi.api.utils.DataProviders.class)
	public void testAddNewProduct(Map<String, String> data) {

		String title = data.get("title");
		double price = Double.parseDouble(data.get("price"));
		String description = data.get("description");
		String image = data.get("image");
		String category = data.get("category");

		Product newProduct = new Product(title, price, description, image, category);

		int productid = given().contentType(ContentType.JSON).body(newProduct)

				.when().post(Routes.CREATE_PRODUCT).then().log().body().statusCode(201).body("id", notNullValue())
				.body("title", equalTo(newProduct.getTitle())).extract().jsonPath().getInt("id");

		System.out.println("Product ID ====>" + productid);
		
		//Delete Product
		
		given().pathParam("id",productid)
		.when()
		.delete(Routes.DELETE_PRODUCT)
		.then().statusCode(200).
		log().body();
		

		System.out.println("Deleted Product ID ====>" + productid);

	}
}
