
import java.util.concurrent.TimeUnit;
import java.io.PrintStream;

/**
 *Consumer 
 *@author Michael Pingert
 *@author Sebastian Hattinger
 *@version V1.0
 *@since 19.11.2015 
**/



public class Consumer <T> extends Thread implements Runnable {
   private Drop<String> drop;
   private int sleepTime;
   private PrintStream outputStream;
   
   /**
    * Constructor for the class Consumer
    * @param drop  ('drop') the name of the Fifo Buffer
    * @param name  ('name')set the consumers 'name'
    * @param sleepTime ('100') ms wait between the lines when reading from the Buffer
    * @param out  ('err' or 'out') switch for the output
    */
  
   public Consumer(Drop<String> drop, String name, int sleepTime,PrintStream out)
   {
      this.drop = drop;
      this.setName(name);
      this.sleepTime = sleepTime;
      this.outputStream = out;
   }


   public void run()
   {

	  String media = "";
      while (!media.equals("DONE")) 
      {
         try
         {
        	media = drop.take().toString();
        	outputStream.println(getName()+" "+media);
            TimeUnit.MILLISECONDS.sleep((int)(Math.random()*sleepTime));
         }
         catch(InterruptedException ex)
         {
            System.out.println(ex);
      //      interrupt();
            System.out.println(Thread.currentThread().getName() + " shut down");
            return;
         }
        
      }
      drop.put( "DONE");
   }
}