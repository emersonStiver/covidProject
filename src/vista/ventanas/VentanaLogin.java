package vista.ventanas;


import modelo.Employee;
import modelo.LoginState;
import modelo.PersonalBaseDeDatos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentanaLogin extends JFrame {
    PersonalBaseDeDatos personalDB = PersonalBaseDeDatos.getInstancia();
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JComboBox comboBox;
    public VentanaLogin() {
        setTitle("VentanaLogin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Set background color for the frame
        getContentPane().setBackground(new Color(224, 228, 204));

        // Use Nimbus Look and Feel for a modern appearance
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create constraints for GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(5, 5, 5, 5); // Padding

        // Icon
        ImageIcon icon = new ImageIcon("src/recursos/company.png");
        JLabel iconLabel = new JLabel(resizeIcon(icon, 50, 50)); // Resize icon
        add(iconLabel, gbc);

        // Username field
        usernameField = new JTextField(20);
        usernameField.setPreferredSize(new Dimension(200, 30));
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        add(new JLabel("Id:"), gbc);
        add(usernameField, gbc);

        // Password field
        passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(200, 30));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        add(new JLabel("Contraseña:"), gbc);
        add(passwordField, gbc);

        comboBox = new JComboBox<String>(new String[]{"Director", "Employee"});
        add(new JLabel("Rol"), gbc);
        add(comboBox, gbc);

        // VentanaLogin button
        loginButton = new JButton("VentanaLogin");
        loginButton.setPreferredSize(new Dimension(100, 40));
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(58, 175, 169));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String role =(String) comboBox.getSelectedItem();

                // Validate username and password (you can add your validation logic here)
                LoginState loginState = validate(username, password, role);

                if (loginState != null && loginState.getAuthenticated()) {
                    // Transition to dashboard
                    dispose(); // Close login window
                    openDashboard(loginState);
                } else {
                    JOptionPane.showMessageDialog(VentanaLogin.this, "Usuario o contraseña invalidos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        gbc.anchor = GridBagConstraints.CENTER;
        add(loginButton, gbc);

        // Set the size of the frame to 50% of the screen height
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 4, screenSize.height / 2);

        // Center the frame
        setLocationRelativeTo(null);

        setVisible(true);
    }

    private LoginState validate(String id, String password, String role) {
        //verify if employee meets parameters to login and work
        Employee p = personalDB.buscarPorId(id);
        return  p.getId().equals(id)
                && p.getPassword().equals(password)
                && p.getRole().equalsIgnoreCase(role)
                ? new LoginState(id, p.getName(), role, true)
                : null;
    }

    private void openDashboard(LoginState loginState) {
        if(loginState.getRole().equalsIgnoreCase("director")){
            new DirectorWindow(loginState);
        } else if (loginState.getRole().equalsIgnoreCase("employee")) {
            new EmployeeWindow(loginState);
        }
    }

    // Método para redimensionar el icono
    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
}
