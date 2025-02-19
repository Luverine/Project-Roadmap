package Java.Bigginer.TaskTracker;

import java.io.*;
import javax.json.*;
import java.nio.file.*;


public class jsonHandler {
    private String FILE_NAME = "task.json";
    //Initializing JSON File
    public static void initializeFile() {
        try {
            if (!File.exists(path.of(FILE_NAME))) {
                File.write(path.of(FILE_NAME), "[]".getBytes());
            }
        } catch (IOException e) {
            System.out.println("Error Initializing JSON file: " + e.getMessage());
        }
    }

    // Reading JSON File
    public static JsonArray readTask() {
        try {
            InputStream is = new FileInputStream(FILE_NAME);
            JsonReader reader = new JsonReader(is);
            JsonArray tasks = reader.readArray();

        }
    }

}
