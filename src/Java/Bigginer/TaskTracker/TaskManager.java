package Java.Bigginer.TaskTracker;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    public void addTask(String description) {
        Task newTask = new Task(nextId++, description);
        tasks.add(newTask);
        System.out.println("Java.Bigginer.TaskTracker.Task added successfully (ID: " + newTask.getId() + ")");
    }

    public void updateTask(int id, String newDescription) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setDescription(newDescription);
                System.out.println("Java.Bigginer.TaskTracker.Task updated successfully.");
                return;
            }
        }
        System.out.println("Java.Bigginer.TaskTracker.Task ID not found.");
    }

    public void deleteTask(int id) {
        if (tasks.removeIf(task -> task.getId() == id)) {
            System.out.println("Java.Bigginer.TaskTracker.Task deleted successfully.");
        } else {
            System.out.println("Java.Bigginer.TaskTracker.Task ID not found.");
        }
    }

    public void changeTaskStatus(int id, String status) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setStatus(status);
                System.out.println("Java.Bigginer.TaskTracker.Task status updated to " + status + ".");
                return;
            }
        }
        System.out.println("Java.Bigginer.TaskTracker.Task ID not found.");
    }

    public void listTasks(String statusFilter) {
        for (Task task : tasks) {
            if (statusFilter == null || task.getStatus().equalsIgnoreCase(statusFilter)) {
                System.out.println(task);
            }
        }
    }
}
