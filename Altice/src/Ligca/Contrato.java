package Ligca;

import java.util.Date;

public class Contrato {

    private String idContrato;
    private Cliente cliente;
    private Plan plan;
    private Date fechaInicio;
    private Date fechaFin;
    private boolean estado;
    private Servicio servicios;
	public String getIdContrato() {
		return idContrato;
	}
	public void setIdContrato(String idContrato) throws Exception {
        if (idContrato == null || idContrato.trim().isEmpty())
            throw new Exception("El id del contrato no puede estar vacio.");
        this.idContrato = idContrato;
    }
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) throws Exception {
        if (cliente == null)
            throw new Exception("El contrato debe tener un cliente asignado.");
        this.cliente = cliente;
    }
	public Plan getPlan() {
		return plan;
	}
	public void setPlan(Plan plan) throws Exception {
        if (plan == null)
            throw new Exception("El contrato debe tener un plan asignado.");
        this.plan = plan;
    }
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) throws Exception {
        if (fechaInicio == null)
            throw new Exception("La fecha de inicio no puede ser nula.");
        this.fechaInicio = fechaInicio;
    }
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) throws Exception {
        if (fechaFin == null)
            throw new Exception("La fecha de fin no puede ser nula.");
        if (this.fechaInicio != null && fechaFin.before(this.fechaInicio))
            throw new Exception("La fecha de fin no puede ser anterior a la fecha de inicio.");
        this.fechaFin = fechaFin;
    }
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public Servicio getServicios() {
		return servicios;
	}
	public void setServicios(Servicio servicios) throws Exception {
        if (servicios == null)
            throw new Exception("El contrato debe tener un servicio asignado.");
        this.servicios = servicios;
    }
	public Contrato(String idContrato, Cliente cliente, Plan plan, Date fechaInicio, Date fechaFin, boolean estado,
			Servicio servicios) {
		super();
		try {
            setIdContrato(idContrato);
            setFechaInicio(fechaInicio);
            setFechaFin(fechaFin);
            setCliente(cliente);
            setPlan(plan);
            setServicios(servicios);
            this.estado = estado;
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
	

}
