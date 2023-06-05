package readWritelock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Test01 {
	static int basket01 = 100;
	static int basket02 = 0;

	static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private static void transOne(){
		
		basket01=basket01-1;
		basket02=basket02+1;
		
	}
	public static void main(String[] args) throws InterruptedException {
		List<Thread> pool=trans();
		long start=System.currentTimeMillis();
		for (Thread thread : pool) {
			thread.join();
		}
		long end=System.currentTimeMillis();
		System.out.println(basket01 + "," + basket02+":"+(end-start));
	}

	private static List<Thread> trans() {
		// TODO Auto-generated method stub
		List<Thread> pool=new ArrayList<Thread>();
		for(int i=0;i<3;i++) {
			Thread thread=new Thread(new Runnable() {

				public void run() {
					// TODO Auto-generated method stub
					while (true) {
						
//						readWriteLock.writeLock().lock();
						System.out.println(1);
						readWriteLock.readLock().lock();
						if (basket01 > 0) {
							System.out.println(2);
							readWriteLock.readLock().unlock();
							readWriteLock.writeLock().lock();
							transOne();
							System.out.println(3);
							readWriteLock.writeLock().unlock();
						} else {
							System.out.println(4);
							readWriteLock.readLock().unlock();
//							readWriteLock.writeLock().unlock();
							break;
						}
						System.out.println(5);
//						readWriteLock.writeLock().unlock();
						System.out.println(6);
					}
					
				}
			});
			pool.add(thread);
		}
		for (Thread thread2 : pool) {
			thread2.start();
		}
		return pool;
	}
}
