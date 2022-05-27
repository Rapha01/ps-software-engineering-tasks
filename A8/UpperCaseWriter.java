/**
 * @team Pingert Michael, Sebastian Hattinger
 */

import java.io.*;


/**
 * Filter class for writing converting letters to upper case
 */
public class UpperCaseWriter extends FilterWriter {

	/**
	 * The underlying character-output stream.
	 */
	protected Writer out;

	/**
	 * Create a new filtered writer.
	 *
	 * @param out  a Writer object to provide the underlying stream.
	 * @throws NullPointerException if <code>out</code> is <code>null</code>
	 */
	protected UpperCaseWriter(Writer out) {
		super(out);
		this.out = out;
	}

	/**
	 * Writes a single character.
	 *
	 * @exception  IOException  If an I/O error occurs
	 */
	public void write(int c) throws IOException {
		char ch = (char)c;
		if(ch >= 'a' && ch <= 'z'){c -= 32;}
		out.write(c);
	}

	/**
	 * Flushes the stream.
	 *
	 * @exception  IOException  If an I/O error occurs
	 */
	public void flush() throws IOException {
		out.flush();
	}

	public void close() throws IOException {
		out.close();
	}

}
