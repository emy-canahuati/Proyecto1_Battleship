/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pii_proyectoi_emycanahuati_22541021;

import java.util.ArrayList;

/**
 *
 * @author emyca
 */
public class Player {
    private String[] ultimas_partidas;
    private String nombre, contraseña;
    private int puntos, indice;
    
    Player(String nombre, String contraseña){
        this.nombre=nombre;
        this.contraseña=contraseña;
        puntos=0;
        indice=0;
        ultimas_partidas= new String[10];
    }
    
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    
    public String getNombre(){
        return nombre;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    public String getContraseña(){
        return contraseña;
    }

    public void setPuntos(int puntos) {
        this.puntos+=puntos;
    }
    
    public int getPuntos(){
        return puntos;
    }
    public void setUltimasPartidas(String ganador,String perdedor,String modo, String fin_partida){
            if (indice>=10){
                for (int contador=0; contador<9;contador++){
                    ultimas_partidas[contador]=ultimas_partidas[contador+1];
                }
                indice=9;
            }
            String registro="";
            if (fin_partida.equals("hundimiento")) {//significa que alguno de los jugadores hundio todos los barcos del otro
                registro=(ganador+" hundió todos los barcos de "+perdedor+" en modo "+modo);
            }else if (fin_partida.equals("retirada")){ //significa que un jugador se retiro
                    registro=(perdedor+" se retiró del juego dejando a "+ganador+" como el ganador en modo "+modo);
            }
            
            ultimas_partidas[indice]=registro;
            indice++;
    }
    
    public String[] getUltimasPartidas(){
        return ultimas_partidas;
    }
   
}
