import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


public class Path 
{
	private Point startPoint, endPoint;
	
	private List<Point> shortestPath;
	
	private BufferedImage simulation;
	
	private PathNode root;
	
	private boolean[][] grid;
	
	private boolean hasSolution;
	
	
	public Path(){
		startPoint = new Point(40, Config.DISPLAY_HEIGHT-40);
		endPoint = new Point(Config.DISPLAY_WIDTH-40, Config.DISPLAY_HEIGHT-40);
		shortestPath = new ArrayList<Point>();
	}
	
	/**
	 * Set the start point
	 * @param p Point
	 */
	public void setStartPoint(Point p){
		if(Config.containsPoint( p.x, p.y )){
			startPoint = p;
		}
	}
	
	/**
	 * Set the end point
	 * @param p Point
	 */
	public void setEndPoint(Point p){
		if(Config.containsPoint( p.x, p.y )){
			endPoint = p;
		}
	}
	
	/**
	 * Clear the path. Typically called when something on the
	 *  grid has changed.
	 */
	public void clear(){
		shortestPath.clear();
	}
	
	/**
	 * does the simulation have a solution?
	 * @return boolean
	 */
	public boolean hasSolution(){
		return this.hasSolution;
	}
	
	/**
	 * clear the simulation BufferedImage
	 */
	public void clearSimulation(){
		simulation = null;
	}
	
	/**
	 * Construct a shortest path 
	 * @param bm
	 */
	public void simulateInit(ObstacleManager om, Queue<PathNode> queue){
		
		hasSolution = false;
		
		shortestPath.clear();
		
		grid = new boolean[Config.DISPLAY_WIDTH][Config.DISPLAY_HEIGHT];
		
		simulation = new BufferedImage( Config.DISPLAY_WIDTH, Config.DISPLAY_HEIGHT, BufferedImage.TYPE_INT_ARGB );
		
		// create a grid that knows all spaces occupying a block
		// false = has block in space
		// false = node visited
		int c = new Color(200, 0, 0).getRGB();
		for(int x = 0; x < Config.DISPLAY_WIDTH; x++){
			for(int y = 0; y < Config.DISPLAY_HEIGHT; y++){
				grid[x][y] = !om.containsPoint( x, y );
				if(!grid[x][y]){
					simulation.setRGB( x, y, c );
				}
			}
		}
		
		// construct a tree starting from the start point
		root = new PathNode(startPoint, null);
		queue.add( root );
		
		// set the start node as visited
		grid[startPoint.x][startPoint.y] = false; 
	}
	
	public boolean simulateIterate(Queue<PathNode> queue){
		if(queue.peek().containsPoint( endPoint )){
			System.out.println("endpoint found");
			addParent(queue.peek());
			hasSolution = true;
			return true;
		}
		addAdjacentNodes( queue, grid );
		return false;
	}
	
	/**
	 * Recursively visit this node's parents
	 * @param node PathNode
	 */
	private void addParent(PathNode node){
		shortestPath.add( node.getPoint() );
		
		if(node.getParent() != null){
			addParent(node.getParent());
		}
	}
	
	
	/**
	 * Add any adjacent nodes surrounding the node at the front of the node
	 *  that have not yet been visited or are not a block
	 * @param queue
	 * @param grid
	 */
	private void addAdjacentNodes(Queue<PathNode> queue, boolean[][] grid){
		PathNode p = queue.poll();
		int x = p.getX();
		int y = p.getY();
	
		int color = new Color(0, 200, 0).getRGB();
		
		// top
		if(p.hasTop() && grid[x][y-1]){
			grid[x][y-1] = false;
			PathNode c = new PathNode(new Point(x, y-1), p);
			p.addChild( c );
			queue.add( c );
			simulation.setRGB( x, y-1, color );
		}
		
		// right
		if(p.hasRight() && grid[x+1][y]){
			grid[x+1][y] = false;
			PathNode c = new PathNode(new Point(x+1, y), p);
			p.addChild( c );
			queue.add( c );
			simulation.setRGB( x+1, y, color );
		}
		
		// bottom
		if(p.hasBottom() && grid[x][y+1]){
			grid[x][y+1] = false;
			PathNode c = new PathNode(new Point(x, y+1), p);
			p.addChild( c );
			queue.add( c );
			simulation.setRGB( x, y+1, color );
		}
		
		// left
		if(p.hasLeft() && grid[x-1][y]){
			grid[x-1][y] = false;
			PathNode c = new PathNode(new Point(x-1, y), p);
			p.addChild( c );
			queue.add( c );
			simulation.setRGB( x-1, y, color );
		}
		
		// bottom left
		if(p.hasBottom() && p.hasLeft() && grid[x-1][y+1]){
			grid[x-1][y+1] = false;
			PathNode c = new PathNode(new Point(x-1, y+1), p);
			p.addChild( c );
			queue.add( c );
			simulation.setRGB( x-1, y+1, color );
		}
		
		// top left
		if(p.hasLeft() && p.hasTop() && grid[x-1][y-1]){
			grid[x-1][y-1] = false;
			PathNode c = new PathNode(new Point(x-1, y-1), p);
			p.addChild( c );
			queue.add( c );
			simulation.setRGB( x-1, y-1, color );
		}
		
		// top right
		if(p.hasTop() && p.hasRight() && grid[x+1][y-1]){
			grid[x+1][y-1] = false;
			PathNode c = new PathNode(new Point(x+1, y-1), p);
			p.addChild( c );
			queue.add( c );
			simulation.setRGB( x+1, y-1, color );
		}
		
		// bottom right
		if(p.hasRight() && p.hasBottom() && grid[x+1][y+1]){
			grid[x+1][y+1] = false;
			PathNode c = new PathNode(new Point(x+1, y+1), p);
			p.addChild( c );
			queue.add( c );
			simulation.setRGB( x+1, y+1, color );
		}
	}
	
	
	/**
	 * Draw this path
	 * @param g Graphics
	 */
	public void onDraw(Graphics g){
		g.setColor( Color.GREEN );
		g.fillOval( startPoint.x-5, startPoint.y-5, 10, 10 );
		g.setColor( Color.RED );
		g.fillOval( endPoint.x-5, endPoint.y-5, 10, 10 );
		
		Point last = endPoint;
		g.setColor(Color.WHITE);
		List<Point> render = new ArrayList<Point>(shortestPath);
		for(Point p : render){
			g.drawLine(last.x, last.y, p.x, p.y);
			last = p;
		}
		
		if(simulation != null){
			g.drawImage( simulation, 0, 0, null );
		}
	}
}
