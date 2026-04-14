package Visual;

import Ligca.Plan;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class PanelPlanes extends JPanel {
    private JPanel contenedorCartas;
    private CardLayout cardInterno;
    
    private JTextField txtNombre, txtPrecio, txtDetalle;
    private JLabel lblDetalleDinamico;
    private String tipoSeleccionado = "";

    private final Color AZUL_ALTICE = new Color(0, 43, 92);
    private final Color MAGENTA_ALTICE = new Color(225, 0, 110);
    private final Color GRIS_FONDO = new Color(245, 246, 250);

    public PanelPlanes() {
        setLayout(new BorderLayout());
        setBackground(GRIS_FONDO);

        cardInterno = new CardLayout();
        contenedorCartas = new JPanel(cardInterno);
        contenedorCartas.setOpaque(false);
        
        contenedorCartas.add(crearPantallaSeleccionTipo(), "seleccion");
        contenedorCartas.add(crearPantallaFormulario(), "formulario");

        add(contenedorCartas, BorderLayout.CENTER);
    }

    private JPanel crearPantallaSeleccionTipo() {
        JPanel pnlMain = new JPanel(new BorderLayout());
        pnlMain.setOpaque(false);
        pnlMain.setBorder(new EmptyBorder(60, 50, 60, 50));

        JLabel lblTitulo = new JLabel("¿Qué tipo de plan desea crear?", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(AZUL_ALTICE);
        pnlMain.add(lblTitulo, BorderLayout.NORTH);

        JPanel pnlCards = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        pnlCards.setOpaque(false);

        pnlCards.add(crearCardServicio("INTERNET", "<html><div style='text-align:center;'>🌐<br>Internet Fibra</div></html>", AZUL_ALTICE));
        pnlCards.add(crearCardServicio("TV", "<html><div style='text-align:center;'>📺<br>Televisión HD</div></html>", MAGENTA_ALTICE));
        pnlCards.add(crearCardServicio("TELEFONIA", "<html><div style='text-align:center;'>📞<br>Voz Digital</div></html>", new Color(100, 100, 100)));

        pnlMain.add(pnlCards, BorderLayout.CENTER);
        return pnlMain;
    }

    private JPanel crearCardServicio(String tipoConstante, String htmlTexto, Color colorBase) {
        JPanel card = new JPanel(new BorderLayout());
        card.setPreferredSize(new Dimension(220, 280));
        card.setBackground(Color.WHITE);
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        card.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 1));

        JLabel lblContent = new JLabel(htmlTexto, SwingConstants.CENTER);
        lblContent.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblContent.setForeground(colorBase);
        card.add(lblContent, BorderLayout.CENTER);

        card.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                card.setBorder(BorderFactory.createLineBorder(colorBase, 3));
                card.setBackground(new Color(252, 252, 252));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                card.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 1));
                card.setBackground(Color.WHITE);
            }
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                abrirFormulario(tipoConstante);
            }
        });

        return card;
    }

    private void abrirFormulario(String tipo) {
        this.tipoSeleccionado = tipo;
        
        switch(tipo) {
            case "INTERNET": lblDetalleDinamico.setText("Velocidad de Subida/Bajada (Mbps):"); break;
            case "TV": lblDetalleDinamico.setText("Número de Canales:"); break;
            default: lblDetalleDinamico.setText("Minutos/Líneas incluidas:"); break;
        }
        
        limpiarCampos();
        cardInterno.show(contenedorCartas, "formulario");
    }

    private JPanel crearPantallaFormulario() {
        JPanel pnlCentro = new JPanel(new GridBagLayout());
        pnlCentro.setBackground(GRIS_FONDO);
        
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            new EmptyBorder(30, 40, 30, 40)
        ));

        JLabel tit = new JLabel("Detalles del Nuevo Plan");
        tit.setFont(new Font("Segoe UI", Font.BOLD, 20));
        tit.setAlignmentX(Component.LEFT_ALIGNMENT);
        form.add(tit); form.add(Box.createVerticalStrut(20));

        txtNombre = agregarCampoAlForm(form, "Nombre del Plan:");
        txtPrecio = agregarCampoAlForm(form, "Precio Mensual (RD$):");
        
        lblDetalleDinamico = new JLabel("Detalle del servicio:");
        lblDetalleDinamico.setFont(new Font("Segoe UI", Font.BOLD, 13));
        form.add(lblDetalleDinamico);
        txtDetalle = new JTextField();
        txtDetalle.setMaximumSize(new Dimension(400, 35));
        form.add(txtDetalle); form.add(Box.createVerticalStrut(20));

        JPanel pnlBtns = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlBtns.setOpaque(false);
        
        JButton btnVolver = new JButton("Cancelar");
        btnVolver.addActionListener(e -> cardInterno.show(contenedorCartas, "seleccion"));
        
        JButton btnSave = new JButton("Guardar Plan");
        btnSave.setBackground(AZUL_ALTICE);
        btnSave.setForeground(Color.WHITE);
        btnSave.addActionListener(e -> guardar());

        pnlBtns.add(btnVolver); pnlBtns.add(btnSave);
        form.add(pnlBtns);

        pnlCentro.add(form);
        return pnlCentro;
    }

    private JTextField agregarCampoAlForm(JPanel pnl, String label) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnl.add(lbl);
        
        JTextField tf = new JTextField();
        tf.setMaximumSize(new Dimension(400, 35));
        tf.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnl.add(tf);
        pnl.add(Box.createVerticalStrut(15));
        return tf;
    }

    private void guardar() {
        String nombre = txtNombre.getText().trim();
        String precioStr = txtPrecio.getText().trim();
        String detalle = txtDetalle.getText().trim();

        if (nombre.isEmpty() || precioStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete los campos de Nombre y Precio.");
            return;
        }

        new Thread(() -> {
            try {
                float precio = Float.parseFloat(precioStr);
                
                Plan nuevoPlan = new Plan(
                    "PLN-" + System.currentTimeMillis(), 
                    nombre, 
                    "Servicio " + tipoSeleccionado, 
                    detalle, 
                    precio, 
                    tipoSeleccionado, 
                    12, 
                    true
                );

                Object respuesta = SocketCliente.recibirDatos("PLANES");
                ArrayList<Plan> listaActualizada = new ArrayList<>();

                if (respuesta instanceof ArrayList<?>) {
                    ArrayList<?> listaRecibida = (ArrayList<?>) respuesta;
                    for (Object obj : listaRecibida) {
                        if (obj instanceof Plan) {
                            listaActualizada.add((Plan) obj);
                        }
                    }
                }

                listaActualizada.add(nuevoPlan);
                boolean exito = SocketCliente.enviarDatos("PLANES", listaActualizada);

                SwingUtilities.invokeLater(() -> {
                    if (exito) {
                        JOptionPane.showMessageDialog(this, "Plan '" + nombre + "' registrado con éxito.");
                        limpiarCampos();
                        cardInterno.show(contenedorCartas, "seleccion");
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al sincronizar con el servidor.");
                    }
                });

            } catch (NumberFormatException e) {
                SwingUtilities.invokeLater(() -> 
                    JOptionPane.showMessageDialog(this, "Error: El precio debe ser un número válido (ej: 1500.00).")
                );
            } catch (Exception e) {
                e.printStackTrace();
                SwingUtilities.invokeLater(() -> 
                    JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage())
                );
            }
        }).start();
    }

    private void limpiarCampos() {
        txtNombre.setText(""); txtPrecio.setText(""); txtDetalle.setText("");
    }
}