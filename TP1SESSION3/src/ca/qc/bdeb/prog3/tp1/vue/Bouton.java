/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog3.tp1.vue;

import javax.swing.JButton;

/**
 *Classe du bouton avec position
 * @author 1742177
 */
public class Bouton extends JButton {

    private int position;

    /**
     *Constructeur
     * @param position position du bouton
     */
    public Bouton(int position) {
        this.position = position;
        setText("" + (position + 1));
    }

    /**
     *Retourne la position du bouton
     * @return position position du bouton
     */
    public int getPosition() {
        return position;
    }

}
