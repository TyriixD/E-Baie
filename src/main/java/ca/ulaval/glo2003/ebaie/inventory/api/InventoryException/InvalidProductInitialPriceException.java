package ca.ulaval.glo2003.ebaie.inventory.api.InventoryException;

public class InvalidProductInitialPriceException extends RuntimeException {
    public InvalidProductInitialPriceException(String message) {
        super(message);
    }

    public String getCode() {
        return "INVALID_AMOUNT";
    }
}
