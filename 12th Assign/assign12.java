/*
* Problem Statement :-
* Database Connectivity:
* Write a program to implement Mongo DB database connectivity with any front end
* language to implement Database navigation operations(add, delete, edit etc.)
*/


package assignmentNo_B4;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.util.Scanner;
import java.util.Iterator;
import org.bson.Document;
import com.mongodb.MongoClient;


public class AssignmentNo_B4
{
	public static void main( String args[] ) 
	{ 
	try
	{
		Scanner sc = new Scanner(System.in);
		int studentID = 0, UG_POYear = 0;
		String fName =  "", lName = "", UG_Dept = "", curr_city = "", email = "";
		MongoClient mongo = new MongoClient( "localhost" , 27017 );
		System.out.println("\n\t Connected to the database successfully...!!");
		MongoDatabase database = mongo.getDatabase("assignment_no_12");
		MongoCollection<Document> collection = database.getCollection("Student");
		System.out.println("\n\t Collection Student selected successfully...!!");
		int choice;
		while(true)
		{
			System.out.println("\n\t === Main Menu === \n\t\t 1. Insert Data"+ "\n\t\t 2. Display Data"+ "\n\t\t 3. Update Document"+ "\n\t\t 4. Delete 									Document"+ "\n\t\t 5. Drop Collection"+ "\n\t\t 6. Exit");
			System.out.print("\n\t Enter Choice : ");
			choice = sc.nextInt();
			switch(choice)
			{
			case 1:
					System.out.print("\n\t Enter the ID of Student : ");
					studentID = sc.nextInt();
					System.out.print("\n\t Enter the First Name of Student : ");
					fName = sc.next();
					System.out.print("\n\t Enter the Last Name of Student : ");
					lName = sc.next();
					System.out.print("\n\t Enter the UG_POYear of Student : ");
					UG_POYear = sc.nextInt();
					System.out.print("\n\t Enter the UG_Dept of Student : ");
					UG_Dept = sc.next();
					System.out.print("\n\t Enter the curr_city of Student : ");
					curr_city = sc.next();
					System.out.print("\n\t Enter the email of Student : ");
					email = sc.next();
					Document document = new Document("_id", studentID).append("fName", fName).append("lName", lName).append("UG_POYear", 												UG_POYear).append("UG_Dept", UG_Dept).append("curr_city", curr_city).append("email", email);
					collection.insertOne(document);
					System.out.println("\n\t Document Inserted Successfully...!! \n");
					break;
			case 2:
					FindIterable<Document> iterDoc = collection.find();
					Iterator<Document> it = iterDoc.iterator();
					while (it.hasNext()) {
						System.out.println(it.next());
					} 
					break;
			case 3:
					int ch = 0;
					System.out.print("\n\t Enter ID of student to update Data : ");
					studentID = sc.nextInt();
					System.out.print("\n\t\t == UPDATE == \n\t\t\t 1. fName"+ "\n\t\t\t 2. lName \n\t\t\t 3. UG_POYear"+ "\n\t\t\t 4. UG_Dept \n\t\t\t 								5. curr_city"+ "\n\t\t\t 6. email \n\t\t\t 7. Cancel"+ "\n\t\t Enter Choice = ");
					ch = sc.nextInt();
					if(ch == 1)
					{
						System.out.print("\n\t Enter new First name of student : ");
						fName = sc.next();
						collection.updateOne(Filters.eq("_id", studentID), 
						Updates.set("fName", fName));
						System.out.println("\n\t Document Updated Successfully...!! \n");
					}
					else if(ch == 2)
					{
						System.out.print("\n\t Enter new Last name of student : ");
						lName = sc.next();
						collection.updateOne(Filters.eq("_id", studentID), 
						Updates.set("lName", lName));
						System.out.println("\n\t Document Updated Successfully...!! \n");
					}
					else if(ch == 3)
					{
						System.out.print("\n\t Enter new UG PassOut Year of student : ");
						UG_POYear = sc.nextInt();
						collection.updateOne(Filters.eq("_id", studentID), 
						Updates.set("UG_POYear", UG_POYear));
						System.out.println("\n\t Document Updated Successfully...!! \n");
					}
					else if(ch == 4)
					{
						System.out.print("\n\t Enter new UG Department of student : ");
						UG_Dept = sc.next();
						collection.updateOne(Filters.eq("_id", studentID), 
						Updates.set("UG_Dept", UG_Dept));
						System.out.println("\n\t Document Updated Successfully...!! \n");
					}
					else if(ch == 5)
					{
						System.out.print("\n\t Enter new Current City of student : ");
						curr_city = sc.next();
						collection.updateOne(Filters.eq("_id", studentID), 
						Updates.set("curr_city", curr_city));
						System.out.println("\n\t Document Updated Successfully...!! \n");
					}
					else if(ch == 6)
					{
						System.out.print("\n\t Enter new email of student : ");
						email = sc.next();
						collection.updateOne(Filters.eq("_id", studentID), 
						Updates.set("email", email));
						System.out.println("\n\t Document Updated Successfully...!!");
					}
					else if(ch == 7)
					{
						System.out.println("\n\t Operation Cancelled Successfully...!!");
						break;
					}
					else
					{
						System.out.println("\n\t Invalid Choice...!!! \n");
						break;
					} 
					break;
			case 4:
					System.out.print("\n\t Enter ID of student to delete Data : ");
					studentID = sc.nextInt();
					collection.deleteOne(Filters.eq("_id", studentID));
					System.out.println("\n\t Document Deleted Successfully...!!\n");
					break;
		          case 5:
					collection.drop();
					System.out.println("\n\t\t\t\t _______ Thank You ..! ______ \n");
					sc.close();
					mongo.close();
					System.exit(0);
					break; 
			case 6:
					System.out.println("\n\t\t\t\t _______ Thank You ..! ______ \n");
					sc.close();
					mongo.close();
					System.exit(0);
			default:
					System.out.println("\n\t Invalid Choice...!!! \n");
					break;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
