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
import controller.BillController;
import controller.MedController;
import model.Bill;
import model.Medicine;

public class PharmacistView {
	private JFrame frame;
	private JTextField medTextField, nameTextField, priceTextField, stockTextField ;
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
		
		
		//0
		JLabel lblIdLabel = new JLabel("Insert/Update/Delete Medicine");
		lblIdLabel.setBounds(24 , 450 , 61 , 16);
		frame.getContentPane().add(lblIdLabel);
		
		
		//1
		JLabel lblMedLabel = new JLabel("Medicine ID");
		lblMedLabel.setBounds(24, 514, 101, 16);
		frame.getContentPane().add(lblMedLabel);

		medTextField = new JTextField();
		medTextField.setBounds(161, 509, 170, 26);
		frame.getContentPane().add(medTextField);
		medTextField.setColumns(10);
		
		
		//2
		JLabel lblNameLabel = new JLabel("Medicine Name");
		lblNameLabel.setBounds(24, 562, 101, 16);
		frame.getContentPane().add(lblNameLabel);

		nameTextField = new JTextField();
		nameTextField .setBounds(161, 557, 170, 26);
		frame.getContentPane().add(nameTextField );
		
		
		
		//3
		JLabel lblPriceLabel = new JLabel("Medicine Price");
		lblPriceLabel.setBounds(24, 615, 101, 16);
		frame.getContentPane().add(lblPriceLabel);

		priceTextField = new JTextField();
		priceTextField .setBounds(161, 610, 170, 26);
		frame.getContentPane().add(priceTextField );
		
		
		
		//4
		JLabel lblStockLabel = new JLabel("Medicine Stock");
		lblStockLabel.setBounds(24, 668, 101, 16);
		frame.getContentPane().add(lblStockLabel);

		stockTextField = new JTextField();
		stockTextField .setBounds(161, 665, 170, 26);
		frame.getContentPane().add(stockTextField );
		
		
		
		
		
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
		
		tableMed.addMouseListener(new MouseListener() {
						
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = 0;
				row = tableMed.getSelectedRow();
				lblIdLabel.setText(""+tableMed.getValueAt(row, 0));
				medTextField.setText(""+tableMed.getValueAt(row, 2));
				nameTextField.setText(""+tableMed.getValueAt(row, 4));
				priceTextField.setText(""+tableMed.getValueAt(row, 3));
				stockTextField.setText(""+tableMed.getValueAt(row, 4));
			}
			
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
		
		
		
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String medId = medTextField.getText();
				String name = nameTextField.getText();
				int price = Integer.parseInt(priceTextField.getText());
				int stock = Integer.parseInt(stockTextField.getText());
				Medicine withId= MedController.getInstance().getMed(medId);
				Medicine withName= MedController.getInstance().getMed(name);
				
				if(withId != null) {
					JOptionPane.showMessageDialog(null, "Id Found!");
					medTextField.setText(""+withId.getId());
					nameTextField.setText(""+withId.getName());
					priceTextField.setText(""+withId.getPrice());
					stockTextField.setText(""+withId.getStock());
				
					
				}else if(withName != null) {
					JOptionPane.showMessageDialog(null, "Name Found!");
					
					medTextField.setText(""+withName.getId());
					nameTextField.setText(""+withName.getName());
					priceTextField.setText(""+withName.getPrice());
					stockTextField.setText(""+withName.getStock());
				
				}
				else {
					JOptionPane.showMessageDialog(null, "Not found!");
					}
				loadData();
			}
		});
		
		
		
		updateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String medId = medTextField.getText();
				String name = nameTextField.getText();
				int price = Integer.parseInt(priceTextField.getText());
				int stock = Integer.parseInt(stockTextField.getText());
				
				if(MedController.getInstance().update(medId, name, price, stock)) {
					JOptionPane.showMessageDialog(null, "Update success!");
				}
				else {
					JOptionPane.showMessageDialog(null, MedController.getInstance().getErrorMsg(), "Error Message", JOptionPane.ERROR_MESSAGE);
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
					JOptionPane.showMessageDialog(null, "Insert success!");
				}
				else {
					JOptionPane.showMessageDialog(null, MedController.getInstance().getErrorMsg(), "Error Message", JOptionPane.ERROR_MESSAGE);
				}
				loadData();
			}
		});
		
		
		addtoBillButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String medId = medTextField.getText();
				String name = nameTextField.getText();
				String price = priceTextField.getText();
				String stock = stockTextField.getText();
				
				if(BillController.getInstance().insertDetail(medId, price, stock)) {
					JOptionPane.showMessageDialog(null, "Insert success!");
				}
				else {
					JOptionPane.showMessageDialog(null, MedController.getInstance().getErrorMsg(), "Error Message", JOptionPane.ERROR_MESSAGE);
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
			
			String headerBill[] = {"ID", "Medicine", "Quantity", "Date"};
			DefaultTableModel dtmBill = new DefaultTableModel(headerBill, 0);
			Vector<Bill> bills = BillController.getInstance().getAll();
			if(bills!=null) {
			for (Bill bill : bills) {
				listBill = new Vector<>();
				listBill.add(bill.getId());
				listBill.add(bill.getBillDetail().getMedId());
				listBill.add(bill.getBillDetail().getQty());
				listBill.add(bill.getCreatedAt());
				
				dtmBill.addRow(listBill);
			}
			}
			
			tableBill.setModel(dtmBill);
			
			
			
			}
	
		
		
		
		
	
}
