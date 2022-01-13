package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.BillController;
import controller.EmployeeController;
import model.Employee;




public class HrView extends JFrame implements ActionListener {

	private JTable tabel;
	
	public HrView() {
		tabel= new JTable();
		tabel.setRowHeight(30);
        getData();
		initialize();
	}
	JButton updateButton;
	Vector<Employee> employeeList;
	public void getData(){
		employeeList = EmployeeController.getInstance().getAllEmployee();
		Vector<String> headers = new Vector<>();
		headers.add("ID");
		headers.add("Role");
		headers.add("Name");
		headers.add("Salary");
		headers.add("Status");
		headers.add("Fire");
		
		DefaultTableModel dtm = new DefaultTableModel(headers,0);
		for (Employee em : employeeList) {
			Vector<Object> row = new Vector<>();
			row.add(em.getEmployeeId());
			row.add(em.getRoleId());
			row.add(em.getName());
			row.add(em.getSalary());
			row.add(em.getStatus());
			row.add("Fire");

			dtm.addRow(row);
		}
		
		tabel.setModel(dtm);
	}
	JButton insertButton;
	public void initialize(){
		setLayout(new BorderLayout()); 
		JPanel panelnorth =new JPanel();
		JPanel paneleast =new JPanel();
		JPanel panelcenter =new JPanel();
		JPanel panelsouth =new JPanel();
		JPanel panelwest =new JPanel();
		
		panelnorth.setPreferredSize(new Dimension(200,40));
		
		JLabel labelhead=new JLabel("HUMAN RESOURCE PAGE");
		labelhead.setForeground(Color.blue);
		Font font=new Font("sans",Font.BOLD,20);
		labelhead.setFont(font);
		panelnorth.add(labelhead);
	

		panelcenter.setLayout(new GridLayout(3,1,0,0)); //baris,kolom,x, jarak antar baris
		
		JScrollPane scroll = new JScrollPane(tabel);
		tabel.getTableHeader().setBackground(Color.WHITE);
		tabel.getTableHeader().setForeground(Color.BLACK);
		scroll.setPreferredSize(new Dimension(970,350));
		scroll.getViewport().setBackground(Color.WHITE);
		scroll.getViewport().setForeground(Color.WHITE);
		panelcenter.add(scroll);

		insertButton=new JButton("Insert");
		updateButton=new JButton("Update");
		JButton fireButton=new JButton("Fire");

		panelsouth.setLayout(new GridLayout(1,3,0,0));
		panelsouth.add(insertButton);
		panelsouth.add(updateButton);
		panelsouth.add(fireButton);
		

		add(panelnorth,BorderLayout.NORTH);
		add(paneleast,BorderLayout.EAST);
		add(panelcenter,BorderLayout.CENTER);
		add(panelwest,"West");
		add(panelsouth,BorderLayout.SOUTH);
		
		setVisible(true);
		this.setSize(new Dimension(600,400));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Moware Hospital");
		insertButton.addActionListener(this);
		updateButton.addActionListener(this);
		
		tabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tabel.getSelectedColumn()==5) {

					int row = tabel.getSelectedRow();
					int employeeID=(int) tabel.getValueAt(row,0);
					EmployeeController.getInstance().fireEmployee(employeeID);
					dispose();
					new HrView();
				}
			}
		});
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == insertButton) {

				this.dispose();
				new InsertEmployeeView();
			
		}else if(e.getSource() == updateButton) {

			this.dispose();
			new UpdateEmployeeView();
		}
		
	}

}

