package tests.get.cards;

import arguments.holders.AuthValidationArgumentsHolder;
import arguments.holders.CardIdValidationArgumentsHolder;
import arguments.providers.AuthGetCardValidationArgumentsProvider;
import arguments.providers.CardIdValidationArgumentsProvider;
import consts.Endpoints;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import tests.BaseTest;

import static consts.UrlParamValues.EXISTING_CARD_ID;
import static consts.UrlParamValues.PATH_PARAM_ID;
import static org.hamcrest.Matchers.equalTo;

public class GetCardsValidationTest extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(CardIdValidationArgumentsProvider.class)
    public void checkGetCardWithInvalidId(CardIdValidationArgumentsHolder argumentsHolder) {
        requestWithAuth()
                .pathParams(argumentsHolder.getPathParams())
                .get(Endpoints.GET_CARD)
                .then()
                .statusCode(argumentsHolder.getStatusCode())
                .body(equalTo(argumentsHolder.getErrorMessage()));

    }

    @ParameterizedTest
    @ArgumentsSource(CardIdValidationArgumentsProvider.class)
    public void checkGetCardsForBoardWithInvalidId(CardIdValidationArgumentsHolder argumentsHolder) {
        requestWithAuth()
                .pathParams(argumentsHolder.getPathParams())
                .get(Endpoints.GET_CARDS_FOR_BOARD)
                .then()
                .statusCode(argumentsHolder.getStatusCode())
                .body(equalTo(argumentsHolder.getErrorMessage()));
    }

    @ParameterizedTest
    @ArgumentsSource(AuthGetCardValidationArgumentsProvider.class)
    public void checkGetCardWithInvalidAuth(AuthValidationArgumentsHolder argumentsHolder) {
        requestWithoutAuth()
                .queryParams(argumentsHolder.getAuthParams())
                .pathParam(PATH_PARAM_ID, EXISTING_CARD_ID)
                .get(Endpoints.GET_CARD)
                .then()
                .statusCode(argumentsHolder.getStatusCode())
                .body(equalTo(argumentsHolder.getErrorMessage()));
    }
}
