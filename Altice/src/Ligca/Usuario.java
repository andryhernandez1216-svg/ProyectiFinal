package Ligca;

import java.util.Date;

public class Usuario extends Persona {
    
    private String rol;

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) throws Exception {
        if (rol == null || rol.trim().isEmpty())
            throw new Exception("El rol no puede estar vacio.");

        if (!rol.equalsIgnoreCase("ADMINISTRATIVO") &&
            !rol.equalsIgnoreCase("COMERCIAL") &&
            !rol.equalsIgnoreCase("TRABAJADOR"))
            throw new Exception("Rol invalido. Use: ADMINISTRATIVO, COMERCIAL o TRABAJADOR.");

        this.rol = rol.toUpperCase();
    }

    public Usuario(String id, String cedula, String nombre, String apellido, String telefono, String email,
                   String direccion, Date fechaRegistro, String rol) {
        super(id, cedula, nombre, apellido, telefono, email, direccion, fechaRegistro);
        try {
            setRol(rol);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }


    public boolean esAdministrativo() {
        return rol.equals("ADMINISTRATIVO");
    }

    public boolean esComercial() {
        return rol.equals("COMERCIAL");
    }

    public boolean esTrabajador() {
        return rol.equals("TRABAJADOR");
    }

    public boolean puedeGestionarClientes() {
        return esAdministrativo() || esComercial();
    }

    public boolean puedeGestionarFacturacion() {
        return esAdministrativo();
    }

    public boolean puedeGestionarServicios() {
        return esAdministrativo() || esTrabajador();
    }

    public void cambiarRol(String nuevoRol) throws Exception {
        setRol(nuevoRol);
    }

    public boolean tieneAccesoTotal() {
        return esAdministrativo();
    }

    @Override
    public String toString() {
        return "Usuario: " + getDatos() +
               " | Rol: " + rol;
    }
}
