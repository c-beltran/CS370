import java.util.EventListener;

public interface DetailListener extends EventListener {
	//received the detailEvent object
	public void detailEventOccurred(DetailEvent event);
	}

