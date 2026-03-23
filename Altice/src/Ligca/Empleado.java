package Ligca;

import java.util.Date;

public abstract class Empleado extends Persona {
	
	public static final String ACTIVO = "activo";
    public static final String INACTIVO = "inactivo";

    protected String codigo;
    protected double salario;
    protected Date fechaIngreso;
    protected String estado;
	public Empleado(String codigo, double salario, Date fechaIngreso, String estado) {
		super();
		this.codigo = codigo;
		this.salario = salario;
		this.fechaIngreso = fechaIngreso;
		this.estado = estado;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
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
	public static String getActivo() {
		return ACTIVO;
	}
	public static String getInactivo() {
		return INACTIVO;
	}

}
