package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountManagerTest {


    private AccountManager accountManagerTester;


    @BeforeEach
    void setUp() {
        accountManagerTester = new AccountManager();
    }

    @Test
    void testConstructor() {
        assertEquals(accountManagerTester.getAccountList(), new ArrayList<Account>());
    }

    @Test
    void testLoginWhenTrue() {
        accountManagerTester.register("Felix", "CPSC210", "CPSC210");

        assertEquals(accountManagerTester.login("Felix", "CPSC210"), true);
        assertEquals(accountManagerTester.getCurrentAccount().getUsername(), "Felix");
        assertEquals(accountManagerTester.isLoggedIn(), true);
    }

    @Test
    void testLoginWhenIncorrectUsername() {
        assertEquals(accountManagerTester.login("Felix", "CPSC210"), false);
        assertEquals(accountManagerTester.getAccountList(), new ArrayList<Account>());
        assertEquals(accountManagerTester.isLoggedIn(), false);
    }

    @Test
    void testLoginWhenIncorrectPassword() {
        accountManagerTester.register("Felix", "CPSC210", "CPSC210");
        assertEquals(accountManagerTester.login("Felix", "awdsgafsfejn"), false);
        assertEquals(accountManagerTester.isLoggedIn(), false);
    }

    @Test
    void testLoginWhenCorrectPasswordButIncorrectUsername() {
        accountManagerTester.register("Felix", "CPSC210", "CPSC210");
        assertEquals(accountManagerTester.login("Felixawd", "CPSC210"), false);
        assertEquals(accountManagerTester.isLoggedIn(), false);
    }

    @Test
    void testLogout() {
        accountManagerTester.register("Felix", "CPSC210", "CPSC210");
        assertEquals(accountManagerTester.login("Felix", "CPSC210"), true);
        accountManagerTester.logout();
        assertEquals(accountManagerTester.getCurrentAccount(), null);
        assertEquals(accountManagerTester.isLoggedIn(), false);
    }


    @Test
    void testRegisterWhenCorrectEverything() {
        assertEquals(accountManagerTester.login("Felix", "CPSC210"), false);
        assertEquals(accountManagerTester.register("Felix", "CPSC210", "CPSC210"),
                true);
        accountManagerTester.login("Felix", "CPSC210");
        assertEquals(accountManagerTester.getCurrentAccount().getUsername(), "Felix");
        assertEquals(accountManagerTester.getCurrentAccount().getPassword(), "CPSC210");
        assertEquals(accountManagerTester.isLoggedIn(), true);
    }

    @Test
    void testRegisterWhenUsernameTaken() {
        assertEquals(accountManagerTester.login("Felix", "CPSC210"), false);
        assertEquals(accountManagerTester.register("Felix", "CPSC210", "CPSC210"),
                true);
        assertEquals(accountManagerTester.register("Felix", "CPSC210", "CPSC210"),
                false);
    }

    @Test
    void testRegisterWhenPasswordDoesNotMatch() {
        assertEquals(accountManagerTester.register("Felix", "CPSC210", "afjkegrse"),
                false);
        assertEquals(accountManagerTester.login("Felix", "CPSC210"), false);
    }

    @Test
    void testGetAccountList() {
        accountManagerTester.register("Felix", "CPSC210", "CPSC210");
        accountManagerTester.register("John", "CPSC210", "CPSC210");

        ArrayList<Account> testList = accountManagerTester.getAccountList();

        assertEquals(testList.get(0).getUsername(), "Felix");
        assertEquals(testList.get(1).getUsername(), "John");
    }

    @Test
    void testGetCurrentAccountWhenNull() {
        assertEquals(accountManagerTester.getCurrentAccount(), null);
    }

    @Test
    void testGetCurrentAccountWhenHaveAccount() {
        assertEquals(accountManagerTester.register("Felix", "CPSC210", "CPSC210"),
                true);
        accountManagerTester.login("Felix", "CPSC210");
        assertEquals(accountManagerTester.getCurrentAccount().getUsername(), "Felix");
    }

    @Test
    void testIsLoggedInWhenFalse() {
        assertEquals(accountManagerTester.isLoggedIn(), false);
    }

    @Test
    void testIsLoggedInWhenTrue() {
        assertEquals(accountManagerTester.register("Felix", "CPSC210", "CPSC210"),
                true);
        accountManagerTester.login("Felix", "CPSC210");
        assertEquals(accountManagerTester.isLoggedIn(), true);
    }

    @Test
    void testToJson() {
        assertEquals(accountManagerTester.register("Felix", "CPSC210", "CPSC210"),
                true);

        JSONObject jsonObj = new JSONObject();

        jsonObj.put("accountList", accountManagerTester.accountToJsonArray());

        assertTrue(accountManagerTester.toJson().similar(jsonObj));
    }

    @Test
    void testAccountToJsonArray() {
        assertEquals(accountManagerTester.register("Felix", "CPSC210", "CPSC210"),
                true);

        Account testerAccount = accountManagerTester.getAccountList().get(0);

        JSONArray jsonArray = new JSONArray();

        jsonArray.put(testerAccount.toJson());

        assertTrue(accountManagerTester.accountToJsonArray().similar(accountManagerTester.accountToJsonArray()));
    }

    @Test
    void testLoadAccountsLog() {
        EventLog logTest = EventLog.getInstance();
        accountManagerTester.loadAccountsLog();
        Boolean contain = false;
        for (Event event : logTest) {
            String newString = (String) event.getDescription();
            if (newString.equals("Loaded accounts from the database.")){
                contain = true;
            }
        }
    }


}
