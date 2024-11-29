import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Benutzerdefinierte RoundedButton-Klasse
class RoundedButton extends JButton {
    private int radius;

    public RoundedButton(String label, int radius) {
        super(label);
        this.radius = radius;
        setContentAreaFilled(false); // Entfernt den Standard-Hintergrund
        setBorderPainted(false); // Deaktiviert den Standardrahmen
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Hintergrundfarbe zeichnen
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        super.paintComponent(g);
    }

    /* @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Rahmenfarbe zeichnen
        g2d.setColor(getForeground());
        g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
    }*/
}
// Hauptklasse
public class RockPaperScissorGUI extends JFrame implements ActionListener {
    public static final int WINDOW_WIDTH = 450;
    private static final int WINDOW_HEIGHT = 574;
    JLabel playerScoreLabel, resultLabel;
    JLabel computerScoreLabel;
    JLabel computerChoice; // will display the choice of the computer
    JButton rockButton, paperButton, scissorButton;
    RockPaperScissor rockPaperScissor; // backend obj
    public RockPaperScissorGUI() {
        super("Rock Paper Scissor"); // invoke iframe constructor and add title to the GUI
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT); // set the sitze of the GUI
        setDefaultCloseOperation(EXIT_ON_CLOSE); // terminate the java virtual machine when clothing the GUI
        setLocationRelativeTo(null); // load GUI in the center of the screen every time we run the application
        setLayout(null);
        getContentPane().setBackground(new Color(255, 255, 255)); // Weißer Hintergrund
        rockPaperScissor = new RockPaperScissor(); // initialize the backend obj
        addGuiComponents();
    }
    private void addGuiComponents() {
        int labelWidth = 450;
        int buttonWidth = 105;
        // Punktestand des Computers
        computerScoreLabel = new JLabel("Computer: 0");
        computerScoreLabel.setBounds((WINDOW_WIDTH - labelWidth) / 2, 43, labelWidth, 30);
        computerScoreLabel.setFont(new Font("Dialog", Font.BOLD, 26));
        computerScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(computerScoreLabel);

        // create computer choice
        computerChoice = new JLabel("?");
        computerChoice.setBounds((WINDOW_WIDTH - 98) / 2, 155, 98, 81);
        computerChoice.setFont(new Font("Dialog", Font.PLAIN, 18));
        computerChoice.setHorizontalAlignment(SwingConstants.CENTER);
        computerChoice.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // create a black border around
        add(computerChoice);

        // Punktestand des Spielers
        playerScoreLabel = new JLabel("Player: 0");
        playerScoreLabel.setBounds((WINDOW_WIDTH - labelWidth) / 2, 80, labelWidth, 30);
        playerScoreLabel.setFont(new Font("Dialog", Font.BOLD, 26));
        playerScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(playerScoreLabel);

        // Ergebnisanzeige
        resultLabel = new JLabel("Make your choice!");
        resultLabel.setBounds((WINDOW_WIDTH - labelWidth) / 2, 120, labelWidth, 30);
        resultLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(resultLabel);

        // Buttons für Spielerwahl
        int firstButtonX = (WINDOW_WIDTH - (3 * buttonWidth + 2 * 10)) / 2; // 10 ist der Abstand zwischen den Buttons
        rockButton = new RoundedButton("Rock", 30); // Abgerundeter Button mit Radius 30
        rockButton.setBounds(40, 387, 105, 81);
        rockButton.setFont(new Font("Dialog", Font.PLAIN, 18));
        // rockButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        rockButton.setBackground(new Color(255, 102, 102)); // Hellrot
        rockButton.setForeground(Color.WHITE); // Weißer Text
        rockButton.addActionListener(this);
        // MouseListener für Hover-Effekt hinzufügen
        rockButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                rockButton.setBackground(new Color(255, 51, 51)); // Dunkleres Rot
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                rockButton.setBackground(new Color(255, 102, 102)); // Ursprüngliches Hellrot
            }
        });
        add(rockButton);

        paperButton = new RoundedButton("Paper", 30);
        paperButton.setBounds(165, 387, 105, 81);
        paperButton.setFont(new Font("Dialog", Font.PLAIN, 18));
        // paperButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        paperButton.setBackground(new Color(102, 178, 255)); // Hellblau
        paperButton.setForeground(Color.WHITE); // Weißer Text
        paperButton.addActionListener(this);
        // MouseListener für den Paper-Button hinzufügen
        paperButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                paperButton.setBackground(new Color(51, 153, 255)); // Dunkleres Blau
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                paperButton.setBackground(new Color(102, 178, 255)); // Ursprüngliches Hellblau
            }
        });
        add(paperButton);

        scissorButton = new RoundedButton("Scissors", 30);
        scissorButton.setBounds(290, 387, 105, 81);
        scissorButton.setFont(new Font("Dialog", Font.PLAIN, 18));
        // scissorButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        scissorButton.setBackground(new Color(255, 255, 102)); // Hellgelb
        scissorButton.setForeground(Color.BLACK); // Schwarzer Text
        scissorButton.addActionListener(this);
        // MouseListener für den Scissor-Button hinzufügen
        scissorButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                scissorButton.setBackground(new Color(255, 204, 51)); // Dunkleres Gelb
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                scissorButton.setBackground(new Color(255, 255, 102)); // Ursprüngliches Hellgelb
            }
        });
        add(scissorButton);


    }
    // displays a message dialog which will show the winner and try again button to play again
    private void showDialog(String message) {
        JDialog resultDialog = new JDialog(this, "Result", true);
        resultDialog.setSize(227, 124);
        resultDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        resultDialog.setResizable(false);

        // message label
        JLabel resultLabel = new JLabel(message);
        resultLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultDialog.add(resultLabel, BorderLayout.CENTER);

        // try again button
        JButton tryAgainButton = new JButton("Try Again?");
        tryAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                computerChoice.setText("?"); // reset computer choice
                resultDialog.dispose(); // close the dialog box
            }
        });
        resultDialog.add(tryAgainButton, BorderLayout.SOUTH);

        resultDialog.setLocationRelativeTo(this);
        resultDialog.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println("Button clicked: " + e.getActionCommand()); (nur für Testzweck)
        String playerChoice = e.getActionCommand(); // Button-Beschriftung als Wahl des Spielers
        String result = rockPaperScissor.playRockPaperScissor(playerChoice); // Spiel-Logik aufrufen
        //System.out.println("Result: " + result); (nur für Testzweck)
        computerChoice.setText(rockPaperScissor.getComputerChoice()); // load computer's choice
        computerScoreLabel.setText("Computer:" + rockPaperScissor.getComputerScore());  // update score
        playerScoreLabel.setText("Player:" + rockPaperScissor.getPlayerScore());
        //System.out.println("Result: " + result); // Testausgabe in der Konsole (nur für Testzweck)

        // Ergebnis mit Farbe anzeigen
        if (result.equals("Player Wins")) {
            resultLabel.setForeground(new Color(34, 139, 34)); // Grün
        } else if (result.equals("Computer Wins")) {
            resultLabel.setForeground(new Color(255, 69, 0)); // Rot
        } else {
            resultLabel.setForeground(Color.GRAY); // Grau
        }
        resultLabel.setText(result);

        showDialog(result); // display result dialog
    }
}
