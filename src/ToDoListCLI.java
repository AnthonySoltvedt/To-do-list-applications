import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;  // <-- Add this import

public class ToDoListCLI {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        // Load saved tasks from file at startup
        taskManager.loadTasksFromFile();

        Scanner scanner = new Scanner(System.in);

        // Initialize commands
        List<ICommand> commands = new ArrayList<>();
        commands.add(new AddCommand(taskManager));
        commands.add(new RemoveCommand(taskManager));
        commands.add(new UpdateCommand(taskManager));
        commands.add(new ViewCommand(taskManager));
        commands.add(new SearchCommand(taskManager));
        commands.add(new MarkCompleteCommand(taskManager));
        commands.add(new UndoCommand(taskManager));  // Assuming you will implement this later

        // Welcome message
        System.out.println(Colour.magenta("Welcome to the To-Do List CLI!"));
        System.out.println("Here's the current task list:");
        taskManager.viewTasks("all");

        while (true) {
            System.out.println("\nCommands:");
            for (int i = 0; i < commands.size(); i++) {
                ICommand command = commands.get(i);
                System.out.println((i + 1) + ". " + command.getCommandName());
            }
            System.out.println("8. exit");

            System.out.print("\nEnter command: ");
            String input = scanner.nextLine().trim().toLowerCase();

            for (int i = 0; i < commands.size(); i++) {
                ICommand command = commands.get(i);
                if (String.valueOf(i + 1).equals(input) || input.equals(command.getCommandName())) {
                    command.execute();
                    break;
                }
            }

            if (input.equals("8") || input.equals("exit")) {
                System.out.println(Colour.magenta("Exiting To-Do List..."));
                break;
            } else {
                System.out.println(Colour.red("Invalid command. Try again."));
            }
        }

        scanner.close();
    }
}
