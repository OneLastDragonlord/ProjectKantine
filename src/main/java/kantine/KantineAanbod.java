package kantine;

import java.util.*;

public class KantineAanbod {
    // interne opslag voorraad
    private HashMap<String, ArrayList<Artikel>> aanbod;
    private HashMap<String, Integer> startVoorraad;
    private HashMap<String, Double> prijzen;

    /**
     * Je gebruikt een HashMap als je dezelfde waardes wilt opslaan
     * Constructor. Het eerste argument is een lijst met artikelnamen, het tweede argument is
     * eenlijst met prijzen en het derde argument is een lijst met hoeveelheden. Let op: de
     * dimensies van de drie arrays moeten wel gelijk zijn!
     */
    public KantineAanbod(String[] artikelnaam, double[] prijs, int[] hoeveelheid) {
        Random random = new Random();
        int randomKorting = random.nextInt(artikelnaam.length);
        aanbod = new HashMap<>();
        startVoorraad = new HashMap<>();
        prijzen = new HashMap<>();
        for (int i = 0; i < artikelnaam.length; i++) {
            ArrayList<Artikel> artikelen = new ArrayList<>();
            for (int j = 0; j < hoeveelheid[i]; j++) {
                artikelen.add(new Artikel(artikelnaam[i], prijs[i]));
            }
            startVoorraad.put(artikelnaam[i], hoeveelheid[i]);
            if(i!=randomKorting) {
                prijzen.put(artikelnaam[i], prijs[i]);
            } else {
                prijzen.put(artikelnaam[i], prijs[i]*0.8);
            }
            aanbod.put(artikelnaam[i], artikelen);
        }
    }

    private void vulVoorraadAan(String productnaam) {
        ArrayList<Artikel> huidigeVoorraad = aanbod.get(productnaam);
        int startHoeveelheid = startVoorraad.get(productnaam);
        int huidigeHoeveelheid = huidigeVoorraad.size();
        double prijs = prijzen.get(productnaam);
        for (int j = huidigeHoeveelheid; j < startHoeveelheid; j++) {
            huidigeVoorraad.add(new Artikel(productnaam, prijs));
        }
        aanbod.put(productnaam, huidigeVoorraad);
    }

    /*
     * Private methode om de lijst van artikelen te krijgen op basis van de naam van het artikel.
     * Retourneert null als artikel niet bestaat.
     */
    private ArrayList<Artikel> getArrayList(String productnaam) {
        return aanbod.get(productnaam);
    }

    /**
     * Private methode om een kantine.Artikel van de stapel artikelen af te pakken. Retourneert null als de
     * stapel leeg is.
     */
    private Artikel getArtikel(ArrayList<Artikel> stapel) {
        if (stapel == null) {
            return null;
        }
        if (stapel.size() == 0) {
            return null;
        } else {
            Artikel a = stapel.get(0);
            stapel.remove(0);
            if (stapel.size() <= KantineSimulatie_2.getMinArtikelenPerSoort())
                vulVoorraadAan(a.getNaam());
            return a;
        }
    }

    /**
     * Publieke methode om een artikel via naam van de stapel te pakken. Retouneert null als artikel
     * niet bestaat of niet op voorraad is.
     *
     * @param productnaam (van artikel)
     * @return artikel (of null)
     */
    public Artikel getArtikel(String productnaam) {
        return getArtikel(getArrayList(productnaam));
    }
}
