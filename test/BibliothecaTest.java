import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class BibliothecaTest {
    ByteArrayOutputStream consoleOutput;
    PrintStream output;
    Bibliotheca bibliotheca;
    BufferedReader input;
    @Before
    public void initialize(){
        consoleOutput = new ByteArrayOutputStream();
        output = new PrintStream(consoleOutput);

        bibliotheca = new Bibliotheca();
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
    public void shouldReturnInfoSelectedCheckingLibNum()throws IOException{
        selectMenuOption("Check library number");
        assertThat(consoleOutput.toString(), is("Please talk to Librarian. Thank you.\n"));

    }

    @Test
    public void shouldReturnWarningSelectedValidOption()throws IOException{
        selectMenuOption("invalid select");
        assertThat(consoleOutput.toString(), is("Select a valid option!!\n"));
    }


    @Test
    public void shouldReturnBooksSelectedViewingBooks()throws IOException{
        selectMenuOption("View all books");
        assertThat(consoleOutput.toString(), is(expectBooks()));
    }

    @Test
    public void shouldReturnBooksListSelectedReservedBooks()throws IOException{
        selectMenuOption("Reserve a book");
        assertThat(consoleOutput.toString(), is("Please choose the index of the book you want:\n" + expectBooks()));
    }
    @Test
    public void shouldReturnMoviesListSelectedViewAllMovies() throws IOException {
        selectMenuOption("View all movies");
        assertThat(consoleOutput.toString(), is("movie\n"));
    }

    @Test
    public void shouldReturnThanksReservedBooksSuccessfully() throws IOException {
        bibliotheca.books.elementAt(1).setReserved(false);
        input = new BufferedReader(new StringReader("2"));
        bibliotheca.reserveBook(input, output);

        assertThat(consoleOutput.toString(), is("Thank You! Enjoy the book.\n"));
        assertEquals(true, bibliotheca.books.elementAt(1).isReserved());
    }

    @Test
    public void shouldReturnSorryReservedBooksUnsuccessfully() throws IOException {
        bibliotheca.books.elementAt(2).setReserved(true);
        input = new BufferedReader(new StringReader("3"));
        bibliotheca.reserveBook(input, output);

        assertThat(consoleOutput.toString(), is("Sorry we don't have that book yet.\n"));
    }

    public void selectMenuOption(String optionName) throws IOException {
        input = new BufferedReader(new StringReader(String.valueOf(bibliotheca.menu.indexOf(optionName)+1)));
        bibliotheca.selectMenuOptions(input, output);
    }

    public String expectBooks(){
        String expectBooks = "";
        for(int index = 0; index < bibliotheca.books.size(); index++)
            expectBooks +=(index+1) + ". " +  bibliotheca.books.elementAt(index).getName() + "\n";
        return expectBooks;
    }

    public String expectMenu(){
        String expectBooks = "";
        for(int index = 0; index < bibliotheca.menu.size(); index++)
            expectBooks +=(index+1) + ". " +  bibliotheca.menu.optionAt(index) + "\n";
        return expectBooks;
    }
}
