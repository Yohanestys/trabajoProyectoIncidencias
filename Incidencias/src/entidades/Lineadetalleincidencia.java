package entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the LINEADETALLEINCIDENCIA database table.
 * 
 */
@Entity
@NamedQuery(name="Lineadetalleincidencia.findAll", query="SELECT l FROM Lineadetalleincidencia l")
public class Lineadetalleincidencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LineadetalleincidenciaPK id;

	@Column(name="MAXIMO_COMENTARIOS")
	private Integer maximoComentarios;

	@Column(name="TOTAL_LINEA_DETALLE")
	private Integer totalLineaDetalle;

	//bi-directional many-to-one association to Estadoincidencia
	@ManyToOne
	@JoinColumn(name="ID_ESTADO")
	private Estadoincidencia estadoincidencia;

	//bi-directional many-to-one association to Incidencia
	@ManyToOne
	@JoinColumn(name="IDINCIDENCIA")
	private Incidencia incidencia;

	public Lineadetalleincidencia() {
	}

	public LineadetalleincidenciaPK getId() {
		return this.id;
	}

	public void setId(LineadetalleincidenciaPK id) {
		this.id = id;
	}

	public Integer getMaximoComentarios() {
		return this.maximoComentarios;
	}

	public void setMaximoComentarios(Integer maximoComentarios) {
		this.maximoComentarios = maximoComentarios;
	}

	public Integer getTotalLineaDetalle() {
		return this.totalLineaDetalle;
	}

	public void setTotalLineaDetalle(Integer totalLineaDetalle) {
		this.totalLineaDetalle = totalLineaDetalle;
	}

	public Estadoincidencia getEstadoincidencia() {
		return this.estadoincidencia;
	}

	public void setEstadoincidencia(Estadoincidencia estadoincidencia) {
		this.estadoincidencia = estadoincidencia;
	}

	public Incidencia getIncidencia() {
		return this.incidencia;
	}

	public void setIncidencia(Incidencia incidencia) {
		this.incidencia = incidencia;
	}

}