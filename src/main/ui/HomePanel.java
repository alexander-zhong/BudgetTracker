package ui;

import model.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Representing the user's homepage screen
public class HomePanel extends JPanel {
    private Gui mainInterface;
    private Account currentAccount;

    public HomePanel(int width, int height, Gui gui) {
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
        addDescription();
        createTransactionButton();
        createCategoryButton();
        createViewTimeframeButton();
        createLogOffButton();
    }

    // MODIFIES: this
    // EFFECTS: Puts a title label onto the panel
    private void addTitle() {
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Welcome " + currentAccount.getUsername() + "!");
        titleLabel.setBackground(Color.white);
        titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        titleLabel.setBorder(new EmptyBorder(10, 0, 5, 0));
        titleLabel.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(titleLabel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: Puts a description onto the panel
    private void addDescription() {
        JLabel descriptionLabel = new JLabel();
        descriptionLabel.setText("Please select an option:");
        descriptionLabel.setBackground(Color.white);
        descriptionLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        descriptionLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
        descriptionLabel.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(descriptionLabel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: Create Add/Remove transaction button for screen
    private void createTransactionButton() {
        JButton button = new JButton();
        button.addActionListener(evnt -> mainInterface.transactionDecisionScreen());
        button.setText("Add/Remove Transaction");
        button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        button.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(button);
    }

    // MODIFIES: this
    // EFFECTS: Create Add/Remove a Category button for screen
    private void createCategoryButton() {
        JButton button = new JButton();
        button.addActionListener(evnt -> mainInterface.categoryDecisionScreen());
        button.setText("Add/Remove a Category");
        button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        button.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(button);
    }

    // MODIFIES: this
    // EFFECTS: Create view timeframe button for screen
    private void createViewTimeframeButton() {
        JButton button = new JButton();
        button.addActionListener(evnt -> mainInterface.viewTimeFrameDecisionScreen());
        button.setText("View Specific Timeframe");
        button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        button.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(button);
    }

    // MODIFIES: this
    // EFFECTS: Create logOff button for screen
    private void createLogOffButton() {
        JButton button = new JButton();
        button.addActionListener(evnt -> {
            mainInterface.logOff();
        });
        button.setText("Logoff");
        button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        button.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(button);
    }

}
