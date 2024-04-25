package ui;

import model.Account;
import model.Expense;
import model.Income;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

// Representing the view time frame screen
public class ViewTimeFramePanel extends JPanel {

    private Gui mainInterface;
    private JComboBox categories;
    private Account currentAccount;
    private int yearGiven;
    private int monthGiven;
    private int dayGiven;



    public ViewTimeFramePanel(int width, int height, Gui gui, int year, int month, int day) {
        this.mainInterface = gui;
        this.setBackground(Color.lightGray);
        this.setSize(width, height);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.currentAccount = gui.getMainManager().getCurrentAccount();
        this.yearGiven = year;
        this.monthGiven = month;
        this.dayGiven = day;
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setUpButtonsAndTexts();
    }


    // EFFECTS: Runs all the functions to put all the display labels and buttons onto the panel
    private void setUpButtonsAndTexts() {
        addTitle();
        expenseBox();
        incomeBox();
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
        titleLabel.setText("From " + LocalDate.of(yearGiven, monthGiven, dayGiven) + " to now");
        titleLabel.setBackground(Color.white);
        titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        titleLabel.setBorder(new EmptyBorder(10, 0, 5, 0));
        titleLabel.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(titleLabel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: Displays all the expenses that the user has
    private void expenseBox() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"Expenses"}, 0);
        for (Expense expense : currentAccount.getExpenseListDated(yearGiven, monthGiven, dayGiven)) {
            model.addRow(new String[]{expense.getDisplayName()});
        }
        JTable table = new JTable(model);

        JScrollPane scroller = new JScrollPane(table);
        scroller.setAlignmentX(this.CENTER_ALIGNMENT);
        scroller.setMaximumSize(new Dimension(250, 250));
        this.add(scroller, BorderLayout.CENTER);
    }


    // MODIFIES: this
    // EFFECTS: Displays all the income that the user has
    private void incomeBox() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"Incomes"}, 0);
        for (Income income : currentAccount.getIncomeListDated(yearGiven, monthGiven, dayGiven)) {
            model.addRow(new String[]{income.getDisplayName()});
        }
        JTable table = new JTable(model);

        JScrollPane scroller = new JScrollPane(table);
        scroller.setAlignmentX(this.CENTER_ALIGNMENT);
        scroller.setMaximumSize(new Dimension(250, 300));
        this.add(scroller, BorderLayout.CENTER);
    }





}
