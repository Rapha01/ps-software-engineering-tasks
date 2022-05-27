/**
 * @team Pingert Michael, Sebastian Hattinger
 * @credits pvm30, https://github.com/pvm30
 */
package stateExploration.hourGlassExploration;
import stateExploration.*;

public class TurnGlass extends AbstractMove{

	private final int[] capacities;

	public TurnGlass(int glass, int[] capacities) {
		super(glass);
		this.capacities = capacities;
	}

	public State change(State state) {
		state = timeFlow(state);
			
		state = state.updated(glassNr, capacities[glassNr] - state.getGlassState(glassNr));
		return state;
	}
}
