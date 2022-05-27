package a9;
/**
 * @team Pingert Michael, Sebastian Hattinger
 * 
 * 
 **/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;



public class JavaSwingView extends javax.swing.JFrame implements Runnable, Listener, ActionListener {
	private static final long serialVersionUID = 1L;
		
	JList<String> list;
	DefaultListModel<String> listModel;
	StudentList studentList;
		
	/**
	 * 
	 * @param studentList
	 * Class JavaSwingView small GUI application were you can add, delete and rename 
	 * the (first name, second name and Matr. Nr.)
	 * 		
	 */
	
	
    public JavaSwingView(StudentList studentList) {
    	
    	this.studentList = studentList;
		studentList.addObserver(this);
        initComponents();
    }
    
    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;  
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration                   
	
      
    private void initComponents() {
    	
        jScrollPane2 = new javax.swing.JScrollPane(); 
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
     
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        listModel = new DefaultListModel<String>();    
        list = new JList<String>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        
        jScrollPane2.setViewportView(list);

        
        jButton1.setText("add");
        jButton1.setActionCommand("add");
  
        jButton2.setText("delete");
        jButton2.setActionCommand("delete");
              
        jButton3.setText("rename");
        jButton3.setActionCommand("rename");
               
		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String eingabe = JOptionPane.showInputDialog("Input name surname matNr");
				if (eingabe != null) {
					String[] split = eingabe.split(" ");
					if (split.length == 3) {

						try {
							studentList.add(split[0], split[1], split[2]);
						} catch (Exception x) {
							exceptionWrongInput(x.getMessage());
						}
					}else {
						exceptionWrongInput("Illegal input!");
					}
				}
			}
		});
		
		jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idx = list.getSelectedIndex();
				try {
					studentList.remove(idx);
				} catch (Exception y) {
					exceptionWrongInput("Nothing to delete");
				}

			}
		});

		jButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idx = list.getSelectedIndex();
				System.out.println(idx);
				if (idx >= 0) {
					String eingabe = JOptionPane.showInputDialog("name surname matNr");
					if (eingabe != null) {
						String[] split = eingabe.split(" ");
						if (split.length == 3) {
							studentList.rename(idx, split[0], split[1], split[2]);
						}
					}

				} else {
					exceptionWrongInput("Nothing to reneame");
				}
			}
		});

		jLabel2.setText("Created by Pingert & Hattinger");

        // Layout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jLabel2))
        );

        pack();
    }                        
     

    @Override
    public void update (EventHandler type, int index, Student student) {
    	switch (type) {
    	case ADD:
    		listModel.addElement(student.getfirstName()+" "+student.getlastName()+" "+student.getmatNr());
    	break;
    	case REMOVE:
    		listModel.remove(index);
    		break;
    	case RENAME:
    		listModel.set(index, student.getfirstName() + " " + student.getlastName() + " " + student.getmatNr());
    	}
    
    }

   
	 private void exceptionWrongInput(String message) {
	        JOptionPane.showMessageDialog(this, message);
	    }
	
	 @Override
	    public void run () {
			setVisible(true);	
	    }

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Action1");		
	}	    
}
