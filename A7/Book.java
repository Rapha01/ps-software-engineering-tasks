
/**
 *Class Book supports the component Book and is a part of the Web-Shop project
 *the class inherited from class medium
 *
 *@author Michael Pingert
 *@author Sebastian Hattinger
 *@version V1.0
 *@since 06.12.2015 
**/


public class Book extends Medium{
	
	private int isbn;
	private String author;

	/**
	 * @param name
	 * @param price
	 * @param isbn
	 */
	
	public Book(String name, double price, int isbn) {
		super(name, price);
		this.isbn = isbn;
	}
	public Book(String name, double price, int isbn, String author) {
		super(name, price);
		this.isbn = isbn;
		this.author = author;
	}

	public void print(int depth) {
		String out = (this.getClass().getName() +"| " + "Name ="+getName() + ", Price ="+getPrice() + ", isbn ="+isbn + ", author ="+author);
	
		System.out.println(new String(new char[depth]).replace("\0", "    ") + out);
	
	}

}
