package backing;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import entidades.Departamento;
import entidades.Grupo;
import entidades.Rol;
import entidades.Usuario;
import servicios.ServicioCrearUsuario;
import javax.ejb.EJBException;
import javax.persistence.OptimisticLockException;

@Named
@ViewScoped
public class backingCrearUsuario implements Serializable{


	private static final long serialVersionUID = 1L;
	@EJB
	private ServicioCrearUsuario servicioCrearUsuarios;
	private List<Rol> listaPerfil;
	private String idperfil;
	private List<Departamento> listaDepartamento;
	private Long iddepartamento;
	
	private Usuario usuario;

	private String email;
	private String passwd;
	private String apellido1;
	private String apellido2;
	private String nombre;
	private String tel;
	private String rol;
	
	public backingCrearUsuario() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	public void init() {
		this.listaPerfil = servicioCrearUsuarios.listaPerfiles();
	}

	public List<Rol> getListaPerfil() {
		return listaPerfil;
	}

	public void setListaPerfil(List<Rol> listaPerfil) {
		this.listaPerfil = listaPerfil;
	}


	public String getIdperfil() {
		return idperfil;
	}

	public void setIdperfil(String idperfil) {
		this.idperfil = idperfil;
	}

	public List<Departamento> getListaDepartamento() {
		return listaDepartamento;
	}

	public void setListaDepartamento(List<Departamento> listaDepartamento) {
		this.listaDepartamento = listaDepartamento;
	}

	public Long getIddepartamento() {
		return iddepartamento;
	}

	public void setIddepartamento(Long iddepartamento) {
		this.iddepartamento = iddepartamento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
// atributos

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	
	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	// fin atributos

	public void ajaxListaDepartamento() {
		this.listaDepartamento = servicioCrearUsuarios.listaDepartamento(this.idperfil);
		if(this.listaDepartamento == null) {
			this.iddepartamento = null;
		}
		System.out.print("Lista departamento: " +this.listaDepartamento);
		System.out.print("Perfil: " + this.idperfil);
		System.out.print("Departamento: " + this.iddepartamento);

	}
	
	
	public void nuevoUsuario() {
		try {
		usuario = new Usuario();
		usuario.setEmail(this.email);
		String password = encriptacion.Md5Hash.rMd5Hash(this.passwd);
		usuario.setPassword(password);
		usuario.setApellido1(this.apellido1);
		usuario.setApellido2(this.apellido2);
		usuario.setNombre(this.nombre);
		usuario.setTel(this.tel);
		
		servicioCrearUsuarios.nuevoUsuario(usuario, this.iddepartamento);
		servicioCrearUsuarios.nuevoGrupo(this.idperfil);
		System.out.println("A ver si espabilas");
		System.out.println("Usuario: " + usuario.getNombre());
		FacesContext context=FacesContext.getCurrentInstance();
		ResourceBundle archivomensajes=ResourceBundle.getBundle("resources.application",context.getViewRoot().getLocale());
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,archivomensajes.getString("generico.registroCreadoConExito"),archivomensajes.getString("generico.registroCreadoConExito")));
		System.out.println("Antes de llamar a prepararCrearAutor por tanto debe haber nueva instancia");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	}

	
}
