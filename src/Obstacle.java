import java.awt.Graphics;
import java.awt.Point;


public abstract class Obstacle {
	
	/**
	 * used when translating this block (reference point)
	 */
	protected Point translate;
	
	/**
	 * The position of this obstacle (top left)
	 */
	protected int currentX, currentY;
	
	public Obstacle(){
		this.translate = new Point(-1, -1);
	}
	
	/**
	 * Called when the mouse is pressed
	 * @param x int
	 * @param y int
	 */
	public void onMousePressed(int x, int y){
		translate.x = x - currentX;
		translate.y = y - currentY;
	}
	
	/**
	 * Called when the mouse button is released
	 */
	public void onMouseReleased(){
		translate.x = -1;
		translate.y = -1;
		
		System.out.println("x/y "+currentX +" "+currentY);
	}
	
	/**
	 * Translate this obstacle to the new coordinates in relation to
	 *  it's current position
	 * @param x int
	 * @param y int
	 */
	public void translate(int x, int y){
		currentX = (x - translate.x);
		currentY = (y - translate.y);
	}
	
	/**
	 * Does the specified point exist within this obstacle?
	 * @param x int
	 * @param y int
	 * @return boolean
	 */
	public abstract boolean containsPoint(int x, int y);
	
	
	/**
	 * Called when this obstacle needs drawn
	 * @param g Graphics
	 * @parem isSelected boolean
	 */
	public abstract void onDraw(Graphics g, boolean isSelected);
	
}
