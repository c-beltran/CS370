import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * This class contains the 
 * layout and logic for when
 * a user logs in
 * @author Carlos
 *
 */
public class UserRegistration extends JFrame implements ActionListener{
	
	Utility Util = new Utility();
	UserAuth savedUser = UserAuth.getProdDB();
	viewGUI StartUI = new viewGUI();
		
	JButton btnLogin; 
	JButton btnExit;
	JButton btnGuest;
	JTextField user;
	JPasswordField password;
	JLabel jLabUser;
	JLabel jLabPass;
	
	JButton btnUpdate;
	JTextField userName;
	JTextField newPassword;
	JLabel jLabUserName;
	JLabel jLabNewPass;
	
	//to check if the user is
	//an Admin or Guest
	static boolean isAdmin = true;
	
	//user AUTH TOKEN
	public static void userType(boolean isUserAdmin)
	{
		isAdmin = isUserAdmin;
	}
	
	public static boolean getUser()
	{
		return isAdmin;
	}
	
	public void initRegistration(){
		
		btnLogin = new JButton("Log in");
		btnExit = new JButton("Exit Application");
		btnGuest = new JButton("Guest User");
		
		btnLogin.setBackground(Color.GREEN);
		btnLogin.setOpaque(true);
		btnLogin.setBorderPainted(false);
		btnExit.setBackground(Color.RED);
		btnExit.setOpaque(true);
		btnExit.setBorderPainted(false);
		
		user = new JTextField();
		password = new JPasswordField();
		
		jLabUser = new JLabel("Username");
		jLabPass = new JLabel("Password");
		
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		btnLogin.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				String userName = user.getText();
				@SuppressWarnings("deprecation")
				String userPswd = password.getText();
				
				String getUserCred = savedUser.getUserKey();
				String getUserPswd = savedUser.getUserName(getUserCred);
				
				try {
					getUserPswd = EncryptionFeature.decrypt(getUserPswd);
				} 
				catch (Exception e1) {
				}
				
				if(getUserCred.equals(userName) && getUserPswd.equals(userPswd)){
					String fileName = "queryLogFile.txt";
					isAdmin = true;
					userType(isAdmin);
					Util.initCMD(fileName);
					StartUI.start();
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Incorrect Username OR Password, please try again.");
				}
			}	
		});//end of listener
		
		
		btnExit.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("Closing Application...");
				System.exit(1);
			}
		});//end of listener
		
		btnGuest.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				isAdmin = false;
				userType(isAdmin);
				StartUI.start();
				dispose();
			}
		});//end of listener
		
		jLabUser.setBounds(10,10,120,20);
		jLabPass.setBounds(10,30,120,20);
		user.setBounds(140, 10, 200, 20);
		password.setBounds(140,30,200,20);
		
		btnLogin.setBounds(140, 55, 100, 20);
		btnGuest.setBounds(240, 55, 100, 20);
		btnExit.setBounds(340, 55, 150, 20);
		
		this.add(jLabUser);
		this.add(jLabPass);
		this.add(user);
		this.add(password);
		this.add(btnLogin);
		this.add(btnExit);
		this.add(btnGuest);
		
		this.setSize(500, 300);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		
	}
	

}
