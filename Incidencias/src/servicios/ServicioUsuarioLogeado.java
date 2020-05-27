package servicios;


import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Comentario;
import entidades.Departamento;
import entidades.Estadoincidencia;
import entidades.Grupo;
import entidades.Incidencia;
import entidades.Lineadetalleincidencia;
import entidades.LineadetalleincidenciaPK;
import entidades.Prioridad;
import entidades.Usuario;

/**
 * Session Bean implementation class ServicioUsuarioLogeado
 */
@Stateless
@LocalBean
public class ServicioUsuarioLogeado {
	@PersistenceContext(unitName = "Incidencias")
	private EntityManager em;
	private static Logger log;
	
	// Parte perfil usuario
	
    @Resource
    private SessionContext context;
    /**
     * Default constructor. 
     */
    public ServicioUsuarioLogeado() {
        // TODO Auto-generated constructor stub
    }
    
    public Usuario getUser() {
	    System.out.println("Username in EJB "+context.getCallerPrincipal().getName());
	    String email = context.getCallerPrincipal().getName();
	    Usuario usuario = em.find(Usuario.class, email);
    	return usuario;
    }
    
    
    public Grupo getGroup() {
	    String email = context.getCallerPrincipal().getName();
	    String consulta = "select g from Grupo g where g.usuario.email like :email";
	    Query queryConsulta = em.createQuery(consulta);
	    queryConsulta.setParameter("email", email);
	    Grupo grupo = (Grupo) queryConsulta.getSingleResult();
	    return grupo;
    }
    
    public Usuario updateUser(Usuario u) {
    	em.merge(u);
    	System.out.println(u);
    	return u;
    }
     
    // Fin parte perfil Usuario
    
    // *** Parte Incidencias ***
    
    @SuppressWarnings("unchecked")
    public List<Departamento> addDepartamentos() {
    	Query queryConsulta = em.createNamedQuery("Departamento.findAll");
		List<Departamento> listaDepartamentos = (List<Departamento>) queryConsulta.getResultList();
    	return listaDepartamentos;
    }
    
    
    @SuppressWarnings("unchecked")
	public List<Prioridad> addPrioridades(){
    	Query queryConsulta = em.createNamedQuery("Prioridad.findAll");
    	List<Prioridad> listaPrioridades = (List<Prioridad>) queryConsulta.getResultList();
    	return listaPrioridades;
    }
    
