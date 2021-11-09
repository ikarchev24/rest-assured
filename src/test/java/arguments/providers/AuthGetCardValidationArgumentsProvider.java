package arguments.providers;

import arguments.holders.AuthValidationArgumentsHolder;
import consts.UrlParamValues;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

import static consts.UrlParamValues.ANOTHER_USER_AUTH_QUERY_PARAMS;

public class AuthGetCardValidationArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream provideArguments(ExtensionContext context) {
        return Stream.of(
                new AuthValidationArgumentsHolder(Collections.emptyMap(),
                        "unauthorized card permission requested", 401),
                new AuthValidationArgumentsHolder(Map.of("key", UrlParamValues.API_KEY),
                        "unauthorized card permission requested", 401),
                new AuthValidationArgumentsHolder(Map.of("token", UrlParamValues.AUTH_TOKEN),
                        "unauthorized card permission requested", 401),
                new AuthValidationArgumentsHolder(ANOTHER_USER_AUTH_QUERY_PARAMS,
                        "invalid token", 401)
        ).map(Arguments::of);
    }
}
