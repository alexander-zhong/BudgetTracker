package ui;

import model.AccountManager;
import model.Event;
import model.EventLog;
import model.Expense;
import model.Income;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

// Represents the entire gui
public class Gui extends JFrame implements WindowListener, WindowFocusListener, WindowStateListener {
    private AccountManager mainManager;
    private boolean running;
    private boolean loggedIn;
    private static final String PATH = "./data/accountManager.json";
    private int windowWidth;
    private int windowHeight;

    // EFFECTS: Instantiates a portfolio app
    public Gui() {
        mainManager = new AccountManager();
        running = false;
        loggedIn = mainManager.isLoggedIn();
        windowHeight = 800;
        windowWidth = 800;
    }

    // MODIFIES: this
    // EFFECTS: Creates the general frame of the app and starts the app
    public void run() {

        setSize(windowWidth, windowHeight);
        setTitle("Personal Finance Tracking Database");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);


        startUpScreen(0);
        revalidate();
        repaint();
        this.addWindowListener(this);
        this.addWindowStateListener(this);
        this.addWindowFocusListener(this);
    }

    // REQUIRES: instruction = 1 or instruction = 2
    // MODIFIES: this
    // EFFECTS: Creates a success screen if something is successful
    //          if instruction = 1, redirect to the login screen
    //          if instruction = 2, redirect to the accountHomePage
    public void successScreen(int instruction) {
        JPanel panel = new JPanel();
        this.setSize(windowWidth, windowHeight);

        ImageIcon image = new ImageIcon("./data/success.png");

        JLabel successPicture = new JLabel();
        successPicture.setIcon(image);
        panel.add(successPicture);

        addScreen(panel);

        Timer delay = new Timer(1000, evnt -> {
            if (instruction == 1) {
                startUpScreen(0);

            } else {
                accountHomeScreen();
            }
            ((Timer) evnt.getSource()).stop();
        });

        delay.start();
    }


    // MODIFIES: this
    // EFFECTS: Refreshes the panel and adds a screen, revalidate and repaints it
    public void addScreen(JPanel panel) {
        this.getContentPane().removeAll();
        add(panel);
        revalidate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: Runs the startup login/registration screen
    public void startUpScreen(int error) {
        JPanel startUpPanel = new StartUpPanel(windowWidth, windowHeight, this, error);
        addScreen(startUpPanel);
    }

    // MODIFIES: this
    // EFFECTS: Run the login screen and puts it onto the frame
    public void loginScreen() {
        JPanel loginPanel = new LoginPanel(windowWidth, windowHeight, this);
        addScreen(loginPanel);
    }

    // MODIFIES: this
    // EFFECTS: Run the register screen and puts it onto the frame
    public void registerScreen() {
        JPanel registerPanel = new RegistrationPanel(windowWidth, windowHeight, this);
        addScreen(registerPanel);
    }

    // MODIFIES: this
    // EFFECTS: Saves the accounts into the json file
    public void save() {
        JsonWriter saver = new JsonWriter(PATH);
        try {
            saver.setUp();
            saver.saveAccountManager(mainManager);
            successScreen(1);
        } catch (FileNotFoundException e) {
            System.out.println("Sorry internal error has been found");
        }
    }

    // MODIFIES: this
    // EFFECTS: Loads the accounts into the json file
    public void load() {
        JsonReader loader = new JsonReader(PATH);
        try {
            mainManager = loader.loadAccountManager();
            successScreen(1);
        } catch (IOException e) {
            System.out.println("Internal error has been found!");
        }
    }

    // MODIFIES: this
    // EFFECTS: Checks if the login is valid, if yes redirect, brings to main menu, if not, redirect to main screen
    public void login(String username, String password) {
        Boolean checkLogin = mainManager.login(username, password);
        if (checkLogin) {
            successScreen(2);
        } else {
            startUpScreen(1);
        }
    }

    // MODIFIES: this
    // EFFECTS: Checks if the registration is valid, if yes
    //          directs to user home screen, if not, redirect to main screen
    public void register(String username, String password, String rePassword) {
        Boolean checkRegistration = mainManager.register(username, password, rePassword);
        if (checkRegistration) {
            mainManager.login(username, password);
            successScreen(2);
        } else {
            startUpScreen(2);
        }
    }

    // MODIFIES: this
    // EFFECTS: Creates the home page of options for the user to take
    public void accountHomeScreen() {
        JPanel homePanel = new HomePanel(windowWidth, windowHeight, this);
        addScreen(homePanel);
    }

    // MODIFIES: this
    // EFFECTS: Run the transactionDecision screen and puts it onto the frame
    public void transactionDecisionScreen() {
        JPanel panel = new TransactionDecisionPanel(windowWidth, windowHeight, this);
        addScreen(panel);
    }

    // MODIFIES: this
    // EFFECTS: Run the categoryDecision screen and puts it onto the frame
    public void categoryDecisionScreen() {
        JPanel panel = new CategoryDecisionPanel(windowWidth, windowHeight, this);
        addScreen(panel);
    }

    // MODIFIES: this
    // EFFECTS: Run the viewTimeFrameDecision screen and puts it onto the frame
    public void viewTimeFrameDecisionScreen() {
        JPanel panel = new ViewTimeFrameDecisionPanel(windowWidth, windowHeight, this);
        addScreen(panel);
    }

    // MODIFIES: this
    // EFFECTS: Run the AddExpense screen and puts it onto the frame
    public void addExpenseScreen() {
        JPanel panel = new AddExpensePanel(windowWidth, windowHeight, this);
        addScreen(panel);
    }

    // MODIFIES: this
    // EFFECTS: Run the DeleteExpense screen and puts it onto the frame
    public void deleteExpenseScreen() {
        JPanel panel = new DeleteExpensePanel(windowWidth, windowHeight, this);
        addScreen(panel);
    }

    // MODIFIES: this
    // EFFECTS: Run the AddIncome screen and puts it onto the frame
    public void addIncomeScreen() {
        JPanel panel = new AddIncomePanel(windowWidth, windowHeight, this);
        addScreen(panel);
    }

    // MODIFIES: this
    // EFFECTS: Run the DeleteIncome screen and puts it onto the frame
    public void deleteIncomeScreen() {
        JPanel panel = new DeleteIncomePanel(windowWidth, windowHeight, this);
        addScreen(panel);
    }

    // MODIFIES: this
    // EFFECTS: Run the AddCategory screen and puts it onto the frame
    public void addCategoryScreen() {
        JPanel panel = new AddCategoryPanel(windowWidth, windowHeight, this);
        addScreen(panel);
    }

    // MODIFIES: this
    // EFFECTS: Add category to the system
    public void addCategory(String name) {
        mainManager.getCurrentAccount().addCategory(name);
        successScreen(2);
    }


    // MODIFIES: this
    // EFFECTS: Run the DeleteCategory screen and puts it onto the frame
    public void deleteCategoryScreen() {
        JPanel panel = new DeleteCategoryPanel(windowWidth, windowHeight, this);
        addScreen(panel);
    }

    // MODIFIES: this
    // EFFECTS: Add category to the system
    public void deleteCategory(int index) {
        mainManager.getCurrentAccount().removeCategory(index);
        successScreen(2);
        successScreen(2);
    }

    // MODIFIES: this
    // EFFECTS: Logs off the user and brings them back to startup
    public void logOff() {
        getMainManager().logout();
        successScreen(1);
    }

    // MODIFIES: this
    // EFFECTS: Adds the expense to the current account
    public void addExpense(String name, String value, int category, String year, String month, String day) {
        this.getMainManager().getCurrentAccount().addExpense(
                new Expense(name,
                        parseDouble(value),
                        LocalDate.of(parseInt(year), parseInt(month), parseInt(day)),
                        mainManager.getCurrentAccount().getCategory((category))));
        successScreen(2);
    }

    // MODIFIES: this
    // EFFECTS: Remove the expense from the current account
    public void deleteExpense(int index) {
        this.getMainManager().getCurrentAccount().removeExpense(index);
        successScreen(2);
    }

    // MODIFIES: this
    // EFFECTS: Adds the income to the current account
    public void addIncome(String name, String value, String year, String month, String day) {
        this.getMainManager().getCurrentAccount().addIncome(
                new Income(name,
                        parseDouble(value),
                        LocalDate.of(parseInt(year), parseInt(month), parseInt(day))));
        successScreen(2);
    }

    // MODIFIES: this
    // EFFECTS: Remove the expense from the current account
    public void deleteIncome(int index) {
        this.getMainManager().getCurrentAccount().removeIncome(index);
        successScreen(2);
    }

    // REQUIRES: year, month, and day must be a real date range
    // MODIFIES: this
    // EFFECTS: Creates the viewTimeFrameScreen
    public void viewTimeFrameScreen(int year, int month, int day) {
        JPanel panel = new ViewTimeFramePanel(windowWidth, windowHeight, this, year, month, day);
        addScreen(panel);
    }

    // MODIFIES: this
    // EFFECTS: When the back button is pressed
    public void backButton() {
        accountHomeScreen();
    }

    // EFFECTS: Gets the account manager
    public AccountManager getMainManager() {
        return mainManager;
    }

    // EFFECTS: The function that prints events when window closes
    public void printEvents() {
        EventLog logger = EventLog.getInstance();
        for (Event event : logger) {
            System.out.println(event.getDescription());
        }
        this.dispose();
    }

    // EFFECTS: function run when window gain focus
    @Override
    public void windowGainedFocus(WindowEvent e) {
        // do nothing
    }

    // EFFECTS: function to run when window lost focus
    @Override
    public void windowLostFocus(WindowEvent e) {
        // do nothing
    }

    // EFFECTS: function when window is opened
    @Override
    public void windowOpened(WindowEvent e) {
        // do nothing
    }

    // EFFECTS: Function when the window is closing
    @Override
    public void windowClosing(WindowEvent e) {
        printEvents();
    }

    // EFFECTS: function run when the window is closed
    @Override
    public void windowClosed(WindowEvent e) {
        // do nothing
    }

    // EFFECTS: function run when the window iconified
    @Override
    public void windowIconified(WindowEvent e) {
        // do nothing
    }

    // EFFECTS: function run when the window deiconified
    @Override
    public void windowDeiconified(WindowEvent e) {
        // do nothing
    }

    // EFFECTS: function run when the window activated
    @Override
    public void windowActivated(WindowEvent e) {
        // do nothing
    }

    // EFFECTS: function run when the window deactivated
    @Override
    public void windowDeactivated(WindowEvent e) {
        // do nothing
    }

    // EFFECTS: function run when the window state changed
    @Override
    public void windowStateChanged(WindowEvent e) {
        // do nothing
    }
}
