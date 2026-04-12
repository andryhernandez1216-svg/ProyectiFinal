package Visual;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Date;
import Ligca.Usuario;

public class VentanaLogin extends JFrame {

    private JTextField txtUser;
    private JPasswordField txtPass;
    
    // Colores corporativos Altice
    private final Color AZUL_ALTICE = new Color(0, 43, 92);
    private final Color MAGENTA_ALTICE = new Color(225, 0, 110);

    public VentanaLogin() {
        setTitle("Altice - Acceso al Sistema");
        setSize(400, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel Principal con fondo azul
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(AZUL_ALTICE);
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(new EmptyBorder(40, 40, 40, 40));

        // Logo Texto
        JLabel lblLogo = new JLabel("altice");
        lblLogo.setFont(new Font("Arial", Font.BOLD, 52));
        lblLogo.setForeground(Color.WHITE);
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Campos de texto
        txtUser = new JTextField();
        txtUser.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        
        txtPass = new JPasswordField();
        txtPass.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));

        // Botón Entrar
        JButton btnEntrar = new JButton("INICIAR SESIÓN");
        btnEntrar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        btnEntrar.setBackground(MAGENTA_ALTICE);
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnEntrar.setFocusPainted(false);
        btnEntrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEntrar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Agregar componentes al panel
        panelPrincipal.add(lblLogo);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 50)));
        panelPrincipal.add(crearEtiqueta("USUARIO:"));
        panelPrincipal.add(txtUser);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
        panelPrincipal.add(crearEtiqueta("CONTRASEÑA:"));
        panelPrincipal.add(txtPass);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 40)));
        panelPrincipal.add(btnEntrar);

        // Evento del botón
        btnEntrar.addActionListener(e -> validarAcceso());

        add(panelPrincipal);
    }

    private JLabel crearEtiqueta(String texto) {
        JLabel label = new JLabel(texto);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(new EmptyBorder(0, 0, 5, 0));
        return label;
    }

    private void validarAcceso() {
        String user = txtUser.getText();
        String pass = new String(txtPass.getPassword());

        // Credenciales de prueba integradas con tu lógica de Usuario
        if (user.equals("admin") && pass.equals("1234")) {
            // Creamos el objeto Usuario según tu constructor de Usuario.java
            Usuario u = new Usuario(
                "402-1234567-8", "Admin", "Altice", 
                "809-555-5555", "admin@altice.com.do", "Av. Churchill", 
                new Date(), "ADMINISTRATIVO"
            );
            
            // Abrimos la principal pasando el usuario validado
            new VentanaPrincipal(u).setVisible(true);
            this.dispose(); // Cerramos el login
        } else if (user.equals("comercial") && pass.equals("1234")) {
            Usuario u = new Usuario(
                "402-9876543-2", "Vendedor", "Comercial", 
                "809-444-4444", "ventas@altice.com.do", "Sambil", 
                new Date(), "COMERCIAL"
            );
            new VentanaPrincipal(u).setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error de Acceso", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * MÉTODO MAIN: El punto de entrada del programa.
     */
    public static void main(String[] args) {
        try {
            // Ajusta la apariencia al sistema operativo actual (Windows, Mac, Linux)
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Ejecutar la interfaz en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            new VentanaLogin().setVisible(true);
        });
    }
}
