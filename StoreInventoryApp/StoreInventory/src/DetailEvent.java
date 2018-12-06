import java.util.EventObject;

/**
 * @author Carlos Alberto
 *Class gets the that comes from 
 *a DetailEvent and returns to the
 *getText() funciton when called
 */
public class DetailEvent extends EventObject {
	
	private String text;
	
	public DetailEvent(Object source, String text){
		super(source);
		
		this.text = text;
	}

	public String getText(){
		return text;
	}
}
