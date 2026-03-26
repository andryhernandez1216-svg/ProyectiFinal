package Ligca;

import java.util.Date;

public  abstract class Persona {
	
	 private String id;
	 private String cedula;
	 private String nombre;
	 private String apellido;
	 private String telefono;
	 private String email;
	 private String direccion;
	 private Date fechaRegistro;
	 
	 public Persona(String id, String cedula, String nombre, String apellido, String telefono, String email,
			String direccion, Date fechaRegistro) {
		super();
		this.id = id;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.email = email;
		this.direccion = direccion;
		this.fechaRegistro = fechaRegistro;
	 }
	 public String getId() {
		 return id;
	 }
	 public void setId(String id) {
		 this.id = id;
	 }
	 public String getCedula() {
		 return cedula;
	 }
	 public void setCedula(String cedula) {
		 this.cedula = cedula;
	 }
	 public String getNombre() {
		 return nombre;
	 }
	 public void setNombre(String nombre) {
		 this.nombre = nombre;
	 }
	 public String getApellido() {
		 return apellido;
	 }
	 public void setApellido(String apellido) {
		 this.apellido = apellido;
	 }
	 public String getTelefono() {
		 return telefono;
	 }
	 public void setTelefono(String telefono) {
		 this.telefono = telefono;
	 }
	 public String getEmail() {
		 return email;
	 }
	 public void setEmail(String email) {
		 this.email = email;
	 }
	 public String getDireccion() {
		 return direccion;
	 }
	 public void setDireccion(String direccion) {
		 this.direccion = direccion;
	 }
	 public Date getFechaRegistro() {
		 return fechaRegistro;
	 }
	 public void setFechaRegistro(Date fechaRegistro) {
		 this.fechaRegistro = fechaRegistro;
	 }

}
