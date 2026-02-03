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
    
    private JPanel menuPrincipal, pantallaLogin, pantallaTablero;
    Tablero tablero;
    private JTextArea areaTablero;
    
    public PantallaJuego(){
        setTitle("BATTLESHIP DINAMICO");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new CardLayout());
        
        tablero = new Tablero(8);
        
        menuPrincipal();
        pantallaLogin();
        pantallaTablero();
        
        add(menuPrincipal, "MENU PRINCIPAL");
        add(pantallaLogin, "LOGIN");
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
        
        JButton btnLogin = new JButton("LOGIN");
        JButton btnJugar = new JButton("JUGAR");
        JButton btnSalir = new JButton("SALIR");
        
        menuPrincipal.add(btnLogin);
        menuPrincipal.add(btnJugar);
        menuPrincipal.add(btnSalir);
        
        btnLogin.addActionListener(e->showPanel("LOGIN"));
         btnJugar.addActionListener(e -> {
            areaTablero.setText(tablero.mostrarTablero());
            showPanel("TABLERO DE JUEGO");
        });
        btnSalir.addActionListener(e -> System.exit(0));
    }
    
    private void pantallaLogin(){
        pantallaLogin = new JPanel();
        pantallaLogin.setLayout(new GridLayout(3, 2, 10, 10));
        
        pantallaLogin.add(new JLabel("Usuario:"));
        JTextField userField = new JTextField();
        pantallaLogin.add(userField);
        
        pantallaLogin.add(new JLabel("Password:"));
        JPasswordField passField = new JPasswordField();
        pantallaLogin.add(passField);
        
        JButton btnAtras = new JButton("Volver");
        btnAtras.addActionListener(e -> showPanel("MENU PRINCIPAL"));
        pantallaLogin.add(btnAtras);
        
        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(e -> JOptionPane.showMessageDialog(this, "Inicio de sesion exitoso!"));
        pantallaLogin.add(btnLogin);
    }
    
    private void pantallaTablero(){
        pantallaTablero = new JPanel();
        pantallaTablero.setLayout(new BorderLayout());
        
        areaTablero = new JTextArea();
        areaTablero.setFont(new Font("Monospaced", Font.PLAIN, 20));
        areaTablero.setEditable(false);
        areaTablero.setText(tablero.mostrarTablero());
        pantallaTablero.add(new JScrollPane(areaTablero), BorderLayout.CENTER);
        
        JPanel panelEntrada = new JPanel();
        panelEntrada.add(new JLabel("Fila:"));
        JTextField entradaFila = new JTextField(2);
        panelEntrada.add(entradaFila);
        
        panelEntrada.add(new JLabel ("Columna:"));
        JTextField entradaColumna = new JTextField(2);
        panelEntrada.add(entradaColumna);
        
        JButton btnFuego = new JButton("DISPARAR");
        
        btnFuego.addActionListener(e -> {
            try {
                int fila = Integer.parseInt(entradaFila.getText());
                int columna = Integer.parseInt(entradaColumna.getText());

                if (fila < 0 || fila >= 8 || columna < 0 || columna >= 8) {
                    JOptionPane.showMessageDialog(this, "Coordenadas fuera del tablero!");
                    return;
                }

                String resultado = tablero.disparo(fila, columna);
                JOptionPane.showMessageDialog(this, resultado);

                areaTablero.setText(tablero.mostrarTablero()); 

                if (tablero.todosHundidos()) {
                    JOptionPane.showMessageDialog(this, "Felicidades! Has hundido todos los barcos.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Introduce numeros válidos!");
            }
        });
        panelEntrada.add(btnFuego);
        
        JButton btnAtras = new JButton("Volver al menu");
        btnAtras.addActionListener(e -> showPanel("MENU PRINCIPAL"));
        panelEntrada.add(btnAtras);
        
        pantallaTablero.add(panelEntrada, BorderLayout.SOUTH);
    }
}
