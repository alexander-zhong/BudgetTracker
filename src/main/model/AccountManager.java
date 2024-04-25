package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonWritable;

import java.util.ArrayList;

// Manages the list of accounts
public class AccountManager implements JsonWritable {
    private ArrayList<Account> accountList;
    private Account currentAccount;
    private boolean loggedIn;
    private EventLog eventLogger;

    public AccountManager() {
        accountList = new ArrayList<>();
        loggedIn = false;
        eventLogger = EventLog.getInstance();
    }

    // EFFECTS: Returns true if there's given username that matches the password given in the list
    //          Returns false if the username is not found or the password does not match
    public boolean login(String username, String password) {
        for (Account currentAccount : accountList) {
            if (currentAccount.getUsername().equals(username) && currentAccount.getPassword().equals(password)) {
                this.currentAccount = currentAccount;
                this.loggedIn = true;
                eventLogger.logEvent(new Event("Successful Logged in into account username: "
                        + currentAccount.getUsername()));
                return true;
            }
        }
        eventLogger.logEvent(new Event("Failed login attempt with username: " + username));
        return false;
    }

    // EFFECTS: Logs the user out by turning the currentAccount as null
    public void logout() {
        eventLogger.logEvent(new Event("Logged out of " + currentAccount.getUsername() + "."));
        currentAccount = null;
        loggedIn = false;
    }

    // EFFECTS: Registers the user into the database,
    //          If username not in list and password == confirmPassword, adds new account to list and return true,
    //          if user in list or password != confirmPassword, return false
    public boolean register(String username, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            eventLogger.logEvent(new Event("Failed registration due to non matching password with username: "
                    + username));
            return false;
        }

        for (Account currentAccount : accountList) {
            if (currentAccount.getUsername().equals(username)) {
                eventLogger.logEvent(new Event("Failed registration due to username already existing: "
                        + username));
                return false;
            }
        }

        accountList.add(new Account(username, password));
        eventLogger.logEvent(new Event("Successfully registered username: " + username));
        return true;
    }

    // EFFECTS: Returns the account list
    public ArrayList<Account> getAccountList() {
        return this.accountList;
    }

    // EFFECTS: Returns the current account (null if there's none)
    public Account getCurrentAccount() {
        return this.currentAccount;
    }

    // EFFECTS: return loggedIn boolean
    public boolean isLoggedIn() {
        return this.loggedIn;
    }

    // Modeled with https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: Converts the account manager into a readable jsonObject
    @Override
    public JSONObject toJson() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("accountList", accountToJsonArray());
        eventLogger.logEvent(new Event("Saved accounts from the database."));

        return jsonObj;
    }

    // Modeled with https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: Converts the list of accounts into an Json Array
    public JSONArray accountToJsonArray() {
        JSONArray jsonArray = new JSONArray();

        for (Account currentAccount : this.accountList) {
            jsonArray.put(currentAccount.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: logs that accounts have been logged
    public void loadAccountsLog() {
        eventLogger.logEvent(new Event("Loaded accounts from the database."));
    }



}
