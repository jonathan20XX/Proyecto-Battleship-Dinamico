/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleship;

/**
 *
 * @author jc1st
 */
public class SistemaLogin {
    private Player[] jugadores = new Player[20];
    private int jugadoresTotales=0;
    private Player usuarioActual;

    public Player[] getJugadores() {
        return jugadores;
    }

    public Player getUsuarioActual() {
        return usuarioActual;
    }
    
    public boolean login(String user, String password){
        usuarioActual = buscarUsuario(user, password, 0);
        return usuarioActual != null;
    }
    
    private Player buscarUsuario(String user, String password, int i){
        if(i>=jugadoresTotales)
            return null;
        
        if(jugadores[i].getUsuario().equals(user) && jugadores[i].validarPassword(password)){
            return jugadores[i];
        }
        
        return buscarUsuario(user, password, i+1);
    }
    
    public boolean crearUsuario(String user, String password){
        if(existeUsuario(user, 0))
            return false;
        
        jugadores[jugadoresTotales++]=new Player(user, password);
        usuarioActual = jugadores[jugadoresTotales-1];
        
        return true;
    }
    
    private boolean existeUsuario(String user, int i){
        if(i>=jugadoresTotales)
            return false;
        
        if(jugadores[i].getUsuario().equals(user))
            return true;
        
        return existeUsuario(user, i+1);
    }
}
