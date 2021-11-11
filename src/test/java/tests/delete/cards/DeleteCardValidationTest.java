package tests.delete.cards;

import arguments.holders.AuthValidationArgumentsHolder;
import arguments.holders.CardIdValidationArgumentsHolder;
import arguments.providers.AuthCreateDeleteCardValidationArgumentsProvider;
import arguments.providers.CardIdValidationArgumentsProvider;
import consts.Endpoints;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import tests.BaseTest;

import static consts.UrlParamValues.*;
import static org.hamcrest.Matchers.equalTo;

public class DeleteCardValidationTest extends BaseTest {
    @ParameterizedTest
    @ArgumentsSource(CardIdValidationArgumentsProvider.class)
    public void checkDeleteCardWithInvalidId(CardIdValidationArgumentsHolder argumentsHolder) {
        requestWithAuth()
                .pathParams(argumentsHolder.getPathParams())
                .delete(Endpoints.DELETE_CARD)
                .then()
                .statusCode(argumentsHolder.getStatusCode())
                .body(equalTo(argumentsHolder.getErrorMessage()));
    }

    @ParameterizedTest
    @ArgumentsSource(AuthCreateDeleteCardValidationArgumentsProvider.class)
    public void checkDeleteCardWithInvalidAuth(AuthValidationArgumentsHolder argumentsHolder) {
        requestWithoutAuth()
                .queryParams(argumentsHolder.getAuthParams())
                .pathParam(PATH_PARAM_ID, EXISTING_CARD_ID)
                .delete(Endpoints.DELETE_CARD)
                .then()
                .statusCode(argumentsHolder.getStatusCode())
                .body(equalTo(argumentsHolder.getErrorMessage()));
    }

    @Test
    public void checkDeleteCardWithAnotherUserCredentials() {
        requestWithoutAuth()
                .queryParams(ANOTHER_USER_AUTH_QUERY_PARAMS)
                .pathParam(PATH_PARAM_ID, EXISTING_CARD_ID)
                .delete(Endpoints.DELETE_CARD)
                .then()
                .statusCode(401)
                .body(equalTo("invalid token"));
    }
}
