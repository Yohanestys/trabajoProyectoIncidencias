package backing;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import entidades.Usuario;
import servicios.ServicioUsuarioLogeado;

@Named
@ViewScoped
public class backingDatosPersonales implements Serializable{
	private static final long serialVersionUID = 1L;
	@EJB
	private ServicioUsuarioLogeado servicioUsuarioLogeado;
	private Usuario usuarioLogeado;
	
	public backingDatosPersonales() {
		// TODO Auto-generated constructor stub
	}

}
