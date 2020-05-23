package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the COMENTARIOS database table.
 * 
 */
@Entity
@Table(name="COMENTARIOS")
@NamedQuery(name="Comentario.findAll", query="SELECT c FROM Comentario c")
public class Comentario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long idcomentario;

	private String comentario;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_COMENTARIO")
	private Date fechaComentario;

	//bi-directional many-to-one association to Incidencia
	@ManyToOne
	@JoinColumn(name="IDINCIDENCIA")
	private Incidencia incidencia;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="EMAIL_USUARIO")
	private Usuario usuario;

	public Comentario() {
	}

	public long getIdcomentario() {
		return this.idcomentario;
	}

	public void setIdcomentario(long idcomentario) {
		this.idcomentario = idcomentario;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getFechaComentario() {
		return this.fechaComentario;
	}

	public void setFechaComentario(Date fechaComentario) {
		this.fechaComentario = fechaComentario;
	}

	public Incidencia getIncidencia() {
		return this.incidencia;
	}

	public void setIncidencia(Incidencia incidencia) {
		this.incidencia = incidencia;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}