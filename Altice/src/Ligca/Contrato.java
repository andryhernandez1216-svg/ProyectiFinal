package Ligca;

import java.util.ArrayList;
import java.util.Date;

public class Contrato {
	public static final String ACTIVO = "activo";
    public static final String CANCELADO = "cancelado";

    private String idContrato;
    private Cliente cliente;
    private Plan plan;
    private Date fechaInicio;
    private Date fechaFin;
    private String estado;
    private ArrayList<Servicio> serviciosAdicionales;
    private double descuento;


}
