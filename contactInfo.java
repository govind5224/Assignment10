package Assignment10;

import java.io.Serializable;
import java.util.List;

public class contactInfo implements Serializable{
	private int contactid;
	private String contactName;
	private String email;
	private List<String> contactNumber;
	public int getContactid() {
		return contactid;
	}
	public void setContactid(int contactid) {
		this.contactid = contactid;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(List<String> contactNumber) {
		this.contactNumber = contactNumber;
	}
	public contact( int contactid, String contactName, String email,List<String> contactNumber) {
		this.contactid=contactid;
		this.contactName=contactName;
		this.email=email;
		this.contactNumber=contactNumber;
	}
}
