package Ligca;

import java.io.Serializable;
import java.util.Date;

/**
 * Clase Usuario que extiende de Persona. 
 * Implementa Serializable para permitir el envío por Sockets y guardado en Ficheros.
 */
public class Usuario extends Persona implements Serializable {
    // El ID debe ser el mismo en el Cliente y en el Servidor para evitar IncompatibleClassException
    private static final long serialVersionUID = 1L;
    
    private String rol;
    private String password;

    public Usuario(String cedula, String nombre, String apellido, String telefono, String email,
                   String direccion, Date fechaRegistro, String rol, String password) {
        // Pasamos los datos a la clase padre
        super(cedula != null ? cedula.trim() : "", 
              nombre != null ? nombre.trim() : "", 
              apellido != null ? apellido.trim() : "", 
              telefono, email, direccion, fechaRegistro);
        
        try {
            setRol(rol);
            this.password = (password != null) ? password.trim() : "";
        } catch (Exception e) {
            // Manejo preventivo por si el rol falla en el constructor
            this.rol = "TRABAJADOR"; 
            throw new IllegalArgumentException("Error al crear usuario: " + e.getMessage());
        }
    }

    // --- Getters y Setters con protección ---

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) throws Exception {
        if (rol == null || rol.trim().isEmpty()) {
            throw new Exception("El rol no puede estar vacío.");
        }

        String r = rol.trim().toUpperCase();
        if (!r.equals("ADMINISTRATIVO") && !r.equals("COMERCIAL") && !r.equals("TRABAJADOR")) {
            throw new Exception("Rol inválido: " + r);
        }

        this.rol = r;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = (password != null) ? password.trim() : "";
    }

    // --- Lógica de Permisos ---

    public boolean esAdministrativo() {
        return "ADMINISTRATIVO".equals(this.rol);
    }

    public boolean esComercial() {
        return "COMERCIAL".equals(this.rol);
    }

    public boolean esTrabajador() {
        return "TRABAJADOR".equals(this.rol);
    }

    // --- Métodos de utilidad ---

    @Override
    public String toString() {
        // Usamos getNombre() que viene de Persona
        return String.format("%s %s [%s]", getNombre(), getApellido(), rol);
    }
}
