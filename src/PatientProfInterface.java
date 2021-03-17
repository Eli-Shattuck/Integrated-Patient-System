import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.function.Consumer;
/*
 * Class to deal with all data and functionality related to allowing a human user
 * to use the Patient Profile System
 */

public class PatientProfInterface {
    private Scanner scanner; // To get input from user
    private PatientProfDB dataBase; // To store/recall information
    private Menu<Void> mainMenu; // main menu
    private Menu<PatientProf> foundPatientMenu; // menu to display after a patient has been searched for
    private Menu<Void> sureQuitMenu; // menu to display after a user has indicated that they wish to quit
    private Menu<Void> continueMenu; // menu to display after a user has saved the database
    private Menu<PatientProf> fieldToEditMenu; // menu to display all fields that can be edited by user for a specific patient

    public PatientProfInterface(String fileName) throws IOException, ClassNotFoundException {
        this.dataBase = new PatientProfDB(fileName); // create a database
        initDB(); // initialize it
        scanner = new Scanner(System.in); // create a scanner

        /* setup all menus */

        sureQuitMenu = new Menu<>();
        // If yes is selected print goodbye and exit
        MenuItem<Void> yes = new MenuItem<>("1. YES.",    new HashSet<>(Arrays.asList("1", "YES")), (in)->{System.out.println("Goodbye!"); System.exit(0);});
        // If no is selected do nothing
        MenuItem<Void> no  = new MenuItem<>("2. NO.",     new HashSet<>(Arrays.asList("2", "NO")),  null);
        sureQuitMenu.addAll(Arrays.asList(yes, no));

        continueMenu = new Menu<>();
        // If yes is selected do nothing
        yes = new MenuItem<>("1. YES.",    new HashSet<>(Arrays.asList("1", "YES")),null);
        // If no is selected print goodbye and exit
        no  = new MenuItem<>("2. NO.",     new HashSet<>(Arrays.asList("2", "NO")), (in)->{System.out.println("Goodbye!"); System.exit(0);});
        continueMenu.addAll(Arrays.asList(yes, no));

        foundPatientMenu = new Menu<>();
        // If delete is selected call the deletePatientProf method
        MenuItem<PatientProf> delete     = new MenuItem<>("1. DELETE this profile.",    new HashSet<>(Arrays.asList("1", "DELETE")), this::deletePatientProf);
        // If edit is selected call the updatePatientProf method
        MenuItem<PatientProf> edit       = new MenuItem<>("2. EDIT this profile.",      new HashSet<>(Arrays.asList("2", "EDIT")), this::updatePatientProf);
        // If return is selected do nothing
        MenuItem<PatientProf> returnTo   = new MenuItem<>("3. RETURN to main menu.",    new HashSet<>(Arrays.asList("3", "RETURN")), null);
        foundPatientMenu.addAll(Arrays.asList(delete, edit, returnTo));

        fieldToEditMenu = new Menu<>();
        // If adminID is selected call the updateFieldInPatientProfile,
        // with the string adminID to prompt the user and the method in PatientProf to update the adminID field
        MenuItem<PatientProf> admin     = new MenuItem<>("1. ADMIN id.",                        new HashSet<>(Arrays.asList("1", "ADMIN")),      pat -> updateFieldInPatientProfile("adminID",                      pat::updateAdminID));

        // If first name is selected call the updateFieldInPatientProfile,
        // with the string first name to prompt the user and the method in PatientProf to update the first name field
        MenuItem<PatientProf> first     = new MenuItem<>("2. FIRST name.",                      new HashSet<>(Arrays.asList("2", "FIRST")),      pat -> updateFieldInPatientProfile("first name",                   pat::updateFirstName));

        // If last name is selected call the updateFieldInPatientProfile,
        // with the string last name to prompt the user and the method in PatientProf to update the last name field
        MenuItem<PatientProf> last      = new MenuItem<>("3. LAST name.",                       new HashSet<>(Arrays.asList("3", "LAST")),       pat -> updateFieldInPatientProfile("last name",                    pat::updateLastName));

        // If address is selected call the updateFieldInPatientProfile,
        // with the string address to prompt the user and the method in PatientProf to update the address field
        MenuItem<PatientProf> address   = new MenuItem<>("4. ADDRESS.",                         new HashSet<>(Arrays.asList("4", "ADDRESS")),    pat -> updateFieldInPatientProfile("address",                      pat::updateAddress));

        // If phone number is selected call the updateFieldInPatientProfile,
        // with the string phone number to prompt the user and the method in PatientProf to update the phone number field
        MenuItem<PatientProf> phone     = new MenuItem<>("5. PHONE number.",                    new HashSet<>(Arrays.asList("5", "PHONE")),      pat -> updateFieldInPatientProfile("phone number",                 pat::updatePhone));

        // If coPay is selected call the updateFieldInPatientProfile,
        // with the string coPay to prompt the user and the method in PatientProf to update the coPay field, converting the user response to a float
        MenuItem<PatientProf> coPay     = new MenuItem<>("6. COPAY.",                           new HashSet<>(Arrays.asList("6", "COPAY")),      pat -> updateFieldInPatientProfile("coPay",                        (s) -> pat.updateCoPay(Float.parseFloat(s))));

        // If adminID is selected call the updateFieldInPatientProfile,
        // with the string adminID to prompt the user and the method in PatientProf to update the adminID field
        MenuItem<PatientProf> insuType  = new MenuItem<>("7. INSURANCE type.",                  new HashSet<>(Arrays.asList("7", "INSURANCE")),  pat -> updateFieldInPatientProfile("insurance type",               pat::updateInsuType));

        // If insurance type is selected call the updateFieldInPatientProfile,
        // with the string insurance type to prompt the user and the method in PatientProf to update the insurance type field
        MenuItem<PatientProf> patType   = new MenuItem<>("8. PATIENT type.",                    new HashSet<>(Arrays.asList("8", "PATIENT")),    pat -> updateFieldInPatientProfile("patient type",                 pat::updatePatientType));

        // If physician's name is selected call the updateFieldInPatientProfile,
        // with the string physician's name to prompt the user and the method in PatientProf.medCondInfo to update the physician's name field
        MenuItem<PatientProf> mdContact = new MenuItem<>("9. PHYSICIAN’s name.",                new HashSet<>(Arrays.asList("9", "PHYSICIAN")),  pat -> updateFieldInPatientProfile("physician's name",             pat.getMedCondInfo()::setMdContact));

        // If medical contact phone number is selected call the updateFieldInPatientProfile,
        // with the string medical contact phone number to prompt the user and the method in PatientProf.medCondInfo to update the medical contact phone number field
        MenuItem<PatientProf> mdPhone   = new MenuItem<>("A. medical CONTACT phone number.",    new HashSet<>(Arrays.asList("A", "CONTACT")),    pat -> updateFieldInPatientProfile("medical contact phone number", pat.getMedCondInfo()::setMdPhone));

        // If allergy type is selected call the updateFieldInPatientProfile,
        // with the string allergy type to prompt the user and the method in PatientProf.medCondInfo to update the allergy type field
        MenuItem<PatientProf> algType   = new MenuItem<>("B. ALLERGY type.",                    new HashSet<>(Arrays.asList("B", "ALLERGY")),    pat -> updateFieldInPatientProfile("allergy type",                 pat.getMedCondInfo()::setAlgType));

        // If illness type is selected call the updateFieldInPatientProfile,
        // with the string illness type to prompt the user and the method in PatientProf.medCondInfo to update the illness type field
        MenuItem<PatientProf> illType   = new MenuItem<>("C. ILLNESS type.",                    new HashSet<>(Arrays.asList("C", "ILLNESS")),    pat -> updateFieldInPatientProfile("illness type",                 pat.getMedCondInfo()::setIllType));
        fieldToEditMenu.addAll(Arrays.asList(admin, first, last, address, phone, coPay, insuType, patType, mdContact, mdPhone, algType, illType));

        mainMenu = new Menu<>();
        // if find is selected run the findPatientProf method
        MenuItem<Void> find       = new MenuItem<>("1. FIND profile.",           new HashSet<>(Arrays.asList("1", "FIND")),   (in)->findPatientProf());
        // if find is selected run the addPatientProf method
        MenuItem<Void> add        = new MenuItem<>("2. ADD profile.",            new HashSet<>(Arrays.asList("2", "ADD")),    (in)->addPatientProf());
        // if find is selected run the displayAllPatientProf method
        MenuItem<Void> displayAll = new MenuItem<>("3. DISPLAY all profiles.",   new HashSet<>(Arrays.asList("3", "DISPLAY")), (in)->displayAllPatientProf());
        // if find is selected run the displayAllPatientProf method
        MenuItem<Void> save       = new MenuItem<>("4. SAVE changes.",           new HashSet<>(Arrays.asList("4", "SAVE")),   (in)->writeToDB());
        // if find is selected run the findPatientProf method
        MenuItem<Void> quit       = new MenuItem<>("5. QUIT without saving.",    new HashSet<>(Arrays.asList("5", "QUIT")),   (in)->quit());
        mainMenu.addAll(Arrays.asList(find,add,displayAll,save,quit));
    }

