/*Deyora Perera
  Course: ICS4U
  Assignment: Sort and Search Assignment
  Due date: April 6
  Program Description: Using Binary Search and Insertion sort to create student database which can search students using key fields */
//imports
import java.awt.EventQueue;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
public class DeyoraSortAndSearch extends JFrame {
	private JPanel contentPane;//panel
	static JTextField nameField;//textfield for user to enter their name
	static JTextField studentIDField;//textfield for user to enter their student id
	static JComboBox comboBoxView;//comboBox where all matched searchs will appear
	static int numEntries =0;//counting number of student entries
	static int n, count,To_Find, y, matchC=0, hi,lo,m,type;//int n to hold numEntries for easy use in binary search, To_Find is studentID user is searching for, matchC is number of matches
	//hi is number of numEntries, lo is lowest index in array, m is match index, type is to see what code to run in save
	static String checkingView, checkIntStr;//checkingView variable to see if comboBox code should be run, checkIntStr to hold if the text entered into studentIDField is a number or not
	static boolean next =true, add=true, searchI=false, searchG=false, searchN=false;//boolean next to see whether to run infoLabel code, boolean add to see whether to add entry based on validness of entered items, search booleans to see what to search with
	static studentSort [] SS = new studentSort [10];// array of size 10 of studentSort objects
	static studentSort [] SSMatches = new studentSort [10];//array of size 10 of matches
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeyoraSortAndSearch frame = new DeyoraSortAndSearch();//creating frame
					frame.setVisible(true);//making frame visible
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public DeyoraSortAndSearch() {

		JTextArea textArea = new JTextArea();//instantiating textArea
		getContentPane().add(textArea, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);//boundaries for panel
		contentPane = new JPanel();//panel
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));//panel boundaries
		setContentPane(contentPane);//setting panel
		contentPane.setLayout(null);
		JButton editButton = new JButton("Edit");//instantiate JButton editButton with text "Edit"
		editButton.setBounds(33, 10, 89, 23);//editButton boundaries
		contentPane.add(editButton);//add editButton to panel
		JButton addButton = new JButton("Add Student");//instantiate JButton addButton with text "Add Student"
		addButton.setBounds(132, 10, 163, 23);//boundaries for addButton
		contentPane.add(addButton);//add addButton to panel
		JButton viewButton = new JButton("View");//instantiate JButton viewButton with text "View"
		viewButton.setBounds(301, 11, 93, 23);//boundaries for viewButton
		contentPane.add(viewButton);//add viewButton to panel		
		nameField = new JTextField();//instantiate JTextfield nameField 
		nameField.setBounds(172, 70, 153, 20);//boundaries for nameField
		contentPane.add(nameField);//add nameField to panel
		nameField.setColumns(10);//nameField columns
		JButton deleteButton = new JButton("Delete");//instantiate JButton deleteButton with text "Delete"	
		deleteButton.setBounds(10, 227, 104, 23);//boundaries for deleteButton
		contentPane.add(deleteButton);//add deleteButton to panel		
		String grade [] = { "All","Grade 9", "Grade 10", "Grade 11", "Grade 12"};//String grade array containing grade options		
		JComboBox comboBoxGrade = new JComboBox(grade);//instantiating comboBoxGrade with array grade
		comboBoxGrade.setBounds(182, 132, 89, 20);//boundaries for comboBoxGrade
		contentPane.add(comboBoxGrade);//add comboBoxGrade to panel
		studentIDField = new JTextField();//instantiate JTextfield studentIDField
		studentIDField.setBounds(172, 101, 153, 20);//boundaries for studentIDField
		contentPane.add(studentIDField);//add studentIDField to panel
		studentIDField.setColumns(10);//studentIDField columns
		JLabel nameLabel = new JLabel("Enter Name: ");//instantiate JLabel nameLabel
		nameLabel.setBounds(72, 73, 89, 14);//boundaries for nameLabel
		contentPane.add(nameLabel);	// add nameLabel to panel	
		JLabel studentIdLabel = new JLabel("Enter student id: ");//instantiate JLabel studentIDLabel
		studentIdLabel.setBounds(50, 104, 123, 14);//boundaries for studentIdLabel
		contentPane.add(studentIdLabel);//add infoLabel to panel		
		JButton exitButton = new JButton("Exit");//instantiate JButton exitButton with text "Exit"
		exitButton.setBounds(301, 227, 123, 23);//boundaries for exitButton
		contentPane.add(exitButton);//add exitButton to panel		
		String view [] = {};//array view which will contain the characters user wants to view
		comboBoxView = new JComboBox(view);//instantiating comboBoxView with view array
		comboBoxView.setBounds(319, 132, 82, 22);//boundaries for comboBoxView
		contentPane.add(comboBoxView);//add comboBoxView to panel		
		JButton saveButton = new JButton("Save");//instantiate JButton saveButton with text "Save"
		saveButton.setBounds(142, 227, 129, 23);//boundaries for saveButton
		contentPane.add(saveButton);//add saveButton to panel		
		JButton cancelButton = new JButton("Cancel");//instantiate JButton cancelButton with text "Cancel"
		cancelButton.setBounds(301, 200, 123, 23);//boundaries for cancelButton
		contentPane.add(cancelButton);//add cancelButton to panel		
		JLabel infoLabel = new JLabel("Details");//instantiating JLabel infoLabel with text "Details"
		infoLabel.setBounds(50, 202, 270, 14);//boundaries for infoLabel
		contentPane.add(infoLabel);	//add infoLabel to panel	
		JButton searchButton = new JButton("Search");//instantiate JButton searchButton with text "Search"
		searchButton.setBounds(33, 131, 89, 23);//boundaries for searchButton
		contentPane.add(searchButton);//add searchButton to panel		
		JButton doneButton = new JButton("Done");//instantiate JButton doneButton with text "Done"
		doneButton.setBounds(182, 163, 89, 23);//boundaries for doneButton
		contentPane.add(doneButton);//add doneButton to panel		
		addButton.setVisible(true);// addButton is visible
		exitButton.setVisible(true);//exitButton is visible
		saveButton.setVisible(false);//saveButton is not visible
		editButton.setVisible(false);//editButton is not visible
		viewButton.setVisible(true);// viewButton is visible
		doneButton.setVisible(false);//doneButton is not visible
		deleteButton.setVisible(false);//deleteButton is not visible
		comboBoxGrade.setVisible(false);//comboBoxGrade is not visible
		comboBoxView.setVisible(false);//comboBoxView is not visible
		nameLabel.setVisible(false);//nameLabel is not visible
		studentIdLabel.setVisible(false);//studentIDLabel is not visible
		nameField.setVisible(false);//nameField is not visible
		studentIDField.setVisible(false);//studentIDField is not visible
		searchButton.setVisible(false);//searchButton is not visible
		infoLabel.setVisible(false);//infoLabel is not visible
		comboBoxView.setVisible(false);//comboBoxView is not visible				
		read();//calling read method		
		addButton.addActionListener(new ActionListener(){//adding action listener to the button component and creating a specific action performed method (override) for addButton
			public void actionPerformed(ActionEvent e) {
				add=true;//add equals true
				nameField.setText("");//set nameField text to blank
				studentIDField.setText("");//set studentIDField text to blank
				comboBoxGrade.setVisible(true);//comboBoxGrade is visible
				nameLabel.setVisible(true);//nameLabel is visible
				studentIdLabel.setVisible(true);//studentIDLabel is visible
				nameField.setVisible(true);//nameField is visible
				studentIDField.setVisible(true);//studentIDField is visible
				doneButton.setVisible(false);//doneButton is not visible
				infoLabel.setText("Adding screen");//set infoLabel text to "Adding Screen"
				editButton.setVisible(false);//editButton is not visible
				searchButton.setVisible(false);//searchButton is not visible
				addButton.setVisible(false);//addButton is not visible
				viewButton.setVisible(false);//viewButton is not visible
				comboBoxView.setVisible(false);//comboBoxView is not visible
				saveButton.setVisible(true);//saveButton is visible
				exitButton.setVisible(false);//exitButton is not visible										
			}
		});		
		cancelButton.addActionListener(new ActionListener(){//adding action listener to the button component and creating a specific action performed method (override) for cancelButton
			public void actionPerformed(ActionEvent e) {				
				addButton.setVisible(true);//addButton is visible
				exitButton.setVisible(true);//exitButton is visible
				saveButton.setVisible(false);//saveButton is not visible
				editButton.setVisible(false);//editButton is not visible
				viewButton.setVisible(true);//viewButton is visible
				doneButton.setVisible(false);//doneButton is not visible
				deleteButton.setVisible(false);//deleteButton is not visible
				comboBoxGrade.setVisible(false);//comboBoxGrade is not visible
				comboBoxView.setVisible(false);//comboBoxView is not visible
				nameLabel.setVisible(false);//nameLabel is not visible
				studentIdLabel.setVisible(false);//studentIDLabel is not visible
				nameField.setVisible(false);//nameField is not visible
				studentIDField.setVisible(false);//studentIDField is not visible
				searchButton.setVisible(false);//searchButton is not visible
				infoLabel.setVisible(false);//infoLabel is not visible	
				searchButton.setEnabled(true);//searchButton can be pressed
				comboBoxView.removeAllItems();//remove all items from the comboBoxView
			}
		});		
		editButton.addActionListener(new ActionListener(){//adding action listener to the button component and creating a specific action performed method (override) for editButton
			public void actionPerformed(ActionEvent e) {			
				nameLabel.setVisible(true);//making nameLabel visible
				nameField.setVisible(true);//making nameField visible
				addButton.setVisible(false);//addButton is not visible
				doneButton.setVisible(false);//doneButton is not visible
				viewButton.setVisible(false);//viewButton is not visible
				searchButton.setVisible(false);//searchButton is not visible
				studentIDField.setVisible(true);//studentIDField is visible
				editButton.setVisible(false);//making editButton not visible
				deleteButton.setVisible(false);//making removeButton not visible
				comboBoxView.setVisible(false);//making comboBoxView not visible
				comboBoxGrade.setVisible(true);// making comboBoxHair visible
				saveButton.setVisible(true);//making savePerson button visible
				exitButton.setVisible(false);//making exitButton not visible
				type=1;//type equals 1
				infoLabel.setText("Edit Screen");//set infoLabel text to "Edit Screen"
				String option = (String) comboBoxView.getSelectedItem();//setting option to selected item from comboBoxView
				for ( int i=0; i<numEntries; i++) { //looping through numEntries
					if (option.equals(SS[i].getName() + SS[i].getID())) { //if option equals name and id of student in object at index i
						int idInt = SS[i].getID();// idInt equals studentID at index i
						String idString = String.valueOf(idInt);//idString is the String of the idInt
						nameField.setText(SS[i].getName());//set nameField to name of person object at index i
						studentIDField.setText(idString);//set studentIDField to age of person object at index i
						comboBoxGrade.setSelectedItem(SS[i].getGrade());//set comboBoxGrade to gender of person object at index i					
					} 
				}								
			}
		});			
		saveButton.addActionListener(new ActionListener() {//adding action listener to the button component and creating a specific action performed method (override) for saveButton
			public void actionPerformed(ActionEvent e) {
				checkInt();//calling checkInt method
				addButton.setVisible(true);//addButton is visible
				exitButton.setVisible(true);//exitButton is visible
				saveButton.setVisible(false);//saveButton is not visible
				editButton.setVisible(false);//editButton is not visible
				viewButton.setVisible(true);//viewButton is visible
				doneButton.setVisible(false);//doneButton is not visible
				deleteButton.setVisible(false);//deleteButton is not visible
				comboBoxGrade.setVisible(false);//comboBoxGrade is not visible
				comboBoxView.setVisible(false);//comboBoxView is not visible
				nameLabel.setVisible(false);//nameLabel is not visible
				studentIdLabel.setVisible(false);//studentIDLabel is not visible
				nameField.setVisible(false);//nameField is not visible
				studentIDField.setVisible(false);//studentIDField is not visible
				searchButton.setVisible(false);//searchButton is not visible
				infoLabel.setVisible(true);// is visible
				infoLabel.setText("");//set infoLabel text to blank
				deleteButton.setEnabled(true);// is visible	
				if(checkIntStr.equals("notInt")) {//if checkIntStr variable equals "notInt"
					add=false;//add equals false
					infoLabel.setText("Invalid, must put integer for StudentID");//output message to user to say invalid entry of studentID
					comboBoxGrade.setVisible(true);//comboBoxGrade is visible
					nameLabel.setVisible(true);//nameLabel is visible
					studentIdLabel.setVisible(true);//studentIDLabel is visible
					nameField.setVisible(true);//nameField is visible
					studentIDField.setVisible(true);//studentIDField is visible
					doneButton.setVisible(false);//doneButton is not visible
					editButton.setVisible(false);//editButton is not visible
					searchButton.setVisible(false);//searchButton is not visible
					addButton.setVisible(false);//addButton is not visible
					viewButton.setVisible(false);//viewButton is not visible
					comboBoxView.setVisible(false);//comboBoxView is not visible
					saveButton.setVisible(true);//saveButton is visible
				}						
				else if (comboBoxGrade.getSelectedItem().equals("All")) {//if comboBoxGrade selected is "All"					
					add=false;//add equals false, therefore will not add 
					infoLabel.setText("Invalid, must choose a grade");//output message to user saying grade entered in invalid for entry
					comboBoxGrade.setVisible(true);//comboBoxGrade is visible
					nameLabel.setVisible(true);//nameLabel is visible
					studentIdLabel.setVisible(true);//studentIDLabel is visible
					nameField.setVisible(true);//nameField is visible
					studentIDField.setVisible(true);//studentIDField is visible
					doneButton.setVisible(false);//doneButton is not visible
					editButton.setVisible(false);//editButton is not visible
					searchButton.setVisible(false);//searchButton is not visible
					addButton.setVisible(false);//addButton is not visible
					viewButton.setVisible(false);//viewButton is not visible
					comboBoxView.setVisible(false);//comboBoxView is not visible
					saveButton.setVisible(true);//saveButton is visible					
				}								
				String option = (String) comboBoxView.getSelectedItem();//setting option to selected item from comboBoxView
				if (type ==1) {//edit button was pressed					
					next=true;//next equals true 
					searchButton.setEnabled(true);//searchButton can be pressed
					for ( int i=0; i<numEntries; i++) {//looping through numEntries
						if (option.equals(SS[i].getName() + SS[i].getID())) {//if option equals name of person and studentID object at index i
							comboBoxView.removeItem(SS[i].getName()+ SS[i].getID());//remove name of person and studentID in object at index i from comboBoxView
							for ( int i1 = i +1; i++< numEntries; i1++) {//looping through numEntries
								SS[i1-1]= SS[i1];	//removing person character
							}
							numEntries--;//numEntries minus 1
						}	 
					}
					if (add==true) {//if add equals true					
						SS[numEntries] = new studentSort(nameField, studentIDField, comboBoxGrade);	//instantiating studentSort object				
						numEntries++;//numEntries plus 1
						n = numEntries;//n equals numEntries
						if (numEntries!=1) {//if numEntries does not equal 1
							for (int i = 1; i < n; ++i) {//loop through numEntries 
								studentSort key = SS[i];//studentSort key equals studentSort entry at index i
								int key3 = SS[i].getID();// key 3 equals studentID at index i
								int j = i - 1;//int j equals i minus 1
								while (j >= 0 && SS[j].getID() > key3) {//while j is greater than or equal to 0 and studentID at index j is greater than key3
									SS[j+1] = SS[j];//studentSort entry at index j+1 equals studentSort entry at index j						
									j = j - 1;// j equals j minus 1
								}					
								SS[j+1]= key;//studentSort entry at index j +1 equals key					
							}
						}
						type=0;//type equals 0
					}
				}
				else if (type ==2) {//remove button was pressed
					searchButton.setEnabled(true);//searchButton is able to be pressed
					searchButton.setVisible(true);//searchButton is visible
					doneButton.setVisible(true);//doneButton is visible
					viewButton.setVisible(true);//viewButton is visible
					deleteButton.setVisible(true);//deleteButton is visible
					comboBoxView.setVisible(true);//comboBoxView is visible
					next =true;//next equals true 
					for ( int i=0; i<numEntries; i++) {//looping through numEntries
						if (option.equals(SS[i].getName() + SS[i].getID())) {//if option equals name of person object index i
							comboBoxView.removeItem(SS[i].getName()+ SS[i].getID());//remove name of person object at index i from comboBoxView
							infoLabel.setText("");//infoLabel text set to blank
							for ( int i1 = i +1; i++< numEntries; i1++) {//looping through numEntries
								SS[i1-1]= SS[i1];	//removing person character
							}
							numEntries--;//numEntries minus 1
						}	 
					}
					type=0;//type equals 0
				}
				else {//if the previous if statements are false
					if(add==true) {	//if add equals true				
						SS[numEntries] = new studentSort(nameField, studentIDField, comboBoxGrade);//instantiating new studentSort object				
						numEntries++;//numEntries plus 1
						n = numEntries;//n equals 
						if (numEntries!=1) {//if numEntries does not equal 1
							for (int i = 1; i < n; ++i) {//looping through numEntries
								studentSort key = SS[i];//studentSort key equals studentSort entry at index i
								int key3 = SS[i].getID();// key 3 equals studentID at index i
								int j = i - 1;//int j equals i minus 1
								while (j >= 0 && SS[j].getID() > key3) {//while j is greater than or equal to 0 and studentID at index j is greater than key3
									SS[j+1] = SS[j];//studentSort entry at index j+1 equals studentSort entry at index j						
									j = j - 1;// j equals j minus 1
								}					
								SS[j+1]= key;//studentSort entry at index j +1 equals key					
							}					
						}
						type=0;//type equals 0
					}
				}
			}
		});		
		exitButton.addActionListener(new ActionListener() {//adding action listener to the button component and creating a specific action performed method (override) for exitButton
			public void actionPerformed(ActionEvent e) {
				write();//calling write method
				System.exit(0);	//closing frame			
			}
		});			
		viewButton.addActionListener(new ActionListener() {//adding action listener to the button component and creating a specific action performed method (override) for viewButton
			public void actionPerformed(ActionEvent e) {
				addButton.setVisible(false);//addButton is not visible
				deleteButton.setVisible(true);//making deleteButton visible
				comboBoxGrade.setVisible(true);//comboBoxGrade is visible
				nameLabel.setVisible(true);//nameLabel is visible
				studentIdLabel.setVisible(true);//studentIDLabel is visible
				nameField.setVisible(true);//nameField is visible
				studentIDField.setVisible(true);//studentIDField is visible
				nameField.setText("");//set nameField text to blank
				studentIDField.setText("");//set studentIDField text to blank
				comboBoxView.setVisible(true);//comboBoxView is visible
				searchButton.setVisible(true);//searchButton is visible
				saveButton.setVisible(true);//saveButton is visible
				doneButton.setVisible(true);//doneButton is visible
				infoLabel.setVisible(true);//infoLabel is visible
				infoLabel.setText("View Screen");//set infoLabel text to "View Screen"
			}
		});						
		deleteButton.addActionListener(new ActionListener() {//adding action listener to the button component and creating a specific action performed method (override) for deleteButton
			public void actionPerformed(ActionEvent e) {
				next=true;//next equals true
				type=2;//type equals 2
				deleteButton.setEnabled(false);//deleteButton is not visible
			}			
		});				
		doneButton.addActionListener(new ActionListener(){//adding action listener to the button component and creating a specific action performed method (override) for doneButton
			public void actionPerformed(ActionEvent e) {
				checkingView = "0";//checkingView equals 0
				infoLabel.setText("Search");//set infoLabel text to "Search"
				searchButton.setEnabled(true);//searchButton is able to be pressed
				matchC=0;//matchC equals 0
				comboBoxView.removeAllItems();//remove all items from comboBoxView				
			}
		});	
		searchButton.addActionListener(new ActionListener() {//adding action listener to the button component and creating a specific action performed method (override) for searchButton
			public void actionPerformed(ActionEvent e) {
				searchButton.setEnabled(false);//searchButton is not visible
				next = false;//next equals false
				checkingView ="1";//checkingView equals 1
				infoLabel.setVisible(true);
				String gradeChoice = (String) comboBoxGrade.getSelectedItem();//gradeChoice equals selected grade from comboBoxGrade
				String nameChoice = (String) nameField.getText();//nameChoice equals text from nameField
				String idChoice = (String) studentIDField.getText();//idChoice equals text from studentIDField
				if (!nameChoice.isEmpty()) {//if a name is entered
					searchN =true;//searchN equal true
				}
				if (!gradeChoice.equals("All")) {//if gradeChoice selectedItem is not "All"
					searchG =true;//searchG equals true				
				}
				if (!idChoice.isEmpty()) {//if an id is entered
					searchI =true;//searchI equals true
					checkInt();
					if(checkIntStr.equals("notInt")) {
						infoLabel.setText("Not found");
						searchI=false;//searchI equals false
						searchG=false;//searchG equals false
						searchN=false;//searchN equals false
				}
				
				}
				if (searchN==false && searchG==false && searchI ==true) {	//if user searched only by id			 				 
					search();//calling binary search method
					if (SS[lo].getID() == To_Find  ) {//if studentID at index lo equals student id user is trying to find
						loSearch();//calling loSearch method
					}
					else if (SS[hi].getID() == To_Find ) {//if studentID at index hi equals student id user is trying to find				    	
						hiSearch();//calling hisearch method
					}
					
				}			 
				else if (searchN==false && searchG==true && searchI ==true) {//searching by grade and id			 
					search();//calling binary search method
					if (SS[lo].getID() == To_Find && gradeChoice.equals(SS[lo].getGrade()) ) {//if studentID at index lo equals student id user is trying to find and gradeChoice equals grade in studentSort object at index lo
						loSearch();//calling loSearch method	
					}
					else if (SS[hi].getID() == To_Find && gradeChoice.equals(SS[hi].getGrade())) {	//if studentID at index hi equals student id user is trying to find and gradeChoice equals grade in studentSort object at index hi			    	
						hiSearch();//calling hiSearch method
					}				   
				}			 
				else  if (searchN==true && searchG==false && searchI ==true) {	//searching by id and name			 
					search();
					if (SS[lo].getID() == To_Find && nameChoice.equals(SS[lo].getName()) ) {//if studentID at index lo equals student id user is trying to find and nameChoice equals name in studentSort object at index lo
						loSearch();//calling loSearch method	
					}
					else if (SS[hi].getID() == To_Find && nameChoice.equals(SS[hi].getName())) {//if studentID at index hi equals student id user is trying to find and nameChoice equals name in studentSort object at index hi				    	
						hiSearch();//calling hiSearch method
					}				   
				}			 			 
				else if(searchN==true && searchG==true && searchI ==true) {	// searching by name, id and grade										
					search();
					if (SS[lo].getID() == To_Find && nameChoice.equals(SS[lo].getName()) && gradeChoice.equals(SS[lo].getGrade()) ) {//if studentID at index lo equals student id user is trying to find and nameChoice equals name in studentSort object at index lo gradeChoice equals grade in studentSort object at index lo
						loSearch();	//calling loSearch method		
					}
					else if (SS[hi].getID() == To_Find && nameChoice.equals(SS[hi].getName()) && gradeChoice.equals(SS[hi].getGrade())) {//if studentID at index hi equals student id user is trying to find and nameChoice equals name in studentSort object at index hi gradeChoice equals grade in studentSort object at index hi			    					    							    	
						hiSearch();//calling hiSearch method
					}						    						    													
				}			
				else if (searchN ==true && searchG ==true && searchI == false) {//searching by name and grade	
					matchC=0;//matchC equals 0
					for (int c =0; c<numEntries ;c++) {//looping through numEntries										
						if (nameChoice.equals(SS[c].getName()) && gradeChoice.equals(SS[c].getGrade())) {//if namechoices equals name in studentSort object at index c and if gradeChoice equals grade in studetnSort object at index c					
							SSMatches[matchC]=SS[c];//SSMatches array at index matchC equals studentSort entry at index c
							matchC++;//adding on to matchC counter
							comboBoxView.addItem(SS[c].getName() + SS[c].getID());//adding name from studentSort object and id from studentSort object at index c to comboBoxView
						}
					}
				}
				else if (searchN ==false && searchG ==true  && searchI ==false) {//searching by grade only
					matchC=0;//matchC equals 0
					for (int c =0; c <numEntries ; c++) { //looping through numEntries
						if (SS[c].getGrade().equals(gradeChoice)) {//if grade in studentSort object at index c equals gradeChoice
							SSMatches[matchC]=SS[c];//SSMatches at index matchC equals studentSort object entry at index c
							matchC++;//add on to matchC
							comboBoxView.addItem(SS[c].getName() + SS[c].getID());//adding name from studentSort object and id from studentSort object at index c to comboBoxView						
						}					
					}				
				}			
				else if (searchN ==true && searchG ==false  && searchI ==false) {//searching by only name
					matchC=0;//matchC equals 0
					for (int c =0; c<numEntries; c++) { //looping through numEntries
						if (SS[c].getName().equals(nameChoice)) {//if name in studentSort object at index c equals nameChoice
							SSMatches[matchC]=SS[c];//SSMatches at index matchC equals studentSort object entry at index c
							matchC++;//add one to matchC
							comboBoxView.addItem(SS[c].getName() + SS[c].getID());//adding name from studentSort object and id from studentSort object at index c to comboBoxView
						}
					}
				}
			}
		});
		comboBoxView.addActionListener(new ActionListener() {//adding action listener to the button component and creating a specific action performed method (override) for comboBoxView
			public void actionPerformed(ActionEvent e) {
				editButton.setVisible(true);//editButton is visible
				if(checkingView.equals("1")) {//if checkingView equals 1
					m = comboBoxView.getSelectedIndex();//m equals index of item selected from comboBoxView
					if (next ==false) {//if next equals false
						infoLabel.setText("Details for: " + SSMatches[m].getName() + " " + SSMatches[m].getID() + " " + SSMatches[m].getGrade());//outputting to user the entry from SSMatches array at index m
					}	
				}
				else {//if the previous if statement is false
					infoLabel.setText("Not found");//set infoLabel text to "Not found"
				}
			}
		});		
	}		
	public void write() //method used to write to the file
	{	
		try //because the PrintWriter object can generate IO exceptions use the try and catch Blocks
		{
			PrintWriter output; //object to connect to a file
			output = new PrintWriter (new FileWriter ("studentData")); //instantiate object
			for (int number = 0 ; number <numEntries; number ++) //for loop to loop through entries
			{
				output.println(SS[number].getName()); //store entry  name
				output.println(SS[number].getID()); //store entry studentID
				output.println(SS[number].getGrade());//store entry grade	
			}	
			output.close (); //you must close the object to save the data in the file
			System.out.println ("studentData file created with data!");
		}
		catch (Exception e) ////need this line for try catch
		{ //need this line for try catch
			System.out.println ("error");
		} //need this line for try catch
	}		
	public void read() //method used to read contents of the file
	{
		try
		{
			numEntries =0;//numEntries equals 0
			BufferedReader input; //object to connect to file for read only
			input = new BufferedReader (new FileReader ("studentData"));
			String line= "";// line equals blank string
			line = input.readLine (); //variable line is equal to input.readline()
			String readName="", readGrade="";//initializing String variables readName, readGrade
			String readID2;//instantiating String variable readID2
			int readID;//instantiating int variable readID
			while (line != null) // while the line does not equal null
			{
				readName = line; //variable readName equals line
				//variables readID2, readGrade and line equals input.readLine();
				readID2 = input.readLine();
				readID = Integer.parseInt(readID2);//readID is the parsed integer of readID2
				readGrade = input.readLine();
				SS[numEntries] = new studentSort (readName, readID, readGrade); //instantiating new object studentSort for index numEntries
				numEntries++;//adding one to numEntries
				line = input.readLine ();
			}
			input.close (); //close the file
		}
		catch (Exception e) //need this line for try catch
		{ //need this line for try catch
			System.out.println ("error");
		} //need this line for try catch	
	}	
	public void search() {//binary search method		
		checkingView ="1";//checkingView equals 1
		int j=1;// int j equals 1
		lo = 0;// lo equals 0
		hi = numEntries-1;//hi equals numEntries minus 1		
		//String idChoice = (String) studentIDField.getText();//idChoice equal 		
		To_Find = Integer.valueOf(studentIDField.getText());//getting the integer value of the studentIDField
		while (hi - lo > 1) {//while hi minus lo is greater than 1
			int mid = (hi + lo) / 2;//middle value is hi + lo divided by 2
			if (SS[mid].getID() < To_Find) {//if studentID in studentSort object at index mid is less than To_Find
				lo = mid + 1;//lo equals mid + 1
			}
			else {//if the previous if statement is false
				hi = mid;//hi = mid
			}
		}			    
	}	
	public void hiSearch() {//hiSearch method
		SSMatches[matchC]=SS[hi];//SSMatches array at index matchC equals studentSort entry at index hi
		matchC++;//matchC plus 1
		comboBoxView.addItem(SS[hi].getName() + SS[hi].getID());//adding name from studentSort object and id from studentSort object at index hi to comboBoxView	
	}
	public void loSearch() {
		SSMatches[matchC]=SS[lo];//SSMatches array at index matchC equals studentSort entry at index lo
		matchC++;//matchC plus 1
		comboBoxView.addItem(SS[lo].getName() + SS[lo].getID());//adding name from studentSort object and id from studentSort object at index lo to comboBoxView
	}
	public void checkInt() {//checkInt method to see if user input is a number or not
		try {
			String myString = studentIDField.getText();//myString equals text from studentIDField
			Integer.parseInt(myString);//parsing myString into an integer
			checkIntStr ="yesInt";//checkIntStr equals yesInt
			add=true;//add equals true
			//searchI=true;
		}
		catch (NumberFormatException e1) {//if there is a NumberFormatException
			checkIntStr ="notInt";//checkIntStr equals notInt therefore a number was not entered
		}
	}
}