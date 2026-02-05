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
    protected boolean [][] barcos_Player1;
    protected boolean [][] bombas_Player2;
    protected boolean [][] barcos_Player2;
    protected String modo,  fin_partida, dificultad;
    protected int jugador1,jugador2, turno;
    protected int barcosDisponibles;
    
    public Battleship() {
        modo="TUTORIAL";
        dificultad="NORMAL";
        barcos_Player1= new boolean [8][8];
        barcos_Player2= new boolean [8][8];
        for (int contador1=0; contador1<8; contador1++){
            for (int contador2=0; contador2<8;contador2++){
                barcos_Player1[contador1][contador2]=false;
                barcos_Player2[contador1][contador2]=false;
            }
        }
        turno=1;
        
    }
    public void retirada(){
        
    }
    public int crearPlayers(String nombre_CrearPlayer, String contraseña){ //se crean los players, en el main se muestran diferentes mensajes dependiendo del numero que se retorne
        for (int contador=0;contador<jugadores.size();contador++){
            if (jugadores.get(contador).getNombre().equals(nombre_CrearPlayer))
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
    public void colocarBarcos(int contador1, int contador2){
        if(turno==1){
            this.barcos_Player1[contador1][contador2]=true;
            barcosDisponibles-=1;
        }else if (turno==2){
            this.barcos_Player2[contador1][contador2]=true;
            barcosDisponibles-=1;
        }
    }
    public void colocarBombas(int contador1, int contador2){
        if(turno==1){
            this.bombas_Player1[contador1][contador2]=true;
        }else if (turno==2){
            this.bombas_Player2[contador1][contador2]=true;
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
        if (dificultad.equals("EASY")){
            barcosDisponibles=5;
        }else if (dificultad.equals("NORMAL")){
            barcosDisponibles=4;
        }else if(dificultad.equals("EXPERT")){
            barcosDisponibles=2;
        }else if (dificultad.equals("GENIUS")){
            barcosDisponibles=1;
        }
    }
    
    public int getBarcosDisp(){
        return barcosDisponibles;
    }
    
    
    
    
}
