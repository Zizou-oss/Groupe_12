package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField emailField;
    private JPasswordField passwordField;

    public Login() {
        setTitle("Connexion Admin - CRM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 683, 555);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(44, 62, 80)); 
        setContentPane(panel);

        JLabel lblTitle = new JLabel("Connexion Administrateur");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitle.setBounds(231, 92, 206, 30);
        panel.add(lblTitle);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setForeground(Color.WHITE);
        lblEmail.setBounds(92, 165, 80, 25);
        panel.add(lblEmail);

        emailField = new JTextField();
        emailField.setBounds(205, 165, 258, 25);
        panel.add(emailField);

        JLabel lblPassword = new JLabel("Mot de passe:");
        lblPassword.setForeground(Color.WHITE);
        lblPassword.setBounds(92, 254, 100, 25);
        panel.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(205, 254, 258, 25);
        panel.add(passwordField);

        JButton btnLogin = new JButton("Se connecter");
        btnLogin.setBounds(259, 348, 150, 30);
        btnLogin.setBackground(new Color(52, 152, 219));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        panel.add(btnLogin);
        
        JLabel lblNewLabel = new JLabel("Bienvenue sur votre CRM de bureau");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(141, 36, 387, 30);
        panel.add(lblNewLabel);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                if (email.equals("admin@crm.com") && password.equals("password123")) {
                    JOptionPane.showMessageDialog(null, "Connexion réussie !");
                    dispose(); // Ferme la fenêtre de login
                    Main mainFrame = new Main(); // Ouvre Main.java
                    mainFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Identifiants incorrects", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Login frame = new Login();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
