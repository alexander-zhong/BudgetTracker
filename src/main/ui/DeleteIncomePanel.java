package ui;

import model.Account;
import model.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

// Representing the delete income screen
public class DeleteIncomePanel extends JPanel {

    private Gui mainInterface;
    private JComboBox incomes;
    private Account currentAccount;



    public DeleteIncomePanel(int width, int height, Gui gui) {
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
        createIncomeInput();
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
        titleLabel.setText("Delete Income");
        titleLabel.setBackground(Color.white);
        titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        titleLabel.setBorder(new EmptyBorder(10, 0, 5, 0));
        titleLabel.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(titleLabel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: Puts the income input box onto the panel
    private void createIncomeInput() {
        ArrayList<String> newList = new ArrayList<>();

        for (Income income : currentAccount.getIncomeList()) {
            newList.add(income.getDisplayName());
        }

        incomes = new JComboBox(newList.toArray(new String[0]));
        incomes.setMaximumSize(new Dimension(200, 20));
        this.add(incomes, BorderLayout.CENTER);
    }



    // MODIFIES: this
    // EFFECTS: Make a Delete button for screen
    private void createDeleteButton() {
        JButton button = new JButton();
        button.addActionListener(evnt -> mainInterface.deleteIncome(incomes.getSelectedIndex()));
        button.setText("Delete");
        button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        button.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(button);
    }




}
