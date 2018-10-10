import java.util.EventListener;

/**
 * @author Carlos Alberto
 *Interface used for implementing
 *detailEventOccurred event
 */
public interface DetailListener extends EventListener {
	//received the detailEvent object
	public void detailEventOccurred(DetailEvent event);
	}

