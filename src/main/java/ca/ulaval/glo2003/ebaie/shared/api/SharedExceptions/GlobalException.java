package ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions;

public class GlobalException extends RuntimeException {
    public GlobalException(String message) {
        super(message);
    }

    public String getCode() {
        return "UNKNOWN_ERROR";
    }
}
