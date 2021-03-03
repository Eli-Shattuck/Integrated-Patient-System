import java.io.IOException;

/*
 * main class to drive the system
 */
public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //create a Patient Profile Interface
        PatientProfInterface ppi = new PatientProfInterface("testDBFile.txt");
        // continually ask user for input (the ppi can halt the program, so the while is not an infinte loop)
        while(true) {
            ppi.getUserChoice();
        }
    }
}
