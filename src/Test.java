import java.io.IOException;
/*
 * class used to generate dummy data for testing the database
 */
public class Test {
    public static final String[] FIRST_NAMES = new String[]{"Zoie", "Natalee", "Jaquan", "Guillermo", "Edith", "Isabela", "Manuel", "Angel", "Lana", "Irene"};
    public static final String[] LAST_NAMES = new String[]{"Lamb", "Mcgrath", "Jefferson", "Singh", "Orr", "Pope", "Yu", "Morse", "Bradford", "Whitaker"};
    public static final String[] STREET_NAMES = new String[]{"Robin Circle", "Blair Road", "Maypole Street", "Belle Vue Glen", "Bilton Grange Road", "Wolseley Side", "Tenbury Gardens", "Pine Tree Hollow", "Lakeview Approach", "Hollin Close"};
    public static final String[] INSU_TYPES = new String[]{"Private", "Government"};
    public static final String[] PAT_TYPES = new String[]{"Pediatric", "Adult", "Senior"};
    public static final String[] ALG_TYPES = new String[]{"none", "food", "medication", "other"};
    public static final String[] ILL_TYPES = new String[]{"none", "CHD", "diabetes", "asthma", "other"};
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        PatientProfDB db = new PatientProfDB("testDBFile.txt");
        // String adminID, String firstName, String lastName, String address, String phone, float coPay, String insuType, String patientType, MedCond medCondInfo
        for(int i = 0; i < 10; i++) {
            db.insertNewProfile(
                    new PatientProf(
                            String.format("ADMIN%d", i),
                            FIRST_NAMES[i],
                            LAST_NAMES[i],
                            String.format("%d %s", (int)(Math.random()*2000) + 1, STREET_NAMES[i]),
                            String.format("(%d)-%d-%d", (int)(Math.random()*900) + 100,(int)(Math.random()*900) + 100,(int)(Math.random()*9000) + 1000),
                            ((int)((Math.random() * 1000 + 150)*100))/100.0f,
                            randFrom(INSU_TYPES),
                            randFrom(PAT_TYPES),
                            new MedCond(
                                    String.format("Dr. %s", randFrom(LAST_NAMES)),
                                    String.format("(%d)-%d-%d", (int)(Math.random()*900) + 100,(int)(Math.random()*900) + 100,(int)(Math.random()*9000) + 1000),
                                    randFrom(ALG_TYPES),
                                    randFrom(ILL_TYPES)
                            )
                    )
            );
        }
        db.writeAllPatientProf();

        PatientProfDB db2 = new PatientProfDB("testDBFile.txt");
        db2.initializeDatabase();
        System.out.println(db2.findProfile("ADMIN1", "Mcgrath"));
    }

    private static String randFrom(String[] s) {
        return s[(int)(Math.random()*s.length)];
    }
}
