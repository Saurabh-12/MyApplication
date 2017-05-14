package com.sks.softsolution.example;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerExampleWithWaitAndNotify {
	
	public static void main(String[] args) {
		
		
		  List<Integer> taskQueue = new ArrayList<Integer>();
	      int MAX_CAPACITY = 7;
	      Thread tProducer = new Thread(new ProducerThread(taskQueue, MAX_CAPACITY), "Producer");
	      Thread tConsumer = new Thread(new ConsumerThread(taskQueue), "Consumer");
	      tProducer.start();
	      tConsumer.start();
	}

}
