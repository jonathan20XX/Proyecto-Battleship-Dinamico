/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package battleship;

import javax.swing.SwingUtilities;

public class Battleship {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuPrincipal programa = new MenuPrincipal();
            programa.setVisible(true);
        });
    }
    
}
