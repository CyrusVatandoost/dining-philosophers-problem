import java.util.concurrent.ThreadLocalRandom;

public class Philosopher extends Thread {

	private static final int THINKING = 0;
	private static final int WAITING = 1;
	private static final int EATING = 2;
	
	public int number;
	public Chopstick leftChopstick;
	public Chopstick rightChopstick;
	private int status;
	private Waiter waiter;
	
	public Philosopher(int num, Chopstick left, Chopstick right, Waiter waiter) {
		this.number = num;
		this.leftChopstick = left;
		this.rightChopstick = right;
		this.waiter = waiter;
	}
	
	public void run() {
		System.out.println("Hi! I'm philosopher #" + number);
		
		while(true) {
//			status = WAITING;
			status = THINKING;
			
			if(waiter.isFree()) {
				waiter.call();
				if(waiter.isAllowed(leftChopstick.isFree(), rightChopstick.isFree())) {
					leftChopstick.grab();
//					System.out.println("Philosopher #" + number + " grabs left chopstick.");
					rightChopstick.grab();
					waiter.release();	
//					System.out.println("Philosopher #" + number + " grabs right chopstick.");
					status = EATING;
					eat();
					status = THINKING;
					leftChopstick.release();
//					System.out.println("Philosopher #" + number + " releases left chopstick.");
					rightChopstick.release();
//					System.out.println("Philosopher #" + number + " releases right chopstick.");
				}
				else
					waiter.release();	
			}
			
//			waiter.call();
//			if(waiter.isAllowed(leftChopstick.isFree(), rightChopstick.isFree())) {
//				leftChopstick.grab();
//				rightChopstick.grab();
////				System.out.println("Philosopher #" + number + " grabs left chopstick.");
////				System.out.println("Philosopher #" + number + " grabs right chopstick.");
//				status = EATING;
//				waiter.release();
//				eat();
//				leftChopstick.release();
////				System.out.println("Philosopher #" + number + " releases left chopstick.");
//				rightChopstick.release();
////				System.out.println("Philosopher #" + number + " releases right chopstick.");
//				status = THINKING;
//			}
//			else {
//				waiter.release();
//			}
		}
	}
	
	public void eat() {
		try {
			int sleepTime = ThreadLocalRandom.current().nextInt(0, 500);
//			System.out.println("Philosopher #" + number + " eats for " + sleepTime);
			Thread.sleep(sleepTime);
		}
		catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
	
	public boolean isEating() {
		if(status == EATING)
			return true;
		return false;
	}
	
	public boolean isThinking() {
		if(status == THINKING)
			return true;
		return false;
	}
	
	public boolean isWaiting() {
		if(status == WAITING)
			return true;
		return false;
	}

}