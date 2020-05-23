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
	private List<Rol> listaPerfil;
	private String perfil;
	private List<Departamento> listaDepartamento;
	private String departamento;
	public backingUsuarios() {
		
	}
	
	@PostConstruct
	public void init() {
		this.listaPerfil = servicioGrupos.listaPerfiles();
	}

	public List<Rol> getListaPerfil() {
		return listaPerfil;
	}

	public void setListaPerfil(List<Rol> listaPerfil) {
		this.listaPerfil = listaPerfil;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public List<Departamento> getListaDepartamento() {
		return listaDepartamento;
	}

	public void setListaDepartamento(List<Departamento> listaDepartamento) {
		this.listaDepartamento = listaDepartamento;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public void ajaxListaDepartamento() {
		this.listaDepartamento = servicioGrupos.listaDepartamento(this.perfil);
		if(this.listaDepartamento == null) {
			this.departamento = null;
		}
		
		System.out.print("Lista departamento: " +this.listaDepartamento);
		System.out.print("Perfil: " + this.perfil);
		System.out.print("Departamento: " + this.departamento);

	}
	

}
