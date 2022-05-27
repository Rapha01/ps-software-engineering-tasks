
package a11;

public class Element  {
	
	/**
	 * @team Pingert Michael, Sebastian Hattinger
	 * class Element return: key, cellCount, xPos, yPos and bonusTrack
	 * and set key, xPos, yPos and bonusTrack
	 *
	 */
	
		public class Node {
			
			private int key;
			private int cellCount;
			
			//Parameter cellBlocker for further requirements
			//private String cellBlocker; 
			private String bonusTrack;
			private int xPos;
			private int yPos;
			
			public Node (int Key) {
				this.key = Key;	 
			}
			
	
			public Node (String bonusTrack) {
				this.bonusTrack = bonusTrack;
			}
				
			
			public int setCount(int cellCount) {
				this.cellCount = cellCount;
				return cellCount;
			}
			
			public void setXpos(int xPos) {
				this.xPos = xPos;
			}
			
			public void setYpos(int yPos) {
				this.yPos =yPos;
			}
			
			public int getXpos() {
				return xPos;
			}
			
			
			public int getYpos() {
				return yPos;
			}
			
			
			public void setBonus(String bonusTrack) {
				this.bonusTrack = bonusTrack;
			}
			
			public String getBonus() {
				return bonusTrack;
			}
			
			
			public int getCount(){
				return cellCount;
			}
					
			public int setkey (int key) {
				this.key = key;
				return key;
			
			}
			
			public int getkey () {
				return key;
			}

			public Node getNode () {
				return  this;
			}			
		}
	}
		


