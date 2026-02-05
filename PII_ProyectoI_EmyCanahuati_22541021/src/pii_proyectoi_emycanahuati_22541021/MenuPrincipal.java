/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pii_proyectoi_emycanahuati_22541021;

/**
 *
 * @author emyca
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class MenuPrincipal extends JFrame{
    protected Battleship battle;
    
    //componentes del menu principal
    protected JMenuBar barraMenu;
    protected JMenu menuPrincipal;
    protected JMenuItem jugarBattleship, configuracion, reportes, miPerfil, salir;
    protected Font fuente= new Font("Arial", Font.PLAIN,30);
    
    //componentes de ventana Jugar Battleship
    protected JLabel nombre, tituloBattle, player_turno, contador_bombas,contador_barcos, status;
    protected JTextField nom_Player2;
    protected JButton exit, ingresar;
    
    //componentes de ventana configuracion
    protected JLabel tituloConfi, dificultad, modo;
    protected JPopupMenu menuDifi, menuModo;
    protected JMenuItem easy, normal, expert, genius, tutorial, arcade;
    protected JButton regresar;
    
    public MenuPrincipal (Battleship battle){
        menuPrincipal();
        this.battle=battle;
    }
    
    public void menuPrincipal(){
        JFrame Principal= new JFrame();
        Principal.setLayout(new BorderLayout());
        Principal.setSize(600,450);
        Principal.setLocationRelativeTo(null);
        
        barraMenu= new JMenuBar();
        menuPrincipal= new JMenu("Menu Principal");
        menuPrincipal.setFont(fuente);
        jugarBattleship= new JMenuItem("Jugar Battleship");
        jugarBattleship.setFont(fuente);
        configuracion = new JMenuItem ("Configuración");
        configuracion.setFont(fuente);
        reportes= new JMenuItem ("Reportes");
        reportes.setFont(fuente);
        miPerfil= new JMenuItem("Mi Perfil");
        miPerfil.setFont(fuente);
        salir= new JMenuItem("Salir");
        salir.setFont(fuente);
        menuPrincipal.add(jugarBattleship);
        jugarBattleship.addActionListener(e-> ventana_jugarBattleship());
        menuPrincipal.addSeparator();
        menuPrincipal.add(configuracion);
        configuracion.addActionListener(e -> ventana_configuracion());
        menuPrincipal.addSeparator();
        menuPrincipal.add(reportes);
        menuPrincipal.addSeparator();
        menuPrincipal.add(miPerfil);
        menuPrincipal.addSeparator();
        menuPrincipal.add(salir);
        salir.addActionListener(e-> Principal.dispose());
        barraMenu.add(menuPrincipal);
        Principal.setJMenuBar(barraMenu);
        Principal.setVisible(true);
        
    }
    
    public void ventana_configuracion(){
        JFrame configuracion= new JFrame("Configuración");
        configuracion.setSize(600,300);
        configuracion.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        configuracion.setLocationRelativeTo(null);
        configuracion.setLayout(null);
        
        tituloConfi= new JLabel("CONFIGURACIÓN");
        tituloConfi.setFont(fuente);
        tituloConfi.setBounds(20,30,200,40);
        configuracion.add(tituloConfi);
        
        dificultad = new JLabel("Dificultad");
        dificultad.setFont(fuente);
        dificultad.setBounds(20,90,200,40);
        configuracion.add(dificultad);
        
        menuDifi= new JPopupMenu();
        menuDifi.setBounds(20, 140 , 200,40);
        easy = new JMenuItem("EASY");
        easy.setFont(fuente);
        normal = new JMenuItem("NORMAL");
        normal.setFont(fuente);
        expert= new JMenuItem("EXPERT");
        expert.setFont(fuente);
        genius= new JMenuItem("GENIUS");
        genius.setFont(fuente);
        menuDifi.add(easy);
        menuDifi.addSeparator();
        menuDifi.add(normal);
        menuDifi.addSeparator();
        menuDifi.add(expert);
        menuDifi.addSeparator();
        menuDifi.add(genius);
        configuracion.add(menuDifi);
        
        modo= new JLabel("Modo de Juego");
        modo.setFont(fuente);
        modo.setBounds(250,90,200,40);
        
        menuModo= new JPopupMenu();
        menuModo.setBounds(20, 140 , 200,40);
        easy = new JMenuItem("EASY");
        easy.setFont(fuente);
        normal = new JMenuItem("NORMAL");
        normal.setFont(fuente);
        expert= new JMenuItem("EXPERT");
        expert.setFont(fuente);
        genius= new JMenuItem("GENIUS");
        genius.setFont(fuente);
        menuModo.add(easy);
        menuModo.addSeparator();
        menuModo.add(normal);
        menuModo.addSeparator();
        menuModo.add(expert);
        menuModo.addSeparator();
        menuModo.add(genius);
        configuracion.add(menuModo);
        
        regresar= new JButton("Regresar");
        regresar.setFont(fuente);
        regresar.setBounds(20, 200,200,40);
        configuracion.add(regresar);
        configuracion.setVisible(true);
        
                
                
    }
    
    public void ventana_jugarBattleship() {
        JFrame jugar = new JFrame("Jugar Battleship");
        jugar.setSize(600,300);
        jugar.setLocationRelativeTo(null);
        jugar.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // solo cierra esta ventana
        jugar.setLayout(null);
            
        tituloBattle= new JLabel("JUGAR BATTLESHIP");
        tituloBattle.setFont(fuente);
        tituloBattle.setBounds(20,20,500,40);
        jugar.add(tituloBattle);
            
        nombre = new JLabel("Ingrese el username del Player 2:");
        nombre.setFont(fuente);
        nombre.setBounds(20, 80, 500, 40);
        jugar.add(nombre);
        
        nom_Player2 = new JTextField();
        nom_Player2.setFont(fuente);
        nom_Player2.setBounds(20, 130, 400, 40);    
        jugar.add(nom_Player2);
        exit = new JButton("EXIT");
        exit.setFont(fuente);
        exit.setBounds(230, 200, 200, 40);
        exit.addActionListener(ev -> jugar.dispose());            
        jugar.add(exit);

        ingresar= new JButton ("Ingresar");
        ingresar.setFont(fuente);
        ingresar.setBounds(20, 200, 200, 40);
        ingresar.addActionListener(ev ->{
            if (battle.setJugador2(nom_Player2.getText())){
                JOptionPane.showMessageDialog(null, "Player 2 Ingresado");
                panel_ColocarBarcos(jugar);
            }else{
                jugar.dispose();
            }
        });
            
        jugar.add(ingresar);
        jugar.setVisible(true);
   
    }     
    public void panel_ColocarBarcos(JFrame jugar){
        battle.barcosDisponibles();//metodo que setea el contador de barcos
        jugar.getContentPane().removeAll();//borra los componentes que estaban ahi antes
        jugar.setSize(900,750);
        jugar.setLocationRelativeTo(null);

        tituloBattle= new JLabel("JUGAR BATTLESHIP");
        tituloBattle.setFont(fuente);
        tituloBattle.setBounds(20,20,500,40);
        jugar.add(tituloBattle);

        player_turno= new JLabel ("Turno: Player "+battle.getTurno());
        player_turno.setFont(fuente);
        player_turno.setBounds(20, 80, 300, 40);
        jugar.add(player_turno);

        contador_barcos= new JLabel ("Barcos Disponibles: "+battle.getBarcosDisp());
        contador_barcos.setFont(fuente);
        contador_barcos.setBounds(20, 130, 300, 40);
        jugar.add(contador_barcos);

        JPanel panelBarcos = new JPanel(new GridLayout(8, 8));
        panelBarcos.setBounds(20, 190, 550, 400);
        //creacion del tablero
        JButton[][] ubi_barcos = new JButton[8][8];
        for (int fila = 0; fila < 8; fila++) {
            for (int col = 0; col < 8; col++) {
                ubi_barcos[fila][col] = new JButton();    
                panelBarcos.add(ubi_barcos[fila][col]);
            }
        }

        for (int fila = 0; fila < 8; fila++) {
            for (int col = 0; col < 8; col++){
                final int columnas=col, filas=fila;
                ubi_barcos[filas][columnas].addActionListener(e ->{
                    if (battle.getBarcosDisp()>0){
                        battle.colocarBarcos(filas, columnas);//guarda las posiciones de lo sbarcos
                        battle.turno();//cambia de turno
                        ubi_barcos[filas][columnas].setBackground(Color.red);//cambia de color el boton
                        JOptionPane.showMessageDialog(null, "Barco colocado en las coordenadas ("+filas+", "+columnas+")");
                    }else{
                        if (battle.getTurno()==2){ //si es el turno del jugador 2
                            JOptionPane.showMessageDialog(null, "¡Colocaste todos los barcos!");
                            panel_ColocarBarcos(jugar);// se resetea el tablero
                        }else{
                            JOptionPane.showMessageDialog(null, "¡Colocaste todos los barcos!");// fin de colocacion del PLayer 2
                        }
                    }

                });
            }
    }
    
    jugar.getContentPane().add(panelBarcos);
    jugar.revalidate();
    jugar.repaint();
        
    }
    
    public void panel_ColocarBombas(JFrame jugar){
        jugar.getContentPane().removeAll();
        jugar.setSize(900,750);

        tituloBattle= new JLabel("JUGAR BATTLESHIP");
        tituloBattle.setFont(fuente);
        tituloBattle.setBounds(20,20,500,40);
        jugar.add(tituloBattle);

        player_turno= new JLabel ("Turno: ");
        player_turno.setFont(fuente);
        player_turno.setBounds(20, 80, 300, 40);
        jugar.add(player_turno);

        contador_bombas= new JLabel ("Bombas Disponibles: ");
        contador_bombas.setFont(fuente);
        contador_bombas.setBounds(20, 130, 300, 40);
        jugar.add(contador_bombas);

        status= new JLabel ("Status:");
        status.setFont(fuente);
        status.setBounds(630,190,300,40);
        jugar.add(status);

        ingresar= new JButton ("Ingresar");
        ingresar.setFont(fuente);
        ingresar.setBounds(630, 550, 200, 40);
        jugar.add(ingresar);

        JPanel panelTablero = new JPanel(new GridLayout(8, 8));
        panelTablero.setBounds(20, 190, 550, 400);

        JButton [][] ubi_bombas = new JButton[8][8];
        for (int fila = 0; fila < 8; fila++) {
            for (int col = 0; col < 8; col++) {
                ubi_bombas[fila][col] = new JButton();
                ubi_bombas[fila][col].setFont(new Font("Arial", Font.BOLD, 16));
                panelTablero.add(ubi_bombas[fila][col]);
            }
        }

        jugar.getContentPane().add(panelTablero);
        jugar.revalidate();
        jugar.repaint();
        
    
    }
    
    
     
}
