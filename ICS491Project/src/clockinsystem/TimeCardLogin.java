package clockinsystem;
//TimeCardLogin file made for ICS491Applet for the ICS491 project
//Author:  Kris Nakamura 
//Created: 7/30/15
//
//Functions used to authenticate and access timecard functions
//

import java.sql.*;

public class TimeCardLogin {
  private ResultSet results;
  private String user;
  private boolean validUser = false;
  private boolean inOrOut;
  private Connection connection;
  
  //Constructor which establishes connection with a database to validate credentials
  //Takes a 4 number long passcode string and checks to see if it is a valid code
  public TimeCardLogin(String passcode) {
      //Extra security feature to prevent any errors in length of input
      if(passcode.length() == 4) {
          if(validLogin(passcode)) {
              //User exists and timecard can be accessed
              validUser = true;
              setInOrOut();
          }
          else {
              //No such user or incorrect passcode
          }
      }
  }
  
  //Returns true if the passcode exists/is associated, false if not
  private boolean validLogin(String passcode) {
      connection();
      
      String database = "jdbc:mysql://localhost:3306/minions";
      String username = "root";
      String password = "";
      
      try {
        //Attempts to get a connection to the database.  (database location, username, password).
          connection = DriverManager.getConnection(database, username, password);
          System.out.println("Connected to Database.");
          
          //Takes a query and executes it
          final String userCredentials = "SELECT user_id FROM pin WHERE pin = ? ";
          
          //Prepares a statement using the passcode given by the user
          PreparedStatement prepStatement = connection.prepareStatement(userCredentials);
          prepStatement.setString(1, passcode);
          
          //Executes a query 
          results = prepStatement.executeQuery();
          
          //If a user with the passcode exists return true, otherwise false
          if(results.isBeforeFirst()) {
              results.first();
              user = results.getString(1);
              return true;
          }
      }
      catch(Exception e) {
          e.printStackTrace();
      }
      return false;
  }
  
  private void setInOrOut() {
      try {
          // Takes a query and executes it
          final String clockStatus = "SELECT in_out FROM clockinout WHERE user_id = ? ";

          // Prepares a statement using the userID of the user
          PreparedStatement prepStatement = connection.prepareStatement(clockStatus);
          prepStatement.setString(1, user);

          // Executes a query
          results = prepStatement.executeQuery();

          // If a user with the userID exists set clock status, otherwise do nothing
          if (results.isBeforeFirst()) {
              results.first();
              inOrOut = results.getBoolean(1);
          }
      }
      catch (Exception e) {
          e.printStackTrace();
      }
  }
  
  //Returns the user that the passcode is associated with
  public String getUser() {
      return user;
  }
  
  //Returns whether or not the user was a valid user or not
  public boolean getValidUser() {
      return validUser;
  }
  
  //Returns whether the user is attempting to clock in or out
  public boolean getInOrOut() {
      return inOrOut;
  }
  
  /** 
   * Get the driver in order to get connection to the database.
   */
  public static void connection() {
      try {
          Class.forName("com.mysql.jdbc.Driver");
          System.out.println("Driver worked.");
      } catch (ClassNotFoundException e) {
          e.printStackTrace();
      }
  }
}
