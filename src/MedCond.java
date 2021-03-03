import java.io.Serializable;

public class MedCond implements Serializable {
    private String mdContact; // Name of medical contact for patient (physician’s name)
    private String mdPhone; // Phone number of medical contact
    private String algType; // Known allergies, must select from: none, food, medication, other
    private String illType; // Known illnesses, must select from: none, CHD, diabetes, asthma, other

    public MedCond(String mdContact, String mdPhone, String algType, String illType) {
        this.mdContact = mdContact;
        this.mdPhone = mdPhone;
        this.algType = algType;
        this.illType = illType;
    }

    public String updateMdContact() {
        return mdContact;
    }

    public void setMdContact(String mdContact) {
        this.mdContact = mdContact;
    }

    public String updateMdPhone() {
        return mdPhone;
    }

    public void setMdPhone(String mdPhone) {
        this.mdPhone = mdPhone;
    }

    public String updateAlgType() {
        return algType;
    }

    public void setAlgType(String algType) {
        this.algType = algType;
    }

    public String updateIllType() {
        return illType;
    }

    public void setIllType(String illType) {
        this.illType = illType;
    }

    @Override
    public String toString() {
        return "MedCond{\n" +
                "\t\tmdContact='"   + mdContact   + "',\n" +
                "\t\tmdPhone='"     + mdPhone     + "',\n" +
                "\t\talgType='"     + algType     + "',\n" +
                "\t\tillType='"     + illType     + "',\n" +
                "\t}";
    }
}
