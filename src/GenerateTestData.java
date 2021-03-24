import java.io.IOException;

/*
 * class used to generate dummy data for testing the database
 */
public class GenerateTestData {
    public static final String[] ADMIN_IDS = new String[]{"ADMIN0","ADMIN1","ADMIN2","ADMIN3","ADMIN4","ADMIN5","ADMIN6","ADMIN7","ADMIN8","ADMIN9"};
    public static final String[] FIRST_NAMES = new String[]{"Zoie", "Natalee", "Jaquan", "Guillermo", "Edith", "Isabela", "Manuel", "Angel", "Lana", "Irene"};
    public static final String[] LAST_NAMES = new String[]{"Lamb", "Mcgrath", "Jefferson", "Singh", "Orr", "Pope", "Yu", "Morse", "Bradford", "Whitaker"};
    public static final String[] STREET_NAMES = new String[]{"Robin Circle", "Blair Road", "Maypole Street", "Belle Vue Glen", "Bilton Grange Road", "Wolseley Side", "Tenbury Gardens", "Pine Tree Hollow", "Lakeview Approach", "Hollin Close"};
    public static final String[] INSU_TYPES = new String[]{"Private", "Government"};
    public static final String[] PAT_TYPES = new String[]{"Pediatric", "Adult", "Senior"};
    public static final String[] ALG_TYPES = new String[]{"none", "food", "medication", "other"};
    public static final String[] ILL_TYPES = new String[]{"none", "CHD", "diabetes", "asthma", "other"};
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        generate("testDBFile.txt");
    }

    public static void generate(String name) throws IOException {
        PatientProfDB db = new PatientProfDB(name);
        for(int i = 0; i < 10; i++) {
            db.insertNewProfile(
                    new PatientProf(
                            ADMIN_IDS[i], //pick admin id from list
                            FIRST_NAMES[i], //pick first name from list
                            LAST_NAMES[i], //pick last name from list
                            String.format("%d %s", (int)(Math.random()*2000) + 1, STREET_NAMES[i]), //pick random street name from list and generate random street number
                            String.format("(%d)-%d-%d", (int)(Math.random()*900) + 100,(int)(Math.random()*900) + 100,(int)(Math.random()*9000) + 1000), //pick 3 random numbers as a phone number
                            ((int)((Math.random() * 1000 + 150)*100))/100.0f, //pick a random number
                            randFrom(INSU_TYPES), //pick a random insurance type
                            randFrom(PAT_TYPES), //pick a random patient type
                            new MedCond(
                                    String.format("Dr. %s", randFrom(LAST_NAMES)), //pick last name from list
                                    String.format("(%d)-%d-%d", (int)(Math.random()*900) + 100,(int)(Math.random()*900) + 100,(int)(Math.random()*9000) + 1000), //pick 3 random numbers as a phone number
                                    randFrom(ALG_TYPES), //pick a random allergy type
                                    randFrom(ILL_TYPES) //pick a random illness type
                            )
                    )
            );
        }
        db.writeAllPatientProf();
    }

    private static String randFrom(String[] s) {
        return s[(int)(Math.random()*s.length)];
    }
}
