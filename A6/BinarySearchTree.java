/**
 * @team Pingert Michael, Sebastian Hattinger
 */
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;


public class BinarySearchTree <T extends Comparable<T>,S>{

	public Node root;

	/**
	 * Inner Node Class for saving Data and reference to child nodes
	 */
	private class Node { 

		/**
		 * Variables for Inner Class 
		 */
		T key;        
		S value;
		Node left;   
		Node right; 
		int height=0;

		/**
		 * Node Constructor 1 for calling constructor 2
		 * with only head filled
		 */
		private Node(T key, S value) {
			this(key, value, null, null);
		}

		/**
		 * Node Constructor 2 for saving parameters
		 * head = data, left = left, right = right
		 * 	 
		 * every Node is an Object of this private class 
		 * NODE and is initialised via this second constructor
		 */
		private Node(T key, S value, Node left, Node right) { 
			this.key = key;
			this.value = value;
			this.left = left;
			this.right = right;
		}    
	}

	/**
	 * Constructor 1 for an empty tree 
	 */
	public BinarySearchTree() { 
		this.root = null;
	}

	/**
	 * Constructor 2 for array insertion
	 */
	public BinarySearchTree(LinkedHashMap<T,S> map) {
		// if array is empty make empty tree
		if(map.isEmpty())
			this.root = null;
		else {		 
			for(Map.Entry<T,S> entry : map.entrySet()) {
				T key = entry.getKey();
				S value = entry.getValue();
				this.insert(key, value);
				//System.out.println(key + " => " + value);
			}
		}
	}


	/**
	 *	runs method insert
	 */
	public void insert(T key, S value) {
		root = this.insert(root, key, value);	
	}

	/**
	 *runs search method 
	 */
	public Node search(T key) throws NoSuchElementException {
		Node missingNode = search(key, root);
		if(missingNode !=null){
			return missingNode;
		}
		else{
			throw new NoSuchElementException();
		}
	}

	/**
	 *runs Remove method 
	 */
	public void remove(T key) {
		if (search(key) != null){		     
			root =  remove(root,key);
		}
	}

	/**
	 * Search Method 
	 * runs search method with searched piece and root  
	 * find a node beginning to search from the rootNode
	 */
	public Node search(T key, Node rootNode) {     
		if (rootNode == null)                          
			return null;
		// search in left child if piece < root	
		else if (key.compareTo(rootNode.key) < 0)    
			return search(key, rootNode.left); 
		//return piece if piece == root	
		else if (key.compareTo(rootNode.key) == 0)   
			return rootNode;
		// search in right child if piece > root		
		else
			return search(key, rootNode.right);        
	}


	/**
	 * Remove Method
	 */ 
	public Node remove( Node rootNode, T key ){
		// if tree is empty
		if ( rootNode == null ) {			
			return null;
		}
		// check node to be removed 
		if (search(key).key == rootNode.key ){
			// check if left child is empty
			// and return the right subtree
			if(rootNode.left == null){
				return rootNode.right;
			} 
			//check if right child is empty
			//and return the left subtree
			else if(rootNode.right == null){
				return rootNode.left; // return remove(rootNode.left,Piece);
			}
			else { //both the children exists
				Node successor = getSuccessorNode(rootNode.left);
				rootNode.key = successor.key;
				rootNode.left = remove(rootNode.left, successor.key);
			}
		}
		// if node to be deleted <= root
		// then go left   
		else if (search(key).key.compareTo(rootNode.key) <= 0){				
			rootNode.left = remove(rootNode.left, key);
		}
		// if node to be deleted > root
		// then go right
		else {
			rootNode.right = remove(rootNode.right, key);
		}
		return rootNode;
	}

	/**
	 * get successor method 
	 * to get the predecessor of the current node
	 */

	public Node getSuccessorNode( Node rootNode){
		// get maximum of the left subtree //
		// or minimum of right sub tree 
		while(rootNode.right != null){
			rootNode = rootNode.right;
		}
		return rootNode;
	}

	/**
	 * Insert Method runs insert method with recursive
	 * changing rootNode and "toIns(erted)" piece
	 * returns inserted Node
	 */
	public Node insert(Node rootNode, T key, S value) { 

		// if root is empty create root 
		//with given value
		if (rootNode == null){                   
			rootNode = new Node(key, value, null, null);
			return rootNode;
		}
		int comparison = rootNode.key.compareTo(key);
		// insert left if node < root
		// and left is empty 
		if(comparison >= 0){
			if(rootNode.left == null){
				rootNode.left= new Node(key, value);
			}
			// if left is not empty
			// call insert on left again till find empty place
			else {
				insert (rootNode.left , key, value);
			}
		}
		//if node > root and right is empty 
		// then insert right 
		else {
			if(rootNode.right == null){
				rootNode.right = new Node(key, value);
			}
			//if node > root and right is not empty 
			// then call insert on right till find empty place
			else{
				insert(rootNode.right , key, value);
			}
		}
		return rootNode;
	} 


	/**
	 * Draw Method Of The Tree
	 * 
	 * Method for drawing Ovals + their lines 
	 * Input is a Node, the graphics element,
	 * x , y, x+ , y+ and sideDepth
	 * x+ and y+ stand for next coordinate and 
	 * sideDepth for sideDepth of the tree  
	 */
	public void drawme(Node drawNode, Graphics g, int x, int y, int xNext, int yNext, int sideDepth)
	{
		//if (drawNode != null){
		// y coordinate intervall
		int downDepth = 65;
		// radia of element circles
		int radia = 11;

		// Draw Element Circle 
		int messedHeight = root.height - drawNode.height-1;
		//g.drawOval(x , y , radia * 3 , radia * 3);

		// Write number into circle (dependence of string length) 
		int stringPosition = String.valueOf(drawNode.key).length() * 3;
		g.drawString( String.valueOf(drawNode.key) +","+String.valueOf(drawNode.value) , x + radia - stringPosition - 2 , y + radia + 8 );
		// Draw Line to next Point (if existing (height != 0)) 
		if (messedHeight != 0){
			g.drawLine(x + radia*2, y, xNext + radia*2, yNext+10);
		}   
		// recursive call for drawing left and right child
		// left child
		if (drawNode.left != null){
			drawme(drawNode.left , g, /*X: */ x - ( 800 / (int)Math.pow(2, sideDepth+2) ), /*Y: */ y + downDepth, /*X+: */ x , /*Y+: */ y + radia * 2, sideDepth+1);
		}
		// right child
		if (drawNode.right != null){
			drawme(drawNode.right, g, /*X: */ x + ( 800 / (int)Math.pow(2, sideDepth+2) ), /*Y: */ y + downDepth, /*X+: */ x , /*Y+: */ y + radia * 2, sideDepth+1);
		}
		//}
	}

}