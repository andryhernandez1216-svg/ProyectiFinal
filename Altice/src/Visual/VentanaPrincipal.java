package Visual;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import Ligca.Usuario;

public class VentanaPrincipal extends JFrame {

    private JPanel barraSuperior, contenedorCentral;
    private CardLayout cardLayout;
    private Usuario usuarioActual;

    private PanelDashboard pnlDash;
    private PanelClientes pnlBuscarClientes;
    private PanelVentas pnlVentaNueva;
    private PanelReportes pnlReportes;
    private PanelPlanes pnlGestionPlanes;
    // --- 1. FALTA DECLARAR LA VARIABLE AQUÍ ---
    private PanelRegistroUsuario pnlRegistro; 

    private final Color AZUL_ALTICE = new Color(0, 43, 92);
    private final Color MAGENTA_ALTICE = new Color(225, 0, 110);
    private final Color BLANCO_PURO = Color.WHITE;
    private final Color GRIS_FONDO = new Color(245, 246, 250);

    public VentanaPrincipal(Usuario usuarioLogueado) {
        this.usuarioActual = usuarioLogueado;
        
        setTitle("Altice Sales System");
        setSize(1366, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(GRIS_FONDO);
        setLayout(new BorderLayout());

        inicializarComponentes();
        
        if (usuarioActual.esAdministrativo()) {
            cardLayout.show(contenedorCentral, "dash");
        } else {
            cardLayout.show(contenedorCentral, "sel_cli");
        }
    }

    private void inicializarComponentes() {
        // --- 1. BARRA SUPERIOR (Se mantiene igual) ---
        barraSuperior = new JPanel(new BorderLayout());
        barraSuperior.setBackground(AZUL_ALTICE);
        barraSuperior.setPreferredSize(new Dimension(0, 70));
        barraSuperior.setBorder(new EmptyBorder(0, 20, 0, 20));

        JLabel lblLogo = new JLabel("altice");
        lblLogo.setFont(new Font("Arial", Font.BOLD, 28));
        lblLogo.setForeground(Color.WHITE);
        barraSuperior.add(lblLogo, BorderLayout.WEST);

        JPanel pnlNavBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 12));
        pnlNavBotones.setOpaque(false);

        String rol = usuarioActual.getRol().toUpperCase();

        if (rol.equals("ADMINISTRATIVO")) {
            pnlNavBotones.add(crearBotonNavbar("Dashboard", "dash"));
        }
        
        pnlNavBotones.add(crearBotonNavbar("Clientes", "sel_cli"));
        pnlNavBotones.add(crearBotonNavbar("Ventas", "sel_venta"));

        if (rol.equals("ADMINISTRATIVO") || rol.equals("TRABAJADOR")) {
            pnlNavBotones.add(crearBotonNavbar("Planes", "nuevo_plan"));
            pnlNavBotones.add(crearBotonNavbar("Reportes", "rep_periodo"));
        }

        if (rol.equals("ADMINISTRATIVO")) {
            // El botón llama al comando "reg_user"
            pnlNavBotones.add(crearBotonNavbar("Usuarios", "reg_user"));
        }

        barraSuperior.add(pnlNavBotones, BorderLayout.CENTER);

        JLabel lblUser = new JLabel(usuarioActual.getNombre() + " (" + usuarioActual.getRol() + ")");
        lblUser.setForeground(Color.WHITE);
        lblUser.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        barraSuperior.add(lblUser, BorderLayout.EAST);

        // --- 2. CONTENEDOR CENTRAL ---
        cardLayout = new CardLayout();
        contenedorCentral = new JPanel(cardLayout);
        contenedorCentral.setBorder(new EmptyBorder(20, 20, 20, 20));
        contenedorCentral.setOpaque(false);

        pnlBuscarClientes = new PanelClientes();
        pnlVentaNueva = new PanelVentas(usuarioActual);
        
        if (rol.equals("ADMINISTRATIVO")) {
            pnlDash = new PanelDashboard();
            contenedorCentral.add(pnlDash, "dash");
            
            // --- 2. AQUÍ ESTABA EL ERROR: DEBES AGREGAR EL PANEL DE REGISTRO ---
            pnlRegistro = new PanelRegistroUsuario();
            contenedorCentral.add(pnlRegistro, "reg_user"); 
        }
        
        contenedorCentral.add(crearPanelSeleccion("Gestión de Clientes", 
            new String[]{"Nuevo Cliente", "Buscar Existente"}, 
            new String[]{"nuevo_cli", "buscar_cli"}), "sel_cli");
            
        contenedorCentral.add(crearPanelSeleccion("Ventas", 
            new String[]{"Realizar Factura", "Historial"}, 
            new String[]{"nueva_fact", "rep_periodo"}), "sel_venta");

        contenedorCentral.add(pnlBuscarClientes, "buscar_cli");
        contenedorCentral.add(pnlVentaNueva, "nueva_fact");

        // Agregar este panel faltante si no estaba
        contenedorCentral.add(new PanelNuevoCliente(), "nuevo_cli");

        if (rol.equals("ADMINISTRATIVO") || rol.equals("TRABAJADOR")) {
            pnlReportes = new PanelReportes();
            pnlGestionPlanes = new PanelPlanes();
            contenedorCentral.add(pnlReportes, "rep_periodo");
            contenedorCentral.add(pnlGestionPlanes, "nuevo_plan");
        }

        add(barraSuperior, BorderLayout.NORTH);
        add(contenedorCentral, BorderLayout.CENTER);
    }

    private JButton crearBotonNavbar(String texto, String comando) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(AZUL_ALTICE);
        btn.setBorder(new EmptyBorder(10, 20, 10, 20));
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setForeground(MAGENTA_ALTICE);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setForeground(Color.WHITE);
            }
        });

        btn.addActionListener(e -> {
            // --- 3. REFRESCAR ESTADÍSTICAS SI ES EL DASH ---
            if (comando.equals("dash") && pnlDash != null) pnlDash.refrescarEstadisticas();
            
            cardLayout.show(contenedorCentral, comando);
            
            // Forzar repintado para evitar que se quede "en blanco"
            contenedorCentral.revalidate();
            contenedorCentral.repaint();
        });

        return btn;
    }

    private JPanel crearPanelSeleccion(String titulo, String[] opciones, String[] comandos) {
        JPanel pnl = new JPanel(new BorderLayout());
        pnl.setOpaque(false);
        JLabel lbl = new JLabel(titulo);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lbl.setBorder(new EmptyBorder(10, 10, 30, 0));
        pnl.add(lbl, BorderLayout.NORTH);

        JPanel cuerpo = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        cuerpo.setOpaque(false);
        for (int i = 0; i < opciones.length; i++) {
            final String cmd = comandos[i];
            
            // Si eres comercial y el botón de submenú lleva a reportes, no lo pongas
            if (cmd.equals("rep_periodo") && usuarioActual.getRol().equalsIgnoreCase("COMERCIAL")) {
                continue;
            }

            JButton btn = new JButton(opciones[i]);
            btn.setPreferredSize(new Dimension(320, 200));
            btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
            btn.setBackground(BLANCO_PURO);
            btn.setForeground(AZUL_ALTICE);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
            
            btn.addActionListener(e -> {
                cardLayout.show(contenedorCentral, cmd);
            });
            cuerpo.add(btn);
        }
        pnl.add(cuerpo, BorderLayout.CENTER);
        return pnl;
    }
}