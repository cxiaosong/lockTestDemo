package readWritelock;

import java.util.ArrayList;
import java.util.List;

public class Index {
	public static void main(String[] args) {
		int numThread=10;
		int readNum=1;
		int exeTimes=50000;
		List<Integer> myList=new ArrayList<Integer>();
		for (int i = 0; i < readNum; i++) {
			for (int j = 0; j < exeTimes; j++) {
				myList.add(i*exeTimes+j);
			}
		}
		ReWrTest test=new ReWrTest(myList);
		long startTime=System.currentTimeMillis();
		Thread[] rd=new ReadThread[readNum];
		int writeNum=numThread-readNum;
		Thread[] wr=new WriteThread[writeNum];
		for (int i = 0; i < readNum; i++) {
			rd[i]=new ReadThread(i, test, exeTimes);
			rd[i].start();
			
		}
		System.out.println("读线程已经启动");
		for (int i = 0; i <writeNum; i++) {
			wr[i]=new WriteThread(readNum+i, test, exeTimes);
			wr[i].start();
		}
		System.out.println("写线程已启动");
		try {
			for (int i = 0; i < readNum; i++) {
				rd[i].join();
				
			}
			for (int i = 0; i <writeNum; i++) {
				wr[i].join();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		long endTime=System.currentTimeMillis();
		System.out.println("使用读写锁花费时间"+(endTime-startTime)+" ms");
	}
}
