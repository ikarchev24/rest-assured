package tests.delete.boards;

import arguments.holders.AuthValidationArgumentsHolder;
import arguments.holders.BoardIdValidationArgumentsHolder;
import arguments.providers.AuthCreateDeleteBoardValidationArgumentsProvider;
import arguments.providers.BoardIdValidationArgumentsProvider;
import consts.Endpoints;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import tests.BaseTest;

import static consts.UrlParamValues.*;
import static org.hamcrest.Matchers.equalTo;

public class DeleteBoardValidationTest extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(BoardIdValidationArgumentsProvider.class)
    public void checkDeleteBoardWithInvalidId(BoardIdValidationArgumentsHolder argumentsHolder) {
        requestWithAuth()
                .pathParams(argumentsHolder.getPathParams())
                .delete(Endpoints.DELETE_BOARD)
                .then()
                .statusCode(argumentsHolder.getStatusCode())
                .body(equalTo(argumentsHolder.getErrorMessage()));
    }

    @ParameterizedTest
    @ArgumentsSource(AuthCreateDeleteBoardValidationArgumentsProvider.class)
    public void checkDeleteBoardWithInvalidAuth(AuthValidationArgumentsHolder argumentsHolder) {
        requestWithoutAuth()
                .queryParams(argumentsHolder.getAuthParams())
                .pathParam(PATH_PARAM_ID, EXISTING_BOARD_ID)
                .delete(Endpoints.DELETE_BOARD)
                .then()
                .statusCode(argumentsHolder.getStatusCode())
                .body(equalTo(argumentsHolder.getErrorMessage()));
    }

    @Test
    public void checkDeleteBoardWithAnotherUserCredentials() {
        requestWithoutAuth()
                .queryParams(ANOTHER_USER_AUTH_QUERY_PARAMS)
                .pathParam(PATH_PARAM_ID, EXISTING_BOARD_ID)
                .delete(Endpoints.DELETE_BOARD)
                .then()
                .statusCode(401)
                .body(equalTo("invalid token"));
    }
}
