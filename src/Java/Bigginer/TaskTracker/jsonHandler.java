package Java.Bigginer.TaskTracker;

import java.io.*;
import lib.javax.json.*;
import java.nio.file.*;


public class jsonHandler {
    private static final String FILE_NAME = "task.json";

    // check whether the file exists or not
    public static boolean exists() {
        return Files.exists(Paths.get(FILE_NAME));
    }
    //Initializing JSON File
    public static void initializeFile() {
        try {
            if (!exists()) Files.write(Path.of(FILE_NAME), "[]".getBytes());
        } catch (IOException e) {
            System.out.println("Error Initializing JSON file: " + e.getMessage());
        }
    }

    // Reading Tasks JSON File
    public static JsonArray readTask() {
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

    // Writing Tasks in JSON File
    public static void writeTasks(JsonArray tasks) throws FileNotFoundException {
        try {
            OutputStream os = new FileOutputStream(FILE_NAME);
            JsonWriter writer = Json.createWriter(os);
            writer.writeArray(tasks);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing tasks : " + e.getMessage());
        }
    }
}
