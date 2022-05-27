/**
 * @team Pingert Michael, Sebastian Hattinger
 */

package a11;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main extends Element {

	public static void main(String args[]) {

		Game2048 game = new Game2048(4);
		RandomNumberGenerator<Integer> keys = new RandomNumberGenerator<Integer>();
		RandomNumberGenerator<String> blocks = new RandomNumberGenerator<String>();

		blocks.addNumber("n", 90);
		blocks.addNumber("@", 10);
		keys.addNumber(2, 90.0);
		keys.addNumber(4, 10.0);
		game.randomFill(blocks.getDistributedRandomNumber(), keys.getDistributedRandomNumber());
		Scanner scanner = new Scanner(System.in);
	
		System.out.println("Welcome to the Game 2048 use 'a' 'd' 'w' 'x' for Your moves\n");
		game.print();
		
		
		while (game.checkfillstate() || game.getCount() >= 2048) {
			String command = scanner.nextLine();
			
			if (Pattern.matches("[adwx]", command)) {
				if (command.equals("a")) {
					game.leftShiftall();
				} else if (command.equals("d"))
					game.rightShiftall();
				else if (command.equals("w"))
					game.shiftupAll();
				else if (command.equals("x"))
					game.shiftdownAll();
				game.randomFill(blocks.getDistributedRandomNumber(), keys.getDistributedRandomNumber());
				game.print();
				
			} else 
				System.out.println("Bitte zur Steuerung die Zeichen 'a' 'd' 'x' 'w' verwenden");
			
		}
			if (game.getCount() >= 2048) 
				System.out.println("Yea You Win !!!!!");
			else
				System.out.println("Game Over");
		scanner.close();

		}
	
	}

