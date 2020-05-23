package backing;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import servicios.ServicioGrupos;

@Named
@ViewScoped
public class backingGrupo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private ServicioGrupos servicioGrupos;
	public backingGrupo() {
		// TODO Auto-generated constructor stub
	}

}
