package BPackage;

/* [BPackage.BImage]
 *  Desc: To create Operationnal JLabel Image fast :)
 *  GitHub: https://github.com/lalBi94
 *  Created by: Bilal Boudjemline
 *  28/09/2022 at 20:35
 * */

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

/**
 * <p>Methodes pour creer des images sous JLabel</p>
 *
 * @author <a href="https://github.com/lalBi94">Bilal Boudjemline</a>
 */

public class BImage extends JLabel {
    public JLabel image;

    /**
     * @param path Chemin vers l'image
     * @throws IOException Erreur du chargement / location de l'image
     * */
    public BImage(String path) throws IOException {
        this.image = new JLabel(new ImageIcon(ImageIO.read(new File(path))));
    }

    /**
     * @param path Chemin vers l'image
     * @param size_x Rogner sa taille en longeur
     * @param size_y Roger sa taille en largeur
     * @throws IOException Erreur du chargement / location de l'image
     * */
    public BImage(String path, int size_x, int size_y) throws IOException {
        this.image = new JLabel(new ImageIcon(ImageIO.read(new File(path))));
        this.image.setPreferredSize(new Dimension(size_x, size_y));
    }

    public JLabel getImage() {
        return this.image;
    }

    public Dimension getImageSize() {
        return this.image.getSize();
    }
}
