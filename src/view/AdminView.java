package view;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import connect.Connect;

public class AdminView {
	
	private JFrame frame;
	private JTextField nameTextField;
	private JTextField emailTextField;
	private JPasswordField passwordField;
	Vector<Object> tableContent;

	private Connect con = Connect.getConnection();
	private JTable tableDoc, tableBill;

	public AdminView() {
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame("Administrative Menu");
		frame.setVisible(true);
		frame.setBounds(100, 100, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPaneDoc = new JScrollPane();
		scrollPaneDoc.setBounds(24, 32, 726, 190);
		frame.getContentPane().add(scrollPaneDoc);
		
		JLabel lblTitleDoctors = new JLabel("Doctors");
		lblTitleDoctors.setBounds(24, 4, 117, 16);
		frame.getContentPane().add(lblTitleDoctors);
		
		tableDoc = new JTable();
		scrollPaneDoc.setViewportView(tableDoc);
		
		JScrollPane scrollPaneBill = new JScrollPane();
		scrollPaneBill.setBounds(24, 250, 726, 190);
		frame.getContentPane().add(scrollPaneBill);
		
		JLabel lblTitleLabel = new JLabel("Bills");
		lblTitleLabel.setBounds(24, 232, 117, 16);
		frame.getContentPane().add(lblTitleLabel);
		
		tableBill = new JTable();
		scrollPaneBill.setViewportView(tableBill);
		
		JLabel lblIdLabel = new JLabel("ID");
		lblIdLabel.setBounds(24 , 450 , 61 , 16);
		frame.getContentPane().add(lblIdLabel);

		JLabel lblNameLabel = new JLabel("Employee");
		lblNameLabel.setBounds(24, 473, 101, 16);
		frame.getContentPane().add(lblNameLabel);

		nameTextField = new JTextField();
		nameTextField.setBounds(161, 468, 170, 26);
		frame.getContentPane().add(nameTextField);
		nameTextField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Patient");
		lblNewLabel.setBounds(24, 514, 101, 16);
		frame.getContentPane().add(lblNewLabel);

		emailTextField = new JTextField();
		emailTextField.setBounds(161, 509, 170, 26);
		frame.getContentPane().add(emailTextField);
		emailTextField.setColumns(10);

		JLabel lblPasswordLabel_1 = new JLabel("Payment Type");
		lblPasswordLabel_1.setBounds(24, 562, 101, 16);
		frame.getContentPane().add(lblPasswordLabel_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(161, 557, 170, 26);
		frame.getContentPane().add(passwordField);

		JButton btnCheckoutButton = new JButton("Checkout");
		btnCheckoutButton.setBounds(24, 625, 117, 29);
		frame.getContentPane().add(btnCheckoutButton);

		JButton btnAddButton = new JButton("Create");
		btnAddButton.setBounds(153, 625, 117, 29);
		frame.getContentPane().add(btnAddButton);

		JButton btnDetailButton = new JButton("View Details");
		btnDetailButton.setBounds(382, 523, 117, 29);
		frame.getContentPane().add(btnDetailButton);

		JButton btnResetButton = new JButton("Reset");
		btnResetButton.setBounds(382, 473, 117, 29);
		frame.getContentPane().add(btnResetButton);

		JButton btnLogoutButton = new JButton("Logout");
		btnLogoutButton.setBounds(653, 725, 117, 29);
		frame.getContentPane().add(btnLogoutButton);
		
		loadData();
	}
	
	public void loadData() {
		
	}

}
