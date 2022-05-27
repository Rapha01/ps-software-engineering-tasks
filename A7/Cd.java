
/**
 *Class CD supports the component CompactDisc and is Part of the Web-Shop project
 *the class inherited from class medium
 *
 *@author Michael Pingert
 *@author Sebastian Hattinger
 *@version V1.0
 *@since 06.12.2015 
**/


public class Cd extends Medium{

	private int isbn;
	private String artist;

	/**
	 * 
	 * @param name
	 * @param price
	 */
	
	
	public Cd(String name, double price) {
		super(name, price);
	}
	public Cd(String name, double price, int isbn, String artist) {
		super(name, price);
		this.isbn = isbn;
		this.artist = artist;
	}

	public void print(int depth) {

		String out = (this.getClass().getName() +"| " + "Name ="+getName() + ", Price ="+getPrice() + ", isbn ="+isbn + ", artist ="+artist);

		//System.out.println(new String(new char[depth]).replace("\0", "    ") + "-------------");
		System.out.println(new String(new char[depth]).replace("\0", "    ") + out);
		//System.out.println(new String(new char[depth]).replace("\0", "    ") + "-------------");


	}

}
