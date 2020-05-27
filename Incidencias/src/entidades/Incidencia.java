package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the INCIDENCIA database table.
 * 
 */
@Entity
@NamedQuery(name="Incidencia.findAll", query="SELECT i FROM Incidencia i")
public class Incidencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="INCIDENCIA_IDINCIDENCIA_GENERATOR", sequenceName="S_INCIDENCIA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INCIDENCIA_IDINCIDENCIA_GENERATOR")
	private long idincidencia;

	private String asunto;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_MODIFICACION")
	private Date fechaModificacion;

	//bi-directional many-to-one association to Comentario
	@OneToMany(mappedBy="incidencia")
	private List<Comentario> comentarios;

	//bi-directional many-to-one association to Departamento
	@ManyToOne
	@JoinColumn(name="CATEGORIA")
	private Departamento departamento;

	//bi-directional many-to-one association to Estadoincidencia
	@ManyToOne
	@JoinColumn(name="IDESTADO")
	private Estadoincidencia estadoincidencia;

	//bi-directional many-to-one association to Prioridad
	@ManyToOne
	@JoinColumn(name="IDPRIORIDAD")
	private Prioridad prioridad;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="EMAIL_USUARIO")
	private Usuario usuario;

	//bi-directional many-to-one association to Lineadetalleincidencia
	@OneToMany(mappedBy="incidencia")
	private List<Lineadetalleincidencia> lineadetalleincidencias;

	public Incidencia() {
	}

	public long getIdincidencia() {
		return this.idincidencia;
	}

	public void setIdincidencia(long idincidencia) {
		this.idincidencia = idincidencia;
	}

	public String getAsunto() {
		return this.asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public List<Comentario> getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Comentario addComentario(Comentario comentario) {
		getComentarios().add(comentario);
		comentario.setIncidencia(this);

		return comentario;
	}

	public Comentario removeComentario(Comentario comentario) {
		getComentarios().remove(comentario);
		comentario.setIncidencia(null);

		return comentario;
	}

	public Departamento getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Estadoincidencia getEstadoincidencia() {
		return this.estadoincidencia;
	}

	public void setEstadoincidencia(Estadoincidencia estadoincidencia) {
		this.estadoincidencia = estadoincidencia;
	}

	public Prioridad getPrioridad() {
		return this.prioridad;
	}

	public void setPrioridad(Prioridad prioridad) {
		this.prioridad = prioridad;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Lineadetalleincidencia> getLineadetalleincidencias() {
		return this.lineadetalleincidencias;
	}

	public void setLineadetalleincidencias(List<Lineadetalleincidencia> lineadetalleincidencias) {
		this.lineadetalleincidencias = lineadetalleincidencias;
	}

	public Lineadetalleincidencia addLineadetalleincidencia(Lineadetalleincidencia lineadetalleincidencia) {
		getLineadetalleincidencias().add(lineadetalleincidencia);
		lineadetalleincidencia.setIncidencia(this);

		return lineadetalleincidencia;
	}

	public Lineadetalleincidencia removeLineadetalleincidencia(Lineadetalleincidencia lineadetalleincidencia) {
		getLineadetalleincidencias().remove(lineadetalleincidencia);
		lineadetalleincidencia.setIncidencia(null);

		return lineadetalleincidencia;
	}

}