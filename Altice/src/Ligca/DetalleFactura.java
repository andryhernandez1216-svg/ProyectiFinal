package Ligca;

import java.io.Serializable; 

public class DetalleFactura implements Serializable {
    private static final long serialVersionUID = 1L; 
    
    private String idDetalle;
    private String descripcion;
    private int cantidad;
    private float precioUnitario;
    private float subtotal;

    public DetalleFactura(String idDetalle, String descripcion, int cantidad, float precioUnitario) {
        try {
            setIdDetalle(idDetalle);
            setDescripcion(descripcion);
            setCantidad(cantidad);
            setPrecioUnitario(precioUnitario);
            calcularSubtotal();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public String getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(String idDetalle) throws Exception {
        if (idDetalle == null || idDetalle.trim().isEmpty())
            throw new Exception("El id del detalle no puede estar vacío.");
        this.idDetalle = idDetalle;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) throws Exception {
        if (descripcion == null || descripcion.trim().isEmpty())
            throw new Exception("La descripción no puede estar vacía.");
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) throws Exception {
        if (cantidad <= 0)
            throw new Exception("La cantidad debe ser mayor a 0.");
        this.cantidad = cantidad;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) throws Exception {
        if (precioUnitario <= 0)
            throw new Exception("El precio debe ser mayor a 0.");
        this.precioUnitario = precioUnitario;
    }

    public float getSubtotal() {
        return subtotal;
    }


    public void calcularSubtotal() {
        subtotal = cantidad * precioUnitario;
    }

    @Override
    public String toString() {
        return descripcion +
               " | Cant: " + cantidad +
               " | Subtotal: RD$" + subtotal;
    }
}
