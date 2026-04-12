package Visual;

import Ligca.*;
import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class PanelVentas extends JPanel {

    private JComboBox<Cliente> cbCliente;
    private JComboBox<Plan> cbPlan;
    private JTextArea areaFactura;
    private Usuario usuarioActual;

    public PanelVentas(Usuario user) {
        this.usuarioActual = user;
        setLayout(new BorderLayout(10, 10));

        // Paneles de selección
        JPanel pnlNorte = new JPanel(new GridLayout(2, 2, 5, 5));
        cbCliente = new JComboBox<>();
        cbPlan = new JComboBox<>();

        pnlNorte.add(new JLabel("Cliente:"));
        pnlNorte.add(cbCliente);
        pnlNorte.add(new JLabel("Plan:"));
        pnlNorte.add(cbPlan);

        JButton btnVenta = new JButton("Generar Factura");
        btnVenta.addActionListener(e -> procesarVenta());

        areaFactura = new JTextArea();
        areaFactura.setEditable(false);

        // Layout
        JPanel contenedorSuperior = new JPanel(new BorderLayout());
        contenedorSuperior.add(pnlNorte, BorderLayout.CENTER);
        contenedorSuperior.add(btnVenta, BorderLayout.SOUTH);

        add(contenedorSuperior, BorderLayout.NORTH);
        add(new JScrollPane(areaFactura), BorderLayout.CENTER);
    }

    // Este es el método que la VentanaPrincipal llama para actualizar la lista
    public void refrescarCombos() {
        cbCliente.removeAllItems();
        cbPlan.removeAllItems();
        
        // Llenamos los combos con los objetos reales
        for (Cliente c : GestionSistema.getInstancia().getClientes()) {
            cbCliente.addItem(c); 
        }
        for (Plan p : GestionSistema.getInstancia().getPlanes()) {
            cbPlan.addItem(p);
        }
    }

    private void procesarVenta() {
        Cliente c = (Cliente) cbCliente.getSelectedItem();
        Plan p = (Plan) cbPlan.getSelectedItem();

        if (c != null && p != null) {
            try {
                // Crear factura y detalle (con tus 4 argumentos)
                Factura f = new Factura("FAC-" + System.currentTimeMillis(), c, null, new Date());
                DetalleFactura det = new DetalleFactura("DET-01", p.getNombre(), 1, p.getPrecio());
                f.agregarDetalle(det);
                
                GestionSistema.getInstancia().agregarFactura(f);
                
                // Mostrar resultado simple
                areaFactura.setText("FACTURA GENERADA\n" +
                                   "Cliente: " + c.toString() + "\n" +
                                   "Plan: " + p.getNombre() + "\n" +
                                   "Total: RD$ " + f.getMontoTotal());
                
                JOptionPane.showMessageDialog(this, "Venta realizada");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }
}