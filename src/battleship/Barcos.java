/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleship;

public class Barcos {
    protected String codigo;
    protected int size;
    protected int vida;
    protected int[][] posicion;
    protected int usadas=0;

    public Barcos(String codigo, int size) {
        this.codigo = codigo;
        this.size = size;
        this.vida = size;
        this.posicion = new int[size][2];
    }
    
    public void agregarPosicion(int fila, int columna){
        posicion[usadas][0]=fila;
        posicion[usadas][1]=columna;
        usadas++;
    }
    
    public boolean recibioImpacto(){
        vida--;
        return vida==0;
    }
    
    public boolean estaHundido(){
        return vida == 0;
    }
    
    public String getCodigo(){
        return codigo;
    }
    
    public int getSize(){
        return size;
    }
}
