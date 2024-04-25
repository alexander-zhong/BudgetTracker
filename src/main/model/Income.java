package model;

import org.json.JSONObject;
import persistence.JsonWritable;

import java.time.LocalDate;

// Representing receiving income for the user
public class Income extends Transaction implements JsonWritable {

    // REQUIRES: name not have spaces in it & double must be at most two decimal places
    // EFFECTS: Instantiates an income object with name value date
    public Income(String name, double value, LocalDate date) {
        super(name, value, date);
    }

    // EFFECTS: Return string in the format (Name - Income)
    @Override
    public String getDisplayName() {
        return this.getName() + " | " + this.getDate() + " | " + this.getValue();
    }

    // Modeled with https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: Returns income into a json format
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("name", this.name);
        json.put("value", this.value);
        json.put("date", this.date.toString());

        return json;
    }


}
