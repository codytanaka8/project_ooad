package src.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import src.controller.EmployeeController;
import src.model.Employee;

public class InsertEmployeeView extends JFrame implements ActionListener{
	JTextField namaField;
	JTextField usernameField;
	JTextField passField;
	JTextField salaryField;
	JTextField statusField;
	JTextField roleField;
	public InsertEmployeeView() {
		init();
	}
	JButton insertButton;
	private String errorMessage = "";
	public void init(){
		setLayout(new BorderLayout());
		JPanel panelnorth =new JPanel();
		JPanel paneleast =new JPanel();
		JPanel panelcenter =new JPanel();
		JPanel panelsouth =new JPanel();
		JPanel panelwest =new JPanel();
		insertButton=new JButton("Insert");
		panelnorth.setPreferredSize(new Dimension(200,40));
		
		JLabel labelhead=new JLabel("INSERT NEW EMPLOYEE");
		labelhead.setForeground(Color.blue);
		Font font=new Font("sans",Font.BOLD,23);
		labelhead.setFont(font);
		panelnorth.add(labelhead);
		
		panelcenter.setPreferredSize(new Dimension(600,600));
		panelcenter.setLayout(new GridLayout(6,2,-100,10));
		namaField=new JTextField();
		usernameField=new JTextField();
		roleField=new JTextField();
		passField=new JTextField();
		salaryField=new JTextField();
		statusField=new JTextField();
		panelcenter.add(new JLabel("Employee Name"));
		panelcenter.add(namaField);
		panelcenter.add(new JLabel("Employee Username"));
		panelcenter.add(usernameField);
		panelcenter.add(new JLabel("Employee Role Id :"));
		panelcenter.add(roleField);
		panelcenter.add(new JLabel("Employee Password"));
		panelcenter.add(passField);
		panelcenter.add(new JLabel("Employee Salary"));
		panelcenter.add(salaryField);
		panelcenter.add(new JLabel("Employee Status"));
		panelcenter.add(statusField);
		
		
		panelsouth.add(insertButton);
		
		add(panelnorth,BorderLayout.NORTH);
		add(paneleast,BorderLayout.EAST);
		add(panelcenter,BorderLayout.CENTER);
		add(panelsouth,BorderLayout.SOUTH);
		add(panelwest,"West");
		
		setVisible(true);
		this.setSize(new Dimension(800,800));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Moware");
		insertButton.addActionListener(this);
	}
	
	public boolean addEmployee(int roleID,String name,String username,String salary,String password,String status){
		int salarydigit;
		try {
			salarydigit = Integer.parseInt(salary);
		} catch (NumberFormatException e) {
			this.errorMessage = "Salary must be numeric";
			return false;
		}
		
		if(name.length()<=0||username.length()<=0||salary.length()<=0||password.length()<=0||status.length()<=0){
			this.errorMessage = "Please fill all of the input";
			return false;
		}
		else if(salarydigit<=0){
			this.errorMessage = "Salary must be above 0";
			return false;
		}else if(!status.equals("Active")&&!status.equals("Inactive")){
			this.errorMessage = "Status must only be Active or Inactive";
			return false;
		}
		
		Employee em=new Employee(roleID, name, username, salarydigit,password, status);
		em=em.insertEmployee();
		
		if(em == null) {
			this.errorMessage = "Insert Employee Failed";
			return false;
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == insertButton) {
			if(usernameField.getText().equals("")||roleField.getText().equals("")||namaField.getText().equals("")||salaryField.getText().equals("")||passField.getText().equals("")||statusField.getText().equals("")){
				JOptionPane.showMessageDialog(this, "Inputs cant be empty");
			}
			
			
			int roleid=Integer.parseInt(roleField.getText());
			boolean success = EmployeeController.getInstance().addEmployee(roleid,namaField.getText(),usernameField.getText(),salaryField.getText(),passField.getText(),statusField.getText());
			
			if(!success) {
				JOptionPane.showMessageDialog(this, EmployeeController.getInstance().getErrorMessage());
			}
			else {
				JOptionPane.showMessageDialog(this, "Successfully saved!");
				this.dispose();
				new HrView();
				
			}
		}
	}

}
