package ui;

import model.Account;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static java.lang.Integer.parseInt;

// Representing the view time frame decision panel screen
public class ViewTimeFrameDecisionPanel extends JPanel {

    private Gui mainInterface;
    private JTextField year;
    private JTextField month;
    private JTextField day;
    private Account currentAccount;



    public ViewTimeFrameDecisionPanel(int width, int height, Gui gui) {
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
        titleLabel.setText("View Time Frame from Specified Date to Now");
        titleLabel.setBackground(Color.white);
        titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        titleLabel.setBorder(new EmptyBorder(10, 0, 5, 0));
        titleLabel.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(titleLabel, BorderLayout.CENTER);
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
        button.addActionListener(evnt -> mainInterface.viewTimeFrameScreen(
                    parseInt(year.getText()), parseInt(month.getText()), parseInt(day.getText())));
        button.setText("View");
        button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        button.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(button);
    }




}
