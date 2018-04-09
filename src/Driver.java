public class Driver {

	public static void main(String argv[]) {

		int philosophersNumber = 10;
		Philosopher philosophers[] = new Philosopher[philosophersNumber];
		Chopstick forks[] = new Chopstick[philosophersNumber];
				  
		System.out.println("Dining philosophers problem.");
			
		for(int i = 0; i < philosophersNumber; i++) {
			forks[i] = new Chopstick();
		}
			
		for(int i = 0; i < philosophersNumber; i++) {
			philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % philosophersNumber]);
			philosophers[i].start();
		}
			
		while(true) {
			
			System.out.println();
			for(Philosopher p : philosophers) {
				if(p.isEating())
					System.out.print("E");
				if(p.isThinking())
					System.out.print("T");
				if(p.isWaiting())
					System.out.print("W");
			}
			System.out.println();
			
			try {
				// sleep 1 sec
				Thread.sleep(1000);
				
				// check for deadlock
				boolean deadlock = true;
				for(Chopstick f : forks) {
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
