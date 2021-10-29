package tests.get;

import arguments.holders.AuthValidationArgumentsHolder;
import arguments.holders.BoardIdValidationArgumentsHolder;
import arguments.providers.AuthValidationArgumentsProvider;
import arguments.providers.BoardIdValidationArgumentsProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import tests.BaseTest;

import static consts.GetBoardsEndpoint.GET_BOARD_ENDPOINT;
import static org.hamcrest.Matchers.equalTo;

public class GetBoardsValidationTest extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(BoardIdValidationArgumentsProvider.class)
    public void getBoardWithInvalidId(BoardIdValidationArgumentsHolder argumentsHolder) {
        requestWithAuth()
                .queryParams(argumentsHolder.getQueryParams())
                .pathParams(argumentsHolder.getPathParams())
                .log().uri()
                .get(GET_BOARD_ENDPOINT)
                .then()
                .statusCode(argumentsHolder.getStatusCode())
                .body(equalTo(argumentsHolder.getErrorMessage()));
    }

    @ParameterizedTest
    @ArgumentsSource(AuthValidationArgumentsProvider.class)
    public void getBoardWithInvalidAuth(AuthValidationArgumentsHolder argumentsHolder) {
        requestWithoutAuth()
                .queryParams(argumentsHolder.getAuthParams())
                .pathParam("id", "617938f1eaa2fd0e661089dc")
                .log().uri()
                .get(GET_BOARD_ENDPOINT)
                .then()
                .statusCode(equalTo(401))
                .body(equalTo("unauthorized permission requested"));
    }
}
