package Java.Bigginer.TaskTracker.src;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class JsonHandler {
    private static final String FILE_NAME = "tasks.json";

    // Ensure file exists
    public static void initializeFile() {
        if (!Files.exists(Paths.get(FILE_NAME))) {
            try (FileWriter file = new FileWriter(FILE_NAME)) {
                file.write("[]"); // Initialize with empty JSON array
            } catch (IOException e) {
                System.out.println("Error creating JSON file: " + e.getMessage());
            }
        }
    }

    // Read JSON from file and return list of tasks
    public static List<Task> readTasks() {
        List<Task> tasks = new ArrayList<>();
        try {
            String content = new String(Files.readAllBytes(Paths.get(FILE_NAME)));
            if (content.trim().isEmpty()) return tasks; // Handle empty file

            // Manually parse JSON (very simple)
            if (content.startsWith("[") && content.endsWith("]")) {
                String[] taskStrings = content.substring(1, content.length() - 1).split("},\\{");
                for (String taskString : taskStrings) {
                    if (!taskString.startsWith("{")) taskString = "{" + taskString;
                    if (!taskString.endsWith("}")) taskString += "}";

                    Task task = Task.fromJson(taskString);
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading tasks: " + e.getMessage());
        }
        return tasks;
    }

    // Write list of tasks to JSON file
    public static void writeTasks(List<Task> tasks) {
        try (FileWriter file = new FileWriter(FILE_NAME)) {
            StringBuilder json = new StringBuilder("[");
            for (Task task : tasks) {
                json.append(task.toJson()).append(",");
            }
            if (!tasks.isEmpty()) json.deleteCharAt(json.length() - 1);
            json.append("]");

            file.write(json.toString());
        } catch (IOException e) {
            System.out.println("Error writing tasks: " + e.getMessage());
        }
    }
}
