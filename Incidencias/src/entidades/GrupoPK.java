package entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the GRUPOS database table.
 * 
 */
@Embeddable
public class GrupoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String idgrupo;

	@Column(name="EMAIL_USUARIO", insertable=false, updatable=false)
	private String emailUsuario;

	public GrupoPK() {
	}
	public String getIdgrupo() {
		return this.idgrupo;
	}
	public void setIdgrupo(String idgrupo) {
		this.idgrupo = idgrupo;
	}
	public String getEmailUsuario() {
		return this.emailUsuario;
	}
	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GrupoPK)) {
			return false;
		}
		GrupoPK castOther = (GrupoPK)other;
		return 
			this.idgrupo.equals(castOther.idgrupo)
			&& this.emailUsuario.equals(castOther.emailUsuario);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idgrupo.hashCode();
		hash = hash * prime + this.emailUsuario.hashCode();
		
		return hash;
	}
}