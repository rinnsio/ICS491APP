package clockinsystem;

/**
 * JUnit test for PaystubLogin for ICS491 Project.
 * Author: Rinn Sio
 * Created: 8/2/15
 * Last Edited: 8/2/15
 */

import static org.junit.Assert.*;
import org.junit.Test;

public class PaystubLoginTest {

    @Test
    public void testGetUser() {
        PaystubLogin login = new PaystubLogin("Bob", "bobob");
        assertEquals("Bob", login.getUser());
    }
    
    @Test
    public void testGetUser2() {
        PaystubLogin login = new PaystubLogin("Kevin", "kev");
        assertNull(login.getUser());
    }
    
    @Test
    public void testGetUser3() {
        PaystubLogin login = new PaystubLogin("stuart", "stutu");
        assertEquals("stuart", login.getUser());
    }
    
    @Test
    public void testGetValidUser() {
        PaystubLogin login = new PaystubLogin("Dave", "davav");
        assertTrue(login.getValidUser());
    }

    @Test
    public void testGetValidUser2() {
        PaystubLogin login = new PaystubLogin("Jer", "jerer");
        assertFalse(login.getValidUser());
    }

    @Test
    public void testGetPrivilegeLevel() {
        PaystubLogin login = new PaystubLogin("Tim", "timim");
        assertFalse(login.getPrivilegeLevel());
    }
    
    @Test
    public void testGetPrivilegeLevel2() {
        PaystubLogin login = new PaystubLogin("Gru", "gruru");
        assertTrue(login.getPrivilegeLevel());
    }
}
