import java.io.*;
import java.util.Scanner;

class Student {
    private int rollNumber;
    private String name;
    private String grNumber;
    private String className;

    public Student(int rollNumber, String name, String grNumber, String className) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.grNumber = grNumber;
        this.className = className;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getName() {
        return name;
    }

    public String getGrNumber() {
        return grNumber;
    }

    public String getClassName() {
        return className;
    }
}

public class ADS8 {
    private static final String FILE_NAME = "students.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nStudent Database Menu:");
            System.out.println("1. Add a new student");
            System.out.println("2. Display all students");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addNewStudent();
                    break;
                case 2:
                    displayAllStudents();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 3);

        scanner.close();
    }

    private static void addNewStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter details for the new student:");
        System.out.print("Roll Number: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("GR Number: ");
        String grNumber = scanner.nextLine();
        System.out.print("Class: ");
        String className = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(rollNumber + "," + name + "," + grNumber + "," + className + "\n");
            System.out.println("Student added successfully!");
        } catch (IOException e) {
            System.out.println("Error occurred while adding student: " + e.getMessage());
        }
    }

    private static void displayAllStudents() {
        System.out.println("\nList of all students:");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int rollNumber = Integer.parseInt(parts[0]);
                String name = parts[1];
                String grNumber = parts[2];
                String className = parts[3];
                System.out.println("Roll Number: " + rollNumber + ", Name: " + name + ", GR Number: " + grNumber + ", Class: " + className);
            }
        } catch (IOException e) {
            System.out.println("Error occurred while reading student data: " + e.getMessage());
        }
    }
}