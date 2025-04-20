public class AddCommand extends BaseCommand {
    public void execute() {
            System.out.print("Enter task description: ");
            String description = scanner.nextLine();
                System.out.print("Enter priority (1-5): ");
                int priority = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.print("Enter due date (yyyy-MM-dd): ");
                String dateInput = scanner.nextLine();
                try {
                    taskManager.addTask(description, priority, dateFormat.parse(dateInput));
                } catch (Exception e) {
                    System.out.println(Colour.red("Invalid date format."));
                }
    }

    public String getCommandName() {
        return "add";
    }
}