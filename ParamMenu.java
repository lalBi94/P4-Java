import BPackage.BDatabase;
import BPackage.BFrame;
import BPackage.BLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ParamMenu extends BFrame implements ActionListener {
    private final String title;
    private final ArrayList<String> options;
    private final int sizeofInputX;
    private final int sizeofInputY;
    private final BDatabase sharedObject;

    public ParamMenu() {
        super(
                "Parametres - Puissance 4",
                500,
                250,
                360,
                300,
                2 /*
                   * Pour celui qui vas retravailler dessus,
                   * 3 = Ferme le programme
                   * 2 = Fermer la fenetre courrante et non toute
                   */
        );

        this.title = "Parametres";

        this.sizeofInputX = 130;
        this.sizeofInputY = 30;

        this.options = new ArrayList<>();
        this.options.add(0, "Retour au jeu");
        this.options.add(1, "Menu principal");
        this.options.add(2, "Aide");
        this.options.add(3, "Sondage");
        this.options.add(4, "Quitter le jeu");

        this.sharedObject = new BDatabase();

        Display();
    }

    /**
     * Pour afficher le menu de parametre
     */
    public void Display() {
        BLayout settings = new BLayout();
        settings.setPositionX(3);
        settings.setPositionY(8);
        settings.setPositionX(0);

        settings.setPositionY(0);
        JLabel name = new JLabel(this.title);
        this.add(name, settings);

        settings.setPositionY(1);
        this.add(new JLabel(" "), settings);

        settings.setPositionY(2);
        JButton play = new JButton(this.options.get(0));
        play.setPreferredSize(new Dimension(this.sizeofInputX, this.sizeofInputY));
        play.setActionCommand("play");
        play.addActionListener(this);
        this.add(play, settings);

        settings.setPositionY(3);
        this.add(new JLabel(" "), settings);

        settings.setPositionY(4);
        JButton help = new JButton(this.options.get(2));
        help.setPreferredSize(new Dimension(this.sizeofInputX, this.sizeofInputY));
        help.setActionCommand("help");
        help.addActionListener(this);
        this.add(help, settings);

        settings.setPositionY(5);
        JButton sondage = new JButton(this.options.get(3));
        sondage.setPreferredSize(new Dimension(this.sizeofInputX, this.sizeofInputY));
        sondage.setActionCommand("feedback");
        sondage.addActionListener(this);
        this.add(sondage, settings);

        settings.setPositionY(6);
        this.add(new JLabel(" "), settings);

        settings.setPositionY(7);
        JButton exit = new JButton(this.options.get(1));
        exit.setPreferredSize(new Dimension(this.sizeofInputX, this.sizeofInputY));
        exit.setActionCommand("exit");
        exit.addActionListener(this);
        this.add(exit, settings);

        settings.setPositionY(8);
        JButton exitAll = new JButton(this.options.get(4));
        exitAll.setPreferredSize(new Dimension(this.sizeofInputX, this.sizeofInputY));
        exitAll.setActionCommand("exitAll");
        exitAll.addActionListener(this);
        this.add(exitAll, settings);

        // TODO : Peut rajouter le copyright ici
        // settings.setPositionY(9);
        // this.add(new JLabel(" "), settings)

        // settings.setPositionY(10);
        // this.add()


        this.openBFrame();
        this.refreshBFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (Objects.equals(command, "play")) {
            this.hide();
        } else if (Objects.equals(command, "exit")) {
            /*
             * TODO: Revenir au menu principal (il est pas encore fini donc a voir plus tard
             * lol)
             */
        } else if (Objects.equals(command, "exitAll")) {
            if (this.sharedObject.getStatus()) {
                new Feedback("exit");
            }

            System.exit(0);
        } else if (Objects.equals(command, "help")) {
            try {
                new Help();
            } catch (IOException ignore) {}
        } else if (Objects.equals(command, "feedback")) {
            if (this.sharedObject.getStatus()) {
                new Feedback("justLikeThis :)");
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Impossible nous faire un retour en sans etre connecte a internet :(",
                        "Puissance 4",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
