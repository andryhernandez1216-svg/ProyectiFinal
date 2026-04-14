package Ligca;

import java.io.Serializable;
import java.util.Date;

/**
 * Clase Cliente que extiende de Persona.
 * Debe ser Serializable porque se envía dentro de los objetos Contrato.
 */
public class Cliente extends Persona implements Serializable {

    // ID de versión para asegurar la compatibilidad en la red
    private static final long serialVersionUID = 1L;

    private String codigoCliente;
    private String tipoCliente;
    private boolean estado;
    private float deuda;
    private int cantDePagosAtrasados;

    public Cliente(String cedula, String nombre, String apellido, String telefono, String email,
                   String direccion, Date fechaRegistro, String codigoCliente, String tipoCliente, 
                   boolean estado, float deuda, int cantDePagosAtrasados) {
        
        super(cedula, nombre, apellido, telefono, email, direccion, fechaRegistro);
        
        try {
            setCodigoCliente(codigoCliente);
            setDeuda(deuda);
            setTipoCliente(tipoCliente);
            setCantDePagosAtrasados(cantDePagosAtrasados);
            this.estado = estado;
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    // --- Getters y Setters con validaciones ---

    public String getCodigoCliente() { return codigoCliente; }

    public void setCodigoCliente(String codigoCliente) throws Exception {
        if (codigoCliente == null || codigoCliente.trim().isEmpty())
            throw new Exception("El id del cliente no puede estar vacio.");
        this.codigoCliente = codigoCliente.trim();
    }

    public String getTipoCliente() { return tipoCliente; }

    public void setTipoCliente(String tipoCliente) throws Exception {
        if (tipoCliente == null || tipoCliente.trim().isEmpty())
            throw new Exception("El tipo de cliente no puede estar vacio.");
        
        String tipo = tipoCliente.trim().toUpperCase();
        if (!tipo.equals("JURIDICO") && !tipo.equals("FISICA"))
            throw new Exception("Tipo de cliente invalido. Use: JURIDICO o FISICA.");
        
        this.tipoCliente = tipo;
    }

    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }

    public float getDeuda() { return deuda; }

    public void setDeuda(float deuda) throws Exception {
        if (deuda < 0) throw new Exception("La deuda no puede ser negativa.");
        this.deuda = deuda;
    }

    public int getCantDePagosAtrasados() { return cantDePagosAtrasados; }

    public void setCantDePagosAtrasados(int cantPagosAtrasados) throws Exception {
        if (cantPagosAtrasados < 0)
            throw new Exception("La cantidad de pagos atrasados no puede ser negativa.");
        this.cantDePagosAtrasados = cantPagosAtrasados;
    }

    // --- Lógica de Negocio ---

    public void pagarDeuda(float monto) throws Exception {
        if (monto <= 0) throw new Exception("Monto inválido.");
        this.deuda -= monto;
        if (this.deuda < 0) this.deuda = 0;
        if (this.deuda == 0) this.cantDePagosAtrasados = 0;
    }

    public boolean esMoroso() {
        return deuda > 0 && cantDePagosAtrasados > 0;
    }

    @Override
    public String toString() {
        // Formato limpio para JComboBox y reportes
        return String.format("%s %s [%s]", getNombre(), getApellido(), codigoCliente);
    }
}
