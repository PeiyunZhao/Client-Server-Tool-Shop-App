package server.control;

public class CONTROLTESTER {

	public static void main(String[] args) {
		ModelControl mc = new ModelControl();
		mc.getDatabaseControl().loadFromDatabase();
//		System.out.println(mc.printAllTools());
//		System.out.println(mc.printAllSuppliers());
	}
}
