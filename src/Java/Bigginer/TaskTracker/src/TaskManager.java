package Java.Bigginer.TaskTracker.src;

import lib.javax.json.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class TaskManager {
    private List<Task> tasks;

    // Constructor - Loads tasks from JSON when the program starts
    public TaskManager() {
        tasks = loadTasksFromJson();
    }

    // 1. Add a task
    public void addTask(String description) {
        int newId = tasks.isEmpty() ? 1 : tasks.get(tasks.size() - 1).getId() + 1;
        Task newTask = new Task(newId, description);
        tasks.add(newTask);
        saveTasksToJson();
        System.out.println("Task added successfully (ID: " + newId + ")");
    }

    // 2. Update a task
    public void updateTask(int id, String newDescription) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setDescription(newDescription);
                saveTasksToJson();
                System.out.println("Task updated successfully.");
                return;
            }
        }
        System.out.println("Task ID not found.");
    }

    // 3. Delete a task
    public void deleteTask(int id) {
        if (tasks.removeIf(task -> task.getId() == id)) {
            saveTasksToJson();
            System.out.println("Task deleted successfully.");
        } else {
            System.out.println("Task ID not found.");
        }
    }

    // 4. Change task status
    public void changeTaskStatus(int id, String status) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                try {
                    task.setStatus(status);
                    saveTasksToJson();
                    System.out.println("Task status updated to " + status + ".");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                return;
            }
        }
        System.out.println("Task ID not found.");
    }

    // 5. List all tasks
    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            tasks.forEach(System.out::println);
        }
    }

    // 6. List tasks by status
    public void listTasksByStatus(String status) {
        boolean found = false;
        for (Task task : tasks) {
            if (task.getStatus().equals(status)) {
                System.out.println(task);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No tasks found with status: " + status);
        }
    }

    // 7. Load tasks from JSON file
    private List<Task> loadTasksFromJson() {
        List<Task> taskList = new ArrayList<>();
        JsonArray jsonArray = jsonHandler.readTasks();

        for (JsonValue value : jsonArray) {
            JsonObject jsonTask = value.asJsonObject();
            Task task = new Task(
                    jsonTask.getInt("id"),
                    jsonTask.getString("description")
            );
            task.setStatus(jsonTask.getString("status"));
            taskList.add(task);
        }
        return taskList;
    }

    // 8. Save tasks to JSON file
    private void saveTasksToJson() {
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (Task task : tasks) {
            jsonArrayBuilder.add(Json.createObjectBuilder()
                    .add("id", task.getId())
                    .add("description", task.getDescription())
                    .add("status", task.getStatus())
                    .add("createdAt", task.getCreatedAt())
                    .add("updatedAt", task.getUpdatedAt())
            );
        }
        jsonHandler.writeTasks(jsonArrayBuilder.build());
    }
}
