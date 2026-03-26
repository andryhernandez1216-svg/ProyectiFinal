package Ligca;

import java.util.Date;

public class Empleado extends Persona {
	

    private String codigo;
    private float salario;
    private Date fechaIngreso;
    private String estado;
    private float comisiones;
    private boolean activo;
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public float getSalario() {
		return salario;
	}
	public void setSalario(float salario) {
		this.salario = salario;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public float getComisiones() {
		return comisiones;
	}
	public void setComisiones(float comisiones) {
		this.comisiones = comisiones;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public Empleado(String id, String cedula, String nombre, String apellido, String telefono, String email,
			String direccion, Date fechaRegistro, String codigo, float salario, Date fechaIngreso, String estado,
			float comisiones, boolean activo) {
		super(id, cedula, nombre, apellido, telefono, email, direccion, fechaRegistro);
		this.codigo = codigo;
		this.salario = salario;
		this.fechaIngreso = fechaIngreso;
		this.estado = estado;
		this.comisiones = comisiones;
		this.activo = true;
	}
	
    
}
