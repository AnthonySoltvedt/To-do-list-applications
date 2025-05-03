public class UndoCommand extends BaseCommand {

    public UndoCommand(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public void execute() {
        taskManager.undoLastAction();
    }

    @Override
    public String getCommandName() {
        return "undo";
    }
}