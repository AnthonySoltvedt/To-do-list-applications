import java.util.*;

public class TaskManager {
    private ArrayList<Task> taskList = new ArrayList<>();
    private PriorityQueue<Task> priorityQueue = new PriorityQueue<>();
    private HashSet<Task> completedTasks = new HashSet<>();
    private Stack<String> actionHistory = new Stack<>();

    public void addTask(String description, int priority, Date dueDate) {
        Task task = new Task(description, priority, dueDate);
        taskList.add(task);
        priorityQueue.add(task);
        actionHistory.push("add " + task.getId());
        System.out.println(Colour.green("Task added successfully!"));
    }

    public void removeTask(int id) {
        taskList.removeIf(task -> task.getId() == id);
        actionHistory.push("remove " + id);
        System.out.println(Colour.cyan("Task removed successfully!"));
    }

    public void updateTask(int id, String newDescription, int newPriority, Date newDueDate) {
        for (Task task : taskList) {
            if (task.getId() == id) {
                task.setDescription(newDescription);
                task.setPriority(newPriority);
                task.setDueDate(newDueDate);
                actionHistory.push("update " + id);
                System.out.println(Colour.blue("Task updated successfully!"));
                return;
            }
        }
    }

    public void markTaskComplete(int id) {
        for (Task task : taskList) {
            if (task.getId() == id) {
                task.markComplete();
                completedTasks.add(task);
                priorityQueue.remove(task);
                actionHistory.push("complete " + id);
                System.out.println(Colour.yellow("Task marked as complete!"));
                return;
            }
        }
    }

    public void viewTasks(String filter) {
        List<Task> sortedTasks = new ArrayList<>(taskList);
        sortedTasks.sort(Comparator.comparing(Task::getPriority).reversed());

        switch (filter.toLowerCase()) {
            case "all":
                sortedTasks.forEach(System.out::println);
                break;
            case "priority":
                priorityQueue.forEach(System.out::println);
                break;
            case "completed":
                completedTasks.forEach(System.out::println);
                break;
        }
    }

    public void searchTask(String keyword) {
        for (Task task : taskList) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(task);
            }
        }
    }

    public void undoLastAction() {
        if (!actionHistory.isEmpty()) {
            String lastAction = actionHistory.pop();
            System.out.println("Undoing: " + lastAction);
            // Implement specific undo logic for different actions
        }
    }
}


