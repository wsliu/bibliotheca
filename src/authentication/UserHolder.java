package authentication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Vector;

public class UserHolder {
    private Vector<User> users = new Vector<User>();
    public User currentUser;

    public UserHolder(){
        addUser("librarian", "password", "111-1111");
        addUser("user1", "password1", "111-1112");
    }

    private void addUser(String userName, String password, String libraryNumber) {
        users.add(new User(userName, password, libraryNumber));
    }

    public boolean checkUser(String userName, String password) {
        int index = indexOf(userName);
        if(index > -1 && users.elementAt(index).getPassword().equals(password))
            return true;
        else
            return false;
    }

    public void login(BufferedReader input, PrintStream output) throws IOException {
        output.println("Please enter your username:");
        String userName = input.readLine();

        output.println("Please enter your password:");
        String password = input.readLine();

        if( checkUser(userName, password) ){
            if(users.elementAt(indexOf(userName)).isLogined())
                output.println("You have been logged in!!");
            else{
                currentUser = users.elementAt(indexOf(userName));
                currentUser.setLogined(true);
                output.println("Login Successfully!");
            }
        }
        else{
            output.println("Login failed!");
        }



    }

    public boolean isLogined(String userName) {
        return users.elementAt(indexOf(userName)).isLogined();
    }

    public int indexOf(String userName){
        int index = -1;
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                index = users.indexOf(user);
                break;
            }
        }
        return index;
    }
}
