package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class EditClientDialog extends JDialog {
    private JTextField txtNom;
    private JTextField txtEmail;
    private JTextField txtEntreprise;
    private JTextField txtTelephone;
    private JButton btnSave;
    private JButton btnCancel;
    private DefaultListModel<String> clientsModel;
    private String selectedClient;

    public EditClientDialog(JFrame parent, DefaultListModel<String> clientsModel, String selectedClient) {
        super(parent, "Modifier un client", true);
        this.clientsModel = clientsModel;
        this.selectedClient = selectedClient;
        initialize();
    }

    private void initialize() {
        getContentPane().setLayout(new BorderLayout());
        setSize(400, 300);
        setLocationRelativeTo(getParent());

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        txtNom = new JTextField();
        txtEmail = new JTextField();
        txtEntreprise = new JTextField();
        txtTelephone = new JTextField();

        formPanel.add(new JLabel("Nom:"));
        formPanel.add(txtNom);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(txtEmail);
        formPanel.add(new JLabel("Entreprise:"));
        formPanel.add(txtEntreprise);
        formPanel.add(new JLabel("Téléphone:"));
        formPanel.add(txtTelephone);

        getContentPane().add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        btnSave = new JButton("Enregistrer");
        btnSave.setForeground(new Color(0, 0, 255));
        btnCancel = new JButton("Annuler");
        btnCancel.setForeground(new Color(255, 0, 0));

        btnSave.addActionListener(this::onSave);
        btnCancel.addActionListener(e -> dispose());

        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        loadClientData();
    }

    private void loadClientData() {
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM clients WHERE nom = ?")) {
            stmt.setString(1, selectedClient);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                txtNom.setText(rs.getString("nom"));
                txtEmail.setText(rs.getString("email"));
                txtEntreprise.setText(rs.getString("entreprise"));
                txtTelephone.setText(rs.getString("telephone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur de chargement des données du client", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onSave(ActionEvent e) {
        String nom = txtNom.getText();
        String email = txtEmail.getText();
        String entreprise = txtEntreprise.getText();
        String telephone = txtTelephone.getText();

        if (nom.isEmpty() || email.isEmpty() || entreprise.isEmpty() || telephone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tous les champs sont obligatoires", "Erreur", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE clients SET nom = ?, email = ?, entreprise = ?, telephone = ? WHERE nom = ?")) {
            stmt.setString(1, nom);
            stmt.setString(2, email);
            stmt.setString(3, entreprise);
            stmt.setString(4, telephone);
            stmt.setString(5, selectedClient);
            stmt.executeUpdate();

            // Mettre à jour la liste des clients
            int index = clientsModel.indexOf(selectedClient);
            clientsModel.set(index, nom);

            JOptionPane.showMessageDialog(this, "Client modifié avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de la modification du client", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}