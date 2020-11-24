package server.model;

import java.util.*;


public class ToolList {

	private OrderList orders;
	private LinkedHashSet <Tool> tools;
	
	public ToolList() {
		tools= new LinkedHashSet<Tool> ();
		orders = new OrderList(); 
	}
	
	/**
	 * Method to decrease tool quantity
	 * 
	 * @param id Tool ID of tool to be decreased in quantity
	 * @param n	Tool quantity will be decreased by n
	 */
	public String decrease(String id,int n) {

		Tool i=searchID(id);
		
		if (i==null) {
			return "not processed\n****** tool not found ******";
		}
		
		else {
		int q = i.getQuantity();
		if (n<=0) return "not processed\n****** invalid input ******";
		if (n>q) return  "not processed\n****** not enough stock ******";
		
		i.decrease(n);
		
		if((q-n)<40&&i.getReorder()==0) {
			i.setReorder(50-i.getQuantity());
			
			String out="quantity decreased, order placed\n" + 
						"****** Low Stock ******\n"+orders.newOrder(i);
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
			out+=i.toString()+"\n";
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
	public void removeTool(String id) {
		Tool i= searchID(id);
		tools.remove(i);
	}
	
	/**
	 * searches for tool based on tool ID
	 * @param str tool id input as string
	 * @return tool if found null if not
	 */
	public Tool searchID(String str) {
		
		for (Tool i: tools) {
			if (i.getToolID().equals(str)) return i;
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
	
	
}
