//A8-group-A-Database Connectivity: 
//Write a program to implement MySQL database connectivity with any front end language to implement Database navigation operations (add, delete, edit etc.) 
//Java- JDBC connectivity-create,insert,update,delete
// need to use project database-any one
// connector-v-5 to 8-mysql-connector-java-5.1.49-bin-jar
//package jdbcnew;

import java.sql.*;//classes,interfaces and methods

public class Test
{
    public static void main(String args[])
    {  
		try
		{  
			Class.forName("com.mysql.jdbc.Driver");  // step-1-register the driver class
			//step2- establish connection
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://10.10.12.108:3306/t31323db","t31323","t31323");
			Statement stmt=con.createStatement();//step-3 create the statement object
			//step-4- to execute the queries-execute(string sql),executeUpdate(),executeQuery()
			stmt.executeUpdate("create table city3(id int unsigned not null auto_increment, primary key(id), name varchar(30))");
			
			String sql = "INSERT INTO city3 " +"VALUES (null, 'Pune')";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO city3 " +"VALUES (null, 'Mumbai')";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO city3 " +"VALUES (null, 'Dubai')";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO city3 " +"VALUES (null, 'Nagpur')";
			stmt.executeUpdate(sql);
			ResultSet r = stmt.executeQuery("select * from city3");
			while(r.next()) 
			{
				System.out.println(r.getInt(1) + "\t" + r.getString(2));
			}
		}
		catch(ClassNotFoundException | SQLException e)
		{ 
			System.out.println(e);
		}
    }
}
