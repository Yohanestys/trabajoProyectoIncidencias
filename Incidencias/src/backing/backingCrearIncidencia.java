package backing;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import entidades.Comentario;
import entidades.Departamento;
import entidades.Estadoincidencia;
import entidades.Grupo;
import entidades.Incidencia;
import entidades.Lineadetalleincidencia;
import entidades.Prioridad;
import entidades.Usuario;
import servicios.ServicioUsuarioLogeado;

@Named
@ViewScoped
public class backingCrearIncidencia implements Serializable{

	private static final long serialVersionUID = 1L;
	@EJB
	private ServicioUsuarioLogeado servicioUsuarioLogeado;
	private List<Departamento> listaDepartamentos;
	private Long iddepartamento;
	private List<Prioridad> listaPrioridades;
	private Long idprioridad;
	private Incidencia nuevaIncidencia = new Incidencia();
	private Comentario nuevoComentario = new Comentario();
	private Estadoincidencia nuevoEstado = new Estadoincidencia();
	private Lineadetalleincidencia lineadetalleincidencia;
	private Usuario logedUser;
	private Grupo grupo;
	
	public backingCrearIncidencia() {
		// TODO Auto-generated constructor stub
		
	}
	
	@PostConstruct
	public void init() {
		grupo = servicioUsuarioLogeado.getGroup();
		listaDepartamentos = servicioUsuarioLogeado.addDepartamentos();
		listaPrioridades = servicioUsuarioLogeado.addPrioridades();
		nuevoComentario.setComentario(grupo.getRol().getIdrol()+" $");
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

	public List<Prioridad> getListaPrioridades() {
		return listaPrioridades;
	}

	public void setListaPrioridades(List<Prioridad> listaPrioridades) {
		this.listaPrioridades = listaPrioridades;
	}

	public Long getIdprioridad() {
		return idprioridad;
	}

	public void setIdprioridad(Long idprioridad) {
		this.idprioridad = idprioridad;
	}
	
	
	public Incidencia getNuevaIncidencia() {
		return nuevaIncidencia;
	}

	public void setNuevaIncidencia(Incidencia nuevaIncidencia) {
		this.nuevaIncidencia = nuevaIncidencia;
	}

	public Comentario getNuevoComentario() {
		return nuevoComentario;
	}

	public void setNuevoComentario(Comentario nuevoComentario) {
		this.nuevoComentario = nuevoComentario;
	}

	public Estadoincidencia getNuevoEstado() {
		return nuevoEstado;
	}

	public void setNuevoEstado(Estadoincidencia nuevoEstado) {
		this.nuevoEstado = nuevoEstado;
	}
	

	public Lineadetalleincidencia getLineadetalleincidencia() {
		return lineadetalleincidencia;
	}

	public void setLineadetalleincidencia(Lineadetalleincidencia lineadetalleincidencia) {
		this.lineadetalleincidencia = lineadetalleincidencia;
	}

	public void crearIncidencia(){
		logedUser = grupo.getUsuario();
		nuevaIncidencia.setUsuario(logedUser);
		servicioUsuarioLogeado.crearIncidencia(nuevaIncidencia, nuevoComentario, idprioridad, iddepartamento);
		FacesContext context=FacesContext.getCurrentInstance();
		ResourceBundle archivomensajes=ResourceBundle.getBundle("resources.application",context.getViewRoot().getLocale());
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,archivomensajes.getString("generico.registroCreadoConExito"),archivomensajes.getString("generico.registroCreadoConExito")));
		System.out.println("Antes de llamar a prepararCrearAutor por tanto debe haber nueva instancia");
		
			
		
	}
		
	

}
