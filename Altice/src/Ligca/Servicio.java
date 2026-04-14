package Ligca;

import java.io.Serializable;

public class Servicio implements Serializable {

    // Identificador de versión para la comunicación entre Cliente y Servidor
    private static final long serialVersionUID = 1L;

    private String idServicio;
    private String nombre;
    private String descripcion;
    private float precio;
    private String tipo; 
    private boolean estado;

    public Servicio(String idServicio, String nombre, String descripcion, float precio, String tipo, boolean estado) {
        try {
            setIdServicio(idServicio);
            setNombre(nombre);
            setDescripcion(descripcion);
            setTipo(tipo);
            setPrecio(precio);
            this.estado = estado;
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    // --- Getters y Setters con validaciones ---

    public String getIdServicio() { return idServicio; }

    public void setIdServicio(String idServicio) throws Exception {
        if (idServicio == null || idServicio.trim().isEmpty())
            throw new Exception("El ID del servicio no puede estar vacío.");
        this.idServicio = idServicio.trim();
    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) throws Exception {
        if (nombre == null || nombre.trim().isEmpty())
            throw new Exception("El nombre del servicio no puede estar vacío.");
        this.nombre = nombre.trim();
    }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion) throws Exception {
        if (descripcion == null || descripcion.trim().isEmpty())
            throw new Exception("La descripción del servicio no puede estar vacía.");
        this.descripcion = descripcion.trim();
    }

    public float getPrecio() { return precio; }

    public void setPrecio(float precio) throws Exception {
        if (precio <= 0)
            throw new Exception("El precio del servicio debe ser mayor a 0.");
        this.precio = precio;
    }

    public String getTipo() { return tipo; }

    public void setTipo(String tipo) throws Exception {
        if (tipo == null || tipo.trim().isEmpty())
            throw new Exception("El tipo del servicio no puede estar vacío.");
        this.tipo = tipo.trim().toUpperCase();
    }

    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }

    // --- Lógica de Negocio ---

    public void activar() { this.estado = true; }
    public void desactivar() { this.estado = false; }
    public boolean estaDisponible() { return estado; }

    public void aplicarDescuento(float porcentaje) throws Exception {
        if (porcentaje <= 0 || porcentaje > 100)
            throw new Exception("Porcentaje inválido.");
        this.precio -= this.precio * (porcentaje / 100);
    }

    @Override
    public String toString() {
        // Formato para JComboBox en la interfaz de Contratos
        return String.format("%s (RD$%.2f)", nombre, precio);
    }
}
