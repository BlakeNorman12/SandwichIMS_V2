/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sandwichims;

/**
 *
 * @author bnorm
 */
import javax.swing.*;
import java.awt.*;

public class DarkTheme {
    public static void applyTheme() {
        UIManager.put("Panel.background", Color.DARK_GRAY);
        UIManager.put("Label.foreground", Color.LIGHT_GRAY);
        UIManager.put("Button.background", Color.GRAY);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("TextField.background", Color.LIGHT_GRAY);
        UIManager.put("TextField.foreground", Color.BLACK);
        UIManager.put("TextField.caretForeground", Color.WHITE);
        UIManager.put("PasswordField.background", Color.LIGHT_GRAY);
        UIManager.put("PasswordField.foreground", Color.BLACK);
        UIManager.put("PasswordField.caretForeground", Color.WHITE);
    }
    
    public static void showCustomDialog(Frame parentFrame, String message) {
        JDialog dialog = new JDialog(parentFrame, "Message", true);

        // Main panel with BorderLayout to position elements
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add some padding

        // Label with black text in the center
        JLabel messageLabel = new JLabel("<html><body style='text-align: center'>" + message + "</body></html>", SwingConstants.CENTER);
        messageLabel.setForeground(Color.BLACK);
        panel.add(messageLabel, BorderLayout.CENTER);

        // Button panel at the bottom
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE); // Match the background with the main panel
        JButton closeButton = new JButton("Close");
        closeButton.setBackground(Color.WHITE);
        closeButton.setForeground(Color.BLACK);
        closeButton.addActionListener(e -> dialog.dispose());
        buttonPanel.add(closeButton);

        // Add the button panel to the main panel at the bottom
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Set the dialog size or pack for automatic sizing
        dialog.add(panel);
        dialog.setSize(new Dimension(300, 150)); // Set your preferred size here
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }

}
