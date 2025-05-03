import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

public class AddCommand extends BaseCommand {
    private final Scanner scanner = new Scanner(System.in);
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public AddCommand(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public String getCommandName() {
        return "add";
    }

    @Override
    public void execute() {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        System.out.print("Enter priority (1-5): ");
        int priority = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter due date (yyyy-MM-dd): ");
        String dateInput = scanner.nextLine();

        try {
            Date dueDate = dateFormat.parse(dateInput);
            taskManager.addTask(description, priority, dueDate);
        } catch (ParseException e) {
            System.out.println(Colour.red("Invalid date format."));
        }
    }
}