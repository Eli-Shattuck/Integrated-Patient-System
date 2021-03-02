import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Consumer;

public class Menu<K> extends ArrayList<MenuItem<K>>{
    public Menu() {
    }

    public void runSelectedItem(Scanner scanner, K input) {
        while(true) {
            for (MenuItem<K> mi : this) {
                System.out.println(mi);
            }
            System.out.print(">>> ");
            String in = scanner.nextLine();

            for (MenuItem<K> mi : this) {
                if (mi.getValidInputs().contains(in)) {
                    Consumer<K> toAccept = mi.getOnSelected();
                    if(toAccept != null) toAccept.accept(input);
                    return;
                }
            }
            System.err.println("Invalid Selection");
        }
    }
}
