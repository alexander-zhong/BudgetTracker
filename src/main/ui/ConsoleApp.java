package ui;


import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

// Portfolio App Application
public class ConsoleApp {

    AccountManager mainManager;
    boolean running;
    Scanner inputScanner;
    boolean loggedIn;
    private static final String PATH = "./data/accountManager.json";

    // EFFECTS: Instantiates a portfolio app
    public ConsoleApp() {
        mainManager = new AccountManager();
        running = false;
        inputScanner = new Scanner(System.in);
        loggedIn = mainManager.isLoggedIn();
    }

    // MODIFIES: this
    // EFFECTS: Runs the portfolio app
    public void run() {
        running = true;

        while (running) {
            loggedIn = mainManager.isLoggedIn();
            if (loggedIn == true) {
                // Runs menuScreen when logged in
                portfolioMenuScreen();
            } else {

                // Runs intro screen
                introScreen();
            }
        }
    }

    // EFFECTS: Prompts the user to login/register for an account
    private void introScreen() {
        System.out.println("\nHello! Welcome to the portfolio app!");
        System.out.println("This is an app designed to track your spending and income!\n");

        // Prompts user to log in
        while (loggedIn != true) {
            printIntroScreenItems();

            String userInput = inputScanner.nextLine();

            if (userInput.equals("1")) {
                if (loginScreen() == true) {
                    System.out.println("Successfully Logged In!");
                    break;
                }
            } else if (userInput.equals("2")) {
                registerScreen();
            } else if (userInput.equals("3")) {
                printEvents();
                System.exit(0);
            } else if (userInput.equals("4")) {
                savesAccountManager();
            } else if (userInput.equals("5")) {
                loadsAccountManager();
            } else {
                System.out.println("Error please input the correct number");
            }
        }

    }


    // EFFECTS: Saves the accountManager to the json file
    private void savesAccountManager() {
        JsonWriter saver = new JsonWriter(PATH);
        try {
            saver.setUp();
            saver.saveAccountManager(mainManager);
            System.out.println("Successfully saved!");
        } catch (FileNotFoundException e) {
            System.out.println("Sorry internal error has been found");
        }
    }

    // EFFECTS: Saves the accountManager to the json file
    private void loadsAccountManager() {
        JsonReader loader = new JsonReader(PATH);
        try {
            mainManager = loader.loadAccountManager();
            System.out.println("Successfully loaded in all the saved accounts!");
        } catch (IOException e) {
            System.out.println("Internal error has been found!");
        }
    }

    // EFFECTS: Prints the intro screen options
    private void printIntroScreenItems() {
        System.out.println("Please Input the following for the desired action:\n");
        System.out.println("<1> Login");
        System.out.println("<2> Register");
        System.out.println("<3> Exit");
        System.out.println("<4> Save Accounts");
        System.out.println("<5> Load Accounts\n");
        System.out.println("Input: ");
    }


    // EFFECTS: prompts user to login, if information is incorrect, returns false
    //          if successfully login, returns true
    private boolean loginScreen() {
        // Prompts user to input username and password
        System.out.println("Username: ");
        String usernameInput = inputScanner.nextLine();
        System.out.println("Password: ");
        String passwordInput = inputScanner.nextLine();

        boolean result = mainManager.login(usernameInput, passwordInput);

        if (result == false) {
            System.out.println("Username not found or password is incorrect!");
        }

        return result;
    }

    // EFFECTS: Prints the prompt for registration and checks if successfully registered, take
    //          user back to login menu, if failed registered, replay the menu
    private void registerScreen() {
        boolean successful = false;

        while (successful == false) {
            // Prompts user to input username and password and confirm password
            System.out.println("Username: ");
            String usernameInput = inputScanner.nextLine();
            System.out.println("Password: ");
            String passwordInput = inputScanner.nextLine();
            System.out.println("Confirm Password: ");
            String confirmPasswordInput = inputScanner.nextLine();

            successful = mainManager.register(usernameInput, passwordInput, confirmPasswordInput);

            if (successful == false) {
                System.out.println("\nUsername may be taken or password does not match! Try Again!");
            }
        }

        System.out.println("Successfully registered! Please login.");
    }

    // EFFECTS: Prints the total income history with the total amount
    private void portfolioMenuScreen() {
        String userInput = "";
        while (userInput == "") {
            printPortfolioMenu();
            userInput = inputScanner.nextLine();

            if (userInput.equals("1")) {
                transactionMenu();
                break;
            } else if (userInput.equals("2")) {
                categoryMenu();
                break;
            } else if (userInput.equals("3")) {
                timeframeMenu();
                break;
            } else if (userInput.equals("4")) {
                mainManager.logout();
                break;
            } else if (userInput.equals("5")) {
                printEvents();
                System.exit(0);
            }
            System.out.println("Error, please select a correct value");
            userInput = "";
        }
    }

