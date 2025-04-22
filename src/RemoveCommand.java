import java.util.Scanner;

public class RemoveCommand extends BaseCommand {

    public RemoveCommand(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public void execute() {
        System.out.print("Enter Task ID to remove: ");
        int taskId = scanner.nextInt();
        scanner.nextLine();
        taskManager.removeTask(taskId);
    }

    @Override
    public String getCommandName() {
        return "remove";
    }
}
