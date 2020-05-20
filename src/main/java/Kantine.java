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


   public Kassa getKassa() {
       return kassa;
   }

}
