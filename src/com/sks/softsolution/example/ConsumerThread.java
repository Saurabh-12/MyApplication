package com.sks.softsolution.example;

import java.util.List;

public class ConsumerThread implements Runnable
{
	   private final List<Integer> taskQueue;
	 
	   public ConsumerThread(List<Integer> sharedQueue)
	   {
	      this.taskQueue = sharedQueue;
	   }
	 
	   @Override
	   public void run()
	   {
	      while (true)
	      {
	         try
	         {
	            consumeTask();
	         } catch (InterruptedException ex)
	         {
	            ex.printStackTrace();
	         }
	      }
	   }
	   
	   private void consumeTask() throws InterruptedException
	   {
	      synchronized (taskQueue)
	      {
	         while (taskQueue.isEmpty())
	         {
	            System.out.println("Queue is empty " + Thread.currentThread().getName() + " is waiting , size: " + taskQueue.size());
	            taskQueue.wait();
	         }
	         Thread.sleep(1000);
	         int i = (Integer) taskQueue.remove(0);
	         System.out.println("Consumed: " + i);
	         taskQueue.notifyAll();
	      }
	   }
}
