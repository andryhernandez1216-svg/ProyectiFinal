package Visual;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import Ligca.Usuario;

public class VentanaLogin extends JFrame {

    private JTextField txtUser;
    private JPasswordField txtPass;
    private final Color AZUL_ALTICE = new Color(0, 43, 92);
    private final Color MAGENTA_ALTICE = new Color(225, 0, 110);

    public VentanaLogin() {
        setTitle("Altice - Acceso");
        setSize(400, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(AZUL_ALTICE);
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(new EmptyBorder(40, 40, 40, 40));

        JLabel lblLogo = new JLabel("altice");
        lblLogo.setFont(new Font("Arial", Font.BOLD, 52));
        lblLogo.setForeground(Color.WHITE);
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);

        txtUser = new JTextField();
        txtUser.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        
        txtPass = new JPasswordField();
        txtPass.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));

        JButton btnEntrar = new JButton("INICIAR SESIÓN");
        btnEntrar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        btnEntrar.setBackground(MAGENTA_ALTICE);
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEntrar.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelPrincipal.add(lblLogo);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 50)));
        panelPrincipal.add(crearEtiqueta("USUARIO (CÉDULA O NOMBRE):"));
        panelPrincipal.add(txtUser);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
        panelPrincipal.add(crearEtiqueta("CONTRASEÑA:"));
        panelPrincipal.add(txtPass);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 40)));
        panelPrincipal.add(btnEntrar);

        btnEntrar.addActionListener(e -> validarAcceso());
        add(panelPrincipal);
    }

    private JLabel crearEtiqueta(String texto) {
        JLabel label = new JLabel(texto);
        label.setForeground(Color.WHITE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private void validarAcceso() {
        String user = txtUser.getText().trim();
        String pass = new String(txtPass.getPassword()).trim();

        if (user.equals("admin") && pass.equals("1234")) {
            Usuario adminMaestro = new Usuario("000", "Admin", "Maestro", "000", "a@a.com", "Sede", new Date(), "ADMINISTRATIVO", "1234");
            new VentanaPrincipal(adminMaestro).setVisible(true);
            this.dispose();
            return; 
        }

        new Thread(() -> {
            try {
                ArrayList<Usuario> registrados = SocketCliente.recibirDatos("USUARIOS");
                Usuario encontrado = null;

                for (Usuario u : registrados) {
                    if (u.getCedula().equals(user) && u.getPassword().equals(pass)) {
                        encontrado = u;
                        break;
                    }
                }

                final Usuario uFinal = encontrado;
                SwingUtilities.invokeLater(() -> {
                    if (uFinal != null) {
                        new VentanaPrincipal(uFinal).setVisible(true);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Credenciales incorrectas o servidor no disponible.");
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaLogin().setVisible(true));
    }
}