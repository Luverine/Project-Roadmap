package Java.Bigginer.TaskTracker.src;

import java.util.List;
import java.util.Scanner;
import Java.Bigginer.TaskTracker.*

public class TaskTrackerCLI {
    public static void main(String[] args) {
        JsonHandler.initializeFile();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter command: ");
            String input = scanner.nextLine();
            String[] commandParts = input.split(" ", 2);

            if (commandParts.length == 0) continue;

            String command = commandParts[0];
            String argument = commandParts.length > 1 ? commandParts[1] : "";

            switch (command) {
                case "add":
                    addTask(argument);
                    break;
                case "update":
                    updateTask(argument);
                    break;
                case "delete":
                    deleteTask(argument);
                    break;
                case "mark-in-progress":
                    markTask(argument, "in-progress");
                    break;
                case "mark-done":
                    markTask(argument, "done");
                    break;
                case "list":
                    listTasks(argument);
                    break;
                case "exit":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid command!");
            }
        }
    }

    private static void addTask(String description) {
        List<Task> tasks = JsonHandler.readTasks();
        Task task = new Task(description);
        tasks.add(task);
        JsonHandler.writeTasks(tasks);
        System.out.println("Task added successfully (ID: " + task.getId() + ")");
    }

    private static void updateTask(String input) {
        List<Task> tasks = JsonHandler.readTasks();
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            System.out.println("Usage: update <id> <new description>");
            return;
        }
        int id = Integer.parseInt(parts[0]);
        String newDescription = parts[1];

        for (Task task : tasks) {
            if (task.getId() == id) {
                task.update(newDescription);
                JsonHandler.writeTasks(tasks);
                System.out.println("Task updated.");
                return;
            }
        }
        System.out.println("Task not found.");
    }

    private static void deleteTask(String idStr) {
        List<Task> tasks = JsonHandler.readTasks();
        int id = Integer.parseInt(idStr);
        tasks.removeIf(task -> task.getId() == id);
        JsonHandler.writeTasks(tasks);
        System.out.println("Task deleted.");
    }

    private static void markTask(String idStr, String status) {
        List<Task> tasks = JsonHandler.readTasks();
        int id = Integer.parseInt(idStr);
        for (Task task : tasks) {
            if (task.getId() == id) {
                if (status.equals("in-progress")) task.markInProgress();
                else task.markDone();
                JsonHandler.writeTasks(tasks);
                System.out.println("Task marked as " + status);
                return;
            }
        }
        System.out.println("Task not found.");
    }

    private static void listTasks(String filter) {
        List<Task> tasks = JsonHandler.readTasks();
        for (Task task : tasks) {
            if (filter.isEmpty() || task.getStatus().equals(filter))
                System.out.println(task);
        }
    }
}
