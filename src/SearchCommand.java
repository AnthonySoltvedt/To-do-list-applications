import java.util.Scanner;

public class SearchCommand extends BaseCommand {

    public SearchCommand(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter keyword to search: ");
        String keyword = scanner.nextLine();
        taskManager.searchTask(keyword);
    }

    @Override
    public String getCommandName() {
        return "search";
    }
}