package com.springboot.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

class ThrowTask implements Runnable {
	public void run() {
	try{int a=1/0;}
	catch (Exception e){
		int a=1/0;;
	}
	}
}

public class ExceptionInThread {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newFixedThreadPool(3,new HandlerThreadFactory());
		exec.execute(new ExceptionThread());
		exec.execute(new ExceptionThread());
		exec.execute(new ExceptionThread());
//		Thread t = new Thread(new ThrowTask());
//		t.setUncaughtExceptionHandler(
//		    (thread, e) ->
//					System.out.println("Catch it!"));
//		t.start();
	}
}


class HandlerThreadFactory implements ThreadFactory {

   @Override
   public Thread newThread(Runnable r) {
       System.out.println("创建一个新的线程");
       Thread t = new Thread(r);
       t.setUncaughtExceptionHandler(new MyUnchecckedExceptionhandler());
       System.out.println("eh121 = " + t.getUncaughtExceptionHandler());
       return t;
   }

}

 class MyUnchecckedExceptionhandler implements Thread.UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("捕获到异常：" + e);
	}

}

 class ExceptionThread implements Runnable {

	@Override
	public void run() {
		int a=1/0;
	}



}