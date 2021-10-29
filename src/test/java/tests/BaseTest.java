package tests;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import static consts.UrlParamValues.QUERY_PARAMS_AUTH;

public class BaseTest {

    @BeforeAll
    public static void setBaseUrl() {
        RestAssured.baseURI = "https://api.trello.com";
    }

    protected RequestSpecification requestWithAuth() {
        return requestWithoutAuth().queryParams(QUERY_PARAMS_AUTH);
    }

    protected RequestSpecification requestWithoutAuth() {
        return RestAssured.given();
    }
}