    // EFFECTS: print the events into the console
    private void printEvents() {
        EventLog logger = EventLog.getInstance();
        for (Event event : logger) {
            System.out.println(event.getDescription());
        }
    }

    // EFFECTS: Prints the total expenses and income history with the total amount
    private void printPortfolioMenu() {
        System.out.println("Welcome to your personal portfolio: \n");
        printExpenses(mainManager.getCurrentAccount().getExpenseList());
        printIncomes(mainManager.getCurrentAccount().getIncomeList());
        printPortfolioMenuItems();
        System.out.println("Please Select An Option:");
    }

    // EFFECTS: Prints the portfolio menu options for the user
    private void printPortfolioMenuItems() {
        System.out.println("Please Input the following for the desired action:");
        System.out.println("<1> Add/Remove a Transaction");
        System.out.println("<2> Add/Remove a Category");
        System.out.println("<3> View Specific Timeframe");
        System.out.println("<4> Logoff");
        System.out.println("<5> Exit Program");
    }

    // EFFECTS Prompts the user to choose a transaction to change
    private void transactionMenu() {
        String userInput = "";

        while (userInput.equals("")) {
            System.out.println("Please choose a type of transaction to change:\n");
            System.out.println("<1> Expenses");
            System.out.println("<2> Income\n");
            System.out.println("Please choose an option: ");
            userInput = inputScanner.nextLine();

            if (userInput.equals("1")) {
                expensesMenu();
                break;
            } else if (userInput.equals("2")) {
                incomeMenu();
                break;
            } else {
                System.out.println("error");
                userInput = "";
            }
        }
    }

    // EFFECTS: Prompts user to add or remove expenses
    private void expensesMenu() {
        String userInput = "";

        while (userInput.equals("")) {
            System.out.println("Please choose an option below for expenses:\n");
            System.out.println("<1> Add");
            System.out.println("<2> Remove\n");
            System.out.println("Please choose an option: ");
            userInput = inputScanner.nextLine();

            if (userInput.equals("1")) {
                addExpenseMenu();
                break;
            } else if (userInput.equals("2")) {
                removeExpenseMenu();
                break;
            } else {
                System.out.println("error");
                userInput = "";
            }
        }
    }

    // EFFECTS: Prompts user to add or remove expenses
    private void incomeMenu() {
        String userInput = "";

        while (userInput.equals("")) {
            System.out.println("Please choose an option below for income:\n");
            System.out.println("<1> Add");
            System.out.println("<2> Remove\n");
            System.out.println("Please choose an option: ");
            userInput = inputScanner.nextLine();

            if (userInput.equals("1")) {
                addIncomeMenu();
                break;
            } else if (userInput.equals("2")) {
                removeIncomeMenu();
                break;
            } else {
                System.out.println("error");
                userInput = "";
            }
        }
    }


    // EFFECTS: adds a new expense item to the menu
    private void addExpenseMenu() {
        System.out.println("Enter Name of Expense: ");
        String nameInput = inputScanner.nextLine();
        System.out.println("Enter Value of the Expense: ");
        String valueInput = inputScanner.nextLine();
        System.out.println("Choose Category for this expense: ");
        int counter = 1;
        for (String category : mainManager.getCurrentAccount().getCategoryList()) {
            System.out.println("<" + counter + "> " + category);
            counter++;
        }
        System.out.println("Enter the number corresponding to a Category: ");
        String categoryInput = inputScanner.nextLine();
        System.out.println("Year of Transaction: ");
        String yearInput = inputScanner.nextLine();
        System.out.println("Month of Transaction (1-12): ");
        String monthInput = inputScanner.nextLine();
        System.out.println("Day of Transaction (1-31): ");
        String dayInput = inputScanner.nextLine();

        mainManager.getCurrentAccount().addExpense(
                new Expense(nameInput,
                        parseDouble(valueInput),
                        LocalDate.of(parseInt(yearInput), parseInt(monthInput), parseInt(dayInput)),
                        mainManager.getCurrentAccount().getCategory((parseInt(categoryInput) - 1))));
    }

    // EFFECTS: Remove an expense from the account
    private void removeExpenseMenu() {
        System.out.println("Here is the list of expenses:");
        int counter = 1;
        for (Expense expense : mainManager.getCurrentAccount().getExpenseList()) {
            System.out.println("<" + counter + "> " + expense.getDisplayName() + " | " + expense.getValue());
            counter++;
        }
        System.out.println("Please choose a number corresponding to an expense to remove:");
        String userInput = inputScanner.nextLine();
        mainManager.getCurrentAccount().removeExpense((parseInt(userInput) - 1));
    }

