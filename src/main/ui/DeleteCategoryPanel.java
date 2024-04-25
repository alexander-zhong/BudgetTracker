package ui;

import model.Account;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

// Representing the delete category screen
public class DeleteCategoryPanel extends JPanel {

    private Gui mainInterface;
    private JComboBox categories;
    private Account currentAccount;



    public DeleteCategoryPanel(int width, int height, Gui gui) {
        this.mainInterface = gui;
        this.setBackground(Color.lightGray);
        this.setSize(width, height);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.currentAccount = gui.getMainManager().getCurrentAccount();
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setUpButtonsAndTexts();
    }


    // EFFECTS: Runs all the functions to put all the display labels and buttons onto the panel
    private void setUpButtonsAndTexts() {
        addTitle();
        createCategoryInput();
        createDeleteButton();
        createBackButton();
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


    // MODIFIES: this
    // EFFECTS: Puts a title label onto the panel
    private void addTitle() {
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Delete Category");
        titleLabel.setBackground(Color.white);
        titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        titleLabel.setBorder(new EmptyBorder(10, 0, 5, 0));
        titleLabel.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(titleLabel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: Puts the income input box onto the panel
    private void createCategoryInput() {
        ArrayList<String> newList = new ArrayList<>();

        for (String category : currentAccount.getCategoryList()) {
            newList.add(category);
        }

        categories = new JComboBox(newList.toArray(new String[0]));
        categories.setMaximumSize(new Dimension(200, 20));
        this.add(categories, BorderLayout.CENTER);
    }



    // MODIFIES: this
    // EFFECTS: Make a Delete button for screen
    private void createDeleteButton() {
        JButton button = new JButton();
        button.addActionListener(evnt -> mainInterface.deleteCategory(categories.getSelectedIndex()));
        button.setText("Delete");
        button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        button.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(button);
    }




}
