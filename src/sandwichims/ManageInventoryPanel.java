/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sandwichims;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author bnorm
 * 
 * This will probably be the most used screen of the application.
 * 
 * There should be all sorts of information, graphs, tables, and functions
 * relevant to inventory management.
 * 
 */

/*

TODO:

BACK BUTTON should navigate back to the "Main Menu" panel.

INPUT FORM:
    This should be in the top left portion of the panel
    3 Input Fields
        1. Product ID
        2. Product Name
        3. Quantity

    4 Buttons
        1. Add
        2. New
        3. Update
        4. Delete

TABLE
    This should be in the top right of the screen. It should be a table
    with three columns: "ID", "Name", and "Qty". This table should show a pre-determined
    amount of recently edited products. If this is too hard to implement, we can figure something else out
    here.

GRAPH
    This should be in the bottom half of the screen. It should use a bar graph to show quantities of products.
    We could use this to show the 5-7 lowest-count items, 5-7 highest count items, 5-7 most recent items, etc.
    It's kind of up to us I guess. Maybe we could implement all three and let the user decide. Product name should be on the bottom of the graph,
    Quantity should be on the left side of the graph.


*/

public class ManageInventoryPanel extends JPanel {
    
    private MainFrame mainFrame;
    
    public ManageInventoryPanel(MainFrame mainFrame) {
        
        //Applying dark mode before adding GUI components
        DarkTheme.applyTheme();
        setBackground(Color.DARK_GRAY);
        
        this.mainFrame = mainFrame;
        setLayout(new FlowLayout());
        
        //Frame Components
        
        JLabel label = new JLabel("Manage Inventory");        
        add(label);

    }
}