
import java.io.IOException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 메인 클래스
 * @author 문현수
 */
public class Main {
    public static Integer shareInt = Global.shareInt;

    public static void main(String[] args) throws IOException {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            //Runnable worker = new Worker(Integer.toString(i), shareInt);
            Runnable worker = new Worker(Integer.toString(i));
            executor.submit(worker);
        }

        executor.shutdown();

        while (!executor.isTerminated()) {
        }

        System.out.println("Finished all threads");
        System.out.println("Result:\t" + Global.shareInt);
    } // void main()
} // class Main
