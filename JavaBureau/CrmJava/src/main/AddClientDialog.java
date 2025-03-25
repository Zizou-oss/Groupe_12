package main;

import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;

@SuppressWarnings("serial")
public class AddClientDialog extends JDialog {
    private JTextField txtNom, txtEmail, txtEntreprise, txtTelephone;
    private DefaultListModel<String> clientsModel;

    public AddClientDialog(JFrame parent, DefaultListModel<String> clientsModel) {
        super(parent, "Ajouter un Client", true);
        this.clientsModel = clientsModel;
        setSize(300, 250);
        getContentPane().setLayout(new GridLayout(5, 2, 5, 5));
        setLocationRelativeTo(parent);

        getContentPane().add(new JLabel("Nom:"));
        txtNom = new JTextField();
        getContentPane().add(txtNom);

        getContentPane().add(new JLabel("Email:"));
        txtEmail = new JTextField();
        getContentPane().add(txtEmail);

        getContentPane().add(new JLabel("Entreprise:"));
        txtEntreprise = new JTextField();
        getContentPane().add(txtEntreprise);

        getContentPane().add(new JLabel("Téléphone:"));
        txtTelephone = new JTextField();
        getContentPane().add(txtTelephone);

        JButton btnAjouter = new JButton("Ajouter");
        btnAjouter.setForeground(new Color(0, 0, 255));
        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.setForeground(new Color(255, 0, 0));
        getContentPane().add(btnAjouter);
        getContentPane().add(btnAnnuler);

        btnAjouter.addActionListener(e -> saveClient());
        btnAnnuler.addActionListener(e -> dispose());
    }

    private void saveClient() {
        String nom = txtNom.getText();
        String email = txtEmail.getText();
        String entreprise = txtEntreprise.getText();
        String telephone = txtTelephone.getText();

        if (nom.isEmpty() || email.isEmpty() || entreprise.isEmpty() || telephone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tous les champs doivent être remplis.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO clients (nom, email, entreprise, telephone) VALUES (?, ?, ?, ?)")) {

            stmt.setString(1, nom);
            stmt.setString(2, email);
            stmt.setString(3, entreprise);
            stmt.setString(4, telephone);
            stmt.executeUpdate();

            clientsModel.addElement(nom);
            JOptionPane.showMessageDialog(this, "Client ajouté avec succès !");
            dispose();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout du client.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
