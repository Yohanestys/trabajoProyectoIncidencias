package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ESTADOINCIDENCIA database table.
 * 
 */
@Entity
@NamedQuery(name="Estadoincidencia.findAll", query="SELECT e FROM Estadoincidencia e")
public class Estadoincidencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long idestado;

	@Column(name="DESCRIPCION_ESTADO")
	private String descripcionEstado;

	//bi-directional many-to-one association to Incidencia
	@OneToMany(mappedBy="estadoincidencia")
	private List<Incidencia> incidencias;

	public Estadoincidencia() {
	}

	public long getIdestado() {
		return this.idestado;
	}

	public void setIdestado(long idestado) {
		this.idestado = idestado;
	}

	public String getDescripcionEstado() {
		return this.descripcionEstado;
	}

	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
	}

	public List<Incidencia> getIncidencias() {
		return this.incidencias;
	}

	public void setIncidencias(List<Incidencia> incidencias) {
		this.incidencias = incidencias;
	}

	public Incidencia addIncidencia(Incidencia incidencia) {
		getIncidencias().add(incidencia);
		incidencia.setEstadoincidencia(this);

		return incidencia;
	}

	public Incidencia removeIncidencia(Incidencia incidencia) {
		getIncidencias().remove(incidencia);
		incidencia.setEstadoincidencia(null);

		return incidencia;
	}

}