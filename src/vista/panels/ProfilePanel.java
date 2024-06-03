package vista.panels;

import modelo.Employee;
import modelo.LoginState;
import modelo.PersonalBaseDeDatos;

import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends JPanel {
    private JLabel nameLabel;
    private JLabel roleLabel;
    private JLabel idLabel;
    private JLabel addressLabel;
    private JLabel emailLabel;
    private PersonalBaseDeDatos personalBaseDeDatos = PersonalBaseDeDatos.getInstancia();

    public ProfilePanel(LoginState loginState) {
        setPreferredSize(new Dimension(400, 200));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

        Employee employee = personalBaseDeDatos.buscarPorId(loginState.getId());

        // Name
        nameLabel = new JLabel("Name:");
        add(nameLabel, gbc);
        gbc.gridx = 1;
        add(new JLabel(employee.getName()), gbc);

        // Role
        gbc.gridx = 0;
        gbc.gridy = 1;
        roleLabel = new JLabel("Role:");
        add(roleLabel, gbc);
        gbc.gridx = 1;
        add(new JLabel(employee.getRole()), gbc);

        // ID
        gbc.gridx = 0;
        gbc.gridy = 2;
        idLabel = new JLabel("ID:");
        add(idLabel, gbc);
        gbc.gridx = 1;
        add(new JLabel(employee.getId()), gbc);

        // Address
        gbc.gridx = 0;
        gbc.gridy = 3;
        addressLabel = new JLabel("Address:");
        add(addressLabel, gbc);
        gbc.gridx = 1;
        add(new JLabel(employee.getAddress()), gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = 4;
        emailLabel = new JLabel("Address:");
        add(emailLabel, gbc);
        gbc.gridx = 1;
        add(new JLabel(employee.getAddress()), gbc);

        // Set background color and make the panel visible
        setBackground(Color.WHITE);
        setVisible(true);
    }
}
