import java.awt.Graphics;
import java.awt.Point;

public class Block
{

	/**
	 * The position of this block (top left)
	 */
	private int currentX, currentY;
	
	/**
	 * the dimensions of this block
	 */
	private int width, height;

	/**
	 * used when translating this block (reference point)
	 */
	private Point translate;
	
	public Block(int x, int y, int width, int height){
		this.currentX = x;
		this.currentY = y;
		this.width = width;
		this.height = height;
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
	}
	
	/**
	 * Translate this block to the new coordinates in relation to
	 *  it's current position
	 * @param x int
	 * @param y int
	 */
	public void translate(int x, int y){
		int newX = (x - translate.x);
		int newY = (y - translate.y);
		
		// make sure block stays in bounds
		if(Config.containsPoint( newX, newY )){
			if(Config.containsPoint( newX+width, newY+height )){
				currentX = newX;
				currentY = newY;
			}
		}
	}
	
	/**
	 * Does the specified point exist within this block?
	 * @param x int
	 * @param y int
	 * @return boolean
	 */
	public boolean containsPoint(int x, int y){
		if(x > currentX){
			if(y > currentY){
				if(x < currentX + width){
					if(y < currentY + height){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Called when this object needs drawn
	 * @param g Graphics
	 */
	public void onDraw(Graphics g){
		g.setColor( Config.BLOCK_COLOR );
		g.fillRect( currentX, currentY, width, height );
	}
	
}
