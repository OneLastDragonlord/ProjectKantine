public class Kantine {

    private Kassa kassa;
    private KassaRij kassarij;

    /**
     * Constructor
     */
    public Kantine() {
        kassarij = new KassaRij();
        kassa = new Kassa(kassarij);
    }

    /**
     * In deze methode wordt een Persoon en Dienblad gemaakt en aan elkaar gekoppeld. Maak twee
     * Artikelen aan en plaats deze op het dienblad. Tenslotte sluit de Persoon zich aan bij de rij
     * voor de kassa.
     */
    public void loopPakSluitAan() {
        Persoon jan = new Persoon();
        Dienblad vanJan = new Dienblad(jan);
        this.kassarij = new KassaRij();
        Artikel artikel1 = new Artikel("ijs", 10);
        Artikel artikel2 = new Artikel("koek", 20);
        vanJan.voegToe(artikel1);
        vanJan.voegToe(artikel2);
        kassarij.sluitAchteraan(vanJan);
    }

    /**
     * Deze methode handelt de rij voor de kassa af.
     * Je weet niet hoelang de rij zal zijn dus nu blijft ie runnen totdat de rij leeg is.
     */
    public void verwerkRijVoorKassa() {
        while (kassarij.erIsEenRij()) {
            kassa.rekenAf(kassarij.eerstePersoonInRij());
        }
    }

    /**
     * Deze methode telt het geld uit de kassa
     *
     * @return hoeveelheid geld in kassa
     */
    public double hoeveelheidGeldInKassa() {

        return kassa.hoeveelheidGeldInKassa();
    }

    /**
     * Deze methode geeft het aantal gepasseerde artikelen.
     *
     * @return het aantal gepasseerde artikelen
     */
    public int aantalArtikelen() {
        // method body omitted
        return kassa.aantalArtikelen();
    }

    /**
     * Deze methode reset de bijgehouden telling van het aantal artikelen en "leegt" de inhoud van
     * de kassa.
     */
    public void resetKassa() {
       kassa.resetKassa();
    }
}
