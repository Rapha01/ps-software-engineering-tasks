
package a11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @team Pingert Michael, Sebastian Hattinger
 * 
 *		Class Game implements the main functionality of the game 2048
 *		a random Generator will insert Elements (Keys) into a 4x4 or bigger 2D Game Board
 *		When you play the Game sometimes a Bonus Cell appears '@' where you can get 10 extra Points  
 */


public class Game2048 extends Element {

	
	
	ArrayList<ArrayList<Node>> gamelist = new ArrayList <ArrayList<Node>>();
	static final int BONUS = 10;	
	boolean fillstate = false;	
	boolean vertikal = false;
	int gameSize = 0;
	int totalCount = 0;
	int cellCount =0;
	int xPos = 0;
	int yPos = 0;
	

	public Game2048(int gameSize) {
		this.gameSize = gameSize;
		initGame();
	}
	
	/**
	 * 
	 * @return
	 * 		The total Game Score will be returned
	 */

	public int getCount() {
		return totalCount;
	}
	
	
 	private void addList() {
		for (int i=0;i<gameSize;i++) {
			ArrayList <Node> Line = new ArrayList <Node>();
			gamelist.add(Line);										
		}
	}

 	
 	/**
 	 * Initialize the Game, fill the gamelist (ArrayList's) with '0' Keys 
 	 */
 	
	private void initGame() {
		addList();	
		for (int i = 0; i < gameSize; i++) {
			for (int j =0; j<gameSize; j++) {
				Node tmp1 = new Node (0); 
				gamelist.get(j).add(i,tmp1);						
			}			
		}	
	}
	
	/**
	 * @return
	 *		 If every free position of the Game Board was filled, the fillstate = false
	 *		 otherwise the state true will returned 
	 */
	
	
	public boolean checkfillstate() {
		fillstate = false;
		for (int i = 0; i < gameSize; i++) {
			for (Element.Node index : gamelist.get(i))
				if (index.getkey() == 0)
					return true;
		}
		return false;
	}
	

	private boolean freePos() {
		xPos= (int) (Math.random()* gameSize);
		yPos= (int) (Math.random()* gameSize);		
		while (gamelist.get(yPos).get(xPos).getkey() != 0) {
			this.xPos= (int) (Math.random()* gameSize);
			this.yPos= (int) (Math.random()* gameSize);		
		}
		return true;	
	}
	
	/**
	 * @param bonusCell
	 * 		generate BonusCells '@', for instance 10% distribution
	 * 
	 * @param keys
	 * 		generate Keys, for instance 10% '4' and 90% '2' distribution
	 * 
	 * @return
	 * 		return true when ready with generating keys and bonusCells				
	 */

	public boolean randomFill(String bonusCell, int keys) {
		if (checkfillstate()) {
			freePos();
			
	  			if (bonusCell.equals("n")) {
					Node newkey = new Node(keys);
					gamelist.get(yPos).set(xPos,newkey);
				}
				if (bonusCell.equals("@")) {
					Node newBonus = new Node("@");
					gamelist.get(yPos).set(xPos,newBonus);
					freePos();
					Node newkey = new Node(keys);
					gamelist.get(yPos).set(xPos,newkey);			
				}		
		}
		return true;
	}
	
	
	private Node searchBonusCell() {
		Node bonusCell = null;
		for (int j = 0; j < gameSize; j++)
			for (int i = 0; i < gameSize; i++) {
				if (gamelist.get(j).get(i).getBonus() == "@") {
					gamelist.get(j).get(i).setYpos(j);
					gamelist.get(j).get(i).setXpos(i);
					bonusCell = gamelist.get(j).get(i);
				}
			}
	
		return bonusCell;
	}
	
	
	private void resetBonus(int row) {		
		Node bonusCell = searchBonusCell();
		if (bonusCell != null && row == gameSize-1) 
			gamelist.get(bonusCell.getYpos()).get(bonusCell.getXpos()).setBonus("n");
	}
	
	
	private boolean checkBonus(int oldpos, int newpos, int row) {
		Node bonusCell = searchBonusCell();
		if (bonusCell != null)
			if (vertikal == true && bonusCell.getXpos() == row || vertikal == false && bonusCell.getYpos() == row) {
			if (oldpos >= bonusCell.getXpos() && bonusCell.getXpos() >= newpos) {
				System.out.println("BONUS !!!!");
				gamelist.get(bonusCell.getYpos()).get(bonusCell.getXpos()).setBonus("n");
				return true;
			}
		}	
		resetBonus(row);
		return false;
	}

	
	/**
	 * 
	 * @param nodelist
	 * 			the  List with Elements (Keys, BonusCells, etc.), as well the cellCount and 
	 * 			totalCount will be increased by every movement
	 *   
	 * @param row
	 * 			the gameList have horizontal and vertical lists, this parameter represent
	 * 			the x-list for horizontal movement and y-List for vertical movement.
	 * 
	 * @return
	 * 			true when finished 			
	 * 
	 */
	
