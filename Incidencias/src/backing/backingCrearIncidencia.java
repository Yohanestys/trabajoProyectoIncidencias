package backing;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import entidades.Departamento;
import servicios.ServicioCrearIncidencia;

@Named
@ViewScoped
public class backingCrearIncidencia implements Serializable{

	private static final long serialVersionUID = 1L;
	@EJB
	private ServicioCrearIncidencia servicioCrearIncidencia;
	private List<Departamento> listaDepartamentos;
	private Long iddepartamento;
	public backingCrearIncidencia() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	public void init() {
		listaDepartamentos = servicioCrearIncidencia.addDepartamentos();
	}

	public List<Departamento> getListaDepartamentos() {
		return listaDepartamentos;
	}

	public void setListaDepartamentos(List<Departamento> listaDepartamentos) {
		this.listaDepartamentos = listaDepartamentos;
	}

	public Long getIddepartamento() {
		return iddepartamento;
	}

	public void setIddepartamento(Long iddepartamento) {
		this.iddepartamento = iddepartamento;
	}
	
	
	

}
