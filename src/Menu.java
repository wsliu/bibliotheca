import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: twer
 * Date: 9/18/11
 * Time: 9:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class Menu {
    Vector<String> menuOptions = new Vector<String>();;
    public Menu() {
        addOption("View all books");
        addOption("Reserve a book");
        addOption("Check library number");
        addOption("View all movies");
    }
    public void addOption(String optionName){
        menuOptions.add(optionName);
    }

    public int size() {
        return menuOptions.size();
    }

    public String optionAt(int index) {
        return menuOptions.elementAt(index);
    }

    public int indexOf(String optionName) {
        return menuOptions.indexOf(optionName);
    }
}
