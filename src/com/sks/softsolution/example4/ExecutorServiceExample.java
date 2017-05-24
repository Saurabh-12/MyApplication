package com.sks.softsolution.example4;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceExample {
	
	public static void main(String[] args) {
		
		//example 1
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		// here we are using execute(runnable) method 
		executorService.execute(new RunnableThread("execute: "));
		
		//Example 2
		Future future = null;
        try {
			Thread.sleep(1000);
        	System.out.println(".............................................");
			//ExecutorService submit() example, returns a Future object
			future = executorService.submit(new RunnableThread("submit: "));
			try {
				//returns current status of work.
				System.out.println("future.IsDone "+future.isDone());
				//returns null if the task has finished correctly.
				System.out.println("future.get() "+future.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(".............................................");
		
		//Example 3 
		//ExecutorService Callable example
		future = executorService.submit(new CallableThread());
		try {
			System.out.println("Callable: future.get() = " + future.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println(".............................................");
		
		//Example 4
		//ExecutorService invokeAny() method example
		Set<Callable<String>> callables = new HashSet<Callable<String>>();
		callables.add(new Callable<String>() {
		    public String call() throws Exception {
		        return "Task 1";
		    }
		});
		callables.add(new Callable<String>() {
		    public String call() throws Exception {
		        return "Task 2";
		    }
		});
		callables.add(new Callable<String>() {
		    public String call() throws Exception {
		        return "Task 3";
		    }
		});
		
		String result = null;
		try {
			result = executorService.invokeAny(callables);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("invokeAny: result = " + result);
		System.out.println(".............................................");
		
		// Example 5
		// ExecutorService invokeAll() method example
		List<Future<String>> futures;
		try {
			futures = executorService.invokeAll(callables);
			for (Future<String> future1 : futures) {
				System.out.println("invokeAll: future.get = " + future1.get());
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		//Shutdown executor to avoid memory leak
		executorService.shutdown();
		
	}
	
	// Runnable thread
    static class RunnableThread implements Runnable {
    	String name = "Runnable:";
    	
    	RunnableThread(String methodName){
    		name = methodName;
    	}
    	
		@Override
		public void run() {
            for (int cnt = 0; cnt < 5; cnt++) {
                System.out.println(name + cnt);
                try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            }
			
		}
    }
    
    
 // Callable thread
    static class CallableThread implements Callable<Integer> {
        @Override
        public Integer call() {
            int cnt = 0;
            for (; cnt < 5; cnt++) {
                System.out.println("call:" + cnt);
            }
            return cnt;
        }
    }
}
