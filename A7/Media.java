
/**
 *Interface for Media
 *
 *@author Michael Pingert
 *@author Sebastian Hattinger
 *@version V1.0
 *@since 06.12.2015 
**/



public interface Media {
	public String getName();
	public double getPrice();
	public void print(int depth);
	public Double search(String name);
}