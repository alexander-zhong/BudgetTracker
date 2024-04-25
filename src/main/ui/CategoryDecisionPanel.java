package ui;

import model.Account;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Representing the category decision screen
public class CategoryDecisionPanel extends JPanel {
    private Gui mainInterface;
    private Account currentAccount;

    public CategoryDecisionPanel(int width, int height, Gui gui) {
        this.mainInterface = gui;
        this.setBackground(Color.lightGray);
        this.setSize(width, height);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.currentAccount = mainInterface.getMainManager().getCurrentAccount();
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setUpButtonsAndTexts();
    }


    // EFFECTS: Runs all the functions to put all the display labels and buttons onto the panel
    private void setUpButtonsAndTexts() {
        addTitle();
        createAddCategoryButton();
        createDeleteCategoryButton();
    }

    // MODIFIES: this
    // EFFECTS: Puts a title label onto the panel
    private void addTitle() {
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Choose an option below:");
        titleLabel.setBackground(Color.white);
        titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        titleLabel.setBorder(new EmptyBorder(10, 0, 5, 0));
        titleLabel.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(titleLabel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: Create Add Category button for screen
    private void createAddCategoryButton() {
        JButton button = new JButton();
        button.addActionListener(evnt -> mainInterface.addCategoryScreen());
        button.setText("Add Category");
        button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        button.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(button);
    }

    // MODIFIES: this
    // EFFECTS: Create Delete Category button for screen
    private void createDeleteCategoryButton() {
        JButton button = new JButton();
        button.addActionListener(evnt -> mainInterface.deleteCategoryScreen());
        button.setText("Delete Category");
        button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        button.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(button);
    }

    // MODIFIES: this
    // EFFECTS: Make a back button for screen
    private void createBackButton() {
        JButton button = new JButton();
        button.addActionListener(evnt -> mainInterface.backButton());
        button.setText("Back");
        button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        button.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(button);
    }

}
