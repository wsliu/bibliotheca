import java.util.Vector;

public class Menu {
    Vector<String> menuOptions = new Vector<String>();
    public Menu() {
        addOption("View all books");
        addOption("Reserve a book");
        addOption("Check library number");
        addOption("View all movies");
        addOption("Login");
    }
    public void addOption(String optionName){
        menuOptions.add(optionName);
    }

    public int size() {
        return menuOptions.size();
    }

    public String optionAt(int index) {
        return menuOptions.elementAt(index - 1);
    }

    public int indexOf(String optionName) {
        return menuOptions.indexOf(optionName) + 1;
    }
}
