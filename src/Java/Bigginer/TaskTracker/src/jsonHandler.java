package Java.Bigginer.TaskTracker.src;

import java.io.*;
import lib.javax.json.*;
import java.nio.file.*;


public class jsonHandler {
    private static final String FILE_NAME = "tasks.json";

    // Initialize JSON File
    public static void initializeFile() {
        try {
            if (!Files.exists(Path.of(FILE_NAME))) Files.write(Path.of(FILE_NAME), "[]".getBytes());
        } catch (IOException e) {
            System.out.println("Error initializing JSON file: " + e.getMessage());
        }
    }

    // Read tasks from JSON file
    public static JsonArray readTasks() {
        try {
            InputStream is = new FileInputStream(FILE_NAME);
            JsonReader reader = Json.createReader(is);
            JsonArray tasks = reader.readArray();
            reader.close();
            return tasks;
        } catch (Exception e) {
            System.out.println("Error reading tasks: " + e.getMessage());
            return Json.createArrayBuilder().build();
        }
    }


    // Write tasks to JSON file
    public static void writeTasks(JsonArray tasks) {
        try {
            OutputStream os = new FileOutputStream(FILE_NAME);
            JsonWriter writer = Json.createWriter(os);
            writer.writeArray(tasks);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing tasks: " + e.getMessage());
        }
    }

}
