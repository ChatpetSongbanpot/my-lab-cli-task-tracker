import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {
        ConsoleUI ui = new ConsoleUI();
        TaskStorage storage = new TaskStorage();
        while (true) {
            String input = ui.input("task-cli");
            String actionType = CommandParser.getActionType(input);
            String description = CommandParser.getDescription(input);
            switch (actionType) {
                case "add":
                    ui.show(storage.addTask(description));
                    break;
                case "update":
                    int updateId = CommandParser.getId(input);
                    ui.show(storage.updateDescriptionById(description, updateId));
                    break;
                case "delete":
                    int deleteId = CommandParser.getId(input);
                    ui.show(storage.deleteTaskById(deleteId));
                    break;
                case "list":
                    if (input.split(" ").length == 1) {
                        storage.listAll();
                    } else {
                        String status = CommandParser.getStatus(input);
                        storage.listByStatus(status);
                    }
                    break;
                default:
                    if (input.startsWith("mark")) {
                        String[] parts = input.split(" ");
                        TaskStatus status = null;
                        int markId = Integer.parseInt(parts[1]);
                        if (parts[0].equals("mark-in-progress")) {
                            status = TaskStatus.IN_PROGRESS;
                        } else if (parts[0].equals("mark-done")) {
                            status = TaskStatus.DONE;
                        } else if (parts[0].equals("mark-todo")) {
                            status = TaskStatus.TODO;
                        }

                        if (status != null) {
                            storage.markStatus(markId, status.getValue());
                        } else {
                            ui.showError("Invalid task status");
                        }

                    } else {
                        ui.showError("Unknown command: " + input);
                    }

            }
        }
    }

}