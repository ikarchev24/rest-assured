package tests.post.cards;

import arguments.holders.AuthValidationArgumentsHolder;
import arguments.holders.CardNameValidationArgumentsHolder;
import arguments.providers.AuthCreateCardValidationArgumentsProvider;
import arguments.providers.CardNameValidationArgumentsProvider;
import consts.Endpoints;
import io.restassured.http.ContentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import tests.BaseTest;

import java.util.Map;

import static consts.UrlParamValues.EXISTING_CARD_ID_LIST;
import static org.hamcrest.Matchers.equalTo;

public class CreateCardValidationTest extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(CardNameValidationArgumentsProvider.class)
    public void createCardWithInvalidNameTest(CardNameValidationArgumentsHolder argumentsHolder) {
        requestWithAuth()
                .body(argumentsHolder.getBodyParams())
                .contentType(ContentType.JSON)
                .post(Endpoints.CREATE_CARD)
                .then()
                .body(equalTo(argumentsHolder.getErrorMessage()))
                .statusCode(argumentsHolder.getStatusCode()
                );
    }

    @ParameterizedTest
    @ArgumentsSource(AuthCreateCardValidationArgumentsProvider.class)
    public void createCardTestWithInvalidAuth(AuthValidationArgumentsHolder argumentsHolder) {
        requestWithoutAuth()
                .queryParams(argumentsHolder.getAuthParams())
                .body(Map.of("name", "test name", "idList", EXISTING_CARD_ID_LIST))
                .contentType(ContentType.JSON)
                .post(Endpoints.CREATE_CARD)
                .then()
                .log().body()
                .statusCode(equalTo(argumentsHolder.getStatusCode()));

    }
}
