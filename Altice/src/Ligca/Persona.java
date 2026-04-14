package Ligca;

import java.io.Serializable;
import java.util.Date;

public abstract class Persona implements Serializable {

    private static final long serialVersionUID = 1L; 
    
    private String id;
    private String cedula;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String direccion;
    private Date fechaRegistro;
    
    // Eliminamos el contador estático aquí si vamos a usar Sockets, 
    // es mejor asignarlo en el momento del registro.

    public Persona() {
        // Constructor vacío necesario para algunas librerías de serialización
    }

    public Persona(String cedula, String nombre, String apellido,
                   String telefono, String email, String direccion, Date fechaRegistro) {
        try {
            setCedula(cedula);
            setNombre(nombre);
            setApellido(apellido);
            setTelefono(telefono);
            setEmail(email);
            setDireccion(direccion);
            setFechaRegistro(fechaRegistro);
            // El ID ahora es la cédula para evitar el error del contador estático
            this.id = cedula; 
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    // --- Getters y Setters ---

    public String getId() { return id; }
    public void setId(String id) { this.id = id; } // Permitir cambiar el ID si es necesario

    public String getCedula() { return cedula; }

    public void setCedula(String cedula) throws Exception {
        if (cedula == null || cedula.trim().isEmpty())
            throw new Exception("La cédula no puede estar vacía.");
        this.cedula = cedula.trim();
    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) throws Exception {
        if (nombre == null || nombre.trim().isEmpty())
            throw new Exception("El nombre no puede estar vacío.");
        this.nombre = nombre.trim();
    }

    public String getApellido() { return apellido; }

    public void setApellido(String apellido) throws Exception {
        if (apellido == null || apellido.trim().isEmpty())
            throw new Exception("El apellido no puede estar vacío.");
        this.apellido = apellido.trim();
    }

    public String getTelefono() { return telefono; }

    public void setTelefono(String telefono) throws Exception {
        this.telefono = (telefono != null) ? telefono.trim() : "";
    }

    public String getEmail() { return email; }

    public void setEmail(String email) throws Exception {
        this.email = (email != null) ? email.trim() : "";
    }

    public String getDireccion() { return direccion; }

    public void setDireccion(String direccion) throws Exception {
        this.direccion = (direccion != null) ? direccion.trim() : "";
    }

    public Date getFechaRegistro() { return fechaRegistro; }

    public void setFechaRegistro(Date fechaRegistro) throws Exception {
        if (fechaRegistro == null)
            throw new Exception("La fecha de registro no puede ser nula.");
        this.fechaRegistro = fechaRegistro;
    }

    // --- Métodos de Ayuda ---

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | " + nombre + " " + apellido + " | Cédula: " + cedula;
    }
}
