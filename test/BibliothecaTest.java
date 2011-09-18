import org.junit.Before;
import org.junit.Test;
import resource.Book;
import resource.Resource;

import java.io.*;
import java.util.Vector;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;


public class BibliothecaTest {
    ByteArrayOutputStream consoleOutput;
    PrintStream output;
    Bibliotheca bibliotheca;
    BufferedReader input;
    Vector<Resource> bookLib;
    Vector<Resource> movieLib;

    @Before
    public void initialize(){
        consoleOutput = new ByteArrayOutputStream();
        output = new PrintStream(consoleOutput);

        bibliotheca = new Bibliotheca();
        bookLib = bibliotheca.resourceHolder.bookLib;
        movieLib = bibliotheca.resourceHolder.movieLib;
    }

    @Test
    public void shouldReturnWelcome()throws IOException{
        bibliotheca.start(output);
        assertThat(consoleOutput.toString(), is("Welcome to The Bangalore Public Library System - Bibliotheca !\n"));
    }

    @Test
    public void shouldReturnMenuTitleWithNoOptions() throws IOException{
        bibliotheca.showMenu(output);
        assertThat(consoleOutput.toString(), is("PLEASE TYPE THE INDEX OF THE OPTION IN THE MENU :\n"));
    }

    @Test
    public void shouldReturnMenuOneOptions()throws IOException{
        bibliotheca.showMenuOptions(output);
        assertThat(consoleOutput.toString(), is(expectMenu()));
    }


    @Test
    public void shouldReturnInfoSelectedCheckingLibNumWithoutLogin()throws IOException{
        selectMenuOption("Check library number");
        assertThat(consoleOutput.toString(), is("Please talk to Librarian. Thank you.\n"));

    }

    @Test
    public void shouldReturnInfoSelectedCheckingLibNumLogedIn()throws IOException{
        input = new BufferedReader(new StringReader("user1\npassword1\n"));
        bibliotheca.selsectLogin(input, output);
        selectMenuOption("Check library number");
        assertThat(consoleOutput.toString(), containsString("111-1112\n"));

    }

    @Test
    public void shouldReturnHintSelectedLoginOption() throws IOException {
        selectMenuOption("Login");
        assertThat(consoleOutput.toString(), containsString("Please enter your username:\n"));
    }

    @Test
    public void shouldReturnSuccessGivenRightPassword() throws IOException {
        input = new BufferedReader(new StringReader("user1\npassword1\n"));
        bibliotheca.selsectLogin(input, output);
        assertThat(consoleOutput.toString(), containsString("Please enter your password:\n"));
        assertThat(consoleOutput.toString(), containsString("Login Successfully!"));
    }

    @Test
    public void shouldReturnFailGivenWrongPassword() throws IOException {
        input = new BufferedReader(new StringReader("user1\nwrongpassword\n"));
        bibliotheca.selsectLogin(input, output);
        assertThat(consoleOutput.toString(), containsString("Please enter your password:\n"));
        assertThat(consoleOutput.toString(), containsString("Login failed!"));
    }

    @Test
    public void shouldReturnWarningSelectedValidOption()throws IOException{
        selectMenuOption("invalid select");
        assertThat(consoleOutput.toString(), is("Select a valid option!!\n"));
    }


    @Test
    public void shouldReturnBooksSelectedViewingBooks()throws IOException{
        selectMenuOption("View all books");
        assertThat(consoleOutput.toString(), is(bibliotheca.resourceHolder.expectResource(bookLib)));
    }

    @Test
    public void shouldReturnBooksListSelectedReservedBooks()throws IOException{
        selectMenuOption("Reserve a book");
        assertThat(consoleOutput.toString(), is("Please choose the index of the book you want:\n" + bibliotheca.resourceHolder.expectResource(bookLib)));
    }
    @Test
    public void shouldReturnMoviesListSelectedViewAllMovies() throws IOException {
        selectMenuOption("View all movies");
        assertThat(consoleOutput.toString(), is("Index Name - director - rating:\n" + bibliotheca.resourceHolder.expectResource(movieLib)));
    }

    @Test
    public void shouldReturnThanksReservedBooksSuccessfully() throws IOException {
        Book book = (Book)bookLib.elementAt(1);
        book.setReserved(false);
        input = new BufferedReader(new StringReader("2"));
        bibliotheca.reserveBook(input, output);

        assertThat(consoleOutput.toString(), is("Thank You! Enjoy the book.\n"));
        assertEquals(true, book.isReserved());
    }

    @Test
    public void shouldReturnSorryReservedBooksUnsuccessfully() throws IOException {
        ((Book)bookLib.elementAt(2)).setReserved(true);
        input = new BufferedReader(new StringReader("3"));
        bibliotheca.reserveBook(input, output);

        assertThat(consoleOutput.toString(), is("Sorry we don't have that book yet.\n"));
    }

    public void selectMenuOption(String optionName) throws IOException {
        input = new BufferedReader(new StringReader(String.valueOf(bibliotheca.menu.indexOf(optionName))));
        bibliotheca.selectMenuOptions(input, output);
    }

    public String expectMenu(){
        String expectMenus = "";
        for(int index = 1; index <= bibliotheca.menu.size(); index++)
            expectMenus +=(index) + ". " +  bibliotheca.menu.optionAt(index) + "\n";
        return expectMenus;
    }
}
