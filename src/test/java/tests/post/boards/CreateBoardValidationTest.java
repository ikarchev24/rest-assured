package tests.post.boards;

import arguments.holders.AuthValidationArgumentsHolder;
import arguments.providers.AuthCreateBoardValidationArgumentsProvider;
import arguments.providers.BoardNameValidationArgumentsProvider;
import consts.Endpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import tests.BaseTest;

import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class CreateBoardValidationTest extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(BoardNameValidationArgumentsProvider.class)
    public void checkCreateBoardWithInvalidName(Map bodyParams) {
        Response response = requestWithAuth()
                .body(bodyParams)
                .contentType(ContentType.JSON)
                .post(Endpoints.CREATE_BOARD);
        response.then().statusCode(400);
        Assertions.assertEquals("invalid value for name", response.body().asString());
    }

    @ParameterizedTest
    @ArgumentsSource(AuthCreateBoardValidationArgumentsProvider.class)
    public void checkCreateBoardWithInvalidAuth(AuthValidationArgumentsHolder argumentsHolder) {
        requestWithoutAuth()
                .queryParams(argumentsHolder.getAuthParams())
                .body(Map.of("name", "new board"))
                .contentType(ContentType.JSON)
                .post(Endpoints.CREATE_BOARD)
                .then()
                .statusCode(equalTo(argumentsHolder.getStatusCode()))
                .body(equalTo(argumentsHolder.getErrorMessage()));
    }
}
