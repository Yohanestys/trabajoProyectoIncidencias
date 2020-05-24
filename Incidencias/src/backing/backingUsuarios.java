package backing;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import entidades.Departamento;
import entidades.Rol;
import servicios.ServicioGrupos;

@Named
@ViewScoped
public class backingUsuarios implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private ServicioGrupos servicioGrupos;
	

}
