package ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions;

public class FieldEmptyException extends RuntimeException {
    public FieldEmptyException(String message) {
        super(message);
    }

    public String getCode() {
        return "MISSING_FIELD";
    }

}
