

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * DemoClass to demonstrate 1 Producer reading from a file 'text.txt' 
 * The Produser put line by line into a FiFo buffer and 2 Consumers which reading
 * from the Fifo buffer
 * 
 *@author Michael Pingert
 *@author Sebastian Hattinger
 *@version V1.0
 *@since 19.11.2015 
 *
**/

public class DemoMain <T> {
	public static void main(String[] args) {
				
		Drop<String> drop = new Drop<String>();		
		try {
			FileReader produceFile = new FileReader("text.txt");	
			Producer<String> p1 = new Producer<String>(drop, "Producer", 300, produceFile);
			Consumer<String> c1 = new Consumer<String>(drop, "Consumer1", 300,System.out);
			Consumer<String> c2 = new Consumer<String>(drop, "Consumer2", 300,System.err);
														
			try {

				InputStreamReader isr = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(isr);
				System.out.println("To start the Threads press enter, ");
				System.out.println("For interruption the running Threads press 'q' and enter");
				
				//wait till enter will be pressed 
				br.readLine();
				
				p1.start();
				c1.start();
				c2.start();		
				
				//interrupt when q and enter will be pressed - during Thread compilation
				String str = br.readLine();
			
				if (str.equals("q")) {
					p1.interrupt();
					c1.interrupt();
					c2.interrupt();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException ex) {
			System.err.println("Can't read from the input File");
		}
	}
}