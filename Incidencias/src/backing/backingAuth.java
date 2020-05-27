package backing;

import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.faces.application.FacesMessage;

import entidades.Grupo;
import entidades.Usuario;
import servicios.ServicioUsuarioLogeado;

@Named
@RequestScoped
public class backingAuth implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(backingAuth.class.getName());
	@EJB
	private ServicioUsuarioLogeado servicioUsuarioLogeado;
	private Usuario usuarioLogeado;
	private Grupo grupoUsuarioLogeado;
	private String password = "*******************";

	// private String password;
	public backingAuth() {
		// TODO Auto-generated constructor stub
	}

	public String logout() {
		String result = "/index?faces-redirect=true";

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

		try {
			request.logout();
		} catch (ServletException e) {
			log.log(Level.SEVERE, "Failed to logout user!", e);
			result = "/loginError?faces-redirect=true";
		}

		return result;
	}

	@PostConstruct
	public void init() {
		this.grupoUsuarioLogeado = servicioUsuarioLogeado.getGroup();
		this.usuarioLogeado = servicioUsuarioLogeado.getUser();
	}
	

	public Grupo getGrupoUsuarioLogeado() {
		return grupoUsuarioLogeado;
	}

	public void setGrupoUsuarioLogeado(Grupo grupoUsuarioLogeado) {
		this.grupoUsuarioLogeado = grupoUsuarioLogeado;
	}
	

	public Usuario getUsuarioLogeado() {
		return usuarioLogeado;
	}

	public void setUsuarioLogeado(Usuario usuarioLogeado) {
		this.usuarioLogeado = usuarioLogeado;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void updatePasswd(String passwd) {
		this.password = passwd;
	}

	public void updateUser() {
		try {
			
			
			System.out.println(this.password);
			
			if (!(this.password.equals("*******************"))) {
				String passwd = encriptacion.Md5Hash.rMd5Hash(this.password);
				usuarioLogeado.setPassword(passwd);
				servicioUsuarioLogeado.updateUser(usuarioLogeado);
				System.out.println(usuarioLogeado.getPassword());
				Grupo grupo = servicioUsuarioLogeado.getGroup();
				System.out.println("Perfil: "+ grupo.getRol().getIdrol());
				FacesContext context = FacesContext.getCurrentInstance();
				ResourceBundle archivomensajes = ResourceBundle.getBundle("resources.application",
						context.getViewRoot().getLocale());
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								archivomensajes.getString("generico.registroActualizado"),
								archivomensajes.getString("generico.registroActualizado")));
			}
			
		} catch (EJBException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			if (e.getCause() instanceof OptimisticLockException)
				;
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro cambió", "El registro cambió"));
		} catch (Exception e) {

		}

	}

}
