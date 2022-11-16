import BPackage.BDatabase;
import BPackage.BFrame;
import BPackage.BInput;
import BPackage.BLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Feedback extends BFrame implements ActionListener {
    private final ArrayList<String> questions;

    private final int sizeofInputX;
    private final int sizeofInputY;

    private final String mode;

    private final BDatabase sharedObject;

    private BInput ageQuestInner;
    private BInput whereKnowQuestInner;
    private BInput itsFunnyOrNotInner;
    private BInput toBeingBetterInner;

    /**
     * Fenetre pour mettre un feedback
     *
     * @param mode Pour definir si il faut quitter l'application apres le feedback
     */
    public Feedback(String mode) {
        super(
                "Feedback - Puissance 4",
                200,
                250,
                300,
                350,
                2 /*
                   * Pour celui qui vas retravailler dessus,
                   * 3 = Ferme le programme
                   * 2 = Fermer la fenetre courrante et non toute
                   */
        );

        this.questions = new ArrayList<>();
        this.questions.add(0, "Quel age avez-vous ?");
        this.questions.add(1, "Ou avez-vous connu le jeu ?");
        this.questions.add(2, "Vous etes-vous amusez ?");
        this.questions.add(3, "Comment pouvons-nous l'ameliorer ?");

        this.sizeofInputX = 150;
        this.sizeofInputY = 21;

        this.mode = mode;

        this.sharedObject = new BDatabase();

        Display();
    }

    /**
     * Pour afficher la plat. de feedback
     */
    public void Display() {
        BLayout settings = new BLayout();
        settings.setPositionX(3);
        settings.setPositionY(17);
        settings.setPositionX(1);

        settings.setPositionY(1);
        JLabel ageQuest = new JLabel(this.questions.get(0));
        this.add(ageQuest, settings);

        settings.setPositionY(2);
        this.ageQuestInner = new BInput(this.sizeofInputX, this.sizeofInputY);
        this.add(ageQuestInner, settings);

        settings.setPositionY(3);
        this.add(new JLabel(" "), settings);

        settings.setPositionY(4);
        JLabel whereKnowQuest = new JLabel(this.questions.get(1));
        this.add(whereKnowQuest, settings);

        settings.setPositionY(5);
        this.whereKnowQuestInner = new BInput(this.sizeofInputX, this.sizeofInputY);
        this.add(whereKnowQuestInner, settings);

        settings.setPositionY(6);
        this.add(new JLabel(" "), settings);

        settings.setPositionY(7);
        JLabel itsFunnyOrNot = new JLabel(this.questions.get(2));
        this.add(itsFunnyOrNot, settings);

        settings.setPositionY(8);
        this.itsFunnyOrNotInner = new BInput(this.sizeofInputX, this.sizeofInputY);
        this.add(itsFunnyOrNotInner, settings);

        settings.setPositionY(9);
        this.add(new JLabel(" "), settings);

        settings.setPositionY(10);
        JLabel toBeingBetter = new JLabel(this.questions.get(3));
        this.add(toBeingBetter, settings);

        settings.setPositionY(11);
        this.toBeingBetterInner = new BInput(this.sizeofInputX, this.sizeofInputY);
        this.add(toBeingBetterInner, settings);

        settings.setPositionY(12);
        this.add(new JLabel(" "), settings);

        settings.setPositionY(13);
        this.add(new JLabel(" "), settings);

        settings.setPositionY(14);
        JButton confirm = new JButton("Soumettre");
        confirm.setActionCommand("send");
        confirm.addActionListener(this);
        this.add(confirm, settings);

        this.openBFrame();
        this.refreshBFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Objects.equals(e.getActionCommand(), "send")) {
            String gen = whereKnowQuestInner.getContent() + " | " +
                    itsFunnyOrNotInner.getContent() + " | " +
                    toBeingBetterInner.getContent();

            if (this.sharedObject.insertRow(
                    "feedback_p4",
                    new String[] { "age", "feedback" },
                    new String[] { ageQuestInner.getContent(), gen })) {
                JOptionPane.showMessageDialog(
                        this,
                        "Merci pour votre avis, a bientot ! :)",
                        "Puissance 4",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Suite a un probleme, votre retour n'a pas ete envoye :(",
                        "Puissance 4",
                        JOptionPane.ERROR_MESSAGE);
            }

            if (this.mode == "exit") {
                this.closeBFrame();
            } else {
                this.dispose();
            }
        }
    }
}