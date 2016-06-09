import java.util.concurrent.locks.ReentrantLock;
import java.lang.System;

public class Worker implements Runnable {
    private String name;
    public Integer shareInt = Global.shareInt;

    //public Worker(String name, Integer shareInt) {
    public Worker(String name) {
        //System.out.println(shareInt.hashCode());
        System.out.println(System.identityHashCode(shareInt));
        this.name = name;
        //this.shareInt = shareInt;
    }

    @Override
	public void run() {
        synchronized (shareInt) {
		System.out.println(Thread.currentThread().getName() + " Start. Command = " + name);
		processCommand();
		System.out.println(Thread.currentThread().getName() + " End.");
        }
	}

	private void processCommand() {
//        synchronized (shareInt) {
            shareInt = shareInt + 1;
            System.out.println(name + ":\t" + shareInt.toString());
            System.out.println(name + "Hash" + System.identityHashCode(shareInt));
//        }
	}
}
