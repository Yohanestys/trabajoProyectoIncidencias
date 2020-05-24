package servicios;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Departamento;

/**
 * Session Bean implementation class ServicioCrearIncidencia
 */
@Stateless
@LocalBean
public class ServicioCrearIncidencia {
	@PersistenceContext(unitName = "Incidencias")
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public ServicioCrearIncidencia() {
        // TODO Auto-generated constructor stub
    }
    
    @SuppressWarnings("unchecked")
    public List<Departamento> addDepartamentos() {
    	Query queryConsulta = em.createNamedQuery("Departamento.findAll");
		List<Departamento> listaDepartamentos = (List<Departamento>) queryConsulta.getResultList();
    	return listaDepartamentos;
    }
    
    

}
