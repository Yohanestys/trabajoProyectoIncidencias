package entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ROL database table.
 * 
 */
@Entity
@NamedQuery(name="Rol.findAll", query="SELECT r FROM Rol r")
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idrol;

	public Rol() {
	}

	public String getIdrol() {
		return this.idrol;
	}

	public void setIdrol(String idrol) {
		this.idrol = idrol;
	}

}