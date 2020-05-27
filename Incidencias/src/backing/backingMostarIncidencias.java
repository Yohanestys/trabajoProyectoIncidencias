package backing;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import entidades.Departamento;
import entidades.Grupo;
import entidades.Incidencia;
import servicios.ServicioUsuarioLogeado;
import util.PaginacionHelper;

@Named
@ViewScoped
public class backingMostarIncidencias implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@EJB
	private ServicioUsuarioLogeado servicioUsuarioLogeado;
	private List<Incidencia> listaIncidencias;
	private int slctnrpag = 5;
	private PaginacionHelper paginacion;
	private Grupo grupo;
	private String rol;
	private String email;
	private String iddepartamento;

	public backingMostarIncidencias() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	public void init() {
		try {
			grupo = servicioUsuarioLogeado.getGroup();
			this.rol = grupo.getRol().getIdrol();
			this.email = grupo.getUsuario().getEmail();
			Departamento departamento = servicioUsuarioLogeado.getDepartamento(this.rol, this.email);
			if(departamento == null) {
				this.iddepartamento = null;
			}
			
			this.iddepartamento = servicioUsuarioLogeado.getDepartamento(this.rol, this.email).getIddepartamento();
			
			if (paginacion == null) {
				paginacion = new PaginacionHelper(5, 0) {
	// sobreescritura del metodo abastracto getItemsCount
	// para poder utilizarlo desde distintos ejb
					@Override
					public long getItemsCount() {
						return servicioUsuarioLogeado.getTolalIncidencias(rol, email, iddepartamento);
					}
				};
			}
			listaIncidencias = servicioUsuarioLogeado.incidenciasEnRango(paginacion.getPagina() * paginacion.getNrpag(),
					paginacion.getNrpag(), this.rol, this.email, this.iddepartamento);
		} catch (NullPointerException e) {
			// TODO: handle exception
		}

			
	}
	
	
	/******************************************************************************************/

	public int getSlctnrpag() {
		return slctnrpag;
	}

	public void setSlctnrpag(int slctnrpag) {
		this.slctnrpag = slctnrpag;
	}

	public PaginacionHelper getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(PaginacionHelper paginacion) {
		this.paginacion = paginacion;
	}

	public List<Incidencia> getListaIncidencias() {
		return listaIncidencias;
	}

	public void setListaIncidencias(List<Incidencia> listaIncidencias) {
		this.listaIncidencias = listaIncidencias;
	}
	
	
	
	/******************************************************************************************/

	/*************************************************************************/
	public int getTotalIncidenciaCandidatos() {
		return (listaIncidencias != null) ? listaIncidencias.size() : 0;
	}

	/***************************************************************************/
	public long getTotalIncidencias() {
		String rol = grupo.getRol().getIdrol();
		String email = grupo.getUsuario().getEmail();
		String iddepartamento =""+grupo.getUsuario().getDepartamento().getIddepartamento();
		return servicioUsuarioLogeado.getTolalIncidencias(rol, email, iddepartamento);
	}

	/********************************************************************/
	public void paginaAnterior() {
		paginacion.getPaginaAnterior();
		listaIncidencias = servicioUsuarioLogeado.incidenciasEnRango(paginacion.getPagina() * paginacion.getNrpag(),
				paginacion.getNrpag(), this.rol, this.email, this.iddepartamento);
	}

	/*********************************************************************/
	public void paginaSiguiente() {
		paginacion.getPaginaSiguiente();
		listaIncidencias = servicioUsuarioLogeado.incidenciasEnRango(paginacion.getPagina() * paginacion.getNrpag(),
				paginacion.getNrpag(), this.rol, this.email, this.iddepartamento);
	}

	/****************************************************************************/
	//public String editarSocio() {
		//return "/admin/editIncidencia.xhtml?pagina=/usuario/incidenciaspaginado.xhtml&faces-redirect=true";
	//}

	/************************************************************************/
	
	
	public void resetPaginacion() {
		/*
		 * Procedimiento que recalcula el número de página en función de como se sube y
		 * baja el numero de registros por página. A este procedimiento se le llama
		 * mediante peticion ajax asociada al cuadro combinado que permite seleccionar
		 * los registros por pagina. EL valor seleccionado esta asociado a la propiedad
		 * slctnrpag de nuestro backing bean.
		 */
		int nuevapagina = (paginacion.getPrimerElementoPagina() / slctnrpag);
		paginacion.setNrpag(slctnrpag);
		paginacion.setPagina(nuevapagina);
		listaIncidencias = servicioUsuarioLogeado.incidenciasEnRango(paginacion.getPagina() * paginacion.getNrpag(),
				paginacion.getNrpag(), this.rol, this.email, this.iddepartamento);
	}
	/**************************************************************************/



}
	
	
	
	
	
	
	
	