    // function that is called when user asks to quit
    private void quit() {
        // prompt the user to ask if they want to quit
        System.out.println("Are you sure you want to quit? All unsaved progress will be lost.");
        // display the save and quit menu, and take the appropriate action based on the user input
        sureQuitMenu.runSelectedItem(scanner, null);
    }

    // function that displays the main menu and calls the appropriate function based on the users response
    public void getUserChoice() {
        // display the main menu, and take the appropriate action based on the user input
        mainMenu.runSelectedItem(scanner, null);
    }

    // function that is called when user asks to delete a profile
    private void deletePatientProf(PatientProf toDelete) {
        // Attempt to delete the selected profile
        if(dataBase.deleteProfile(toDelete.getAdminID(), toDelete.getLastName())) {
            System.out.println("Profile deleted."); // if deleted report the user
        } else {
            System.err.println("Error deleting profile, try again."); // if not deleted report error to the user
        }
    }

    // function that is called when user requests to search for a patient
    private void findPatientProf() {
        // create a null profile
        PatientProf found = null;

        // prompt the user to enter info about the patient they are searching for
        System.out.print("Enter patient last name.\n>>> ");
        String name = scanner.nextLine();
        System.out.print("Enter ID of admin that generated the profile.\n>>> ");
        String adminID = scanner.nextLine();

        // search the database for the selected profile
        found = dataBase.findProfile(adminID, name);

        // if profile isn't found report error to the user
        if(found == null) {
            System.err.println("invalid information");
            return;
        }

        // otherwise report the found profile to the user
        System.out.println("Profile Found.");
        System.out.println(found);

        // display the found patient menu, and take the appropriate action based on the user input
        foundPatientMenu.runSelectedItem(scanner, found);
    }
    // function that is called when user asks to update a profile
    private void updatePatientProf(PatientProf toUpdate) {
        // display the field to edit menu, and edit the appropriate field based on the user input
        fieldToEditMenu.runSelectedItem(scanner, toUpdate);
    }

