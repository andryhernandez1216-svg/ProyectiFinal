package Ligca;

import java.util.ArrayList;
import java.util.Date;

public class Factura {
	public static final String PENDIENTE = "pendiente";
    public static final String PAGADA = "pagada";
    public static final String VENCIDA = "vencida";

    private String numeroFactura;
    private Cliente cliente;
    private Date fechaEmision;
    private Date fechaVencimiento;
    private ArrayList<DetalleFactura> detalles;

    private double total;
    private double saldoPendiente;
    private String estado;
	public Factura(String numeroFactura, Cliente cliente, Date fechaEmision, Date fechaVencimiento,
			ArrayList<DetalleFactura> detalles, double total, double saldoPendiente, String estado) {
		super();
		this.numeroFactura = numeroFactura;
		this.cliente = cliente;
		this.fechaEmision = fechaEmision;
		this.fechaVencimiento = fechaVencimiento;
		this.detalles = detalles;
		this.total = total;
		this.saldoPendiente = saldoPendiente;
		this.estado = estado;
	}
	public String getNumeroFactura() {
		return numeroFactura;
	}
	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public ArrayList<DetalleFactura> getDetalles() {
		return detalles;
	}
	public void setDetalles(ArrayList<DetalleFactura> detalles) {
		this.detalles = detalles;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getSaldoPendiente() {
		return saldoPendiente;
	}
	public void setSaldoPendiente(double saldoPendiente) {
		this.saldoPendiente = saldoPendiente;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public static String getPendiente() {
		return PENDIENTE;
	}
	public static String getPagada() {
		return PAGADA;
	}
	public static String getVencida() {
		return VENCIDA;
	}

}
