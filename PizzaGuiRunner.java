import javax.swing.*;

public class PizzaGuiRunner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PizzaGuiFrame().setVisible(true));
    }
}

