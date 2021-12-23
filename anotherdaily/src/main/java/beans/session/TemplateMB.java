package beans.session;

import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.inject.Named;

@Named("templateMB")
@SessionScoped
public class TemplateMB implements Serializable {
	private String template = "protoss"; //Protoss is default OFC
	
	public TemplateMB() {
		
	}

	public void tempEvent(ValueChangeEvent e) {
		template = (String) e.getNewValue();
	}
	
	public String getTemplate() {
		return template;
	}
	
}
