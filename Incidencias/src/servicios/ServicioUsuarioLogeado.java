package servicios;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Usuario;

/**
 * Session Bean implementation class ServicioUsuarioLogeado
 */
@Stateless
@LocalBean
public class ServicioUsuarioLogeado {
	@PersistenceContext(unitName = "Incidencias")
	private EntityManager em;
	
    @Resource
    private SessionContext context;
    /**
     * Default constructor. 
     */
    public ServicioUsuarioLogeado() {
        // TODO Auto-generated constructor stub
    }
    
    public Usuario getUser() {
	    System.out.println("Username in EJB "+context.getCallerPrincipal().getName());
	    String email = context.getCallerPrincipal().getName();
	    Usuario usuario = em.find(Usuario.class, email);
    	return usuario;
    }
    
   
    public boolean isUserInRole(String roleName) {
		return context.isCallerInRole(roleName);
    }
    
    
    public Usuario updateUser(Usuario u) {
    	em.merge(u);
    	System.out.println(u);
    	return u;
    }

}
