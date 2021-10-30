package tests.get.cards;

import consts.Endpoints;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import tests.BaseTest;

import static consts.UrlParamValues.PATH_PARAM_ID;
import static org.hamcrest.Matchers.equalTo;

public class GetCardsValidationTest extends BaseTest {

    @Test
    public void checkGetCardWithInvalidId() {

    }

    @Test
    public void checkGetCardsForBoardWithInvalidId() {

    }

    @Test
    public void checkGetCardWithInvalidAuth() {
        requestWithoutAuth()
                .queryParam("AUTH PARAM")
                .pathParam(PATH_PARAM_ID, "617cd35314124046fc513c02")
                .get(Endpoints.GET_CARD_ENDPOINT)
                .then()
                .statusCode(equalTo(401))
                .body(equalTo("unauthorized card permission requested"));
    }
}
