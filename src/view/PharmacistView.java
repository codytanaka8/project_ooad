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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connect.Connect;
import controller.BillController;
import controller.MedController;
import model.Bill;
import model.Medicine;

public class PharmacistView {
	private JFrame frame;
	private JTextField medTextField, nameTextField, priceTextField, stockTextField ;
	private Connect con = Connect.getConnection();
	private JTable table;
	Vector<Object> listMed, listBill;

	private JTable tableMed, tableBill;
	public PharmacistView() {
	initialize();
	}
	
	private void initialize() {
		frame = new JFrame("Pharmacist Menu");
		frame.setVisible(true);
		frame.setBounds(100, 100, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPaneMed = new JScrollPane();
		scrollPaneMed.setBounds(24, 32, 726, 190);
		frame.getContentPane().add(scrollPaneMed);
		
		JLabel lblTitleMedicines = new JLabel("Medicines");
		lblTitleMedicines.setBounds(24, 4, 117, 16);
		frame.getContentPane().add(lblTitleMedicines);
		
		tableMed = new JTable();
		scrollPaneMed.setViewportView(tableMed);
		
		JScrollPane scrollPaneBill = new JScrollPane();
		scrollPaneBill.setBounds(24, 250, 726, 100);
		frame.getContentPane().add(scrollPaneBill);
		
		JLabel lblTitleLabel = new JLabel("Bills");
		lblTitleLabel.setBounds(24, 232, 117, 16);
		frame.getContentPane().add(lblTitleLabel);
		
		tableBill = new JTable();
		scrollPaneBill.setViewportView(tableBill);
		
		JLabel lblIdLabel = new JLabel("Insert/Update/Delete Medicine");
		lblIdLabel.setBounds(24 , 450 , 61 , 16);
		frame.getContentPane().add(lblIdLabel);
		
		JLabel lblPatientLabel = new JLabel("Medicine ID");
		lblPatientLabel.setBounds(24, 514, 101, 16);
		frame.getContentPane().add(lblPatientLabel);

		medTextField = new JTextField();
		medTextField.setBounds(161, 509, 170, 26);
		frame.getContentPane().add(medTextField);
		medTextField.setColumns(10);

		JLabel lblPaymentLabel = new JLabel("Medicine Name");
		lblPaymentLabel.setBounds(24, 562, 101, 16);
		frame.getContentPane().add(lblPaymentLabel);

		nameTextField = new JTextField();
		nameTextField .setBounds(161, 557, 170, 26);
		frame.getContentPane().add(nameTextField );

		JButton addMedButton = new JButton("Insert");
		addMedButton.setBounds(24, 625, 117, 29);
		frame.getContentPane().add(addMedButton);

		JButton updateButton = new JButton("Update");
		updateButton.setBounds(153, 625, 117, 29);
		frame.getContentPane().add(updateButton);

		JButton deleteButton = new JButton("Delete");
		deleteButton.setBounds(300, 625, 117, 29);
		frame.getContentPane().add(deleteButton);


		JButton searchButton = new JButton("Search (By ID/Name)");
		searchButton.setBounds(382, 523, 117, 29);
		frame.getContentPane().add(searchButton);

		JButton addtoBillButton = new JButton("Add To Bill");
		addtoBillButton.setBounds(382, 473, 117, 29);
		frame.getContentPane().add(addtoBillButton);

		loadData();
		
		tableBill.addMouseListener(new MouseListener() {
						
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = 0;
				row = tableBill.getSelectedRow();
				lblIdLabel.setText(""+tableBill.getValueAt(row, 0));
				medTextField.setText(""+tableBill.getValueAt(row, 2));
				nameTextField.setText(""+tableBill.getValueAt(row, 4));
				priceTextField.setText(""+tableBill.getValueAt(row, 6));
				stockTextField.setText(""+tableBill.getValueAt(row, 6));
			}
			
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
		
		updateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String medId = medTextField.getText();
				String name = nameTextField.getText();
				int price = Integer.parseInt(priceTextField.getText());
				int stock = Integer.parseInt(stockTextField.getText());
				
				if(MedController.getInstance().insert(name, price, stock)) {
					JOptionPane.showMessageDialog(null, "Update success!");
				}
				else {
					JOptionPane.showMessageDialog(null, BillController.getInstance().getErrorMsg(), "Error Message", JOptionPane.ERROR_MESSAGE);
				}
				loadData();
			}
		});
		

		addMedButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String medId = medTextField.getText();
				String name = nameTextField.getText();
				int price = Integer.parseInt(priceTextField.getText());
				int stock = Integer.parseInt(stockTextField.getText());
				
				if(MedController.getInstance().insert(name, price, stock)) {
					JOptionPane.showMessageDialog(null, "Update success!");
				}
				else {
					JOptionPane.showMessageDialog(null, BillController.getInstance().getErrorMsg(), "Error Message", JOptionPane.ERROR_MESSAGE);
				}
				loadData();
			}
		});
		
		
		
	}
		
		public void loadData(){
			String headerMed[] = {"ID", "Name", "Price", "Qty"};
			DefaultTableModel dtmMed = new DefaultTableModel(headerMed, 0);
			
			Vector<Medicine> meds = MedController.getInstance().getAll();
			if(meds != null) {
				for(Medicine med: meds) {
					listMed = new Vector<>();
					listMed.add(med.getId());
					listMed.add(med.getName());
					listMed.add(med.getPrice());
					listMed.add(med.getStock());
					
					dtmMed.addRow(listMed);
				}
			}
			
			tableMed.setModel(dtmMed);
			
			String headerBill[] = {"ID", "Employee", "Patient", "Date", "Payment", "Status"};
			DefaultTableModel dtmBill = new DefaultTableModel(headerBill, 0);
			
			tableMed.setModel(dtmBill);
		}
		
		
		
	
}
