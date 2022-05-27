/**
 * @team Pingert Michael, Sebastian Hattinger
 * @credits pvm30, https://github.com/pvm30
 */
package stateExploration.waterJarExploration;
import stateExploration.*;

public class Empty extends AbstractMove {

	public Empty(int glass) {
		super(glass);
	}

	public State change(State state) {
		return state.updated(glassNr, 0);
	}
}
