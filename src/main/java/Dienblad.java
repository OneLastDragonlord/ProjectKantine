import java.util.*;

public class Dienblad {
    private Stack<Artikel> artikelen;
    private Persoon klant;

    /**
     * Constructor
     */
    public Dienblad() {
        artikelen = new Stack<>();
    }

    public Dienblad(Persoon klant) {
        artikelen = new Stack<>();
        this.klant = klant;
    }

    /**
     * Methode om artikel aan dienblad toe te voegen
     *
     * @param artikel
     */
    public void voegToe(Artikel artikel) {
        artikelen.push(artikel);
    }

    /**
     * Methode om aantal artikelen op dienblad te tellen
     *
     * @return Het aantal artikelen
     */

    public Iterator<Artikel> getIterator(){
        Iterator<Artikel> itr = artikelen.iterator();
        return itr;
    }

}

