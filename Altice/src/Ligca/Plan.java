package Ligca;

public class Plan {

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

    public String getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(String idPlan) throws Exception {
        if (idPlan != null && !idPlan.trim().isEmpty()) {
            this.idPlan = idPlan;
        } else {
            throw new Exception("El ID del plan no puede estar vacío.");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws Exception {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        } else {
            throw new Exception("El nombre no puede estar vacío.");
        }
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) throws Exception {
        if (descripcion != null && !descripcion.trim().isEmpty()) {
            this.descripcion = descripcion;
        } else {
            throw new Exception("La descripción no puede estar vacía.");
        }
    }

    public String getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(String velocidad) throws Exception {
        if (velocidad != null && !velocidad.trim().isEmpty()) {
            this.velocidad = velocidad;
        } else {
            throw new Exception("La velocidad no puede estar vacía.");
        }
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) throws Exception {
        if (precio > 0) {
            this.precio = precio;
        } else {
            throw new Exception("El precio debe ser mayor que 0.");
        }
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) throws Exception {
        if (tipo != null && !tipo.trim().isEmpty()) {
            this.tipo = tipo;
        } else {
            throw new Exception("El tipo no puede estar vacío.");
        }
    }

    public int getDuracionContrato() {
        return duracionContrato;
    }

    public void setDuracionContrato(int duracionContrato) throws Exception {
        if (duracionContrato > 0) {
            this.duracionContrato = duracionContrato;
        } else {
            throw new Exception("La duración del contrato debe ser mayor que 0.");
        }
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }


    public void activar() {
        estado = true;
    }

    public void desactivar() {
        estado = false;
    }


    public boolean estaDisponible() {
        return estado;
    }

    public void aplicarDescuento(float porcentaje) throws Exception {
        if (porcentaje <= 0 || porcentaje > 100)
            throw new Exception("Porcentaje inválido.");

        precio -= precio * (porcentaje / 100);
    }

    public void aumentarPrecio(float porcentaje) throws Exception {
        if (porcentaje <= 0)
            throw new Exception("Porcentaje inválido.");

        precio += precio * (porcentaje / 100);
    }

    public float calcularPrecioAnual() {
        return precio * 12;
    }
    
    public boolean esCombo() {
        return tipo.equalsIgnoreCase("combo");
    }

    @Override
    public String toString() {
        return "Plan: " + nombre +
               " | ID: " + idPlan +
               " | Tipo: " + tipo +
               " | Precio: RD$" + precio +
               " | Estado: " + (estado ? "Disponible" : "No disponible");
    }
}