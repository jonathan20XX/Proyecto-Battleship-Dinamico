/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleship;

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
    
    public String turnoDeDisparo(int fila, int columna){
        if(turnoJ1){
            String resultado = tableroJ2.disparo(fila, columna);
            turnoJ1=false;
            
            return interpretarDisparo(resultado, jugador1, jugador2);
        } else {
            String resultado = tableroJ1.disparo(fila, columna);
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
    
    public void finalizarPartida(){
        Player ganador = mostrarGanador();
        
        if(ganador != null){
            ganador.sumarPuntos(3);
        }
    }
}
