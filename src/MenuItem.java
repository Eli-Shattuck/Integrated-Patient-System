import java.util.Set;
import java.util.function.Consumer;

public class MenuItem<K> {
    private final String display;
    private Set<String> validInputs;
    private Consumer<K> onSelected;

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
