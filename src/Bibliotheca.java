import authentication.User;
import authentication.UserHolder;
import resource.Book;
import resource.Resource;
import resource.ResourceHolder;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Vector;

public class Bibliotheca {
    Menu menu = new Menu();
    ResourceHolder resourceHolder = new ResourceHolder();
    UserHolder userHolder = new UserHolder();

    public Bibliotheca(){

    }

    public void start(PrintStream output) {
        output.println("Welcome to The Bangalore Public Library System - Bibliotheca !");
    }

    public void showMenu(PrintStream output) {
        output.println("PLEASE TYPE THE INDEX OF THE OPTION IN THE MENU :");
    }

    public void showMenuOptions(PrintStream output) throws IOException {
        for(int index = 1; index <= menu.size(); index++)
            output.println((index) + ". " + menu.optionAt(index));
    }

    public void selectMenuOptions(BufferedReader input, PrintStream output) throws IOException {
            try{
                int selectedMenuOption = Integer.parseInt(input.readLine());
                if(selectedMenuOption > 0 && selectedMenuOption <= menu.size()){
                    if (menu.optionAt(selectedMenuOption) == "Check library number")
                        selectCheckLibraryNumber(output);
                    if (menu.optionAt(selectedMenuOption) == "View all books"){
                        output.print(resourceHolder.expectResource(resourceHolder.bookLib));
                    }
                    if (menu.optionAt(selectedMenuOption) == "Reserve a book"){
                        selectReserveBook(input, output);
                    }
                    if (menu.optionAt(selectedMenuOption) == "View all movies"){
                        output.println("Index Name - director - rating:");
                        output.print(resourceHolder.expectResource(resourceHolder.movieLib));
                    }
                    if (menu.optionAt(selectedMenuOption) =="Login"){
                        selsectLogin(input, output);
                    }
                }
                else
                    output.println("Select a valid option!!");
            }
            catch (NumberFormatException e){
                output.println("Select a valid option!!");
            }
    }

    private void selectCheckLibraryNumber(PrintStream output) {
        User currentUser = userHolder.currentUser;
        if(currentUser!= null && currentUser.isLogined()){
            output.println(userHolder.currentUser.getLibraryNumber());
        }
        else
        output.println("Please talk to Librarian. Thank you.");

    }

    private void selectReserveBook(BufferedReader input, PrintStream output) throws IOException {
            output.println("Please choose the index of the book you want:");
            output.print(resourceHolder.expectResource(resourceHolder.bookLib));
            reserveBook(input, output);

    }

    public void selsectLogin(BufferedReader input, PrintStream output) throws IOException {
        userHolder.login(input, output);
    }

    public void reserveBook(BufferedReader input, PrintStream output) throws IOException {
        String selectedOption = input.readLine();
        if(selectedOption == null)return;
        else{
            try{
                int selectedBookIndex = Integer.parseInt(selectedOption) - 1;
                Book selectedBook = (Book)resourceHolder.bookLib.elementAt(selectedBookIndex);
                if(selectedBookIndex > 0 && selectedBookIndex <= resourceHolder.amount(resourceHolder.bookLib) && !(selectedBook).isReserved()){
                    output.println("Thank You! Enjoy the book.");
                    selectedBook.setReserved(true);
                }
                else
                    output.println("Sorry we don't have that book yet.");
            }
            catch (NumberFormatException e){
                output.println("Select a valid option!!");
            }
        }
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
