package Ligca;

import java.util.Date;

public class Comercial extends Empleado {
	private String zona;
    private double comision;
    private double ventasMes;
    private double meta;
	public Comercial(String id, String cedula, String nombre, String apellido, String telefono, String email,
			String direccion, Date fechaRegistro, String codigo, double salario, Date fechaIngreso, String estado,
			String zona, double comision, double ventasMes, double meta) {
		super(id, cedula, nombre, apellido, telefono, email, direccion, fechaRegistro, codigo, salario, fechaIngreso,
				estado);
		this.zona = zona;
		this.comision = comision;
		this.ventasMes = ventasMes;
		this.meta = meta;
	}
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
	public double getComision() {
		return comision;
	}
	public void setComision(double comision) {
		this.comision = comision;
	}
	public double getVentasMes() {
		return ventasMes;
	}
	public void setVentasMes(double ventasMes) {
		this.ventasMes = ventasMes;
	}
	public double getMeta() {
		return meta;
	}
	public void setMeta(double meta) {
		this.meta = meta;
	}
	

}
