import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class PatientProfDB implements Serializable {
    private int numPatient;
    private int currentPatientIndex;
    private String fileName;
    private ArrayList<PatientProf> patientList;

    /*
     * This is a constructor method which accepts the name of the file in which the
     * patient profiles will be stored. Thus, for the IPS system in its current version, the database of
     * PatientProfs is implemented using a file.
     */
    /* note:
    Using ArrayList because it's easier to add/delete items...
     */
    public PatientProfDB(String fileName) {
        this.fileName = fileName;
        this.numPatient = 0;
        this.currentPatientIndex = 0;
        this.patientList = new ArrayList<PatientProf>();
    }

    /*
     * This method accepts a PatientProf as input and inserts it into the arrayList patientList.
     */
    public void insertNewProfile(PatientProf patientProf) {
        this.patientList.add(patientProf);
        this.numPatient++;
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
        int j = -1;
        for(int i = 0; i < this.patientList.size();i++){
            PatientProf searchCandidate  = this.patientList.get(i);
            if(searchCandidate.getAdminID().equals(adminID) && searchCandidate.getLastName().equals(lastName)){
                j = i;
                break;
            }
        }
        if(j == -1) return null;
        return this.patientList.get(j);
    }

    public PatientProf findFirstProfile() {
        return null;
    }

    public PatientProf findNextProfile() {
        return null;
    }

    /*
     * This method writes all PatientProf stored in the arraylist
     * patientList to a file.
     */
    public void writeAllPatientProf() throws IOException{
        FileOutputStream outputStream = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(patientList); //Write entire arraylist to file
        objectOutputStream.close();
    }

    /*
     * This method is used to read in the existing patient profiles placed in the file.
     */
    public void initializeDatabase() throws IOException, ClassNotFoundException {
        FileInputStream inputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        this.patientList = (ArrayList<PatientProf>)objectInputStream.readObject();
    }
}
