import java.util.ArrayList;
import java.util.List;

public class Task {
    private String description;
    private String startTime;
    private String endTime;
    private String priority;
    private boolean completed;
    private List<String> tags;
    private String recurrence; // Daily, Weekly, Monthly, etc.

    public Task(String description, String startTime, String endTime, String priority) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
        this.completed = false;
        this.tags = new ArrayList<>();
        this.recurrence = null;
    }

    // Getters and setters
    public String getCompleted()
    {
        if(this.completed==true)
        {
            return "Completed";
        }
        return "Not Completed";
    }
    public void markCompleted() {
        this.completed = true;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public List<String> getTags() {
        return tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(String recurrence) {
        this.recurrence = recurrence;
    }

    public boolean conflictsWith(Task other) {
        return (this.startTime.compareTo(other.endTime) < 0 && this.endTime.compareTo(other.startTime) > 0);
    }

    public void setCompleted(boolean boolean1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCompleted'");
    }
}
