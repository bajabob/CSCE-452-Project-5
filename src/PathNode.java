import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class PathNode
{
	private Point point;
    private PathNode parent;
    private List<PathNode> children;
    
    public PathNode(Point point, PathNode parent){
    	this.point = point;
    	this.parent = parent;
    	children = new ArrayList<PathNode>();
    }
    
    public void addChild(PathNode p){
    	children.add( p );
    }
    
    public boolean hasLeft(){
    	return (point.x-1 >= 0);
    }
    
    public boolean hasRight(){
    	return (point.x+1 < Config.DISPLAY_WIDTH);
    }
    
    public boolean hasTop(){
    	return (point.y-1 >= 0);
    }
    
    public boolean hasBottom(){
    	return (point.y+1 < Config.DISPLAY_HEIGHT);
    }
    
    public int getX(){
    	return point.x;
    }
    
    public int getY(){
    	return point.y;
    }
    
    public Point getPoint(){
    	return point;
    }
    
    public PathNode getParent(){
    	return parent;
    }
    
    public boolean containsPoint(Point p){
    	if(point.x == p.x && point.y == p.y){
    		return true;
    	}
    	return false;
    }
}
