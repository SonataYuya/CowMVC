import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CowView extends JFrame {
    private JTextField cowIdField = new JTextField(10);
    private JTextArea resultArea = new JTextArea(10, 30);
    private JButton submitButton = new JButton("Submit Cow ID");
    private JButton milkButton = new JButton("Milk Cow");
    private JButton lemonMilkButton = new JButton("Milk with Lemon");

    public CowView() {
        // Layout setup
        JPanel cowPanel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 300);

        cowPanel.add(new JLabel("Enter Cow ID:"));
        cowPanel.add(cowIdField);
        cowPanel.add(submitButton);
        cowPanel.add(milkButton);
        cowPanel.add(lemonMilkButton);

        resultArea.setEditable(false);
        cowPanel.add(new JScrollPane(resultArea));

        this.add(cowPanel);
        milkButton.setVisible(false);
        lemonMilkButton.setVisible(false);
    }

    public String getCowId() {
        return cowIdField.getText();
    }

    public void setResultArea(String result) {
        resultArea.setText(result);
    }

    public void showMilkingOptions(boolean showLemonOption) {
        milkButton.setVisible(true);
        lemonMilkButton.setVisible(showLemonOption);
    }

    public void addSubmitListener(ActionListener listenForSubmitButton) {
        submitButton.addActionListener(listenForSubmitButton);
    }

    public void addMilkListener(ActionListener listenForMilkButton) {
        milkButton.addActionListener(listenForMilkButton);
    }

    public void addLemonMilkListener(ActionListener listenForLemonMilkButton) {
        lemonMilkButton.addActionListener(listenForLemonMilkButton);
    }
}
