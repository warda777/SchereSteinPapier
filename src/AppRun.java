import javax.swing.*;

public class AppRun {
    public static void main(String[] args) {
        // GUI-Initialisierung im EDT sicherstellen
        SwingUtilities.invokeLater(AppRun::createAndShowGUI);
    }

    public static void createAndShowGUI() {
        try {
            // Plattformübergreifendes Look-and-Feel setzen
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.err.println("Fehler beim Setzen des Look-and-Feel: " + e.getMessage());
            // Optional: Weitere Fehlerbehandlung oder Fallback-Look-and-Feel
        }

        // GUI erstellen
        RockPaperScissorGUI rockPaperScissorGUI = new RockPaperScissorGUI();
        rockPaperScissorGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Stellen Sie sicher, dass die Anwendung komplett geschlossen wird
        rockPaperScissorGUI.setSize(500, 600); // Größe festlegen, falls noch nicht im Konstruktor geschehen
        rockPaperScissorGUI.setResizable(false); // Fenstergröße fixieren
        // GUI anzeigen
        rockPaperScissorGUI.setVisible(true);
    }
}
