package arguments.holders;

import java.util.Map;

public class BoardNameValidationArgumentsHolder {

    private final Map<String, ?> bodyParams;
    private final String errorMessage;
    private final int statusCode;

    public BoardNameValidationArgumentsHolder(Map<String, ?> bodyParams, String errorMessage, int statusCode) {
        this.bodyParams = bodyParams;
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }

    public Map<String, ?> getBodyParams() {
        return bodyParams;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
