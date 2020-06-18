package geldzaken;

import kantine.Artikel;
import kantine.Dienblad;
import persoon.Persoon;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Iterator;

public class Factuur implements Serializable {
    private Long id;
    private LocalDate datum;
    private double korting;
    private double totaal;
    private double newTotaal;
    private int artikelen;

    public Factuur() {
        totaal = 0;
        korting = 0;
    }

    public Factuur(Dienblad klant, LocalDate datum) {
        this();
        this.datum = datum;

        verwerkBestelling(klant);
    }

    /**
     * Verwerk artikelen en pas kortingen toe.
     *
     * Zet het totaal te betalen bedrag en het
     * totaal aan ontvangen kortingen.
     *
     * @param klant
     */

    private void verwerkBestelling(Dienblad klant) {
        Persoon persoon = klant.getKlant();

        System.out.println(persoon instanceof KortingskaartHouder);
        Betaalwijze betaalwijze = persoon.getBetaalwijze();
        double korting = 0;
        double dagelijkKorting = getTotaalKorting(klant);
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
            if (persoon instanceof KortingskaartHouder) {
                betaalwijze.betaal(getTotaalPrijs(klant) - korting);
                this.newTotaal += (getTotaalPrijs(klant) - korting);
            } else {
                betaalwijze.betaal(getTotaalPrijs(klant) - dagelijkKorting);
                this.newTotaal += (getTotaalPrijs(klant) - dagelijkKorting);
            }
            this.artikelen += getAantalArtikelen(klant);
        } catch (TeWeinigGeldException e) {
            System.out.println("Transactie geweigerd " + persoon.getVoornaam() + ' ' + persoon.getAchternaam());
        }
        System.out.println(this.toString());
    }


    /*
     * @return het totaalbedrag
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

    /*
         * @return de toegepaste korting
    */
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
    /*
         * @return een printbaar bonnetje
    */
    public String toString() {
        return "Factuurdatum: "+ this.datum + "\nbedrag: "+this.totaal+  "\nKorting: "+ this.korting + "\nTotaalbedrag: "+ (this.totaal-this.korting);
    }

    public double getKorting() {
        return korting;
    }

    public void setKorting(double korting) {
        this.korting = korting;
    }

    public double getTotaal() {
        return totaal;
    }

    public void setTotaal(double totaal) {
        this.totaal = totaal;
    }

    public int getArtikelen() {
        return artikelen;
    }

    public void setArtikelen(int artikelen) {
        this.artikelen = artikelen;
    }
}
