package ca.ulaval.glo2003.ebaie.buyer.entities;

import ca.ulaval.glo2003.ebaie.valueobjects.BuyerId;

import java.time.LocalDate;

public class Buyer {

    private String name;
    private LocalDate birthDate;
    private BuyerId id;


    public Buyer(String name, LocalDate birthDate, BuyerId id) {
        this.name = name;
        this.birthDate = birthDate;
        this.id = id;
    }

    public BuyerId getId() {
        return id;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }


    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }


}
