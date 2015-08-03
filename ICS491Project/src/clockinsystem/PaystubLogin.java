package clockinsystem;

//PaystubLogin file made for ICS491Applet for the ICS491 project
//Author:  Kris Nakamura 
//Created: 8/1/15
//
//Functions used to authenticate and access any paystub info.
//

import java.sql.*;

public class PaystubLogin {
    private boolean validUser = false;
    private boolean privilegeLevel = false;
    private ResultSet results;
    private ResultSet databaseInfo;
    private String user;
    private Connection connection = null;
    private PaystubRetriever RFunctions;
    
    //Constructor which establishes connection with a database to validate credentials
    //Takes a username and password string and checks to see if it is a valid login
    public PaystubLogin(String username, String password) {
        if(validLogin(username, password)) {
            //User exists and paystub info can be accessed
            validUser = true;
            RFunctions = new PaystubRetriever(connection, username);
        }
        else {
            //No such user or incorrect password
        }
    }
    
    private boolean validLogin(String username, String password) {
        connection();
        
        String database = "jdbc:mysql://localhost:3306/minions";
        String databaseUsername = "root";
        String databasePassword = "";
        
        try {
            //Attempts to get a connection to the database.  (database location, username, password).
            connection = DriverManager.getConnection(database, databaseUsername, databasePassword);
            
            //Takes a query and executes it
            final String userCredentials = "SELECT privilege_level FROM login WHERE (user_id = ? AND password = ?) ";
            
            //Prepares a statement using the password given by the user
            PreparedStatement prepStatement = connection.prepareStatement(userCredentials);
            prepStatement.setString(1, username);
            prepStatement.setString(2, password);
            
            //Executes a query 
            results = prepStatement.executeQuery();
            
            //If a user with the password exists return true, otherwise false
            if(results.isBeforeFirst()) {
                user = username;
                results.first();
                privilegeLevel = results.getBoolean(1);
                return true;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    //Returns the user that is logged in
    public String getUser() {
        return user;
    }
    
    //Returns whether or not the user was a valid user or not
    public boolean getValidUser() {
        return validUser;
    }
    
    //Returns the privilege level of the user
    public boolean getPrivilegeLevel(){
        return privilegeLevel;
    }
    
    
    /** 
     * Get the driver in order to get connection to the database.
     */
    public static void connection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    //Retrieves info from the database in the form of a ResultSet
    //SHOULD ONLY BE CALLED IF VALID USER
    //The retrieved results will either be empty, or contain:
    //date, wage, time in, time out, total hours, total wage
    public ResultSet getDatabaseInfo() {
        databaseInfo = RFunctions.getData();
        return databaseInfo;
    }
}
