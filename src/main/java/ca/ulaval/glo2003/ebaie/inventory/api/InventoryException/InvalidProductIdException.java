package ca.ulaval.glo2003.ebaie.inventory.api.InventoryException;

public class InvalidProductIdException extends RuntimeException {
    public InvalidProductIdException(String message) {
        super(message);
    }

    public String getCode() {
        return "PRODUCT_NOT_FOUND";
    }
}
