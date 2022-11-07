
//A8-group-A-Database Connectivity: 
//Write a program to implement MySQL database connectivity with any front end language to implement Database navigation operations (add, delete, edit etc.) 
//Java- JDBC connectivity-create,insert,update,delete
// need to use project database-any one
// connector-v-5 to 8-mysql-connector-java-5.1.49-bin-jar
//package jdbcnew;

//mysql databse connectivity in Java

import java.sql.*;//classes,interfaces and methods
import java.util.Scanner;

public class Connect
{
	
	public static void input() {
		int roll;
		String name;
		float cgpa;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter roll number: \n");
		roll = sc.nextInt();
		System.out.print("Enter name: \n");
		name = sc.next();
		System.out.print("Enter cgpa: \n");
		cgpa = sc.nextFloat();
		try {
		Class.forName("com.mysql.jdbc.Driver");  // step-1-register the driver class
		//step2- establish connection
		Connection con = DriverManager.getConnection(  
		"jdbc:mysql://10.10.12.108:3306/t31332db","t31332","t31332");
		Statement stmt = con.createStatement();//step-3 create the statement object
		String sql = "INSERT INTO pictStudent " +"VALUES (null,'" + name + "'," + roll + "," + cgpa + ")";
		stmt.executeUpdate(sql);
		System.out.print("Student added successfully!\n");
		}
		catch(ClassNotFoundException | SQLException e)
		{ 
			System.out.println(e);
		}
	}
	
	public static void update() {
		int roll;
		String name;
		float cgpa;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter roll number of student to be updated: \n");
		roll = sc.nextInt();

		try {
		Class.forName("com.mysql.jdbc.Driver");  // step-1-register the driver class
		//step2- establish connection
		Connection con = DriverManager.getConnection(  
		"jdbc:mysql://10.10.12.108:3306/t31332db","t31332","t31332");
		Statement stmt = con.createStatement();//step-3 create the statement object
		String sql = "SELECT * FROM pictStudent WHERE roll = " + roll;
		ResultSet r = stmt.executeQuery(sql);
		
		if(r != null) {
			System.out.print("Data to be updated: \n");
			while(r.next()){
					System.out.println(r.getInt(1) + "\t" + r.getString(2) + "\t" + r.getInt(3) + "\t" + r.getFloat(4));
			}
			String updateQuery = "UPDATE pictStudent SET ";
			System.out.print("Enter new name(0 for no update): ");
			name = sc.next();
			if(name.charAt(0) != '0'){
				updateQuery += "name = '"+ name + "' WHERE roll = " + roll;
				stmt.executeUpdate(updateQuery);
				System.out.print("Student name updated successfully!\n");
			}
			System.out.print("Enter new cgpa(0 for no update): ");
			cgpa = sc.nextFloat();
			if(cgpa != 0){
				updateQuery = "UPDATE pictStudent SET ";
				updateQuery += "cgpa = "+ cgpa + "WHERE roll = " + roll;
				stmt.executeUpdate(updateQuery);
				System.out.print("Student cgpa updated successfully!\n");
			}
			System.out.print("Exiting update menu...\n");
		}else {
			System.out.print("Student does not exists with roll number "+ roll + "\n");
		}
		}
		catch(ClassNotFoundException | SQLException e)
		{ 
			System.out.println(e);
		}
	}
	
	public static void delete() {
		int roll;
		int conf;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter roll number of student to be deleted: \n");
		roll = sc.nextInt();

		try {
		Class.forName("com.mysql.jdbc.Driver");  // step-1-register the driver class
		//step2- establish connection
		Connection con = DriverManager.getConnection(  
		"jdbc:mysql://10.10.12.108:3306/t31332db","t31332","t31332");
		Statement stmt = con.createStatement();//step-3 create the statement object
		String sql = "SELECT * FROM pictStudent WHERE roll = " + roll;
		ResultSet r = stmt.executeQuery(sql);
		
		if(r != null) {
			System.out.print("Data to be deleted: \n");
			while(r.next()){
					System.out.println(r.getInt(1) + "\t" + r.getString(2) + "\t" + r.getInt(3) + "\t" + r.getFloat(4));
			}
			System.out.print("Enter '1' to delete: ");
			conf = sc.nextInt();
			if(conf == 1){
				stmt.executeUpdate("DELETE FROM pictStudent WHERE roll = " + roll);
				System.out.print("Student deleted successfully!\n");
			}else {
				System.out.print("Student deletion failed!\n");
			}
			System.out.print("Exiting delete menu...\n");
		}else {
			System.out.print("Student does not exists with roll number "+ roll + "\n");
		}
		}
		catch(ClassNotFoundException | SQLException e)
		{ 
			System.out.println(e);
		}
	}
	
	public static void display() {
		try {
		Class.forName("com.mysql.jdbc.Driver");  // step-1-register the driver class
		//step2- establish connection
		Connection con = DriverManager.getConnection(  
		"jdbc:mysql://10.10.12.108:3306/t31332db","t31332","t31332");
		Statement stmt = con.createStatement();//step-3 create the statement object
		String sql = "SELECT * FROM pictStudent";
		ResultSet r = stmt.executeQuery(sql);
		
		if(r != null) {
			System.out.print("Data to be deleted: \n");
			while(r.next()){
					System.out.println(r.getInt(1) + "\t" + r.getString(2) + "\t" + r.getInt(3) + "\t" + r.getFloat(4));
			}
		}else {
			System.out.print("Student does not exists\n");
		}
		}
		catch(ClassNotFoundException | SQLException e)
		{ 
			System.out.println(e);
		}
	}
	
	public static void create() {
		try
		{  
			Class.forName("com.mysql.jdbc.Driver");  // step-1-register the driver class
			//step2- establish connection
			Connection con = DriverManager.getConnection(  
			"jdbc:mysql://10.10.12.108:3306/t31332db","t31332","t31332");
			Statement stmt = con.createStatement();//step-3 create the statement object
			//step-4- to execute the queries-execute(string sql),executeUpdate(),executeQuery()
			DatabaseMetaData dbm = con.getMetaData();
			// check if "employee" table is there
			ResultSet tables = dbm.getTables(null, null, "pictStudent", null);
			
			if (tables.next()) {
				  // Table exists
				System.out.print("The table already exists!\n");
			}
			else {
				  // Table does not exist
			stmt.executeUpdate("create table pictStudent(id int unsigned not null auto_increment, primary key(id), name varchar(30), roll int(5), cgpa float(6))");
			System.out.print("Table created successfully!\n");
			}
		}
		catch(ClassNotFoundException | SQLException e)
		{ 
			System.out.println(e);
		}
	}
	
    public static void main(String args[])
    {  
    	int option = 1;
    	Scanner sc = new Scanner(System.in);
    	while(option>0 && option<6) {
    		System.out.print("<----- Menu ----->\n1. Create \n2. Insert \n3. Update \n4. Delete \n5. Display \n");
    		System.out.print("Enter option(-1 to exit): ");
    		option = sc.nextInt();
    		switch(option) {
    		case 1: create();
    				break;
    		case 2: input();
    				break;
    		case 3: update();
    				break;
    		case 4: delete();
    				break;
    		case 5: display();
    				break;
    		default: System.out.print("Quiting...");
    				option = -1;
    				break;
    		}
    	}
    }
}
