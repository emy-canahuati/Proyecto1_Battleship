/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pii_proyectoi_emycanahuati_22541021;

/**
 *
 * @author emyca
 */
public class Configuracion {
    private static Battleship.Dificultad dificultadActual = Battleship.Dificultad.NORMAL;
    private static Battleship.Modo modoActual = Battleship.Modo.TUTORIAL;

    public static void setAjustes(Battleship.Dificultad dificultad, Battleship.Modo modo) {
        dificultadActual = dificultad;
        modoActual = modo;
    }
    
    public static void setDificultad(String dificultad) {
        Configuracion.dificultadActual = Battleship.Dificultad.valueOf(dificultad);
    }

    public static void setModo(String modo) {
        Configuracion.modoActual = Battleship.Modo.valueOf(modo);
    }

    public static Battleship.Dificultad getDificultadActual() {
        return dificultadActual;
    }

    public static Battleship.Modo getModoActual() {
        return modoActual;
    }
    
    
}
