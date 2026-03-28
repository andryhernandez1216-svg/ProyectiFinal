package Ligca;

import java.util.ArrayList;

public class SistemaAltice {

    private ArrayList<Cliente> clientes;
    private ArrayList<Empleado> empleados;
    private ArrayList<Plan> planes;
    private ArrayList<Contrato> contratos;
    private ArrayList<Pago> pagos;
    private ArrayList<Servicio> servicios;

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    public ArrayList<Plan> getPlanes() {
        return planes;
    }

    public ArrayList<Contrato> getContratos() {
        return contratos;
    }

    public ArrayList<Pago> getPagos() {
        return pagos;
    }

    public ArrayList<Servicio> getServicios() {
        return servicios;
    }

    public SistemaAltice(ArrayList<Cliente> clientes, ArrayList<Empleado> empleados, ArrayList<Plan> planes,
                         ArrayList<Contrato> contratos, ArrayList<Pago> pagos, ArrayList<Servicio> servicios) {

        this.clientes = new ArrayList<>();
        this.empleados = new ArrayList<>();
        this.planes = new ArrayList<>();
        this.contratos = new ArrayList<>();
        this.pagos = new ArrayList<>();
        this.servicios = new ArrayList<>();
    }


    public void agregarCliente(Cliente c) {
        clientes.add(c);
    }

    public Cliente buscarClientePorCodigo(String codigo) {
        for (Cliente c : clientes) {
            if (c.getCodigoCliente().equals(codigo))
                return c;
        }
        return null;
    }

    public boolean eliminarCliente(String codigo) {
        Cliente c = buscarClientePorCodigo(codigo);
        if (c != null) {
            clientes.remove(c);
            return true;
        }
        return false;
    }


    public void agregarEmpleado(Empleado e) {
        empleados.add(e);
    }

    public Empleado buscarEmpleado(String codigo) {
        for (Empleado e : empleados) {
            if (e.getCodigo().equals(codigo))
                return e;
        }
        return null;
    }


    public void agregarPlan(Plan p) {
        planes.add(p);
    }

    public Plan buscarPlan(String id) {
        for (Plan p : planes) {
            if (p.getIdPlan().equals(id))
                return p;
        }
        return null;
    }


    public void agregarServicio(Servicio s) {
        servicios.add(s);
    }

    public Servicio buscarServicio(String id) {
        for (Servicio s : servicios) {
            if (s.getIdServicio().equals(id))
                return s;
        }
        return null;
    }


    public void agregarContrato(Contrato c) {
        contratos.add(c);
    }

    public Contrato buscarContrato(String id) {
        for (Contrato c : contratos) {
            if (c.getIdContrato().equals(id))
                return c;
        }
        return null;
    }


    public void agregarPago(Pago p) {
        pagos.add(p);
    }

    public ArrayList<Pago> getPagosPorContrato(String idContrato) {
        ArrayList<Pago> lista = new ArrayList<>();

        for (Pago p : pagos) {
            if (p.getIdContrato().equals(idContrato)) {
                lista.add(p);
            }
        }

        return lista;
    }


    public int cantidadClientesActivos() {
        int count = 0;
        for (Cliente c : clientes) {
            if (c.isEstado())
                count++;
        }
        return count;
    }

    public float totalIngresos() {
        float total = 0;

        for (Pago p : pagos) {
            if (p.isPagado())
                total += p.getMonto();
        }

        return total;
    }

    public int cantidadContratosActivos() {
        int count = 0;
        for (Contrato c : contratos) {
            if (c.isEstado())
                count++;
        }
        return count;
    }


    @Override
    public String toString() {
        return "Sistema Altice\n" +
               "Clientes: " + clientes.size() +
               " | Empleados: " + empleados.size() +
               " | Planes: " + planes.size() +
               " | Contratos: " + contratos.size() +
               " | Pagos: " + pagos.size();
    }
}
