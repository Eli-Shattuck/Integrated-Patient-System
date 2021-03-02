public class Main {
    public static void main(String[] args) {
        PatientProfInterface ppi = new PatientProfInterface("dbFile.txt");
        while(true) {
            ppi.getUserChoice();
        }
    }
}
