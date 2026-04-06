package Visual;


import Ligca.Plan;
import javax.swing.*;
import java.awt.*;

public class PanelPlanes extends JPanel {
    private JTextField txtNom, txtPre, txtVel;
    private JComboBox<String> cbTipo;

    public PanelPlanes() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        setBackground(Color.WHITE);

        txtNom = new JTextField(10);
        txtPre = new JTextField(7);
        txtVel = new JTextField(7);
        cbTipo = new JComboBox<>(new String[]{"INTERNET", "TV", "TELEFONIA", "COMBO"});

        add(new JLabel("Nombre Plan:")); add(txtNom);
        add(new JLabel("Precio (RD$):")); add(txtPre);
        add(new JLabel("Velocidad:")); add(txtVel);
        add(new JLabel("Tipo:")); add(cbTipo);

        JButton btnAdd = new JButton("Agregar Plan");
        btnAdd.addActionListener(e -> {
            try {
                Plan p = new Plan(
                    "PLN-" + System.currentTimeMillis(), txtNom.getText(), "Servicio Altice",
                    txtVel.getText(), Float.parseFloat(txtPre.getText()), 
                    cbTipo.getSelectedItem().toString(), 12, true
                );
                GestionSistema.getInstancia().agregarPlan(p);
                JOptionPane.showMessageDialog(this, "Plan agregado correctamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });
        add(btnAdd);
    }
}