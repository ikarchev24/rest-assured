package tests.post;

import consts.Endpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import java.time.LocalDateTime;
import java.util.Map;

import static consts.Endpoints.GET_BOARDS;
import static consts.UrlParamValues.PATH_PARAM_MEMBER;
import static consts.UrlParamValues.QUERY_PARAMS_FIELDS;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class CreateBoardTest extends BaseTest {

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

        requestWithAuth()
                .queryParams(QUERY_PARAMS_FIELDS)
                .pathParams(PATH_PARAM_MEMBER)
                .get(GET_BOARDS)
                .then()
                .statusCode(200)
                .log().body()
                .body("name", hasItem(boardName));
    }
}
