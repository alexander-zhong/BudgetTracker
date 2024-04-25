package model;

import org.json.JSONObject;
import persistence.JsonWritable;

import java.time.LocalDate;

// Representing an expense transaction
public class Expense extends Transaction implements JsonWritable {

    private String category;

    // REQUIRES: name not have spaces in it & double must be at most two decimal places
    // EFFECTS: Instantiates a expenses object with name, value, date, and category
    public Expense(String name, double value, LocalDate date, String category) {
        super(name, value, date);
        this.category = category;
    }

    // EFFECTS: returns in display format (Name | Category | Date | Value)
    @Override
    public String getDisplayName() {
        return this.getName() + " | " + this.getCategory() + " | " + this.getDate() + " | " + this.getValue();
    }

    // EFFECTS: Returns the current category
    public String getCategory() {
        return this.category;
    }

    // MODIFIES: this
    // EFFECTS: Sets the category of the expense
    public void setCategory(String category) {
        this.category = category;
    }

    // Modeled with https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: Converts the expense into json format
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("name", this.name);
        json.put("value", this.value);
        json.put("category", this.category);
        json.put("date", this.date.toString());
        return json;
    }


}
