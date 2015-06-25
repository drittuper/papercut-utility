package papercutUtility.main;

import com.unboundid.ldap.sdk.*;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.Collections;

import papercutUtility.api.*;
import papercutUtility.gui.*;
import papercutUtility.model.*;
import papercutUtility.ldap.*;

/**
 * The Class MainApp is the desktop application 
 * implementation of the PaperCutUtility Application.
 * @author Vo1d
 */
public class MainApp {
	
	// Connection variables
	private static String dcHostName;
	private static int dcPort;
	private static String dcBaseDn;
	private static String dcAccount;
	private static String dcAccountPassword;
	private static String ppcHostName;
	private static String ppcAccountPassword;
	
	// What must be updated
	private static boolean mustUpdateDepartment;
	private static boolean mustUpdateOffice;
	private static boolean mustUpdateEmail;
	
	private static final String filter = "(&(objectClass=user)(!(objectCategory=computer)))";	// Search for all users
	private static String outputMessage;
	
	private static LDAPConnector ldapConnector;
	private static LDAPCrawler ldapCrawler;
	
	public static void main(String[] args) {
		
		final ApplicationWindow appWindow = new ApplicationWindow();
		appWindow.frameSetVisible(true);		
		
		/*
		 * The button "Test Connection" only test the connection
		 * to the LDAP server.
		 */
		appWindow.getBtnTestConnection().addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent evt) {
				try {
					dcHostName = appWindow.getDcHostName().getText();
					dcPort = Integer.parseInt(appWindow.getDcPort().getText());
					dcBaseDn = appWindow.getBaseDn().getText();
					dcAccount = appWindow.getDcAccount().getText();
					dcAccountPassword = appWindow.getDcAccountPassword().getText();
					ppcHostName = appWindow.getPpcHostName().getText();
					ppcAccountPassword = appWindow.getPpcAccountPassword().getText();
				} catch (NumberFormatException e) {
					appWindow.getTextPaneOutput().setText("Enter correct connection settings prior to testing the connection.");	
					return;
				}
				
				try {
					ldapConnector = new LDAPConnector(dcHostName, dcPort, dcAccount, 
									dcAccountPassword, dcBaseDn, filter);
					if(ldapConnector.getConnection().isConnected()) {
						appWindow.getTextPaneOutput().setText("Connection to LDAP established.");
					}
				} catch (LDAPException e) {
					appWindow.getTextPaneOutput().setText("Unable to establish LDAP connection. Enter correct connection settings.");
					return;
				};
				
			}	// end actionPerformed
		});	// end ActionListener
		
		/*
		 * The button "Update" first retrieves all the users from LDAP and sorts the result.
		 * Then it retrieves all the users from PaperCut (which are sorted by default).
		 * After that it cross-references the users and creates a new filtered list of users
		 * which exist both in LDAP and PaperCut.
		 * Finally it loops through that filtered list and performs the selected updates.
		 * If nothing is selected for update, it only notifies the user to select something.
		 */
		appWindow.getBtnUpdate().addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent evt) {
				List<LDAPUser> ldapUserList;
				Vector ppcUserVector= new Vector();
				List<LDAPUser> filteredUserList = new ArrayList<LDAPUser>();
				
				try {
					dcHostName = appWindow.getDcHostName().getText();
					dcPort = Integer.parseInt(appWindow.getDcPort().getText());
					dcBaseDn = appWindow.getBaseDn().getText();
					dcAccount = appWindow.getDcAccount().getText();
					dcAccountPassword = appWindow.getDcAccountPassword().getText();
					ppcHostName = appWindow.getPpcHostName().getText();
					ppcAccountPassword = appWindow.getPpcAccountPassword().getText();
					mustUpdateDepartment = appWindow.getQueryUpdateDepartment().isSelected();
					mustUpdateOffice = appWindow.getQueryUpdateOffice().isSelected();
					mustUpdateEmail = appWindow.getQueryUpdateEmail().isSelected();
				} catch (NumberFormatException e) {
					appWindow.getTextPaneOutput().setText("Enter correct connection settings prior to requesting an update.");	
					return;
				}
				
				try {
					ldapCrawler = new LDAPCrawler(dcHostName, dcPort, dcAccount, dcAccountPassword, dcBaseDn);
					ldapUserList = ldapCrawler.getLdapUsers();
					Collections.sort(ldapUserList);
					/*
					outputMessage = "Connection to LDAP established. Retrieved " + ldapUserList.size() +
															" users from LDAP.";
					appWindow.getTextPaneOutput().setText(outputMessage);*/
				} catch (Exception e) {
					appWindow.getTextPaneOutput().setText("Unable to establish LDAP connection. Enter correct connection settings.");
					return;
				}
				
				if(mustUpdateDepartment || mustUpdateOffice || mustUpdateEmail) {
					ServerCommandProxy serverProxy = new ServerCommandProxy(ppcHostName, 9191, ppcAccountPassword);
					ppcUserVector = serverProxy.listUserAccounts(0, 1000);
					
					/*
					 * The following loops through all retrieved ldapUsers,
					 * check if they exist in PaperCut, and if true,
					 * adds them to a new list of filteredUsers.
					 * Each successful match deletes the user from
					 * the ppcUserVector to reduce the time taken for processing.
					 */
					for (LDAPUser ldapUser : ldapUserList) {
						if (ppcUserVector.size() > 0) {
							for (int i = 0; i < ppcUserVector.size(); i++) {
								if (ldapUser.getUserName().equals(ppcUserVector.elementAt(i))) {
									filteredUserList.add(ldapUser);
									ppcUserVector.remove(i);
								}
							}
						}
					}		
					
					/*
					 * The following loops through all the filtered users,
					 * and updates the requested properties.
					 */
					for (LDAPUser user : filteredUserList) {
						if (mustUpdateDepartment) {
							if (user.getDepartment() != null) {
								serverProxy.setUserProperty(user.getUserName(), "department", user.getDepartment());
							}
							else {
								serverProxy.setUserProperty(user.getUserName(), "department", "");
							}
						}
						if (mustUpdateOffice) {
							if (user.getOffice() != null) {
								serverProxy.setUserProperty(user.getUserName(), "office", user.getOffice());
							} 
							else {
								serverProxy.setUserProperty(user.getUserName(), "office", "");
							}
						}
						if (mustUpdateEmail) {
							if (user.getEmail() != null) {
								serverProxy.setUserProperty(user.getUserName(), "email", user.getEmail());
							} 
							else {
								serverProxy.setUserProperty(user.getUserName(), "email", "");
							}
						}
					}
					outputMessage = "Update complete.";
					appWindow.getTextPaneOutput().setText(outputMessage);
				}
				else {
					// No checkbox was selected
					appWindow.getTextPaneOutput().setText("Nothing selected for update.");
				}
			}	// end actionPerformed
		});	// end ActionListener
	}	// end main()
}	//end MainApp
