package Ligca;

import java.util.Date;

public abstract class Empleado extends Persona {
	
	public static final String ACTIVO = "activo";
    public static final String INACTIVO = "inactivo";

    protected String codigo;
    protected double salario;
    protected Date fechaIngreso;
    protected String estado;

}
