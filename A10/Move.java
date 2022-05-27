/**
 * @team Pingert Michael, Sebastian Hattinger
 * @credits pvm30, https://github.com/pvm30
 */
package stateExploration;

/**
 * A Move is everything that alters a State (Pour, Empty, ...)
 */
public interface Move {
	State change(State state);
}