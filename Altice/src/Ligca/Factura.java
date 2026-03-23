package Ligca;

import java.util.ArrayList;
import java.util.Date;

public class Factura {
	public static final String PENDIENTE = "pendiente";
    public static final String PAGADA = "pagada";
    public static final String VENCIDA = "vencida";

    private String numeroFactura;
    private Cliente cliente;
    private Date fechaEmision;
    private Date fechaVencimiento;
    private ArrayList<DetalleFactura> detalles;

    private double total;
    private double saldoPendiente;
    private String estado;

}
