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
    protected int cont_PA, cont_AZ, cont_SM, cont_DT;
    protected Modo modo;
    protected Dificultad dificultad;
    protected int[][] contTipos = new int[2][4];

    enum Modo{
        TUTORIAL, ARCADE;
    }
    
    enum Dificultad{
        EASY(5), NORMAL(4), EXPERT(2), GENIUS(1);
        public int barcosDisponibles;
        
        Dificultad(int barcosDisponibles){
            this.barcosDisponibles=barcosDisponibles;
        }
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

    public void nuevaPartida(){
    resetearTableros();
    resetContadores();
    this.dificultad= Configuracion.getDificultadActual();
    this.modo=Configuracion.getModoActual();
    turno= 1;
    
    barcosVivosPlayer1 = dificultad.barcosDisponibles;
    barcosVivosPlayer2 = dificultad.barcosDisponibles;
    barcosDisponibles = dificultad.barcosDisponibles;
    }
    
    private void resetearTableros(){
        barcos_Player1= new Barco [8][8];
        barcos_Player2= new Barco [8][8];
        bombas_Player1 = new boolean[8][8];
        bombas_Player2 = new boolean[8][8];
    }

    private boolean Login(String nombre_login, String contraseña_login, int indexJugador){
        if(jugadores==null || jugadores.isEmpty()){
            throw new IllegalArgumentException("Error: No se han creado Players");
        }
        if (indexJugador<jugadores.size()){
            if (jugadores.get(indexJugador)==null)
                return Login(nombre_login, contraseña_login, indexJugador+1);

            if (jugadores.get(indexJugador).getNombre().equals(nombre_login) &&
                jugadores.get(indexJugador).getContraseña().equals(contraseña_login)){
                this.jugador1=indexJugador;
                return true;
            }else{
                return Login(nombre_login, contraseña_login, indexJugador+1);
            }
        }else{
            throw new IllegalArgumentException("Error: Nombre de usuario y/o contraseña invalidos");
        }
    }

    private int getTipoIndex(String ID) {
        switch (ID) {
            case "PA": return 0;
            case "AZ": return 1;
            case "SM": return 2;
            case "DT": return 3;
            default: return -1;
        }
    }

    private int getLimiteTipo(int tipo) {
        if (tipo == 3) {
            return (dificultad == Dificultad.EASY) ? 2 : 1;
        } else {
            return 1;
        }
    }

    public boolean crearPlayers(String nombre_CrearPlayer, String contraseña){
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

    public boolean setJugador2(String player2){
        if (jugadores.size()>1){
            for (int contador=0;contador<jugadores.size();contador++){
                if (jugadores.get(contador)!=null && !jugadores.get(contador).getNombre().equals(jugadores.get(jugador1).getNombre())){
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

    public void modificarDatos(String nombre, String contraseña, int indicador){
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
        }
    }

    public int validarYModificarDatos(String nuevoNombre, String nuevaContra) {
        boolean nombreVacio = nuevoNombre.trim().isEmpty();
        boolean contraVacia = nuevaContra.trim().isEmpty();

        if (nombreVacio && contraVacia) {
            throw new IllegalArgumentException("Error: No puede dejar ambos espacios vacíos");
        }

        int indicador;
        if (nombreVacio) indicador = 1; // Solo contra
        else if (contraVacia) indicador = 2; // Solo nombre
        else indicador = 3; // Ambos

        modificarDatos(nuevoNombre, nuevaContra, indicador);
        return indicador;
    }
    
    public void eliminarCuenta(){
        jugadores.set(jugador1, null);
    }

    public void setDificultad(String dificultad){
        this.dificultad=Dificultad.valueOf(dificultad);
        barcosDisponibles();
    }
    
    public void setModo(String modo){
        this.modo=Modo.valueOf(modo);
    }

    public void barcosDisponibles(){
        barcosDisponibles=dificultad.barcosDisponibles;
        barcosVivosPlayer1=dificultad.barcosDisponibles;
        barcosVivosPlayer2=dificultad.barcosDisponibles;
    }
    
    public int getBarcosDisp(){
        return barcosDisponibles;
    }

    public void turno(){
        turno= (turno==1)? 2:1;
    }

    public int getTurno(){
        return turno;
    }

    public String getJugadorNom(){
        return (turno==1)? jugadores.get(jugador1).getNombre() : jugadores.get(jugador2).getNombre();
    }

    public String getJugadorLogged(){
    return jugadores.get(jugador1).getNombre();
}

    public String getJugadorLoggedContra(){
        return jugadores.get(jugador1).getContraseña();
    }

    
    public String status(){
        return (turno==1)? jugadores.get(jugador2).getNombre()+" tiene "+barcosVivosPlayer2+" naves": jugadores.get(jugador1).getNombre()+" tiene "+barcosVivosPlayer1+" naves";
    }

    public void colocarBombas(int contador1, int contador2){
        if(turno==1){
            bombas_Player1[contador1][contador2]=true;
        }else{
            bombas_Player2[contador1][contador2]=true;
        }
    }

    public boolean compararPosiciones(int fila, int columna){
        String mensaje="";
        if(turno==1){
            if (barcos_Player2[fila][columna]!=null &&
                barcos_Player2[fila][columna].isSeleccionado()){

                barcos_Player2[fila][columna].restarCant_bombas();

                if (barcos_Player2[fila][columna].getCant_bombas()==0){
                    if (barcosVivosPlayer2!=0){
                        barcosVivosPlayer2--;
                        mensaje="Se hundio el "+barcos_Player2[fila][columna].getNombre()+" de "+jugadores.get(jugador2).getNombre();
                        barcos_Player2[fila][columna]=null;
                    }
                    throw new IllegalArgumentException(mensaje);
                }else{
                    return true;
                }
            }else
                return false;
        }else{
            if (barcos_Player1[fila][columna]!=null &&
                barcos_Player1[fila][columna].isSeleccionado()){

                barcos_Player1[fila][columna].restarCant_bombas();

                if (barcos_Player1[fila][columna].getCant_bombas()==0){
                    if (barcosVivosPlayer1!=0){
                        barcosVivosPlayer1--;
                        mensaje="Se hundio el "+barcos_Player1[fila][columna].getNombre()+" de "+jugadores.get(jugador1).getNombre();
                        barcos_Player1[fila][columna]=null;
                    }
                    throw new IllegalArgumentException(mensaje);
                }else
                    return true;
            }else
                return false;
        }
    }

    public String getGanador(){
        String mensaje="";
        if (barcosVivosPlayer2==0){
            setUltimasPartidas(jugadores.get(jugador1).getNombre(), jugadores.get(jugador2).getNombre(), "hundimiento");
            jugadores.get(jugador1).setPuntos(3);
            mensaje="¡El ganador es "+jugadores.get(jugador1).getNombre()+"!";
        }else if(barcosVivosPlayer1==0){
            setUltimasPartidas(jugadores.get(jugador2).getNombre(), jugadores.get(jugador1).getNombre(), "hundimiento");
            jugadores.get(jugador2).setPuntos(3);
            mensaje="¡El ganador es "+jugadores.get(jugador2).getNombre()+"!";
        }
        return mensaje;
    }

    public void setUltimasPartidas(String ganador, String perdedor, String fin_partida){
        jugadores.get(jugador1).setUltimasPartidas(ganador, perdedor, modo.name(), fin_partida);
        jugadores.get(jugador2).setUltimasPartidas(ganador, perdedor, modo.name(), fin_partida);

    }
    
    public void retirada(){
        if (turno==1){
            setUltimasPartidas(jugadores.get(jugador2).getNombre(), jugadores.get(jugador1).getNombre(), "retirada");
            jugadores.get(jugador2).setPuntos(3);
            throw new IllegalArgumentException(jugadores.get(jugador1).getNombre()+" se ha retirado !El ganador es "+ jugadores.get(jugador2).getNombre()+"!");
        }else{
            setUltimasPartidas(jugadores.get(jugador1).getNombre(), jugadores.get(jugador2).getNombre(), "retirada");
            jugadores.get(jugador1).setPuntos(3);
            throw new IllegalArgumentException( jugadores.get(jugador2).getNombre()+" se ha retirado !El ganador es "+jugadores.get(jugador1).getNombre()+"!");
        }
    }

    public void shuffleTablero(){
        if (turno==1){
            List <Barco> lista= new ArrayList<>();
            for (Barco[] fila:barcos_Player2){
                for (Barco barco: fila){
                    lista.add(barco);
                }
            }
            Collections.shuffle(lista);

            int index=0;
            for (int i=0;i<8;i++){
                for(int j=0;j<8;j++){
                    barcos_Player2[i][j]=lista.get(index++);
                }
            }  
        }else{
            List <Barco> lista= new ArrayList<>();
            for (Barco[] fila:barcos_Player1){
                for (Barco barco: fila){
                    lista.add(barco);
                }
            }
            Collections.shuffle(lista);

            int index=0;
            for (int i=0;i<8;i++){
                for(int j=0;j<8;j++){
                    barcos_Player1[i][j]=lista.get(index++);
                }
            }
        }
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
        }
        Image imagenRedimensionada =
                iconoOG.getImage().getScaledInstance(105, 75, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenRedimensionada);
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
    
    public ImageIcon getIconoGolpe(int fila, int col) {
        Barco[][] tableroEnemigo = (turno == 1) ? barcos_Player2 : barcos_Player1;
        if (tableroEnemigo[fila][col] != null) {
            return tableroEnemigo[fila][col].getIconHits();
        }
        return null;
    }

    public String getNombreBarcoEn(int fila, int col) {
        Barco[][] tableroEnemigo = (turno == 1) ? barcos_Player2 : barcos_Player1;
        return (tableroEnemigo[fila][col] != null) ? tableroEnemigo[fila][col].getNombre() : "Agua";
    }
    
    public int getVidasBarcoEn(int fila, int col) {
        Barco[][] tableroEnemigo = (turno == 1) ? barcos_Player2 : barcos_Player1;
        return (tableroEnemigo[fila][col] != null) ? tableroEnemigo[fila][col].getCant_bombas() : 0;
    }
    
    public ArrayList<Player> getRanking(){
        ArrayList<Player> ranking= new ArrayList <>(jugadores);
        Player guardarDato;

        for (int i=0;i<(ranking.size()-1);i++){
            for (int j=0;j<(ranking.size()-1);j++){
                if(ranking.get(j)!=null){
                    if (ranking.get(j).getPuntos()<ranking.get(j+1).getPuntos()){
                        guardarDato=ranking.get(j);
                        ranking.set(j, ranking.get(j+1));
                        ranking.set(j+1,guardarDato);
                    }
                }
            }
        }
        return ranking; 
    }

    public boolean tienePartidasRegistradas() {
        String[] partidas = getUltimasPartidas();
        if (partidas == null) return false;
        for (String player : partidas) {
            if (player != null && !player.trim().isEmpty()) 
                return true;
        }
        return false;
    }

    public boolean tieneRankingActivo() {
        ArrayList<Player> ranking = getRanking();
        for (Player player : ranking) {
            if (player != null && player.getPuntos()>0) 
                return true;
        }
        return false;
    }
    
    public String[] getUltimasPartidas(){
        return jugadores.get(jugador1).getUltimasPartidas();
    }

    
    public void resetContadores() {
        for (int contador1 = 0; contador1< 2; contador1++) {
            for (int contador2= 0; contador2< 4; contador2++) {
                contTipos[contador1][contador2] = 0;
            }
        }
    }

    public boolean puedeColocarTipo(String ID) {
        int tipo = getTipoIndex(ID);
        int jugadorIndex = turno - 1; 
        int limite = getLimiteTipo(tipo);

        return contTipos[jugadorIndex][tipo]<limite;
    }

    public void colocarBarcos(int contador1, int contador2, String ID) {
        if (!puedeColocarTipo(ID)) {
            throw new IllegalArgumentException(
                "No puedes colocar otro barco de tipo " + ID+". Ingrese otro tipo de barco.");
        }

        if (turno == 1) {
            if (barcos_Player1[contador1][contador2] != null &&
                barcos_Player1[contador1][contador2].isSeleccionado()) {

                throw new IllegalArgumentException("No puede colocar dos barcos en la misma posición.");

            } else if (barcos_Player1[contador1][contador2] == null) {

                barcos_Player1[contador1][contador2] = new Barco(ID);
                barcos_Player1[contador1][contador2].setSeleccionado(true);
                barcosDisponibles -= 1;
                contTipos[0][getTipoIndex(ID)]++;
            }
        } else {
            if (barcos_Player2[contador1][contador2] != null &&
                barcos_Player2[contador1][contador2].isSeleccionado()) {

                throw new IllegalArgumentException("No puede colocar dos barcos en la misma posición.");

            } else if (barcos_Player2[contador1][contador2] == null) {

                barcos_Player2[contador1][contador2] = new Barco(ID);
                barcos_Player2[contador1][contador2].setSeleccionado(true);
                barcosDisponibles -= 1;
                contTipos[1][getTipoIndex(ID)]++;
            }
        }
    }
    
    public int estadoColocacion() {
        if (barcosDisponibles > 0) return 0;
        
        turno(); //cambia el turno
        if (turno == 2) {
            barcosDisponibles = dificultad.barcosDisponibles; //reinicia para player2
            return 1; 
        }
        return 2;//ambos listos
    }
    
    public boolean isGameOver() {
        return barcosVivosPlayer1 <= 0 || barcosVivosPlayer2 <= 0;
    }
}