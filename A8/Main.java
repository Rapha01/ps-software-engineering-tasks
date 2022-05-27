/**
 * @team Pingert Michael, Sebastian Hattinger
 */

import java.io.*;

public class Main {

	public static void main(String[] args) {
		ReadWriteHandler rwh = new ReadWriteHandler();

		File file = new File("data.txt");
		try{
			rwh.readFile(file);
			rwh.writeFile(file);
		}
		catch(IOException ex){System.out.error("read/write error")}
	}



}

