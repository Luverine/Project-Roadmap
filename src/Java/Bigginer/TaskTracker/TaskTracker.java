package Java.Bigginer.TaskTracker;
import java.util.*;

public class TaskTracker {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        System.out.println("Welcome to Task Tracker!");
        System.out.println("Type 'help' to see available commands.");

        while (true){
            System.out.println("\nEnter command");
            String input = s.nextLine().trim();
            String[] tokens = input.split(" ",2);
            String command = tokens[0].toLowerCase();
            String argument = tokens.length > 1 ? tokens[1] : "";

            switch (command) {
                case "add":
                    if (!argument.isEmpty()) taskManager.addTask(argument);
                    else System.out.println("Usage : <task_description>");
                    break;
                case "update" :
                    String[] updateArgs = argument.split(" ", 2);
                    if(updateArgs.length == 2){
                        try {
                            int id = Integer.parseInt(updateArgs[0]);
                            taskManager.updateTask(id, updateArgs[1]);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid task ID.");
                        }
                    }
            }
        }
    }
}
