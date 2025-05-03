import java.text.SimpleDateFormat;
import java.util.Date;

public class Task implements Comparable<Task> {
    private static int counter = 1;

    private int id;
    private String description;
    private int priority;
    private Date dueDate;
    private boolean complete;

    public Task(String description, int priority, Date dueDate) {
        this.id = counter++;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.complete = false;
    }

    // Constructor for loading from file
    public Task(int id, String description, int priority, Date dueDate, boolean complete) {
        this.id = id;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.complete = complete;
        if (id >= counter) {
            counter = id + 1;
        }
    }

    public int getId() { return id; }
    public String getDescription() { return description; }
    public int getPriority() { return priority; }
    public Date getDueDate() { return dueDate; }
    public boolean isComplete() { return complete; }

    public void setDescription(String description) { this.description = description; }
    public void setPriority(int priority) { this.priority = priority; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }
    public void markComplete() { this.complete = true; }

    @Override
    public int compareTo(Task other) {
        return Integer.compare(other.priority, this.priority); // higher first
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String status = complete ? "[X]" : "[ ]";
        return String.format("%s ID: %d | %s | Priority: %d | Due: %s",
                status, id, description, priority, format.format(dueDate));
    }
}