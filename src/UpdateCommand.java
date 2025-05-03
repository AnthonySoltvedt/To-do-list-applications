import java.util.Scanner;
import java.text.SimpleDateFormat;

public class UpdateCommand extends BaseCommand {

    public UpdateCommand(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

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
    }

    @Override
    public String getCommandName() {
        return "update";
    }
}