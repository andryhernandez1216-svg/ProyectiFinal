package Visual;

import Ligca.Cliente;
import Ligca.Factura;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

public class PanelReportes extends JPanel {
    private JTable tabla;
    private DefaultTableModel modelo;
    private JLabel lblTotal;
    private JSpinner spinInicio, spinFin;
    private ArrayList<Factura> listaFacturasActual;

    public PanelReportes() {
        setLayout(new BorderLayout(15, 15));
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // --- ENCABEZADO Y FILTROS ---
        JPanel pnlNorte = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        pnlNorte.setOpaque(false);
        
        spinInicio = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
        spinFin = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
        spinInicio.setEditor(new JSpinner.DateEditor(spinInicio, "dd/MM/yyyy"));
        spinFin.setEditor(new JSpinner.DateEditor(spinFin, "dd/MM/yyyy"));

        JButton btnGenerar = new JButton("Generar Reporte");
        btnGenerar.addActionListener(e -> filtrarPorPeriodo());
        
        pnlNorte.add(new JLabel("Desde:")); pnlNorte.add(spinInicio);
        pnlNorte.add(new JLabel("Hasta:")); pnlNorte.add(spinFin);
        pnlNorte.add(btnGenerar);

        // --- TABLA ---
        String[] columnas = {"No. Factura", "Cliente", "Fecha", "Monto Total"};
        modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tabla = new JTable(modelo);
        
        // --- SUR: TOTAL ---
        lblTotal = new JLabel("Total Recaudado: RD$ 0.00");
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 16));
        
        add(pnlNorte, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(lblTotal, BorderLayout.SOUTH);

        filtrarPorPeriodo(); 
    }

    public void filtrarPorPeriodo() {
        new Thread(() -> {
            try {
                // 1. Recibimos los datos como un Objeto genérico
                Object respuesta = SocketCliente.recibirDatos("facturas");
                
                // 2. Casting seguro: Solo convertimos si realmente es una ArrayList
                ArrayList<Factura> facturasServidor;
                if (respuesta instanceof ArrayList<?>) {
                    facturasServidor = (ArrayList<Factura>) respuesta;
                } else {
                    facturasServidor = new ArrayList<>();
                }

                SwingUtilities.invokeLater(() -> {
                    if (facturasServidor.isEmpty()) {
                        lblTotal.setText("No hay facturas en el sistema.");
                        modelo.setRowCount(0);
                        return;
                    }
                    
                    modelo.setRowCount(0); // Limpiar tabla
                    float totalPeriodo = 0;

                    for (Factura f : facturasServidor) {
                        // Agregamos la fila a la tabla
                        modelo.addRow(new Object[]{
                            f.getIdFactura(),
                            f.getCliente().getNombre() + " " + f.getCliente().getApellido(),
                            f.getFecha().toString(),
                            "RD$ " + String.format("%.2f", f.getMontoTotal())
                        });
                        totalPeriodo += f.getMontoTotal();
                    }
                    lblTotal.setText("Total Recaudado: RD$ " + String.format("%.2f", totalPeriodo));
                    
                    listaFacturasActual = facturasServidor;
                    // ...
                });
            } catch (Exception e) {
                System.err.println("Error en Reportes: " + e.getMessage());
            }
        }).start();
    }
}