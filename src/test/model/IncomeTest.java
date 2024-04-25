package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IncomeTest {

    private Income exampleIncome;

    @BeforeEach
    void setUp() {
        exampleIncome = new Income(
                "Work Salary",
                1000,
                LocalDate.of(2023, 1, 1));
    }

    @Test
    void testConstructor() {
        assertEquals(exampleIncome.getName(), "Work Salary");
        assertEquals(exampleIncome.getValue(), 1000);
        assertEquals(exampleIncome.getDate(), LocalDate.of(2023, 1, 1));
    }

    @Test
    void testSetValueOnce() {
        exampleIncome.setValue(1);
        assertEquals(exampleIncome.getValue(), 1);
    }

    @Test
    void testSetValueTwice() {
        exampleIncome.setValue(1);
        assertEquals(exampleIncome.getValue(), 1);
        exampleIncome.setValue(2);
        assertEquals(exampleIncome.getValue(), 2);
    }

    @Test
    void testGetValue() {
        assertEquals(exampleIncome.getValue(), 1000);
    }

    @Test
    void testSetNameOnce() {
        exampleIncome.setName("Forex");
        assertEquals(exampleIncome.getName(), "Forex");
    }

    @Test
    void testSetNameTwice() {
        exampleIncome.setName("Small Business");
        assertEquals(exampleIncome.getName(), "Small Business");
        exampleIncome.setName("Company");
        assertEquals(exampleIncome.getName(), "Company");
    }


    @Test
    void testGetName() {
        assertEquals(exampleIncome.getName(), "Work Salary");
    }

    @Test
    void testSetDateOnce() {
        exampleIncome.setDate(LocalDate.of(2028, 1, 1));
        assertEquals(exampleIncome.getDate(), LocalDate.of(2028, 1, 1));
    }

    @Test
    void testSetDateTwice() {
        exampleIncome.setDate(LocalDate.of(2020, 1, 1));
        assertEquals(exampleIncome.getDate(), LocalDate.of(2020, 1, 1));
        exampleIncome.setDate(LocalDate.of(2028, 8, 8));
        assertEquals(exampleIncome.getDate(), LocalDate.of(2028, 8, 8));
    }

    @Test
    void testGetDate() {
        assertEquals(exampleIncome.getDate(), LocalDate.of(2023, 1, 1));
    }

    @Test
    void testGetDisplayName() {
        assertEquals(exampleIncome.getDisplayName(), "Work Salary | 2023-01-01 | 1000.0");
    }

    @Test
    void testToJson() {
        JSONObject json = new JSONObject();

        json.put("name", exampleIncome.getName());
        json.put("value", exampleIncome.getValue());
        json.put("date", exampleIncome.getDate().toString());

        assertTrue(exampleIncome.toJson().similar(json));
    }


}