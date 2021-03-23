import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ConvertTextDBToSerializedDB {
    public static void main(String[] args) throws IOException {
        File dbFile = new File("database.txt");
        Scanner input = new Scanner(dbFile);

        PatientProfDB db = new PatientProfDB("databaseSerialize.txt");

        while(input.hasNext()) {
            PatientProf prof = new PatientProf(
                    input.nextLine(),
                    input.nextLine(),
                    input.nextLine(),
                    input.nextLine(),
                    input.nextLine(),
                    Float.parseFloat(input.nextLine()),
                    input.nextLine(),
                    input.nextLine(),
                    new MedCond(
                            input.nextLine(),
                            input.nextLine(),
                            input.nextLine(),
                            input.nextLine()
                    )
            );
            db.insertNewProfile(prof);
        }

        db.writeAllPatientProf();
    }
}
