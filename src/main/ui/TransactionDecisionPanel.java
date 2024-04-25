package ui;

import model.Account;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Representing the transaction decision screen
public class TransactionDecisionPanel extends JPanel {
    private Gui mainInterface;
    private Account currentAccount;

    public TransactionDecisionPanel(int width, int height, Gui gui) {
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
        createAddExpenseButton();
        createDeleteExpenseButton();
        createAddIncomeButton();
        createDeleteIncomeButton();
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
    // EFFECTS: Create Add Expense button for screen
    private void createAddExpenseButton() {
        JButton button = new JButton();
        button.addActionListener(evnt -> mainInterface.addExpenseScreen());
        button.setText("Add Expense");
        button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        button.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(button);
    }

    // MODIFIES: this
    // EFFECTS: Create Delete Expense button for screen
    private void createDeleteExpenseButton() {
        JButton button = new JButton();
        button.addActionListener(evnt -> mainInterface.deleteExpenseScreen());
        button.setText("Delete Expense");
        button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        button.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(button);
    }

    // MODIFIES: this
    // EFFECTS: Create Add Income button for screen
    private void createAddIncomeButton() {
        JButton button = new JButton();
        button.addActionListener(evnt -> mainInterface.addIncomeScreen());
        button.setText("Add Income");
        button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        button.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(button);
    }

    // MODIFIES: this
    // EFFECTS: Create Delete Income button for screen
    private void createDeleteIncomeButton() {
        JButton button = new JButton();
        button.addActionListener(evnt -> mainInterface.deleteIncomeScreen());
        button.setText("Delete Income");
        button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        button.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(button);
    }

}
