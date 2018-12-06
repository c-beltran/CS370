import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

/**
 * This class is the storage of the
 * username and password
 * @author Carlos
 *
 */
public class UserAuth {
	
	private static UserAuth user_Auth = null;
	
	public static UserAuth getProdDB()
	{
		if(user_Auth == null)
		{
			user_Auth = new UserAuth();
		}
		return user_Auth;
	}

	HashMap<String, String> userInfo = new HashMap<String, String>();
	
	public void newUser(String name, String pswd)
	{
		userInfo.put(name, pswd);		
	}
	
	public void updateUserPassword(String newName, String newPswd)
	{
		userInfo.put(newName, newPswd);
	}
	
	public String getUserName(String input)
	{
		return userInfo.get(input);
	}
	
	public void updateUserName(String input)
	{
		String oldKey = getUserKey();
		String obj = userInfo.remove(oldKey);
		userInfo.put(input, obj);
	}
	
	public String getUserKey()
	{
		String userId = null;
		
		Set<java.util.Map.Entry<String, String>> hashSet = userInfo.entrySet();
        for(java.util.Map.Entry<String, String> entry:hashSet )
        {       	
        	userId = entry.getKey();
        }
        return userId;
	}
}
