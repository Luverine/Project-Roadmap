package Java.Bigginer.TaskTracker.src;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private final int id;
    private String description;
    private String status; // "todo", "in-progress", "done"
    private String createdAt;
    private String updatedAt;

    // Constructor
    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.status = "todo"; // Default status
        this.createdAt = getCurrentTimestamp();
        this.updatedAt = this.createdAt;
    }

    // Getters
    public int getId() { return id; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public String getCreatedAt() { return createdAt; }
    public String getUpdatedAt() { return updatedAt; }

    // Setters
    public void setDescription(String description) {
        this.description = description;
        updateTimestamp();
    }

    public void setStatus(String status) {
        if (status.equals("todo") || status.equals("in-progress") || status.equals("done")) {
            this.status = status;
            updateTimestamp();
        } else throw new IllegalArgumentException("Invalid status: " + status);
    }

    // Timestamp management
    private String getCurrentTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    private void updateTimestamp() {
        this.updatedAt = getCurrentTimestamp();
    }

    // Convert Task object to String
    @Override
    public String toString() {
        return "Task ID: " + id + "\nDescription: " + description + "\nStatus: " + status +
                "\nCreated At: " + createdAt + "\nUpdated At: " + updatedAt + "\n";
    }
}
