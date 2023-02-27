package osProject3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Test{
	
	public static long startTime;
	public static long totalTime;
	public static int boxOpenings;
	
	public static void main(String args[]){
		
		// create the 8x8 grid. isCheese() for all boxes set to false except for the box at the given coordinates.
		Box[][] g1 = G.getG(7, 7);
		
		// Print out the grid of boxes: "X" if box has cheese, "-" if box does not have cheese.
		for (int i=0; i<g1.length; i++) {
			for (int j=0; j<g1[1].length; j++) {
				if (g1[i][j].isCheese() == true) {
					System.out.print("X" + " | ");
				}
				else System.out.print("-" + " | ");
				}
			System.out.println("\n");
			}
		
		// initialize startTime.
		startTime = System.currentTimeMillis();
		// We use the Executor Service and Executor interfaces to create a new thread pool that will contain our different threads/mice. 
		ExecutorService executor = Executors.newCachedThreadPool();
		
		// Below, the Executor Service invokes the inherited .execute() method.
		// The .execute() method takes a Runnable as argument and executes it. 
		// i.e., it calls the .run() method of the CheeseFinder objects (the threads/mice) without having to create each thread/mouse and call .start() for each thread/mouse individually.
		// .shutdown() allows all previously executed tasks/threads to finish before terminating the ExecutorService.
		
		// Uncomment for experiment with 1 mouse
//		executor.execute(new CheeseFinder(0, 7, g1));
//		executor.shutdown();
		
		// Uncomment for experiment with 4 mice
//		executor.execute(new CheeseFinder(0, 1, g1));
//		executor.execute(new CheeseFinder(2, 3, g1));
//		executor.execute(new CheeseFinder(4, 5, g1));
//		executor.execute(new CheeseFinder(6, 7, g1));
//		executor.shutdown();
//		
		// Uncomment for experiment with 8 mice
		executor.execute(new CheeseFinder(0, 0, g1));
		executor.execute(new CheeseFinder(1, 1, g1));
		executor.execute(new CheeseFinder(2, 2, g1));
		executor.execute(new CheeseFinder(3, 3, g1));
		executor.execute(new CheeseFinder(4, 4, g1));
		executor.execute(new CheeseFinder(5, 5, g1));
		executor.execute(new CheeseFinder(6, 6, g1));
		executor.execute(new CheeseFinder(7, 7, g1));
		executor.shutdown();
		
	}
	
	public static class CheeseFinder implements Runnable{
		
		int startRow;
		int finishRow;
		Box[][] g;
		
		// Create semaphore object to use for synchronization. 
		// It allows us to limit the amount of concurrent threads/mice accessing the shared boxOpenings counter.
		private static Semaphore semaphore = new Semaphore(1); 
		
		// CheeseFinder constructor method.
		public CheeseFinder(int startRow, int finishRow, Box[][] g) {
			this.startRow = startRow;
			this.finishRow = finishRow;
			this.g = g;
		}
		
		// run() method for the CheeseFinders objects. Makes each mouse go through every box in their assigned rows until cheese is found.
		@Override
		public void run() {
			for (int i = startRow; i <= finishRow ; i++) {
				for (int j = 0; j <= 7 ; j++) {
					try {
						Thread.sleep(100); // the amount of time to open a box object.
//						semaphore.acquire(); // allows mouse/thread to call .run() method by acquiring a permit.
						boxOpenings = boxOpenings + 1; // increment box openings counter when time passes.
					}
					catch (InterruptedException ex) {
					}
//					finally { // releases mouse/thread 
//						semaphore.release();
//					}
					if ( g[i][j].isCheese() == true ) {
						System.out.println(Thread.currentThread().getName() + " found cheese at x = " + i + ", y = " + j );
						totalTime = System.currentTimeMillis() - startTime;
						System.out.println("total time (ms) = " + totalTime + " box opening count = " + boxOpenings);
						}
					}
				}
			}
	}

}
