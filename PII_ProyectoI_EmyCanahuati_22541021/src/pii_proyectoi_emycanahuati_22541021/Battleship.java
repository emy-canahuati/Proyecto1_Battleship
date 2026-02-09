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
import javax.swing.*;
import java.util.ArrayList;
public class Battleship {
    protected ArrayList <Player> jugadores= new ArrayList<>();
    protected boolean [][] bombas_Player1;
    protected Barco [][] barcos_Player1;
    protected boolean [][] bombas_Player2;
    protected Barco [][] barcos_Player2;
    protected String [][] tablero_Player1;
    protected String [][] tablero_Player2;
    protected String modo,  fin_partida, dificultad;
    protected int jugador1,jugador2, turno;
    protected int barcosDisponibles;
    
    public Battleship() {
        modo="TUTORIAL";
        dificultad="NORMAL";
        barcos_Player1= new Barco [8][8];
        barcos_Player2= new Barco [8][8];
        tablero_Player1= new String [8][8];
        tablero_Player2= new String [8][8];
        bombas_Player1 = new boolean[8][8];
        bombas_Player2 = new boolean[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tablero_Player1[i][j] = "";
                tablero_Player2[i][j] = "";
            }
        }
        turno=1;
        barcosDisponibles();
    }
    public void retirada(){
        
    }
    public int crearPlayers(String nombre_CrearPlayer, String contraseña){ //se crean los players, en el main se muestran diferentes mensajes dependiendo del numero que se retorne
        for (Player jugador: jugadores){
            if (jugador.getNombre().equals(nombre_CrearPlayer))
                return -1;
        }
        Player jugador= new Player(nombre_CrearPlayer, contraseña);
        jugadores.add(jugador);
        return 0;
    }
    
    public boolean Login(String nombre_login, String contraseña_login){
        return Login(nombre_login, contraseña_login, 0);
    }
    private boolean Login(String nombre_login, String contraseña_login, int indexJugador){ //valida que el usuario y contra sean correctas, en el main se haran diferentes acciones dependiendo de lo que retorne
        if(jugadores==null|| jugadores.isEmpty()){
            JOptionPane.showMessageDialog(null,"Error: No se han creado Players");
            return false;
        }
        if (indexJugador<jugadores.size()){
            if (jugadores.get(indexJugador).getNombre().equals(nombre_login) && jugadores.get(indexJugador).getContraseña().equals(contraseña_login)){
                this.jugador1=indexJugador;
                return true;
            }else{
                return Login(nombre_login, contraseña_login, indexJugador+1);
            }   
        }
        JOptionPane.showMessageDialog(null,"Error: Nombre de usuario y/o contraseña invalidos");
        return false;
    }
    
    public boolean setJugador2(String player2){
        if (jugadores.size()>1){
            for (int contador=0;contador<jugadores.size();contador++){
                if (player2.equals(jugadores.get(contador).getNombre())){
                    this.jugador2=contador;
                    return true;
                }
            }
            JOptionPane.showMessageDialog(null,"Error: Player no encontrado. Intentelo de nuevo");
            return true;
        }else{
            JOptionPane.showMessageDialog(null,"Error: Debe crear otro Player. Battleship se juego con 2 players.");
            return false;
        }
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
            case "EASY":
                barcosDisponibles=5;
                break;
            case "NORMAL":
                barcosDisponibles=4;
                break;
            case "EXPERT":
                barcosDisponibles=2;
                break;
            case "GENIUS":
                barcosDisponibles=1;
                break;
            default:
                break;
        }
    }
    
    public int getBarcosDisp(){
        return barcosDisponibles;
    }
    
    public boolean compararPosiciones(int contador1, int contador2){
        if(turno==1){
            if (barcos_Player2[contador1][contador2]!=null && barcos_Player2[contador1][contador2].isSeleccionado()){
                barcos_Player2[contador1][contador2].restarCant_bombas();
                if (barcos_Player2[contador1][contador2].getCant_bombas()==0)
                    throw new IllegalArgumentException("Se hundio el "+barcos_Player2[contador1][contador2].getNombre()+" de "+jugadores.get(1).getNombre());
                else
                    return true;
            }else
                return false;
        }else if (turno==2){
            if (barcos_Player1[contador1][contador2]!=null && barcos_Player1[contador1][contador2].isSeleccionado()){
                barcos_Player1[contador1][contador2].restarCant_bombas();
                if (barcos_Player1[contador1][contador2].getCant_bombas()==0)
                    throw new IllegalArgumentException("Se hundio el "+barcos_Player1[contador1][contador2].getNombre()+" de "+jugadores.get(0).getNombre());
                else
                    return true;
            }else
                return false;
        }
        return false;
    }
    
    public void setPosicionTableros(int contador1, int contador2){
        if (turno==1 && barcos_Player2[contador1][contador2]!=null){
            tablero_Player2[contador1][contador2]=barcos_Player2[contador1][contador2].getID();
        }else if (turno==2 && barcos_Player1[contador1][contador2]!=null){
            tablero_Player1[contador1][contador2]=barcos_Player1[contador1][contador2].getID();
        }
    }
    
    public void setDificultad(String dificultad){
        this.dificultad=dificultad;
        barcosDisponibles();
    }
    
    public void setModo(String modo){
        this.modo=modo;
    }
}
