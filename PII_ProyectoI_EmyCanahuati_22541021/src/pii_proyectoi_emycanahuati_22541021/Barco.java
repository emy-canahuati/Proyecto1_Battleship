/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pii_proyectoi_emycanahuati_22541021;

/**
 *
 * @author emyca
 */
public class Barco {
    protected int cant_bombas, fila, columna;
    protected String ID, nombre;
    protected boolean seleccionado;

    public Barco(String ID){
        this.ID=ID;
        seleccionado=false;
        switch (ID) {
            case "PA":
                cant_bombas=5;
                nombre="Portaaviones";
                break;
            case "AZ":
                cant_bombas=4;
                nombre="Acorazado";
                break;
            case "SM":
                cant_bombas=3;
                nombre="Submarino";
                break;
            case "DT":
                cant_bombas=2;
                nombre="Destructor";
                break;
            default:
                break;
        }
    }

    public String getNombre() {
        return nombre;
    }
    
    public int getCant_bombas() {
        return cant_bombas;
    }

    public void restarCant_bombas() {
        this.cant_bombas = cant_bombas-1;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
    
    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
    
    public void setCoordenadas(int fila,int columna){
        this.fila=fila;
        this.columna=columna;
    }
   
    
}
