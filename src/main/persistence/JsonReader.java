package persistence;

import model.Account;
import model.AccountManager;
import model.Expense;
import model.Income;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Stream;

// Represents the reader that helps the application read JSON data when user requests
public class JsonReader {
    private String path;

    // EFFECTS: Creates an instance of reader which reads from path
    public JsonReader(String path) {
        this.path = path;
    }

    
    // EFFECTS: loads source file as string and returns it
    private String loadFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }


    // EFFECTS: Loads in the account manager from path and returns it
    public AccountManager loadAccountManager() throws IOException {
        String contentFromFile = loadFile(path);
        JSONObject json = new JSONObject(contentFromFile);

        return makeAccountManager(json);
    }

    // EFFECTS: Makes the account manager from the json content
    private AccountManager makeAccountManager(JSONObject json) {
        AccountManager accountManager = new AccountManager();

        JSONArray jsonArray = json.getJSONArray("accountList");
        for (Object account : jsonArray) {
            JSONObject accountObj = (JSONObject) account;
            Account accountInstance = makeAccount(accountObj);

            accountManager.getAccountList().add(accountInstance);
        }
        accountManager.loadAccountsLog();
        return accountManager;
    }

    // EFFECT: Turns the json object into an account instance
    private Account makeAccount(JSONObject json) {
        String username = json.getString("username");
        String password  = json.getString("password");

        Account account = new Account(username, password);

        ArrayList<String> categoryList = makeCategoryList(json);
        ArrayList<Income> incomeList = makeIncomeList(json);
        ArrayList<Expense> expenseList = makeExpenseList(json);

        ArrayList<String> tempCategory = account.getCategoryList();
        ArrayList<Income> tempIncome = account.getIncomeList();
        ArrayList<Expense> tempExpense = account.getExpenseList();

        tempCategory.clear();
        tempCategory.addAll(categoryList);
        tempIncome.addAll(incomeList);
        tempExpense.addAll(expenseList);

        return account;
    }

    // EFFECTS: makes the category list from the json object and returns it
    private ArrayList<String> makeCategoryList(JSONObject json) {
        JSONArray jsonArray = json.getJSONArray("categoryList");
        ArrayList<String> categoryList = new ArrayList<>();

        for (Object categoryJson : jsonArray) {
            JSONObject category = (JSONObject) categoryJson;
            categoryList.add(category.getString("category"));
        }
        return categoryList;
    }


    // EFFECTS: makes the income list from the json object and returns it
    private ArrayList<Income> makeIncomeList(JSONObject json) {
        JSONArray jsonArray = json.getJSONArray("incomeList");
        ArrayList<Income> incomeList = new ArrayList<>();

        for (Object incomeJson : jsonArray) {
            JSONObject incomeObj = (JSONObject) incomeJson;
            incomeList.add(makeIncome(incomeObj));
        }
        return incomeList;
    }

    // EFFECTS: Makes the income instance from the json object and returns it
    private Income makeIncome(JSONObject json) {
        String name = json.getString("name");
        double value = json.getDouble("value");
        LocalDate date = LocalDate.parse(json.getString("date"));

        return new Income(name, value, date);
    }


    // EFFECTS: makes the expense list from the json object and returns it
    private ArrayList<Expense> makeExpenseList(JSONObject json) {
        JSONArray jsonArray = json.getJSONArray("expenseList");
        ArrayList<Expense> expenseList = new ArrayList<>();

        for (Object expenseJson : jsonArray) {
            JSONObject expenseObj = (JSONObject) expenseJson;
            expenseList.add(makeExpense(expenseObj));
        }

        return expenseList;
    }

    // EFFECTS: Makes the income instance from the json object and returns it
    private Expense makeExpense(JSONObject json) {
        String name = json.getString("name");
        double value = json.getDouble("value");
        String category = json.getString("category");
        LocalDate date = LocalDate.parse(json.getString("date"));

        return new Expense(name, value, date, category);
    }





}
