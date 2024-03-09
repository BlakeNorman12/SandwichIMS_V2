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
}
