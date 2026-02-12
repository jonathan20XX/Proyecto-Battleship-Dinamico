/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleship;

import java.util.Random;

public class Tablero {
    private char[][] matriz;
    private Barcos[] barcos;
    private Random random;

    public Tablero(int cantidadBarcos, Random rand) {
        this.random = rand;
        this.matriz = new char[8][8];
        this.barcos = new Barcos[cantidadBarcos];
        
        String[] nombres = {"Portaaviones", "Acorazado", "Submarino", "Destructor"};
        String[] codigos = {"PA", "AZ", "SM", "DT"};
        int[] sizes = {5, 4, 3, 2};

        for (int i = 0; i < cantidadBarcos; i++) {
            int tipo = i % 4;
            barcos[i] = new Barcos(nombres[tipo], codigos[tipo], sizes[tipo]);
        }
        
        limpiarMatriz();
        ubicarBarco();
    }

    private void limpiarMatriz() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) matriz[i][j] = '~';
        }
    }

    private void ubicarBarco() {
        for (Barcos b:barcos) {
            if (barcos != null && !b.estaHundido()) {
                boolean colocado = false;
                while (!colocado) {
                    int fila = random.nextInt(8);
                    int columna = random.nextInt(8);
                    boolean horizontal = random.nextBoolean();
                    if (puedeColocar(b.getSize(), fila, columna, horizontal)) {
                        colocarEnMatriz(b, fila, columna, horizontal);
                        colocado = true;
                    }
                }
            }
        }
    }

    public void regenerar() {
        limpiarMatriz();
        ubicarBarco();
    }

    private boolean puedeColocar(int s, int f, int c, boolean h) {
        for (int i=0; i<s; i++) {
            int pf=f + (h?0:i);
            int pc=c+(h?i:0);
            if (pf>=8 || pc>=8 || matriz[pf][pc] != '~') 
                return false;
        }
        return true;
    }

    private void colocarEnMatriz(Barcos b, int f, int c, boolean h) {
        for (int i=0; i < b.getSize(); i++) {
            int pf=f + (h?0:i);
            int pc=c+(h?i:0);
            matriz[pf][pc] = b.getCodigo().charAt(0);
        }
    }

    public String recibirDisparo(int fila, int columna) {
        if (matriz[fila][columna] == '~') {
            matriz[fila][columna] = 'F';
            return "FALLO";
        }
        if (matriz[fila][columna] == 'F' || matriz[fila][columna] == 'X') return "REPETIDO";

        char codigo = matriz[fila][columna];
        matriz[fila][columna] = 'X'; 
        
        for (Barcos b:barcos) {
            if (b.getCodigo().startsWith(String.valueOf(codigo))) {
                b.registrarImpacto();
                return b.estaHundido() ? "HUNDIDO " + b.getCodigo() : "IMPACTO";
            }
        }
        return "IMPACTO";
    }

    public boolean todosHundidos() {
        for (Barcos b:barcos) {
            if (!b.estaHundido()) return false;
        }
        return true;
    }
    
    public char[][] getMatriz() { 
        return matriz; 
    }
}

