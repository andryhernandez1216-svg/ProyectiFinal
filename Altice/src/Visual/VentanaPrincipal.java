package Visual;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import Ligca.Usuario;

public class VentanaPrincipal extends JFrame {

    private JPanel menuLateral, contenedorCentral;
    private CardLayout cardLayout;
    private Usuario usuarioActual;

    private PanelDashboard pnlDash;
    private PanelClientes pnlBuscarClientes;
    private PanelNuevoCliente pnlNuevoCliente;
    private PanelVentas pnlVentaNueva;
    private PanelPlanes pnlGestionPlanes;
    private PanelReportes pnlReportes;
    private PanelRegistroUsuario pnlRegistro; 

    private final Color AZUL_ALTICE = new Color(0, 43, 92);
    private final Color GRIS_FONDO = new Color(240, 242, 245);

    public VentanaPrincipal(Usuario usuarioLogueado) {
        this.usuarioActual = usuarioLogueado;
        
        String nombreCompleto = (usuarioActual != null) ? 
                                usuarioActual.getNombre() + " " + usuarioActual.getApellido() : "Invitado";
        
        setTitle("Altice Sales System - " + nombreCompleto + " [" + usuarioActual.getRol() + "]");
        setSize(1280, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        inicializarComponentes();
        
        // --- REDIRECCIÓN INICIAL SEGÚN ROL ---
        // Si no es admin, no puede entrar al dashboard, lo mandamos a clientes.
        if (usuarioActual.esAdministrativo()) {
            cardLayout.show(contenedorCentral, "dash");
        } else {
            cardLayout.show(contenedorCentral, "sel_cli");
        }
    }

    private void inicializarComponentes() {
        // --- 1. MENÚ LATERAL ---
        menuLateral = new JPanel();
        menuLateral.setBackground(AZUL_ALTICE);
        menuLateral.setPreferredSize(new Dimension(280, 0));
        menuLateral.setLayout(new BorderLayout());

        JLabel lblLogo = new JLabel("altice", SwingConstants.CENTER);
        lblLogo.setFont(new Font("Arial", Font.BOLD, 45));
        lblLogo.setForeground(Color.WHITE);
        lblLogo.setBorder(new EmptyBorder(40, 0, 40, 0));
        menuLateral.add(lblLogo, BorderLayout.NORTH);

        JPanel pnlBotones = new JPanel();
        pnlBotones.setLayout(new BoxLayout(pnlBotones, BoxLayout.Y_AXIS));
        pnlBotones.setOpaque(false);
        pnlBotones.setBorder(new EmptyBorder(0, 15, 0, 15));

        // --- FILTRADO DE BOTONES DEL MENÚ ---
        String rol = usuarioActual.getRol().toUpperCase();

        // PRINCIPAL (Dashboard): Solo Administrativo
        if (rol.equals("ADMINISTRATIVO")) {
            pnlBotones.add(crearBotonMenu("Principal", "dash"));
            pnlBotones.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        // CLIENTES: Todos
        pnlBotones.add(crearBotonMenu("Clientes", "sel_cli"));
        pnlBotones.add(Box.createRigidArea(new Dimension(0, 10)));

        // VENTAS Y FACTURAS: Todos
        pnlBotones.add(crearBotonMenu("Ventas y Facturas", "sel_venta"));
        pnlBotones.add(Box.createRigidArea(new Dimension(0, 10)));

        // PLANES Y REPORTES: Administrativo y Trabajador
        if (rol.equals("ADMINISTRATIVO") || rol.equals("TRABAJADOR")) {
            pnlBotones.add(crearBotonMenu("Planes / Servicios", "nuevo_plan"));
            pnlBotones.add(Box.createRigidArea(new Dimension(0, 10)));
            pnlBotones.add(crearBotonMenu("Reportes", "rep_periodo"));
        }

        // REGISTRAR USUARIO: Solo Administrativo
        if (rol.equals("ADMINISTRATIVO")) {
            pnlBotones.add(Box.createRigidArea(new Dimension(0, 10)));
            pnlBotones.add(crearBotonMenu("Registrar Usuario", "reg_user"));
        }

        menuLateral.add(pnlBotones, BorderLayout.CENTER);

        // --- 2. CONTENEDOR CENTRAL ---
        cardLayout = new CardLayout();
        contenedorCentral = new JPanel(cardLayout);

        // Instancias básicas
        pnlBuscarClientes = new PanelClientes();
        pnlNuevoCliente = new PanelNuevoCliente();
        pnlVentaNueva = new PanelVentas(usuarioActual);

        // Submenús (Ventas y Clientes disponibles para todos)
        contenedorCentral.add(crearPanelSeleccion("Gestión de Clientes", 
            new String[]{"Ingresar Cliente Nuevo", "Buscar Cliente Existente"}, 
            new String[]{"nuevo_cli", "buscar_cli"}), "sel_cli");
            
        contenedorCentral.add(crearPanelSeleccion("Ventas y Facturación", 
            new String[]{"Realizar Factura", "Historial de Facturas"}, 
            new String[]{"nueva_fact", "rep_periodo"}), "sel_venta");

        // Paneles condicionales
        contenedorCentral.add(pnlNuevoCliente, "nuevo_cli");
        contenedorCentral.add(pnlBuscarClientes, "buscar_cli");
        contenedorCentral.add(pnlVentaNueva, "nueva_fact");

        // Paneles de Trabajador / Admin
        if (rol.equals("ADMINISTRATIVO") || rol.equals("TRABAJADOR")) {
            pnlGestionPlanes = new PanelPlanes();
            pnlReportes = new PanelReportes();
            contenedorCentral.add(pnlGestionPlanes, "nuevo_plan");
            contenedorCentral.add(pnlReportes, "rep_periodo");
        }

        // Paneles exclusivos de Admin
        if (rol.equals("ADMINISTRATIVO")) {
            pnlDash = new PanelDashboard();
            pnlRegistro = new PanelRegistroUsuario();
            contenedorCentral.add(pnlDash, "dash");
            contenedorCentral.add(pnlRegistro, "reg_user");
        }

        add(menuLateral, BorderLayout.WEST);
        add(contenedorCentral, BorderLayout.CENTER);
    }

    private JPanel crearPanelSeleccion(String titulo, String[] opciones, String[] comandos) {
        JPanel pnl = new JPanel(new BorderLayout());
        pnl.setBackground(GRIS_FONDO);
        JLabel lbl = new JLabel(titulo);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lbl.setBorder(new EmptyBorder(30, 40, 20, 0));
        pnl.add(lbl, BorderLayout.NORTH);

        JPanel cuerpo = new JPanel(new FlowLayout(FlowLayout.LEFT, 40, 40));
        cuerpo.setOpaque(false);
        for (int i = 0; i < opciones.length; i++) {
            final String cmd = comandos[i];
            
            // --- VALIDACIÓN DE SUBMENÚ SEGÚN ROL ---
            // Si el comando es reportes y el usuario es comercial, no creamos este botón
            if (cmd.equals("rep_periodo") && usuarioActual.getRol().equalsIgnoreCase("COMERCIAL")) {
                continue; 
            }

            JButton btn = new JButton(opciones[i]);
            btn.setPreferredSize(new Dimension(300, 180));
            btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
            btn.setBackground(Color.WHITE);
            btn.setFocusPainted(false);
            
            btn.addActionListener(e -> {
                if (cmd.equals("buscar_cli")) pnlBuscarClientes.actualizarTabla(""); 
                if (cmd.equals("nueva_fact")) pnlVentaNueva.refrescarCombos(); 
                if (cmd.equals("rep_periodo")) pnlReportes.filtrarPorPeriodo(); 
                if (cmd.equals("dash")) pnlDash.refrescarEstadisticas();

                cardLayout.show(contenedorCentral, cmd);
            });
            
            cuerpo.add(btn);
        }
        pnl.add(cuerpo, BorderLayout.CENTER);
        return pnl;
    }

    private JButton crearBotonMenu(String texto, String comando) {
        JButton btn = new JButton(texto);
        btn.setMaximumSize(new Dimension(250, 45));
        btn.setBackground(AZUL_ALTICE);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(new EmptyBorder(0, 20, 0, 0));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFocusPainted(false);
        
        btn.addActionListener(e -> {
            // Refrescar datos al navegar
            if (comando.equals("rep_periodo") && pnlReportes != null) pnlReportes.filtrarPorPeriodo();
            if (comando.equals("nueva_fact")) pnlVentaNueva.refrescarCombos();
            if (comando.equals("dash") && pnlDash != null) pnlDash.refrescarEstadisticas();
            
            cardLayout.show(contenedorCentral, comando);
        });
        return btn;
    }
}