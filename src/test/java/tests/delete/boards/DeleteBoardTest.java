package tests.delete.boards;

import consts.Endpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import java.time.LocalDateTime;
import java.util.Map;

import static consts.Endpoints.GET_BOARDS;
import static consts.UrlParamValues.*;
import static org.hamcrest.Matchers.*;

public class DeleteBoardTest extends BaseTest {

    private String createdBoardId;

    @BeforeEach
    public void createBoard() {
        String boardName = "New Board " + LocalDateTime.now();
        Response response = requestWithAuth()
                .body(Map.of("name", boardName))
                .contentType(ContentType.JSON)
                .post(Endpoints.CREATE_BOARD);

        response.
                then()
                .statusCode(200)
                .body("name", Matchers.equalTo(boardName));
        createdBoardId = response.body().jsonPath().get("id");
    }

    @Test
    public void deleteBoard() {
        requestWithAuth()
                .pathParam(PATH_PARAM_ID, createdBoardId)
                .delete(Endpoints.DELETE_BOARD)
                .then()
                .statusCode(200)
                .body("_value", equalTo(null));

        requestWithAuth()
                .queryParams(QUERY_PARAMS_FIELDS)
                .pathParams(PATH_PARAM_MEMBER)
                .get(GET_BOARDS)
                .then()
                .statusCode(200)
                .body("id", not(hasItem(createdBoardId)));
    }
}
