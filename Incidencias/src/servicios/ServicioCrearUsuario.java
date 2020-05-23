package servicios;

import java.util.List;

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
 * Session Bean implementation class ServicioCrearUsuario
 */
@Stateless
@LocalBean
public class ServicioCrearUsuario {
	@PersistenceContext(unitName = "Incidencias")
	private EntityManager em;
	private Usuario usuario;
    /**
     * Default constructor. 
     */
    public ServicioCrearUsuario() {
        // TODO Auto-generated constructor stub
    }
    
    /* ************************/
    
  	public Usuario nuevoUsuario(Usuario u,Long iddepartamento) {
  		
  		if(iddepartamento!=null){
  		Departamento departamento = em.find(Departamento.class, iddepartamento);
  		u.setDepartamento(departamento);
  		} else {
  			u.setDepartamento(null);

  		}
  		
  		usuario = u;
  		em.persist(u);
      	return u;
      }
      
      public Grupo nuevoGrupo(String idperfil) {
  		Grupo grupo = new Grupo();
  		grupo.setUsuario(usuario);
  		Rol perfil = em.find(Rol.class, idperfil);
  		grupo.setRol(perfil);
      	em.persist(grupo);
  		System.out.println("Usuario: "+usuario.getNombre()+ " creado...");
      	return grupo;
      }
      
      /******************* */
      
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
