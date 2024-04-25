package model;

import java.time.LocalDate;

// Abstract class representing a transaction that the user is tracking
public abstract class Transaction {

    protected String name;
    protected double value;
    protected LocalDate date;

    // REQUIRES: name not have spaces in it & double must be at most two decimal places
    // MODIFIES: this
    // EFFECTS: Instantiates a transaction class
    public Transaction(String name, double value, LocalDate date) {
        this.name = name;
        this.value = value;
        this.date = date;
    }

    // REQUIRES: value > 0 AND 0 <= value decimal places <= 2
    // MODIFIES: this
    // EFFECTS: Sets the new value of the transaction
    public void setValue(double value) {
        this.value = value;
    }

    // EFFECTS: Returns the value of the transaction
    public double getValue() {
        return this.value;
    }

    // MODIFIES: this
    // EFFECTS: Sets the name for the transaction
    public void setName(String name) {
        this.name = name;
    }

    // EFFECTS: Returns the name for the transaction
    public String getName() {
        return this.name;
    }

    // MODIFIES: this
    // EFFECTS: Set the date of the transaction
    public void setDate(LocalDate date) {
        this.date = date;
    }

    // EFFECTS: Returns the date of the transaction
    public LocalDate getDate() {
        return this.date;
    }

    // EFFECTS: Returns the display name of the transaction
    public abstract String getDisplayName();

}
