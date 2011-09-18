package resource;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ResourceHolderTest {
    ResourceHolder resourceHolder;
    @Before
    public void initialize(){
        resourceHolder = new ResourceHolder();
    }

    @Test
    public void testCreateBookLib(){
        assertEquals(3, resourceHolder.amount(resourceHolder.bookLib));
    }

    @Test
    public void testElementAt(){
        Book book = (Book)resourceHolder.elementAt(resourceHolder.bookLib, 1);
        assertEquals("Agile Samurai", book.getInfo() );
    }

    @Test
    public void testAdd(){
        int oldAmount = resourceHolder.amount(resourceHolder.movieLib);
        Movie movie = new Movie("TestMovieName","Susan","N/A");
        resourceHolder.add(resourceHolder.movieLib, movie);

        int newAmount = resourceHolder.amount(resourceHolder.movieLib);
        Movie actualMovie = (Movie)resourceHolder.elementAt(resourceHolder.movieLib, newAmount);
        assertEquals(oldAmount+1, newAmount);
        assertEquals("TestMovieName - Susan - N/A", actualMovie.getInfo());
    }

    @Test
    public void testExpectResource(){
        String expectResources = "1. Avatar - James Cameron - 9\n2. Contagion - Steven Soderbergh - N/A\n3. Rio - Carlos Saldanha - 9\n";
        assertEquals(expectResources, resourceHolder.expectResource(resourceHolder.movieLib));
    }
}
