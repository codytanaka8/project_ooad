package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connect.Connect;
import controller.BillController;
import model.Bill;

public class AdminView {
	
	private JFrame frame;
	private JTextField employeeTextField;
	private JTextField patientTextField, paymentField;
	Vector<Object> tableContentDoc, tableContentBill;

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

//		JLabel lblEmployeeLabel = new JLabel("Employee");
//		lblEmployeeLabel.setBounds(24, 473, 101, 16);
//		frame.getContentPane().add(lblEmployeeLabel);

//		employeeTextField = new JTextField();
//		employeeTextField.setBounds(161, 468, 170, 26);
//		frame.getContentPane().add(employeeTextField);
//		employeeTextField.setColumns(10);

		JLabel lblPatientLabel = new JLabel("Patient ID");
		lblPatientLabel.setBounds(24, 514, 101, 16);
		frame.getContentPane().add(lblPatientLabel);

		patientTextField = new JTextField();
		patientTextField.setBounds(161, 509, 170, 26);
		frame.getContentPane().add(patientTextField);
		patientTextField.setColumns(10);

		JLabel lblPaymentLabel = new JLabel("Payment Type");
		lblPaymentLabel.setBounds(24, 562, 101, 16);
		frame.getContentPane().add(lblPaymentLabel);

		paymentField = new JTextField();
		paymentField.setBounds(161, 557, 170, 26);
		frame.getContentPane().add(paymentField);

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
		
		tableBill.addMouseListener(new MouseListener() {
						
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = 0;
				row = tableBill.getSelectedRow();
				lblIdLabel.setText(""+tableBill.getValueAt(row, 0));
				patientTextField.setText(""+tableBill.getValueAt(row, 2));
				paymentField.setText(""+tableBill.getValueAt(row, 4));
			}
			
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
		
		btnCheckoutButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = lblIdLabel.getText();
				
				if(BillController.getInstance().checkout(id)) {
					JOptionPane.showMessageDialog(null, "Checkout success!");
				}
				else {
					JOptionPane.showMessageDialog(null, BillController.getInstance().getErrorMsg(), "Error Message", JOptionPane.ERROR_MESSAGE);
				}
				loadData();
			}
		});
		
		btnAddButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String patientId = patientTextField.getText();
				String paymentType = paymentField.getText();
				
				if(BillController.getInstance().insert(0, patientId, paymentType)) {
					JOptionPane.showMessageDialog(null, "Insert bill success!");
				}
				else {
					JOptionPane.showMessageDialog(null, BillController.getInstance().getErrorMsg(), "Error Message", JOptionPane.ERROR_MESSAGE);
				}
				loadData();
			}
		});
		
		btnDetailButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = lblIdLabel.getText();
				BillController.getInstance().showBillDetailView(id);
				frame.dispose();
			}
		});
		
		btnResetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lblIdLabel.setText("");
				patientTextField.setText("");
				paymentField.setText("");
			}
		});
		
		btnLogoutButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new LoginView();
			}
		});
	}
	
	public void loadData() {
		String headerDoc[] = {"ID", "Name", "Status"};
		DefaultTableModel dtmDoc = new DefaultTableModel(headerDoc, 0);
		
		//Vector<Employee> doctors = 
		
		tableDoc.setModel(dtmDoc);
		
		String headerBill[] = {"ID", "Employee", "Patient", "Date", "Payment", "Status"};
		DefaultTableModel dtmBill = new DefaultTableModel(headerBill, 0);
		
		Vector<Bill> bills = BillController.getInstance().getAll();
		if(bills != null) {
			for(Bill bill : bills) {
				tableContentBill = new Vector<>();
				tableContentBill.add(bill.getId());
				tableContentBill.add(bill.getEmployeeId());
				tableContentBill.add(bill.getPatientId());
				tableContentBill.add(bill.getCreatedAt());
				tableContentBill.add(bill.getPaymentType());
				tableContentBill.add(bill.getStatus());
				
				dtmBill.addRow(tableContentBill);
			}
		}
		
		tableBill.setModel(dtmBill);
	}

}
