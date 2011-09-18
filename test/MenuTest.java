import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class MenuTest {
    Menu menu;
    @Before
    public void initialize(){
        menu = new Menu();
    }

    @Test
    public void testSize(){
        assertEquals(5, menu.size());
    }

    @Test
    public void testIndexOf(){
        assertEquals(1,menu.indexOf("View all books"));
    }

    @Test
    public void testOptionAt(){
        assertEquals("View all books", menu.optionAt(1));
    }

    @Test
    public void testAddOption(){
        int oldSize = menu.size();
        menu.addOption("The new option");

        assertEquals(oldSize+1, menu.size());
        assertEquals("The new option", menu.optionAt(menu.size()));

    }
}
