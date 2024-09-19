import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.*;

// Student class representing an individual student
class Student {
    private String name;
    private int rollNumber;
    private String grade;

    // Constructor
    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    // toString method to display student information
    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}

// Class to manage the collection of students
class StudentManagementSystem {
    private List<Student> students = new ArrayList<>();

    // Method to add a new student
    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added successfully.");
    }

    // Method to remove a student by roll number
    public void removeStudent(int rollNumber) {
        boolean removed = students.removeIf(student -> student.getRollNumber() == rollNumber);
        if (removed) {
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    // Method to search for a student by roll number
    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        System.out.println("Student not found.");
        return null;
    }

    // Method to display all students
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    // Save students to a file
    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Student student : students) {
                writer.write(student.getName() + "," + student.getRollNumber() + "," + student.getGrade());
                writer.newLine();
            }
            System.out.println("Data saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Load students from a file
public void loadFromFile(String filename) {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        students.clear();  // Clear existing students before loading new data
        while ((line = reader.readLine()) != null) {
            // Split line by commas
            String[] data = line.split(",");
            
            // Ensure there are exactly 3 parts (name, roll number, grade)
            if (data.length == 3) {
                try {
                    String name = data[0].trim();  // Trim whitespace
                    int rollNumber = Integer.parseInt(data[1].trim());  // Parse roll number
                    String grade = data[2].trim();  // Trim whitespace
                    students.add(new Student(name, rollNumber, grade));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid roll number format: " + data[1]);
                }
            } else {
                System.out.println("Invalid line format: " + line);
            }
        }
        System.out.println("Data loaded from file successfully.");
    } catch (IOException e) {
        System.out.println("Error reading from file: " + e.getMessage());
    }
}

}

// Main class for the console-based UI
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();

        // Load students from file at the beginning
        sms.loadFromFile("students.txt");

        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Save to File");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    
                    // Input validation for adding a new student
                    scanner.nextLine();  // Consume leftover newline character from previous input
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();  // Now properly captures the name
                    System.out.print("Enter roll number: ");
                    int rollNumber = scanner.nextInt();
                    scanner.nextLine();  // Consume leftover newline character
                    System.out.print("Enter grade: ");
                    String grade = scanner.next();
                    sms.addStudent(new Student(name, rollNumber, grade));
                    break;


                case 2:
                    System.out.print("Enter roll number to remove: ");
                    rollNumber = scanner.nextInt();
                    sms.removeStudent(rollNumber);
                    break;

                case 3:
                    System.out.print("Enter roll number to search: ");
                    rollNumber = scanner.nextInt();
                    Student student = sms.searchStudent(rollNumber);
                    if (student != null) {
                        System.out.println(student);
                    }
                    break;

                case 4:
                    sms.displayAllStudents();
                    break;

                case 5:
                    sms.saveToFile("students.txt");
                    break;

                case 6:
                    System.out.println("Exiting the application.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
