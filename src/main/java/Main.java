import kantine.KantineSimulatie_2;

public class Main {


    public static void main(String[] args) {


        int dagen;

        if (args.length == 0) {
            dagen = 7;
        } else {
            dagen = Integer.parseInt(args[0]);
        }
        KantineSimulatie_2 kantineSimulatie = new KantineSimulatie_2();
        kantineSimulatie.simuleer(dagen);
        //System.out.printf("%.4f\n", Administratie.berekenGemiddeldAantal(new int[]{45, 56, 34, 39, 40, 31}));
        //System.out.printf("%.4f\n", Administratie.berekenGemiddeldeOmzet(new double[]{567.70, 498.25, 458.90}));
        //System.out.println(Arrays.toString(Administratie.berekenDagOmzet(new double[]{321.35, 450.50, 210.45, 190.85, 193.25, 159.90, 214.25, 220.90, 201.90, 242.70, 260.35})));

    }

//    public void create(Student student) {
//        EntityTransaction transaction = null;
//        try {
//            // Get a transaction, sla de student gegevens op en commit de transactie
//            transaction = manager.getTransaction();
//            transaction.begin();
//            manager.persist(student);
//            transaction.commit();
//        } catch (Exception ex) {
//            // If there are any exceptions, roll back the changes
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            ex.printStackTrace();
//        }
//    }

}
