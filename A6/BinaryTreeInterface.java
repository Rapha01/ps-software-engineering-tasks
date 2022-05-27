/**
 * @team Pingert Michael, Sebastian Hattinger
 */
public interface BinaryTreeInterface<T extends Comparable<T>,S> {
		
		class Node{	
		}
	
        /**
         * Look up data in the tree.
         * 
         * @return the requested object, or null if not existent
         */
        public Node search(T key);

        /**
         * Add data to the tree.
         */
        public void insert(T key, S value);

        /**
         * Remove data from the tree, if existent.
         */
        public void remove(T key);

}

