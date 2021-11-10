package tests.put.cards;

import consts.Endpoints;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import java.time.LocalDateTime;
import java.util.Map;

import static consts.UrlParamValues.*;
import static org.hamcrest.Matchers.equalTo;

public class UpdateCardTest extends BaseTest {

    @Test
    public void checkUpdateCard() {
        String updatedCardName = "Updated card " + LocalDateTime.now();

        requestWithAuth()
                .pathParam(PATH_PARAM_ID, CARD_ID_TO_UPDATE)
                .body(Map.of("name", updatedCardName))
                .contentType(ContentType.JSON)
                .put(Endpoints.UPDATE_CARD)
                .then()
                .statusCode(200)
                .body("name", equalTo(updatedCardName));

        requestWithAuth()
                .pathParam(PATH_PARAM_ID, CARD_ID_TO_UPDATE)
                .get(Endpoints.GET_CARD)
                .then()
                .statusCode(200)
                .body("name", equalTo(updatedCardName));
    }
}
