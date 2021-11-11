package tests.get.cards;

import consts.Endpoints;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import static consts.Endpoints.GET_CARDS_FOR_BOARD;
import static consts.Endpoints.GET_CARDS_LIST;
import static consts.UrlParamValues.*;
import static org.hamcrest.Matchers.equalTo;

public class GetCardsTest extends BaseTest {

    @Test
    public void checkGetCard() {
        requestWithAuth()
                .pathParam(PATH_PARAM_ID, EXISTING_CARD_ID)
                .get(Endpoints.GET_CARD)
                .then()
                .log().body()
                .statusCode(200)
                .body("name", equalTo("To learn Rest-Assured"));
    }

    @Test
    public void checkGetCardsForBoard() {
        final int listSize = requestWithAuth()
                .pathParam(PATH_PARAM_ID, EXISTING_BOARD_ID)
                .get(GET_CARDS_FOR_BOARD)
                .then()
                .statusCode(200)
                .log().body()
                .extract()
                .body()
                .jsonPath()
                .getList("$").size();
        Assertions.assertEquals(8, listSize);
    }

    @Test
    public void checkGetCardsList() {
        requestWithAuth()
                .pathParam(PATH_PARAM_ID, "617938f1eaa2fd0e661089de")
                .get(GET_CARDS_LIST)
                .then()
                .log().body()
                .statusCode(200)
                .body("[0].name", equalTo("To learn Rest-Assured"));
    }
}
