import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ScheduleManager {
    private static ScheduleManager instance;
    private TreeSet<Task> tasks;

    private ScheduleManager() {
        tasks = new TreeSet<>((task1, task2) -> task1.getStartTime().compareTo(task2.getStartTime()));
    }

    public static ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    public boolean addTask(Task task) {
        if (validateTask(task)) {
            tasks.add(task);
            LogManager.logInfo("Task added: " + task.getDescription());
            return true;
        } else {
            LogManager.logWarning("Task conflict detected: " + task.getDescription());
            return false;
        }
    }

    public boolean removeTask(String description) {
        for (Task task : tasks) {
            if (task.getDescription().equals(description)) {
                tasks.remove(task);
                LogManager.logInfo("Task removed: " + description);
                return true;
            }
        }
        LogManager.logWarning("Task not found: " + description);
        return false;
    }

    public List<Task> viewTasks() {
        return tasks.stream().collect(Collectors.toList());
    }

    public boolean editTask(String description, String newDescription, String newStartTime, String newEndTime, String newPriority) {
        for (Task task : tasks) {
            if (task.getDescription().equals(description)) {
                if (newStartTime != null) task.setStartTime(newStartTime);
                if (newEndTime != null) task.setEndTime(newEndTime);
                if (newPriority != null) task.setPriority(newPriority);
                if (newDescription != null) task.setDescription(newDescription);
                LogManager.logInfo("Task edited: " + newDescription);
                return true;
            }
        }
        LogManager.logWarning("Task not found for editing: " + description);
        return false;
    }

    public List<Task> viewTasksByPriority(String priority) {
        return tasks.stream()
                .filter(task -> task.getPriority().equalsIgnoreCase(priority))
                .collect(Collectors.toList());
    }

    private boolean validateTask(Task newTask) {
        for (Task task : tasks) {
            if (task.conflictsWith(newTask)) {
                return false;
            }
        }
        return true;
    }

    public Task getTaskByDescription(String description) {
        for (Task task : tasks) {
            if (task.getDescription().equals(description)) {
                return task;
            }
        }   
        throw new UnsupportedOperationException("Unimplemented method 'getTaskByDescription'");
    }
}
