/**
 * Created by 
 * Michael Pingert
 * Sebastian Hartinger
 * on 17.10.15.
 *
 * Userinput via the Scanner class.
 * Send the String to the method calculate() of the object myCalculator and get the result in return.
 */

import java.util.Scanner;
import java.util.NoSuchElementException;

public class main {
	public static void main(String[] args) {

		RPNcalculator myCalculator = new RPNcalculator();

		Scanner calcInput = new Scanner(System.in);
		System.out.print("Input: ");
		String input = calcInput.nextLine();

		System.out.println(myCalculator.calculate(input));

	}     
}
