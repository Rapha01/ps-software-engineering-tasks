/**
 * @team Pingert Michael, Sebastian Hattinger
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.*;
import java.util.LinkedHashMap;

public class TreeDemo {
	public static BinarySearchTree<Integer,String> tree1 = null;
	
	public static void main(String[] args) {

		/**
		 * System readIn
		 * (as <Integer,String>)
		 */
		
		String readIn = "";
		String[] readInParts = {""};
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(input);	
		LinkedHashMap<Integer,String> testMap = new LinkedHashMap<Integer,String>();
		
		System.out.println("Type key,value List separated by comma: (ex.: 11,hallo,5,welt,8,auto,..)");
		try{
			readIn = reader.readLine(); 
		}catch(IOException ex){}
		readInParts = readIn.split(",");
		
		try{
			for (int i = 0; i < readInParts.length; i=i+2) {
				try{
					testMap.put(Integer.parseInt(readInParts[i].trim()), readInParts[i+1]);
				}
				catch(NumberFormatException ex){
					System.err.println("unproper Formatting");System.exit(0);
				}
			} 
		}
		catch(ArrayIndexOutOfBoundsException ex){
			System.err.println("unproper Formatting");System.exit(0);
		}
		
		/**
		 * Creation of the Tree and its draw
		 * Also possible to create empty tree and fill it with:
		 * 
		 * tree1 = new BinarySearchTree<Integer,String>();
		 * tree1.insert(10,"a");
		 * tree1.insert(1,"b");
		 * tree1.insert(5,"c");
		 * 
		 * and check for functional generics:
		 * 
		 * tree1 = new BinarySearchTree<Double,Integer>();
		 * tree1.insert(1.5,1);
		 * tree1.insert(2.4,66);
		 * tree1.insert(2.2,23);
		 */
		
		tree1 = new BinarySearchTree<Integer,String>(testMap);
		TreeGUI gui = new TreeGUI();


		/**
		 * Tree editing loop, while not empty.
		 * Ask for the element to be removed or inserted, do it, and draw a new tree
		 * (only implemented with <Integer,String>)
		 */
		Integer IntInput = 0;

		while(tree1.root != null){
			System.out.println("Which element should be removed (enter just a key) or inserted (enter key,value)?");
			try{
				readInParts = reader.readLine().split(",");
			}
			catch(IOException ex){}

			if(readInParts.length == 2){
				try{
					tree1.insert(Integer.parseInt(readInParts[0]),readInParts[1]);
				}
				catch(NumberFormatException ex){System.err.println("wrong key format");}
			}
			else{	
				try
				{		
					IntInput = Integer.parseInt(readInParts[0]);
					tree1.remove(IntInput);				
				}
				catch(NumberFormatException ex){System.err.println("wrong key format");}
			}

			if(tree1.root != null){
				gui.update();
			}
			else{
				System.out.println("Tree is empty");
				System.exit(0);
			}
		}
	}

	
	/**
	 * Draw method gives root as input, g as graphical interface,
	 * 400 as first x coordinate, 20 as first y coordinate,
	 * x+ = 0; y+ = 0
	 */
	public static void draw(Graphics g){
		tree1.drawme(tree1.root,g, 400, 20, 0, 0,0);
	}

}
