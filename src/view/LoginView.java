package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.BillController;
import controller.EmployeeController;
import controller.MedController;
import controller.PatientController;
import model.Employee;


public class LoginView extends JFrame implements ActionListener {
	
	private JFrame frame;
	JTextField namaField;
	JPasswordField passField;

	JButton submitButton,cancelButton;
	
	public void initialize(){
		setLayout(new BorderLayout()); //utara selatan dll
		JPanel panelnorth =new JPanel();
		JPanel paneleast =new JPanel();
		JPanel panelcenter =new JPanel();
		JPanel panelsouth =new JPanel();
		JPanel panelwest =new JPanel();
		
		panelnorth.setPreferredSize(new Dimension(200,40));
		
		JLabel labelhead=new JLabel("LOGIN FORM");
		labelhead.setForeground(Color.blue);
		Font font=new Font("sans",Font.BOLD,20);
		labelhead.setFont(font);
		panelnorth.add(labelhead);
	
		panelcenter.setLayout(new GridLayout(5,2,-100,10)); //baris,kolom,x, jarak antar baris
		namaField=new JTextField();
		passField=new JPasswordField();
		
		panelcenter.add(new JLabel("Name"));
		panelcenter.add(namaField);
		panelcenter.add(new JLabel("Password"));
		panelcenter.add(passField);
		
		
		submitButton=new JButton("Submit");
		cancelButton=new JButton("Cancel");
		panelsouth.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelsouth.add(cancelButton);
		panelsouth.add(submitButton);

		add(panelnorth,BorderLayout.NORTH);
		add(paneleast,BorderLayout.EAST);
		add(panelcenter,BorderLayout.CENTER);
		add(panelsouth,BorderLayout.SOUTH);
		add(panelwest,"West");
		
		setVisible(true);
		this.setSize(new Dimension(600,400));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Moware Hospital");
	}
	
	public LoginView() {
		initialize();
		submitButton.addActionListener(this);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == submitButton) {
			String username = namaField.getText();
			String password = new String(passField.getPassword());
			
			Employee user = EmployeeController.getInstance().authenticate(username,password);
			if(user == null) {
				JOptionPane.showMessageDialog(this, EmployeeController.getInstance().getErrorMessage());
			}else if(user.getRoleId()==1){
				JOptionPane.showMessageDialog(this, "Login as admin Success!");
				this.dispose();
				BillController.getInstance().showAdminView();
			}else if(user.getRoleId()==2){
				JOptionPane.showMessageDialog(this, "Login as pharmacist Success!");
				this.dispose();
				System.out.println(1);
				MedController.getInstance().showPharmacistView();
			}else if(user.getRoleId()==3){
				JOptionPane.showMessageDialog(this, "Login as doctor Success!");
				this.dispose();
				PatientController.getInstance().showDoctorView();
			}else if(user.getRoleId()==4){
				JOptionPane.showMessageDialog(this, "Login as nurse Success!");
				this.dispose();
				PatientController.getInstance().showDoctorView();
			}else if(user.getRoleId()==5){
				JOptionPane.showMessageDialog(this, "Login as hr Success!");
				this.dispose();
				System.out.println(1);
				new HrView();
			}
			else {
				System.out.println(user.getRoleId());
				System.out.println(user.getName());
				
				System.out.println("pass"+user.getPassword());
				System.out.println(user.getSalary());
				JOptionPane.showMessageDialog(this, "Login Success!");
				this.dispose();
//				BillController.getInstance().showAdminView();
			}
		}
	}

}
