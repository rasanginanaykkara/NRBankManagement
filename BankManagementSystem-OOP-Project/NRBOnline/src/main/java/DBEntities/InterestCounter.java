package DBEntities;

public class InterestCounter implements Runnable{

	@Override
	public void run() {
		while(true) {
			System.out.println("thread on fire");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
