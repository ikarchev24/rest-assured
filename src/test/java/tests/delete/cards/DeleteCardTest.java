package tests.delete.cards;

import consts.Endpoints;
import consts.UrlParamValues;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import static consts.UrlParamValues.PATH_PARAM_ID;
import static consts.UrlParamValues.QUERY_PARAMS_CREATE_CARD;
import static org.hamcrest.Matchers.*;

public class DeleteCardTest extends BaseTest {

    private String createdCardId;

    @BeforeEach
    public void createCard() {
       Response response = requestWithAuth()
                .body(QUERY_PARAMS_CREATE_CARD)
                .contentType(ContentType.JSON)
                .post(Endpoints.CREATE_CARD)
                .then()
                .statusCode(200)
                .body("name", equalTo(QUERY_PARAMS_CREATE_CARD.get("name")))
                .extract().response();
        createdCardId = response.then().extract().body().jsonPath().get("id");
    }

    @Test
    public void deleteCard() {
        requestWithAuth()
                .pathParam(PATH_PARAM_ID, createdCardId)
                .delete(Endpoints.DELETE_CARD)
                .then()
                .log().body()
                .statusCode(200);

        requestWithAuth()
                .pathParam(PATH_PARAM_ID, UrlParamValues.EXISTING_CARD_ID_LIST)
                .get(Endpoints.GET_CARDS_LIST)
                .then()
                .statusCode(200)
                .body("id", not(hasItem(createdCardId)));
    }
}
