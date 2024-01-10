/*Deyora Perera
  Course: ICS4U
  Assignment: Sort and Search Assignment
  Due date: April 6
  Program Description: Using Binary Search and Insertion sort to create student database which can search students using key fields */
//imports
import javax.swing.JComboBox;
import javax.swing.JTextField;
//the "studentSort" class
public class studentSort {

	protected String grade, name;//protect String grade,name, studentIDnorm variables
	protected String studentIDnorm;
	protected int studentIDInt;//protected int studentIDInt
	//protected String studentIDString;
	//constructor
	public studentSort(JTextField nameField, JTextField studentIDField, JComboBox comboBoxGrade){

		setName(nameField);//setting name
		setID(studentIDField);//setting studentID
		setGrade(comboBoxGrade);//setting grade
	}//studentSort constructor

	//overload constructor	
	public studentSort (String readName, int readID, String readGrade) {

		name = readName;//name equals readName
		studentIDInt = readID;//studentIDInt equals readID
		grade = readGrade;//grade equals readGrade

	}//overload constructor
	public void setName(JTextField nameField) {//setter method for variable name

		name = nameField.getText();//variable name is equal to text from nameField

	}
	public String getName() {//getter method for variable name
		return name;//return name
	}
	public void setID(JTextField studentIDField) {//setter method for variable studentIDInt

		studentIDnorm = studentIDField.getText();//studentIDnorm equals text from studentIDField
		studentIDInt = Integer.parseInt(studentIDnorm);//studentIDInt equals parsed integer from studentIDnorm	
	}
	public int getID() {//getter method for studentIDInt

		return studentIDInt;//return studentIDInt
	}
	public void setGrade(JComboBox comboBoxGrade) {//setter method for variable grade
		grade = (String) comboBoxGrade.getSelectedItem ();//grade equals selected item from comboBoxGrade
	}
	public String getGrade() {//getter method for grade
		return grade;//return grade
	}
}
