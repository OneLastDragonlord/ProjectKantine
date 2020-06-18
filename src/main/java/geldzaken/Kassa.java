package geldzaken;

import kantine.Artikel;
import kantine.Dienblad;
import kantine.KassaRij;
import persoon.Persoon;

import java.time.LocalDate;
import java.util.Iterator;

public class Kassa {

    private int artikelen;
    private double geld;

    /**
     * Constructor
     */
    public Kassa(KassaRij kassarij) {
        this.artikelen = 0;
        this.geld = 0;
    }

    /**
     * Vraag het aantal artikelen en de totaalprijs op. Tel deze gegevens op bij de controletotalen
     * die voor de kassa worden bijgehouden. De implementatie wordt later vervangen door een echte
     * betaling door de persoon.
     *
     * @param klant die moet afrekenen
     */
    public void rekenAf(Dienblad klant) {
        Factuur factuur = new Factuur(klant,LocalDate.of(2019,5,16));
        this.geld += factuur.getTotaal();
        this.artikelen += factuur.getArtikelen();
    }


    /**
     * Geeft het aantal artikelen dat de kassa heeft gepasseerd, vanaf het moment dat de methode
     * resetWaarden is aangeroepen.
     *
     * @return aantal artikelen
     */
    public int aantalArtikelen() {
        return this.artikelen;
    }

    /**
     * Geeft het totaalbedrag van alle artikelen die de kass zijn gepasseerd, vanaf het moment dat
     * de methode resetKassa is aangeroepen.
     *
     * @return hoeveelheid geld in de kassa
     */
    public double hoeveelheidGeldInKassa() {
        return this.geld;
    }

    /**
     * reset de waarden van het aantal gepasseerde artikelen en de totale hoeveelheid geld in de
     * kassa.
     */
    public void resetKassa() {
        setArtikelen(0);
        setGeld(0);
    }

    public void setArtikelen(int artikelen) {
        this.artikelen = artikelen;
    }

    public void setGeld(double geld) {
        this.geld = geld;
    }

    /**
     * totaalprijs per kantine.Dienblad
     */





}


