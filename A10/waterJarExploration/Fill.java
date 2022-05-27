/**
 * @team Pingert Michael, Sebastian Hattinger
 * @credits pvm30, https://github.com/pvm30
 */
package stateExploration.waterJarExploration;
import stateExploration.*;

public class Fill extends AbstractMove{

	private final int[] capacities;

	public Fill(int[] capacities, int glass) {
		super(glass);
		this.capacities = capacities;
	}

	public State change(State state) {
		return state.updated(glassNr, capacities[glassNr]);
	}
}
