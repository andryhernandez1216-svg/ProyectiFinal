package Visual;

import javax.swing.*;
import java.awt.*;
import java.io.*;
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

        // Campos básicos (puedes añadir más según tu constructor)
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
        // Validar que no haya campos vacíos
        if(txtCed.getText().isEmpty() || txtNom.getText().isEmpty() || txtPass.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "La cédula, nombre y contraseña son obligatorios.");
            return;
        }

        try {
            // Creamos el objeto con trims para asegurar limpieza
            Usuario nuevo = new Usuario(
                txtCed.getText().trim(), 
                txtNom.getText().trim(), 
                txtApe.getText().trim(),
                "809-000-0000", 
                "empleado@altice.do", 
                "Sede", 
                new Date(), 
                cbRol.getSelectedItem().toString(),
                txtPass.getText().trim()
            );

            // Cargar lista actual, añadir y sobrescribir
            ArrayList<Usuario> lista = cargarUsuarios();
            lista.add(nuevo);
            
            File archivo = new File("usuarios.dat");
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
                oos.writeObject(lista);
            }

            JOptionPane.showMessageDialog(this, "¡Usuario " + nuevo.getNombre() + " registrado con éxito!");
            limpiarCampos();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static ArrayList<Usuario> cargarUsuarios() {
        File archivo = new File("usuarios.dat");
        if (!archivo.exists() || archivo.length() == 0) return new ArrayList<>();
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            Object data = ois.readObject();
            if (data instanceof ArrayList) {
                return (ArrayList<Usuario>) data;
            }
            return new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Error al cargar usuarios: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    

    private void limpiarCampos() {
        txtNom.setText(""); txtApe.setText(""); txtCed.setText(""); txtPass.setText("");
    }
}