package Java.Bigginer.TaskTracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private final int id;
    private String description;
    private String status;
    private String createdAt;
    private String updatedAt;

    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.status = "todo"; // Default status
        this.createdAt = getCurrentTimestamp();
        this.updatedAt = this.createdAt;
    }

    public int getId() { return id; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public String getCreatedAt() { return createdAt; }
    public String getUpdatedAt() { return updatedAt; }

    public void setDescription(String description) {
        this.description = description;
        updateTimestamp();
    }

    public void setStatus(String status) {
        if (status.equals("todo") || status.equals("in-progress") || status.equals("done")) {
            this.status = status;
            updateTimestamp();
        } else {
            throw new IllegalArgumentException("Invalid status: " + status);
        }
    }

    private String getCurrentTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    private void updateTimestamp() {
        this.updatedAt = getCurrentTimestamp();
    }

    @Override
    public String toString() {
        return "Task ID: " + id + " | " + description + " | Status: " + status;
    }
}
