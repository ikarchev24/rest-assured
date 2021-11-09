package tests.post.boards;

import consts.Endpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import java.time.LocalDateTime;
import java.util.Map;

import static consts.Endpoints.GET_BOARDS;
import static consts.UrlParamValues.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class CreateBoardTest extends BaseTest {

    private String createdBoardId;

    @Test
    public void checkCreateBoard() {
        String boardName = "New Board " + LocalDateTime.now();
        Response response = requestWithAuth().body(Map.of("name", boardName))
                .contentType(ContentType.JSON)
                .post(Endpoints.CREATE_BOARD);
        response.
                then()
                .log().body()
                .statusCode(equalTo(200))
                .body("name", Matchers.equalTo(boardName));
        createdBoardId = response.body().jsonPath().get("id");

        requestWithAuth()
                .queryParams(QUERY_PARAMS_FIELDS)
                .pathParams(PATH_PARAM_MEMBER)
                .get(GET_BOARDS)
                .then()
                .statusCode(200)
                .log().body()
                .body("name", hasItem(boardName));
    }

    @AfterEach
    public void deleteCreatedBoard() {
        requestWithAuth()
                .pathParam(PATH_PARAM_ID, createdBoardId)
                .delete(Endpoints.DELETE_BOARD)
                .then()
                .log().body()
                .statusCode(equalTo(200));
    }
}
