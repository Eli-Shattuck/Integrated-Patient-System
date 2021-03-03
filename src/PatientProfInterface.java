import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/*
 * Class to deal with all data and functionality related to allowing a human user
 * to use the Patient Profile System
 */

public class PatientProfInterface {
    private Scanner scanner; //To get input from user
    private PatientProfDB dataBase; //To store/recall information
    private Menu<Void> mainMenu; //main menu
    private Menu<PatientProf> foundPatientMenu; //menu to display after a patient has been searched for
    private Menu<Void> sureQuit; //menu to display after a user has indicated that they wish to quit

    public PatientProfInterface(String fileName) throws IOException, ClassNotFoundException {
        this.dataBase = new PatientProfDB(fileName); //create a database
        initDB(); //initialize it
        scanner = new Scanner(System.in); //create a scanner

        //setup all menus
        //TODO create a new MenuSystem type to make this less messy?
        sureQuit = new Menu<>();
        MenuItem<Void> yes = new MenuItem<>("1. YES.",    new HashSet<>(Arrays.asList("1", "Y", "YES")), (in)->{System.out.println("Goodbye!"); System.exit(0);});
        MenuItem<Void> no  = new MenuItem<>("2. NO.",     new HashSet<>(Arrays.asList("2", "N", "NO")),  null);
        sureQuit.addAll(Arrays.asList(yes, no));

        foundPatientMenu = new Menu<>();
        MenuItem<PatientProf> delete     = new MenuItem<>("1. DELETE this profile.",    new HashSet<>(Arrays.asList("1", "D", "DELETE")), (found)->deletePatientProf(found));
        MenuItem<PatientProf> edit       = new MenuItem<>("2. EDIT this profile.",      new HashSet<>(Arrays.asList("2", "E", "EDIT")),   (found)->updatePatientProf(found));
        MenuItem<PatientProf> returnTo   = new MenuItem<>("3. RETURN to main menu.",    new HashSet<>(Arrays.asList("3", "R", "RETURN")), null);
        foundPatientMenu.addAll(Arrays.asList(delete, edit, returnTo));

        mainMenu = new Menu<>();
        MenuItem<Void> find       = new MenuItem<>("1. FIND profile.",           new HashSet<>(Arrays.asList("1", "F", "FIND")),   (in)->findPatientProf());
        MenuItem<Void> add        = new MenuItem<>("2. ADD profile.",            new HashSet<>(Arrays.asList("2", "A", "ADD")),    null);
        MenuItem<Void> save       = new MenuItem<>("4. SAVE changes.",           new HashSet<>(Arrays.asList("4", "S", "SAVE")),   null);
        MenuItem<Void> displayAll = new MenuItem<>("3. DISPLAY all profiles.",   new HashSet<>(Arrays.asList("3", "D", "DISPLAY")),null);
        MenuItem<Void> quit       = new MenuItem<>("5. QUIT without saving.",    new HashSet<>(Arrays.asList("5", "Q", "QUIT")),   (in)->quit());
        mainMenu.addAll(Arrays.asList(find,add,displayAll,save,quit));
    }

    //function that is called when user asks to quit
    private void quit() {
        System.out.println("Are you sure you want to quit? All unsaved progress will be lost.");
        sureQuit.runSelectedItem(scanner, null);
    }

    //function that displays the main menu and calls the appropriate function based on the users response
    public void getUserChoice() {
        mainMenu.runSelectedItem(scanner, null);
    }

    //function that is called when user asks to delete a profile
    public void deletePatientProf(PatientProf toDelete) {
        System.out.println("Profile deleted.");
    }

    //function that is called when user requests to search for a patient
    public void findPatientProf() {
        PatientProf found = null;

        System.out.print("Enter patient last name.\n>>> ");
        String name = scanner.nextLine();
        System.out.print("Enter ID of admin that generated the profile.\n>>> ");
        String adminID = scanner.nextLine();

        found = dataBase.findProfile(adminID, name);

        System.out.println("Profile Found.");
        System.out.println(found);

        foundPatientMenu.runSelectedItem(scanner, found);
    }
    //function that is called when user asks to update a profile
    public void updatePatientProf(PatientProf toUpdate) {

    }

    public void displayPatientProf() {

    }

    public void displayAllPatientProf() {

    }

    public void writeToDB() {

    }

    //initialized the database (must be called after database is created)
    public void initDB() throws IOException, ClassNotFoundException {
        this.dataBase.initializeDatabase();
    }

    public PatientProf createNewPatientProf() {
        return null;
    }

    public MedCond createNewMedCond() {
        return null;
    }
}
