package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionTest {

    private Transaction exampleIncome;

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
        exampleIncome.setName("Stocks");
        assertEquals(exampleIncome.getName(), "Stocks");
    }

    @Test
    void testSetNameTwice() {
        exampleIncome.setName("Stocks");
        assertEquals(exampleIncome.getName(), "Stocks");
        exampleIncome.setName("Forex");
        assertEquals(exampleIncome.getName(), "Forex");
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


}