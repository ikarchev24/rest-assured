package arguments.providers;

import arguments.holders.AuthValidationArgumentsHolder;
import consts.UrlParamValues;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import tests.get.boards.GetBoardsValidationTest;
import tests.get.cards.GetCardsValidationTest;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

import static consts.UrlParamValues.ANOTHER_USER_AUTH_QUERY_PARAMS;

public class AuthValidationArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream provideArguments(ExtensionContext context) {

        String errorMessage = null;
        String contextClass = context.getRequiredTestClass().getSimpleName();

        if (contextClass.equals(GetCardsValidationTest.class.getSimpleName())) {
            errorMessage = "unauthorized card permission requested";
        } else if (contextClass.equals(GetBoardsValidationTest.class.getSimpleName())) {
            errorMessage = "unauthorized permission requested";
        }

            return Stream.of(
                    new AuthValidationArgumentsHolder(Collections.emptyMap(),
                            errorMessage),
                    new AuthValidationArgumentsHolder(Map.of("key", UrlParamValues.API_KEY),
                            errorMessage),
                    new AuthValidationArgumentsHolder(Map.of("token", UrlParamValues.AUTH_TOKEN),
                            errorMessage),
                    new AuthValidationArgumentsHolder(ANOTHER_USER_AUTH_QUERY_PARAMS,
                            "invalid token")
            ).map(Arguments::of);
    }
}
