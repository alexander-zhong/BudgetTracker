package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountTest {

    private Account accountTester1;
    private Expense testExpense1;
    private Expense testExpense2;
    private Expense testExpense3;
    private Income testIncome1;
    private Income testIncome2;
    private Income testIncome3;

    @BeforeEach
    void setUp() {
        accountTester1 = new Account("John", "CPSC210");

        testExpense1 = new Expense(
                "Food",
                100,
                LocalDate.of(2020, 1, 1),
                "Essentials");

        testExpense2 = new Expense(
                "Bike",
                100,
                LocalDate.of(2021, 1, 1),
                "Essentials");

        testExpense3 = new Expense(
                "Car",
                1000,
                LocalDate.of(2022, 1, 1),
                "Essentials");

        testIncome1 = new Income("Work Salary",
                1000000,
                LocalDate.of(2025, 1, 1));

        testIncome2 = new Income("Stocks",
                100,
                LocalDate.of(2023, 1, 1));

        testIncome3 = new Income("Company",
                1000,
                LocalDate.of(2028, 1, 1));

    }

    @Test
    void testConstructor() {
        assertEquals(accountTester1.getUsername(), "John");
        assertEquals(accountTester1.getPassword(), "CPSC210");
    }

    @Test
    void testInCategoryListWhenTrue() {
        assertEquals(accountTester1.inCategoryList("Social"), true);
    }

    @Test
    void testInCategoryListWhenFalse() {
        assertEquals(accountTester1.inCategoryList("JustForFun"), false);
    }

    @Test
    void testAddCategoryWhenFalse() {
        assertEquals(accountTester1.inCategoryList("JustForFun"), false);
        accountTester1.addCategory("JustForFun");
        assertEquals(accountTester1.addCategory("JustForFun"), false);
    }

    @Test
    void testAddCategoryOnce() {
        assertEquals(accountTester1.inCategoryList("JustForFun"), false);
        accountTester1.addCategory("JustForFun");
        assertEquals(accountTester1.inCategoryList("JustForFun"), true);
    }

    @Test
    void testAddCategoryTwice() {
        assertEquals(accountTester1.inCategoryList("JustForFun"), false);
        accountTester1.addCategory("JustForFun");
        assertEquals(accountTester1.inCategoryList("JustForFun"), true);

        assertEquals(accountTester1.inCategoryList("Books"), false);
        accountTester1.addCategory("Books");
        assertEquals(accountTester1.inCategoryList("Books"), true);
    }

    @Test
    void testGetCategory() {
        assertEquals(accountTester1.getCategory(0), "Essentials");
    }

    @Test
    void testRemoveCategoryOnce() {
        assertEquals(accountTester1.inCategoryList("Essentials"), true);
        accountTester1.removeCategory(0);
        assertEquals(accountTester1.inCategoryList("Essentials"), false);

    }

    @Test
    void testRemoveCategoryTwice() {
        assertEquals(accountTester1.inCategoryList("Essentials"), true);
        accountTester1.removeCategory(0);
        assertEquals(accountTester1.inCategoryList("Essentials"), false);

        assertEquals(accountTester1.inCategoryList("Personal"), true);
        accountTester1.removeCategory(0);
        assertEquals(accountTester1.inCategoryList("Personal"), false);
    }

    @Test
    void testAddExpenseOnce() {
        ArrayList<Expense> testList = new ArrayList<>();
        testList.add(testExpense1);

        accountTester1.addExpense(testExpense1);

        assertEquals(accountTester1.getExpenseList(), testList);
    }

    @Test
    void testAddExpenseTwice() {
        ArrayList<Expense> testList = new ArrayList<>();
        testList.add(testExpense1);

        accountTester1.addExpense(testExpense1);

        assertEquals(accountTester1.getExpenseList(), testList);

        testList.add(testExpense2);

        accountTester1.addExpense(testExpense2);

        assertEquals(accountTester1.getExpenseList(), testList);
    }

    @Test
    void testRemoveExpenseOnce() {
        ArrayList<Expense> testList = new ArrayList<>();
        testList.add(testExpense2);

        accountTester1.addExpense(testExpense1);
        accountTester1.addExpense(testExpense2);

        accountTester1.removeExpense(0);

        assertEquals(accountTester1.getExpenseList(), testList);
    }

    @Test
    void testRemoveExpenseTwice() {
        ArrayList<Expense> testList = new ArrayList<>();

        accountTester1.addExpense(testExpense1);
        accountTester1.addExpense(testExpense2);

        accountTester1.removeExpense(0);
        accountTester1.removeExpense(0);

        assertEquals(accountTester1.getExpenseList(), testList);
    }

    @Test
    void testAddIncomeOnce() {
        ArrayList<Income> testList = new ArrayList<>();
        testList.add(testIncome1);

        accountTester1.addIncome(testIncome1);

        assertEquals(accountTester1.getIncomeList(), testList);
    }

    @Test
    void testAddIncomeTwice() {
        ArrayList<Income> testList = new ArrayList<>();
        testList.add(testIncome1);

        accountTester1.addIncome(testIncome1);

        assertEquals(accountTester1.getIncomeList(), testList);

        testList.add(testIncome2);

        accountTester1.addIncome(testIncome2);

        assertEquals(accountTester1.getIncomeList(), testList);
    }

    @Test
    void testRemoveIncomeOnce() {
        ArrayList<Income> testList = new ArrayList<>();
        testList.add(testIncome2);

        accountTester1.addIncome(testIncome1);
        accountTester1.addIncome(testIncome2);

        accountTester1.removeIncome(0);

        assertEquals(accountTester1.getIncomeList(), testList);
    }

    @Test
    void testRemoveIncomeTwice() {
        ArrayList<Income> testList = new ArrayList<>();

        accountTester1.addIncome(testIncome1);
        accountTester1.addIncome(testIncome2);

        accountTester1.removeIncome(1);
        accountTester1.removeIncome(0);


        assertEquals(accountTester1.getIncomeList(), testList);
    }

    @Test
    void testGetUsername() {
        assertEquals(accountTester1.getUsername(), "John");
    }

    @Test
    void testSetUsername() {
        assertEquals(accountTester1.getUsername(), "John");
        accountTester1.setUsername("Felix");
        assertEquals(accountTester1.getUsername(), "Felix");
    }

    @Test
    void testGetPassword() {
        assertEquals(accountTester1.getPassword(), "CPSC210");
    }

    @Test
    void testSetPassword() {
        assertEquals(accountTester1.getPassword(), "CPSC210");
        accountTester1.setPassword("CPSC21000");
        assertEquals(accountTester1.getPassword(), "CPSC21000");
    }

    @Test
    void testGetCategoryList() {
        ArrayList<String> testList = new ArrayList<>();
        testList.add("Essentials");
        testList.add("Personal");
        testList.add("Social");
        testList.add("Career");

        assertEquals(accountTester1.getCategoryList(), testList);
    }


    @Test
    void testGetExpenseList() {

        ArrayList<Expense> testList = new ArrayList<>();
        testList.add(testExpense1);
        testList.add(testExpense2);

        accountTester1.addExpense(testExpense1);
        accountTester1.addExpense(testExpense2);

        assertEquals(accountTester1.getExpenseList(), testList);
    }


    @Test
    void testGetIncomeList() {

        ArrayList<Income> testList = new ArrayList<>();
        testList.add(testIncome1);
        testList.add(testIncome2);

        accountTester1.addIncome(testIncome1);
        accountTester1.addIncome(testIncome2);

        assertEquals(accountTester1.getIncomeList(), testList);
    }


    @Test
    void testGetExpenseListDated() {

        ArrayList<Expense> testList = new ArrayList<>();
        testList.add(testExpense2);

        accountTester1.addExpense(testExpense1);
        accountTester1.addExpense(testExpense2);

        assertEquals(accountTester1.getExpenseListDated(2020, 8, 8).size(), 1);
    }

    @Test
    void testGetExpenseListDatedWhenYearEqualsButMonthGreater() {
        ArrayList<Expense> testList = new ArrayList<>();
        testList.add(testExpense2);
        testExpense1.setDate(LocalDate.of(2020, 9, 2));
        testExpense2.setDate(LocalDate.of(2019, 1, 2));
        accountTester1.addExpense(testExpense1);
        accountTester1.addExpense(testExpense2);

        assertEquals(accountTester1.getExpenseListDated(2020, 8, 8).size(), 1);
    }

    @Test
    void testGetExpenseListDatedWhenYearEqualsAndMonthEqualButDaysGreater() {
        ArrayList<Expense> testList = new ArrayList<>();
        testList.add(testExpense2);
        testExpense1.setDate(LocalDate.of(2020, 8, 9));
        testExpense2.setDate(LocalDate.of(2019, 1, 2));
        accountTester1.addExpense(testExpense1);
        accountTester1.addExpense(testExpense2);

        assertEquals(accountTester1.getExpenseListDated(2020, 8, 8).size(), 1);
    }

    @Test
    void testGetExpenseListDatedWhenDoesNotMatchCondition() {
        ArrayList<Expense> testList = new ArrayList<>();
        testList.add(testExpense2);
        testExpense1.setDate(LocalDate.of(2020, 8, 1));
        testExpense2.setDate(LocalDate.of(2020, 1, 2));
        testExpense3.setDate(LocalDate.of(2019, 1, 2));
        accountTester1.addExpense(testExpense1);
        accountTester1.addExpense(testExpense2);

        assertEquals(accountTester1.getExpenseListDated(2020, 8, 8).size(), 0);
    }


    @Test
    void testGetIncomeListDated() {

        ArrayList<Income> testList = new ArrayList<>();
        testList.add(testIncome2);

        accountTester1.addIncome(testIncome1);
        accountTester1.addIncome(testIncome2);

        assertEquals(accountTester1.getIncomeListDated(2023, 8, 8).size(), 1);
    }

    @Test
    void testGetIncomeListDatedWhenYearEqualsButMonthGreater() {
        ArrayList<Income> testList = new ArrayList<>();
        testList.add(testIncome2);
        testIncome1.setDate(LocalDate.of(2020, 9, 2));
        testIncome2.setDate(LocalDate.of(2019, 1, 2));
        accountTester1.addIncome(testIncome1);
        accountTester1.addIncome(testIncome2);

        assertEquals(accountTester1.getIncomeListDated(2020, 8, 8).size(), 1);
    }

    @Test
    void testGetIncomeListDatedWhenYearEqualsAndMonthEqualButDaysGreater() {
        ArrayList<Income> testList = new ArrayList<>();
        testList.add(testIncome2);
        testIncome1.setDate(LocalDate.of(2020, 8, 9));
        testIncome2.setDate(LocalDate.of(2019, 1, 2));
        accountTester1.addIncome(testIncome1);
        accountTester1.addIncome(testIncome2);

        assertEquals(accountTester1.getIncomeListDated(2020, 8, 8).size(), 1);
    }

    @Test
    void testGetIncomeListDatedWhenDoesNotMatchCondition() {
        ArrayList<Income> testList = new ArrayList<>();
        testList.add(testIncome2);
        testIncome1.setDate(LocalDate.of(2020, 8, 1));
        testIncome2.setDate(LocalDate.of(2020, 1, 2));
        testIncome3.setDate(LocalDate.of(2019, 1, 2));
        accountTester1.addIncome(testIncome1);
        accountTester1.addIncome(testIncome2);

        assertEquals(accountTester1.getIncomeListDated(2020, 8, 8).size(), 0);
    }

    @Test
    void testToJson() {
        JSONObject json = new JSONObject();

        accountTester1.addExpense(testExpense1);

        json.put("username", "John");
        json.put("password", "CPSC210");
        json.put("categoryList", accountTester1.categoryListToJson());
        json.put("incomeList", accountTester1.incomeListToJson());
        json.put("expenseList", accountTester1.expenseListToJson());

        assertTrue(accountTester1.toJson().similar(json));

    }


    @Test
    void testCategoryListToJson() {
        JSONArray json = new JSONArray();

        for (String category : accountTester1.getCategoryList()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("category", category);
            json.put(jsonObject);
        }

        assertTrue(accountTester1.categoryListToJson().similar(json));

    }

    @Test
    void testIncomeListToJson() {
        JSONArray json = new JSONArray();

        accountTester1.addIncome(testIncome1);
        accountTester1.addIncome(testIncome2);

        for (Income income : accountTester1.getIncomeList()) {
            json.put(income.toJson());
        }

        assertTrue(accountTester1.incomeListToJson().similar(json));

    }

    @Test
    void testExpenseListToJson() {
        JSONArray json = new JSONArray();

        accountTester1.addExpense(testExpense1);
        accountTester1.addExpense(testExpense2);

        for (Expense expense : accountTester1.getExpenseList()) {
            json.put(expense.toJson());
        }

        assertTrue(accountTester1.expenseListToJson().similar(json));

    }



}


