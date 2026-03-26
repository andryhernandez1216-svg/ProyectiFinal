package Ligca;

import java.util.ArrayList;

public class SistemaAltice {

	private ArrayList<Cliente> clientes;
    private ArrayList<Empleado> empleados;
    private ArrayList<Plan> planes;
    private ArrayList<Contrato> contratos;
    private ArrayList<Pago> pagos;
    private ArrayList<Servicio> servicios;
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
	public ArrayList<Servicio> getServicios() {
		return servicios;
	}
	public void setServicios(ArrayList<Servicio> servicios) {
		this.servicios = servicios;
	}
	public SistemaAltice(ArrayList<Cliente> clientes, ArrayList<Empleado> empleados, ArrayList<Plan> planes,
			ArrayList<Contrato> contratos, ArrayList<Pago> pagos, ArrayList<Servicio> servicios) {
		super();
		this.clientes = clientes;
		this.empleados = empleados;
		this.planes = planes;
		this.contratos = contratos;
		this.pagos = pagos;
		this.servicios = servicios;
	}
    
	
}
