package Ligca;

import java.io.Serializable;

public class Plan implements Serializable {

    // ID de versión para compatibilidad entre Cliente y Servidor
    private static final long serialVersionUID = 1L;

    private String idPlan;
    private String nombre;
    private String descripcion;
    private String velocidad; 
    private float precio;
    private String tipo; 
    private int duracionContrato; 
    private boolean estado;
    
    public Plan(String idPlan, String nombre, String descripcion, String velocidad, float precio, String tipo,
            int duracionContrato, boolean estado) throws Exception {

        setIdPlan(idPlan);
        setNombre(nombre);
        setDescripcion(descripcion);
        setVelocidad(velocidad);
        setPrecio(precio);
        setTipo(tipo);
        setDuracionContrato(duracionContrato);
        this.estado = estado;
    }

    // --- Getters y Setters con validaciones ---

    public String getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(String idPlan) throws Exception {
        if (idPlan == null || idPlan.trim().isEmpty()) {
            throw new Exception("El ID del plan no puede estar vacío.");
        }
        this.idPlan = idPlan.trim();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws Exception {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) throws Exception {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new Exception("La descripción no puede estar vacía.");
        }
        this.descripcion = descripcion.trim();
    }

    public String getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(String velocidad) throws Exception {
        if (velocidad == null || velocidad.trim().isEmpty()) {
            throw new Exception("La velocidad no puede estar vacía.");
        }
        this.velocidad = velocidad.trim();
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) throws Exception {
        if (precio <= 0) {
            throw new Exception("El precio debe ser mayor que 0.");
        }
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) throws Exception {
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new Exception("El tipo no puede estar vacío.");
        }
        this.tipo = tipo.trim().toUpperCase();
    }

    public int getDuracionContrato() {
        return duracionContrato;
    }

    public void setDuracionContrato(int duracionContrato) throws Exception {
        if (duracionContrato <= 0) {
            throw new Exception("La duración del contrato debe ser mayor que 0.");
        }
        this.duracionContrato = duracionContrato;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    // --- Lógica de Negocio ---

    public void activar() { this.estado = true; }
    public void desactivar() { this.estado = false; }
    public boolean estaDisponible() { return estado; }

    public void aplicarDescuento(float porcentaje) throws Exception {
        if (porcentaje <= 0 || porcentaje > 100)
            throw new Exception("Porcentaje inválido.");
        precio -= precio * (porcentaje / 100);
    }

    public float calcularPrecioAnual() {
        return precio * 12;
    }
    
    public boolean esCombo() {
        return "COMBO".equalsIgnoreCase(this.tipo);
    }

    @Override
    public String toString() {
        // Formato para que se vea bien en los ComboBox de la interfaz
        return String.format("%s - RD$%.2f (%s)", nombre, precio, velocidad);
    }
}