package Visual;

import Ligca.Cliente;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Date;

public class PanelNuevoCliente extends JPanel {

    private JTextField txtCedula, txtNombre, txtApellido, txtTelefono, txtEmail, txtDireccion, txtCod, txtIdVista;
    private JComboBox<String> cbTipo;

    public PanelNuevoCliente() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // Panel de campos (GridLayout para organización limpia)
        JPanel pnlCampos = new JPanel(new GridLayout(9, 2, 10, 10));
        
        // 1. Campo ID: No modificable
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

        // Agregar al panel
        pnlCampos.add(new JLabel("ID Sistema:"));    pnlCampos.add(txtIdVista);
        pnlCampos.add(new JLabel("Nombre:"));        pnlCampos.add(txtNombre);
        pnlCampos.add(new JLabel("Apellido:"));      pnlCampos.add(txtApellido);
        pnlCampos.add(new JLabel("Cédula:"));        pnlCampos.add(txtCedula);
        pnlCampos.add(new JLabel("Teléfono:"));      pnlCampos.add(txtTelefono);
        pnlCampos.add(new JLabel("Email:"));         pnlCampos.add(txtEmail);
        pnlCampos.add(new JLabel("Dirección:"));     pnlCampos.add(txtDireccion);
        pnlCampos.add(new JLabel("Código Cliente:")); pnlCampos.add(txtCod);
        pnlCampos.add(new JLabel("Tipo:"));          pnlCampos.add(cbTipo);

        // Botón Guardar
        JButton btnGuardar = new JButton("Registrar Nuevo Cliente");
        btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnGuardar.addActionListener(e -> registrar());

        add(pnlCampos, BorderLayout.CENTER);
        add(btnGuardar, BorderLayout.SOUTH);
    }

    private void registrar() {
        // Validación estricta de campos vacíos
        if (esCampoVacio(txtNombre) || esCampoVacio(txtApellido) || esCampoVacio(txtCedula) || 
            esCampoVacio(txtCod) || esCampoVacio(txtTelefono)) {
            JOptionPane.showMessageDialog(this, "Error: Debe completar todos los campos obligatorios.", "Campos Incompletos", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Se crea el cliente pasando todos los atributos de tu clase Cliente
            // Nota: El ID se genera solo dentro de la clase Persona al llamar a super()
            Cliente nuevo = new Cliente(
                txtCedula.getText(), 
                txtNombre.getText(), 
                txtApellido.getText(), 
                txtTelefono.getText(), 
                txtEmail.getText(), 
                txtDireccion.getText(), 
                new Date(),                  // fechaRegistro
                txtCod.getText(), 
                cbTipo.getSelectedItem().toString(), 
                true,                        // estado inicial: activo
                0.0f,                        // deuda inicial: 0
                0                            // pagos atrasados inicial: 0
            );

            // Guardar en la lista global
            GestionSistema.getInstancia().agregarCliente(nuevo);
            
            // Confirmación y Limpieza
            JOptionPane.showMessageDialog(this, "¡Cliente " + nuevo.getNombre() + " registrado exitosamente!");
            limpiarCampos();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en los datos: " + ex.getMessage());
        }
    }

    private boolean esCampoVacio(JTextField campo) {
        return campo.getText().trim().isEmpty();
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtCedula.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        txtDireccion.setText("");
        txtCod.setText("");
        cbTipo.setSelectedIndex(0);
    }
}