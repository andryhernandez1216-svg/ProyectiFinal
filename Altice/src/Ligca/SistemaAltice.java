package Ligca;

import java.util.ArrayList;

public class SistemaAltice {

	private ArrayList<Cliente> clientes;
    private ArrayList<Empleado> empleados;
    private ArrayList<Plan> planes;
    private ArrayList<Factura> facturas;
    private ArrayList<Contrato> contratos;
    private ArrayList<Pago> pagos;
    private ArrayList<Local> locales;
    private ArrayList<Servicio> servicios;
	public SistemaAltice(ArrayList<Cliente> clientes, ArrayList<Empleado> empleados, ArrayList<Plan> planes,
			ArrayList<Factura> facturas, ArrayList<Contrato> contratos, ArrayList<Pago> pagos, ArrayList<Local> locales,
			ArrayList<Servicio> servicios) {
		super();
		this.clientes = clientes;
		this.empleados = empleados;
		this.planes = planes;
		this.facturas = facturas;
		this.contratos = contratos;
		this.pagos = pagos;
		this.locales = locales;
		this.servicios = servicios;
	}
	public ArrayList<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}
	public ArrayList<Empleado> getEmpleados() {
		return empleados;
	}
	public void setEmpleados(ArrayList<Empleado> empleados) {
		this.empleados = empleados;
	}
	public ArrayList<Plan> getPlanes() {
		return planes;
	}
	public void setPlanes(ArrayList<Plan> planes) {
		this.planes = planes;
	}
	public ArrayList<Factura> getFacturas() {
		return facturas;
	}
	public void setFacturas(ArrayList<Factura> facturas) {
		this.facturas = facturas;
	}
	public ArrayList<Contrato> getContratos() {
		return contratos;
	}
	public void setContratos(ArrayList<Contrato> contratos) {
		this.contratos = contratos;
	}
	public ArrayList<Pago> getPagos() {
		return pagos;
	}
	public void setPagos(ArrayList<Pago> pagos) {
		this.pagos = pagos;
	}
	public ArrayList<Local> getLocales() {
		return locales;
	}
	public void setLocales(ArrayList<Local> locales) {
		this.locales = locales;
	}
	public ArrayList<Servicio> getServicios() {
		return servicios;
	}
	public void setServicios(ArrayList<Servicio> servicios) {
		this.servicios = servicios;
	}
}
