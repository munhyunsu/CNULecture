package apl;

/**
 * Created by DNLab on 2016-05-06.
 */
public class Tablet extends ClassLoader implements IPrintable {
    private String name = null;

    public Tablet() {
        this.name = "random";
    }

    public Tablet(String name) {
        this.name = name;
    }

    public Tablet getInstance() {
        return new Tablet();
    }

    @Override
    public void printName() {
        System.out.println("Tablet");
    }
}
