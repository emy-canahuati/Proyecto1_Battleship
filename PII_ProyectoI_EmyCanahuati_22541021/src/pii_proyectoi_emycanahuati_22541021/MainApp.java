/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pii_proyectoi_emycanahuati_22541021;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author emyca
 */
public class MainApp extends JFrame{
    Player player= new Player();
    Battleship battle= new Battleship();
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
    
//componentes de menu de inicio
    private JMenu menuInicio;
    private JMenuItem login,crearPlayer, salir;
    private JMenuBar barraMenu;
    
    //componentes de menu principal
    private final Font fuente= new Font("Arial", Font.PLAIN,30);
    
    public MainApp(){
        setVisible(false);
        menuInicio();
        
    }
    
    public void menuInicio(){
        JFrame Inicio = new JFrame();
        this.setLayout(null);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setSize(600,450);
        this.setLocationRelativeTo(null);
       
        barraMenu= new JMenuBar();
        menuInicio= new JMenu("Menu de Inicio");
        menuInicio.setFont(fuente);
        login= new JMenuItem("Login");
        login.setFont(fuente);
        crearPlayer = new JMenuItem ("Crear Player");
        crearPlayer.setFont(fuente);
        salir= new JMenuItem ("Salir");
        salir.setFont(fuente);
        menuInicio.add(login);
        login.addActionListener(e -> ventana_login());
        menuInicio.addSeparator();
        menuInicio.add(crearPlayer);
        crearPlayer.addActionListener(e -> ventana_crear());
        menuInicio.addSeparator();
        menuInicio.add(salir);
        salir.addActionListener(e -> System.exit(0));
        barraMenu.add(menuInicio);
        this.setJMenuBar(barraMenu);
        this.setVisible(true);
        
    
    }
    
    public void ventana_login() {
        JFrame ventana_login = new JFrame();
        ventana_login.setLayout(null);
        ventana_login.setSize(600, 450);
        ventana_login.setTitle("Login");
        ventana_login.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        ventana_login.setLocationRelativeTo(null);

        tituloLogin = new JLabel("LOGIN");
        tituloLogin.setBounds(30, 30, 250, 30);
        tituloLogin.setFont(fuente);
        ventana_login.add(tituloLogin);

        etiquetaNomLogin = new JLabel("Ingrese su nombre de usuario:");
        etiquetaNomLogin.setBounds(30, 80, 450, 30);
        etiquetaNomLogin.setFont(fuente);
        ventana_login.add(etiquetaNomLogin);

        etiquetaContraLogin = new JLabel("Ingrese su contraseña:");
        etiquetaContraLogin.setBounds(30, 185, 450, 30);
        etiquetaContraLogin.setFont(fuente);
        ventana_login.add(etiquetaContraLogin);

        nomPlayer1 = new JTextField();
        nomPlayer1.setBounds(30, 135, 450, 40);
        nomPlayer1.setFont(fuente);
        ventana_login.add(nomPlayer1);

        contraPlayer1 = new JPasswordField();
        contraPlayer1.setBounds(30, 235, 450, 40);
        contraPlayer1.setFont(fuente);
        ventana_login.add(contraPlayer1);

        ingresar = new JButton("Ingresar");
        ingresar.setBounds(50, 300, 180, 50);
        ingresar.setFont(fuente);
        ventana_login.add(ingresar);
        ingresar.addActionListener(ev -> {
            if (battle.Login(nomPlayer1.getText(), contraPlayer1.getText())) {
                ventana_login.dispose();
                MenuPrincipal menu = new MenuPrincipal(battle);
                menu.setVisible(true);
            }
        });

        cancelarLogin = new JButton("Cancelar");
        cancelarLogin.setBounds(350, 300, 180, 50);
        cancelarLogin.setFont(fuente);
        ventana_login.add(cancelarLogin);

        cancelarLogin.addActionListener(i -> menuInicio());
        ventana_login.setVisible(true);

    }
    
    public void ventana_crear() {
        JFrame ventana_crear = new JFrame();
        ventana_crear.setLayout(null);
        ventana_crear.setTitle("Crear Player");
        ventana_crear.setSize(600, 450);
        ventana_crear.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        ventana_crear.setLocationRelativeTo(null);

        tituloCrear = new JLabel("CREAR UN NUEVO PLAYER");
        tituloCrear.setBounds(30, 30, 400, 30);
        tituloCrear.setFont(fuente);
        ventana_crear.add(tituloCrear);

        etiquetaNomCrear = new JLabel("Ingrese su nombre de usuario:");
        etiquetaNomCrear.setBounds(30, 80, 450, 30);
        etiquetaNomCrear.setFont(fuente);
        ventana_crear.add(etiquetaNomCrear);

        etiquetaContraCrear = new JLabel("Ingrese su contraseña:");
        etiquetaContraCrear.setBounds(30, 185, 450, 30);
        etiquetaContraCrear.setFont(fuente);
        ventana_crear.add(etiquetaContraCrear);

        nomPlayerCrear = new JTextField();
        nomPlayerCrear.setBounds(30, 135, 450, 40);
        nomPlayerCrear.setFont(fuente);
        ventana_crear.add(nomPlayerCrear);

        contraPlayerCrear = new JPasswordField();
        contraPlayerCrear.setBounds(30, 235, 450, 40);
        contraPlayerCrear.setFont(fuente);
        ventana_crear.add(contraPlayerCrear);

        crear = new JButton("Crear");
        crear.setBounds(50, 300, 180, 50);
        crear.setFont(fuente);
        ventana_crear.add(crear);
        crear.addActionListener(ev -> {
            if (nomPlayerCrear.getText().trim().isEmpty() || contraPlayerCrear.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error: No puede dejar ninguno de los campos vacios");
            } else {
                if (battle.crearPlayers(nomPlayerCrear.getText(), contraPlayerCrear.getText()) == -1) {
                    JOptionPane.showMessageDialog(null, "Error: Este player ya existe");
                } else {
                    JOptionPane.showMessageDialog(null, "Jugador Creado");
                }
            }
        });

        cancelarCrear = new JButton("Cancelar");
        cancelarCrear.setBounds(350, 300, 180, 50);
        cancelarCrear.setFont(fuente);
        ventana_crear.add(cancelarCrear); 
        cancelarCrear.addActionListener(i -> ventana_crear.dispose());

        ventana_crear.setVisible(true);
    }


}
