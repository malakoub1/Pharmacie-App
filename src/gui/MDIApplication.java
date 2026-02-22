/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.plot.PlotOrientation;
import javax.swing.JFrame;
import Service.MedicamentService;
import Service.FournisseurService;
import Service.VenteService;
import model.Medicament;
import model.Fournisseur;
import java.util.List;
import java.util.Map;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author pc
 */
public class MDIApplication extends javax.swing.JFrame {

    private static MDIApplication instance;
    
    public MDIApplication (){
        initComponents ();
        this.setTitle("Gestion de Pharmacie");
        this.setExtendedState(MAXIMIZED_BOTH);
        setIconImage(new
         javax.swing.ImageIcon(
            getClass().getResource("/images/icone.png")).getImage());
        
    }
    
    public static MDIApplication getInstance(){
        if (instance == null){
            instance = new MDIApplication ();
        }
        return instance;
    }
    
    private void fermerFenetresExistantes(JDesktopPane desktop){
        for (JInternalFrame frame : desktop.getAllFrames()){
            frame.dispose();
        }
    }
    public void addInternalFrame(JInternalFrame frame) {
    desktopPane.add(frame);
    frame.setVisible(true);
    frame.toFront();
}

    /**
     * Creates new form MDIApplication
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();

        javax.swing.GroupLayout desktopPaneLayout = new javax.swing.GroupLayout(desktopPane);
        desktopPane.setLayout(desktopPaneLayout);
        desktopPaneLayout.setHorizontalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1094, Short.MAX_VALUE)
        );
        desktopPaneLayout.setVerticalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 637, Short.MAX_VALUE)
        );

        jMenu1.setText("Gestion");

        jMenuItem2.setText("Medicament");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuMedicamentActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Founisseur");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuFournisseurActionPerfomed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Vente");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuVenteActionPerfomed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Recherche");

        jMenuItem5.setText("Par fournisseur");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RechercheByFournisseur(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem6.setText("Par date ");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RechercheByDate(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuItem7.setText("Par famille");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RechercheByFamille(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Statistique");

        jMenuItem1.setText("Medicament sous seuil");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem8.setText("Vente Par Famille");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenuMedicamentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuMedicamentActionPerformed
        // TODO add your handling code here:
        try {
        fermerFenetresExistantes(desktopPane);
        MedicamentForm mf = new MedicamentForm();
        desktopPane.add(mf);
        mf.setVisible(true);
        mf.pack();
    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, e.toString());
        e.printStackTrace();
    }
    }//GEN-LAST:event_MenuMedicamentActionPerformed

    private void MenuFournisseurActionPerfomed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuFournisseurActionPerfomed
        // TODO add your handling code here:
        fermerFenetresExistantes(desktopPane);
        FournisseurForm ff = new FournisseurForm();
        desktopPane.add(ff);
        ff.setVisible(true);
    }//GEN-LAST:event_MenuFournisseurActionPerfomed

    private void MenuVenteActionPerfomed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuVenteActionPerfomed
        // TODO add your handling code here:
        try {
        fermerFenetresExistantes(desktopPane);
        VenteForm vf = new VenteForm();
        desktopPane.add(vf);
        vf.setVisible(true);
        vf.pack();

    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, e.toString());
        e.printStackTrace();
    }
    }//GEN-LAST:event_MenuVenteActionPerfomed

    private void RechercheByFournisseur(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RechercheByFournisseur
        // TODO add your handling code here:
        fermerFenetresExistantes(desktopPane);
        MedicamentByFournisseur mbf = new MedicamentByFournisseur();
        desktopPane.add(mbf);
        mbf.setVisible(true);
    }//GEN-LAST:event_RechercheByFournisseur

    private void RechercheByDate(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RechercheByDate
        // TODO add your handling code here:
        fermerFenetresExistantes(desktopPane);
        VenteByDate vbd = new VenteByDate();
        desktopPane.add(vbd);
        vbd.setVisible(true);
    }//GEN-LAST:event_RechercheByDate

    private void RechercheByFamille(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RechercheByFamille
        // TODO add your handling code here:
        fermerFenetresExistantes(desktopPane);
        MedicamentByFamille mbff = new MedicamentByFamille();
        desktopPane.add(mbff);
        mbff.setVisible(true);
    }//GEN-LAST:event_RechercheByFamille

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    
    MedicamentService ms = new MedicamentService();

    List<Medicament> low = ms.stockFaible();

    if (low != null) {
        for (Medicament m : low) {
            dataset.addValue(m.getStock(), "Stock", m.getNom()); 
        }
    }

    JFreeChart barChart = ChartFactory.createBarChart(
            "Médicaments sous seuil",   
            "Médicament",               
            "Stock restant",            
            dataset,
            PlotOrientation.VERTICAL,
            true, true, false
    );

    // Panel
    ChartPanel chartPanel = new ChartPanel(barChart);
    chartPanel.setPreferredSize(new java.awt.Dimension(800, 450));

    // Fenêtre
    JFrame frame = new JFrame("Statistique - Stock sous seuil");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.getContentPane().add(chartPanel);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    VenteService vs = new VenteService();
    Map<String, Integer> stats = vs.statsVentesParFamille();

    if (stats != null) {
        for (Map.Entry<String, Integer> e : stats.entrySet()) {
            dataset.addValue(e.getValue(), "Ventes", e.getKey());
        }
    }

    JFreeChart barChart = ChartFactory.createBarChart(
            "Ventes par famille",
            "Famille",
            "Quantité vendue",
            dataset,
            PlotOrientation.VERTICAL,
            true, true, false
    );

    ChartPanel chartPanel = new ChartPanel(barChart);
    chartPanel.setPreferredSize(new java.awt.Dimension(800, 450));

    JFrame frame = new JFrame("Statistique - Ventes par famille");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.getContentPane().add(chartPanel);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    }//GEN-LAST:event_jMenuItem8ActionPerformed
  public javax.swing.JDesktopPane getDesktopPane() {
    return desktopPane;
}
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    // End of variables declaration//GEN-END:variables
}
