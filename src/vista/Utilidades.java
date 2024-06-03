package vista;

import modelo.LoginState;
import vista.ventanas.VentanaLogin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Utilidades {

    private JScrollPane createActionOptionsCard(List<String[]> actionsMedaData, JPanel panel, JFrame parent) {
        JPanel actionOptionsCard = new JPanel();
        actionOptionsCard.setBorder(null);
        actionOptionsCard.setBackground(new Color(176, 196, 222)); // Light blue
        actionOptionsCard.setLayout(new BoxLayout(actionOptionsCard, BoxLayout.Y_AXIS));
        for(String[] list : actionsMedaData){
            JPanel action = new JPanel();
            action.setBackground(new Color(176, 196, 222)); // Light blue
            action.setLayout(new BoxLayout(action, BoxLayout.Y_AXIS));
            action.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel manageLabel = new JLabel(list[0]);
            manageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            ImageIcon manageIcon = new ImageIcon(list[1]);
            JButton manageBtn = new JButton(resizeIcon(manageIcon, 60, 60));
            manageBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            manageBtn.setBackground(Color.WHITE);

            manageBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(list[2].equals("")){
                        parent.dispose();
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                new VentanaLogin();
                            }
                        });
                    }else{
                        CardLayout cardLayout = (CardLayout) panel.getLayout();
                        cardLayout.show(panel, list[2]);
                    }

                }
            });
            action.add(manageLabel);
            action.add(manageBtn);
            action.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
            actionOptionsCard.add(action);
        }

        // Add a scroll pane
        JScrollPane scrollPane = new JScrollPane(actionOptionsCard);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        return scrollPane;
    }

    private JPanel createPersonalInfoCard(JPanel sidePanel, String iconPath,  String name, String position) {

        JPanel doctorInfoCard = new JPanel();
        doctorInfoCard.setBackground(new Color(176, 196, 222)); // Light blue
        doctorInfoCard.setLayout(new BoxLayout(doctorInfoCard, BoxLayout.Y_AXIS));
        doctorInfoCard.setSize(sidePanel.getWidth(),sidePanel.getHeight()/3);

        // Doctor icon
        ImageIcon doctorIcon = new ImageIcon(iconPath);
        JLabel iconLabel = new JLabel(resizeIcon(doctorIcon, 70,90));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        doctorInfoCard.add(iconLabel);

        // Doctor name label
        JLabel nameLabel = new JLabel(name);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(5,0,0,0));
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        doctorInfoCard.add(nameLabel);

        // Doctor specialty label
        JLabel specialtyLabel = new JLabel(position);
        specialtyLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        specialtyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        doctorInfoCard.add(specialtyLabel);

        return doctorInfoCard;
    }
    public ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }

    public JPanel crearPanelIzquierdo(List<String[]> listMetaData, JPanel panelDerecha, JFrame ventana, LoginState loginState) {
        JPanel panelIzquierda = new JPanel(new BorderLayout());
        panelIzquierda.setPreferredSize(new Dimension(150, ventana.getHeight()));
        panelIzquierda.setBackground(new Color(176, 196, 222));

        // Tarjeta de Información Personal
        JPanel tarjetaInfoPersonal = this.createPersonalInfoCard(panelIzquierda, "src/recursos/user.png", loginState.getName(), loginState.getRole());
        tarjetaInfoPersonal.setBackground(new Color(176, 196, 222));
        tarjetaInfoPersonal.setBorder(BorderFactory.createEmptyBorder(10, 10, 30, 10));

        // Tarjeta de Opciones de Acciones
        JScrollPane tarjetaOpcionesAccion = this.createActionOptionsCard(listMetaData, panelDerecha, ventana);

        //Guardamos los subpaneles de acciones y info personal en el panel izquierdo
        panelIzquierda.add(tarjetaInfoPersonal, BorderLayout.NORTH);
        panelIzquierda.add(tarjetaOpcionesAccion, BorderLayout.CENTER);
        return panelIzquierda;
    }

    public void mostrarResultadoActualizarTabla(DefaultTableModel model, String mensajeExito, String mensajeFallo, boolean resultado, JDialog dialog, JTable tabla){
        if(resultado){
            JOptionPane.showMessageDialog(dialog, mensajeExito, "Resultado", JOptionPane.INFORMATION_MESSAGE);
            dialog.dispose();
        }else{
            JOptionPane.showMessageDialog(dialog, mensajeFallo, "Error", JOptionPane.ERROR_MESSAGE);
            SwingUtilities.invokeLater(() -> {
                tabla.setModel(model);
            });
        }
    }

}