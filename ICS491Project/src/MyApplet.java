
import java.awt.*;
import java.net.URL;

import javax.swing.JApplet;

public class MyApplet extends JApplet {

//    public void paint(Graphics g) {
//        super.paint(g);
//        g.drawString("Hello World", 25, 25);
//    }
//	// Test

    public String username = "";
    public String password = "";

    public void init() {
        if (!login()) {
            try {
                System.out.println(new URL(getCodeBase() + "accessdenied.html"));
                getAppletContext().showDocument(new URL(getCodeBase() + "login.html"), "_top");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // here the username and password are OK
        }
    }

    public boolean login() {
        boolean userValid = false;
        MyLogin login = new MyLogin(new Frame(""));
        requestFocus();
        if (login.id) {
            username = login.username.getText();
            password = login.password.getText();
            userValid = validateUser(username, password);
            System.out.println("The password for " + username
                    + " is " + (userValid ? "valid" : "invalid"));
        } else {
            System.out.println("Cancel was pressed.");
        }

        login.dispose();
        return userValid;

    }

    private boolean validateUser(String usr, String pwd) {
   // here you will code some logic to validate the username
        // password... for testing purpose :
        //                 username = java  password = avaj
        return (usr.equals("java") && pwd.equals("avaj"));
    }
}
