package Visual;

import Ligca.Cliente;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelClientes extends JPanel {
    private JTable tabla;
    private DefaultTableModel modelo;
    private JTextField txtBuscar;

    public PanelClientes() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        txtBuscar = new JTextField();
        txtBuscar.setBorder(BorderFactory.createTitledBorder("Buscar por nombre, cédula o código..."));
        
        // Filtro en tiempo real
        txtBuscar.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                actualizarTabla(txtBuscar.getText());
            }
        });

        modelo = new DefaultTableModel(new String[]{"ID", "Cédula", "Nombre", "Código", "Deuda"}, 0);
        tabla = new JTable(modelo);

        add(txtBuscar, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        actualizarTabla(""); // Carga inicial automática
    }


    public void actualizarTabla(String filtro) {
        modelo.setRowCount(0);
        // IMPORTANTE: Usar la instancia única
        for (Cliente c : GestionSistema.getInstancia().getClientes()) {
            String busqueda = (c.getNombre() + " " + c.getApellido() + " " + c.getCedula()).toLowerCase();
            
            if (busqueda.contains(filtro.toLowerCase())) {
                modelo.addRow(new Object[]{
                    c.getId(),               // El ID autogenerado estático
                    c.getCedula(),
                    c.getNombreCompleto(),   // Método de Persona
                    c.getCodigoCliente(),
                    "RD$ " + c.getDeuda(),
                    c.isEstado() ? "Activo" : "Inactivo"
                });
            }
        }
    }
}
