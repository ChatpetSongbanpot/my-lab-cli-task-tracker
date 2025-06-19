import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskStorage {
    private static final Logger logger = Logger.getLogger(TaskStorage.class.getName());

    private static final String FILENAME = "tasks.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    public List<TaskTracker> loadTask() throws IOException {

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


    public void listTask(List<TaskTracker> taskTrackers) {
        for (TaskTracker taskTracker : taskTrackers) {
            System.out.printf("ID:%d, Task : %s, Status : %s \n",
                    taskTracker.getId(),
                    taskTracker.getDescription(),
                    taskTracker.getStatus());
        }
    }

    public void listTaskByStatus(List<TaskTracker> taskTrackers,TaskStatus status) {
        for (TaskTracker taskTracker : taskTrackers) {
            if (taskTracker.getStatus().equals(status.getValue())) {
                System.out.printf("ID:%d, Task : %s, Status : %s \n",
                        taskTracker.getId(),
                        taskTracker.getDescription(),
                        taskTracker.getStatus());
            }

        }
    }
}
