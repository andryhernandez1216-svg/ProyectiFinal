package Visual;

import Ligca.Cliente;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class PanelClientes extends JPanel {
    private JTable tabla;
    private DefaultTableModel modelo;
    private JTextField txtBuscar;
    private JButton btnRefrescar;

    private final Color AZUL_ALTICE = new Color(0, 43, 92);
    private final Color MAGENTA_ALTICE = new Color(225, 0, 110);

    public PanelClientes() {
        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(245, 246, 250));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // --- 1. BARRA DE BÚSQUEDA ---
        JPanel pnlNorte = new JPanel(new BorderLayout(10, 0));
        pnlNorte.setOpaque(false);

        txtBuscar = new JTextField();
        txtBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtBuscar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        
        // Label flotante o placeholder (simulado con TitledBorder estilizado)
        txtBuscar.setBorder(BorderFactory.createTitledBorder("🔍 Buscar por nombre, apellido o cédula"));

        txtBuscar.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                actualizarTabla(txtBuscar.getText());
            }
        });

        btnRefrescar = new JButton("🔄 Refrescar");
        btnRefrescar.setBackground(AZUL_ALTICE);
        btnRefrescar.setForeground(Color.WHITE);
        btnRefrescar.setFocusPainted(false);
        btnRefrescar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRefrescar.addActionListener(e -> actualizarTabla(txtBuscar.getText()));

        pnlNorte.add(txtBuscar, BorderLayout.CENTER);
        pnlNorte.add(btnRefrescar, BorderLayout.EAST);

        // --- 2. CONFIGURACIÓN DE TABLA ---
        modelo = new DefaultTableModel(new String[]{"Cédula", "Cliente", "ID Sistema", "Balance Pendiente", "Estado"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tabla = new JTable(modelo);
        tabla.setRowHeight(35);
        tabla.setSelectionBackground(new Color(225, 0, 110, 40)); // Magenta suave al seleccionar
        tabla.setSelectionForeground(Color.BLACK);
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabla.getTableHeader().setBackground(AZUL_ALTICE);
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.setShowVerticalLines(false);
        tabla.setGridColor(new Color(230, 230, 230));

        // Aplicar Renderizado Especial (Colores en Deuda y Estado)
        estilizarColumnas();

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        scroll.getViewport().setBackground(Color.WHITE);

        add(pnlNorte, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        actualizarTabla(""); 
    }

    private void estilizarColumnas() {
        // Columna de Balance (3) y Estado (4)
        tabla.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String val = value.toString().replace("RD$ ", "");
                try {
                    double deuda = Double.parseDouble(val.replace(",", ""));
                    if (deuda > 0) setForeground(new Color(180, 0, 0)); // Rojo si debe
                    else setForeground(new Color(0, 120, 0)); // Verde si está limpio
                } catch (Exception e) { setForeground(Color.BLACK); }
                return c;
            }
        });
    }

    public void actualizarTabla(String filtro) {
        btnRefrescar.setText("Cargando...");
        btnRefrescar.setEnabled(false);

        new Thread(() -> {
            Object data = SocketCliente.recibirDatos("clientes");
            ArrayList<Cliente> listaServidor = (data instanceof ArrayList) ? (ArrayList<Cliente>) data : new ArrayList<>();
            
            SwingUtilities.invokeLater(() -> {
                modelo.setRowCount(0);
                for (Cliente c : listaServidor) {
                    String nombreFull = (c.getNombre() + " " + c.getApellido()).trim();
                    String cedula = c.getCedula() != null ? c.getCedula() : "";
                    
                    String busqueda = (nombreFull + " " + cedula).toLowerCase();
                    
                    if (busqueda.contains(filtro.toLowerCase())) {
                        modelo.addRow(new Object[]{
                            cedula,
                            nombreFull.toUpperCase(),
                            c.getCodigoCliente(),
                            String.format("%.2f", c.getDeuda()),
                            c.isEstado() ? "✅ ACTIVO" : "❌ INACTIVO"
                        });
                    }
                }
                btnRefrescar.setText("🔄 Refrescar");
                btnRefrescar.setEnabled(true);
                tabla.repaint();
            });
        }).start();
    }
}
