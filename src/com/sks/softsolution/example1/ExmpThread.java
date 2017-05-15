package com.sks.softsolution.example1;

public class ExmpThread extends Thread {

	private int stopCount;

	public ExmpThread(int count) {
		this.stopCount = count;
	}

	public void run() {
		for (int i = 0; i < 50; i++) {
			if (i % stopCount == 0) {
				System.out.println("Stoping thread: " + getName());
				yield();
			}
		}
	}

}
