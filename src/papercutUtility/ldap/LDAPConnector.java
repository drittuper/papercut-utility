package papercutUtility.ldap;

import com.unboundid.ldap.sdk.*;
import java.util.List;

/**
 * The Connector class provides a means of connecting to LDAP.
 * @author Void
 */
public class LDAPConnector {
	
	private static String server;
	private static int port;
	private static String userName;
	private static String password;
	private static String baseDN;
	private static String filter;
	
	/**
	 * The constructor assigns the provided parameters 
	 * to its private member variables.
	 * @param server The domain controller server
	 * @param port The domain controller server port
	 * @param userName The domain account with read only access
	 * @param password The domain account password
	 * @param baseDN The base from which to start the search (for limiting the search scope)
	 * @param filter The search filter which determines what to look for
	 * @throws LDAPException If something fails at LDAP level
	 */
	public LDAPConnector(String server, int port, String userName, 
						String password, String baseDN, String filter) throws LDAPException {
		super();
		LDAPConnector.server = server;
		LDAPConnector.port = port;
		LDAPConnector.userName = userName;
		LDAPConnector.password = password;
		LDAPConnector.baseDN = baseDN;
		LDAPConnector.filter = filter;
	}
	
	/**
	 * Creates a connection object with the supplied parameters
	 * and returns it.
	 * @return LDAPConnection The established connection object.
	 * @throws LDAPException If supplied connection information is wrong.
	 */
	public LDAPConnection getConnection() throws LDAPException {
		return new LDAPConnection(server, port, userName, password);
	}	// end getConnection()
		
	/**
	 * Uses an established LDAP connection to perform
	 * a search of the predefined baseDN and filter,
	 * then returns the searchResults. 
	 * Returns null if the passed connection object is not established.
	 * @param connection The established LDAP connection
	 * @return searchResult The found users.
	 * @throws LDAPSearchException If something fails on LDAP level.
	 */
	public List<SearchResultEntry> getResults(LDAPConnection connection) throws LDAPSearchException {
		SearchResult searchResult;
		if (connection.isConnected()) {
			//System.out.println("LDAP Connection established.");
			searchResult = connection.search(baseDN, SearchScope.SUB, filter);
			return searchResult.getSearchEntries();
		}
		//System.out.println("Unable to establish LDAP connection. Re-check connection info!");
		return null;
	}	// end getResults()

}
