package client.model;

import java.io.IOException;

public class Model {
	private String name; 
	private SupplierList suppliers;
	private ToolList tools;
	private FileManager file;
	
	public Model(String name) {
		this.setName(name);
		tools = new ToolList();
		suppliers = new SupplierList();
		file= new FileManager(this);
	}
	
	/**
	 * imports csv file of suppliers 
	 * 
	 * @param fn Filename input as String
	 * @return output message
	 */
	public String importSupplier(String fn){
		try {
			file.importSuppliers(fn);
			return "import successful";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "import unsuccessful";
	}
	
	/**
	 * imports csv file of items
	 * 
	 * @param fn Filename input as String
	 * @return output message
	 */
	public String importItem(String fn){

			try {
				file.importItems(fn);
				return "import successful";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "import unsuccessful";
	
	}
	
	/**
	 * prints all items within items list 
	 * 
	 * @return string of list of all item details
	 */
	public String printAllItems() {
		return tools.printAllTools();
	}
	
	/**
	 * method to simulate the purchase of items. Will automatically generate order if item
	 * quantity goes below 40
	 * 
	 * @param id item ID input as String
	 * @param n quantity to decrease stock by
	 * @return message regarding execution of the method
	 */

	public String decreaseItem(String id, int n) {
		return tools.decrease(id, n);
	}
	
	/**
	 * adds item to list of items
	 * 
	 * @param i item to be added
	 */
	public void addItem(Tool i) {
		tools.addTool(i);
	}
	/**
	 * adds supplier to list of suppliers
	 * @param s supplier to be added
	 */
	public void addSupplier(Supplier s) {
		suppliers.addSupplier(s);
	}
	
	
	/**
	 * finds supplier within suppliers list
	 * 
	 * @param id supplier ID input as String
	 * @return supplier with matching ID
	 */
	public Supplier findSupplierID(String id) {
		return suppliers.searchID(id);
	}

	/**
	 * finds item within items list
	 * 
	 * @param id item ID input as String
	 * @return item with matching ID
	 */
	public Tool finditemID(String id) {
		return tools.searchID(id);
	}

	/**
	 * finds item within items list
	 * 
	 * @param name of item input as string
	 * @return item with matching name
	 */
	public Tool finditemName(String name) {
		return tools.searchName(name);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
