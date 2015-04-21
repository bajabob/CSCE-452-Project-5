import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;


public class ObstacleManager
{

	private List<Obstacle> obstacles;
	
	/**
	 * The current selected obstacle by the mouse
	 */
	private Obstacle currentSelectedObstacle = null;
	
	public ObstacleManager(){
		obstacles = new ArrayList<Obstacle>();
		obstacles.add( new Block(398, 269, 200, 200) );
		obstacles.add( new Block(324, -14, 150, 150) );
		obstacles.add( new Block(492, 71, 100, 100 ) );
		
		obstacles.add( new Block(699, 153, 20, 450 ) );
		obstacles.add( new Block(654, -1, 20, 450 ) );
		obstacles.add( new Block(610, 153, 20, 450 ) );
		
		obstacles.add( new Block(279, 154, 350, 20 ) );
		obstacles.add( new Block(-1, 196, 350, 20 ) );
		obstacles.add( new Block(279, 239, 350, 20 ) );
		
		obstacles.add( new Block(-1, 284, 400, 20 ) );
	}
	
	/**
	 * Are there any obstacles occupying this point?
	 * @param x int
	 * @param y int
	 * @return boolean
	 */
	public boolean containsPoint(int x, int y){
		for(Obstacle o : obstacles){
			
			// does this obstacle contain the mouse click coordinates?
			if(o.containsPoint( x, y )){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Called when the mouse is pressed initially
	 * @param x int
	 * @param y int
	 */
	public void onMousePressed(int x, int y){
		for(Obstacle o : obstacles){
			
			// is the mouse click contained within this obstacle?
			if(o.containsPoint( x, y )){
				currentSelectedObstacle = o;
				currentSelectedObstacle.onMousePressed( x, y );
				break;
			}
		}
	}
	
	/**
	 * Called when the mouse is dragged
	 * @param x int
	 * @param y int
	 */
	public void onMouseDragged(int x, int y){
		if(currentSelectedObstacle != null){
			currentSelectedObstacle.translate( x, y );
		}
	}
	
	/**
	 * Called when the mouse is released
	 * @param x int
	 * @param y int
	 */
	public void onMouseReleased(int x, int y){
		if(currentSelectedObstacle != null){
			currentSelectedObstacle.onMouseReleased();
		}
		currentSelectedObstacle = null;
	}
	
	/**
	 * Called when all obstacles require drawing
	 * @param g Graphics
	 */
	public void onDraw(Graphics g){
		for(Obstacle o : obstacles){
			o.onDraw( g, (currentSelectedObstacle == o));
		}
	}
	
}
