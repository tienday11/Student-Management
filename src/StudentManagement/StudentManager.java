package StudentManagement;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class StudentManager {
    private ArrayList<Student> students;

    public StudentManager() {
        students = new ArrayList<>();
    }

    public void addStudent(String id, String name, double marks) {
        students.add(new Student(id, name, marks));
    }

    public void editStudent(String id, String name, double marks) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                s.setName(name);
                s.setMarks(marks);
                return;
            }
        }
        System.out.println("Student with ID " + id + " not found.");
    }

    public void deleteStudent(String id) {
        students.removeIf(s -> s.getId().equals(id));
    }

    public Student searchStudentById(String id) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    private void selectionSortByMarks() {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                double marks1 = students.get(j).getMarks();
                double marks2 = students.get(minIndex).getMarks();
                if (marks1 < marks2) {
                    minIndex = j;
                }
            }
            // Swap the positions of students
            Student temp = students.get(i);
            students.set(i, students.get(minIndex));
            students.set(minIndex, temp);
        }
    }
    private int partition(ArrayList<Student> list, int low, int high) {
        double pivot = list.get(high).getMarks();
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (list.get(j).getMarks() < pivot) {
                i++;
                Student temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }

        Student temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);

        return i + 1;
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("| ID       | Name                | Marks |");
            System.out.println("------------------------------------------------------------------");
            for (Student student : students) {
                System.out.println(student);
            }
            System.out.println("------------------------------------------------------------------");
        }
    }

    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("-----------------------------------------------------------------------------------------------------------------------");
            System.out.println("| Option | Description                                                      ");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------");
            System.out.println("|   1    | Add Student                                                                ");
            System.out.println("|   2    | Edit Student                                                      ");
            System.out.println("|   3    | Delete Student                                                                       ");
            System.out.println("|   4    | Search Student by ID                                                                  ");
            System.out.println("|   5    | Sort Students by Marks                                                                   ");
            System.out.println("|   6    | Display All Students                                                                     ");
            System.out.println("|   7    | Exit                                                                                    ");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Marks: ");
                    double marks = scanner.nextDouble();
                    manager.addStudent(id, name, marks);
                    break;
                case 2:
                    System.out.print("Enter ID of student to edit: ");
                    id = scanner.nextLine();
                    System.out.print("Enter new Name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter new Marks: ");
                    marks = scanner.nextDouble();
                    manager.editStudent(id, name, marks);
                    break;
                case 3:
                    System.out.print("Enter ID of student to delete: ");
                    id = scanner.nextLine();
                    manager.deleteStudent(id);
                    break;
                case 4:
                    System.out.print("Enter ID of student to search: ");
                    id = scanner.nextLine();
                    Student student = manager.searchStudentById(id);
                    if (student != null) {
                        System.out.println(student);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 5:
                    manager.selectionSortByMarks();
                    break;
                case 6:
                    manager.displayAllStudents();
                    break;
                case 7:
                    scanner.close();
                    System.exit(0);
            }
        }
    }
}


