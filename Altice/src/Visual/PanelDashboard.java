package Visual;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.ArrayList;
import Ligca.*;

public class PanelDashboard extends JPanel {
    
    private JLabel lblIngresos;
    private JLabel lblClientes;
    private JLabel lblContratos;

    public PanelDashboard() {
        setBackground(new Color(245, 246, 250)); 
        setLayout(new BorderLayout(20, 20));
        setBorder(new EmptyBorder(30, 30, 30, 30));
        JLabel titulo = new JLabel("Resumen General Altice");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        add(titulo, BorderLayout.NORTH);

        JPanel gridCartas = new JPanel(new GridLayout(1, 3, 25, 0));
        gridCartas.setOpaque(false);

        lblIngresos = new JLabel("RD$ 0.00"); 
        lblClientes = new JLabel("0");
        lblContratos = new JLabel("0");

        gridCartas.add(crearTarjeta("Ingresos Totales", lblIngresos, new Color(0, 43, 92))); 
        gridCartas.add(crearTarjeta("Clientes Activos", lblClientes, Color.DARK_GRAY));
        gridCartas.add(crearTarjeta("Contratos Vigentes", lblContratos, new Color(225, 0, 110))); 

        add(gridCartas, BorderLayout.CENTER);
        
        JLabel info = new JLabel("Datos sincronizados con el servidor central.");
        info.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        add(info, BorderLayout.SOUTH);

        refrescarEstadisticas();
    }

    private JPanel crearTarjeta(String titulo, JLabel valorLabel, Color colorBorde) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(230, 230, 230), 1),
            new EmptyBorder(20, 20, 20, 20)
        ));

        JLabel txtTitulo = new JLabel(titulo);
        txtTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtTitulo.setForeground(Color.GRAY);

        valorLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        valorLabel.setForeground(colorBorde);

        card.add(txtTitulo, BorderLayout.NORTH);
        card.add(valorLabel, BorderLayout.CENTER);

        JPanel linea = new JPanel();
        linea.setPreferredSize(new Dimension(0, 4));
        linea.setBackground(colorBorde);
        card.add(linea, BorderLayout.SOUTH);

        return card;
    }

    public void refrescarEstadisticas() {
        new Thread(() -> {
            try {
                Object dataC = SocketCliente.recibirDatos("clientes");
                Object dataF = SocketCliente.recibirDatos("facturas");

                ArrayList<Cliente> clientes = (dataC instanceof ArrayList) ? (ArrayList<Cliente>) dataC : new ArrayList<>();
                ArrayList<Factura> facturas = (dataF instanceof ArrayList) ? (ArrayList<Factura>) dataF : new ArrayList<>();

                float sumaAuxiliar = 0;
                for (Factura f : facturas) {
                    sumaAuxiliar += f.getMontoTotal();
                }

                final float resultadoIngresos = sumaAuxiliar;
                final int resultadoClientes = clientes.size();
                final int resultadoContratos = facturas.size();

                SwingUtilities.invokeLater(() -> {
                    lblIngresos.setText("RD$ " + String.format("%.2f", resultadoIngresos));
                    lblClientes.setText(String.valueOf(resultadoClientes));
                    lblContratos.setText(String.valueOf(resultadoContratos));
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
