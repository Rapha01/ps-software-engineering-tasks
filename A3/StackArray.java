/**
 * Created by 
 * Michael Pingert
 * Sebastian Hartinger
 * on 17.10.15.
 *
 * A generic stack, implemented using a array [].
 * 
 * resize(): extend or shrink array size depending on elements.
 * pop(): get the first element of the stack.
 * push(): put a element on the stack.
 */
package ps_se_ws2015.hattinger;
import java.util.NoSuchElementException;

public class StackArray <T> implements Stack<T>  {
	private T[] array;
	private int total;

	public StackArray() {
		array = (T[]) new Object[2];
	}

	private void resize(int size) {
		T[] tmp = (T[]) new Object[size];
		System.arraycopy(array, 0, tmp, 0, total);
		array= tmp;
	}

	public void push(T ele) {
		if (array.length == total) resize(array.length*2);
		array[total++] = ele;
	}

	public boolean isEmpty() {
		return total == 0;
	}

	public T pop () {
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		T element = array[--total];
		array[total] = null;
		if (total > 0 && array.length == array.length/4) resize(array.length/4);
		return element;
	}
}