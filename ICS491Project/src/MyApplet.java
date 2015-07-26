import java.awt.*;

import javax.swing.JApplet;


public class MyApplet extends JApplet{
	public void paint(Graphics g){
		super.paint(g);
		g.drawString("Hello World", 25, 25);
	}
}
