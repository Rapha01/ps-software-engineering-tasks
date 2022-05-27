/**
 * @team Pingert Michael, Sebastian Hattinger
 * @credits pvm30, https://github.com/pvm30
 */
package stateExploration.waterJarExploration;
import java.util.ArrayList;
import java.util.List;

import stateExploration.*;

public class WaterJarExploration extends Exploration {
	protected List<Move> moves;
	boolean noFill = false;

	public WaterJarExploration(int[] capacities) {	
		State initialState = new State(new int[capacities.length]);	
		initialize(capacities, initialState);
	}
	
	public WaterJarExploration(int[] capacities, String option) {
		State initialState = new State(new int[capacities.length]);
		
		if(option.equals("noFill")){
			noFill=true;
			initialState = new State(capacities);
		}
		initialize(capacities, initialState);
	}

	private void initialize(int[] capacities, State initialState){
		moves = getAllPossibleMoves(capacities);
		initialPaths.add(new Path(new ArrayList<State>(), new ArrayList<Move>(), initialState));
		explored.add(initialState);
		
		pathSets = explore(initialPaths, explored, moves);
	}

	private List<Move> getAllPossibleMoves(int[] capacities) {
		List<Move> moves = new ArrayList<Move>();

		for (int glass = 0; glass < capacities.length; glass++) {
			moves.add(new Empty(glass));
			if(!noFill)
				moves.add(new Fill(capacities, glass));
		}

		for (int from = 0; from < capacities.length; from++)
			for (int to = 0; to < capacities.length; to++)
				if (to != from)
					moves.add(new Pour(capacities, from, to));

		return moves;
	}


}

