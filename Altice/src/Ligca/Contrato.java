package Ligca;

import java.util.ArrayList;
import java.util.Date;

public class Contrato {

    private String idContrato;
    private Cliente cliente;
    private Plan plan;
    private Date fechaInicio;
    private Date fechaFin;
    private boolean estado;
    private ArrayList<Servicio> servicios; 

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

    public ArrayList<Servicio> getServicios() {
        return servicios;
    }

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


    public void agregarServicio(Servicio servicio) throws Exception {
        if (servicio == null)
            throw new Exception("Servicio inválido.");
        servicios.add(servicio);
    }

    public void eliminarServicio(Servicio servicio) {
        servicios.remove(servicio);
    }

    public double calcularTotal() {
        double total = plan.getPrecio();

        for (Servicio s : servicios) {
            total += s.getPrecio();
        }

        return total;
    }

    public boolean estaActivo() {
        return estado && new Date().before(fechaFin);
    }

    public boolean estaVencido() {
        return new Date().after(fechaFin);
    }

    public void cancelar() {
        estado = false;
    }

    public void renovar(Date nuevaFechaFin) throws Exception {
        if (nuevaFechaFin.before(new Date()))
            throw new Exception("Fecha inválida.");
        this.fechaFin = nuevaFechaFin;
        this.estado = true;
    }

    @Override
    public String toString() {
        return "Contrato: " + idContrato +
               " | Cliente: " + cliente.getDatos() +
               " | Plan: " + plan.getNombre() +
               " | Estado: " + (estado ? "Activo" : "Inactivo");
    }
}