package ca.ulaval.glo2003.ebaie.valueobjects;

public class BuyerId {
    private final String value;

    public String getValue() {
        return value;
    }

    public BuyerId(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object id) {
        if (id == null || getClass() != id.getClass()) {
            return false;
        }
        BuyerId other = (BuyerId) id;
        return value.equals(other.value);
    }
}
