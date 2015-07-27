import java.awt.*;
import java.awt.event.*;

public class MyLogin extends Frame implements ActionListener {
	boolean id = false;
	Button ok, cancel;
	TextField username;
	TextField password;

	public MyLogin(){
		setLayout(new FlowLayout());
		setTitle("Login");
		username = new TextField(15);
		password = new TextField(15);
		add(new Label("User :"));
		add(username);
		add(new Label("Password :"));
		add(password);
		password.setEchoChar('*');
		setVisible(true);
		addOKCancelPanel();
		//createFrame();
		pack();
	}
	
	void addOKCancelPanel() {
		Panel p = new Panel();
		p.setLayout(new FlowLayout());
		createButtons(p);
		add(p);
	}

	void createButtons(Panel p) {
		p.add(ok = new Button("OK"));
		ok.addActionListener(this);
		p.add(cancel = new Button("Cancel"));
		cancel.addActionListener(this);
	}

/*	void createFrame() {
		Dimension d = getToolkit().getScreenSize();
		setLocation(d.width / 4, d.height / 3);
	}
*/
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == ok) {
			id = true;
			setVisible(false);
		}
		else if (ae.getSource() == cancel) {
			id = false;
			setVisible(false);
		}
	}
}