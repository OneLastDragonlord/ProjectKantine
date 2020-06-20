package geldzaken;

import kantine.Artikel;
import kantine.Dienblad;
import kantine.KassaRij;
import persoon.Persoon;

import java.time.LocalDate;
import java.util.Iterator;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class Kassa {

    private int artikelen;
    private double geld;
    private EntityManager manager;

    /**
     * Constructor
     */
    public Kassa(KassaRij kassarij, EntityManager manager) {
        this.artikelen = 0;
        this.geld = 0;
        this.manager = manager;
    }


    /**
     * Vraag het aantal artikelen en de totaalprijs op. Tel deze gegevens op bij de controletotalen
     * die voor de kassa worden bijgehouden. De implementatie wordt later vervangen door een echte
     * betaling door de persoon.
     *
     * @param klant die moet afrekenen
     */
    public void rekenAf(Dienblad klant) {
        Factuur factuur = new Factuur(klant,LocalDate.of(2019,5,16),manager);
        create(factuur);
        this.geld += factuur.getTotaal();
        this.artikelen += factuur.getArtikelen();
    }

    public void create(Factuur factuur) {
        EntityTransaction transaction = null;
        try {
            // Get a transaction, sla de student gegevens op en commit de transactie
            transaction = manager.getTransaction();
            transaction.begin();
            manager.persist(factuur);
            transaction.commit();

        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
              //  System.out.println("hoi");
            }
            ex.printStackTrace();
        }
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


