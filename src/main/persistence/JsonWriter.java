package persistence;

import model.AccountManager;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

// Represents the writer that helps the application write JSON data into the file
public class JsonWriter {
    private String path;
    private PrintWriter writer;


    // EFFECTS: Creates an instance of the file with the path
    public JsonWriter(String path) {
        this.path = path;
    }


    // MODIFIES: this
    // EFFECTS: opens the writer for the file (at path) to be written
    public void setUp() throws FileNotFoundException {
        writer = new PrintWriter(new File(path));
    }

    // MODIFIES: this
    // EFFECTS creates a JSON representation of the AccountManager
    public void saveAccountManager(AccountManager accountManager) {
        JSONObject jsonObj = accountManager.toJson();
        writer.print(jsonObj.toString(4));
        writer.close();
    }



}
