package view.button;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import view.Main;

public class Player2Button extends JButton {
	
	private static final long serialVersionUID = 1L;
	
	private Main main;

	public Player2Button(Main main) {
		super("Play Player2");
		this.main = main;
		setAlignmentY(0.5f);
		setBounds(35, 100, 130, 30);
		setVisible(true);
		
		 addMouseListener(new MouseAdapter() {	
		 
				public void mouseClicked(MouseEvent e) {
					Player2Button.this.main.playPlayer2();
		    	}
			});
	}
}
