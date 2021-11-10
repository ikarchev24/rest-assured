package tests.put.cards;

import arguments.holders.AuthValidationArgumentsHolder;
import arguments.holders.CardIdValidationArgumentsHolder;
import arguments.providers.AuthCreateCardValidationArgumentsProvider;
import arguments.providers.CardIdValidationArgumentsProvider;
import consts.Endpoints;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import tests.BaseTest;

import java.util.Map;

import static consts.UrlParamValues.*;
import static org.hamcrest.Matchers.equalTo;

public class UpdateCardValidationTest extends BaseTest {
    @ParameterizedTest
    @ArgumentsSource(CardIdValidationArgumentsProvider.class)
    public void checkUpdateBoardWithInvalidId(CardIdValidationArgumentsHolder argumentsHolder) {
        requestWithAuth()
                .pathParams(argumentsHolder.getPathParams())
                .body(Map.of("name", "updatedBoardName"))
                .contentType(ContentType.JSON)
                .put(Endpoints.UPDATE_CARD)
                .then()
                .statusCode(argumentsHolder.getStatusCode())
                .body(equalTo(argumentsHolder.getErrorMessage()));
    }

    @ParameterizedTest
    @ArgumentsSource(AuthCreateCardValidationArgumentsProvider.class)
    public void checkUpdateBoardWithInvalidAuth(AuthValidationArgumentsHolder argumentsHolder) {
        requestWithoutAuth()
                .queryParams(argumentsHolder.getAuthParams())
                .pathParam(PATH_PARAM_ID, EXISTING_CARD_ID)
                .body(Map.of("name", "updatedBoardName"))
                .contentType(ContentType.JSON)
                .put(Endpoints.UPDATE_CARD)
                .then()
                .statusCode(argumentsHolder.getStatusCode())
                .body(equalTo(argumentsHolder.getErrorMessage()));
    }

    @Test
    public void checkUpdateBoardWithAnotherUserCredentials() {
        requestWithoutAuth()
                .queryParams(ANOTHER_USER_AUTH_QUERY_PARAMS)
                .pathParam(PATH_PARAM_ID, EXISTING_CARD_ID)
                .body(Map.of("name", "updatedBoardName"))
                .contentType(ContentType.JSON)
                .put(Endpoints.UPDATE_CARD)
                .then()
                .statusCode(401)
                .body(equalTo("invalid token"));
    }
}
