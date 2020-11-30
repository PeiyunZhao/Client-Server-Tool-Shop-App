package client.view;


import javax.swing.JFrame;
import java.awt.Window.Type;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;

import client.control.InventoryViewControl;

import javax.swing.JTextArea;

public class GUIInventoryViewer {

	private JFrame frmInventoryManagementSystem;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JButton btnClear;
	private JButton btnSave;
	private JButton btnDelete;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JTextField textParameter; //search input
	private JButton btnSearch;
	private JButton btnToolList;
	private JButton btnDecrease;
	private JButton btnClearSearch;
	private JScrollPane scrollPane;
	private JTextArea textSearchResult;
	private JRadioButton rdbtnToolID;
	private JRadioButton rdbtnToolName;
	private JRadioButton rdbtnCheckQuantity;
	private JSpinner spinner;
	
	
	private InventoryViewControl viewControl;
	

	/**
	 * Create the application.
	 * @param socketOut 
	 * @param socketIn 
	 */
	public GUIInventoryViewer(InventoryViewControl viewControl) {
		
		
		this.viewControl=viewControl;
		
		initializeComponents();
		createEvents();
		frmInventoryManagementSystem.setVisible(true);
	}
	
	//////////////////////////////////////////////////
	////////This method contains all the code for creating events///////
	//////////////////////////////////////////////////

	private void createEvents() {
		
		
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=textField.getText();
				String name=textField_1.getText();
				String quantity=textField_2.getText();
				String price=textField_3.getText();
				String supplierId=textField_4.getText();
//				textSearchResult.setText(id+name+quantity+price+supplierId+spinner.getValue());
				
				if(isNumeric(id) && 
						!name.isBlank()&&
						isNumeric(quantity)&&
						isNumeric(price)&&
						isNumeric(supplierId));
				{
					viewControl.sendMessage("IUI "+textField.getText());
					String response1 = viewControl.retrieveResponse();
					String type="";
					if (spinner.getValue().equals("Regular")) {
						type="R";
						viewControl.sendTool(id+","+name+","+price+","+quantity+","+supplierId,type);
					}else {
						type="E";
						viewControl.sendTool(id+","+name+","+price+","+quantity+",120V,"+supplierId,type);
					}
					String response2 = viewControl.retrieveResponse();
					textSearchResult.setText(response1+"\n"+response2);
				}
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewControl.sendMessage("IDI "+textField.getText());
				String response1 = viewControl.retrieveResponse();
				String response2 = viewControl.retrieveResponse();
				textSearchResult.setText(response1+"\n"+response2);
				
			}
		});
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnToolID.isSelected() ||rdbtnCheckQuantity.isSelected() ) {
					viewControl.sendMessage("ISI "+textParameter.getText().trim());
				} 
				else if (rdbtnToolName.isSelected()) {
					viewControl.sendMessage("ISN "+textParameter.getText().trim());
				} 
				else return;
				
				String response1 = viewControl.retrieveResponse();
				String tool= viewControl.retrieveTool();
				
				String out =displayTool(tool);
				
				textSearchResult.setText(response1+"\n"+ out);
				
			}
		});
		
		btnToolList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				viewControl.sendMessage("ILA 1");
				String response1 = viewControl.retrieveResponse();
				String response2 = viewControl.retrieveTools();
				textSearchResult.setText(response1+"\n"+response2);
				
			}
		});
		
		btnDecrease.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewControl.sendMessage("IDQ "+textField.getText());
				viewControl.sendMessage(textParameter.getText().trim());
				String response1 = viewControl.retrieveResponse();
				String response2 = viewControl.retrieveResponse();
				textSearchResult.setText(response1+"\n"+response2);
				
				
			}
		});
		
		btnClearSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textSearchResult.setText("");
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
			}
		});
	}
	
	
	private String displayTool(String tool) {
		
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");
		
		String[] list =tool.split(",");
		String out="";
		try {
			out+="ID: "+list[0]+"  ";
			out+="Name: "+list[1]+"  ";
			out+=", $"+list[2]+"  ";
			out+=", "+list[3]+"  ";

		if(list.length>5) {
			out+="Power: "+list[4]+"  Supplier: "+list[5]+"\n";
			
			spinner.setValue("Electrical");
		}else {
			out+="Supplier: "+list[4]+"\n";
			spinner.setValue("Regular");
		}
		}catch(ArrayIndexOutOfBoundsException err) {
			out="Tool Not Found";
		}
		
		if(!out.equals("Tool Not Found")) {
			textField.setText(list[0]);
			textField_1.setText(list[1]);
			textField_2.setText(list[3]);
			textField_3.setText(list[2]);
			textField_4.setText(list[4]);
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
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeComponents() {
		frmInventoryManagementSystem = new JFrame();
		frmInventoryManagementSystem.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frmInventoryManagementSystem.setTitle("Inventory Management System");
		frmInventoryManagementSystem.setType(Type.UTILITY);
		frmInventoryManagementSystem.setBounds(100, 100, 800, 457);
		frmInventoryManagementSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		
		JLabel lblNewLabel = new JLabel("Tool Information");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel_1 = new JLabel("Search Tools");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel_2 = new JLabel("Tool ID:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Tool Name:");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Quantity:");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Price:");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Supplier ID:");
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerListModel(new String[] {"Regular", "Electrical"}));
		
		JLabel lblNewLabel_7 = new JLabel("Tool type:");
		
		btnClear = new JButton("Clear");
		btnSave = new JButton("Save");
		btnDelete = new JButton("Delete");
		
		JLabel lblSearchingTypeSelect = new JLabel("Select type of search to be performed:");
		
		rdbtnToolName = new JRadioButton("Tool Name");
		buttonGroup.add(rdbtnToolName);
		
		rdbtnToolID = new JRadioButton("Tool ID");
		buttonGroup.add(rdbtnToolID);
		
		btnToolList = new JButton("All Tools List");
		
		rdbtnCheckQuantity = new JRadioButton("Check Quantity");
		buttonGroup.add(rdbtnCheckQuantity);
		
		textParameter = new JTextField();
		textParameter.setColumns(10);
		
		JLabel lblParameter = new JLabel("Enter the input parameter below:");
		
		btnSearch = new JButton("Search");
		
		JLabel lblSearchResult = new JLabel("Search Result:");
		
		scrollPane = new JScrollPane();
		
		btnDecrease = new JButton("Decrease Item Quantity");
		
		btnClearSearch = new JButton("Clear Search");
	
		GroupLayout groupLayout = new GroupLayout(frmInventoryManagementSystem.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(114)
							.addComponent(lblNewLabel_1))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblSearchingTypeSelect))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(60)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(rdbtnToolID)
								.addComponent(rdbtnToolName)
								.addComponent(rdbtnCheckQuantity)))
