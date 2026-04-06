package Visual;


import Ligca.Cliente;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Date;

public class PanelClientes extends JPanel {
    private JTable tabla;
    private DefaultTableModel modelo;
    private JTextField txtId, txtCedula, txtNombre, txtApellido, txtCodCliente, txtDeuda;
    private JComboBox<String> cbTipo;

    public PanelClientes() {
        setLayout(new BorderLayout(15, 15));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // --- FORMULARIO ---
        JPanel pnlInputs = new JPanel(new GridLayout(4, 4, 10, 10));
        pnlInputs.setOpaque(false);

        txtId = new JTextField(); txtCedula = new JTextField();
        txtNombre = new JTextField(); txtApellido = new JTextField();
        txtCodCliente = new JTextField(); txtDeuda = new JTextField("0");
        cbTipo = new JComboBox<>(new String[]{"FISICA", "JURIDICO"});

        pnlInputs.add(new JLabel("ID Persona:")); pnlInputs.add(txtId);
        pnlInputs.add(new JLabel("Cédula:")); pnlInputs.add(txtCedula);
        pnlInputs.add(new JLabel("Nombre:")); pnlInputs.add(txtNombre);
        pnlInputs.add(new JLabel("Apellido:")); pnlInputs.add(txtApellido);
        pnlInputs.add(new JLabel("Cod. Cliente:")); pnlInputs.add(txtCodCliente);
        pnlInputs.add(new JLabel("Tipo:")); pnlInputs.add(cbTipo);
        pnlInputs.add(new JLabel("Deuda Inicial:")); pnlInputs.add(txtDeuda);

        // --- BOTONES CRUD ---
        JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnGuardar = new JButton("Guardar Cliente");
        JButton btnLimpiar = new JButton("Limpiar");
        
        btnGuardar.setBackground(new Color(225, 0, 110));
        btnGuardar.setForeground(Color.WHITE);

        btnGuardar.addActionListener(e -> guardarCliente());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        pnlBotones.add(btnLimpiar); pnlBotones.add(btnGuardar);

        // --- TABLA ---
        String[] columnas = {"ID", "Cédula", "Nombre", "Código", "Tipo", "Deuda", "Estado"};
        modelo = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modelo);
        actualizarTabla();

        JPanel pnlNorte = new JPanel(new BorderLayout());
        pnlNorte.add(pnlInputs, BorderLayout.CENTER);
        pnlNorte.add(pnlBotones, BorderLayout.SOUTH);

        add(pnlNorte, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }

    private void guardarCliente() {
        try {
            float deuda = Float.parseFloat(txtDeuda.getText());
            Cliente c = new Cliente(
                txtId.getText(), txtCedula.getText(), txtNombre.getText(), txtApellido.getText(),
                "S/N", "S/N", "S/N", new Date(), 
                txtCodCliente.getText(), cbTipo.getSelectedItem().toString(), true, deuda, 0
            );
            
            GestionSistema.getInstancia().agregarCliente(c);
            actualizarTabla();
            limpiarCampos();
            JOptionPane.showMessageDialog(this, "Cliente registrado exitosamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Validación", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actualizarTabla() {
        modelo.setRowCount(0);
        for (Cliente c : GestionSistema.getInstancia().getClientes()) {
            modelo.addRow(new Object[]{c.getId(), c.getCedula(), c.getNombreCompleto(), c.getCodigoCliente(), c.getTipoCliente(), c.getDeuda(), c.isEstado() ? "Activo" : "Inactivo"});
        }
    }

    private void limpiarCampos() {
        txtId.setText(""); txtCedula.setText(""); txtNombre.setText("");
        txtApellido.setText(""); txtCodCliente.setText(""); txtDeuda.setText("0");
    }
}