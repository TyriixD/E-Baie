package ca.ulaval.glo2003.ebaie.buyer.entities;

import ca.ulaval.glo2003.ebaie.valueobjects.BuyerId;

import java.time.LocalDate;
import java.util.UUID;

public class BuyerFactory {

    public BuyerFactory() {
    }

    static UUID generateUUID() {

        return UUID.randomUUID();
    }

    public Buyer create(String name, LocalDate birthDate) {
        UUID id = generateUUID();
        BuyerId idString = new BuyerId(id.toString());

        return new Buyer(name, birthDate, idString);
    }

}
