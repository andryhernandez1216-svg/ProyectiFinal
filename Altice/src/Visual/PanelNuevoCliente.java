package Visual;

import Ligca.Cliente;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class PanelNuevoCliente extends JPanel {

    private JTextField txtCedula, txtNombre, txtApellido, txtTelefono, txtEmail, txtDireccion, txtCod, txtIdVista;
    private JComboBox<String> cbTipo;

    public PanelNuevoCliente() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setBackground(Color.WHITE);

        JPanel pnlCampos = new JPanel(new GridLayout(9, 2, 10, 10));
        pnlCampos.setBackground(Color.WHITE);
        
        txtIdVista = new JTextField("Asignado automáticamente");
        txtIdVista.setEditable(false);
        txtIdVista.setForeground(Color.GRAY);

        txtNombre = new JTextField();
        txtApellido = new JTextField();
        txtCedula = new JTextField();
        txtTelefono = new JTextField();
        txtEmail = new JTextField();
        txtDireccion = new JTextField();
        txtCod = new JTextField();
        cbTipo = new JComboBox<>(new String[]{"FISICA", "JURIDICO"});

        pnlCampos.add(new JLabel("ID Sistema:"));    pnlCampos.add(txtIdVista);
        pnlCampos.add(new JLabel("Nombre:"));        pnlCampos.add(txtNombre);
        pnlCampos.add(new JLabel("Apellido:"));      pnlCampos.add(txtApellido);
        pnlCampos.add(new JLabel("Cédula:"));        pnlCampos.add(txtCedula);
        pnlCampos.add(new JLabel("Teléfono:"));      pnlCampos.add(txtTelefono);
        pnlCampos.add(new JLabel("Email:"));         pnlCampos.add(txtEmail);
        pnlCampos.add(new JLabel("Dirección:"));     pnlCampos.add(txtDireccion);
        pnlCampos.add(new JLabel("Código Cliente:")); pnlCampos.add(txtCod);
        pnlCampos.add(new JLabel("Tipo:"));          pnlCampos.add(cbTipo);

        JButton btnGuardar = new JButton("Registrar Nuevo Cliente");
        btnGuardar.setBackground(new Color(225, 0, 110)); // Color Altice
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnGuardar.addActionListener(e -> registrar());

        add(pnlCampos, BorderLayout.CENTER);
        add(btnGuardar, BorderLayout.SOUTH);
    }

    private void registrar() {
        if (esCampoVacio(txtNombre) || esCampoVacio(txtApellido) || esCampoVacio(txtCedula) || 
            esCampoVacio(txtCod) || esCampoVacio(txtTelefono)) {
            JOptionPane.showMessageDialog(this, "Error: Debe completar todos los campos obligatorios.", "Campos Incompletos", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Ejecutar en hilo para no congelar la app mientras el servidor guarda
        new Thread(() -> {
            try {
                Cliente nuevo = new Cliente(
                    txtCedula.getText().trim(), 
                    txtNombre.getText().trim(), 
                    txtApellido.getText().trim(), 
                    txtTelefono.getText().trim(), 
                    txtEmail.getText().trim(), 
                    txtDireccion.getText().trim(), 
                    new Date(), 
                    txtCod.getText().trim(), 
                    cbTipo.getSelectedItem().toString(), 
                    true, 0.0f, 0
                );

                // PROTOCOLO DE RED
                ArrayList<Cliente> listaActual = SocketCliente.recibirDatos("CLIENTES");
                listaActual.add(nuevo);
                boolean exito = SocketCliente.enviarDatos("CLIENTES", listaActual);

                SwingUtilities.invokeLater(() -> {
                    if (exito) {
                        JOptionPane.showMessageDialog(this, "¡Cliente registrado y sincronizado en el servidor!");
                        limpiarCampos();
                    } else {
                        JOptionPane.showMessageDialog(this, "Error: No se pudo conectar con el servidor.");
                    }
                });

            } catch (Exception ex) {
                SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(this, "Error en los datos: " + ex.getMessage()));
            }
        }).start();
    }

    private boolean esCampoVacio(JTextField campo) { return campo.getText().trim().isEmpty(); }

    private void limpiarCampos() {
        txtNombre.setText(""); txtApellido.setText(""); txtCedula.setText("");
        txtTelefono.setText(""); txtEmail.setText(""); txtDireccion.setText("");
        txtCod.setText(""); cbTipo.setSelectedIndex(0);
    }
}