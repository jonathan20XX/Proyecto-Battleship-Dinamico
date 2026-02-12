/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleship;

/**
 *
 * @author jc1st
 */
public class Player {
    private String usuario;
    private String password;
    private int puntos;
    private String[] logs;
    private int logIndice=0;

    public Player(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
        this.puntos=0;
        this.logs=new String[10];
    }
    
    public Player(String usuario){
        this.usuario = usuario;
        this.password = "";
        this.puntos = 0;
        this.logs = new String[10];
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }

    public int getPuntos() {
        return puntos;
    }
    
    public void sumarPuntos(){
        puntos += 3;
    }
    
    public boolean validarPassword(String pass){
        return password.equals(pass);
    }
    
    public void agregarLog(String log){
        logs[logIndice%10]=log;
        logIndice++;
    }
    
    public void mostrarLogs(int i){
        if(i>logs.length)
            return;
        System.out.println((i+1)+"- "+logs[i]);
        mostrarLogs(i+1);
    }
    
    public String obtenerLogs() {
        StringBuilder sb = new StringBuilder();
  
        for (int i=0; i<logs.length; i++) {
        if (logs[i] != null) {
            sb.append("> ").append(logs[i]).append("\n");
        }
    }
    return sb.toString();
}
}
