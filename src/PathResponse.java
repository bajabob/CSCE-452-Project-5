
public class PathResponse {
	
	private boolean hasError;
	private String message;
	
	public PathResponse(){
		hasError = false;
	}
	
	public PathResponse(boolean hasError, String message){
		this.hasError = hasError;
		this.message = message;
	}
	
	public boolean hasError(){
		return this.hasError;
	}
	
	public String getMessage(){
		return this.message;
	}
}
