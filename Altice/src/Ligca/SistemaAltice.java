package Ligca;

import java.util.ArrayList;

public class SistemaAltice {

    private ArrayList<Cliente> clientes;
    private ArrayList<Empleado> empleados;
    private ArrayList<Plan> planes;
    private ArrayList<Contrato> contratos;
    private ArrayList<Factura> facturas; // ¡NUEVA LISTA NECESARIA!
    private ArrayList<Pago> pagos;

    public SistemaAltice() { // Constructor simplificado
        this.clientes = new ArrayList<>();
        this.empleados = new ArrayList<>();
        this.planes = new ArrayList<>();
        this.contratos = new ArrayList<>();
        this.facturas = new ArrayList<>();
        this.pagos = new ArrayList<>();
    }

    // --- GETTERS ---
    public ArrayList<Cliente> getClientes() { return clientes; }
    public ArrayList<Plan> getPlanes() { return planes; }
    public ArrayList<Contrato> getContratos() { return contratos; }
    public ArrayList<Factura> getFacturas() { return facturas; } // Para el PanelReportes
    public ArrayList<Pago> getPagos() { return pagos; }
 

    // --- MÉTODOS DE AGREGAR ---
    public void agregarCliente(Cliente c) { clientes.add(c); }
    public void agregarPlan(Plan p) { planes.add(p); }
    public void agregarContrato(Contrato c) { contratos.add(c); }
    public void agregarFactura(Factura f) { facturas.add(f); }
    public void agregarPago(Pago p) { pagos.add(p); }

    // --- LÓGICA DE DASHBOARD ---
    public int cantidadClientesActivos() {
        int count = 0;
        for (Cliente c : clientes) { if (c.isEstado()) count++; }
        return count;
    }

    public float totalIngresos() {
        float total = 0;
        for (Pago p : pagos) { if (p.isPagado()) total += p.getMonto(); }
        return total;
    }

    public int cantidadContratosActivos() {
        int count = 0;
        for (Contrato c : contratos) { if (c.isEstado()) count++; }
        return count;
    }

    @Override
    public String toString() {
        return "Sistema Altice | Clientes: " + clientes.size() + " | Facturas: " + facturas.size();
    }
}