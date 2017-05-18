package com.sks.softsolution.example3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExample {

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(10);

		for (int i = 0; i < 20; i++) {
			service.submit(new ThreadPoolExample().new Task(i));
		}

		service.shutdown();
		try {
			service.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
		}

	}

	final class Task implements Runnable {
		private int taskId;

		public Task(int id) {
			this.taskId = id;
		}

		@Override
		public void run() {
			System.out.println("Task ID : " + this.taskId + " performed by " + Thread.currentThread().getName());
		}

	}
}
