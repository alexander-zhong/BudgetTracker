package persistence;

import model.AccountManager;
import model.Expense;
import model.Income;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

    @Test
    void testFileNotFound() {
        try {
            AccountManager tester = new AccountManager();
            JsonWriter writer = new JsonWriter("./data/\0iwwww.json");
            writer.setUp();
            writer.saveAccountManager(tester);
            fail("FileNotFoundException should've been thrown");
        } catch (FileNotFoundException e) {
            System.out.println("passed!");
        }
    }

    @Test
    void testFileEmptyAndWriteEmpty() {
        try {
            AccountManager tester = new AccountManager();
            JsonWriter writer = new JsonWriter("./data/testEmptyFileForWriter.json");
            writer.setUp();
            writer.saveAccountManager(tester);

            JsonReader reader = new JsonReader("./data/testEmptyFileForWriter.json");
            AccountManager testerTwo = reader.loadAccountManager();

            assertEquals(0, testerTwo.getAccountList().size());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }


    @Test
    void testFileWhenNotEmpty() {
        try {
            AccountManager initialTester = new AccountManager();
            initialTester.register("Joe", "CPSC210", "CPSC210");
            Expense exampleExpense = new Expense("Computer", 1000, LocalDate.of(2020, 1, 1), "Essentials");
            Income exampleIncome = new Income("Microsoft", 1000, LocalDate.of(2021, 1, 1));
            initialTester.getAccountList().get(0).addExpense(exampleExpense);
            initialTester.getAccountList().get(0).addIncome(exampleIncome);
            initialTester.getAccountList().get(0).addCategory("UBC");

            JsonWriter writer = new JsonWriter("./data/testWhenNotEmptyForWriter.json");
            writer.setUp();
            writer.saveAccountManager(initialTester);

            JsonReader reader = new JsonReader("./data/testWhenNotEmptyForWriter.json");
            AccountManager tester = reader.loadAccountManager();

            assertEquals("Joe", tester.getAccountList().get(0).getUsername());
            assertEquals("CPSC210", tester.getAccountList().get(0).getPassword());
            assertEquals(1, tester.getAccountList().get(0).getExpenseList().size());
            assertEquals(1, tester.getAccountList().get(0).getIncomeList().size());
            assertEquals(5, tester.getAccountList().get(0).getCategoryList().size());

            assertEquals(LocalDate.of(2020, 1, 1),
                    tester.getAccountList().get(0).getExpenseList().get(0).getDate());
            assertEquals("Computer", tester.getAccountList().get(0).getExpenseList().get(0).getName());
            assertEquals("Essentials", tester.getAccountList().get(0).getExpenseList().get(0).getCategory());
            assertEquals(1000, tester.getAccountList().get(0).getExpenseList().get(0).getValue());

            assertEquals(LocalDate.of(2021, 1, 1), tester.getAccountList().get(0).getIncomeList().get(0).getDate());
            assertEquals("Microsoft", tester.getAccountList().get(0).getIncomeList().get(0).getName());
            assertEquals(1000, tester.getAccountList().get(0).getIncomeList().get(0).getValue());

            assertEquals("Essentials", tester.getAccountList().get(0).getCategory(0));
            assertEquals("Personal", tester.getAccountList().get(0).getCategory(1));
            assertEquals("Social", tester.getAccountList().get(0).getCategory(2));
            assertEquals("Career", tester.getAccountList().get(0).getCategory(3));
            assertEquals("UBC", tester.getAccountList().get(0).getCategory(4));

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}
