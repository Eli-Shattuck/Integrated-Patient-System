import java.io.File;
import java.io.IOException;
import java.util.Scanner;
/*
 * class used to convert a plaintext database file
 * into the standard serialized list format that
 * is used by the PatientProfDB class
 *
 * Each line of the plaintext file is the next attribute of a PatientProf object.
 */
public class ConvertTextDBToSerializedDB {
    public static void main(String[] args) throws IOException {
        File dbFile = new File("database.txt"); //read in the plaintext file
        Scanner input = new Scanner(dbFile); //create a scanner from the file

        PatientProfDB db = new PatientProfDB("databaseSerialize.txt"); //create a PatientProfileDB which can write files of the correct type

        while(input.hasNext()) { //as long as there are more lines in the file
            PatientProf prof = new PatientProf( //generate a new PatientProf from the data in the file
                    input.nextLine(),
                    input.nextLine(),
                    input.nextLine(),
                    input.nextLine(),
                    input.nextLine(),
                    Float.parseFloat(input.nextLine()), //the 6th attribute is the float coPAy, so this attribute must be parsed as a float not as a string
                    input.nextLine(),
                    input.nextLine(),
                    new MedCond(
                            input.nextLine(),
                            input.nextLine(),
                            input.nextLine(),
                            input.nextLine()
                    )
            );
            db.insertNewProfile(prof); //add the profile to the database
        }

        db.writeAllPatientProf(); //write the database to a file
    }
}
