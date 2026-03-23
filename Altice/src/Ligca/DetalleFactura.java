package Ligca;

public class DetalleFactura {

	private String idDetalle;
	private String concepto;
	private int cantidad;
	private double precioUnitario;
	private double subtotal;
	public DetalleFactura(String idDetalle, String concepto, int cantidad, double precioUnitario, double subtotal) {
		super();
		this.idDetalle = idDetalle;
		this.concepto = concepto;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
		this.subtotal = subtotal;
	}
	public String getIdDetalle() {
		return idDetalle;
	}
	public void setIdDetalle(String idDetalle) {
		this.idDetalle = idDetalle;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
}
