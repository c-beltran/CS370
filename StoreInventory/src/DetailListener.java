import java.util.EventListener;

public interface DetailListener extends EventListener {
	//received the detailevent object
	public void detailEventOccurred(DetailEvent event);
}
