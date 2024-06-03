package vista.panels;


import modelo.Employee;
import modelo.PersonalBaseDeDatos;
import vista.Utilidades;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.Vector;

public class GestionarEmpleadosPanel extends JPanel {
    private Utilidades utilidades = new Utilidades();
    private JTable tablaEmpleados;
    private JLabel etiquetaFiltro;
    private JTextField campoFiltro;
    private PersonalBaseDeDatos personalBaseDeDatos = PersonalBaseDeDatos.getInstancia();

    public GestionarEmpleadosPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Agregar un poco de relleno

        // Título
        JLabel titulo = new JLabel("Centro de Empleados", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        add(titulo, BorderLayout.NORTH);

        // Sección de filtro
        JPanel panelFiltro = new JPanel(new BorderLayout());
        JPanel panelEntradaFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        etiquetaFiltro = new JLabel("Filtrar por Nombre:");
        campoFiltro = new JTextField(20);
        campoFiltro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrarEmpleados(campoFiltro.getText());
            }
        });
        panelEntradaFiltro.add(etiquetaFiltro);
        panelEntradaFiltro.add(campoFiltro);
        panelFiltro.add(panelEntradaFiltro, BorderLayout.WEST);

        // Botón para crear nuevo empleado
        JButton botonCrear = new JButton("Crear Nuevo Empleado");
        botonCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Campos vacíos para un nuevo emplead
                    abrirVentanaEditarEmpleado(tablaEmpleados, "", "John Doe", "","123 Main St", "2", "8",
                        false, "Low", true, false, "Good", false, 98.6, "Employee", "12345");

            }
        });
        botonCrear.setMaximumSize(new Dimension(Integer.MAX_VALUE, botonCrear.getPreferredSize().height / 2));
        panelFiltro.add(botonCrear, BorderLayout.LINE_END); // o BorderLayout.EAST
        add(panelFiltro, BorderLayout.CENTER);

        tablaEmpleados = new JTable(listarEmpleados());
        tablaEmpleados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaEmpleados.setEnabled(false);
        JScrollPane panelDesplazamiento = new JScrollPane(tablaEmpleados);
        add(panelDesplazamiento, BorderLayout.SOUTH);

        // Agregar oyente de doble clic a la tabla
        tablaEmpleados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int fila = tablaEmpleados.rowAtPoint(e.getPoint());
                    if (fila != -1) {
                        String id = (String) tablaEmpleados.getValueAt(fila, 0);
                        Employee empleado = personalBaseDeDatos.buscarPorId(id);
                        abrirVentanaEditarEmpleado(
                                tablaEmpleados,
                                empleado.getId(),
                                empleado.getName(),
                                empleado.getAddress(),
                                empleado.getLocality(),
                                empleado.getPeopleLivingWith(),
                                empleado.getCurrentHealthStatus(),
                                empleado.isHadCovid(),
                                empleado.getCovidImpactLevel(),
                                empleado.isHasComorbidities(),
                                empleado.isFamilyWithComorbidities(),
                                empleado.getDailyStatus(),
                                empleado.isContactWithCovidPersons(),
                                empleado.getDailyTemperature(),
                                empleado.getRole(),
                                empleado.getPassword()

                        );
                    }
                }
            }
        });
    }

    private DefaultTableModel listarEmpleados() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Rol");
        modelo.addColumn("Address");

        Collection<Employee> personalCentroMedico = personalBaseDeDatos.getPersonal();
        for (Employee p : personalCentroMedico) {
            Vector<String> columnas = new Vector<>();
            columnas.add(p.getId());
            columnas.add(p.getName());
            columnas.add(p.getRole());
            columnas.add(p.getAddress());
            modelo.addRow(columnas);
        }
        return modelo;
    }

    private void filtrarEmpleados(String nombre) {
        DefaultTableModel modelo = (DefaultTableModel) tablaEmpleados.getModel();
        TableRowSorter<DefaultTableModel> clasificador = new TableRowSorter<>(modelo);
        tablaEmpleados.setRowSorter(clasificador);
        clasificador.setRowFilter(RowFilter.regexFilter("(?i)" + nombre));
    }

    private void abrirVentanaEditarEmpleado(JTable tabla,  String id, String name, String address, String locality, String peopleLivingWith, String currentHealthStatus, boolean hadCovid, String covidImpactLevel, boolean hasComorbidities, boolean familyWithComorbidities, String dailyStatus, boolean contactWithCovidPersons, double dailyTemperature, String role, String password) {
        JDialog dialogoEditar = new JDialog(SwingUtilities.getWindowAncestor(this), "Edit Employee", Dialog.ModalityType.APPLICATION_MODAL);
        dialogoEditar.setSize(500, 400);
        dialogoEditar.setLocationRelativeTo(null);

        JPanel panelEditar = new JPanel(new GridLayout(0, 2, 10, 10));

        JTextField campoId = new JTextField(id);
        JTextField campoName = new JTextField(name);
        JTextField campoAddress = new JTextField(address);
        JTextField campoLocality = new JTextField(locality);
        JTextField campoPeopleLivingWith = new JTextField(peopleLivingWith);
        JTextField campoCurrentHealthStatus = new JTextField(currentHealthStatus);
        JCheckBox campoHadCovid = new JCheckBox();
        campoHadCovid.setSelected(hadCovid);
        JTextField campoCovidImpactLevel = new JTextField(covidImpactLevel);
        JCheckBox campoHasComorbidities = new JCheckBox();
        campoHasComorbidities.setSelected(hasComorbidities);
        JCheckBox campoFamilyWithComorbidities = new JCheckBox();
        campoFamilyWithComorbidities.setSelected(familyWithComorbidities);
        JTextField campoDailyStatus = new JTextField(dailyStatus);
        JCheckBox campoContactWithCovidPersons = new JCheckBox();
        campoContactWithCovidPersons.setSelected(contactWithCovidPersons);
        JTextField campoDailyTemperature = new JTextField(Double.toString(dailyTemperature));
        JTextField campoRole = new JTextField(role);
        JPasswordField campoPassword = new JPasswordField(password);

        // Add fields to the panel
        panelEditar.add(new JLabel("ID:"));
        panelEditar.add(campoId);
        panelEditar.add(new JLabel("Name:"));
        panelEditar.add(campoName);
        panelEditar.add(new JLabel("Address:"));
        panelEditar.add(campoAddress);
        panelEditar.add(new JLabel("Locality:"));
        panelEditar.add(campoLocality);
        panelEditar.add(new JLabel("People Living With:"));
        panelEditar.add(campoPeopleLivingWith);
        panelEditar.add(new JLabel("Current Health Status:"));
        panelEditar.add(campoCurrentHealthStatus);
        panelEditar.add(new JLabel("Had Covid:"));
        panelEditar.add(campoHadCovid);
        panelEditar.add(new JLabel("Covid Impact Level:"));
        panelEditar.add(campoCovidImpactLevel);
        panelEditar.add(new JLabel("Has Comorbidities:"));
        panelEditar.add(campoHasComorbidities);
        panelEditar.add(new JLabel("Family With Comorbidities:"));
        panelEditar.add(campoFamilyWithComorbidities);
        panelEditar.add(new JLabel("Daily Status:"));
        panelEditar.add(campoDailyStatus);
        panelEditar.add(new JLabel("Contact With Covid Persons:"));
        panelEditar.add(campoContactWithCovidPersons);
        panelEditar.add(new JLabel("Daily Temperature:"));
        panelEditar.add(campoDailyTemperature);
        panelEditar.add(new JLabel("Role:"));
        panelEditar.add(campoRole);
        panelEditar.add(new JLabel("Password:"));
        panelEditar.add(campoPassword);

        JButton botonGuardar = new JButton("Save Changes");
        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nuevoId = campoId.getText();
                String nuevoName = campoName.getText();
                String nuevoAddress = campoAddress.getText();
                String nuevoLocality = campoLocality.getText();
                String nuevoPeopleLivingWith = campoPeopleLivingWith.getText();
                String nuevoCurrentHealthStatus = campoCurrentHealthStatus.getText();
                boolean nuevoHadCovid = campoHadCovid.isSelected();
                String nuevoCovidImpactLevel = campoCovidImpactLevel.getText();
                boolean nuevoHasComorbidities = campoHasComorbidities.isSelected();
                boolean nuevoFamilyWithComorbidities = campoFamilyWithComorbidities.isSelected();
                String nuevoDailyStatus = campoDailyStatus.getText();
                boolean nuevoContactWithCovidPersons = campoContactWithCovidPersons.isSelected();
                double nuevoDailyTemperature = Double.parseDouble(campoDailyTemperature.getText());
                String nuevoRole = campoRole.getText();
                String nuevoPassword = new String(campoPassword.getPassword());

                // Create Employee object with the updated information
                Employee empleado = new Employee(
                        nuevoId, nuevoName, nuevoPassword, nuevoAddress, nuevoLocality, nuevoPeopleLivingWith, nuevoCurrentHealthStatus,
                        nuevoHadCovid, nuevoCovidImpactLevel, nuevoHasComorbidities, nuevoFamilyWithComorbidities,
                        nuevoDailyStatus, nuevoContactWithCovidPersons, nuevoDailyTemperature, nuevoRole
                );

                // Add logic to save the updated employee
                personalBaseDeDatos.guardarActualizar(empleado);
                dialogoEditar.dispose();
            }
        });

        dialogoEditar.add(new JScrollPane(panelEditar));

        JPanel accionesPanel = new JPanel();
        accionesPanel.add(botonGuardar);

        dialogoEditar.add(accionesPanel, BorderLayout.SOUTH);
        dialogoEditar.setVisible(true);
    }

}
