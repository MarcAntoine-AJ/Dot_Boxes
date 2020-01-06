/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog3.tp1.vue;

import ca.qc.bdeb.prog3.tp1.modele.Modele;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import static javax.swing.JOptionPane.YES_OPTION;

/**
 * classe contenant la fenêtre principale du jeu
 *
 * @author 1742177
 */
public class Fenetre extends JFrame implements Observer {

    private Quad[][] tabQuad = new Quad[4][4];

    private JPanel pnl1 = new JPanel(new GridLayout(4, 4));
    private JPanel pnlPrincipal = new JPanel(new BorderLayout());
    private JPanel pnlPointJoueur1 = new JPanel(new GridLayout(1, 13));
    private JPanel pnlPointJoueur2 = new JPanel(new GridLayout(1, 13));

    private JLabel lblJoueur1 = new JLabel("Joueur 1 : ");
    private JLabel lblJoueur2 = new JLabel("Joueur 2 : ");
    private JLabel lblTimer = new JLabel("0:0");

    private ArrayList<JLabel> lblPointsJoueur1 = new ArrayList();
    private ArrayList<JLabel> lblPointsJoueur2 = new ArrayList();

    private JMenuBar mnuBar = new JMenuBar();

    private JMenu mnuFichier = new JMenu("Fichier");
    private JMenu mnuAide = new JMenu("Aide");

    private JMenuItem mnuNouvellePartie = new JMenuItem("Nouvelle Partie");
    private JMenuItem mnuOptions = new JMenuItem("Options...");
    private JMenuItem mnuQuitter = new JMenuItem("Quitter");
    private JMenuItem mnuAPropos = new JMenuItem("A propos");

    private Modele modele;

    /**
     * Constructeur
     * @param modele accès au modèle
     */
    public Fenetre(Modele modele) {
        this.modele = modele;
        modele.addObserver(this);
        this.setTitle("Hijara");
        this.setSize(1000, 1000);
        creerInterface();
        creerMenu();
        creerEvenementMenu();
        this.setJMenuBar(mnuBar);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(null, "Voulez vous fermer l’application?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        this.setVisible(true);
    }
/**
 * Crée les quads
 */
    private void creerQuad() {
        for (int i = 0; i < tabQuad.length; i++) {
            for (int j = 0; j < tabQuad[i].length; j++) {
                Quad quad = new Quad(j, i, modele);
                pnl1.add(quad);

            }
        }
    }
/**
 * Crée l'interface graphique
 */
    private void creerInterface() {
        creerQuad();
        creerPointage();
        creerTimer();
        pnlPrincipal.add(pnl1, BorderLayout.CENTER);
        pnlPrincipal.add(pnlPointJoueur1, BorderLayout.NORTH);
        pnlPrincipal.add(pnlPointJoueur2, BorderLayout.SOUTH);
        this.add(pnlPrincipal);

    }
/**
 * Crée le menu
 */
    private void creerMenu() {
        mnuFichier.add(mnuNouvellePartie);
        mnuFichier.add(mnuOptions);
        mnuFichier.addSeparator();
        mnuFichier.add(mnuQuitter);
        mnuAide.add(mnuAPropos);
        mnuBar.add(mnuFichier);
        mnuBar.add(mnuAide);

    }
/**
 * Crée interface de pointages
 */
    private void creerPointage() {
        pnlPointJoueur1.add(lblJoueur1);
        lblJoueur1.setOpaque(true);
        lblJoueur1.setBackground(modele.getJoueur1().getColor());
        pnlPointJoueur2.add(lblJoueur2);

        for (int i = 0; i <= 13; i++) {
            JLabel lblPoints = new JLabel("" + i * 5, JLabel.CENTER);

            lblPoints.setOpaque(true);
            if (i == 0) {
                lblPoints.setBackground(modele.getJoueur1().getColor());
            }
            lblPointsJoueur1.add(lblPoints);

            pnlPointJoueur1.add(lblPointsJoueur1.get(i));

        }
        for (int i = 0; i <= 13; i++) {
            JLabel lblPoints = new JLabel("" + i * 5, JLabel.CENTER);
            lblPoints.setOpaque(true);
            if (i == 0) {
                lblPoints.setBackground(modele.getJoueur2().getColor());
            }
            lblPointsJoueur2.add(lblPoints);

            pnlPointJoueur2.add(lblPointsJoueur2.get(i));
        }
    }

/**
 * Crée interface sur les menus
 */
    public void creerEvenementMenu() {
        mnuOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FenetreOptions fenetre2 = new FenetreOptions(modele);
            }
        });

        mnuAPropos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Fenetre.this, "Marc-Antoine Abou Jaoude \n Le 14 octobre 2018");
            }
        });

        mnuQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogMsg = 0;
                dialogMsg = JOptionPane.showConfirmDialog(Fenetre.this, "Etes-vous sur de vouloir quitter?");
                if (dialogMsg == YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        mnuNouvellePartie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modele.clear();

            }
        });

    }
