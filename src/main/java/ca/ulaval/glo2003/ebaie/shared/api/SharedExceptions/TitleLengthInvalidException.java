package ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions;

public class TitleLengthInvalidException extends  RuntimeException{

    public TitleLengthInvalidException(String message) {
        super(message);
    }

    public String getCode() {
        return "TITLLE_LENGTH_INVALID";
    }
}
