package tests.get.boards;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import static consts.Endpoints.GET_BOARDS_ENDPOINT;
import static consts.Endpoints.GET_BOARD_ENDPOINT;
import static consts.UrlParamValues.PATH_PARAM_MEMBER;
import static consts.UrlParamValues.QUERY_PARAMS_FIELDS;

public class GetBoardsTest extends BaseTest {

    @Test
    public void checkGetBoards() {
        requestWithAuth()
                .queryParams(QUERY_PARAMS_FIELDS)
                .pathParams(PATH_PARAM_MEMBER)
                .get(GET_BOARDS_ENDPOINT)
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_boards.json"));
    }

    @Test
    public void checkGetBoard() {
        requestWithAuth()
                .queryParams(QUERY_PARAMS_FIELDS)
                .pathParams("id", "617938f1eaa2fd0e661089dc")
                .get(GET_BOARD_ENDPOINT)
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_board.json"));
        ;
    }
}
