package geldzaken;

import kantine.Artikel;
import kantine.Dienblad;
import kantine.KassaRij;
import persoon.Persoon;

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
        Persoon persoon = klant.getKlant();
        persoon.setBetaalwijze(new Contant());
        System.out.println(persoon instanceof KortingskaartHouder);
        Betaalwijze betaalwijze = persoon.getBetaalwijze();
        double korting = 0;
        double dagelijkKorting = getTotaalKorting(klant);;
        if (persoon instanceof KortingskaartHouder) {
            KortingskaartHouder persoon1 = (KortingskaartHouder) persoon;
            if (!persoon1.heeftMaximum()) {
                korting = getTotaalPrijs(klant) * persoon1.geefKortingsPercentage() / 100;
            } else {
                korting = getTotaalPrijs(klant) * persoon1.geefKortingsPercentage() / 100;
                if (korting > persoon1.geefMaximum()) {
                    korting = 1;
                }
            }
        }



        try {
            if(persoon instanceof KortingskaartHouder) {
                betaalwijze.betaal(getTotaalPrijs(klant) - korting);
                this.geld += (getTotaalPrijs(klant) - korting);
            }else{
                betaalwijze.betaal(getTotaalPrijs(klant) - dagelijkKorting);
                this.geld += (getTotaalPrijs(klant) - dagelijkKorting);
            }
            this.artikelen += getAantalArtikelen(klant);
        } catch (TeWeinigGeldException e) {
            System.out.println("Transactie geweigerd " + persoon.getVoornaam() + ' ' + persoon.getAchternaam());
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
    public double getTotaalPrijs(Dienblad klant) {
        double prijs = 0;
        Iterator<Artikel> itr = klant.getIterator();
        while (itr.hasNext()) {
            double temp = itr.next().getPrijs();
            prijs += temp;
            System.out.println(temp);
        }
        return prijs;
    }

    public double getTotaalKorting(Dienblad klant) {
        double korting = 0;
        Iterator<Artikel> itr = klant.getIterator();
        while (itr.hasNext()) {
            double temp = itr.next().getKorting();
            korting += temp;
        }
        return korting;
    }

    public int getAantalArtikelen(Dienblad klant) {
        int hoeveelArtikelen = 0;
        Iterator<Artikel> itr = klant.getIterator();
        while (itr.hasNext()) {
            System.out.println(itr.next().getNaam());
            hoeveelArtikelen++;
        }
        return hoeveelArtikelen;
    }
}


