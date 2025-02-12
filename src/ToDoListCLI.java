import java.util.Scanner;
import java.text.SimpleDateFormat;

public class ToDoListCLI {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        while (true) {
            System.out.println("\nCommands:");
            System.out.println("1. add");
            System.out.println("2. remove");
            System.out.println("3. update");
            System.out.println("4. view [all|priority|completed]");
            System.out.println("5. search");
            System.out.println("6. mark complete");
            System.out.println("7. undo");
            System.out.println("8. exit");

            System.out.print("\nEnter command: ");
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "add":
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter priority (1-5): ");
                    int priority = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter due date (yyyy-MM-dd): ");
                    String dateInput = scanner.nextLine();
                    try {
                        taskManager.addTask(description, priority, dateFormat.parse(dateInput));
                    } catch (Exception e) {
                        System.out.println("Invalid date format.");
                    }
                    break;

                case "remove":
                    System.out.print("Enter Task ID to remove: ");
                    int removeId = scanner.nextInt();
                    scanner.nextLine();
                    taskManager.removeTask(removeId);
                    break;

                case "update":
                    System.out.print("Enter Task ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new description: ");
                    String newDesc = scanner.nextLine();
                    System.out.print("Enter new priority (1-5): ");
                    int newPriority = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new due date (yyyy-MM-dd): ");
                    String newDateInput = scanner.nextLine();
                    try {
                        taskManager.updateTask(updateId, newDesc, newPriority, dateFormat.parse(newDateInput));
                    } catch (Exception e) {
                        System.out.println("Invalid date format.");
                    }
                    break;

                case "view":
                    System.out.print("View (all/priority/completed): ");
                    String filter = scanner.nextLine();
                    taskManager.viewTasks(filter);
                    break;

                case "search":
                    System.out.print("Enter keyword to search: ");
                    String keyword = scanner.nextLine();
                    taskManager.searchTask(keyword);
                    break;

                case "mark complete":
                    System.out.print("Enter Task ID to mark as complete: ");
                    int completeId = scanner.nextInt();
                    scanner.nextLine();
                    taskManager.markTaskComplete(completeId);
                    break;

                case "undo":
                    taskManager.undoLastAction();
                    break;

                case "exit":
                    System.out.println("Exiting To-Do List...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid command. Try again.");
            }
        }
    }
}
