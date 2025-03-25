package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JTextField;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.UIManager;
import java.awt.Panel;

public class Main extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JList<String> clientsList;
    private DefaultListModel<String> clientsModel;
    private JPanel clientDetailsPanel;
    private JLabel lblTotalClients;
    private JLabel lblTotalClients_1;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Main frame = new Main();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Main() {
        setTitle("CRM Java - Application de Bureau");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 849, 608);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(52, 152, 219));
        titlePanel.setPreferredSize(new Dimension(800, 40));
        JLabel titleLabel = new JLabel("CRM Java - Application de Bureau", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titlePanel.add(titleLabel);
        contentPane.add(titlePanel, BorderLayout.NORTH);


        JPanel clientsPanel = new JPanel(new BorderLayout());
        contentPane.add(clientsPanel, BorderLayout.CENTER);

        clientsModel = new DefaultListModel<>();
        clientsList = new JList<>(clientsModel);
        clientsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        clientsList.setFont(new Font("Arial", Font.PLAIN, 14));
        clientsList.setBackground(new Color(235, 245, 251));
        clientsList.addListSelectionListener(this::onClientSelected);

        JPanel clientsListPanel = new JPanel(new BorderLayout());
        clientsListPanel.setPreferredSize(new Dimension(425, 0));
        clientsListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(clientsList);
        clientsListPanel.add(scrollPane, BorderLayout.CENTER);
        
     // Dans la classe Main, ajoutez ce code dans le constructeur, après la création de clientsListPanel
        JPanel searchPanel = new JPanel(new BorderLayout());
        JTextField searchField = new JTextField();
        JButton btnSearch = new JButton("Rechercher");
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(btnSearch, BorderLayout.EAST);
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        clientsListPanel.add(searchPanel, BorderLayout.NORTH);
        lblTotalClients_1 = new JLabel("Total Clients: 0");
        lblTotalClients_1.setBackground(new Color(0, 0, 0));
        searchPanel.add(lblTotalClients_1, BorderLayout.NORTH);
        lblTotalClients_1.setFont(new Font("Arial", Font.BOLD, 16));
        lblTotalClients_1.setForeground(new Color(0, 0, 0));
        
     // Panneau pour afficher le nombre total de clients
        JPanel totalClientsPanel = new JPanel();
        lblTotalClients = new JLabel("Total Clients: 0");
        lblTotalClients.setFont(new Font("Arial", Font.BOLD, 14));
        totalClientsPanel.add(lblTotalClients);
        clientsListPanel.add(totalClientsPanel, BorderLayout.SOUTH);

        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 128, 0));
        scrollPane.setColumnHeaderView(panel);
     

        // Ajouter un écouteur pour le bouton "Rechercher" (optionnel)
        btnSearch.addActionListener(e -> filterClients(searchField.getText()));
        
        JLabel lblNewLabel = new JLabel("Liste des Clients");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        panel.add(lblNewLabel);

        JPanel clientButtonsPanel = new JPanel(new GridLayout(1, 2, 5, 0));
        JButton btnAddClient = new JButton("Ajouter");
        btnAddClient.addActionListener(e -> {
            new AddClientDialog(this, clientsModel).setVisible(true); // Ajout d'un client via un dialogue
        });

        btnAddClient.setForeground(new Color(0, 0, 255));
        btnAddClient.setBackground(UIManager.getColor("Button.background"));
        JButton btnDeleteClient = new JButton("Supprimer");
        btnDeleteClient.setForeground(new Color(255, 0, 0));
        clientButtonsPanel.add(btnAddClient);
        JButton btnEditClient = new JButton("Editer");
        btnEditClient.setForeground(new Color(0, 128, 64));
        btnEditClient.addActionListener(e -> {
            int selectedIndex = clientsList.getSelectedIndex();
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(this, "Sélectionnez un client à modifier.", "Erreur", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String selectedClient = clientsModel.getElementAt(selectedIndex);
            new EditClientDialog(this, clientsModel, selectedClient).setVisible(true);
        });
        clientButtonsPanel.add(btnEditClient);
        clientButtonsPanel.add(btnDeleteClient);
        clientsListPanel.add(clientButtonsPanel, BorderLayout.SOUTH);
        // Dans la classe Main, dans le constructeur, après la création des autres boutons
           JButton btnExport = new JButton("Exporter");
           clientButtonsPanel.add(btnExport); // Ajouter le bouton au panneau des boutons
           btnExport.addActionListener(e -> exportClientsToCSV());

        btnAddClient.addActionListener(e -> new AddClientDialog(this, clientsModel).setVisible(true));
        btnDeleteClient.addActionListener(this::onDeleteClient);

        clientsPanel.add(clientsListPanel, BorderLayout.WEST);

        clientDetailsPanel = new JPanel(new BorderLayout());
        clientsPanel.add(clientDetailsPanel, BorderLayout.CENTER);
        
        Panel panel_1 = new Panel();
        clientDetailsPanel.add(panel_1, BorderLayout.CENTER);

        loadClientsFromDatabase();
        
    }
    private void onDeleteClient(ActionEvent e) {
        int selectedIndex = clientsList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Sélectionnez un client à supprimer.", "Erreur", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String clientName = clientsModel.getElementAt(selectedIndex);
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM clients WHERE nom = ?")) {
            stmt.setString(1, clientName);
            stmt.executeUpdate();
            clientsModel.remove(selectedIndex);
            loadClientsFromDatabase(); // Recharger les clients après suppression
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur de suppression", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadClientsFromDatabase() {
        clientsModel.clear();
        int totalClients = 0;

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT nom FROM clients");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                clientsModel.addElement(rs.getString("nom"));
                totalClients++; // Incrémentation du compteur
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur de chargement des clients", "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        // Mise à jour du label affichant le nombre total de clients
        lblTotalClients_1.setText("Total clients enregistrer: " + totalClients);
    }

    
    
    
    
    private void filterClients(String keyword) {
        DefaultListModel<String> filteredModel = new DefaultListModel<>();
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT nom FROM clients WHERE nom LIKE ? OR email LIKE ? OR entreprise LIKE ? OR telephone LIKE ?")) {
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            stmt.setString(3, "%" + keyword + "%");
            stmt.setString(4, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                filteredModel.addElement(rs.getString("nom"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de la recherche des clients", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        clientsList.setModel(filteredModel);
        
     // Dans la méthode filterClients, après avoir défini le nouveau modèle
        clientsList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedClient = clientsList.getSelectedValue();
                if (selectedClient != null) {
                    updateClientDetails(selectedClient);
                }
            }
        });
    }
    
    private void exportClientsToCSV() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Enregistrer le fichier CSV");
        fileChooser.setSelectedFile(new File("clients.csv")); // Nom par défaut du fichier

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (Connection conn = DatabaseManager.getConnection();
                 PreparedStatement stmt = conn.prepareStatement("SELECT * FROM clients");
                 ResultSet rs = stmt.executeQuery();
                 FileWriter writer = new FileWriter(fileToSave)) {

                // Écrire l'en-tête du fichier CSV
                writer.append("Nom,Email,Entreprise,Téléphone\n");

                // Écrire les données des clients
                while (rs.next()) {
                    writer.append(rs.getString("nom")).append(",");
                    writer.append(rs.getString("email")).append(",");
                    writer.append(rs.getString("entreprise")).append(",");
                    writer.append(rs.getString("telephone")).append("\n");
                }

                JOptionPane.showMessageDialog(this, "Exportation réussie !", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException | IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erreur lors de l'exportation", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void onClientSelected(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            String selectedClient = clientsList.getSelectedValue();
            if (selectedClient != null) {
                updateClientDetails(selectedClient);
            }
        }
    }

    private void updateClientDetails(String clientName) {
        clientDetailsPanel.removeAll();

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM clients WHERE nom = ?")) {
            stmt.setString(1, clientName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                JPanel detailsPanel = new JPanel();
                detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
                detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Ajout d’un padding

                Font labelFont = new Font("Arial", Font.BOLD, 16); // Police en gras, taille 16
                int spacing = 10; // Espacement entre les labels

                JLabel nameLabel = new JLabel("Nom: " + rs.getString("nom"));
                nameLabel.setFont(labelFont);
                nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, spacing, 0));

                JLabel emailLabel = new JLabel("Email: " + rs.getString("email"));
                emailLabel.setFont(labelFont);
                emailLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, spacing, 0));

                JLabel entrepriseLabel = new JLabel("Entreprise: " + rs.getString("entreprise"));
                entrepriseLabel.setFont(labelFont);
                entrepriseLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, spacing, 0));

                JLabel phoneLabel = new JLabel("Téléphone: " + rs.getString("telephone"));
                phoneLabel.setFont(labelFont);
                phoneLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, spacing, 0));

                detailsPanel.add(nameLabel);
                detailsPanel.add(emailLabel);
                detailsPanel.add(entrepriseLabel);
                detailsPanel.add(phoneLabel);

                clientDetailsPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Alignement à gauche
                clientDetailsPanel.add(detailsPanel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur de chargement des détails", "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        clientDetailsPanel.revalidate();
        clientDetailsPanel.repaint();
    }


}