import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PizzaGuiFrame extends JFrame {
    private JRadioButton thinCrustButton, regularCrustButton, deepDishCrustButton;
    private JComboBox<String> sizeComboBox;
    private JCheckBox topping1, topping2, topping3, topping4, topping5, topping6;
    private JTextArea orderSummaryArea;
    private JButton orderButton, clearButton, quitButton;

    public PizzaGuiFrame() {
        setTitle("Pizza Order");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel crustPanel = createCrustPanel();
        JPanel sizePanel = createSizePanel();
        JPanel toppingsPanel = createToppingsPanel();
        JPanel summaryPanel = createSummaryPanel();
        JPanel buttonPanel = createButtonPanel();

        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        centerPanel.add(toppingsPanel);
        centerPanel.add(summaryPanel);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(crustPanel, BorderLayout.NORTH);
        contentPane.add(sizePanel, BorderLayout.WEST);
        contentPane.add(centerPanel, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createCrustPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Crust Type"));

        thinCrustButton = new JRadioButton("Thin");
        regularCrustButton = new JRadioButton("Regular");
        deepDishCrustButton = new JRadioButton("Deep-dish");
        ButtonGroup group = new ButtonGroup();
        group.add(thinCrustButton);
        group.add(regularCrustButton);
        group.add(deepDishCrustButton);

        panel.add(thinCrustButton);
        panel.add(regularCrustButton);
        panel.add(deepDishCrustButton);

        return panel;
    }

    private JPanel createSizePanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Pizza Size"));

        String[] sizes = {"Small", "Medium", "Large", "Super"};
        sizeComboBox = new JComboBox<>(sizes);

        panel.add(sizeComboBox);

        return panel;
    }

    private JPanel createToppingsPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Toppings"));

        topping1 = new JCheckBox("Pepperoni");
        topping2 = new JCheckBox("Mushrooms");
        topping3 = new JCheckBox("Onions");
        topping4 = new JCheckBox("Bacon");
        topping5 = new JCheckBox("Olives");
        topping6 = new JCheckBox("Pineapple");

        panel.add(topping1);
        panel.add(topping2);
        panel.add(topping3);
        panel.add(topping4);
        panel.add(topping5);
        panel.add(topping6);

        return panel;
    }

    private JPanel createSummaryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Order Summary"));

        orderSummaryArea = new JTextArea(10, 30);
        orderSummaryArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(orderSummaryArea);

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();

        orderButton = new JButton("Order");
        clearButton = new JButton("Clear");
        quitButton = new JButton("Quit");

        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle order button click
                displayOrder();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle clear button click
                clearForm();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle quit button click
                quitProgram();
            }
        });

        panel.add(orderButton);
        panel.add(clearButton);
        panel.add(quitButton);

        return panel;
    }

    private void displayOrder() {
        StringBuilder orderText = new StringBuilder();
        orderText.append("=========================================\n");
        orderText.append("Type of Crust & Size\t\tPrice\n");
        orderText.append("-----------------------------------------\n");

        // Get selected crust type
        String crust = "";
        if (thinCrustButton.isSelected()) {
            crust = "Thin";
        } else if (regularCrustButton.isSelected()) {
            crust = "Regular";
        } else if (deepDishCrustButton.isSelected()) {
            crust = "Deep-dish";
        }

        String size = (String) sizeComboBox.getSelectedItem();
        double basePrice = getPrice(size);
        orderText.append(crust).append(" Crust & ").append(size).append("\t\t$").append(String.format("%.2f", basePrice)).append("\n");

        orderText.append("Ingredients\t\t\tPrice\n");
        double toppingPrice = 0.0;
        if (topping1.isSelected()) {
            orderText.append("Pepperoni\t\t\t$1.00\n");
            toppingPrice += 1.0;
        }
        if (topping2.isSelected()) {
            orderText.append("Mushrooms\t\t\t$1.00\n");
            toppingPrice += 1.0;
        }
        if (topping3.isSelected()) {
            orderText.append("Onions\t\t\t$1.00\n");
            toppingPrice += 1.0;
        }
        if (topping4.isSelected()) {
            orderText.append("Bacon\t\t\t$1.00\n");
            toppingPrice += 1.0;
        }
        if (topping5.isSelected()) {
            orderText.append("Olives\t\t\t$1.00\n");
            toppingPrice += 1.0;
        }
        if (topping6.isSelected()) {
            orderText.append("Pineapple\t\t\t$1.00\n");
            toppingPrice += 1.0;
        }

        double subTotal = basePrice + toppingPrice;
        double tax = subTotal * 0.07;
        double total = subTotal + tax;

        orderText.append("-----------------------------------------\n");
        orderText.append("Sub-total:\t\t\t$").append(String.format("%.2f", subTotal)).append("\n");
        orderText.append("Tax:\t\t\t\t$").append(String.format("%.2f", tax)).append("\n");
        orderText.append("-----------------------------------------\n");
        orderText.append("Total:\t\t\t\t$").append(String.format("%.2f", total)).append("\n");
        orderText.append("=========================================\n");

        orderSummaryArea.setText(orderText.toString());
    }

    private void clearForm() {
        thinCrustButton.setSelected(false);
        regularCrustButton.setSelected(false);
        deepDishCrustButton.setSelected(false);
        sizeComboBox.setSelectedIndex(0);
        topping1.setSelected(false);
        topping2.setSelected(false);
        topping3.setSelected(false);
        topping4.setSelected(false);
        topping5.setSelected(false);
        topping6.setSelected(false);
        orderSummaryArea.setText("");
    }

    private void quitProgram() {
        int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private double getPrice(String size) {
        switch (size) {
            case "Small":
                return 8.00;
            case "Medium":
                return 12.00;
            case "Large":
                return 16.00;
            case "Super":
                return 20.00;
            default:
                return 0.00;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                PizzaGuiFrame frame = new PizzaGuiFrame();
                frame.setVisible(true);
            }
        });
    }
}

