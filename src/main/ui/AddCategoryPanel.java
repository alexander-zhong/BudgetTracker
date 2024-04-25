package ui;

import model.Account;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Representing the add category screen
public class AddCategoryPanel extends JPanel {

    private Gui mainInterface;
    private JTextField name;
    private JTextField value;
    private JComboBox category;
    private JTextField year;
    private JTextField month;
    private JTextField day;
    private Account currentAccount;



    public AddCategoryPanel(int width, int height, Gui gui) {
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
        addNameText();
        createNameInput();
        createAddButton();
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
        titleLabel.setText("Add a new category");
        titleLabel.setBackground(Color.white);
        titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        titleLabel.setBorder(new EmptyBorder(10, 0, 5, 0));
        titleLabel.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(titleLabel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: Puts the name text onto the panel
    private void addNameText() {
        JLabel descriptionLabel = new JLabel();
        descriptionLabel.setText("Name Of Category");
        descriptionLabel.setBackground(Color.white);
        descriptionLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        descriptionLabel.setBorder(new EmptyBorder(0, 0, 0, 0));
        descriptionLabel.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(descriptionLabel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: Puts the name input box onto the panel
    private void createNameInput() {
        name = new JTextField(20);
        name.setMaximumSize(new Dimension(200, 20));
        this.add(name, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: Make an Add button for screen
    private void createAddButton() {
        JButton button = new JButton();
        button.addActionListener(evnt -> mainInterface.addCategory(name.getText()));
        button.setText("Add");
        button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        button.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(button);
    }




}
