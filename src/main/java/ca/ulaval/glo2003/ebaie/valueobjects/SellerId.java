package ca.ulaval.glo2003.ebaie.valueobjects;

public class SellerId {
    public String getValue() {
        return value;
    }

    private final String value;


    public SellerId(String value) {
        this.value = value;
    }

    public boolean isEmpty() {
        return value.isEmpty();
    }

    @Override
    public boolean equals(Object id) {
        if (id == null || getClass() != id.getClass()) {
            return false;
        }
        SellerId other = (SellerId) id;
        return value.equals(other.value);
    }
}
