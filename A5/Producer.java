
import java.util.concurrent.TimeUnit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *Producer
 *@author Michael Pingert
 *@author Sebastian Hattinger
 *@version V1.0
 *@since 19.11.2015 
**/


public class Producer <T> extends Thread implements Runnable
{
   private Drop<String> drop;
   private int sleepTime;
   ArrayList<String> input = new ArrayList<String>();

   /**
    * Constructor for the class Producer
    * @param drop ('drop') the name of the Fifo Buffer
    * @param name ('name')set the producer 'name'
    * @param sleepTime ('100') ms wait between the lines when reading from the Buffer
    * @param inputFile (fileName) 
    */
   
   public Producer(Drop<String> drop, String name, int sleepTime,FileReader inputFile)
   {
	   try (BufferedReader br = new BufferedReader(inputFile)) {
		    String line;
		    while ((line = br.readLine()) != null) {
		      input.add(line);
		    }
		} catch(IOException ex) {
			System.err.println("File not Found Error");
		}
	   
      this.drop = drop;
      this.setName(name);
      this.sleepTime = sleepTime;
   }

   
   public void run()
   {
       for (int i = 0; i < input.size();i++) {       
      {
         try
         {
        	drop.put(input.get(i));  
            TimeUnit.MILLISECONDS.sleep((int)(Math.random()*sleepTime));
         }
         
         catch(InterruptedException ex)
         {
            System.out.println(ex);
            interrupt();
            System.out.println(this.getName() + " shut down");
            break;
         }
      }
     
   }
      drop.put( "DONE"); 
      
}
}

   
   