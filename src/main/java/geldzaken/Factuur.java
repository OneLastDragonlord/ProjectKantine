package geldzaken;

import jdk.jfr.Unsigned;
import kantine.Artikel;
import kantine.Dienblad;
import persoon.Persoon;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "factuur")

public class Factuur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "factuur_id", unique = true)
    private Long id;
    @Column(name = "datum", nullable = false)
    private LocalDate datum;
    @Column(name = "totaal", nullable = false)
    private double totaal;
    @Column(name = "artikelen", nullable = false)
    private int artikelen;
    @Column(name = "totalekorting", nullable = false)
    private double totalekorting;
    @Transient
    private double korting;
    @ManyToMany(cascade = CascadeType.ALL)
    private ArrayList<FactuurRegel> regels;

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
     * <p>
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
                this.totaal += (getTotaalPrijs(klant) - korting);
                totalekorting = korting;
            } else {
                betaalwijze.betaal(getTotaalPrijs(klant) - dagelijkKorting);
                this.totaal += (getTotaalPrijs(klant) - dagelijkKorting);
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
            Artikel artikel = itr.next();
            double temp = artikel.getPrijs();
            regels.add(new FactuurRegel(Factuur.this,artikel));
            prijs += temp;
            System.out.println(temp);
        }
        return prijs;
    }

    /*
     * @return de toegepaste korting
     */
    public double getTotaalKorting(Dienblad klant) {
        double dagelijksekorting = 0;
        Iterator<Artikel> itr = klant.getIterator();
        while (itr.hasNext()) {
            double temp = itr.next().getKorting();
            dagelijksekorting += temp;
        }
        return dagelijksekorting;
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
        String factuurdatum = "Factuurdatum: " + this.datum;
        String uitkomst = String.format("%s\nBedrag: %.2f\nKorting: %.2f\nTotaalbedrag: %.2f", factuurdatum, this.totaal, this.totalekorting, (this.totaal - this.korting));
        return uitkomst;
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






