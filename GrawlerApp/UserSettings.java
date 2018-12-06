import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * This class is in charge of
 * updating user settings such as
 * username and password
 * @author Carlos
 *
 */
public class UserSettings extends JFrame implements ActionListener{
	
	Utility Util = new Utility();
	UserAuth savedUser = UserAuth.getProdDB();
	viewGUI StartUI = new viewGUI();
	JButton btnUpdate;
	JTextField userName;
	JTextField newPassword;
	JLabel jLabUserName;
	JLabel jLabNewPass;
	
	public void updateUserInfo(){
		
		btnUpdate = new JButton("Update");
		
		btnUpdate.setBackground(Color.GREEN);
		btnUpdate.setOpaque(true);
		btnUpdate.setBorderPainted(false);
		
		userName = new JTextField();
		newPassword = new JPasswordField();
		
		jLabUserName = new JLabel("Username");
		jLabNewPass = new JLabel("New Password");
		
		String getUserCred = savedUser.getUserKey();
		String getUserPswd = savedUser.getUserName(getUserCred);
		userName.setText(getUserCred);
		newPassword.setText(getUserPswd);
		
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		btnUpdate.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
								
				String user = userName.getText();
				@SuppressWarnings("deprecation")
				String userPswd = newPassword.getText();
				
				try {
					userPswd = EncryptionFeature.decrypt(userPswd);
				} catch (Exception e2) {
				}

				savedUser.updateUserName(user);
				savedUser.updateUserPassword(user, userPswd);
				
				JOptionPane.showMessageDialog(null, "User Setting were Updated Successfully!");
				
				String saveToLog = " User Name: " + user + " Password: " + userPswd + "\n";
				
				try {
					Util.updateLog(saveToLog);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}	
		});//end of listener
		
		jLabUserName.setBounds(10,10,120,20);
		jLabNewPass.setBounds(10,30,120,20);
		userName.setBounds(140, 10, 200, 20);
		newPassword.setBounds(140,30,200,20);
		
		btnUpdate.setBounds(140, 55, 100, 20);
		
		this.add(jLabUserName);
		this.add(jLabNewPass);
		this.add(userName);
		this.add(newPassword);
		this.add(btnUpdate);
		
		this.setSize(500, 300);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
