package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExpenseTest {

    private Expense exampleExpense;

    @BeforeEach
    void setUp() {
        exampleExpense = new Expense(
                "Apple Iphone",
                1000,
                LocalDate.of(2023, 1, 1),
                "Fun");

    }

    @Test
    void testConstructor() {
        assertEquals(exampleExpense.getName(), "Apple Iphone");
        assertEquals(exampleExpense.getValue(), 1000);
        assertEquals(exampleExpense.getDate(), LocalDate.of(2023, 1, 1));
        assertEquals(exampleExpense.getCategory(), "Fun");
    }

    @Test
    void testSetValueOnce() {
        exampleExpense.setValue(1);
        assertEquals(exampleExpense.getValue(), 1);
    }

    @Test
    void testSetValueTwice() {
        exampleExpense.setValue(1);
        assertEquals(exampleExpense.getValue(), 1);
        exampleExpense.setValue(2);
        assertEquals(exampleExpense.getValue(), 2);
    }

    @Test
    void testGetValue() {
        assertEquals(exampleExpense.getValue(), 1000);
    }

    @Test
    void testSetNameOnce() {
        exampleExpense.setName("Apple IMac");
        assertEquals(exampleExpense.getName(), "Apple IMac");
    }

    @Test
    void testSetNameTwice() {
        exampleExpense.setName("Apple IMac");
        assertEquals(exampleExpense.getName(), "Apple IMac");
        exampleExpense.setName("Ipad");
        assertEquals(exampleExpense.getName(), "Ipad");
    }


    @Test
    void testGetName() {
        assertEquals(exampleExpense.getName(), "Apple Iphone");
    }

    @Test
    void testSetDateOnce() {
        exampleExpense.setDate(LocalDate.of(2028, 1, 1));
        assertEquals(exampleExpense.getDate(), LocalDate.of(2028, 1, 1));
    }

    @Test
    void testSetDateTwice() {
        exampleExpense.setDate(LocalDate.of(2020, 1, 1));
        assertEquals(exampleExpense.getDate(), LocalDate.of(2020, 1, 1));
        exampleExpense.setDate(LocalDate.of(2028, 8, 8));
        assertEquals(exampleExpense.getDate(), LocalDate.of(2028, 8, 8));
    }

    @Test
    void testGetDate() {
        assertEquals(exampleExpense.getDate(), LocalDate.of(2023, 1, 1));
    }

    @Test
    void testGetDisplayName() {
        assertEquals(exampleExpense.getDisplayName(), "Apple Iphone | Fun | 2023-01-01 | 1000.0");
    }

    @Test
    void testGetCategory() {
        assertEquals(exampleExpense.getCategory(), "Fun");
    }

    @Test
    void testSetCategoryOnce() {
        exampleExpense.setCategory("Personal");
        assertEquals(exampleExpense.getCategory(), "Personal");
    }

    @Test
    void testSetCategoryTwice() {
        exampleExpense.setCategory("Personal");
        assertEquals(exampleExpense.getCategory(), "Personal");
        exampleExpense.setCategory("Work");
        assertEquals(exampleExpense.getCategory(), "Work");
    }

    @Test
    void testToJson() {
        JSONObject json = new JSONObject();

        json.put("name", exampleExpense.getName());
        json.put("value", exampleExpense.getValue());
        json.put("category", exampleExpense.getCategory());
        json.put("date", exampleExpense.getDate().toString());

        assertTrue(exampleExpense.toJson().similar(json));
    }

}