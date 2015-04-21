import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Path 
{
	private Point startPoint, endPoint;
	
	private List<Point> shortestPath;
	
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
	 * Construct a shortest path 
	 * @param bm
	 */
	public PathResponse simulate(ObstacleManager om){
		
		shortestPath.clear();
		
		boolean[][] grid = new boolean[Config.DISPLAY_WIDTH][Config.DISPLAY_HEIGHT];
		
		// create a grid that knows all spaces occupying a block
		// false = has block in space
		// false = node visited
		for(int x = 0; x < Config.DISPLAY_WIDTH; x++){
			for(int y = 0; y < Config.DISPLAY_HEIGHT; y++){
				grid[x][y] = !om.containsPoint( x, y );
			}
		}
		
		// construct a tree starting from the start point
		PathNode root = new PathNode(startPoint, null);
		// set the start node as visited
		grid[startPoint.x][startPoint.y] = false; 
		
		// add the root node to the queue for processing
		Queue<PathNode> queue = new LinkedList<PathNode>();
		queue.add( root );
		
		while(!queue.isEmpty()){
			if(queue.peek().containsPoint( endPoint )){
				System.out.println("endpoint found");
				addParent(queue.peek());
				return new PathResponse();
			}
			addAdjacentNodes( queue, grid );
		}
		return new PathResponse(true, "Path Not Found");
	}
	
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
	
		
		// top
		if(p.hasTop() && grid[x][y-1]){
			grid[x][y-1] = false;
			PathNode c = new PathNode(new Point(x, y-1), p);
			p.addChild( c );
			queue.add( c );
		}
		
		// right
		if(p.hasRight() && grid[x+1][y]){
			grid[x+1][y] = false;
			PathNode c = new PathNode(new Point(x+1, y), p);
			p.addChild( c );
			queue.add( c );
		}
		
		// bottom
		if(p.hasBottom() && grid[x][y+1]){
			grid[x][y+1] = false;
			PathNode c = new PathNode(new Point(x, y+1), p);
			p.addChild( c );
			queue.add( c );
		}
		
		// left
		if(p.hasLeft() && grid[x-1][y]){
			grid[x-1][y] = false;
			PathNode c = new PathNode(new Point(x-1, y), p);
			p.addChild( c );
			queue.add( c );
		}
		
		// bottom left
		if(p.hasBottom() && p.hasLeft() && grid[x-1][y+1]){
			grid[x-1][y+1] = false;
			PathNode c = new PathNode(new Point(x-1, y+1), p);
			p.addChild( c );
			queue.add( c );
		}
		
		// top left
		if(p.hasLeft() && p.hasTop() && grid[x-1][y-1]){
			grid[x-1][y-1] = false;
			PathNode c = new PathNode(new Point(x-1, y-1), p);
			p.addChild( c );
			queue.add( c );
		}
		
		// top right
		if(p.hasTop() && p.hasRight() && grid[x+1][y-1]){
			grid[x+1][y-1] = false;
			PathNode c = new PathNode(new Point(x+1, y-1), p);
			p.addChild( c );
			queue.add( c );
		}
		
		// bottom right
		if(p.hasRight() && p.hasBottom() && grid[x+1][y+1]){
			grid[x+1][y+1] = false;
			PathNode c = new PathNode(new Point(x+1, y+1), p);
			p.addChild( c );
			queue.add( c );
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
		for(Point p : shortestPath){
			g.drawLine(last.x, last.y, p.x, p.y);
			last = p;
		}
	}
}
