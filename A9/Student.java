package a9;
/**
 * @team Pingert Michael, Sebastian Hattinger
 * 
 * 
 **/

public class Student {

	private String firstName;
	private String lastName;
	private String matNr;

	public Student (String firstName, String lastName, String matNr) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.matNr = matNr;
	}
	
	public String getfirstName() {
		return firstName;
	}

	public String getlastName() {
		return lastName;
	}

	public String getmatNr() {
		return matNr;
	}

	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setlastName(String lastName) {
		this.lastName = lastName;
	}

	public void setmatNr(String matNr) {
		this.matNr = matNr;
	}

}