//								.addComponent(rdbtnSupplier)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblParameter))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(textParameter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnSearch)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnToolList))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(10)
									.addComponent(scrollPane))//, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSearchResult)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnDecrease)
							.addGap(18)
							.addComponent(btnClearSearch)))
					.addGap(40)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(22)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(30)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_2)
										.addComponent(lblNewLabel_3)
										.addComponent(lblNewLabel_4)
										.addComponent(lblNewLabel_5)
										.addComponent(lblNewLabel_6)
										.addComponent(lblNewLabel_7))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(textField_4)
										.addComponent(textField_3)
										.addComponent(textField_2)
										.addComponent(textField_1)
										.addComponent(textField)
										.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(78)
									.addComponent(lblNewLabel))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(33)
							.addComponent(btnSave)
							.addGap(18)
							.addComponent(btnDelete)
							.addGap(18)
							.addComponent(btnClear)))
					.addGap(83))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(65)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_3)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_4)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_5)
								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_6)
								.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(separator, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_1)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblNewLabel)
											.addGap(37)
											.addComponent(lblSearchingTypeSelect)))
									.addGap(18)
									.addComponent(rdbtnToolName)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(rdbtnToolID)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(111)
											.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNewLabel_7))
											.addGap(74)
											.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(btnSave)
												.addComponent(btnDelete)
												.addComponent(btnClear)))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(18)//was 3
											.addComponent(rdbtnCheckQuantity)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(lblParameter)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(textParameter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnSearch)
												.addComponent(btnToolList))
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(lblSearchResult)
											.addGap(8)
											.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
											.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(btnDecrease)
												.addComponent(btnClearSearch))
											.addGap(28)))))))
					.addContainerGap())
		);
		
		textSearchResult = new JTextArea(50, 30);
		textSearchResult.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setViewportView(textSearchResult);
		frmInventoryManagementSystem.getContentPane().setLayout(groupLayout);
		
	}
}
