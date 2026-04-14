package Visual;

import Ligca.Cliente;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class PanelClientes extends JPanel {
    private JTable tabla;
    private DefaultTableModel modelo;
    private JTextField txtBuscar;

    public PanelClientes() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        txtBuscar = new JTextField();
        txtBuscar.setBorder(BorderFactory.createTitledBorder("Buscar por nombre o cédula..."));
        
        txtBuscar.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                actualizarTabla(txtBuscar.getText());
            }
        });

        modelo = new DefaultTableModel(new String[]{"Cédula", "Nombre", "Código", "Deuda", "Estado"}, 0);
        tabla = new JTable(modelo);

        add(txtBuscar, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        actualizarTabla(""); 
    }

    public void actualizarTabla(String filtro) {
        new Thread(() -> {
            // CAMBIO IMPORTANTE: "clientes" en minúsculas para coincidir con el servidor
            ArrayList<Cliente> listaServidor = SocketCliente.recibirDatos("clientes");
            
            SwingUtilities.invokeLater(() -> {
                // Validar que la lista no sea nula para evitar errores
                if (listaServidor == null) return;

                modelo.setRowCount(0);
                for (Cliente c : listaServidor) {
                    // Validación para evitar NullPointerException al buscar
                    String nombre = (c.getNombre() != null) ? c.getNombre() : "";
                    String apellido = (c.getApellido() != null) ? c.getApellido() : "";
                    String cedula = (c.getCedula() != null) ? c.getCedula() : "";
                    
                    String busqueda = (nombre + " " + apellido + " " + cedula).toLowerCase();
                    
                    if (busqueda.contains(filtro.toLowerCase())) {
                        modelo.addRow(new Object[]{
                            cedula,
                            nombre + " " + apellido,
                            c.getCodigoCliente(),
                            "RD$ " + String.format("%.2f", c.getDeuda()),
                            c.isEstado() ? "Activo" : "Inactivo"
                        });
                    }
                }
                // Notificar a la tabla que los datos cambiaron
                tabla.revalidate();
                tabla.repaint();
            });
        }).start();
    }
}
