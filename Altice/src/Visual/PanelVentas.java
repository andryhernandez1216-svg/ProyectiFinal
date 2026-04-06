package Visual;


import Ligca.*;
import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class PanelVentas extends JPanel {
    private JComboBox<Cliente> cbClientes;
    private JComboBox<Plan> cbPlanes;
    private JTextArea areaFactura;

    public PanelVentas() {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        cbClientes = new JComboBox<>();
        cbPlanes = new JComboBox<>();
        areaFactura = new JTextArea(10, 30);
        areaFactura.setEditable(false);

        // Cargar datos desde la instancia global
        for(Cliente c : GestionSistema.getInstancia().getClientes()) cbClientes.addItem(c);
        for(Plan p : GestionSistema.getInstancia().getPlanes()) cbPlanes.addItem(p);

        gbc.gridx = 0; gbc.gridy = 0; add(new JLabel("Cliente:"), gbc);
        gbc.gridx = 1; add(cbClientes, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1; add(new JLabel("Plan:"), gbc);
        gbc.gridx = 1; add(cbPlanes, gbc);

        JButton btnGenerar = new JButton("Generar Factura");
        btnGenerar.addActionListener(e -> {
            try {
                Cliente c = (Cliente) cbClientes.getSelectedItem();
                Plan p = (Plan) cbPlanes.getSelectedItem();
                
                // 1. Crear Contrato (según tu lógica)
                Contrato contra = new Contrato("CON-"+System.currentTimeMillis(), c, p, new Date(), new Date(System.currentTimeMillis()+31536000000L), true);
                
                // 2. Crear Factura
                Factura fact = new Factura("FAC-"+System.currentTimeMillis(), c, contra, new Date());
                
                // 3. Agregar detalle (el plan)
                DetalleFactura detalle = new DetalleFactura("D01", p.getNombre(), 1, p.getPrecio());
                fact.agregarDetalle(detalle);
                
                areaFactura.setText(fact.toString() + "\nDetalles:\n" + detalle.toString());
                
                // Registrar en el sistema
                GestionSistema.getInstancia().agregarContrato(contra);
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        add(btnGenerar, gbc);
        gbc.gridy = 3;
        add(new JScrollPane(areaFactura), gbc);
    }
}
