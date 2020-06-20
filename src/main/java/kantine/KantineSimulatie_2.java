package kantine;

import geldzaken.Administratie;
import geldzaken.Contant;
import geldzaken.Factuur;
import kantine.Dienblad;
import kantine.Kantine;
import kantine.KantineAanbod;
import persoon.Docent;
import persoon.KantineMedewerker;
import persoon.Student;
import utility.Datum;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.*;

public class KantineSimulatie_2 {

    // kantine
    private Kantine kantine;

    // kantineaanbod
    private KantineAanbod kantineaanbod;

    // random generator
    private Random random;

    // aantal artikelen
    private static final int AANTAL_ARTIKELEN = 4;

    // artikelen
    private static final String[] artikelnamen =
            new String[]{"Koffie", "Broodje pindakaas", "Broodje kaas", "Appelsap"};

    // prijzen
    private static double[] artikelprijzen = new double[]{1.50, 2.10, 1.65, 1.65};

    public static int getMinArtikelenPerSoort() {
        return MIN_ARTIKELEN_PER_SOORT;
    }

    public static int getMaxArtikelenPerSoort() {
        return MAX_ARTIKELEN_PER_SOORT;
    }

    // minimum en maximum aantal artikelen per soort
    private static final int MIN_ARTIKELEN_PER_SOORT = 10;
    private static final int MAX_ARTIKELEN_PER_SOORT = 20;

    // minimum en maximum aantal personen per dag
    private static final int MIN_PERSONEN_PER_DAG = 50;
    private static final int MAX_PERSONEN_PER_DAG = 100;

    // minimum en maximum artikelen per persoon
    private static final int MIN_ARTIKELEN_PER_PERSOON = 1;
    private static final int MAX_ARTIKELEN_PER_PERSOON = 4;


    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("Main");
    private EntityManager manager;


    /**
     * Constructor
     */
//    Main runner = new Main();
//        runner.runVoorbeeld();
    public KantineSimulatie_2() {
        runVoorbeeld();
        kantine = new Kantine(manager);
        random = new Random();
        int[] hoeveelheden =
                getRandomArray(AANTAL_ARTIKELEN, MIN_ARTIKELEN_PER_SOORT, MAX_ARTIKELEN_PER_SOORT);
        kantineaanbod = new KantineAanbod(artikelnamen, artikelprijzen, hoeveelheden);
        kantine.setKantineAanbod(kantineaanbod);

    }

    public void runVoorbeeld() {
        manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        Student persoon = new Student(1, "henk", "jan", new Datum(29, 5, 2000), 'm', 1, "SCMI");
        // create(persoon);

    }

    public void exitVoorbeeld() {
        manager.close();
        ENTITY_MANAGER_FACTORY.close();
    }

    /**
     * Methode om een array van random getallen liggend tussen min en max van de gegeven lengte te
     * genereren
     *
     * @param lengte
     * @param min
     * @param max
     * @return De array met random getallen
     */
    private int[] getRandomArray(int lengte, int min, int max) {
        int[] temp = new int[lengte];
        for (int i = 0; i < lengte; i++) {
            temp[i] = getRandomValue(min, max);
        }

        return temp;
    }

