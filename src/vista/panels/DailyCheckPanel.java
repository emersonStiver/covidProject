package vista.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DailyCheckPanel extends JPanel {
    private JTextField temperatureField;
    private JComboBox<String> healthStatusComboBox;
    private JCheckBox hadCovidCheckBox;
    private JButton submitButton;
    private JLabel statusLabel;

    public DailyCheckPanel() {
        setPreferredSize(new Dimension(400, 200));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

        // Initialize components
        temperatureField = new JTextField(10);
        healthStatusComboBox = new JComboBox<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"});
        hadCovidCheckBox = new JCheckBox("Had Covid");
        submitButton = new JButton("Submit");
        statusLabel = new JLabel("");

        // Add components to the panel
        add(new JLabel("Daily Temperature (°C):"), gbc);
        gbc.gridx = 1;
        add(temperatureField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Current Health Status (1-10):"), gbc);
        gbc.gridx = 1;
        add(healthStatusComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Had Covid:"), gbc);
        gbc.gridx = 1;
        add(hadCovidCheckBox, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(submitButton, gbc);

        gbc.gridy = 4;
        add(statusLabel, gbc);

        // Add action listeners
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get and submit health status data here
                double dailyTemperature = Double.parseDouble(temperatureField.getText());
                String currentHealthStatus = (String) healthStatusComboBox.getSelectedItem();
                boolean hadCovid = hadCovidCheckBox.isSelected();

                // Example: Print the collected data
                System.out.println("Daily Temperature: " + dailyTemperature);
                System.out.println("Current Health Status: " + currentHealthStatus);
                System.out.println("Had Covid: " + hadCovid);

                // Display status message
                statusLabel.setText("Health status submitted.");

                // Clear fields
                temperatureField.setText("");
                healthStatusComboBox.setSelectedIndex(0);
                hadCovidCheckBox.setSelected(false);
            }
        });

        setVisible(true);
    }

}
