package Visual;

import Ligca.Factura;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.stream.Collectors;

public class PanelReportes extends JPanel {
    private JTable tabla;
    private DefaultTableModel modelo;
    private JLabel lblTotal, lblConteo;
    private JSpinner spinInicio, spinFin;
    private JTextField txtBuscar;
    private ArrayList<Factura> todasLasFacturas = new ArrayList<>();

    private final Color AZUL_ALTICE = new Color(0, 43, 92);
    private final Color MAGENTA_ALTICE = new Color(225, 0, 110);

    public PanelReportes() {
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(245, 246, 250));
        setBorder(new EmptyBorder(25, 25, 25, 25));

        JPanel pnlSuperior = new JPanel(new BorderLayout(10, 10));
        pnlSuperior.setOpaque(false);

        JPanel pnlFiltros = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        pnlFiltros.setOpaque(false);
        
        spinInicio = crearSpinnerFecha();
        spinFin = crearSpinnerFecha();
        
        JButton btnCargar = new JButton("Consultar Servidor");
        estilizarBoton(btnCargar, AZUL_ALTICE);
        btnCargar.addActionListener(e -> filtrarPorPeriodo());

        pnlFiltros.add(new JLabel("Desde:")); pnlFiltros.add(spinInicio);
        pnlFiltros.add(new JLabel("Hasta:")); pnlFiltros.add(spinFin);
        pnlFiltros.add(btnCargar);

        JPanel pnlBusqueda = new JPanel(new BorderLayout(10, 0));
        pnlBusqueda.setOpaque(false);
        pnlBusqueda.setBorder(new EmptyBorder(15, 15, 0, 0));
        
        txtBuscar = new JTextField();
        txtBuscar.setPreferredSize(new Dimension(300, 35));
        txtBuscar.setToolTipText("Buscar por cliente o ID de factura...");
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                aplicarFiltroBusqueda();
            }
        });

        pnlBusqueda.add(new JLabel("🔍 Filtrar resultados:"), BorderLayout.WEST);
        pnlBusqueda.add(txtBuscar, BorderLayout.CENTER);

        pnlSuperior.add(pnlFiltros, BorderLayout.NORTH);
        pnlSuperior.add(pnlBusqueda, BorderLayout.SOUTH);

        String[] columnas = {"ID Factura", "Cliente", "Fecha Emisión", "Importe Total"};
        modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        
        tabla = new JTable(modelo);
        configurarTabla();

        JPanel pnlInferior = new JPanel(new BorderLayout());
        pnlInferior.setOpaque(false);
        pnlInferior.setBorder(new EmptyBorder(10, 0, 0, 0));

        JPanel pnlLabels = new JPanel(new GridLayout(2, 1));
        pnlLabels.setOpaque(false);
        lblTotal = new JLabel("Total Recaudado: RD$ 0.00");
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTotal.setForeground(AZUL_ALTICE);
        
        lblConteo = new JLabel("Documentos encontrados: 0");
        lblConteo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        pnlLabels.add(lblTotal);
        pnlLabels.add(lblConteo);

     

        pnlInferior.add(pnlLabels, BorderLayout.WEST);
       

        add(pnlSuperior, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(pnlInferior, BorderLayout.SOUTH);

        filtrarPorPeriodo(); 
    }

    private JSpinner crearSpinnerFecha() {
        JSpinner s = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
        s.setEditor(new JSpinner.DateEditor(s, "dd/MM/yyyy"));
        s.setPreferredSize(new Dimension(120, 30));
        return s;
    }

    private void estilizarBoton(JButton btn, Color color) {
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(new EmptyBorder(10, 20, 10, 20));
    }

    private void configurarTabla() {
        tabla.setRowHeight(35);
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabla.getTableHeader().setBackground(AZUL_ALTICE);
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.setSelectionBackground(new Color(225, 0, 110, 50));
        tabla.setSelectionForeground(Color.BLACK);
        tabla.setShowGrid(false);
        tabla.setIntercellSpacing(new Dimension(0, 0));
    }

    public void filtrarPorPeriodo() {
        new Thread(() -> {
            try {
                Object respuesta = SocketCliente.recibirDatos("facturas");
                if (respuesta instanceof ArrayList<?>) {
                    todasLasFacturas = (ArrayList<Factura>) respuesta;
                }
                actualizarTablaUI(todasLasFacturas);
            } catch (Exception e) {
                System.err.println("Error en Reportes: " + e.getMessage());
            }
        }).start();
    }

    private void aplicarFiltroBusqueda() {
        String query = txtBuscar.getText().toLowerCase();
        ArrayList<Factura> filtradas = (ArrayList<Factura>) todasLasFacturas.stream()
            .filter(f -> f.getIdFactura().toLowerCase().contains(query) || 
                         (f.getCliente().getNombre() + " " + f.getCliente().getApellido()).toLowerCase().contains(query))
            .collect(Collectors.toList());
        actualizarTablaUI(filtradas);
    }

    private void actualizarTablaUI(ArrayList<Factura> lista) {
        SwingUtilities.invokeLater(() -> {
            modelo.setRowCount(0);
            float total = 0;
            for (Factura f : lista) {
                modelo.addRow(new Object[]{
                    f.getIdFactura(),
                    f.getCliente().getNombre() + " " + f.getCliente().getApellido(),
                    f.getFecha().toString(),
                    "RD$ " + String.format("%.2f", f.getMontoTotal())
                });
                total += f.getMontoTotal();
            }
            lblTotal.setText("Total Recaudado: RD$ " + String.format("%.2f", total));
            lblConteo.setText("Documentos encontrados: " + lista.size());
        });
    }
}