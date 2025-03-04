package Java.Bigginer.TaskTracker;

import java.util.List;

public class TaskManager {
    private List<Task> tasks;

    // Construcor to load tasks from Json when task starts
    public TaskManager {
        tasks = loadTasksFromJson();
    }

    // Adding Tasks
    public void addTask(String description){
        int newId = tasks.isEmpty() ? 1 : tasks.get(tasks.size() - 1).getId() + 1;
        Task newTask = new Task(newId, description);
        tasks.add(newTask);
        saveTaskToJson();
        System.out.println("Task added successfully (ID: " + newId + ")");
    }

    // Updating Tasks
    public void updateTask(int id, String newDescription){
        for (Task task : tasks){
            if(task.getId() == id){
                task.setDescription(newDescription);
                saveTasksToJson();
                System.out.println("Task supdated successfully.");
                return;
            }
        }
        System.out.println("Task ID not found.");
    }

    // Deleting Tasks
    public void deleteTask(int id){
        if(tasks.removeIf(task -> task.getId() == id)){
            saveTasksToJson();
            System.out.println("Task deleted successfully.");
        } else System.out.println("Task ID not found.");
    }
}
