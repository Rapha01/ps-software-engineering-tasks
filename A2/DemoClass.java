/**
 *@author Michael Pingert
 *@author Sebastian Hattinger
 *@version V1.0
 *
 *Demo program for testing the method reconstruct(String .., PrintStream ..) and
 *reconstruct(Class .. , PrintStream ..)
 *the output file will be created in a <test/...> folder
 */

package ps_se_ws2015.hattinger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class DemoClass {



    public static void main(String[] args)  {

        new File("test").mkdir();
        String requestedFull = "ps_se_ws2015.mpingert.Reconstructor";
        Class requestedClass = java.util.ArrayList.class;


        try {
	        new Reconstructor().reconstruct(requestedClass, new PrintStream(new FileOutputStream("test/" + requestedClass + "_reconstructed.txt")));
        } catch (FileNotFoundException e) {
                System.err.println("I/O Error not possible to writing the file");
        }

        try {
	        new Reconstructor().reconstruct(requestedFull, new PrintStream(new FileOutputStream("test/" + requestedFull + "_reconstructed.txt")));
        } catch (FileNotFoundException f) {
            System.err.println("I/O Error not possible to writing the file");

        } catch (ClassNotFoundException c) {
            System.err.println("Class not found");
        }
    }
}


