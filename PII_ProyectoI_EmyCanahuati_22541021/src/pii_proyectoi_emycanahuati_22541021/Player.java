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
public class Player {
    private String [] ultimas_partidas= new String[10];
    private String nombre, contraseña;
    private int puntos;
    
    Player(){
    
    }
    
    Player(String nombre, String contraseña){
        this.nombre=nombre;
        this.contraseña=contraseña;
        puntos=0;
    }
    
    String getNombre(){
        return nombre;
    }
    
    String getContraseña(){
        return contraseña;
    }
    
    int getPuntos(){
        return puntos;
    }
    void setUltimasPartidas(String ganador,String perdedor,String modo, String fin_partida, int contador){
        while(contador>0){
            if (fin_partida.equals("hundimiento")) {//significa que alguno de los jugadores hundio todos los barcos del otro
                ultimas_partidas[contador]=ganador+" hundió todos los barcos de "+perdedor+" en modo "+modo;
            }else{ 
                //significa que un jugador se retiro
                if (fin_partida.equals("retirada"))
                    ultimas_partidas[contador]=perdedor+" se retiró del juego dejando a "+ganador+" como el ganador en modo "+modo;
            }
            contador++;
        }
    }
    
    String [] getUltimasPartidas(int num_jugador ){
         return ultimas_partidas;
    }
}
