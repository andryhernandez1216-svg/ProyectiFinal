package Ligca;

import java.util.Date;

public class Administrativo extends Empleado{

	private String area;
    private String nivelAcceso; 
    private String horario;
	public Administrativo(String id, String cedula, String nombre, String apellido, String telefono, String email,
			String direccion, Date fechaRegistro, String codigo, double salario, Date fechaIngreso, String estado,
			String area, String nivelAcceso, String horario) {
		super(id, cedula, nombre, apellido, telefono, email, direccion, fechaRegistro, codigo, salario, fechaIngreso,
				estado);
		this.area = area;
		this.nivelAcceso = nivelAcceso;
		this.horario = horario;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getNivelAcceso() {
		return nivelAcceso;
	}
	public void setNivelAcceso(String nivelAcceso) {
		this.nivelAcceso = nivelAcceso;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}

}
