package clockinsystem;

import static org.junit.Assert.*;

import org.junit.Test;

public class TimeCardLoginTest {

    @Test
    public void testGetUser() {
        TimeCardLogin timeInOut = new TimeCardLogin("2345");
        assertEquals("Dave", timeInOut.getUser());
    }

    @Test
    public void testGetUser2() {
        TimeCardLogin timeInOut = new TimeCardLogin("6802");
        assertEquals("Tim", timeInOut.getUser());
    }
}
