package beans.session;

import java.io.Serializable;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named
@SessionScoped
public class lightUser implements Serializable {
    
    private long user_id;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
    
    
    public lightUser(){
        
    }
    
    @PostConstruct
    public void init() {  
        //user_id=context().getExternalContext()
    }
    
     protected FacesContext context() {
        return (FacesContext.getCurrentInstance());
    }
    
}