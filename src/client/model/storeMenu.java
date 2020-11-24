package client.model;

import java.util.Scanner;

public class storeMenu{
	
	private Model store;
		
	/**
	 * class for console menu to test store functionality
	 * @param Title name of store
	 */
	public storeMenu(String Title) {
		Scanner s = new Scanner(System.in);
		store= new Model(Title);
		System.out.println(store.importSupplier("src/myStore/suppliers.txt"));
		System.out.println(store.importItem("src/myStore/items.txt"));
		
		
		System.out.println("Welcome to "+Title);
		boolean open=true;
		while (open) {
			
			System.out.println("1. List all tools.\n" + 
					"2. Search for tool by toolName\n" + 
					"3. Search for tool by toolID\n" + 
					"4. Check item quantity\n" + 
					"5. Decrease Item quantity \n" + 
					"6. Quit");
			
			String inputString = s.next();
			
	        switch (inputString) {
	            case "1":{
	            	System.out.println(store.printAllItems());
	            	break;
	            }
	            case "2":  {
	            	System.out.print("please enter tool Name: ");
	            	String str=s.next();
	            	Tool tool=store.finditemName(str);
	            	if(tool==null) {
	            		System.out.println("Tool not found");
	            		break;
	            	}else {
	            		System.out.println("Tool Found!\n"+tool.toString());
	            		break;
	            	}
	            	
	            }
	            
	            case "3":  {
	            	System.out.print("please enter toolID: ");
	            	String str=s.next();
	            	Tool tool=store.finditemID(str);
	            	if(tool==null) {
	            		System.out.println("Tool not found");
	            		break;
	            	}else {
	            		System.out.println("Tool Found!\n"+tool.toString());
	            		break;
	            	}
	            }
	            
	            case "4":  {
	            	System.out.print("please enter toolID: ");
	            	String str=s.next();
	            	Tool tool=store.finditemID(str);
	            	if(tool==null) {
	            		System.out.println("Tool not found");
	            		break;
	            	}else {
	            		System.out.println("Tool Found!\n"+"Quantity: "
	            	+Integer.toString(tool.getQuantity()));
	            		break;
	            	}
	            }
	            
	            case "5": {
	            	System.out.print("please enter toolID: ");
	            	String str=s.next();
	            	Tool tool=store.finditemID(str);
	            	if(tool==null) {
	            		System.out.println("Tool not found");
	            		break;
	            	}else {
	            		System.out.println("Tool Found!\n"+
	            	"please enter number greater than 0 to decrease quantity by");
	            		String strn=s.next();
	            		try {
	            			int n = Integer.parseInt(strn);
	            			System.out.println(store.decreaseItem(str, n));
	            			break;
	            		} catch (Exception e) {
	            			System.out.println("Invalid Input! Try Again!");
	            			break;
	            		}
	            	}
	            }
	            
	            case "6":  {
	            	open=false;
	            	break;
	            }
	
	        }
	        
		}

	}
	
	public static void main(String[] args) {
		storeMenu myNewStore = new storeMenu("My New Store");
	
	}
}
