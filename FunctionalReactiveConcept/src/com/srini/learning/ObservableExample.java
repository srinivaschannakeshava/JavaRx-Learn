package com.srini.learning;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.FutureTask;

import org.apache.commons.lang3.ThreadUtils;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ObservableExample {

	public static void main(String[] args) {
		Thread waitMonitor = new Thread();
		waitMonitor.start();
		synchronized (waitMonitor) {
			try {
				waitMonitor.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Observable<Integer> observ = null;
			System.out.println("--------------------------------------------------");
			System.out.println("Observable Creation from a single value");
			System.out.println("--------------------------------------------------");
			observ = Observable.just(Integer.valueOf(42));
			observ.subscribe(i -> {
				System.out.println("Integer Sub : " + i + " Thread : " + Thread.currentThread().getName());
			});
			System.out.println("--------------------------------------------------");
			System.out.println("Observable Creation from an Iterable");
			System.out.println("--------------------------------------------------");
			observ = Observable.fromIterable(generateRandomList(10));
			observ.subscribe(i -> {
				System.out.println("Integer List : " + i + " Thread : " + Thread.currentThread().getName());
			});

			System.out.println("--------------------------------------------------");
			System.out.println("Observable Creation from an array");
			System.out.println("--------------------------------------------------");
			observ = Observable.fromArray(generateRandomArray(10));
			observ.subscribe(i -> {
				System.out.println("Integer Array : " + i + " Thread : " + Thread.currentThread().getName());
			});

			System.out.println("--------------------------------------------------");
			System.out.println("Observable Creation from a future");
			System.out.println("--------------------------------------------------");

			Observable<List<Integer>> observableFutureList;
			FutureTask<List<Integer>> future = new FutureTask<List<Integer>>(() -> {
				return generateDelayedRandomList(10);
			});
			observableFutureList = Observable.fromFuture(future);
			Schedulers.computation().scheduleDirect(() -> {
				future.run();
			});
			observableFutureList.subscribe((list) -> {
				list.forEach(s -> {
					System.out.println(
							"Future Delayed List Value : " + s + " Thread : " + Thread.currentThread().getName());
				});
			});
			System.out.println("--------------------------------------------------");
			System.out.println("Observable Subscription on different Thread");
			System.out.println("--------------------------------------------------");
			observ.subscribeOn(Schedulers.computation()).subscribe(i -> {
				System.out.println("Integer Array : " + i + " Compute Thread : " + Thread.currentThread().getName());
			}, e -> {
				e.printStackTrace();
			}, () -> {
				System.out.println("-----------------Compute Thread Completed---------------------------------");
			});
			System.out.println("--------------------------------------------------");
			observ.subscribeOn(Schedulers.newThread()).subscribe(i -> {
				System.out.println("Integer Array : " + i + " new Thread : " + Thread.currentThread().getName());
			}, e -> {
				e.printStackTrace();
			}, () -> {
				System.out.println("-----------------New Thread Completed---------------------------------");
			});
			Scheduler io = Schedulers.io();
			observ.observeOn(io).subscribe(i -> {
				System.out.println("Integer Array : " + i + " IO Thread : " + Thread.currentThread().getName());
			}, e -> {
				e.printStackTrace();
			}, () -> {
				System.out.println("-----------------Io Thread Completed---------------------------------");
				synchronized (waitMonitor) {
					waitMonitor.notify();					
				}
			});
			observ = Observable.fromArray(generateRandomArray(10));
			
		}
//		ArrayList<String> test = new ArrayList<>();
//		test.add("a");
//		test.add("b");
//		test.add("c");
//		test.add("d");
//		Flowable<String> flow = Flowable.fromIterable(test);
//		flow.subscribe(new Consumer<String>() {
//			@Override
//			public void accept(String t) throws Throwable {
//				// TODO Auto-generated method stub
//				System.out.println("consuming : " + t);
//			}
//		});
//		flow.doOnNext(new Consumer<String>() {
//		@Override
//		public void accept(String t) throws Throwable {
//			// TODO Auto-generated method stub
//			System.out.println("Doing Next : "+t);
//		}
//		});
//		Flowable.fromPublisher(new Publisher<String>() {
//			@Override
//			public void subscribe(Subscriber<? super String> s) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//		test.add("1");
//		sleep();
//		test.add("2");
//		sleep();
//		test.add("3");
//		sleep();
//		test.add("4");
//		
//		Observable<String> obj=Observable.just("Test");

	}

	private static void sleep(long delayTime) {
		try {
			Thread.sleep(delayTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static List<Integer> generateRandomList(int size) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			list.add(Math.abs(new Random().nextInt()));
		}
		return list;
	}

	private static List<Integer> generateDelayedRandomList(int size) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			list.add(Math.abs(new Random().nextInt()));
			sleep(100);
		}
		return list;
	}

	private static Integer[] generateRandomArray(int size) {
		Integer[] array = new Integer[size];
		for (int i = 0; i < size; i++) {
			array[i] = Math.abs(new Random().nextInt());
		}
		return array;
	}

}
