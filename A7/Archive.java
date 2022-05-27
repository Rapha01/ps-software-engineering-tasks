import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *In the Class Archive different Media types can be stored or retrieved
 *The Interface Media is implemented in this Class 
 *
 *@author Michael Pingert
 *@author Sebastian Hattinger
 *@version V1.0
 *@since 06.12.2015 
**/



public class Archive implements Media{


	private String name;
	private List<Media> mediaList = new ArrayList<>();

	/**
	 * 
	 * @param name
	 */
	
	public Archive(String name){
		this.name = name;
	}
	/**
	 * 
	 * @param media
	 */
	public void add(Media media) {
		mediaList.add(media);
	}


	public Media getChild(int i) {
		return mediaList.get(i);
	}

	
	public String getName() {
		return name;
	}


	public double getPrice() {
		Double sum = new Double(0.0);
	
		Iterator<Media> mediaIterator = mediaList.iterator();
		while(mediaIterator.hasNext()){
			Media media = mediaIterator.next();
			sum += media.getPrice();
		}
		return sum;
	}
	
	public void print() {
		int depth=0;
		print(depth);
	}
	

	
	public void print(int depth) {
		String out = (this.getClass().getName() +"| " + "Name ="+getName() + ", Price ="+getPrice());


		//System.out.println(new String(new char[depth]).replace("\0", "    ") + "-------------");
		System.out.println(new String(new char[depth]).replace("\0", "    ") + out);
		//System.out.println(new String(new char[depth]).replace("\0", "    ") + "-------------");
		
		depth++;
		Iterator<Media> mediaIterator = mediaList.iterator();
		while(mediaIterator.hasNext()){
			Media media = mediaIterator.next();
			media.print(depth);
		}
	}
	
	public Double search(String name){
		Double price;
		Iterator<Media> mediaIterator = mediaList.iterator();
		
		while(mediaIterator.hasNext()){
			Media media = mediaIterator.next();		
			price = media.search(name);
			if(price != null){
				return price;
			}
		}
		return null;
	}
	
}
