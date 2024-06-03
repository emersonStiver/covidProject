package vista.ventanas;

import modelo.LoginState;
import vista.Utilidades;
import vista.panels.GestionarEmpleadosPanel;
import vista.panels.ProfilePanel;
import vista.panels.RegisterNewEmployeePanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DirectorWindow extends JFrame {
    private JPanel  panelDerecha;
    private Utilidades utilidades;
    private LoginState loginState;

    public DirectorWindow(LoginState loginState) {
        //Configurar atributos de la ventana
        setResizable(false);
        setTitle("Panel de Director");
        setIconImage(new ImageIcon("src/recursos/hospital.png").getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(800, 600);
        setLocationRelativeTo(null);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Inicializacion de propiedades de la ventana
        this.loginState = loginState;
        this.utilidades = new Utilidades();

        add(crearPanelDerecha(), BorderLayout.CENTER);

        List<String[]> listMetaData = new ArrayList<>();
        listMetaData.add(new String[]{"Profile", "src/recursos/profile.jpg", "profilePanel"});
        listMetaData.add(new String[]{"Crear empleado", "src/recursos/register.png", "patientRegistrationPanel"});
        listMetaData.add(new String[]{"Cerrar sesion", "src/recursos/logout.jpg", ""});
        JPanel panelIzquierda = utilidades.crearPanelIzquierdo(listMetaData,this.panelDerecha,this, this.loginState);

        add(panelIzquierda, BorderLayout.WEST);

        setVisible(true);
    }
    private JPanel crearPanelDerecha(){
        this.panelDerecha = new JPanel(new CardLayout());
        // Crear paneles que se mostraran en el panel Principal con base en la accion elegida
        panelDerecha.add(new ProfilePanel(loginState), "profilePanel");
        panelDerecha.add(new GestionarEmpleadosPanel(), "patientRegistrationPanel");
        return this.panelDerecha;
    }

}