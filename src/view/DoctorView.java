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
import controller.EmployeeController;
import controller.MedController;
import controller.PatientController;

import model.Bill;
import model.Employee;
import model.Medicine;
import model.Patient;
import model.PatientDetail;


public class DoctorView extends JFrame{


		
		private JFrame frame;
		
		//add patient detail
		private JTextField patientTextField, employeeField,symptomField,checkDateField;
		Vector<Object> tableContentPatDt, tableContentPat ,tableContentMed;
		
		//search patient
		private JTextField patientField;
		
		//search medicine
		private JTextField medicineField;

		private JTable tableDoc, tablePat ,tableMed;

		public DoctorView() {
			initialize();
		}
		
		private void initialize() {
			frame = new JFrame("Doctor & Nurse Menu");
			frame.setVisible(true);
			frame.setBounds(100, 100, 1200, 900);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			
			//table patient patient
			JScrollPane scrollPanePatient = new JScrollPane();
			scrollPanePatient.setBounds(24, 22, 1126, 120);
			frame.getContentPane().add(scrollPanePatient);
			
			JLabel lblTitlePatient = new JLabel("Patient");
			lblTitlePatient.setBounds(24, 4, 117, 16);
			frame.getContentPane().add(lblTitlePatient);
			
			tablePat = new JTable();
			scrollPanePatient.setViewportView(tablePat);
			
			
			//scrollpane patient detail
			JScrollPane scrollPanePatientDt = new JScrollPane();
			scrollPanePatientDt.setBounds(24, 178, 1126, 120);
			frame.getContentPane().add(scrollPanePatientDt);
			
			JLabel lblTitlePatientDt = new JLabel("Patient Detail");
			lblTitlePatientDt.setBounds(24, 160, 117, 16);
			frame.getContentPane().add(lblTitlePatientDt);
			
			tableDoc = new JTable();
			scrollPanePatientDt.setViewportView(tableDoc);
			
			
			//table medicine
			JScrollPane scrollPaneMed = new JScrollPane();
			scrollPaneMed.setBounds(24, 334, 1126, 120);
			frame.getContentPane().add(scrollPaneMed);
			
			JLabel lblTitleMed = new JLabel("Medicine");
			lblTitleMed.setBounds(24, 316, 117, 16);
			frame.getContentPane().add(lblTitleMed);
			
			tableMed = new JTable();
			scrollPaneMed.setViewportView(tableMed);
			
			//add patientDetail
			JLabel lblIdLabel = new JLabel("Patient ID");
			lblIdLabel.setBounds(24 , 530 , 101 , 16);
			frame.getContentPane().add(lblIdLabel);
			
			
			JLabel lblDoctorLabel = new JLabel("Doctor ID");
			lblDoctorLabel.setBounds(24, 580, 101, 16);
			frame.getContentPane().add(lblDoctorLabel);
			
			JLabel lblSymptomLabel = new JLabel("Symptom");
			lblSymptomLabel.setBounds(24, 630, 101, 16);
			frame.getContentPane().add(lblSymptomLabel);
			
			employeeField = new JTextField();
			employeeField.setBounds(161, 580, 170, 26);
			frame.getContentPane().add(employeeField);
			
			symptomField = new JTextField();
			symptomField.setBounds(161, 630, 170, 26);
			frame.getContentPane().add(symptomField);
	
			
			JButton btnAddButton = new JButton("Add");
			btnAddButton.setBounds(161, 720, 170, 26);
			frame.getContentPane().add(btnAddButton);
			
			//search patient 
			JLabel lblSearchPatientLabel = new JLabel("Search Patient");
			lblSearchPatientLabel.setBounds(400 , 530 , 101 , 16);
			frame.getContentPane().add(lblSearchPatientLabel);
			
			JLabel lblpatientNameLabel = new JLabel("Name");
			lblpatientNameLabel.setBounds(400, 580, 101, 16);
			frame.getContentPane().add(lblpatientNameLabel);
			
			patientField = new JTextField();
			patientField.setBounds(561, 580, 170, 26);
			frame.getContentPane().add(patientField);
			
			JButton btnSearchPatientButton = new JButton("Search");
			btnSearchPatientButton.setBounds(561, 720, 170, 26);
			frame.getContentPane().add(btnSearchPatientButton);
			
			
			//search medicine
			JLabel lblSearchMedicineLabel = new JLabel("Search Medicine");
			lblSearchMedicineLabel.setBounds(800 , 530 , 101 , 16);
			frame.getContentPane().add(lblSearchMedicineLabel);
			
			JLabel lblMedicineNameLabel = new JLabel("Medicine Name");
			lblMedicineNameLabel.setBounds(800, 580, 101, 16);
			frame.getContentPane().add(lblMedicineNameLabel);
			
			medicineField = new JTextField();
			medicineField.setBounds(961, 580, 170, 26);
			frame.getContentPane().add(medicineField);
			
			JButton btnSearchMedicineButton = new JButton("Search");
			btnSearchMedicineButton.setBounds(961, 720, 170, 26);
			frame.getContentPane().add(btnSearchMedicineButton);
		

			JButton btnLogoutButton = new JButton("Logout");
			btnLogoutButton.setBounds(1000, 750, 117, 29);
			frame.getContentPane().add(btnLogoutButton);
			
			loadData();
			
			tablePat.addMouseListener(new MouseListener() {
							
				@Override
				public void mouseClicked(MouseEvent e) {
					int row = 0;
					row = tablePat.getSelectedRow();
					lblIdLabel.setText(""+tablePat.getValueAt(row, 0));
					loadData();
					loadPatientDt( tablePat.getValueAt(row, 0).toString());

				}
				
				public void mousePressed(MouseEvent e) {}
				public void mouseReleased(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
			});
			
			
			
			btnAddButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String PatientID = lblIdLabel.getText().toString();
					String DoctorID = employeeField.getText();
					String Symptom = symptomField.getText();
	
					
					if(PatientController.getInstance().addPatientDetail(PatientID, DoctorID, Symptom)) {
						JOptionPane.showMessageDialog(null, "Insert patient detail success!");
					}
					else {
						JOptionPane.showMessageDialog(null, PatientController.getInstance().getErrorMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
					}
					loadData();
				}
			});

			btnSearchMedicineButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String Name = medicineField.getText();
				
					MedController.getInstance().getMed(Name); 
					JOptionPane.showMessageDialog(null, "Search success");
					loadPatient(Name);
				}
			});
			
			
			btnSearchPatientButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String Name = patientField.getText();
				
					PatientController.getInstance().searchPatient(Name); 
					JOptionPane.showMessageDialog(null, "Search success");
					loadPatient(Name);
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

			String headerPat[] = {"Patient ID", "Name", "Date of Birth"};
			DefaultTableModel dtmPat = new DefaultTableModel(headerPat, 0);
			
			Vector<Patient> patients = PatientController.getInstance().getAllPatient();
			for(Patient patient : patients) {
				tableContentPat = new Vector<>();
				tableContentPat.add(patient.getPatientID());
				tableContentPat.add(patient.getName());
				tableContentPat.add(patient.getDOB());
				
				dtmPat.addRow(tableContentPat);
			}
			
			tablePat.setModel(dtmPat);
			
			String headerMed[] = {"Medicine ID", "Name", "Price","Stock"};
			DefaultTableModel dtmMed = new DefaultTableModel(headerMed, 0);
			
			Vector<Medicine> meds = MedController.getInstance().getAll();
			for(Medicine med : meds) {
				tableContentMed = new Vector<>();
				tableContentMed.add(med.getId());
				tableContentMed.add(med.getName());
				tableContentMed.add(med.getPrice());
				tableContentMed.add(med.getStock());
				
				dtmMed.addRow(tableContentMed);
			}
			
			tableMed.setModel(dtmMed);

		}

		public void loadPatientDt(String PatientID) {

			String headerDoc[] = {"Patient Detail ID", "Patient", "Employee" , "Symptom","CheckDate"};
			DefaultTableModel dtmDoc = new DefaultTableModel(headerDoc, 0);
			
			Vector<PatientDetail> patientsDt = PatientController.getInstance().getPatientDetail(PatientID);
			for(PatientDetail patientDt : patientsDt) {
				tableContentPatDt = new Vector<>();
				tableContentPatDt.add(patientDt.getPatientDetailID());
				tableContentPatDt.add(patientDt.getPatientID());
				tableContentPatDt.add(patientDt.getEmployeeID());
				tableContentPatDt.add(patientDt.getSymptom());
				tableContentPatDt.add(patientDt.getCheckDate());
				dtmDoc.addRow(tableContentPatDt);
			}
			
			
			tableDoc.setModel(dtmDoc);
		}
		
		public void loadPatient(String Name){
			String headerPat[] = {"Patient ID", "Name", "Date of Birth"};
			DefaultTableModel dtmPat = new DefaultTableModel(headerPat, 0);
			
			Vector<Patient> patients = PatientController.getInstance().searchPatient(Name);
			for(Patient patient : patients) {
				tableContentPat = new Vector<>();
				tableContentPat.add(patient.getPatientID());
				tableContentPat.add(patient.getName());
				tableContentPat.add(patient.getDOB());
				
				dtmPat.addRow(tableContentPat);
			}
			
			tablePat.setModel(dtmPat);
		}
		
		public void loadMed(String Name){
			String headerMed[] = {"Medicine ID", "Name", "Price","Stock"};
			DefaultTableModel dtmMed = new DefaultTableModel(headerMed, 0);
			
			Medicine meds = MedController.getInstance().getMed(Name);
		
			tableContentMed.add(meds.getId());
			tableContentMed.add(meds.getName());
			tableContentMed.add(meds.getPrice());
			tableContentMed.add(meds.getStock());
				
			dtmMed.addRow(tableContentMed);
			
			
			tableMed.setModel(dtmMed);
		}

		
}
