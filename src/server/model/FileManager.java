package server.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class FileManager {
	
	private Model shop;
	
	public FileManager(Model shop) {
		this.shop=shop;
	}
	
	/**
	 * function to import csv file of suppliers into store
	 * 
	 * @param file filename of supplier list to be imported
	 * @throws IOException from FileReader
	 */
	public void importSuppliers(String file) throws IOException {
		BufferedReader csvReader = new BufferedReader(new FileReader(file));
		String row = csvReader.readLine();
		while (row != null) {
		    String[] data = row.split(";");
		    Supplier s= new Supplier(data[0],data[1],data[2],data[3]);
		    shop.addSupplier(s);
		    row = csvReader.readLine();
		}
		csvReader.close();
	}
	
	/**
	 * method to import csv file of tools into store
	 * 
	 * @param file csv filename of tool list to be imported
	 * @throws IOException from FileReader
	 */
	public void importItems(String file) throws IOException {
		BufferedReader csvReader = new BufferedReader(new FileReader(file));
		String row = csvReader.readLine();
		while (row != null) {
		    String[] data = row.split(";");
		    Tool i= new Tool(Integer.parseInt(data[0]),data[1],Integer.parseInt(data[2]),
		    		Double.parseDouble(data[3]),Integer.parseInt(data[4]));
		    shop.addItem(i);
		    
		    row = csvReader.readLine();
		}
		csvReader.close();
	}
	
	/**
	 * method intended to export orders as text into a .txt file
	 * 
	 * @param name name of file to write to
	 * @param s content to be written
	 */
	public void exportOrders (String name,String s) {
		try {
			File file = new File(name);
			if (!file.exists()) {
				file.createNewFile();
				}
			
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(s);
			bw.close();
	  
	        } 
		catch (IOException e) 
		{
			e.printStackTrace();
			}
	}
}