	private boolean leftshift(List<Node> nodelist, int row) {
		Node help;
		for (int i = 0; i < nodelist.size(); i++) {
			for (int j = i; j < nodelist.size(); j++) {						
				if (nodelist.get(j).getkey() != 0) {					
					if (checkBonus(j,i,row))
						totalCount=totalCount+BONUS;
					help = nodelist.get(i);
					cellCount = nodelist.get(j).getCount() + j - i;	
					nodelist.set(i, nodelist.get(j));
					nodelist.set(j, help);
					nodelist.get(i).setCount(cellCount);
					j = nodelist.size();					
				}
			}

		}
		return true;
	}
	
		
	private boolean copyrightNodes(List <Node> nodelist,int xline) {	
		Collections.reverse(nodelist);
		copyleftNodes(nodelist,xline);
		Collections.reverse(nodelist);
		return true;
	}
	
	
	private boolean copyleftNodes(List <Node> nodelist,int xline) {
		Node tmp=new Node(0);
		leftshift(nodelist, xline);
		
		for (int i=0;i<nodelist.size()-1;i++) {
			if (nodelist.get(i).getkey()==(nodelist).get(i+1).getkey()&&(nodelist.get(i).getkey()!=0)) {		
				cellCount = nodelist.get(i+1).getCount()+nodelist.get(i).getCount();
				nodelist.set(i+1, tmp);
				totalCount = totalCount+nodelist.get(i).setkey(nodelist.get(i).getkey()*2);
				nodelist.get(i).setCount(cellCount+1);
				leftshift(nodelist, xline);
			}
	}
		return true;			
	}
	
	
	private List<Node> vertikal (int row) {
		vertikal = true;
		ArrayList<Node> vertikalList = new ArrayList<Node>();
		for (int i = 0; i < gameSize; i++) 
			vertikalList.add(gamelist.get(i).get(row));
		return vertikalList;
	}
	
	
	//generate a new Vertikal list with the content of the horizontal lists
	private void shiftUp(int row, List <Node> vertikalList) {	
		copyleftNodes(vertikalList, row);
		for (int i = 0; i < gameSize; i++)
			gamelist.get(i).set(row, vertikalList.get(i));
	}

	
	private void shiftdown (int row, List <Node> vertikalList ) {
		copyrightNodes(vertikalList, row);
		for (int i = 0; i < gameSize; i++)
			gamelist.get(i).set(row, vertikalList.get(i));	
	}

	
	public void shiftupAll() {
		for (int row = 0; row < gameSize; row++) {
			shiftUp(row, vertikal(row));
		}
	}
	
	
	public void shiftdownAll() {
		for (int row = 0; row < gameSize; row++) {
			shiftdown(row, vertikal(row));
		}
	}
	
	
	public void leftShiftall() {
		vertikal = false;
		for (int i = 0; i < gameSize; i++) {
			copyleftNodes(gamelist.get(i), i);
		}

	}
	
	public void rightShiftall () {
		vertikal = false;
		for (int i=0;i<gameSize;i++)
			copyrightNodes(gamelist.get(i),i);
	}
	
	
	private void printList(List <Node> nodelist) {
		 for (int i = 0; i < nodelist.size(); i++) {
			 if  (nodelist.get(i).getBonus()!="@")
				 System.out.format("|"+"%4s",String.valueOf(nodelist.get(i).getkey()).replaceFirst("0", ""));
			 else if (nodelist.get(i).getBonus()=="@") {
				 System.out.print(String.format("|"+"%4s",nodelist.get(i).getBonus().replace(' ','0' )));
			 }
		 }		  
		 System.out.println("|"+"\n");
	}

	/**
	 * print out the Game 
	 */
	
	public void print() {
		int i=0;	
		while (i<gameSize){ 
			printList(gamelist.get(i));
			i++;
		}	
		System.out.println("Points: "+totalCount+"\nCellShiftCounter for Cell 1x1: "+gamelist.get(0).get(0).getCount());
		
	}
}
