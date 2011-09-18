package authentication;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static junit.framework.Assert.assertEquals;

public class UserHolderTest {

    UserHolder userHolder;

    @Before
    public void initialize(){
        userHolder = new UserHolder();
    }

    @Test
    public void testCheckUser(){
        assertEquals(true, userHolder.checkUser("user1", "password1"));
    }

    @Test
    public void testLoginSuccessfully() throws IOException {
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(consoleOutput);
        BufferedReader input = new BufferedReader(new StringReader("user1\npassword1\n"));
        userHolder.login(input, output);

        assertEquals(true, userHolder.isLogined("user1"));
    }


    @Test
    public void testLoginUnsuccessfully() throws IOException {
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(consoleOutput);
        BufferedReader input = new BufferedReader(new StringReader("user1\nwrongpassword\n"));
        userHolder.login(input, output);

        assertEquals(false, userHolder.isLogined("user1"));
    }


}
