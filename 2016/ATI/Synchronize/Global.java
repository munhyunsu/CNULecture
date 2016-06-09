import java.util.concurrent.locks.ReentrantLock;

public class Global {
    public static Integer shareInt = new Integer(0);
    public static final ReentrantLock lock = new ReentrantLock();
}
