import java.io.*;



public class Main {

	public static void main(String[] args) {
		
		Archive test = ComposeMediaBib.xmlToComposite(new File("input.txt"));
		test.print();
		System.out.println(test.search("B2"));
	}
}	
	
	
	
	
	