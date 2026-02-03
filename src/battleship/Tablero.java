/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleship;

import java.util.Random;

public class Tablero {
    Random randomize=new Random();
    
    protected char[][] matriz;
    protected Barcos[] barco;
    protected int cantidadBarcos;
    protected Random posicionAleatoria= new Random();

    public Tablero(int cantidadBarcos) {
        this.cantidadBarcos = cantidadBarcos;
        matriz = new char[8][8];
        barco = new Barcos[cantidadBarcos];
        iniciar();
    }
    
    private void iniciar(){
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                matriz[i][j] = '~';
            }
        }
    }
    
    public boolean colocarBarco(Barcos bote, int fila, int columna, boolean horizontal){
        if(!puedeColocar(bote.getSize(), fila, columna, horizontal)){
            return false;
        }
        
        for(int i=0; i<bote.getSize(); i++){
            int posicionFila = fila+(horizontal ? 0 : i);
            int posicionColumna = columna+(horizontal ? i : 0);
            
            matriz[posicionFila][posicionColumna] = bote.getCodigo().charAt(0);
            bote.agregarPosicion(posicionFila, posicionColumna);
        }
        
        agregarBarco(bote);
        return true;
    }
    
    public void colocarBarcoAleatorio(Barcos b){
        int fila = randomize.nextInt(8);
        int columna = randomize.nextInt(8);
        boolean horizontal = randomize.nextBoolean();
        
        if(!colocarBarco(b, fila, columna, horizontal)){
            colocarBarcoAleatorio(b);
        }
    }
    
    public boolean puedeColocar(int size, int fila, int columna, boolean horizontal){
        for(int i=0; i<size; i++){
            int posicionFila = fila+(horizontal ? 0:i);
            int posicionColumna = columna+(horizontal ? i:0);
            
            if (posicionFila<0 || posicionFila>=8 || posicionColumna<0 || posicionColumna>=8){
                return false;
            }
            
            if(matriz[posicionFila][posicionColumna] != '~'){
                return false;
            }
        }
        return true;
    }
    
    public String disparo(int fila, int columna){
        if(matriz[fila][columna] == '~'){
            matriz[fila][columna] = 'F'; 
            return "FALLO";
        }
        
        if(matriz[fila][columna] == 'F' || matriz[fila][columna] == 'X'){  
            return "REPETIDO";
        }
        
        char impacto = matriz[fila][columna];
        matriz[fila][columna] = 'X';
        
        Barcos b = buscarBarcoImpactado(impacto);
        String mensaje = "IMPACTO";
        
       if (b != null && b.recibioImpacto()) {
       mensaje = "SE HUNDIO EL " + b.getCodigo();
        }
       
       iniciarRegeneracion();
       return mensaje;
    }   
    
    
    private Barcos buscarBarcoImpactado(char code){
        for(Barcos b:barco){
            if(b != null && b.getCodigo().charAt(0) == code){
                return b;
            }
        }
        return null;
    }
    
    private void limpiarTablero(){
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                matriz[i][j]='~';
            }
        }
    }
    
    public void iniciarRegeneracion(){
        limpiarTablero();
        regenerarTablero(0);
    }
    
    private void regenerarTablero(int i){
        if(i>=barco.length){
            return;
        }
        
        Barcos b = barco[i];
        
        if(b !=null && !b.estaHundido()){
            colocarBarcoAleatorio(b);
        }
        
        regenerarTablero(i+1);
    }
    
    public String mostrarTablero(){
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<matriz.length; i++) {
            for (int j=0; j<matriz[i].length; j++) {
                sb.append(matriz[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    
    private void agregarBarco(Barcos bote){
        for(int i=0; i<barco.length; i++){
            if(barco[i] == null || barco[i] == bote){
                barco[i] = bote;
                return;
            }
        }
    }
    
    public boolean todosHundidos(){
        for(Barcos b : barco){
            if(b != null && !b.estaHundido()){
                return false;
            }
        }
        return true;
    }
}
