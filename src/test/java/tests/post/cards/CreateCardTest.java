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
                .queryParams(QUERY_PARAMS_CREATE_CARD)
                .contentType(ContentType.JSON)
                .post(Endpoints.CREATE_CARD);
        createdCardId = response.then().extract().body().jsonPath().get(PATH_PARAM_ID);

        response.
                then()
                .statusCode(equalTo(200))
                .body(PATH_PARAM_NAME, equalTo(QUERY_PARAMS_CREATE_CARD.get(PATH_PARAM_NAME)));
        requestWithAuth()
                .pathParam(PATH_PARAM_ID, EXISTING_CARD_ID_LIST)
                .get(Endpoints.GET_CARDS_LIST)
                .then()
                .log().body()
                .body(PATH_PARAM_NAME, hasItem(QUERY_PARAMS_CREATE_CARD.get(PATH_PARAM_NAME)));
    }

    @AfterEach
    public void deleteCreatedCard() {
        requestWithAuth()
                .pathParam(PATH_PARAM_ID, createdCardId)
                .delete(Endpoints.DELETE_CARD)
                .then()
                .log().body()
                .statusCode(200);
    }
}
