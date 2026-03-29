package Ligca;

import java.util.ArrayList;
import java.util.Date;

public class Factura {

    private String idFactura;
    private Cliente cliente;
    private Contrato contrato;
    private Date fecha;
    private ArrayList<DetalleFactura> detalles;
    private float subtotal;
    private float itbis;
    private float total;
    private boolean estado;

    public Factura(String idFactura, Cliente cliente, Contrato contrato, Date fecha) {
        try {
            setIdFactura(idFactura);
            setCliente(cliente);
            setContrato(contrato);
            setFecha(fecha);
            this.detalles = new ArrayList<>();
            this.estado = false;
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public String getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(String idFactura) throws Exception {
        if (idFactura == null || idFactura.trim().isEmpty())
            throw new Exception("El id de la factura no puede estar vacío.");
        this.idFactura = idFactura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) throws Exception {
        if (cliente == null)
            throw new Exception("La factura debe tener un cliente.");
        this.cliente = cliente;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) throws Exception {
        if (contrato == null)
            throw new Exception("La factura debe tener un contrato.");
        this.contrato = contrato;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) throws Exception {
        if (fecha == null)
            throw new Exception("La fecha no puede ser nula.");
        this.fecha = fecha;
    }

    public float getTotal() {
        return total;
    }

    public boolean isEstado() {
        return estado;
    }


    public void agregarDetalle(DetalleFactura d) {
        detalles.add(d);
        calcularTotales();
    }

    public void calcularTotales() {
        subtotal = 0;

        for (DetalleFactura d : detalles) {
            subtotal += d.getSubtotal();
        }

        itbis = subtotal * 0.18f;
        total = subtotal + itbis;
    }

    public void marcarPagada() {
        estado = true;
    }

    public void marcarPendiente() {
        estado = false;
    }

    @Override
    public String toString() {
        return "Factura: " + idFactura +
               " | Cliente: " + cliente.getDatos() +
               " | Total: RD$" + total +
               " | Estado: " + (estado ? "Pagada" : "Pendiente");
    }
}