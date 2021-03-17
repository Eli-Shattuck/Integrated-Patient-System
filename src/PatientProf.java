import java.io.Serializable;

public class PatientProf implements Serializable {
    private String adminID; // ID number of administrative personnel that created the profile.
    private String firstName; // Patient's first name
    private String lastName; // Patient's last name
    private String address; // Patient's home address
    private String phone; // Patient's home phone number
    private float coPay; // Co-pay associated with the insurance
    private String insuType; // Type of insurance, must be either "Private" or "Government"
    private String patientType; // Category of patient, must be either “Pediatric”, “Adult”, or “Senior”
    private MedCond medCondInfo; // object to hold medical condition information for this patient

    // creates a new PatientProf object from the given information
    public PatientProf(String adminID, String firstName, String lastName, String address, String phone, float coPay, String insuType, String patientType, MedCond medCondInfo) {
        this.adminID = adminID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.coPay = coPay;
        this.insuType = insuType;
        this.patientType = patientType;
        this.medCondInfo = medCondInfo;
    }

    //standard getters and setters for every field of PatientProf
    public String getAdminID() {
        return adminID;
    }

    public void updateAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void updateFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void updateLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void updateAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void updatePhone(String phone) {
        this.phone = phone;
    }

    public float getCoPay() {
        return coPay;
    }

    public void updateCoPay(float coPay) {
        this.coPay = coPay;
    }

    public String getInsuType() {
        return insuType;
    }

    public void updateInsuType(String insuType) {
        this.insuType = insuType;
    }

    public String getPatientType() {
        return patientType;
    }

    public void updatePatientType(String patientType) {
        this.patientType = patientType;
    }

    public MedCond getMedCondInfo() {
        return medCondInfo;
    }

    public void updateMedCondInfo(MedCond medCondInfo) {
        this.medCondInfo = medCondInfo;
    }

    // toString for printing
    @Override
    public String toString() {
        return "Patient Profile:\n" +
                "\tadmin ID = '"                + adminID                   + "',\n" +
                "\tfirst name = '"              + firstName                 + "',\n" +
                "\tlast name = '"               + lastName                  + "',\n" +
                "\taddress = '"                 + address                   + "',\n" +
                "\tphone = '"                   + phone                     + "',\n" +
                "\tcoPay = '"                   + coPay                     + "',\n" +
                "\tinsurance type = '"          + insuType                  + "',\n" +
                "\tpatient type = '"            + patientType               + "',\n" +
                "\tmedical condition info:"     + medCondInfo.toString()     + '\n';
    }
}
