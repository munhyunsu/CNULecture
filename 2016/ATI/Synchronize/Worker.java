import java.util.concurrent.locks.ReentrantLock;
import java.lang.System;

public class Worker implements Runnable {
    private String name;
    public Integer shareInt = Global.shareInt;

    //public Worker(String name, Integer shareInt) {
    public Worker(String name) {
        //System.out.println(shareInt.hashCode());
        System.out.println(System.identityHashCode(Global.shareInt));
        this.name = name;
        //this.shareInt = shareInt;
    }

    @Override
	public void run() {
        Global.lock.lock();
        try {
//        synchronized (Global.shareInt) {
		System.out.println(Thread.currentThread().getName() + " Start. Command = " + name);
		processCommand();
		System.out.println(Thread.currentThread().getName() + " End.");
//        }
        } finally {
            Global.lock.unlock();
        }
	}

	private void processCommand() {
//        synchronized (shareInt) {
            Global.shareInt = Global.shareInt + 1;
            while (Global.shareInt == 3) {
            }
            System.out.println(name + ":\t" + Global.shareInt.toString());
            System.out.println(name + "Hash" + System.identityHashCode(Global.shareInt));
//        }
	}
}
