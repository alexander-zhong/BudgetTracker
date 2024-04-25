package model;


import jdk.jfr.Category;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonWritable;

import java.util.ArrayList;


// Representing each user's personal accounts
public class Account implements JsonWritable {

    private String username;
    private String password;

    private ArrayList<String> categoryList;
    private ArrayList<Expense> expenseList;
    private ArrayList<Income> incomeList;

    private EventLog eventLogger;

    // REQUIRES: username not have space in it, password not have space in it
    // EFFECTS: Instantiates a Account class
    public Account(String username, String password) {
        this.username = username;
        this.password = password;

        categoryList = new ArrayList<>();
        categoryList.add("Essentials");
        categoryList.add("Personal");
        categoryList.add("Social");
        categoryList.add("Career");

        expenseList = new ArrayList<>();
        incomeList = new ArrayList<>();
        eventLogger = EventLog.getInstance();
    }

    // EFFECTS: Returns true if the category is in the list,
    //          returns false if the category is not in the list
    public boolean inCategoryList(String category) {
        for (String currentCategory : categoryList) {
            if (currentCategory.equals(category)) {
                return true;
            }
        }
        return false;
    }

    // REQUIRES: category.length() > 0 && inCategoryList(category) == false
    // MODIFIES: this
    // EFFECTS: Adds a new Category to the list
    public boolean addCategory(String category) {
        if (inCategoryList(category)) {
            eventLogger.logEvent(new Event("Failed to add category due to duplicate."));
            return false;
        }
        categoryList.add(category);
        return true;
    }

    // REQUIRES: 0 <= index <= this.categoryList.size()
    // EFFECTS: Finds the category of the given index
    public String getCategory(int index) {
        return categoryList.get(index);
    }

    // REQUIRES: 0 <= index <= this.categoryList.size()
    // MODIFIES: this
    // EFFECTS: Removes a Category to the list
    public void removeCategory(int index) {
        eventLogger.logEvent(new Event("Removed " + categoryList.get(index) + " from categories."));
        categoryList.remove(index);
    }

    // MODIFIES: this
    // EFFECTS: Adds a new expense to the list
    public void addExpense(Expense expense) {
        eventLogger.logEvent(new Event("Added " + expense.getName() + " to expenses."));
        this.expenseList.add(expense);
    }

    // REQUIRES: 0 <= index <= this.expenseList.size()
    // MODIFIES: this
    // EFFECTS: Remove expense from the list with the given index
    public void removeExpense(int index) {
        eventLogger.logEvent(new Event("Removed " + expenseList.get(index).getName() + " from expenses."));
        this.expenseList.remove(index);
    }

    // MODIFIES: this
    // EFFECTS: Adds a new income to the list
    public void addIncome(Income income) {
        eventLogger.logEvent(new Event("Added " + income.getName() + " to incomes."));
        this.incomeList.add(income);
    }

    // REQUIRES: 0 <= index <= this.incomeList.size()
    // MODIFIES: this
    // EFFECTS: Remove income from the list with the given index
    public void removeIncome(int index) {
        eventLogger.logEvent(new Event("Removed " + incomeList.get(index).getName() + " from incomes."));
        this.incomeList.remove(index);
    }

    // EFFECTS: Returns the username
    public String getUsername() {
        return this.username;
    }

    // REQUIRES: username.length() > 0
    // MODIFIES: this
    // EFFECTS: Sets the new username
    public void setUsername(String username) {
        this.username = username;
    }

    // EFFECTS: Returns the password
    public String getPassword() {
        return this.password;
    }

    // REQUIRES: password.length() > 0
    // MODIFIES: this
    // EFFECTS: Sets the new password
    public void setPassword(String password) {
        this.password = password;
    }


    // EFFECTS: Return the categoryList
    public ArrayList<String> getCategoryList() {
        return this.categoryList;
    }

    // EFFECTS: Return the expenseList
    public ArrayList<Expense> getExpenseList() {
        return this.expenseList;
    }

    // EFFECTS: Return the incomeList
    public ArrayList<Income> getIncomeList() {
        return this.incomeList;
    }

    // EFFECTS: Return the list of expenses after the given date
    public ArrayList<Expense> getExpenseListDated(int year, int month, int day) {
        ArrayList<Expense> currentList = new ArrayList<>();
        eventLogger.logEvent(new Event("Viewed expenses from year: "
                + year + ", month: " + month + ", day: " + day + " to now."));
        for (Expense expense : expenseList) {
            if (expense.getDate().getYear() > year) {
                currentList.add(expense);
            } else if (expense.getDate().getYear() == year) {
                if (expense.getDate().getMonthValue() > month) {
                    currentList.add(expense);
                } else if (expense.getDate().getMonthValue() == month) {
                    if (expense.getDate().getDayOfMonth() >= day) {
                        currentList.add(expense);
                    }
                }
            }
        }

        return currentList;
    }


    // EFFECTS: Return the list of income after the given date
    public ArrayList<Income> getIncomeListDated(int year, int month, int day) {
        ArrayList<Income> currentList = new ArrayList<>();
        eventLogger.logEvent(new Event("Viewed incomes from year: "
                + year + ", month: " + month + ", day: " + day + " to now."));
        for (Income income : incomeList) {
            if (income.getDate().getYear() > year) {
                currentList.add(income);
            } else if (income.getDate().getYear() == year) {
                if (income.getDate().getMonthValue() > month) {
                    currentList.add(income);
                } else if (income.getDate().getMonthValue() == month) {
                    if (income.getDate().getDayOfMonth() >= day) {
                        currentList.add(income);
                    }
                }
            }
        }

        return currentList;
    }

    // EFFECTS: Turns account into JSON format
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("username", this.username);
        json.put("password", this.password);
        json.put("categoryList", categoryListToJson());
        json.put("incomeList", incomeListToJson());
        json.put("expenseList", expenseListToJson());

        return json;
    }

    // EFFECTS: Turns categoryList to json format
    public JSONArray categoryListToJson() {
        JSONArray json = new JSONArray();

        for (String category : this.categoryList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("category", category);
            json.put(jsonObject);
        }
        return json;
    }


    // EFFECTS: Turns incomeList to json format
    public JSONArray incomeListToJson() {
        JSONArray json = new JSONArray();

        for (Income income : this.incomeList) {
            json.put(income.toJson());
        }
        return json;
    }


    // EFFECTS: Turns categoryList to json format
    public JSONArray expenseListToJson() {
        JSONArray json = new JSONArray();

        for (Expense expense : this.expenseList) {
            json.put(expense.toJson());
        }
        return json;
    }

}
