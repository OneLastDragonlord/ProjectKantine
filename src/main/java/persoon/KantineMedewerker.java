package persoon;

import geldzaken.KortingskaartHouder;
import utility.Datum;

public class KantineMedewerker extends Persoon implements KortingskaartHouder {
    private int nummer;
    private boolean kassa;

    public KantineMedewerker(int BSN, String voornaam, String achternaam, Datum geboorteDatum, char geslacht, int nummer, boolean kassa) {
        super(BSN, voornaam, achternaam, geboorteDatum, geslacht);
        this.nummer = nummer;
        this.kassa = kassa;
    }

    public KantineMedewerker(int nummer, boolean kassa) {
        this.nummer = nummer;
        this.kassa = kassa;
    }

    public KantineMedewerker() {
        super();
    }

    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nummer) {
        this.nummer = nummer;
    }

    public boolean isKassa() {
        return kassa;
    }

    public void setKassa(boolean kassa) {
        this.kassa = kassa;
    }

    @Override
    public double geefKortingsPercentage() {
        return 35;
    }

    @Override
    public boolean heeftMaximum() {
        return false;
    }

    @Override
    public double geefMaximum() {
        return 0;
    }
}
