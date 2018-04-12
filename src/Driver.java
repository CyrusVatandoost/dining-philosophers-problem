public class Driver {

	public static void main(String argv[]) {

		int philosophersNumber = 19;
		Philosopher philosophers[] = new Philosopher[philosophersNumber];
		Chopstick chopsticks[] = new Chopstick[philosophersNumber];
		Waiter waiter = new Waiter();
		
		System.out.println("Dining philosophers problem.");
			
		for(int i = 0; i < philosophersNumber; i++) {
			chopsticks[i] = new Chopstick();
		}
			
		for(int i = 0; i < philosophersNumber; i++) {
			philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % philosophersNumber], waiter);
			philosophers[i].start();
		}
			
		while(true) {
			
//			System.out.println();
			
			int index = 0;
			for(Philosopher p : philosophers) {

				if(chopsticks[index].isFree()) {
					System.out.print(" ^ ");
				}
				else {
					System.out.print(" . ");
				}
				
				index++;
				
				if(index >= philosophersNumber) {
					index = 0;
				}
				
				if(p.isEating())
					System.out.print("E");
				if(p.isThinking())
					System.out.print("T");
				if(p.isWaiting())
					System.out.print("W");
			}
			System.out.println();
//			System.out.println();
			
			try {
				// sleep 1 sec
				Thread.sleep(1000);
				
				// check for deadlock
				boolean deadlock = true;
				for(Chopstick f : chopsticks) {
					if(f.isFree()) {
						deadlock = false;
						break;
					}
				}
				if(deadlock) {
					Thread.sleep(1000);
					System.out.println("Hurray! There is a deadlock!");
					break;
				}
			}
			catch(Exception e) {
				e.printStackTrace(System.out);
			}
			
		}
			
		System.out.println("Bye!");
		System.exit(0);

	}
}
