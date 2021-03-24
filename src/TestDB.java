import java.io.File;
import java.io.IOException;

/*
 * class used to test the PatientProfDB Class
 *
 * This class tests the following properties of PatientProfDB:
 *      - initializing the database
 *      - finding a profile in the database
 *      - finding the first profile, and using findNext to iterate through the database
 *      - adding a new profile
 *      - saving a database to a file and loading it later
 */
public class TestDB {
    public static void main(String[] args) {
        File testFile = new File("testing.txt");
        testFile.delete();

        try {
            GenerateTestData.generate("testing.txt");
        } catch (IOException e) {
            System.err.println("Error Generating Testing Data:");
            e.printStackTrace();
        }

        PatientProfDB db = new PatientProfDB("testing.txt");
        try {
            db.initializeDatabase();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error Initializing Database");
            e.printStackTrace();
        }

        PatientProf prof = db.findProfile(GenerateTestData.ADMIN_IDS[0], GenerateTestData.LAST_NAMES[0]);
        assert prof != null : "Error Finding Profile";

        prof = db.findFirstProfile();
        assert prof.getLastName().equals(GenerateTestData.LAST_NAMES[0]): "Find Next Profile Not Returning First Profile";

        prof = db.findNextProfile();
        assert prof.getLastName().equals(GenerateTestData.LAST_NAMES[0]): "Find Next Profile Not Returning First Profile";

        prof = db.findNextProfile();
        assert prof.getLastName().equals(GenerateTestData.LAST_NAMES[1]): "Find Next Profile Not Returning Next Profile";

        prof = db.findNextProfile();
        assert prof.getLastName().equals(GenerateTestData.LAST_NAMES[2]): "Find Next Profile Not Returning First Profile";

        db.resetCurrentPatientIndex();
        prof = db.findNextProfile();
        assert prof.getLastName().equals(GenerateTestData.LAST_NAMES[0]): "Resting DatabaseIndex Failed";

        db.insertNewProfile(new PatientProf("A0", "Jane", "Doe", null, null, 0.0f, null, null, null));
        prof = db.findProfile("A0", "Doe");
        assert prof != null && prof.getFirstName().equals("Jane"): "Error Inserting New Profile";

        try {
            db.writeAllPatientProf();
        } catch (IOException e) {
            System.err.println("Error Saving Database");
            e.printStackTrace();
        }

        PatientProfDB db2 = new PatientProfDB("testing.txt");
        try {
            db2.initializeDatabase();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error Initializing Database");
            e.printStackTrace();
        }
        prof = db2.findProfile("A0", "Doe");
        assert prof != null && prof.getFirstName().equals("Jane"): "Error Loading Information From Database After Saving";

        System.out.println("All test cases passed.");
    }
}
