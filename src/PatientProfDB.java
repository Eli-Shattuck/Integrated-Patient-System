public class PatientProfDB {
    private int numPatient;
    private int currentPatientIndex;
    private String fileName;
    private PatientProf[] patientList;

    /*
     * This is a constructor method which accepts the name of the file in which the
     * patient profiles will be stored. Thus, for the IPS system in its current version, the database of
     * PatientProfs is implemented using a file.
     */
    public PatientProfDB(String fileName) {
        this.fileName = fileName;
    }

    /*
     * This method accepts a PatientProf as input and inserts it into the array patientList.
     */
    public void insertNewProfile(PatientProf patientProf) {

    }

    /*
     * This method accepts the adminID and lastName as inputs and deletes the
     * corresponding patient profile. It returns a Boolean value to indicate whether the delete operation was
     * successful.
     */
    public boolean deleteProfile(String adminID, String lastName) {
        return false;
    }

    /*
     * This method accepts the adminID and lastName as inputs and returns the
     * corresponding patient profile as output. For the purpose of this project, assume that adminID and
     * lastName uniquely identify a patient.
     */
    public PatientProf findProfile(String adminID, String lastName) {
        return null;
    }

    public PatientProf findFirstProfile() {
        return null;
    }

    public PatientProf findNextProfile() {
        return null;
    }

    /*
     * This method writes all PatientProf stored in the array
     * patientList to a file.
     */
    public void writeAllPatientProf() {

    }

    /*
     * This method is used to read in the existing patient profiles placed in the file.
     */
    public void initializeDatabase() {

    }
}
