
import java.util.concurrent.LinkedBlockingQueue;

/**
 *Fifo Buffer with a LinkedBlockingQueue
 *@author Michael Pingert
 *@author Sebastian Hattinger
 *@version V1.0
 *@since 19.11.2015 
**/

public class Drop <T>{
	
	private LinkedBlockingQueue<T> mediaQueue = new LinkedBlockingQueue<T>(10);

	private boolean empty = true;
	private boolean full = false;

	public synchronized T take() {
		
		// Wait until a message is available.
		while (empty) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}

		// Toggle status.
		if(mediaQueue.size() == 1){
			empty = true;
		}		
		full = false;
		
		//System.out.println(mediaQueue.toString());
		
		// Notify producer that status has changed.
		notifyAll();
		
		return mediaQueue.poll();
	}
	
	public synchronized void put(T medium) {
		// Wait until message has
		// been retrieved.
		while (full) {
			try { 
				wait();
			} catch (InterruptedException e) {
				System.out.println("");
			}
		}
		// Toggle status.
		empty = false;
		if(mediaQueue.remainingCapacity() == 1){
			full = true;
		}

		// Store message.
		mediaQueue.add(medium);
		// Notify consumer that status
		// has changed.
		notifyAll();
	}
}