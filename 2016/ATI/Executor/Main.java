
import java.io.IOException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 메인 클래스
 * @author 문현수
 */
public class Main {
	public static void main(String[] args) throws IOException {
		ExecutorService executor = Executors.newFixedThreadPool(5);

		for (int i = 0; i < 10; i++) {
			Runnable worker = new Worker("" + i);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			executor.submit(worker);
		}

		executor.shutdown();

		while (!executor.isTerminated()) {
		}

		System.out.println("Finished all threads");
	} // void main()
} // class Main
