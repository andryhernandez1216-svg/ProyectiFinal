package Visual;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import Ligca.SistemaAltice;

public class PanelDashboard extends JPanel {
    
    // Etiquetas para actualizar los datos en tiempo real
    private JLabel lblIngresos;
    private JLabel lblClientes;
    private JLabel lblContratos;
    private SistemaAltice logica;

    public PanelDashboard() {
        this.logica = GestionSistema.getInstancia(); // Conexión con tu lógica
        
        setBackground(new Color(245, 246, 250)); // Gris claro de fondo
        setLayout(new BorderLayout(20, 20));
        setBorder(new EmptyBorder(30, 30, 30, 30));

        // Título del Panel
        JLabel titulo = new JLabel("Resumen General Altice");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        add(titulo, BorderLayout.NORTH);

        // Contenedor de Tarjetas (Cards)
        JPanel gridCartas = new JPanel(new GridLayout(1, 3, 25, 0));
        gridCartas.setOpaque(false);

        // Creamos las tarjetas usando tus métodos de SistemaAltice.java
        lblIngresos = new JLabel("RD$ " + logica.totalIngresos()); 
        lblClientes = new JLabel(String.valueOf(logica.cantidadClientesActivos()));
        lblContratos = new JLabel(String.valueOf(logica.cantidadContratosActivos()));

        gridCartas.add(crearTarjeta("Ingresos Totales", lblIngresos, new Color(0, 43, 92))); // Azul
        gridCartas.add(crearTarjeta("Clientes Activos", lblClientes, Color.DARK_GRAY));
        gridCartas.add(crearTarjeta("Contratos Vigentes", lblContratos, new Color(225, 0, 110))); // Magenta

        add(gridCartas, BorderLayout.CENTER);
        
        // Mensaje inferior informativo
        JLabel info = new JLabel("Datos actualizados según el registro del sistema.");
        info.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        add(info, BorderLayout.SOUTH);
    }

    /**
     * Método auxiliar para crear el diseño de las tarjetas informativas
     */
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

        // Línea decorativa inferior
        JPanel linea = new JPanel();
        linea.setPreferredSize(new Dimension(0, 4));
        linea.setBackground(colorBorde);
        card.add(linea, BorderLayout.SOUTH);

        return card;
    }

    /**
     * Refresca los números cuando hay cambios en el sistema
     */
    public void refrescarEstadisticas() {
        lblIngresos.setText("RD$ " + logica.totalIngresos()); // Llama a totalIngresos()
        lblClientes.setText(String.valueOf(logica.cantidadClientesActivos())); // Llama a cantidadClientesActivos()
        lblContratos.setText(String.valueOf(logica.cantidadContratosActivos())); // Llama a cantidadContratosActivos()
    }
}
