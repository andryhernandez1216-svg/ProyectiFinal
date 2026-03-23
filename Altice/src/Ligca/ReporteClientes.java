package Ligca;

import java.util.ArrayList;

public class ReporteClientes extends Reporte {

	private ArrayList<Cliente> clientes;
    private Local local;
	public ReporteClientes(String titulo, String periodo, ArrayList<Cliente> clientes, Local local) {
		super(titulo, periodo);
		this.clientes = clientes;
		this.local = local;
	}
	public ArrayList<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}
	public Local getLocal() {
		return local;
	}
	public void setLocal(Local local) {
		this.local = local;
	}
	
}
