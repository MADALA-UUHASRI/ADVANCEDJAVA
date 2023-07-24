//Task Manager

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Task {
    private String title;
    private String description;
    private int priority;
    private boolean isCompleted;
    private String dueDate;

    public Task(String title, String description, int priority, String dueDate) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.isCompleted = false;
        this.dueDate = dueDate;
    }

    // Getters and Setters

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}

class TaskController {
    private ArrayList<Task> tasks;

    public TaskController() {
        tasks = new ArrayList<>();
    }

    public void createTask(String title, String description, int priority, String dueDate) {
        Task task = new Task(title, description, priority, dueDate);
        tasks.add(task);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void markTaskAsComplete(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.setCompleted(true);
            System.out.println("Task marked as complete: " + task.getTitle());
        } else {
            System.out.println("Invalid task index.");
        }
    }

    // Additional Features

    public void sortTasksByPriority() {
        Collections.sort(tasks, Comparator.comparingInt(Task::getPriority));
    }

    public ArrayList<Task> searchTasksByTitle(String searchTerm) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTitle().toLowerCase().contains(searchTerm.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}

class TaskView {
    public void displayMenu() {
        System.out.println("===== Task Manager Menu =====");
        System.out.println("1. Create a new task");
        System.out.println("2. View the task list");
        System.out.println("3. Update task details");
        System.out.println("4. Mark task as complete");
        System.out.println("5. Sort tasks by priority");
        System.out.println("6. Search for a task");
        System.out.println("7. Set due date for a task");
        System.out.println("8. Exit");
    }

    public void displayTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            System.out.println("===== Task List =====");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                System.out.println((i + 1) + ". " + task.getTitle() + " - Priority: " + task.getPriority() +
                        " - Completion: " + (task.isCompleted() ? "Complete" : "Incomplete") +
                        " - Due Date: " + task.getDueDate());
            }
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}

public class TaskManagerApp {
    public static void main(String[] args) {
        TaskController taskController = new TaskController();
        TaskView taskView = new TaskView();

        while (true) {
            taskView.displayMenu();
            String choice = taskView.getUserInput();

            switch (choice) {
                case "1":
                    System.out.print("Enter task title: ");
                    String title = taskView.getUserInput();
                    System.out.print("Enter task description: ");
                    String description = taskView.getUserInput();
                    System.out.print("Enter task priority (1-5): ");
                    int priority = Integer.parseInt(taskView.getUserInput());
                    System.out.print("Enter due date (e.g., DD-MM-YYYY): ");
                    String dueDate = taskView.getUserInput();
                    taskController.createTask(title, description, priority, dueDate);
                    taskView.displayMessage("Task created successfully.");
                    break;

                case "2":
                    taskView.displayTasks(taskController.getTasks());
                    break;

                case "3":
                    taskView.displayMessage("Not implemented yet.");
                    break;

                case "4":
                    System.out.print("Enter task index to mark as complete: ");
                    int index = Integer.parseInt(taskView.getUserInput()) - 1;
                    taskController.markTaskAsComplete(index);
                    break;

                case "5":
                    taskController.sortTasksByPriority();
                    taskView.displayMessage("Tasks sorted by priority.");
                    break;

                case "6":
                    System.out.print("Enter search term: ");
                    String searchTerm = taskView.getUserInput();
                    ArrayList<Task> matchingTasks = taskController.searchTasksByTitle(searchTerm);
                    taskView.displayTasks(matchingTasks);
                    break;

                case "7":
                    taskView.displayMessage("Not implemented yet.");
                    break;

                case "8":
                    taskView.displayMessage("Exiting Task Manager.");
                    System.exit(0);

                default:
                    taskView.displayMessage("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
