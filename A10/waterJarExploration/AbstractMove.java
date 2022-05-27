/**
 * @team Pingert Michael, Sebastian Hattinger
 * @credits pvm30, https://github.com/pvm30
 */
package stateExploration.waterJarExploration;
import stateExploration.*;

/**
 * All the properties that moves have in common
 */
public abstract class AbstractMove implements Move {

	protected final int glassNr;

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

}