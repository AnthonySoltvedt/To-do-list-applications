import java.util.Date;

public class Task implements Comparable<Task> {
    private static int idCounter = 1;
    private int id;
    private String description;
    private int priority;  // Higher number = higher priority
    private Date dueDate;
    private boolean isCompleted;

    public Task(String description, int priority, Date dueDate) {
        this.id = idCounter++;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.isCompleted = false;
    }

    public int getId() { return id; }
    public String getDescription() { return description; }
    public int getPriority() { return priority; }
    public Date getDueDate() { return dueDate; }
    public boolean isCompleted() { return isCompleted; }

    public void setDescription(String description) { this.description = description; }
    public void setPriority(int priority) { this.priority = priority; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }
    public void markComplete() { this.isCompleted = true; }

    @Override
    public int compareTo(Task other) {
        return Integer.compare(other.priority, this.priority);
    }

    @Override
    public String toString() {
        return "Task ID: " + id + " | " + description +
                " | Priority: " + priority +
                " | Due: " + dueDate +
                " | Status: " + (isCompleted ? "Completed" : "Pending");
    }
}
