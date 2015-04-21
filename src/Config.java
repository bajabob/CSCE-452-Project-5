import java.awt.Color;


public class Config {
	
	public static final int DISPLAY_WIDTH = 800;
	public static final int DISPLAY_HEIGHT = 600;
	
	public static final Color BLOCK_COLOR = new Color(0.0f, 0.0f, 1.0f, 0.8f);
	
	/**
	 * Does this point exist in the configuration?
	 * @param x int
	 * @param y int
	 * @return boolean
	 */
	public static boolean containsPoint(int x, int y){
		if(x >= 0 && x < DISPLAY_WIDTH){
			if(y >= 0 && y < DISPLAY_HEIGHT){
				return true;
			}
		}
		return false;
	}
	
}
