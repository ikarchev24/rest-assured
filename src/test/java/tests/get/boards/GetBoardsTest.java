package tests.get.boards;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import static consts.Endpoints.GET_BOARDS;
import static consts.Endpoints.GET_BOARD;
import static consts.UrlParamValues.*;

public class GetBoardsTest extends BaseTest {

    @Test
    public void checkGetBoards() {
        requestWithAuth()
                .queryParams(QUERY_PARAMS_FIELDS)
                .pathParams(PATH_PARAM_MEMBER)
                .get(GET_BOARDS)
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_boards.json"));
    }

    @Test
    public void checkGetBoard() {
        requestWithAuth()
                .queryParams(QUERY_PARAMS_FIELDS)
                .pathParams(PATH_PARAM_ID, EXISTING_BOARD_ID)
                .get(GET_BOARD)
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_board.json"));
        ;
    }
}
