package arguments.providers;

import arguments.holders.AuthValidationArgumentsHolder;
import consts.UrlParamValues;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

public class AuthCreateBoardValidationArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream provideArguments(ExtensionContext context) {

        return Stream.of(
                new AuthValidationArgumentsHolder(Collections.emptyMap(),
                        "invalid key", 401),
                new AuthValidationArgumentsHolder(Map.of("key", UrlParamValues.API_KEY),
                        "unauthorized permission requested", 401),
                new AuthValidationArgumentsHolder(Map.of("token", UrlParamValues.AUTH_TOKEN),
                        "invalid key", 401)
        ).map(Arguments::of);
    }
}
