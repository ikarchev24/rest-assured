package tests.put.boards;

import arguments.holders.AuthValidationArgumentsHolder;
import arguments.holders.BoardIdValidationArgumentsHolder;
import arguments.providers.AuthCreateBoardValidationArgumentsProvider;
import arguments.providers.BoardIdValidationArgumentsProvider;
import consts.Endpoints;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import tests.BaseTest;

import java.time.LocalDateTime;
import java.util.Map;

import static consts.UrlParamValues.*;
import static org.hamcrest.Matchers.equalTo;

public class UpdateBoardValidationTest extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(BoardIdValidationArgumentsProvider.class)
    public void checkUpdateBoardWithInvalidId(BoardIdValidationArgumentsHolder argumentsHolder) {
        String updatedBoardName = "Updated name " + LocalDateTime.now();
        requestWithAuth()
                .pathParams(argumentsHolder.getPathParams())
                .body(Map.of("name", updatedBoardName))
                .contentType(ContentType.JSON)
                .put(Endpoints.UPDATE_BOARD)
                .then()
                .statusCode(argumentsHolder.getStatusCode())
                .body(equalTo(argumentsHolder.getErrorMessage()));
    }

    @ParameterizedTest
    @ArgumentsSource(AuthCreateBoardValidationArgumentsProvider.class)
    public void checkUpdateBoardWithInvalidAuth(AuthValidationArgumentsHolder argumentsHolder) {
        requestWithoutAuth()
                .queryParams(argumentsHolder.getAuthParams())
                .pathParam(PATH_PARAM_ID, EXISTING_BOARD_ID)
                .body(Map.of("name", "updatedBoardName"))
                .contentType(ContentType.JSON)
                .put(Endpoints.UPDATE_BOARD)
                .then()
                .statusCode(argumentsHolder.getStatusCode())
                .body(equalTo(argumentsHolder.getErrorMessage()));
    }

    @Test
    public void checkUpdateBoardWithAnotherUserCredentials() {
        requestWithoutAuth()
                .queryParams(ANOTHER_USER_AUTH_QUERY_PARAMS)
                .pathParam(PATH_PARAM_ID, EXISTING_BOARD_ID)
                .body(Map.of("name", "updatedBoardName"))
                .contentType(ContentType.JSON)
                .put(Endpoints.UPDATE_BOARD)
                .then()
                .statusCode(401)
                .body(equalTo("invalid token"));
    }
}
