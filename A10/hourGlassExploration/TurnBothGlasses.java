/**
 * @team Pingert Michael, Sebastian Hattinger
 * @credits pvm30, https://github.com/pvm30
 */
package stateExploration.hourGlassExploration;
import stateExploration.*;

public class TurnBothGlasses extends AbstractMove{

	private final int[] capacities;

	public TurnBothGlasses(int glass, int[] capacities) {
		super(glass);
		this.capacities = capacities;
	}

	public State change(State state) {
		state = timeFlow(state);

		state = state.updated(1, capacities[1] - state.getGlassState(1));
		state = state.updated(2, capacities[2] - state.getGlassState(2));
		return state;
	}
}
