package papercutUtility.ldap;

import com.unboundid.ldap.sdk.*;
import papercutUtility.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The LDAPCrawler class contains predefined methods 
 * for easier access to LDAP search operations.
 * @author Void
 */
public class LDAPCrawler {
	
	private String ldapHostname;
	private int ldapPort;
	private String ldapAccount;
	private String ldapAccountPassword;
	private String ldapBaseDn;
	
	private LDAPConnector ldapConnector;
	private LDAPConnection ldapConnection;
	
	public LDAPCrawler(String ldapHostname, int ldapPort, String ldapAccount, 
					String ldapAccountPassword, String ldapBaseDn) {
		this.ldapHostname = ldapHostname;
		this.ldapPort = ldapPort;
		this.ldapAccount = ldapAccount;
		this.ldapAccountPassword = ldapAccountPassword;
		this.ldapBaseDn = ldapBaseDn;
	}
	
	/**
	 * Connect to LDAP and retrieve the user list starting 
	 * from the baseDN provided in the constructor of this class.
	 * LDAP fields retrieved are "sAMAccountName", "name", "mail", "department" and "office".
	 * @return ldapUserList The list of users found in LDAP.
	 */
	public List<LDAPUser> getLdapUsers() {
		
		String ldapUserFilter = "(&(objectClass=user)(!(objectCategory=computer)))";	// Search for all users
		List<SearchResultEntry> results = null;
		List<LDAPUser> ldapUserList = new ArrayList<LDAPUser>();
		
		try {
			ldapConnector = new LDAPConnector(ldapHostname, ldapPort, ldapAccount, ldapAccountPassword, ldapBaseDn, ldapUserFilter);
			ldapConnection = ldapConnector.getConnection();
			results = ldapConnector.getResults(ldapConnection);
		} catch (LDAPException e) {
			e.printStackTrace();
		}
	
		// Create a new ldapUserList item for each retrieved user
		for (SearchResultEntry e : results) {
			String currentName = e.getAttributeValue("sAMAccountName");
			String currentFullName = e.getAttributeValue("name");
			String currentEmail = e.getAttributeValue("mail");
			String currentDepartment = e.getAttributeValue("department");
			String currentOffice;
			if (e.getAttributeValue("office")!=null)
				currentOffice = e.getAttributeValue("office");
			else
				currentOffice = e.getAttributeValue("physicalDeliveryOfficeName");
			ldapUserList.add(new LDAPUser(currentName.toLowerCase(), currentFullName, currentEmail, currentDepartment, currentOffice));
		}
			
		return ldapUserList;
	}

}
