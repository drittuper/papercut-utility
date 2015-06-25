package papercutUtility.model;

import java.io.Serializable;

/**
 * This class represents the LDAP User object model. This class can be used
 * throughout all layers, the data layer, the controller layer and 
 * the view layer.
 * @author Vo1d
 */
public class LDAPUser implements Serializable, Comparable<LDAPUser> {
	
	// Constants

	private static final long serialVersionUID = 1L;

	// Class member properties
	
	private String username;
	private String fullName;
	private String email;
	private String department;
	private String office;

	public LDAPUser(String username, String fullName, String email,
					String department, String office) {
		super();
		this.username = username;
		this.fullName = fullName;
		this.email = email;
		this.department = department;
		this.office = office;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username.toLowerCase();
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	@Override
	public String toString() {
		return String
				.format("username=%s, fullName=%s, email=%s, department=%s, office=%s",
						username, fullName, email, department, office);
	}
	
	@Override
	public int compareTo(LDAPUser other) {
		int last = this.username.compareTo(other.username);
		return last == 0 ? this.username.compareTo(other.username) : last;
	}

}