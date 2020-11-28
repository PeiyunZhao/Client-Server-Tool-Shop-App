package server.model;

import server.control.ModelControl;

public class Model {
	private Inventory inventory;
	private SupplierList suppliers;
	private CustomerList customers;
	private ModelControl modelControl;
	
	public Model(ModelControl mc) {
		inventory= new Inventory();
		suppliers = new SupplierList();
		customers = new CustomerList();
		this.modelControl= mc;
	}
	
	public void importTool(Tool t) {
		inventory.addTool(t);
	}
	
	public void importSupplier(Supplier s) {
		suppliers.addSupplier(s);
	}
	
	public void importOrderLine(OrderLine ol) {
		inventory.addOrderLine(ol);
	}
	
	public void importOrder(Order o) {
		inventory.addOrder(o);
	}
	public void importCustomer(Customer c) {
		customers.addCustomer(c);
	}
	
	public String printAllTools() {
		return inventory.printAllTools();
	}

	public String printAllSuppliers() {
		return suppliers.printAllSuppliers();
	}
	
	public String printAllCustomers() {
		return customers.printAllCustomers();
	}
	public String printAllOrders() {
		return inventory.printAllOrders();
	}
	
	public Inventory getInventory() {
		return inventory;
	}

}
