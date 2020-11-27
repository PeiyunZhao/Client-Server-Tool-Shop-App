package server.model;

import java.util.*;

import server.control.ModelControl;


public class Inventory {

	private OrderList orderList;
	private LinkedHashSet <Tool> tools;
	private ModelControl modelControl;
	
	public Inventory() {
		tools= new LinkedHashSet<Tool> ();
		orderList = new OrderList(); 
	}
	
	/**
	 * Method to decrease tool quantity
	 * 
	 * @param id Tool ID of tool to be decreased in quantity
	 * @param n	Tool quantity will be decreased by n
	 * 
	 * @return 0 if processed -1 if process error or returns OrderID of order generated
	 */
	public int decrease(int id,int n) {

		Tool tool=searchID(id);
		
		if (tool==null) {
			return -1;
		}
			
		int q = tool.getQuantity();
		
		if (n<=0) return -1;
		
		if (n>q && (q-n) > 40) {
			tool.decrease(n);
			return  0;
		}
		
		
		if((q-n)<40) {
			if(tool.getReorder()==0) {
				tool.setReorder(50-q-n);
				tool.decrease(n);
				
				Order o =orderList.newOrder(tool);
				
				return o.getOrderID();
			}
			else if (tool.getReorder()!=0) {
				tool.decrease(n);
				return 0;
			}
		}
		
		return -1;
		
	}
	
	/**
	 * prints all tools within list 
	 * 
	 * @return list of all tool and details
	 */
	public String printAllTools() {
		String out="";
		for (Tool i :tools) {
			out+=i.toString();
		}
		return out;
	}
	
	/**
	 * adds tool to list of tools
	 * 
	 * 
	 * @param i tool to be added
	 */
	public void addTool(Tool i) {
		tools.add(i);
	}
	
	public void addOrder(Order o) {
		orderList.addOrder(o);
	}
	
	/**
	 * removes tool from list of tools
	 * 
	 * @param id tool ID of tool to be removed
	 */
	public void removeTool(int id) {
		Tool i= searchID(id);
		tools.remove(i);
	}
	
	public void addOrderLine(OrderLine ol) {
		orderList.addOrderLine(ol);
	}
	
	/**
	 * searches for tool based on tool ID
	 * @param str tool id input as string
	 * @return tool if found null if not
	 */
	public Tool searchID(int id) {
		
		for (Tool t: tools) {
			if (t.getID()==id) return t;
		}
		return null;
	}
	
	/**
	 * searches for tool based on tool name
	 * @param str tool name input as string
	 * @return tool if found null if not
	 */
	public Tool searchName(String str) {
		
		for (Tool i: tools) {
			if (i.getName().equals(str)) return i;
		}
		
		return null;
	}
	public int getToolListSize(){
		return tools.size();
	}
	
	public int getOrderListSize(){
		return orderList.size();
	}

	public ModelControl getModelControl() {
		return modelControl;
	}

	public void setModelControl(ModelControl mc) {
		this.modelControl = mc;
	}
	
	
}
