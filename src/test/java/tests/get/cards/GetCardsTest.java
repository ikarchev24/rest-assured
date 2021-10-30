package tests.get.cards;

import consts.Endpoints;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import tests.BaseTest;

import static consts.Endpoints.GET_CARDS_FOR_BOARD_ENDPOINT;
import static consts.Endpoints.GET_CARDS_LIST_ENDPOINT;
import static consts.UrlParamValues.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class GetCardsTest extends BaseTest {

    @Test
    public void checkGetCard() {
        requestWithAuth()
                .pathParam(PATH_PARAM_ID, EXISTING_CARD_ID)
                .get(Endpoints.GET_CARD_ENDPOINT)
                .then()
                .statusCode(equalTo(200))
                .body("name", equalTo("To learn Rest-Assured"));
    }

    @Test
    public void checkGetCardsForBoard() {
        final int listSize = requestWithAuth()
                .pathParam(PATH_PARAM_ID, EXISTING_BOARD_ID)
                .get(GET_CARDS_FOR_BOARD_ENDPOINT)
                .then()
                .statusCode(equalTo(200))
                .extract()
                .body()
                .jsonPath()
                .getList("$").size();
        Assertions.assertEquals(3, listSize);
    }

    @Test
    public void checkGetCardsList() {
        requestWithAuth()
                .pathParam(PATH_PARAM_ID, "617938f1eaa2fd0e661089de")
                .get(GET_CARDS_LIST_ENDPOINT)
                .then()
                .statusCode(equalTo(200))
                .body("[0].name", equalTo("To learn Rest-Assured"));
    }
}
