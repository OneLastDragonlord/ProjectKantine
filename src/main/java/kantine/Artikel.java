package kantine;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class Artikel {
    @Column
    private String naam;
    @Column
    private double prijs;
    @Transient
    private double korting;

    public Artikel(String naam, double prijs) {
        this.naam = naam;
        this.prijs = prijs;
        this.korting = 0;
    }

    public Artikel(String naam, double prijs, double korting) {
        this.naam = naam;
        this.prijs = prijs;
        this.korting = korting;
    }

    public Artikel() {
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getNaam() {
        return naam;
    }

    public void setPrijs(String naam) {
        this.naam = naam;
    }

    public double getPrijs() {
        return prijs;
    }

    public double getKorting() {
        return korting;
    }

    public void setKorting(double korting) {
        this.korting = korting;
    }

    public String toString() {
        return getNaam() + ", " + getPrijs();
    }
}
