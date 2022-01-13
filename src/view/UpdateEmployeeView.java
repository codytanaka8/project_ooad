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

public class UpdateEmployeeView extends JFrame implements ActionListener {
	JTextField nameField;
	JTextField usernameField;
	JTextField passField;
	JTextField salaryField;
	JTextField statusField;
	JTextField roleField;
	JTextField idField;
	JButton updateButton;
	public UpdateEmployeeView() {
		init();
	}
	
	
	public void init(){
		setLayout(new BorderLayout());
		JPanel panelnorth =new JPanel();
		JPanel paneleast =new JPanel();
		JPanel panelcenter =new JPanel();
		JPanel panelsouth =new JPanel();
		JPanel panelwest =new JPanel();
		updateButton=new JButton("Update");
		panelnorth.setPreferredSize(new Dimension(200,40));
		
		JLabel labelhead=new JLabel("UPDATE EMPLOYEE");
		labelhead.setForeground(Color.blue);
		Font font=new Font("sans",Font.BOLD,23);
		labelhead.setFont(font);
		panelnorth.add(labelhead);
		
		panelcenter.setPreferredSize(new Dimension(600,600));
		panelcenter.setLayout(new GridLayout(6,2,-100,10));
		idField=new JTextField();
		nameField=new JTextField();
		usernameField=new JTextField();
		roleField=new JTextField();
		passField=new JTextField();
		salaryField=new JTextField();
		statusField=new JTextField();
		panelcenter.add(new JLabel("Employee Id To Find :"));
		panelcenter.add(idField);
		panelcenter.add(new JLabel("Employee Role Id : "));
		panelcenter.add(roleField);
		panelcenter.add(new JLabel("Employee Name :"));
		panelcenter.add(nameField);
		panelcenter.add(new JLabel("Employee Salary : "));
		panelcenter.add(salaryField);

		panelsouth.add(updateButton);
		
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
		updateButton.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == updateButton) {
			if(idField.getText().equals("")||roleField.getText().equals("")||nameField.getText().equals("")||salaryField.getText().equals("")){
				JOptionPane.showMessageDialog(this, "Inputs cant be empty");
			}
			
			Boolean a=EmployeeController.getInstance().updateEmployee(Integer.parseInt(idField.getText()), Integer.parseInt(roleField.getText()), nameField.getText(), salaryField.getText());
			
			if(a) {
				JOptionPane.showMessageDialog(this, "Update Success!");
				this.dispose();
				new HrView();
			}
			else {
				JOptionPane.showMessageDialog(this, EmployeeController.getInstance().getErrorMessage());
			}
			
			this.dispose();
			new HrView();
		
	}
	}

}
