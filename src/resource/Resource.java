package resource;
public  abstract class Resource {
    String name;

    public Resource(String name) {
        this.name = name;
    }

    public Resource() {
    }

    public String getInfo(){
        return name;
    }
}