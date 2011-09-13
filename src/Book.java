public class Book {
    String name;
    private boolean reserved;

    public Book(String name) {
        this.name = name;
        this.reserved = false;
    }

    public boolean isReserved() {
        return reserved;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
}
