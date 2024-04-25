package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import model.*;

// Representing the add expense screen
public class AddExpensePanel extends JPanel {

    private Gui mainInterface;
    private JTextField name;
    private JTextField value;
    private JComboBox category;
    private JTextField year;
    private JTextField month;
    private JTextField day;
    private Account currentAccount;



    public AddExpensePanel(int width, int height, Gui gui) {
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
        addValueText();
        createValueInput();
        addCategoryText();
        createCategoryInput();
        addYearText();
        createYearInput();
        addMonthText();
        createMonthInput();
        addDayText();
        createDayInput();
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
        titleLabel.setText("Add a new expense");
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
        descriptionLabel.setText("Name Of Transaction");
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
    // EFFECTS: Puts the value text onto the panel
    private void addValueText() {
        JLabel descriptionLabel = new JLabel();
        descriptionLabel.setText("Value ($)");
        descriptionLabel.setBackground(Color.white);
        descriptionLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        descriptionLabel.setBorder(new EmptyBorder(0, 0, 0, 0));
        descriptionLabel.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(descriptionLabel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: Puts the value input box onto the panel
    private void createValueInput() {
        value = new JTextField(20);
        value.setMaximumSize(new Dimension(200, 20));
        this.add(value, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: Puts the value text onto the panel
    private void addCategoryText() {
        JLabel descriptionLabel = new JLabel();
        descriptionLabel.setText("Category");
        descriptionLabel.setBackground(Color.white);
        descriptionLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        descriptionLabel.setBorder(new EmptyBorder(0, 0, 0, 0));
        descriptionLabel.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(descriptionLabel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: Puts the category input box onto the panel
    private void createCategoryInput() {
        category = new JComboBox(currentAccount.getCategoryList().toArray(new String[0]));
        category.setMaximumSize(new Dimension(200, 20));
        this.add(category, BorderLayout.CENTER);
    }



    // MODIFIES: this
    // EFFECTS: Puts the year text onto the panel
    private void addYearText() {
        JLabel descriptionLabel = new JLabel();
        descriptionLabel.setText("Year");
        descriptionLabel.setBackground(Color.white);
        descriptionLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        descriptionLabel.setBorder(new EmptyBorder(0, 0, 0, 0));
        descriptionLabel.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(descriptionLabel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: Puts the year input box onto the panel
    private void createYearInput() {
        year = new JTextField(20);
        year.setMaximumSize(new Dimension(200, 20));
        this.add(year, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: Puts the month text onto the panel
    private void addMonthText() {
        JLabel descriptionLabel = new JLabel();
        descriptionLabel.setText("Month (1-12)");
        descriptionLabel.setBackground(Color.white);
        descriptionLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        descriptionLabel.setBorder(new EmptyBorder(0, 0, 0, 0));
        descriptionLabel.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(descriptionLabel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: Puts the year input box onto the panel
    private void createMonthInput() {
        month = new JTextField(20);
        month.setMaximumSize(new Dimension(200, 20));
        this.add(month, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: Puts the day text onto the panel
    private void addDayText() {
        JLabel descriptionLabel = new JLabel();
        descriptionLabel.setText("Day (1-31)");
        descriptionLabel.setBackground(Color.white);
        descriptionLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        descriptionLabel.setBorder(new EmptyBorder(0, 0, 0, 0));
        descriptionLabel.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(descriptionLabel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: Puts the day input box onto the panel
    private void createDayInput() {
        day = new JTextField(20);
        day.setMaximumSize(new Dimension(200, 20));
        this.add(day, BorderLayout.CENTER);
    }


    // MODIFIES: this
    // EFFECTS: Make an Add button for screen
    private void createAddButton() {
        JButton button = new JButton();
        button.addActionListener(evnt -> mainInterface.addExpense(name.getText(), value.getText(),
                category.getSelectedIndex(), year.getText(), month.getText(), day.getText()));
        button.setText("Add");
        button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        button.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(button);
    }




}
