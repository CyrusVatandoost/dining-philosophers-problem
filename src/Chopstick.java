import java.util.concurrent.Semaphore;

public class Chopstick {

	private Semaphore mutex = new Semaphore(1);
	
	public void grab() {
		try {
			mutex.acquire();
		}
		catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
	
	public void release() {
		mutex.release();
	}
	
	public boolean isFree() {
		return mutex.availablePermits() > 0;
	}

}