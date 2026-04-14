package Ligca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Contrato implements Serializable {

    private static final long serialVersionUID = 1L;

    private String idContrato;
    private Cliente cliente; 
    private Plan plan;       
    private Date fechaInicio;
    private Date fechaFin;
    private boolean estado;
    private ArrayList<Servicio> servicios; 

    public Contrato(String idContrato, Cliente cliente, Plan plan, Date fechaInicio, Date fechaFin, boolean estado) {
        try {
            setIdContrato(idContrato);
            setFechaInicio(fechaInicio);
            setFechaFin(fechaFin);
            setCliente(cliente);
            setPlan(plan);
            this.estado = estado;
            this.servicios = new ArrayList<>(); 
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }


    public String getIdContrato() { return idContrato; }

    public void setIdContrato(String idContrato) throws Exception {
        if (idContrato == null || idContrato.trim().isEmpty())
            throw new Exception("El id del contrato no puede estar vacio.");
        this.idContrato = idContrato.trim();
    }

    public Cliente getCliente() { return cliente; }

    public void setCliente(Cliente cliente) throws Exception {
        if (cliente == null)
            throw new Exception("El contrato debe tener un cliente asignado.");
        this.cliente = cliente;
    }

    public Plan getPlan() { return plan; }

    public void setPlan(Plan plan) throws Exception {
        if (plan == null)
            throw new Exception("El contrato debe tener un plan asignado.");
        this.plan = plan;
    }

    public Date getFechaInicio() { return fechaInicio; }

    public void setFechaInicio(Date fechaInicio) throws Exception {
        if (fechaInicio == null)
            throw new Exception("La fecha de inicio no puede ser nula.");
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() { return fechaFin; }

    public void setFechaFin(Date fechaFin) throws Exception {
        if (fechaFin == null)
            throw new Exception("La fecha de fin no puede ser nula.");
        if (this.fechaInicio != null && fechaFin.before(this.fechaInicio))
            throw new Exception("La fecha de fin no puede ser anterior a la fecha de inicio.");
        this.fechaFin = fechaFin;
    }

    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }

    public ArrayList<Servicio> getServicios() { return servicios; }


    public void agregarServicio(Servicio servicio) throws Exception {
        if (servicio == null)
            throw new Exception("Servicio inválido.");
        servicios.add(servicio);
    }

    public void eliminarServicio(Servicio servicio) {
        servicios.remove(servicio);
    }

    public double calcularTotal() {
        double total = (plan != null) ? plan.getPrecio() : 0;
        if (servicios != null) {
            for (Servicio s : servicios) {
                total += s.getPrecio();
            }
        }
        return total;
    }

    public boolean estaActivo() {
        return estado && new Date().before(fechaFin);
    }

    public void cancelar() {
        this.estado = false;
    }

    @Override
    public String toString() {
        return "Contrato: " + idContrato +
               " | Cliente: " + (cliente != null ? cliente.getNombreCompleto() : "N/A") +
               " | Plan: " + (plan != null ? plan.getNombre() : "N/A") +
               " | Total: RD$" + calcularTotal();
    }
}