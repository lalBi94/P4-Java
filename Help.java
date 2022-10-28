import BPackage.BFrame;
import BPackage.BImage;
import BPackage.BLayout;

import javax.swing.JLabel;
import java.io.IOException;
import java.util.ArrayList;

public class Help extends BFrame {
    private final ArrayList<JLabel> task;
    private final ArrayList<JLabel> taskImg;

    /**
     * @throws IOException Si l'image n'est pas trouve
     * */
    public Help() throws IOException {
        super(
                "Aide - Puissance 4",
                1,
                1,
                900,
                900,
                2 /* Pour celui qui vas retravailler dessus,
                    3 = Ferme le programme
                    2 = Fermer la fenetre courrante et non toute
                    */
        );

        this.task = new ArrayList<>();
        this.task.add(new JLabel(
                "1. Choisir une case blanche dans la colonne ou vous voulez y glisser un pion")
        );
        this.task.add(new JLabel(
                "2. Le but est d'alligner soit en vertical, soit en horizontal, soit en diagonale 4 pions pour gagner la partie")
        );

        this.taskImg = new ArrayList<>();
        this.taskImg.add(new BImage("assets/tuto1.png").getImage());
        this.taskImg.add(new BImage("assets/tuto2.png").getImage());

        Display();
    }

    /**
     * Pour afficher le menu d'aide
     * */
     public void Display() {
         BLayout settings = new BLayout();
         settings.setPositionX(0);
         settings.setPositionY(4);

         settings.setPositionY(0);
         this.add(this.task.get(0), settings);

         settings.setPositionY(1);
         this.add(this.taskImg.get(0), settings);

         settings.setPositionY(2);
         this.add(new JLabel(" "), settings);

         settings.setPositionY(3);
         this.add(this.task.get(1), settings);

         settings.setPositionY(4);
         this.add(this.taskImg.get(1), settings);

         this.openBFrame();
         this.refreshBFrame();
     }
}
