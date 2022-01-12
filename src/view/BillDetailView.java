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
import model.BillDetail;

public class BillDetailView {
	
	private JFrame frame;
	private JTextField medTextField, qtyTextField;
	Vector<Object> tableContent;
	private String billId;
	
	private JTable table;

	public BillDetailView(String id) {
		initialize();
		billId = id;
	}
	
	private void initialize() {
		frame = new JFrame("Bill Detail");
		frame.setVisible(true);
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 32, 726, 190);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblIdLabel = new JLabel("ID");
		lblIdLabel.setBounds(24 , 250 , 61 , 16);
		frame.getContentPane().add(lblIdLabel);

		JLabel lblNameLabel = new JLabel("Medicine Name");
		lblNameLabel.setBounds(24, 273, 61, 16);
		frame.getContentPane().add(lblNameLabel);

		medTextField = new JTextField();
		medTextField.setBounds(121, 268, 130, 26);
		frame.getContentPane().add(medTextField);
		medTextField.setColumns(10);

		JLabel lblQtyLabel = new JLabel("Quantity");
		lblQtyLabel.setBounds(24, 314, 61, 16);
		frame.getContentPane().add(lblQtyLabel);

		qtyTextField = new JTextField();
		qtyTextField.setBounds(121, 309, 130, 26);
		frame.getContentPane().add(qtyTextField);
		qtyTextField.setColumns(10);

		JButton btnAddButton = new JButton("Add");
		btnAddButton.setBounds(24, 425, 117, 29);
		frame.getContentPane().add(btnAddButton);

		JButton btnUpdateButton = new JButton("Update");
		btnUpdateButton.setBounds(153, 425, 117, 29);
		frame.getContentPane().add(btnUpdateButton);

		JButton btnDeleteButton = new JButton("Delete");
		btnDeleteButton.setBounds(282, 425, 117, 29);
		frame.getContentPane().add(btnDeleteButton);

		JButton btnResetButton = new JButton("Reset");
		btnResetButton.setBounds(282, 268, 117, 29);
		frame.getContentPane().add(btnResetButton);

		JButton btnBackButton = new JButton("Back");
		btnBackButton.setBounds(407, 425, 117, 29);
		frame.getContentPane().add(btnBackButton);

		JLabel lblTitleLabel = new JLabel("Bill Details");
		lblTitleLabel.setBounds(24, 4, 117, 16);
		frame.getContentPane().add(lblTitleLabel);
		
		loadData();
		
		table.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnAddButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String medId = medTextField.getText();
				String qty = qtyTextField.getText();
				
				if(BillController.getInstance().insertDetail(billId, medId, qty)) {
					JOptionPane.showMessageDialog(null, "Insert success!");
				}
				else {
					JOptionPane.showMessageDialog(null, BillController.getInstance().getErrorMsg(), "Error Message", JOptionPane.ERROR_MESSAGE);
				}
				loadData();
			}
		});
		
		btnDeleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String detailId = lblIdLabel.getText();
				
				if(BillController.getInstance().deleteDetail(detailId)) {
					JOptionPane.showMessageDialog(null, "Delete success!");
				}
				else {
					JOptionPane.showMessageDialog(null, BillController.getInstance().getErrorMsg(), "Error Message", JOptionPane.ERROR_MESSAGE);
				}
				loadData();
			}
		});
		
		btnUpdateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String detailId = lblIdLabel.getText();
				String medId = medTextField.getText();
				String qty = qtyTextField.getText();
				
				if(BillController.getInstance().updateDetail(detailId, medId, qty)) {
					JOptionPane.showMessageDialog(null, "Update success!");
				}
				else {
					JOptionPane.showMessageDialog(null, BillController.getInstance().getErrorMsg(), "Error Message", JOptionPane.ERROR_MESSAGE);
				}
				loadData();
			}
		});
		
		btnResetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lblIdLabel.setText("");
				medTextField.setText("");
				qtyTextField.setText("");
			}
		});
		
		btnBackButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				BillController.getInstance().showAdminView();
			}
		});
	}
	
	public void loadData() {
		String header[] = {"ID", "Medicine", "Quantity"};
		DefaultTableModel dtm = new DefaultTableModel(header, 0);
		
		Vector<BillDetail> details = BillController.getInstance().getAllDetailById(billId);
		if(details!=null) {
			for(BillDetail detail : details) {
				tableContent = new Vector<>();
				tableContent.add(detail.getId());
				tableContent.add(detail.getMedId());
				tableContent.add(detail.getQty());
				
				dtm.addRow(tableContent);
			}
		}
		
		table.setModel(dtm);
	}

}
