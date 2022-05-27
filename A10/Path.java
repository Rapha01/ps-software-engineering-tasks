/**
 * @team Pingert Michael, Sebastian Hattinger
 * @credits pvm30, https://github.com/pvm30
 */
package stateExploration;
import java.util.ArrayList;
import java.util.List;

import stateExploration.*;
import stateExploration.hourGlassExploration.AbstractMove;

/**
 *  Paths: sequences of moves and final state they produce. 
 *  The last move in the path appears first in the history
 */
public class Path {

	public final List<Move> moveHistory;
	public final List<State> stateHistory;
	public final State endState;

	public Path(List<State> stateHistory, List<Move> moveHistory, State endState) {
		this.moveHistory = moveHistory;
		this.stateHistory = stateHistory;
		this.endState = endState;
	}

	// Adding a new Move to the Path...
	public Path extend(Move move) {
		List<Move> extendedMoveHistory = new ArrayList<Move>(moveHistory);
		extendedMoveHistory.add(0, move);
		List<State> extendedStateHistory = new ArrayList<State>(stateHistory);
		extendedStateHistory.add(0, endState);
		return new Path(extendedStateHistory ,extendedMoveHistory, move.change(endState));
	}

	@Override
	public String toString() {

		StringBuffer stringBuffer = new StringBuffer();

		for (int i = moveHistory.size() - 1; i >= 0; i--) {
			Move move = moveHistory.get(i);
			State state = stateHistory.get(i);
			
			stringBuffer.append(state.toString()+ " - ");
			stringBuffer.append(move.toString() + "\n");
		}

		return stringBuffer.append(" arrives to " + endState.toString()).toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractMove other = (AbstractMove) obj;
		if (endState.equals(other))
			return false;
		return true;
	}
}

