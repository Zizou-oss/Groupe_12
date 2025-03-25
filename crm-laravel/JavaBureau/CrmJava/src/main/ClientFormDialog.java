package main;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class ClientFormDialog extends JDialog {
    private JTextField txtNom, txtEmail, txtEntreprise, txtTelephone;
    private boolean submitted = false;

    public ClientFormDialog(JFrame parent) {
        super(parent, "Ajouter un Client", true);
        setSize(300, 250);
        setLayout(new GridLayout(5, 2, 5, 5));
        setLocationRelativeTo(parent);

        add(new JLabel("Nom:"));
        txtNom = new JTextField();
        add(txtNom);

        add(new JLabel("Email:"));
        txtEmail = new JTextField();
        add(txtEmail);

        add(new JLabel("Entreprise:"));
        txtEntreprise = new JTextField();
        add(txtEntreprise);

        add(new JLabel("Téléphone:"));
        txtTelephone = new JTextField();
        add(txtTelephone);

        JButton btnAjouter = new JButton("Ajouter");
        JButton btnAnnuler = new JButton("Annuler");
        add(btnAjouter);
        add(btnAnnuler);

        btnAjouter.addActionListener(e -> {
            if (!txtNom.getText().trim().isEmpty() && !txtEmail.getText().trim().isEmpty()
                    && !txtEntreprise.getText().trim().isEmpty() && !txtTelephone.getText().trim().isEmpty()) {
                submitted = true;
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnAnnuler.addActionListener(e -> dispose());
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public String getNom() { return txtNom.getText(); }
    public String getEmail() { return txtEmail.getText(); }
    public String getEntreprise() { return txtEntreprise.getText(); }
    public String getTelephone() { return txtTelephone.getText(); }
}
