public class PatientProfDB {
    private int numPatient;
    private int currentPatientIndex;
    private String fileName;
    private PatientProf[] patientList;

    public PatientProfDB(String fileName) {
        this.fileName = fileName;
    }

    public void insertNewProfile(PatientProf patientProf) {

    }

    public boolean deleteProfile(String adminID, String lastName) {
        return false;
    }

    public PatientProf findProfile(String adminID, String lastName) {
        return null;
    }

    public PatientProf findFirstProfile() {
        return null;
    }

    public PatientProf findNextProfile() {
        return null;
    }

    public void writeAllPatientProf() {

    }

    public void initializeDatabase() {

    }
}
