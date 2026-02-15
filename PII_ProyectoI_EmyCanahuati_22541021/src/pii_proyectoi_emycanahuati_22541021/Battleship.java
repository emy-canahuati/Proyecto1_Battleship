/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pii_proyectoi_emycanahuati_22541021;

/**
 *
 * @author emyca
 */
import java.awt.Image;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Battleship{
    protected ArrayList <Player> jugadores= new ArrayList<>();
    protected boolean [][] bombas_Player1;
    protected Barco [][] barcos_Player1;
    protected boolean [][] bombas_Player2;
    protected Barco [][] barcos_Player2;
    protected int jugador1,jugador2, turno;
    protected int barcosDisponibles;
    protected ImageIcon iconoOG;
    protected int barcosVivosPlayer1;
    protected int barcosVivosPlayer2;
    protected Modo modo;
    protected Dificultad dificultad;
    
    enum Modo{
        TUTORIAL, ARCADE;
    }
    
    enum Dificultad{
        EASY, NORMAL, EXPERT, GENIUS;
    }
    
    public Battleship() {
        modo=Modo.TUTORIAL;
        dificultad= Dificultad.NORMAL;
        barcosVivosPlayer1=0;
        barcosVivosPlayer2=0;
        barcos_Player1= new Barco [8][8];
        barcos_Player2= new Barco [8][8];
        bombas_Player1 = new boolean[8][8];
        bombas_Player2 = new boolean[8][8];
        turno=1;
    }
    public void retirada(){
        barcos_Player1= new Barco [8][8];
        barcos_Player2= new Barco [8][8];
        bombas_Player1 = new boolean[8][8];
        bombas_Player2 = new boolean[8][8];
        if (turno==1){
            jugadores.get(jugador1).setUltimasPartidas(jugadores.get(jugador2).getNombre(), jugadores.get(jugador1).getNombre(), modo.name(), "retirada");
            jugadores.get(jugador2).setUltimasPartidas(jugadores.get(jugador2).getNombre(), jugadores.get(jugador1).getNombre(), modo.name(), "retirada");
            jugadores.get(jugador2).setPuntos(3);
            throw new IllegalArgumentException (jugadores.get(jugador1).getNombre()+" se ha retirado !El ganador es "+jugadores.get(jugador2).getNombre()+"!");
        }else if (turno==2){
            jugadores.get(jugador1).setUltimasPartidas(jugadores.get(jugador1).getNombre(), jugadores.get(jugador2).getNombre(), modo.name(), "retirada");
            jugadores.get(jugador2).setUltimasPartidas(jugadores.get(jugador1).getNombre(), jugadores.get(jugador2).getNombre(), modo.name(), "retirada");
            jugadores.get(jugador1).setPuntos(3);
            throw new IllegalArgumentException (jugadores.get(jugador2).getNombre()+" se ha retirado !El ganador es "+jugadores.get(jugador1).getNombre()+"!");
        }
    }
    public boolean crearPlayers(String nombre_CrearPlayer, String contraseña){ //se crean los players, en el main se muestran diferentes mensajes dependiendo del numero que se retorne
        if (jugadores!=null || !jugadores.isEmpty()){
            for (Player jugador: jugadores){
                if (jugador!=null){
                    if (jugador.getNombre().equals(nombre_CrearPlayer))
                        return false; 
                }   
            }
        }
        Player jugador= new Player(nombre_CrearPlayer, contraseña);
        jugadores.add(jugador);
        return true;
    }
    
    public boolean Login(String nombre_login, String contraseña_login){
        return Login(nombre_login, contraseña_login, 0);
    }
    private boolean Login(String nombre_login, String contraseña_login, int indexJugador){ //valida que el usuario y contra sean correctas, en el main se haran diferentes acciones dependiendo de lo que retorne
        if(jugadores==null || jugadores.isEmpty()){//revisa que se hayan creado players
            throw new IllegalArgumentException("Error: No se han creado Players");
        }
        if (indexJugador<jugadores.size()){//revisa que los datos que se ingresaron sean de algun player
            if (jugadores.get(indexJugador)==null)//si el espacio esta vacio que se lo salte
                return Login(nombre_login, contraseña_login, indexJugador+1);
            if (jugadores.get(indexJugador).getNombre().equals(nombre_login) && jugadores.get(indexJugador).getContraseña().equals(contraseña_login)){//si existe un jugador lo deja hacer login
                this.jugador1=indexJugador;
                return true;
            }else{
                return Login(nombre_login, contraseña_login, indexJugador+1);//sino se vuelve a repetir el ciclo
            }   
        }else{
            throw new IllegalArgumentException("Error: Nombre de usuario y/o contraseña invalidos");
        }
    }
    
    public boolean setJugador2(String player2){
        if (jugadores.size()>1){
            for (int contador=0;contador<jugadores.size();contador++){
                if (jugadores.get(contador)!=null){
                    if (player2.equals(jugadores.get(contador).getNombre())){
                    this.jugador2=contador;
                    return true;
                    }
                }
            }
            return false;
        }else{
            throw new IllegalArgumentException("Error: Debe crear otro Player. Battleship se juego con 2 players.");
        }
    }
    
    public String getJugadorNom(){
        return (turno==1)? jugadores.get(jugador1).getNombre():jugadores.get(jugador2).getNombre();
    }
    
    public void colocarBarcos(int contador1, int contador2, String ID){
        if(turno==1){
            if (barcos_Player1[contador1][contador2]!=null && barcos_Player1[contador1][contador2].isSeleccionado()){
                throw new IllegalArgumentException ("No puede colocar dos barcos en la misma posición.");
            }
            else if (barcos_Player1[contador1][contador2]==null){
                barcos_Player1[contador1][contador2]= new Barco(ID);
                barcos_Player1[contador1][contador2].setSeleccionado(true);
                barcosDisponibles-=1;
            }
        }else if (turno==2){
            if (barcos_Player2[contador1][contador2]!=null && barcos_Player2[contador1][contador2].isSeleccionado()){
                throw new IllegalArgumentException ("No puede colocar dos barcos en la misma posición.");
            }
            else if (barcos_Player2[contador1][contador2]==null){
                barcos_Player2[contador1][contador2]= new Barco(ID);
                barcos_Player2[contador1][contador2].setSeleccionado(true);
                barcosDisponibles-=1;
                
            }
        }
    }
       
    public void colocarBombas(int contador1, int contador2){
        if(turno==1){
            bombas_Player1[contador1][contador2]=true;
        }else if (turno==2){
            bombas_Player2[contador1][contador2]=true;
        }
    }
    public void turno(){
        if (turno==1){
            turno=2;
        }else{
            turno=1;
        }
    }
    
    public int getTurno(){
        return turno;
    }
    
    public void barcosDisponibles(){
        switch (dificultad) {
            case EASY:
                barcosDisponibles=5;
                barcosVivosPlayer1=5;
                barcosVivosPlayer2=5;
                break;
            case NORMAL:
                barcosDisponibles=4;
                barcosVivosPlayer1=4;
                barcosVivosPlayer2=4;
                break;
            case EXPERT:
                barcosDisponibles=2;
                barcosVivosPlayer1=2;
                barcosVivosPlayer2=2;
                break;
            case GENIUS:
                barcosDisponibles=1;
                barcosVivosPlayer1=1;
                barcosVivosPlayer2=1;
                break;
            default:
                break;
        }
    }
    
    public int getBarcosDisp(){
        return barcosDisponibles;
    }
    
    public String getGanador(){
        String mensaje="";
        if (barcosVivosPlayer2==0){
            jugadores.get(jugador1).setUltimasPartidas(jugadores.get(jugador1).getNombre(), jugadores.get(jugador2).getNombre(), modo.name(), "hundimiento");
            jugadores.get(jugador2).setUltimasPartidas(jugadores.get(jugador1).getNombre(), jugadores.get(jugador2).getNombre(), modo.name(), "hundimiento");
            mensaje="¡El ganador es "+jugadores.get(jugador1).getNombre()+"!";
            jugadores.get(jugador1).setPuntos(3);
        }else if(barcosVivosPlayer1==0){
            jugadores.get(jugador1).setUltimasPartidas(jugadores.get(jugador2).getNombre(), jugadores.get(jugador1).getNombre(), modo.name(), "hundimiento");
            jugadores.get(jugador2).setUltimasPartidas(jugadores.get(jugador2).getNombre(), jugadores.get(jugador1).getNombre(), modo.name(), "hundimiento");
            mensaje="¡El ganador es "+jugadores.get(jugador2).getNombre()+"!";
            jugadores.get(jugador2).setPuntos(3);
        }
        return mensaje;
    }
    
    public boolean compararPosiciones(int fila, int columna){
        if(turno==1){
            if (barcos_Player2[fila][columna]!=null && barcos_Player2[fila][columna].isSeleccionado()){
                barcos_Player2[fila][columna].restarCant_bombas();
                if (barcos_Player2[fila][columna].getCant_bombas()==0){// si el barco ya no tiene vidas
                    if (barcosVivosPlayer2!=0){//si el player 2 aun tiene barcos vivos
                        barcosVivosPlayer2--;//se le resta un barco
                        barcos_Player2[fila][columna]=null;//se quita el barco de la posicion en el tablero
                        throw new IllegalArgumentException("Se hundio el "+barcos_Player2[fila][columna].getNombre()+" de "+jugadores.get(jugador2).getNombre());
                    }
                }else{
                    return true;
                }
            }else
                return false;
        }else if (turno==2){
            if (barcos_Player1[fila][columna]!=null && barcos_Player1[fila][columna].isSeleccionado()){
                barcos_Player1[fila][columna].restarCant_bombas();
                if (barcos_Player1[fila][columna].getCant_bombas()==0){
                    if (barcosVivosPlayer1!=0){
                        barcosVivosPlayer1--;
                        barcos_Player1[fila][columna]=null;
                        throw new IllegalArgumentException("Se hundio el "+barcos_Player1[fila][columna].getNombre()+" de "+jugadores.get(jugador1).getNombre());
                    }
                }else
                    return true;
            }else
                return false;
        }
        return false;
    }
    

    public void resetearTablero(){
        if (turno==1){
            List <Barco> lista= new ArrayList<>(); //el arreglo de Barcos de guarda en una lista
            for (Barco[] fila:barcos_Player2){//por cada fila en el arreglo
                for (Barco barco: fila){//por cada barco en una fila
                    lista.add(barco);
                }
            }
            
            Collections.shuffle(lista);//shuffle de la lista
            
            int index=0;
            for (int contador1=0;contador1<8;contador1++){
                for(int contador2=0; contador2<8;contador2++){
                    barcos_Player2[contador1][contador2]=lista.get(index++);
                }
            }  
        }
        if(turno==2){
            List <Barco> lista= new ArrayList<>(); //el arreglo de Barcos de guarda en una lista
            for (Barco[] fila:barcos_Player1){
                for (Barco barco: fila){
                    lista.add(barco);
                }
            }
            
            Collections.shuffle(lista);//shuffle de la lista
            
            int index=0;
            for (int contador1=0;contador1<8;contador1++){
                for(int contador2=0; contador2<8;contador2++){//los datos reordenados de la lista se ingresan al arreglo
                    barcos_Player1[contador1][contador2]=lista.get(index++);
                }
            }
        }
    }
    
    public void setDificultad(String dificultad){
        this.dificultad=Dificultad.valueOf(dificultad);
        barcosDisponibles();
    }
    
    public void setModo(String modo){
        this.modo=Modo.valueOf(modo);
    }
    
    public ImageIcon getIconBarcos(String ID){
        switch (ID) {
            case "PA":
                iconoOG=new ImageIcon("src/Imagenes/portaaviones.png");
                break;
            case "AZ":
                iconoOG=new ImageIcon("src/Imagenes/acorazado.png");
                break;
            case "SM":
                iconoOG=new ImageIcon("src/Imagenes/submarino.png");
                break;
            case "DT":
                iconoOG=new ImageIcon("src/Imagenes/destructor.png");
                break;
            default:
                break;
        }
        Image imagenRedimensionada = iconoOG.getImage().getScaledInstance(105, 75, Image.SCALE_SMOOTH);
        ImageIcon icono = new ImageIcon(imagenRedimensionada);
        return icono;
    }
    
    public ImageIcon TableroBarcos(int fila, int col){
        if (modo.name().equals("TUTORIAL")) {
            if (turno == 1 && barcos_Player2[fila][col] != null) {
                iconoOG = barcos_Player2[fila][col].getIcon();
            } else if (turno == 2 && barcos_Player1[fila][col] != null) {
                iconoOG = barcos_Player1[fila][col].getIcon();
            } else {
                iconoOG = new ImageIcon("src/Imagenes/agua.png");
            }
        } else {
            iconoOG = new ImageIcon("src/Imagenes/agua.png");
        }
        return iconoOG;
    }
    
    public String status(){
        return (turno==1)? jugadores.get(jugador2).getNombre()+" tiene "+barcosVivosPlayer2+" naves":jugadores.get(jugador1).getNombre()+" tiene "+barcosVivosPlayer1+" naves";
    }
    
    public String getJugadorLogged(){
        return jugadores.get(jugador1).getNombre();
    }
    
    public String getJugadorLoggedContra(){
        return jugadores.get(jugador1).getContraseña();
    }
    
    public void modificarDatos(String nombre, String contraseña, int indicador /*si es 1, solo nombre se cambia, si es 2 solo contra y si es 3 ambas*/){
        switch (indicador){
            case 1:
                jugadores.get(jugador1).setContraseña(contraseña);
                break;
            case 2:
                jugadores.get(jugador1).setNombre(nombre);
                break;
            case 3:
                jugadores.get(jugador1).setNombre(nombre);
                jugadores.get(jugador1).setContraseña(contraseña);
                break;
            default:
                break;
        }
    }
    
    public void eliminarCuenta(){
        jugadores.set(jugador1, null);
    }
    
    public String[] getUltimasPartidas(){
        return jugadores.get(jugador1).getUltimasPartidas();
    }
    
    public ArrayList<Player> getRanking(){
        ArrayList<Player> ranking= new ArrayList <>(jugadores);
        Player guardarDato;
        for (int contador1=0;contador1<(ranking.size()-1);contador1++){
            for (int contador2=0;contador2<(ranking.size()-1);contador2++){
                if(ranking.get(contador2)!=null){
                    if (ranking.get(contador2).getPuntos()<ranking.get(contador2+1).getPuntos()){
                        guardarDato=ranking.get(contador2);
                        ranking.set(contador2, ranking.get(contador2+1));
                        ranking.set(contador2+1,guardarDato);
                    }
                }
                
            }
        }
       return ranking; 
    }
    
    
}
