import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Consumer;

/*
 * Class to store the information associated with a menu
 * A menu in this case is a List that can only hold MenuItems
 */

public class Menu<K> extends ArrayList<MenuItem<K>>{
    public Menu() {
    }

    /*
     * Method that looks through all menu items and if one of them is selected via
     * the provided scanner, that items corresponding consumer is run
     */
    public void runSelectedItem(Scanner scanner, K input) {
        while(true) { // loop until scanner returns a valid selection
            for (MenuItem<K> mi : this) {
                System.out.println(mi); // print out all option for user to pick from
            }
            System.out.print(">>> "); // angle brackets to indicate the user can type
            String in = scanner.nextLine(); // get input from user

            for (MenuItem<K> mi : this) { //check every menu item
                if (mi.getValidInputs().contains(in)) { //if the provided input is a valid input for a menu item
                    Consumer<K> toAccept = mi.getOnSelected();
                    if(toAccept != null) toAccept.accept(input); //run that menu items associated function
                    return; // leave loop
                }
            }
            System.err.println("Invalid Selection"); //report to user that they made a mistake
        }
    }
}
