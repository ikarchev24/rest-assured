package arguments.holders;

import java.util.Map;

public class AuthValidationArgumentsHolder {

    private final Map<String, String> authParams;
    private final String errorMessage;
    private final int statusCode;

    public AuthValidationArgumentsHolder(Map<String, String> authParams, String errorMessage, int statusCode) {
        this.authParams = authParams;
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }

    public Map<String, String> getAuthParams() {
        return authParams;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
