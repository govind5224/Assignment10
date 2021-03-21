package Assignment10;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import java.util.Scanner;
import java.util.Set;

class ContactNotFoundException extends Exception{  
	ContactNotFoundException(String s){  
	  super(s);  
	 }  
	}  
public class Contact {
	contact c; 
		static Set<contact> set1;
	 static List<contact> clist=new ArrayList<contact>();
	public static void main(String[] args) throws ContactNotFoundException, IOException, ClassNotFoundException, SQLException {
		ContactMain cm=new ContactMain();
		while(true)
		{
			System.out.println("press 1: to add a contact");
			System.out.println("press 2: to remove a contact");
			System.out.println("pre"
					+ "ss 3: to search the contact by name");
			System.out.println("press 4: to search the contact by number");
			System.out.println("press 5: to update the contact number");
			System.out.println("press 6: to sort the contact Details");
			System.out.println("press 7: to read data for file");
			System.out.println("press 8: to display contact Details");
			System.out.println("press 9: to serialize the details");
			System.out.println("press 10: to deserialize the details");
			System.out.println("press 11: to populate data from DataBase");
			System.out.println("press 12: to add contacts from Database to List of contact Details");
			Scanner sc=new Scanner(System.in);
			
			
			switch (sc.nextInt()) {
			case 1:
					cm.addContact();
					break;
			case 2:
					cm.RemoveContact();
					break;
			case 3: 
					sc.nextLine();
					System.out.println("Enter the name");
					String ssname=sc.nextLine();
					try {
					contact c1=cm.searchContactByName(ssname);
					System.out.println("contactid = "+c1.getContactid());
					System.out.println("contactemail = "+c1.getEmail());
					System.out.println("contactNumbers = "+c1.getContactNumber().toString());
					}catch (Exception e) {
						System.out.println(e);
					}
					break;
			case 4:
					sc.nextLine();
					System.out.println("Enter the number to search");
					String ss=sc.nextLine();
					try {
					contact c2=cm.SearchContactByNumber(ss);
					
					System.out.println("contactid = "+c2.getContactid());
					System.out.println("contactemail = "+c2.getEmail());
					System.out.println("contactNumbers = "+c2.getContactNumber().toString());
					}catch (Exception e) {
						System.out.println(e);
					}
					break;
			case 5:
					
					System.out.println("enter the contactid for update");
					int contactid=sc.nextInt();
					sc.nextLine();
					System.out.println("enter the number to add");
					String contactnumber=sc.nextLine();
					try {
						cm.addContactNumber(contactid, contactnumber);
					}catch (Exception e) {
						System.out.println(e);
					}
					break;
			case 6:
					cm.sortContactsByName();
					break;
			case 7:
					cm.readContactsFromFile();
					break;
			case 8:
					cm.display();
					break;
			case 9:
					cm.serializeContactDetails();
					break;
			case 10:
					List<contact> l1=cm.deserializeContact();
					for (int i = 0; i < l1.size(); i++) {
						clist.add(l1.get(i));
					}
					break;
			case 11:
					set1=cm.populateContactFromDb();
					break;
			case 12:
					if(cm.addContacts(set1))
					{
						System.out.println("Succesfully added to list of contacts-------------");
					}
					else
						System.out.println("Failed  in exicution process --------------------------");
					break;
			case 13:
				break;
			default:
					cm.display();
				break;
			}
		}
	}
	public void addContact()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("enter the contactID ");
		int id=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter the ContactName");
		String cn=sc.nextLine();
		System.out.println("Enter the email");
		String email=sc.nextLine();
		System.out.println("Enter the numbers of contactNumbers");
		int n=sc.nextInt();
		List<String> la= new ArrayList<String>(); 
		sc.nextLine();
		for (int i = 0; i < n; i++) {
			System.out.println("Enter the Mobile Number");
			String cnumber=sc.nextLine();
			la.add(cnumber);
		}
		System.out.println(id+" "+cn+" "+email+" "+la);
		 clist.add(new contact(id,cn,email,la));
		 System.out.println("Contact successfully added");
	}
	public void RemoveContact() throws ContactNotFoundException
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter contact id to remove contact");
		int a=sc.nextInt();
		int ex=0;
		for (int i = 0; i < clist.size(); i++) {
			int cid=clist.get(i).getContactid();
			if(cid==a)
			{
				ex+=1;
				clist.remove(i);
				break;
			}
		}
		if(ex==0)
		{
			throw new ContactNotFoundException("Contact not found");
		}
		else
		{
			System.out.println("Contact Succesfully removed");
		}
	}
	public contact searchContactByName(String name) throws ContactNotFoundException
	{
		
		int ex=0;
		for (int i = 0; i < clist.size(); i++) {
			String cname=clist.get(i).getContactName();
			System.out.println(cname);
			if(cname.equals(name))
			{
				ex+=1;
				return clist.get(i);
			}
		}
		if(ex==0)
		{
			throw new ContactNotFoundException("Contact not found");
		}
		return null;
	}
	public contact SearchContactByNumber(String number )throws ContactNotFoundException
	{
		int ex=0;
		for (int i = 0; i < clist.size(); i++) {
			List<String> l1=clist.get(i).getContactNumber();
			for (int j = 0; j < l1.size(); j++) {
				if(l1.get(j).contains(number))
				{
					ex+=1;
					System.out.println("Contains the number-------------------------");
					return clist.get(i);
				}
			}
			
		}
		if(ex==0)
		{
			throw new ContactNotFoundException("Contact not found");
		}
		return null; 
	}
	public void addContactNumber(int contactId, String contactNo) throws ContactNotFoundException
	{
		int ex=0;
		for (int i = 0; i < clist.size(); i++) {
			int id1=clist.get(i).getContactid();
			if(id1==contactId)
			{
				ex+=1;
				clist.get(i).getContactNumber().add(contactNo);
				System.out.println("contactNo for contactid = "+contactId+" is updated ");
			}
			
			
		}
		if(ex==0)
		{
			throw new ContactNotFoundException("Contact not found------------");
		}
	}
	public void  sortContactsByName()
	{
		sortContactsByName s1=new sortContactsByName();
		Collections.sort(clist,s1);
		System.out.println("Contactid"+"\t"+"ContactName"+"\t"+"Contactemial"+"\t"+"ContactNumbers");
		for (int i = 0; i < clist.size(); i++) {
			System.out.println(clist.get(i).getContactid()+"\t"+clist.get(i).getContactName()+"\t"+clist.get(i).getEmail()+"\t"+clist.get(i).getContactNumber().toString());
		}
		
	}
	public void readContactsFromFile()
	{
		int ex=0;
		 try{
             File file = new File("m2.txt");
             FileInputStream ft = new FileInputStream(file);
             DataInputStream in = new DataInputStream(ft);
             BufferedReader br = new BufferedReader(new InputStreamReader(in));
             String strline;
             while((strline = br.readLine()) != null){
            	 System.out.println(br.readLine());
                 String[] s1=br.readLine().split(",");
                 List<String> l=new ArrayList<String>();
                 for (int i = 3; i < s1.length; i++) {
					l.add(s1[i]);
				}
                 clist.add(new contact(Integer.parseInt(s1[0]), s1[1], s1[2], l));
             }
             in.close();
         }catch(Exception e){
        	 ex+=1;
             System.out.println(e);
         }
		 if(ex==0)
		 System.out.println("added successfully-----------------------------------------------");
		 System.out.println();
	}
	
	public void display()
	{
		System.out.println("Contactid"+"\t"+"ContactName"+"\t"+"Contactemial"+"\t"+"ContactNumbers");
		for (int i = 0; i < clist.size(); i++) {
			System.out.println(clist.get(i).getContactid()+"\t"+clist.get(i).getContactName()+"\t"+clist.get(i).getEmail()+"\t"+clist.get(i).getContactNumber().toString());
		}
	}
	public void serializeContactDetails() throws IOException
	{
		FileOutputStream fos=new FileOutputStream("C:\\Users\\HP\\eclipse\\PersistentSystems\\src\\Assignment10\\contactInfo.txt");
		ObjectOutputStream os=new ObjectOutputStream(fos);
		for (int i = 0; i < 1; i++) {
			contact c=clist.get(0);
			os.writeObject(c);
		}
		System.out.println("Succesfully serialized------------------------------------------");
		System.out.println();
		os.close();
		fos.close();
	}
	public List<contact> deserializeContact() throws IOException, ClassNotFoundException
	{
		FileInputStream fis=new FileInputStream("C:\\Users\\HP\\eclipse\\PersistentSystems\\src\\Assignment10\\contactInfo.txt");
		ObjectInputStream ois=new ObjectInputStream(fis);
		List<contact> al=new ArrayList<contact>();
		al.add((contact) ois.readObject());
		System.out.println("successfully deserialized ---------------------------------- ");
		System.out.println();
		return al;
	}
	public Set<contact> populateContactFromDb() throws ClassNotFoundException, SQLException
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Admin","Admin");
		Statement s1=con.createStatement();
		ResultSet rs=s1.executeQuery("Select * from contact_tbl");
		Set<contact> set=new HashSet<contact>();
		while(rs.next())
		{
			String s[]=null;
			try {
			 s=rs.getString(4).split(",");
			}catch (Exception e) {
				// TODO: handle exception
			}
			List<String> l12=new ArrayList<String>();
			if(s!=null)
			for (int i = 0; i < s.length; i++) {
				l12.add(s[i]);
			}
			set.add(new contact(rs.getInt(1), rs.getString(2), rs.getString(3), l12));
		}
		System.out.println("Succesfully added to set------------------------------------------");
		return set;
		
	}
	public Boolean addContacts(Set<contact> newContacts)
	{
		Iterator<contact> i = newContacts.iterator(); 
        while (i.hasNext()) 
            clist.add(i.next());
        return true;
	}
	
}
 class sortContactsByName implements Comparator<contact>
{
	public int compare(contact o1,contact o2)
	{
		if(o1.getContactName().compareTo(o2.getContactName())>0)
		{
			return 1;
		}
		return 0;
	}

}
