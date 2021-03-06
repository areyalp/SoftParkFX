package app.softparkmulti.model;

import java.sql.ResultSet;

import app.softparkmulti.util.PasswordEncryptor;

public class Login {

	public static User loggedUser;
	public static Station fromStation;
	
	public static boolean authenticate(String username, String plainPassword){
		boolean isPasswordOk = false;
		boolean authenticated = false;
		ResultSet rows;
		try{
			Db db = new Db();
			rows = db.select("SELECT Id, Password FROM Users WHERE Login='"+ username +"';");
			if(rows.next()){
				String encryptedPassword = rows.getString("Password");
				isPasswordOk = PasswordEncryptor.checkPassword(plainPassword, encryptedPassword);
				if(isPasswordOk){
					authenticated = true;
				}
			
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return authenticated;
	}
	
    public static void setUserInfo(String username){
    	Db db = new Db();
	
		 loggedUser = db.loadUserInfo(
				 Db.getUserId(username)
				 );
    }
	
    
    
}
