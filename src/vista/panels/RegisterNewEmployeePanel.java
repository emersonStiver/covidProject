package vista.panels;

import modelo.Employee;
import modelo.PersonalBaseDeDatos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterNewEmployeePanel extends JPanel {
    private PersonalBaseDeDatos personalBaseDeDatos = PersonalBaseDeDatos.getInstancia();
    private JTextField idField;
    private JTextField nameField;
    private JTextField addressField;
    private JTextField localityField;
    private JTextField peopleLivingWithField;
    private JTextField currentHealthStatusField;
    private JCheckBox hadCovidCheckBox;
    private JTextField covidImpactLevelField;
    private JCheckBox hasComorbiditiesCheckBox;
    private JCheckBox familyWithComorbiditiesCheckBox;
    private JTextField dailyStatusField;
    private JCheckBox contactWithCovidPersonsCheckBox;
    private JTextField dailyTemperatureField;
    private JTextField roleField;
    private JPasswordField passwordField; // Added password field
    private JButton submitButton;
    private JLabel statusLabel;

    public RegisterNewEmployeePanel() {
        setLayout(new GridLayout(17, 2));

        // Initialize components
        idField = new JTextField(20);
        nameField = new JTextField(20);
        addressField = new JTextField(20);
        localityField = new JTextField(20);
        peopleLivingWithField = new JTextField(20);
        currentHealthStatusField = new JTextField(20);
        hadCovidCheckBox = new JCheckBox();
        covidImpactLevelField = new JTextField(20);
        hasComorbiditiesCheckBox = new JCheckBox();
        familyWithComorbiditiesCheckBox = new JCheckBox();
        dailyStatusField = new JTextField(20);
        contactWithCovidPersonsCheckBox = new JCheckBox();
        dailyTemperatureField = new JTextField(20);
        roleField = new JTextField(20);
        passwordField = new JPasswordField(20); // Initialize password field
        submitButton = new JButton("Create Employee");
        statusLabel = new JLabel("");

        // Add components to the panel
        add(new JLabel("ID:"));
        add(idField);
        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Address:"));
        add(addressField);
        add(new JLabel("Locality:"));
        add(localityField);
        add(new JLabel("People Living With:"));
        add(peopleLivingWithField);
        add(new JLabel("Current Health Status:"));
        add(currentHealthStatusField);
        add(new JLabel("Had Covid:"));
        add(hadCovidCheckBox);
        add(new JLabel("Covid Impact Level:"));
        add(covidImpactLevelField);
        add(new JLabel("Has Comorbidities:"));
        add(hasComorbiditiesCheckBox);
        add(new JLabel("Family With Comorbidities:"));
        add(familyWithComorbiditiesCheckBox);
        add(new JLabel("Daily Status:"));
        add(dailyStatusField);
        add(new JLabel("Contact With Covid Persons:"));
        add(contactWithCovidPersonsCheckBox);
        add(new JLabel("Daily Temperature:"));
        add(dailyTemperatureField);
        add(new JLabel("Role:"));
        add(roleField);
        add(new JLabel("Password:")); // Add label for password
        add(passwordField); // Add password field
        add(submitButton);
        add(statusLabel);

        // Add button action listener
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get values from fields
                    String id = idField.getText();
                    String name = nameField.getText();
                    String address = addressField.getText();
                    String locality = localityField.getText();
                    String peopleLivingWith = peopleLivingWithField.getText();
                    String currentHealthStatus = currentHealthStatusField.getText();
                    boolean hadCovid = hadCovidCheckBox.isSelected();
                    String covidImpactLevel = covidImpactLevelField.getText();
                    boolean hasComorbidities = hasComorbiditiesCheckBox.isSelected();
                    boolean familyWithComorbidities = familyWithComorbiditiesCheckBox.isSelected();
                    String dailyStatus = dailyStatusField.getText();
                    boolean contactWithCovidPersons = contactWithCovidPersonsCheckBox.isSelected();
                    double dailyTemperature = Double.parseDouble(dailyTemperatureField.getText());
                    String role = roleField.getText();
                    String password = new String(passwordField.getPassword());

                    // Create Employee object
                    Employee employee = new Employee(
                            id, name, password, address, locality, peopleLivingWith, currentHealthStatus, hadCovid,
                            covidImpactLevel, hasComorbidities, familyWithComorbidities,
                            dailyStatus, contactWithCovidPersons, dailyTemperature, role
                    );

                    personalBaseDeDatos.guardarActualizar(employee);
                    idField.setText("");
                    nameField.setText("");
                    addressField.setText("");
                    localityField.setText("");
                    peopleLivingWithField.setText("");
                    currentHealthStatusField.setText("");
                    hadCovidCheckBox.setSelected(false);
                    covidImpactLevelField.setText("");
                    hasComorbiditiesCheckBox.setSelected(false);
                    familyWithComorbiditiesCheckBox.setSelected(false);
                    dailyStatusField.setText("");
                    contactWithCovidPersonsCheckBox.setSelected(false);
                    dailyTemperatureField.setText("");
                    roleField.setText("");
                    passwordField.setText("");

                    statusLabel.setText("Employee created: " + employee.getName());
                } catch (NumberFormatException ex) {
                    statusLabel.setText("Invalid input: " + ex.getMessage());
                }
            }
        });
    }

}
