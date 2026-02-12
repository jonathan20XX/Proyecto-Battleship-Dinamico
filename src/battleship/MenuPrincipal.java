/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleship;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author jc1st
 */
public class MenuPrincipal extends JFrame{
    private LogicaDeJuego control;
    private JPanel pantallaPrincipal;
    private CardLayout cardLayout;
    private JButton[][] botonesJ1 = new JButton[8][8];
    private JButton[][] botonesJ2 = new JButton[8][8];
    private JLabel lblTurno;
    
    public MenuPrincipal() {
        control=new LogicaDeJuego();
        
        setTitle("BATTLESHIP DINAMICO");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        cardLayout=new CardLayout();
        pantallaPrincipal=new JPanel(cardLayout);
        
        pantallaPrincipal.add(crearPanelInicio(), "INICIO");
        pantallaPrincipal.add(crearPanelLogin(), "LOGIN");
        pantallaPrincipal.add(crearPanelCrearUsuario(), "CREAR USUARIO");
        pantallaPrincipal.add(crearMenuPrincipal(), "MENU PRINCIPAL");
        pantallaPrincipal.add(crearPanelReportes(), "REPORTES");
        pantallaPrincipal.add(crearPanelSeleccionOponente(), "SELECCION DE OPONENTE");
        pantallaPrincipal.add(crearPantallaJuego(), "PANTALLA JUEGO");
        pantallaPrincipal.add(crearPanelConfiguracion(), "CONFIGURACION");
        pantallaPrincipal.add(crearPanelPerfil(), "MI PERFIL");
        
        add(pantallaPrincipal);
        cardLayout.show(pantallaPrincipal, "INICIO");
    }
    
    private JPanel crearMenuPrincipal(){
        JPanel panel = new JPanel(new GridBagLayout());
        
        panel.setBackground(new Color(44, 62, 80));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel lblTitulo = new JLabel("MENU PRINCIPAL");
        lblTitulo.setForeground(Color.CYAN);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        
        JButton btnJugar = new JButton("JUGAR BATTLESHIP");
        JButton btnConfig = new JButton("CONFIGURACION");
        JButton btnReportes = new JButton("REPORTES");
        JButton btnPerfil = new JButton("MI PERFIL");
        JButton btnCerrar = new JButton("CERRAR SESION");
        
        Dimension btnDim = new Dimension(200, 40);
        JButton[] buttons = {btnJugar, btnConfig, btnReportes, btnPerfil, btnCerrar};
        for(JButton b : buttons) b.setPreferredSize(btnDim);
        
        btnJugar.addActionListener(e -> {
            cardLayout.show(pantallaPrincipal, "SELECCION DE OPONENTE");
        });

        btnConfig.addActionListener(e -> {
            cardLayout.show(pantallaPrincipal, "CONFIGURACION");
        });

        btnReportes.addActionListener(e -> {
            cardLayout.show(pantallaPrincipal, "REPORTES");
        });

        btnPerfil.addActionListener(e -> {
            cardLayout.show(pantallaPrincipal, "MI PERFIL");
        });

        btnCerrar.addActionListener(e -> {
            control.logout();
            cardLayout.show(pantallaPrincipal, "INICIO");
        });
        
        gbc.gridx = 0; gbc.gridy = 0; panel.add(lblTitulo, gbc);
        gbc.gridy = 1; panel.add(btnJugar, gbc);
        gbc.gridy = 2; panel.add(btnConfig, gbc);
        gbc.gridy = 3; panel.add(btnReportes, gbc);
        gbc.gridy = 4; panel.add(btnPerfil, gbc);
        gbc.gridy = 5; panel.add(btnCerrar, gbc);

        return panel;
    }
    
    private JPanel crearPanelInicio(){
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(25, 25, 25));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel lblTitulo = new JLabel("BATTLESHIP DINAMICO", SwingConstants.CENTER);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Serif", Font.BOLD, 30));
        
        JButton btnLogin = new JButton("LOGIN");
        JButton btnCrear = new JButton("CREAR USUARIO");
        JButton btnSalir = new JButton("SALIR");
        
        Dimension dim=new Dimension(250, 45);
        btnLogin.setPreferredSize(dim);
        btnCrear.setPreferredSize(dim);
        btnSalir.setPreferredSize(dim);
        
        btnLogin.addActionListener(e -> cardLayout.show(pantallaPrincipal, "LOGIN"));
        btnCrear.addActionListener(e -> cardLayout.show(pantallaPrincipal, "CREAR USUARIO"));
        btnSalir.addActionListener(e -> System.exit(0));
        
        gbc.gridy = 0; panel.add(lblTitulo, gbc);
        gbc.gridy = 1; panel.add(btnLogin, gbc);
        gbc.gridy = 2; panel.add(btnCrear, gbc);
        gbc.gridy = 3; panel.add(btnSalir, gbc);
        
