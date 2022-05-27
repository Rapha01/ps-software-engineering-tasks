package a9;

/**
 * @team Pingert Michael, Sebastian Hattinger
 * 
 * 
 **/

import java.util.LinkedList;

public class StudentList {
	private LinkedList<Student> studentList;
	private LinkedList<Listener> observerList;
	
	public StudentList () {
		studentList = new LinkedList<Student>();
		observerList = new LinkedList<Listener>();
	}
	
	public void add (String firstName, String lastName, String matNr) throws Exception {
		/**
		  if (!testMatnr(matNr)) {
	            throw new Exception("Matr.Nr. already exists");
	        }
		**/
		  
		Student student = new Student(firstName, lastName, matNr);
		for (Student tmp : studentList) {
			if (matNr.equals(tmp.getmatNr())) 
				 throw new Exception("Matr.Nr. already exists");

		}
		studentList.add(student);
		changed(EventHandler.ADD, studentList.size(), student);
	}
	
	
	
	
	
	public void remove (int index) {
		Student student = studentList.remove(index);
		changed(EventHandler.REMOVE, index, student);
	}
	
	public void rename (int idx, String firstName, String lastName, String matNr) {
		Student student = studentList.get(idx);
		student.setfirstName(firstName);
		student.setlastName(lastName);
		student.setmatNr(matNr);
		changed(EventHandler.RENAME, idx, student);
	}
	
	/**
	private boolean testMatnr(String matNr) {
        for (Student p : studentList) {
            if (matNr.equals(p.getmatNr())) {
                return false;
            }
        }
        return true;
    }
	
	**/
	public void addObserver (Listener observer) {
		observerList.add(observer);
	}
	
	private void changed (EventHandler type, int idx, Student student) {
		for (Listener observer : observerList)
			observer.update(type, idx, student);
	}
}
