package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings({ "serial", "unused" })
public class ClientFormFrame extends JFrame {
    private JTextField txtNom, txtEmail, txtEntreprise, txtTelephone;

    public ClientFormFrame(final DefaultListModel<String> clientsModel) {
        // Configuration de la fenêtre du formulaire
        setTitle("Ajouter un Client");
        setSize(400, 300);
        setLocationRelativeTo(null); // Centrer la fenêtre
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fermer uniquement cette fenêtre
        setLayout(null);

        // Champ Nom
        JLabel lblNom = new JLabel("Nom:");
        lblNom.setBounds(20, 20, 100, 25);
        add(lblNom);
        txtNom = new JTextField();
        txtNom.setBounds(120, 20, 200, 25);
        add(txtNom);

        // Champ Email
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(20, 60, 100, 25);
        add(lblEmail);
        txtEmail = new JTextField();
        txtEmail.setBounds(120, 60, 200, 25);
        add(txtEmail);

        // Champ Entreprise
        JLabel lblEntreprise = new JLabel("Entreprise:");
        lblEntreprise.setBounds(20, 100, 100, 25);
        add(lblEntreprise);
        txtEntreprise = new JTextField();
        txtEntreprise.setBounds(120, 100, 200, 25);
        add(txtEntreprise);

        // Champ Téléphone
        JLabel lblTelephone = new JLabel("Téléphone:");
        lblTelephone.setBounds(20, 140, 100, 25);
        add(lblTelephone);
        txtTelephone = new JTextField();
        txtTelephone.setBounds(120, 140, 200, 25);
        add(txtTelephone);

        // Bouton Valider
        JButton btnValider = new JButton("Valider");
        btnValider.setBounds(120, 180, 100, 30);
        add(btnValider);

        // Action du bouton Valider
        btnValider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Récupérer les informations saisies et ajouter dans la liste
                String clientInfo = "Nom: " + txtNom.getText() + ", Email: " + txtEmail.getText() +
                                    ", Entreprise: " + txtEntreprise.getText() + ", Téléphone: " + txtTelephone.getText();
                clientsModel.addElement(clientInfo);  // Ajouter l'élément à la liste de clients
                dispose();  // Fermer la fenêtre du formulaire après validation
            }
        });

        setVisible(true);
    }
}
