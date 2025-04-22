import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class TaskManager {
    private ArrayList<Task> taskList = new ArrayList<>();
    private PriorityQueue<Task> priorityQueue = new PriorityQueue<>();
    private HashSet<Task> completedTasks = new HashSet<>();
    private Stack<String> actionHistory = new Stack<>();
    private final String FILE_NAME = "tasks.csv";
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public void addTask(String description, int priority, Date dueDate) {
        Task task = new Task(description, priority, dueDate);
        taskList.add(task);
        priorityQueue.add(task);
        actionHistory.push("add " + task.getId());
        saveTasksToFile();
        System.out.println(Colour.green("Task added successfully!"));
    }

    public void removeTask(int id) {
        taskList.removeIf(task -> task.getId() == id);
        priorityQueue.removeIf(task -> task.getId() == id);
        completedTasks.removeIf(task -> task.getId() == id);
        actionHistory.push("remove " + id);
        saveTasksToFile();
        System.out.println(Colour.cyan("Task removed successfully!"));
    }

    public void updateTask(int id, String newDescription, int newPriority, Date newDueDate) {
        for (Task task : taskList) {
            if (task.getId() == id) {
                task.setDescription(newDescription);
                task.setPriority(newPriority);
                task.setDueDate(newDueDate);
                actionHistory.push("update " + id);
                saveTasksToFile();
                System.out.println(Colour.blue("Task updated successfully!"));
                return;
            }
        }
        System.out.println(Colour.red("Task not found."));
    }

    public void markTaskComplete(int id) {
        for (Task task : taskList) {
            if (task.getId() == id) {
                task.markComplete();
                completedTasks.add(task);
                priorityQueue.remove(task);
                actionHistory.push("complete " + id);
                saveTasksToFile();
                System.out.println(Colour.yellow("Task marked as complete!"));
                return;
            }
        }
        System.out.println(Colour.red("Task not found."));
    }

    public void viewTasks(String filter) {
        List<Task> sortedTasks = new ArrayList<>(taskList);
        sortedTasks.sort(Comparator.comparing(Task::getPriority).reversed());

        switch (filter.toLowerCase()) {
            case "all":
                sortedTasks.forEach(System.out::println);
                break;
            case "priority":
                new ArrayList<>(priorityQueue).forEach(System.out::println);
                break;
            case "completed":
                completedTasks.forEach(System.out::println);
                break;
            default:
                System.out.println(Colour.red("Unknown filter. Use all, priority, or completed."));
        }
    }

    public void searchTask(String keyword) {
        boolean found = false;
        for (Task task : taskList) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(task);
                found = true;
            }
        }
        if (!found) {
            System.out.println(Colour.red("No tasks matched your search."));
        }
    }

    public void undoLastAction() {
        if (!actionHistory.isEmpty()) {
            String lastAction = actionHistory.pop();
            System.out.println("Undoing: " + lastAction);
            // (Undo logic to be implemented)
        } else {
            System.out.println(Colour.red("No actions to undo."));
        }
    }

    public void saveTasksToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Task task : taskList) {
                writer.printf("%d,%s,%d,%s,%b%n",
                        task.getId(),
                        task.getDescription().replace(",", " "),
                        task.getPriority(),
                        dateFormat.format(task.getDueDate()),
                        task.isComplete());
            }
        } catch (IOException e) {
            System.out.println(Colour.red("Error saving tasks: " + e.getMessage()));
        }
    }

    public void loadTasksFromFile() {
        taskList.clear();
        priorityQueue.clear();
        completedTasks.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 5);
                int id = Integer.parseInt(parts[0]);
                String description = parts[1];
                int priority = Integer.parseInt(parts[2]);
                Date dueDate = dateFormat.parse(parts[3]);
                boolean complete = Boolean.parseBoolean(parts[4]);

                Task task = new Task(id, description, priority, dueDate, complete);
                taskList.add(task);
                if (complete) {
                    completedTasks.add(task);
                } else {
                    priorityQueue.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            // File doesn't exist yet — that's fine
        } catch (Exception e) {
            System.out.println(Colour.red("Error loading tasks: " + e.getMessage()));
        }
    }
}

