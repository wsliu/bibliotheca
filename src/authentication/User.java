package authentication;

import sun.security.krb5.PrincipalName;

public class User {
    private String userName;
    private String password;
    private String libraryNumber;
    public boolean logined;

    public User(String userName, String password, String libraryNumber) {
        this.userName = userName;
        this.password = password;
        this.libraryNumber = libraryNumber;
        this.logined = false;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public boolean isLogined() {
        return logined;
    }

    public void setLogined(boolean state) {
        logined = state;
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }
}
