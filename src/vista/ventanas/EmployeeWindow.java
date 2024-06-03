package vista.ventanas;

import modelo.LoginState;
import vista.Utilidades;
import vista.panels.DailyCheckPanel;
import vista.panels.ProfilePanel;
import vista.panels.RegisterNewEmployeePanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeWindow extends JFrame {
    private Utilidades utilidades;
    private JPanel panelIzquierda, panelDerecha;
    private LoginState loginState;
    public EmployeeWindow(LoginState loginState){
        //Configurar atributos de la ventana
        setResizable(false);
        setIconImage(new ImageIcon("src/recursos/hospital.png").getImage());
        setTitle("Panel de administrativo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(800, 600);
        setLocationRelativeTo(null);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Inicializar variables de la clase
        this.panelIzquierda = new JPanel();
        this.utilidades = new Utilidades();
        this.loginState = loginState;

        // Tarjeta de Opciones de Acciones
        add(crearPanelDerecha(), BorderLayout.CENTER);

        List<String[]> listMetaData = new ArrayList<>();
        listMetaData.add(new String[]{"Profile", "src/recursos/profile.jpg", "profilePanel"});
        listMetaData.add(new String[]{"Daily check", "src/recursos/check.png", "dailyCheckPanel"});
        listMetaData.add(new String[]{"Cerrar sesion", "src/recursos/logout.jpg", ""});
        JPanel panelIzquierda = utilidades.crearPanelIzquierdo(listMetaData, this.panelDerecha, this, this.loginState);

        add(panelIzquierda, BorderLayout.WEST);

        setVisible(true);
    }
    private JPanel crearPanelDerecha(){
        this.panelDerecha = new JPanel(new CardLayout());
        // Crear paneles que se mostraran en el panel Principal con base en la accion elegida
        panelDerecha.add(new ProfilePanel(this.loginState), "profilePanel");
        panelDerecha.add(new DailyCheckPanel(), "dailyCheckPanel");
        return this.panelDerecha;
    }

}