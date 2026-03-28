package Ligca;

import java.util.Date;

public class Pago {

    private String idPago;
    private String idContrato;
    private float monto;
    private Date fecha;
    private boolean pagado;

    public String getIdPago() {
        return idPago;
    }

    public void setIdPago(String idPago) throws Exception {
        if (idPago == null || idPago.trim().isEmpty())
            throw new Exception("El id del pago no puede estar vacío.");
        this.idPago = idPago;
    }

    public String getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(String idContrato) throws Exception {
        if (idContrato == null || idContrato.trim().isEmpty())
            throw new Exception("El id del contrato no puede estar vacío.");
        this.idContrato = idContrato;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) throws Exception {
        if (monto <= 0)
            throw new Exception("El monto debe ser mayor a 0.");
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) throws Exception {
        if (fecha == null)
            throw new Exception("La fecha no puede ser nula.");
        this.fecha = fecha;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public Pago(String idPago, String idContrato, float monto, Date fecha, boolean pagado) {
        try {
            setIdPago(idPago);
            setIdContrato(idContrato);
            setMonto(monto);
            setFecha(fecha);
            this.pagado = pagado; 
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void realizarPago() {
        this.pagado = true;
    }

    public void marcarPendiente() {
        this.pagado = false;
    }

    public boolean estaPagado() {
        return pagado;
    }

    public boolean esValido() {
        return monto > 0 && idContrato != null && fecha != null;
    }

    public float aplicarPago(float deudaActual) {
        if (!pagado) return deudaActual;

        deudaActual -= monto;

        if (deudaActual < 0)
            deudaActual = 0;

        return deudaActual;
    }

    @Override
    public String toString() {
        return "Pago ID: " + idPago +
               " | Contrato: " + idContrato +
               " | Monto: RD$" + monto +
               " | Fecha: " + fecha +
               " | Estado: " + (pagado ? "Pagado" : "Pendiente");
    }
}
