package Ligca;

import java.util.Date;

public class Pago {
	private String idPago;
    private Factura factura;
    private double monto;
    private Date fecha;
	public Pago(String idPago, Factura factura, double monto, Date fecha) {
		super();
		this.idPago = idPago;
		this.factura = factura;
		this.monto = monto;
		this.fecha = fecha;
	}
	public String getIdPago() {
		return idPago;
	}
	public void setIdPago(String idPago) {
		this.idPago = idPago;
	}
	public Factura getFactura() {
		return factura;
	}
	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
