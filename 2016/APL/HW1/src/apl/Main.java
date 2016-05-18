package apl;

/**
 * Created by DNLab on 2016-05-06.
 */
public class Main implements IPrintable {
    public static void main(String[] argv) {
        DynamicClassProvider.register("Alpha", "M", "apl.Tablet");

        Object o = DynamicClassProvider.newInstance("Alpha", "M");

        System.out.println(o.getClass());
        ((IPrintable)o).printName();

        DynamicClassProvider.register("Beta", "M", "apl.Tablet");

        Object o2 = DynamicClassProvider.newInstance("Beta", "M");

        System.out.println(o2.getClass());
        ((IPrintable)o2).printName();
        o = DynamicClassProvider.newInstance("Alpha", "M");
        System.out.println(o.getClass());
        ((IPrintable)o).printName();
    }

    @Override
    public void printName() {
        System.out.println("Main");
    }
}
