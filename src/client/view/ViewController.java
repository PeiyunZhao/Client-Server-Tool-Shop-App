import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ViewController {
	GUIInventoryViewer gui;
	public Object R;
	
	
	
	public ViewController(GUIInventoryViewer gui) {
		this.gui = gui;
		gui.addListener(new toolListener());
	}
class toolListener implements ActionListener, ListSelectionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == gui.btnSave) {
			String toolName = gui.getToolName();
			System.out.println(toolName);
			String toolID = gui.getToolID();
			//System.out.println(toolID);
			String quantity = gui.getQuantity();
			//System.out.println(quantity);
			String price = gui.getPrice();
			//System.out.println(price);
			String supplierID = gui.getSupplierID();
			//System.out.println(supplierID);
			JSpinner spinner = (JSpinner) findViewById(R.id.yourspinnerid);
			String text = spinner.getSelectedItem().toString();
			
		}
		if (e.getSource() == gui.btnDelete) {
			String toolName = gui.getToolName();
			toolName = null;
			System.out.println(toolName+"deleted");
			String toolID = gui.getToolID();
			toolID = null;
			String quantity = gui.getQuantity();
			quantity = null;
			String price = gui.getPrice();
			price = null;
			String supplierID = gui.getSupplierID();
			supplierID = null;
		}
		if (e.getSource() == gui.btnClear) {
			gui.setToolName("");
			gui.setToolID("");
			gui.setQuantity("");
			gui.setPrice("");
			gui.setSupplierID("");
		}
		if (e.getSource() == gui.btnSearch) {
			
		}
		if(e.getSource()== frame.list_showTime) {
			showTime=null;
			JList showTimeObject = (JList) e.getSource();
			showTime = (ShowTime) showTimeObject.getSelectedValue();
			if (showTime != null) {

			System.out.println(showTime.getTime_slot());
			frame.panel_seatMap.setVisible(true);
			populateSeat(showTime.getSeatMap());
			
			
			}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
}
