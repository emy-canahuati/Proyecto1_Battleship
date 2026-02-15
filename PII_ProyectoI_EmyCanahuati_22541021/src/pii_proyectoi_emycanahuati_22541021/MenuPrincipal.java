/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pii_proyectoi_emycanahuati_22541021;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class MenuPrincipal extends JFrame {

    protected Battleship battle;
    protected Font fuente = new Font("Arial", Font.PLAIN, 30);
    protected Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    //componentes Menu Principal
    protected JLabel menuPrincipal;
    protected JButton jugarBattleship, configuracion, reportes, miPerfil, cerrarSesion;

    //componentes Ventana Jugar Battleship
    protected JLabel nombre, tituloBattle, player_turno, contador_bombas, contador_barcos, status;
    protected JTextField nom_Player2;
    protected JButton exit, ingresar, retirada, iniciar;
    protected String ID;
    protected ImageIcon iconoOG, icono;
    protected Image imagenRedimensionada;

    //componentes Ventana Configuración
    protected JLabel tituloConfi, dificultad, modo;
    protected JPopupMenu menuDifi, menuModo;
    protected JMenuItem easy, normal, expert, genius, tutorial, arcade;
    protected JButton regresar, btnModo, btnDifi;

    //componentes Ventana Perfil
    protected JLabel tituloPerfil;
    protected JButton datos, modificar_datos, eliminar_cuenta, regresar_perfil;

    //componentes JDialog Ver Datos (Perfil)
    protected JLabel tituloUser, nombreUser, tituloPass, passUser;
    protected JButton btnOkDatos;

    // componentes JDialog Modificar Datos (Perfil)
    protected JLabel tituloNuevoNom, tituloNuevaContra;
    protected JTextField nuevoNombre;
    protected JPasswordField nuevaContra;
    protected JButton btnModificar;

    //componentes Ventana Reportes
    protected JLabel tituloReportes;
    protected JButton ranking, ultimos_juegos, regresarReportes;
    
    //componentes JDialog Ultimas Partidas (Reportes)
    protected JLabel tituloJuegos;
    protected JButton btnCerrarPartidas;
    
    //componentes JDialog Ranking (Reportes)
    protected JLabel tituloRanking;
    protected JLabel[] lblJugadoresRanking;
    protected JButton btnCerrarRanking;

    // --- Componentes Panel Colocar Barcos ---
    protected JPanel panelCuadricula;
    protected JButton[][] ubi_barcos;

    // --- Componentes Panel Colocar Bombas ---
    protected JPanel panelTableroBombas;
    protected JButton[][] ubi_bombas;
    protected JLabel lblEstatusTitulo, lblLeyenda;
    protected JButton btnRetiradaAccion;
    
    protected JFrame FramePrincipal=this;

    public MenuPrincipal(Battleship battle) {
        this.battle = battle;
        // Configuramos el JFrame base como pantalla completa
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        menuPrincipal();
    }

    //metodo para crear el fondo con el cuadro blanco central
    private JPanel crearContenedorConFondo(int anchoCuadro, int altoCuadro) {
        JPanel panelFondo = new JPanel(null);
        panelFondo.setBounds(0, 0, screenSize.width, screenSize.height);

        //cuadro blanco del centro
        JPanel cuadroBlanco = new JPanel(null);
        cuadroBlanco.setBackground(Color.WHITE);
        cuadroBlanco.setBounds((screenSize.width - anchoCuadro) / 2, (screenSize.height - altoCuadro) / 2, anchoCuadro, altoCuadro);
        
        // imagen de Fondo
        JLabel fondo = new JLabel();
        ImageIcon imgFondo = new ImageIcon("src/Imagenes/wallpaper.jpg");
        Image imgSized = imgFondo.getImage().getScaledInstance(screenSize.width, screenSize.height, Image.SCALE_SMOOTH);
        fondo.setIcon(new ImageIcon(imgSized));
        fondo.setBounds(0, 0, screenSize.width, screenSize.height);

        panelFondo.add(cuadroBlanco);
        panelFondo.add(fondo); // El fondo se agrega después para que quede atrás
        
        return panelFondo; // Retornamos el panel que contiene al cuadro blanco
    }

    public void menuPrincipal() {
        getContentPane().removeAll();
        JPanel contenedor = crearContenedorConFondo(600, 550);
        JPanel cuadro = (JPanel) contenedor.getComponent(0);

        menuPrincipal = new JLabel("MENU PRINCIPAL", SwingConstants.CENTER);
        menuPrincipal.setFont(new Font("Arial", Font.BOLD, 35));
        menuPrincipal.setBounds(0, 30, 600, 50);
        cuadro.add(menuPrincipal);

        jugarBattleship= new JButton("Jugar Battleship");
        jugarBattleship.setFont(fuente);
        jugarBattleship.setBounds(40, 110, 500, 50);
        cuadro.add(jugarBattleship);
        
        configuracion = new JButton ("Configuración");
        configuracion.setFont(fuente);
        configuracion.setBounds(40, 190, 500, 50);
        cuadro.add(configuracion);
        
        reportes= new JButton ("Reportes");
        reportes.setFont(fuente);
        reportes.setBounds(40, 270, 500, 50);
        cuadro.add(reportes);

        miPerfil= new JButton("Mi Perfil");
        miPerfil.setFont(fuente);
        miPerfil.setBounds(40, 350, 500, 50);
        cuadro.add(miPerfil);
        
        cerrarSesion= new JButton("Cerrar Sesion");
        cerrarSesion.setFont(fuente);
        cerrarSesion.setBounds(40, 430, 500, 50);
        cuadro.add(cerrarSesion);

        jugarBattleship.addActionListener(e -> ventana_jugarBattleship());
        configuracion.addActionListener(e -> ventana_configuracion());
        miPerfil.addActionListener(e -> ventana_MiPerfil());
        reportes.addActionListener(e -> ventana_Reportes());
        cerrarSesion.addActionListener(ev-> {
            JOptionPane.showMessageDialog(null, "Sesion cerrada");
            FramePrincipal.dispose();
        });

        add(contenedor);
        setVisible(true);
        revalidate();
        repaint();
    }

    public void ventana_configuracion() {
        getContentPane().removeAll();
        JPanel contenedor = crearContenedorConFondo(620, 400);
        JPanel cuadro = (JPanel) contenedor.getComponent(0);

        tituloConfi = new JLabel("CONFIGURACIÓN", SwingConstants.CENTER);
        tituloConfi.setFont(new Font("Arial", Font.BOLD, 35));
        tituloConfi.setBounds(0, 30, 620, 40);
        cuadro.add(tituloConfi);

        dificultad = new JLabel("Dificultad:");
        dificultad.setBounds(50, 100, 300, 40);
        dificultad.setFont(fuente);
        cuadro.add(dificultad);

        btnDifi = new JButton("Seleccionar");
        btnDifi.setFont(fuente);
        btnDifi.setBounds(50, 150, 200, 40);
        cuadro.add(btnDifi);

        menuDifi= new JPopupMenu();
        easy = new JMenuItem("EASY");
        easy.setFont(fuente);
        easy.addActionListener(e -> battle.setDificultad("EASY"));
        normal = new JMenuItem("NORMAL");
        normal.setFont(fuente);
        normal.addActionListener(e -> battle.setDificultad("NORMAL"));
        expert= new JMenuItem("EXPERT");
        expert.setFont(fuente);
        expert.addActionListener(e -> battle.setDificultad("EXPERT"));
        genius= new JMenuItem("GENIUS");
        genius.setFont(fuente);
        genius.addActionListener(e -> battle.setDificultad("GENIUS"));
        menuDifi.add(easy);
        menuDifi.addSeparator();
        menuDifi.add(normal);
        menuDifi.addSeparator();
        menuDifi.add(expert);
        menuDifi.addSeparator();
        menuDifi.add(genius);
        cuadro.add(menuDifi);
                
        modo= new JLabel("Modo de Juego");
        modo.setFont(fuente);
        modo.setBounds(290,100,300,40);
        cuadro.add(modo);
        
        btnModo= new JButton("Seleccionar");
        btnModo.setFont(fuente);
        btnModo.setBounds(290,150,300,40);
        cuadro.add(btnModo);
       
        menuModo= new JPopupMenu();
        tutorial = new JMenuItem("TUTORIAL");
        tutorial.setFont(fuente);
        tutorial.addActionListener(e -> battle.setModo("TUTORIAL"));
        arcade = new JMenuItem("ARCADE");
        arcade.setFont(fuente);
        arcade.addActionListener(e -> battle.setModo("ARCADE"));
        
        menuModo.add(arcade);
        menuModo.addSeparator();
        menuModo.add(tutorial);
        cuadro.add(menuModo);
        
        btnModo.addActionListener(e -> {
            //muestra el menu debajo del botón
            menuModo.show(btnModo, 0, btnModo.getHeight());
        });
        
        btnDifi.addActionListener(e -> {
            //muestra el menu debajo del botón
            menuDifi.show(btnDifi, 0, btnDifi.getHeight());
        });
        
        regresar = new JButton("Regresar");
        regresar.setBounds(50, 300, 200, 40);
        regresar.setFont(fuente);
        regresar.addActionListener(e -> menuPrincipal());
        cuadro.add(regresar);

        add(contenedor);
        setVisible(true);
        revalidate();
        repaint();
    }

    public void ventana_jugarBattleship() {
        getContentPane().removeAll();
        JPanel contenedor = crearContenedorConFondo(600, 400);
        JPanel cuadro = (JPanel) contenedor.getComponent(0);

        tituloBattle = new JLabel("JUGAR BATTLESHIP", SwingConstants.CENTER);
        tituloBattle.setFont(new Font("Arial", Font.BOLD, 35));
        tituloBattle.setBounds(0, 30, 600, 40);
        cuadro.add(tituloBattle);

        nombre = new JLabel("Username del Player 2:");
        nombre.setBounds(50, 100, 500, 40);
        nombre.setFont(fuente);
        cuadro.add(nombre);

        nom_Player2 = new JTextField();
        nom_Player2.setFont(fuente);
        nom_Player2.setBounds(50, 150, 500, 40);
        cuadro.add(nom_Player2);

        ingresar = new JButton("Ingresar");
        ingresar.setFont(fuente);
        ingresar.setBounds(50, 250, 220, 40);
        ingresar.addActionListener(ev -> {
            try{
                if (battle.setJugador2(nom_Player2.getText())) {
                    JOptionPane.showMessageDialog(null, "Player 2 Ingresado");
                    panel_ColocarBarcos();
                }else
                    JOptionPane.showMessageDialog(null,"Error: Player no encontrado. Intentelo de nuevo");
            }catch(Exception excepcion){
                    JOptionPane.showMessageDialog(null,excepcion.getMessage());
                    menuPrincipal();
            }
        });
        cuadro.add(ingresar);

        exit = new JButton("Regresar");
        exit.setFont(fuente);
        exit.setBounds(330, 250, 220, 40);
        exit.addActionListener(e -> menuPrincipal());
        cuadro.add(exit);

        add(contenedor);
        revalidate();
        repaint();
    }

    public void ventana_MiPerfil() {
        getContentPane().removeAll();
        JPanel contenedor = crearContenedorConFondo(500, 500);
        JPanel cuadro = (JPanel) contenedor.getComponent(0);

        tituloPerfil = new JLabel("MI PERFIL", SwingConstants.CENTER);
        tituloPerfil.setFont(new Font("Arial", Font.BOLD, 35));
        tituloPerfil.setBounds(0, 30, 500, 40);
        cuadro.add(tituloPerfil);

        datos = new JButton("Ver mis Datos");
        datos.setBounds(40, 90, 400, 40);
        datos.setFont(fuente);
        cuadro.add(datos);
        
        modificar_datos = new JButton("Modificar mis Datos");
        modificar_datos.setFont(fuente);
        modificar_datos.setBounds(40,160, 400, 40);
        cuadro.add(modificar_datos);
        
        eliminar_cuenta = new JButton("Eliminar Cuenta");
        eliminar_cuenta.setFont(fuente);
        eliminar_cuenta.setBounds(40, 230, 400, 40);
        cuadro.add(eliminar_cuenta);
        
        regresar_perfil = new JButton("Regresar");
        regresar_perfil.setFont(fuente);
        regresar_perfil.setBounds(40, 300,400, 40);
        cuadro.add(regresar_perfil);

        datos.addActionListener(e -> {
            JDialog info = new JDialog(this, "Datos", true);
            info.setSize(400, 300);
            info.setLayout(null);
            info.setLocationRelativeTo(null);

            tituloUser = new JLabel("Usuario:");
            tituloUser.setBounds(20, 20, 200, 30);
            tituloUser.setFont(fuente);
            nombreUser = new JLabel(battle.getJugadorLogged());
            nombreUser.setBounds(20, 50, 200, 30);
            nombreUser.setFont(fuente);

            tituloPass = new JLabel("Password:");
            tituloPass.setBounds(20, 100, 200, 30);
            tituloPass.setFont(fuente);
            passUser = new JLabel(battle.getJugadorLoggedContra());
            passUser.setBounds(20, 130, 200, 30);
            passUser.setFont(fuente);

            btnOkDatos = new JButton("OK");
            btnOkDatos.setBounds(150, 200, 80, 30);
            btnOkDatos.setFont(fuente);
            btnOkDatos.addActionListener(a -> info.dispose());

            info.add(tituloUser); info.add(nombreUser);
            info.add(tituloPass); info.add(passUser);
            info.add(btnOkDatos);
            info.setVisible(true);
        });

         modificar_datos.addActionListener(ev -> {
            JDialog modificar = new JDialog();
            modificar.setSize(500,300);
            modificar.setLayout(null);
            modificar.setLocationRelativeTo(null);
            
            modificar.add(new JLabel ("MODIFICAR DATOS"));
            tituloNuevoNom= new JLabel ("Nuevo Nombre de Usuario");
            tituloNuevoNom.setBounds(20, 20, 400, 30);
            tituloNuevoNom.setFont(fuente);
            modificar.add(tituloNuevoNom);
            
            nuevoNombre= new JTextField();
            nuevoNombre.setFont(fuente);
            nuevoNombre.setBounds(20, 50, 400, 30);
            modificar.add(nuevoNombre);
           
            tituloNuevaContra= new JLabel ("Nueva Contraseña");
            tituloNuevaContra.setBounds(20, 100, 400, 30);
            tituloNuevaContra.setFont(fuente);
            modificar.add(tituloNuevaContra);
            
            nuevaContra = new JPasswordField();
            nuevaContra.setFont(fuente);
            nuevaContra.setBounds(20, 130, 400, 30);
            modificar.add(nuevaContra);
            
            btnModificar = new JButton ("Modificar");
            btnModificar.setFont(fuente);
            btnModificar.setBounds(150, 200, 200, 30);
            btnModificar.addActionListener(evento -> {
                int indicador;
                if (nuevoNombre.getText().trim().isEmpty() && !nuevaContra.getText().trim().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Contraseña modificada");
                    indicador=1;
                }else if(!nuevoNombre.getText().trim().isEmpty() && nuevaContra.getText().trim().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Nombre modificado");
                    indicador=2;
                }else if(nuevoNombre.getText().trim().isEmpty() && nuevaContra.getText().trim().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Error: No puede dejar ambos espacios vacios");
                    indicador=4;
                }
                else{
                    JOptionPane.showMessageDialog(null, "Datos modificados");
                    indicador=3;
                }
                battle.modificarDatos(nuevoNombre.getText(), nuevaContra.getText(), indicador);
            });
            modificar.add(btnModificar);
            modificar.setVisible(true);
        });
        
        eliminar_cuenta.addActionListener(evento -> {
            battle.eliminarCuenta();
            JOptionPane.showMessageDialog(null, "Cuenta eliminada");
            FramePrincipal.dispose();
        });
         
        regresar_perfil.addActionListener(e -> menuPrincipal());

        add(contenedor);
        revalidate();
        repaint();
    }
    
    public void ventana_Reportes() {
    getContentPane().removeAll();
    JPanel contenedor = crearContenedorConFondo(600, 450);
    JPanel cuadro = (JPanel) contenedor.getComponent(0);

    tituloReportes = new JLabel("REPORTES", SwingConstants.CENTER);
    tituloReportes.setFont(new Font("Arial", Font.BOLD, 35));
    tituloReportes.setBounds(0, 30, 600, 50);
    cuadro.add(tituloReportes);

    ultimos_juegos = new JButton("Mis Últimos 10 Juegos");
    ultimos_juegos.setFont(fuente);
    ultimos_juegos.setBounds(50, 120, 500, 50);
    ultimos_juegos.addActionListener(ev -> {
        JDialog juegos = new JDialog(this, "Ultimas Partidas", true);
        juegos.setSize(400, 500);
        juegos.setLayout(null);
        juegos.setLocationRelativeTo(null);

        tituloJuegos = new JLabel("ULTIMAS PARTIDAS", SwingConstants.CENTER);
        tituloJuegos.setFont(new Font("Arial", Font.BOLD, 20));
        tituloJuegos.setBounds(0, 20, 400, 30);
        juegos.add(tituloJuegos);

        String[] listaPartidas = battle.getUltimasPartidas();
        if (listaPartidas==null){
            JLabel etiListaVacia =new JLabel("Aun no ha jugado una partida.");
            juegos.add(etiListaVacia);
        }else{
            int posiciony=70;
            int contadorPosicion=1;
            for(int indexJuegos=9; indexJuegos>=0;indexJuegos--){
                if (listaPartidas[indexJuegos]!=null){
                    String texto = (contadorPosicion) + ". " + listaPartidas[indexJuegos];
                    JLabel etiPartida = new JLabel(texto);
                    etiPartida.setBounds(50, posiciony, 300, 25);
                    juegos.add(etiPartida);
                    posiciony += 30;
                }else
                    break;
            }
        }
        

        btnCerrarPartidas = new JButton("Cerrar");
        btnCerrarPartidas.setBounds(150, 400, 100, 30);
        btnCerrarPartidas.addActionListener(e -> juegos.dispose());
        juegos.add(btnCerrarPartidas);

        juegos.setVisible(true);
    });
    cuadro.add(ultimos_juegos);

    ranking = new JButton("Ranking de Jugadores");
    ranking.setFont(fuente);
    ranking.setBounds(50, 200, 500, 50);
    ranking.addActionListener(ev -> {
        JDialog ranking_jugadores = new JDialog(this, "Ranking", true);
        ranking_jugadores.setSize(400, 500);
        ranking_jugadores.setLayout(null);
        ranking_jugadores.setLocationRelativeTo(null);

        tituloRanking = new JLabel("TOP PLAYERS", SwingConstants.CENTER);
        tituloRanking.setFont(new Font("Arial", Font.BOLD, 20));
        tituloRanking.setBounds(0, 20, 400, 30);
        ranking_jugadores.add(tituloRanking);

        ArrayList<Player> listaRanking = battle.getRanking();
        
        int indexranking=0;
        int posiciony=70;
        for(Player jugador: listaRanking){
            if (jugador!=null){
                String texto = (indexranking + 1) + ". " + jugador.getNombre()+ " - Puntos: " + jugador.getPuntos();
                JLabel etiJugador = new JLabel(texto);
                etiJugador.setBounds(50, posiciony, 300, 25);
                etiJugador.setFont(fuente);
                ranking_jugadores.add(etiJugador);
                posiciony += 30;
            }
            
        }

        btnCerrarRanking = new JButton("Cerrar");
        btnCerrarRanking.setBounds(150, 400, 100, 30);
        btnCerrarRanking.addActionListener(e -> ranking_jugadores.dispose());
        ranking_jugadores.add(btnCerrarRanking);

        ranking_jugadores.setVisible(true);
    });
    cuadro.add(ranking);

    regresarReportes = new JButton("Regresar al Menú");
    regresarReportes.setFont(fuente);
    regresarReportes.setBounds(50, 320, 500, 50);
    regresarReportes.addActionListener(e -> menuPrincipal());
    cuadro.add(regresarReportes);

    add(contenedor);
    revalidate();
    repaint();
}
    
    public void panel_ColocarBarcos() {
    getContentPane().removeAll();
    JPanel contenedor = crearContenedorConFondo(1100, 850);
    JPanel cuadro = (JPanel) contenedor.getComponent(0);
    battle.barcosDisponibles(); // Setea contador inicial

    tituloBattle = new JLabel("CONFIGURACIÓN DE FLOTA", SwingConstants.CENTER);
    tituloBattle.setFont(new Font("Arial", Font.BOLD, 30));
    tituloBattle.setBounds(0, 20, 1100, 40);
    cuadro.add(tituloBattle);

    player_turno = new JLabel("Turno de: " + battle.getJugadorNom());
    player_turno.setFont(fuente);
    player_turno.setBounds(50, 80, 400, 40);
    cuadro.add(player_turno);

    contador_barcos = new JLabel("Barcos Disponibles: " + battle.getBarcosDisp());
    contador_barcos.setFont(fuente);
    contador_barcos.setForeground(new Color(0, 102, 204));
    contador_barcos.setBounds(50, 130, 400, 40);
    cuadro.add(contador_barcos);

    iniciar = new JButton("Elegir Barco");
    iniciar.setFont(fuente);
    iniciar.setBounds(750, 100, 250, 50);
    cuadro.add(iniciar);

    // Panel de la cuadrícula
    panelCuadricula = new JPanel(new GridLayout(8, 8, 2, 2));
    panelCuadricula.setBackground(Color.LIGHT_GRAY);
    panelCuadricula.setBounds(50, 190, 1000, 600);

    ubi_barcos = new JButton[8][8];
    for (int fila = 0; fila < 8; fila++) {
        for (int colum = 0; colum < 8; colum++) {
            ubi_barcos[fila][colum] = new JButton();
            iconoOG = new ImageIcon("src/Imagenes/agua.png");
            imagenRedimensionada = iconoOG.getImage().getScaledInstance(120, 70, Image.SCALE_SMOOTH);
            ubi_barcos[fila][colum].setIcon(new ImageIcon(imagenRedimensionada));
            ubi_barcos[fila][colum].setBackground(new Color(200, 230, 255));
            
            final int fil = fila;
            final int col = colum;
            
            ubi_barcos[fila][colum].addActionListener(e -> {
                if (ID == null) {
                    JOptionPane.showMessageDialog(null, "Primero haz clic en 'Elegir Barco'");
                    return;
                }
                colocacion(fil, col);
            });
            panelCuadricula.add(ubi_barcos[fila][colum]);
        }
    }
    cuadro.add(panelCuadricula);

    iniciar.addActionListener(ev -> {
        pedirIDBarco();
    });

    add(contenedor);
    revalidate();
    repaint();
}

//metodo  para pedir el ID del barco
private void pedirIDBarco() {
    boolean validacion = true;
    do {
        String idIngresado = JOptionPane.showInputDialog("PA = Portaaviones (5)\nAZ = Acorazado (4)\nSM = Submarino (3)\nDT = Destructor (2)\n\nIngrese el Código:");
        if (idIngresado != null && (idIngresado.equals("PA") || idIngresado.equals("AZ") || idIngresado.equals("SM") || idIngresado.equals("DT"))) {
            ID = idIngresado;
            validacion=false;
        } else if (idIngresado != null) {
            JOptionPane.showMessageDialog(null, "Código inválido. Debe ingresar PA, AZ, SM o DT");
            validacion=true;
        }
    }while (validacion);
    
}

//metodo para colocar los barcos
private void colocacion(int fila, int colum) {//cada vez que se toque un boton pasara esto
    if (battle.getBarcosDisp() > 0) {
        try {
            battle.colocarBarcos(fila, colum, ID);//guarda las posiciones de los barcos
            ubi_barcos[fila][colum].setIcon(battle.getIconBarcos(ID));// cambia la imagen
            contador_barcos.setText("Barcos Disponibles: " + battle.getBarcosDisp());// actualiza el jlabel

            if (battle.getBarcosDisp() > 0) {//pregunta por el ID
                Timer timerID = new Timer(500, event2 -> {
                    pedirIDBarco();
                });
                timerID.setRepeats(false);
                timerID.start();
            } else {
                battle.turno();//cambia de turno
                if (battle.getTurno() == 2) {//jugador 1 acaba de terminar
                    JOptionPane.showMessageDialog(null, "¡Jugador 1 listo! Ahora Jugador 2.");
                    ID=null;
                    panel_ColocarBarcos();// se resetea el JPanel
                } else {
                    JOptionPane.showMessageDialog(null, "¡Todos los barcos colocados! Iniciando batalla");//fin de la colocacion de barcos
                    panel_ColocarBombas();
                }
            }
        } catch (Exception excepcion) {
            JOptionPane.showMessageDialog(null, excepcion.getMessage());
        }
    }
}   

    public void panel_ColocarBombas() {
    getContentPane().removeAll();
    // Cuadro amplio para la fase de batalla
    JPanel contenedor = crearContenedorConFondo(1150, 880);
    JPanel cuadro = (JPanel) contenedor.getComponent(0);

    // --- Títulos e Indicadores ---
    tituloBattle = new JLabel("BATTLESHIP - EN COMBATE", SwingConstants.CENTER);
    tituloBattle.setFont(new Font("Arial", Font.BOLD, 30));
    tituloBattle.setBounds(50, 20, 1150, 40);
    cuadro.add(tituloBattle);

    player_turno = new JLabel("TURNO: " + battle.getJugadorNom());
    player_turno.setFont(new Font("Arial", Font.PLAIN, 28));
    player_turno.setForeground(new Color(200, 0, 0)); // Rojo para resaltar acción
    player_turno.setBounds(50, 80, 500, 40);
    cuadro.add(player_turno);

    status = new JLabel("Estatus: " + battle.status());
    status.setFont(new Font("Arial", Font.PLAIN, 20));
    status.setBounds(50, 130, 700, 50);
    cuadro.add(status);

    retirada = new JButton("Rendirse");
    retirada.setFont(fuente);
    retirada.setBackground(new Color(255, 200, 200));
    retirada.setBounds(880, 80, 220, 50);
    retirada.addActionListener(ev -> {
        int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas retirarte?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try{
                battle.retirada();
            }catch(Exception excepcion){
                JOptionPane.showMessageDialog(null, excepcion.getMessage());
            }
            menuPrincipal();
        }
    });
    cuadro.add(retirada);

    //panel del tablero
    panelTableroBombas = new JPanel(new GridLayout(8, 8, 3, 3));
    panelTableroBombas.setBackground(Color.DARK_GRAY);
    panelTableroBombas.setBounds(50, 200, 1050, 620);

    ubi_bombas = new JButton[8][8];
    for (int fila = 0; fila < 8; fila++) {
        for (int colum = 0; colum < 8; colum++) {
            final int fil = fila;
            final int col = colum;
            
            ubi_bombas[fila][colum] = new JButton();
            // cargar imagen segun el estado actual del tablero del jugador
            imagenRedimensionada = battle.TableroBarcos(fila, col).getImage().getScaledInstance(120, 75, Image.SCALE_SMOOTH);
            ubi_bombas[fila][colum].setIcon(new ImageIcon(imagenRedimensionada));
            
            ubi_bombas[fila][colum].addActionListener(e -> bombardear(fil, col));
            panelTableroBombas.add(ubi_bombas[fila][colum]);
        }
    }
    cuadro.add(panelTableroBombas);

    add(contenedor);
    revalidate();
    repaint();
}

