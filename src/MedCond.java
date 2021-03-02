public class MedCond {
    private String mdContact;
    private String mdPhone;
    private String algType;
    private String illType;

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
}
