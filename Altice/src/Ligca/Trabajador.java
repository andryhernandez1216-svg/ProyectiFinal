package Ligca;

import java.util.Date;

public class Trabajador extends Empleado{
	private String cargo;
    private String departamento;
	public Trabajador(String id, String cedula, String nombre, String apellido, String telefono, String email,
			String direccion, Date fechaRegistro, String codigo, double salario, Date fechaIngreso, String estado,
			String cargo, String departamento) {
		super(id, cedula, nombre, apellido, telefono, email, direccion, fechaRegistro, codigo, salario, fechaIngreso,
				estado);
		this.cargo = cargo;
		this.departamento = departamento;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
    

}
