/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog3.tp1.modele;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

/**
 * Classe contenant le fonctionnement du jeu
 *
 * @author 1742177
 */
public class Modele extends Observable {
    
    private Joueur joueur1 = new Joueur(Color.RED, 0);
    private Joueur joueur2 = new Joueur(Color.BLUE, 0);
    private Joueur tabJoueur[][][] = new Joueur[4][4][4];
    private Joueur joueurActif = joueur1;
    private int minutes = 0;
    private int secondes = 0;
    private boolean finPartie = false;
    private boolean gagnantJoueur1 = false;
    private boolean gagnantJoueur2 = false;
    
    private javax.swing.Timer timer = new javax.swing.Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            secondes++;
            if (secondes == 60) {
                minutes++;
                secondes = 0;
                
            }
            if (!finPartie) {
                majObservers();
            }
            
        }
    }
    );

    /**
     * Constructeur
     */
    public Modele() {
        timer.start();
    }

    /**
     * Apelle les observers
     */
    private void majObservers() {
        setChanged();
        notifyObservers();
    }

    /**
     * Modifie la planche de jeu selon le bouton cliqué
     *
     * @param positionXQuad position horizontale du quad contenant le bouton
     * cliqué
     * @param positionYQuad position verticale du quad contenant le bouton
     * cliqué
     * @param positionXBoutton position du bouton cliqué dans le quad
     * @see verificationFinPartie() vérifie fin de la partie
     * @see calculerPoints() calcul les points des joueurs
     */
    public void boutonCliqué(int positionXQuad, int positionYQuad, int positionXBoutton) {
        tabJoueur[positionXQuad][positionYQuad][positionXBoutton] = joueurActif;
        
        if (joueurActif.getColor() == joueur1.getColor()) {
            joueurActif = joueur2;
        } else {
            joueurActif = joueur1;
        }
        calculerPoints();
        verificationFinPartie();
        majObservers();
    }

    /**
     * retourne la planche de jeu
     *
     * @return tabJoueur
     */
    public Joueur[][][] getTabJoueur() {
        return tabJoueur;
    }

    /**
     * Réinitialise la partie
     */
    public void clear() {
        finPartie = false;
        gagnantJoueur1 = false;
        gagnantJoueur2 = false;
        this.joueurActif = joueur1;
        this.joueur1.setPoint(0);
        this.joueur2.setPoint(0);
        this.joueur1.setColor(Color.RED);
        this.joueur2.setColor(Color.BLUE);
        minutes = 0;
        secondes = 0;
        
        for (int i = 0; i < tabJoueur.length; i++) {
            for (int j = 0; j < tabJoueur[i].length; j++) {
                for (int h = 0; h < tabJoueur[i][j].length; h++) {
                    tabJoueur[i][j][h] = null;
                }
            }
        }
        
        majObservers();
    }

    /**
     * Change couleur de Joueur 1
     *
     * @param color couleur du joueur 1
     */
    public void changerCouleurJoueur1(Color color) {
        joueur1.setColor(color);
        majObservers();
    }

    /**
     * Change couleur joueur 2
     *
     * @param color couleur du joueur 2
     */
    public void changerCouleurJoueur2(Color color) {
        joueur2.setColor(color);
        majObservers();
    }

    /**
     * Retourne le joueur 1
     *
     * @return Joueur1 joueur 1
     */
    public Joueur getJoueur1() {
        return joueur1;
    }

    /**
     * Retourne le joueur 2
     *
     * @return Joueur2 joueur2
     */
    public Joueur getJoueur2() {
        return joueur2;
    }

    /**
     * Retourne le joueur actif
     *
     * @return JoueurActif joueur Actif
     */
    public Joueur getJoueurActif() {
        return this.joueurActif;
    }

    /**
     * Calcul l'ensemble des points
     *
     * @see calculerPoints10()
     * @see calculerPoints20()
     * @see calculerPoints15()
     */
    private void calculerPoints() {
        calculerPoints10();
        calculerPoints15();
        calculerPoints20();
        majObservers();
    }

    /**
     * Calcul les points de 10
     */
    private void calculerPoints10() {
        boolean validePointsJoueur1;
        boolean validePointsJoueur2;
        int pointsJoueur1 = 0;
        int pointsJoueur2 = 0;
        //verticale
        for (int k = 0; k < tabJoueur[0][0].length; k++) {
            
            for (int i = 0; i < tabJoueur.length; i++) {
                validePointsJoueur2 = true;
                validePointsJoueur1 = true;
                for (int j = 0; j < tabJoueur[i].length - 1; j++) {
                    try {
                        if (tabJoueur[i][j][k].getColor() == (tabJoueur[i][j + 1][k].getColor())) {
                            if (tabJoueur[i][j][k] == joueur1 && validePointsJoueur1) {
                                validePointsJoueur1 = true;
                                validePointsJoueur2 = false;
                            } else if (tabJoueur[i][j][k] == joueur2 && validePointsJoueur2) {
                                validePointsJoueur2 = true;
                                validePointsJoueur1 = false;
                                
                            }
                            
                        } else {
                            validePointsJoueur1 = false;
                            validePointsJoueur2 = false;
                        }
                    } catch (NullPointerException e) {
                        validePointsJoueur1 = false;
                        validePointsJoueur2 = false;
                    }
                    
                }
                if (validePointsJoueur1) {
                    pointsJoueur1 = pointsJoueur1 + 10;
                }
                if (validePointsJoueur2) {
                    pointsJoueur2 = pointsJoueur2 + 10;
                }
            }
            
        }

        //horizontale
        for (int k = 0; k < tabJoueur[0][0].length; k++) {
            
            for (int j = 0; j < tabJoueur[0].length; j++) {
                validePointsJoueur1 = true;
                validePointsJoueur2 = true;
                for (int i = 0; i < tabJoueur.length - 1; i++) {
                    try {
                        if (tabJoueur[i][j][k].getColor() == tabJoueur[i + 1][j][k].getColor()) {
                            if (tabJoueur[i][j][k] == joueur1 && validePointsJoueur1) {
                                validePointsJoueur1 = true;
                                validePointsJoueur2 = false;
                            } else if (tabJoueur[i][j][k] == joueur2 && validePointsJoueur2) {
                                validePointsJoueur2 = true;
                                validePointsJoueur1 = false;
                            }
                            
                        } else {
                            validePointsJoueur1 = false;
                            validePointsJoueur2 = false;
                        }
                    } catch (NullPointerException e) {
                        validePointsJoueur1 = false;
                        validePointsJoueur2 = false;
                        
                    }
                    
                }
                if (validePointsJoueur1) {
                    pointsJoueur1 = pointsJoueur1 + 10;
                }
                if (validePointsJoueur2) {
                    pointsJoueur2 = pointsJoueur2 + 10;
                }
            }
            
        }
        //diagonale 1

        for (int k = 0; k < tabJoueur[0][0].length; k++) {
            validePointsJoueur1 = true;
            validePointsJoueur2 = true;
            int j = 0;
            for (int i = 0; i < tabJoueur.length - 1; i++) {
                try {
                    if (tabJoueur[i][j][k].getColor() == tabJoueur[i + 1][j + 1][k].getColor()) {
                        if (tabJoueur[i][j][k] == joueur1 && validePointsJoueur1) {
                            validePointsJoueur1 = true;
                            validePointsJoueur2 = false;
                        } else if (tabJoueur[i][j][k] == joueur2 && validePointsJoueur2) {
                            validePointsJoueur2 = true;
                            validePointsJoueur1 = false;
                        }
                    } else {
                        validePointsJoueur1 = false;
                        validePointsJoueur2 = false;
                        
                    }
                } catch (NullPointerException e) {
                    validePointsJoueur1 = false;
                    validePointsJoueur2 = false;
                }
                
                j++;
            }
            if (validePointsJoueur1) {
                pointsJoueur1 = pointsJoueur1 + 10;
            }
            if (validePointsJoueur2) {
                pointsJoueur2 = pointsJoueur2 + 10;
            }
            
        }
        //diagonale 2

        for (int k = 0; k < tabJoueur[0][0].length; k++) {
            validePointsJoueur1 = true;
            validePointsJoueur2 = true;
            int j = 3;
            for (int i = 0; i < tabJoueur[i].length - 1; i++) {
                try {
                    if (tabJoueur[i][j][k].getColor() == (tabJoueur[i + 1][j - 1][k].getColor())) {
                        if (tabJoueur[i][j][k] == joueur1 && validePointsJoueur1) {
                            validePointsJoueur1 = true;
                            validePointsJoueur2 = false;
                        } else if (tabJoueur[i][j][k] == joueur2 && validePointsJoueur2) {
                            validePointsJoueur2 = true;
                            validePointsJoueur1 = false;
                        }
                    } else {
                        validePointsJoueur1 = false;
                        validePointsJoueur2 = false;
                        
                    }
                } catch (NullPointerException e) {
                    validePointsJoueur1 = false;
                    validePointsJoueur2 = false;
                    
                }
                
                j--;
            }
            if (validePointsJoueur1) {
                pointsJoueur1 = pointsJoueur1 + 10;
            }
            if (validePointsJoueur2) {
                pointsJoueur2 = pointsJoueur2 + 10;
            }
            
        }

        //4 coins
        for (int k = 0; k < tabJoueur[0][0].length; k++) {
            try {
                if (tabJoueur[0][0][k].getColor() == tabJoueur[3][0][k].getColor() && tabJoueur[0][0][k].getColor() == tabJoueur[0][3][k].getColor() && tabJoueur[0][0][k].getColor() == tabJoueur[3][3][k].getColor()) {
                    if (tabJoueur[0][0][k] == joueur1) {
                        pointsJoueur1 = pointsJoueur1 + 10;
                    } else if (tabJoueur[0][0][k] == joueur2) {
                        pointsJoueur2 = pointsJoueur2 + 10;
                    }
                }
            } catch (NullPointerException e) {
            }
            
        }
        
        joueur1.setPoint(pointsJoueur1);
        joueur2.setPoint(pointsJoueur2);
    }

    /**
     * Calcul les points de 20
     */
    private void calculerPoints20() {
        boolean validePointsJoueur1;
        boolean validePointsJoueur2;
        int pointsJoueur1 = 0;
        int pointsJoueur2 = 0;
        
        for (int i = 0; i < tabJoueur.length; i++) {
            for (int j = 0; j < tabJoueur[i].length; j++) {
                validePointsJoueur1 = true;
                validePointsJoueur2 = true;
                for (int k = 0; k < tabJoueur[i][j].length - 1; k++) {
                    try {
                        if (tabJoueur[i][j][k].getColor() == (tabJoueur[i][j][k + 1].getColor())) {
                            if (tabJoueur[i][j][k] == joueur1 && validePointsJoueur1) {
                                validePointsJoueur1 = true;
                                validePointsJoueur2 = false;
                            } else if (tabJoueur[i][j][k] == joueur2 && validePointsJoueur2) {
                                validePointsJoueur2 = true;
                                validePointsJoueur1 = false;
                                
                            }
                        } else {
                            validePointsJoueur1 = false;
                            validePointsJoueur2 = false;
                        }
                    } catch (NullPointerException e) {
                        validePointsJoueur1 = false;
                        validePointsJoueur2 = false;
                    }
                }
                if (validePointsJoueur1) {
                    pointsJoueur1 = pointsJoueur1 + 20;
                }
                if (validePointsJoueur2) {
                    pointsJoueur2 = pointsJoueur2 + 20;
                }
                
            }
        }
        joueur1.setPoint(joueur1.getPoint() + pointsJoueur1);
        joueur2.setPoint(joueur2.getPoint() + pointsJoueur2);
    }

    /**
     * Calcul les points de 15
     */
    private void calculerPoints15() {
        boolean validePointsJoueur1;
        boolean validePointsJoueur2;
        int pointsJoueur1 = 0;
        int pointsJoueur2 = 0;

        //verticale 1
        for (int i = 0; i < tabJoueur.length; i++) {
            int k = 0;
            validePointsJoueur2 = true;
            validePointsJoueur1 = true;
            for (int j = 0; j < tabJoueur[i].length - 1; j++) {
                try {
                    if (tabJoueur[i][j][k].getColor() == (tabJoueur[i][j + 1][k + 1].getColor())) {
                        if (tabJoueur[i][j][k] == joueur1 && validePointsJoueur1) {
                            validePointsJoueur1 = true;
                            validePointsJoueur2 = false;
                        } else if (tabJoueur[i][j][k] == joueur2 && validePointsJoueur2) {
                            validePointsJoueur2 = true;
                            validePointsJoueur1 = false;
                            
                        }
                        
                    } else {
                        validePointsJoueur1 = false;
                        validePointsJoueur2 = false;
                    }
                } catch (NullPointerException e) {
                    validePointsJoueur1 = false;
                    validePointsJoueur2 = false;
                }
                k++;
                
            }
            if (validePointsJoueur1) {
                pointsJoueur1 = pointsJoueur1 + 15;
            }
            if (validePointsJoueur2) {
                pointsJoueur2 = pointsJoueur2 + 15;
            }
        }
        //verticale 2
        for (int i = 0; i < tabJoueur.length; i++) {
            int k = 3;
            validePointsJoueur2 = true;
            validePointsJoueur1 = true;
            for (int j = 0; j < tabJoueur[i].length - 1; j++) {
                try {
                    if (tabJoueur[i][j][k].getColor() == (tabJoueur[i][j + 1][k - 1].getColor())) {
                        if (tabJoueur[i][j][k] == joueur1 && validePointsJoueur1) {
                            validePointsJoueur1 = true;
                            validePointsJoueur2 = false;
                        } else if (tabJoueur[i][j][k] == joueur2 && validePointsJoueur2) {
                            validePointsJoueur2 = true;
                            validePointsJoueur1 = false;
                            
                        }
                        
                    } else {
                        validePointsJoueur1 = false;
                        validePointsJoueur2 = false;
                    }
                } catch (NullPointerException e) {
                    validePointsJoueur1 = false;
                    validePointsJoueur2 = false;
                }
                k--;
                
            }
            if (validePointsJoueur1) {
                pointsJoueur1 = pointsJoueur1 + 15;
            }
            if (validePointsJoueur2) {
                pointsJoueur2 = pointsJoueur2 + 15;
            }
        }
        //horizontale 1
        for (int j = 0; j < tabJoueur[0].length; j++) {
            int k = 0;
            validePointsJoueur1 = true;
            validePointsJoueur2 = true;
            for (int i = 0; i < tabJoueur.length - 1; i++) {
                try {
                    if (tabJoueur[i][j][k].getColor() == tabJoueur[i + 1][j][k + 1].getColor()) {
                        if (tabJoueur[i][j][k] == joueur1 && validePointsJoueur1) {
                            validePointsJoueur1 = true;
                            validePointsJoueur2 = false;
                        } else if (tabJoueur[i][j][k] == joueur2 && validePointsJoueur2) {
                            validePointsJoueur2 = true;
                            validePointsJoueur1 = false;
                        }
                        
                    } else {
                        validePointsJoueur1 = false;
                        validePointsJoueur2 = false;
                    }
                } catch (NullPointerException e) {
                    validePointsJoueur1 = false;
                    validePointsJoueur2 = false;
                    
                }
                k++;
                
            }
            if (validePointsJoueur1) {
                pointsJoueur1 = pointsJoueur1 + 15;
            }
            if (validePointsJoueur2) {
                pointsJoueur2 = pointsJoueur2 + 15;
            }
        }
        //horizontale 2
        for (int j = 0; j < tabJoueur[0].length; j++) {
            validePointsJoueur1 = true;
            validePointsJoueur2 = true;
            int k = 3;
            for (int i = 0; i < tabJoueur.length - 1; i++) {
                try {
                    if (tabJoueur[i][j][k].getColor() == tabJoueur[i + 1][j][k - 1].getColor()) {
                        if (tabJoueur[i][j][k] == joueur1 && validePointsJoueur1) {
                            validePointsJoueur1 = true;
                            validePointsJoueur2 = false;
                        } else if (tabJoueur[i][j][k] == joueur2 && validePointsJoueur2) {
                            validePointsJoueur2 = true;
                            validePointsJoueur1 = false;
                        }
                        
                    } else {
                        validePointsJoueur1 = false;
                        validePointsJoueur2 = false;
                    }
                } catch (NullPointerException e) {
                    validePointsJoueur1 = false;
                    validePointsJoueur2 = false;
                    
                }
                k--;
            }
            if (validePointsJoueur1) {
                pointsJoueur1 = pointsJoueur1 + 15;
            }
            if (validePointsJoueur2) {
                pointsJoueur2 = pointsJoueur2 + 15;
            }
        }
        //diagonale 1
        validePointsJoueur1 = true;
        validePointsJoueur2 = true;
        int j = 0;
        int k = 0;
        for (int i = 0; i < tabJoueur.length - 1; i++) {
            try {
                if (tabJoueur[i][j][k].getColor() == tabJoueur[i + 1][j + 1][k + 1].getColor()) {
                    if (tabJoueur[i][j][k] == joueur1 && validePointsJoueur1) {
                        validePointsJoueur1 = true;
                        validePointsJoueur2 = false;
                    } else if (tabJoueur[i][j][k] == joueur2 && validePointsJoueur2) {
                        validePointsJoueur2 = true;
                        validePointsJoueur1 = false;
                    }
                } else {
                    validePointsJoueur1 = false;
                    validePointsJoueur2 = false;
                    
                }
            } catch (NullPointerException e) {
                validePointsJoueur1 = false;
                validePointsJoueur2 = false;
            }
            k++;
            j++;
        }
        if (validePointsJoueur1) {
            pointsJoueur1 = pointsJoueur1 + 15;
        }
        if (validePointsJoueur2) {
            pointsJoueur2 = pointsJoueur2 + 15;
        }
        //diagonale 2
        validePointsJoueur1 = true;
        validePointsJoueur2 = true;
        j = 0;
        k = 3;
        for (int i = 0; i < tabJoueur.length - 1; i++) {
            try {
                if (tabJoueur[i][j][k].getColor() == tabJoueur[i + 1][j + 1][k - 1].getColor()) {
                    if (tabJoueur[i][j][k] == joueur1 && validePointsJoueur1) {
                        validePointsJoueur1 = true;
                        validePointsJoueur2 = false;
                    } else if (tabJoueur[i][j][k] == joueur2 && validePointsJoueur2) {
                        validePointsJoueur2 = true;
                        validePointsJoueur1 = false;
                    }
                } else {
                    validePointsJoueur1 = false;
                    validePointsJoueur2 = false;
                    
                }
            } catch (NullPointerException e) {
                validePointsJoueur1 = false;
                validePointsJoueur2 = false;
            }
            k--;
            j++;
        }
        if (validePointsJoueur1) {
            pointsJoueur1 = pointsJoueur1 + 15;
        }
        if (validePointsJoueur2) {
            pointsJoueur2 = pointsJoueur2 + 15;
        }
        
        validePointsJoueur1 = true;
        validePointsJoueur2 = true;
        j = 3;
        k = 0;
        for (int i = 0; i < tabJoueur[i].length - 1; i++) {
            try {
                if (tabJoueur[i][j][k].getColor() == (tabJoueur[i + 1][j - 1][k + 1].getColor())) {
                    if (tabJoueur[i][j][k] == joueur1 && validePointsJoueur1) {
                        validePointsJoueur1 = true;
                        validePointsJoueur2 = false;
                    } else if (tabJoueur[i][j][k] == joueur2 && validePointsJoueur2) {
                        validePointsJoueur2 = true;
                        validePointsJoueur1 = false;
                    }
                } else {
                    validePointsJoueur1 = false;
                    validePointsJoueur2 = false;
                    
                }
            } catch (NullPointerException e) {
                validePointsJoueur1 = false;
                validePointsJoueur2 = false;
                
            }
            k++;
            j--;
        }
        if (validePointsJoueur1) {
            pointsJoueur1 = pointsJoueur1 + 15;
        }
        if (validePointsJoueur2) {
            pointsJoueur2 = pointsJoueur2 + 15;
        }
        
        validePointsJoueur1 = true;
        validePointsJoueur2 = true;
        k = 3;
        j = 3;
        for (int i = 0; i < tabJoueur[i].length - 1; i++) {
            try {
                if (tabJoueur[i][j][k].getColor() == (tabJoueur[i + 1][j - 1][k - 1].getColor())) {
                    if (tabJoueur[i][j][k] == joueur1 && validePointsJoueur1) {
                        validePointsJoueur1 = true;
                        validePointsJoueur2 = false;
                    } else if (tabJoueur[i][j][k] == joueur2 && validePointsJoueur2) {
                        validePointsJoueur2 = true;
                        validePointsJoueur1 = false;
                    }
                } else {
                    validePointsJoueur1 = false;
                    validePointsJoueur2 = false;
                    
                }
            } catch (NullPointerException e) {
                validePointsJoueur1 = false;
                validePointsJoueur2 = false;
                
            }
            k--;
            j--;
        }
        if (validePointsJoueur1) {
            pointsJoueur1 = pointsJoueur1 + 15;
        }
        if (validePointsJoueur2) {
            pointsJoueur2 = pointsJoueur2 + 15;
        }
// 4 coins 
        try {
            //1 en haut a gauche
            if (tabJoueur[0][0][0].getColor() == tabJoueur[3][0][1].getColor() && tabJoueur[0][0][0].getColor() == tabJoueur[0][3][3].getColor() && tabJoueur[0][0][0].getColor() == tabJoueur[3][3][2].getColor()) {
                if (tabJoueur[0][0][0] == joueur1) {
                    pointsJoueur1 = pointsJoueur1 + 15;
                } else if (tabJoueur[0][0][0] == joueur2) {
                    pointsJoueur2 = pointsJoueur2 + 15;
                }
            }
        } catch (NullPointerException e) {
        }
        try {
            // 1 en haut a droite
            if (tabJoueur[0][0][3].getColor() == tabJoueur[3][0][0].getColor() && tabJoueur[0][0][3].getColor() == tabJoueur[0][3][2].getColor() && tabJoueur[0][0][3].getColor() == tabJoueur[3][3][1].getColor()) {
                if (tabJoueur[0][0][3] == joueur1) {
                    pointsJoueur1 = pointsJoueur1 + 15;
                } else if (tabJoueur[0][0][3] == joueur2) {
                    pointsJoueur2 = pointsJoueur2 + 15;
                }
            }
        } catch (NullPointerException e) {
        }
        
        try {
            // 1 en bas a droite
            if (tabJoueur[0][0][2].getColor() == tabJoueur[3][0][3].getColor() && tabJoueur[0][0][2].getColor() == tabJoueur[0][3][1].getColor() && tabJoueur[0][0][2].getColor() == tabJoueur[3][3][0].getColor()) {
                if (tabJoueur[0][0][2] == joueur1) {
                    pointsJoueur1 = pointsJoueur1 + 15;
                } else if (tabJoueur[0][0][2] == joueur2) {
                    pointsJoueur2 = pointsJoueur2 + 15;
                }
            }
        } catch (NullPointerException e) {
        }
        try {
            //en bas a gauche
            if (tabJoueur[0][0][1].getColor() == tabJoueur[3][0][2].getColor() && tabJoueur[0][0][1].getColor() == tabJoueur[0][3][0].getColor() && tabJoueur[0][0][1].getColor() == tabJoueur[3][3][3].getColor()) {
                if (tabJoueur[0][0][1] == joueur1) {
                    pointsJoueur1 = pointsJoueur1 + 15;
                } else if (tabJoueur[0][0][1] == joueur2) {
                    pointsJoueur2 = pointsJoueur2 + 15;
                }
            }
        } catch (NullPointerException e) {
        }
        
        joueur1.setPoint(joueur1.getPoint() + pointsJoueur1);
        joueur2.setPoint(joueur2.getPoint() + pointsJoueur2);
    }

    /**
     * Retourne les minutes
     *
     * @return minutes minutes du timer
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * Retourne les secondes
     *
     * @return secondes secondes du timer
     */
    public int getSecondes() {
        return secondes;
    }

    /**
     * Vérifie que le jeu n'est pas terminé
     */
    private void verificationFinPartie() {
        boolean continuer = true;
        for (int i = 0; i < tabJoueur.length && continuer; i++) {
            for (int j = 0; j < tabJoueur[i].length && continuer; j++) {
                for (int k = 0; k < tabJoueur[i][j].length && continuer; k++) {
                    if (tabJoueur[i][j][k] != null) {
                        finPartie = true;
                    } else {
                        finPartie = false;
                        continuer = false;
                    }
                }
            }
        }
        
        if (finPartie) {
            if (joueur1.getPoint() > joueur2.getPoint()) {
                gagnantJoueur1 = true;
            } else if (joueur1.getPoint() < joueur2.getPoint()) {
                gagnantJoueur2 = true;
            }
        }
    }

    /**
     * Retourne la valeur du joueurGagnant 1
     *
     * @return gagnantJoueur1 si le joueur1 a gagné
     */
    public boolean getGagnantJoueur1() {
        return this.gagnantJoueur1;
    }

    /**
     * Retourne valeur de gagnant joueur 2
     *
     * @return gagnantJoueur2 si le joueur2 a gangé
     */
    public boolean getGagnantJoueur2() {
        return this.gagnantJoueur2;
    }

    /**
     * Retourne la valeur de la fin de partie
     *
     * @return finPartie si la partie a fini
     */
    public boolean getFinPartie() {
        return this.finPartie;
    }
}