    // function that is called when user asks to update a profile
    private void addPatientProf() {
        // create an empty patient profile
        PatientProf patientProf = createNewPatientProf();
        // reuse the consumers defined in the fieldToEditMenu,
        // that ask the user for a field and then update that field for a specific profile
        for(MenuItem<PatientProf> mi : fieldToEditMenu){
            mi.getOnSelected().accept(patientProf); // give every field in patientProf a value
        }
        dataBase.insertNewProfile(patientProf); // add the new patient to the database
    }

    private void displayPatientProf(PatientProf patientProf) {
        System.out.println(patientProf); // print a patient using the PatientProf type's toString method
    }

    private void displayAllPatientProf() {
        dataBase.resetCurrentPatientIndex(); // reset the index in the database to 0
        for (int i = 0; i < dataBase.getNumPatient(); i++) {
            PatientProf patientProf = dataBase.findNextProfile();
            displayPatientProf(patientProf); // for each profile display it
        }
    }

    private void writeToDB() {
        try {
            dataBase.writeAllPatientProf(); // attempt the write the database to disk
            System.out.println("Session saved successfully, continue working?"); // if successful report the user
            // ask the user if they would like to continue
            continueMenu.runSelectedItem(scanner, null);
        } catch (IOException e) {
            // if thee write fails report the error to the user
            System.err.println("Error saving database, try again.");
        }
    }

    // initialized the database (must be called after database is created)
    private void initDB() throws IOException, ClassNotFoundException {
        this.dataBase.initializeDatabase(); // initialize the database
    }

    // create and return a PatientProfile with all fields empty (null or 0),
    // except medCond which is initialized, with all of it's fields empty
    private PatientProf createNewPatientProf() {
        return new PatientProf(null,null,null,null,null,0.0f,null,null, createNewMedCond());
    }

    // create and return a MedCond with all fields empty (null),
    private MedCond createNewMedCond() {
        return new MedCond(null, null, null, null);
    }
    // takes in a String fieldName, which is used to tell the user what information to enter,
    // and a Consumer that takes in the String from the user and then updates tha appropriate field in the selected patient profile
    private void updateFieldInPatientProfile(String fieldName, Consumer<String> fieldUpdater){
        System.out.printf("Enter %s\n>>>", fieldName);
        fieldUpdater.accept(scanner.nextLine());
    }
}
