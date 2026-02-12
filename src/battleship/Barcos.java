/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleship;

import java.util.ArrayList;

public class Barcos {
    private String nombre;
    private String codigo;
    private int size;
    private int impactosRecibidos;

    public Barcos(String nombre, String codigo, int size) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.size = size;
        this.impactosRecibidos=0;
    }

    public String getCodigo() { 
        return codigo; 
    }
    
    public int getSize() { 
        return size; 
    }
    
    public void registrarImpacto() {
        impactosRecibidos++;
    }

    public boolean estaHundido() {
        return impactosRecibidos >= size;
    }
}
