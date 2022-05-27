/**
 * @team Pingert Michael, Sebastian Hattinger
 */

import java.io.*;
import java.util.ArrayList;


/**
 * Class for reading or writing files
 * used for io decorations and characterwise reading and writing
 */
public class ReadWriteHandler {
	ArrayList<Character> data = new ArrayList<>();


	/**
	 * Reading characters from file and applying some decorations to the reader
	 * save characters to data
	 */
	public void readFile(File file)
			throws IOException {	

		/**
		 * The decoration
		 */
		InputStream in = new FileInputStream(file);
		Reader reader = new InputStreamReader(in);
		Reader buffer = new BufferedReader(reader);
		Reader rotEncodingBuffer = new RotEncodingReader(buffer);
		Reader rotEncodingPushbackBuffer = new PushbackReader(rotEncodingBuffer);

		handleCharacters(rotEncodingPushbackBuffer);
		rotEncodingPushbackBuffer.close();
	}

	/**
	 * Writing characters to file and applying some decorations to the writer
	 * takes characters from the data field
	 */
	public void writeFile(File file) throws IOException{

		/**
		 * The decoration
		 */
		Writer writer = new FileWriter("output.txt"); 
		Writer uCWriter = new UpperCaseWriter(writer); 

		for(Character c: data) {
			uCWriter.write(c);
		}
		uCWriter.close();
	}


	private void handleCharacters(Reader reader)
			throws IOException {
		int r;
		while ((r = reader.read()) != -1) {
			char ch = (char) r;
			//System.out.print(ch);
			data.add(ch);
		}
	}

}
