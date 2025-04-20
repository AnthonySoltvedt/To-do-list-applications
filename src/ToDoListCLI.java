import java.text.SimpleDateFormat;
import java.util.Scanner;

public class ToDoListCLI {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<ICommand> commands = new ArrayList<>();
        commands.add(new AddCommand(taskManager));

        // Welcome message
        System.out.println(Colour.magenta("Welcome to the To-Do List CLI!"));
        System.out.println("Here's the current task list:");
        taskManager.viewTasks("all");

        while (true) {
            System.out.println("\nCommands:");
            for(int i = 0; i < commands.size(); i++) {
                ICommand command = commands.get(i);
                System.out.println((i + 1) + ". " + command.getCommandName());
            }
            System.out.println("2. remove");
            System.out.println("3. update");
            System.out.println("4. view [all|priority|completed]");
            System.out.println("5. search");
            System.out.println("6. mark complete");
            System.out.println("7. undo");
            System.out.println("8. exit");

            System.out.print("\nEnter command: ");
            String input = scanner.nextLine().trim().toLowerCase();

            for(int i = 0; i < commands.size(); i++) {
                ICommand command = commands.get(i);
                if (String.valueOf(i+1).equals(input) || input.equals(command.getCommandName())) {
                    command.execute();
                    break;
                }
            }

            if (input.equals("1") || input.equals("add")) {

            } else if (input.equals("2") || input.equals("remove")) {
                System.out.print("Enter Task ID to remove: ");
                int taskId = scanner.nextInt();
                scanner.nextLine();
                taskManager.removeTask(taskId);
            } else if (input.equals("3") || input.equals("update")) {
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
                    System.out.println(Colour.red("Invalid date format."));
                }
            } else if (input.equals("4") || input.equals("view")) {
                System.out.print("View (all/priority/completed): ");
                String filter = scanner.nextLine();
                taskManager.viewTasks(filter);
            } else if (input.equals("5") || input.equals("search")) {
                System.out.print("Enter keyword to search: ");
                String keyword = scanner.nextLine();
                taskManager.searchTask(keyword);
            } else if (input.equals("6") || input.equals("mark complete")) {
                System.out.print("Enter Task ID to mark as complete: ");
                int completeId = scanner.nextInt();
                scanner.nextLine();
                taskManager.markTaskComplete(completeId);
            } else if (input.equals("7") || input.equals("undo")) {
                taskManager.undoLastAction();
            } else if (input.equals("8") || input.equals("exit")) {
                System.out.println(Colour.magenta("Exiting To-Do List..."));
                break;
            } else {
                System.out.println(Colour.red("Invalid command. Try again."));
            }
        }

        scanner.close();
    }
}

