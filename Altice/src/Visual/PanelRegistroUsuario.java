package Visual;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import Ligca.Usuario;

public class PanelRegistroUsuario extends JPanel {
    private JTextField txtNom, txtApe, txtCed, txtPass;
    private JComboBox<String> cbRol;
    private JButton btnGuardar;

    private final Color AZUL_ALTICE = new Color(0, 43, 92);
    private final Color MAGENTA_ALTICE = new Color(225, 0, 110);
    private final Color GRIS_FONDO = new Color(245, 246, 250);

    public PanelRegistroUsuario() {
        setLayout(new GridBagLayout());
        setBackground(GRIS_FONDO);

        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            new EmptyBorder(40, 50, 40, 50)
        ));

        JPanel pnlHeader = new JPanel(new GridLayout(2, 1));
        pnlHeader.setOpaque(false);
        
        JLabel titulo = new JLabel("Gestión de Personal", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(AZUL_ALTICE);
        
        JLabel subtitulo = new JLabel("Complete los datos para dar de alta a un nuevo colaborador", SwingConstants.CENTER);
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitulo.setForeground(Color.GRAY);

        pnlHeader.add(titulo);
        pnlHeader.add(subtitulo);
        pnlHeader.add(Box.createVerticalStrut(30));
        card.add(pnlHeader, BorderLayout.NORTH);

        JPanel pnlCuerpo = new JPanel(new GridLayout(3, 2, 30, 15));
        pnlCuerpo.setOpaque(false);
        pnlCuerpo.setBorder(new EmptyBorder(20, 0, 20, 0));

        txtCed = crearCampo(pnlCuerpo, "🆔 Cédula:");
        txtNom = crearCampo(pnlCuerpo, "👤 Nombre:");
        txtApe = crearCampo(pnlCuerpo, "👥 Apellido:");
        txtPass = crearCampo(pnlCuerpo, "🔑 Contraseña:");

        JPanel pnlRol = new JPanel(new BorderLayout(0, 5));
        pnlRol.setOpaque(false);
        JLabel lblRol = new JLabel("🛠️ Rol del Usuario:");
        lblRol.setFont(new Font("Segoe UI", Font.BOLD, 13));
        cbRol = new JComboBox<>(new String[]{"COMERCIAL", "TRABAJADOR", "ADMINISTRATIVO"});
        cbRol.setPreferredSize(new Dimension(0, 35));
        pnlRol.add(lblRol, BorderLayout.NORTH);
        pnlRol.add(cbRol, BorderLayout.CENTER);
        
        pnlCuerpo.add(pnlRol);
        card.add(pnlCuerpo, BorderLayout.CENTER);

        btnGuardar = new JButton("REGISTRAR COLABORADOR");
        btnGuardar.setPreferredSize(new Dimension(0, 50));
        btnGuardar.setBackground(MAGENTA_ALTICE);
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setBorder(null);
        
        btnGuardar.addActionListener(e -> guardarNuevoUsuario());
        card.add(btnGuardar, BorderLayout.SOUTH);

        add(card);
    }

    private JTextField crearCampo(JPanel pnl, String label) {
        JPanel contenedor = new JPanel(new BorderLayout(0, 5));
        contenedor.setOpaque(false);

        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        JTextField tf = new JTextField();
        tf.setPreferredSize(new Dimension(250, 35));
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tf.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            new EmptyBorder(0, 10, 0, 10)
        ));

        contenedor.add(lbl, BorderLayout.NORTH);
        contenedor.add(tf, BorderLayout.CENTER);
        pnl.add(contenedor);
        return tf;
    }

    private void guardarNuevoUsuario() {
        String cedula = txtCed.getText().trim();
        String nombre = txtNom.getText().trim();
        String pass = txtPass.getText().trim();

        if(cedula.isEmpty() || nombre.isEmpty() || pass.isEmpty()){
            JOptionPane.showMessageDialog(this, "Datos obligatorios faltantes", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        btnGuardar.setEnabled(false);
        btnGuardar.setText("Enviando credenciales...");

        new Thread(() -> {
            try {
                Usuario nuevo = new Usuario(cedula, nombre, txtApe.getText().trim(), 
                    "809-000-0000", "empleado@altice.do", "Sede", 
                    new Date(), cbRol.getSelectedItem().toString(), pass
                );

                Object respuesta = SocketCliente.recibirDatos("usuarios");
                ArrayList<Usuario> listaActual = new ArrayList<>();
                if (respuesta instanceof ArrayList<?>) {
                    for (Object obj : (ArrayList<?>) respuesta) {
                        if (obj instanceof Usuario) listaActual.add((Usuario) obj);
                    }
                }
                
                listaActual.add(nuevo);
                boolean exito = SocketCliente.enviarDatos("usuarios", listaActual);

                SwingUtilities.invokeLater(() -> {
                    if (exito) {
                        JOptionPane.showMessageDialog(this, "¡Colaborador registrado!");
                        limpiarCampos();
                    }
                    btnGuardar.setEnabled(true);
                    btnGuardar.setText("REGISTRAR COLABORADOR");
                });
            } catch (Exception ex) {
                SwingUtilities.invokeLater(() -> {
                    btnGuardar.setEnabled(true);
                    btnGuardar.setText("REGISTRAR COLABORADOR");
                });
            }
        }).start();
    }

    private void limpiarCampos() {
        txtNom.setText(""); txtApe.setText(""); txtCed.setText(""); txtPass.setText("");
    }
}