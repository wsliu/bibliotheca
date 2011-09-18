package resource;

import java.util.Vector;

public class ResourceHolder {
    public Vector<Resource> bookLib;
    public Vector<Resource> movieLib;
    public ResourceHolder(){
        bookLib = new Vector<Resource>();
        movieLib = new Vector<Resource>();
        createBookLib();
        createMovieLib();
    }

    private void createBookLib() {
        bookLib.add(new Book("Agile Samurai"));
        bookLib.add(new Book("Head First Java"));
        bookLib.add(new Book("Testing Extreme Programming"));
    }

    private void createMovieLib() {
        movieLib.add(new Movie("Avatar","James Cameron","9"));
        movieLib.add(new Movie("Contagion","Steven Soderbergh","N/A"));
        movieLib.add(new Movie("Rio","Carlos Saldanha","9"));
    }

    public int amount(Vector<Resource> resources){
        return resources.size();
    }

    public Resource elementAt(Vector<Resource> resources, int index){
        return resources.elementAt(index-1);
    }

    public void add(Vector<Resource> resources, Resource element) {
        resources.add(element);
    }

    public String expectResource(Vector<Resource> resources){
        String expectResources = "";
        for(int index = 0; index < amount(resources);index++)
            expectResources +=(index+1) + ". " + resources.elementAt(index).getInfo() + "\n";
        return expectResources;
    }
}
