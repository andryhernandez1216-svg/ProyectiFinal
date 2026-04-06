package Visual;



import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import Ligca.Usuario;
import Ligca.SistemaAltice;

public class VentanaPrincipal extends JFrame {

    private JPanel menuLateral;
    private JPanel contenedorCentral;
    private CardLayout cardLayout;
    
    private Usuario usuarioActual;
    private SistemaAltice logica;

    // Paneles (Instancias únicas para persistencia de datos en la vista)
    private PanelDashboard pnlDash;
    private PanelClientes pnlClientes;
    private PanelVentas pnlVentas;
    private PanelPlanes pnlPlanes;

    // Colores corporativos
    private final Color AZUL_ALTICE = new Color(0, 43, 92);
    private final Color MAGENTA_ALTICE = new Color(225, 0, 110);
    private final Color GRIS_FONDO = new Color(240, 242, 245);

    public VentanaPrincipal(Usuario usuarioLogueado) {
        this.usuarioActual = usuarioLogueado;
        this.logica = GestionSistema.getInstancia();

        setTitle("Altice Sales System - Sesión: " + usuarioActual.getNombreCompleto());
        setSize(1280, 800);
        setMinimumSize(new Dimension(1000, 700));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setLayout(new BorderLayout());

        inicializarComponentes();
        
        // Mostrar el Dashboard por defecto al iniciar
        cardLayout.show(contenedorCentral, "dash");
    }

    private void inicializarComponentes() {
        // 1. MENÚ LATERAL
        menuLateral = new JPanel();
        menuLateral.setBackground(AZUL_ALTICE);
        menuLateral.setPreferredSize(new Dimension(280, 0));
        menuLateral.setLayout(new BorderLayout());

        // Header del Menú (Logo)
        JLabel lblLogo = new JLabel("altice", SwingConstants.CENTER);
        lblLogo.setFont(new Font("Arial", Font.BOLD, 45));
        lblLogo.setForeground(Color.WHITE);
        lblLogo.setBorder(new EmptyBorder(40, 0, 40, 0));
        menuLateral.add(lblLogo, BorderLayout.NORTH);

        // Cuerpo del Menú (Botones)
        JPanel pnlBotones = new JPanel();
        pnlBotones.setOpaque(false);
        pnlBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));

        pnlBotones.add(crearBotonMenu("Dashboard", "dash"));

        if (usuarioActual.puedeGestionarClientes()) {
            pnlBotones.add(crearBotonMenu("Clientes (CRUD)", "cli"));
        }

        if (usuarioActual.puedeGestionarFacturacion()) {
            pnlBotones.add(crearBotonMenu("Ventas y Facturas", "venta"));
        }

        if (usuarioActual.puedeGestionarServicios()) {
            pnlBotones.add(crearBotonMenu("Planes / Servicios", "plan"));
        }

        menuLateral.add(pnlBotones, BorderLayout.CENTER);

        // Footer del Menú (Info Usuario)
        JPanel pnlUser = new JPanel(new GridLayout(2, 1));
        pnlUser.setOpaque(false);
        pnlUser.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JLabel name = new JLabel(usuarioActual.getNombreCompleto());
        name.setForeground(Color.WHITE);
        name.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        JLabel rol = new JLabel("Rol: " + usuarioActual.getRol());
        rol.setForeground(Color.LIGHT_GRAY);
        rol.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        
        pnlUser.add(name);
        pnlUser.add(rol);
        menuLateral.add(pnlUser, BorderLayout.SOUTH);

        // 2. CONTENEDOR CENTRAL (CARDLAYOUT)
        cardLayout = new CardLayout();
        contenedorCentral = new JPanel(cardLayout);
        contenedorCentral.setBackground(GRIS_FONDO);

        // Inicializar Paneles
        pnlDash = new PanelDashboard();
        pnlClientes = new PanelClientes();
        pnlVentas = new PanelVentas();
        pnlPlanes = new PanelPlanes();

        // Agregar al CardLayout
        contenedorCentral.add(pnlDash, "dash");
        contenedorCentral.add(pnlClientes, "cli");
        contenedorCentral.add(pnlVentas, "venta");
        contenedorCentral.add(pnlPlanes, "plan");

        // Agregar todo a la ventana
        add(menuLateral, BorderLayout.WEST);
        add(contenedorCentral, BorderLayout.CENTER);
    }

    private JButton crearBotonMenu(String texto, String comando) {
        JButton btn = new JButton(texto);
        btn.setPreferredSize(new Dimension(250, 50));
        btn.setBackground(AZUL_ALTICE);
        btn.setForeground(Color.black);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(0, 25, 0, 0));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Eventos
        btn.addActionListener(e -> {
            // Acción especial antes de mostrar el panel
            if (comando.equals("dash")) pnlDash.refrescarEstadisticas();
            if (comando.equals("cli")) pnlClientes.actualizarTabla();
            
            cardLayout.show(contenedorCentral, comando);
            
            // Forzar actualización visual
            contenedorCentral.revalidate();
            contenedorCentral.repaint();
        });

        // Efecto Hover
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(0, 65, 140));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(AZUL_ALTICE);
            }
        });

        return btn;
    }
}
