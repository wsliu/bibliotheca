package resource;

public class Movie extends Resource{
    private String name;
    private String director;
    private String rating;

    public Movie(String name, String director, String rating) {
        this.name = name;
        this.director = director;
        this.rating = rating;
    }

    public String getInfo(){
        return name + " - " + director + " - " + rating;
    }
}