    // *** Crear incidencia ***
    public void crearIncidencia(Incidencia nuevaIncidencia,Comentario nuevoComentario, long idprioridad, long iddepartamento){
    	
    	String consunltaCodigoIncidencia = "SELECT S_INCIDENCIA.NEXTVAL AS idincidencia FROM DUAL";
    	Query queryCodigoIncidencia = em.createNativeQuery(consunltaCodigoIncidencia);
    	long idincidencia = Long.parseLong(queryCodigoIncidencia.getSingleResult().toString());
    	/* *** 																	*** */
    	String consunltaCodigoComentario = "SELECT S_COMENTARIO.NEXTVAL AS idcomentario FROM DUAL";
    	Query queryCodigoComentario = em.createNativeQuery(consunltaCodigoComentario);
    	long idcomentario = Long.parseLong(queryCodigoComentario.getSingleResult().toString());
    	/* *** 	                                                               *** */
    	String consunltaCodigoLineadetalle = "SELECT S_LINEADETALLE.NEXTVAL AS idlineadetalle FROM DUAL";
    	Query queryCodigoLineadetalle = em.createNativeQuery(consunltaCodigoLineadetalle);
    	long idlineadetalle = Long.parseLong(queryCodigoLineadetalle.getSingleResult().toString());
    	
    																	
    	
    	
    	
    	/* Crear log file */
    	
    	/*

		try {
	    	LogManager.getLogManager().reset();
	    	log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	    	log.setLevel(Level.ALL);
	    	FileHandler file;
			file = new FileHandler("C:\\Users\\Nick\\Documents\\incidencias\\info.txt");
	    	file.setFormatter(new SimpleFormatter());
	    	file.setLevel(Level.INFO);
	    	log.addHandler(file);
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			*/
    	/*
    	System.out.println("Idincidencia "+ idincidencia);
    	System.out.println("Idcomentario "+ idcomentario);
    	System.out.println("IdLineaDetalle "+ idlineadetalle);
    	System.out.println("Comentario: "+ nuevoComentario.getComentario());
    	System.out.println("idprioridad: "+ idprioridad);*/
    	

    	Prioridad prioridad = em.find(Prioridad.class, idprioridad);
    	nuevaIncidencia.setPrioridad(prioridad);
    	
    	Departamento departamento = em.find(Departamento.class, iddepartamento);
    	nuevaIncidencia.setDepartamento(departamento);
    	
    	Estadoincidencia estado = em.find(Estadoincidencia.class, 1L);
    	nuevaIncidencia.setEstadoincidencia(estado);
    	
    	String consultaFecha = "SELECT (SYSDATE) FROM DUAL";
    	Query queryFecha = em.createNativeQuery(consultaFecha);
    	Date fecha = (Date) queryFecha.getSingleResult();
    	
    	nuevaIncidencia.setFechaCreacion(fecha);
    	nuevaIncidencia.setFechaModificacion(fecha);
    	nuevaIncidencia.setIdincidencia(idincidencia);
    	
    	nuevoComentario.setFechaComentario(fecha);
    	nuevoComentario.setIdcomentario(idcomentario);
    	nuevoComentario.setIncidencia(nuevaIncidencia);
    	nuevoComentario.setUsuario(nuevaIncidencia.getUsuario());
    	
    	
   

    	String maxComentariosConsulta = "select max(c.idcomentario)  from Comentario c where c.incidencia.idincidencia like :idincidencia";
    	Query queryMaxComentariosConsulta = em.createNativeQuery(maxComentariosConsulta);
    	queryMaxComentariosConsulta.setParameter("idincidencia", nuevaIncidencia.getIdincidencia());
    	Integer maxComentarios = (Integer) queryMaxComentariosConsulta.getFirstResult();
    	/**************************/
    	
    	Lineadetalleincidencia nuevalineadetalleincidencia = new Lineadetalleincidencia();
    	nuevalineadetalleincidencia.setEstadoincidencia(estado);
    	LineadetalleincidenciaPK ids = new LineadetalleincidenciaPK();
    	ids.setIdincidencia(idincidencia);
    	ids.setIdLineaDetalle(idlineadetalle);
    	nuevalineadetalleincidencia.setId(ids);
    	nuevalineadetalleincidencia.setIncidencia(nuevaIncidencia);
    	if(maxComentarios == 0) {
    		maxComentarios = 1;
    	}
    	
    	nuevalineadetalleincidencia.setMaximoComentarios(maxComentarios);
    	nuevalineadetalleincidencia.setTotalLineaDetalle(1);
    	
    	
    	
    /* Mostrar los logs */	
    	
     /*	System.out.println();
    	log.info(("Idincidencia "+ nuevaIncidencia.getIdincidencia()));
    	System.out.println();
    	log.info(("Idcomentario "+ nuevoComentario.getIdcomentario()));
    	System.out.println();
    	//log.info(("IdLineaDetalle "+ idLineadetalle.getIdincidencia()));
    	System.out.println();
    	log.info(("Comentario: "+ nuevoComentario.getComentario()));
    	log.info(("MaxComentarios: "+ nuevalineadetalleincidencia.getMaximoComentarios()));
    	System.out.println();
    	log.info(("idprioridad: "+ idprioridad));
    	log.info(("Fecha : "+ nuevaIncidencia.getFechaCreacion()));
    	log.info(("Prioridad Tipo : "+ prioridad.getTipo()));
    	
    	log.info(("Comentario : "+ nuevoComentario.getComentario() + "\n" + nuevoComentario.getIdcomentario() + "\n"
    			+ nuevoComentario.getUsuario().getNombre()));
    	
    	*/
    	
    	em.persist(nuevaIncidencia);
    	em.persist(nuevoComentario);
    	em.persist(nuevalineadetalleincidencia);
    	
    	
    }
    
