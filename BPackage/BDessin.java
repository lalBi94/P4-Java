package BPackage;

/* [BPackage.BDessin]
 *  Desc: To create Operationnal Image with JComponent fast :)
 *  GitHub: https://github.com/lalBi94
 *  Created by: Bilal Boudjemline
 *  28/09/2022 at 20:35
 * */

import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Color;

/**
 * <p>Pour faire des beaux dessins.</p>
 *
 * @author <a href="https://github.com/lalBi94">Bilal Boudjemline</a>
 */
public class BDessin extends JComponent {
    private Color color;
    private final String form;
    private final int w;
    private final int h;

    /**
     * Avec la forme, la couleur, la longeur et la hauteur.
     *
     * @param form  La figure a dessiner (Circle et rectangle plein pour l'instant).s
     * @param color La couleur.
     * @param w     La longeur, x ce que tu veux.
     * @param h     La Hauteur, Largeur ce que tu veux.
     */
    public BDessin(String form, Color color, int w, int h) {
        this.color = color;
        this.form = form;
        this.w = w;
        this.h = h;
    }

    /**
     * Recuperer la couleur d'un objet.
     *
     * @return La couleur de l'objet.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Attribuer une couleur a un objet.
     *
     * @param color La couleur.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    protected void paintComponent(Graphics chef) {
        Graphics mainDuChef = chef.create();
        mainDuChef.setColor(this.color);

        if (this.form == "rectangle") {
            mainDuChef.fillRect(1, 1, w, h);
        } else if (this.form == "circle") {
            mainDuChef.fillOval(1, 1, w, h);
        }
    }
}