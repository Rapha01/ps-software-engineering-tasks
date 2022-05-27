/**
 * Created by 
 * Michael Pingert
 * Sebastian Hattinger
 * on 17.10.15.18.10.15
 *
 * RPNCalculator object for calculating reverse polish notation String terms.
 * 
 * calculate(): calculates a String of a RPN form into a double.
 */
package ps_se_ws2015.hattinger;
import java.util.NoSuchElementException;
import javax.xml.soap.Node;

public class RPNcalculator<T>{

	StackArray rpnStack = new StackArray();
	// if Array calculator is needed:
	//StackLinkedList rpnStack = new StackLinkedList();

	public double calculate(String input){
		String delims = "(?<=[+\\-*/\\^ ]+)|(?=[+\\-*/\\^ ]+)";  //so the delimiters are:  + - * / ^ space
		String[] tokens = input.split(delims);
		boolean valid  = false;

		for (int i = 0; i < tokens.length; i++) {
			if (tokens[i].equals("+"))
				valid = addition();
			else if (tokens[i].equals("-")) 
				valid = subtraktion();
			else if (tokens[i].equals("/")) 
				valid = division();
			else if (tokens[i].equals("*")) 
				valid = multiplikation();
			else if (tokens[i].equals(" "))
				;
			else if (tokens[i].matches("\\d+\\.\\d+|\\d")) 
				rpnStack.push(Double.parseDouble(tokens[i]));		
			else throw new NoSuchElementException("There was an mistake in the " +
					"reverse-polish-notation or to less input");
		}

		if (valid == true){	
			return (double)rpnStack.pop();
		}
		else throw new NoSuchElementException("No calculation available, please consider " +
				"to put arithmetic operation like 1.5 2 + 3 4 - *");

	}


	public boolean addition () {
		double secondOperand = (double)rpnStack.pop();
		double firstOperand = (double)rpnStack.pop();
		rpnStack.push(firstOperand + secondOperand);
		return true;
	}

	public boolean subtraktion () {	
		double secondOperand = (double)rpnStack.pop();
		double firstOperand = (double)rpnStack.pop();
		rpnStack.push(firstOperand - secondOperand);
		return true;
	}

	public boolean  multiplikation () {
		double secondOperand = (double)rpnStack.pop();
		double firstOperand = (double)rpnStack.pop();
		rpnStack.push(firstOperand*secondOperand);
		return true;
	}

	public boolean division() {
		double secondOperand = (double)rpnStack.pop();
		double firstOperand = (double)rpnStack.pop();
		rpnStack.push(firstOperand/secondOperand);
		return true;
	}

}
