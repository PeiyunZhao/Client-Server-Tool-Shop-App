import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Window.Type;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private JTextField textParameter;
	private JButton btnSearch;
	private JButton btnToolList;
	private JButton btnDecrease;
	private JButton btnClearSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIInventoryViewer window = new GUIInventoryViewer();
					window.frmInventoryManagementSystem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIInventoryViewer() {
		initializeComponents();
		createEvents();
	}
	
	//////////////////////////////////////////////////
	////////This method contains all the code for creating events///////
	//////////////////////////////////////////////////

	private void createEvents() {
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(btnClear, "Do you really want to Clear?");
				JOptionPane.showMessageDialog(null, "Successfully Added!");
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(btnSave, "Do you really want to save?");
				JOptionPane.showMessageDialog(null, "Successfully saved!");
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(btnDelete, "Do you really want to delete?");
				JOptionPane.showMessageDialog(null, "Successfully deleted!");
			}
		});
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(btnSearch, "Start searching?");
			}
		});
		
		btnToolList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(btnToolList, "Start searching?");
			}
		});
		
		btnDecrease.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(btnDecrease, "Do you really want to decrease the item quantity?");
				JOptionPane.showMessageDialog(null, "Successfully done!");
			}
		});
		
		btnClearSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(btnClearSearch, "Do you want to clear search?");
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeComponents() {
		frmInventoryManagementSystem = new JFrame();
		frmInventoryManagementSystem.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frmInventoryManagementSystem.setTitle("Inventory Management System");
		frmInventoryManagementSystem.setType(Type.UTILITY);
		frmInventoryManagementSystem.setBounds(100, 100, 700, 457);
		frmInventoryManagementSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		
		JLabel lblNewLabel = new JLabel("Tool Information");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel_1 = new JLabel("Search Tools");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel_2 = new JLabel("Tool name:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Tool ID:");
		
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
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerListModel(new String[] {"Select One", "Electrical", "Non-Electrical"}));
		
		JLabel lblNewLabel_7 = new JLabel("Tool type:");
		
		btnClear = new JButton("Clear");
		btnSave = new JButton("Save");
		btnDelete = new JButton("Delete");
		
		JLabel lblSearchingTypeSelect = new JLabel("Select type of search to be performed:");
		
		JRadioButton rdbtnToolName = new JRadioButton("Tool Name");
		buttonGroup.add(rdbtnToolName);
		
		JRadioButton rdbtnToolID = new JRadioButton("Tool ID");
		buttonGroup.add(rdbtnToolID);
		
		btnToolList = new JButton("All Tools List");
		
		JRadioButton rdbtnCheckQuantity = new JRadioButton("Check Quantity");
		buttonGroup.add(rdbtnCheckQuantity);
		
		textParameter = new JTextField();
		textParameter.setColumns(10);
		
		JLabel lblParameter = new JLabel("Enter the search parameter below:");
		
		btnSearch = new JButton("Search");
		
		JRadioButton rdbtnSupplier = new JRadioButton("Check Supplier");
		buttonGroup.add(rdbtnSupplier);
		
		JLabel lblSearchResult = new JLabel("Search Result:");
		
		JScrollPane scrollPane = new JScrollPane();
		
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
								.addComponent(rdbtnCheckQuantity)
								.addComponent(rdbtnSupplier)))
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
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE))
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
											.addGap(3)
											.addComponent(rdbtnCheckQuantity)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(rdbtnSupplier)
											.addGap(18)
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
		
		JTextArea textSearchResult = new JTextArea();
		textSearchResult.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setViewportView(textSearchResult);
		frmInventoryManagementSystem.getContentPane().setLayout(groupLayout);
	}
}
