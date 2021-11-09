package arguments.providers;

import arguments.holders.CardNameValidationArgumentsHolder;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

import static consts.UrlParamValues.EXISTING_CARD_ID_LIST;

public class CardNameValidationArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream provideArguments(ExtensionContext context) {
        return Stream.of(
                new CardNameValidationArgumentsHolder(Map.of("idList", EXISTING_CARD_ID_LIST, "name", 1234), "invalid value for name", 400),
                new CardNameValidationArgumentsHolder(Map.of("name", "test card"), "invalid value for idList", 400),
                new CardNameValidationArgumentsHolder(Collections.emptyMap(), "invalid value for idList", 400)
        ).map(Arguments::of);
    }
}
