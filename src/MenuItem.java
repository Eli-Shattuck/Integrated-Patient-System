import java.util.Set;
import java.util.function.Consumer;

/*
 * Class to store the information associated with a menu item, along with
 * a Consumer function of generic type 'K' which indicates what to do when that
 * item is selected
 */

public class MenuItem<K> {
    private final String display; //text to be displayed for this item
    private Set<String> validInputs; //all inputs that correlate to this item being selected
    private Consumer<K> onSelected; //function to be run when selected

    public MenuItem(String display, Set<String> validInputs, Consumer<K> onSelected) {
        this.display = display;
        this.validInputs = validInputs;
        this.onSelected = onSelected;
    }

    public Consumer<K> getOnSelected() {
        return onSelected;
    }

    public void setOnSelected(Consumer<K> onSelected) {
        this.onSelected = onSelected;
    }

    @Override
    public String toString() {
        return display;
    }

    public Set<String> getValidInputs() {
        return validInputs;
    }
}
