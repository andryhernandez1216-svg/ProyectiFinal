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
	
	public ArrayList<Empleado> getEmpleados() {
		return empleados;
	}
	
	public ArrayList<Plan> getPlanes() {
		return planes;
	}
	
	public ArrayList<Contrato> getContratos() {
		return contratos;
	}
	
	public ArrayList<Pago> getPagos() {
		return pagos;
	}

	public ArrayList<Servicio> getServicios() {
		return servicios;
	}
	
	public SistemaAltice(ArrayList<Cliente> clientes, ArrayList<Empleado> empleados, ArrayList<Plan> planes,
			ArrayList<Contrato> contratos, ArrayList<Pago> pagos, ArrayList<Servicio> servicios) {
		super();
		this.clientes = new ArrayList<>();
		this.empleados = new ArrayList<>();
		this.planes = new ArrayList<>();
		this.contratos = new ArrayList<>();
		this.pagos = new ArrayList<>();
		this.servicios = new ArrayList<>();
	}
    
	
}
