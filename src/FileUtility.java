import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileUtility {

    // Write to file (overwrite or append)
    public static void writeToFile(String filePath, String content, boolean append) {
        try (FileWriter writer = new FileWriter(filePath, append)) {
            writer.write(content);
            System.out.println("✅ Written successfully.");
        } catch (IOException e) {
            System.err.println("❌ Error writing: " + e.getMessage());
        }
    }

    // Read from file
    public static void readFile(String filePath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            System.out.println("📄 File Content:");
            lines.forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("❌ Error reading: " + e.getMessage());
        }
    }

    // Modify text in file
    public static void modifyFile(String filePath, String search, String replace) {
        try {
            Path path = Paths.get(filePath);
            String content = Files.readString(path);
            content = content.replace(search, replace);
            Files.writeString(path, content);
            System.out.println("✅ Modified successfully.");
        } catch (IOException e) {
            System.err.println("❌ Error modifying: " + e.getMessage());
        }
    }

    // Menu system
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String filePath = "sample.txt";
        int choice;

        do {
            System.out.println("\n==== File Handling Utility ====");
            System.out.println("1. Write to file (overwrite)");
            System.out.println("2. Append to file");
            System.out.println("3. Read file");
            System.out.println("4. Modify text in file");
            System.out.println("5. Exit");
            System.out.print("Choose an option (1-5): ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter content to write: ");
                    String content = sc.nextLine();
                    writeToFile(filePath, content + "\n", false);
                }
                case 2 -> {
                    System.out.print("Enter content to append: ");
                    String content = sc.nextLine();
                    writeToFile(filePath, content + "\n", true);
                }
                case 3 -> readFile(filePath);
                case 4 -> {
                    System.out.print("Enter text to search: ");
                    String search = sc.nextLine();
                    System.out.print("Enter replacement text: ");
                    String replace = sc.nextLine();
                    modifyFile(filePath, search, replace);
                }
                case 5 -> System.out.println("👋 Exiting...");
                default -> System.out.println("❌ Invalid choice, try again.");
            }

        } while (choice != 5);

        sc.close();
    }
}
