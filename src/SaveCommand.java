public class SaveCommand extends BaseCommand {

    public SaveCommand(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public void execute() {
        taskManager.saveTasksToFile();
        System.out.println(Colour.green("Tasks saved manually!"));
    }

    @Override
    public String getCommandName() {
        return "save";
    }
}
