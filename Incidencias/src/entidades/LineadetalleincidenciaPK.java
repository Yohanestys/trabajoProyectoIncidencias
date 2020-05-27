package entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the LINEADETALLEINCIDENCIA database table.
 * 
 */
@Embeddable
public class LineadetalleincidenciaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private long idincidencia;

	@Column(name="ID_LINEA_DETALLE")
	private long idLineaDetalle;

	public LineadetalleincidenciaPK() {
	}
	public long getIdincidencia() {
		return this.idincidencia;
	}
	public void setIdincidencia(long idincidencia) {
		this.idincidencia = idincidencia;
	}
	public long getIdLineaDetalle() {
		return this.idLineaDetalle;
	}
	public void setIdLineaDetalle(long idLineaDetalle) {
		this.idLineaDetalle = idLineaDetalle;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof LineadetalleincidenciaPK)) {
			return false;
		}
		LineadetalleincidenciaPK castOther = (LineadetalleincidenciaPK)other;
		return 
			(this.idincidencia == castOther.idincidencia)
			&& (this.idLineaDetalle == castOther.idLineaDetalle);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.idincidencia ^ (this.idincidencia >>> 32)));
		hash = hash * prime + ((int) (this.idLineaDetalle ^ (this.idLineaDetalle >>> 32)));
		
		return hash;
	}
}