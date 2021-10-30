package tests.get.cards;

import arguments.holders.AuthValidationArgumentsHolder;
import arguments.holders.CardIdValidationArgumentsHolder;
import arguments.providers.AuthValidationArgumentsProvider;
import arguments.providers.CardIdValidationArgumentsProvider;
import consts.Endpoints;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import tests.BaseTest;

import static consts.UrlParamValues.PATH_PARAM_ID;
import static org.hamcrest.Matchers.equalTo;

public class GetCardsValidationTest extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(CardIdValidationArgumentsProvider.class)
    public void checkGetCardWithInvalidId(CardIdValidationArgumentsHolder argumentsHolder) {
        requestWithAuth()
                .pathParams(argumentsHolder.getPathParams())
                .get(Endpoints.GET_CARD_ENDPOINT)
                .then()
                .statusCode(argumentsHolder.getStatusCode())
                .body(equalTo(argumentsHolder.getErrorMessage()));

    }

    @ParameterizedTest
    @ArgumentsSource(CardIdValidationArgumentsProvider.class)
    public void checkGetCardsForBoardWithInvalidId(CardIdValidationArgumentsHolder argumentsHolder) {
        requestWithAuth()
                .pathParams(argumentsHolder.getPathParams())
                .get(Endpoints.GET_CARD_ENDPOINT)
                .then()
                .statusCode(argumentsHolder.getStatusCode())
                .body(equalTo(argumentsHolder.getErrorMessage()));
    }

    @ParameterizedTest
    @ArgumentsSource(AuthValidationArgumentsProvider.class)
    public void checkGetCardWithInvalidAuth(AuthValidationArgumentsHolder argumentsHolder) {
        requestWithoutAuth()
                .queryParams(argumentsHolder.getAuthParams())
                .pathParam(PATH_PARAM_ID, "617cd35314124046fc513c02")
                .get(Endpoints.GET_CARD_ENDPOINT)
                .then()
                .statusCode(equalTo(401))
                .body(equalTo("unauthorized card permission requested"));
    }
}
