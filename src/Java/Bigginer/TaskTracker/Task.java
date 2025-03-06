package Java.Bigginer.TaskTracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private static int idCounter = 1;

    private int id;
    private String description;
    private String status; // "todo", "in-progress", "completed"
    private String createdAt;
    private String updatedAt;

    public Task(String description) {
        this.id = idCounter++;
        this.description = description;
        this.status = "todo"; // Default status
        this.createdAt = getCurrentTimestamp();
        this.updatedAt = this.createdAt;
    }

    public void update(String newDescription) {
        this.description = newDescription;
        this.updatedAt = getCurrentTimestamp();
    }

    public void markInProgress() {
        this.status = "in-progress";
        this.updatedAt = getCurrentTimestamp();
    }

    public void markDone() {
        this.status = "done";
        this.updatedAt = getCurrentTimestamp();
    }

    // Convert Java.Bigginer.TaskTracker.Task to JSON string
    public String toJson() {
        return String.format("{\"id\":%d,\"description\":\"%s\",\"status\":\"%s\",\"createdAt\":\"%s\",\"updatedAt\":\"%s\"}",
                id, description, status, createdAt, updatedAt);
    }

    // Convert JSON string to Java.Bigginer.TaskTracker.Task object
    public static Task fromJson(String json) {
        String[] parts = json.replace("{", "").replace("}", "").split(",");
        int id = Integer.parseInt(parts[0].split(":")[1]);
        String description = parts[1].split(":")[1].replace("\"", "");
        String status = parts[2].split(":")[1].replace("\"", "");
        String createdAt = parts[3].split(":")[1].replace("\"", "");
        String updatedAt = parts[4].split(":")[1].replace("\"", "");

        Task task = new Task(description);
        task.id = id;
        task.status = status;
        task.createdAt = createdAt;
        task.updatedAt = updatedAt;
        return task;
    }

    private static String getCurrentTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public String toString() {
        return String.format("[%d] %s (%s)", id, description, status);
    }

    public int getId() { return id; }
    public String getStatus() { return status; }
}
