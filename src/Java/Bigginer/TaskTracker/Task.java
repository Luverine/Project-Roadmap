package Java.Bigginer.TaskTracker;

public class Task {
    private final int id;
    private String description;
    private String status;
    private final String createdAt;
    private final String updatedAt;

    // Constructors
    public Task(int id, String description){
        this.id = id;
        this.description = description;
        this.status = "Todo";
        this.createdAt = getCurrentTimestamp();
        this.updatedAt = this.createdAt;// updates
    }

    // Getters
    public int getId(){ return id; }
    public String getDescription(){ return description; }
    public String getCreatedAt(){ return createdAt; }
    public String getUpdatedAt(){ return updatedAt; }
    public String getStatus(){ return status; }

    // Setters
    public void setDescription(String description){
        this.description = description;
        updateTimestamp();
    }

    public void setStatus(String status){
        if (status.equals("todo") || status.equals("in-progress") || status.equals("Completed")){
            this.status = status;
            updateTimestamp();
        } else { throw new IllegalArgumentException("Invalid status" + status); }

    // Helper methods
    private String getCurrentTimestamp() { return ""; }

}
