/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author jc1st
 */
public class PantallaJuego extends JFrame{
    
    private JPanel menuPrincipal, pantallaLoginJ1, pantallaLoginJ2, pantallaTablero;
    private JTextField userJ1Field, userJ2Field;

    private JButton[][] botonesJ1;
    private JButton[][] botonesJ2;

    private Player jugador1;
    private Player jugador2;

    private LogicaDeJuego ejecucion;
    
    public PantallaJuego(){
        setTitle("BATTLESHIP DINAMICO");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new CardLayout());
        
        menuPrincipal();
        pantallaLoginJ1();
        pantallaLoginJ2();
        pantallaTablero();
        
        add(menuPrincipal, "MENU PRINCIPAL");
        add(pantallaLoginJ1, "LOGIN JUGADOR 1");
        add(pantallaLoginJ2, "LOGIN JUGADOR 2");
        add(pantallaTablero, "TABLERO DE JUEGO");

        showPanel("MENU PRINCIPAL");
    }
    
    private void showPanel(String nombrePanel) {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), nombrePanel);
    }
    
    private void menuPrincipal(){
        menuPrincipal = new JPanel();
        menuPrincipal.setLayout(new GridBagLayout());
        
        JButton btnJugar = new JButton("JUGAR");
        JButton btnLogin = new JButton("LOGIN");
        JButton btnSalir = new JButton("SALIR");
        
        btnJugar.addActionListener(e -> showPanel("LOGIN JUGADOR 1"));
        btnSalir.addActionListener(e -> System.exit(0));
        
        menuPrincipal.add(btnJugar);
        menuPrincipal.add(btnLogin);
        menuPrincipal.add(btnSalir);
        
        btnLogin.addActionListener(e -> showPanel("LOGIN JUGADOR 1"));

        btnJugar.addActionListener(e -> {
            if (jugador1 == null) {
                JOptionPane.showMessageDialog(this, "Primero inicia sesion con Jugador 1");
                return;
            }
            showPanel("LOGIN JUGADOR 2");
        });

        btnSalir.addActionListener(e -> System.exit(0));
    }
    
    private void pantallaLoginJ1(){
        pantallaLoginJ1 = new JPanel(new GridLayout(3, 2, 10, 10));

        pantallaLoginJ1.add(new JLabel("Usuario Jugador 1:"));
        userJ1Field = new JTextField();
        pantallaLoginJ1.add(userJ1Field);

        JButton btnContinuar = new JButton("Continuar");
        btnContinuar.addActionListener(e -> {
            jugador1 = new Player(userJ1Field.getText());
            JOptionPane.showMessageDialog(this, "Jugador 1 logeado: " + jugador1.getUsuario());
            showPanel("LOGIN JUGADOR 2");
        });

        pantallaLoginJ1.add(new JLabel());
        pantallaLoginJ1.add(btnContinuar);
    }
    
    private void pantallaLoginJ2(){
        pantallaLoginJ2 = new JPanel(new GridLayout(3, 2, 10, 10));

        pantallaLoginJ2.add(new JLabel("Jugador 2:"));
        userJ2Field = new JTextField();
        pantallaLoginJ2.add(userJ2Field);

        JButton btnIniciar = new JButton("Iniciar partida");
        btnIniciar.addActionListener(e -> {
            jugador2 = new Player(userJ2Field.getText());

            ejecucion = new LogicaDeJuego(jugador1, jugador2, 8);

            actualizarTableros();
            showPanel("TABLERO DE JUEGO");
        });

        pantallaLoginJ2.add(new JLabel());
        pantallaLoginJ2.add(btnIniciar);
    }
    
   private void pantallaTablero(){
        pantallaTablero = new JPanel(new BorderLayout());

        JPanel panelTableros = new JPanel(new GridLayout(1, 2, 20, 0));

        botonesJ1 = crearTablero(true);
        botonesJ2 = crearTablero(false);

        panelTableros.add(crearPanelJugador("Jugador 1", botonesJ1));
        panelTableros.add(crearPanelJugador("Jugador 2", botonesJ2));

        pantallaTablero.add(panelTableros, BorderLayout.CENTER);

        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(e -> showPanel("MENU PRINCIPAL"));
        pantallaTablero.add(btnSalir, BorderLayout.SOUTH);
    }
    
   
   
     private JButton[][] crearTablero(boolean tableroJ1) {
        JButton[][] botones = new JButton[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton btn = new JButton("~");
                btn.setFont(new Font("Monospaced", Font.BOLD, 16));

                int fila = i;
                int col = j;

                btn.addActionListener(e -> manejarClick(fila, col));

                botones[i][j] = btn;
            }
        }
        return botones;
    }
     
    private JPanel crearPanelJugador(String nombre, JButton[][] botones) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(nombre, SwingConstants.CENTER), BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(8, 8));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                grid.add(botones[i][j]);
            }
        }

        panel.add(grid, BorderLayout.CENTER);
        return panel;
    } 
     
    private void manejarClick(int fila, int columna) {
        if(ejecucion == null) return;
        
        String mensaje = ejecucion.turnoDeDisparo(fila, columna);
        JOptionPane.showMessageDialog(this, mensaje);
        
        actualizarTableros();

        if (ejecucion.hayGanador()) {
            Player ganador = ejecucion.mostrarGanador();
            JOptionPane.showMessageDialog(this,
                    "GANADOR: " + ganador.getUsuario());
        }
    }
     
     private void actualizarTableros() {
        actualizarVista(botonesJ1, ejecucion.getTableroJ1());
        actualizarVista(botonesJ2, ejecucion.getTableroJ2());
    }
     
     private void actualizarVista(JButton[][] botones, Tablero tablero) {
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                char c=tablero.matriz[i][j];
                if (c=='X' || c=='F') {
                    botones[i][j].setText(String.valueOf(c));
                }
            }
        }
    } 
}
