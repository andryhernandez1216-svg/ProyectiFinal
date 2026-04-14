package Visual;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import Ligca.Usuario;

public class PanelRegistroUsuario extends JPanel {
    private JTextField txtNom, txtApe, txtCed, txtPass;
    private JComboBox<String> cbRol;

    public PanelRegistroUsuario() {
        setBackground(Color.WHITE);
        setLayout(null);

        // --- Interfaz ---
        JLabel titulo = new JLabel("REGISTRO DE PERSONAL NUEVO");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setBounds(50, 30, 400, 30);
        add(titulo);

        txtCed = crearCampo("Cédula:", 100);
        txtNom = crearCampo("Nombre:", 170);
        txtApe = crearCampo("Apellido:", 240);
        txtPass = crearCampo("Contraseña para el Login:", 310);

        JLabel lblRol = new JLabel("Rol asignado:");
        lblRol.setBounds(50, 380, 150, 20);
        add(lblRol);

        cbRol = new JComboBox<>(new String[]{"COMERCIAL", "TRABAJADOR", "ADMINISTRATIVO"});
        cbRol.setBounds(50, 405, 300, 35);
        add(cbRol);

        JButton btnGuardar = new JButton("DAR DE ALTA EN SISTEMA");
        btnGuardar.setBounds(50, 480, 250, 45);
        btnGuardar.setBackground(new Color(225, 0, 110));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        btnGuardar.addActionListener(e -> guardarNuevoUsuario());
        add(btnGuardar);
    }

    private JTextField crearCampo(String label, int y) {
        JLabel lbl = new JLabel(label);
        lbl.setBounds(50, y, 200, 20);
        add(lbl);
        JTextField tf = new JTextField();
        tf.setBounds(50, y + 25, 300, 30);
        add(tf);
        return tf;
    }

    private void guardarNuevoUsuario() {
        // 1. Validaciones normales (esto es rápido)
        if(txtCed.getText().isEmpty() || txtNom.getText().isEmpty()){
            return;
        }

        // 2. Ejecutar la red en un hilo separado para que NO se frise
        new Thread(() -> {
            try {
                Usuario nuevo = new Usuario(
                    txtCed.getText().trim(), txtNom.getText().trim(), 
                    txtApe.getText().trim(), "809-000-0000", 
                    "empleado@altice.do", "Sede", new Date(), 
                    cbRol.getSelectedItem().toString(), txtPass.getText().trim()
                );

                // Esto es lo que tardaba y frisaba la pantalla
                ArrayList<Usuario> lista = SocketCliente.recibirDatos("USUARIOS");
                lista.add(nuevo);
                boolean exito = SocketCliente.enviarDatos("USUARIOS", lista);

                // 3. Volver al hilo de la interfaz para mostrar el mensaje
                SwingUtilities.invokeLater(() -> {
                    if (exito) {
                        JOptionPane.showMessageDialog(this, "¡Sincronizado con éxito!");
                        limpiarCampos();
                    } else {
                        JOptionPane.showMessageDialog(this, "Error: Servidor no disponible.");
                    }
                });

            } catch (Exception ex) {
                SwingUtilities.invokeLater(() -> 
                    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage()));
            }
        }).start(); // ¡Importante! Aquí arranca el hilo separado
    }

    private void limpiarCampos() {
        txtNom.setText(""); txtApe.setText(""); txtCed.setText(""); txtPass.setText("");
    }
}