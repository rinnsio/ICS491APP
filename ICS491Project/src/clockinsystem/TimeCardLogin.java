package clockinsystem;
//TimeCardLogin file made for ICS491Applet for the ICS491 project
//Author:  Kris Nakamura 
//Created: 7/30/15
//
//Functions used to authenticate and access timecard functions
//

import java.sql.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

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
        //if(passcode.length() == 4) {  // THIS IS NOT NEEDED BECAUSE PASSCODE IS LIMITED TO 4 DIGITS
        if(validLogin(passcode)) {
            //User exists and timecard can be accessed
            validUser = true;
            setInOrOut();
        }
        else {
            //No such user or incorrect passcode
            JOptionPane.showMessageDialog(null, "Incorrect passcode.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        //}
    }
  
    // Returns true if the passcode exists/is associated, false if not
    private boolean validLogin(String passcode) {
        connection();

        String database = "jdbc:mysql://localhost:3306/minions";
        String databaseUsername = "root";
        String databasePassword = "";

        try {
            // Attempts to get a connection to the database. (database location,
            // username, password).
            connection = DriverManager.getConnection(database,
                    databaseUsername, databasePassword);

            // Takes a query and executes it
            final String userCredentials = "SELECT user_id FROM pin WHERE pin = ? ";

            // Prepares a statement using the passcode given by the user
            PreparedStatement prepStatement = connection
                    .prepareStatement(userCredentials);
            prepStatement.setString(1, passcode);

            // Executes a query
            results = prepStatement.executeQuery();

            // If a user with the passcode exists return true, otherwise false
            if (results.isBeforeFirst()) {
                results.first();
                user = results.getString(1);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
  
    private void setInOrOut() {
        try {
            // Takes a query and executes it
            final String clockStatus = "SELECT in_out FROM clockinout WHERE user_id = ? ";

            // Prepares a statement using the userID of the user
            PreparedStatement prepStatement = connection
                    .prepareStatement(clockStatus);
            prepStatement.setString(1, user);

            // Executes a query
            results = prepStatement.executeQuery();

            // If a user with the userID exists set clock status, otherwise do
            // nothing
            if (results.isBeforeFirst()) {
                results.first();
                inOrOut = results.getBoolean(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getWage() {
        try {
            // Takes a query and executes it
            final String clockStatus = "SELECT wage FROM currentwages WHERE user_id = ? ";

            // Prepares a statement using the userID of the user
            PreparedStatement prepStatement = connection
                    .prepareStatement(clockStatus);
            prepStatement.setString(1, user);

            // Executes a query
            results = prepStatement.executeQuery();

            // If a user with the userID exists set clock status, otherwise do
            // nothing
            if (results.isBeforeFirst()) {
                results.first();
                return results.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private java.sql.Timestamp getTimeIn() {
        try {
            // Takes a query and executes it
            final String clockStatus = "SELECT MAX(time_in) time_in FROM log WHERE user_id = ? ";

            // Prepares a statement using the userID of the user
            PreparedStatement prepStatement = connection
                    .prepareStatement(clockStatus);
            prepStatement.setString(1, user);

            // Executes a query
            results = prepStatement.executeQuery();

            // If a user with the userID exists set clock status, otherwise do
            // nothing
            if (results.isBeforeFirst()) {
                results.first();
                // add date and time together and return the number format of
                // it.
                java.sql.Timestamp datee = new java.sql.Timestamp(results
                        .getDate(1).getTime());

                // make time string
                String pattern = "HH:mm:ss";
                SimpleDateFormat format = new SimpleDateFormat(pattern);
                String time = format.format(results.getTime(1));
                // make date string
                String pattern2 = "yyyy-MM-dd";
                SimpleDateFormat format2 = new SimpleDateFormat(pattern2);
                String date = format2.format(results.getDate(1));
                // add them together to be a datetime
                String datetime = date + " " + time;
                System.out.println(results.getDate(1) + " "
                        + results.getTime(1));
                SimpleDateFormat formatter = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");
                // format it to a timestamp object
                Date dateformat = formatter.parse(datetime);
                java.sql.Timestamp finaldatatime = new java.sql.Timestamp(
                        dateformat.getTime());

                return finaldatatime;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // clock in for user
    public void clockin() {

        // update the clockinout table
        try {

            // Takes a query and executes it
            final String clockStatus = "UPDATE clockinout SET in_out = ? WHERE user_id = ? ";

            // Prepares a statement using the userID of the user
            PreparedStatement prepStatement = connection
                    .prepareStatement(clockStatus);

            prepStatement.setInt(1, 1);
            prepStatement.setString(2, user);

            // Executes a update
            prepStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // insert the record log to the logs table
        PreparedStatement ps = null;
        try {
            String insert = "INSERT INTO `log` (user_id , date, wage, time_in) ";
            insert += "VALUE ( ?,?,?,?)";
            ps = connection.prepareStatement(insert);
            ps.setString(1, user);

            // today's date
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            Date today = new Date();
            String date = format.format(today);
            java.sql.Date date2 = new java.sql.Date(0000 - 00 - 00);
            ps.setDate(2, date2.valueOf(date));
            ps.setInt(3, this.getWage());

            // today's time
            java.sql.Timestamp timed = getCurrentTimeStamp();
            ps.setTimestamp(4, timed);

            ps.executeUpdate();
            ps.close();
        } catch (NullPointerException e) {
        } catch (Exception e) {
            System.out.println("error occur when insert new row of log :" + e);
        }
    }

    // covert the util date to mysql date format for insert to the database
    private static java.sql.Timestamp getCurrentTimeStamp() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());
    }

    // clock out for user
    public void clockout() {
        try {

            // Takes a query and executes it
            final String clockStatus = "UPDATE clockinout SET in_out = ? WHERE user_id = ? ";

            // Prepares a statement using the userID of the user
            PreparedStatement prepStatement = connection
                    .prepareStatement(clockStatus);

            prepStatement.setInt(1, 0);
            prepStatement.setString(2, user);

            // Executes a update
            prepStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // update the record log to the logs table
        PreparedStatement ps = null;
        try {
            String update = "UPDATE `log` SET time_out = ?, total_hours = ?, total_wages= ? WHERE user_id = ? ";

            ps = connection.prepareStatement(update);

            // today's time
            java.sql.Timestamp timeout = getCurrentTimeStamp();
            ps.setTimestamp(1, timeout);

            // worked hours
            java.sql.Timestamp timein = getTimeIn();

            // calculate the different between clockin and out
            long milliseconds = timeout.getTime() - timein.getTime();
            Double hours = (new Long((milliseconds / (1000 * 60 * 60)) % 24))
                    .doubleValue();
            ps.setDouble(2, hours);

            // total_wages
            int wage = this.getWage();
            Double total = hours * wage;
            ps.setDouble(3, total);

            // user_id
            ps.setString(4, user);

            ps.executeUpdate();
            ps.close();
        } catch (NullPointerException e) {
        } catch (Exception e) {
            System.out.println("error occur when update the log :" + e);
        }
    }

    // Returns the user that the passcode is associated with
    public String getUser() {
        return user;
    }

    // Returns whether or not the user was a valid user or not
    public boolean getValidUser() {
        return validUser;
    }

    // Returns whether the user is attempting to clock in or out
    public boolean getInOrOut() {
        return inOrOut;
    }

    /**
     * Get the driver in order to get connection to the database.
     */
    public static void connection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
