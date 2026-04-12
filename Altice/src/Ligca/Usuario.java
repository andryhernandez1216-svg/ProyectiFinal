package Ligca;

import java.io.Serializable;
import java.util.Date;

public class Usuario extends Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String rol;
    private String password;

    public Usuario(String cedula, String nombre, String apellido, String telefono, String email,
                   String direccion, Date fechaRegistro, String rol, String password) {
        // Usamos trim() en los campos clave para evitar errores de espacios
        super(cedula.trim(), nombre.trim(), apellido.trim(), telefono, email, direccion, fechaRegistro);
        try {
            setRol(rol.trim());
            this.password = password.trim(); 
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
 // --- Métodos de Lógica de Negocio ---

    public boolean esAdministrativo() {
        // Es vital que el campo 'rol' no sea nulo
        return rol != null && rol.equalsIgnoreCase("ADMINISTRATIVO");
    }

    public boolean esComercial() {
        return rol != null && rol.equalsIgnoreCase("COMERCIAL");
    }

    public boolean esTrabajador() {
        return rol != null && rol.equalsIgnoreCase("TRABAJADOR");
    }

    public String getRol() { return rol; }

    public void setRol(String rol) throws Exception {
        if (rol == null || rol.trim().isEmpty())
            throw new Exception("El rol no puede estar vacío.");

        String r = rol.trim().toUpperCase();
        if (!r.equals("ADMINISTRATIVO") && !r.equals("COMERCIAL") && !r.equals("TRABAJADOR"))
            throw new Exception("Rol inválido.");

        this.rol = r;
    }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password.trim(); }

    @Override
    public String toString() {
        return getNombre() + " " + getApellido() + " (" + rol + ")";
    }
}
