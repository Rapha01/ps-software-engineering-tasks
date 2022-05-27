/**
 * @team Pingert Michael, Sebastian Hattinger
 * @credits pvm30, https://github.com/pvm30
 */
package stateExploration;

import java.util.Arrays;

/**
 * Class describing the containers contents at any time
 */
public class State {

	public final int[] state;

	public State(int[] state) {
		this.state = state;
	}

	public State updated(int glass, int newValue) {
		int[] copy = new int[state.length];
		System.arraycopy(state, 0, copy, 0, state.length);
		copy[glass] = newValue;

		return new State(copy);
	}

	public int getGlassState(int glass) {
		return state[glass];
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(state);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (!Arrays.equals(state, other.state))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "(" + getListString() + ")";
	}

	private String getListString() {
		StringBuffer stringBuffer = new StringBuffer();

		for (int i = 0; i < state.length; i++) {
			if (i == state.length - 1) {
				stringBuffer.append(state[i]);
			} else {
				stringBuffer.append(state[i] + ",");
			}
		}

		return stringBuffer.toString();
	}

	public boolean contains(int target) {
		for (int i : state) {
			if (i == target) {
				return true;
			}
		}

		return false;
	}
}