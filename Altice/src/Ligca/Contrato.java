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
	public Contrato(String idContrato, Cliente cliente, Plan plan, Date fechaInicio, Date fechaFin, String estado,
			ArrayList<Servicio> serviciosAdicionales, double descuento) {
		super();
		this.idContrato = idContrato;
		this.cliente = cliente;
		this.plan = plan;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estado = estado;
		this.serviciosAdicionales = serviciosAdicionales;
		this.descuento = descuento;
	}
	public String getIdContrato() {
		return idContrato;
	}
	public void setIdContrato(String idContrato) {
		this.idContrato = idContrato;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Plan getPlan() {
		return plan;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public ArrayList<Servicio> getServiciosAdicionales() {
		return serviciosAdicionales;
	}
	public void setServiciosAdicionales(ArrayList<Servicio> serviciosAdicionales) {
		this.serviciosAdicionales = serviciosAdicionales;
	}
	public double getDescuento() {
		return descuento;
	}
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}
	public static String getActivo() {
		return ACTIVO;
	}
	public static String getCancelado() {
		return CANCELADO;
	}


}
