import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Vector;

public class Bibliotheca {
    Vector<String> menu = new Vector<String>();
    Vector<Book> books = new Vector<Book>();

    Bibliotheca(){
        menu.add("View all books");
        menu.add("Reserve a book");
        menu.add("Check library number");

        books.add(new Book("Agile Samurai"));
        books.add(new Book("Head First Java"));
        books.add(new Book("Testing Extreme Programming"));
    }

    public void start(PrintStream output) {
        output.println("Welcome to The Bangalore Public Library System - Bibliotheca !");
    }

    public void showMenu(PrintStream output) {
        output.println("PLEASE TYPE THE INDEX OF THE OPTION IN THE MENU :");
    }

    public void showMenuOptions(PrintStream output) throws IOException {
        for(int index = 0; index < menu.size(); index++)
            output.println((index+1) +". " + menu.elementAt(index));
    }

    public void selectMenuOptions(BufferedReader input, PrintStream output) throws IOException {
            try{
                int selectedMenuOption = Integer.parseInt(input.readLine());
                if(selectedMenuOption > 0 && selectedMenuOption <= menu.size()){
                    if (menu.elementAt(selectedMenuOption-1) == "Check library number")

                        output.println("Please talk to Librarian. Thank you.");
                    if (menu.elementAt(selectedMenuOption-1) == "View all books"){
                        showBooks(output);
                    }
                    if (menu.elementAt(selectedMenuOption-1) == "Reserve a book"){
                        selectReserveBook(input, output);
                    }
                }
                else
                    output.println("Select a valid option!!");
            }
            catch (NumberFormatException e){
                output.println("Select a valid option!!");
            }
    }

    private void selectReserveBook(BufferedReader input, PrintStream output) throws IOException {
            output.println("Please choose the index of the book you want:");
            showBooks(output);
            reserveBook(input, output);

    }

    public void reserveBook(BufferedReader input, PrintStream output) throws IOException {
        String selectedOption = input.readLine();
        if(selectedOption == null)return;
        else{
            try{
                int selectedBook = Integer.parseInt(selectedOption);
                if(selectedBook > 0 && selectedBook <= books.size() && !books.elementAt(selectedBook-1).isReserved()){
                    output.println("Thank You! Enjoy the book.");
                    books.elementAt(selectedBook-1).setReserved(true);
                }
                else
                    output.println("Sorry we don't have that book yet.");
            }
            catch (NumberFormatException e){
                output.println("Select a valid option!!");
            }
        }
    }

    public void showBooks(PrintStream output){
        for(int index = 0; index < books.size(); index++)
            output.println((index + 1) + ". " + books.elementAt(index).getName());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        Bibliotheca bibliotheca = new Bibliotheca();

        bibliotheca.start(System.out);
        do {
        bibliotheca.showMenu(System.out);
        bibliotheca.showMenuOptions(System.out);
        bibliotheca.selectMenuOptions(input, System.out);
        System.out.println("(You can type 'exit' to quit or any other words to continue...)");
        }while (!input.readLine().equals("exit"));
    }
}
