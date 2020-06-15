import geldzaken.Administratie;
import kantine.KantineSimulatie_2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;

public class Main {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("KantineSimulatie");
    private EntityManager manager;

    public void runVoorbeeld(){
        manager.close();
        ENTITY_MANAGER_FACTORY.close();
    }

    public static void main(String[] args) {
        Main runner = new Main();
        runner.runVoorbeeld();

        int dagen;

        if (args.length == 0) {
            dagen = 7;
        } else {
            dagen = Integer.parseInt(args[0]);
        }
        KantineSimulatie_2 kantineSimulatie = new KantineSimulatie_2();
        kantineSimulatie.simuleer(dagen);
        System.out.printf("%.4f\n", Administratie.berekenGemiddeldAantal(new int[]{45, 56, 34, 39, 40, 31}));
        System.out.printf("%.4f\n", Administratie.berekenGemiddeldeOmzet(new double[]{567.70, 498.25, 458.90}));
        System.out.println(Arrays.toString(Administratie.berekenDagOmzet(new double[]{321.35, 450.50, 210.45, 190.85, 193.25, 159.90, 214.25, 220.90, 201.90, 242.70, 260.35})));

    }


}