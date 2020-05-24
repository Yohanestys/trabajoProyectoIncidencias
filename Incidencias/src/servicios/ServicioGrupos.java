package servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Departamento;
import entidades.Grupo;
import entidades.Rol;
import entidades.Usuario;

/**
 * Session Bean implementation class ServicioGrupos
 */
@Stateless
@LocalBean
public class ServicioGrupos {
	@PersistenceContext(unitName = "Incidencias")
	private EntityManager em;

	
    /**
     * Default constructor. 
     */
    public ServicioGrupos() {
        // TODO Auto-generated constructor stub
    }
    
    
    @SuppressWarnings("unchecked")
    public List<Rol> listaPerfiles(){
    	Query queryconsulta = em.createNamedQuery("Rol.findAll");
    	List<Rol> listaPerfiles = (List<Rol>) queryconsulta.getResultList();
    	return listaPerfiles;
    }
    
    
    @SuppressWarnings("unchecked")
	public List<Departamento> listaDepartamento(String perfil){
    	//perfil = "tecnico";
    	List<Departamento> listaDepartamento = null;
    	if(perfil.equals("técnico")){
    		Query queryconsulta = em.createNamedQuery("Departamento.findAll");
    		listaDepartamento = (List<Departamento>) queryconsulta.getResultList();
    	}
    	
    	return listaDepartamento;
    }
    
    
    
    
    @SuppressWarnings("unchecked")
    public List<Grupo> listaGrupos(String email){
    	if(email.equals("-1") || email.equals("")) {
    		email = "%";
    	}
    	
    	String consulta = "select g from Grupo g where g.usuario.email like :email";
    	Query queryconsulta = em.createQuery(consulta);
    	queryconsulta.setParameter("email", email);
    	List<Grupo> listaGrupos = (List<Grupo>)queryconsulta.getResultList();
    	return listaGrupos;
    }
      

}
