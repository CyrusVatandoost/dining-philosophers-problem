import java.util.concurrent.Semaphore;

public class Waiter {
	
	private Semaphore mutex = new Semaphore(1);

	public boolean isAllowed(boolean left, boolean right) {
		if(left && right) {
			return true;
		}
		return false;
	}
	
	public void call() {
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
