import BPackage.BDessin;
import BPackage.BFrame;
import BPackage.BLayout;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Tray extends BFrame implements MouseListener, ActionListener {
    private JLabel whoPlay;
    private int idPlayerWhoPlay;

    private final String Player1;
    private final String Player2;
    private final Color p1Color;
    private final Color p2Color;
    private final int btnRadius;

    private final Color trayColor;
    private final int trayW;
    private final int trayH;

    private BDessin[][] matrix;

    public Tray(String joueur1, String joueur2) {
        super(
                "Plateau - Puissance 4",
                250,
                250,
                1250,
                500,
                "GridLayout",
                1,
                2,
                3);

        this.setResizable(false);

        this.setTitle(joueur1 + " vs " + joueur2 + " - Puissance 4");

        this.Player1 = joueur1;
        this.Player2 = joueur2;

        this.p1Color = new Color(255, 1, 0);
        this.p2Color = new Color(255, 174, 1);

        this.trayColor = new Color(1, 42, 253);
        this.trayW = 7;
        this.trayH = 6;

        this.btnRadius = 60;

        Display();
    }

    /**
     * TODO: Algo pour resoudre le puissance 4 (Je delegue la tache lol)
     */
    public void isGameWon(int whoPlay) {
        // Verification lineaire
        for (int i = 0; i <= this.matrix.length - 1; i++) {
            for (int j = 0; j <= this.matrix[i].length - 1; j++) {
                if (whoPlay == 1) {
                    isThereNeighbors(i, j, p1Color);
                } else if (whoPlay == 2) {
                    isThereNeighbors(i, j, p2Color);
                } else {
                    System.out.println("Erreur sur isGameWon().");
                    System.exit(-1);
                }
            }
        }
    }

    /**
     * Detecte les voisins du pion pour verifier la condition de victoire
     */
    public void isThereNeighbors(int i, int j, Color color) {

        if (this.matrix[i][j].getColor() != Color.WHITE &&
                this.matrix[i][j].getColor() != Color.GREEN &&
                i != 0 && j != 0 &&
                this.matrix[i][j].getColor().equals(this.matrix[i - 1][j - 1].getColor()) &&
                this.matrix[i][j].getColor().equals(color)) {
            System.out.println("haut gauche " + color);
        }

        if (this.matrix[i][j].getColor() != Color.WHITE &&
                this.matrix[i][j].getColor() != Color.GREEN &&
                i != 0 && this.matrix[i][j].getColor().equals(this.matrix[i - 1][j].getColor()) &&
                this.matrix[i][j].getColor().equals(color)) {
            System.out.println("haut " + color);
        }

        if (this.matrix[i][j].getColor() != Color.WHITE &&
                this.matrix[i][j].getColor() != Color.GREEN &&
                i != 0 && j != this.matrix[i].length - 1 &&
                this.matrix[i][j].getColor().equals(this.matrix[i - 1][j + 1].getColor()) &&
                this.matrix[i][j].getColor().equals(color)) {
            System.out.println("haut droite " + color);
        }

        if (this.matrix[i][j].getColor() != Color.WHITE &&
                this.matrix[i][j].getColor() != Color.GREEN &&
                j != 0 && this.matrix[i][j].getColor().equals(this.matrix[i][j - 1].getColor()) &&
                this.matrix[i][j].getColor().equals(color)) {
            System.out.println("gauche " + color);
        }

        if (this.matrix[i][j].getColor() != Color.WHITE &&
                this.matrix[i][j].getColor() != Color.GREEN &&
                j != this.matrix[i].length - 1 && this.matrix[i][j].getColor().equals(this.matrix[i][j + 1].getColor())
                &&
                this.matrix[i][j].getColor().equals(color)) {
            System.out.println("droite " + color);
        }

        if (this.matrix[i][j].getColor() != Color.WHITE &&
                this.matrix[i][j].getColor() != Color.GREEN &&
                i != this.matrix.length - 1 && j != 0 &&
                this.matrix[i][j].getColor().equals(this.matrix[i + 1][j - 1].getColor()) &&
                this.matrix[i][j].getColor().equals(color)) {
            System.out.println("bas gauche " + color);
        }

        if (this.matrix[i][j].getColor() != Color.WHITE &&
                this.matrix[i][j].getColor() != Color.GREEN &&
                i != this.matrix.length - 1 && this.matrix[i][j].getColor().equals(this.matrix[i + 1][j].getColor()) &&
                this.matrix[i][j].getColor().equals(color)) {
            System.out.println("bas " + color);
        }

        if (this.matrix[i][j].getColor() != Color.WHITE &&
                this.matrix[i][j].getColor() != Color.GREEN &&
                i != this.matrix.length - 1 && j != this.matrix[i].length - 1 &&
                this.matrix[i][j].getColor().equals(this.matrix[i + 1][j + 1].getColor()) &&
                this.matrix[i][j].getColor().equals(color)) {
            System.out.println("bas droite " + color);
        }

    }

    /**
     * Afficher le plateau de jeu
     */
    public void Display() {
        this.idPlayerWhoPlay = 1;
        this.whoPlay = new JLabel("Au tour de : " + this.Player1);
        this.whoPlay.setFont(new Font("Arial Black", Font.BOLD, 22));

        JPanel gameContainer = new JPanel(new GridLayout(this.trayH, this.trayW));
        gameContainer.setBackground(this.trayColor);

        this.matrix = new BDessin[this.trayH][this.trayW];

        // Generation de la matrice
        for (int i = 0; i <= this.matrix.length - 1; i++) {
            for (int j = 0; j <= this.matrix[i].length - 1; j++) {
                this.matrix[i][j] = new BDessin("circle", Color.WHITE, this.btnRadius, this.btnRadius);
                this.matrix[i][j].addMouseListener(this);
                gameContainer.add(this.matrix[i][j]);
            }
        }

        this.add(gameContainer);

        JPanel controlContainer = new JPanel(new GridBagLayout());
        BLayout controlSettings = new BLayout();
        controlSettings.setPositionX(0);
        controlSettings.setPositionY(3);

        controlSettings.setPositionX(0);
        controlSettings.setPositionY(1);
        controlContainer.add(this.whoPlay, controlSettings);

        controlSettings.setPositionX(0);
        controlSettings.setPositionY(2);
        controlContainer.add(new JLabel(" "), controlSettings);

        controlSettings.setPositionX(0);
        controlSettings.setPositionY(3);
        JButton param = new JButton("Parametres");
        param.addActionListener(this);
        param.setActionCommand("settings");
        controlContainer.add(param, controlSettings);

        this.add(controlContainer);

        this.openBFrame();
        this.refreshBFrame();
    }

    /**
     * Obtenir puis modifier le dernier pion possible d'une colonne
     *
     * @param posCol Sa position dans le colonne
     */
    public void getTheLast(int posCol) {
        try {
            int row = trayH - 1;
            int col = posCol;

            for (int i = row; i <= trayH - 1; i--) {
                if (this.matrix[i][col].getColor() == Color.WHITE || this.matrix[i][col].getColor() == Color.GREEN) {
                    if (this.idPlayerWhoPlay == 1) {
                        System.out.println("Put: " + i + ":" + col);
                        this.matrix[i][col].setColor(this.p1Color);
                        switchPlayer(1);
                    } else if (this.idPlayerWhoPlay == 2) {
                        System.out.println("Put: " + i + ":" + col);
                        this.matrix[i][col].setColor(this.p2Color);
                        switchPlayer(2);
                    }

                    break;
                }
            }
        } catch (ArrayIndexOutOfBoundsException ignore) {
        }
    }

    /**
     * Pour changer le joueur
     *
     * @param currentPlayer Le joueur qui joue actuellement
     */
    public void switchPlayer(int currentPlayer) {
        if (currentPlayer == 1) {
            this.idPlayerWhoPlay = 2;
            this.whoPlay.setText("Au tour de : " + this.Player2);
            this.refreshBFrame();
        } else if (currentPlayer == 2) {
            this.idPlayerWhoPlay = 1;
            this.whoPlay.setText("Au tour de : " + this.Player1);
            this.refreshBFrame();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        for (int i = 0; i <= this.matrix.length - 1; i++) {
            for (int j = 0; j <= this.matrix[i].length - 1; j++) {
                if (this.matrix[i][j] == e.getSource()) {
                    System.out.println("Click: " + i + ":" + j);
                    this.getTheLast(j);
                    isGameWon(this.idPlayerWhoPlay);
                    break;
                }
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        BDessin source = (BDessin) e.getSource();

        if (source.getColor() == Color.WHITE) {
            source.setColor(Color.GREEN);
            this.refreshBFrame();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (Objects.equals(command, "settings")) {
            new ParamMenu();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        BDessin source = (BDessin) e.getSource();

        if (source.getColor() == Color.GREEN) {
            source.setColor(Color.WHITE);
            this.refreshBFrame();
        }
    }
}