package FileSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileSystem fs = new FileSystem();

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        System.out.println("File System Manager - Commands:");
        System.out.println("1. create <path> - Create a new path");
        System.out.println("2. write <path> <content> - Write content to a file");
        System.out.println("3. read <path> - Read content from a file");
        System.out.println("4. delete <path> - Delete a path");
        System.out.println("5. display - Show the entire file system structure");
        System.out.println("6. exit - Exit the program");

        while (isRunning) {
            System.out.print("nEnter command: ");
            String input = scanner.nextLine().trim();

            String[] parts = input.split("\\s+", 3); // Split input into command, path, and possibly content
            if (parts.length == 0)
                continue; // Skip empty input
            String command = parts[0].toLowerCase(); // Get the command

            try {
                switch (command) {
                    case "create":
                        if (parts.length >= 2) {
                            String path = parts[1]; // Extract path
                            boolean isCreated = fs.createPath(path); // Attempt to create the path
                            System.out.println(isCreated ? "Path created successfully" : "Failed to create path");
                        } else {
                            System.out.println("Usage: create <path>");
                        }
                        break;

                    case "write":
                        if (parts.length >= 3) {
                            String path = parts[1]; // Extract path
                            String content = parts[2]; // Extract content
                            boolean isWritten = fs.setFileContent(path, content); // Attempt to write content
                            System.out.println(
                                    isWritten ? "Content written successfully" : "Failed to write content");
                        } else {
                            System.out.println("Usage: write <path> <content>");
                        }
                        break;

                    case "read":
                        if (parts.length >= 2) {
                            String path = parts[1]; // Extract path
                            String content = fs.getFileContent(path); // Attempt to read content
                            if (content != null) {
                                System.out.println("Content: " + content);
                            } else {
                                System.out.println("Failed to read content");
                            }
                        } else {
                            System.out.println("Usage: read <path>");
                        }
                        break;

                    case "delete":
                        if (parts.length >= 2) {
                            String path = parts[1]; // Extract path
                            boolean isDeleted = fs.deletePath(path); // Attempt to delete the path
                            System.out.println(isDeleted ? "Path deleted successfully" : "Failed to delete path");
                        } else {
                            System.out.println("Usage: delete <path>");
                        }
                        break;

                    case "display":
                        System.out.println("nFile System Structure:");
                        fs.display();
                        break;

                    case "exit":
                        isRunning = false;
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println(
                                "Unknown command. Available commands: create, write, read, delete, display, exit");
                }
            } catch (Exception e) {
                // Handle general exceptions
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
