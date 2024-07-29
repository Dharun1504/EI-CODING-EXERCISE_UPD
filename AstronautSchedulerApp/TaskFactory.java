public class TaskFactory {
    public static Task createTask(String description, String startTime, String endTime, String priority) {
        if (isValidTimeFormat(startTime) && isValidTimeFormat(endTime)) {
            return new Task(description, startTime, endTime, priority);
        } else {
            LogManager.logError("Invalid time format: " + startTime + " - " + endTime, null);
            return null;
        }
    }

    private static boolean isValidTimeFormat(String time) {
        return time.matches("\\d{2}:\\d{2}");
    }
}
