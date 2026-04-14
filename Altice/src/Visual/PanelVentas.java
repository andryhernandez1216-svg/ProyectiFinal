package Visual;

import Ligca.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class PanelVentas extends JPanel {

    private JComboBox<Cliente> cbCliente;
    private JComboBox<Plan> cbPlan;
    private JTextArea areaFactura;
    private JButton btnVenta;
    private Usuario usuarioActual;
    
    // Colores Altice
    private final Color MAGENTA_ALTICE = new Color(225, 0, 110);
    private final Color AZUL_ALTICE = new Color(0, 43, 92);

    public PanelVentas(Usuario user) {
        this.usuarioActual = user;
        setBackground(new Color(245, 246, 250)); // Gris muy claro de fondo
        setLayout(new BorderLayout(20, 20));
        setBorder(new EmptyBorder(25, 25, 25, 25));

        // --- PANEL DE SELECCIÓN (IZQUIERDA) ---
        JPanel pnlIzquierdo = new JPanel(new GridBagLayout());
        pnlIzquierdo.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;

        cbCliente = new JComboBox<>();
        cbPlan = new JComboBox<>();
        
        // Estilizar combos
        cbCliente.setPreferredSize(new Dimension(300, 35));
        cbPlan.setPreferredSize(new Dimension(300, 35));

        pnlIzquierdo.add(new JLabel("👤 Seleccionar Cliente:"), gbc);
        gbc.gridy = 1; pnlIzquierdo.add(cbCliente, gbc);
        gbc.gridy = 2; pnlIzquierdo.add(new JLabel("📦 Seleccionar Plan de Servicio:"), gbc);
        gbc.gridy = 3; pnlIzquierdo.add(cbPlan, gbc);

        btnVenta = new JButton("GENERAR VENTA Y FACTURAR");
        btnVenta.setBackground(MAGENTA_ALTICE);
        btnVenta.setForeground(Color.WHITE);
        btnVenta.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVenta.setPreferredSize(new Dimension(300, 50));
        btnVenta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVenta.setFocusPainted(false);
        btnVenta.addActionListener(e -> procesarVenta());

        gbc.gridy = 4; gbc.insets = new Insets(30, 0, 10, 0);
        pnlIzquierdo.add(btnVenta, gbc);

        // --- PANEL DE RECIBO (DERECHA) ---
        areaFactura = new JTextArea();
        areaFactura.setFont(new Font("Monospaced", Font.PLAIN, 14));
        areaFactura.setEditable(false);
        areaFactura.setBackground(new Color(255, 255, 240)); // Papel crema
        
        JScrollPane scrollFactura = new JScrollPane(areaFactura);
        scrollFactura.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY), "VISTA PREVIA DE FACTURA", 
            TitledBorder.CENTER, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 12)));

        // Distribución Final
        add(pnlIzquierdo, BorderLayout.WEST);
        add(scrollFactura, BorderLayout.CENTER);

        refrescarCombos();
    }

    public void refrescarCombos() {
        btnVenta.setEnabled(false);
        btnVenta.setText("Cargando datos...");
        
        new Thread(() -> {
            try {
                // Sincronizamos con las claves exactas que usa tu servidor
                Object dataClientes = SocketCliente.recibirDatos("clientes");
                Object dataPlanes = SocketCliente.recibirDatos("planes");

                ArrayList<Cliente> clientes = (dataClientes instanceof ArrayList) ? (ArrayList<Cliente>) dataClientes : new ArrayList<>();
                ArrayList<Plan> planes = (dataPlanes instanceof ArrayList) ? (ArrayList<Plan>) dataPlanes : new ArrayList<>();

                SwingUtilities.invokeLater(() -> {
                    cbCliente.removeAllItems();
                    cbPlan.removeAllItems();
                    for (Cliente c : clientes) cbCliente.addItem(c);
                    for (Plan p : planes) cbPlan.addItem(p);
                    
                    btnVenta.setEnabled(true);
                    btnVenta.setText("GENERAR VENTA Y FACTURAR");
                });
            } catch (Exception e) {
                System.err.println("Error cargando combos: " + e.getMessage());
            }
        }).start();
    }

    private void procesarVenta() {
        Cliente cSel = (Cliente) cbCliente.getSelectedItem();
        Plan pSel = (Plan) cbPlan.getSelectedItem();

        if (cSel == null || pSel == null) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione ambos campos.");
            return;
        }

        // Bloqueamos para evitar doble clic
        btnVenta.setEnabled(false);
        btnVenta.setText("Procesando...");

        new Thread(() -> {
            try {
                // 1. Obtención de datos históricos
                Object dCli = SocketCliente.recibirDatos("clientes");
                Object dFac = SocketCliente.recibirDatos("facturas");

                ArrayList<Cliente> listaCli = (dCli instanceof ArrayList) ? (ArrayList<Cliente>) dCli : new ArrayList<>();
                ArrayList<Factura> listaFac = (dFac instanceof ArrayList) ? (ArrayList<Factura>) dFac : new ArrayList<>();

                // 2. Actualizar Deuda
                boolean exitoActualizacion = false;
                for (Cliente c : listaCli) {
                    if (c.getCedula().equals(cSel.getCedula())) {
                        c.setDeuda(c.getDeuda() + pSel.getPrecio());
                        exitoActualizacion = true;
                        break;
                    }
                }

                if (exitoActualizacion) {
                    // 3. Crear Factura y Detalle
                    String idFac = "FAC-" + (System.currentTimeMillis() % 100000);
                    Factura nuevaFac = new Factura(idFac, cSel, null, new Date());
                    
                    DetalleFactura det = new DetalleFactura("DET-01", pSel.getNombre(), 1, pSel.getPrecio());
                    nuevaFac.agregarDetalle(det);
                    listaFac.add(nuevaFac);

                    // 4. Guardado Doble
                    SocketCliente.enviarDatos("clientes", listaCli);
                    SocketCliente.enviarDatos("facturas", listaFac);

                    SwingUtilities.invokeLater(() -> {
                        areaFactura.setText(generarReciboTexto(cSel, pSel, idFac));
                        JOptionPane.showMessageDialog(this, "¡Transacción Exitosa!\nLa deuda ha sido cargada.");
                        btnVenta.setEnabled(true);
                        btnVenta.setText("GENERAR VENTA Y FACTURAR");
                    });
                }
            } catch (Exception ex) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                    btnVenta.setEnabled(true);
                });
            }
        }).start();
    }

    private String generarReciboTexto(Cliente c, Plan p, String id) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return  "\n" +
                "   ========================================\n" +
                "              ALTICE DOMINICANA           \n" +
                "            RNC: 1-30-90291-1             \n" +
                "   ========================================\n" +
                "   No. Factura:  " + id + "\n" +
                "   Fecha/Hora:   " + sdf.format(new Date()) + "\n" +
                "   Atendido por: " + usuarioActual.getNombre() + "\n" +
                "   ----------------------------------------\n" +
                "   CLIENTE:      " + c.getNombre().toUpperCase() + " " + c.getApellido().toUpperCase() + "\n" +
                "   CÉDULA:       " + c.getCedula() + "\n" +
                "   ----------------------------------------\n" +
                "   DESCRIPCIÓN DEL SERVICIO:               \n" +
                "   " + p.getNombre() + " (" + p.getTipo() + ")\n" +
                "                                           \n" +
                "   SUBTOTAL:             RD$ " + String.format("%.2f", p.getPrecio()) + "\n" +
                "   ITBIS (18%):          RD$ 0.00 (Incluido)\n" +
                "   ----------------------------------------\n" +
                "   TOTAL A CARGAR:       RD$ " + String.format("%.2f", p.getPrecio()) + "\n" +
                "   ----------------------------------------\n" +
                "   ESTADO: CARGADO A BALANCE PENDIENTE\n" +
                "   ========================================\n" +
                "        ¡Gracias por preferir Altice!      \n" +
                "   ========================================\n";
    }
}