/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog3.tp1.vue;

import ca.qc.bdeb.prog3.tp1.modele.Modele;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 *Classe de la fenêtre options
 * @author 1742177
 */
public class FenetreOptions extends JFrame {
/**
 * 
 */
    private String[] choixCouleur = {"Bleu", "Rouge", "Jaune", "Orange"};
    private JComboBox cboChoixCouleur1 = new JComboBox(choixCouleur);
    private JComboBox cboChoixCouleur2 = new JComboBox(choixCouleur);
    private JLabel lblJoueur1 = new JLabel("Joueur 1 : ");
    private JLabel lblJoueur2 = new JLabel("Joueur 2 : ");
    private JPanel pnlJoueur = new JPanel(new GridLayout(0, 4));

    private JButton btnConfirmer = new JButton("Confirmer");
    private Modele modele;

    /**
     *
     * @param modele Accès au modèle
     */
    public FenetreOptions(Modele modele) {
        this.setTitle("Options");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.modele = modele;
        creerInterface();
        creerEvenement();
        this.setVisible(true);
    }
/**
 * Crée l'interface graphique
 */
    private void creerInterface() {
        pnlJoueur.add(lblJoueur1);
        pnlJoueur.add(cboChoixCouleur1);
        pnlJoueur.add(lblJoueur2);
        pnlJoueur.add(cboChoixCouleur2);
        this.add(pnlJoueur, BorderLayout.CENTER);
        this.add(btnConfirmer, BorderLayout.SOUTH);
    }
/**
 * Crée les évènements
 */
    private void creerEvenement() {
        this.btnConfirmer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (((String) cboChoixCouleur1.getSelectedItem()).equals(((String) cboChoixCouleur2.getSelectedItem()))) {
                    JOptionPane.showMessageDialog(FenetreOptions.this, "Choisissez deux couleurs différentes pour chaque joueur");
                } else {
                    switch ((String) cboChoixCouleur1.getSelectedItem()) {
                        case "Bleu":
                            modele.changerCouleurJoueur1(Color.blue);
                            break;
                        case "Rouge":
                            modele.changerCouleurJoueur1(Color.red);
                            break;
                        case "Jaune":
                            modele.changerCouleurJoueur1(Color.yellow);
                            break;
                        case "Orange":
                            modele.changerCouleurJoueur1(Color.orange);
                            break;
                    }
                    switch ((String) cboChoixCouleur2.getSelectedItem()) {
                        case "Bleu":
                            modele.changerCouleurJoueur2(Color.blue);
                            break;
                        case "Rouge":
                            modele.changerCouleurJoueur2(Color.red);
                            break;
                        case "Jaune":
                            modele.changerCouleurJoueur2(Color.yellow);
                            break;
                        case "Orange":
                            modele.changerCouleurJoueur2(Color.orange);
                            break;

                    }
                    FenetreOptions.super.dispose(); 
                }

            }
        });
    }

}
