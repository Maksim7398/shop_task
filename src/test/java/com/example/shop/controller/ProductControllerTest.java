package com.example.shop.controller;

import com.example.shop.controller.request.CreateProductRequest;
import com.example.shop.model.CreateProductRequestBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("local")
public class ProductControllerTest {
    @LocalServerPort
    private int port;

    @Test
    public void create_whenRequestGet_thenOK(){
        CreateProductRequest createProductRequest = CreateProductRequestBuilder.aCreateProductRequest().build();
        RestAssured.given()
                .baseUri("http://localhost:" + port + "/my-app")
                .contentType(ContentType.JSON)
                .when()
                .body(createProductRequest)
                .post("/product")
                .then()
                .statusCode(200);
    }

    @Test
    public void create_whenRequestGetWithNull_thenBadRequest() {
        CreateProductRequest createProductRequest = CreateProductRequestBuilder.aCreateProductRequest()
                .withTitle(null)
                .build();

        RestAssured.given()
                .contentType(ContentType.JSON)
                .baseUri("http://localhost:" + port + "/my-app")
                .when()
                .body(createProductRequest)
                .post("/product")
                .then()
                .statusCode(400);
    }

}
