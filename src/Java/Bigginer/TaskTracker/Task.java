package Java.Bigginer.TaskTracker;

public class Task {
    private int id;
    private final String description;
    private final String status;
    private final String createdAt;
    private final String updatedAt;

    // Constructors
    public Task(int id, String description){
        this.id = id;
        this.description = description;
        this.status = "Todo";
        this.createdAt = getCurrentTimestamp();
        this.updatedAt = this.createdAt;
    }

    // Getters
    public int getId(){ return id; }
    public String getDescription(){ return description; }
}
