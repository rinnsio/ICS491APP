
import java.awt.*;
import java.net.URL;
import java.sql.*;
import javax.swing.JApplet;

public class MyApplet extends JApplet {
	
    public String m_username = "";
    public String m_password = "";
    
    // I made this up. Database is not created yet!
	private static final String ADMIN_USERNAME = "root";
	private static final String ADMIN_PASSWORD = "root";
	private static final String CONNECT_DATABASE = "jdbc:mysql://localhost:8000/minions";

    public void init() {
        if (!login()) {
            /*try {
                System.out.println(new URL(getCodeBase() + "accessdenied.html"));
                getAppletContext().showDocument(new URL(getCodeBase() + "login.html"), "_top");
            }
            catch (Exception e) {
                e.printStackTrace();
            }*/
        }
        else {
            // here the username and password are OK
        	Connection conn = null;
        	
        	try{
        		// Connect to the database
        		conn = DriverManager.getConnection(CONNECT_DATABASE, ADMIN_USERNAME, ADMIN_PASSWORD);
        		System.out.println("Connected Successfully.");
        		
        		// Retrieves data from database
        		Statement stat = (Statement) conn.createStatement();
        		String getinfo = "SELECT * FROM user WHERE name = " + m_username;
        		stat.executeQuery(getinfo);
        	}
        	catch (SQLException e){
        		System.err.println(e);
        	}
        }
    }

    public boolean login() {
        boolean userValid = false;
        MyLogin login = new MyLogin();
        requestFocus();
        if (login.id) {
        	m_username = login.username.getText();
        	m_password = login.password.getText();
            userValid = validateUser(m_username, m_password);
            System.out.println("The password for " + m_username
                    + " is " + (userValid ? "valid" : "invalid"));
        }
        else {
            System.out.println("Cancel was pressed.");
        }

        //login.dispose();
        return userValid;

    }

    private boolean validateUser(String usr, String pwd) {
    	// here you will code some logic to validate the username
        // password... for testing purpose :
        //                 username = java  password = avaj
        return (usr.equals("java") && pwd.equals("avaj"));
    }
    
/*    public void paint(Graphics g) {
    	super.paint(g);
    	g.drawString("Hello World", 25, 25);
	}*/
}
