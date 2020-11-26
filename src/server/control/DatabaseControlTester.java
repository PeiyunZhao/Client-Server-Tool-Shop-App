/**
 * class used to connect to local database
 */
package server.control;

import java.sql.*;

public class DatabaseControlTester {

	
	public static void main(String[] args) {

		try {
			//1. Get a connection  // ENTER PASSWORD to root BEFORE RUNNING
			Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/TOOLSHOP","root","Rosewoodd1211");
			
			//2. Write a statement
			Statement myStmt=myCon.createStatement();
			
			//3. excute a sql Query
			ResultSet myRes= myStmt.executeQuery("SELECT * FROM TOOL WHERE Ttype= \"E\" ");
			
			//4. process the result set
			while(myRes.next()) {
				System.out.println(myRes.getString("Tname"));
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

}
