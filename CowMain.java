import javax.swing.*;

public class CowMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CowView view = new CowView();
            CowDatabase cowDatabase = new CowDatabase(); // Create CowDatabase instance
            CowController controller = new CowController(view, cowDatabase);
            view.setVisible(true);
        });
    }
}
