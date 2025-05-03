public class ExitCommand extends BaseCommand {

    public ExitCommand(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public void execute() {
        System.out.println(Colour.magenta("Exiting To-Do List..."));
        System.exit(0);
    }

    @Override
    public String getCommandName() {
        return "exit";
    }
}