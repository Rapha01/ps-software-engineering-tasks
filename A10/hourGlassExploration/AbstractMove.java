/**
 * @team Pingert Michael, Sebastian Hattinger
 * @credits pvm30, https://github.com/pvm30
 */
package stateExploration.hourGlassExploration;

import stateExploration.*;

/**
 * All the properties that moves have in common
 */
public abstract class AbstractMove implements Move {

	protected final int glassNr;
	
	//highest possible overall time for the paths/solution. to avoid infinite loops
	protected int lock = 100;
	
	protected AbstractMove(int fromGlass) {
		this.glassNr = fromGlass;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "(" + glassNr + ")";
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
		if (glassNr != other.glassNr)
			return false;
		return true;
	}
	
	/**
	 * The simulation of the flow of time of 1 step (until one glass is empty)
	 */
	public State timeFlow(State state) {
		State temp = new State(null);
		temp = state;
		
		if(state.state[1] < state.state[2]){
			if(state.state[1] != 0){
				temp = temp.updated(0, state.state[0] + state.state[1]);
				temp = temp.updated(1, 0);
				temp = temp.updated(2, state.state[2] - state.state[1]);	
			}
			else{
				temp = temp.updated(0, state.state[0] + state.state[2]);
				temp = temp.updated(2, 0);
				temp = temp.updated(1, 0);
			}
		}
		
		if(state.state[2] < state.state[1]){
			if(state.state[2] != 0){
				temp = temp.updated(0, state.state[0] + state.state[2]);
				temp = temp.updated(2, 0);
				temp = temp.updated(1, state.state[1] - state.state[2]);
			}
			else{
				temp = temp.updated(0, state.state[0] + state.state[1]);
				temp = temp.updated(2, 0);
				temp = temp.updated(1, 0);
			}					
		}
		
		if(state.state[2] == state.state[1]){
			temp = temp.updated(0, state.state[0] + state.state[1]);
			temp = temp.updated(2, 0);
			temp = temp.updated(1, 0);
		}
		
		if(temp.state[0] > lock){
			temp = temp.updated(0, lock);
		}
		
		return temp;
	}

}

