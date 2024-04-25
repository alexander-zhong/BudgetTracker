package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Representing the initial start up login screen
public class LoginPanel extends JPanel {

    private Gui mainInterface;
    private String username;
    private String password;
    private JTextField usernameInput;
    private JPasswordField passwordInput;



    public LoginPanel(int width, int height, Gui gui) {
        this.mainInterface = gui;
        this.setBackground(Color.lightGray);
        this.setSize(width, height);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setUpButtonsAndTexts();
    }


    // EFFECTS: Runs all the functions to put all the display labels and buttons onto the panel
    private void setUpButtonsAndTexts() {
        addTitle();
        addDescription();
        addUsernameText();
        createUsernameInput();
        addPasswordText();
        createPasswordInput();
        createLoginButton();
    }


    // MODIFIES: this
    // EFFECTS: Puts a title label onto the panel
    private void addTitle() {
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Login Menu");
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
        descriptionLabel.setText("Please enter a valid username/email");
        descriptionLabel.setBackground(Color.white);
        descriptionLabel.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        descriptionLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
        descriptionLabel.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(descriptionLabel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: Puts the username text onto the panel
    private void addUsernameText() {
        JLabel descriptionLabel = new JLabel();
        descriptionLabel.setText("Username:");
        descriptionLabel.setBackground(Color.white);
        descriptionLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        descriptionLabel.setBorder(new EmptyBorder(0, 0, 0, 0));
        descriptionLabel.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(descriptionLabel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: Puts the username input box onto the panel
    private void createUsernameInput() {
        usernameInput = new JTextField(20);
        usernameInput.setMaximumSize(new Dimension(200, 20));
        this.add(usernameInput, BorderLayout.CENTER);
    }


    // MODIFIES: this
    // EFFECTS: Puts the username text onto the panel
    private void addPasswordText() {
        JLabel descriptionLabel = new JLabel();
        descriptionLabel.setText("Password:");
        descriptionLabel.setBackground(Color.white);
        descriptionLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        descriptionLabel.setBorder(new EmptyBorder(0, 0, 0, 0));
        descriptionLabel.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(descriptionLabel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: Puts the username input box onto the panel
    private void createPasswordInput() {
        passwordInput = new JPasswordField(20);
        passwordInput.setMaximumSize(new Dimension(200, 20));
        this.add(passwordInput, BorderLayout.CENTER);
    }


    // MODIFIES: this
    // EFFECTS: Create login button for screen
    private void createLoginButton() {
        JButton button = new JButton();
        button.addActionListener(evnt -> {
            username = usernameInput.getText();
            password = passwordInput.getText();
            mainInterface.login(username, password);
        });
        button.setText("Login");
        button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        button.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(button);
    }

    // EFFECTS: Get the username
    public String getUsername() {
        return username;
    }

    // EFFECTS: Get the password
    public String getPassword() {
        return password;
    }


}
