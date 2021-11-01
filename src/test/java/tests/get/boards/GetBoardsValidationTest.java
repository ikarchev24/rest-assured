package tests.get.boards;

import arguments.holders.AuthValidationArgumentsHolder;
import arguments.holders.BoardIdValidationArgumentsHolder;
import arguments.providers.AuthValidationArgumentsProvider;
import arguments.providers.BoardIdValidationArgumentsProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import tests.BaseTest;

import static consts.Endpoints.GET_BOARD;
import static consts.UrlParamValues.EXISTING_BOARD_ID;
import static consts.UrlParamValues.PATH_PARAM_ID;
import static org.hamcrest.Matchers.equalTo;

public class GetBoardsValidationTest extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(BoardIdValidationArgumentsProvider.class)
    public void checkGetBoardWithInvalidId(BoardIdValidationArgumentsHolder argumentsHolder) {
        requestWithAuth()
                .queryParams(argumentsHolder.getQueryParams())
                .pathParams(argumentsHolder.getPathParams())
                .log().uri()
                .get(GET_BOARD)
                .then()
                .statusCode(argumentsHolder.getStatusCode())
                .body(equalTo(argumentsHolder.getErrorMessage()));
    }

    @ParameterizedTest
    @ArgumentsSource(AuthValidationArgumentsProvider.class)
    public void checkGetBoardWithInvalidAuth(AuthValidationArgumentsHolder argumentsHolder) {
        requestWithoutAuth()
                .queryParams(argumentsHolder.getAuthParams())
                .pathParam(PATH_PARAM_ID, EXISTING_BOARD_ID)
                .log().uri()
                .get(GET_BOARD)
                .then()
                .statusCode(equalTo(401))
                .body(equalTo(argumentsHolder.getErrorMessage()));
    }
}
