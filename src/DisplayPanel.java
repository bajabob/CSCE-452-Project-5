import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JPanel;


public class DisplayPanel extends JPanel{

	/**
	 * Handles the drawing and coordinate management of the various blocks
	 */
	private ObstacleManager obstacleManager;
	
	/**
	 * Contains the path information and construction routines
	 */
	private Path path;
	
	private boolean paintWait;
	
	public DisplayPanel(){
		this.setBounds(0, 0, Config.DISPLAY_WIDTH, Config.DISPLAY_HEIGHT);
		obstacleManager = new ObstacleManager();
		path = new Path();
	}
	
	/**
	 * Called when the mouse is pressed initially
	 * @param x int
	 * @param y int
	 * @param currentToggle String
	 */
	public void onMousePressed(int x, int y, String currentToggle){
		if(currentToggle == ControlPanel.TOGGLE_MOVE_OBSTACLES){
			obstacleManager.onMousePressed( x, y );
		}
		if(currentToggle == ControlPanel.TOGGLE_DROP_START_POINT){
			if( !obstacleManager.containsPoint( x, y )){
				path.setStartPoint( new Point(x, y) );
			}
		}
		if(currentToggle == ControlPanel.TOGGLE_DROP_END_POINT){
			if( !obstacleManager.containsPoint( x, y )){
				path.setEndPoint( new Point(x, y) );
			}
		}
		
		// clear the path since something has changed
		path.clear();
		this.repaint();
	}
	
	/**
	 * Called when the mouse is pressed and dragging
	 * @param x int
	 * @param y int
	 * @param currentToggle String
	 */
	public void onMouseDragged(int x, int y, String currentToggle){
		if(currentToggle == ControlPanel.TOGGLE_MOVE_OBSTACLES){
			obstacleManager.onMouseDragged( x, y );
		}
		if(currentToggle == ControlPanel.TOGGLE_DROP_START_POINT){
			if( !obstacleManager.containsPoint( x, y )){
				path.setStartPoint( new Point(x, y) );
			}
		}
		if(currentToggle == ControlPanel.TOGGLE_DROP_END_POINT){
			if( !obstacleManager.containsPoint( x, y )){
				path.setEndPoint( new Point(x, y) );
			}
		}
		this.repaint();
	}
	
	/**
	 * Called when the mouse is released
	 * @param x int
	 * @param y int
	 * @param currentToggle String
	 */
	public void onMouseReleased(int x, int y, String currentToggle){
		if(currentToggle == ControlPanel.TOGGLE_MOVE_OBSTACLES){
			obstacleManager.onMouseReleased( x, y );
		}
		this.repaint();
	}
	
	
	/**
	 * Simulate the shortest path
	 */
	public void onSimulate(){
		Queue<PathNode> queue = new LinkedList<PathNode>();
		
		path.simulateInit( obstacleManager, queue );
		this.repaint();
		
		Thread thread = new Thread(){
			public void run(){
				int count = 0;
				while(!queue.isEmpty() && !path.simulateIterate( queue )){
					if(count < 150){
						count++;
						continue;
					}
					paintWait = true;
					while(paintWait){
						repaint();
					}
					count = 0;
				}
				path.clearSimulation();
				repaint();
			}
		};
		
		thread.start();
	}
	
	/**
	 * Called when this object requires drawing
	 * @param g Graphics
	 */
	public void paintComponent( Graphics g )
	{
		super.paintComponent( g );
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Config.DISPLAY_WIDTH, Config.DISPLAY_HEIGHT);
		obstacleManager.onDraw( g );
		path.onDraw( g );
		paintWait = false;
		
	}
	
}
