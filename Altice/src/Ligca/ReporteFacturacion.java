package Ligca;

import java.util.ArrayList;

public class ReporteFacturacion extends Reporte {

	private ArrayList<Factura> facturas;
	private Local local;
	public ReporteFacturacion(String titulo, String periodo, ArrayList<Factura> facturas, Local local) {
		super(titulo, periodo);
		this.facturas = facturas;
		this.local = local;
	}
	public ArrayList<Factura> getFacturas() {
		return facturas;
	}
	public void setFacturas(ArrayList<Factura> facturas) {
		this.facturas = facturas;
	}
	public Local getLocal() {
		return local;
	}
	public void setLocal(Local local) {
		this.local = local;
	} 
}
