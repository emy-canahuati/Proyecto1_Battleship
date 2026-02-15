/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pii_proyectoi_emycanahuati_22541021;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author emyca
 */
public class Barco {
    protected int cant_bombas, fila, columna;
    protected String nombre;
    protected boolean seleccionado;
    protected ImageIcon icono;
    protected Codigos ID;
    
    enum Codigos{
        PA("Portaaviones", 5), AZ("Acorazado", 4), SM("Submarino", 3), DT("Destructor", 2);
        public String nombre;
        public int cant_bombas;
        
        Codigos(String nombre, int cant_bombas){
            this.nombre=nombre;
            this.cant_bombas=cant_bombas;
            
        }
    }
    

    public Barco(String ID){
        this.ID=Codigos.valueOf(ID);
        seleccionado=false;
        nombre=this.ID.nombre;
        cant_bombas=this.ID.cant_bombas;
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
        return ID.name();
    }

    public void setID(String ID) {
        this.ID = Codigos.valueOf(ID);
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
   
    public ImageIcon getIcon(){
        switch (ID) {
            case PA:
                icono=new ImageIcon("src/Imagenes/portaaviones.png");
                break;
            case AZ:
                icono=new ImageIcon("src/Imagenes/acorazado.png");
                break;
            case SM:
                icono=new ImageIcon("src/Imagenes/submarino.png");
                break;
            case DT:
                icono=new ImageIcon("src/Imagenes/destructor.png");
                break;
            default:
                break;
        }
        return icono;
    }
    
    public ImageIcon getIconHits(){
        switch (ID) {
            case PA:
                icono=new ImageIcon("src/Imagenes/portaaviones_hit.png");
                break;
            case AZ:
                icono=new ImageIcon("src/Imagenes/acorazado_hit.png");
                break;
            case SM:
                icono=new ImageIcon("src/Imagenes/submarino_hit.png");
                break;
            case DT:
                icono=new ImageIcon("src/Imagenes/destructor_hit.png");
                break;
            default:
                break;
        }
        
        return icono;
    }
}
