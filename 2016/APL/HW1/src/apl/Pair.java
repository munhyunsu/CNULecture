package apl;

/**
 * Created by DNLab on 2016-05-06.
 */
public class Pair<T1, T2> implements IPrintable {
    private T1 t1 = null;
    private T2 t2 = null;

    public Pair(T1 t1, T2 t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    @Override
    public boolean equals(Object o) {
        if ( !(o instanceof Pair) ) {
            return false;
        }

        Pair<T1, T2> pr = (Pair<T1, T2>) o;

        return (this.t1).equals(pr.t1) && (this.t2).equals(pr.t2);
    }

    @Override
    public int hashCode() {
        int f = t1.hashCode();
        f = f + t2.hashCode();

        return f;
    }

    @Override
    public void printName() {
        System.out.println("Pair");
    }
}

