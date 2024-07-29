
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AstronautSchedulerApp {
    public static void main(String[] args) {
        ScheduleManager manager = ScheduleManager.getInstance();
        TaskObserver observer = new ConsoleTaskObserver();
        Scanner scanner = new Scanner(System.in);

        LogManager.logInfo("AstronautSchedulerApp started.");
        handleUserChoice(manager, scanner, observer);
        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\nAstronaut Scheduler Menu:");
        System.out.println("1. Add Task");
        System.out.println("2. View All Tasks");
        System.out.println("3. Edit Task");
        System.out.println("4. Mark Task as Completed");
        System.out.println("5. View Tasks by Priority");
        System.out.println("6. Remove Task");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice(Scanner scanner) {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.next(); // Clear the invalid input
            return -1; // Invalid choice
        }
    }

    private static void handleUserChoice(ScheduleManager manager, Scanner scanner, TaskObserver observer) {
        showMenu();
        int choice = getUserChoice(scanner);

        if (choice == 7) {
            LogManager.logInfo("Exiting application.");
            System.out.println("Exiting...");
            return; // Exit the recursion and thus the program
        }

        try {
            switch (choice) {
                case 1:
                    addTask(manager, scanner, observer);
                    break;
                case 2:
                    viewTasks(manager);
                    break;
                case 3:
                    editTask(manager, scanner);
                    break;
                case 4:
                    markTaskCompleted(manager, scanner);
                    break;
                case 5:
                    viewTasksByPriority(manager, scanner);
                    break;
                case 6:
                    removeTask(manager, scanner);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } catch (Exception e) {
            LogManager.logError("An unexpected error occurred: " + e.getMessage(), e);
            System.out.println("An unexpected error occurred. Please try again.");
        }

        handleUserChoice(manager, scanner, observer); // Recursively call the method for next user input
    }

    private static void removeTask(ScheduleManager manager, Scanner scanner) {
        System.out.print("Enter the description of the task to be deleted: ");
        String description = scanner.next();
        boolean a= manager.removeTask(description);
        if(a==true)
        {
            System.out.println("Task Deleted successfully");
        }
        else
        {
            System.out.println("Task not found");
        }
    }

    private static void addTask(ScheduleManager manager, Scanner scanner, TaskObserver observer) {
        System.out.print("Enter task description: ");
        String description = scanner.next();
        System.out.print("Enter start time (HH:mm): ");
        String startTime = scanner.next();
        System.out.print("Enter end time (HH:mm): ");
        String endTime = scanner.next();
        System.out.print("Enter priority (High, Medium, Low): ");
        String priority = scanner.next();

        Task task = TaskFactory.createTask(description, startTime, endTime, priority);
        if (task != null && manager.addTask(task)) {
            observer.notify("Task added successfully. No conflicts.");
        } else {
            observer.notify("Error: Task conflicts with an existing task or invalid input.");
        }
    }

    private static void viewTasks(ScheduleManager manager) {
        List<Task> tasks = manager.viewTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (Task task : tasks) {
                System.out.println(task.getStartTime() + " - " + task.getEndTime() + ": " + task.getDescription() + " [" + task.getPriority() + "]" +task.getCompleted());
            }
        }
    }

    private static void editTask(ScheduleManager manager, Scanner scanner) {
        System.out.print("Enter the description of the task to edit: ");
        String oldDescription = scanner.next();
        System.out.print("Enter new task description: ");
        String newDescription = scanner.next();
        System.out.print("Enter new start time (HH:mm): ");
        String newStartTime = scanner.next();
        System.out.print("Enter new end time (HH:mm): ");
        String newEndTime = scanner.next();
        System.out.print("Enter new priority (High, Medium, Low): ");
        String newPriority = scanner.next();

        manager.editTask(oldDescription, newDescription, newStartTime, newEndTime, newPriority);
    }

    private static void markTaskCompleted(ScheduleManager manager, Scanner scanner) {
        System.out.print("Enter the description of the task to mark as completed: ");
        String description = scanner.next();
        Task task = manager.getTaskByDescription(description);
        if (task != null) {
            task.markCompleted();
            System.out.println("Task marked as completed.");
        } else {
            System.out.println("Task not found.");
        }
    }

    private static void viewTasksByPriority(ScheduleManager manager, Scanner scanner) {
        System.out.print("Enter priority to filter (High, Medium, Low): ");
        String priority = scanner.next();
        List<Task> tasks = manager.viewTasksByPriority(priority);
        if (tasks.isEmpty()) {
            System.out.println("No tasks found with priority " + priority + ".");
        } else {
            for (Task task : tasks) {
                System.out.println(task.getStartTime() + " - " + task.getEndTime() + ": " + task.getDescription() + " [" + task.getPriority() + "]");
            }
        }
    }
}