/**
 * crée le label du timer
 */
    private void creerTimer() {
        this.add(lblTimer, BorderLayout.NORTH);
    }

    /**
     * Met à jour l'interface
     * @param o
     * @param arg 
     */
    @Override
    public void update(Observable o, Object arg) {
        for (JLabel l : lblPointsJoueur1) {
            l.setBackground(null);
            l.setOpaque(true);
        }
        for (JLabel l : lblPointsJoueur2) {
            l.setBackground(null);
            l.setOpaque(true);
        }

        try {
            lblPointsJoueur1.get(((modele.getJoueur1().getPoint()) / 5)).setBackground(modele.getJoueur1().getColor());

        } catch (IndexOutOfBoundsException ex) {
            lblPointsJoueur1.get(lblPointsJoueur1.size() - 1).setBackground(modele.getJoueur1().getColor());
        }

        try {
            lblPointsJoueur2.get(((modele.getJoueur2().getPoint()) / 5)).setBackground(modele.getJoueur2().getColor());

        } catch (IndexOutOfBoundsException ex) {
            lblPointsJoueur2.get(lblPointsJoueur2.size() - 1).setBackground(modele.getJoueur2().getColor());
        }

        if (modele.getJoueurActif().equals(modele.getJoueur1())) {
            lblJoueur2.setBackground(null);
            lblJoueur1.setOpaque(true);
            lblJoueur1.setBackground(modele.getJoueur1().getColor());
        } else {
            lblJoueur1.setBackground(null);
            lblJoueur2.setOpaque(true);
            lblJoueur2.setBackground(modele.getJoueur2().getColor());
        }
        if (modele.getSecondes() < 10) {
            lblTimer.setText("" + modele.getMinutes() + " : 0" + modele.getSecondes());
        } else {
            lblTimer.setText("" + modele.getMinutes() + " : " + modele.getSecondes());
        }

        if (modele.getFinPartie()) {
            if (modele.getGagnantJoueur1()) {
                JOptionPane.showMessageDialog(null, "Le joueur 1 a gagné avec : " + modele.getJoueur1().getPoint() + "\n Le joueur 2 avait : " + modele.getJoueur2().getPoint() + " points");

            } else if (modele.getGagnantJoueur2()) {

                JOptionPane.showMessageDialog(null, "Le joueur 2 a gagné avec : " + modele.getJoueur2().getPoint() + "\n Le joueur 1 avait : " + modele.getJoueur1().getPoint() + " points");
                System.out.println("1");
            } else {

                JOptionPane.showMessageDialog(null, "C'est une égalité avec : " + modele.getJoueur1().getPoint() + " points pour les deux joueurs!");
                System.out.println("1");
            }

            int confirm = JOptionPane.showOptionDialog(null, "Voulez-vous recommencer? Si non l'application va fermer", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (confirm == YES_OPTION) {
                modele.clear();
            } else {
                System.exit(0);
            }
        }

    }

}
