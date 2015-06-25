package papercutUtility.gui;

// boilerplate code imports
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import javax.swing.UIManager;

/**
 * The ApplicationWindow class is designed using the WindowBuilder Eclipse plugin.
 * It represents the applications main window, along with it's getter methods.
 * @author Vo1d
 *
 */
public class ApplicationWindow {

	private JFrame frmPapercutUtility;
	private JTextField textFieldDcHostName;
	private JTextField textFieldDcPort;
	private JTextField textFieldBaseDn;
	private JTextField textFieldDcAccount;
	private JPasswordField passwordFieldDcAccountPassword;
	private JTextField textFieldPpcHostName;
	private JPasswordField passwordFieldPpcAccountPassword;
	private JButton btnTestConnection;
	private JButton btnUpdate;
	private JTextPane textPaneOutput;
	private JCheckBox chckbxUpdateDepartment;
	private JCheckBox chckbxUpdateOffice;
	private JCheckBox chckbxUpdateEmail;
	
	/**
	 * Sets the visibility of the application window frame.
	 * @param visibility Set the window to visible or invisible.
	 */
	public void frameSetVisible(boolean visibility) {
		this.frmPapercutUtility.setVisible(visibility);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationWindow window = new ApplicationWindow();
					window.frmPapercutUtility.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ApplicationWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPapercutUtility = new JFrame();
		frmPapercutUtility.setBackground(Color.LIGHT_GRAY);
		frmPapercutUtility.setResizable(false);
		frmPapercutUtility.setTitle("PaperCut Utility");
		frmPapercutUtility.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 12));
		frmPapercutUtility.setBounds(100, 100, 300, 600);
		frmPapercutUtility.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPapercutUtility.getContentPane().setLayout(null);
		
		JPanel connectionPanel = new JPanel();
		connectionPanel.setBounds(10, 26, 275, 184);
		frmPapercutUtility.getContentPane().add(connectionPanel);
		GridBagLayout gbl_connectionPanel = new GridBagLayout();
		gbl_connectionPanel.columnWidths = new int[]{90, 150, 0};
		gbl_connectionPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_connectionPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_connectionPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		connectionPanel.setLayout(gbl_connectionPanel);
		
		JLabel lblDcHostName = new JLabel("DC host name:");
		GridBagConstraints gbc_lblDcHostName = new GridBagConstraints();
		gbc_lblDcHostName.anchor = GridBagConstraints.WEST;
		gbc_lblDcHostName.insets = new Insets(0, 0, 5, 5);
		gbc_lblDcHostName.gridx = 0;
		gbc_lblDcHostName.gridy = 0;
		connectionPanel.add(lblDcHostName, gbc_lblDcHostName);
		
		textFieldDcHostName = new JTextField();
		GridBagConstraints gbc_textFieldDcHostName = new GridBagConstraints();
		gbc_textFieldDcHostName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDcHostName.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDcHostName.gridx = 1;
		gbc_textFieldDcHostName.gridy = 0;
		connectionPanel.add(textFieldDcHostName, gbc_textFieldDcHostName);
		textFieldDcHostName.setColumns(10);
		
		JLabel lblDcPort = new JLabel("DC port:");
		GridBagConstraints gbc_lblDcPort = new GridBagConstraints();
		gbc_lblDcPort.anchor = GridBagConstraints.WEST;
		gbc_lblDcPort.insets = new Insets(0, 0, 5, 5);
		gbc_lblDcPort.gridx = 0;
		gbc_lblDcPort.gridy = 1;
		connectionPanel.add(lblDcPort, gbc_lblDcPort);
		
		textFieldDcPort = new JTextField();
		GridBagConstraints gbc_textFieldDcPort = new GridBagConstraints();
		gbc_textFieldDcPort.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDcPort.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDcPort.gridx = 1;
		gbc_textFieldDcPort.gridy = 1;
		connectionPanel.add(textFieldDcPort, gbc_textFieldDcPort);
		textFieldDcPort.setColumns(10);
		
		JLabel lblBaseDn = new JLabel("Base DN:");
		GridBagConstraints gbc_lblBaseDn = new GridBagConstraints();
		gbc_lblBaseDn.anchor = GridBagConstraints.WEST;
		gbc_lblBaseDn.insets = new Insets(0, 0, 5, 5);
		gbc_lblBaseDn.gridx = 0;
		gbc_lblBaseDn.gridy = 2;
		connectionPanel.add(lblBaseDn, gbc_lblBaseDn);
		
		textFieldBaseDn = new JTextField();
		GridBagConstraints gbc_textFieldBaseDn = new GridBagConstraints();
		gbc_textFieldBaseDn.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldBaseDn.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldBaseDn.gridx = 1;
		gbc_textFieldBaseDn.gridy = 2;
		connectionPanel.add(textFieldBaseDn, gbc_textFieldBaseDn);
		textFieldBaseDn.setColumns(10);
		
		JLabel lblDcAccount = new JLabel("DC account:");
		GridBagConstraints gbc_lblDcAccount = new GridBagConstraints();
		gbc_lblDcAccount.anchor = GridBagConstraints.WEST;
		gbc_lblDcAccount.insets = new Insets(0, 0, 5, 5);
		gbc_lblDcAccount.gridx = 0;
		gbc_lblDcAccount.gridy = 3;
		connectionPanel.add(lblDcAccount, gbc_lblDcAccount);
		
		textFieldDcAccount = new JTextField();
		GridBagConstraints gbc_textFieldDcAccount = new GridBagConstraints();
		gbc_textFieldDcAccount.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDcAccount.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDcAccount.gridx = 1;
		gbc_textFieldDcAccount.gridy = 3;
		connectionPanel.add(textFieldDcAccount, gbc_textFieldDcAccount);
		textFieldDcAccount.setColumns(10);
		
		JLabel lblDcAccountPassword = new JLabel("DC account password:");
		GridBagConstraints gbc_lblDcAccountPassword = new GridBagConstraints();
		gbc_lblDcAccountPassword.anchor = GridBagConstraints.WEST;
		gbc_lblDcAccountPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblDcAccountPassword.gridx = 0;
		gbc_lblDcAccountPassword.gridy = 4;
		connectionPanel.add(lblDcAccountPassword, gbc_lblDcAccountPassword);
		
		passwordFieldDcAccountPassword = new JPasswordField();
		GridBagConstraints gbc_passwordFieldDcAccountPassword = new GridBagConstraints();
		gbc_passwordFieldDcAccountPassword.insets = new Insets(0, 0, 5, 0);
		gbc_passwordFieldDcAccountPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordFieldDcAccountPassword.gridx = 1;
		gbc_passwordFieldDcAccountPassword.gridy = 4;
		connectionPanel.add(passwordFieldDcAccountPassword, gbc_passwordFieldDcAccountPassword);
		
		JLabel lblPpcHostName = new JLabel("PPC host name:");
		GridBagConstraints gbc_lblPpcHostName = new GridBagConstraints();
		gbc_lblPpcHostName.anchor = GridBagConstraints.WEST;
		gbc_lblPpcHostName.insets = new Insets(0, 0, 5, 5);
		gbc_lblPpcHostName.gridx = 0;
		gbc_lblPpcHostName.gridy = 5;
		connectionPanel.add(lblPpcHostName, gbc_lblPpcHostName);
		
		textFieldPpcHostName = new JTextField();
		GridBagConstraints gbc_textFieldPpcHostName = new GridBagConstraints();
		gbc_textFieldPpcHostName.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPpcHostName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPpcHostName.gridx = 1;
		gbc_textFieldPpcHostName.gridy = 5;
		connectionPanel.add(textFieldPpcHostName, gbc_textFieldPpcHostName);
		textFieldPpcHostName.setColumns(10);
		
		JLabel lblPpcAccountPassword = new JLabel("PPC account password:");
		GridBagConstraints gbc_lblPpcAccountPassword = new GridBagConstraints();
		gbc_lblPpcAccountPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPpcAccountPassword.insets = new Insets(0, 0, 0, 5);
		gbc_lblPpcAccountPassword.gridx = 0;
		gbc_lblPpcAccountPassword.gridy = 6;
		connectionPanel.add(lblPpcAccountPassword, gbc_lblPpcAccountPassword);
		
		passwordFieldPpcAccountPassword = new JPasswordField();
		GridBagConstraints gbc_passwordFieldPpcAccountPassword = new GridBagConstraints();
		gbc_passwordFieldPpcAccountPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordFieldPpcAccountPassword.gridx = 1;
		gbc_passwordFieldPpcAccountPassword.gridy = 6;
		connectionPanel.add(passwordFieldPpcAccountPassword, gbc_passwordFieldPpcAccountPassword);
		
		JPanel updatePanel = new JPanel();
		updatePanel.setBounds(10, 294, 275, 88);
		frmPapercutUtility.getContentPane().add(updatePanel);
		GridBagLayout gbl_updatePanel = new GridBagLayout();
		gbl_updatePanel.columnWidths = new int[]{160, 0, 0};
		gbl_updatePanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_updatePanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_updatePanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		updatePanel.setLayout(gbl_updatePanel);
		
		chckbxUpdateDepartment = new JCheckBox("Update Department");
		GridBagConstraints gbc_chckbxUpdateDepartment = new GridBagConstraints();
		gbc_chckbxUpdateDepartment.anchor = GridBagConstraints.WEST;
		gbc_chckbxUpdateDepartment.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxUpdateDepartment.gridx = 0;
		gbc_chckbxUpdateDepartment.gridy = 1;
		updatePanel.add(chckbxUpdateDepartment, gbc_chckbxUpdateDepartment);
		
		chckbxUpdateOffice = new JCheckBox("Update Office");
		GridBagConstraints gbc_chckbxUpdateOffice = new GridBagConstraints();
		gbc_chckbxUpdateOffice.anchor = GridBagConstraints.WEST;
		gbc_chckbxUpdateOffice.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxUpdateOffice.gridx = 0;
		gbc_chckbxUpdateOffice.gridy = 2;
		updatePanel.add(chckbxUpdateOffice, gbc_chckbxUpdateOffice);
		
		btnUpdate = new JButton("Update");
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.insets = new Insets(0, 0, 5, 0);
		gbc_btnUpdate.gridx = 1;
		gbc_btnUpdate.gridy = 2;
		updatePanel.add(btnUpdate, gbc_btnUpdate);
		
		chckbxUpdateEmail = new JCheckBox("Update Email");
		GridBagConstraints gbc_chckbxUpdateEmail = new GridBagConstraints();
		gbc_chckbxUpdateEmail.anchor = GridBagConstraints.WEST;
		gbc_chckbxUpdateEmail.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxUpdateEmail.gridx = 0;
		gbc_chckbxUpdateEmail.gridy = 3;
		updatePanel.add(chckbxUpdateEmail, gbc_chckbxUpdateEmail);
		
		JSeparator separatorTop = new JSeparator();
		separatorTop.setBounds(10, 221, 275, 2);
		frmPapercutUtility.getContentPane().add(separatorTop);
		
		JSeparator separatorMiddle = new JSeparator();
		separatorMiddle.setBounds(10, 281, 275, 2);
		frmPapercutUtility.getContentPane().add(separatorMiddle);
		
		JPanel testConnectionButtonPanel = new JPanel();
		testConnectionButtonPanel.setBounds(10, 234, 275, 36);
		frmPapercutUtility.getContentPane().add(testConnectionButtonPanel);
		
		btnTestConnection = new JButton("Test Connection");
		testConnectionButtonPanel.add(btnTestConnection);
		
		JSeparator separatorBottom = new JSeparator();
		separatorBottom.setBounds(10, 393, 275, 2);
		frmPapercutUtility.getContentPane().add(separatorBottom);
		
		textPaneOutput = new JTextPane();
		textPaneOutput.setEditable(false);
		textPaneOutput.setBackground(UIManager.getColor("Button.background"));
		textPaneOutput.setBounds(10, 406, 274, 161);
		frmPapercutUtility.getContentPane().add(textPaneOutput);
	}
	public JButton getBtnTestConnection() {
		return btnTestConnection;
	}
	public JButton getBtnUpdate() {
		return btnUpdate;
	}
	public JTextField getDcHostName() {
		return textFieldDcHostName;
	}
	public JTextField getDcPort() {
		return textFieldDcPort;
	}
	public JTextField getBaseDn() {
		return textFieldBaseDn;
	}
	public JTextField getDcAccount() {
		return textFieldDcAccount;
	}
	public JPasswordField getDcAccountPassword() {
		return passwordFieldDcAccountPassword;
	}
	public JTextField getPpcHostName() {
		return textFieldPpcHostName;
	}
	public JPasswordField getPpcAccountPassword() {
		return passwordFieldPpcAccountPassword;
	}
	public JTextPane getTextPaneOutput() {
		return textPaneOutput;
	}
	public JCheckBox getQueryUpdateDepartment() {
		return chckbxUpdateDepartment;
	}
	public JCheckBox getQueryUpdateOffice() {
		return chckbxUpdateOffice;
	}
	public JCheckBox getQueryUpdateEmail() {
		return chckbxUpdateEmail;
	}
}
