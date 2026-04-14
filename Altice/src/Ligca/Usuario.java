package Ligca;

import java.io.Serializable;
import java.util.Date;


public class Usuario extends Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    private String rol;
    private String password;

    public Usuario(String cedula, String nombre, String apellido, String telefono, String email,
                   String direccion, Date fechaRegistro, String rol, String password) {
        super(cedula != null ? cedula.trim() : "", 
              nombre != null ? nombre.trim() : "", 
              apellido != null ? apellido.trim() : "", 
              telefono, email, direccion, fechaRegistro);
        
        try {
            setRol(rol);
            this.password = (password != null) ? password.trim() : "";
        } catch (Exception e) {
            this.rol = "TRABAJADOR"; 
            throw new IllegalArgumentException("Error al crear usuario: " + e.getMessage());
        }
    }


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


    public boolean esAdministrativo() {
        return "ADMINISTRATIVO".equals(this.rol);
    }

    public boolean esComercial() {
        return "COMERCIAL".equals(this.rol);
    }

    public boolean esTrabajador() {
        return "TRABAJADOR".equals(this.rol);
    }


    @Override
    public String toString() {
        return String.format("%s %s [%s]", getNombre(), getApellido(), rol);
    }
}
