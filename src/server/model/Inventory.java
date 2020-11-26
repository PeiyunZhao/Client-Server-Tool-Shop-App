package server.model;

import java.util.*;


public class Inventory {

	private OrderList orderList;
	private LinkedHashSet <Tool> tools;
	
	public Inventory() {
		tools= new LinkedHashSet<Tool> ();
		orderList = new OrderList(); 
	}
	
	/**
	 * Method to decrease tool quantity
	 * 
	 * @param id Tool ID of tool to be decreased in quantity
	 * @param n	Tool quantity will be decreased by n
	 */
	public String decrease(int id,int n) {

		Tool tool=searchID(id);
		
		if (tool==null) {
			return "not processed\n****** tool not found ******";
		}
		
		else {
		int q = tool.getQuantity();
		if (n<=0) return "not processed\n****** invalid input ******";
		if (n>q) return  "not processed\n****** not enough stock ******";
		
		tool.decrease(n);
		
		if((q-n)<40&&tool.getReorder()==0) {
			tool.setReorder(tool.getQuantity());
			
			int orderID=orderList.newOrder(tool);
					
			String out="quantity decreased, order Generated\n" + 
						"****** Low Stock ******\n"+orderList.searchID(orderID).toString();
			return out;
		}
		
		return "Tool found, quantity decreased\n";
		}
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
	
	
}