private void bombardear(int fila, int columna) {//cada vez que toque un boton pasa esto
    try {
        if (battle.compararPosiciones(fila, columna)) {//revisa si hay un barco en esta posicion
            // logica si le pega a un barco
            if (battle.getTurno() == 1) {
                JOptionPane.showMessageDialog(null, "¡DIRECTO! Has golpeado un " + battle.barcos_Player2[fila][columna].getNombre()+"\nVidas: "+battle.barcos_Player2[fila][columna].getCant_bombas());
                imagenRedimensionada = battle.barcos_Player2[fila][columna].getIconHits().getImage().getScaledInstance(120, 75, Image.SCALE_SMOOTH);
            } else {
                JOptionPane.showMessageDialog(null, "¡DIRECTO! Has golpeado un " + battle.barcos_Player1[fila][columna].getNombre()+"\nVidas: "+battle.barcos_Player1[fila][columna].getCant_bombas());
                imagenRedimensionada = battle.barcos_Player1[fila][columna].getIconHits().getImage().getScaledInstance(120, 75, Image.SCALE_SMOOTH);
            }
            battle.resetearTablero();
        } else {
            //logica si fallo
            iconoOG = new ImageIcon("src/Imagenes/tiro_fallido.png");
            imagenRedimensionada = iconoOG.getImage().getScaledInstance(120, 75, Image.SCALE_SMOOTH);
            JOptionPane.showMessageDialog(null, "Agua. Intenta de nuevo en el próximo turno");
        }

    } catch (Exception excepcion) {
        // Maneja el caso de barco hundido (Exception lanzada desde tu lógica de Battle)
        JOptionPane.showMessageDialog(null, "¡HUNDIDO! " + excepcion.getMessage());
        status.setText("Estatus: "+ battle.status());
    }
    //actualizar icono visualmente
        ubi_bombas[fila][columna].setIcon(new ImageIcon(imagenRedimensionada));
        
        // Bloquear tablero para evitar clics multiples
        for(JButton[] filas : ubi_bombas) for(JButton b : filas) b.setEnabled(false);

        // Timer para dar tiempo a ver el resultado antes de cambiar de turno
        Timer pausa = new Timer(1500, e -> {
            if (battle.barcosVivosPlayer1 == 0 || battle.barcosVivosPlayer2 == 0) {
                JOptionPane.showMessageDialog(null, "¡FIN DE LA PARTIDA!\n"+battle.getGanador());
                menuPrincipal();
            } else {
                battle.turno(); //cambia el turno
                panel_ColocarBombas(); // Refresca la pantalla completa
            }
        });
        pausa.setRepeats(false);
        pausa.start();
}


}