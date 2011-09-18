package resource;

public class Book extends Resource{
    String name;
    private boolean reserved;

    public Book(String name) {
        this.name = name;
        this.reserved = false;
    }

    public boolean isReserved() {
        return reserved;
    }

    public String getInfo(){
        return name;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
}
