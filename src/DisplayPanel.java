import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class DisplayPanel extends JPanel{

	/**
	 * Handles the drawing and coordinate management of the various blocks
	 */
	private BlockManager blockManager;
	
	public DisplayPanel(){
		this.setBounds(0, 0, Config.DISPLAY_WIDTH, Config.DISPLAY_HEIGHT);
		blockManager = new BlockManager();
	}
	
	public void onMousePressed(int x, int y){
		blockManager.onMousePressed( x, y );
		this.repaint();
	}
	
	public void onMouseDragged(int x, int y){
		blockManager.onMouseDragged( x, y );
		this.repaint();
	}
	
	public void onMouseReleased(int x, int y){
		blockManager.onMouseReleased( x, y );
		this.repaint();
	}
	
	public void paintComponent( Graphics g )
	{
		super.paintComponent( g );
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Config.DISPLAY_WIDTH, Config.DISPLAY_HEIGHT);
		blockManager.onDraw( g );
		
	}
	
}
