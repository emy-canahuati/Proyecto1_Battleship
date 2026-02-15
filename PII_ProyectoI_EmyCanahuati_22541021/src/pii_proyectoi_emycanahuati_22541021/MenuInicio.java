/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pii_proyectoi_emycanahuati_22541021;

import java.awt.*;
import javax.swing.JTextField;
import javax.swing.*;

/**
 *
 * @author emyca
 */
public class MenuInicio extends JFrame{
    Battleship battle= new Battleship();
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final Font fuente= new Font("Arial", Font.PLAIN,30);
   
    //componentes de menu de inicio
    private JLabel menuInicio;
    private JButton login,crearPlayer, salir;
    
    //componentes de ventana de Login
    private JLabel etiquetaNomLogin, etiquetaContraLogin, tituloLogin;
    private JTextField nomPlayer1;
    private JPasswordField contraPlayer1;
    private JButton ingresar, cancelarLogin;
    
    //componentes de ventana de crear
    private JLabel tituloCrear, etiquetaNomCrear, etiquetaContraCrear;
    private JPasswordField contraPlayerCrear;
    private JTextField nomPlayerCrear;
    private JButton crear, cancelarCrear;
        
    public MenuInicio(){
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        menuInicio();
        
    }
    
    private JPanel crearContenedorConFondo(int anchoCuadro, int altoCuadro) {
        JPanel panelFondo = new JPanel(null);
        panelFondo.setBounds(0, 0, screenSize.width, screenSize.height);

        JPanel cuadroBlanco = new JPanel(null);
        cuadroBlanco.setBackground(Color.WHITE);
        cuadroBlanco.setBounds((screenSize.width - anchoCuadro) / 2, (screenSize.height - altoCuadro) / 2, anchoCuadro, altoCuadro);

        JLabel fondo = new JLabel();
        ImageIcon imgFondo = new ImageIcon("src/Imagenes/wallpaper.jpg");
        Image imgSized = imgFondo.getImage().getScaledInstance(screenSize.width, screenSize.height, Image.SCALE_SMOOTH);
        fondo.setIcon(new ImageIcon(imgSized));
        fondo.setBounds(0, 0, screenSize.width, screenSize.height);

        panelFondo.add(cuadroBlanco);
        panelFondo.add(fondo);

        return panelFondo;
    }
    
    public void menuInicio(){
        getContentPane().removeAll();
        JPanel contenedor = crearContenedorConFondo(600, 400);
        JPanel cuadro = (JPanel) contenedor.getComponent(0);

        menuInicio = new JLabel("Menú de Inicio", SwingConstants.CENTER);
        menuInicio.setFont(fuente);
        menuInicio.setBounds(0, 30, 600, 50);
        cuadro.add(menuInicio);

        login = new JButton("Login");
        login.setFont(fuente);
        login.setBounds(50, 110, 500, 50);
        cuadro.add(login);

        crearPlayer = new JButton("Crear Player");
        crearPlayer.setFont(fuente);
        crearPlayer.setBounds(50, 190, 500, 50);
        cuadro.add(crearPlayer);

        salir = new JButton("Salir");
        salir.setFont(fuente);
        salir.setBounds(50, 270, 500, 50);
        cuadro.add(salir);

        login.addActionListener(e -> ventana_login());
        crearPlayer.addActionListener(e -> ventana_crear());
        salir.addActionListener(e -> System.exit(0));

        add(contenedor);
        setVisible(true);
        revalidate();
        repaint();
        
    
    }
    
