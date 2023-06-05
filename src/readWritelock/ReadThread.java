package readWritelock;

public class ReadThread extends Thread{
	int id;
	ReWrTest test;
	int num;
	public ReadThread(int id, ReWrTest test, int num) {
		super();
		this.id = id;
		this.test = test;
		this.num = num;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		int index;
		for (int i = 0; i < num; i++) {
			index=id*num+i;
			test.get(index);
		}
	}

}
