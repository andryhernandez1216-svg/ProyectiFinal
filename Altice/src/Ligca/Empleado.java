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
	public void setCodigo(String codigo) throws Exception {
        if (codigo == null || codigo.trim().isEmpty())
            throw new Exception("El id del empleado no puede estar vacio.");
        this.codigo = codigo;
    }
	public float getSalario() {
		return salario;
	}
	public void setSalario(float salario) throws Exception {
        if (salario <= 0)
            throw new Exception("El salario debe ser mayor a 0.");
        this.salario = salario;
    }
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) throws Exception {
        if (fechaIngreso == null)
            throw new Exception("La fecha de contratacion no puede ser nula.");
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
	public void setComisiones(float comisiones) throws Exception {
        if (comisiones < 0)
            throw new Exception("Las comisiones no pueden ser negativas.");
        this.comisiones = comisiones;
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
		try {
            setCodigo(codigo);
            setSalario(salario);
            setComisiones(comisiones);
            setFechaIngreso(fechaIngreso);
            this.activo = true;
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
	
    
}
