package com.growsimplyee;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class OrderCreate {

	@Test
	public void getToken() throws IOException {

		FileInputStream fileInputStream = new FileInputStream(
				new File("C:\\Users\\shamb\\Downloads\\demoAPI\\demoAPI\\src\\main\\java\\jsonFileInput\\token.json"));

		RestAssured.baseURI = "http://localhost:8181";

		given()

				.header("Content-Type", "application/json")

				.and().body(IOUtils.toString(fileInputStream, "UTF-8")).when().post("/v1")

				.then()

				.statusCode(200).body("RestResponse.result.status", equalTo("SUCCESS")).and()
				.body("RestResponse.result.token", equalTo("jlksalkjfad245421sa-asdfa35"))

				.log().all();

	}

	// @Test
	public void getMethod() throws IOException {

		String bearerToken = "jlksalkjfad245421sa-asdfa35";
		// FileInputStream fileInputStream = new FileInputStream(new File
		// (".\\jsonFileInput\\sample.json"));
		FileInputStream fileInputStream = new FileInputStream(
				new File("C:\\Users\\shamb\\Downloads\\demoAPI\\demoAPI\\src\\main\\java\\jsonFileInput\\sample.json"));

		RestAssured.baseURI = "http://localhost:8181/v1/orin/api";

		given().auth().preemptive().basic("gaurav", "password").header("Authorization", "Bearer " + bearerToken)
				.header("Content-Type", "application/json")

				.and().body(IOUtils.toString(fileInputStream, "UTF-8")).when().post("/orders").then().statusCode(200)

				.log().all();

	}

}
