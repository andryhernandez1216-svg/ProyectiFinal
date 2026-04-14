package Visual;

import Ligca.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class PanelVentas extends JPanel {

    private JComboBox<Cliente> cbCliente;
    private JComboBox<Plan> cbPlan;
    private JTextArea areaFactura;
    private Usuario usuarioActual;

    public PanelVentas(Usuario user) {
        this.usuarioActual = user;
        setBackground(Color.WHITE);
        setLayout(new BorderLayout(15, 15));

        // --- Interfaz ---
        JPanel pnlNorte = new JPanel(new GridLayout(2, 2, 10, 10));
        pnlNorte.setBackground(Color.WHITE);
        pnlNorte.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        cbCliente = new JComboBox<>();
        cbPlan = new JComboBox<>();

        pnlNorte.add(new JLabel("Seleccionar Cliente:"));
        pnlNorte.add(cbCliente);
        pnlNorte.add(new JLabel("Seleccionar Plan:"));
        pnlNorte.add(cbPlan);

        JButton btnVenta = new JButton("GENERAR VENTA Y CARGAR A CUENTA");
        btnVenta.setBackground(new Color(225, 0, 110));
        btnVenta.setForeground(Color.WHITE);
        btnVenta.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnVenta.addActionListener(e -> procesarVenta());

        areaFactura = new JTextArea();
        areaFactura.setFont(new Font("Monospaced", Font.PLAIN, 13));
        areaFactura.setEditable(false);

        JPanel contenedorSuperior = new JPanel(new BorderLayout());
        contenedorSuperior.add(pnlNorte, BorderLayout.CENTER);
        contenedorSuperior.add(btnVenta, BorderLayout.SOUTH);

        add(contenedorSuperior, BorderLayout.NORTH);
        add(new JScrollPane(areaFactura), BorderLayout.CENTER);

        // Carga inicial
        refrescarCombos();
    }

    public void refrescarCombos() {
        new Thread(() -> {
            ArrayList<Cliente> clientes = SocketCliente.recibirDatos("CLIENTES");
            ArrayList<Plan> planes = SocketCliente.recibirDatos("PLANES");

            SwingUtilities.invokeLater(() -> {
                cbCliente.removeAllItems();
                cbPlan.removeAllItems();
                for (Cliente c : clientes) cbCliente.addItem(c);
                for (Plan p : planes) cbPlan.addItem(p);
            });
        }).start();
    }

    private void procesarVenta() {
        Cliente clienteSeleccionado = (Cliente) cbCliente.getSelectedItem();
        Plan planSeleccionado = (Plan) cbPlan.getSelectedItem();

        if (clienteSeleccionado == null || planSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente y un plan.");
            return;
        }

        new Thread(() -> {
            try {
                // 1. CARGAR DATOS ACTUALES DEL SERVIDOR
                // Usamos minúsculas para coincidir con la lógica del servidor (tipo.toLowerCase())
            	// 1. Recibimos los datos como objetos genéricos primero
            	Object dataClientes = SocketCliente.recibirDatos("clientes");
            	Object dataFacturas = SocketCliente.recibirDatos("facturas");

            	// 2. Validamos y asignamos de forma segura
            	ArrayList<Cliente> listaClientes = (dataClientes instanceof ArrayList) 
            	    ? (ArrayList<Cliente>) dataClientes 
            	    : new ArrayList<>();

            	ArrayList<Factura> listaFacturas = (dataFacturas instanceof ArrayList) 
            	    ? (ArrayList<Factura>) dataFacturas 
            	    : new ArrayList<>();
                
                if (listaClientes == null) listaClientes = new ArrayList<>();
                if (listaFacturas == null) listaFacturas = new ArrayList<>();

                // 2. ACTUALIZAR DEUDA DEL CLIENTE
                boolean encontrado = false;
                for (Cliente c : listaClientes) {
                    if (c.getCedula().equals(clienteSeleccionado.getCedula())) {
                        c.setDeuda(c.getDeuda() + planSeleccionado.getPrecio());
                        encontrado = true;
                        break;
                    }
                }

                if (encontrado) {
                    // 3. CREAR EL OBJETO FACTURA
                    // Generamos un ID único basado en el tiempo
                    String idFac = "FAC-" + System.currentTimeMillis();
                    Factura nuevaFactura = new Factura(idFac, clienteSeleccionado, null, new Date());
                    
                    // Creamos un detalle para la factura (asumiendo que tienes la clase DetalleFactura)
                    DetalleFactura detalle = new DetalleFactura(
                    	    "DET-01", 
                    	    planSeleccionado.getNombre(), 
                    	    1, 
                    	    planSeleccionado.getPrecio()
                    	);
                    nuevaFactura.agregarDetalle(detalle);
                    
                    // Añadimos a la lista histórica
                    listaFacturas.add(nuevaFactura);

                    // 4. ENVIAR AMBAS LISTAS AL SERVIDOR
                    // Aquí se resuelven los "Unhandled exception" con este bloque try/catch
                    SocketCliente.enviarDatos("clientes", listaClientes);
                    SocketCliente.enviarDatos("facturas", listaFacturas);

                    SwingUtilities.invokeLater(() -> {
                        areaFactura.setText(generarReciboTexto(clienteSeleccionado, planSeleccionado));
                        JOptionPane.showMessageDialog(this, "Venta procesada y factura archivada.");
                    });
                }

            } catch (Exception ex) {
                // Este catch captura cualquier error de SocketCliente (compilación resuelta)
                SwingUtilities.invokeLater(() -> 
                    JOptionPane.showMessageDialog(this, "Error en la transacción: " + ex.getMessage()));
                ex.printStackTrace();
            }
        }).start();
    }

    private String generarReciboTexto(Cliente c, Plan p) {
        return "==========================================\n" +
               "             ALTICE DOMINICANA            \n" +
               "==========================================\n" +
               "Fecha:        " + new Date() + "\n" +
               "Atendido por: " + usuarioActual.getNombre() + "\n" +
               "------------------------------------------\n" +
               "CLIENTE:      " + c.getNombre() + " " + c.getApellido() + "\n" +
               "ID CLIENTE:   " + c.getCodigoCliente() + "\n" +
               "------------------------------------------\n" +
               "SERVICIO:     " + p.getNombre() + "\n" +
               "TIPO:         " + p.getTipo() + "\n" +
               "CARGO:        RD$ " + String.format("%.2f", p.getPrecio()) + "\n" +
               "------------------------------------------\n" +
               "ESTADO:       CARGADO A CUENTA\n" +
               "==========================================\n";
    }
}