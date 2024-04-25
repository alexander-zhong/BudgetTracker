package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Representing the initial start up login screen
public class StartUpPanel extends JPanel {
    private Gui mainInterface;
    private int error;

    public StartUpPanel(int width, int height, Gui gui, int error) {
        this.mainInterface = gui;
        this.setBackground(Color.lightGray);
        this.setSize(width, height);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.error = error;
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setUpButtonsAndTexts();

    }


    // EFFECTS: Runs all the functions to put all the display labels and buttons onto the panel
    private void setUpButtonsAndTexts() {
        addTitle();
        addDescription();
        if (error != 0) {
            addErrorMessage();
        }
        createLoginButton();
        createRegistrationButton();
        createSaveButton();
        createLoadButton();
    }

    // MODIFIES: this
    // EFFECTS: Puts an error message depending on the error code given
    //          1 = failure login
    //          2 = registration failure
    private void addErrorMessage() {
        JLabel message = new JLabel();
        if (error == 1) {
            message.setText("There was login error! Either username/passwords are wrong or account not found!");
        } else {
            message.setText("There was registration error! Username taken or passwords did not match!");
        }
        message.setBackground(Color.white);
        message.setForeground(Color.red);
        message.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        message.setBorder(new EmptyBorder(0, 0, 20, 0));
        message.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(message, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: Puts a title label onto the panel
    private void addTitle() {
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Finance Portfolio Suite");
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
        descriptionLabel.setText("This is where you can create accounts to keep track of your spending/income!");
        descriptionLabel.setBackground(Color.white);
        descriptionLabel.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        descriptionLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
        descriptionLabel.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(descriptionLabel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: Create login button for screen
    private void createLoginButton() {
        JButton button = new JButton();
        button.addActionListener(evnt -> mainInterface.loginScreen());
        button.setText("Login");
        button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        button.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(button);
    }

    // MODIFIES: this
    // EFFECTS: Create registration button for screen
    private void createRegistrationButton() {
        JButton button = new JButton();
        button.addActionListener(evnt -> mainInterface.registerScreen());
        button.setText("Register");
        button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        button.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(button);
    }

    // MODIFIES: this
    // EFFECTS: Create exit button for screen
    private void createExitButton() {
        JButton button = new JButton();
        button.addActionListener(evnt -> System.exit(0));
        button.setText("Close The App");
        button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        button.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(button);
    }

    // MODIFIES: this
    // EFFECTS: Create save button for screen
    private void createSaveButton() {
        JButton button = new JButton();
        button.addActionListener(evnt -> mainInterface.save());
        button.setText("Save Accounts");
        button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        button.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(button);
    }

    // MODIFIES: this
    // EFFECTS: Create load button for screen
    private void createLoadButton() {
        JButton button = new JButton();
        button.addActionListener(evnt -> mainInterface.load());
        button.setText("Load Accounts");
        button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        button.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(button);
    }


}
