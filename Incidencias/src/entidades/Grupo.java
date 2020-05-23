package entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the GRUPOS database table.
 * 
 */
@Entity
@Table(name="GRUPOS")
@NamedQuery(name="Grupo.findAll", query="SELECT g FROM Grupo g")
public class Grupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GrupoPK id;

	//uni-directional many-to-one association to Rol
	@ManyToOne
	@JoinColumn(name="IDGRUPO")
	private Rol rol;

	//uni-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="EMAIL_USUARIO")
	private Usuario usuario;

	public Grupo() {
	}

	public GrupoPK getId() {
		return this.id;
	}

	public void setId(GrupoPK id) {
		this.id = id;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}