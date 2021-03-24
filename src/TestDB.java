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
        // If the file testing.txt exists, delete it
        File testFile = new File("testing.txt");
        testFile.delete();

        // call the generate method of GenerateTestData to create a testfile
        try {
            GenerateTestData.generate("testing.txt");
        } catch (IOException e) {
            System.err.println("Error Generating Testing Data:");
            e.printStackTrace();
            return;
        }

        // create a database that reads from that file
        PatientProfDB db = new PatientProfDB("testing.txt");
        try {
            db.initializeDatabase();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error Initializing Database");
            e.printStackTrace();
            return;
        }

        // use assert to make sure that profiles that are supposed to be in the database can be found
        PatientProf prof = db.findProfile(GenerateTestData.ADMIN_IDS[0], GenerateTestData.LAST_NAMES[0]);
        assert prof != null : "Error Finding Profile";

        // use assert to make sure that the first profile is returned by the find first method
        prof = db.findFirstProfile();
        assert prof.getLastName().equals(GenerateTestData.LAST_NAMES[0]): "Find Next Profile Not Returning First Profile";

        // use assert to make sure that the first profile is returned when find next is called for the first time
        prof = db.findNextProfile();
        assert prof.getLastName().equals(GenerateTestData.LAST_NAMES[0]): "Find Next Profile Not Returning First Profile";

        // use assert to make sure that the second profile is returned when find next is called again
        prof = db.findNextProfile();
        assert prof.getLastName().equals(GenerateTestData.LAST_NAMES[1]): "Find Next Profile Not Returning Next Profile";

        // use assert to make sure that the first profile is returned after the patient index is returned
        db.resetCurrentPatientIndex();
        prof = db.findNextProfile();
        assert prof.getLastName().equals(GenerateTestData.LAST_NAMES[0]): "Resting DatabaseIndex Failed";

        // use assert to make sure that profiles can be added to the database
        db.insertNewProfile(new PatientProf("A0", "Jane", "Doe", null, null, 0.0f, null, null, null));
        prof = db.findProfile("A0", "Doe");
        assert prof != null && prof.getFirstName().equals("Jane"): "Error Inserting New Profile";

        // use assert to make sure that profiles can be deleted from the database
        db.deleteProfile(GenerateTestData.ADMIN_IDS[2], GenerateTestData.LAST_NAMES[2]);
        prof = db.findProfile(GenerateTestData.ADMIN_IDS[2], GenerateTestData.LAST_NAMES[2]);
        assert prof == null : "Error Removing Profile";

        // use try catch to make sure that the database can be written to
        try {
            db.writeAllPatientProf();
        } catch (IOException e) {
            System.err.println("Error Saving Database");
            e.printStackTrace();
            return;
        }

        // create a new database object from the recently saved file
        PatientProfDB db2 = new PatientProfDB("testing.txt");
        try {
            db2.initializeDatabase();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error Initializing Database");
            e.printStackTrace();
            return;
        }

        // use assert to make sure that profiles that are added remain after a save
        prof = db2.findProfile("A0", "Doe");
        assert prof != null && prof.getFirstName().equals("Jane"): "Error Loading Information From Database After Saving";

        //if all asserts pass then report success to user
        System.out.println("All test cases passed.");
    }
}
