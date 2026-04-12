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
	public void setCodigoCliente(String codigoCliente) throws Exception {
		if (codigoCliente == null || codigoCliente.trim().isEmpty())
			throw new Exception("El id del cliente no puede estar vacio.");
		this.codigoCliente = codigoCliente;
	}
	public String getTipoCliente() {
		return tipoCliente;
	}
	public void setTipoCliente(String tipoCliente) throws Exception {
		if (tipoCliente == null || tipoCliente.trim().isEmpty())
			throw new Exception("El tipo de cliente no puede estar vacio.");
		if (!tipoCliente.equalsIgnoreCase("JURIDICO") &&
				!tipoCliente.equalsIgnoreCase("FISICA"))
			throw new Exception("Tipo de cliente invalido. Use: JURIDICO o FISICA.");
		this.tipoCliente = tipoCliente.toUpperCase();
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
	public void setDeuda(float deuda) throws Exception {
		if (deuda < 0)
			throw new Exception("La deuda no puede ser negativa.");
		this.deuda = deuda;
	}
	public int getCantDePagosAtrasados() {
		return cantDePagosAtrasados;
	}
	public void setCantDePagosAtrasados(int cantPagosAtrasados) throws Exception {
		if (cantPagosAtrasados < 0)
			throw new Exception("La cantidad de pagos atrasados no puede ser negativa.");
		this.cantDePagosAtrasados = cantPagosAtrasados;
	}
	public Cliente( String cedula, String nombre, String apellido, String telefono, String email,
			String direccion, Date fechaRegistro, String codigoCliente, String tipoCliente, boolean estado, float deuda,
			int cantDePagosAtrasados) {
		super(cedula, nombre, apellido, telefono, email, direccion, fechaRegistro);
		try {
			setCodigoCliente(codigoCliente);
			setDeuda(deuda);
			setTipoCliente(tipoCliente);
			setCantDePagosAtrasados(cantDePagosAtrasados);
			this.estado = estado;
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	public void agregarDeuda(float monto) throws Exception {
	    if (monto <= 0)
	        throw new Exception("Monto inválido.");
	    deuda += monto;
	}
	
	public void pagarDeuda(float monto) throws Exception {
	    if (monto <= 0)
	        throw new Exception("Monto inválido.");

	    deuda -= monto;

	    if (deuda < 0)
	        deuda = 0;

	    if (deuda == 0)
	        cantDePagosAtrasados = 0;
	}
	
	public void registrarPagoAtrasado() {
	    cantDePagosAtrasados++;
	}
	
	public void suspender() {
	    estado = false;
	}
	
	public void reactivar() {
	    estado = true;
	}
	
	public boolean esMoroso() {
	    return deuda > 0 && cantDePagosAtrasados > 0;
	}
	
	@Override
	public String toString() {
	    // Esto es lo que verá el usuario en el ComboBox
	    return getNombre() + " " + getApellido();
	}

}
