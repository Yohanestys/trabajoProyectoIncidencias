package backing;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class backingAuth implements Serializable{

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(backingAuth.class.getName());
	public backingAuth() {
		// TODO Auto-generated constructor stub
	}
	
	  public String logout() {
		    String result="/index?faces-redirect=true";
		    
		    FacesContext context = FacesContext.getCurrentInstance();
		    HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
		    
		    try {
		      request.logout();
		    } catch (ServletException e) {
		      log.log(Level.SEVERE, "Failed to logout user!", e);
		      result = "/loginError?faces-redirect=true";
		    }
		    
		    return result;
		  }
  

}
