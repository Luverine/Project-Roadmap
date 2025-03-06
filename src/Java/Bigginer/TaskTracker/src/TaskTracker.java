package Java.Bigginer.TaskTracker.src;

import java.util.Scanner;

public class TaskTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        System.out.println("Welcome to Task Tracker!");
        System.out.println("Type 'help' to see available commands.");

        while (true) {
            System.out.print("\nEnter command: ");
            String input = scanner.nextLine().trim();
            String[] tokens = input.split(" ", 2);
            String command = tokens[0].toLowerCase();
            String argument = tokens.length > 1 ? tokens[1] : "";

            switch (command) {
                case "add":
                    if (!argument.isEmpty()) {
                        taskManager.addTask(argument);
                    } else {
                        System.out.println("Usage: add <task_description>");
                    }
                    break;

                case "update":
                    String[] updateArgs = argument.split(" ", 2);
                    if (updateArgs.length == 2) {
                        try {
                            int id = Integer.parseInt(updateArgs[0]);
                            taskManager.updateTask(id, updateArgs[1]);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid task ID.");
                        }
                    } else {
                        System.out.println("Usage: update <task_id> <new_description>");
                    }
                    break;

                case "delete":
                    try {
                        int id = Integer.parseInt(argument);
                        taskManager.deleteTask(id);
                    } catch (NumberFormatException e) {
                        System.out.println("Usage: delete <task_id>");
                    }
                    break;

                case "status":
                    String[] statusArgs = argument.split(" ", 2);
                    if (statusArgs.length == 2) {
                        try {
                            int id = Integer.parseInt(statusArgs[0]);
                            taskManager.changeTaskStatus(id, statusArgs[1]);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid task ID.");
                        }
                    } else {
                        System.out.println("Usage: status <task_id> <todo|in-progress|done>");
                    }
                    break;

                case "list":
                    if (argument.isEmpty()) {
                        taskManager.listTasks();
                    } else {
                        taskManager.listTasksByStatus(argument);
                    }
                    break;

                case "exit":
                    System.out.println("Exiting Task Tracker. Goodbye!");
                    scanner.close();
                    return;

                case "help":
                    printHelp();
                    break;

                default:
                    System.out.println("Unknown command. Type 'help' for available commands.");
                    break;
            }
        }
    }

    private static void printHelp() {
        System.out.println("\nAvailable Commands:");
        System.out.println("  add <description>       - Add a new task");
        System.out.println("  update <id> <desc>      - Update task description");
        System.out.println("  delete <id>             - Delete a task");
        System.out.println("  status <id> <status>    - Change task status (todo, in-progress, done)");
        System.out.println("  list                    - List all tasks");
        System.out.println("  list <status>           - List tasks by status");
        System.out.println("  exit                    - Exit the application");
    }
}
