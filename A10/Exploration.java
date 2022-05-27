/**
 * @team Pingert Michael, Sebastian Hattinger
 * @credits pvm30, https://github.com/pvm30
 */
package stateExploration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** 
 * Exploration class for recursively exploring new states and solution finding
 */
public class Exploration {

	/** 
	 * Initialisation of
	 * Paths: One path consists of moves and states
	 * Pathsets: The sequence of all possible movements and the final states they lead to
	 * explored set: A Memory for not storing a second occurence of the same state twice
	 */
	protected List<List<Path>> pathSets = null;
	protected List<Path> initialPaths;
	protected Set<State> explored;

	// Constructor.. This 'capacities' array represents the different available glasses and their sizes...
	public Exploration() {
		initialPaths = new ArrayList<Path>();		
		explored = new HashSet<State>();
	}
	/** 
	 * The exploration iterates over every possible path to explore every possible move for it, 
	 * creating new paths for every move. Then recursively starting exploration on every one 
	 * of the new paths
	 */
	protected List<List<Path>> explore(List<Path> initialPaths,	Set<State> explored, List<Move> moves) {

		if (initialPaths.isEmpty()) {
			// Using Collections.EMPTY_LIST was throwing a UnsupportedOperationException
			// when prepending
			return new ArrayList<List<Path>>();
		}
		else {
			// This 'more' gives us the new set of paths after adding all
			// possible moves to all pre-existing paths not yet explored...
			List<Path> more = new ArrayList<Path>();
			for (Path path : initialPaths) {
				for (Move move : moves) {
					Path next = path.extend(move);
					if (!explored.contains(next.endState)) {
						more.add(next);
					}
				}
			}

			// Calling recursively and prepending...
			List<List<Path>> newPaths = explore(more, addToExplored(explored, more), moves);
			newPaths.add(0, initialPaths);

			return newPaths;
		}
	}

	private Set<State> addToExplored(Set<State> explored, List<Path> more) {
		Set<State> newExplored = new HashSet<State>(explored);

		for (Path path : more)
			newExplored.add(path.endState);

		return newExplored;
	}

	public String getShortestSolution(int target) {
		for (List<Path> pathSet : pathSets) {
			for (Path path : pathSet) {
				if (path.endState.contains(target)) {
					return path.toString();
				}
			}
		}
		return "No Solution fround";
	}
}