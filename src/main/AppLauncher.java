package main;

import gui.MDIApplication;
import gui.Main; // ta fenêtre login

import javax.swing.SwingUtilities;

public class AppLauncher {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            MDIApplication mdi = new MDIApplication();
            mdi.setVisible(true);

            Main login = new Main(); 
            mdi.addInternalFrame(login); 
            login.setVisible(true);
        });
    }
}