package Ligca;

import java.util.ArrayList;

public class Local {

	private String idLocal;
    private String nombre;
    private String direccion;
    private String telefono;
    private String ciudad;
    private String provincia;
    private Administrativo gerente;
    private String estado;
    private ArrayList<Empleado> personal;
	public Local(String idLocal, String nombre, String direccion, String telefono, String ciudad, String provincia,
			Administrativo gerente, String estado, ArrayList<Empleado> personal) {
		super();
		this.idLocal = idLocal;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.ciudad = ciudad;
		this.provincia = provincia;
		this.gerente = gerente;
		this.estado = estado;
		this.personal = personal;
	}
	public String getIdLocal() {
		return idLocal;
	}
	public void setIdLocal(String idLocal) {
		this.idLocal = idLocal;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public Administrativo getGerente() {
		return gerente;
	}
	public void setGerente(Administrativo gerente) {
		this.gerente = gerente;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public ArrayList<Empleado> getPersonal() {
		return personal;
	}
	public void setPersonal(ArrayList<Empleado> personal) {
		this.personal = personal;
	}
}