    public void ventana_login() {
        getContentPane().removeAll();
        JPanel contenedor = crearContenedorConFondo(600, 450);
        JPanel cuadro = (JPanel) contenedor.getComponent(0);

        tituloLogin = new JLabel("LOGIN", SwingConstants.CENTER);
        tituloLogin.setBounds(0, 30, 600, 40);
        tituloLogin.setFont(new Font("Arial", Font.BOLD, 35));
        cuadro.add(tituloLogin);

        etiquetaNomLogin = new JLabel("Ingrese su Nombre de Usuario:");
        etiquetaNomLogin.setBounds(50, 90, 450, 30);
        etiquetaNomLogin.setFont(fuente);
        cuadro.add(etiquetaNomLogin);

        nomPlayer1 = new JTextField();
        nomPlayer1.setBounds(50, 130, 500, 45);
        nomPlayer1.setFont(fuente);
        cuadro.add(nomPlayer1);

        etiquetaContraLogin = new JLabel("Ingrese su Contraseña:");
        etiquetaContraLogin.setBounds(50, 195, 450, 30);
        etiquetaContraLogin.setFont(fuente);
        cuadro.add(etiquetaContraLogin);

        contraPlayer1 = new JPasswordField();
        contraPlayer1.setBounds(50, 235, 500, 45);
        contraPlayer1.setFont(fuente);
        cuadro.add(contraPlayer1);

        ingresar = new JButton("Ingresar");
        ingresar.setBounds(50, 320, 230, 50);
        ingresar.setFont(fuente);
        ingresar.addActionListener(ev -> {
            try{
                if (battle.Login(nomPlayer1.getText(), new String(contraPlayer1.getPassword()))) {
                    menuInicio();
                    MenuPrincipal menu = new MenuPrincipal(battle);
            }
            }catch (Exception excepcion){
                JOptionPane.showMessageDialog(null, excepcion.getMessage());
            }
        });
        cuadro.add(ingresar);

        cancelarLogin = new JButton("Regresar");
        cancelarLogin.setBounds(320, 320, 230, 50);
        cancelarLogin.setFont(fuente);
        cancelarLogin.addActionListener(e -> menuInicio());
        cuadro.add(cancelarLogin);

        add(contenedor);
        revalidate();
        repaint();

    }
    
    public void ventana_crear() {
        getContentPane().removeAll();
        JPanel contenedor = crearContenedorConFondo(600, 450);
        JPanel cuadro = (JPanel) contenedor.getComponent(0);

        tituloCrear = new JLabel("CREAR PLAYERS", SwingConstants.CENTER);
        tituloCrear.setBounds(0, 30, 600, 40);
        tituloCrear.setFont(new Font("Arial", Font.BOLD, 35));
        cuadro.add(tituloCrear);

        etiquetaNomCrear = new JLabel("Ingrese un nombre de usuario:");
        etiquetaNomCrear.setBounds(50, 90, 450, 30);
        etiquetaNomCrear.setFont(fuente);
        cuadro.add(etiquetaNomCrear);

        nomPlayerCrear = new JTextField();
        nomPlayerCrear.setBounds(50, 130, 500, 45);
        nomPlayerCrear.setFont(fuente);
        cuadro.add(nomPlayerCrear);

        etiquetaContraCrear = new JLabel("Ingrese una contraseña:");
        etiquetaContraCrear.setBounds(50, 195, 450, 30);
        etiquetaContraCrear.setFont(fuente);
        cuadro.add(etiquetaContraCrear);

        contraPlayerCrear = new JPasswordField();
        contraPlayerCrear.setBounds(50, 235, 500, 45);
        contraPlayerCrear.setFont(fuente);
        cuadro.add(contraPlayerCrear);

        crear = new JButton("Crear");
        crear.setBounds(50, 320, 230, 50);
        crear.setFont(fuente);
        crear.addActionListener(ev -> {
            if (nomPlayerCrear.getText().trim().isEmpty() || contraPlayerCrear.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error: No puede dejar ninguno de los campos vacios");
            } else {
                if (battle.crearPlayers(nomPlayerCrear.getText(), contraPlayerCrear.getText())) {
                    JOptionPane.showMessageDialog(null, "¡Jugador Creado!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error: Este player ya existe");
                }
            }
        });
        cuadro.add(crear);
        
        cancelarCrear = new JButton("Regresar");
        cancelarCrear.setBounds(320, 320, 230, 50);
        cancelarCrear.setFont(fuente);
        cancelarCrear.addActionListener(e -> menuInicio());
        cuadro.add(cancelarCrear);

        add(contenedor);
        revalidate();
        repaint();
    }


}