    // *** Fin Crear incidencia ***
    
    // *** Listar incidencias ***
    
   public Departamento getDepartamento(String rol, String email) {
	   /*if(rol.equals("superadmin") || rol.equals("manager")) {
		   rol = "%";
		   email = "%";
	   }*/
	   Departamento departamento = new Departamento();
	  try {
		   String consulta = "select li.incidencia.departamento from Lineadetalleincidencia li where "
			   		+ "li.incidencia.usuario in (select g.usuario from Grupo g where "
					   		+"g.usuario.email like :email " 
					   		+"and g.rol.idrol like :rol) and li.incidencia.departamento is not null";	
			   
			   Query queryconsulta = em.createQuery(consulta);
			   queryconsulta.setParameter("email", email);
			   queryconsulta.setParameter("rol", rol);
			   departamento = (Departamento) queryconsulta.getSingleResult();
	} catch (javax.persistence.NoResultException e) {
		// TODO: handle exception
	}
	  


	   
	   return departamento;
   }
    
   @SuppressWarnings("unchecked")
   public List<Incidencia> incidenciasEnRango(int primerResultado, int maxResultados, String rol, String email, String iddepartamento){
	   
	   
	   if(rol.equals("superadmin") || rol.equals("manager")) {
		   rol = "%";
		   email = "%";
		   iddepartamento = "%";
	   }
	   
	   if(rol.equals("técnico")) {
		   email = "%";
	   }
	   
	   if(rol.equals("user")){
		   iddepartamento = "%";
	   }
	   
	   
	   String consulta = "select li.incidencia from Lineadetalleincidencia li where "
			   +"li.incidencia.usuario "
					   + "in (select g.usuario from Grupo g where  g.rol.idrol like :rol "
					   + "and g.usuario.email like :email) "
			   +"and li.incidencia.departamento.iddepartamento like :iddepartamento";
	   
	   Query queryConsulta = em.createQuery(consulta);
	   queryConsulta.setParameter("rol", rol);
	   queryConsulta.setParameter("email", email);
	   queryConsulta.setParameter("iddepartamento", iddepartamento);
	   
	   queryConsulta.setFirstResult(primerResultado);
	   queryConsulta.setMaxResults(maxResultados);
	   List<Incidencia> listaIncidencias = (List<Incidencia>) queryConsulta.getResultList();
	   return listaIncidencias;
   }
   
   public long getTolalIncidencias(String rol, String email, String iddepartamento) {
	   
	   if(rol.equals("superadmin") || rol.equals("manager")) {
		   rol = "%";
		   email = "%";
		   iddepartamento = "%";
	   }
	   
	   if(rol.equals("técnico")) {
		   email = "%";
	   }
	   
	   if(rol.equals("user")){
		   iddepartamento = "%";
	   }
	   
	   
	   String consulta = "select count(li.incidencia) from Lineadetalleincidencia li where "
			   +"li.incidencia.usuario "
					   + "in (select g.usuario from Grupo g where  g.rol.idrol like :rol "
					   + "and g.usuario.email like :email) "
			   +"and li.incidencia.departamento.iddepartamento like :iddepartamento";
	   
	   Query queryConsulta = em.createQuery(consulta);
	   queryConsulta.setParameter("rol", rol);
	   queryConsulta.setParameter("email", email);
	   queryConsulta.setParameter("iddepartamento", iddepartamento);
	   
	   return (Long) queryConsulta.getSingleResult();
   }
    
}
