/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleship;

import java.util.ArrayList;
/**
 *
 * @author jc1st
 */
public class LogicaDeJuego {
    private ArrayList<Player> listaJugadores;
    private Player userLoggedIn;
    private Player oponente;
    private int barcosPorDificultad=4;
    
    private Tablero tableroJ1;
    private Tablero tableroJ2;
    private String dificultad = "NORMAL";
    private boolean turnoJ1 = true;
    
    public LogicaDeJuego(){
        this.listaJugadores=new ArrayList<>();
        this.userLoggedIn=null;
    }
    
    public boolean login(String username, String password) {
        for (Player persona : listaJugadores) {
            if (persona.getUsuario().equals(username) && persona.validarPassword(password)) {
                this.userLoggedIn = persona;
                return true;
            }
        }
        return false;
    }
    
    public void logout(){
        this.userLoggedIn=null;
    }

    public boolean crearPlayer(String username, String password) {
        if (username.trim().isEmpty() || buscarJugador(username) != null) {
            return false;
        }
        listaJugadores.add(new Player(username, password));
        return true;
    }
    
    private Player buscarJugador(String username) {
        for (Player persona : listaJugadores) {
            if (persona.getUsuario().equalsIgnoreCase(username)) 
                return persona;
        }
        return null;
    }

    public void modificarPerfil(String nuevoUser, String nuevoPass) {
        if (userLoggedIn != null) {
            userLoggedIn.setUsuario(nuevoUser);
            userLoggedIn.setPassword(nuevoPass);
        }
    }

    public boolean eliminarCuenta() {
        if (userLoggedIn != null) {
            listaJugadores.remove(userLoggedIn);
            userLoggedIn = null;
            return true;
        }
        return false;
    }
    
    public boolean loginOponente(String username, String password) {
    for (Player p : listaJugadores) {
        if (p.getUsuario().equals(username) && p.validarPassword(password)) {
            this.oponente = p;
            return true;
        }
    }
    return false;
}

    public ArrayList<Player> obtenerRanking() {
        ArrayList<Player> ranking = new ArrayList<>(listaJugadores);
        ranking.sort((p1, p2) -> Integer.compare(p2.getPuntos(), p1.getPuntos()));
        return ranking;
    }

    public Player getUserLoggedIn() {
        return userLoggedIn;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad, int cantidad) {
        this.dificultad = dificultad;
        this.barcosPorDificultad=cantidad;
    }

    public int getBarcosPorDificultad() {
        return barcosPorDificultad;
    }
    
    public void prepararNuevaPartida() {
        this.tableroJ1 = new Tablero(barcosPorDificultad, new java.util.Random());
        this.tableroJ2 = new Tablero(barcosPorDificultad, new java.util.Random());
        this.turnoJ1 = true;
    }

    public Tablero getTableroJ1() {
        return tableroJ1;
    }

    public Tablero getTableroJ2() {
        return tableroJ2;
    }
    
    
    public String turnoDeDisparo(int fila, int columna) {
        String resultado;
        
        if (turnoJ1) {
            resultado = tableroJ2.recibirDisparo(fila, columna);
            
            if (resultado.contains("IMPACTO") || resultado.contains("HUNDIDO")) {
                tableroJ2.regenerar(); 
            }
            
            if (!resultado.equals("REPETIDO")) turnoJ1 = false;
            return interpretarMensaje(resultado, userLoggedIn, oponente);
        } else {
            resultado = tableroJ1.recibirDisparo(fila, columna);
            
            if (resultado.contains("IMPACTO") || resultado.contains("HUNDIDO")) {
                tableroJ1.regenerar();
            }
            
            if (!resultado.equals("REPETIDO")) turnoJ1 = true;
            return interpretarMensaje(resultado, oponente, userLoggedIn);
        }
    }

    private String interpretarMensaje(String resultado, Player atacante, Player defensor) {
        switch (resultado) {
            case "FALLO": return atacante.getUsuario() + " disparo al agua.";
            case "IMPACTO": return "IMPACTO! Los barcos de " + defensor.getUsuario() + " han cambiado de posicion.";
            case "REPETIDO": return "Ya disparaste aqui, intenta otro lado";
            default: return resultado;
        }
    }    
        
    
    public void agregarPuntos(Player ganador){
        if(ganador != null){
            ganador.sumarPuntos();
            ganador.agregarLog("Victoria contra "+
                    (ganador == userLoggedIn ? oponente.getUsuario():userLoggedIn.getUsuario())+"+3 pts");
        }
    }
    
    public void finalizarPorRetiro(){
        Player ganador = oponente;
        agregarPuntos(ganador);
        userLoggedIn.agregarLog("Retiro / Derrota contra: "+oponente.getUsuario());
    }
    
    public Player getGanador(){
        if(tableroJ1.todosHundidos())
            return oponente;
        
        if(tableroJ2.todosHundidos())
            return userLoggedIn;
        
        return null;
    }
    
    public boolean hayGanador(){
        return tableroJ1.todosHundidos() || tableroJ2.todosHundidos();
    }
    
    public Player getTurnoActual(){
        return turnoJ1 ? userLoggedIn : oponente;
    }
}    
