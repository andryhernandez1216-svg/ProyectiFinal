package Visual;

import Ligca.Plan;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PanelPlanes extends JPanel {
    private JPanel contenedorCartas;
    private CardLayout cardInterno;
    
    // Componentes del formulario
    private JTextField txtNombre, txtPrecio, txtVelocidad;
    private String tipoSeleccionado = "";

    public PanelPlanes() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        cardInterno = new CardLayout();
        contenedorCartas = new JPanel(cardInterno);
        
        // 1. Pantalla de Selección de Tipo
        contenedorCartas.add(crearPantallaSeleccionTipo(), "seleccion");
        
        // 2. Pantalla de Formulario
        contenedorCartas.add(crearPantallaFormulario(), "formulario");

        add(contenedorCartas, BorderLayout.CENTER);
        
    }

    private JPanel crearPantallaSeleccionTipo() {
        JPanel pnl = new JPanel(new GridLayout(1, 3, 20, 0));
        pnl.setBackground(Color.WHITE);
        pnl.setBorder(new EmptyBorder(50, 50, 50, 50));

        pnl.add(crearBotonTipo("INTERNET", new Color(0, 43, 92)));
        pnl.add(crearBotonTipo("TV", new Color(225, 0, 110)));
        pnl.add(crearBotonTipo("TELEFONIA", Color.GRAY));

        return pnl;
    }

    private JButton crearBotonTipo(String tipo, Color color) {
        JButton btn = new JButton(tipo);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        
        btn.addActionListener(e -> {
            this.tipoSeleccionado = tipo;
            // Ajustar campos según el tipo si es necesario
            if(tipo.equals("TV")) txtVelocidad.setText("N/A (Canales)");
            else txtVelocidad.setText("");
            
            cardInterno.show(contenedorCartas, "formulario");
        });
        return btn;
    }

    private JPanel crearPantallaFormulario() {
        JPanel pnl = new JPanel(new GridBagLayout());
        pnl.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtNombre = new JTextField(20);
        txtPrecio = new JTextField(20);
        txtVelocidad = new JTextField(20);

        gbc.gridx = 0; gbc.gridy = 0; pnl.add(new JLabel("Nombre del Plan:"), gbc);
        gbc.gridx = 1; pnl.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 1; pnl.add(new JLabel("Precio Mensual (RD$):"), gbc);
        gbc.gridx = 1; pnl.add(txtPrecio, gbc);

        gbc.gridx = 0; gbc.gridy = 2; pnl.add(new JLabel("Velocidad / Descripción:"), gbc);
        gbc.gridx = 1; pnl.add(txtVelocidad, gbc);

        JButton btnGuardar = new JButton("Guardar Plan");
        btnGuardar.setBackground(new Color(0, 43, 92));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.addActionListener(e -> guardar());

        JButton btnVolver = new JButton("Cancelar");
        btnVolver.addActionListener(e -> cardInterno.show(contenedorCartas, "seleccion"));

        gbc.gridx = 0; gbc.gridy = 3; pnl.add(btnVolver, gbc);
        gbc.gridx = 1; pnl.add(btnGuardar, gbc);

        return pnl;
    }
    

    private void guardar() {
        try {
            Plan p = new Plan(
                "PLN-" + System.currentTimeMillis(), 
                txtNombre.getText(), 
                "Servicio " + tipoSeleccionado,
                txtVelocidad.getText(), 
                Float.parseFloat(txtPrecio.getText()), 
                tipoSeleccionado, 12, true
            );
            GestionSistema.getInstancia().agregarPlan(p);
            JOptionPane.showMessageDialog(this, "Plan de " + tipoSeleccionado + " guardado.");
            cardInterno.show(contenedorCartas, "seleccion");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en los datos.");
        }
    }
}