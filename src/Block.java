import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Block extends Obstacle
{
	
	/**
	 * the dimensions of this block
	 */
	private int width, height;

	
	public Block(int x, int y, int width, int height){
		this.currentX = x;
		this.currentY = y;
		this.width = width;
		this.height = height;
		this.translate = new Point(-1, -1);
	}
	
	
	/**
	 * Does the specified point exist within this block?
	 * @param x int
	 * @param y int
	 * @return boolean
	 */
	public boolean containsPoint(int x, int y){
		if(x >= currentX){
			if(y >= currentY){
				if(x <= currentX + width){
					if(y <= currentY + height){
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
	public void onDraw(Graphics g, boolean isSelected){
		g.setColor( Config.BLOCK_COLOR );
		g.fillRect( currentX, currentY, width, height );
		if(isSelected){
			g.setColor(Color.WHITE);
			g.drawRect( currentX, currentY, width, height );
		}
	}
	
}
