package demo;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Test001 {
	static ReentrantReadWriteLock lock=new ReentrantReadWriteLock();
	public  void sayhello() {
		// TODO Auto-generated method stub
		lock.writeLock().lock();
		System.out.println("hello");
		lock.writeLock().unlock();
	}

}
