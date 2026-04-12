package Visual;

import Ligca.Factura;
import Ligca.SistemaAltice; // Asegúrate de importar tu Singleton
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Date;
import java.util.Calendar;

public class PanelReportes extends JPanel {
    private JTable tabla;
    private DefaultTableModel modelo;
    private JSpinner spinInicio, spinFin;
    private JLabel lblTotal; // Para mostrar la suma total

    public PanelReportes() {
        setLayout(new BorderLayout(15, 15));
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // --- FILTROS DE PERIODO ---
        JPanel pnlFiltros = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        pnlFiltros.setOpaque(false);

        spinInicio = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
        spinFin = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
        
        spinInicio.setEditor(new JSpinner.DateEditor(spinInicio, "dd/MM/yyyy"));
        spinFin.setEditor(new JSpinner.DateEditor(spinFin, "dd/MM/yyyy"));

        JButton btnGenerar = new JButton("Generar Reporte");
        btnGenerar.setBackground(new Color(0, 43, 92));
        btnGenerar.setForeground(Color.BLACK);
        btnGenerar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnGenerar.addActionListener(e -> filtrarPorPeriodo());

        pnlFiltros.add(new JLabel("Desde:")); pnlFiltros.add(spinInicio);
        pnlFiltros.add(new JLabel("Hasta:")); pnlFiltros.add(spinFin);
        pnlFiltros.add(btnGenerar);

        // --- TABLA DE RESULTADOS ---
        String[] columnas = {"No. Factura", "Cliente", "Fecha", "Monto Total"};
        modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tabla = new JTable(modelo);
        
        // --- RESUMEN ---
        lblTotal = new JLabel("Total Recaudado: RD$ 0.00");
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        
        add(pnlFiltros, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(lblTotal, BorderLayout.SOUTH);
    }

    public void filtrarPorPeriodo() {
        Date inicio = (Date) spinInicio.getValue();
        Date fin = (Date) spinFin.getValue();
        
        // Ajustar fin de día para incluir las facturas de hoy
        Calendar cal = Calendar.getInstance();
        cal.setTime(fin);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        fin = cal.getTime();

        modelo.setRowCount(0);
        float totalPeriodo = 0;

        // Accedemos a la lista de facturas global
        for (Factura f : GestionSistema.getInstancia().getFacturas()) {
            Date fechaF = f.getFecha();
            
            if (!fechaF.before(inicio) && !fechaF.after(fin)) {
                modelo.addRow(new Object[]{
                    f.getIdFactura(),
                    f.getCliente().getNombreCompleto(),
                    f.getFecha().toString(),
                    "RD$ " + f.getMontoTotal() // Verifica si es getTotal() o getMontoTotal()
                });
                totalPeriodo += f.getMontoTotal();
            }
        }
        
        lblTotal.setText("Total Recaudado: RD$ " + String.format("%.2f", totalPeriodo));
        
        if (modelo.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No se encontraron facturas en este periodo.");
        }
    }
}