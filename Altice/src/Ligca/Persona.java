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

	public Persona(String id, String cedula, String nombre, String apellido,
			String telefono, String email, String direccion, Date fechaRegistro) {
		try {
			setId(id);
			setCedula(cedula);
			setNombre(nombre);
			setApellido(apellido);
			setTelefono(telefono);
			setEmail(email);
			setDireccion(direccion);
			setFechaRegistro(fechaRegistro);
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	public String getId() {
		return id;
	}
	public void setId(String id) throws Exception {
        if (id == null || id.trim().isEmpty())
            throw new Exception("El id no puede estar vacio.");
        this.id = id;
    }
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) throws Exception {
        if (cedula == null || cedula.trim().isEmpty())
            throw new Exception("La cedula no puede estar vacia.");
        this.cedula = cedula;
    }
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) throws Exception {
        if (nombre == null || nombre.trim().isEmpty())
            throw new Exception("El nombre no puede estar vacio.");
        this.nombre = nombre;
    }
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) throws Exception {
        if (apellido == null || apellido.trim().isEmpty())
            throw new Exception("El apellido no puede estar vacio.");
        this.apellido = apellido;
    }
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) throws Exception {
        if (telefono == null || telefono.trim().isEmpty())
            throw new Exception("El telefono no puede estar vacio.");
        this.telefono = telefono;
    }
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) throws Exception {
        if (email == null || email.trim().isEmpty())
            throw new Exception("El email no puede estar vacio.");
        this.email = email;
    }
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) throws Exception {
        if (direccion == null || direccion.trim().isEmpty())
            throw new Exception("La direccion no puede estar vacia.");
        this.direccion = direccion;
    }
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) throws Exception {
        if (fechaRegistro == null)
            throw new Exception("La fecha de registro no puede ser nula.");
        this.fechaRegistro = fechaRegistro;
    }
	
	

}
