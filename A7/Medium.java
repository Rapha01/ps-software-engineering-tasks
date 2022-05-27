
/**
 *The Class Medium offers the basic functions for products which can be stored in a Web-Shop archive 
 *The Interface Media is implemented in this class
 *
 *@author Michael Pingert
 *@author Sebastian Hattinger
 *@version V1.0
 *@since 06.12.2015 
**/


public class Medium implements Media{

	private String name;
	private double price;
	public Medium(String name, double price){
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public void print(int depth) {
		String out = (this.getClass().getName() +"| " + "Name ="+getName() + ", Price ="+getPrice());

		//System.out.println(new String(new char[depth]).replace("\0", "    ") + "-------------");
		System.out.println(new String(new char[depth]).replace("\0", "    ") + out);
		//System.out.println(new String(new char[depth]).replace("\0", "    ") + "-------------");
	}

	public Double search(String name){
		if(this.name.equals(name)){
			return this.price;
		}else{
			return null;
		}
	}

}



