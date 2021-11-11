package tests.post.cards;

import consts.Endpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import static consts.UrlParamValues.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class CreateCardTest extends BaseTest {

    private String createdCardId;

    @Test
    public void createCardTest() {
        Response response = requestWithAuth()
                .body(QUERY_PARAMS_CREATE_CARD)
                .contentType(ContentType.JSON)
                .post(Endpoints.CREATE_CARD);
        createdCardId = response.then().extract().body().jsonPath().get("id");

        response.
                then()
                .statusCode(200)
                .body("name", equalTo(QUERY_PARAMS_CREATE_CARD.get("name")));

        requestWithAuth()
                .pathParam(PATH_PARAM_ID, EXISTING_CARD_ID_LIST)
                .get(Endpoints.GET_CARDS_LIST)
                .then()
                .body("name", hasItem(QUERY_PARAMS_CREATE_CARD.get("name")));
    }

    @AfterEach
    public void deleteCreatedCard() {
        requestWithAuth()
                .pathParam(PATH_PARAM_ID, createdCardId)
                .delete(Endpoints.DELETE_CARD)
                .then()
                .statusCode(200);
    }
}
