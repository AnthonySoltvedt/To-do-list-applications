import java.util.Scanner;

public class ViewCommand extends BaseCommand {

    public ViewCommand(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("View (all/priority/completed): ");
        String filter = scanner.nextLine();
        taskManager.viewTasks(filter);
    }

    @Override
    public String getCommandName() {
        return "view";
    }
}