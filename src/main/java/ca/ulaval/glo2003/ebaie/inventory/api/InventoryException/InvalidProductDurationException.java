package ca.ulaval.glo2003.ebaie.inventory.api.InventoryException;

public class InvalidProductDurationException extends RuntimeException {
    public InvalidProductDurationException(String message) {
        super(message);
    }

    public String getCode() {
        return "INVALID_DATERANGE";
    }
}
