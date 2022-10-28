package BPackage;

/* [BPackage.BImage]
 *  Desc: To create Operationnal JLabel Image fast :)
 *  GitHub: https://github.com/lalBi94
 *  Created by: Bilal Boudjemline
 *  28/09/2022 at 20:35
 * */

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;

/**
 * <p>Pour faire des inputs.</p>
 *
 * @author <a href="https://github.com/lalBi94">Bilal Boudjemline</a>
 */

public class BInput extends JTextField {
    public int width = 100;
    public int height = 50;
    public String inner;
    public Color color = Color.BLACK;

    /**
     * @param in Mettre un text a l'interieur du fils
     */
    public BInput(String in) {
        this.inner = in;
        this.setText(in);
    }

    /**
     * @param w La longueur.
     * @param h La hauteur, largeur.
     */
    public BInput(int w, int h) {
        this.width = w;
        this.height = h;
        this.setPreferredSize(new Dimension(w, h));
    }

    /**
     * @param w La longueur.
     * @param h La hauteur, largeur.
     * @param c La couleur.
     */
    public BInput(int w, int h, Color c) {
        this.width = w;
        this.height = h;
        this.color = c;
        this.setPreferredSize(new Dimension(w, h));
        this.setBackground(this.color);
    }

    /**
     * Ajouter une taille au field
     *
     * @param w La longueur.
     * @param h La hauteur, largeur.
     * */
    public void setSize(int w, int h) {
        this.width = w;
        this.height = h;
        this.setPreferredSize(new Dimension(w, h));
    }

    /**
     * Recuperer le contenu.
     *
     * @return Le contenu
     */
    public String getContent() {
        return this.getText();
    }

    @Override
    public String toString() {
        return this.width + "\n" + this.height;
    }
}
