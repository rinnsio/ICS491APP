package clockinsystem;
//TimeCardLogin file made for ICS491Applet for the ICS491 project
//Author:  Kris Nakamura 
//Created: 7/30/15
//
//Functions used to authenticate and access timecard functions
//

import java.sql.*;

public class TimeCardLogin {
    private TimeCardFunctions functions;
    private boolean validLogin = false;
    
    //Constructor which establishes connection with a database to validate credentials
    public TimeCardLogin(String passcode) {
        if(passcode.length() == 4) {
            try {
                //Attempts to get a connection to the database
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test");
                
                //Uses the statement to execute query
                Statement statement = connection.createStatement();
                
                //Takes a query and executes it
                //Make sure that the query gets sent not as a command by using preparedstatement
                ResultSet results = statement.executeQuery("");
                
                functions = new TimeCardFunctions(true);
                validLogin = true;
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public boolean isValidLogin() {
        return validLogin;
    }
}