    // EFFECTS: adds a new income item to the menu
    private void addIncomeMenu() {
        System.out.println("Enter Name of Income: ");
        String nameInput = inputScanner.nextLine();
        System.out.println("Enter Value of the Income: ");
        String valueInput = inputScanner.nextLine();
        System.out.println("Year of Transaction: ");
        String yearInput = inputScanner.nextLine();
        System.out.println("Month of Transaction (1-12): ");
        String monthInput = inputScanner.nextLine();
        System.out.println("Day of Transaction (1-31): ");
        String dayInput = inputScanner.nextLine();

        mainManager.getCurrentAccount().addIncome(new Income(nameInput, parseDouble(valueInput),
                LocalDate.of(parseInt(yearInput), parseInt(monthInput), parseInt(dayInput))));
    }

    // EFFECTS: Remove an income from the account
    private void removeIncomeMenu() {
        System.out.println("Here is the list of incomes:");
        int counter = 1;
        for (Income income : mainManager.getCurrentAccount().getIncomeList()) {
            System.out.println("<" + counter + "> " + income.getDisplayName() + " | " + income.getValue());
            counter++;
        }
        System.out.println("Please choose a number corresponding to an income to remove:");
        String userInput = inputScanner.nextLine();
        mainManager.getCurrentAccount().removeIncome((parseInt(userInput) - 1));
    }


    // EFFECTS: Prompts user to add or remove categories
    private void categoryMenu() {
        String userInput = "";

        while (userInput.equals("")) {
            System.out.println("Please choose an option below for categories:\n");
            System.out.println("<1> Add");
            System.out.println("<2> Remove\n");
            System.out.println("Please choose an option: ");
            userInput = inputScanner.nextLine();

            if (userInput.equals("1")) {
                addCategoriesMenu();
                break;
            } else if (userInput.equals("2")) {
                removeCategoriesMenu();
                break;
            } else {
                System.out.println("error");
                userInput = "";
            }
        }
    }


    // EFFECTS: adds a new category item to the menu
    private void addCategoriesMenu() {
        boolean result = false;

        while (result == false) {
            System.out.println("Enter Name of a Category to be added: ");
            String userInput = inputScanner.nextLine();

            result = mainManager.getCurrentAccount().addCategory(userInput);
            if (result == true) {
                System.out.println("Success!");
                break;
            } else {
                System.out.println("Error! Name is already in the list, please a different one!");
            }
        }
    }

    // EFFECTS: Remove an category from the account
    private void removeCategoriesMenu() {
        System.out.println("Here is the list of Categories:");
        int counter = 1;
        for (String category : mainManager.getCurrentAccount().getCategoryList()) {
            System.out.println("<" + counter + "> " + category);
            counter++;
        }
        System.out.println("Please choose a number corresponding to an income to remove:");
        String userInput = inputScanner.nextLine();
        mainManager.getCurrentAccount().removeCategory((parseInt(userInput) - 1));
    }


    // EFFECTS: Prints a list of transactions from a chosen date to present
    private void timeframeMenu() {
        System.out.println("\nPlease enter a date to view transactions from (date to now)");
        System.out.println("Select a year:");
        String yearInput = inputScanner.nextLine();
        System.out.println("Select a month (1-12):");
        String monthInput = inputScanner.nextLine();
        System.out.println("Select a day (1-31):");
        String dayInput = inputScanner.nextLine();

        printTimeFrameMenu(parseInt(yearInput), parseInt(monthInput), parseInt(dayInput));
        System.out.println("Enter Any key to go back to the main menu");
        String temp = inputScanner.nextLine();
    }


    // EFFECTS: Prints a list of transactions from a chosen date to present
    private void printTimeFrameMenu(int year, int month, int day) {
        System.out.println("\n\nFrom " + LocalDate.of(year, month, day) + " to present");
        printExpenses(mainManager.getCurrentAccount().getExpenseListDated(year, month, day));
        printIncomes(mainManager.getCurrentAccount().getIncomeListDated(year, month, day));
    }

    // EFFECTS: Prints list of expenses
    private void printExpenses(ArrayList<Expense> expenses) {
        double totalExpenses = 0;
        System.out.println("Expenses History (Unordered)");
        System.out.println("__________________________________________________");
        for (Transaction currentTransaction : expenses) {
            System.out.println(currentTransaction.getDisplayName());
            totalExpenses += currentTransaction.getValue();
        }
        System.out.println("__________________________________________________");
        System.out.println("Total Amount Expenses: $" + totalExpenses + "\n");
    }

    // EFFECTS: Prints list of incomes
    private void printIncomes(ArrayList<Income> incomes) {
        double totalIncome = 0;
        System.out.println("Income History (Unordered)");
        System.out.println("__________________________________________________");
        for (Transaction currentTransaction : incomes) {
            System.out.println(currentTransaction.getDisplayName());
            totalIncome += currentTransaction.getValue();
        }
        System.out.println("__________________________________________________");
        System.out.println("Total Amount Income: $" + totalIncome);
    }


}
