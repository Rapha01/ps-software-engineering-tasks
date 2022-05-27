/**
 * @team Pingert Michael, Sebastian Hattinger
 * @credits pvm30, https://github.com/pvm30
 */
package stateExploration.hourGlassExploration;
import java.util.ArrayList;
import java.util.List;

import stateExploration.*;

public class HourGlassExploration extends Exploration {
	protected List<Move> moves;
	
	public HourGlassExploration(int[] capacities) {
		
		int[] copy = new int[capacities.length+1];
		System.arraycopy(capacities, 0, copy, 1, capacities.length);
		capacities = copy;
		
		moves = getAllPossibleMoves(capacities);
		State initialState = new State(new int[capacities.length]);	
		
		initialPaths.add(new Path(new ArrayList<State>(), new ArrayList<Move>(), initialState));
		explored.add(initialState);
		
		pathSets = explore(initialPaths, explored, moves);
	}

	private List<Move> getAllPossibleMoves(int[] capacities) {
		List<Move> moves = new ArrayList<Move>();
		moves.add(new TurnGlass(1, capacities));
		moves.add(new TurnGlass(2, capacities));
		moves.add(new TurnBothGlasses(1, capacities));
		return moves;
	}


}
