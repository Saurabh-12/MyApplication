package com.sks.softsolution.example1;

public class ThreadSleepExample implements Runnable {
	
	@Override
	public void run() {
		
		for (int i = 10; i < 13; i++) {
			System.out.println(Thread.currentThread().getName() + "  " + i);
			try {
				// thread to sleep for 1000 milliseconds
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	public static void main(String[] args) {
		
		Thread t = new Thread(new ThreadSleepExample());
	      // this will call run() function
	      t.start();

	      Thread t2 = new Thread(new ThreadSleepExample());
	      // this will call run() function
	      t2.start();
	}



}
