package persistence;

import model.AccountManager;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    @Test
    void testWhenFileIsNotFound() {
        JsonReader loader = new JsonReader("./data/nooooo.json");
        try {
            AccountManager tester = loader.loadAccountManager();
            fail("Error should have been thrown");
        } catch (IOException e) {
            System.out.println("Pass!");
        }
    }

    @Test
    void testWhenAccountManagerEmpty() {
        JsonReader loader = new JsonReader("./data/testEmptyAccountManager.json");
        try {
            AccountManager tester = loader.loadAccountManager();
            System.out.println("Pass!");

        } catch (IOException e) {
            fail("Should not have thrown exception!");
        }
    }

    @Test
    void testWhenAccountManagerHasItems() {
        JsonReader loader = new JsonReader("./data/testTwoUserAccountManager.json");
        try {
            AccountManager tester = loader.loadAccountManager();
            assertEquals(2, tester.getAccountList().size());
            assertEquals("John", tester.getAccountList().get(0).getUsername());
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
            assertEquals("CPSC 210 TA", tester.getAccountList().get(0).getIncomeList().get(0).getName());
            assertEquals(1000, tester.getAccountList().get(0).getIncomeList().get(0).getValue());

            assertEquals("Essentials", tester.getAccountList().get(0).getCategory(0));
            assertEquals("Personal", tester.getAccountList().get(0).getCategory(1));
            assertEquals("Social", tester.getAccountList().get(0).getCategory(2));
            assertEquals("Career", tester.getAccountList().get(0).getCategory(3));
            assertEquals("UBC", tester.getAccountList().get(0).getCategory(4));

            assertEquals("Hazel", tester.getAccountList().get(1).getUsername());
            assertEquals("a", tester.getAccountList().get(1).getPassword());
            assertEquals(0, tester.getAccountList().get(1).getExpenseList().size());
            assertEquals(0, tester.getAccountList().get(1).getIncomeList().size());
            assertEquals(4, tester.getAccountList().get(1).getCategoryList().size());

            assertEquals("Essentials", tester.getAccountList().get(1).getCategory(0));
            assertEquals("Personal", tester.getAccountList().get(1).getCategory(1));
            assertEquals("Social", tester.getAccountList().get(1).getCategory(2));
            assertEquals("Career", tester.getAccountList().get(1).getCategory(3));

        } catch (IOException e) {
            fail("Should not have thrown exception!");
        }
    }

}
