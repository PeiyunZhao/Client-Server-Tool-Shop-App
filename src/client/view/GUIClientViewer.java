package client.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import client.control.ClientViewControl;

import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GUIClientViewer {

	private JFrame ClientManagementSystem;
	private JTextField textFirstName;
	private JTextField textID;
	private JTextField textLastName;
	private JTextField textAddress;
	private JTextField textPostalCode;
	private JButton btnSave;
	private JButton btnDelete;
	private JButton btnClear;
	private JTextField textPhoneNo;
	private JLabel lblNewLabel_8;
	private JSeparator separator;
	private JLabel lblNewLabel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblNewLabel_7;
	private JTextField textField;
	private JButton btnSearch;
	private JButton btnClearSearch;
	private JSeparator separator_1;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
	private JTextArea textSearchResult;
	
	private JSpinner spinner;
	private JRadioButton rdbtnClientID;
	private JRadioButton rdbtnName;
	private JRadioButton rdbtnType;
	private ClientViewControl viewControl;

	/**
	 * Create the application.
	 * @param cViewControl 
	 */
	public GUIClientViewer(ClientViewControl cViewControl) {
		this.viewControl=cViewControl;
		initializeComponents();
		createEvents();
		ClientManagementSystem.setVisible(true);
		
	}

	//////////////////////////////////////////////////
	////////This method contains all the code for creating events///////
	//////////////////////////////////////////////////
	
	private void createEvents() {
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=textField.getText();
				String fname=textFirstName.getText();
				String lname=textLastName.getText();
				String address=textAddress.getText();
				String postalCode=textPostalCode.getText();
				String phone=textPhoneNo.getText();

				if(isNumeric(id) && 
						!fname.isBlank()&&
						!lname.isBlank()&&
						!address.isBlank()&&
						!postalCode.isBlank()&&
						!phone.isBlank()
						) {
					viewControl.sendMessage("CUI "+textField.getText());
					String response1 = viewControl.retrieveResponse();
					String type="";
					if (spinner.getValue().equals("Residential")) {
						type="R";
					}else {
						type="C";

					}
					viewControl.sendCustomer(id+","+fname+","+lname+","+type+","+address+","+postalCode+","+phone);
					String response2 = viewControl.retrieveResponse();
					textSearchResult.setText(response1+"\n"+response2);
				}

			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewControl.sendMessage("CDI "+textField.getText());
				String response1 = viewControl.retrieveResponse();
				String response2 = viewControl.retrieveResponse();
				textSearchResult.setText(response1+"\n"+response2);
			}
		});
		
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textID.setText("");
				textFirstName.setText("");
				textLastName.setText("");
				textAddress.setText("");
				textPostalCode.setText("");
				textPhoneNo.setText("");
				spinner.setValue("Residential");
			}
		});
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String out="";
				String response1="";
				
				if (rdbtnClientID.isSelected()) {
					viewControl.sendMessage("CSI "+textField.getText().trim());
					response1=viewControl.retrieveResponse();
					String str =viewControl.retrieveCustomer();
					
					if (str.equals("Customer Not Found")) 
						out= "Customer Not Found";
					else 
						out = displayCustomer(str);
					
				} else if (rdbtnName.isSelected()) {
					viewControl.sendMessage("CSN "+textField.getText().trim());
					response1=viewControl.retrieveResponse();
					out = viewControl.retrieveCustomers();
					
				} else if (rdbtnType.isSelected()) {
					viewControl.sendMessage("CST "+textField.getText().trim());
					response1=viewControl.retrieveResponse();
					out = viewControl.retrieveCustomers();
			
				} else return;
				
				
				textSearchResult.setText(response1+"\n"+ out);
				
			}
		});
		
		btnClearSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textSearchResult.setText("");
				
			}
		});
	}
	
	
	private String displayCustomer(String customer) {

		textID.setText("");
		textFirstName.setText("");
		textLastName.setText("");
		textAddress.setText("");
		textPostalCode.setText("");
		textPhoneNo.setText("");
		spinner.setValue("Residential");

		String[] list =customer.split(",");
		String out="";
		try {
			out+="ID: "+list[0]+"  ";
			out+="Name: "+list[1]+", ";
			out+=" "+list[2]+"  ";
			out+="Type: "+list[3]+"  ";
			out+="Address: "+list[4]+" , ";
			out+=list[5]+"  ";
			out+="Phone: "+list[6]+"  ";
			if(list[3].equals("R")) {
				spinner.setValue("Residential");
			}else {
				spinner.setValue("Commercial");
			}
		}catch(ArrayIndexOutOfBoundsException err) {
			out="Customer Not Found";
		}

		if(!out.equals("Commercial Not Found")) {
			textID.setText(list[0]);
			textFirstName.setText(list[1]);
			textLastName.setText(list[2]);
			textAddress.setText(list[4]);
			textPostalCode.setText(list[5]);
			textPhoneNo.setText(list[6]);
		}
		return out;
	}

	public boolean isNumeric(String strNum) {
	    if (strNum == null|| strNum.equals("")) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}

	//////////////////////////////////////////////////
	////////This method creates and initializes all the contents of the frame///////
	//////////////////////////////////////////////////
	
	private void initializeComponents() {
		ClientManagementSystem = new JFrame();
		ClientManagementSystem.setFont(new Font("Tahoma", Font.BOLD, 18));
		ClientManagementSystem.setTitle("Client Management Screen");
		ClientManagementSystem.setBounds(100, 100, 620, 410);
		ClientManagementSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel_1 = new JLabel("Client ID:");
		
		JLabel lblNewLabel_2 = new JLabel("First name:");
		
		textFirstName = new JTextField();
		textFirstName.setColumns(10);
		
		textID = new JTextField();
		textID.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Last name:");
		
		textLastName = new JTextField();
		textLastName.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Address:");
		
		textAddress = new JTextField();
		textAddress.setText("");
		textAddress.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Postal Code:");
		
		textPostalCode = new JTextField();
		textPostalCode.setText("");
		textPostalCode.setColumns(10);
		
		btnSave = new JButton("Save");
		btnDelete = new JButton("Delete");
		btnClear = new JButton("Clear");
		
		JLabel lblNewLabel_6 = new JLabel("Phone no.:");
		
		textPhoneNo = new JTextField();
		textPhoneNo.setText("");
		textPhoneNo.setColumns(10);
		
		lblNewLabel_8 = new JLabel("Select type of search to be performed: ");
		
		rdbtnClientID = new JRadioButton("Client ID");
		buttonGroup.add(rdbtnClientID);
		
		rdbtnName = new JRadioButton("Name");
		buttonGroup.add(rdbtnName);
		rdbtnName.setToolTipText("International\r\nLocal");
		
		rdbtnType = new JRadioButton("Client Type");
		buttonGroup.add(rdbtnType);
		
		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		
		lblNewLabel = new JLabel("Client type:");
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerListModel(new String[] {"Residential", "Commercial"}));
		
		lblNewLabel_7 = new JLabel("Enter the search parameter below:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnClearSearch = new JButton("Clear Search");
		
		separator_1 = new JSeparator();
		
		lblNewLabel_9 = new JLabel("Search Result:");
		
		JScrollPane scrollPane = new JScrollPane();
		
		lblNewLabel_10 = new JLabel("Search Clients");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		lblNewLabel_11 = new JLabel("Client Information");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		GroupLayout groupLayout = new GroupLayout(ClientManagementSystem.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addContainerGap(33, Short.MAX_VALUE)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(68)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(rdbtnClientID)
												.addComponent(rdbtnName)
												.addComponent(rdbtnType)))
										.addComponent(lblNewLabel_8)
										.addComponent(lblNewLabel_7)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(textField, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(btnSearch)
											.addGap(18)
											.addComponent(btnClearSearch))
										.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(groupLayout.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblNewLabel_9)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(groupLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(scrollPane)//, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(102)
							.addComponent(lblNewLabel_10)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnSave)
									.addGap(30)
									.addComponent(btnDelete)
									.addGap(29)
									.addComponent(btnClear))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(50)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lblNewLabel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblNewLabel_3, Alignment.LEADING)
										.addComponent(lblNewLabel_4, Alignment.LEADING)
										.addComponent(lblNewLabel_5, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblNewLabel_6, Alignment.LEADING)
										.addComponent(lblNewLabel, Alignment.LEADING)
										.addComponent(lblNewLabel_1, Alignment.LEADING))
									.addGap(7)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(textFirstName)
										.addComponent(textID, Alignment.LEADING)
										.addComponent(textPhoneNo, Alignment.LEADING)
										.addComponent(textLastName, Alignment.LEADING)
										.addComponent(textAddress, Alignment.LEADING)
										.addComponent(textPostalCode, Alignment.LEADING)
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(spinner)))))
							.addContainerGap(37, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_11)
							.addGap(77))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(separator, GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_10)
							.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
							.addComponent(lblNewLabel_8)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(rdbtnClientID)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnName))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_11)
							.addGap(51)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_1)
								.addComponent(textID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(8)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_2)
								.addComponent(textFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_3)
								.addComponent(textLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_4)
								.addComponent(textAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_5)
								.addComponent(textPostalCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_6)
								.addComponent(textPhoneNo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(37)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnSave)
								.addComponent(btnDelete)
								.addComponent(btnClear)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(rdbtnType)
							.addGap(18)
							.addComponent(lblNewLabel_7)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSearch)
								.addComponent(btnClearSearch))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_9)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		textSearchResult = new JTextArea();
		scrollPane.setViewportView(textSearchResult);
		ClientManagementSystem.getContentPane().setLayout(groupLayout);
	}
}
