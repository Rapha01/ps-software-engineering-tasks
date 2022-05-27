package a9;

/**
 * @team Pingert Michael, Sebastian Hattinger
 **/

public class Main {

    public static void main (String[] args) {

    	StudentList studentList = new StudentList();
    	
   	JavaSwingView view =  new JavaSwingView(studentList);
    JavaSwingView view2 = new JavaSwingView(studentList);
   	
   	
    javax.swing.SwingUtilities.invokeLater(view);
    javax.swing.SwingUtilities.invokeLater(view2);
    		
    
  	try {
		studentList.add("Robin", "Hood", "123");
		studentList.add("Onkel","Donald","456");
	    
	} catch (Exception e) {
		e.printStackTrace();
	}
  
    	
    }
}
