/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog3.tp1.vue;

import ca.qc.bdeb.prog3.tp1.modele.Modele;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

/**
 * Classe des 4 bouttons regroupés
 * @author 1742177
 */
public class Quad extends JPanel implements Observer {

    private ArrayList<Bouton> tabBouton = new ArrayList();
    private int positionX;
    private int positionY;
    private Modele modele;

    /**
     *Constructeur
     * @param positionX position horizontale du quad
     * @param positionY position verticale du quad
     * @param modele accès au modèle
     */
    public Quad(int positionX, int positionY, Modele modele) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.setLayout(new GridLayout(2, 2));
        this.modele = modele;
        modele.addObserver(this);
        creerInterface();
    }
/**
 * Crée les boutons
 */
    private void creerBtn() {
        for (int i = 0; i < 4; i++) {

            Bouton bouton = new Bouton(i);

            if (i != 0) {
                bouton.setEnabled(false);
            }
            bouton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Bouton btn = (Bouton) e.getSource();
                    if (bouton.isEnabled()) {
                        modele.boutonCliqué(positionX, positionY, btn.getPosition());
                       
                    }

                }
            });
            tabBouton.add(bouton);
        }

    }
/**
 * Crée l'affichage graphique
 */
    private void creerInterface() {
        creerBtn();
        this.add(tabBouton.get(1));
        this.add(tabBouton.get(2));
        this.add(tabBouton.get(0));
        this.add(tabBouton.get(3));
        this.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
    }
 /**
  * Met à jour le quad
  * @param o
  * @param arg 
  */
    @Override
    public void update(Observable o, Object arg) {
        for (int i = 0; i < tabBouton.size(); i++) {
            if (modele.getTabJoueur()[positionX][positionY][i] != null) {
                tabBouton.get(i).setBackground(modele.getTabJoueur()[positionX][positionY][i].getColor());
                tabBouton.get(i).setEnabled(false);
                try {
                    tabBouton.get(i + 1).setEnabled(true);
                } catch (IndexOutOfBoundsException e) {

                }
            } else {
                if (i == 0) {
                    tabBouton.get(i).setEnabled(true);
                    tabBouton.get(i).setBackground(null);
                } else if (modele.getTabJoueur()[positionX][positionY][i - 1] == null) {
                    tabBouton.get(i).setEnabled(false);
                    tabBouton.get(i).setBackground(null);

                }
            }

        }

    }

}
