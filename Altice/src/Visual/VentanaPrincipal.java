package Visual;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import Ligca.Usuario;

public class VentanaPrincipal extends JFrame {

    private JPanel menuLateral, contenedorCentral;
    private CardLayout cardLayout;
    private Usuario usuarioActual;

    // Paneles Funcionales
    private PanelDashboard pnlDash;
    private PanelClientes pnlBuscarClientes;
    private PanelNuevoCliente pnlNuevoCliente;
    private PanelVentas pnlVentaNueva;
    private PanelPlanes pnlGestionPlanes;
    private PanelReportes pnlReportes; // <--- Ahora sí está integrado

    private final Color AZUL_ALTICE = new Color(0, 43, 92);
    private final Color GRIS_FONDO = new Color(240, 242, 245);

    public VentanaPrincipal(Usuario usuarioLogueado) {
        this.usuarioActual = usuarioLogueado;
        setTitle("Altice Sales System - " + usuarioActual.getNombreCompleto());
        setSize(1280, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        inicializarComponentes();
        cardLayout.show(contenedorCentral, "dash");
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

        JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        pnlBotones.setOpaque(false);
        pnlBotones.add(crearBotonMenu("Principal", "dash"));
        pnlBotones.add(crearBotonMenu("Clientes", "sel_cli"));
        pnlBotones.add(crearBotonMenu("Ventas y Facturas", "sel_venta"));
        pnlBotones.add(crearBotonMenu("Planes / Servicios", "sel_plan"));
        pnlBotones.add(crearBotonMenu("Reportes", "rep_periodo")); // Salto directo al panel de reportes

        menuLateral.add(pnlBotones, BorderLayout.CENTER);

        // --- 2. CONTENEDOR CENTRAL ---
        cardLayout = new CardLayout();
        contenedorCentral = new JPanel(cardLayout);

        // Instancias
        pnlDash = new PanelDashboard();
        pnlBuscarClientes = new PanelClientes();
        pnlNuevoCliente = new PanelNuevoCliente();
        pnlVentaNueva = new PanelVentas(usuarioActual);
        pnlGestionPlanes = new PanelPlanes();
        pnlReportes = new PanelReportes(); // <--- Inicialización

        // Paneles de Selección (Submenús)
        contenedorCentral.add(crearPanelSeleccion("Gestión de Clientes", 
            new String[]{"Ingresar Cliente Nuevo", "Buscar Cliente Existente"}, 
            new String[]{"nuevo_cli", "buscar_cli"}), "sel_cli");
            
        contenedorCentral.add(crearPanelSeleccion("Ventas y Facturación", 
            new String[]{"Realizar Factura", "Historial de Facturas"}, 
            new String[]{"nueva_fact", "rep_periodo"}), "sel_venta"); // El historial ahora apunta al reporte

        // Agregar Paneles de Trabajo Finales
        contenedorCentral.add(pnlDash, "dash");
        contenedorCentral.add(pnlNuevoCliente, "nuevo_cli");
        contenedorCentral.add(pnlBuscarClientes, "buscar_cli");
        contenedorCentral.add(pnlVentaNueva, "nueva_fact");
        contenedorCentral.add(pnlGestionPlanes, "nuevo_plan");
        contenedorCentral.add(pnlReportes, "rep_periodo"); // <--- Agregado al CardLayout

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
            JButton btn = new JButton(opciones[i]);
            btn.setPreferredSize(new Dimension(300, 180));
            btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
            btn.setBackground(Color.WHITE);
            btn.setFocusPainted(false);
            
            final String cmd = comandos[i];
            btn.addActionListener(e -> {
                // Lógica de sincronización antes de cambiar
                if (cmd.equals("nueva_fact")) pnlVentaNueva.refrescarCombos(); 
                if (cmd.equals("buscar_cli")) pnlBuscarClientes.actualizarTabla(""); 
                if (cmd.equals("rep_periodo")) pnlReportes.filtrarPorPeriodo(); // Carga datos al entrar
                
                cardLayout.show(contenedorCentral, cmd);
            });
            cuerpo.add(btn);
        }
        pnl.add(cuerpo, BorderLayout.CENTER);
        return pnl;
    }

    private JButton crearBotonMenu(String texto, String comando) {
        JButton btn = new JButton(texto);
        btn.setPreferredSize(new Dimension(250, 45));
        btn.setBackground(AZUL_ALTICE);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(new EmptyBorder(0, 20, 0, 0));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(e -> {
            // Si desde el menú lateral vas a reportes, refresca los datos automáticamente
            if (comando.equals("rep_periodo")) {
                pnlReportes.filtrarPorPeriodo();
            }
            cardLayout.show(contenedorCentral, comando);
        });
        return btn;
    }
}