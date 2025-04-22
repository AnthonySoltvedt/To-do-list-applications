import java.text.SimpleDateFormat;
import java.util.Scanner;

public abstract class BaseCommand implements ICommand {
    protected TaskManager taskManager;
    protected Scanner scanner;
    protected SimpleDateFormat dateFormat;

    public BaseCommand(TaskManager taskManager) {
        this.taskManager = taskManager;
        this.scanner = new Scanner(System.in);
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public abstract void execute();
    public abstract String getCommandName();
}
