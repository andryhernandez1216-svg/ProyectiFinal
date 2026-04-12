package Ligca;

import java.io.Serializable; // Agregado
import java.util.Date;

public abstract class Persona implements Serializable { // Agregado Serializable

    private static final long serialVersionUID = 1L; // Agregado para compatibilidad
    private String id;
    private String cedula;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String direccion;
    private Date fechaRegistro;
    private static int contadorId = 1;

    // --- AGREGADO: Constructor vacío para corregir el error de la consola ---
    public Persona() {
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
            this.id = String.valueOf(contadorId++);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public String getId() {
        return id;
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

    public String getDatos() {
        return nombre + " " + apellido;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public boolean tieneEmail() {
        return email != null && !email.trim().isEmpty();
    }

    public boolean tieneTelefono() {
        return telefono != null && !telefono.trim().isEmpty();
    }

    public void actualizarContacto(String telefono, String email, String direccion) throws Exception {
        setTelefono(telefono);
        setEmail(email);
        setDireccion(direccion);
    }

    @Override
    public String toString() {
        return "ID: " + id +
               " | Nombre: " + nombre + " " + apellido +
               " | Cédula: " + cedula +
               " | Tel: " + telefono;
    }
}
