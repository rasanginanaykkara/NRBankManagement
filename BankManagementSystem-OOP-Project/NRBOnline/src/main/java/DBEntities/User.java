package DBEntities;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ConnectionVariables.SQLConnector;

public class User {
	private String nic;
	private String email;
	private String name;
	private String address;
	private String pictureURL;
	private String passwordHash;
	private int isActivated;
	public final static int ACTIVATED = 1;
	public final static int  BANNED = 99;
	public final static int DEACTIVATED = 2;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(String nic,String email, String name ,String address, String pictureURL, String password,int isActivated) {
		this.nic = nic;
		this.email = email;
		this.name = name;
		this.address = address;
		this.pictureURL = pictureURL;
		this.passwordHash = doHashPassword(password);
		this.isActivated = isActivated;
	}
		
	public String getNic() {
		return nic;
	}
	public String getAddress() {
		return address;
	}
	public String getName() {
		return name;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public String getEmail() {
		return email;
	}
	public int getIsActivated() {
		return isActivated;
	}
	public String getPictureURL() {
		return pictureURL;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setIsActivated(int isActivated) {
		this.isActivated = isActivated;
	}
	public void setNic(String nic) {
		this.nic = nic;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}
	public static String doHashPassword(String password) {
		//String val = md5("");
		
		try {
            MessageDigest md = MessageDigest.getInstance("MD5");
 
            byte[] messageDigest = md.digest(password.getBytes());
 
            BigInteger no = new BigInteger(1, messageDigest);
 
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
 
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
		
	}
	public void addInstanceToDB() {
		Connection conn = SQLConnector.getDBConnection();
		String query = "INSERT INTO \"user\" VALUES ("
				+"'"+nic+"',"
				+"'"+email+"',"
				+"'"+name+"',"
				+"'"+address+"',"
				+"'"+pictureURL+"',"
				+"'"+passwordHash+"',"
				+isActivated+");";
		try {
			System.out.println(query);
			Statement stmnt = conn.createStatement();
			boolean  val = stmnt.execute(query);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean validateUser(String nic,String password) {
		Connection conn = SQLConnector.getDBConnection();
		String query = "SELECT * from \"user\" where nic='"+nic+"';";
		try {
			Statement stmnt = conn.createStatement();
			ResultSet rs = stmnt.executeQuery(query);
			while(rs.next()) {
				if(User.doHashPassword(password).equals(rs.getString("password"))) {
					this.nic = rs.getString("nic");
					this.address = rs.getString("address");
					this.name = rs.getString("ful_name");
					this.email = rs.getString("email");
					this.pictureURL = rs.getString("image_url");
					this.passwordHash = rs.getString("password");
					this.isActivated = rs.getInt("is_activated");
					return true;
				}else					
					return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
			
		}	
		return false;
	}
	public static void deleteUser(String nic) {
		
		String query = "DELETE FROM \"user\" WHERE nic = '"+nic+"'";
		Connection conn = SQLConnector.getDBConnection();
		User user = new User();
		user.loadUser(nic);
		ArrayList<FixedDeposit> fixedDeposits = user.getOwnFixedDeposits();
		ArrayList<SavingsAccount> savingsAccounts = user.getOwnSavingsAccounts();
		for(int i=0;i<fixedDeposits.size();i++) {
			fixedDeposits.get(i).deleteAccount();
		}
		for(int i=0;i<savingsAccounts.size();i++) {
			savingsAccounts.get(i).deleteAccount();
		}
		
		try {
			Statement stmnt = conn.createStatement();
			if(stmnt.execute(query))
				System.out.println("deletion successful");
			else
				System.out.println("deletion failed");
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
	}
	public ArrayList<FixedDeposit> getOwnFixedDeposits(){
		ArrayList<FixedDeposit> fds = new ArrayList<>();
		Connection conn = SQLConnector.getDBConnection();
		String query = "SELECT * FROM fixed_deposit f "
				+ "JOIN account a ON a.account_number = f.account_number "
				+ "JOIN \"user\" u ON u.nic = a.user_id WHERE a.user_id = '"+this.nic+"'";
		try {
			Statement stmnt = conn.createStatement();
			ResultSet rs = stmnt.executeQuery(query);
			while(rs.next()) {
				FixedDeposit fd = new FixedDeposit();
				fd.loadInstance(rs.getInt("account_number"));
				fds.add(fd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fds;
	}
	public ArrayList<SavingsAccount> getOwnSavingsAccounts(){
		ArrayList<SavingsAccount> fds = new ArrayList<>();
		Connection conn = SQLConnector.getDBConnection();
		String query = "SELECT * FROM savings_account s "
				+ "JOIN account a ON a.account_number = s.account_number "
				+ "JOIN \"user\" u ON u.nic = a.user_id WHERE a.user_id = '"+this.nic+"'";
		try {
			Statement stmnt = conn.createStatement();
			ResultSet rs = stmnt.executeQuery(query);
			while(rs.next()) {
				SavingsAccount sa = new SavingsAccount();
				sa.loadInstance(rs.getInt("account_number"));
				fds.add(sa);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fds;
	}
	public boolean loadUser(String nic) {
		Connection conn = SQLConnector.getDBConnection();
		String query = "SELECT * from \"user\" where nic='"+nic+"';";
		try {
			Statement stmnt = conn.createStatement();
			ResultSet rs = stmnt.executeQuery(query);
			while(rs.next()) {
					this.passwordHash = rs.getString("password");
					this.nic = rs.getString("nic");
					this.address = rs.getString("address");
					this.name = rs.getString("ful_name");
					this.email = rs.getString("email");
					this.pictureURL = rs.getString("image_url");
					this.passwordHash = rs.getString("password");
					this.isActivated = rs.getInt("is_activated");
					return true;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
			
		}	
		return false;
	}
	public static ArrayList<User> getAllusers(){
		ArrayList<User> list = new ArrayList<>();
		String query = "SELECT nic FROM \"user\"";
		Connection conn = SQLConnector.getDBConnection();
		try {
			Statement stmnt = conn.createStatement();
			ResultSet rs = stmnt.executeQuery(query);
			while(rs.next()) {
				String nic = rs.getString("nic");
				User user = new User();
				user.loadUser(nic);
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
}
