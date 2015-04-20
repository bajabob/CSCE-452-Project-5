import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class DisplayPanel extends JPanel{

	
	public DisplayPanel(){
		this.setBounds(0, 0, Config.DISPLAY_WIDTH, Config.DISPLAY_HEIGHT);
	}
	
	public void paintComponent( Graphics g )
	{
		super.paintComponent( g );
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Config.DISPLAY_WIDTH, Config.DISPLAY_HEIGHT);
	
	}
	
}
