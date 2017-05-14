package com.sks.softsolution.example;

import java.util.List;

public class ProducerThread implements Runnable {

	private final List<Integer> taskQueue;
	private final int MAX_CAPACITY;


	public ProducerThread(List<Integer> sharedQueue, int size)
	{
		this.taskQueue = sharedQueue;
		this.MAX_CAPACITY = size;
	}

	@Override
	public void run() {
		int counter = 0;
	      while (true)
	      {
	         try
	         {
	        	 ProduceTask(counter++);
	         } 
	         catch (InterruptedException ex)
	         {
	            ex.printStackTrace();
	         }
	      }

	}
	
	
	 private void ProduceTask(int i) throws InterruptedException
	   {
	      synchronized (taskQueue)
	      {
	         while (taskQueue.size() == MAX_CAPACITY)
	         {
	            System.out.println("Queue is full " + Thread.currentThread().getName() + " is waiting , size: " + taskQueue.size());
	            taskQueue.wait();//calling wait on taskQueue lock
	         }
	           
	         Thread.sleep(1000);
	         taskQueue.add(i);
	         System.out.println("Produced: " + i);
	         taskQueue.notifyAll();
	      }
	   }


}
