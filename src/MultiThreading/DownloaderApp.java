package multithreading;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class DownloaderApp {
	
	enum Downloader {
		INSTANCE; // You can name it anything
		private Semaphore semaphore = new Semaphore(5, true);
		
		// By default ENUM has private no arg constructor
		private Downloader() {
			
		}
		
		public void download()  {
			try {
				System.out.println(Thread.currentThread().getName() + " - Permits available " + semaphore.availablePermits());
				semaphore.acquire();
				System.out.println(Thread.currentThread().getName() + " - Downloading..." + semaphore.availablePermits());

				Thread.sleep(new Random().nextInt(1000));//millisecond
				System.out.println(Thread.currentThread().getName() + " -Downloaded...");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				semaphore.release();
				System.out.println(Thread.currentThread().getName() + "Download done Permits available " + semaphore.availablePermits());
			}
			
		}
		
	}
	
	public static void main(String[] args)  {

		ExecutorService executorService = Executors.newCachedThreadPool();
		for(int i=0;i<12;i++) {
			executorService.execute(()->Downloader.INSTANCE.download());
		}
	}

}


