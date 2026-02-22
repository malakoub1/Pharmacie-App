package main;

import gui.Login;

import javax.swing.SwingUtilities;

public class AppLauncher {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new 
                Login().setVisible(true);
        });
    }
}