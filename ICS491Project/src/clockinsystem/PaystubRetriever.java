package clockinsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//PaystubRetriever file made for ICS491Applet for the ICS491 project
//Author:  Kris Nakamura 
//Created: 8/1/15
//
//Functions used to authenticate and access any paystub info.
//

public class PaystubRetriever {
    private ResultSet results;
    
    public PaystubRetriever(Connection connection, String username) {
        //Takes a query and executes it
        //Query retrieves all user's paystub info and stores it in a ResultSet
        final String userCredentials =
                "SELECT date, wage, time_in, time_out, total_hours, total_wages "
                        + "FROM log WHERE user_id = ? ";

        try {
            //Prepares a statement using the password given by the user
            PreparedStatement prepStatement = connection.prepareStatement(userCredentials);
            prepStatement.setString(1, username);

            //Executes a query 
            results = prepStatement.executeQuery();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public ResultSet getData() {
        return results;
    }
}
