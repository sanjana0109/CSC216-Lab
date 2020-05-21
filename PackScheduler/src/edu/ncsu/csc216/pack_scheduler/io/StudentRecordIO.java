package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;

/**
 * 
 * This class reads student records from files and writes them to another file
 * 
 * @author Walker Clem
 * @author Zeyu Chang
 * @author Sanjana Cheerla
 *
 */
public class StudentRecordIO {

    /**
     * Generates an SortedList of the student records read from the file. If the
     * file can't be found or accessed a File NotFoundException is thrown.
     * 
     * @param fileName the file to read Course records from
     * @return a list of Student objects from the file provided
     * @throws FileNotFoundException is thrown if the file can't be found or read
     */
    public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
        Scanner fileReader = new Scanner(new FileInputStream(fileName));
        SortedList<Student> students = new SortedList<Student>();
        while (fileReader.hasNextLine()) {
            try {
                Student student = processStudent(fileReader.nextLine());
                boolean duplicate = false;
                for (int i = 0; i < students.size(); i++) {
                    User s = students.get(i);
                    if (student.getId().equals(s.getId())) {
                        // duplicate student
                        duplicate = true;
                    }
                }
                if (!duplicate) {
                    students.add(student);
                }
            } catch (IllegalArgumentException e) {
                // skip the line
            }
        }
        fileReader.close();
        return students;
    }

    /**
     * Processes a line from the file and returns a constructed Student object from
     * it.
     * 
     * @param line the line that is being processed
     * @return a Student object that gets constructed from the input parameter line
     * @throws IllegalArgumentException if the course is in an invalid format.
     */
    private static Student processStudent(String line) {
        Scanner lineScanner = new Scanner(line);
        lineScanner.useDelimiter(",");
        try {
            try {

                String fName = lineScanner.next();
                String lName = lineScanner.next();
                String id = lineScanner.next();
                String email = lineScanner.next();
                String password = lineScanner.next();
                int credits = lineScanner.nextInt();

                lineScanner.close();

                Student s = new Student(fName, lName, id, email, password, credits);
                return s;

            } catch (NoSuchElementException e) {
                throw new IllegalArgumentException();
            }

        }

        catch (InputMismatchException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Using PrintStream as writer main method.
     * 
     * @param fileName file name
     * @param courses  course name and section
     * @throws IOException throw an IO exception
     */
    public static void writeStudentRecords(String fileName, SortedList<Student> courses) throws IOException {
        PrintStream fileWriter = new PrintStream(new File(fileName));

        for (int i = 0; i < courses.size(); i++) {
            fileWriter.println(courses.get(i).toString());
        }

        fileWriter.close();

    }

}
