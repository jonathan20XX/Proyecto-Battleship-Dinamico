/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleship;

import java.util.Random;
/**
 *
 * @author jc1st
 */
public class LogicaDeJuego {
    private Tablero tableroJ1;
    private Tablero tableroJ2;
    private Player jugador1;
    private Player jugador2;
    
    private boolean turnoJ1 = true;
    
    public LogicaDeJuego(Player jugador1, Player jugador2, int cantidadBarcos){
    this.jugador1 = jugador1;
    this.jugador2 = jugador2;

    this.tableroJ1 = new Tablero(cantidadBarcos, new Random(System.currentTimeMillis()));
    this.tableroJ2 = new Tablero(cantidadBarcos, new Random(System.currentTimeMillis() + 1000));
}

    public Tablero getTableroJ1() {
        return tableroJ1;
    }

    public Tablero getTableroJ2() {
        return tableroJ2;
    }
    
    public String turnoDeDisparo(int fila, int columna){
        String resultado;

        if (turnoJ1) {
            resultado = tableroJ2.disparo(fila, columna);
            turnoJ1 = false;
            return interpretarDisparo(resultado, jugador1, jugador2);
        } else {
            resultado = tableroJ1.disparo(fila, columna);
            turnoJ1 = true;
            return interpretarDisparo(resultado, jugador2, jugador1);
        }
    }
    
    private String interpretarDisparo(String disparo, Player atacante, Player defensor){
        switch(disparo){
            case "FALLO":
                return atacante.getUsuario() + " fallo el disparo contra: "+defensor.getUsuario();
            case "IMPACTO":
                return atacante.getUsuario() + " impacto el barco de: "+defensor.getUsuario();
            case "REPETIDO":
                return "Esa posicion ya fue atacada";
            default:
                return "";
        }
    }
    
    public boolean hayGanador(){
        return tableroJ1.todosHundidos() || tableroJ2.todosHundidos();
    }
    
    public Player mostrarGanador(){
        if(tableroJ1.todosHundidos()){
            return jugador2;
        }
        
        if(tableroJ2.todosHundidos()){
            return jugador1;
        }
        return null;
    }
}
