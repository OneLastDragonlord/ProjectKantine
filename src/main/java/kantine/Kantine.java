package kantine;

import geldzaken.Kassa;
import javax.persistence.EntityManager;
public class Kantine {

    private Kassa kassa;
    private KassaRij kassarij;
    private KantineAanbod kantineAanbod;
    //private EntityManager manager;
    /**
     * Constructor
     */
    public Kantine(EntityManager manager) {
        kassarij = new KassaRij();
        kassa = new Kassa(kassarij, manager);
        //this.manager = manager;
    }

    /**
     * In deze methode wordt een persoon.Persoon en kantine.Dienblad gemaakt en aan elkaar gekoppeld. Maak twee
     * Artikelen aan en plaats deze op het dienblad. Tenslotte sluit de persoon.Persoon zich aan bij de rij
     * voor de kassa.
     */
    public void loopPakSluitAan(Dienblad dienblad, String[] artikelnamen) {
        for (String artikel : artikelnamen) {
            dienblad.voegToe(kantineAanbod.getArtikel(artikel));
        }
        kassarij.sluitAchteraan(dienblad);
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

    public void setKantineAanbod(KantineAanbod kantineAanbod) {
        this.kantineAanbod = kantineAanbod;
    }

    public KantineAanbod getKantineAanbod() {
        return kantineAanbod;
    }
}
