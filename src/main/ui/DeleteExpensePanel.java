package ui;

import model.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;

// Representing the delete expense screen
public class DeleteExpensePanel extends JPanel {

    private Gui mainInterface;
    private JComboBox expenses;
    private Account currentAccount;



    public DeleteExpensePanel(int width, int height, Gui gui) {
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
        deleteExpenseText();
        createExpenseInput();
        createDeleteButton();
        createBackButton();
    }


    // MODIFIES: this
    // EFFECTS: Puts a title label onto the panel
    private void addTitle() {
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Delete Expense");
        titleLabel.setBackground(Color.white);
        titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        titleLabel.setBorder(new EmptyBorder(10, 0, 5, 0));
        titleLabel.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(titleLabel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: Puts the value text onto the panel
    private void deleteExpenseText() {
        JLabel descriptionLabel = new JLabel();
        descriptionLabel.setText("Category");
        descriptionLabel.setBackground(Color.white);
        descriptionLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        descriptionLabel.setBorder(new EmptyBorder(0, 0, 0, 0));
        descriptionLabel.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(descriptionLabel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: Puts the Expense input box onto the panel
    private void createExpenseInput() {
        ArrayList<String> newList = new ArrayList<>();

        for (Expense expense : currentAccount.getExpenseList()) {
            newList.add(expense.getDisplayName());
        }

        expenses = new JComboBox(newList.toArray(new String[0]));
        expenses.setMaximumSize(new Dimension(200, 20));
        this.add(expenses, BorderLayout.CENTER);
    }



    // MODIFIES: this
    // EFFECTS: Make a Delete button for screen
    private void createDeleteButton() {
        JButton button = new JButton();
        button.addActionListener(evnt -> mainInterface.deleteExpense(expenses.getSelectedIndex()));
        button.setText("Delete");
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
