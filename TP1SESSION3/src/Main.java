
import ca.qc.bdeb.prog3.tp1.modele.Modele;
import ca.qc.bdeb.prog3.tp1.vue.Fenetre;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 1742177
 */
public class Main {

    /**
     * Main
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Modele modele = new Modele();
        Fenetre fenetre = new Fenetre(modele);
    }

}