        return panel;
    }
    
    private JPanel crearPanelLogin(){
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(181, 176, 173));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JTextField txtUser = new JTextField(15);
        JPasswordField txtPassword = new JPasswordField(15);
        JButton btnIngresar = new JButton("INGRESAR");
        JButton btnVolver = new JButton("VOLVER");
        
        btnIngresar.addActionListener(e -> {
            String usuario = txtUser.getText();
            String password = new String(txtPassword.getPassword());
            
            if(control.login(usuario, password)){
                JOptionPane.showMessageDialog(this, "Bienvenido, " + usuario);
                txtUser.setText(""); txtPassword.setText("");
                cardLayout.show(pantallaPrincipal, "MENU PRINCIPAL");
            } else {
                JOptionPane.showMessageDialog(this, "Usario o password incorrectos");
            }
        });
        
        btnVolver.addActionListener(e -> cardLayout.show(pantallaPrincipal, "INICIO"));
        
        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Usuario:"), gbc);
        gbc.gridx = 1; panel.add(txtUser, gbc);
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1; panel.add(txtPassword, gbc);
        gbc.gridx = 0; gbc.gridy = 2; panel.add(btnIngresar, gbc);
        gbc.gridx = 1; panel.add(btnVolver, gbc);
        
        return panel;
    }
    
    private JPanel crearPanelCrearUsuario(){
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(181, 176, 173));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JTextField txtUser = new JTextField(15);
        JPasswordField txtPass = new JPasswordField(15);
        JButton btnRegistrar = new JButton("REGISTRAR");
        JButton btnVolver = new JButton("VOLVER");
        
        btnRegistrar.addActionListener(e -> {
            String user = txtUser.getText();
            String password = new String(txtPass.getPassword());
            
            if(control.crearPlayer(user, password)){
                JOptionPane.showMessageDialog(this, "Usuario creado exitosamente");
                txtUser.setText("");
                txtPass.setText("");
                cardLayout.show(pantallaPrincipal, "INICIO");
            } else {
                JOptionPane.showMessageDialog(this, "Error: El usario ya existe o faltan datos");
            }
        });
        
        btnVolver.addActionListener(e -> cardLayout.show(pantallaPrincipal, "INICIO"));
        
        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Nuevo Usuario:"), gbc);
        gbc.gridx = 1; panel.add(txtUser, gbc);
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1; panel.add(txtPass, gbc);
        gbc.gridx = 0; gbc.gridy = 2; panel.add(btnRegistrar, gbc);
        gbc.gridx = 1; panel.add(btnVolver, gbc);

        return panel;
    }
    
    private JPanel crearPanelConfiguracion(){
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(45, 45, 45));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        JLabel lblTitulo = new JLabel("CONFIGURACIÓN DE DIFICULTAD");
        lblTitulo.setForeground(Color.YELLOW);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));

        JRadioButton rbEasy = new JRadioButton("EASY");
        JRadioButton rbNormal = new JRadioButton("NORMAL");
        JRadioButton rbExpert = new JRadioButton("EXPERT");
        
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rbEasy); grupo.add(rbNormal); grupo.add(rbExpert);
        rbNormal.setSelected(true);
        
        JButton btnGuardar = new JButton("GUARDAR Y VOLVER");
        btnGuardar.addActionListener(e -> {
            if (rbEasy.isSelected()) control.setDificultad("EASY", 5);
            else if (rbExpert.isSelected()) control.setDificultad("EXPERT", 2);
            else control.setDificultad("NORMAL", 4);
            
            cardLayout.show(pantallaPrincipal, "INICIO");
    });
        
        gbc.gridy = 0; panel.add(lblTitulo, gbc);
        gbc.gridy = 1; panel.add(rbEasy, gbc);
        gbc.gridy = 2; panel.add(rbNormal, gbc);
        gbc.gridy = 3; panel.add(rbExpert, gbc);
        gbc.gridy = 4; panel.add(btnGuardar, gbc);

        return panel;
    }

    private JPanel crearPanelPerfil(){
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(50, 50, 50));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.fill=GridBagConstraints.HORIZONTAL;
        
        JLabel lblTitulo=new JLabel("MI PERFIL");
        lblTitulo.setForeground(Color.CYAN);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        
        JButton btnVerPerfil = new JButton("Ver mis datos");
        JButton btnModificar = new JButton("Modificar Perfil");
        JButton btnBorrar = new JButton("Eliminar esta Cuenta");
        JButton btnRegresar = new JButton("Volver");
        
        btnVerPerfil.addActionListener(e -> {
           Player player = control.getUserLoggedIn();
           
           if(player != null)
               JOptionPane.showMessageDialog(this, "Usuario: "+player.getUsuario()+"\nPuntos obtenidos: "+player.getPuntos());
        });
        
        btnModificar.addActionListener(e -> {
            String usuarioNuevo = JOptionPane.showInputDialog(this, "Nuevo Usuario:");
            String passwordNueva = JOptionPane.showInputDialog(this, "Nueva Password:");
            
            if(usuarioNuevo != null && passwordNueva != null)
                control.modificarPerfil(usuarioNuevo, passwordNueva);
        });
        
        btnBorrar.addActionListener(e -> {
            int respuesta = JOptionPane.showConfirmDialog(this, "Eliminar la cuenta seleccionada?");
            
            if(respuesta == JOptionPane.YES_OPTION){
                control.eliminarCuenta();
                cardLayout.show(pantallaPrincipal, "INICIO");
            }
        });
        
        btnRegresar.addActionListener(e -> cardLayout.show(pantallaPrincipal, "INICIO"));
        
        gbc.gridy=0; panel.add(lblTitulo, gbc);
        gbc.gridy=1; panel.add(btnVerPerfil, gbc);
        gbc.gridy=2; panel.add(btnModificar, gbc);
        gbc.gridy=3; panel.add(btnBorrar, gbc);
        gbc.gridy=4; panel.add(btnRegresar, gbc);
        
        return panel;
    }
    
    private JPanel crearPanelReportes(){
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(40, 40, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel lblTitulo = new JLabel("REPORTES DE PARTIDA");
        lblTitulo.setForeground(Color.ORANGE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        
        JButton btnRanking = new JButton("Clasificacion de Jugadores");
        JButton btnLogs = new JButton("Ver ultimas 10 partidas");
        JButton btnVolver = new JButton("Regresar a menu Principal");
        
        btnRanking.addActionListener(e -> {
            ArrayList<Player> lista = control.obtenerRanking();
            StringBuilder tabla = new StringBuilder("=== CLASIFICACION DE JUGADORES ===\n");
            
            for(Player player:lista){
                tabla.append(player.getUsuario()).append(" Puntos: ").append(player.getPuntos()).append("\n");
            }
            JOptionPane.showMessageDialog(this, new JScrollPane(new JTextArea(tabla.toString())));
        });
        
        btnLogs.addActionListener(e -> {
           Player jugadorLogueado = control.getUserLoggedIn();
           
           if(jugadorLogueado != null){
               JOptionPane.showMessageDialog(this, "Ultimas partidas de "+jugadorLogueado.getUsuario()+"\n"+jugadorLogueado.obtenerLogs());
           }
        });
        
        btnVolver.addActionListener(e -> cardLayout.show(pantallaPrincipal, "INICIO"));
        
        gbc.gridy=0; panel.add(lblTitulo, gbc);
        gbc.gridy=1; panel.add(btnRanking, gbc);
        gbc.gridy=2; panel.add(btnLogs, gbc);
        gbc.gridy=3; panel.add(btnVolver, gbc);

        return panel;
    }

    private JPanel crearPanelSeleccionOponente(){
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(181, 176, 173));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel lblTitulo = new JLabel("SELECCIONAR JUGADOR 2");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        
        JTextField user = new JTextField(15);
        JPasswordField password = new JPasswordField(15);
        JButton btnConfirmar = new JButton("Iniciar Partida");
        JButton btnVolver = new JButton("Cancelar");
        
        btnConfirmar.addActionListener(e -> {
        String user2 = user.getText();
        String pass2 = new String(password.getPassword());
        Player logueado = control.getUserLoggedIn();
        
        if (user2.equals(logueado.getUsuario())) {
            JOptionPane.showMessageDialog(this, "No puedes jugar contra ti mismo.");
        } else if (control.loginOponente(user2, pass2)) {
            JOptionPane.showMessageDialog(this, "Oponente aceptado! Cargando tableros...");
            iniciarPartidaNueva(); 
        } else {
            JOptionPane.showMessageDialog(this, "No se encontro ese usuario");
        }
    });
        
        btnVolver.addActionListener(e -> cardLayout.show(pantallaPrincipal, "MENU PRINCIPAL"));
        
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; panel.add(lblTitulo, gbc);
        gbc.gridwidth = 1; gbc.gridy = 1; panel.add(new JLabel("Username Jugador 2:"), gbc);
        gbc.gridx = 1; panel.add(user, gbc);
        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("Password Jugador 2:"), gbc);
        gbc.gridx = 1; panel.add(password, gbc);
        gbc.gridx = 0; gbc.gridy = 3; panel.add(btnConfirmar, gbc);
        gbc.gridx = 1; panel.add(btnVolver, gbc);

    return panel;
    }

    private void iniciarPartidaNueva() {
        control.prepararNuevaPartida();
        cardLayout.show(pantallaPrincipal, "PANTALLA JUEGO");
        actualizarInterfazTableros();
        
        pantallaPrincipal.revalidate();
        pantallaPrincipal.repaint();
    }
    
    private JPanel crearPantallaJuego(){
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(211, 211, 211));
        
        lblTurno = new JLabel("TURNO DE: ", SwingConstants.CENTER);
        lblTurno.setForeground(Color.BLACK);
        lblTurno.setFont(new Font("Arial", Font.BOLD, 20));
        panelPrincipal.add(lblTurno, BorderLayout.NORTH);
        
        JPanel tableros = new JPanel(new GridLayout(1,2,20,0));
        tableros.setBackground(new Color(44,62,80));
        tableros.add(crearSubTablero("TABLERO J1", botonesJ1, false));
        tableros.add(crearSubTablero("TABLERO J2", botonesJ2, true));
        
        panelPrincipal.add(tableros, BorderLayout.CENTER);
        
        JButton btnRetirar = new JButton("RETIRARSE");
        
        btnRetirar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Te has retirado. El oponente gana +3 puntos");
            control.finalizarPorRetiro();
            cardLayout.show(pantallaPrincipal, "MENU PRINCIPAL");
        });
        
        panelPrincipal.add(btnRetirar, BorderLayout.SOUTH);
        return panelPrincipal;
    }
    
    private JPanel crearSubTablero(String titulo, JButton[][] botonesTablero, boolean enemigo){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(30,30,30));
        
        JLabel lbl = new JLabel(titulo, SwingConstants.CENTER);
        lbl.setForeground(Color.WHITE);
        panel.add(lbl, BorderLayout.NORTH);
        
        JPanel campo=new JPanel(new GridLayout(8,8));
        
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                botonesTablero[i][j]=new JButton("~");
                botonesTablero[i][j].setFont(new Font("Monospaced", Font.BOLD, 10));
                botonesTablero[i][j].setPreferredSize(new Dimension(45, 45));
                botonesTablero[i][j].setMargin(new Insets(0, 0, 0, 0));
                botonesTablero[i][j].setBackground(new Color(0,100,150));
                botonesTablero[i][j].setForeground(Color.WHITE);
                
                final int fila=i;
                final int columna=j;
                
                botonesTablero[i][j].addActionListener(e -> procesarDisparo(fila, columna));
                campo.add(botonesTablero[i][j]);
            }
        }
        
        panel.add(campo, BorderLayout.CENTER);
        return panel;
    }
    
    private void procesarDisparo(int fila, int columna){
        String resultado = control.turnoDeDisparo(fila, columna);
        JOptionPane.showMessageDialog(this, resultado);
    
        actualizarInterfazTableros();

        if (control.hayGanador()) {
            Player ganador = control.getGanador();
            JOptionPane.showMessageDialog(this, "VICTORIA PARA " + ganador.getUsuario() + "!");
            control.agregarPuntos(ganador);
            cardLayout.show(pantallaPrincipal, "MENU PRINCIPAL");
        }
    }    
     
    public void actualizarInterfazTableros() {
        if (control.getTableroJ1() == null || control.getTableroJ2() == null) return;

 
        if (control.getTurnoActual() != null) {
        lblTurno.setText("TURNO DE: " + control.getTurnoActual().getUsuario());
        }

        char[][] m1 = control.getTableroJ1().getMatriz();
        char[][] m2 = control.getTableroJ2().getMatriz();
        boolean esTurnoJ1 = control.getTurnoActual() == control.getUserLoggedIn();

        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                if (esTurnoJ1) {
                botonesJ1[i][j].setText(String.valueOf(m1[i][j]));
                botonesJ1[i][j].setEnabled(false);
                } else {
                char c1 = m1[i][j];
                botonesJ1[i][j].setText((c1 == 'X' || c1 == 'F') ? String.valueOf(c1) : "~");
                botonesJ1[i][j].setEnabled(c1 != 'X' && c1 != 'F');
                }

                if (!esTurnoJ1) {
                botonesJ2[i][j].setText(String.valueOf(m2[i][j]));
                botonesJ2[i][j].setEnabled(false);
                } else {
                char c2 = m2[i][j];
                botonesJ2[i][j].setText((c2 == 'X' || c2 == 'F') ? String.valueOf(c2) : "~");
                botonesJ2[i][j].setEnabled(c2 != 'X' && c2 != 'F');
                }
            
            botonesJ1[i][j].setEnabled(m1[i][j] != 'X' && m1[i][j] != 'F');
            botonesJ2[i][j].setEnabled(m2[i][j] != 'X' && m2[i][j] != 'F');
        }
    }
}       
}
                
        
        
        
    

    


