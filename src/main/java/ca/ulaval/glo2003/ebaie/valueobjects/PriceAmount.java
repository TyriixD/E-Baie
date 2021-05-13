package ca.ulaval.glo2003.ebaie.valueobjects;

public class PriceAmount {
    private double amount;

    public PriceAmount(double value) {
        this.amount = value;
    }

    public double getAmount() {
        return amount;
    }


    public boolean isSmaller(int number) {
        return amount < number;
    }

    public double multiply(double number) {
        return amount * number;
    }

    public double arrondir(double number) {
        return (double) Math.round(number * 100d / 100d);
    }

    @Override
    public boolean equals(Object amount) {
        if (amount == null || getClass() != amount.getClass()) {
            return false;
        }
        PriceAmount other = (PriceAmount) amount;
        return this.amount == (other.amount);
    }
}
