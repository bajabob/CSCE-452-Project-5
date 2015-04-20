import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;


public class BlockManager
{

	private List<Block> blocks;
	
	/**
	 * The current selected block by the mouse
	 */
	private Block currentSelectedBlock = null;
	
	public BlockManager(){
		blocks = new ArrayList<Block>();
		blocks.add( new Block(10, 10, 200, 200) );
		blocks.add( new Block(260, 30, 150, 150) );
		blocks.add( new Block(300, 300, 100, 100 ) );

	}
	
	public void onMousePressed(int x, int y){
		for(Block b : blocks){
			if(b.containsPoint( x, y )){
				currentSelectedBlock = b;
				currentSelectedBlock.onMousePressed( x, y );
				break;
			}
		}
	}
	
	public void onMouseDragged(int x, int y){
		if(currentSelectedBlock != null){
			currentSelectedBlock.translate( x, y );
		}
	}
	
	public void onMouseReleased(int x, int y){
		if(currentSelectedBlock != null){
			currentSelectedBlock.onMouseReleased();
		}
	}
	
	public void onDraw(Graphics g){
		for(int i = 0; i < blocks.size(); i++){
			blocks.get( i ).onDraw( g );
		}
	}
	
}
