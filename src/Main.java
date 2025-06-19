import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        TaskStorage storage = new TaskStorage();
        List<TaskTracker> taskTrackers = new ArrayList<>();
        int id = 1;
        while (true) {
            System.out.print("task-cli > ");
            String task = sc.nextLine().trim();
            if (task.contains("add")) {
                List<String> tasks = Arrays.asList(task.split("\""));
                System.out.println(tasks);
                taskTrackers = storage.loadTask();
                if (taskTrackers.isEmpty()) {
                    processSaveTask(storage, taskTrackers, id, tasks);
                } else {
                    id = taskTrackers.size() + 1;
                    processSaveTask(storage, taskTrackers, id, tasks);
                }
            } else if (task.contains("delete")) {
                processDeleteTask(task, storage);
            } else if (task.contains("list")) {
                getListTask(task, storage);

            } else if (task.contains("update")) {
                processUpdateTask(task, storage);
            } else if (task.contains("mark")) {
                List<String> inputTasks = Arrays.asList(task.split(" "));
                int idInput = Integer.parseInt(inputTasks.get(1));
                taskTrackers = storage.loadTask();
                taskTrackers.stream().filter(taskTracker -> taskTracker.getId() == idInput).forEach(taskTracker -> {
                    if (task.contains("in-progress")) {
                        taskTracker.setStatus(TaskStatus.IN_PROGRESS.getValue());
                    } else if (task.contains("done")) {
                        taskTracker.setStatus(TaskStatus.DONE.getValue());
                    } else if (task.contains("todo")) {
                        taskTracker.setStatus(TaskStatus.TODO.getValue());
                    }
                });
                storage.saveTask(taskTrackers);
                System.out.println("Task mark successfully");
            }
        }
    }

    private static void processDeleteTask(String task, TaskStorage storage) throws IOException {
        List<TaskTracker> taskTrackers;
        List<String> tasks = Arrays.asList(task.split(" "));
        int idDeleted = Integer.parseInt(tasks.get(1));
        taskTrackers = storage.loadTask();
        System.out.println(taskTrackers.size());
        taskTrackers.removeIf(taskTracker -> taskTracker.getId() == idDeleted);
        storage.saveTask(taskTrackers);
        System.out.println("Task deleted successfully");
    }

    private static void processUpdateTask(String task, TaskStorage storage) throws IOException {
        List<TaskTracker> taskTrackers;
        List<String> inputTasks = Arrays.asList(task.split("\"")); //update 1 "message xxx" -> [update 1 , message xxx]
        String prefix = inputTasks.get(0);//
        int idInput = Integer.parseInt(prefix.split(" ")[1]);
        String descUpdate = inputTasks.get(1);
        taskTrackers = storage.loadTask();
        taskTrackers.stream().filter(taskTracker -> taskTracker.getId() == idInput).forEach(taskTracker -> {
            taskTracker.setDescription(descUpdate);
            taskTracker.setUpdated(new Date());
        });
        storage.saveTask(taskTrackers);
        System.out.println("Task updated successfully");
    }

    private static void getListTask(String task, TaskStorage storage) throws IOException {
        List<String> tasks = Arrays.asList(task.split(" "));
        if (tasks.size() == 1) {
            storage.listTask(storage.loadTask());
        } else {
            TaskStatus status = TaskStatus.valueOf(tasks.get(1).toUpperCase());
            storage.listTaskByStatus(storage.loadTask(), status);
        }
    }

    private static void processSaveTask(TaskStorage storage, List<TaskTracker> taskTrackers, int id, List<String> tasks) throws IOException {
        TaskStatus status = TaskStatus.TODO;
        TaskTracker taskTracker = new TaskTracker(id, tasks.get(1).trim(), status.getValue(), new Date(), null);
        taskTrackers.add(taskTracker);
        storage.saveTask(taskTrackers);
        System.out.printf("Task added successfully (ID: %s)", id);
    }
}