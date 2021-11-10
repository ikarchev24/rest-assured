package arguments.providers;

import arguments.holders.BoardNameValidationArgumentsHolder;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

public class BoardNameValidationArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream provideArguments(ExtensionContext context) {
        return Stream.of(
                new BoardNameValidationArgumentsHolder(Map.of("name", 1234), "invalid value for name", 400),
                new BoardNameValidationArgumentsHolder(Collections.emptyMap(), "invalid value for name", 400),
                new BoardNameValidationArgumentsHolder(Map.of("", "valid name"), "invalid value for name", 400)
        ).map(Arguments::of);
    }
}
