package readWritelock;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReWrTest {
	private List<Integer> myList;
	private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private Lock readLock = readWriteLock.readLock();
	private Lock writeLock = readWriteLock.writeLock();

	public ReWrTest(List<Integer> myList) {
		// TODO Auto-generated constructor stub
		this.myList = myList;
	}

	public Object get(int index) {
		readLock.lock();
		try {
			return myList.get(index);
		} finally {
			readLock.unlock();
		}
	}

	public void insert(int newValue) {
		writeLock.lock();
		try {
			myList.add(newValue);
		} finally {
			// TODO: handle finally clause
			writeLock.unlock();
		}
	}
}
