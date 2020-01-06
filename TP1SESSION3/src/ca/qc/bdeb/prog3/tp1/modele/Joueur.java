/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog3.tp1.modele;

import java.awt.Color;

/**
 * Classe des joueurs participants au jeu
 * @author 1742177
 */
public class Joueur {

    private Color color;
    private int point;
   
    /**
     *Constructeur
     * @param color couleur associé au joueur
     * @param point points associés au joueur
     */
    public Joueur(Color color, int point) {
        this.color = color;
        this.point = point;
    }

    /**
     *Retourne la couleur du joueur
     * @return color couleur du joueur
     */
    public Color getColor() {
        return color;
    }

    /**
     *Change la valeur des points du joueur
     * @param point point du joueur
     */
    public void setPoint(int point) {
        this.point = point;
    }

    /**
     *Change la couleur du joueur
     * @param color couleur du joueur
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     *Retourne valeur des points
     * @return point points du joueur
     */
    public int getPoint() {
        return point;
    }

    

}
