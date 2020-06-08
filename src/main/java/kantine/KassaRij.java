package kantine;

import java.util.*;

public class KassaRij {
    private LinkedList<Dienblad> personen;

    /**
     * Constructor
     */
    public KassaRij() {
        personen = new LinkedList<>();// method body omitted
    }

    /**
     * persoon.Persoon sluit achter in de rij aan
     *
     * @param klant
     */
    public void sluitAchteraan(Dienblad klant) {
        personen.push(klant);
    }

    /**
     * Indien er een rij bestaat, de eerste klant uit de rij verwijderen en retourneren. Als er
     * niemand in de rij staat geeft deze null terug.
     *
     * @return Eerste klant in de rij of null
     */
    public Dienblad eerstePersoonInRij() {
        if (erIsEenRij()) {
            return personen.pop();
        }
        return null;
    }

    /**
     * Methode kijkt of er personen in de rij staan.
     *
     * @return Of er wel of geen rij bestaat
     */
    public boolean erIsEenRij() {
        return personen.size() > 0;
    }
}
