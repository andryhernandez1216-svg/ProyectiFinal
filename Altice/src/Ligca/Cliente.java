package Ligca;

import java.util.Date;

public class Cliente extends Persona {

	private String codigoCliente;
	private String tipoCliente;
	private boolean estado;
	private float deuda;
	private int cantDePagosAtrasados;
	
	public String getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public String getTipoCliente() {
		return tipoCliente;
	}
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public float getDeuda() {
		return deuda;
	}
	public void setDeuda(float deuda) {
		this.deuda = deuda;
	}
	public int getCantDePagosAtrasados() {
		return cantDePagosAtrasados;
	}
	public void setCantDePagosAtrasados(int cantDePagosAtrasados) {
		this.cantDePagosAtrasados = cantDePagosAtrasados;
	}
	public Cliente(String id, String cedula, String nombre, String apellido, String telefono, String email,
			String direccion, Date fechaRegistro, String codigoCliente, String tipoCliente, boolean estado, float deuda,
			int cantDePagosAtrasados) {
		super(id, cedula, nombre, apellido, telefono, email, direccion, fechaRegistro);
		this.codigoCliente = codigoCliente;
		this.tipoCliente = tipoCliente;
		this.estado = true;
		this.deuda = deuda;
		this.cantDePagosAtrasados = cantDePagosAtrasados;
	}
	
	

	
}