    /**
     * Methode om een random getal tussen min(incl) en max(incl) te genereren.
     *
     * @param min
     * @param max
     * @return Een random getal
     */
    private int getRandomValue(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Methode om op basis van een array van indexen voor de array artikelnamen de bijhorende array
     * van artikelnamen te maken
     *
     * @param indexen
     * @return De array met artikelnamen
     */
    private String[] geefArtikelNamen(int[] indexen) {
        String[] artikelen = new String[indexen.length];

        for (int i = 0; i < indexen.length; i++) {
            artikelen[i] = artikelnamen[indexen[i]];

        }

        return artikelen;
    }

    public List<Double> getTotalePrijs() {
        List<Double> totalen = manager
                .createQuery("SELECT SUM(totaal) FROM  Factuur",
                        Double.class).getResultList();
        return totalen;
    }

    public List<Double> getTotaleKorting() {
        List<Double> totalen = manager
                .createQuery("SELECT SUM(totalekorting) FROM  Factuur",
                        Double.class).getResultList();
        return totalen;
    }

    public List<Double> getAverageKorting() {
        List<Double> totalen = manager
                .createQuery("SELECT AVG(totalekorting) FROM  Factuur",
                        Double.class).getResultList();
        return totalen;
    }

    public List<Double> getAveragePrijs() {
        List<Double> totalen = manager
                .createQuery("SELECT AVG(totaal) FROM  Factuur",
                        Double.class).getResultList();
        return totalen;
    }
    public List<Double> getHoogsteOmzet() {
        List<Double> totalen = manager
                .createQuery("SELECT totaal FROM  Factuur   ORDER BY totaal DESC",
                        Double.class).setMaxResults(3).getResultList();
        return totalen;
    }


    /**
     * Deze methode simuleert een aantal dagen
     * in het verloop van de kantine
     *
     * @param dagen
     */
    public void simuleer(int dagen) {
        // for lus voor dagen
        int[] aantalKlanten = new int[dagen];
        double[] omzet = new double[dagen];
        for (int i = 0; i < dagen; i++) {

            //niet random personen
            int aantalpersonen = 3;
            int studentInt = 1;
            int docentInt = 2;

            // bedenk hoeveel personen vandaag binnen lopen
//            int aantalpersonen = 100 ;
//            Random randomInt = new Random();
//            int studentInt = 89;
//            int docentInt = 99;

            // laat de personen maar komen...
            for (int j = 0; j < aantalpersonen; j++) {

                // maak persoon en dienblad aan, koppel ze
                // en bedenk hoeveel artikelen worden gepakt
                Dienblad dienblad;
                //int temp = randomInt.nextInt(aantalpersonen);
                if (j/*temp*/ < studentInt) {
                    Student persoon = new Student();
                    persoon.setBetaalwijze(new Contant());
                    persoon.getBetaalwijze().setSaldo(10);
                    dienblad = new Dienblad(persoon);
                    System.out.println(j/*temp*/ + "," + persoon.toString());
                } else if (j/*temp*/ < docentInt) {
                    Docent persoon = new Docent();
                    persoon.setBetaalwijze(new Contant());
                    persoon.getBetaalwijze().setSaldo(35);
                    dienblad = new Dienblad(persoon);
                    System.out.println(j/*temp*/ + "," + persoon.toString());
                } else {
                    KantineMedewerker persoon = new KantineMedewerker();
                    persoon.setBetaalwijze(new Contant());
                    persoon.getBetaalwijze().setSaldo(50);
                    dienblad = new Dienblad(persoon);
                    System.out.println(j/*temp*/ + "," + persoon.toString());
                }


                int aantalartikelen = 1;

                // genereer de "artikelnummers", dit zijn indexen
                // van de artikelnamen

                int[] tepakken = getRandomArray(
                        aantalartikelen, 0, AANTAL_ARTIKELEN - 1);

                // vind de artikelnamen op basis van
                // de indexen hierboven
                String[] artikelen = geefArtikelNamen(tepakken);
                kantine.loopPakSluitAan(dienblad, artikelen);
                // loop de kantine binnen, pak de gewenste
                // artikelen, sluit aan

            }
            // verwerk rij voor de kassa
            kantine.verwerkRijVoorKassa();
            // toon dagtotalen (artikelen en geld in kassa)
            System.out.printf("Totaal artikelen per dag: %s, Totale winst: %.2f %n", kantine.getKassa().aantalArtikelen(), kantine.getKassa().hoeveelheidGeldInKassa());
            aantalKlanten[i] = kantine.getKassa().aantalArtikelen();
            omzet[i] = kantine.getKassa().hoeveelheidGeldInKassa();
            // reset de kassa voor de volgende dag
            kantine.getKassa().resetKassa();

        }
        //System.out.printf("%.2f\n", Administratie.berekenGemiddeldAantal(aantalKlanten));
        //System.out.printf("%.2f\n", Administratie.berekenGemiddeldeOmzet(omzet));
        //System.out.println(Arrays.toString(Administratie.berekenDagOmzet(omzet)));
        System.out.println("De totale prijs: " + getTotalePrijs());
        System.out.println("De totale korting: " + getTotaleKorting());
        System.out.println("De gemiddelde prijs: " + getAveragePrijs());
        System.out.println("De gemiddelde korting: " + getAverageKorting());
        System.out.println("De 3 hoogste: " + getHoogsteOmzet());
        exitVoorbeeld();
    }
}
