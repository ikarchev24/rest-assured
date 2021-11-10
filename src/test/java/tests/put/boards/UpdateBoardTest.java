package tests.put.boards;

import consts.Endpoints;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import java.time.LocalDateTime;
import java.util.Map;

import static consts.UrlParamValues.BOARD_ID_TO_UPDATE;
import static consts.UrlParamValues.PATH_PARAM_ID;
import static org.hamcrest.Matchers.equalTo;

public class UpdateBoardTest extends BaseTest {

    @Test
    public void checkUpdateBoard() {
        String updatedBoardName = "Updated name " + LocalDateTime.now();
        requestWithAuth()
                .pathParam(PATH_PARAM_ID, BOARD_ID_TO_UPDATE)
                .body(Map.of("name", updatedBoardName))
                .contentType(ContentType.JSON)
                .put(Endpoints.UPDATE_BOARD)
                .then()
                .log().body()
                .statusCode(200)
                .body("name", equalTo(updatedBoardName));

        requestWithAuth()
                .pathParam(PATH_PARAM_ID, BOARD_ID_TO_UPDATE)
                .get(Endpoints.GET_BOARD)
                .then()
                .body("name", equalTo(updatedBoardName));
    }
}
