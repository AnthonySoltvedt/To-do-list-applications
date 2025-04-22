import java.util.Scanner;

public class MarkCompleteCommand extends BaseCommand {

    public MarkCompleteCommand(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Task ID to mark as complete: ");
        int completeId = scanner.nextInt();
        scanner.nextLine();
        taskManager.markTaskComplete(completeId);
    }

    @Override
    public String getCommandName() {
        return "mark complete";
    }
}
