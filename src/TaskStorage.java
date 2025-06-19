import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskStorage {
    private static final Logger logger = Logger.getLogger(TaskStorage.class.getName());

    private static final String FILENAME = "tasks.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    private List<TaskTracker> loadTask() throws IOException {

        try {
            File file = new File(FILENAME);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            return mapper.readValue(file, new TypeReference<List<TaskTracker>>() {
            });
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return List.of();
        }
    }

    public void saveTask(List<TaskTracker> taskTrackers) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(FILENAME), taskTrackers);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    public String addTask(String description) throws IOException {
        List<TaskTracker> taskTrackers = loadTask();
        int max = taskTrackers.stream().mapToInt(TaskTracker::getId).max().orElse(0);
        int newId = max + 1;
        TaskTracker taskTracker = new TaskTracker(newId
                , description
                , TaskStatus.TODO.getValue());
        taskTrackers.add(taskTracker);
        saveTask(taskTrackers);
        return String.format("Task added successfully (ID: %d)", newId);
    }

    public String updateDescriptionById(String description, int id) throws IOException {
        List<TaskTracker> taskTrackers = loadTask();
        taskTrackers.stream().filter(taskTracker -> taskTracker.getId() == id).forEach(taskTracker -> {
            taskTracker.setDescription(description);
        });
        saveTask(taskTrackers);
        return String.format("Task updated successfully (ID: %d)", id);
    }

    public String deleteTaskById(int id) throws IOException {
        List<TaskTracker> taskTrackers = loadTask();
        taskTrackers.removeIf(taskTracker -> taskTracker.getId() == id);
        saveTask(taskTrackers);
        return String.format("Task deleted successfully (ID: %d)", id);
    }

    public void listAll() throws IOException {
        List<TaskTracker> taskTrackers = loadTask();
        taskTrackers.forEach(taskTracker -> {
            System.out.printf("Task ID: %d %s %s %n"
                    , taskTracker.getId()
                    , taskTracker.getDescription()
                    , taskTracker.getStatus());
        });
    }

    public void listByStatus(String status) throws IOException {
        List<TaskTracker> taskTrackers = loadTask();
        taskTrackers.stream().filter(taskTracker -> Objects.equals(taskTracker.getStatus(), status)).forEach(taskTracker -> {
            System.out.printf("Task ID: %d %s %s %n"
                    , taskTracker.getId()
                    , taskTracker.getDescription()
                    , taskTracker.getStatus());
        });
    }

    public void markStatus(int markId, String status) throws IOException {
        List<TaskTracker> taskTrackers = loadTask();
        taskTrackers.stream().filter(taskTracker -> taskTracker.getId() == markId).forEach(taskTracker -> {
            taskTracker.setStatus(status);
        });
        saveTask(taskTrackers);
    }
}
