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
        this.idFactura = idFactura;
        this.cliente = cliente;
        this.contrato = contrato;
        this.fecha = fecha;
        this.detalles = new ArrayList<>();
        this.estado = false;
    }

    // Métodos necesarios para el Panel de Reportes
    public String getIdFactura() { return idFactura; }
    public Cliente getCliente() { return cliente; }
    public Date getFecha() { return fecha; }
    public float getMontoTotal() { return total; } // Este es el que pedía el reporte
    public boolean isEstado() { return estado; }

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


    @Override
    public String toString() {
        return "Factura: " + idFactura + " | Total: RD$" + total;
    }
}